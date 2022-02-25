package com.tencent.smtt.sdk;

import android.content.Context;
import java.lang.reflect.Field;

/* loaded from: classes2.dex */
public class CookieSyncManager {

    /* renamed from: a */
    private static android.webkit.CookieSyncManager f12757a = null;

    /* renamed from: b */
    private static CookieSyncManager f12758b = null;

    /* renamed from: c */
    private static boolean f12759c = false;

    public static synchronized CookieSyncManager createInstance(Context context) {
        CookieSyncManager cookieSyncManager;
        synchronized (CookieSyncManager.class) {
            f12757a = android.webkit.CookieSyncManager.createInstance(context);
            if (f12758b == null || !f12759c) {
                f12758b = new CookieSyncManager(context.getApplicationContext());
            }
            cookieSyncManager = f12758b;
        }
        return cookieSyncManager;
    }

    public static synchronized CookieSyncManager getInstance() {
        CookieSyncManager cookieSyncManager;
        synchronized (CookieSyncManager.class) {
            if (f12758b != null) {
                cookieSyncManager = f12758b;
            } else {
                throw new IllegalStateException("CookieSyncManager::createInstance() needs to be called before CookieSyncManager::getInstance()");
            }
        }
        return cookieSyncManager;
    }

    private CookieSyncManager(Context context) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a != null && a.m16618b()) {
            a.m16616c().m16598b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieSyncManager_createInstance", new Class[]{Context.class}, context);
            f12759c = true;
        }
    }

    public void sync() {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            f12757a.sync();
        } else {
            a.m16616c().m16598b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieSyncManager_Sync", new Class[0], new Object[0]);
        }
    }

    public void stopSync() {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            f12757a.stopSync();
        } else {
            a.m16616c().m16598b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieSyncManager_stopSync", new Class[0], new Object[0]);
        }
    }

    public void startSync() {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            f12757a.startSync();
            try {
                Field declaredField = Class.forName("android.webkit.WebSyncManager").getDeclaredField("mSyncThread");
                declaredField.setAccessible(true);
                ((Thread) declaredField.get(f12757a)).setUncaughtExceptionHandler(new SQLiteUncaughtExceptionHandler());
            } catch (Exception unused) {
            }
        } else {
            a.m16616c().m16598b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieSyncManager_startSync", new Class[0], new Object[0]);
        }
    }
}
