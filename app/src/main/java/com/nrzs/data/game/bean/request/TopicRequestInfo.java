package com.nrzs.data.game.bean.request;

import com.nrzs.data.base.BaseRequest;

/* loaded from: classes2.dex */
public class TopicRequestInfo extends BaseRequest {
    private long AuthorId;
    public long TopicId = 780;

    public long getAuthorId() {
        return this.AuthorId;
    }

    public void setAuthorId(long j) {
        this.AuthorId = j;
    }

    public void setmaxId(long j) {
        this.TopicId = j;
    }
}
