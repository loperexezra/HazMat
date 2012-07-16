package com.rel.hazmat.tasks;

import java.util.List;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.actionbarsherlock.view.Window;
import com.google.common.base.Strings;
import com.rel.hazmat.db.service.contracts.IMaterialService;
import com.rel.hazmat.dto.SearchDTO;
import com.rel.hazmat.tasks.contracts.IProgressLoader;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class SearchMaterialTask extends
        AsyncTask<String, Integer, List<SearchDTO>> {
    protected ISearchMaterialTask activity;
    protected IMaterialService materialService;
    protected ProgressDialog progressDialog;

    public SearchMaterialTask(ISearchMaterialTask activity,
            IMaterialService materialService) {
        this.activity = activity;
        this.materialService = materialService;
    }

    public SearchMaterialTask(ISearchMaterialTask activity,
            ProgressDialog progressDialog) {
        this.activity = activity;
        this.progressDialog = progressDialog;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (progressDialog != null) {
            progressDialog.show();
        }
    }

    @Override
    protected List<SearchDTO> doInBackground(String... queries) {
        List<SearchDTO> searchDTOList = null;
        if (queries.length > 0) {
            String query = queries[0];
            if (!Strings.isNullOrEmpty(query)) {
                searchDTOList = materialService.searchMaterial(query);
            }
        }
        return searchDTOList;
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        int intProgress = (Window.PROGRESS_END - Window.PROGRESS_START) / 100
                * progress[0];
        activity.setSupportProgress(intProgress);
        if (progressDialog != null) {
            progressDialog.setProgress(progress[0]);
        }
    }

    @Override
    protected void onPostExecute(List<SearchDTO> result) {
        activity.success(result);
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    public interface ISearchMaterialTask extends IProgressLoader {
        public void success(List<SearchDTO> searchDTOList);

        public void fail(String message);
    }
}
