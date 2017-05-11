package com.slashmobility.bottleflip_android.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.slashmobility.bottleflip_android.Constants;
import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.activities.ChallengesActivity;
import com.slashmobility.bottleflip_android.activities.LinkBottleActivity;
import com.slashmobility.bottleflip_android.activities.OnboardingActivity;
import com.slashmobility.bottleflip_android.activities.WelcomeActivity;
import com.slashmobility.bottleflip_android.singleton.SingletonSession;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Edgar-W10 on 8/4/2017.
 */

public class ProfileFragment extends BaseFragment {

    @BindView(R.id.layoutLogout)
    LinearLayout layoutLogout;
    @BindView(R.id.layoutTermsAndConditions)
    LinearLayout layoutTermsAndConditions;
    @BindView(R.id.layoutHelp)
    LinearLayout layoutHelp;
    @BindView(R.id.layoutShare)
    LinearLayout layoutShare;
    @BindView(R.id.layoutPrivacyPolicy)
    LinearLayout layoutPrivacyPolicy;
    @BindView(R.id.layoutSetBottleCode)
    LinearLayout layoutSetBottleCode;


    private View mView;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_perfil, container, false);
        ButterKnife.bind(this, mView);
        configViews();
        return mView;
    }


    private void configViews() {
        if (!SingletonSession.getInstance().getBottleCode().equals("")) {
            layoutSetBottleCode.setEnabled(false);
            layoutSetBottleCode.setClickable(false);
            layoutSetBottleCode.getBackground().setAlpha(Constants.ALPHA50);
        }

    }

    @OnClick(R.id.layoutLogout)
    protected void logout() {
        Intent intent = new Intent(getActivity(), WelcomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @OnClick(R.id.layoutHelp)
    protected void gotoHelp() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constants.ISHELP, true);
        ((ChallengesActivity) getActivity()).openActivity(OnboardingActivity.class, bundle);
    }

    @OnClick(R.id.layoutShare)
    protected void shareApp() {
        try {
            Intent i = new Intent(android.content.Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
            String sAux = getString(R.string.share_recomendation);
            sAux = sAux + "\n" + Constants.LINK_APP;
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            startActivity(Intent.createChooser(i, getString(R.string.choose_one)));
        } catch (Exception e) {
            //e.toString();
        }
    }

    @OnClick(R.id.layoutSetBottleCode)
    protected void linkCode() {
        if (SingletonSession.getInstance().getBottleCode().equals("")) {
            ((ChallengesActivity) getActivity()).openActivity(LinkBottleActivity.class);
        }


    }
}
