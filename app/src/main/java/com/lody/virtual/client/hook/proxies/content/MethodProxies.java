package com.lody.virtual.client.hook.proxies.content;

import android.content.pm.ProviderInfo;
import android.database.IContentObserver;
import android.net.Uri;
import android.os.Build;
import com.lody.virtual.client.hook.base.MethodProxy;
import com.lody.virtual.client.ipc.VContentManager;
import com.lody.virtual.client.ipc.VPackageManager;
import com.lody.virtual.helper.Keep;
import com.lody.virtual.p061os.VUserHandle;
import java.lang.reflect.Method;

@Keep
/* loaded from: classes.dex */
public class MethodProxies {
    private static boolean isAppUri(Uri uri) {
        ProviderInfo resolveContentProvider = VPackageManager.get().resolveContentProvider(uri.getAuthority(), 0, VUserHandle.myUserId());
        return resolveContentProvider != null && resolveContentProvider.enabled;
    }

    public static Object registerContentObserver(Object obj, Method method, Object[] objArr) throws Throwable {
        if (Build.VERSION.SDK_INT >= 24 && objArr.length >= 5) {
            objArr[4] = 22;
        }
        Uri uri = (Uri) objArr[0];
        boolean booleanValue = ((Boolean) objArr[1]).booleanValue();
        IContentObserver iContentObserver = (IContentObserver) objArr[2];
        if (isAppUri(uri)) {
            VContentManager.get().registerContentObserver(uri, booleanValue, iContentObserver, VUserHandle.myUserId());
            return 0;
        }
        MethodProxy.replaceFirstUserId(objArr);
        return method.invoke(obj, objArr);
    }

    public static Object unregisterContentObserver(Object obj, Method method, Object[] objArr) throws Throwable {
        VContentManager.get().unregisterContentObserver((IContentObserver) objArr[0]);
        return method.invoke(obj, objArr);
    }

    public static Object notifyChange(Object obj, Method method, Object[] objArr) throws Throwable {
        if (Build.VERSION.SDK_INT >= 24 && objArr.length >= 6) {
            objArr[5] = 22;
        }
        Uri uri = (Uri) objArr[0];
        if (isAppUri(uri)) {
            boolean z = true;
            IContentObserver iContentObserver = (IContentObserver) objArr[1];
            boolean booleanValue = ((Boolean) objArr[2]).booleanValue();
            if (!(objArr[3] instanceof Integer)) {
                z = ((Boolean) objArr[3]).booleanValue();
            } else if ((((Integer) objArr[3]).intValue() & 1) == 0) {
                z = false;
            }
            VContentManager.get().notifyChange(uri, iContentObserver, booleanValue, z, VUserHandle.myUserId());
            return 0;
        }
        MethodProxy.replaceLastUserId(objArr);
        return method.invoke(obj, objArr);
    }
}
