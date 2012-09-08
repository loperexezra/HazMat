package com.rel.hazmat.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.rel.hazmat.R;
import com.rel.hazmat.dto.GridDTO;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class GridImageAdapter extends ArrayAdapter<GridDTO> {
    public static final String TAG = "GridImageAdapter";
    private final List<GridDTO> gridDTOList;
    private Activity mContext;

    public GridImageAdapter(Activity context, List<GridDTO> gridDTOList) {
        super(context, R.layout.item_grid_image, gridDTOList);
        this.mContext = context;
        this.gridDTOList = gridDTOList;
    }

    @Override
    public GridDTO getItem(int position) {
        return gridDTOList.get(position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.item_grid_image, parent, false);
        }
        GridDTO gridDTO = getItem(position);
        ImageView imgGrid = (ImageView) row.findViewById(R.id.imgGrid);
        imgGrid.setImageBitmap(decodeSampledBitmapFromResource(
                mContext.getResources(), gridDTO.getResourceID(), 100, 100));
        Log.i(TAG,
                "Displaying category inside list with title : "
                        + gridDTO.getSlug());
        return row;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res,
            int resId, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
            int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }
        return inSampleSize;
    }
}