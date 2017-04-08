package com.slashmobility.bottleflip_android.activities;

import android.os.Bundle;
import android.widget.Button;

import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends BaseActivity {


    @BindView(R.id.btnLogin)Button btnLogin;
    @BindView(R.id.btnRegister)Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        Utils.changeColorDrawable(btnLogin, WelcomeActivity.this, R.color.white);
        Utils.changeStrokeColorDrawable(btnLogin, WelcomeActivity.this, 3, R.color.black);

        Utils.changeColorDrawable(btnRegister, WelcomeActivity.this, R.color.black);
    }

    @OnClick(R.id.btnLogin)
    protected void gotoLogin(){
        openActivity(LoginActivity.class);
    }

    @OnClick(R.id.btnRegister)
    protected void gotoRegister(){
        openActivity(RegisterActivity.class);

    }
}
