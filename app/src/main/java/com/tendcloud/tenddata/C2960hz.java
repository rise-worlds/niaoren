package com.tendcloud.tenddata;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.v4.internal.view.SupportMenu;
import android.text.Html;
import com.lody.virtual.client.ipc.ServiceManagerNative;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.hz */
/* loaded from: classes2.dex */
class C2960hz extends ContextWrapper {

    /* renamed from: a */
    public static NotificationChannel f14222a = null;

    /* renamed from: b */
    public static final String f14223b = "default";

    /* renamed from: c */
    public static final String f14224c = "推送";

    /* renamed from: d */
    private NotificationManager f14225d;

    public C2960hz(Context context) {
        super(context);
        try {
            if (C2855es.m15807a(26) && f14222a == null) {
                f14222a = new NotificationChannel("default", f14224c, 3);
                f14222a.setLightColor(SupportMenu.CATEGORY_MASK);
                f14222a.setLockscreenVisibility(0);
                m15463b().createNotificationChannel(f14222a);
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    public Notification m15464a(String str, String str2) {
        Notification notification;
        try {
            if (C2855es.m15807a(26)) {
                notification = new Notification.Builder(getApplicationContext(), "default").setContentTitle(Html.fromHtml(str, 0)).setContentText(Html.fromHtml(str2, 0)).setSmallIcon(m15466a()).setAutoCancel(true).build();
            } else if (C2855es.m15807a(23)) {
                notification = new Notification.Builder(getApplicationContext()).setContentTitle(Html.fromHtml(str)).setContentText(Html.fromHtml(str2)).setSmallIcon(m15466a()).setAutoCancel(true).build();
            } else {
                Notification notification2 = new Notification(0, Html.fromHtml(str), System.currentTimeMillis());
                try {
                    notification2.icon = m15466a();
                    notification2.flags = 16;
                    notification = notification2;
                } catch (Throwable unused) {
                    notification = notification2;
                }
            }
            return notification;
        } catch (Throwable unused2) {
            return null;
        }
    }

    /* renamed from: a */
    private int m15466a() {
        try {
            return getApplicationContext().getPackageManager().getApplicationInfo(getApplicationContext().getPackageName(), 0).icon;
        } catch (Throwable unused) {
            return 0;
        }
    }

    /* renamed from: a */
    public void m15465a(int i, Notification notification) {
        try {
            m15463b().notify(i, notification);
        } catch (Throwable unused) {
        }
    }

    /* renamed from: b */
    private NotificationManager m15463b() {
        try {
            if (this.f14225d == null) {
                this.f14225d = (NotificationManager) getSystemService(ServiceManagerNative.NOTIFICATION);
            }
        } catch (Throwable unused) {
        }
        return this.f14225d;
    }
}
