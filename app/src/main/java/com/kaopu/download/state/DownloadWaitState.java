package com.kaopu.download.state;

import android.os.Parcel;
import android.os.Parcelable;
import com.kaopu.download.BaseDownloadStateFactory;
import com.kaopu.download.intf.IDownloadClickHelper;
import com.kaopu.download.intf.IDownloadDisplayHelper;
import com.kaopu.download.intf.IDownloadState;
import com.kaopu.download.kernel.BaseDownloadInfo;

/* loaded from: classes.dex */
public class DownloadWaitState implements IDownloadState {
    public static final Parcelable.Creator<DownloadWaitState> CREATOR = new Parcelable.Creator<DownloadWaitState>() { // from class: com.kaopu.download.state.DownloadWaitState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DownloadWaitState[] newArray(int i) {
            return new DownloadWaitState[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DownloadWaitState createFromParcel(Parcel parcel) {
            return new DownloadWaitState(parcel);
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
        return BaseDownloadStateFactory.State.DOWNLOAD_WAIT;
    }

    @Override // com.kaopu.download.intf.IDownloadState
    public void onClick(IDownloadClickHelper<? extends BaseDownloadInfo> iDownloadClickHelper) {
        iDownloadClickHelper.onDownloadWaitClick();
    }

    @Override // com.kaopu.download.intf.IDownloadState
    public void display(IDownloadDisplayHelper<? extends BaseDownloadInfo> iDownloadDisplayHelper) {
        iDownloadDisplayHelper.onDownloadWaitDisplay();
    }

    public DownloadWaitState() {
    }

    public DownloadWaitState(Parcel parcel) {
    }
}
