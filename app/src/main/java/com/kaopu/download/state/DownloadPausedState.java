package com.kaopu.download.state;

import android.os.Parcel;
import android.os.Parcelable;
import com.kaopu.download.BaseDownloadStateFactory;
import com.kaopu.download.intf.IDownloadClickHelper;
import com.kaopu.download.intf.IDownloadDisplayHelper;
import com.kaopu.download.intf.IDownloadState;
import com.kaopu.download.kernel.BaseDownloadInfo;

/* loaded from: classes.dex */
public class DownloadPausedState implements IDownloadState {
    public static final Parcelable.Creator<DownloadPausedState> CREATOR = new Parcelable.Creator<DownloadPausedState>() { // from class: com.kaopu.download.state.DownloadPausedState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DownloadPausedState[] newArray(int i) {
            return new DownloadPausedState[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DownloadPausedState createFromParcel(Parcel parcel) {
            return new DownloadPausedState(parcel);
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
        return BaseDownloadStateFactory.State.DOWNLOAD_PAUSED;
    }

    @Override // com.kaopu.download.intf.IDownloadState
    public void onClick(IDownloadClickHelper<? extends BaseDownloadInfo> iDownloadClickHelper) {
        iDownloadClickHelper.onDownloadPausedClick();
    }

    @Override // com.kaopu.download.intf.IDownloadState
    public void display(IDownloadDisplayHelper<? extends BaseDownloadInfo> iDownloadDisplayHelper) {
        iDownloadDisplayHelper.onDownloadPausedDisplay();
    }

    public DownloadPausedState() {
    }

    public DownloadPausedState(Parcel parcel) {
    }
}
