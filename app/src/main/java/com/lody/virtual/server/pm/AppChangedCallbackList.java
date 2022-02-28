package com.lody.virtual.server.pm;

import java.util.ArrayList;
import java.util.List;

/* renamed from: com.lody.virtual.server.pm.AppChangedCallbackList */
/* loaded from: classes.dex */
public class AppChangedCallbackList {
    private static final AppChangedCallbackList sInstance = new AppChangedCallbackList();
    private List<IAppChangedCallback> mList = new ArrayList(2);

    public static AppChangedCallbackList get() {
        return sInstance;
    }

    public void register(IAppChangedCallback iAppChangedCallback) {
        this.mList.add(iAppChangedCallback);
    }

    public void unregister(IAppChangedCallback iAppChangedCallback) {
        this.mList.remove(iAppChangedCallback);
    }

    void notifyCallbacks(boolean z) {
        for (IAppChangedCallback iAppChangedCallback : this.mList) {
            iAppChangedCallback.onCallback(z);
        }
    }
}
