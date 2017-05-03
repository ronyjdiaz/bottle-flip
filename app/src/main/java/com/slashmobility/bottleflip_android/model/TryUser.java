package com.slashmobility.bottleflip_android.model;

/**
 * Created by rony_2 on 2/5/2017.
 */

public class TryUser {
    String userID;
    int value;

    public TryUser() {
    }

    public TryUser(String userID, int value) {
        this.userID = userID;
        this.value = value;
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
