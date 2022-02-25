package com.lody.virtual.client.hook.proxies.media.router;

import android.annotation.TargetApi;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.ReplaceCallingPkgMethodProxy;
import p110z1.IMediaRouterService;

@TargetApi(16)
/* loaded from: classes.dex */
public class MediaRouterServiceStub extends BinderInvocationProxy {
    public MediaRouterServiceStub() {
        super(IMediaRouterService.C5164a.asInterface, "media_router");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new ReplaceCallingPkgMethodProxy("registerClientAsUser"));
    }
}
