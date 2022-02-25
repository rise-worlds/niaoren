package com.lody.virtual.client.hook.proxies.telecom;

import android.annotation.TargetApi;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.ReplaceCallingPkgMethodProxy;
import p110z1.ITelecomService;

@TargetApi(21)
/* loaded from: classes.dex */
public class TelecomManagerStub extends BinderInvocationProxy {
    public TelecomManagerStub() {
        super(ITelecomService.C5202a.TYPE, "telecom");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new ReplaceCallingPkgMethodProxy("showInCallScreen"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getDefaultOutgoingPhoneAccount"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getCallCapablePhoneAccounts"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getSelfManagedPhoneAccounts"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getPhoneAccountsSupportingScheme"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("isVoiceMailNumber"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getVoiceMailNumber"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getLine1Number"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("silenceRinger"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("isInCall"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("isInManagedCall"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("isRinging"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("acceptRingingCall"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("acceptRingingCallWithVideoState("));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("cancelMissedCallsNotification"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("handlePinMmi"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("handlePinMmiForPhoneAccount"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getAdnUriForPhoneAccount"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("isTtySupported"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getCurrentTtyMode"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("placeCall"));
    }
}
