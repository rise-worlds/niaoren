package com.kaopu.download.state;

import android.os.Parcel;
import android.os.Parcelable;
import com.kaopu.download.BaseDownloadStateFactory;
import com.kaopu.download.intf.IDownloadClickHelper;
import com.kaopu.download.intf.IDownloadDisplayHelper;
import com.kaopu.download.intf.IDownloadState;
import com.kaopu.download.kernel.BaseDownloadInfo;

/* loaded from: classes.dex */
public class DownloadUnKnownState implements IDownloadState {
    public static final Parcelable.Creator<DownloadUnKnownState> CREATOR = new Parcelable.Creator<DownloadUnKnownState>() { // from class: com.kaopu.download.state.DownloadUnKnownState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DownloadUnKnownState[] newArray(int i) {
            return new DownloadUnKnownState[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DownloadUnKnownState createFromParcel(Parcel parcel) {
            return new DownloadUnKnownState(parcel);
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
        return BaseDownloadStateFactory.State.DOWNLOAD_UNKNOWN;
    }

    @Override // com.kaopu.download.intf.IDownloadState
    public void onClick(IDownloadClickHelper<? extends BaseDownloadInfo> iDownloadClickHelper) {
        iDownloadClickHelper.onDownloadNoneClick();
    }

    @Override // com.kaopu.download.intf.IDownloadState
    public void display(IDownloadDisplayHelper<? extends BaseDownloadInfo> iDownloadDisplayHelper) {
        iDownloadDisplayHelper.onDownloadNoneDisplay();
    }

    public DownloadUnKnownState() {
    }

    public DownloadUnKnownState(Parcel parcel) {
    }
}
