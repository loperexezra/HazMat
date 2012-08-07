package com.rel.hazmat.activity;

import java.util.ArrayList;
import java.util.List;

import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.view.Window;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockActivity;
import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.rel.hazmat.R;
import com.rel.hazmat.adapter.BoxedValueAdapter;
import com.rel.hazmat.db.dao.HazardousMaterialDAO;
import com.rel.hazmat.db.model.HazardousMaterial;
import com.rel.hazmat.dto.ListViewDTO;
import com.rel.hazmat.utils.DTOConverter;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class DisplayVerifyActivity extends RoboSherlockActivity {
    public static final String TAG = "DisplayActivity";
    public static final String MATERIAL_SLUG = "material_slug";
    public static final String SEARCH_QUERY = "search_query";

    public static final String GENERAL_INFO = "General Info";
    public static final String PROPERTIES = "Properties";
    public static final String LIMITS = "Limits";

    // General Information
    protected final static String NAME = "Actual Name";
    protected final static String FORMULA = "Chemical Formula";
    protected final static String DOT = "DOT Number";
    protected final static String STATE = "Physical Description";
    // Properties
    protected final static String MOLECULAR_WEIGHT = "Molecular Weight";
    protected final static String SOLUBILITY = "Solubility";
    protected final static String FLASH_POINT = "Flash Point";
    // Limits
    protected final static String UEL = "UEL";
    protected final static String LEL = "LEL";
    protected final static String ION_POTENTIAL = "Ionization Potential";
    protected final static String GRAVITY = "Specific Gravity";
    protected final static String IDLH = "IDLH";
    protected final static String REL = "REL";
    protected final static String PEL = "PEL";

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
        setContentView(R.layout.form_display_verify);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Verify");
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
        Log.i(TAG, "Received material from DB : " + material);
        if (material != null) {
            initGeneralInfo(material);
        }
    }

    protected void initGeneralInfo(HazardousMaterial material) {
        View headerGeneralInfo = (View) getLayoutInflater().inflate(
                R.layout.item_header, null);
        TextView lblHeader = (TextView) headerGeneralInfo
                .findViewById(R.id.lblHeader);
        lblHeader.setText(GENERAL_INFO);
        // lvwGeneralInfo.addHeaderView(headerGeneralInfo);
        List<ListViewDTO> generalInfoDTOList = new ArrayList<ListViewDTO>();
        generalInfoDTOList.add(new ListViewDTO(NAME, DTOConverter
                .format(material.getName())));
        generalInfoDTOList.add(new ListViewDTO(FORMULA, DTOConverter
                .format(material.getFormula())));
        generalInfoDTOList.add(new ListViewDTO(DOT, DTOConverter
                .format(material.getDotNo())));
        generalInfoDTOList.add(new ListViewDTO(STATE, DTOConverter
                .format(material.getStates())));

        BoxedValueAdapter listAdapter = new BoxedValueAdapter(this,
                generalInfoDTOList);
        initProperties(listAdapter, material.getMolecularWeight(),
                material.getSolubility(), material.getFlashPoint());
        initLimits(listAdapter, material.getUel(), material.getLel(),
                material.getIonizationPressure(), material.getGravity(),
                material.getIdlh(), material.getRel(), material.getPel());
        lvwGeneralInfo.setAdapter(listAdapter);
    }

    protected void initProperties(BoxedValueAdapter listAdapter,
            String molecularWeight, String solubility, String flashPoint) {
        View headerGeneralInfo = (View) getLayoutInflater().inflate(
                R.layout.item_header, null);
        TextView lblHeader = (TextView) headerGeneralInfo
                .findViewById(R.id.lblHeader);
        lblHeader.setText(PROPERTIES);
        List<ListViewDTO> propertiesDTOList = new ArrayList<ListViewDTO>();
        propertiesDTOList.add(new ListViewDTO(MOLECULAR_WEIGHT, DTOConverter
                .format(molecularWeight)));
        propertiesDTOList.add(new ListViewDTO(SOLUBILITY, DTOConverter
                .format(solubility)));
        propertiesDTOList.add(new ListViewDTO(FLASH_POINT, DTOConverter
                .format(flashPoint)));
        listAdapter.addAll(propertiesDTOList);
    }

    protected void initLimits(BoxedValueAdapter listAdapter, String uel,
            String lel, String ionPressure, String gravity, String idlh,
            String rel, String pel) {
        View headerGeneralInfo = (View) getLayoutInflater().inflate(
                R.layout.item_header, null);
        TextView lblHeader = (TextView) headerGeneralInfo
                .findViewById(R.id.lblHeader);
        lblHeader.setText(LIMITS);
        // lvwGeneralInfo.addHeaderView(headerGeneralInfo);
        List<ListViewDTO> limitsDTOList = new ArrayList<ListViewDTO>();

        limitsDTOList.add(new ListViewDTO(UEL, DTOConverter.format(uel + "%")));
        limitsDTOList.add(new ListViewDTO(LEL, DTOConverter.format(lel + "%")));
        limitsDTOList.add(new ListViewDTO(ION_POTENTIAL, DTOConverter
                .format(ionPressure)));
        limitsDTOList
                .add(new ListViewDTO(GRAVITY, DTOConverter.format(gravity)));
        limitsDTOList.add(new ListViewDTO(IDLH, DTOConverter.format(idlh)));
        limitsDTOList.add(new ListViewDTO(REL, DTOConverter.format(rel)));
        limitsDTOList.add(new ListViewDTO(PEL, DTOConverter.format(pel)));
        listAdapter.addAll(limitsDTOList);
    }

}
