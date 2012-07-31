package com.rel.hazmat.activity;

import android.os.Bundle;

import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockActivity;
import com.google.common.base.Strings;
import com.rel.hazmat.R;
import com.rel.hazmat.db.model.HazardousMaterial;

/**
 * 
 * @author Lope Chupijay Emano
 *
 */
public class ViewChemicalDetailsActivity extends RoboSherlockActivity {
    public final static String CHEMICAL_SLUG = "chemicalSlug";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_display_hazardous_material);
        getSupportActionBar().setHomeButtonEnabled(true);
        initChemicalSlug();
    }

    public void initChemicalSlug() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String chemicalSlug = extras.getString(CHEMICAL_SLUG);
            if (!Strings.isNullOrEmpty(chemicalSlug)) {
                getChemical(chemicalSlug);
            }
        }
    }

    protected void initUserActivity(String lobangsAdded, String pricesAdded) {
        // List<ListViewDTO> activityDTOList = new ArrayList<ListViewDTO>();
        // activityDTOList.add(new ListViewDTO(LOBANGS_ADDED, lobangsAdded));
        // activityDTOList.add(new ListViewDTO(PRICES_ADDED, pricesAdded));
        // ListAdapter listAdapter = new BoxedValueAdapter(this,
        // activityDTOList);
        // lvwActivity.setAdapter(listAdapter);
    }

    public HazardousMaterial getChemical(String chemicalSlug) {
        return null;

    }

}