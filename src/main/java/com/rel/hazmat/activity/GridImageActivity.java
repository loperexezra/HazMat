package com.rel.hazmat.activity;

import java.util.ArrayList;
import java.util.List;

import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockActivity;
import com.rel.hazmat.R;
import com.rel.hazmat.adapter.GridImageAdapter;
import com.rel.hazmat.dto.GridDTO;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class GridImageActivity extends RoboSherlockActivity {
    protected GridImageAdapter adapter;
    @InjectView(R.id.gridView)
    protected GridView gridView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_chart);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("HazMatIQ");
        init();
    }

    public void init() {
        adapter = new GridImageAdapter(this, getGraphList());
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                GridDTO gridDTO = adapter.getItem(position);
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),
                        ViewImageActivity.class);
                intent.putExtra(ViewImageActivity.IMAGE_RESOURCE_ID,
                        gridDTO.getResourceID());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(
            com.actionbarsherlock.view.MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getTitle().equals(getSupportActionBar().getTitle().toString())) {
            Intent intent = new Intent();
            intent.setClass(this, ICSSearchActivity.class);
            startActivity(intent);
        }
        return true;
    }

    public List<GridDTO> getGraphList() {
        List<GridDTO> returnList = new ArrayList<GridDTO>();
        returnList.add(new GridDTO("chart1", R.drawable.chart1));
        returnList.add(new GridDTO("chart2", R.drawable.chart2));
        returnList.add(new GridDTO("chart3", R.drawable.chart3));
        returnList.add(new GridDTO("chart4", R.drawable.chart4));
        returnList.add(new GridDTO("chart5", R.drawable.chart5));
        returnList.add(new GridDTO("chart6", R.drawable.chart6));
        return returnList;
    }
}