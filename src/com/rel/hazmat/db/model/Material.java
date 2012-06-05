package com.rel.hazmat.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "comment")
public class Material extends DBModel{

    public static final String NAME = "username";

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = NAME)
    private String name;

    public Material(String name) {
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

}
