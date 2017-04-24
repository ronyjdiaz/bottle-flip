package com.slashmobility.bottleflip_android.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.activities.LinkBottleActivity;
import com.slashmobility.bottleflip_android.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class LinkUserCodeFragment extends BaseFragment {

    @BindView(R.id.toolbar)Toolbar mToolbar;
    @BindView(R.id.toolbarTitle)TextView mtoolbarTitle;
    @BindView(R.id.edittextUser)EditText medittextUsername;
    @BindView(R.id.edittextPassword)EditText medittextPassword;
    @BindView(R.id.edittextEmail)EditText medittextEmail;
    @BindView(R.id.btnRegister)Button mbtnRegister;
    @BindView(R.id.btnRegisterFacebook)Button mbtnRegisterFacebook;
    @BindView(R.id.imageviewBackButton)ImageView mimageviewBackButton;




    public LinkUserCodeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_link_user_code, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        ButterKnife.bind(this,v);
         configViews();
        return v;

    }

    private void configViews(){
        Utils.changeColorDrawable(mbtnRegister, this.getContext(), R.color.color_bar_perfil);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        mtoolbarTitle.setText(getString(R.string.text_link));
        mToolbar.setBackgroundResource(R.color.color_bar_perfil);
        mimageviewBackButton.setVisibility(View.VISIBLE);
        ((LinkBottleActivity)getActivity()).changeColorBarNotification(R.color.color_bar_perfil);
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

    @OnClick(R.id.btnRegister)
    protected void register(){
        if(isValidateLogin()){
            Toast.makeText(getActivity(), "Registrando...", Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R.id.imageviewBackButton)
    protected void back(){

        getActivity().getSupportFragmentManager().popBackStack();

    }







}
