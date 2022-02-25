package com.nrzs.libcommon.util.share;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;

/* renamed from: com.nrzs.libcommon.util.share.b */
/* loaded from: classes2.dex */
public class PreferenceUtil {

    /* renamed from: a */
    public static final String f11207a = "method_contain_key";

    /* renamed from: b */
    public static String f11208b = null;

    /* renamed from: c */
    public static Uri f11209c = null;

    /* renamed from: d */
    public static final String f11210d = "method_query_value";

    /* renamed from: e */
    public static final String f11211e = "method_edit";

    /* renamed from: f */
    public static final String f11212f = "method_query_pid";

    /* renamed from: g */
    public static final String f11213g = "key_result";

    /* renamed from: a */
    public static void m18517a(Context context) {
        String packageName = context.getPackageName();
        if (packageName.endsWith(".addon.arm64")) {
            f11208b = packageName.substring(0, packageName.indexOf(".addon.arm64")) + ".preference";
        } else {
            f11208b = context.getPackageName() + ".preference";
        }
        f11209c = Uri.parse("content://" + f11208b);
    }

    /* renamed from: a */
    public static SharedPreferences m18516a(@NonNull Context context, String str) {
        return SharedPreferenceProxy.m18515a(context, str);
    }
}
