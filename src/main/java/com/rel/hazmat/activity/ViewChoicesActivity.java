package com.rel.hazmat.activity;

import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockActivity;
import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.rel.hazmat.R;
import com.rel.hazmat.db.dao.HazardousMaterialDAO;
import com.rel.hazmat.db.model.HazardousMaterial;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class ViewChoicesActivity extends RoboSherlockActivity {
    public static final String TAG = "ViewChoicesActivity";
    public final static String MATERIAL_SLUG = "chemicalSlug";
    public static final String SEARCH_QUERY = "search_query";

    @Inject
    protected HazardousMaterialDAO materialDAO;

    @InjectView(R.id.lblChemicalName)
    protected TextView lblChemicalName;
    @InjectView(R.id.lblFormula)
    protected TextView lblFormula;

    protected String materialSlug;
    protected String query;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_choose_display);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Select a View Type");
        initChemicalSlug();
    }

    public void initChemicalSlug() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            materialSlug = extras.getString(MATERIAL_SLUG);
            if (!Strings.isNullOrEmpty(materialSlug)) {
                HazardousMaterial material = materialDAO
                        .getItemUsingSlug(materialSlug);
                if (material != null) {
                    setLabels(material.getName(), material.getFormula());
                }
            }

            query = extras.getString(SEARCH_QUERY);
            Log.i(TAG, "received search query from extra" + query);
        }
    }

    public void setLabels(String chemicalName, String formula) {
        lblChemicalName.setText(chemicalName);
        lblFormula.setText(formula);
    }

    @Override
    public boolean onOptionsItemSelected(
            com.actionbarsherlock.view.MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getTitle().equals(getSupportActionBar().getTitle().toString())) {
            Intent intent = new Intent();
            if (!Strings.isNullOrEmpty(query)) {
                intent.setClass(this, SearchResultActivity.class);
                intent.putExtra(SearchResultActivity.SEARCH_QUERY, query);
            } else {
                Log.i(TAG, "This was called with an empty search query");
                intent.setClass(this, ICSSearchActivity.class);
            }
            startActivity(intent);
        }
        return true;
    }

    public void onClickRescue(View view) {
        Intent intent = new Intent();
        intent.setClass(this, DisplayRescueActivity.class);
        intent.putExtra(DisplayRescueActivity.MATERIAL_SLUG, materialSlug);
        intent.putExtra(DisplayRescueActivity.SEARCH_QUERY, query);
        startActivity(intent);
    }

    public void onClickPlumbing(View view) {
        Intent intent = new Intent();
        intent.setClass(this, DisplayPlumbingActivity.class);
        intent.putExtra(DisplayPlumbingActivity.MATERIAL_SLUG, materialSlug);
        intent.putExtra(DisplayPlumbingActivity.SEARCH_QUERY, query);
        startActivity(intent);
    }

    public void onClickVerify(View view) {
        Intent intent = new Intent();
        intent.setClass(this, DisplayVerifyActivity.class);
        intent.putExtra(DisplayVerifyActivity.MATERIAL_SLUG, materialSlug);
        intent.putExtra(DisplayVerifyActivity.SEARCH_QUERY, query);
        startActivity(intent);
    }

}