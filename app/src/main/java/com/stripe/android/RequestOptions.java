package com.stripe.android;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* renamed from: com.stripe.android.o */
/* loaded from: classes2.dex */
public class RequestOptions {

    /* renamed from: a */
    public static final String f12301a = "source";

    /* renamed from: b */
    public static final String f12302b = "json_data";
    @NonNull

    /* renamed from: c */
    private final String f12303c;
    @Nullable

    /* renamed from: d */
    private final String f12304d;
    @Nullable

    /* renamed from: e */
    private final String f12305e;
    @Nullable

    /* renamed from: f */
    private final String f12306f;
    @NonNull

    /* renamed from: g */
    private final String f12307g;
    @Nullable

    /* renamed from: h */
    private final String f12308h;

    /* compiled from: RequestOptions.java */
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.stripe.android.o$b */
    /* loaded from: classes2.dex */
    public @interface AbstractC2404b {
    }

    private RequestOptions(@NonNull String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @NonNull String str5, @Nullable String str6) {
        this.f12303c = str;
        this.f12304d = str2;
        this.f12305e = str3;
        this.f12306f = str4;
        this.f12307g = str5;
        this.f12308h = str6;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    /* renamed from: a */
    public String m17581a() {
        return this.f12303c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    /* renamed from: b */
    public String m17577b() {
        return this.f12304d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    /* renamed from: c */
    public String m17576c() {
        return this.f12305e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    /* renamed from: d */
    public String m17575d() {
        return this.f12306f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    /* renamed from: e */
    public String m17574e() {
        return this.f12307g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    /* renamed from: f */
    public String m17573f() {
        return this.f12308h;
    }

    /* renamed from: a */
    public static C2403a m17580a(@Nullable String str) {
        return m17579a(str, f12301a);
    }

    /* renamed from: a */
    public static C2403a m17578a(@Nullable String str, @Nullable String str2, @NonNull String str3) {
        return new C2403a(str, str3).m17567e(str2);
    }

    /* renamed from: a */
    public static C2403a m17579a(@Nullable String str, @NonNull String str2) {
        return new C2403a(str, str2);
    }

    /* compiled from: RequestOptions.java */
    /* renamed from: com.stripe.android.o$a */
    /* loaded from: classes2.dex */
    public static final class C2403a {

        /* renamed from: a */
        private String f12309a;

        /* renamed from: b */
        private String f12310b;

        /* renamed from: c */
        private String f12311c;

        /* renamed from: d */
        private String f12312d;

        /* renamed from: e */
        private String f12313e;

        /* renamed from: f */
        private String f12314f;

        C2403a(@Nullable String str, @NonNull String str2) {
            this.f12312d = str;
            this.f12313e = str2;
        }

        @NonNull
        /* renamed from: a */
        C2403a m17571a(@NonNull String str) {
            this.f12312d = str;
            return this;
        }

        @NonNull
        /* renamed from: b */
        C2403a m17570b(@Nullable String str) {
            this.f12311c = str;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @NonNull
        /* renamed from: c */
        public C2403a m17569c(@Nullable String str) {
            this.f12310b = str;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @NonNull
        /* renamed from: d */
        public C2403a m17568d(@Nullable String str) {
            if (StripeTextUtils.m17202b(str)) {
                str = null;
            }
            this.f12309a = str;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @NonNull
        /* renamed from: e */
        public C2403a m17567e(@Nullable String str) {
            this.f12314f = str;
            return this;
        }

        /* renamed from: a */
        public RequestOptions m17572a() {
            return new RequestOptions(this.f12309a, this.f12310b, this.f12311c, this.f12312d, this.f12313e, this.f12314f);
        }
    }
}
