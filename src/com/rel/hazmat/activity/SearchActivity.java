package com.rel.hazmat.activity;

import roboguice.activity.RoboActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.rel.hazmat.R;

public class SearchActivity extends RoboActivity {

    static final String[] COUNTRIES = new String[] { "Carbonyl Flouride",
            "Propane", "Helium", "Flourine, Compressed" };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.search);
        AutoCompleteTextView edtSearch = (AutoCompleteTextView) findViewById(R.id.edtSearch);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.list_item, COUNTRIES);
        edtSearch.setAdapter(adapter);
    }

    public void onClickSearch(View view) {
        Intent intent = new Intent();
        intent.setClass(this, DisplayActivity.class);
        startActivity(intent);
    }
}
