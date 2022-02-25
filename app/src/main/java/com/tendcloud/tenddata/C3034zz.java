package com.tendcloud.tenddata;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.tendcloud.tenddata.TDAccount;
import java.util.HashMap;
import java.util.Map;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.zz */
/* loaded from: classes2.dex */
public final class C3034zz implements AbstractC2680ao {

    /* renamed from: a */
    public static volatile boolean f14345a = false;

    /* renamed from: b */
    public static volatile boolean f14346b = false;

    /* renamed from: c */
    static boolean f14347c = false;

    /* renamed from: d */
    public static C2961i f14348d = null;

    /* renamed from: e */
    public static final int f14349e = 102;

    /* renamed from: f */
    private static volatile C3034zz f14350f = null;

    /* renamed from: g */
    private static String f14351g = null;

    /* renamed from: h */
    private static String f14352h = null;

    /* renamed from: i */
    private static boolean f14353i = false;

    /* renamed from: j */
    private static final int f14354j = 101;

    /* renamed from: k */
    private static final int f14355k = 103;

    /* renamed from: n */
    private static Handler f14358n;

    /* renamed from: m */
    private static final HandlerThread f14357m = new HandlerThread("ProcessingThread");

    /* renamed from: o */
    private static final HandlerThread f14359o = new HandlerThread("PauseEventThread");

    /* renamed from: l */
    private static Handler f14356l = new HandlerC2994j(f14357m.getLooper());

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.zz$a */
    /* loaded from: classes2.dex */
    public static class C3035a {
        public HashMap paraMap = new HashMap();
    }

