package com.nrzs.data.game.bean;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class TopicNewsFlashBean implements Serializable {
    private String ExecArgs;
    private String ExecCommand;
    private String ImgUrl;
    private String SubTitle;
    private String Title;

    public String getTitle() {
        return this.Title;
    }

    public void setTitle(String str) {
        this.Title = str;
    }

    public String getSubTitle() {
        return this.SubTitle;
    }

    public void setSubTitle(String str) {
        this.SubTitle = str;
    }

    public String getImgUrl() {
        return this.ImgUrl;
    }

    public void setImgUrl(String str) {
        this.ImgUrl = str;
    }

    public String getExecCommand() {
        return this.ExecCommand;
    }

    public void setExecCommand(String str) {
        this.ExecCommand = str;
    }

    public String getExecArgs() {
        return this.ExecArgs;
    }

    public void setExecArgs(String str) {
        this.ExecArgs = str;
    }
}
