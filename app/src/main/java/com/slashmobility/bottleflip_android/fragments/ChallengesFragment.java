package com.slashmobility.bottleflip_android.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.adapters.ChallengeAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Edgar-W10 on 8/4/2017.
 */

public class ChallengesFragment extends BaseFragment {

    @BindView(R.id.rv_retos) RecyclerView rv_retos;
    private ChallengeAdapter mAdapter;
    private View mView;
    private LinearLayoutManager mLinearLayoutManager;

    public ChallengesFragment() {}

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
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        rv_retos.setLayoutManager(mLinearLayoutManager);
        ArrayList<String> list = new ArrayList<String>();

        //dummy rows
        for (int i =0; i<50; i++){
            list.add("");
        }


        mAdapter = new ChallengeAdapter(getActivity(),list);
        rv_retos.setAdapter(mAdapter);
    }
}
