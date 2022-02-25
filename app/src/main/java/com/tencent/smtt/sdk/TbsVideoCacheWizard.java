package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Bundle;
import com.tencent.smtt.export.external.DexLoader;

/* renamed from: com.tencent.smtt.sdk.n */
/* loaded from: classes2.dex */
class TbsVideoCacheWizard {

    /* renamed from: a */
    private DexLoader f13227a;

    /* renamed from: b */
    private Object f13228b = null;

    public TbsVideoCacheWizard(DexLoader dexLoader) {
        this.f13227a = null;
        this.f13227a = dexLoader;
    }

    /* renamed from: a */
    public Object m16654a(Context context, Object obj, Bundle bundle) {
        DexLoader dexLoader = this.f13227a;
        if (dexLoader != null) {
            this.f13228b = dexLoader.newInstance("com.tencent.tbs.cache.TbsVideoCacheTaskProxy", new Class[]{Context.class, Object.class, Bundle.class}, context, obj, bundle);
        }
        return this.f13228b;
    }

    /* renamed from: a */
    public void m16655a() {
        DexLoader dexLoader = this.f13227a;
        if (dexLoader != null) {
            dexLoader.invokeMethod(this.f13228b, "com.tencent.tbs.cache.TbsVideoCacheTaskProxy", "pauseTask", new Class[0], new Object[0]);
        }
    }

    /* renamed from: b */
    public void m16652b() {
        DexLoader dexLoader = this.f13227a;
        if (dexLoader != null) {
            dexLoader.invokeMethod(this.f13228b, "com.tencent.tbs.cache.TbsVideoCacheTaskProxy", "resumeTask", new Class[0], new Object[0]);
        }
    }

    /* renamed from: c */
    public void m16651c() {
        DexLoader dexLoader = this.f13227a;
        if (dexLoader != null) {
            dexLoader.invokeMethod(this.f13228b, "com.tencent.tbs.cache.TbsVideoCacheTaskProxy", "stopTask", new Class[0], new Object[0]);
        }
    }

    /* renamed from: a */
    public void m16653a(boolean z) {
        DexLoader dexLoader = this.f13227a;
        if (dexLoader != null) {
            dexLoader.invokeMethod(this.f13228b, "com.tencent.tbs.cache.TbsVideoCacheTaskProxy", "removeTask", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    /* renamed from: d */
    public long m16650d() {
        DexLoader dexLoader = this.f13227a;
        if (dexLoader == null) {
            return 0L;
        }
        Object invokeMethod = dexLoader.invokeMethod(this.f13228b, "com.tencent.tbs.cache.TbsVideoCacheTaskProxy", "getContentLength", new Class[0], new Object[0]);
        if (invokeMethod instanceof Long) {
            return ((Long) invokeMethod).longValue();
        }
        return 0L;
    }

    /* renamed from: e */
    public int m16649e() {
        DexLoader dexLoader = this.f13227a;
        if (dexLoader != null) {
            Object invokeMethod = dexLoader.invokeMethod(this.f13228b, "com.tencent.tbs.cache.TbsVideoCacheTaskProxy", "getDownloadedSize", new Class[0], new Object[0]);
            if (invokeMethod instanceof Integer) {
                return ((Integer) invokeMethod).intValue();
            }
        }
        return 0;
    }

    /* renamed from: f */
    public int m16648f() {
        DexLoader dexLoader = this.f13227a;
        if (dexLoader != null) {
            Object invokeMethod = dexLoader.invokeMethod(this.f13228b, "com.tencent.tbs.cache.TbsVideoCacheTaskProxy", "getProgress", new Class[0], new Object[0]);
            if (invokeMethod instanceof Integer) {
                return ((Integer) invokeMethod).intValue();
            }
        }
        return 0;
    }
}
