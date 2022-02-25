package com.lody.virtual.client.hook.providers;

import com.lody.virtual.client.core.VirtualCore;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class ExternalProviderHook extends ProviderHook {
    public ExternalProviderHook(Object obj) {
        super(obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.providers.ProviderHook
    public void processArgs(Method method, Object... objArr) {
        if (objArr != null && objArr.length > 0 && (objArr[0] instanceof String)) {
            if (VirtualCore.get().isAppInstalled((String) objArr[0])) {
                objArr[0] = VirtualCore.get().getHostPkg();
            }
        }
    }
}
