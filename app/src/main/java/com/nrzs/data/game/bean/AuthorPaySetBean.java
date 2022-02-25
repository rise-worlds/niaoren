package com.nrzs.data.game.bean;

/* loaded from: classes2.dex */
public class AuthorPaySetBean {
    public long AuthorId;
    public boolean Enabled;

    /* renamed from: Id */
    public long f10642Id;
    public String Remark;
    public String Url;

    public long getId() {
        return this.f10642Id;
    }

    public void setId(long j) {
        this.f10642Id = j;
    }

    public long getAuthorId() {
        return this.AuthorId;
    }

    public void setAuthorId(long j) {
        this.AuthorId = j;
    }

    public String getUrl() {
        return this.Url;
    }

    public void setUrl(String str) {
        this.Url = str;
    }

    public boolean isEnabled() {
        return this.Enabled;
    }

    public void setEnabled(boolean z) {
        this.Enabled = z;
    }

    public String getRemark() {
        return this.Remark;
    }

    public void setRemark(String str) {
        this.Remark = str;
    }
}
