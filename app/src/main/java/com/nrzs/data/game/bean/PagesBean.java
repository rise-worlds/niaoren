package com.nrzs.data.game.bean;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class PagesBean implements Serializable {
    private int CurrentPage;
    private int DataCount;
    private int IsLastPage;
    private int PageCount;
    private int PageSize;

    public int getPageCount() {
        return this.PageCount;
    }

    public void setPageCount(int i) {
        this.PageCount = i;
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

    public int getDataCount() {
        return this.DataCount;
    }

    public void setDataCount(int i) {
        this.DataCount = i;
    }

    public int getIsLastPage() {
        return this.IsLastPage;
    }

    public void setIsLastPage(int i) {
        this.IsLastPage = i;
    }
}
