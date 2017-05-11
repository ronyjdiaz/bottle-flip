package com.slashmobility.bottleflip_android.activities;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.fragments.RegisterMainFragment;

import butterknife.ButterKnife;

public class RegisterActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        changeToFragment(new RegisterMainFragment());
        configViews();
    }

    private void configViews() {
        changeColorBarNotification(R.color.dark_blue);

    }


}
