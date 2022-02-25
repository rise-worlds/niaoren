package com.lody.virtual.client.hook.proxies.window;

import android.os.Build;
import com.lody.virtual.client.hook.annotations.Inject;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.StaticMethodProxy;
import p110z1.Display;
import p110z1.IWindowManager;
import p110z1.PhoneWindow;
import p110z1.WindowManagerGlobal;

@Inject(MethodProxies.class)
/* loaded from: classes.dex */
public class WindowManagerStub extends BinderInvocationProxy {
    public WindowManagerStub() {
        super(IWindowManager.C5187a.asInterface, "window");
    }

    @Override // com.lody.virtual.client.hook.base.BinderInvocationProxy, com.lody.virtual.client.hook.base.MethodInvocationProxy, com.lody.virtual.client.interfaces.IInjector
    public void inject() throws Throwable {
        super.inject();
        if (Build.VERSION.SDK_INT >= 17) {
            if (WindowManagerGlobal.sWindowManagerService != null) {
                WindowManagerGlobal.sWindowManagerService.set(getInvocationStub().getProxyInterface());
            }
        } else if (Display.sWindowManager != null) {
            Display.sWindowManager.set(getInvocationStub().getProxyInterface());
        }
        if (PhoneWindow.TYPE != null) {
            PhoneWindow.sWindowManager.set(getInvocationStub().getProxyInterface());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new StaticMethodProxy("addAppToken"));
        addMethodProxy(new StaticMethodProxy("setScreenCaptureDisabled"));
    }
}
