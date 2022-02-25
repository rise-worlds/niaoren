package com.lody.virtual.client.hook.providers;

import android.content.ContentValues;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IInterface;
import android.os.ParcelFileDescriptor;
import android.support.p003v4.app.NotificationCompat;
import com.lody.virtual.client.hook.base.MethodBox;
import com.lody.virtual.helper.compat.BuildCompat;
import com.lody.virtual.helper.utils.VLog;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.tools.ant.taskdefs.optional.j2ee.HotDeploymentTool;
import p110z1.IContentProvider;

/* loaded from: classes.dex */
public class ProviderHook implements InvocationHandler {
    private static final Map<String, HookFetcher> PROVIDER_MAP = new HashMap();
    public static final String QUERY_ARG_SQL_SELECTION = "android:query-arg-sql-selection";
    public static final String QUERY_ARG_SQL_SELECTION_ARGS = "android:query-arg-sql-selection-args";
    public static final String QUERY_ARG_SQL_SORT_ORDER = "android:query-arg-sql-sort-order";
    protected final Object mBase;

    /* loaded from: classes.dex */
    public interface HookFetcher {
        ProviderHook fetch(boolean z, IInterface iInterface);
    }

    protected void processArgs(Method method, Object... objArr) {
    }

    static {
        PROVIDER_MAP.put("settings", new HookFetcher() { // from class: com.lody.virtual.client.hook.providers.ProviderHook.1
            @Override // com.lody.virtual.client.hook.providers.ProviderHook.HookFetcher
            public ProviderHook fetch(boolean z, IInterface iInterface) {
                return new SettingsProviderHook(iInterface);
            }
        });
        PROVIDER_MAP.put("downloads", new HookFetcher() { // from class: com.lody.virtual.client.hook.providers.ProviderHook.2
            @Override // com.lody.virtual.client.hook.providers.ProviderHook.HookFetcher
            public ProviderHook fetch(boolean z, IInterface iInterface) {
                return new DownloadProviderHook(iInterface);
            }
        });
        PROVIDER_MAP.put("com.android.badge", new HookFetcher() { // from class: com.lody.virtual.client.hook.providers.ProviderHook.3
            @Override // com.lody.virtual.client.hook.providers.ProviderHook.HookFetcher
            public ProviderHook fetch(boolean z, IInterface iInterface) {
                return new BadgeProviderHook(iInterface);
            }
        });
        PROVIDER_MAP.put("com.huawei.android.launcher.settings", new HookFetcher() { // from class: com.lody.virtual.client.hook.providers.ProviderHook.4
            @Override // com.lody.virtual.client.hook.providers.ProviderHook.HookFetcher
            public ProviderHook fetch(boolean z, IInterface iInterface) {
                return new BadgeProviderHook(iInterface);
            }
        });
    }

    public ProviderHook(Object obj) {
        this.mBase = obj;
    }

    private static HookFetcher fetchHook(String str) {
        HookFetcher hookFetcher = PROVIDER_MAP.get(str);
        return hookFetcher == null ? new HookFetcher() { // from class: com.lody.virtual.client.hook.providers.ProviderHook.5
            @Override // com.lody.virtual.client.hook.providers.ProviderHook.HookFetcher
            public ProviderHook fetch(boolean z, IInterface iInterface) {
                if (z) {
                    return new ExternalProviderHook(iInterface);
                }
                return new InternalProviderHook(iInterface);
            }
        } : hookFetcher;
    }

    private static IInterface createProxy(IInterface iInterface, ProviderHook providerHook) {
        if (iInterface == null || providerHook == null) {
            return null;
        }
        return (IInterface) Proxy.newProxyInstance(iInterface.getClass().getClassLoader(), new Class[]{IContentProvider.TYPE}, providerHook);
    }

    public static IInterface createProxy(boolean z, String str, IInterface iInterface) {
        IInterface createProxy;
        if ((iInterface instanceof Proxy) && (Proxy.getInvocationHandler(iInterface) instanceof ProviderHook)) {
            return iInterface;
        }
        HookFetcher fetchHook = fetchHook(str);
        return (fetchHook == null || (createProxy = createProxy(iInterface, fetchHook.fetch(z, iInterface))) == null) ? iInterface : createProxy;
    }

    public Bundle call(MethodBox methodBox, String str, String str2, Bundle bundle) throws InvocationTargetException {
        return (Bundle) methodBox.call();
    }

    public Uri insert(MethodBox methodBox, Uri uri, ContentValues contentValues) throws InvocationTargetException {
        return (Uri) methodBox.call();
    }

