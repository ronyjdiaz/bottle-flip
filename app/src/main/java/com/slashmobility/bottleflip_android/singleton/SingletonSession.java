package com.slashmobility.bottleflip_android.singleton;

/**
 * Created by rony_2 on 16/12/2016.
 */

public class SingletonSession {

    private static SingletonSession instance;

    private String bottleCode;
    private String videoUri;


    //no outer class can initialize this class's object
    private SingletonSession() {}

    public static SingletonSession getInstance()
    {
        //if no instance is initialized yet then create new instance
        //else return stored instance
        if (instance == null)
        {
            instance = new SingletonSession();
        }
        return instance;
    }

    public String getBottleCode() {
        if(bottleCode!=null)
            return bottleCode;
        else
            return "";
    }

    public void setBottleCode(String bottleCode) {
        this.bottleCode = bottleCode;
    }

    public String getVideoUri() {
        return videoUri;
    }

    public void setVideoUri(String videoUri) {
        this.videoUri = videoUri;
    }
}
