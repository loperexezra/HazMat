package com.rel.hazmat.module;

import com.google.inject.AbstractModule;
import com.rel.hazmat.db.DBHelper;
import com.rel.hazmat.db.dao.HazardousMaterialDAO;
import com.rel.hazmat.db.dao.MaterialDAO;
import com.rel.hazmat.db.service.contracts.IMaterialService;
import com.rel.hazmat.provider.DBHelperProvider;
import com.rel.hazmat.provider.HazardousMaterialDAOProvider;
import com.rel.hazmat.provider.MaterialDAOProvider;
import com.rel.hazmat.provider.MaterialServiceProvider;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class HazMatModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DBHelper.class).toProvider(DBHelperProvider.class);
        bind(MaterialDAO.class).toProvider(MaterialDAOProvider.class);
        bind(HazardousMaterialDAO.class).toProvider(
                HazardousMaterialDAOProvider.class);
        
        bind(IMaterialService.class).toProvider(
                MaterialServiceProvider.class);
        
    }

}
