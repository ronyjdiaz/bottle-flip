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
import com.slashmobility.bottleflip_android.adapters.RankingAdapter;
import com.slashmobility.bottleflip_android.model.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Edgar-W10 on 8/4/2017.
 */

public class RankingFragment extends BaseFragment {

    @BindView(R.id.rv_ranking) RecyclerView rv_ranking;
    private RankingAdapter mAdapter;
    private View mView;
    private LinearLayoutManager mLinearLayoutManager;

    public RankingFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_ranking, container, false);
        ButterKnife.bind(this, mView);
        configViews();
        initListeners();
        return mView;
    }

    private void initListeners(){
        DatabaseReference database = ((ChallengesActivity)getActivity()).getDatabase();
        database.child("scores").orderByChild("score").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList  scores = new ArrayList<>();
                for (DataSnapshot scoreDataSnapshot : dataSnapshot.getChildren()) {
                    Score score = scoreDataSnapshot.getValue(Score.class);
                    scores.add(score);
                }
                Collections.reverse(scores);
                mAdapter = new RankingAdapter(getActivity(),scores);
                rv_ranking.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }

    private void configViews(){
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        rv_ranking.setLayoutManager(mLinearLayoutManager);
    }

}
