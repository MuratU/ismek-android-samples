package com.dnkilic.specialrecycleview;

import java.util.List;

public class AssistantDialog {

    private int mDialogType;
    private String mDialogText;
    private int mResourceId;
    private String mFirstDialogText;
    private String mSecondDialogText;
    private String mResourceUrl;
    private List<Long> mTweetIds;

    public AssistantDialog(String dialogText, int type) {
        this.mDialogType = type;
        this.mDialogText = dialogText;
    }

    public AssistantDialog(int type) {
        this.mDialogType = type;
    }

    public AssistantDialog(int resourceId, int type) {
        this.mDialogType = type;
        this.mResourceId = resourceId;
    }

    public AssistantDialog(List<Long> tweetIds, int type) {
        this.mDialogType = type;
        this.mTweetIds = tweetIds;
    }

    public AssistantDialog(String resourceUrl, String firstDialogText, String secondDialogText, int type) {
        this.mDialogType = type;
        this.mResourceUrl = resourceUrl;
        this.mSecondDialogText = secondDialogText;
        this.mFirstDialogText = firstDialogText;
    }

    public String getResourceUrl() {
        return mResourceUrl;
    }

    public int getDialogType() {
        return mDialogType;
    }

    public String getDialogText() {
        return mDialogText;
    }

    public String getFirstDialogText() {
        return mFirstDialogText;
    }

    public String getSecondDialogText() {
        return mSecondDialogText;
    }

    public int getResourceId() {
        return mResourceId;
    }

    public List<Long> getTweetIds() {
        return mTweetIds;
    }
}
