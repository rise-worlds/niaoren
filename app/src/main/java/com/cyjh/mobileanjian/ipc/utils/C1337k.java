package com.cyjh.mobileanjian.ipc.utils;

import android.content.Context;
import com.liulishuo.filedownloader.model.ConnectionModel;

/* compiled from: ResourceUtils.java */
/* renamed from: com.cyjh.mobileanjian.ipc.utils.k */
/* loaded from: classes.dex */
public final class C1337k {
    /* renamed from: a */
    private static int m20633a(Context context, String str) {
        return context.getResources().getIdentifier(str, "layout", context.getPackageName());
    }

    /* renamed from: b */
    private static int m20632b(Context context, String str) {
        return context.getResources().getIdentifier(str, "string", context.getPackageName());
    }

    /* renamed from: c */
    private static int m20631c(Context context, String str) {
        return context.getResources().getIdentifier(str, "bool", context.getPackageName());
    }

    /* renamed from: d */
    private static int m20630d(Context context, String str) {
        return context.getResources().getIdentifier(str, "drawable", context.getPackageName());
    }

    /* renamed from: e */
    private static int m20629e(Context context, String str) {
        return context.getResources().getIdentifier(str, "style", context.getPackageName());
    }

    /* renamed from: f */
    private static Object m20628f(Context context, String str) {
        return Integer.valueOf(context.getResources().getIdentifier(str, "styleable", context.getPackageName()));
    }

    /* renamed from: g */
    private static int m20627g(Context context, String str) {
        return context.getResources().getIdentifier(str, "anim", context.getPackageName());
    }

    /* renamed from: h */
    private static int m20626h(Context context, String str) {
        return context.getResources().getIdentifier(str, ConnectionModel.f10389a, context.getPackageName());
    }

    /* renamed from: i */
    private static int m20625i(Context context, String str) {
        return context.getResources().getIdentifier(str, "color", context.getPackageName());
    }
}
