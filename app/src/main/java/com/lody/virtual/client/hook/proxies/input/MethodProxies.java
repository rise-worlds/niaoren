package com.lody.virtual.client.hook.proxies.input;

import android.view.inputmethod.EditorInfo;
import com.lody.virtual.client.hook.base.MethodProxy;
import com.lody.virtual.helper.utils.ArrayUtils;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
class MethodProxies {
    MethodProxies() {
    }

    /* loaded from: classes.dex */
    static class StartInput extends StartInputOrWindowGainedFocus {
        @Override // com.lody.virtual.client.hook.proxies.input.MethodProxies.StartInputOrWindowGainedFocus, com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "startInput";
        }

        StartInput() {
        }
    }

    /* loaded from: classes.dex */
    static class WindowGainedFocus extends StartInputOrWindowGainedFocus {
        @Override // com.lody.virtual.client.hook.proxies.input.MethodProxies.StartInputOrWindowGainedFocus, com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "windowGainedFocus";
        }

        WindowGainedFocus() {
        }
    }

    /* loaded from: classes.dex */
    static class StartInputOrWindowGainedFocus extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "startInputOrWindowGainedFocus";
        }

        StartInputOrWindowGainedFocus() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            int indexOfFirst = ArrayUtils.indexOfFirst(objArr, EditorInfo.class);
            if (indexOfFirst != -1) {
                ((EditorInfo) objArr[indexOfFirst]).packageName = getHostPkg();
            }
            return method.invoke(obj, objArr);
        }
    }
}
