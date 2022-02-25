package com.kaopu.download.intf;

import com.kaopu.download.kernel.BaseDownloadInfo;

/* loaded from: classes.dex */
public interface IDownloadClickHelper<T extends BaseDownloadInfo> {
    T getDownloadInfo();

    void onDownloadCancelingClick();

    void onDownloadConnectingClick();

    void onDownloadFailedClick();

    void onDownloadNewClick();

    void onDownloadNoneClick();

    void onDownloadPausedClick();

    void onDownloadPausingClick();

    void onDownloadWaitClick();

    void onDownloadedClick();

    void onDownloadingClick();

    void setDownloadInfo(T t);
}
