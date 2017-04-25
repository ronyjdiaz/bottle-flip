package com.slashmobility.bottleflip_android.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.slashmobility.bottleflip_android.Constants;
import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.singleton.SingletonSession;
import com.slashmobility.bottleflip_android.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChallengeDetailActivity extends BaseActivity implements YouTubePlayer.OnInitializedListener {

    @BindView(R.id.toolbar)Toolbar mToolbar;
    @BindView(R.id.toolbarTitle)TextView mtoolbarTitle;
    @BindView(R.id.btnAccept)Button mbtnAccept;
    @BindView(R.id.textviewChallengeName)TextView mtextviewChallengeName;
    @BindView(R.id.textviewInstructionsChallenge)TextView mtextviewInstructionsChallenge;
    private static final int VIDEO_CAPTURE = 101;
    private Uri mVideoUri;
    private Uri picUri;
    private String mCurrentVideoPath;
    private AlbumStorageDirFactory mAlbumStorageDirFactory = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_detail);
        ButterKnife.bind(this);
       // changeToFragment(new ChallengeMainFragment());
        YouTubePlayerFragment youTubePlayerFragment =
                (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtube_fragment);

        youTubePlayerFragment.initialize(Constants.GOOGLE_API_KEY, this);
        configViews();
        configPermissions();
        mAlbumStorageDirFactory = new BaseAlbumDirFactory();

    }

    private void configPermissions(){

        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                ).withListener(new MultiplePermissionsListener() {
            @Override public void onPermissionsChecked(MultiplePermissionsReport report) {/* ... */}
            @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {/* ... */}
        }).check();

    }

    private void configViews(){
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mtoolbarTitle.setText("Reto 6");//Challenge name
        mToolbar.setBackgroundColor(getResources().getColor(R.color.light_green_challenges));
        changeColorBarNotification(R.color.light_green_challenges);
        Utils.changeColorDrawable(mbtnAccept, this, R.color.light_green_challenges);
    }

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

    @OnClick(R.id.btnAccept)
    protected void doChallenge() {
        if(SingletonSession.getInstance().getBottleCode().equals(""))
        {
            if(hasCamera()){
                //File mediaFile = createVideoFile();
                //
                File mediaFile =
                        new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                                + "/myvideo.mp4");
                mCurrentVideoPath = mediaFile.getAbsolutePath();
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                Uri videoUri = Uri.fromFile(mediaFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
                startActivityForResult(intent, VIDEO_CAPTURE);

            }
            else{
                showMessageDialog("Mensaje temporal: No tiene cámara");
            }

        }
        else
        {
            showMessageDialog("Mensaje temporal: No tiene código");
        }
    }
    private boolean hasCamera() {
        if (getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA_ANY)){
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VIDEO_CAPTURE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Video saved to:\n" +
                        data.getData(), Toast.LENGTH_LONG).show();
                Bundle bundle = new Bundle();
                bundle.putString("VideoUri",mCurrentVideoPath);
                openActivity(VideoPlayerActivity.class,bundle);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Video recording cancelled.",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Failed to record video",
                        Toast.LENGTH_LONG).show();
            }
        }

    }

    private String getAlbumName() {
        return getString(R.string.app_name);
    }

    private File getAlbumDir() {
        File storageDir = null;

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {

            storageDir = mAlbumStorageDirFactory.getAlbumStorageDir(getAlbumName());

            if (storageDir != null) {
                if (! storageDir.mkdirs()) {
                    if (! storageDir.exists()){
                        Log.d("CameraSample", "failed to create directory");
                        return null;
                    }
                }
            }

        } else {
            Log.v(getString(R.string.app_name), "External storage is not mounted READ/WRITE.");
        }

        return storageDir;
    }
    private File createVideoFile()  {
        File albumF = getAlbumDir();
        File imageF = null;
        try {
            imageF = File.createTempFile(Constants.VIDEO_NAME, Constants.VIDEO_EXTENSION, albumF);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageF;
    }

}
