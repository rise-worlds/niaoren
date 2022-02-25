package com.king.sdk;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/* renamed from: com.king.sdk.a */
/* loaded from: classes.dex */
public final class ClientService extends Service {

    /* renamed from: a */
    private static I2FABB9840C76199A1E170A7C19698595 f9405a;

    /* renamed from: b */
    private AbstractBinderC1464f f9406b;

    /* renamed from: a */
    public static I2FABB9840C76199A1E170A7C19698595 m19825a() {
        return f9405a;
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.f9406b;
    }
}
