package com.lody.virtual.client.hook.proxies.graphics;

import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.ReplaceCallingPkgMethodProxy;
import p110z1.IGraphicsStats;

/* loaded from: classes.dex */
public class GraphicsStatsStub extends BinderInvocationProxy {
    public GraphicsStatsStub() {
        super(IGraphicsStats.C5186a.asInterface, "graphicsstats");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new ReplaceCallingPkgMethodProxy("requestBufferForProcess"));
    }
}
