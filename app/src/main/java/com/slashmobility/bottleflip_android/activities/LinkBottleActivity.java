package com.slashmobility.bottleflip_android.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.fragments.LinkCodeFragment;
import com.slashmobility.bottleflip_android.fragments.RegisterMain_Fragment;

import butterknife.BindView;
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
