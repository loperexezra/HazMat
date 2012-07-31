package com.rel.hazmat.tasks;

import java.io.UnsupportedEncodingException;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rel.hazmat.db.service.contracts.IMaterialService;
import com.rel.hazmat.json.http.HttpHandler;
import com.rel.hazmat.json.http.RequestHandler;
import com.rel.hazmat.json.model.request.UpdateChemicalsRequest;
import com.rel.hazmat.json.model.serializable.ChemicalResponseSerializable;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class UpdateChemicalsTask extends
        AsyncTask<UpdateChemicalsRequest, Integer, Void> {
    protected Gson gson = new GsonBuilder().create();
    protected Context mContext;
    protected IMaterialService materialService;
    protected ProgressDialog progressDialog;

    public UpdateChemicalsTask(Context mContext,
            IMaterialService materialService) {
        this.mContext = mContext;
        this.materialService = materialService;
    }

    public UpdateChemicalsTask(Context mContext,
            IMaterialService materialService, ProgressDialog progressDialog) {
        this.mContext = mContext;
        this.materialService = materialService;
        this.progressDialog = progressDialog;
    }

    @Override
    protected Void doInBackground(UpdateChemicalsRequest... req) {
        RequestHandler handler = new RequestHandler(new HttpHandler(
                "http://www.hazmatiq.net/json/json.php"));
        ChemicalResponseSerializable[] response = null;
        try {
            publishProgress(30);
            String responseString = handler.sendRequest(req[0].getMapping());
            publishProgress(60);
            response = gson.fromJson(responseString,
                    ChemicalResponseSerializable[].class);
            materialService.save(response);
            publishProgress(100);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (progressDialog != null) {
            progressDialog.show();
        }
    }

    @Override
    protected void onPostExecute(Void result) {
        Toast.makeText(mContext, "Your chemical list is now up to date",
                Toast.LENGTH_LONG).show();
        super.onPostExecute(result);
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        // int intProgress = (Window.PROGRESS_END - Window.PROGRESS_START) / 100
        // * progress[0];
        if (progressDialog != null) {
            progressDialog.setProgress(progress[0]);
        }
    }

}
