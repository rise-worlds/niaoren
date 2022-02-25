package com.stripe.android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.p003v4.content.LocalBroadcastManager;
import com.stripe.android.EphemeralKeyManager;
import com.stripe.android.StripeApiHandler;
import com.stripe.android.model.C2395g;
import com.stripe.android.model.Customer;
import com.stripe.android.model.ShippingInformation;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import p110z1.APIConnectionException;
import p110z1.APIException;
import p110z1.InvalidRequestException;
import p110z1.StripeException;

/* renamed from: com.stripe.android.c */
/* loaded from: classes2.dex */
public class CustomerSession implements EphemeralKeyManager.AbstractC2379b {

    /* renamed from: A */
    private static final int f11787A = 11;

    /* renamed from: B */
    private static final int f11788B = 13;

    /* renamed from: C */
    private static final int f11789C = 17;

    /* renamed from: D */
    private static final int f11790D = 19;

    /* renamed from: E */
    private static final int f11791E = 3;

    /* renamed from: F */
    private static final int f11792F = 2;

    /* renamed from: H */
    private static final long f11794H = 30;

    /* renamed from: J */
    private static CustomerSession f11796J = null;

    /* renamed from: a */
    public static final String f11797a = "action_api_exception";

    /* renamed from: b */
    public static final String f11798b = "exception";

    /* renamed from: c */
    public static final String f11799c = "shipping_info_saved";

    /* renamed from: d */
    private static final String f11800d = "add_source";

    /* renamed from: e */
    private static final String f11801e = "delete_source";

    /* renamed from: f */
    private static final String f11802f = "default_source";

    /* renamed from: g */
    private static final String f11803g = "set_shipping_info";

    /* renamed from: h */
    private static final String f11804h = "source";

    /* renamed from: i */
    private static final String f11805i = "source_type";

    /* renamed from: j */
    private static final String f11806j = "shipping_info";

    /* renamed from: k */
    private static final String f11807k = "PaymentSession";

    /* renamed from: z */
    private static final int f11809z = 7;
    @Nullable

    /* renamed from: m */
    private Customer f11810m;

    /* renamed from: n */
    private long f11811n;
    @Nullable

    /* renamed from: o */
    private WeakReference<Context> f11812o;
    @Nullable

    /* renamed from: p */
    private AbstractC2375a f11813p;
    @Nullable

    /* renamed from: q */
    private AbstractC2376b f11814q;
    @Nullable

    /* renamed from: r */
    private EphemeralKey f11815r;
    @NonNull

    /* renamed from: s */
    private EphemeralKeyManager f11816s;
    @Nullable

    /* renamed from: v */
    private Calendar f11819v;
    @Nullable

    /* renamed from: w */
    private AbstractC2377c f11820w;

    /* renamed from: l */
    private static final Set<String> f11808l = new HashSet(Arrays.asList("AddSourceActivity", "PaymentMethodsActivity", "PaymentFlowActivity", "PaymentSession", "ShippingInfoScreen", "ShippingMethodScreen"));

    /* renamed from: G */
    private static final TimeUnit f11793G = TimeUnit.SECONDS;

    /* renamed from: I */
    private static final long f11795I = TimeUnit.MINUTES.toMillis(1);

    /* renamed from: x */
    private final BlockingQueue<Runnable> f11821x = new LinkedBlockingQueue();
    @NonNull

    /* renamed from: y */
    private ThreadPoolExecutor f11822y = m18027l();
    @NonNull

    /* renamed from: t */
    private Handler f11817t = m18028k();
    @NonNull

    /* renamed from: u */
    private Set<String> f11818u = new HashSet();

    /* compiled from: CustomerSession.java */
    /* renamed from: com.stripe.android.c$a */
    /* loaded from: classes2.dex */
    public interface AbstractC2375a {
        /* renamed from: a */
        void mo17305a(int i, @Nullable String str);

        /* renamed from: a */
        void mo17304a(@NonNull Customer dVar);
    }

    /* compiled from: CustomerSession.java */
    /* renamed from: com.stripe.android.c$b */
    /* loaded from: classes2.dex */
    public interface AbstractC2376b {
        /* renamed from: a */
        void mo17448a(int i, @Nullable String str);

