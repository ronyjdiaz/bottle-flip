package com.slashmobility.bottleflip_android.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.fragments.PerfilFragment;
import com.slashmobility.bottleflip_android.fragments.RankingFragment;
import com.slashmobility.bottleflip_android.fragments.RetoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RetoActivity extends BaseActivity {

    @BindView(R.id.toolbar) Toolbar default_toolbar;
    @BindView(R.id.btn_reto) ImageButton btn_reto;
    @BindView(R.id.btn_ranking) ImageButton btn_ranking;
    @BindView(R.id.btn_perfil) ImageButton btn_perfil;
    @BindView(R.id.content_view) RelativeLayout content_view;
    final static String TAG_1 = "RETO";
    final static String TAG_2 = "RANKING";
    final static String TAG_3 = "PERFIL";
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private RetoFragment fragment_reto;
    private RankingFragment fragment_ranking;
    private PerfilFragment fragment_perfil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reto);
        ButterKnife.bind(this);
        getViews();
    }

    private void getViews(){
        default_toolbar.setTitle(getResources().getString(R.string.title_reto));
        setSupportActionBar(default_toolbar);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragment_reto = new RetoFragment();
        fragmentTransaction.add(R.id.fragment_container, fragment_reto, TAG_1);
        fragmentTransaction.commit();
    }

    @OnClick(R.id.btn_reto)
    public void gotoReto(){
        default_toolbar.setTitle(getResources().getString(R.string.title_reto));
        RetoFragment fragment = (RetoFragment)fragmentManager.findFragmentByTag(TAG_1);
        if(fragment == null){
            active_btn(TAG_1);
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment_reto, TAG_1);
            fragmentTransaction.commit();
        }
    }

    @OnClick(R.id.btn_ranking)
    public void gotoRanking(){
        default_toolbar.setTitle(getResources().getString(R.string.title_ranking));
        if(fragment_ranking == null) fragment_ranking = new RankingFragment();
        RankingFragment fragment = (RankingFragment)fragmentManager.findFragmentByTag(TAG_2);
        if(fragment == null){
            active_btn(TAG_2);
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment_ranking, TAG_2);
            fragmentTransaction.commit();
        }
    }

    @OnClick(R.id.btn_perfil)
    public void gotoPerfil(){
        default_toolbar.setTitle(getResources().getString(R.string.title_perfil));
        if(fragment_perfil == null) fragment_perfil = new PerfilFragment();
        PerfilFragment fragment = (PerfilFragment)fragmentManager.findFragmentByTag(TAG_3);
        if(fragment == null){
            active_btn(TAG_3);
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment_perfil, TAG_3);
            fragmentTransaction.commit();
        }
    }

    private void active_btn(String tag){
        switch (tag){
            case TAG_1:
                content_view.setBackground(ContextCompat.getDrawable(this, R.drawable.gradient_green));
                btn_reto.setImageResource(R.drawable.ic_retos_active);
                btn_ranking.setImageResource(R.drawable.ic_ranking_default);
                btn_perfil.setImageResource(R.drawable.ic_otros_default);
                break;
            case TAG_2:
                content_view.setBackground(ContextCompat.getDrawable(this, R.drawable.gradient_red));
                btn_ranking.setImageResource(R.drawable.ic_ranking_active);
                btn_reto.setImageResource(R.drawable.ic_retos_default);
                btn_perfil.setImageResource(R.drawable.ic_otros_default);
                break;
            case TAG_3:
                content_view.setBackground(ContextCompat.getDrawable(this, R.drawable.gradient_yellow));
                btn_reto.setImageResource(R.drawable.ic_retos_default);
                btn_ranking.setImageResource(R.drawable.ic_ranking_default);
                btn_perfil.setImageResource(R.drawable.ic_otros_active);
                break;

        }
    }
}
