package com.rel.hazmat.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
@DatabaseTable(tableName = "material")
public class State extends DBModel {
    public static final String NAME = "state_name";
    public static final String HAZARDOUS_MATERIAL = "hazardous_material";

    @DatabaseField(generatedId = true, columnName = ID)
    private int id;
    @DatabaseField(columnName = NAME)
    private String name;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = HAZARDOUS_MATERIAL)
    private HazardousMaterial hazardousMaterial;

    public State() {
        super();
    }

    public State(String name) {
        super(name);
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HazardousMaterial getHazardousMaterial() {
        return hazardousMaterial;
    }

    public void setHazardousMaterial(HazardousMaterial hazardousMaterial) {
        this.hazardousMaterial = hazardousMaterial;
    }

}
