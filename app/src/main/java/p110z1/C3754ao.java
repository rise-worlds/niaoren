package p110z1;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;

/* renamed from: z1.ao */
/* loaded from: classes3.dex */
public class C3754ao {

    /* renamed from: z1.ao$c */
    /* loaded from: classes3.dex */
    static final class C3758c {

        /* renamed from: a */
        private static final String f16939a = "alipay_cashier_statistic_v";

        C3758c() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: a */
        public static synchronized long m12139a(Context context) {
            long j;
            synchronized (C3758c.class) {
                long j2 = 0;
                try {
                    String b = C4964ck.m5125b(null, context, f16939a, null);
                    if (!TextUtils.isEmpty(b)) {
                        j2 = Long.parseLong(b);
                    }
                } catch (Throwable unused) {
                }
                j = j2 + 1;
                try {
                    C4964ck.m5127a(null, context, f16939a, Long.toString(j));
                } catch (Throwable unused2) {
                }
            }
            return j;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: z1.ao$a */
    /* loaded from: classes3.dex */
    public static final class C3755a {

        /* renamed from: a */
        private static final String f16936a = "RecordPref";

        /* renamed from: b */
        private static final String f16937b = "alipay_cashier_statistic_record";

        private C3755a() {
        }

        /* renamed from: a */
        static synchronized String m12148a(Context context, String str, String str2) {
            synchronized (C3755a.class) {
                C4921cd.m5620a(f16936a, "stat append " + str2 + " , " + str);
                if (context != null && !TextUtils.isEmpty(str)) {
                    if (TextUtils.isEmpty(str2)) {
                        str2 = UUID.randomUUID().toString();
                    }
                    C3756a b = m12146b(context);
                    if (b.f16938a.size() > 20) {
                        b.f16938a.clear();
                    }
                    b.f16938a.put(str2, str);
                    m12147a(context, b);
                    return str2;
                }
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: a */
        public static synchronized String m12150a(Context context) {
            synchronized (C3755a.class) {
                C4921cd.m5620a(f16936a, "stat peek");
                if (context == null) {
                    return null;
                }
                C3756a b = m12146b(context);
                if (b.f16938a.isEmpty()) {
                    return null;
                }
                return b.f16938a.entrySet().iterator().next().getValue();
            }
        }

        /* renamed from: a */
        static synchronized int m12149a(Context context, String str) {
            synchronized (C3755a.class) {
                C4921cd.m5620a(f16936a, "stat remove " + str);
                if (context != null && !TextUtils.isEmpty(str)) {
                    C3756a b = m12146b(context);
                    if (b.f16938a.isEmpty()) {
                        return 0;
                    }
                    ArrayList arrayList = new ArrayList();
                    for (Map.Entry<String, String> entry : b.f16938a.entrySet()) {
                        if (str.equals(entry.getValue())) {
                            arrayList.add(entry.getKey());
                        }
                    }
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        b.f16938a.remove((String) it.next());
                    }
                    m12147a(context, b);
                    return arrayList.size();
                }
                return 0;
            }
        }

        /* renamed from: b */
        private static synchronized C3756a m12146b(Context context) {
            synchronized (C3755a.class) {
                try {
                    String b = C4964ck.m5125b(null, context, f16937b, null);
                    if (TextUtils.isEmpty(b)) {
                        return new C3756a();
                    }
                    return new C3756a(b);
                } catch (Throwable th) {
                    C4921cd.m5618a(th);
                    return new C3756a();
                }
            }
        }

        /* renamed from: a */
        private static synchronized void m12147a(Context context, C3756a aVar) {
            synchronized (C3755a.class) {
                if (aVar == null) {
                    try {
                        aVar = new C3756a();
                    } catch (Throwable th) {
                        try {
                            C4921cd.m5618a(th);
                        } catch (Throwable th2) {
                            throw th2;
                        }
                    }
                }
                C4964ck.m5127a(null, context, f16937b, aVar.m12145a());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: z1.ao$a$a */
        /* loaded from: classes3.dex */
        public static final class C3756a {

            /* renamed from: a */
            final LinkedHashMap<String, String> f16938a = new LinkedHashMap<>();

            C3756a() {
            }

            C3756a(String str) {
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONArray jSONArray2 = jSONArray.getJSONArray(i);
                        this.f16938a.put(jSONArray2.getString(0), jSONArray2.getString(1));
                    }
                } catch (Throwable th) {
                    C4921cd.m5618a(th);
                }
            }

            /* renamed from: a */
            String m12145a() {
                try {
                    JSONArray jSONArray = new JSONArray();
                    for (Map.Entry<String, String> entry : this.f16938a.entrySet()) {
                        JSONArray jSONArray2 = new JSONArray();
                        jSONArray2.put(entry.getKey()).put(entry.getValue());
                        jSONArray.put(jSONArray2);
                    }
                    return jSONArray.toString();
                } catch (Throwable th) {
                    C4921cd.m5618a(th);
                    return new JSONArray().toString();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: z1.ao$b */
    /* loaded from: classes3.dex */
    public static final class C3757b {
        private C3757b() {
        }

        /* renamed from: a */
        static synchronized void m12141a(Context context, C3857aq aqVar, String str, String str2) {
            synchronized (C3757b.class) {
                if (context != null && aqVar != null && str != null) {
                    m12142a(context, aqVar.m11646a(str), str2);
                }
            }
        }

        /* renamed from: a */
        static synchronized void m12144a(Context context) {
            synchronized (C3757b.class) {
                m12142a(context, null, null);
            }
        }

        /* renamed from: a */
        private static synchronized void m12142a(Context context, String str, String str2) {
            synchronized (C3757b.class) {
                if (context != null) {
                    if (!TextUtils.isEmpty(str)) {
                        C3755a.m12148a(context, str, str2);
                    }
                    new Thread(new RunnableC3828ap(str, context)).start();
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public static synchronized boolean m12140b(Context context, String str) {
            synchronized (C3757b.class) {
                C4921cd.m5620a(C3876ar.f17447x, "stat sub " + str);
                if ((C3894au.m9967i().m9969g() ? new C4629bo() : new C4670bp()).mo9548a((C4745bt) null, context, str) == null) {
                    return false;
                }
                C3755a.m12149a(context, str);
                return true;
            }
        }
    }

    private C3754ao() {
    }

    /* renamed from: a */
    public static synchronized void m12158a(Context context, C4745bt btVar, String str, String str2) {
        synchronized (C3754ao.class) {
            if (context != null && btVar != null) {
                try {
                    C3755a.m12148a(context, btVar.f20088s.m11646a(str), str2);
                } catch (Throwable th) {
                    C4921cd.m5618a(th);
                }
            }
        }
    }

    /* renamed from: b */
    public static synchronized void m12152b(Context context, C4745bt btVar, String str, String str2) {
        synchronized (C3754ao.class) {
            if (context != null && btVar != null) {
                C3757b.m12141a(context, btVar.f20088s, str, str2);
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m12159a(Context context) {
        synchronized (C3754ao.class) {
            C3757b.m12144a(context);
        }
    }

    /* renamed from: a */
    public static void m12153a(C4745bt btVar, String str, Throwable th) {
        if (btVar != null && th != null && th.getClass() != null) {
            btVar.f20088s.m11643a(str, th.getClass().getSimpleName(), th);
        }
    }

    /* renamed from: a */
    public static void m12154a(C4745bt btVar, String str, String str2, Throwable th, String str3) {
        if (btVar != null) {
            btVar.f20088s.m11642a(str, str2, th, str3);
        }
    }

    /* renamed from: a */
    public static void m12155a(C4745bt btVar, String str, String str2, Throwable th) {
        if (btVar != null) {
            btVar.f20088s.m11643a(str, str2, th);
        }
    }

    /* renamed from: a */
    public static void m12156a(C4745bt btVar, String str, String str2, String str3) {
        if (btVar != null) {
            btVar.f20088s.m11644a(str, str2, str3);
        }
    }

    /* renamed from: b */
    public static void m12151b(C4745bt btVar, String str, String str2, String str3) {
        if (btVar != null) {
            btVar.f20088s.m11637b(str, str2, str3);
        }
    }

    /* renamed from: a */
    public static void m12157a(C4745bt btVar, String str, String str2) {
        if (btVar != null) {
            btVar.f20088s.m11645a(str, str2);
        }
    }
}
