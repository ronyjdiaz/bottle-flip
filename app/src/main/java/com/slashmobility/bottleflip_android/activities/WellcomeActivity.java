package com.slashmobility.bottleflip_android.activities;

import android.os.Bundle;
import android.widget.Button;

import com.slashmobility.bottleflip_android.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WellcomeActivity extends BaseActivity {


    @BindView(R.id.btnFacebookLogin)Button btnLogin;
    @BindView(R.id.btnRegister)Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnFacebookLogin)
    protected void gotoLogin(){
        openActivity(LoginActivity.class);

    }

    @OnClick(R.id.btnRegister)
    protected void gotoRegister(){
        openActivity(RegisterActivity.class);

    }
}
