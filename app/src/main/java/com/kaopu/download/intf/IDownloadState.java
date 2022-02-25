package com.kaopu.download.intf;

import android.os.Parcelable;
import com.kaopu.download.BaseDownloadStateFactory;
import com.kaopu.download.kernel.BaseDownloadInfo;

/* loaded from: classes.dex */
public interface IDownloadState extends Parcelable {
    void display(IDownloadDisplayHelper<? extends BaseDownloadInfo> iDownloadDisplayHelper);

    BaseDownloadStateFactory.State getState();

    void onClick(IDownloadClickHelper<? extends BaseDownloadInfo> iDownloadClickHelper);
}
