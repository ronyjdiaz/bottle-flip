package com.slashmobility.bottleflip_android.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.slashmobility.bottleflip_android.R;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyChallengeFragment extends BaseFragment {


    private int mpositionChallenge;

    public MyChallengeFragment() {
        // Required empty public constructor
    }

    public static MyChallengeFragment newInstance(int positionChallenge){
        MyChallengeFragment myChallengeFragment = new MyChallengeFragment();
        myChallengeFragment.mpositionChallenge = positionChallenge;
        return myChallengeFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_challenge, container, false);
        ButterKnife.bind(this,v);
        return v;
    }

}
