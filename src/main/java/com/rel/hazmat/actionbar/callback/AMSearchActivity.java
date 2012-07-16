package com.rel.hazmat.actionbar.callback;

import android.app.Activity;
import android.content.Intent;

import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.rel.hazmat.activity.DisplayActivity;
import com.rel.hazmat.dto.SearchDTO;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class AMSearchActivity implements ActionMode.Callback {
    public static final String VIEW = "View";
    protected IAMSearchActivity activity;
    protected SearchDTO searchDTO;

    public AMSearchActivity(IAMSearchActivity activity) {
        this.activity = activity;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        menu.add(VIEW).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        // mode.setTitle(numberSelected + " item selected");
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        if (item.getTitle().equals(VIEW)) {
            activity.displayMaterial(searchDTO.getSlug());
        }
        mode.finish();
        return true;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
    }

    public SearchDTO getSearchDTO() {
        return searchDTO;
    }

    public void setSearchDTO(SearchDTO searchDTO) {
        this.searchDTO = searchDTO;
    }

    public interface IAMSearchActivity {
        public void displayMaterial(String materialSlug);
    }

}
