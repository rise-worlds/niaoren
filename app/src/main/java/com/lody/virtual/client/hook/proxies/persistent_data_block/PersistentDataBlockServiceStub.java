package com.lody.virtual.client.hook.proxies.persistent_data_block;

import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.ResultStaticMethodProxy;
import p110z1.IPersistentDataBlockService;

/* loaded from: classes.dex */
public class PersistentDataBlockServiceStub extends BinderInvocationProxy {
    public PersistentDataBlockServiceStub() {
        super(IPersistentDataBlockService.C5183a.TYPE, "persistent_data_block");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new ResultStaticMethodProxy("write", -1));
        addMethodProxy(new ResultStaticMethodProxy("read", new byte[0]));
        addMethodProxy(new ResultStaticMethodProxy("wipe", null));
        addMethodProxy(new ResultStaticMethodProxy("getDataBlockSize", 0));
        addMethodProxy(new ResultStaticMethodProxy("getMaximumDataBlockSize", 0));
        addMethodProxy(new ResultStaticMethodProxy("setOemUnlockEnabled", 0));
        addMethodProxy(new ResultStaticMethodProxy("getOemUnlockEnabled", false));
    }
}
