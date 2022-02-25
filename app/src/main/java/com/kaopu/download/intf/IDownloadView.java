package com.kaopu.download.intf;

import android.content.Context;
import com.kaopu.download.BaseDownloadStateFactory;
import com.kaopu.download.kernel.BaseDownloadInfo;

/* loaded from: classes.dex */
public interface IDownloadView<T extends BaseDownloadInfo> {
    void cancel();

    boolean checkDownloadState(T t);

    Context getContext();

    T getDownloadInfo();

    BaseDownloadStateFactory.State getState();

    void pause();

    void setDownloadInfo(T t);
}
