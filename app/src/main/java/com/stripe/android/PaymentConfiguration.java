package com.stripe.android;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import java.util.Currency;

/* renamed from: com.stripe.android.k */
/* loaded from: classes2.dex */
public class PaymentConfiguration {

    /* renamed from: a */
    private static PaymentConfiguration f11895a;
    @Nullable

    /* renamed from: b */
    private ClassLoader f11896b;
    @NonNull

    /* renamed from: c */
    private String f11897c;

    /* renamed from: d */
    private int f11898d;

    /* renamed from: e */
    private boolean f11899e;

    /* renamed from: f */
    private Currency f11900f;

    private PaymentConfiguration(@NonNull String str) {
        this.f11897c = str;
    }

    @NonNull
    /* renamed from: a */
    public static PaymentConfiguration m17996a() {
        PaymentConfiguration kVar = f11895a;
        if (kVar != null) {
            return kVar;
        }
        throw new IllegalStateException("Attempted to get instance of PaymentConfiguration without initialization.");
    }

    /* renamed from: a */
    public static void m17993a(@NonNull String str) {
        f11895a = new PaymentConfiguration(str);
        PaymentConfiguration kVar = f11895a;
        kVar.f11898d = 0;
        kVar.f11899e = true;
    }

    @NonNull
    /* renamed from: b */
    public String m17991b() {
        return this.f11897c;
    }

    /* renamed from: c */
    public int m17990c() {
        return this.f11898d;
    }

    @NonNull
    /* renamed from: a */
    public PaymentConfiguration m17995a(int i) {
        this.f11898d = i;
        return this;
    }

    /* renamed from: d */
    public boolean m17989d() {
        return this.f11899e;
    }

    @NonNull
    /* renamed from: a */
    public PaymentConfiguration m17992a(boolean z) {
        this.f11899e = z;
        return this;
    }

    @VisibleForTesting
    /* renamed from: a */
    static void m17994a(@Nullable PaymentConfiguration kVar) {
        f11895a = kVar;
    }
}
