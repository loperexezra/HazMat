package com.rel.hazmat.provider;

import android.content.Context;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.rel.hazmat.db.DBHelper;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class DBHelperProvider implements Provider<DBHelper> {
    @Inject
    private Provider<Context> contextProvider;

    public DBHelper get() {
        return OpenHelperManager.getHelper(contextProvider.get(), DBHelper.class);
    }

}
