package com.lody.virtual.client.hook.providers;

import android.os.Build;
import android.os.Bundle;
import com.lody.virtual.client.VClient;
import com.lody.virtual.client.hook.base.MethodBox;
import com.lody.virtual.remote.VDeviceConfig;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.cookie.ClientCookie;
import org.apache.tools.ant.types.selectors.SizeSelector;

/* loaded from: classes.dex */
public class SettingsProviderHook extends ExternalProviderHook {
    private static final int METHOD_GET = 0;
    private static final int METHOD_PUT = 1;
    private static final Map<String, String> PRE_SET_VALUES = new HashMap();
    private static final String TAG = "SettingsProviderHook";

    static {
        PRE_SET_VALUES.put("user_setup_complete", "1");
        PRE_SET_VALUES.put("install_non_market_apps", "1");
    }

    public SettingsProviderHook(Object obj) {
        super(obj);
    }

    private static int getMethodType(String str) {
        if (str.startsWith("GET_")) {
            return 0;
        }
        return str.startsWith("PUT_") ? 1 : -1;
    }

    private static boolean isSecureMethod(String str) {
        return str.endsWith(ClientCookie.SECURE_ATTR);
    }

    @Override // com.lody.virtual.client.hook.providers.ProviderHook
    public Bundle call(MethodBox methodBox, String str, String str2, Bundle bundle) throws InvocationTargetException {
        if (!VClient.get().isAppRunning()) {
            return (Bundle) methodBox.call();
        }
        int methodType = getMethodType(str);
        if (methodType == 0) {
            String str3 = PRE_SET_VALUES.get(str2);
            if (str3 != null) {
                return wrapBundle(str2, str3);
            }
            if ("android_id".equals(str2)) {
                VDeviceConfig deviceConfig = VClient.get().getDeviceConfig();
                if (deviceConfig.enable && deviceConfig.androidId != null) {
                    return wrapBundle("android_id", deviceConfig.androidId);
                }
            }
        }
        if (1 == methodType && isSecureMethod(str)) {
            return null;
        }
        try {
            return (Bundle) methodBox.call();
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof SecurityException) {
                return null;
            }
            throw e;
        }
    }

    private Bundle wrapBundle(String str, String str2) {
        Bundle bundle = new Bundle();
        if (Build.VERSION.SDK_INT >= 24) {
            bundle.putString("name", str);
            bundle.putString(SizeSelector.SIZE_KEY, str2);
        } else {
            bundle.putString(str, str2);
        }
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.providers.ExternalProviderHook, com.lody.virtual.client.hook.providers.ProviderHook
    public void processArgs(Method method, Object... objArr) {
        super.processArgs(method, objArr);
    }
}
