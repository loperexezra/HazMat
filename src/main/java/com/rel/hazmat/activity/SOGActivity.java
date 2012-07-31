package com.rel.hazmat.activity;

import android.content.Intent;
import android.os.Bundle;

import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockActivity;
import com.rel.hazmat.R;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class SOGActivity extends RoboSherlockActivity {
    public static final String TAG = "SOGActivity";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_sog);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("HazMat Guru");
    }

    public void init() {

    }

    @Override
    public boolean onOptionsItemSelected(
            com.actionbarsherlock.view.MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getTitle().equals(getSupportActionBar().getTitle().toString())) {
            Intent intent = new Intent();
            intent.setClass(this, ICSSearchActivity.class);
            startActivity(intent);
        }
        return true;
    }

}