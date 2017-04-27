package com.slashmobility.bottleflip_android;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**

 * @author Rony Diaz
 * @version %I, %G
 * @since 1.0
 */

public class BottleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
       // Stetho.initializeWithDefaults(this);
    }

}