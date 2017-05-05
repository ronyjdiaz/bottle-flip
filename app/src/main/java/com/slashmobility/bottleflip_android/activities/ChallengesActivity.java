package com.slashmobility.bottleflip_android.activities;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.fragments.ChallengesFragment;
import com.slashmobility.bottleflip_android.fragments.ProfileFragment;
import com.slashmobility.bottleflip_android.fragments.RankingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChallengesActivity extends BaseActivity {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.btn_reto) ImageButton btn_reto;
    @BindView(R.id.btn_ranking) ImageButton btn_ranking;
    @BindView(R.id.btn_perfil) ImageButton btn_perfil;
    @BindView(R.id.content_view) RelativeLayout content_view;
    final static String TAG_1 = "RETO";
    final static String TAG_2 = "RANKING";
    final static String TAG_3 = "PERFIL";
    private DatabaseReference database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reto);
        ButterKnife.bind(this);
        database = FirebaseDatabase.getInstance().getReference();
        getViews();
    }

    private void getViews(){
        mToolbar.setTitle(getResources().getString(R.string.title_challenge));
        setSupportActionBar(mToolbar);
        changeToFragment( new ChallengesFragment() );
        changeColorBarNotification(R.color.green_start_challenges);
    }

    @OnClick(R.id.btn_reto)
    public void gotoReto(){
        mToolbar.setTitle(getResources().getString(R.string.title_challenge));
        active_btn(TAG_1);
        changeToFragment( new ChallengesFragment() );
    }

    @OnClick(R.id.btn_ranking)
    public void gotoRanking(){
        mToolbar.setTitle(getResources().getString(R.string.title_ranking));
        active_btn(TAG_2);
        changeToFragment( new RankingFragment() );
    }

    @OnClick(R.id.btn_perfil)
    public void gotoPerfil(){
        mToolbar.setTitle(getResources().getString(R.string.title_profile));
        active_btn(TAG_3);
        changeToFragment( new ProfileFragment() );

    }

    private void active_btn(String tag){
        switch (tag){
            case TAG_1:
                content_view.setBackground(ContextCompat.getDrawable(this, R.drawable.gradient_green));
                changeColorBarNotification(R.color.green_start_challenges);
                btn_reto.setImageResource(R.drawable.ic_retos_active);
                btn_ranking.setImageResource(R.drawable.ic_ranking_default);
                btn_perfil.setImageResource(R.drawable.ic_otros_default);
                break;
            case TAG_2:
                content_view.setBackground(ContextCompat.getDrawable(this, R.drawable.gradient_red));
                changeColorBarNotification(R.color.red_start_ranking);
                btn_ranking.setImageResource(R.drawable.ic_ranking_active);
                btn_reto.setImageResource(R.drawable.ic_retos_default);
                btn_perfil.setImageResource(R.drawable.ic_otros_default);
                break;
            case TAG_3:
                content_view.setBackground(ContextCompat.getDrawable(this, R.drawable.gradient_yellow));
                changeColorBarNotification(R.color.yellow_start_ranking);
                btn_reto.setImageResource(R.drawable.ic_retos_default);
                btn_ranking.setImageResource(R.drawable.ic_ranking_default);
                btn_perfil.setImageResource(R.drawable.ic_otros_active);
                break;

        }
    }

    public DatabaseReference getDatabase() {
        return database;
    }
}
