package com.lbd.xj.socket.model;

/* renamed from: com.lbd.xj.socket.model.ImprotBean */
/* loaded from: classes.dex */
public class ImprotBean {
    private int childCount;
    private String fileState;
    private FileType fileType;
    private int holderType;
    private int isSelect = 0;
    private String name;
    private String path;
    private String picture;
    private long size;
    private String vmosPath;

    public String getVmosPath() {
        return this.vmosPath;
    }

    public void setVmosPath(String str) {
        this.vmosPath = str;
    }

    public int getIsSelect() {
        return this.isSelect;
    }

    public void setIsSelect(int i) {
        this.isSelect = i;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String str) {
        this.picture = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public FileType getFileType() {
        return this.fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public int getChildCount() {
        return this.childCount;
    }

    public void setChildCount(int i) {
        this.childCount = i;
    }

    public long getSize() {
        return this.size;
    }

    public void setSize(long j) {
        this.size = j;
    }

    public int getHolderType() {
        return this.holderType;
    }

    public void setHolderType(int i) {
        this.holderType = i;
    }

    public String getFileState() {
        return this.fileState;
    }

    public void setFileState(String str) {
        this.fileState = str;
    }
}
