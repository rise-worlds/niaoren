package com.stripe.android;

import android.support.annotation.NonNull;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* renamed from: com.stripe.android.l */
/* loaded from: classes2.dex */
public interface PaymentResultListener {

    /* renamed from: a */
    public static final String f11901a = "success";

    /* renamed from: b */
    public static final String f11902b = "user_cancelled";

    /* renamed from: c */
    public static final String f11903c = "error";

    /* renamed from: d */
    public static final String f11904d = "incomplete";

    /* compiled from: PaymentResultListener.java */
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.stripe.android.l$a */
    /* loaded from: classes2.dex */
    public @interface AbstractC2381a {
    }

    /* renamed from: a */
    void mo17973a(@NonNull String str);
}
