package com.slashmobility.bottleflip_android.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.slashmobility.bottleflip_android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChallengeDetailActivity extends BaseActivity {

    @BindView(R.id.toolbar)Toolbar mToolbar;
    @BindView(R.id.toolbarTitle)TextView mtoolbarTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_detail);
        ButterKnife.bind(this);
        configViews();

    }

    private void configViews(){
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mtoolbarTitle.setText(getString(R.string.challenge));
    }
}
