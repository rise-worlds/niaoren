package com.lbd.p054xj.downloads.callback;

import android.os.Parcel;
import android.os.Parcelable;
import com.kaopu.download.BaseDownloadWorker;

/* renamed from: com.lbd.xj.downloads.callback.FileDownloadCallback */
/* loaded from: classes.dex */
public class FileDownloadCallback implements BaseDownloadWorker.DownloadCallBack {
    public static final Parcelable.Creator<FileDownloadCallback> CREATOR = new Parcelable.Creator<FileDownloadCallback>() { // from class: com.lbd.xj.downloads.callback.FileDownloadCallback.1
        /* renamed from: a */
        public FileDownloadCallback createFromParcel(Parcel parcel) {
            return new FileDownloadCallback(parcel);
        }

        /* renamed from: a */
        public FileDownloadCallback[] newArray(int i) {
            return new FileDownloadCallback[i];
        }
    };

    /* renamed from: a */
    private AbstractC1492a f9454a;

    /* renamed from: com.lbd.xj.downloads.callback.FileDownloadCallback$a */
    /* loaded from: classes.dex */
    public interface AbstractC1492a {
        /* renamed from: a */
        void m19781a(String str);

        /* renamed from: a */
        void m19780a(String str, long j, long j2, int i);

        /* renamed from: a */
        void m19779a(String str, String str2, long j);

        /* renamed from: b */
        void m19778b(String str);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.kaopu.download.BaseDownloadWorker.DownloadCallBack
    public void onDownloadCanceling(String str) {
    }

    @Override // com.kaopu.download.BaseDownloadWorker.DownloadCallBack
    public void onDownloadPaused(String str) {
    }

    @Override // com.kaopu.download.BaseDownloadWorker.DownloadCallBack
    public void onDownloadPausing(String str) {
    }

    @Override // com.kaopu.download.BaseDownloadWorker.DownloadCallBack
    public void onDownloadStart(String str, long j) {
    }

    @Override // com.kaopu.download.BaseDownloadWorker.DownloadCallBack
    public void onDownloadWait(String str) {
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
    }

    public FileDownloadCallback(Parcel parcel) {
    }

    /* renamed from: a */
    public void m19784a(AbstractC1492a aVar) {
        this.f9454a = aVar;
    }

    public FileDownloadCallback() {
    }

    @Override // com.kaopu.download.BaseDownloadWorker.DownloadCallBack
    public void onDownloadCanceled(String str) {
        AbstractC1492a aVar = this.f9454a;
        if (aVar != null) {
            aVar.m19781a(str);
        }
    }

    @Override // com.kaopu.download.BaseDownloadWorker.DownloadCallBack
    public void onDownloadWorking(String str, long j, long j2, int i) {
        AbstractC1492a aVar = this.f9454a;
        if (aVar != null) {
            aVar.m19780a(str, j, j2, i);
        }
    }

    @Override // com.kaopu.download.BaseDownloadWorker.DownloadCallBack
    public void onDownloadCompleted(String str, String str2, long j) {
        AbstractC1492a aVar = this.f9454a;
        if (aVar != null) {
            aVar.m19779a(str, str2, j);
        }
    }

    @Override // com.kaopu.download.BaseDownloadWorker.DownloadCallBack
    public void onDownloadFailed(String str) {
        AbstractC1492a aVar = this.f9454a;
        if (aVar != null) {
            aVar.m19778b(str);
        }
    }
}
