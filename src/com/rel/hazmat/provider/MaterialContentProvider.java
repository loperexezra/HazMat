package com.rel.hazmat.provider;

import java.sql.SQLException;

import roboguice.RoboGuice;
import roboguice.content.RoboContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.support.DatabaseConnection;
import com.rel.hazmat.db.DBHelper;
import com.rel.hazmat.db.dao.MaterialDAO;

public class MaterialContentProvider extends RoboContentProvider {
    String TAG = "DictionaryProvider";

    public static String AUTHORITY = "com.rel.hazmat.provider.MaterialContentProvider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
            + "/dictionary");

    // MIME types used for searching words or looking up a single definition
    public static final String WORDS_MIME_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/vnd.example.android.searchabledict";
    public static final String DEFINITION_MIME_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
            + "/vnd.example.android.searchabledict";

    // UriMatcher stuff
    private static final int SEARCH_WORDS = 0;
    private static final int GET_WORD = 1;
    private static final int SEARCH_SUGGEST = 2;
    private static final int REFRESH_SHORTCUT = 3;

    @Inject
    MaterialDAO materialDAO;

    @Inject
    DBHelper helper;

    @Override
    public String getType(Uri uri) {
        // TODO Auto-generated method stub

        System.out.println("uri =============");
        return null;
    }

    @Override
    public boolean onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        System.out.println("onCreate =============");
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder) {
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = OpenHelperManager
                    .getHelper(getContext(), DBHelper.class)
                    .getConnectionSource().getReadWriteConnection();
            helper.getConnectionSource().getReadWriteConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // ConnectionSource connectionSource = helper.getConnectionSource();
        // System.out.println("query =============" + connectionSource);
        // databaseConnection = connectionSource.getReadWriteConnection();
        return materialDAO.getCursor(databaseConnection);
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
            String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }

}