    public C3034zz() {
        f14350f = this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static synchronized C3034zz m15237a() {
        C3034zz zzVar;
        synchronized (C3034zz.class) {
            if (f14350f == null) {
                synchronized (C3034zz.class) {
                    if (f14350f == null) {
                        f14350f = new C3034zz();
                    }
                }
            }
            zzVar = f14350f;
        }
        return zzVar;
    }

    static {
        f14358n = null;
        f14357m.start();
        f14359o.start();
        f14358n = new HandlerC3024u(f14359o.getLooper());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static Handler m15215b() {
        return f14358n;
    }

    /* renamed from: c */
    public static Handler m15206c() {
        return f14356l;
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: a */
    public void mo15233a(Context context, AbstractC2790d dVar) {
        mo15230a(context, (String) null, (String) null, dVar);
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: a */
    public void mo15230a(Context context, String str, String str2, AbstractC2790d dVar) {
        try {
            if (context == null) {
                C2811dq.iForDeveloper("Init failed Context is null");
            } else if (!C2855es.m15792b(context, "android.permission.INTERNET")) {
                C2811dq.eForDeveloper("[SDKInit] Permission \"android.permission.INTERNET\" is needed.");
            } else if (dVar == null) {
                C2811dq.eForDeveloper("Failed to initialize!");
            } else {
                if (!f14345a) {
                    C2664ab.f13513g = context.getApplicationContext();
                    f14351g = str;
                    f14352h = str2;
                    Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
                    String a = C2855es.m15803a(bundle, C2664ab.f13500Q);
                    String a2 = C2855es.m15803a(bundle, "TD_CHANNEL_ID");
                    if (C2855es.m15791b(a)) {
                        a = f14351g;
                    }
                    f14351g = a;
                    if (C2855es.m15791b(a2)) {
                        a2 = f14352h;
                    }
                    f14352h = a2;
                    String a3 = C2855es.m15804a(context, "ChannelConfig.json");
                    if (C2855es.m15791b(a3)) {
                        a3 = f14352h;
                    }
                    f14352h = a3;
                    f14352h = !C2855es.m15791b(f14352h) ? f14352h : "Default";
                    if (C2855es.m15791b(f14351g)) {
                        C2811dq.eForDeveloper("[SDKInit] TD AppId is null");
                        return;
                    }
                    C2664ab.m16355a(f14351g, f14352h, dVar);
                    C2913gm.m15591a().m15584a(f14351g, f14352h, dVar);
                    m15234a(context);
                    C2919gr.m15562a();
                    C2744bq.m16223a();
                    f14345a = true;
                }
                C2855es.execute(new RunnableC2673ah(this, dVar));
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: b */
    public String mo15212b(Context context, AbstractC2790d dVar) {
        if (context != null) {
            try {
                if (!f14345a) {
                    C2811dq.iForDeveloper("SDK have not been initialized");
                }
            } catch (Throwable unused) {
                return null;
            }
        }
        return C2819dt.m15969a(context);
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: b */
    public String mo15213b(Context context) {
        try {
            return C2819dt.m15969a(context);
        } catch (Throwable unused) {
            return null;
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: d */
    public Context mo15201d() {
        try {
            return C2664ab.f13513g;
        } catch (Throwable unused) {
            return null;
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: c */
    public String mo15205c(Context context, AbstractC2790d dVar) {
        return C2664ab.m16358a(context, dVar);
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: d */
    public String mo15200d(Context context, AbstractC2790d dVar) {
        return C2664ab.m16353b(context, dVar);
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: a */
    public void mo15236a(Activity activity, AbstractC2790d dVar) {
        try {
            if (!f14345a) {
                C2811dq.eForDeveloper("SDK have not been initialized");
            } else if (!f14353i || !C2664ab.f13487D) {
                m15234a(activity);
                C2681ap.m16303a(activity, dVar);
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: a */
    public void mo15235a(Activity activity, String str, String str2, AbstractC2790d dVar) {
        try {
            if (!f14345a) {
                C2811dq.eForDeveloper("SDK have not been initialized");
                return;
            }
            m15234a(activity);
            mo15236a(activity, dVar);
        } catch (Throwable unused) {
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: b */
    public void mo15214b(Activity activity, AbstractC2790d dVar) {
        try {
            if (!f14345a) {
                C2811dq.eForDeveloper("SDK have not been initialized");
            } else if (!f14353i) {
                m15234a(activity);
                C2681ap.m16302b(activity, dVar);
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: a */
    public void mo15231a(Context context, String str, AbstractC2790d dVar) {
        try {
            if (!f14345a) {
                C2811dq.eForDeveloper("SDK have not been initialized");
            } else if (f14347c) {
                f14347c = false;
            } else {
                if (C2855es.m15791b(str) && (context instanceof Activity)) {
                    str = ((Activity) context).getLocalClassName();
                }
                m15232a(context, str, 4, dVar);
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: b */
    public void mo15211b(Context context, String str, AbstractC2790d dVar) {
        try {
            if (!f14345a) {
                C2811dq.eForDeveloper("SDK have not been initialized");
                return;
            }
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                if (C2855es.m15791b(str)) {
                    str = activity.getLocalClassName();
                }
                if ((activity.getChangingConfigurations() & 128) == 128) {
                    f14347c = true;
                    return;
                }
            }
            m15232a(context, str, 5, dVar);
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    private void m15232a(Context context, String str, int i, AbstractC2790d dVar) {
        C2855es.execute(new RunnableC2674ai(this, i, str, dVar));
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: a */
    public void mo15229a(Context context, String str, String str2, Map map, AbstractC2790d dVar) {
        String str3;
        try {
            if (!f14345a) {
                C2811dq.eForDeveloper("SDK have not been initialized");
            } else if (TextUtils.isEmpty(str)) {
                C2811dq.eForDeveloper("onEvent()# event id is empty.");
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("onEvent being called! eventId: ");
                sb.append(str);
                if (dVar.index() != 3) {
                    sb.append(", eventLabel: ");
                    sb.append(str2 == null ? "null" : str2);
                }
                sb.append(", eventMap: ");
                if (map == null) {
                    str3 = "null";
                } else {
                    str3 = "mapSize: " + String.valueOf(map.size());
                }
                sb.append(str3);
                C2811dq.iForDeveloper(sb.toString());
                C2855es.execute(new RunnableC2675aj(this, dVar, str, str2, map));
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: c */
    public void mo15202c(boolean z) {
        try {
            C2664ab.f13511e = z;
            C2811dq.iForDeveloper(" setReportUncaughtExceptions: " + z);
        } catch (Throwable unused) {
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: a */
    public void mo15228a(Context context, Throwable th, AbstractC2790d dVar) {
        try {
            if (!f14345a) {
                C2811dq.eForDeveloper("SDK have not been initialized");
            } else if (th != null) {
                C2855es.execute(new RunnableC2676ak(this, th, dVar));
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: e */
    public void mo15198e() {
        try {
            C2811dq.f13769a = false;
        } catch (Throwable unused) {
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: a */
    public void mo15222a(String str, Object obj) {
        if (!f14345a) {
            C2811dq.eForDeveloper("SDK have not been initialized");
            return;
        }
        if (!(str == null || obj == null)) {
            C2811dq.iForDeveloper("setGlobalKV# key:" + str + " value:" + obj.toString());
        }
        C2664ab.f13510d.put(str, obj);
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    public void removeGlobalKV(String str) {
        if (!f14345a) {
            C2811dq.eForDeveloper("SDK have not been initialized");
            return;
        }
        if (str != null) {
            C2811dq.iForDeveloper("removeGlobalKV# key:" + str);
        }
        C2664ab.f13510d.remove(str);
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: a */
    public void mo15223a(String str, AbstractC2790d dVar) {
        try {
            if (!f14345a) {
                C2811dq.eForDeveloper("SDK have not been initialized");
            } else if (TextUtils.isEmpty(str)) {
                C2811dq.eForDeveloper("onLogin: account could not be null or empty");
            } else {
                C2811dq.iForDeveloper("onLogin called --> account is " + str);
                C2855es.execute(new RunnableC2677al(this, dVar, str));
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: a */
    public void mo15224a(String str, TDAccount.AccountType accountType, String str2, AbstractC2790d dVar) {
        try {
            if (!f14345a) {
                C2811dq.eForDeveloper("SDK have not been initialized");
            } else if (TextUtils.isEmpty(str)) {
                C2811dq.eForDeveloper("onRegister: account could not be null or empty");
            } else {
                C2811dq.iForDeveloper("onRegister called --> account is " + str + " ，type is " + accountType + " , name is " + str2);
                C2855es.execute(new RunnableC2678am(this, dVar, str, accountType, str2));
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: b */
    public void mo15209b(String str, TDAccount.AccountType accountType, String str2, AbstractC2790d dVar) {
        try {
            if (!f14345a) {
                C2811dq.eForDeveloper("SDK have not been initialized");
            } else if (TextUtils.isEmpty(str)) {
                C2811dq.eForDeveloper("onLogin: account could not be null or empty");
            } else {
                C2811dq.iForDeveloper("onLogin called --> account is " + str + " ，type is " + accountType + " , name is " + str2);
                C2855es.execute(new RunnableC2679an(this, dVar, str, accountType, str2));
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: a */
    public void mo15225a(String str, TDAccount.AccountType accountType, AbstractC2790d dVar) {
        try {
            if (!f14345a) {
                C2811dq.eForDeveloper("SDK have not been initialized");
            } else if (TextUtils.isEmpty(str)) {
                C2811dq.eForDeveloper("onLogin: account could not be null or empty");
            } else {
                C2811dq.iForDeveloper("onLogin called --> account is " + str + " ，type is " + accountType);
                C2855es.execute(new RunnableC3014k(this, dVar, str, accountType));
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: b */
    public void mo15208b(String str, AbstractC2790d dVar) {
        try {
            if (!f14345a) {
                C2811dq.eForDeveloper("SDK have not been initialized");
            } else if (TextUtils.isEmpty(str)) {
                C2811dq.eForDeveloper("onRegister: account could not be null or empty");
            } else {
                C2811dq.iForDeveloper("onRegister called --> account is " + str);
                C2855es.execute(new RunnableC3015l(this, dVar, str));
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: b */
    public void mo15210b(String str, TDAccount.AccountType accountType, AbstractC2790d dVar) {
        try {
            if (!f14345a) {
                C2811dq.eForDeveloper("SDK have not been initialized");
            } else if (TextUtils.isEmpty(str)) {
                C2811dq.eForDeveloper("onApply: account could not be null or empty");
            } else {
                C2811dq.iForDeveloper("onApply called --> account is " + str + " ,type is " + accountType);
                C2855es.execute(new RunnableC3016m(this, dVar, str, accountType));
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: c */
    public void mo15204c(String str, TDAccount.AccountType accountType, AbstractC2790d dVar) {
        try {
            if (!f14345a) {
                C2811dq.eForDeveloper("SDK have not been initialized");
            } else if (TextUtils.isEmpty(str)) {
                C2811dq.eForDeveloper("onActivate: account could not be null or empty");
            } else {
                C2811dq.iForDeveloper("onActivate called --> account is " + str + " ,type is " + accountType);
                C2855es.execute(new RunnableC3017n(this, dVar, str, accountType));
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    public void onLogout(AbstractC2790d dVar) {
        try {
            if (!f14345a) {
                C2811dq.eForDeveloper("SDK have not been initialized");
                return;
            }
            C2811dq.iForDeveloper("ModuleAccount.logout ");
            C2855es.execute(new RunnableC3018o(this, dVar));
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: a */
    public void mo15217a(String str, String str2, String str3, int i, int i2, AbstractC2790d dVar) {
        try {
            if (!f14345a) {
                C2811dq.eForDeveloper("SDK have not been initialized");
                return;
            }
            C2811dq.iForDeveloper("onAddItemToShoppingCart called --> itemId: " + str + " ,category: " + str2 + " ,name: " + str3 + " ,unitPrice: " + i + " ,amount: " + i2);
            C2855es.execute(new RunnableC3019p(this, dVar, str, str2, str3, i, i2));
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: a */
    public void mo15220a(String str, String str2, int i, String str3, String str4, AbstractC2790d dVar) {
        try {
            if (!f14345a) {
                C2811dq.eForDeveloper("SDK have not been initialized");
                return;
            }
            if (str != null && str.trim().length() > 0) {
                C2811dq.iForDeveloper("onOrderPaySucc called --> account: " + str + " ,orderid: " + str2 + " ,amount: " + i + " ,currencyType: " + str3 + " ,payType: " + str4);
                if (str3.trim().length() != 3) {
                    C2811dq.eForDeveloper("currencyType length must be 3 likes CNY so so");
                    return;
                } else {
                    C2855es.execute(new RunnableC3020q(this, dVar, str, str2, i, str3, str4));
                    return;
                }
            }
            C2811dq.eForDeveloper("onOrderPaySucc: account could not be null or empty");
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: b */
    public void mo15207b(String str, String str2, int i, String str3, String str4, AbstractC2790d dVar) {
        try {
            if (!f14345a) {
                C2811dq.eForDeveloper("SDK have not been initialized");
                return;
            }
            if (str != null && str.trim().length() > 0) {
                C2811dq.iForDeveloper("onPay called --> account: " + str + " ,orderid: " + str2 + " ,amount: " + i + " ,currencyType: " + str3 + " ,payType: " + str4);
                if (str3.trim().length() != 3) {
                    C2811dq.eForDeveloper("currencyType length must be 3 likes CNY so so");
                    return;
                } else {
                    C2855es.execute(new RunnableC3021r(this, dVar, str, str2, i, str3, str4));
                    return;
                }
            }
            C2811dq.eForDeveloper("onPay: account could not be null or empty");
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: a */
    public void mo15219a(String str, String str2, int i, String str3, String str4, String str5, int i2, AbstractC2790d dVar) {
        try {
            if (!f14345a) {
                C2811dq.eForDeveloper("SDK have not been initialized");
                return;
            }
            if (str != null && str.trim().length() > 0) {
                C2811dq.iForDeveloper("onPay called --> account: " + str + " ,orderid: " + str2 + " ,amount: " + i + " ,currencyType: " + str3 + " ,payType: " + str4 + " ,itemId: " + str5 + " ,itemCount: " + i2);
                if (str3.trim().length() != 3) {
                    C2811dq.eForDeveloper("currencyType length must be 3 likes CNY so so");
                    return;
                } else {
                    C2855es.execute(new RunnableC3022s(this, dVar, str, str2, i, str3, str4, str5, i2));
                    return;
                }
            }
            C2811dq.eForDeveloper("onPay: account could not be null or empty");
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: c */
    public void mo15203c(String str, AbstractC2790d dVar) {
        try {
            if (!f14345a) {
                C2811dq.eForDeveloper("SDK have not been initialized");
                return;
            }
            if (str != null && str.trim().length() > 0) {
                C2811dq.iForDeveloper("onPay called --> accountID: " + str);
                C2855es.execute(new RunnableC3023t(this, dVar, str));
                return;
            }
            C2811dq.eForDeveloper("onPay: account could not be null or empty");
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: a */
    public void mo15221a(String str, String str2, int i, String str3, String str4, Order order, AbstractC2790d dVar) {
        try {
            if (!f14345a) {
                C2811dq.eForDeveloper("SDK have not been initialized");
                return;
            }
            if (str != null && str.trim().length() > 0) {
                if (order == null) {
                    C2811dq.eForDeveloper("onPay: order could not be null");
                    return;
                }
                C2811dq.iForDeveloper("onPay called --> account: " + str + " ,orderid: " + str2 + " ,amount: " + i + " ,currencyType: " + str3 + " ,payType: " + str4 + " ,order: " + order.toString());
                if (str3.trim().length() != 3) {
                    C2811dq.eForDeveloper("currencyType length must be 3 likes CNY so so");
                    return;
                } else {
                    C2855es.execute(new RunnableC3025v(this, dVar, str, str2, i, str3, str4, order));
                    return;
                }
            }
            C2811dq.eForDeveloper("onPay: account could not be null or empty");
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: a */
    public void mo15218a(String str, String str2, Order order, AbstractC2790d dVar) {
        try {
            if (!f14345a) {
                C2811dq.eForDeveloper("SDK have not been initialized");
                return;
            }
            if (str != null && str.trim().length() > 0) {
                if (order != null && !order.optString(Order.keyOrderId).isEmpty()) {
                    C2811dq.iForDeveloper("onPay called --> account: " + str + " ,payType: " + str2 + " ,order: " + order.toString());
                    C2855es.execute(new RunnableC3026w(this, dVar, str, order, str2));
                    return;
                }
                C2811dq.eForDeveloper("onPay: order or orderID could not be null or empty");
                return;
            }
            C2811dq.eForDeveloper("onPay: account could not be null or empty");
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: a */
    public void mo15226a(String str, Order order, AbstractC2790d dVar) {
        try {
            if (!f14345a) {
                C2811dq.eForDeveloper("SDK have not been initialized");
                return;
            }
            if (str != null && str.trim().length() > 0) {
                if (order != null && !order.optString(Order.keyOrderId).isEmpty()) {
                    C2811dq.iForDeveloper("onPlaceOrder called --> account: " + str + " ,order: " + order.toString());
                    C2855es.execute(new RunnableC3027x(this, dVar, str, order));
                    return;
                }
                C2811dq.eForDeveloper("onPlaceOrder: order or orderID could not be null or empty");
                return;
            }
            C2811dq.eForDeveloper("onPlaceOrder: account could not be null or empty");
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: a */
    public void mo15216a(String str, String str2, String str3, int i, AbstractC2790d dVar) {
        try {
            if (!f14345a) {
                C2811dq.eForDeveloper("SDK have not been initialized");
                return;
            }
            C2811dq.iForDeveloper("onViewItem called --> itemId: " + str + " ,category: " + str2 + " ,name: " + str3 + " ,unitPrice: " + i);
            C2855es.execute(new RunnableC3028y(this, dVar, str, str2, str3, i));
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: a */
    public void mo15227a(ShoppingCart shoppingCart, AbstractC2790d dVar) {
        try {
            if (!f14345a) {
                C2811dq.eForDeveloper("SDK have not been initialized");
                return;
            }
            C2811dq.iForDeveloper("onViewShoppingCart called --> shoppingCart: " + shoppingCart);
            if (shoppingCart != null && shoppingCart.length() > 0) {
                C2855es.execute(new RunnableC3029z(this, dVar, shoppingCart));
                return;
            }
            C2811dq.eForDeveloper("viewShoppingCart# shoppingCart can't be null or empty");
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: d */
    public void mo15199d(String str, AbstractC2790d dVar) {
        try {
            if (!f14345a) {
                C2811dq.eForDeveloper("SDK have not been initialized");
            } else if (TextUtils.isEmpty(str)) {
                C2811dq.eForDeveloper("onReceiveDeepLink: url could not be null or empty");
            } else {
                C2811dq.iForDeveloper("onReceiveDeepLink --> link: " + str);
                C2855es.execute(new RunnableC2669ad(this, str, dVar));
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    /* renamed from: e */
    public void mo15197e(String str, AbstractC2790d dVar) {
        try {
            if (!f14345a) {
                C2811dq.eForDeveloper("SDK have not been initialized");
                return;
            }
            C2811dq.iForDeveloper("createRole called --> roleName: " + str);
            C2855es.execute(new RunnableC2670ae(this, str, dVar));
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: a */
    private void m15234a(Context context) {
        if (C2855es.m15807a(14)) {
            Application application = null;
            try {
                if (C2664ab.f13513g instanceof Activity) {
                    application = ((Activity) C2664ab.f13513g).getApplication();
                } else if (C2664ab.f13513g instanceof Application) {
                    application = (Application) C2664ab.f13513g;
                }
                if (application != null && !f14353i) {
                    f14348d = new C2961i();
                    application.registerActivityLifecycleCallbacks(f14348d);
                    f14353i = true;
                }
            } catch (Throwable unused) {
            }
        } else {
            try {
                C2855es.m15802a((Class) Class.forName("android.app.ActivityManagerNative"), (AbstractC2847el) new C2671af(this, context), "gDefault", "android.app.IActivityManager");
                f14353i = true;
            } catch (Throwable th) {
                C2811dq.eForDeveloper("registerActivityLifecycleListener " + th.getMessage());
            }
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2680ao
    public void setAntiCheatingDisabled(boolean z) {
        try {
            C2855es.execute(new RunnableC2672ag(this, z));
        } catch (Throwable unused) {
        }
    }
}
