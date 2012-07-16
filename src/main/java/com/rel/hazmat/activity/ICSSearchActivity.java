package com.rel.hazmat.activity;

import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.actionbarsherlock.view.Window;
import com.google.inject.Inject;
import com.rel.hazmat.R;
import com.rel.hazmat.actionbar.ABSearchActivity;
import com.rel.hazmat.csv.ChemicalCSVReader;
import com.rel.hazmat.db.DBHelper;
import com.rel.hazmat.db.dao.HazardousMaterialDAO;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class ICSSearchActivity extends ABSearchActivity {
    public static final String TAG = "ICSSearchActivity";
    static final String[] COUNTRIES = new String[] { "Carbonyl Flouride",
            "Propane", "Helium", "Flourine, Compressed" };
    @Inject
    protected DBHelper helper;
    @Inject
    protected HazardousMaterialDAO materialDAO;
    @InjectView(R.id.edtSearchName)
    protected EditText edtSearchName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ics_search);
        loadCSV();
    }

    public void loadCSV() {
        ChemicalCSVReader reader = new ChemicalCSVReader(this, materialDAO);
        reader.loadToDatabase();
    }

    public void onClickSearch(View view) {
        Intent intent = new Intent();
        intent.setClass(this, SearchResultActivity.class);
        Log.i(TAG, "Sending query string : " + edtSearchName
                .getText().toString());
        intent.putExtra(SearchResultActivity.SEARCH_QUERY, edtSearchName
                .getText().toString());
        startActivity(intent);
    }

    public void onClickSearchUnknown(View view) {
    }

}
