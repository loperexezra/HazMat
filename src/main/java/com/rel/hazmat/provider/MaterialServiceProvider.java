package com.rel.hazmat.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.rel.hazmat.db.dao.HazardousMaterialDAO;
import com.rel.hazmat.db.service.MaterialService;
import com.rel.hazmat.db.service.contracts.IMaterialService;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class MaterialServiceProvider implements Provider<IMaterialService> {

    @Inject
    private HazardousMaterialDAO dao;

    public IMaterialService get() {
        return new MaterialService(dao);
    }
}