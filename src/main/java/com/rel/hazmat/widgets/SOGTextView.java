package com.rel.hazmat.widgets;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import android.util.Log;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class SOGTextView extends TypefacedTextView {
    private static final String TAG = "SOGTextView";
    public static final String ALWAYS = "ALWAYS";
    public static final String YELLOW = "YELLOW";
    public static final String GO = " GO";
    public static final String NO_GO = " NO GO";

    public SOGTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        String text = (String) getText();
        Log.i(TAG, "Text contains GO : " + text.contains(GO) + " text dump : "
                + text);
        if (text.contains(ALWAYS)) {
            Integer startPosition = text.indexOf(ALWAYS, 0);
            SpannableString spannable = new SpannableString(text);
            spannable.setSpan(new ForegroundColorSpan(Color.GREEN),
                    startPosition, startPosition + 6, 0);
            spannable.setSpan(new RelativeSizeSpan(1.5f), startPosition,
                    startPosition + 6, 0);
            setText(spannable);
        } else if (text.contains(NO_GO)) {
            Integer startPosition = text.indexOf(NO_GO, 0);
            SpannableString spannable = new SpannableString(text);
            spannable.setSpan(new ForegroundColorSpan(Color.RED),
                    startPosition, startPosition + 6, 0);
            spannable.setSpan(new RelativeSizeSpan(1.5f), startPosition,
                    startPosition + 6, 0);
            if (text.contains(YELLOW)){
                Integer startPositionYellow = text.indexOf(YELLOW, 0);
                spannable.setSpan(new ForegroundColorSpan(Color.YELLOW), startPositionYellow, startPositionYellow + 6, 0);
            }
            setText(spannable);
        } else if (text.contains(GO)) {
            Integer startPosition = text.indexOf(GO, 0);
            SpannableString spannable = new SpannableString(text);
            spannable.setSpan(new ForegroundColorSpan(Color.GREEN),
                    startPosition, startPosition + 3, 0);
            spannable.setSpan(new RelativeSizeSpan(1.5f), startPosition,
                    startPosition + 3, 0);
            setText(spannable);
        }
    }
}
