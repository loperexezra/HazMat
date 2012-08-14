package com.rel.hazmat.db.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
@DatabaseTable(tableName = "hazardous_material")
public class HazardousMaterial extends DBModel {
    public static final String NAME = "chemical_name";
    public static final String FORMULA = "chemical_formula";
    // General Information
    public final static String STATE = "state";
    public final static String DOT_NO = "dot";
    // Properties
    public final static String MOLECULAR_WEIGHT = "molecular_weight";
    public final static String SOLUBILITY = "solubility";
    public final static String ION_PRESSURE = "ion_pressure";
    public final static String GRAVITY = "gravity";
    // Limits
    public final static String FLASH_POINT = "flash_point";
    public final static String UEL = "UEL";
    public final static String LEL = "LEL";
    public final static String IDLH = "IDLH";
    public final static String REL = "REL";
    public final static String PEL = "PEL";
    // rescue
    public final static String RES_CHEMICAL_HAZARDS = "resc_chemical_hazards";
    public final static String RES_STATE_OF_MATTER = "resc_state_of_matter";
    public final static String RES_INI_ISO_ZONE = "resc_ini_iso_zone";
    public final static String RES_HAZMAT_SOG = "resc_hazmatiq_sog";
    public final static String RES_LOS_RES_PIPE = "resc_los_resc_ppe";
    public final static String RES_METER_COCKPIT = "resc_meter_cockpit";
    public final static String RES_DECON = "resc_decon";
    // plum
    public final static String PLUM_CHEM_HAZARDS = "plum_chemical_hazards";
    public final static String PLUM_STATE_OF_MATTER = "plum_state_of_matter";
    public final static String PLUM_INI_ISO_ZONE = "plum_ini_iso_zone";
    public final static String PLUM_HAZMATIQ_SOG = "plum_hazmatiq_sog";
    public final static String PLUM_PLUM_PIPE = "plum_plum_ppe";
    public final static String PLUM_METER_COCKPIT = "plum_meter_cockpit";
    public final static String PLUM_TECHNICAL_DECON = "plum_technical_decon";

    public final static String UN_ID = "un_id";

    protected final static String RADIOACTIVITY = "radioactivity";
    protected final static String CORROSIVITY = "corrosivity";
    protected final static String POLYMERIZATION_POTENTIAL = "polymerization_potential";
    protected final static String REACTIVITY = "reactivity";

    @DatabaseField(columnName = NAME)
    private String name;
    @DatabaseField(columnName = FORMULA)
    private String formula;
    @DatabaseField(columnName = STATE)
    private String states;
    @DatabaseField(columnName = DOT_NO)
    private String dotNo;
    @DatabaseField(columnName = MOLECULAR_WEIGHT)
    private String molecularWeight;
    @DatabaseField(columnName = SOLUBILITY)
    private String solubility;
    @DatabaseField(columnName = ION_PRESSURE)
    private String ionizationPressure;
    @DatabaseField(columnName = GRAVITY)
    private String gravity;
    @DatabaseField(columnName = FLASH_POINT)
    private String flashPoint;
    @DatabaseField(columnName = UEL)
    private String uel;
    @DatabaseField(columnName = LEL)
    private String lel;
    @DatabaseField(columnName = IDLH)
    private String idlh;
    @DatabaseField(columnName = REL)
    private String rel;
    @DatabaseField(columnName = PEL)
    private String pel;
    // rescue
    @DatabaseField(columnName = RES_CHEMICAL_HAZARDS)
    private String rescChemicalHazards;
    @DatabaseField(columnName = RES_STATE_OF_MATTER)
    private String rescStateOfMatter;
    @DatabaseField(columnName = RES_INI_ISO_ZONE)
    private String rescIniIsoZone;
    @DatabaseField(columnName = RES_HAZMAT_SOG)
    private String rescHazmatIqSog;
    @DatabaseField(columnName = RES_LOS_RES_PIPE)
    private String rescLosRescPpe;
    @DatabaseField(columnName = RES_METER_COCKPIT)
    private String rescMeterCockpit;
    @DatabaseField(columnName = RES_DECON)
    private String rescDecon;
    // plumbing
    @DatabaseField(columnName = PLUM_CHEM_HAZARDS)
    private String plumChemicalHazards;
    @DatabaseField(columnName = PLUM_STATE_OF_MATTER)
    private String plumStateofMatter;
    @DatabaseField(columnName = PLUM_INI_ISO_ZONE)
    private String plumIniIsoZone;
    @DatabaseField(columnName = PLUM_HAZMATIQ_SOG)
    private String plumHazmatIqSog;
    @DatabaseField(columnName = PLUM_PLUM_PIPE)
    private String plumPlumPpe;
    @DatabaseField(columnName = PLUM_METER_COCKPIT)
    private String plumMeterCockpit;
    @DatabaseField(columnName = PLUM_TECHNICAL_DECON)
    private String plumTechnicalDecon;
    @DatabaseField(columnName = UN_ID)
    private String unID;

