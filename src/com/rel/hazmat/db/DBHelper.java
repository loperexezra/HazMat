package com.rel.hazmat.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.rel.hazmat.db.model.Material;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class DBHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "lobangclub.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Material, Integer> categoryTable = null;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Material.class);
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
            TableUtils.dropTable(connectionSource, Material.class, true);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e("DBHelper", "Can't drop databases");
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<Material, Integer> getMaterialDao() throws SQLException {
        if (categoryTable == null) {
            try {
                categoryTable = getDao(Material.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return categoryTable;
    }

    public void createFakeData() {
        Material product = new Material("Carbon");
        Material product2 = new Material("Carbonyl");
        Material product3 = new Material("Carbonate");
        try {
            getMaterialDao().createIfNotExists(product);
            getMaterialDao().createIfNotExists(product2);
            getMaterialDao().createIfNotExists(product3);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public String test() {
        return "Successful injection";
    }
}
