package com.tendcloud.tenddata;

import android.app.Activity;
import android.content.Context;
import com.tendcloud.tenddata.TDAccount;
import java.util.Map;

/* compiled from: td */
/* loaded from: classes2.dex */
public final class TCAgent {
    public static boolean ENABLE_MULTI_PROCESS_POST = false;
    public static boolean LOG_ON = true;

    public static synchronized void init(Context context) {
        synchronized (TCAgent.class) {
            C2811dq.f13769a = LOG_ON;
            C2668ac.m16341a(context, AbstractC2790d.APP);
        }
    }

    public static synchronized void init(Context context, String str, String str2) {
        synchronized (TCAgent.class) {
            C2811dq.f13769a = LOG_ON;
            C2668ac.m16339a(context, str, str2, AbstractC2790d.APP);
        }
    }

    public static void setPushDisabled() {
        C2668ac.m16321b();
    }

    public static synchronized String getDeviceId(Context context) {
        String d;
        synchronized (TCAgent.class) {
            d = C2668ac.m16307d(context, AbstractC2790d.APP);
        }
        return d;
    }

    @Deprecated
    public static void onResume(Activity activity) {
        C2668ac.m16344a(activity, AbstractC2790d.APP);
    }

    @Deprecated
    public static void onPause(Activity activity) {
        C2668ac.m16320b(activity, AbstractC2790d.APP);
    }

    public static void onPageStart(Context context, String str) {
        C2668ac.m16340a(context, str, AbstractC2790d.APP);
    }

    public static void onPageEnd(Context context, String str) {
        C2668ac.m16317b(context, str, AbstractC2790d.APP);
    }

    public static void setGlobalKV(String str, Object obj) {
        C2668ac.m16329a(str, obj, AbstractC2790d.APP);
    }

    public static void removeGlobalKV(String str) {
        C2668ac.m16330a(str, AbstractC2790d.APP);
    }

    public static void onEvent(Context context, String str) {
        onEvent(context, str, "");
    }

    public static void onEvent(Context context, String str, String str2) {
        onEvent(context, str, str2, null);
    }

    public static void onEvent(Context context, String str, String str2, Map map) {
        C2668ac.m16338a(context, str, str2, map, AbstractC2790d.APP);
    }

    public static void setReportUncaughtExceptions(boolean z) {
        C2668ac.m16322a(z, AbstractC2790d.APP);
    }

    public static void onError(Context context, Throwable th) {
        C2668ac.m16336a(context, th, AbstractC2790d.APP);
    }

    public static int getNFCStatus(Context context) {
        return C2668ac.m16319b(context);
    }

    public static void setAntiCheatingEnabled(Context context, boolean z) {
        C2668ac.m16335a(context, z, AbstractC2790d.APP);
    }

    public static void onRegister(String str, TDAccount.AccountType accountType, String str2) {
        C2668ac.m16315b(str, accountType, str2, AbstractC2790d.APP);
    }

    public static void onLogin(String str, TDAccount.AccountType accountType, String str2) {
        C2668ac.m16331a(str, accountType, str2, AbstractC2790d.APP);
    }

    public static void onPlaceOrder(String str, Order order) {
        C2668ac.m16332a(str, order, AbstractC2790d.APP);
    }

    public static void onOrderPaySucc(String str, String str2, Order order) {
        C2668ac.m16325a(str, str2, order, AbstractC2790d.APP);
    }

    public static void onAddItemToShoppingCart(String str, String str2, String str3, int i, int i2) {
        C2668ac.m16324a(str, str2, str3, i, i2, AbstractC2790d.APP);
    }

    public static void onViewItem(String str, String str2, String str3, int i) {
        C2668ac.m16323a(str, str2, str3, i, AbstractC2790d.APP);
    }

    public static void onViewShoppingCart(ShoppingCart shoppingCart) {
        C2668ac.m16334a(shoppingCart, AbstractC2790d.APP);
    }
}
