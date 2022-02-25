package com.kaopu;

import android.app.Application;
import android.content.Context;
import android.os.RemoteException;
import com.lody.virtual.remote.InstalledAppInfo;

/* loaded from: classes.dex */
public interface VACallBack {
    void fixFor1(InstalledAppInfo installedAppInfo);

    void fixFor2(String str, int i) throws RemoteException;

    void fixForAll(Context context, Application application, InstalledAppInfo installedAppInfo, String str);

    void startYafaVa(Context context);
}
