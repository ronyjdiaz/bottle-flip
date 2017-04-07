package com.slashmobility.bottleflip_android.activities;

import android.os.Bundle;
import android.widget.Button;

import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WellcomeActivity extends BaseActivity {


    @BindView(R.id.btnFacebookLogin)Button btnLogin;
    @BindView(R.id.btnRegister)Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        Utils.changeColorDrawable(btnLogin, WellcomeActivity.this, R.color.white);
        Utils.changeStrokeColorDrawable(btnLogin, WellcomeActivity.this, 3, R.color.black);

        Utils.changeColorDrawable(btnRegister, WellcomeActivity.this, R.color.black);
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
