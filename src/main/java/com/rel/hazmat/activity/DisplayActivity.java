package com.rel.hazmat.activity;

import roboguice.activity.RoboActivity;
import android.os.Bundle;
import android.view.Window;

import com.rel.hazmat.R;

public class DisplayActivity extends RoboActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.display);
    }
}
