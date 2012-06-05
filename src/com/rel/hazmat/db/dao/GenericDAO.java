package com.rel.hazmat.db.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.google.common.base.Strings;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.rel.hazmat.db.model.DBModel;
import com.rel.hazmat.exception.NullSlugException;

public abstract class GenericDAO<T extends DBModel> {
    protected Dao<T, Integer> dao;

    public GenericDAO(Dao<T, Integer> dao) {
        this.dao = dao;
    }

    public boolean exists(String slug) throws NullSlugException {
        Log.i("GenericDAO", "Checking if retailer slug exists : " + slug);
        if (!Strings.isNullOrEmpty(slug)) {
            if (getItemUsingSlug(slug) != null) {
                return true;
            }
            return false;
        } else {
            throw new NullSlugException();
        }
    }

    public T save(T t) throws NullSlugException {
        Log.i("GenericDAO", "Recieved retailer with slug" + t.getSlug()
                + " number of entries");
        T resultModel = null;
        try {
            if (exists(t.getSlug())) {
                dao.update(t);
                resultModel = getItemUsingSlug(t.getSlug());
            } else {
                resultModel = dao.createIfNotExists(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultModel;
    }

    public void save(List<T> modelList) throws NullSlugException {
        Log.i("GenericDAO", "Recieved retailer list with " + modelList.size()
                + " number of entries");
        if ((modelList.size() != 0) && (dao != null)) {
            for (T t : modelList) {
                save(t);
            }
        } else if (modelList.size() == 0) {
            Log.e("GenericDAO",
                    "You gave an empty retailer list to the method addRetailerList");
        } else {
            Log.e("GenericDAO",
                    "You gave an empty dao to this object, list was not cached");
        }
    }

    public T getItemUsingSlug(String slug) {
        try {
            QueryBuilder<T, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().eq(T.SLUG, slug);
            PreparedQuery<T> prepQuery = queryBuilder.prepare();
            List<T> dbModelList = dao.query(prepQuery);
            if (dbModelList.size() != 0) {
                Log.i("GenericDAO", "returning model with slug : "
                        + dbModelList.get(0).getSlug());
                return dbModelList.get(0);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<T> getItemList(int limit) {
        List<T> dbModelList = new ArrayList<T>();
        if (dao != null) {
            try {
                dbModelList = dao.queryBuilder().limit(new Long(limit)).query();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("GenericDAO",
                    "Retailer DAO was null, something's wrong with the injection");
        }
        return dbModelList;
    }

    public List<T> getItemUsingQuery(String columnName, String query, int limit) {
        List<T> dbModelList = new ArrayList<T>();
        if (dao != null) {
            try {
                QueryBuilder<T, Integer> queryBuilder = dao.queryBuilder();
                queryBuilder.where().eq(columnName, query);
                PreparedQuery<T> prepQuery = queryBuilder.prepare();
                dbModelList = dao.query(prepQuery);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("GenericDAO",
                    "Retailer DAO was null, something's wrong with the injection");
        }
        return dbModelList;
    }

    public List<T> refresh(List<T> itemList) {
        List<T> returnList = new ArrayList<T>();
        for (T t : itemList) {
            refresh(t);
            returnList.add(t);
        }
        return returnList;
    }

    public T refresh(T item) {
        try {
            dao.refresh(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

}
