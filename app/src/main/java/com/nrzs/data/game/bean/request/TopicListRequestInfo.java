package com.nrzs.data.game.bean.request;

import com.nrzs.data.base.BaseRequest;

/* loaded from: classes2.dex */
public class TopicListRequestInfo extends BaseRequest {
    private long AuthorId;
    private int CurrentPage;
    private int PageSize;
    private int TopicID;
    private int UserID;

    public long getAuthorId() {
        return this.AuthorId;
    }

    public void setAuthorId(long j) {
        this.AuthorId = j;
    }

    public int getTopicID() {
        return this.TopicID;
    }

    public void setTopicID(int i) {
        this.TopicID = i;
    }

    public int getCurrentPage() {
        return this.CurrentPage;
    }

    public void setCurrentPage(int i) {
        this.CurrentPage = i;
    }

    public int getUserID() {
        return this.UserID;
    }

    public void setUserID(int i) {
        this.UserID = i;
    }

    public int getPageSize() {
        return this.PageSize;
    }

    public void setPageSize(int i) {
        this.PageSize = i;
    }
}
