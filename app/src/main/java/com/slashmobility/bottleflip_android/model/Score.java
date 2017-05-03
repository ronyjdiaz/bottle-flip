package com.slashmobility.bottleflip_android.model;

/**
 * Created by rony_2 on 2/5/2017.
 */

public class Score {
    String displayName;
    String email;
    int score;
    String userId;

    public Score() {
    }

    public Score(String displayName, String email, int score, String userId) {
        this.displayName = displayName;
        this.email = email;
        this.score = score;
        this.userId = userId;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
