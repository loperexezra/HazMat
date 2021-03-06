package com.rel.hazmat.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.rel.hazmat.R;

public class TypefacedTextView extends TextView {
    public static final String TAG = "TypefacedTextView";

    public TypefacedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Typeface.createFromAsset doesn't work in the layout editor.
        // Skipping...
        if (isInEditMode()) {
            return;
        }

        TypedArray styledAttrs = context.obtainStyledAttributes(attrs,
                R.styleable.TypefacedTextView);
        String fontName = styledAttrs
                .getString(R.styleable.TypefacedTextView_typeface);
        styledAttrs.recycle();

        if (fontName != null) {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(),
                    fontName);
            setTypeface(typeface);
        }

        String text = (String) getText();
        Log.i(TAG, "Text : " + text);
        setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);
    }

}
