package com.rel.hazmat.actionbar;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.actionbarsherlock.view.MenuItem;
import com.google.inject.Inject;
import com.rel.hazmat.HazMatBaseActivity;
import com.rel.hazmat.R;
import com.rel.hazmat.activity.ICSSearchActivity;
import com.rel.hazmat.db.DBHelper;
import com.rel.hazmat.db.service.contracts.IMaterialService;
import com.rel.hazmat.json.model.request.UpdateChemicalsRequest;
import com.rel.hazmat.tasks.UpdateChemicalsTask;
import com.rel.hazmat.tasks.contracts.IProgressLoader;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class ABSearchActivity extends HazMatBaseActivity implements
        IProgressLoader {
    public static final String SEARCH = "Search";
    public static final String LOGIN = "Login";
    public static final String UPDATE_DATA = "Update";
    public static final String HELP = "Help";

    @Inject
    protected IMaterialService materialService;
    @Inject
    protected DBHelper helper;
    protected boolean isIndeterminateProgressVisible;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.hazmatTheme);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("HazMatIQ");
    }

    protected void checkConnection() {
        if (!isNetworkAvailable()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0,
                                        int arg1) {
                                }
                            });
            builder.setMessage("Not connected to a network or access point");
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    protected boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    @Override
    public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
        super.onCreateOptionsMenu(menu);

        boolean isLight = true;

        // menu.add(SEARCH)
        // .setIcon(
        // isLight ? R.drawable.ic_search_inverse
        // : R.drawable.ic_search)
        // .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        // menu.add(LOGIN).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add(UPDATE_DATA).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(
            com.actionbarsherlock.view.MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getTitle().equals(getSupportActionBar().getTitle().toString())) {
            Intent intent = new Intent();
            intent.setClass(this, ICSSearchActivity.class);
            startActivity(intent);
        } else if (item.getTitle().equals(UPDATE_DATA)) {
            if (isNetworkAvailable()) {
                updateChemicalList();
            } else {
                checkConnection();
            }
        } else if (item.getTitle().equals(HELP)) {
            Toast.makeText(this, "Long press in order to select an item",
                    Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    protected void updateChemicalList() {
        helper.clearDB();
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Updating..");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setProgress(0);
        progressDialog.setMax(100);
        UpdateChemicalsTask task = new UpdateChemicalsTask(this,
                materialService, progressDialog);
        if (Build.VERSION.SDK_INT >= 11) {
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                    new UpdateChemicalsRequest(materialService.getLastID()));
        } else {
            task.execute(new UpdateChemicalsRequest(materialService.getLastID()));
        }

    }

    @Override
    public void setSupportProgressBarIndeterminateVisibility(boolean visible) {
        super.setSupportProgressBarIndeterminateVisibility(false);
        isIndeterminateProgressVisible = visible;
        invalidateOptionsMenu();
    }

    public void setLoading(boolean visible) {
        setSupportProgressBarIndeterminateVisibility(visible);
    }

}