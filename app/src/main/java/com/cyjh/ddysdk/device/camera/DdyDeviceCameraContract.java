package com.cyjh.ddysdk.device.camera;

import com.cyjh.ddy.base.p033a.NoProGuard;
import com.cyjh.ddysdk.order.base.bean.DdyOrderInfo;

/* loaded from: classes.dex */
public interface DdyDeviceCameraContract extends NoProGuard {
    void start(DdyOrderInfo ddyOrderInfo, String str, int i);

    void stop();

    void upMediaPing(int i);
}
