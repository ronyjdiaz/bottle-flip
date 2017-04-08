package com.slashmobility.bottleflip_android.activities;

import android.app.Activity;
import android.app.AlertDialog;

import 	android.support.v4.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.slashmobility.bottleflip_android.fragments.RegisterMain_Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        RegisterMain_Fragment fragment = new RegisterMain_Fragment();
        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
        ButterKnife.bind(this);
        configViews();
    }

    private void configViews(){
        changeColorBarNotification(R.color.dark_blue);

    }
}
