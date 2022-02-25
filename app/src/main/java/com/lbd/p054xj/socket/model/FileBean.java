package com.lbd.p054xj.socket.model;

/* renamed from: com.lbd.xj.socket.model.FileBean */
/* loaded from: classes.dex */
public class FileBean {
    private int childCount;
    private String drawable;
    private FileType fileType;
    private int holderType;
    private int imageId;
    private int isSelect;
    private String name;
    private String packageName;
    private String path;
    private long size;
    private int versionCode;
    private String versionName;
    private String vmosPath;

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public String getVersionName() {
        return this.versionName;
    }

    public void setVersionName(String str) {
        this.versionName = str;
    }

    public int getVersionCode() {
        return this.versionCode;
    }

    public void setVersionCode(int i) {
        this.versionCode = i;
    }

    public int getChildCount() {
        return this.childCount;
    }

    public String getDrawable() {
        return this.drawable;
    }

    public FileType getFileType() {
        return this.fileType;
    }

    public int getHolderType() {
        return this.holderType;
    }

    public int getImageId() {
        return this.imageId;
    }

    public int getIsSelect() {
        return this.isSelect;
    }

    public String getName() {
        return this.name;
    }

    public String getPath() {
        return this.path;
    }

    public long getSize() {
        return this.size;
    }

    public String getVmosPath() {
        return this.vmosPath;
    }

    public void setChildCount(int i) {
        this.childCount = i;
    }

    public void setDrawable(String str) {
        this.drawable = str;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public void setHolderType(int i) {
        this.holderType = i;
    }

    public void setImageId(int i) {
        this.imageId = i;
    }

    public void setIsSelect(int i) {
        this.isSelect = i;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public void setSize(long j) {
        this.size = j;
    }

    public void setVmosPath(String str) {
        this.vmosPath = str;
    }

    public String toString() {
        return "FileBean{drawable='', isSelect=" + this.isSelect + ", imageId=" + this.imageId + ", name='" + this.name + "', path='" + this.path + "', fileType=" + this.fileType + ", childCount=" + this.childCount + ", size=" + this.size + ", holderType=" + this.holderType + ", vmosPath='" + this.vmosPath + "'}";
    }
}