    @DatabaseField(columnName = RADIOACTIVITY)
    private String radioactivity;
    @DatabaseField(columnName = CORROSIVITY)
    private String corrosivity;
    @DatabaseField(columnName = POLYMERIZATION_POTENTIAL)
    private String polymerizationPotential;
    @DatabaseField(columnName = REACTIVITY)
    private String reactivity;

    public HazardousMaterial() {
        super();
    }

    public HazardousMaterial(String slug, String name, String formula) {
        super(slug);
        this.name = name;
        this.formula = formula;
    }

    public HazardousMaterial(Integer id, String slug, String name,
            String formula, String states, String dotNo,
            String molecularWeight, String solubility,
            String ionizationPressure, String gravity, String flashPoint,
            String uel, String lel, String idlh, String rel, String pel) {
        super(slug);
        this.id = id;
        this.name = name;
        this.formula = formula;
        this.states = states;
        this.dotNo = dotNo;
        this.molecularWeight = molecularWeight;
        this.solubility = solubility;
        this.ionizationPressure = ionizationPressure;
        this.gravity = gravity;
        this.flashPoint = flashPoint;
        this.uel = uel;
        this.lel = lel;
        this.idlh = idlh;
        this.rel = rel;
        this.pel = pel;
    }

    public HazardousMaterial(Integer id, String slug, String name,
            String formula, String states, String dotNo,
            String molecularWeight, String solubility,
            String ionizationPressure, String gravity, String flashPoint,
            String uel, String lel, String idlh, String rel, String pel,
            String rescChemicalHazards, String rescStateOfMatter,
            String rescIniIsoZone, String rescHazmatIqSog,
            String rescLosRescPpe, String rescMeterCockpit, String rescDecon,
            String plumChemicalHazards, String plumStateofMatter,
            String plumIniIsoZone, String plumHazmatIqSog, String plumPlumPpe,
            String plumMeterCockpit, String plumTechnicalDecon, String unID,
            String radioactivity, String corrosivity,
            String polymerizationPotential, String reactivity) {
        super(slug);
        this.id = id;
        this.name = name;
        this.formula = formula;
        this.states = states;
        this.dotNo = dotNo;
        this.molecularWeight = molecularWeight;
        this.solubility = solubility;
        this.ionizationPressure = ionizationPressure;
        this.gravity = gravity;
        this.flashPoint = flashPoint;
        this.uel = uel;
        this.lel = lel;
        this.idlh = idlh;
        this.rel = rel;
        this.pel = pel;
        this.rescChemicalHazards = rescChemicalHazards;
        this.rescStateOfMatter = rescStateOfMatter;
        this.rescIniIsoZone = rescIniIsoZone;
        this.rescHazmatIqSog = rescHazmatIqSog;
        this.rescLosRescPpe = rescLosRescPpe;
        this.rescMeterCockpit = rescMeterCockpit;
        this.rescDecon = rescDecon;
        this.plumChemicalHazards = plumChemicalHazards;
        this.plumStateofMatter = plumStateofMatter;
        this.plumIniIsoZone = plumIniIsoZone;
        this.plumHazmatIqSog = plumHazmatIqSog;
        this.plumPlumPpe = plumPlumPpe;
        this.plumMeterCockpit = plumMeterCockpit;
        this.plumTechnicalDecon = plumTechnicalDecon;
        this.unID = unID;
        this.radioactivity = radioactivity;
        this.corrosivity = corrosivity;
        this.polymerizationPotential = polymerizationPotential;
        this.reactivity = reactivity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public String getDotNo() {
        return dotNo;
    }

    public void setDotNo(String dotNo) {
        this.dotNo = dotNo;
    }

    public String getMolecularWeight() {
        return molecularWeight;
    }

    public void setMolecularWeight(String molecularWeight) {
        this.molecularWeight = molecularWeight;
    }

    public String getSolubility() {
        return solubility;
    }

    public void setSolubility(String solubility) {
        this.solubility = solubility;
    }

    public String getIonizationPressure() {
        return ionizationPressure;
    }

    public void setIonizationPressure(String ionizationPressure) {
        this.ionizationPressure = ionizationPressure;
    }

    public String getGravity() {
        return gravity;
    }

    public void setGravity(String gravity) {
        this.gravity = gravity;
    }

    public String getFlashPoint() {
        return flashPoint;
    }

    public void setFlashPoint(String flashPoint) {
        this.flashPoint = flashPoint;
    }

    public String getUel() {
        return uel;
    }

    public void setUel(String uel) {
        this.uel = uel;
    }

    public String getLel() {
        return lel;
    }

    public void setLel(String lel) {
        this.lel = lel;
    }

    public String getIdlh() {
        return idlh;
    }

    public void setIdlh(String idlh) {
        this.idlh = idlh;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getPel() {
        return pel;
    }

    public void setPel(String pel) {
        this.pel = pel;
    }

    public String getRescChemicalHazards() {
        return rescChemicalHazards;
    }

    public void setRescChemicalHazards(String rescChemicalHazards) {
        this.rescChemicalHazards = rescChemicalHazards;
    }

    public String getRescStateOfMatter() {
        return rescStateOfMatter;
    }

    public void setRescStateOfMatter(String rescStateOfMatter) {
        this.rescStateOfMatter = rescStateOfMatter;
    }

    public String getRescIniIsoZone() {
        return rescIniIsoZone;
    }

    public void setRescIniIsoZone(String rescIniIsoZone) {
        this.rescIniIsoZone = rescIniIsoZone;
    }

    public String getRescHazmatIqSog() {
        return rescHazmatIqSog;
    }

    public void setRescHazmatIqSog(String rescHazmatIqSog) {
        this.rescHazmatIqSog = rescHazmatIqSog;
    }

    public String getRescLosRescPpe() {
        return rescLosRescPpe;
    }

    public void setRescLosRescPpe(String rescLosRescPpe) {
        this.rescLosRescPpe = rescLosRescPpe;
    }

    public String getRescMeterCockpit() {
        return rescMeterCockpit;
    }

    public void setRescMeterCockpit(String rescMeterCockpit) {
        this.rescMeterCockpit = rescMeterCockpit;
    }

    public String getRescDecon() {
        return rescDecon;
    }

    public void setRescDecon(String rescDecon) {
        this.rescDecon = rescDecon;
    }

    public String getPlumChemicalHazards() {
        return plumChemicalHazards;
    }

    public void setPlumChemicalHazards(String plumChemicalHazards) {
        this.plumChemicalHazards = plumChemicalHazards;
    }

    public String getPlumStateofMatter() {
        return plumStateofMatter;
    }

    public void setPlumStateofMatter(String plumStateofMatter) {
        this.plumStateofMatter = plumStateofMatter;
    }

    public String getPlumIniIsoZone() {
        return plumIniIsoZone;
    }

    public void setPlumIniIsoZone(String plumIniIsoZone) {
        this.plumIniIsoZone = plumIniIsoZone;
    }

    public String getPlumHazmatIqSog() {
        return plumHazmatIqSog;
    }

    public void setPlumHazmatIqSog(String plumHazmatIqSog) {
        this.plumHazmatIqSog = plumHazmatIqSog;
    }

    public String getPlumPlumPpe() {
        return plumPlumPpe;
    }

    public void setPlumPlumPpe(String plumPlumPpe) {
        this.plumPlumPpe = plumPlumPpe;
    }

    public String getPlumMeterCockpit() {
        return plumMeterCockpit;
    }

    public void setPlumMeterCockpit(String plumMeterCockpit) {
        this.plumMeterCockpit = plumMeterCockpit;
    }

    public String getPlumTechnicalDecon() {
        return plumTechnicalDecon;
    }

    public void setPlumTechnicalDecon(String plumTechnicalDecon) {
        this.plumTechnicalDecon = plumTechnicalDecon;
    }

    public String getUnID() {
        return unID;
    }

    public void setUnID(String unID) {
        this.unID = unID;
    }

    public String getRadioactivity() {
        return radioactivity;
    }

    public void setRadioactivity(String radioactivity) {
        this.radioactivity = radioactivity;
    }

    public String getCorrosivity() {
        return corrosivity;
    }

    public void setCorrosivity(String corrosivity) {
        this.corrosivity = corrosivity;
    }

    public String getPolymerizationPotential() {
        return polymerizationPotential;
    }

    public void setPolymerizationPotential(String polymerizationPotential) {
        this.polymerizationPotential = polymerizationPotential;
    }

    public String getReactivity() {
        return reactivity;
    }

    public void setReactivity(String reactivity) {
        this.reactivity = reactivity;
    }

}