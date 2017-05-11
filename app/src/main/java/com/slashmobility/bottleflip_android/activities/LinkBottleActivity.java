package com.slashmobility.bottleflip_android.activities;

import android.os.Bundle;

import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.fragments.LinkCodeFragment;

import butterknife.ButterKnife;

public class LinkBottleActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_bottle);
        ButterKnife.bind(this);
        changeToFragment(new LinkCodeFragment());

    }


}
