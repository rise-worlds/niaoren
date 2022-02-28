package com.kaopu.download.kernel;

import android.os.Parcel;
import android.os.Parcelable;
import com.cyjh.db.JsonPersister;
import com.kaopu.download.BaseDownloadWorker;
import com.kaopu.download.abst.ADownloadWorker;
import com.kaopu.download.intf.IDownloadClickHelper;
import com.kaopu.download.intf.IDownloadDisplayHelper;
import com.kaopu.download.intf.IDownloadState;
import com.kaopu.download.state.DownloadNewState;
import java.io.File;
import p110z1.DatabaseField;

/* loaded from: classes.dex */
public class BaseDownloadInfo implements Parcelable {
    public static final Parcelable.Creator<BaseDownloadInfo> CREATOR = new Parcelable.Creator<BaseDownloadInfo>() { // from class: com.kaopu.download.kernel.BaseDownloadInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BaseDownloadInfo createFromParcel(Parcel parcel) {
            return new BaseDownloadInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BaseDownloadInfo[] newArray(int i) {
            return new BaseDownloadInfo[i];
        }
    };
    @DatabaseField
    private long createTime;
    @DatabaseField
    private long dSize;
    private String downloadWorkerClassName;
    @DatabaseField
    private long fSize;
    @DatabaseField(m859f = true)
    private String identification;
    private BaseDownloadWorker.DownloadCallBack mCallBack;
    @DatabaseField
    private String saveDir;
    @DatabaseField
    private String saveName;
    @DatabaseField
    private long speed;
    @DatabaseField
    private String url;
    @DatabaseField(m842w = JsonPersister.class)
    private IDownloadState state = new DownloadNewState();
    @DatabaseField
    private long downloadedTime = 0;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BaseDownloadInfo() {
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long j) {
        this.createTime = j;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public long getfSize() {
        return this.fSize;
    }

    public void setfSize(long j) {
        this.fSize = j;
    }

    public long getdSize() {
        return this.dSize;
    }

    public boolean isDownLoaded() {
        return new File(this.saveDir, this.saveName).exists();
    }

    public void setdSize(long j) {
        this.dSize = j;
    }

    public IDownloadState getState() {
        return this.state;
    }

    public void setState(IDownloadState iDownloadState) {
        this.state = iDownloadState;
    }

    public long getSpeed() {
        return this.speed;
    }

    public void setSpeed(long j) {
        this.speed = j;
    }

    public long getDownloadedTime() {
        return this.downloadedTime;
    }

    public void setDownloadedTime(long j) {
        this.downloadedTime = j;
    }

    public String getSaveName() {
        return this.saveName;
    }

    public void setSaveName(String str) {
        this.saveName = str;
    }

    public String getSaveDir() {
        return this.saveDir;
    }

    public String getSavePath() {
        return this.saveDir + this.saveName;
    }

    public void setSaveDir(String str) {
        this.saveDir = str;
    }

    public BaseDownloadWorker.DownloadCallBack getCallBack() {
        return this.mCallBack;
    }

    public void setCallBack(BaseDownloadWorker.DownloadCallBack downloadCallBack) {
        this.mCallBack = downloadCallBack;
    }

    public String getDownloadWorkerClassName() {
        return this.downloadWorkerClassName;
    }

    public void setDownloadWorkerClassName(String str) {
        this.downloadWorkerClassName = str;
    }

    public void setDownloadWorkerClassName(Class<? extends ADownloadWorker<? extends BaseDownloadInfo>> cls) {
        this.downloadWorkerClassName = cls.getName();
    }

    public String getIdentification() {
        return this.identification;
    }

    public void setIdentification(String str) {
        this.identification = str;
    }

    public void onClick(IDownloadClickHelper<? extends BaseDownloadInfo> iDownloadClickHelper) {
        this.state.onClick(iDownloadClickHelper);
    }

    public void display(IDownloadDisplayHelper<? extends BaseDownloadInfo> iDownloadDisplayHelper) {
        this.state.display(iDownloadDisplayHelper);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof BaseDownloadInfo)) {
            return ((BaseDownloadInfo) obj).getIdentification().equals(getIdentification());
        }
        return false;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.url);
        parcel.writeLong(this.fSize);
        parcel.writeLong(this.dSize);
        parcel.writeParcelable(this.state, 0);
        parcel.writeLong(this.speed);
        parcel.writeLong(this.createTime);
        parcel.writeLong(this.downloadedTime);
        parcel.writeString(this.saveDir);
        parcel.writeString(this.saveName);
        parcel.writeParcelable(this.mCallBack, 0);
        parcel.writeString(this.downloadWorkerClassName);
        parcel.writeString(this.identification);
    }

    public BaseDownloadInfo(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void readFromParcel(Parcel parcel) {
        setUrl(parcel.readString());
        setfSize(parcel.readLong());
        setdSize(parcel.readLong());
        setState((IDownloadState) parcel.readParcelable(IDownloadState.class.getClassLoader()));
        setSpeed(parcel.readLong());
        setCreateTime(parcel.readLong());
        setDownloadedTime(parcel.readLong());
        setSaveDir(parcel.readString());
        setSaveName(parcel.readString());
        setCallBack((BaseDownloadWorker.DownloadCallBack) parcel.readParcelable(BaseDownloadWorker.DownloadCallBack.class.getClassLoader()));
        setDownloadWorkerClassName(parcel.readString());
        setIdentification(parcel.readString());
    }

    public String toString() {
        return "BaseDownloadInfo{identification='" + this.identification + "', url='" + this.url + "', saveDir='" + this.saveDir + "', saveName='" + this.saveName + "', fSize=" + this.fSize + ", dSize=" + this.dSize + ", state=" + this.state.getState().toString() + ", speed=" + this.speed + ", createTime=" + this.createTime + ", downloadedTime=" + this.downloadedTime + ", mCallBack=" + this.mCallBack + ", downloadWorkerClassName='" + this.downloadWorkerClassName + "'}";
    }
}
