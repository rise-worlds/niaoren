package com.cyjh.mq.sdk.inf;

/* renamed from: com.cyjh.mq.sdk.inf.IScript */
/* loaded from: classes.dex */
public interface IScript {
    String getAppId();

    String getAtcPath();

    String getConfigPath();

    long getEncryptKey();

    String getLcPath();

    int getRepeat();

    int getTimeInMinutes();

    int getTrialTime();

    String getUserName();

    boolean isEncrypt();

    boolean isFengwoScript();

    IScript setAppId(String str);

    IScript setAtcPath(String str);

    IScript setConfigPath(String str);

    IScript setEncrypt(boolean z);

    IScript setEncryptKey(long j);

    IScript setIsFengwoScript(boolean z);

    IScript setLcPath(String str);

    IScript setRepeat(int i);

    IScript setTimeInMinutes(int i);

    IScript setTrialTime(int i);

    IScript setUserName(String str);
}
