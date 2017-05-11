package com.slashmobility.bottleflip_android.activities;

import android.os.Bundle;

import com.slashmobility.bottleflip_android.Constants;
import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.utils.PreferenceManager;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends BaseActivity {


    private PreferenceManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        prefManager = new PreferenceManager(this);
        initTimer();
    }

    private void initTimer() {

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                // Start the next activity
                finish();
                if (prefManager.isFirstTimeLaunch()) {
                    openActivity(OnboardingActivity.class);
                } else {
                    openActivity(WelcomeActivity.class);
                }


            }
        };
        // Simulate a long loading process on application startup.
        Timer timer = new Timer();
        timer.schedule(task, Constants.SPLASH_SCREEN_DELAY);

    }
}
