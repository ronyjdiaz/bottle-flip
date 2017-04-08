package com.slashmobility.bottleflip_android.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.utils.Utils;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    CallbackManager callbackManager;
    com.facebook.login.LoginManager fbLoginManager;

    @BindView(R.id.btnFacebookLoginDummy)Button mbtnFacebookLoginDummy;
    @BindView(R.id.btnFacebookLogin)Button mbtnFacebookLogin;
    @BindView(R.id.btnLogin)Button mbtnLogin;
    @BindView(R.id.edittextUsername)EditText medittextUsername;
    @BindView(R.id.edittextPassword)EditText medittextPassword;
    @BindView(R.id.textviewForgotPassword)TextView mtextviewForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


        fbLoginManager = com.facebook.login.LoginManager.getInstance();
        callbackManager = CallbackManager.Factory.create();
        configViews();
        initListeners();


    }

    private void configViews(){
        Utils.changeColorDrawable(mbtnLogin, LoginActivity.this, R.color.white);
    }

    private void initListeners(){
        fbLoginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override

            public void onSuccess(LoginResult loginResult) {
                openActivity(ChallengesActivity.class);

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                String mErrorMessage = getString(R.string.connection_error);
                Toast.makeText(LoginActivity.this,mErrorMessage, Toast.LENGTH_SHORT).show();


            }
        });
    }

    @OnClick(R.id.btnFacebookLogin)
    protected void facebookLoginClick(){
        fbLoginManager.logInWithReadPermissions(LoginActivity.this, Arrays.asList("email", "public_profile", "user_birthday"));
    }

    @OnClick(R.id.btnLogin)
    protected void loginClick(){
        if(isValidateLogin()) {
            Utils.hideSoftKeyboard(LoginActivity.this);
            String mEmail = medittextUsername.getText().toString();
            String mPassword = medittextPassword.getText().toString();
            openActivity(ChallengesActivity.class);
        }
    }

    //edittextUsername, edittextPassword
    private boolean isValidateLogin(){
        if(!Utils.validEmail(medittextUsername.getText().toString()))
        {
            showMessageDialog(getResources().getString(R.string.invalid_email));
            return false;
        }
        if(TextUtils.isEmpty(medittextPassword.getText().toString()))
        {
            showMessageDialog(getResources().getString(R.string.pass_required));
            return false;
        }

        return true;
    }
}
