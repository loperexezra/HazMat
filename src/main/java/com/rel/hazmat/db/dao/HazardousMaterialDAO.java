package com.rel.hazmat.db.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.DatabaseConnection;
import com.rel.hazmat.db.model.HazardousMaterial;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class HazardousMaterialDAO extends GenericDAO<HazardousMaterial> {
    protected DatabaseConnection connectionSource;

    public HazardousMaterialDAO(Dao<HazardousMaterial, Integer> materialDAO) {
        super(materialDAO);
    }

    public List<HazardousMaterial> getHazardousMaterialListUsingQuery(String query) {
        QueryBuilder<HazardousMaterial, Integer> queryBuilder = dao.queryBuilder();
        List<HazardousMaterial> dbModelList = new ArrayList<HazardousMaterial>();
        try {
            queryBuilder.where().like(HazardousMaterial.NAME, "%" + query + "%");
            PreparedQuery<HazardousMaterial> prepQuery = queryBuilder.prepare();
            dbModelList.addAll(dao.query(prepQuery));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbModelList;
    }

}