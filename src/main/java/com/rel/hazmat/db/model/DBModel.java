package com.rel.hazmat.db.model;

import com.j256.ormlite.field.DatabaseField;

public abstract class DBModel {
    public static final String SLUG = "slug";
    public static final String ID = "id";
    @DatabaseField(columnName = SLUG, unique = true)
    protected String slug;
    @DatabaseField(id = true)
    protected int id;

    public DBModel() {
    }

    public DBModel(String slug) {
        this.slug = slug;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
