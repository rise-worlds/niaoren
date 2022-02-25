package com.common.base;

import android.app.Application;
import com.common.utils.SharedPreferencesUtil;
import com.tencent.mmkv.MMKV;

/* loaded from: classes.dex */
public abstract class BaseApplication extends Application {
    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        initMMKV();
        SharedPreferencesUtil.getInstance().init(this);
        instance = this;
    }

    private void initMMKV() {
        MMKV.initialize(this);
    }
}
