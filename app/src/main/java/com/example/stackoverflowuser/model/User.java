package com.example.stackoverflowuser.model;

public class User {
    private String name;
    private String avatarUrl;
    private long reputation;

    public User(String name, String avatarUrl, long reputation) {
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.reputation = reputation;
    }

    public String getName() {
        return name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public long getReputation() {
        return reputation;
    }
}
