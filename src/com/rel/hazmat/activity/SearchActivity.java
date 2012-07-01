package com.rel.hazmat.activity;

import java.sql.SQLException;

import roboguice.activity.RoboActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.inject.Inject;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.support.DatabaseConnection;
import com.rel.hazmat.R;
import com.rel.hazmat.db.DBHelper;
import com.rel.hazmat.db.dao.MaterialDAO;

public class SearchActivity extends RoboActivity {

    static final String[] COUNTRIES = new String[] { "Carbonyl Flouride",
            "Propane", "Helium", "Flourine, Compressed" };
    @Inject
    protected DBHelper helper;
    @Inject
    protected MaterialDAO materialDAO;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.search);
        try {

            DatabaseConnection conn = OpenHelperManager
                    .getHelper(this, DBHelper.class).getConnectionSource()
                    .getReadWriteConnection();
            System.out.println("+++++++++++++++++++" + conn);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        helper.createFakeData();
        Intent intent = getIntent();
        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            // handles a click on a search suggestion; launches activity to show word
            System.out.println("==================" + intent.getData().getPath());
//            Intent wordIntent = new Intent(this, WordActivity.class);
//            wordIntent.setData(intent.getData());
//            System.out.println("==================" + intent.getData().getPath());
//            startActivity(wordIntent);
            finish();
        } else if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            // handles a search query
            String query = intent.getStringExtra(SearchManager.QUERY);
//            showResults(query);
        }
        
        AutoCompleteTextView edtSearch = (AutoCompleteTextView) findViewById(R.id.edtSearch);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.list_item, COUNTRIES);
        edtSearch.setAdapter(adapter);
    }

    public void onClickSearch(View view) {
        // Intent intent = new Intent();
        // intent.setClass(this, DisplayActivity.class);
        // startActivity(intent);
        onSearchRequested();
    }
}
