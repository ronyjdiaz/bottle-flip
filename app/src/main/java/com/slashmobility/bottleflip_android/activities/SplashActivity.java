package com.slashmobility.bottleflip_android.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import com.slashmobility.bottleflip_android.Constants;
import com.slashmobility.bottleflip_android.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends BaseActivity {
    // Set the duration of the splash screen


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initListeners();
    }

    private void initListeners(){

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                // Start the next activity

                openActivity(IntroActivity.class);
                // Close the activity so the user won't able to go back this
                // activity pressing Back button
                finish();
            }
        };
        // Simulate a long loading process on application startup.
        Timer timer = new Timer();
        timer.schedule(task, Constants.SPLASH_SCREEN_DELAY);

    }
}
