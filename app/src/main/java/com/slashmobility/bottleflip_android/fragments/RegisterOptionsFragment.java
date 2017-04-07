package com.slashmobility.bottleflip_android.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.slashmobility.bottleflip_android.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RegisterOptionsFragment extends Fragment {

    @BindView(R.id.btnRegisterMail)Button mbtnRegisterMail;
    @BindView(R.id.btnRegisterFacebook)Button mbtnRegisterFacebook;


    public RegisterOptionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_register_options, container, false);
        ButterKnife.bind(this,v);

        return v;
    }

    @OnClick(R.id.btnRegisterMail)
    protected void RegisterMailClick(){

    }


    @OnClick(R.id.btnRegisterFacebook)
    protected void RegisterFacebookClick(){

    }

}
