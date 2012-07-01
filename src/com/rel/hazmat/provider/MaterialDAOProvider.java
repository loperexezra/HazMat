package com.rel.hazmat.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.j256.ormlite.support.DatabaseConnection;
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
//        DatabaseConnection databaseConnection = null;
//        try {
//            databaseConnection = helper.getConnectionSource()
//                    .getReadWriteConnection();
//        } catch (java.sql.SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        return new MaterialDAO(helper.getMaterialDao());
    }
}