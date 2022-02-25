package com.cyjh.ddy.media.media.listener;

import com.cyjh.ddysdk.order.base.bean.NoticeSyncInfo;

/* loaded from: classes.dex */
public interface IHwySDKListener {
    void actionCodeCallback(int i, String str);

    void autoRotateScreen(int i);

    void upClipboard(String str);

    void upFps(String str);

    void upFrameTime(long j);

    void upNoticeSyncInfo(NoticeSyncInfo noticeSyncInfo);

    void upPing(String str);

    void upTraffic(long j, long j2);
}
