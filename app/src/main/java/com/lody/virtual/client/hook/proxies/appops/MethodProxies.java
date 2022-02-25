package com.lody.virtual.client.hook.proxies.appops;

import android.annotation.TargetApi;
import com.lody.virtual.GmsSupport;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.helper.Keep;
import java.lang.reflect.Method;

@Keep
@TargetApi(19)
/* loaded from: classes.dex */
public class MethodProxies {
    private static void replaceUidAndPackage(Object[] objArr, int i) {
        objArr[i] = VirtualCore.get().getHostPkg();
        int i2 = i - 1;
        if (objArr[i2] instanceof Integer) {
            objArr[i2] = Integer.valueOf(VirtualCore.get().myUid());
        }
    }

    public static Object checkAudioOperation(Object obj, Method method, Object[] objArr) throws Throwable {
        replaceUidAndPackage(objArr, 3);
        return method.invoke(obj, objArr);
    }

    public static Object checkOperation(Object obj, Method method, Object[] objArr) throws Throwable {
        replaceUidAndPackage(objArr, 2);
        return method.invoke(obj, objArr);
    }

    public static Object checkPackage(Object obj, Method method, Object[] objArr) throws Throwable {
        if (GmsSupport.isGoogleAppOrService((String) objArr[1])) {
            return 0;
        }
        replaceUidAndPackage(objArr, 1);
        return method.invoke(obj, objArr);
    }

    public static Object getOpsForPackage(Object obj, Method method, Object[] objArr) throws Throwable {
        replaceUidAndPackage(objArr, 1);
        return method.invoke(obj, objArr);
    }

    public static Object getPackagesForOps(Object obj, Method method, Object[] objArr) throws Throwable {
        return method.invoke(obj, objArr);
    }

    public static Object noteOperation(Object obj, Method method, Object[] objArr) throws Throwable {
        replaceUidAndPackage(objArr, 2);
        return method.invoke(obj, objArr);
    }

    public static Object noteProxyOperation(Object obj, Method method, Object[] objArr) throws Throwable {
        return 0;
    }

    public static Object resetAllModes(Object obj, Method method, Object[] objArr) throws Throwable {
        objArr[0] = 0;
        objArr[1] = VirtualCore.get().getHostPkg();
        return method.invoke(obj, objArr);
    }

    public static Object startOperation(Object obj, Method method, Object[] objArr) throws Throwable {
        replaceUidAndPackage(objArr, 3);
        return method.invoke(obj, objArr);
    }

    public static Object finishOperation(Object obj, Method method, Object[] objArr) throws Throwable {
        replaceUidAndPackage(objArr, 3);
        return method.invoke(obj, objArr);
    }

    public static Object checkOperationRaw(Object obj, Method method, Object[] objArr) throws Throwable {
        replaceUidAndPackage(objArr, 2);
        return method.invoke(obj, objArr);
    }
}
