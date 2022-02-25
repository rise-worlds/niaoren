package com.cyjh.mobileanjian.ipc.interfaces;

/* loaded from: classes.dex */
public interface ScriptStateObserver {
    void onScriptIsRunning();

    void onStartScript();

    void onStopScript(int i, String str);
}
