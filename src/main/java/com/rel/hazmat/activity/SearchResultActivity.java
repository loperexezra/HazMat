package com.rel.hazmat.activity;

import java.util.List;

import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.view.Window;
import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.rel.hazmat.R;
import com.rel.hazmat.actionbar.ABSearchActivity;
import com.rel.hazmat.actionbar.callback.AMSearchActivity.IAMSearchActivity;
import com.rel.hazmat.adapter.ChemicalSearchResultAdapter;
import com.rel.hazmat.db.dao.HazardousMaterialDAO;
import com.rel.hazmat.db.service.contracts.IMaterialService;
import com.rel.hazmat.dto.SearchDTO;
import com.rel.hazmat.tasks.SearchMaterialTask;
import com.rel.hazmat.tasks.SearchMaterialTask.ISearchMaterialTask;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class SearchResultActivity extends ABSearchActivity implements
        IAMSearchActivity, ISearchMaterialTask {
    public static final String TAG = "SearchResultActivity";
    public static final String SEARCH_QUERY = "search_query";

    @Inject
    protected HazardousMaterialDAO materialDAO;
    @Inject
    protected IMaterialService materialService;
    @InjectView(R.id.lvwChemicals)
    protected ListView lvwChemicals;

    protected ChemicalSearchResultAdapter adapter;
    protected String query;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result_list);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initForm();
    }

    public void initForm() {
        lvwChemicals.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                SearchDTO selectedItem = adapter.getItem(position);
                Log.i("SearchResultActivity", "Selected product slug: "
                        + selectedItem.getChemicalName());
                displayMaterial(selectedItem.getSlug());
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String chemicalSlug = extras.getString(SEARCH_QUERY);
            Log.i(TAG, "received search query from extra" + chemicalSlug);
            if (!Strings.isNullOrEmpty(chemicalSlug)) {
                searchDatabase(chemicalSlug);
                query = chemicalSlug;
            } else {
                Log.i(TAG, "This was called with an empty search query");
            }
        }
    }

    public void searchDatabase(String query) {
        Log.i(TAG, "searching database with query : " + query);
        SearchMaterialTask task = new SearchMaterialTask(this, materialService);
        if (Build.VERSION.SDK_INT >= 11) {
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, query);
        } else {
            task.execute(query);
        }
    }

    public void setChemicalList(List<SearchDTO> searchList) {
        Log.i(TAG, "received search DTO list with size " + searchList.size());
        adapter = new ChemicalSearchResultAdapter(this, searchList);
        lvwChemicals.setAdapter(adapter);
    }

    @Override
    public void displayMaterial(String materialSlug) {
        Intent intent = new Intent();
        intent.setClass(this, ViewChoicesActivity.class);
        intent.putExtra(ViewChoicesActivity.SEARCH_QUERY, query);
        intent.putExtra(ViewChoicesActivity.MATERIAL_SLUG, materialSlug);
        startActivity(intent);
    }

    @Override
    public void success(List<SearchDTO> searchDTOList) {
        setChemicalList(searchDTOList);
    }

    @Override
    public void fail(String message) {
        Toast.makeText(this, "No chemical found", Toast.LENGTH_LONG).show();
    }

}
