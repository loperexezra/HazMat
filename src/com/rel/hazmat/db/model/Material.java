package com.rel.hazmat.db.model;

import android.app.SearchManager;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "material")
public class Material extends DBModel {

    public static final String NAME = SearchManager.SUGGEST_COLUMN_TEXT_1;

    @DatabaseField(generatedId = true, columnName = ID)
    private int id;
    @DatabaseField(columnName = NAME)
    private String name;

    public Material() {
        super();
    }

    public Material(String name) {
        super("1" + name);
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
