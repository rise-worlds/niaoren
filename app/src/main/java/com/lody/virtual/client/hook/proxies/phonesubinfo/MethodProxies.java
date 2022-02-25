package com.lody.virtual.client.hook.proxies.phonesubinfo;

import android.text.TextUtils;
import com.lody.virtual.client.hook.base.ReplaceLastPkgMethodProxy;
import com.lody.virtual.remote.VDeviceConfig;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
class MethodProxies {
    MethodProxies() {
    }

    /* loaded from: classes.dex */
    static class GetDeviceId extends ReplaceLastPkgMethodProxy {
        public GetDeviceId() {
            super("getDeviceId");
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            VDeviceConfig deviceConfig = getDeviceConfig();
            if (deviceConfig.enable) {
                String str = deviceConfig.deviceId;
                if (!TextUtils.isEmpty(str)) {
                    return str;
                }
            }
            return super.call(obj, method, objArr);
        }
    }

    /* loaded from: classes.dex */
    static class GetDeviceIdForPhone extends GetDeviceId {
        @Override // com.lody.virtual.client.hook.base.StaticMethodProxy, com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getDeviceIdForPhone";
        }

        GetDeviceIdForPhone() {
        }
    }

    /* loaded from: classes.dex */
    static class GetDeviceIdForSubscriber extends GetDeviceId {
        @Override // com.lody.virtual.client.hook.base.StaticMethodProxy, com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getDeviceIdForSubscriber";
        }

        GetDeviceIdForSubscriber() {
        }
    }

    /* loaded from: classes.dex */
    static class GetImeiForSubscriber extends GetDeviceId {
        @Override // com.lody.virtual.client.hook.base.StaticMethodProxy, com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getImeiForSubscriber";
        }

        GetImeiForSubscriber() {
        }
    }

    /* loaded from: classes.dex */
    static class GetIccSerialNumber extends ReplaceLastPkgMethodProxy {
        public GetIccSerialNumber() {
            super("getIccSerialNumber");
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            if (getDeviceConfig().enable) {
                String str = getDeviceConfig().iccId;
                if (!TextUtils.isEmpty(str)) {
                    return str;
                }
            }
            return super.call(obj, method, objArr);
        }
    }

    /* loaded from: classes.dex */
    static class GetIccSerialNumberForSubscriber extends GetIccSerialNumber {
        @Override // com.lody.virtual.client.hook.base.StaticMethodProxy, com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getIccSerialNumberForSubscriber";
        }

        GetIccSerialNumberForSubscriber() {
        }
    }
}
