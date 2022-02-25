package com.cyjh.ddysdk.ddyobs;

import com.cyjh.ddy.base.p033a.NoProGuard;

/* loaded from: classes.dex */
public interface ObsContract extends NoProGuard {

    /* loaded from: classes.dex */
    public interface Callback<T> extends NoProGuard {
        void onFail(int i, String str);

        void onSuccess(T t);
    }

    /* loaded from: classes.dex */
    public interface UploadCallback<T> extends NoProGuard {
        void onCancel(long j);

        void onFail(long j, int i, String str);

        void onSuccess(T t);
    }
}
