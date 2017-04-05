package com.slashmobility.bottleflip_android;

import android.app.Application;

/**

 * @author Rony Diaz
 * @version %I, %G
 * @since 1.0
 */

public class BottleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
       // Stetho.initializeWithDefaults(this);
    }

}