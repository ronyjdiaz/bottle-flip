package com.slashmobility.bottleflip_android.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.slashmobility.bottleflip_android.Constants;


/**
 * Created by rony_2 on 26/1/2017.
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";


    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        SharedPreferences sharedpreferences;
        sharedpreferences = getSharedPreferences(Constants.MYPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Constants.TOKENFIREBASE,refreshedToken);
        editor.commit();



    }


}
