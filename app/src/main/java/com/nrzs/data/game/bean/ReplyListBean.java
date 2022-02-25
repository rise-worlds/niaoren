package com.nrzs.data.game.bean;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class ReplyListBean implements Serializable, Cloneable {
    private String content;
    private String uname;

    public String getUname() {
        return this.uname;
    }

    public void setUname(String str) {
        this.uname = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
