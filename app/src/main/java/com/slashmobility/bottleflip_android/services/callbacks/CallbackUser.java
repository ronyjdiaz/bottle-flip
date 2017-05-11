package com.slashmobility.bottleflip_android.services.callbacks;

import com.slashmobility.bottleflip_android.model.Challenge;
import com.slashmobility.bottleflip_android.model.User;

import java.util.ArrayList;

/**
 * Created by Rony on 11/5/2017.
 */

public interface CallbackUser extends CallbackBase{
    void onSuccess(User user);
}
