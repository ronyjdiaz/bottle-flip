package com.slashmobility.bottleflip_android.services.callbacks;

import android.service.notification.NotificationListenerService;

import com.slashmobility.bottleflip_android.model.Challenge;
import com.slashmobility.bottleflip_android.model.Score;

import java.util.ArrayList;

/**
 * Created by Rony on 11/5/2017.
 */

public interface CallbackRanking extends CallbackBase{
    void onSuccess(ArrayList<Score> scores);
}