    public Cursor query(MethodBox methodBox, Uri uri, String[] strArr, String str, String[] strArr2, String str2, Bundle bundle) throws InvocationTargetException {
        return (Cursor) methodBox.call();
    }

    public String getType(MethodBox methodBox, Uri uri) throws InvocationTargetException {
        return (String) methodBox.call();
    }

    public int bulkInsert(MethodBox methodBox, Uri uri, ContentValues[] contentValuesArr) throws InvocationTargetException {
        return ((Integer) methodBox.call()).intValue();
    }

    public int delete(MethodBox methodBox, Uri uri, String str, String[] strArr) throws InvocationTargetException {
        return ((Integer) methodBox.call()).intValue();
    }

    public int update(MethodBox methodBox, Uri uri, ContentValues contentValues, String str, String[] strArr) throws InvocationTargetException {
        return ((Integer) methodBox.call()).intValue();
    }

    public ParcelFileDescriptor openFile(MethodBox methodBox, Uri uri, String str) throws InvocationTargetException {
        return (ParcelFileDescriptor) methodBox.call();
    }

    public AssetFileDescriptor openAssetFile(MethodBox methodBox, Uri uri, String str) throws InvocationTargetException {
        return (AssetFileDescriptor) methodBox.call();
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object... objArr) throws Throwable {
        Bundle bundle;
        String str;
        String[] strArr;
        try {
            processArgs(method, objArr);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        MethodBox methodBox = new MethodBox(method, this.mBase, objArr);
        int i = Build.VERSION.SDK_INT >= 18 ? 1 : 0;
        try {
            String name = method.getName();
            if (NotificationCompat.CATEGORY_CALL.equals(name)) {
                if (BuildCompat.isQ()) {
                    i = 2;
                }
                return call(methodBox, (String) objArr[i], (String) objArr[i + 1], (Bundle) objArr[i + 2]);
            } else if ("insert".equals(name)) {
                return insert(methodBox, (Uri) objArr[i], (ContentValues) objArr[i + 1]);
            } else {
                if ("getType".equals(name)) {
                    return getType(methodBox, (Uri) objArr[0]);
                }
                if (HotDeploymentTool.ACTION_DELETE.equals(name)) {
                    return Integer.valueOf(delete(methodBox, (Uri) objArr[i], (String) objArr[i + 1], (String[]) objArr[i + 2]));
                }
                if ("bulkInsert".equals(name)) {
                    return Integer.valueOf(bulkInsert(methodBox, (Uri) objArr[i], (ContentValues[]) objArr[i + 1]));
                }
                if ("update".equals(name)) {
                    return Integer.valueOf(update(methodBox, (Uri) objArr[i], (ContentValues) objArr[i + 1], (String) objArr[i + 2], (String[]) objArr[i + 3]));
                }
                if ("openFile".equals(name)) {
                    return openFile(methodBox, (Uri) objArr[i], (String) objArr[i + 1]);
                }
                if ("openAssetFile".equals(name)) {
                    return openAssetFile(methodBox, (Uri) objArr[i], (String) objArr[i + 1]);
                }
                if (!"query".equals(name)) {
                    return methodBox.call();
                }
                Uri uri = (Uri) objArr[i];
                String[] strArr2 = (String[]) objArr[i + 1];
                String str2 = null;
                if (BuildCompat.isOreo()) {
                    Bundle bundle2 = (Bundle) objArr[i + 2];
                    if (bundle2 != null) {
                        String string = bundle2.getString(QUERY_ARG_SQL_SELECTION);
                        String[] stringArray = bundle2.getStringArray(QUERY_ARG_SQL_SELECTION_ARGS);
                        bundle = bundle2;
                        str = bundle2.getString(QUERY_ARG_SQL_SORT_ORDER);
                        strArr = stringArray;
                        str2 = string;
                    } else {
                        bundle = bundle2;
                        strArr = null;
                        str = null;
                    }
                } else {
                    str2 = (String) objArr[i + 2];
                    strArr = (String[]) objArr[i + 3];
                    str = (String) objArr[i + 4];
                    bundle = null;
                }
                return query(methodBox, uri, strArr2, str2, strArr, str, bundle);
            }
        } catch (Throwable th2) {
            VLog.m18986w("ProviderHook", "call: %s (%s) with error", method.getName(), Arrays.toString(objArr));
            if (th2 instanceof InvocationTargetException) {
                throw th2.getCause();
            }
            throw th2;
        }
    }
}
