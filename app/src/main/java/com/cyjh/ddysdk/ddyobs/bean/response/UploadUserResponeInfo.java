package com.cyjh.ddysdk.ddyobs.bean.response;

import java.util.List;

/* loaded from: classes.dex */
public class UploadUserResponeInfo {
    private boolean Authorized;
    private List<AppBlack> BlackList;
    private float PostFileSize;
    private int PostFiles;
    private String PostFolderName;
    private float PostPerFileSize;

    public boolean isAuthorized() {
        return this.Authorized;
    }

    public void setAuthorized(boolean z) {
        this.Authorized = z;
    }

    public int getPostFiles() {
        return this.PostFiles;
    }

    public void setPostFiles(int i) {
        this.PostFiles = i;
    }

    public float getPostFileSize() {
        return this.PostFileSize;
    }

    public void setPostFileSize(float f) {
        this.PostFileSize = f;
    }

    public String getPostFolderName() {
        return this.PostFolderName;
    }

    public void setPostFolderName(String str) {
        this.PostFolderName = str;
    }

    public float getPostPerFileSize() {
        return this.PostPerFileSize;
    }

    public void setPostPerFileSize(float f) {
        this.PostPerFileSize = f;
    }

    public List<AppBlack> getBlackList() {
        return this.BlackList;
    }

    public void setBlackList(List<AppBlack> list) {
        this.BlackList = list;
    }
}
