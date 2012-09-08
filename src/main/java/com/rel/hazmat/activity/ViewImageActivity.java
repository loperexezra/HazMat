package com.rel.hazmat.activity;

import roboguice.inject.InjectView;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockActivity;
import com.rel.hazmat.R;
import com.rel.hazmat.widgets.TouchImageView;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class ViewImageActivity extends RoboSherlockActivity {
    public static final String TAG = "ViewImageActivity";
    public static final String IMAGE_RESOURCE_ID = "image_resource_id";

    @InjectView(R.id.imgChart)
    protected TouchImageView imgChart;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_view_image);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("HazMatIQ");
        init();
    }

    protected void init() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Integer resourceID = extras.getInt(IMAGE_RESOURCE_ID);
            Log.i(TAG, "Received resource ID : " + resourceID);
            loadImage(resourceID);
        }
    }

    protected void loadImage(Integer resourceID) {
        Bitmap snoop = BitmapFactory.decodeResource(getResources(), resourceID);
        imgChart.setImageBitmap(snoop);
        imgChart.setMaxZoom(4f);
    }

    @Override
    public boolean onOptionsItemSelected(
            com.actionbarsherlock.view.MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getTitle().equals(getSupportActionBar().getTitle().toString())) {
            Intent intent = new Intent();
            intent.setClass(this, GridImageActivity.class);
            startActivity(intent);
        }
        return true;
    }

}