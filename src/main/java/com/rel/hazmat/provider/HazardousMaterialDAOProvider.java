package com.rel.hazmat.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.rel.hazmat.db.DBHelper;
import com.rel.hazmat.db.dao.HazardousMaterialDAO;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class HazardousMaterialDAOProvider implements
        Provider<HazardousMaterialDAO> {

    @Inject
    private DBHelper helper;

    public HazardousMaterialDAO get() {
        return new HazardousMaterialDAO(helper.getHazardousMaterialTable());
    }
}