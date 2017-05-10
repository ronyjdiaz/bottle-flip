package com.slashmobility.bottleflip_android.model;

/**
 * Created by Rony on 10/5/2017.
 */

public class User {
    String accessToken;
    String bottleCode;
    String created;
    String displayName;
    String email;
    String fcmDeviceToken;
    String providerID;
    String userID;
    String userName;

    public User() {
    }

    public User(String accessToken, String bottleCode, String created, String displayName, String email, String fcmDeviceToken, String providerID, String userID, String userName) {
        this.accessToken = accessToken;
        this.bottleCode = bottleCode;
        this.created = created;
        this.displayName = displayName;
        this.email = email;
        this.fcmDeviceToken = fcmDeviceToken;
        this.providerID = providerID;
        this.userID = userID;
        this.userName = userName;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getBottleCode() {
        return bottleCode;
    }

    public void setBottleCode(String bottleCode) {
        this.bottleCode = bottleCode;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFcmDeviceToken() {
        return fcmDeviceToken;
    }

    public void setFcmDeviceToken(String fcmDeviceToken) {
        this.fcmDeviceToken = fcmDeviceToken;
    }

    public String getProviderID() {
        return providerID;
    }

    public void setProviderID(String providerID) {
        this.providerID = providerID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
