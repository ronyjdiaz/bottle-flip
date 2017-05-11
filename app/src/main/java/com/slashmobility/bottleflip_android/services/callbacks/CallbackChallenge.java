package com.slashmobility.bottleflip_android.services.callbacks;

import com.slashmobility.bottleflip_android.model.Challenge;

import java.util.ArrayList;

/**
 * Created by Rony on 11/5/2017.
 */

public interface CallbackChallenge extends CallbackBase{
    void onSuccess(ArrayList<Challenge>challenges);
}
