package com.slashmobility.bottleflip_android.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;


import com.google.firebase.messaging.RemoteMessage;
import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.activities.SplashActivity;

import br.com.goncalves.pugnotification.notification.PugNotification;

/**
 * Created by rony_2 on 26/1/2017.
 */

public class FirebaseMessagingServiceClass extends com.google.firebase.messaging.FirebaseMessagingService {


    private static final String TAG = "MyFirebaseMsgService";


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            sendNotification(remoteMessage.getNotification().getBody());
        }
    }

    private void sendNotification(String messageBody) {

        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        PugNotification.with(this)
                .load()
                .autoCancel(true)
                .title("Bottle Flip Message")
                .message(messageBody)
                .smallIcon(R.drawable.ic_launcher_xxxhdpi)
                .largeIcon(R.drawable.ic_launcher_xxxhdpi)
                .flags(Notification.DEFAULT_ALL)
                .simple()
                .build();

    }
}
