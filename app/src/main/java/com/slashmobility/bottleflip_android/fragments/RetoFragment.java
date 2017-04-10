package com.slashmobility.bottleflip_android.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.adapters.RetoAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Edgar-W10 on 8/4/2017.
 */

public class RetoFragment extends BaseFragment {

    @BindView(R.id.rv_retos) RecyclerView rv_retos;
    private RetoAdapter mAdapter;
    private View mView;
    private LinearLayoutManager mLinearLayoutManager;

    public RetoFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_retos, container, false);
        ButterKnife.bind(this, mView);
        getViews();
        return mView;
    }

    private void getViews(){
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        rv_retos.setLayoutManager(mLinearLayoutManager);
        mAdapter = new RetoAdapter();
        rv_retos.setAdapter(mAdapter);
    }
}
