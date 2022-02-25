package com.cyjh.ddy.base.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import java.text.SimpleDateFormat;
import java.util.Date;

/* renamed from: com.cyjh.ddy.base.utils.v */
/* loaded from: classes.dex */
public class WSUtils {

    /* renamed from: a */
    public static final String f7153a = "yyyy-MM-dd HH:mm:ss";

    /* renamed from: a */
    public static DisplayMetrics m21714a(Context context) {
        return context.getResources().getDisplayMetrics();
    }

    /* renamed from: b */
    public static int m21712b(Context context) {
        return m21714a(context).widthPixels;
    }

    /* renamed from: c */
    public static int m21711c(Context context) {
        return m21714a(context).heightPixels;
    }

    /* renamed from: a */
    public static String m21713a(Date date, String str) {
        return new SimpleDateFormat(str).format(date);
    }
}
