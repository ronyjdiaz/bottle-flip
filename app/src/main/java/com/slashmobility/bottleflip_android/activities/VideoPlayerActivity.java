package com.slashmobility.bottleflip_android.activities;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.VideoView;

import com.afollestad.easyvideoplayer.EasyVideoCallback;
import com.afollestad.easyvideoplayer.EasyVideoPlayer;
import com.slashmobility.bottleflip_android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoPlayerActivity extends Activity implements EasyVideoCallback {

   private  EasyVideoPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        Bundle bundle = getIntent().getExtras();
        if(getIntent().hasExtra("VideoUri"))
        {
            Toast.makeText(this,bundle.getString("VideoUri"),Toast.LENGTH_LONG).show();
            player = (EasyVideoPlayer)findViewById(R.id.player);
            player.setCallback(this);

            // Sets the source to the HTTP URL held in the TEST_URL variable.
            // To play files, you can use Uri.fromFile(new File("..."))
            player.setSource(Uri.parse(bundle.getString("VideoUri")));
        }


    }

    @Override
    public void onStarted(EasyVideoPlayer player) {

    }

    @Override
    public void onPaused(EasyVideoPlayer player) {

    }

    @Override
    public void onPreparing(EasyVideoPlayer player) {

    }

    @Override
    public void onPrepared(EasyVideoPlayer player) {

    }

    @Override
    public void onBuffering(int percent) {

    }

    @Override
    public void onError(EasyVideoPlayer player, Exception e) {

    }

    @Override
    public void onCompletion(EasyVideoPlayer player) {

    }

    @Override
    public void onRetry(EasyVideoPlayer player, Uri source) {

    }

    @Override
    public void onSubmit(EasyVideoPlayer player, Uri source) {

    }
}
