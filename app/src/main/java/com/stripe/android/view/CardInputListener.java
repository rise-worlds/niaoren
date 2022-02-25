package com.stripe.android.view;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* renamed from: com.stripe.android.view.b */
/* loaded from: classes2.dex */
public interface CardInputListener {

    /* compiled from: CardInputListener.java */
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.stripe.android.view.b$a */
    /* loaded from: classes2.dex */
    public @interface AbstractC2476a {

        /* renamed from: a */
        public static final String f12597a = "focus_card";

        /* renamed from: b */
        public static final String f12598b = "focus_expiry";

        /* renamed from: c */
        public static final String f12599c = "focus_cvc";

        /* renamed from: d */
        public static final String f12600d = "focus_postal";
    }

    /* renamed from: a */
    void m17273a();

    /* renamed from: a */
    void m17272a(String str);

    /* renamed from: b */
    void m17271b();

    /* renamed from: c */
    void m17270c();

    /* renamed from: d */
    void m17269d();
}
