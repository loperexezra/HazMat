package com.rel.hazmat.db.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

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
    public static final String TAG = "HazardousMaterialDAO";
    protected DatabaseConnection connectionSource;

    public HazardousMaterialDAO(Dao<HazardousMaterial, Integer> materialDAO) {
        super(materialDAO);
    }

    public List<HazardousMaterial> getHazardousMaterialListUsingQuery(
            String query) {
        QueryBuilder<HazardousMaterial, Integer> queryBuilder = dao
                .queryBuilder();
        List<HazardousMaterial> dbModelList = new ArrayList<HazardousMaterial>();
        try {
            queryBuilder.where()
                    .like(HazardousMaterial.NAME, "%" + query + "%");
            PreparedQuery<HazardousMaterial> prepQuery = queryBuilder.prepare();
            dbModelList.addAll(dao.query(prepQuery));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbModelList;
    }

    public Integer getLastID() {
        QueryBuilder<HazardousMaterial, Integer> queryBuilder = dao
                .queryBuilder();
        Integer maxID = 0;
        try {
            queryBuilder = dao.queryBuilder();
            queryBuilder.orderBy(HazardousMaterial.ID, false);
            queryBuilder.limit(1);
            HazardousMaterial material = dao.queryForFirst(queryBuilder
                    .prepare());
            if (material != null)
                maxID = material.getId();
            Log.i(TAG, "Returning maximum id : " + maxID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxID;
    }

    public HazardousMaterial getMaterialUsingDOT(Integer dotNumber) {
        QueryBuilder<HazardousMaterial, Integer> queryBuilder = dao
                .queryBuilder();
        HazardousMaterial material = null;
        try {
            queryBuilder = dao.queryBuilder();
            queryBuilder.orderBy(HazardousMaterial.DOT_NO, false);
            queryBuilder.limit(1);
            material = dao.queryForFirst(queryBuilder.prepare());
            Log.i(TAG, "Returning material : " + material);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return material;
    }
}