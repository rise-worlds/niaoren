package com.king.sdk;

import android.accounts.Account;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;

/* renamed from: com.king.sdk.b */
/* loaded from: classes.dex */
public interface I2FABB9840C76199A1E170A7C19698595 extends IInterface {
    /* renamed from: a */
    int mo19822a();

    /* renamed from: a */
    int mo19818a(Account account, String str, String str2, String str3);

    /* renamed from: a */
    Intent mo19815a(String str);

    /* renamed from: a */
    String mo19819a(Account account);

    /* renamed from: a */
    void mo19821a(int i);

    /* renamed from: a */
    void mo19820a(int i, int i2, String str);

    /* renamed from: a */
    void mo19817a(ComponentName componentName, int i, int i2);

    /* renamed from: a */
    void mo19816a(IDFEE16B42C8B2890D8FF2860AF5562B1 idfee16b42c8b2890d8ff2860af5562b1);

    /* renamed from: a */
    void mo19814a(String str, int i, int i2);

    /* renamed from: a */
    void mo19813a(String str, int i, String str2, Notification notification);

    /* renamed from: a */
    void mo19812a(String str, IBinder iBinder, int i);

    /* renamed from: a */
    void mo19810a(String str, String str2, int i);

    /* renamed from: a */
    void mo19807a(String str, boolean z);

    /* renamed from: a */
    void mo19806a(String[] strArr, int i, String str, String str2);

    /* renamed from: a */
    boolean mo19811a(String str, String str2);

    /* renamed from: a */
    boolean mo19809a(String str, String str2, int i, byte[] bArr, PendingIntent pendingIntent, PendingIntent pendingIntent2);

    /* renamed from: a */
    boolean mo19808a(String str, String str2, String str3, PendingIntent pendingIntent, PendingIntent pendingIntent2);

    /* renamed from: b */
    int mo19804b(Account account, String str, String str2, String str3);

    /* renamed from: b */
    IBinder mo19802b(String str);

    /* renamed from: b */
    void mo19803b(IDFEE16B42C8B2890D8FF2860AF5562B1 idfee16b42c8b2890d8ff2860af5562b1);

    /* renamed from: b */
    byte[] mo19805b();

    /* renamed from: c */
    String mo19801c();

    /* renamed from: c */
    void mo19800c(String str);

    /* renamed from: d */
    String mo19799d();

    /* renamed from: d */
    boolean mo19798d(String str);

    /* renamed from: e */
    int mo19797e();

    /* renamed from: e */
    void mo19796e(String str);

    /* renamed from: f */
    String mo19795f();

    /* renamed from: g */
    String mo19794g();
}
