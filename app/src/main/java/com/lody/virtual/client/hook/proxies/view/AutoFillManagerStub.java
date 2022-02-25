package com.lody.virtual.client.hook.proxies.view;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.os.IInterface;
import android.util.Log;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.ReplaceLastPkgMethodProxy;
import com.lody.virtual.client.hook.base.ReplaceLastUserIdMethodProxy;
import com.lody.virtual.helper.utils.ArrayUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import p110z1.IAutoFillManager;

/* loaded from: classes.dex */
public class AutoFillManagerStub extends BinderInvocationProxy {
    private static final String AUTO_FILL_NAME = "autofill";
    private static final String TAG = "AutoFillManagerStub";

    public AutoFillManagerStub() {
        super(IAutoFillManager.C5185a.asInterface, AUTO_FILL_NAME);
    }

    @Override // com.lody.virtual.client.hook.base.BinderInvocationProxy, com.lody.virtual.client.hook.base.MethodInvocationProxy, com.lody.virtual.client.interfaces.IInjector
    @SuppressLint({"WrongConstant"})
    public void inject() throws Throwable {
        super.inject();
        try {
            Object systemService = getContext().getSystemService(AUTO_FILL_NAME);
            if (systemService != null) {
                IInterface proxyInterface = getInvocationStub().getProxyInterface();
                if (proxyInterface != null) {
                    Field declaredField = systemService.getClass().getDeclaredField("mService");
                    declaredField.setAccessible(true);
                    declaredField.set(systemService, proxyInterface);
                    addMethodProxy(new ReplacePkgAndComponentProxy("startSession") { // from class: com.lody.virtual.client.hook.proxies.view.AutoFillManagerStub.1
                        @Override // com.lody.virtual.client.hook.base.MethodProxy
                        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
                            replaceFirstUserId(objArr);
                            return super.call(obj, method, objArr);
                        }
                    });
                    addMethodProxy(new ReplacePkgAndComponentProxy("updateOrRestartSession"));
                    addMethodProxy(new ReplaceLastPkgMethodProxy("isServiceEnabled"));
                    addMethodProxy(new ReplaceLastUserIdMethodProxy("addClient"));
                    addMethodProxy(new ReplaceLastUserIdMethodProxy("removeClient"));
                    addMethodProxy(new ReplaceLastUserIdMethodProxy("updateSession"));
                    addMethodProxy(new ReplaceLastUserIdMethodProxy("finishSession"));
                    addMethodProxy(new ReplaceLastUserIdMethodProxy("cancelSession"));
                    addMethodProxy(new ReplaceLastUserIdMethodProxy("setAuthenticationResult"));
                    addMethodProxy(new ReplaceLastUserIdMethodProxy("setHasCallback"));
                    addMethodProxy(new ReplaceLastUserIdMethodProxy("disableOwnedAutofillServices"));
                    addMethodProxy(new ReplaceLastUserIdMethodProxy("isServiceSupported"));
                    addMethodProxy(new ReplaceLastUserIdMethodProxy("isServiceEnabled"));
                    return;
                }
                throw new NullPointerException("AutoFillManagerProxy is null.");
            }
            throw new NullPointerException("AutoFillManagerInstance is null.");
        } catch (Throwable th) {
            Log.e(TAG, "AutoFillManagerStub inject error.", th);
        }
    }

    /* loaded from: classes.dex */
    static class ReplacePkgAndComponentProxy extends ReplaceLastPkgMethodProxy {
        ReplacePkgAndComponentProxy(String str) {
            super(str);
        }

        @Override // com.lody.virtual.client.hook.base.ReplaceLastPkgMethodProxy, com.lody.virtual.client.hook.base.MethodProxy
        public boolean beforeCall(Object obj, Method method, Object... objArr) {
            replaceLastAppComponent(objArr, getHostPkg());
            return super.beforeCall(obj, method, objArr);
        }

        private void replaceLastAppComponent(Object[] objArr, String str) {
            int indexOfLast = ArrayUtils.indexOfLast(objArr, ComponentName.class);
            if (indexOfLast != -1) {
                objArr[indexOfLast] = new ComponentName(str, ((ComponentName) objArr[indexOfLast]).getClassName());
            }
        }
    }

    public static void disableAutoFill(Object obj) {
        try {
            if (obj != null) {
                Field declaredField = obj.getClass().getDeclaredField("mService");
                declaredField.setAccessible(true);
                declaredField.set(obj, null);
                return;
            }
            throw new NullPointerException("AutoFillManagerInstance is null.");
        } catch (Throwable th) {
            Log.e(TAG, "AutoFillManagerStub inject error.", th);
        }
    }
}
