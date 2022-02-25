package com.lody.virtual.client.hook.proxies.imms;

import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.ReplaceCallingPkgMethodProxy;
import com.lody.virtual.client.hook.base.ReplaceSpecPkgMethodProxy;
import p110z1.IMms;

/* loaded from: classes.dex */
public class MmsStub extends BinderInvocationProxy {
    public MmsStub() {
        super(IMms.C5204a.asInterface, "imms");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        addMethodProxy(new ReplaceSpecPkgMethodProxy("sendMessage", 1));
        addMethodProxy(new ReplaceSpecPkgMethodProxy("downloadMessage", 1));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("importTextMessage"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("importMultimediaMessage"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("deleteStoredMessage"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("deleteStoredConversation"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("updateStoredMessageStatus"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("archiveStoredConversation"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("addTextMessageDraft"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("addMultimediaMessageDraft"));
        addMethodProxy(new ReplaceSpecPkgMethodProxy("sendStoredMessage", 1));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("setAutoPersisting"));
    }
}
