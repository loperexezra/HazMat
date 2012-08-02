package com.rel.hazmat.activity;

import java.util.ArrayList;
import java.util.List;

import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.actionbarsherlock.view.Window;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockActivity;
import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.rel.hazmat.R;
import com.rel.hazmat.adapter.BoxedValueAdapter;
import com.rel.hazmat.db.dao.HazardousMaterialDAO;
import com.rel.hazmat.db.model.HazardousMaterial;
import com.rel.hazmat.dto.ListViewDTO;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class DisplayPlumbingActivity extends RoboSherlockActivity {
    public static final String TAG = "DisplayActivity";
    public static final String MATERIAL_SLUG = "material_slug";
    public static final String SEARCH_QUERY = "search_query";

    // General Information
    protected final static String NAME = "Chemical Name";
    protected final static String HAZARDS = "Chemical Hazards";
    protected final static String STATE = "State of Matter";
    protected final static String INITIAL_ISOLATION_ZONE = "Initial Isolation Zone";
    protected final static String HAZMAT_IQ_SOG = "HazMatIQ SOG";
    protected final static String PROTECTIVE_EQUIPMENT = "Plumbing Personal Protective Equipment";
    protected final static String METER_COCKPIT = "Meter Cockpit";
    protected final static String TECHNICAL_DECON = "Technical Decon";

    @Inject
    protected HazardousMaterialDAO materialDAO;

    @InjectView(R.id.lvwGeneralInfo)
    protected ListView lvwGeneralInfo;

    protected String query;
    protected String chemicalSlug;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_display_hazardous_material);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Plumbing");
        initForm();
    }

    public void initForm() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            chemicalSlug = extras.getString(MATERIAL_SLUG);
            query = extras.getString(SEARCH_QUERY);
            Log.i(TAG, "received search query from extra" + chemicalSlug);
            if (!Strings.isNullOrEmpty(chemicalSlug)) {
                displayMaterial(chemicalSlug);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(
            com.actionbarsherlock.view.MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getTitle().equals(getSupportActionBar().getTitle().toString())) {
            Intent intent = new Intent();
            if (!Strings.isNullOrEmpty(query)) {
                intent.setClass(this, ViewChoicesActivity.class);
                intent.putExtra(ViewChoicesActivity.SEARCH_QUERY, query);
                intent.putExtra(ViewChoicesActivity.MATERIAL_SLUG, chemicalSlug);
            } else {
                Log.i(TAG, "This was called with an empty search query");
                intent.setClass(this, ICSSearchActivity.class);
            }
            startActivity(intent);
        }
        return true;
    }

    public void displayMaterial(String chemicalSlug) {
        HazardousMaterial material = materialDAO.getItemUsingSlug(chemicalSlug);
        Log.i(TAG, "Received material from DB : " + material.getName());
        if (material != null) {
            initGeneralInfo(material.getName(), material.getFormula(),
                    material.getStates(), material.getDotNo());
        }
    }

    protected void initGeneralInfo(String name, String formula, String state,
            String DOTnum) {
        List<ListViewDTO> generalInfoDTOList = new ArrayList<ListViewDTO>();
        generalInfoDTOList.add(new ListViewDTO(NAME, name));
        generalInfoDTOList.add(new ListViewDTO(HAZARDS, ""));
        generalInfoDTOList.add(new ListViewDTO(STATE, state));
        generalInfoDTOList.add(new ListViewDTO(INITIAL_ISOLATION_ZONE, ""));
        generalInfoDTOList.add(new ListViewDTO(HAZMAT_IQ_SOG, ""));
        generalInfoDTOList.add(new ListViewDTO(PROTECTIVE_EQUIPMENT, ""));
        generalInfoDTOList.add(new ListViewDTO(METER_COCKPIT, ""));
        generalInfoDTOList.add(new ListViewDTO(TECHNICAL_DECON, ""));
        ListAdapter listAdapter = new BoxedValueAdapter(this,
                generalInfoDTOList);
        lvwGeneralInfo.setAdapter(listAdapter);
    }

}
