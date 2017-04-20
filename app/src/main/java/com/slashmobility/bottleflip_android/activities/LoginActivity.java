package com.slashmobility.bottleflip_android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


        fbLoginManager = com.facebook.login.LoginManager.getInstance();
        callbackManager = CallbackManager.Factory.create();
       // FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        configViews();
        initListeners();




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void configViews(){
        Utils.changeColorDrawable(mbtnLogin, LoginActivity.this, R.color.white);
        changeColorBarNotification(R.color.light_orange);
        enableButtonLogin(false);

    }

    private void enableButtonLogin(boolean enabled)
    {
        mbtnLogin.setEnabled(enabled);
        if(enabled)
            mbtnLogin.getBackground().setAlpha(255);
        else
            mbtnLogin.getBackground().setAlpha(128);

    }

    private void initListeners(){
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };


        fbLoginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override

            public void onSuccess(LoginResult loginResult) {
                //openActivity(ChallengesActivity.class);
                handleFacebookAccessToken(loginResult.getAccessToken());

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

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(isValidateLogin(false)){
                    enableButtonLogin(true);
                }
                else
                    enableButtonLogin(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };



        medittextPassword.addTextChangedListener(textWatcher);
        medittextUsername.addTextChangedListener(textWatcher);
    }

    @OnClick(R.id.btnFacebookLogin)
    protected void facebookLoginClick(){
        fbLoginManager.logInWithReadPermissions(LoginActivity.this, Arrays.asList("email", "public_profile", "user_birthday"));
    }

    @OnClick(R.id.btnLogin)
    protected void loginClick(){
        if(isValidateLogin(true)) {
            Utils.hideSoftKeyboard(LoginActivity.this);
            String mEmail = medittextUsername.getText().toString();
            String mPassword = medittextPassword.getText().toString();
            openActivity(ChallengesActivity.class);
        }
    }

    @OnClick(R.id.textviewForgotPassword)
    protected void forgotPassword(){
        openActivity(RecoverPasswordActivity.class);
    }

    //edittextUsername, edittextPassword
    private boolean isValidateLogin(boolean showAlert){
        if(TextUtils.isEmpty(medittextUsername.getText().toString()))
        {
            if(showAlert)
                showMessageDialog(getResources().getString(R.string.username_required));
            return false;
        }
        if(TextUtils.isEmpty(medittextPassword.getText().toString()))
        {
            if(showAlert)
                showMessageDialog(getResources().getString(R.string.pass_required));
            return false;
        }

        return true;
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());



                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                        finish();
                        openActivity(ChallengesActivity.class);

                        // ...
                    }
                });
    }





}
