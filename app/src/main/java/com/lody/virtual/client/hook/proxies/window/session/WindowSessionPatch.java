package com.lody.virtual.client.hook.proxies.window.session;

import android.graphics.Rect;
import android.graphics.Region;
import android.os.Build;
import android.os.IInterface;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.hook.base.MethodInvocationProxy;
import com.lody.virtual.client.hook.base.MethodInvocationStub;
import com.lody.virtual.helper.utils.Reflect;
import com.lody.virtual.helper.utils.VLog;
import java.lang.reflect.Method;
import p110z1.WindowManagerGlobal;

/* loaded from: classes.dex */
public class WindowSessionPatch extends MethodInvocationProxy<MethodInvocationStub<IInterface>> {
    private static final int ADD_PERMISSION_DENIED;

    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy, com.lody.virtual.client.interfaces.IInjector
    public void inject() throws Throwable {
    }

    static {
        ADD_PERMISSION_DENIED = WindowManagerGlobal.ADD_PERMISSION_DENIED != null ? WindowManagerGlobal.ADD_PERMISSION_DENIED.get() : -8;
    }

    public WindowSessionPatch(IInterface iInterface) {
        super(new MethodInvocationStub(iInterface));
    }

    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        addMethodProxy(new BaseMethodProxy("add"));
        addMethodProxy(new BaseMethodProxy("addToDisplay") { // from class: com.lody.virtual.client.hook.proxies.window.session.WindowSessionPatch.1
            @Override // com.lody.virtual.client.hook.base.MethodProxy
            public Object call(Object obj, Method method, Object... objArr) throws Throwable {
                if (isDrawOverlays() && VirtualCore.getConfig().isDisableDrawOverlays(getAppPkg())) {
                    return Integer.valueOf(WindowSessionPatch.ADD_PERMISSION_DENIED);
                }
                Object call = super.call(obj, method, objArr);
                if (Build.VERSION.SDK_INT >= 28) {
                    Reflect.m18998on(objArr[9]).set("mInner", Build.VERSION.SDK_INT == 28 ? Reflect.m18997on("android.view.DisplayCutout").create(new Rect(), new Region(), false).get() : Reflect.m18997on("android.view.DisplayCutout").create(new Rect(), new Rect(), new Rect(), new Rect(), new Rect(), false).get());
                    Rect rect = (Rect) objArr[5];
                    if (rect.right > rect.bottom) {
                        objArr[5] = new Rect(0, 0, (rect.bottom * 16) / 9, rect.bottom);
                    } else {
                        objArr[5] = new Rect(0, 0, rect.right, (rect.right * 16) / 9);
                    }
                    objArr[6] = new Rect();
                    objArr[7] = new Rect();
                    if (objArr.length >= 4) {
                        VLog.m18993d("sunya", "last args2=" + objArr[2] + " args3=" + objArr[3], new Object[0]);
                    }
                    if (objArr.length >= 6) {
                        VLog.m18993d("sunya", "last args4=" + objArr[4] + " args5=" + objArr[5], new Object[0]);
                    }
                    if (objArr.length >= 8) {
                        VLog.m18993d("sunya", "last args6=" + objArr[6] + " args7=" + objArr[7], new Object[0]);
                    }
                    if (objArr.length >= 11) {
                        VLog.m18993d("sunya", "last args8=" + objArr[8] + " args9=" + objArr[9] + " args10=" + objArr[10], new Object[0]);
                    }
                }
                return call;
            }
        });
        addMethodProxy(new BaseMethodProxy("addToDisplayWithoutInputChannel"));
        addMethodProxy(new BaseMethodProxy("addWithoutInputChannel"));
        addMethodProxy(new BaseMethodProxy("relayout"));
        addMethodProxy(new BaseMethodProxy("getDisplayFrame") { // from class: com.lody.virtual.client.hook.proxies.window.session.WindowSessionPatch.2
            @Override // com.lody.virtual.client.hook.base.MethodProxy
            public Object call(Object obj, Method method, Object... objArr) throws Throwable {
                Object call = super.call(obj, method, objArr);
                Rect rect = (Rect) objArr[1];
                if (rect.right > 0 && rect.bottom > 0) {
                    if (rect.right > rect.bottom) {
                        VLog.m18993d("sunya", "修改大小 16:9 右边减少 " + rect, new Object[0]);
                        rect.right = (rect.bottom * 16) / 9;
                    } else {
                        VLog.m18993d("sunya", "修改大小 16:9 下边减少 " + rect, new Object[0]);
                        rect.bottom = (rect.right * 16) / 9;
                    }
                }
                VLog.m18993d("sunya", "after getDisplayFrame args[1]=" + objArr[1], new Object[0]);
                return call;
            }
        });
    }

    @Override // com.lody.virtual.client.interfaces.IInjector
    public boolean isEnvBad() {
        return getInvocationStub().getProxyInterface() != null;
    }
}
