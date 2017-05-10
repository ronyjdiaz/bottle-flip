package com.slashmobility.bottleflip_android.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.slashmobility.bottleflip_android.MainActivity;
import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.activities.ChallengesActivity;
import com.slashmobility.bottleflip_android.activities.RegisterActivity;
import com.slashmobility.bottleflip_android.adapters.RankingAdapter;
import com.slashmobility.bottleflip_android.model.Score;
import com.slashmobility.bottleflip_android.singleton.SingletonSession;
import com.slashmobility.bottleflip_android.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Challenge;


public class RegisterMain_Fragment extends BaseFragment {

    @BindView(R.id.btnContinue)Button mbtnContinue;
    @BindView(R.id.edittextBottleCode)EditText medittextBottleCode;
    @BindView(R.id.textviewContinue)TextView mtextviewContinue;
    Boolean isValidCode = false;

    public RegisterMain_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register_main, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ButterKnife.bind(this,v);

        Utils.changeColorDrawable(mbtnContinue, this.getContext(), R.color.white);
        return v;
    }

    @OnClick(R.id.textviewContinue)
    protected void continue_no_code(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.alert_conitnue_no_code)
                .setTitle(R.string.continue_without_code);

        builder.setPositiveButton(R.string.continue_without_code, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //changeToFragment(new RegisterOptionsFragment());
                ((RegisterActivity)getActivity()).openActivity(ChallengesActivity.class);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();



    }

    @OnClick(R.id.btnContinue)
    protected void continueCode(){

        if(TextUtils.isEmpty(medittextBottleCode.getText().toString()))
        {
            showMessageDialog(getResources().getString(R.string.bottle_code_required));
            return ;
        }
        else {

            DatabaseReference database = ((RegisterActivity) getActivity()).getDatabase();


            database.child("bottleCodes").orderByChild("code").equalTo(medittextBottleCode.getText().toString()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot codeDaSnapshot : dataSnapshot.getChildren()) {
                        if (codeDaSnapshot.exists()) {
                            isValidCode = true;
                            SingletonSession.getInstance().setBottleCode(medittextBottleCode.getText().toString());
                            changeToFragment(new RegisterOptionsFragment());
                        } else {
                            showMessageDialog(getResources().getString(R.string.bottle_code_invalid));
                        }
                    }
                    if(!isValidCode){
                        showMessageDialog(getResources().getString(R.string.bottle_code_invalid));
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    showMessageDialog(getResources().getString(R.string.bottle_code_invalid));
                }

            });


        }
    }


}
