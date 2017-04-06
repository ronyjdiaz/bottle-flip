package com.slashmobility.bottleflip_android.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.slashmobility.bottleflip_android.R;

/**
 * Created by Edgar on 05-04-2017.
 */

public class OnboardingAdapter extends PagerAdapter {

    private LayoutInflater layoutInflater;
    private Context mContext;

    private static final int[] layouts = {
        R.layout.slide_onboarding1,
        R.layout.slide_onboarding2,
        R.layout.slide_onboarding3
    };

    public OnboardingAdapter(Context context) {
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(layouts[position], container, false);
        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return layouts.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

}
