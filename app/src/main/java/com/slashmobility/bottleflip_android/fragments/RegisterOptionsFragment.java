package com.slashmobility.bottleflip_android.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RegisterOptionsFragment extends BaseFragment {

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
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ButterKnife.bind(this,v);
        Utils.changeColorDrawable(mbtnRegisterMail, this.getContext(), R.color.white);

        return v;
    }

    @OnClick(R.id.btnRegisterMail)
    protected void RegisterMailClick(){
        changeToFragment(new RegisterMailFragment());
    }


    @OnClick(R.id.btnRegisterFacebook)
    protected void RegisterFacebookClick(){
        changeToFragment(new RegisterMailFragment());
    }

}
