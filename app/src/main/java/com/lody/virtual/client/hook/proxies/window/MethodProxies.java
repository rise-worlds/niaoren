package com.lody.virtual.client.hook.proxies.window;

import android.os.IInterface;
import com.lody.virtual.client.hook.base.MethodProxy;
import com.lody.virtual.client.hook.proxies.window.session.WindowSessionPatch;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
class MethodProxies {
    MethodProxies() {
    }

    /* loaded from: classes.dex */
    static class OpenSession extends BasePatchSession {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "openSession";
        }

        OpenSession() {
        }
    }

    /* loaded from: classes.dex */
    static class OverridePendingAppTransition extends BasePatchSession {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "overridePendingAppTransition";
        }

        OverridePendingAppTransition() {
        }

        @Override // com.lody.virtual.client.hook.proxies.window.MethodProxies.BasePatchSession, com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            if (objArr[0] instanceof String) {
                objArr[0] = getHostPkg();
            }
            return super.call(obj, method, objArr);
        }
    }

    /* loaded from: classes.dex */
    static class OverridePendingAppTransitionInPlace extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "overridePendingAppTransitionInPlace";
        }

        OverridePendingAppTransitionInPlace() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            if (objArr[0] instanceof String) {
                objArr[0] = getHostPkg();
            }
            return method.invoke(obj, objArr);
        }
    }

    /* loaded from: classes.dex */
    static class SetAppStartingWindow extends BasePatchSession {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "setAppStartingWindow";
        }

        SetAppStartingWindow() {
        }
    }

    /* loaded from: classes.dex */
    static abstract class BasePatchSession extends MethodProxy {
        BasePatchSession() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            Object invoke = method.invoke(obj, objArr);
            return invoke instanceof IInterface ? proxySession((IInterface) invoke) : invoke;
        }

        private Object proxySession(IInterface iInterface) {
            return new WindowSessionPatch(iInterface).getInvocationStub().getProxyInterface();
        }
    }
}
