package com.lody.virtual.client.hook.proxies.restriction;

import android.annotation.TargetApi;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.ReplaceCallingPkgMethodProxy;
import p110z1.IRestrictionsManager;

@TargetApi(21)
/* loaded from: classes.dex */
public class RestrictionStub extends BinderInvocationProxy {
    public RestrictionStub() {
        super(IRestrictionsManager.C5136a.asInterface, "restrictions");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getApplicationRestrictions"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("notifyPermissionResponse"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("requestPermission"));
    }
}
