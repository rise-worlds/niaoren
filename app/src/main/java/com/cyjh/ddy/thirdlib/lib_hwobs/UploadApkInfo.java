package com.cyjh.ddy.thirdlib.lib_hwobs;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class UploadApkInfo implements Parcelable {
    public static final Parcelable.Creator<UploadApkInfo> CREATOR = new Parcelable.Creator<UploadApkInfo>() { // from class: com.cyjh.ddy.thirdlib.lib_hwobs.UploadApkInfo.1
        /* renamed from: a */
        public UploadApkInfo createFromParcel(Parcel parcel) {
            return new UploadApkInfo(parcel);
        }

        /* renamed from: a */
        public UploadApkInfo[] newArray(int i) {
            return new UploadApkInfo[i];
        }
    };
    public static final String FILE_PACKAGE_NAME = "com.cyjh.file";
    public static final int TYPE_APK = 1;
    public static final int TYPE_FILE = 2;

    /* renamed from: a */
    private String f7559a;

    /* renamed from: b */
    private String f7560b;

    /* renamed from: c */
    private Drawable f7561c;

    /* renamed from: d */
    private long f7562d;

    /* renamed from: e */
    private String f7563e;

    /* renamed from: f */
    private String f7564f;

    /* renamed from: g */
    private String f7565g;

    /* renamed from: h */
    private int f7566h;

    /* renamed from: i */
    private String f7567i;

    /* renamed from: j */
    private String f7568j;

    /* renamed from: k */
    private String f7569k;

    /* renamed from: l */
    private long f7570l;

    /* renamed from: m */
    private long f7571m;

    /* renamed from: n */
    private EState f7572n;

    /* renamed from: o */
    private int f7573o = 1;

    /* renamed from: p */
    private String f7574p;

    /* renamed from: q */
    private int f7575q;

    /* loaded from: classes.dex */
    public enum EState {
        Init,
        Checked,
        Uploading,
        UploadCancle,
        UploadFaile,
        UploadSuccess,
        UploadDeleted
    }

    public UploadApkInfo() {
    }

    protected UploadApkInfo(Parcel parcel) {
        this.f7559a = parcel.readString();
        this.f7560b = parcel.readString();
        this.f7562d = parcel.readLong();
        this.f7563e = parcel.readString();
        this.f7564f = parcel.readString();
        this.f7565g = parcel.readString();
        this.f7566h = parcel.readInt();
        this.f7567i = parcel.readString();
        this.f7568j = parcel.readString();
        this.f7569k = parcel.readString();
        this.f7570l = parcel.readLong();
        this.f7571m = parcel.readLong();
        int readInt = parcel.readInt();
        this.f7572n = readInt == -1 ? null : EState.values()[readInt];
        this.f7574p = parcel.readString();
        this.f7575q = parcel.readInt();
    }

    public UploadApkInfo copy() {
        UploadApkInfo uploadApkInfo = new UploadApkInfo();
        uploadApkInfo.f7559a = this.f7559a;
        uploadApkInfo.f7560b = this.f7560b;
        uploadApkInfo.f7561c = this.f7561c;
        uploadApkInfo.f7569k = this.f7569k;
        uploadApkInfo.f7562d = this.f7562d;
        uploadApkInfo.f7563e = this.f7563e;
        uploadApkInfo.f7564f = this.f7564f;
        uploadApkInfo.f7565g = this.f7565g;
        uploadApkInfo.f7566h = this.f7566h;
        uploadApkInfo.f7567i = this.f7567i;
        uploadApkInfo.f7572n = this.f7572n;
        uploadApkInfo.f7568j = this.f7568j;
        uploadApkInfo.f7570l = this.f7570l;
        uploadApkInfo.f7571m = this.f7571m;
        uploadApkInfo.f7573o = this.f7573o;
        uploadApkInfo.f7574p = this.f7574p;
        return uploadApkInfo;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getFileId() {
        return this.f7574p;
    }

    public String getFileName() {
        return this.f7564f;
    }

    public Drawable getIcon() {
        return this.f7561c;
    }

    public String getIconUrl() {
        return this.f7569k;
    }

    public String getMd5() {
        return this.f7565g;
    }

    public String getName() {
        return this.f7560b;
    }

    public String getObjectKey() {
        return this.f7568j;
    }

    public String getPackageName() {
        return this.f7567i;
    }

    public String getPath() {
        return this.f7559a;
    }

    public long getSize() {
        return this.f7562d;
    }

    public String getSsize() {
        return this.f7563e;
    }

    public EState getState() {
        return this.f7572n;
    }

    public int getSyncState() {
        return this.f7575q;
    }

    public long getTaskID() {
        return this.f7571m;
    }

    public int getType() {
        return this.f7573o;
    }

    public long getUploadTime() {
        return this.f7570l;
    }

    public int getVercode() {
        return this.f7566h;
    }

    public void setFileId(String str) {
        this.f7574p = str;
    }

    public void setFileName(String str) {
        this.f7564f = str;
    }

    public void setIcon(Drawable drawable) {
        this.f7561c = drawable;
    }

    public void setIconUrl(String str) {
        this.f7569k = str;
    }

    public void setMd5(String str) {
        this.f7565g = str;
    }

    public void setName(String str) {
        this.f7560b = str;
    }

    public void setObjectKey(String str) {
        this.f7568j = str;
    }

    public void setPackageName(String str) {
        this.f7567i = str;
    }

    public void setPath(String str) {
        this.f7559a = str;
    }

    public void setSize(long j) {
        this.f7562d = j;
    }

    public void setSsize(String str) {
        this.f7563e = str;
    }

    public void setState(EState eState) {
        this.f7572n = eState;
    }

    public void setSyncState(int i) {
        this.f7575q = i;
    }

    public void setTaskID(long j) {
        this.f7571m = j;
    }

    public void setType(int i) {
        this.f7573o = i;
    }

    public void setUploadTime(long j) {
        this.f7570l = j;
    }

    public void setVercode(int i) {
        this.f7566h = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f7559a);
        parcel.writeString(this.f7560b);
        parcel.writeLong(this.f7562d);
        parcel.writeString(this.f7563e);
        parcel.writeString(this.f7564f);
        parcel.writeString(this.f7565g);
        parcel.writeInt(this.f7566h);
        parcel.writeString(this.f7567i);
        parcel.writeString(this.f7568j);
        parcel.writeString(this.f7569k);
        parcel.writeLong(this.f7570l);
        parcel.writeLong(this.f7571m);
        EState eState = this.f7572n;
        parcel.writeInt(eState == null ? -1 : eState.ordinal());
        parcel.writeString(this.f7574p);
        parcel.writeInt(this.f7575q);
    }
}
