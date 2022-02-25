package com.kaopu.download.state;

import android.os.Parcel;
import android.os.Parcelable;
import com.kaopu.download.BaseDownloadStateFactory;
import com.kaopu.download.intf.IDownloadClickHelper;
import com.kaopu.download.intf.IDownloadDisplayHelper;
import com.kaopu.download.intf.IDownloadState;
import com.kaopu.download.kernel.BaseDownloadInfo;

/* loaded from: classes.dex */
public class DownloadCancelingState implements IDownloadState {
    public static final Parcelable.Creator<DownloadCancelingState> CREATOR = new Parcelable.Creator<DownloadCancelingState>() { // from class: com.kaopu.download.state.DownloadCancelingState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DownloadCancelingState[] newArray(int i) {
            return new DownloadCancelingState[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DownloadCancelingState createFromParcel(Parcel parcel) {
            return new DownloadCancelingState(parcel);
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
    }

    @Override // com.kaopu.download.intf.IDownloadState
    public BaseDownloadStateFactory.State getState() {
        return BaseDownloadStateFactory.State.DOWNLOAD_CANCELING;
    }

    @Override // com.kaopu.download.intf.IDownloadState
    public void onClick(IDownloadClickHelper<? extends BaseDownloadInfo> iDownloadClickHelper) {
        iDownloadClickHelper.onDownloadCancelingClick();
    }

    @Override // com.kaopu.download.intf.IDownloadState
    public void display(IDownloadDisplayHelper<? extends BaseDownloadInfo> iDownloadDisplayHelper) {
        iDownloadDisplayHelper.onDownloadCancelingDisplay();
    }

    public DownloadCancelingState() {
    }

    public DownloadCancelingState(Parcel parcel) {
    }
}
