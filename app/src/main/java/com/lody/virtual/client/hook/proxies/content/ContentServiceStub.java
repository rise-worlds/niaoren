package com.lody.virtual.client.hook.proxies.content;

import com.lody.virtual.client.hook.annotations.Inject;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import p110z1.ContentResolver;
import p110z1.IContentService;

@Inject(MethodProxies.class)
/* loaded from: classes.dex */
public class ContentServiceStub extends BinderInvocationProxy {
    private static final String TAG = "ContentServiceStub";

    public ContentServiceStub() {
        super(IContentService.C5135a.asInterface, ServiceManagerNative.CONTENT);
    }

    @Override // com.lody.virtual.client.hook.base.BinderInvocationProxy, com.lody.virtual.client.hook.base.MethodInvocationProxy, com.lody.virtual.client.interfaces.IInjector
    public void inject() throws Throwable {
        super.inject();
        ContentResolver.sContentService.set(getInvocationStub().getProxyInterface());
    }
}
