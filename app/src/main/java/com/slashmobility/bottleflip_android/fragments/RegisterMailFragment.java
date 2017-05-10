package com.slashmobility.bottleflip_android.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.slashmobility.bottleflip_android.Constants;
import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.activities.ChallengesActivity;
import com.slashmobility.bottleflip_android.activities.RegisterActivity;
import com.slashmobility.bottleflip_android.model.User;
import com.slashmobility.bottleflip_android.singleton.SingletonSession;
import com.slashmobility.bottleflip_android.utils.Utils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterMailFragment extends BaseFragment {

    @BindView(R.id.edittextUsername)EditText medittextUsername;
    @BindView(R.id.edittextPassword)EditText medittextPassword;
    @BindView(R.id.edittextEmail)EditText medittextEmail;
    @BindView(R.id.btnContinue)Button nbtnContinue;
    SharedPreferences sharedPreferences;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "RegisterMailFragment";
    CallbackManager callbackManager;



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
        // FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        sharedPreferences = getActivity().getSharedPreferences(Constants.MYPREFERENCES, Context.MODE_PRIVATE);
        initListeners();
    }

    public RegisterMailFragment() {

    }

    private void initListeners() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }


        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.fragment_register_mail, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ButterKnife.bind(this,v);

        return v;
    }

    //edittextUsername, edittextPassword
    private boolean isValidateLogin(){
        if(!Utils.validEmail(medittextEmail.getText().toString()))
        {
            showMessageDialog(getResources().getString(R.string.invalid_email));
            return false;
        }
        if(TextUtils.isEmpty(medittextPassword.getText().toString()))
        {
            showMessageDialog(getResources().getString(R.string.pass_required));
            return false;
        }

        if(TextUtils.isEmpty(medittextUsername.getText().toString()))
        {
            showMessageDialog(getResources().getString(R.string.username_required));
            return false;
        }

        return true;
    }

    @OnClick(R.id.btnContinue)
    protected void continueClick(){

        if(isValidateLogin()){
            String email = medittextEmail.getText().toString();
            String password = medittextPassword.getText().toString();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());


                            if (task.isSuccessful()) {

                                DatabaseReference database = ((RegisterActivity)getActivity()).getDatabase();
                                DatabaseReference usersRef = database.child("users");
                                Map<String, User> users = new HashMap<String, User>();
                                User userObject = new User();
                                userObject.setEmail(email);
                                String mtokenDevice = sharedPreferences.getString(Constants.TOKENFIREBASE, "");
                                userObject.setFcmDeviceToken(mtokenDevice);
                                userObject.setDisplayName(medittextUsername.getText().toString());
                                userObject.setProviderID(Constants.PROVIDER_EMAIL);
                                userObject.setCreated(Calendar.getInstance().getTime().toString());
                                userObject.setBottleCode(SingletonSession.getInstance().getBottleCode());
                                usersRef.push().setValue(userObject);


                            }
                            else
                            {
                                Toast.makeText(getActivity(), getString(R.string.error_register),Toast.LENGTH_SHORT).show();
                            }


                        }
                    });
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
