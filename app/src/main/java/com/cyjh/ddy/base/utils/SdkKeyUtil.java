package com.cyjh.ddy.base.utils;

import android.text.TextUtils;
import com.blankj.utilcode.util.MetaDataUtils;
import com.cyjh.ddy.base.p033a.NoProGuard;

/* loaded from: classes.dex */
public class SdkKeyUtil implements NoProGuard {

    /* renamed from: a */
    private String f7108a;

    /* renamed from: b */
    private String f7109b;

    /* renamed from: c */
    private String f7110c;

    /* renamed from: d */
    private boolean f7111d;

    /* renamed from: e */
    private boolean f7112e;

    /* renamed from: f */
    private boolean f7113f;

    public void setSdkKey(String str) {
        this.f7108a = str;
    }

    public String getSdkKey() {
        if (TextUtils.isEmpty(this.f7108a)) {
            return MetaDataUtils.m23661a("DDY_SDK_APPKEY");
        }
        return this.f7108a;
    }

    public String getSdkType() {
        if (TextUtils.isEmpty(this.f7109b)) {
            return MetaDataUtils.m23661a("DDY_SDK_TYPE");
        }
        return this.f7109b;
    }

    public void setSdkType(String str) {
        this.f7109b = str;
        CLog.m21882i("sdk-KeyUtil", "setSdkType " + str);
    }

    public void setSdkUcid(String str) {
        this.f7110c = str;
    }

    private SdkKeyUtil() {
        this.f7113f = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class LazyHolder {

        /* renamed from: a */
        private static final SdkKeyUtil f7114a = new SdkKeyUtil();

        private LazyHolder() {
        }
    }

    public static SdkKeyUtil getInstance() {
        return LazyHolder.f7114a;
    }

    public boolean isEnableD310() {
        return this.f7111d;
    }

    public void setEnableD310(boolean z) {
        this.f7111d = z;
    }

    public boolean isPush() {
        return this.f7112e;
    }

    public void setPush(boolean z) {
        this.f7112e = z;
    }

    public boolean isHardCodeH264() {
        return this.f7113f;
    }

    public void setHardCodeH264(boolean z) {
        this.f7113f = z;
    }
}
