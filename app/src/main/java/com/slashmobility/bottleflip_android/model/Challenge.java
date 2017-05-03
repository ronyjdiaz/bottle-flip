package com.slashmobility.bottleflip_android.model;

import java.util.List;

/**
 * Created by rony_2 on 2/5/2017.
 */

public class Challenge {

    List<TryUser> attemptsByUsers;
    List<Rule> points;

    private String created;

    public String getCreated() { return this.created; }

    public void setCreated(String created) { this.created = created; }

    private String description;

    public String getDescription() { return this.description; }

    public void setDescription(String description) { this.description = description; }

    private int level;

    public int getLevel() { return this.level; }

    public void setLevel(int level) { this.level = level; }

    private String name;

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    private int score;

    public int getScore() { return this.score; }

    public void setScore(int score) { this.score = score; }

    private String videoURLDownload;

    public String getVideoURLDownload() { return this.videoURLDownload; }

    public void setVideoURLDownload(String videoURLDownload) { this.videoURLDownload = videoURLDownload; }

    private String videoURLStorage;

    public String getVideoURLStorage() { return this.videoURLStorage; }

    public void setVideoURLStorage(String videoURLStorage) { this.videoURLStorage = videoURLStorage; }


    public Challenge() {
    }

    public List<TryUser> getAttemptsByUsers() {
        return attemptsByUsers;
    }

    public void setAttemptsByUsers(List<TryUser> attemptsByUsers) {
        this.attemptsByUsers = attemptsByUsers;
    }

    public List<Rule> getPoints() {
        return points;
    }

    public void setPoints(List<Rule> points) {
        this.points = points;
    }

    public Challenge(List<TryUser> attemptsByUsers, List<Rule> points, String created, String description, int level, String name, int score, String videoURLDownload, String videoURLStorage) {
        this.attemptsByUsers = attemptsByUsers;
        this.points = points;
        this.created = created;
        this.description = description;
        this.level = level;
        this.name = name;
        this.score = score;
        this.videoURLDownload = videoURLDownload;
        this.videoURLStorage = videoURLStorage;
    }
}
