package com.kaopu.download.state;

import android.os.Parcel;
import android.os.Parcelable;
import com.kaopu.download.BaseDownloadStateFactory;
import com.kaopu.download.intf.IDownloadClickHelper;
import com.kaopu.download.intf.IDownloadDisplayHelper;
import com.kaopu.download.intf.IDownloadState;
import com.kaopu.download.kernel.BaseDownloadInfo;

/* loaded from: classes.dex */
public class DownloadNewState implements IDownloadState {
    public static final Parcelable.Creator<DownloadNewState> CREATOR = new Parcelable.Creator<DownloadNewState>() { // from class: com.kaopu.download.state.DownloadNewState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DownloadNewState[] newArray(int i) {
            return new DownloadNewState[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DownloadNewState createFromParcel(Parcel parcel) {
            return new DownloadNewState(parcel);
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
        return BaseDownloadStateFactory.State.DOWNLOAD_NEW;
    }

    @Override // com.kaopu.download.intf.IDownloadState
    public void onClick(IDownloadClickHelper<? extends BaseDownloadInfo> iDownloadClickHelper) {
        iDownloadClickHelper.onDownloadNewClick();
    }

    @Override // com.kaopu.download.intf.IDownloadState
    public void display(IDownloadDisplayHelper<? extends BaseDownloadInfo> iDownloadDisplayHelper) {
        iDownloadDisplayHelper.onDownloadNewDisplay();
    }

    public DownloadNewState() {
    }

    public DownloadNewState(Parcel parcel) {
    }
}
