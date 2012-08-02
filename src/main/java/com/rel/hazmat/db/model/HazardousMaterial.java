package com.rel.hazmat.db.model;

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

    public HazardousMaterial() {
        super();
    }

    public HazardousMaterial(String slug, String name, String formula) {
        super(slug);
        this.name = name;
        this.formula = formula;
    }

    public HazardousMaterial(String slug, String name, String formula,
            String states, String dotNo, String molecularWeight,
            String solubility, String ionizationPressure, String gravity,
            String flashPoint, String uel, String lel, String idlh, String rel,
            String pel) {
        super(slug);
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
    
    public HazardousMaterial(Integer id, String slug, String name, String formula,
            String states, String dotNo, String molecularWeight,
            String solubility, String ionizationPressure, String gravity,
            String flashPoint, String uel, String lel, String idlh, String rel,
            String pel) {
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

}