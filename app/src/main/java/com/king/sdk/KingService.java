package com.king.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.king.sdk.p053a.BinderIntent;

/* renamed from: com.king.sdk.i */
/* loaded from: classes.dex */
public final class KingService {

    /* renamed from: b */
    private static KingService f9411b;

    /* renamed from: a */
    private I2FABB9840C76199A1E170A7C19698595 f9412a;

    private KingService(I2FABB9840C76199A1E170A7C19698595 bVar) {
        this.f9412a = bVar;
    }

    /* renamed from: a */
    public final void m19792a(IDFEE16B42C8B2890D8FF2860AF5562B1 idfee16b42c8b2890d8ff2860af5562b1) {
        try {
            this.f9412a.mo19816a(idfee16b42c8b2890d8ff2860af5562b1);
        } catch (Exception unused) {
        }
    }

    /* renamed from: a */
    public final void m19791a(KingListener kingListener) {
        try {
            this.f9412a.mo19803b(kingListener);
        } catch (Exception unused) {
        }
    }

    /* renamed from: a */
    public final void m19788a(String[] strArr, int i, String str, String str2) {
        try {
            this.f9412a.mo19806a(strArr, i, str, str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static synchronized KingService m19793a(Context context) {
        I2FABB9840C76199A1E170A7C19698595 a;
        synchronized (KingService.class) {
            if (f9411b != null) {
                return f9411b;
            }
            I2FABB9840C76199A1E170A7C19698595 a2 = ClientService.m19825a();
            if (a2 != null) {
                f9411b = new KingService(a2);
            } else {
                Intent registerReceiver = context.registerReceiver(null, new IntentFilter("GET_KING_SERVICE"));
                if (!(registerReceiver == null || (a = AbstractBinderC1462c.m19823a(BinderIntent.m19824a(registerReceiver, "GET_KING_SERVICE"))) == null)) {
                    f9411b = new KingService(a);
                }
            }
            return f9411b;
        }
    }

    /* renamed from: a */
    public final boolean m19789a(String str, String str2) {
        try {
            return this.f9412a.mo19808a(str, null, str2, null, null);
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: a */
    public final boolean m19790a(String str, int i, byte[] bArr) {
        try {
            return this.f9412a.mo19809a(str, null, i, bArr, null, null);
        } catch (Exception unused) {
            return false;
        }
    }
}
