package com.rel.hazmat.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import com.rel.hazmat.R;

public class TypefacedEditText extends EditText {

    public TypefacedEditText(Context context, AttributeSet attrs) {
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
    }

}
