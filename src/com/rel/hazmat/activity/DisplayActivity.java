package com.rel.hazmat.activity;

import com.rel.hazmat.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class DisplayActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.search);
    }
}
