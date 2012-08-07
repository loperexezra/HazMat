package com.rel.hazmat.activity;

import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.UpdateManager;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.actionbarsherlock.view.Window;
import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.rel.hazmat.R;
import com.rel.hazmat.actionbar.ABSearchActivity;
import com.rel.hazmat.csv.ChemicalCSVReader;
import com.rel.hazmat.db.DBHelper;
import com.rel.hazmat.db.dao.HazardousMaterialDAO;
import com.rel.hazmat.db.model.HazardousMaterial;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class ICSSearchActivity extends ABSearchActivity {
    protected static final String APP_ID = "6ba842081fff4c250d845c56ab83414b";
    public static final String TAG = "ICSSearchActivity";
    static final String[] COUNTRIES = new String[] { "Carbonyl Flouride",
            "Propane", "Helium", "Flourine, Compressed" };
    @Inject
    protected DBHelper helper;
    @Inject
    protected HazardousMaterialDAO materialDAO;
    @InjectView(R.id.edtSearchName)
    protected EditText edtSearchName;
    @InjectView(R.id.edtSearchUNID)
    protected EditText edtSearchUNID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        checkForCrashes();
        requestWindowFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ics_search);
        // loadCSV();
        checkForUpdates();
        if (Build.VERSION.SDK_INT >= 11) {
            edtSearchName.setTextColor(0xffffffff);
            edtSearchUNID.setTextColor(0xffffffff);
        }
    }

    private void checkForCrashes() {
        CrashManager.register(this, APP_ID);
    }

    private void checkForUpdates() {
        UpdateManager.register(this, APP_ID);
    }

    public void loadCSV() {
        ChemicalCSVReader reader = new ChemicalCSVReader(this, materialDAO);
        reader.loadToDatabase();
    }

    public void onClickSearch(View view) {
        decideQueryOrUN();
    }

    public void decideQueryOrUN() {
        if (isUNFilled()) {
            searchUNID(Integer.parseInt(edtSearchUNID.getText().toString()));
        } else
            launchQuery(edtSearchName.getText().toString());
    }

    public void launchQuery(String query) {
        if (query != null) {
            Intent intent = new Intent();
            intent.setClass(this, SearchResultActivity.class);
            Log.i(TAG, "Sending query string : "
                    + edtSearchName.getText().toString());
            String trimmedQuery = query.trim();
            intent.putExtra(SearchResultActivity.SEARCH_QUERY, trimmedQuery);
            startActivity(intent);
        }
    }

    public void searchUNID(Integer UNID) {
        HazardousMaterial material = materialDAO.getMaterialUsingUNID(UNID);
        if (material != null) {
            Intent intent = new Intent();
            intent.setClass(this, ViewChoicesActivity.class);
            intent.putExtra(ViewChoicesActivity.SEARCH_QUERY,
                    material.getName());
            intent.putExtra(ViewChoicesActivity.MATERIAL_SLUG,
                    material.getSlug());
            startActivity(intent);
        } else {
            Toast.makeText(this, "UNID does not exist in our servers",
                    Toast.LENGTH_LONG).show();
        }
    }

    private boolean isUNFilled() {
        return !Strings.isNullOrEmpty(edtSearchUNID.getText().toString());
    }

    public void onClickSearchUnknown(View view) {
        Intent intent = new Intent();
        intent.setClass(this, SOGActivity.class);
        startActivity(intent);
    }

}
