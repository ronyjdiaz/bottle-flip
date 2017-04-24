package com.slashmobility.bottleflip_android.singleton;

/**
 * Created by rony_2 on 16/12/2016.
 */

public class SingletonSession {

    private static SingletonSession instance;

    private String bottleCode;


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
        return bottleCode;
    }

    public void setBottleCode(String bottleCode) {
        this.bottleCode = bottleCode;
    }
}
