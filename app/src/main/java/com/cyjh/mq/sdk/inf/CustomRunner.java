package com.cyjh.mq.sdk.inf;

import android.app.Application;
import com.cyjh.mobileanjian.ipc.interfaces.OnRequestCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnSpecialMqCmdCallback;

/* renamed from: com.cyjh.mq.sdk.inf.a */
/* loaded from: classes.dex */
public interface CustomRunner {
    void download(String str);

    void getScriptPerm(String str);

    void init(Application application, String str);

    void recordLog2File(boolean z);

    void setDeviceSessionId(String str);

    void setOnSpecialMqCmdCallback(OnSpecialMqCmdCallback onSpecialMqCmdCallback);

    void setRequestCallback(OnRequestCallback onRequestCallback);

    void setSid(long j);
}
