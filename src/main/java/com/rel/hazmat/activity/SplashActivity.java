package com.rel.hazmat.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import com.rel.hazmat.R;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class SplashActivity extends Activity {
    // Set the display time, in milliseconds (or extract it out as a
    // configurable parameter)
    private final int SPLASH_DISPLAY_LENGTH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(this);
        // Obtain the sharedPreference, default to true if not available
        boolean isSplashEnabled = sp.getBoolean("isSplashEnabled", true);

        if (isSplashEnabled) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Finish the splash activity so it can't be returned to.
                    SplashActivity.this.finish();
                    // Create an Intent that will start the main activity.
                    Intent mainIntent = new Intent(SplashActivity.this,
                            ICSSearchActivity.class);
                    SplashActivity.this.startActivity(mainIntent);
                }
            }, SPLASH_DISPLAY_LENGTH);
        } else {
            // if the splash is not enabled, then finish the activity
            // immediately and go to main.
            finish();
            Intent mainIntent = new Intent(SplashActivity.this,
                    ICSSearchActivity.class);
            SplashActivity.this.startActivity(mainIntent);
        }
    }
}