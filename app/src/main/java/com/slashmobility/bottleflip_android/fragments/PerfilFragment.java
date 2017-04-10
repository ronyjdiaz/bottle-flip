package com.slashmobility.bottleflip_android.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.slashmobility.bottleflip_android.R;

import butterknife.ButterKnife;

/**
 * Created by Edgar-W10 on 8/4/2017.
 */

public class PerfilFragment extends BaseFragment {

    private View mView;

    public PerfilFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_perfil, container, false);
        ButterKnife.bind(this, mView);
        getViews();
        return mView;
    }

    private void getViews(){
    }
}
