package com.rel.hazmat.activity;

import java.util.ArrayList;
import java.util.List;

import roboguice.inject.InjectView;
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
public class DisplayActivity extends RoboSherlockActivity {
    public static final String TAG = "DisplayActivity";
    public static final String MATERIAL_SLUG = "material_slug";
    // General Information
    protected final static String NAME = "Name";
    protected final static String FORMULA = "Formula";
    protected final static String STATE = "State";
    protected final static String DOT = "DOT no.";
    // Properties
    protected final static String MOLECULAR_WEIGHT = "Molecular Weight";
    protected final static String SOLUBILITY = "Solubility";
    protected final static String ION_PRESSURE = "Ionization Pressure";
    protected final static String GRAVITY = "Specific Gravity";
    // Limits
    protected final static String FLASH_POINT = "Flash Point";
    protected final static String UEL = "UEL";
    protected final static String LEL = "LEL";
    protected final static String IDLH = "IDLH";
    protected final static String REL = "REL";
    protected final static String PEL = "PEL";

    @Inject
    protected HazardousMaterialDAO materialDAO;

    @InjectView(R.id.lvwGeneralInfo)
    protected ListView lvwGeneralInfo;
    @InjectView(R.id.lvwProperties)
    protected ListView lvwProperties;
    @InjectView(R.id.lvwLimits)
    protected ListView lvwLimits;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_display_hazardous_material);
        initForm();
    }

    public void initForm() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String chemicalSlug = extras.getString(MATERIAL_SLUG);
            Log.i(TAG, "received search query from extra" + chemicalSlug);
            if (!Strings.isNullOrEmpty(chemicalSlug)) {
                displayMaterial(chemicalSlug);
            }
        }
    }

    public void displayMaterial(String chemicalSlug) {
        HazardousMaterial material = materialDAO.getItemUsingSlug(chemicalSlug);
        Log.i(TAG, "Received material from DB : " + material.getName());
        if (material != null) {
            initGeneralInfo(material.getName(), material.getFormula(),
                    material.getStates(), material.getDotNo());
            initProperties(material.getMolecularWeight(),
                    material.getSolubility(), material.getIonizationPressure(),
                    material.getGravity());
            initLimits(material.getFlashPoint(), material.getUel(),
                    material.getLel(), material.getIdlh(), material.getRel(),
                    material.getPel());
        }
    }

    protected void initGeneralInfo(String name, String formula, String state,
            String DOTnum) {
        List<ListViewDTO> generalInfoDTOList = new ArrayList<ListViewDTO>();
        generalInfoDTOList.add(new ListViewDTO(NAME, name));
        generalInfoDTOList.add(new ListViewDTO(FORMULA, formula));
        generalInfoDTOList.add(new ListViewDTO(STATE, state));
        generalInfoDTOList.add(new ListViewDTO(DOT, DOTnum));
        ListAdapter listAdapter = new BoxedValueAdapter(this,
                generalInfoDTOList);
        lvwGeneralInfo.setAdapter(listAdapter);
    }

    protected void initProperties(String molecularWeight, String solubility,
            String ionPressure, String gravity) {
        List<ListViewDTO> propertiesDTOList = new ArrayList<ListViewDTO>();
        propertiesDTOList
                .add(new ListViewDTO(MOLECULAR_WEIGHT, molecularWeight));
        propertiesDTOList.add(new ListViewDTO(SOLUBILITY, solubility));
        propertiesDTOList.add(new ListViewDTO(ION_PRESSURE, ionPressure));
        propertiesDTOList.add(new ListViewDTO(GRAVITY, gravity));
        ListAdapter popularityListAdapter = new BoxedValueAdapter(this,
                propertiesDTOList);
        lvwProperties.setAdapter(popularityListAdapter);
    }

    protected void initLimits(String flashPoint, String uel, String lel,
            String idlh, String rel, String pel) {
        List<ListViewDTO> limitsDTOList = new ArrayList<ListViewDTO>();
        limitsDTOList.add(new ListViewDTO(FLASH_POINT, flashPoint));
        limitsDTOList.add(new ListViewDTO(UEL, uel + "%"));
        limitsDTOList.add(new ListViewDTO(LEL, lel + "%"));
        limitsDTOList.add(new ListViewDTO(IDLH, idlh));
        limitsDTOList.add(new ListViewDTO(REL, rel));
        limitsDTOList.add(new ListViewDTO(PEL, pel));
        ListAdapter basicInfoListAdapter = new BoxedValueAdapter(this,
                limitsDTOList);
        lvwLimits.setAdapter(basicInfoListAdapter);
    }

}
