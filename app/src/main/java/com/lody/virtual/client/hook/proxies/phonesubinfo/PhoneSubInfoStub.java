package com.lody.virtual.client.hook.proxies.phonesubinfo;

import com.lody.virtual.client.hook.annotations.Inject;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.ReplaceCallingPkgMethodProxy;
import com.lody.virtual.client.hook.base.ReplaceLastPkgMethodProxy;
import p110z1.IPhoneSubInfo;

@Inject(MethodProxies.class)
/* loaded from: classes.dex */
public class PhoneSubInfoStub extends BinderInvocationProxy {
    public PhoneSubInfoStub() {
        super(IPhoneSubInfo.C5205a.asInterface, "iphonesubinfo");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new ReplaceLastPkgMethodProxy("getNaiForSubscriber"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getDeviceSvn"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("getDeviceSvnUsingSubId"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getSubscriberId"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("getSubscriberIdForSubscriber"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getGroupIdLevel1"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("getGroupIdLevel1ForSubscriber"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getLine1AlphaTag"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("getLine1AlphaTagForSubscriber"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getMsisdn"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("getMsisdnForSubscriber"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getVoiceMailNumber"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("getVoiceMailNumberForSubscriber"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getVoiceMailAlphaTag"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("getVoiceMailAlphaTagForSubscriber"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getLine1Number"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("getLine1NumberForSubscriber"));
    }
}
