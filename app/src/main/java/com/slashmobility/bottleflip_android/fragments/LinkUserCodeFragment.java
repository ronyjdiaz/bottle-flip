package com.slashmobility.bottleflip_android.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.activities.LinkBottleActivity;
import com.slashmobility.bottleflip_android.utils.Utils;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class LinkUserCodeFragment extends Fragment {


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
        // configViews();
        return v;

    }




}
