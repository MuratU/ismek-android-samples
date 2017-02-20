package com.dnkilic.dplayer;

public class Media {

    public static final int MUSIC = 0;
    public static final int VOICE_NOTE = 1;
    public static final int RADIO = 2;
    public static final int STORY_READER = 3;
    private String path;
    private String title;
    private int mediaType;

    public Media(String path, String title, int mediaType) {
        this.path = path;
        this.title = title;
        this.mediaType = mediaType;
    }

    public String getPath() {
        return path;
    }

    public String getTitle() {
        return title;
    }

    public int getMediaType() {
        return mediaType;
    }
}
