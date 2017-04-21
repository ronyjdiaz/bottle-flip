package com.slashmobility.bottleflip_android.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.activities.ChallengesActivity;
import com.slashmobility.bottleflip_android.activities.LinkBottleActivity;
import com.slashmobility.bottleflip_android.activities.LoginActivity;
import com.slashmobility.bottleflip_android.activities.OnboardingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Edgar-W10 on 8/4/2017.
 */

public class PerfilFragment extends BaseFragment {

    @BindView(R.id.layoutLogout) LinearLayout layoutLogout;
    @BindView(R.id.layoutTermsAndConditions) LinearLayout layoutTermsAndConditions;
    @BindView(R.id.layoutHelp) LinearLayout layoutHelp;
    @BindView(R.id.layoutShare) LinearLayout layoutShare;
    @BindView(R.id.layoutPrivacyPolicy) LinearLayout layoutPrivacyPolicy;
    @BindView(R.id.layoutSetBottleCode) LinearLayout layoutSetBottleCode;


    private View mView;

    public PerfilFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_perfil, container, false);
        ButterKnife.bind(this, mView);
        getViews();
        return mView;
    }


    private void getViews(){
    }

    @OnClick(R.id.layoutLogout)
    protected void logout(){
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @OnClick(R.id.layoutHelp)
    protected void gotoHelp(){
        Bundle bundle = new Bundle();
        bundle.putBoolean("isHelp", true);
        ((ChallengesActivity)getActivity()).openActivity(OnboardingActivity.class, bundle);
    }

    @OnClick(R.id.layoutShare)
    protected void shareApp(){
        try {
            Intent i = new Intent(android.content.Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
            String sAux = "\nLet me recommend you this application\n\n";
            sAux = sAux + "https://play.google.com/store/apps/details?id=com.flaregames.nskchuck \n\n";
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            startActivity(Intent.createChooser(i, "choose one"));
        } catch(Exception e) {
            //e.toString();
        }
    }

    @OnClick(R.id.layoutSetBottleCode)
    protected void linkCode(){
        ((ChallengesActivity)getActivity()).openActivity(LinkBottleActivity.class);
    }
}
