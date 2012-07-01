package com.rel.hazmat.activity;

import roboguice.activity.RoboActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.google.inject.Inject;
import com.rel.hazmat.R;
import com.rel.hazmat.db.DBHelper;
import com.rel.hazmat.db.dao.MaterialDAO;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class ICSSearchActivity extends RoboActivity {

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
        setContentView(R.layout.ics_search);
    }

    public void onClickSearch(View view) {
    }
}
