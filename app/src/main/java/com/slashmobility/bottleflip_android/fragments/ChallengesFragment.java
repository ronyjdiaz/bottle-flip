package com.slashmobility.bottleflip_android.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.activities.ChallengesActivity;
import com.slashmobility.bottleflip_android.adapters.ChallengeAdapter;
import com.slashmobility.bottleflip_android.model.Challenge;
import com.slashmobility.bottleflip_android.singleton.SingletonSession;

import java.util.ArrayList;
import java.util.List;

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
        configViews();
        initListeners();
        return mView;
    }

    private void initListeners(){

        DatabaseReference database = ((ChallengesActivity)getActivity()).getDatabase();
        database.child("challenges").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList challengesList = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Challenge challenge = noteDataSnapshot.getValue(Challenge.class);
                    challengesList.add(challenge);
                }

                SingletonSession.getInstance().setChallenges(challengesList);

                mAdapter = new ChallengeAdapter(getActivity(),challengesList);
                rv_retos.setAdapter(mAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

    private void configViews(){
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_retos.setLayoutManager(mLinearLayoutManager);


    }
}
