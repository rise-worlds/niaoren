package com.nrzs.data.game.bean.request;

import com.nrzs.data.base.BaseRequest;

/* loaded from: classes2.dex */
public class SearchRequestInfo extends BaseRequest {
    private long AuthorId;
    private int CurrentPage;
    private int PageSize;
    private String SearchKey;
    private long UserID;

    public long getAuthorId() {
        return this.AuthorId;
    }

    public void setAuthorId(long j) {
        this.AuthorId = j;
    }

    public String getSearchKey() {
        return this.SearchKey;
    }

    public void setSearchKey(String str) {
        this.SearchKey = str;
    }

    public int getCurrentPage() {
        return this.CurrentPage;
    }

    public void setCurrentPage(int i) {
        this.CurrentPage = i;
    }

    public int getPageSize() {
        return this.PageSize;
    }

    public void setPageSize(int i) {
        this.PageSize = i;
    }

    public long getUserID() {
        return this.UserID;
    }

    public void setUserID(long j) {
        this.UserID = j;
    }
}
