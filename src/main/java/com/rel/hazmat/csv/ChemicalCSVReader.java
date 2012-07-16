package com.rel.hazmat.csv;

import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.util.Log;

import com.csvreader.CsvReader;
import com.rel.hazmat.db.dao.HazardousMaterialDAO;
import com.rel.hazmat.db.model.HazardousMaterial;
import com.rel.hazmat.exception.NullSlugException;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class ChemicalCSVReader {
    public static final String TAG = "ChemicalCSVReader";
    protected HazardousMaterialDAO materialDAO;
    protected Context context;

    public ChemicalCSVReader(Context context, HazardousMaterialDAO materialDAO) {
        this.materialDAO = materialDAO;
        this.context = context;
    }

    public void loadToDatabase() {
        try {
            CsvReader products = new CsvReader(new InputStreamReader(context
                    .getAssets().open("csv/chemicals.csv")));
            products.readHeaders();
            while (products.readRecord()) {
                String name = products.get("name");
                String formula = products.get("formula");
                String states = products.get("states");
                String dot_no = products.get("dot_no");
                String molecular_weight = products.get("molecular_weight");
                String solubility = products.get("solubility");
                String flash_point = products.get("flash_point");
                String ionization = products.get("ionization");
                String gravity = products.get("gravity");
                String UEL = products.get("UEL");
                String LEL = products.get("LEL");
                String IDLH = products.get("IDLH");
                String REL = products.get("REL");
                String PEL = products.get("PEL");
                Log.i(TAG, " name " + name + " formula " + formula + " states "
                        + states);
                try {
                    materialDAO.save(new HazardousMaterial(name, name, formula,
                            states, dot_no, molecular_weight, solubility,
                            flash_point, ionization, gravity, UEL, LEL, IDLH,
                            REL, PEL));
                } catch (NullSlugException e) {
                    e.printStackTrace();
                }
            }
            products.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
