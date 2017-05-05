package com.slashmobility.bottleflip_android.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.model.Challenge;
import com.slashmobility.bottleflip_android.model.Rule;
import com.slashmobility.bottleflip_android.singleton.SingletonSession;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ReviewTutorialFragment extends BaseFragment implements YouTubePlayer.OnInitializedListener {
    @BindView(R.id.btnAccept)Button mbtnAccept;
    @BindView(R.id.textviewChallengeName)TextView mtextviewChallengeName;
    @BindView(R.id.textviewInstructionsChallenge)TextView mtextviewInstructionsChallenge;
    @BindView(R.id.toolbar)Toolbar mToolbar;

    private int mpositionChallenge;

    public ReviewTutorialFragment() {
        // Required empty public constructor
    }

    public static ReviewTutorialFragment newInstance(int positionChallenge){
        ReviewTutorialFragment reviewTutorialFragment = new ReviewTutorialFragment();
        reviewTutorialFragment.mpositionChallenge = positionChallenge;
        return reviewTutorialFragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_review_tutorial, container, false);
        ButterKnife.bind(this,v);
        configViews();
        setData();
        return v;

    }

    private void configViews(){
        mbtnAccept.setVisibility(View.GONE);
        mToolbar.setVisibility(View.GONE);

    }

    private void setData(){

        Challenge challenge;

        if(mpositionChallenge>=0){
            challenge = SingletonSession.getInstance().getChallenges().get(mpositionChallenge);
            mtextviewChallengeName.setText(challenge.getName());
            mtextviewInstructionsChallenge.setText(challenge.getDescription());
            for (Rule rule: challenge.getPoints())
            {
                mtextviewInstructionsChallenge.setText( mtextviewInstructionsChallenge.getText().toString() +  System.lineSeparator());
                mtextviewInstructionsChallenge.setText(mtextviewInstructionsChallenge.getText().toString() + "-" + String.valueOf(rule.getValue()));
                mtextviewInstructionsChallenge.setText(mtextviewInstructionsChallenge.getText().toString() + " " + rule.getTitle());

            }
        }

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            youTubePlayer.cueVideo("ch7_EyVvldM");
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        getFragmentManager().findFragmentById(R.id.youtube_fragment);
    }
}
