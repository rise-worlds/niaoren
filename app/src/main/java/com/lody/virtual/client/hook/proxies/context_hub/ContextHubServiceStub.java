package com.lody.virtual.client.hook.proxies.context_hub;

import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.ResultStaticMethodProxy;
import com.lody.virtual.helper.compat.BuildCompat;
import p110z1.IContextHubService;

/* loaded from: classes.dex */
public class ContextHubServiceStub extends BinderInvocationProxy {
    public ContextHubServiceStub() {
        super(IContextHubService.C5153a.asInterface, getServiceName());
    }

    private static String getServiceName() {
        return BuildCompat.isOreo() ? "contexthub" : "contexthub_service";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new ResultStaticMethodProxy("registerCallback", 0));
        addMethodProxy(new ResultStaticMethodProxy("getContextHubInfo", null));
        addMethodProxy(new ResultStaticMethodProxy("getContextHubHandles", new int[0]));
    }
}
