package com.blankj.utilcode.util;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.p003v4.app.NotificationCompat;
import android.support.p003v4.app.NotificationManagerCompat;
import com.lody.virtual.client.ipc.ServiceManagerNative;

/* renamed from: com.blankj.utilcode.util.ah */
/* loaded from: classes.dex */
public class NotificationUtils {
    private NotificationUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static void m23615a(Context context, int i, Intent intent, int i2, String str, String str2) {
        ((NotificationManager) context.getSystemService(ServiceManagerNative.NOTIFICATION)).notify(i, new NotificationCompat.Builder(context).setContentIntent(PendingIntent.getActivity(Utils.m24103a(), 0, intent, 134217728)).setContentTitle(str).setContentText(str2).setSmallIcon(i2).setAutoCancel(true).build());
    }

    /* renamed from: a */
    public static void m23614a(Context context, int i, String str, Intent intent, int i2, String str2, String str3) {
        ((NotificationManager) context.getSystemService(ServiceManagerNative.NOTIFICATION)).notify(i, new NotificationCompat.Builder(context).setContentIntent(intent != null ? PendingIntent.getActivity(Utils.m24103a(), 0, intent, 134217728) : null).setContentTitle(str2).setContentText(str3).setSmallIcon(i2).setGroup(str).setAutoCancel(true).build());
    }

    /* renamed from: a */
    public static void m23616a(int i, String str, String str2) {
        ((NotificationManager) Utils.m24103a().getSystemService(ServiceManagerNative.NOTIFICATION)).notify(0, new NotificationCompat.Builder(Utils.m24103a()).setContentTitle(str).setContentText(str2).setSmallIcon(i).setAutoCancel(true).build());
    }

    /* renamed from: a */
    public static void m23613a(@Nullable String str, int i) {
        NotificationManagerCompat.from(Utils.m24103a()).cancel(str, i);
    }

    /* renamed from: a */
    public static void m23617a(int i) {
        NotificationManagerCompat.from(Utils.m24103a()).cancel(i);
    }

    /* renamed from: a */
    public static void m23618a() {
        NotificationManagerCompat.from(Utils.m24103a()).cancelAll();
    }
}
