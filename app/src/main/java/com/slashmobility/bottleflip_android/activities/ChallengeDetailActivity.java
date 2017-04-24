package com.slashmobility.bottleflip_android.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.slashmobility.bottleflip_android.Constants;
import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.fragments.ChallengeMainFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChallengeDetailActivity extends BaseActivity implements YouTubePlayer.OnInitializedListener {

   // @BindView(R.id.toolbar)Toolbar mToolbar;
    //@BindView(R.id.toolbarTitle)TextView mtoolbarTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_detail);
        ButterKnife.bind(this);
       // changeToFragment(new ChallengeMainFragment());
        YouTubePlayerFragment youTubePlayerFragment =
                (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtube_fragment);

        youTubePlayerFragment.initialize(Constants.GOOGLE_API_KEY, this);

    }

 /*   private void configViews(){
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mtoolbarTitle.setText(getString(R.string.challenge));
    }
*/
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            youTubePlayer.cueVideo("p80Jo3YiCSE");
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
       getFragmentManager().findFragmentById(R.id.youtube_fragment);
    }
}
