package com.slashmobility.bottleflip_android.model;

/**
 * Created by rony_2 on 2/5/2017.
 */

public class Rule {
    String title;
    int value;


    public Rule() {
    }

    public Rule(String title, int value) {
        this.title = title;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
