package com.lody.virtual.client.stub;

import java.util.Locale;

/* loaded from: classes.dex */
public class StubManifest {
    public static String PACKAGE_NAME;
    public static String PACKAGE_NAME_64BIT;
    public static String STUB_ACTIVITY = ShadowActivity.class.getName();
    public static String STUB_DIALOG = ShadowDialogActivity.class.getName();
    public static String STUB_CP = ShadowContentProvider.class.getName();
    public static String STUB_JOB = ShadowJobService.class.getName();
    public static String STUB_SERVICE = ShadowService.class.getName();
    public static String RESOLVER_ACTIVITY = ResolverActivity.class.getName();
    public static String STUB_CP_AUTHORITY = null;
    public static String STUB_CP_AUTHORITY_64BIT = null;
    public static String PROXY_CP_AUTHORITY = null;
    public static String PROXY_CP_AUTHORITY_64BIT = null;
    public static int STUB_COUNT = 100;
    public static String[] PRIVILEGE_APPS = new String[0];
    public static final String[] REQUIRED_FRAMEWORK = {"com.android.location.provider"};

    public static String getStubActivityName(int i) {
        return String.format(Locale.ENGLISH, "%s$P%d", STUB_ACTIVITY, Integer.valueOf(i));
    }

    public static String getStubDialogName(int i) {
        return String.format(Locale.ENGLISH, "%s$P%d", STUB_DIALOG, Integer.valueOf(i));
    }

    public static String getStubContentProviderName(int i) {
        return String.format(Locale.ENGLISH, "%s$P%d", STUB_CP, Integer.valueOf(i));
    }

    public static String getStubServiceName(int i) {
        return String.format(Locale.ENGLISH, "%s$P%d", STUB_SERVICE, Integer.valueOf(i));
    }

    public static String getStubAuthority(int i, boolean z) {
        Locale locale = Locale.ENGLISH;
        Object[] objArr = new Object[2];
        objArr[0] = z ? STUB_CP_AUTHORITY_64BIT : STUB_CP_AUTHORITY;
        objArr[1] = Integer.valueOf(i);
        return String.format(locale, "%s%d", objArr);
    }

    public static String getProxyAuthority(boolean z) {
        return z ? PROXY_CP_AUTHORITY_64BIT : PROXY_CP_AUTHORITY;
    }

    public static String getStubPackageName(boolean z) {
        return z ? PACKAGE_NAME_64BIT : PACKAGE_NAME;
    }

    public static boolean isHostPackageName(String str) {
        return PACKAGE_NAME.equals(str) || PACKAGE_NAME_64BIT.equals(str);
    }

    public static boolean is64bitPackageName(String str) {
        String str2 = PACKAGE_NAME_64BIT;
        if (str2 == null) {
            return false;
        }
        return str2.equals(str);
    }
}
