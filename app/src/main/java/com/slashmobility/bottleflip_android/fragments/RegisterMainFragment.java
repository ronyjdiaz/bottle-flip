package com.slashmobility.bottleflip_android.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.activities.ChallengesActivity;
import com.slashmobility.bottleflip_android.activities.RegisterActivity;
import com.slashmobility.bottleflip_android.services.ServiceManager;
import com.slashmobility.bottleflip_android.services.callbacks.CallbackBoolean;
import com.slashmobility.bottleflip_android.singleton.SingletonSession;
import com.slashmobility.bottleflip_android.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterMainFragment extends BaseFragment {

    @BindView(R.id.btnContinue)
    Button mbtnContinue;
    @BindView(R.id.edittextBottleCode)
    EditText medittextBottleCode;
    @BindView(R.id.textviewContinue)
    TextView mtextviewContinue;


    public RegisterMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register_main, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ButterKnife.bind(this, v);

        Utils.changeColorDrawable(mbtnContinue, this.getContext(), R.color.white);
        return v;
    }

    @OnClick(R.id.textviewContinue)
    protected void continueNoCode() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.alert_conitnue_no_code)
                .setTitle(R.string.continue_without_code);

        builder.setPositiveButton(R.string.continue_without_code, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //changeToFragment(new RegisterOptionsFragment());
                ((RegisterActivity) getActivity()).openActivity(ChallengesActivity.class);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();


    }

    @OnClick(R.id.btnContinue)
    protected void continueCode() {
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
                        changeToFragment(new RegisterOptionsFragment());
                    } else {
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


}
