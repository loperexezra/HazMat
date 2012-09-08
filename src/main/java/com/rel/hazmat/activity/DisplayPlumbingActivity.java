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
import com.rel.hazmat.utils.DTOConverter;

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
    protected final static String HAZMAT_IQ_SOG = "HazMatIQ SOG";
    protected final static String HAZARDS = "Chemical Hazards";
    protected final static String STATE = "State of Matter";
    protected final static String INITIAL_ISOLATION_ZONE = "Initial Isolation Distance";
    protected final static String PROTECTIVE_EQUIPMENT = "Plumbing PPE";
    protected final static String METER_COCKPIT = "Meter Cockpit";
    protected final static String TECHNICAL_DECON = "Technical DECON";

    // Tabbed metered cockpit
    protected final static String RAD_METER = "Rad Meter";
    protected final static String F_VALUE = "F";
    protected final static String PH = "pH";
    protected final static String TEMPERATURE_GUN = "Temp Gun";
    protected final static String CGI = "CGI";
    protected final static String KISS = "KIss\u2122";
    protected final static String DUST_MONITOR = "Dust Monitor";
    protected final static String PID = "PID";
    protected final static String FID = "FID";
    protected final static String FREON_METER = "Freon Meter";
    protected final static String TUBE_CHIP = "Tube-Chip";

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
        setContentView(R.layout.form_display_plumbing);
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
            List<ListViewDTO> dtoList = new ArrayList<ListViewDTO>();
            dtoList.addAll(initGeneralInfo(material.getName(),
                    material.getPlumChemicalHazards(),
                    material.getPlumStateofMatter(),
                    material.getPlumIniIsoZone(),
                    material.getPlumHazmatIqSog(), material.getPlumPlumPpe(),
                    material.getPlumMeterCockpit(),
                    material.getPlumTechnicalDecon()));
            dtoList.addAll(initMeteredCockPit(material.getPlumRadmeter(),
                    material.getPlumFValue(), material.getPlumPhValue(),
                    material.getPlumTempgun(), material.getPlumCgi(),
                    material.getPlumKiss(), material.getDustMonitor(),
                    material.getPid(), material.getFid(),
                    material.getFreonMeter(), material.getTubeChip()));
            setList(dtoList);
        }
    }

    protected void setList(List<ListViewDTO> dtoList) {
        ListAdapter listAdapter = new BoxedValueAdapter(this, dtoList);
        lvwGeneralInfo.setAdapter(listAdapter);
    }

    protected List<ListViewDTO> initGeneralInfo(String name, String hazards,
            String state, String initialIsolationZone, String hazmatIQSOG,
            String protectiveEquipment, String meterCockpit,
            String technicalDecon) {
        List<ListViewDTO> generalInfoDTOList = new ArrayList<ListViewDTO>();
        generalInfoDTOList
                .add(new ListViewDTO(NAME, DTOConverter.format(name)));
        generalInfoDTOList.add(new ListViewDTO(HAZMAT_IQ_SOG, DTOConverter
                .format(hazmatIQSOG)));
        generalInfoDTOList.add(new ListViewDTO(HAZARDS, DTOConverter
                .format(hazards)));
        generalInfoDTOList.add(new ListViewDTO(STATE, DTOConverter
                .format(state)));
        generalInfoDTOList.add(new ListViewDTO(INITIAL_ISOLATION_ZONE,
                DTOConverter.format(initialIsolationZone)));
        generalInfoDTOList.add(new ListViewDTO(PROTECTIVE_EQUIPMENT,
                DTOConverter.format(protectiveEquipment)));
        generalInfoDTOList.add(new ListViewDTO(TECHNICAL_DECON, DTOConverter
                .format(technicalDecon)));
        generalInfoDTOList.add(new ListViewDTO(METER_COCKPIT, DTOConverter
                .format(meterCockpit)));
        return generalInfoDTOList;
    }

    protected List<ListViewDTO> initMeteredCockPit(String radMeter,
            String fValue, String ph, String tempGun, String cGI, String kIss,
            String dustMonitor, String pID, String fID, String freonMeter,
            String tubeChip) {
        List<ListViewDTO> meteredCockpitDTOList = new ArrayList<ListViewDTO>();
        meteredCockpitDTOList.add(new ListViewDTO(RAD_METER, DTOConverter
                .format(radMeter), true, R.color.red));
        meteredCockpitDTOList.add(new ListViewDTO(F_VALUE, DTOConverter
                .format(fValue), true, R.color.red));
        meteredCockpitDTOList.add(new ListViewDTO(PH, DTOConverter.format(ph),
                true, R.color.red));
        meteredCockpitDTOList.add(new ListViewDTO(TEMPERATURE_GUN, DTOConverter
                .format(tempGun), true, R.color.red));
        meteredCockpitDTOList.add(new ListViewDTO(CGI,
                DTOConverter.format(cGI), true, R.color.red));
        meteredCockpitDTOList.add(new ListViewDTO(KISS, DTOConverter
                .format(kIss), true, R.color.blue));
        meteredCockpitDTOList.add(new ListViewDTO(DUST_MONITOR, DTOConverter
                .format(dustMonitor), true, R.color.spec_green));
        meteredCockpitDTOList.add(new ListViewDTO(PID,
                DTOConverter.format(pID), true, R.color.spec_green));
        meteredCockpitDTOList.add(new ListViewDTO(FID,
                DTOConverter.format(fID), true, R.color.spec_green));
        meteredCockpitDTOList.add(new ListViewDTO(FREON_METER, DTOConverter
                .format(freonMeter), true, R.color.spec_green));
        meteredCockpitDTOList.add(new ListViewDTO(TUBE_CHIP, DTOConverter
                .format(tubeChip), true, R.color.spec_green));
        return meteredCockpitDTOList;
    }
}
