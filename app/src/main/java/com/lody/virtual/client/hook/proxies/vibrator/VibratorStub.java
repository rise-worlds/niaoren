package com.lody.virtual.client.hook.proxies.vibrator;

import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.ReplaceCallingPkgMethodProxy;
import java.lang.reflect.Method;
import p110z1.IVibratorService;

/* loaded from: classes.dex */
public class VibratorStub extends BinderInvocationProxy {
    public VibratorStub() {
        super(IVibratorService.C5200a.asInterface, "vibrator");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        addMethodProxy(new VibrateMethodProxy("vibrateMagnitude"));
        addMethodProxy(new VibrateMethodProxy("vibratePatternMagnitude"));
        addMethodProxy(new VibrateMethodProxy("vibrate"));
        addMethodProxy(new VibrateMethodProxy("vibratePattern"));
    }

    /* loaded from: classes.dex */
    private static final class VibrateMethodProxy extends ReplaceCallingPkgMethodProxy {
        private VibrateMethodProxy(String str) {
            super(str);
        }

        @Override // com.lody.virtual.client.hook.base.ReplaceCallingPkgMethodProxy, com.lody.virtual.client.hook.base.MethodProxy
        public boolean beforeCall(Object obj, Method method, Object... objArr) {
            if (objArr[0] instanceof Integer) {
                objArr[0] = Integer.valueOf(getRealUid());
            }
            return super.beforeCall(obj, method, objArr);
        }
    }
}
