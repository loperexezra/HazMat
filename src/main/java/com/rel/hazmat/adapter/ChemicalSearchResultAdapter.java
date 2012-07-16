package com.rel.hazmat.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.rel.hazmat.R;
import com.rel.hazmat.dto.SearchDTO;
import com.rel.hazmat.widgets.TypefacedTextView;

/**
 * 
 * @author Lope Chupijay Emano
 *
 */
public class ChemicalSearchResultAdapter extends BaseAdapter {

    protected List<SearchDTO> searchDTOList;
    protected Context mContext;

    public ChemicalSearchResultAdapter(Context mContext, List<SearchDTO> searchDTOList) {
        this.mContext = mContext;
        this.searchDTOList = searchDTOList;
    }

    @Override
    public int getCount() {
        return searchDTOList.size();
    }

    @Override
    public SearchDTO getItem(int position) {
        return searchDTOList.get(position);
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.item_search, parent, false);
        }
        SearchDTO searchDTO = getItem(position);
        TypefacedTextView lblChemicalName = (TypefacedTextView) row
                .findViewById(R.id.lblChemicalName);
        TypefacedTextView lblFormula = (TypefacedTextView) row
                .findViewById(R.id.lblFormula);
        lblChemicalName.setText(searchDTO.getChemicalName());
        lblFormula.setText(searchDTO.getFormula());
        return row;
    }

}
