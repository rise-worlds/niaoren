package com.lody.virtual.client.hook.proxies.display;

import android.annotation.TargetApi;
import android.os.IInterface;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.hook.base.MethodInvocationProxy;
import com.lody.virtual.client.hook.base.MethodInvocationStub;
import com.lody.virtual.client.hook.base.MethodProxy;
import com.lody.virtual.client.hook.base.ReplaceCallingPkgMethodProxy;
import com.lody.virtual.helper.utils.Reflect;
import java.lang.reflect.Method;
import p110z1.DisplayManagerGlobal;
import p110z1.ShareVal;
import p110z1.apf;

@TargetApi(17)
/* loaded from: classes.dex */
public class DisplayStub extends MethodInvocationProxy<MethodInvocationStub<IInterface>> {
    public DisplayStub() {
        super(new MethodInvocationStub(DisplayManagerGlobal.mDm.get(DisplayManagerGlobal.getInstance.call(new Object[0]))));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new ReplaceCallingPkgMethodProxy("createVirtualDisplay"));
        addMethodProxy(new GetDisplayInfo());
    }

    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy, com.lody.virtual.client.interfaces.IInjector
    public void inject() throws Throwable {
        DisplayManagerGlobal.mDm.set(DisplayManagerGlobal.getInstance.call(new Object[0]), getInvocationStub().getProxyInterface());
    }

    @Override // com.lody.virtual.client.interfaces.IInjector
    public boolean isEnvBad() {
        return DisplayManagerGlobal.mDm.get(DisplayManagerGlobal.getInstance.call(new Object[0])) != getInvocationStub().getProxyInterface();
    }

    /* loaded from: classes.dex */
    public static class GetDisplayInfo extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getDisplayInfo";
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            Object call = super.call(obj, method, objArr);
            int i = 0;
            try {
                i = apf.m11838b(VirtualCore.get().getContext(), "common_shared_file", ShareVal.f16606p, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (i == 0) {
                ((Integer) Reflect.m18998on(call).get("logicalWidth")).intValue();
                ((Integer) Reflect.m18998on(call).get("logicalHeight")).intValue();
                int intValue = ((Integer) Reflect.m18998on(call).get("appWidth")).intValue();
                int intValue2 = ((Integer) Reflect.m18998on(call).get("appHeight")).intValue();
                if (intValue > intValue2) {
                    Reflect.m18998on(call).set("appWidth", Integer.valueOf((intValue2 * 16) / 9));
                } else {
                    Reflect.m18998on(call).set("appHeight", Integer.valueOf((intValue * 16) / 9));
                }
            }
            return call;
        }
    }
}
