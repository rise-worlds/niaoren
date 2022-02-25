package com.nrzs.base.router.provider;

import com.nrzs.base.router.RouterConstants;
import p110z1.ARouter;

/* loaded from: classes.dex */
public class ProviderFactory {
    public static PayProvider createPay() {
        return (PayProvider) create(RouterConstants.Provider.PROVIDER_TOWEB_PAY);
    }

    public static InvokeProvider createInvoke() {
        return (InvokeProvider) create(RouterConstants.Provider.PROVIDER_GAME);
    }

    public static PsySuccessProvider createSuccess() {
        return (PsySuccessProvider) create(RouterConstants.Provider.PROVIDER_PAY);
    }

    public static CarkeyProvider createCarkey() {
        return (CarkeyProvider) create(RouterConstants.Provider.PROVIDER_APP);
    }

    public static XNKJRunProvider createXNKJRun() {
        return (XNKJRunProvider) create(RouterConstants.Provider.PROVIDER_XNKJ_RUN);
    }

    public static Object create(String str) {
        return ARouter.m1714a().m1707a(str).navigation();
    }
}
