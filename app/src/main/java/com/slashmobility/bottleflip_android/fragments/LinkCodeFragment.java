package com.slashmobility.bottleflip_android.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.activities.LinkBottleActivity;
import com.slashmobility.bottleflip_android.services.ServiceManager;
import com.slashmobility.bottleflip_android.services.callbacks.CallbackBoolean;
import com.slashmobility.bottleflip_android.singleton.SingletonSession;
import com.slashmobility.bottleflip_android.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class LinkCodeFragment extends BaseFragment {

    @BindView(R.id.btnContinue)
    Button mbtnContinue;
    @BindView(R.id.edittextBottleCode)
    EditText medittextBottleCode;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbarTitle)
    TextView mtoolbarTitle;
    @BindView(R.id.imageviewBackButton)
    ImageView mimageviewBackButton;

    public LinkCodeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_link_code, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        ButterKnife.bind(this, v);
        configViews();

        return v;
    }

    private void configViews() {
        Utils.changeColorDrawable(mbtnContinue, this.getContext(), R.color.color_bar_perfil);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        mtoolbarTitle.setText(getString(R.string.text_link));
        mToolbar.setBackgroundResource(R.color.color_bar_perfil);
        mimageviewBackButton.setVisibility(View.VISIBLE);
        ((LinkBottleActivity) getActivity()).changeColorBarNotification(R.color.color_bar_perfil);
    }

    @OnClick(R.id.btnContinue)
    protected void continueClick() {

        if (TextUtils.isEmpty(medittextBottleCode.getText().toString())) {
            showMessageDialog(getResources().getString(R.string.bottle_code_required));
            return;
        } else {
            showProgressDialog(false);

            ServiceManager.validateBottleCode(medittextBottleCode.getText().toString(), new CallbackBoolean() {
                @Override
                public void onSuccess(Boolean result) {
                    hideProgressDialog();
                    if (result) {
                        SingletonSession.getInstance().setBottleCode(medittextBottleCode.getText().toString());
                        if(SingletonSession.getInstance().getUser()!=null) {
                            SingletonSession.getInstance().getUser().setBottleCode(medittextBottleCode.getText().toString());
                            ServiceManager.updateUser(SingletonSession.getInstance().getUser(), new CallbackBoolean() {
                                @Override
                                public void onSuccess(Boolean result) {

                                }

                                @Override
                                public void onError(int errorCode, String errorMessage) {

                                }
                            });
                        }
                        else
                        {
                            changeToFragment(new LinkUserCodeFragment());
                        }

                    }
                    else {
                        showMessageDialog(getResources().getString(R.string.bottle_code_invalid));
                    }

                }

                @Override
                public void onError(int errorCode, String errorMessage) {
                    hideProgressDialog();
                }
            });


        }
    }

    @OnClick(R.id.imageviewBackButton)
    protected void back() {
        getActivity().finish();
    }


}
