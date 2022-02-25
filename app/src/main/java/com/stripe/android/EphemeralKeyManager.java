package com.stripe.android;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* renamed from: com.stripe.android.d */
/* loaded from: classes2.dex */
class EphemeralKeyManager {
    @Nullable

    /* renamed from: a */
    private EphemeralKey f11848a;
    @NonNull

    /* renamed from: b */
    private EphemeralKeyProvider f11849b;
    @Nullable

    /* renamed from: c */
    private Calendar f11850c;
    @NonNull

    /* renamed from: d */
    private AbstractC2379b f11851d;

    /* renamed from: e */
    private final long f11852e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: EphemeralKeyManager.java */
    /* renamed from: com.stripe.android.d$b */
    /* loaded from: classes2.dex */
    public interface AbstractC2379b {
        /* renamed from: a */
        void mo18013a(int i, @Nullable String str);

        /* renamed from: a */
        void mo18012a(@Nullable EphemeralKey ephemeralKey, @Nullable String str, @Nullable Map<String, Object> map);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EphemeralKeyManager(@NonNull EphemeralKeyProvider eVar, @NonNull AbstractC2379b bVar, long j, @Nullable Calendar calendar) {
        this.f11849b = eVar;
        this.f11851d = bVar;
        this.f11852e = j;
        this.f11850c = calendar;
        m18014a((String) null, (Map<String, Object>) null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m18014a(@Nullable String str, Map<String, Object> map) {
        if (m18018a(this.f11848a, this.f11852e, this.f11850c)) {
            this.f11849b.m18011a("2017-06-05", new C2378a(this, str, map));
        } else {
            this.f11851d.mo18012a(this.f11848a, str, map);
        }
    }

    @VisibleForTesting
    @Nullable
    /* renamed from: a */
    EphemeralKey m18020a() {
        return this.f11848a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m18015a(@NonNull String str, @Nullable String str2, @Nullable Map<String, Object> map) {
        this.f11848a = EphemeralKey.m18124a(str);
        this.f11851d.mo18012a(this.f11848a, str2, map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m18019a(int i, @Nullable String str) {
        this.f11848a = null;
        this.f11851d.mo18013a(i, str);
    }

    /* renamed from: a */
    static boolean m18018a(@Nullable EphemeralKey ephemeralKey, long j, @Nullable Calendar calendar) {
        if (ephemeralKey == null) {
            return true;
        }
        if (calendar == null) {
            calendar = Calendar.getInstance();
        }
        return ephemeralKey.m18120e() < TimeUnit.MILLISECONDS.toSeconds(calendar.getTimeInMillis()) + j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: EphemeralKeyManager.java */
    /* renamed from: com.stripe.android.d$a */
    /* loaded from: classes2.dex */
    public static class C2378a implements EphemeralKeyUpdateListener {
        @Nullable

        /* renamed from: a */
        private String f11853a;
        @Nullable

        /* renamed from: b */
        private Map<String, Object> f11854b;
        @NonNull

        /* renamed from: c */
        private WeakReference<EphemeralKeyManager> f11855c;

        C2378a(@NonNull EphemeralKeyManager dVar, @Nullable String str, @Nullable Map<String, Object> map) {
            this.f11855c = new WeakReference<>(dVar);
            this.f11853a = str;
            this.f11854b = map;
        }

        @Override // com.stripe.android.EphemeralKeyUpdateListener
        /* renamed from: a */
        public void mo18009a(@NonNull String str) {
            EphemeralKeyManager dVar = this.f11855c.get();
            if (dVar != null) {
                dVar.m18015a(str, this.f11853a, this.f11854b);
            }
        }

        @Override // com.stripe.android.EphemeralKeyUpdateListener
        /* renamed from: a */
        public void mo18010a(int i, @Nullable String str) {
            EphemeralKeyManager dVar = this.f11855c.get();
            if (dVar != null) {
                dVar.m18019a(i, str);
            }
        }
    }
}
