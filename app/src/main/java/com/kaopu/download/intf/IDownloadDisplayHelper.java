package com.kaopu.download.intf;

import com.kaopu.download.kernel.BaseDownloadInfo;

/* loaded from: classes.dex */
public interface IDownloadDisplayHelper<T extends BaseDownloadInfo> {
    T getDownloadInfo();

    void onDownloadCancelingDisplay();

    void onDownloadConnectDisplay();

    void onDownloadFailedDisplay();

    void onDownloadNewDisplay();

    void onDownloadNoneDisplay();

    void onDownloadPausedDisplay();

    void onDownloadPausingDisplay();

    void onDownloadWaitDisplay();

    void onDownloadedDisplay();

    void onDownloadingDisplay();

    void registerDownloadReceiver();

    void setDownloadInfo(T t);

    void unregisterDownloadReceiver();
}
