package com.cyjh.mobileanjian.ipc.interfaces;

import com.cyjh.mobileanjian.ipc.CrashRunnerState;
import com.cyjh.mq.p048c.IpcConnection;

/* loaded from: classes.dex */
public interface EngineStateObserver {
    void onConnected(IpcConnection bVar);

    void onCrash(CrashRunnerState bVar);

    void onExit();
}