        /* renamed from: a */
        void mo17447a(@NonNull C2395g gVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: CustomerSession.java */
    /* renamed from: com.stripe.android.c$c */
    /* loaded from: classes2.dex */
    public interface AbstractC2377c {
        /* renamed from: a */
        Customer m18025a(@Nullable Context context, @NonNull String str, @NonNull String str2, @NonNull List<String> list, @NonNull ShippingInformation shippingInformation, @NonNull String str3) throws InvalidRequestException, APIConnectionException, APIException;

        /* renamed from: a */
        Customer m18022a(@NonNull String str, @NonNull String str2) throws InvalidRequestException, APIConnectionException, APIException;

        /* renamed from: a */
        C2395g m18024a(@Nullable Context context, @NonNull String str, @NonNull String str2, @NonNull List<String> list, @NonNull String str3, @NonNull String str4) throws InvalidRequestException, APIConnectionException, APIException;

        /* renamed from: a */
        C2395g m18023a(@Nullable Context context, @NonNull String str, @NonNull String str2, @NonNull List<String> list, @NonNull String str3, @NonNull String str4, @NonNull String str5) throws InvalidRequestException, APIConnectionException, APIException;

        /* renamed from: b */
        Customer m18021b(@Nullable Context context, @NonNull String str, @NonNull String str2, @NonNull List<String> list, @NonNull String str3, @NonNull String str4, @NonNull String str5) throws InvalidRequestException, APIConnectionException, APIException;
    }

    /* renamed from: a */
    public static void m18058a(@NonNull EphemeralKeyProvider eVar) {
        m18057a(eVar, (AbstractC2377c) null, (Calendar) null);
    }

    /* renamed from: a */
    public static CustomerSession m18072a() {
        CustomerSession cVar = f11796J;
        if (cVar != null) {
            return cVar;
        }
        throw new IllegalStateException("Attempted to get instance of CustomerSession without initialization.");
    }

    /* renamed from: b */
    public static void m18046b() {
        m18041c();
    }

    @VisibleForTesting
    /* renamed from: a */
    static void m18057a(@NonNull EphemeralKeyProvider eVar, @Nullable AbstractC2377c cVar, @Nullable Calendar calendar) {
        f11796J = new CustomerSession(eVar, cVar, calendar);
    }

    @VisibleForTesting
    /* renamed from: c */
    static void m18041c() {
        CustomerSession cVar = f11796J;
        if (cVar != null) {
            cVar.f11822y.shutdownNow();
            f11796J = null;
        }
    }

    private CustomerSession(@NonNull EphemeralKeyProvider eVar, @Nullable AbstractC2377c cVar, @Nullable Calendar calendar) {
        this.f11820w = cVar;
        this.f11819v = calendar;
        this.f11816s = new EphemeralKeyManager(eVar, this, f11794H, calendar);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* renamed from: a */
    public void m18055a(String str) {
        if (str != null && f11808l.contains(str)) {
            this.f11818u.add(str);
        }
    }

    /* renamed from: a */
    public void m18066a(@NonNull AbstractC2375a aVar) {
        if (m18029j()) {
            aVar.mo17304a(m18039d());
            return;
        }
        this.f11810m = null;
        this.f11813p = aVar;
        this.f11816s.m18014a((String) null, (Map<String, Object>) null);
    }

    /* renamed from: b */
    public void m18045b(@NonNull AbstractC2375a aVar) {
        this.f11810m = null;
        this.f11813p = aVar;
        this.f11816s.m18014a((String) null, (Map<String, Object>) null);
    }

    @Nullable
    /* renamed from: d */
    public Customer m18039d() {
        if (m18029j()) {
            return this.f11810m;
        }
        return null;
    }

    /* renamed from: a */
    public void m18068a(@NonNull Context context, @NonNull String str, @NonNull String str2, @Nullable AbstractC2376b bVar) {
        this.f11812o = new WeakReference<>(context);
        HashMap hashMap = new HashMap();
        hashMap.put("source", str);
        hashMap.put(f11805i, str2);
        this.f11814q = bVar;
        this.f11816s.m18014a(f11800d, hashMap);
    }

    /* renamed from: a */
    public void m18070a(@NonNull Context context, @NonNull String str, @Nullable AbstractC2376b bVar) {
        this.f11812o = new WeakReference<>(context);
        HashMap hashMap = new HashMap();
        hashMap.put("source", str);
        this.f11814q = bVar;
        this.f11816s.m18014a(f11801e, hashMap);
    }

    /* renamed from: a */
    public void m18071a(@NonNull Context context, @NonNull ShippingInformation shippingInformation) {
        this.f11812o = new WeakReference<>(context);
        HashMap hashMap = new HashMap();
        hashMap.put(f11806j, shippingInformation);
        this.f11816s.m18014a(f11803g, hashMap);
    }

    /* renamed from: a */
    public void m18069a(@NonNull Context context, @NonNull String str, @NonNull String str2, @Nullable AbstractC2375a aVar) {
        this.f11812o = new WeakReference<>(context);
        HashMap hashMap = new HashMap();
        hashMap.put("source", str);
        hashMap.put(f11805i, str2);
        this.f11813p = aVar;
        this.f11816s.m18014a(f11802f, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public void m18037e() {
        this.f11818u.clear();
    }

    @VisibleForTesting
    @Nullable
    /* renamed from: f */
    Customer m18035f() {
        return this.f11810m;
    }

    @VisibleForTesting
    /* renamed from: g */
    long m18033g() {
        return this.f11811n;
    }

    @VisibleForTesting
    @Nullable
    /* renamed from: h */
    EphemeralKey m18031h() {
        return this.f11815r;
    }

    @VisibleForTesting
    /* renamed from: i */
    Set<String> m18030i() {
        return this.f11818u;
    }

    @VisibleForTesting
    /* renamed from: a */
    void m18065a(@Nullable AbstractC2377c cVar) {
        this.f11820w = cVar;
    }

    /* renamed from: a */
    private void m18052a(@NonNull final WeakReference<Context> weakReference, @NonNull final EphemeralKey ephemeralKey, @NonNull final String str, @NonNull final String str2, @NonNull final List<String> list) {
        m18056a(new Runnable() { // from class: com.stripe.android.c.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    CustomerSession.this.f11817t.sendMessage(CustomerSession.this.f11817t.obtainMessage(13, CustomerSession.m18048a(weakReference, ephemeralKey, new ArrayList(list), str, str2, CustomerSession.this.f11820w)));
                } catch (StripeException e) {
                    CustomerSession.this.f11817t.sendMessage(CustomerSession.this.f11817t.obtainMessage(17, e));
                    CustomerSession.m18047a(weakReference, e);
                }
            }
        });
    }

    /* renamed from: j */
    private boolean m18029j() {
        return this.f11810m != null && m18026m().getTimeInMillis() - this.f11811n < f11795I;
    }

    /* renamed from: a */
    private void m18051a(@NonNull final WeakReference<Context> weakReference, @NonNull final EphemeralKey ephemeralKey, @NonNull final String str, @NonNull final List<String> list) {
        m18056a(new Runnable() { // from class: com.stripe.android.c.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    CustomerSession.this.f11817t.sendMessage(CustomerSession.this.f11817t.obtainMessage(13, CustomerSession.m18049a(weakReference, ephemeralKey, new ArrayList(list), str, CustomerSession.this.f11820w)));
                } catch (StripeException e) {
                    CustomerSession.this.f11817t.sendMessage(CustomerSession.this.f11817t.obtainMessage(17, e));
                    CustomerSession.m18047a(weakReference, e);
                }
            }
        });
    }

