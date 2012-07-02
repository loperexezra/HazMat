package com.rel.hazmat.db.dao;

import java.sql.SQLException;

import android.database.Cursor;
import android.util.Log;

import com.j256.ormlite.android.AndroidCompiledStatement;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.StatementBuilder.StatementType;
import com.j256.ormlite.support.DatabaseConnection;
import com.rel.hazmat.db.model.Material;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class MaterialDAO extends GenericDAO<Material> {
    protected DatabaseConnection connectionSource;

    public MaterialDAO(Dao<Material, Integer> commentDAO) {
        super(commentDAO);
    }

    public MaterialDAO(Dao<Material, Integer> commentDAO,
            DatabaseConnection connectionSource) {
        super(commentDAO);
        this.connectionSource = connectionSource;
    }

    public Cursor getCursor(DatabaseConnection connectionSource) {
        QueryBuilder<Material, Integer> queryBuilder = dao.queryBuilder();
        queryBuilder.limit(new Long(10));
        PreparedQuery<Material> query;
        Cursor cursor = null;
        try {
            queryBuilder.where().isNotNull(Material.NAME);
            query = queryBuilder.prepare();
            for (Material material : queryBuilder.query()) {
                Log.i("MaterialDAO",
                        "Retrieving material : " + material.getName());
            }
            AndroidCompiledStatement compiledStatement = (AndroidCompiledStatement) query
                    .compile(connectionSource, StatementType.SELECT);
            cursor = compiledStatement.getCursor();
            Log.i("MaterialDAO", "Count : " + cursor.getCount());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursor;
    }

}