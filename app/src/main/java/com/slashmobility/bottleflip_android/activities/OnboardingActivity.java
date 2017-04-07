package com.slashmobility.bottleflip_android.activities;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.adapters.OnboardingAdapter;
import com.slashmobility.bottleflip_android.utils.PreferenceManager;
import com.slashmobility.bottleflip_android.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnPageChange;

/**
 * Created by Edgar-W10 on 5/4/2017.
 */

public class OnboardingActivity extends BaseActivity {


    @BindView(R.id.pager_slider) ViewPager viewPager;
    @BindView(R.id.layoutDots) LinearLayout dotsLayout;
    @BindView(R.id.btn_next) Button btnNext;
    @BindView(R.id.btn_skip) TextView btnSkip;

    private OnboardingAdapter onboardingViewPagerAdapter;
    private TextView[] dots;
    private static final int LAYOUTS = 3;
    private PreferenceManager prefManager;
    private int[] colorsActive, colorsInactive;
    private static final int[] COLOR_BUTTON = {R.color.dot_light_screen1, R.color.dot_light_screen2, R.color.dot_light_screen3};
    private ViewPager.OnPageChangeListener viewPagerPageChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefManager = new PreferenceManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            gotoWelcome();
            finish();
        }

        setContentView(R.layout.activity_onboarding);
        ButterKnife.bind(this);
        getViews();
        loadSlider();
    }

    private void getViews(){
        Utils.changeColorDrawable(btnNext, OnboardingActivity.this, COLOR_BUTTON[0]);
        addBottomDots(0);
    }

    private void loadSlider(){
        onboardingViewPagerAdapter = new OnboardingAdapter(this);
        viewPager.setAdapter(onboardingViewPagerAdapter);
    }

    @OnClick(R.id.btn_skip)
    protected void gotoWelcome(){
        prefManager.setFirstTimeLaunch(false);
        openActivity(WelcomeActivity.class);
    }

    @OnClick(R.id.btn_next)
    protected void gotoNext(){
        int current = getItem(+1);
        if (current < LAYOUTS) {
            Utils.changeColorDrawable(btnNext, OnboardingActivity.this, COLOR_BUTTON[current]);
            // move to next screen
            viewPager.setCurrentItem(current);
        } else {
            gotoWelcome();
        }
    }

    @OnPageChange(value = R.id.pager_slider, callback = OnPageChange.Callback.PAGE_SELECTED)
    protected void pageSelected(int position){
        addBottomDots(position);
        Utils.changeColorDrawable(btnNext, OnboardingActivity.this, COLOR_BUTTON[position]);
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[LAYOUTS];

        colorsActive = getResources().getIntArray(R.array.array_dot_active);
        colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }



}
