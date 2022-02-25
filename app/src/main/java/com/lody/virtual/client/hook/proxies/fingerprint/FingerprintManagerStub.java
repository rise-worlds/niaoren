package com.lody.virtual.client.hook.proxies.fingerprint;

import android.annotation.TargetApi;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.ReplaceLastPkgMethodProxy;
import p110z1.IFingerprintService;

@TargetApi(23)
/* loaded from: classes.dex */
public class FingerprintManagerStub extends BinderInvocationProxy {
    public FingerprintManagerStub() {
        super(IFingerprintService.C5152a.asInterface, "fingerprint");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        addMethodProxy(new ReplaceLastPkgMethodProxy("isHardwareDetected"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("hasEnrolledFingerprints"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("authenticate"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("cancelAuthentication"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("getEnrolledFingerprints"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("getAuthenticatorId"));
    }
}
