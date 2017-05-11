package com.slashmobility.bottleflip_android.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.activities.ChallengesActivity;
import com.slashmobility.bottleflip_android.adapters.RankingAdapter;
import com.slashmobility.bottleflip_android.model.Score;
import com.slashmobility.bottleflip_android.services.ServiceManager;
import com.slashmobility.bottleflip_android.services.callbacks.CallbackRanking;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Edgar-W10 on 8/4/2017.
 */

public class RankingFragment extends BaseFragment {

    @BindView(R.id.rv_ranking)
    RecyclerView rv_ranking;
    private RankingAdapter mAdapter;
    private View mView;
    private LinearLayoutManager mLinearLayoutManager;

    public RankingFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_ranking, container, false);
        ButterKnife.bind(this, mView);
        configViews();
        getValues();
        return mView;
    }

    private void getValues() {

        ServiceManager.getRanking(new CallbackRanking() {
            @Override
            public void onSuccess(ArrayList<Score> scores) {

                if (scores.size() > 0) {
                    mAdapter = new RankingAdapter(getActivity(), scores);
                    rv_ranking.setAdapter(mAdapter);
                }

            }

            @Override
            public void onError(int errorCode, String errorMessage) {

                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void configViews() {
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        rv_ranking.setLayoutManager(mLinearLayoutManager);
    }

}
