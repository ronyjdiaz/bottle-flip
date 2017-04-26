package com.slashmobility.bottleflip_android.activities;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;



import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.utils.Utils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import net.protyposis.android.mediaplayer.MediaPlayer;
import net.protyposis.android.mediaplayer.MediaSource;
import net.protyposis.android.mediaplayer.VideoView;

public class VideoPlayerActivity extends BaseActivity  {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.toolbarTitle)TextView mtoolbarTitle;
    @BindView(R.id.vv) net.protyposis.android.mediaplayer.VideoView mVideoView;

    private String uriVideo;

    private MediaController.MediaPlayerControl mMediaPlayerControl;
    private MediaController mMediaController;
    private Uri mVideoUri;
    private int mVideoPosition;
    private float mVideoPlaybackSpeed;
    private boolean mVideoPlaying;
    private MediaSource mMediaSource;
    private static final String TAG = VideoPlayerActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();

        mMediaPlayerControl = mVideoView; //new MediaPlayerDummyControl();
        mMediaController = new MediaController(this);
        mMediaController.setAnchorView(findViewById(R.id.container));
        mMediaController.setMediaPlayer(mMediaPlayerControl);
        mMediaController.setEnabled(false);
        mVideoPosition = 0;
        mVideoPlaybackSpeed = 1;
        mVideoPlaying = false;
        //mVideoUri = getIntent().getData();
        if(getIntent().hasExtra("VideoUri"))
        {
            mVideoUri = Uri.parse( getIntent().getExtras().getString("VideoUri"));

        }

        configViews();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_upload, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_apply:
                Toast.makeText(this, "Click a aplicar, funcionalidad no terminada", Toast.LENGTH_SHORT).show();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void configViews(){
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mtoolbarTitle.setText("Reto 6");//Challenge name
        mToolbar.setBackgroundColor(getResources().getColor(R.color.light_green_challenges));
        changeColorBarNotification(R.color.light_green_challenges);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mVideoUri = savedInstanceState.getParcelable("uri");
        mVideoPosition = savedInstanceState.getInt("position");
        mVideoPlaybackSpeed = savedInstanceState.getFloat("playbackSpeed");
        mVideoPlaying = savedInstanceState.getBoolean("playing");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!mVideoView.isPlaying()) {
            initPlayer();
        }
    }

    private void initPlayer() {


        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer vp) {
                //mProgress.setVisibility(View.GONE);
                mMediaController.setEnabled(true);
            }
        });
        mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Toast.makeText(VideoPlayerActivity.this,
                        "Cannot play the video, see logcat for the detailed exception",
                        Toast.LENGTH_LONG).show();
                //mProgress.setVisibility(View.GONE);
                mMediaController.setEnabled(false);
                return true;
            }
        });
        mVideoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                String whatName = "";
                switch (what) {
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        whatName = "MEDIA_INFO_BUFFERING_END";
                        break;
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        whatName = "MEDIA_INFO_BUFFERING_START";
                        break;
                    case MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
                        whatName = "MEDIA_INFO_VIDEO_RENDERING_START";
                        break;
                    case MediaPlayer.MEDIA_INFO_VIDEO_TRACK_LAGGING:
                        whatName = "MEDIA_INFO_VIDEO_TRACK_LAGGING";
                        break;
                }
                Log.d(TAG, "onInfo " + whatName);
                return false;
            }
        });
        mVideoView.setOnSeekListener(new MediaPlayer.OnSeekListener() {
            @Override
            public void onSeek(MediaPlayer mp) {
                Log.d(TAG, "onSeek");
               // mProgress.setVisibility(View.VISIBLE);
            }
        });
        mVideoView.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
            @Override
            public void onSeekComplete(MediaPlayer mp) {
                Log.d(TAG, "onSeekComplete");
                //mProgress.setVisibility(View.GONE);
            }
        });
        mVideoView.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                Log.d(TAG, "onBufferingUpdate " + percent + "%");
            }
        });

        Utils.MediaSourceAsyncCallbackHandler mMediaSourceAsyncCallbackHandler =
                new Utils.MediaSourceAsyncCallbackHandler() {
                    @Override
                    public void onMediaSourceLoaded(MediaSource mediaSource) {
                        mMediaSource = mediaSource;
                        mVideoView.setVideoSource(mediaSource);
                        mVideoView.seekTo(mVideoPosition);
                        mVideoView.setPlaybackSpeed(mVideoPlaybackSpeed);
                        if (mVideoPlaying) {
                            mVideoView.start();
                        }
                    }

                    @Override
                    public void onException(Exception e) {
                        Log.e(TAG, "error loading video", e);
                    }
                };
        if(mMediaSource == null) {
            // Convert uri to media source asynchronously to avoid UI blocking
            // It could take a while, e.g. if it's a DASH source and needs to be preprocessed
            Utils.uriToMediaSourceAsync(this, mVideoUri, mMediaSourceAsyncCallbackHandler);
        } else {
            // Media source is already here, just use it
            mMediaSourceAsyncCallbackHandler.onMediaSourceLoaded(mMediaSource);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (mMediaController.isShowing()) {
                mMediaController.hide();
            } else {
                mMediaController.show();
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onStop() {
        mMediaController.hide();
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mVideoView != null) {
            mVideoPosition = mVideoView.getCurrentPosition();
            mVideoPlaybackSpeed = mVideoView.getPlaybackSpeed();
            mVideoPlaying = mVideoView.isPlaying();
            // the uri is stored in the base activity
            outState.putParcelable("uri", mVideoUri);
            outState.putInt("position", mVideoPosition);
            outState.putFloat("playbackSpeed", mVideoView.getPlaybackSpeed());
            outState.putBoolean("playing", mVideoPlaying);
        }
    }



}
