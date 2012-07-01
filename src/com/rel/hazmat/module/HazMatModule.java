package com.rel.hazmat.module;

import com.google.inject.AbstractModule;
import com.rel.hazmat.db.DBHelper;
import com.rel.hazmat.db.dao.MaterialDAO;
import com.rel.hazmat.provider.DBHelperProvider;
import com.rel.hazmat.provider.MaterialDAOProvider;

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
    }

}
