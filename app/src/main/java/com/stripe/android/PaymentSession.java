package com.stripe.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.stripe.android.CustomerSession;
import com.stripe.android.model.Customer;
import com.stripe.android.view.PaymentFlowActivity;
import com.stripe.android.view.PaymentMethodsActivity;

/* renamed from: com.stripe.android.m */
/* loaded from: classes2.dex */
public class PaymentSession {

    /* renamed from: a */
    public static final String f11905a = "PaymentSession";

    /* renamed from: b */
    public static final String f11906b = "payment_session_active";

    /* renamed from: c */
    static final int f11907c = 3004;

    /* renamed from: d */
    static final int f11908d = 3003;

    /* renamed from: e */
    public static final String f11909e = "payment_session_data";

    /* renamed from: f */
    public static final String f11910f = "payment_session_config";
    @NonNull

    /* renamed from: g */
    private Activity f11911g;
    @NonNull

    /* renamed from: h */
    private PaymentSessionData f11912h = new PaymentSessionData();
    @Nullable

    /* renamed from: i */
    private AbstractC2384a f11913i;
    @NonNull

    /* renamed from: j */
    private PaymentSessionConfig f11914j;

    /* compiled from: PaymentSession.java */
    /* renamed from: com.stripe.android.m$a */
    /* loaded from: classes2.dex */
    public interface AbstractC2384a {
        /* renamed from: a */
        void m17972a(int i, @Nullable String str);

        /* renamed from: a */
        void m17971a(@NonNull PaymentSessionData paymentSessionData);

        /* renamed from: a */
        void m17970a(boolean z);
    }

    public PaymentSession(@NonNull Activity activity) {
        this.f11911g = activity;
    }

    /* renamed from: a */
    public void m17983a(@NonNull PaymentCompletionProvider jVar) {
        jVar.m17997a(this.f11912h, new PaymentResultListener() { // from class: com.stripe.android.m.1
            @Override // com.stripe.android.PaymentResultListener
            /* renamed from: a */
            public void mo17973a(@NonNull String str) {
                PaymentSession.this.f11912h.m18090a(str);
                CustomerSession.m18072a().m18037e();
                if (PaymentSession.this.f11913i != null) {
                    PaymentSession.this.f11913i.m17971a(PaymentSession.this.f11912h);
                }
            }
        });
    }

    /* renamed from: a */
    public boolean m17987a(int i, int i2, @NonNull Intent intent) {
        if (i2 == 0) {
            m17974e();
            return false;
        }
        if (i2 == -1) {
            switch (i) {
                case f11908d /* 3003 */:
                    m17974e();
                    return true;
                case f11907c /* 3004 */:
                    PaymentSessionData paymentSessionData = (PaymentSessionData) intent.getParcelableExtra(f11909e);
                    m17984a(this.f11914j, paymentSessionData);
                    this.f11912h = paymentSessionData;
                    this.f11913i.m17971a(paymentSessionData);
                    return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public boolean m17984a(PaymentSessionConfig paymentSessionConfig, PaymentSessionData paymentSessionData) {
        if (StripeTextUtils.m17202b(paymentSessionData.m18094a()) || ((paymentSessionConfig.m18109d() && paymentSessionData.m18082f() == null) || (paymentSessionConfig.m18108e() && paymentSessionData.m18081g() == null))) {
            paymentSessionData.m18089a(false);
            return false;
        }
        paymentSessionData.m18089a(true);
        return true;
    }

    /* renamed from: a */
    public boolean m17982a(@NonNull AbstractC2384a aVar, @NonNull PaymentSessionConfig paymentSessionConfig) {
        return m17981a(aVar, paymentSessionConfig, (Bundle) null);
    }

    /* renamed from: a */
    public boolean m17981a(@NonNull AbstractC2384a aVar, @NonNull PaymentSessionConfig paymentSessionConfig, @Nullable Bundle bundle) {
        PaymentSessionData paymentSessionData;
        if (bundle == null) {
            try {
                CustomerSession.m18072a().m18037e();
            } catch (IllegalStateException unused) {
                this.f11913i = null;
                return false;
            }
        }
        CustomerSession.m18072a().m18055a(f11905a);
        this.f11913i = aVar;
        if (!(bundle == null || (paymentSessionData = (PaymentSessionData) bundle.getParcelable(f11909e)) == null)) {
            this.f11912h = paymentSessionData;
        }
        this.f11914j = paymentSessionConfig;
        m17974e();
        return true;
    }

    /* renamed from: a */
    public void m17988a() {
        Intent a = PaymentMethodsActivity.m17325a(this.f11911g);
        a.putExtra(f11906b, true);
        this.f11911g.startActivityForResult(a, f11908d);
    }

    /* renamed from: a */
    public void m17985a(@NonNull Bundle bundle) {
        bundle.putParcelable(f11909e, this.f11912h);
    }

    /* renamed from: a */
    public void m17986a(@IntRange(from = 0) long j) {
        this.f11912h.m18093a(j);
    }

    /* renamed from: b */
    public void m17979b() {
        Intent intent = new Intent(this.f11911g, PaymentFlowActivity.class);
        intent.putExtra(f11910f, this.f11914j);
        intent.putExtra(f11909e, this.f11912h);
        intent.putExtra(f11906b, true);
        this.f11911g.startActivityForResult(intent, f11907c);
    }

    /* renamed from: c */
    public PaymentSessionData m17977c() {
        return this.f11912h;
    }

    /* renamed from: d */
    public void m17975d() {
        this.f11913i = null;
    }

    /* renamed from: e */
    private void m17974e() {
        AbstractC2384a aVar = this.f11913i;
        if (aVar != null) {
            aVar.m17970a(true);
        }
        CustomerSession.m18072a().m18066a(new CustomerSession.AbstractC2375a() { // from class: com.stripe.android.m.2
            @Override // com.stripe.android.CustomerSession.AbstractC2375a
            /* renamed from: a */
            public void mo17304a(@NonNull Customer dVar) {
                PaymentSession.this.f11912h.m18086b(dVar.m17812d());
                PaymentSession mVar = PaymentSession.this;
                mVar.m17984a(mVar.f11914j, PaymentSession.this.f11912h);
                if (PaymentSession.this.f11913i != null) {
                    PaymentSession.this.f11913i.m17971a(PaymentSession.this.f11912h);
                    PaymentSession.this.f11913i.m17970a(false);
                }
            }

            @Override // com.stripe.android.CustomerSession.AbstractC2375a
            /* renamed from: a */
            public void mo17305a(int i, @Nullable String str) {
                if (PaymentSession.this.f11913i != null) {
                    PaymentSession.this.f11913i.m17972a(i, str);
                    PaymentSession.this.f11913i.m17970a(false);
                }
            }
        });
    }
}
