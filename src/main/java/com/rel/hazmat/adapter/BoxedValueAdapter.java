package com.rel.hazmat.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.rel.hazmat.R;
import com.rel.hazmat.dto.ListViewDTO;
import com.rel.hazmat.widgets.TypefacedTextView;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class BoxedValueAdapter extends BaseAdapter {

    protected List<ListViewDTO> activityDTOList;
    protected Context mContext;

    public BoxedValueAdapter(Context mContext, List<ListViewDTO> activityDTOList) {
        this.mContext = mContext;
        this.activityDTOList = activityDTOList;
    }

    @Override
    public int getCount() {
        return activityDTOList.size();
    }

    @Override
    public ListViewDTO getItem(int position) {
        return activityDTOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    public void addAll(List<ListViewDTO> activityDTOList){
        this.activityDTOList.addAll(activityDTOList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.item_boxed_value, parent, false);
        }
        ListViewDTO activityDTO = getItem(position);
        TypefacedTextView lblActivityName = (TypefacedTextView) row
                .findViewById(R.id.lblActivityName);
        TypefacedTextView lblActivityValue = (TypefacedTextView) row
                .findViewById(R.id.lblActivityValue);
        lblActivityName.setText(activityDTO.getValueName());
        lblActivityValue.setText(activityDTO.getValue());
        return row;
    }
}
