package com.cyjh.ddy.base.utils;

import android.text.TextUtils;

/* renamed from: com.cyjh.ddy.base.utils.p */
/* loaded from: classes.dex */
public class SdkUtils {
    /* renamed from: a */
    public static boolean m21761a() {
        String sdkKey = SdkKeyUtil.getInstance().getSdkKey();
        return !TextUtils.isEmpty(sdkKey) && !sdkKey.equals("null");
    }

    /* renamed from: b */
    public static boolean m21760b() {
        if (!m21761a()) {
            return false;
        }
        String sdkType = SdkKeyUtil.getInstance().getSdkType();
        if (TextUtils.isEmpty(sdkType)) {
            return false;
        }
        return sdkType.startsWith("yungame");
    }
}