    /* renamed from: b */
    private void m18043b(@NonNull final WeakReference<Context> weakReference, @NonNull final EphemeralKey ephemeralKey, @NonNull final String str, @NonNull final String str2, @NonNull final List<String> list) {
        m18056a(new Runnable() { // from class: com.stripe.android.c.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    CustomerSession.this.f11817t.sendMessage(CustomerSession.this.f11817t.obtainMessage(7, CustomerSession.m18042b(weakReference, ephemeralKey, new ArrayList(list), str, str2, CustomerSession.this.f11820w)));
                } catch (StripeException e) {
                    CustomerSession.this.f11817t.sendMessage(CustomerSession.this.f11817t.obtainMessage(11, e));
                    CustomerSession.m18047a(weakReference, e);
                }
            }
        });
    }

    /* renamed from: a */
    private void m18053a(@NonNull final WeakReference<Context> weakReference, @NonNull final EphemeralKey ephemeralKey, @NonNull final ShippingInformation shippingInformation, @NonNull final List<String> list) {
        m18056a(new Runnable() { // from class: com.stripe.android.c.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    CustomerSession.this.f11817t.sendMessage(CustomerSession.this.f11817t.obtainMessage(19, CustomerSession.m18050a(weakReference, ephemeralKey, new ArrayList(list), shippingInformation, CustomerSession.this.f11820w)));
                } catch (StripeException e) {
                    CustomerSession.this.f11817t.sendMessage(CustomerSession.this.f11817t.obtainMessage(11, e));
                    CustomerSession.m18047a(weakReference, e);
                }
            }
        });
    }

    /* renamed from: a */
    private void m18067a(@NonNull final EphemeralKey ephemeralKey) {
        m18056a(new Runnable() { // from class: com.stripe.android.c.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    CustomerSession.this.f11817t.sendMessage(CustomerSession.this.f11817t.obtainMessage(7, CustomerSession.m18054a(CustomerSession.this.f11812o, ephemeralKey, CustomerSession.this.f11820w)));
                } catch (StripeException e) {
                    CustomerSession.this.f11817t.sendMessage(CustomerSession.this.f11817t.obtainMessage(11, e));
                }
            }
        });
    }

    /* renamed from: a */
    private void m18056a(@NonNull Runnable runnable) {
        if (this.f11820w != null) {
            runnable.run();
        } else {
            this.f11822y.execute(runnable);
        }
    }

    @Override // com.stripe.android.EphemeralKeyManager.AbstractC2379b
    /* renamed from: a */
    public void mo18012a(@Nullable EphemeralKey ephemeralKey, @Nullable String str, @Nullable Map<String, Object> map) {
        this.f11815r = ephemeralKey;
        EphemeralKey ephemeralKey2 = this.f11815r;
        if (ephemeralKey2 == null) {
            return;
        }
        if (str == null) {
            m18067a(ephemeralKey2);
        } else if (f11800d.equals(str) && this.f11812o != null && map != null && map.containsKey("source") && map.containsKey(f11805i)) {
            m18052a(this.f11812o, this.f11815r, (String) map.get("source"), (String) map.get(f11805i), new ArrayList(this.f11818u));
            m18037e();
        } else if (f11801e.equals(str) && this.f11812o != null && map != null && map.containsKey("source")) {
            m18051a(this.f11812o, this.f11815r, (String) map.get("source"), new ArrayList(this.f11818u));
            m18037e();
        } else if (f11802f.equals(str) && this.f11812o != null && map != null && map.containsKey("source") && map.containsKey(f11805i)) {
            m18043b(this.f11812o, this.f11815r, (String) map.get("source"), (String) map.get(f11805i), new ArrayList(this.f11818u));
            m18037e();
        } else if (f11803g.equals(str) && this.f11812o != null && map != null && map.containsKey(f11806j)) {
            m18053a(this.f11812o, this.f11815r, (ShippingInformation) map.get(f11806j), new ArrayList(this.f11818u));
            m18037e();
        }
    }

    @Override // com.stripe.android.EphemeralKeyManager.AbstractC2379b
    /* renamed from: a */
    public void mo18013a(int i, @Nullable String str) {
        AbstractC2375a aVar = this.f11813p;
        if (aVar != null) {
            aVar.mo17305a(i, str);
            this.f11813p = null;
        }
        AbstractC2376b bVar = this.f11814q;
        if (bVar != null) {
            bVar.mo17448a(i, str);
            this.f11814q = null;
        }
    }

    /* renamed from: k */
    private Handler m18028k() {
        return new Handler(Looper.getMainLooper()) { // from class: com.stripe.android.c.6
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                Object obj = message.obj;
                int i = message.what;
                if (i != 7) {
                    int i2 = 400;
                    if (i != 11) {
                        if (i == 13) {
                            if ((obj instanceof C2395g) && CustomerSession.this.f11814q != null) {
                                CustomerSession.this.f11814q.mo17447a((C2395g) obj);
                            }
                            CustomerSession.this.f11814q = null;
                            CustomerSession.this.f11812o = null;
                        } else if (i == 17) {
                            StripeException arfVar = (StripeException) obj;
                            if (CustomerSession.this.f11814q != null) {
                                if (arfVar.getStatusCode() != null) {
                                    i2 = arfVar.getStatusCode().intValue();
                                }
                                CustomerSession.this.f11814q.mo17448a(i2, arfVar.getLocalizedMessage());
                                CustomerSession.this.f11814q = null;
                                CustomerSession.this.m18037e();
                            }
                        } else if (i == 19 && (obj instanceof Customer)) {
                            CustomerSession.this.f11810m = (Customer) obj;
                            LocalBroadcastManager.getInstance((Context) CustomerSession.this.f11812o.get()).sendBroadcast(new Intent(CustomerSession.f11799c));
                        }
                    } else if (obj instanceof StripeException) {
                        StripeException arfVar2 = (StripeException) obj;
                        if (CustomerSession.this.f11813p != null) {
                            if (arfVar2.getStatusCode() != null) {
                                i2 = arfVar2.getStatusCode().intValue();
                            }
                            CustomerSession.this.f11813p.mo17305a(i2, arfVar2.getLocalizedMessage());
                            CustomerSession.this.f11813p = null;
                        }
                        CustomerSession.this.m18037e();
                    }
                } else if (obj instanceof Customer) {
                    CustomerSession.this.f11810m = (Customer) obj;
                    CustomerSession cVar = CustomerSession.this;
                    cVar.f11811n = cVar.m18026m().getTimeInMillis();
                    if (CustomerSession.this.f11813p != null) {
                        CustomerSession.this.f11813p.mo17304a(CustomerSession.this.f11810m);
                        CustomerSession.this.f11813p = null;
                    }
                }
            }
        };
    }

    /* renamed from: l */
    private ThreadPoolExecutor m18027l() {
        return new ThreadPoolExecutor(3, 3, 2L, f11793G, this.f11821x);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NonNull
    /* renamed from: m */
    public Calendar m18026m() {
        Calendar calendar = this.f11819v;
        return calendar == null ? Calendar.getInstance() : calendar;
    }

    /* renamed from: a */
    static C2395g m18048a(@NonNull WeakReference<Context> weakReference, @NonNull EphemeralKey ephemeralKey, @NonNull List<String> list, @NonNull String str, @NonNull String str2, @Nullable AbstractC2377c cVar) throws StripeException {
        if (cVar != null) {
            return cVar.m18023a(weakReference.get(), ephemeralKey.m18121d(), PaymentConfiguration.m17996a().m17991b(), list, str, str2, ephemeralKey.m18116i());
        }
        return StripeApiHandler.m17522a(weakReference.get(), ephemeralKey.m18121d(), PaymentConfiguration.m17996a().m17991b(), list, str, str2, ephemeralKey.m18116i(), null);
    }

    /* renamed from: a */
    static C2395g m18049a(@NonNull WeakReference<Context> weakReference, @NonNull EphemeralKey ephemeralKey, @NonNull List<String> list, @NonNull String str, @Nullable AbstractC2377c cVar) throws StripeException {
        if (cVar != null) {
            return cVar.m18024a(weakReference.get(), ephemeralKey.m18121d(), PaymentConfiguration.m17996a().m17991b(), list, str, ephemeralKey.m18116i());
        }
        return StripeApiHandler.m17523a(weakReference.get(), ephemeralKey.m18121d(), PaymentConfiguration.m17996a().m17991b(), list, str, ephemeralKey.m18116i(), (StripeApiHandler.AbstractC2412a) null);
    }

    /* renamed from: a */
    static Customer m18050a(@NonNull WeakReference<Context> weakReference, @NonNull EphemeralKey ephemeralKey, @NonNull List<String> list, @NonNull ShippingInformation shippingInformation, @Nullable AbstractC2377c cVar) throws StripeException {
        if (cVar != null) {
            return cVar.m18025a(weakReference.get(), ephemeralKey.m18121d(), PaymentConfiguration.m17996a().m17991b(), list, shippingInformation, ephemeralKey.m18116i());
        }
        return StripeApiHandler.m17524a(weakReference.get(), ephemeralKey.m18121d(), PaymentConfiguration.m17996a().m17991b(), list, shippingInformation, ephemeralKey.m18116i(), (StripeApiHandler.AbstractC2412a) null);
    }

    /* renamed from: b */
    static Customer m18042b(@NonNull WeakReference<Context> weakReference, @NonNull EphemeralKey ephemeralKey, @NonNull List<String> list, @NonNull String str, @NonNull String str2, @Nullable AbstractC2377c cVar) throws StripeException {
        if (cVar != null) {
            return cVar.m18021b(weakReference.get(), ephemeralKey.m18121d(), PaymentConfiguration.m17996a().m17991b(), list, str, str2, ephemeralKey.m18116i());
        }
        return StripeApiHandler.m17497b(weakReference.get(), ephemeralKey.m18121d(), PaymentConfiguration.m17996a().m17991b(), list, str, str2, ephemeralKey.m18116i(), null);
    }

    @Nullable
    /* renamed from: a */
    static Customer m18054a(@Nullable WeakReference<Context> weakReference, @NonNull EphemeralKey ephemeralKey, @Nullable AbstractC2377c cVar) throws StripeException {
        if (cVar != null) {
            return cVar.m18022a(ephemeralKey.m18121d(), ephemeralKey.m18116i());
        }
        return StripeApiHandler.m17511a(ephemeralKey.m18121d(), ephemeralKey.m18116i());
    }

    @NonNull
    /* renamed from: a */
    static void m18047a(@Nullable WeakReference<Context> weakReference, @NonNull StripeException arfVar) {
        if (weakReference != null && weakReference.get() != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(f11798b, arfVar);
            Intent intent = new Intent(f11797a);
            intent.putExtras(bundle);
            LocalBroadcastManager.getInstance(weakReference.get()).sendBroadcast(intent);
        }
    }
}
