package com.slashmobility.bottleflip_android.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Window;
import android.view.WindowManager;

import com.github.paolorotolo.appintro.AppIntro;

import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.fragments.SampleSlide;

public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_intro);
        addSlide(SampleSlide.newInstance(R.layout.slide_onboarding1));
        addSlide(SampleSlide.newInstance(R.layout.slide_onboarding2));
        addSlide(SampleSlide.newInstance(R.layout.slide_onboarding3));

        setFadeAnimation();




    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
        Intent main = new Intent(this, WellcomeActivity.class);
        startActivity(main);
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.edit().putBoolean("onboarding_complete", true).apply();
        Intent main = new Intent(this, WellcomeActivity.class);
        startActivity(main);
        finish();

    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }
}
