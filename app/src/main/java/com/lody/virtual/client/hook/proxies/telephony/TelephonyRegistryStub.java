package com.lody.virtual.client.hook.proxies.telephony;

import android.os.Build;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.ReplaceCallingPkgMethodProxy;
import com.lody.virtual.client.hook.base.ReplaceSequencePkgMethodProxy;
import java.lang.reflect.Method;
import p110z1.ITelephonyRegistry;

/* loaded from: classes.dex */
public class TelephonyRegistryStub extends BinderInvocationProxy {
    public TelephonyRegistryStub() {
        super(ITelephonyRegistry.C5209a.asInterface, "telephony.registry");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new ReplaceCallingPkgMethodProxy("listen"));
        addMethodProxy(new ReplaceSequencePkgMethodProxy("listenForSubscriber", 1) { // from class: com.lody.virtual.client.hook.proxies.telephony.TelephonyRegistryStub.1
            @Override // com.lody.virtual.client.hook.base.ReplaceSequencePkgMethodProxy, com.lody.virtual.client.hook.base.MethodProxy
            public boolean beforeCall(Object obj, Method method, Object... objArr) {
                if (Build.VERSION.SDK_INT >= 17 && isFakeLocationEnable()) {
                    int length = objArr.length - 1;
                    while (true) {
                        if (length <= 0) {
                            break;
                        } else if (objArr[length] instanceof Integer) {
                            objArr[length] = Integer.valueOf((((Integer) objArr[length]).intValue() ^ 1024) ^ 16);
                            break;
                        } else {
                            length--;
                        }
                    }
                }
                return super.beforeCall(obj, method, objArr);
            }
        });
    }
}
