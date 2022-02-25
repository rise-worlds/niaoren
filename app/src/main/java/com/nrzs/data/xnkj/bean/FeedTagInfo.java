package com.nrzs.data.xnkj.bean;

import java.util.List;

/* loaded from: classes2.dex */
public class FeedTagInfo {
    private String feedbackQQGroupNum;
    private List<String> feedbackTags;
    private AppUpdateInfo fullAppUpdateResult;

    public AppUpdateInfo getFullAppUpdateResult() {
        return this.fullAppUpdateResult;
    }

    public void setFullAppUpdateResult(AppUpdateInfo appUpdateInfo) {
        this.fullAppUpdateResult = appUpdateInfo;
    }

    public String getFeedbackQQGroupNum() {
        return this.feedbackQQGroupNum;
    }

    public void setFeedbackQQGroupNum(String str) {
        this.feedbackQQGroupNum = str;
    }

    public List<String> getFeedbackTags() {
        return this.feedbackTags;
    }

    public void setFeedbackTags(List<String> list) {
        this.feedbackTags = list;
    }
}
