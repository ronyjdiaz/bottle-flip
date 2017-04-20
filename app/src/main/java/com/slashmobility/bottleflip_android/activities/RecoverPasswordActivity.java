package com.slashmobility.bottleflip_android.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.utils.BaseUtils;
import com.slashmobility.bottleflip_android.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecoverPasswordActivity extends BaseActivity {

    @BindView(R.id.btnRecoverPassword)Button mbtnRecoverPassword;
    @BindView(R.id.edittextEmail)EditText medittextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_password);
        ButterKnife.bind(this);
        configViews();

    }
    private void configViews(){
        Utils.changeColorDrawable(mbtnRecoverPassword, RecoverPasswordActivity.this, R.color.white);
        changeColorBarNotification(R.color.light_orange);



    }

    @OnClick(R.id.btnRecoverPassword)
    protected void recoverPasswordClick(){
        String mEmail = medittextEmail.getText().toString();
        if(Utils.validEmail(mEmail)) {
            Utils.hideSoftKeyboard(RecoverPasswordActivity.this);
            //DO ACTION
        }
        else {
            showMessageDialog(getResources().getString(R.string.invalid_email));
        }
    }


}
