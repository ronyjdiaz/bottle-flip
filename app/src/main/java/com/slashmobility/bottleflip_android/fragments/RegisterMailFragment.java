package com.slashmobility.bottleflip_android.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterMailFragment extends BaseFragment {

    @BindView(R.id.edittextUsername)EditText medittextUsername;
    @BindView(R.id.edittextPassword)EditText medittextPassword;
    @BindView(R.id.edittextEmail)EditText medittextEmail;
    @BindView(R.id.btnContinue)Button nbtnContinue;


    public RegisterMailFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.fragment_register_mail, container, false);
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
            //Valido
        }

    }

}
