package com.rel.hazmat.db.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.DatabaseConnection;
import com.rel.hazmat.db.model.Material;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class MaterialDAO extends GenericDAO<Material> {
    protected DatabaseConnection connectionSource;

    public MaterialDAO(Dao<Material, Integer> materialDAO) {
        super(materialDAO);
    }

    public List<Material> getMaterialListUsingQuery(String query) {
        QueryBuilder<Material, Integer> queryBuilder = dao.queryBuilder();
        List<Material> dbModelList = new ArrayList<Material>();
        try {
            queryBuilder.where().like(Material.NAME, query);
            PreparedQuery<Material> prepQuery = queryBuilder.prepare();
            dbModelList.addAll(dao.query(prepQuery));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbModelList;
    }

}