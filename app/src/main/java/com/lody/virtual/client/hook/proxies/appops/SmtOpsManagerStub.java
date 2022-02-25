package com.lody.virtual.client.hook.proxies.appops;

import android.annotation.TargetApi;
import com.lody.virtual.client.hook.annotations.Inject;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import p110z1.ISmtOpsService;

@Inject(MethodProxies.class)
@TargetApi(19)
/* loaded from: classes.dex */
public class SmtOpsManagerStub extends BinderInvocationProxy {
    public SmtOpsManagerStub() {
        super(ISmtOpsService.C5196a.asInterface, "smtops");
    }
}
