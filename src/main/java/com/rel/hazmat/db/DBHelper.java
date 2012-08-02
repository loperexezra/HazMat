package com.rel.hazmat.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.rel.hazmat.db.model.HazardousMaterial;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class DBHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "hazmat.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<HazardousMaterial, Integer> hazardousMaterialTable = null;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, HazardousMaterial.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
            int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, HazardousMaterial.class,
                    true);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e("DBHelper", "Can't drop databases");
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<HazardousMaterial, Integer> getHazardousMaterialTable() {
        if (hazardousMaterialTable == null) {
            try {
                hazardousMaterialTable = getDao(HazardousMaterial.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return hazardousMaterialTable;
    }

    public void clearDB() {
        try {
            TableUtils.clearTable(getConnectionSource(), HazardousMaterial.class);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public String test() {
        return "Successful injection";
    }

}
