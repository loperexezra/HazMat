package com.rel.hazmat.actionbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.rel.hazmat.HazMatBaseActivity;
import com.rel.hazmat.R;
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
    public static final String HELP = "Help";

    protected boolean isIndeterminateProgressVisible;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setTheme(R.style.Theme_Sherlock_Light_ForceOverflow);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
        super.onCreateOptionsMenu(menu);

        boolean isLight = true;

        menu.add(SEARCH)
                .setIcon(
                        isLight ? R.drawable.ic_search_inverse
                                : R.drawable.ic_search)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add(LOGIN).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(HELP).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(
            com.actionbarsherlock.view.MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getTitle().equals(getString(R.string.app_name))) {
        } else if (item.getTitle().equals(HELP)) {
            Toast.makeText(this, "Long press in order to select an item",
                    Toast.LENGTH_SHORT).show();
        }
        return true;
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