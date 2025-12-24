package com.exemple.mediaserver.model;

public class VideoModel {
    private String id;
    private String title;
    private String description;
    private String url;
    private long durationSeconds;
    private CreatorModel creator;

    public VideoModel() {}

    // getters / setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public long getDurationSeconds() { return durationSeconds; }
    public void setDurationSeconds(long durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public CreatorModel getCreator() { return creator; }
    public void setCreator(CreatorModel creator) { this.creator = creator; }
}
