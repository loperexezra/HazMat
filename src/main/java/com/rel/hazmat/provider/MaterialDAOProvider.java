package com.rel.hazmat.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.rel.hazmat.db.DBHelper;
import com.rel.hazmat.db.dao.MaterialDAO;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class MaterialDAOProvider implements Provider<MaterialDAO> {

    @Inject
    private DBHelper helper;

    public MaterialDAO get() {
        return new MaterialDAO(helper.getMaterialDao());
    }
}