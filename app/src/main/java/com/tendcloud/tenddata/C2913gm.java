package com.tendcloud.tenddata;

import android.os.Handler;
import android.os.HandlerThread;
import com.github.kevinsawicki.http.HttpRequest;
import com.liulishuo.filedownloader.model.ConnectionModel;
import com.tendcloud.tenddata.C2813ds;
import com.tendcloud.tenddata.C2832ea;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.apache.tools.ant.taskdefs.WaitFor;
import org.json.JSONArray;
import org.json.JSONObject;
import p110z1.C4745bt;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.gm */
/* loaded from: classes2.dex */
public class C2913gm {

    /* renamed from: a */
    private static final int f14079a = 0;

    /* renamed from: b */
    private static final int f14080b = 1;

    /* renamed from: c */
    private static final int f14081c = 3;

    /* renamed from: i */
    private static String f14082i = null;

    /* renamed from: j */
    private static String f14083j = "TDpref_cloudcontrol";

    /* renamed from: k */
    private static final String f14084k = "TDpref_config";

    /* renamed from: l */
    private static final String f14085l = "TDpref_last_request_time";

    /* renamed from: m */
    private static final String f14086m = "TDpref_cloud_cv";

    /* renamed from: o */
    private static boolean f14088o;

    /* renamed from: s */
    private static String f14092s;

    /* renamed from: t */
    private static String f14093t;

    /* renamed from: u */
    private static String f14094u;

    /* renamed from: v */
    private static String f14095v;

    /* renamed from: d */
    private long f14098d = 720;

    /* renamed from: e */
    private long f14099e = WaitFor.ONE_WEEK;

    /* renamed from: f */
    private HashMap f14100f = new HashMap();

    /* renamed from: g */
    private HashSet f14101g = new HashSet();

    /* renamed from: h */
    private JSONObject f14102h = new JSONObject();

    /* renamed from: x */
    private int f14103x;

    /* renamed from: y */
    private Handler f14104y;

    /* renamed from: n */
    private static String[] f14087n = {"232", "206", "284", "280", "219", "230", "238", "248", "244", "208", "308", "340", "543", "546", "547", "647", "742", "262", "202", "216", "272", "222", "247", "246", "270", "278", "204", "363", "362", "260", "268", "226", "231", "293", "214", "240", "234", "235", "266", "346", "348", "350", "354", "376", "750", "454", "455", "466", "525", "310", "311", "312", "313", "314", "315", "316", "330", "332", "534", "535", "544", "302", "505", "530", "548"};

    /* renamed from: p */
    private static boolean f14089p = true;

    /* renamed from: q */
    private static boolean f14090q = false;

    /* renamed from: r */
    private static boolean f14091r = true;

    /* renamed from: w */
    private static boolean f14096w = false;

    /* renamed from: z */
    private static boolean f14097z = true;

    /* renamed from: A */
    private static volatile C2913gm f14078A = null;

    /* renamed from: a */
    public static C2913gm m15591a() {
        if (f14078A == null) {
            synchronized (C2913gm.class) {
                if (f14078A == null) {
                    f14078A = new C2913gm();
                }
            }
        }
        return f14078A;
    }

    private C2913gm() {
        this.f14104y = null;
        HandlerThread handlerThread = new HandlerThread("ModuleCloudControl");
        handlerThread.start();
        this.f14104y = new HandlerC2914gn(this, handlerThread.getLooper());
    }

    /* renamed from: a */
    public void m15584a(String str, String str2, AbstractC2790d dVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("appId", str);
        hashMap.put("channelId", str2);
        hashMap.put("Features", dVar);
        this.f14103x = m15590a(dVar);
        m15573d();
        f14090q = C2664ab.m16357a(dVar);
        if (f14090q) {
            if (!C2836ec.m15863i(C2664ab.f13513g)) {
                C2852ep.f13904a.execute(new RunnableC2915go(this));
                Handler handler = this.f14104y;
                handler.sendMessageDelayed(handler.obtainMessage(3, hashMap), 5000L);
                f14096w = true;
                return;
            }
            f14091r = false;
        }
        String q = C2836ec.m15855q(C2664ab.f13513g);
        String p = C2836ec.m15856p(C2664ab.f13513g);
        f14092s = C2855es.m15791b(q) ? "" : q.substring(0, 3);
        f14093t = C2855es.m15791b(q) ? "" : q.substring(3);
        f14094u = C2855es.m15791b(p) ? "" : p.substring(0, 3);
        f14095v = C2855es.m15791b(p) ? "" : p.substring(3);
        f14088o = m15577b(f14092s) || m15577b(f14094u);
        m15569h();
        try {
            if (C2664ab.f13523q || m15572e()) {
                this.f14104y.sendMessage(this.f14104y.obtainMessage(0, hashMap));
            }
        } catch (Throwable unused) {
        }
        f14096w = true;
    }

    /* renamed from: d */
    private void m15573d() {
        try {
            f14083j += this.f14103x;
            f14082i = C2832ea.EnumC2834b.Cloud_Control_Lock_File.toString() + this.f14103x;
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: e */
    private boolean m15572e() {
        long b = C2843eh.m15841b(C2664ab.f13513g, f14083j, f14085l, 0L);
        long currentTimeMillis = System.currentTimeMillis();
        if (b == 0) {
            return true;
        }
        long j = currentTimeMillis - b;
        return j > (this.f14098d * 60) * 1000 || j > this.f14099e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m15576b(String str, String str2, AbstractC2790d dVar) {
        try {
            JSONObject jSONObject = new JSONObject();
            m15580a(jSONObject, "bl", Integer.valueOf(this.f14103x));
            m15580a(jSONObject, "pl", (Object) 1);
            m15580a(jSONObject, C4745bt.f20077h, "4.0.30");
            m15580a(jSONObject, "smcc", f14092s);
            m15580a(jSONObject, "smnc", f14093t);
            m15580a(jSONObject, "bmcc", f14094u);
            m15580a(jSONObject, "bmnc", f14095v);
            m15580a(jSONObject, "dt", "Mobile");
            m15580a(jSONObject, "ov", C2821dv.m15946a());
            m15580a(jSONObject, C4745bt.f20080k, C2812dr.m16005l());
            m15580a(jSONObject, "px", C2821dv.m15935c(C2664ab.f13513g));
            m15580a(jSONObject, "nt", C2836ec.m15859m(C2664ab.f13513g));
            m15580a(jSONObject, "op", C2836ec.m15851u(C2664ab.f13513g));
            m15580a(jSONObject, "ch", str2);
            m15580a(jSONObject, "cv", Long.valueOf(C2843eh.m15841b(C2664ab.f13513g, f14083j, f14086m, 0L)));
            m15580a(jSONObject, "TDID", C2819dt.m15969a(C2664ab.f13513g));
            m15580a(jSONObject, "AppID", str);
            C2813ds.C2818e a = C2813ds.m15993a(C2664ab.f13513g, "cloud.xdrig.com", C2663aa.f13442L, C2663aa.f13446P, "", jSONObject.toString().getBytes(), HttpRequest.ENCODING_GZIP, "application/json");
            C2832ea.getFileLock(f14082i);
            if (a.getStatusCode() == 200) {
                m15575b(m15582a(new JSONObject(a.getResponseMsg())));
                C2843eh.m15843a(C2664ab.f13513g, f14083j, f14085l, System.currentTimeMillis());
            } else if (f14097z) {
                HashMap hashMap = new HashMap();
                hashMap.put("appId", str);
                hashMap.put("channelId", str2);
                hashMap.put("Features", dVar);
                this.f14104y.sendMessageDelayed(this.f14104y.obtainMessage(1, hashMap), 5000L);
            }
        } catch (Throwable th) {
            try {
                C2933hb.postSDKError(th);
            } finally {
                C2832ea.releaseFileLock(f14082i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public void m15571f() {
        try {
            String a = C2813ds.m15984a(C2663aa.f13452V, (String) null, false);
            if (!C2855es.m15791b(a)) {
                f14089p = new JSONObject(a).optBoolean("GDPRregion");
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    private void m15580a(JSONObject jSONObject, String str, Object obj) {
        if (obj != null) {
            try {
                if (!(obj instanceof String) || ((String) obj).length() != 0) {
                    jSONObject.put(str, obj);
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: a */
    private JSONObject m15582a(JSONObject jSONObject) {
        try {
            String b = C2843eh.m15840b(C2664ab.f13513g, f14083j, f14084k, "");
            if (!C2855es.m15791b(b)) {
                JSONObject jSONObject2 = new JSONObject(b);
                if (!jSONObject.has("cv") || jSONObject2.getInt("cv") != jSONObject.getInt("cv")) {
                    C2843eh.m15842a(C2664ab.f13513g, f14083j, f14084k, jSONObject.toString());
                    if (!jSONObject.has("cv")) {
                        return jSONObject;
                    }
                    C2843eh.m15843a(C2664ab.f13513g, f14083j, f14086m, jSONObject.getLong("cv"));
                    return jSONObject;
                }
                if (jSONObject.has("r")) {
                    jSONObject2.put("r", jSONObject.getJSONArray("r"));
                }
                C2843eh.m15842a(C2664ab.f13513g, f14083j, f14084k, jSONObject2.toString());
                return jSONObject2;
            }
            C2843eh.m15842a(C2664ab.f13513g, f14083j, f14084k, jSONObject.toString());
            return jSONObject;
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return null;
        }
    }

    /* renamed from: g */
    private void m15570g() {
        try {
            if (this.f14102h.length() == 0) {
                String a = m15587a(C2832ea.m15898b(f14082i));
                JSONObject jSONObject = null;
                if (!C2855es.m15791b(a)) {
                    jSONObject = new JSONObject(a);
                }
                if (jSONObject != null && jSONObject.length() > 0) {
                    this.f14102h = jSONObject;
                }
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: h */
    private void m15569h() {
        try {
            C2832ea.getFileLock(f14082i);
            String b = C2843eh.m15840b(C2664ab.f13513g, f14083j, f14084k, "");
            JSONObject jSONObject = null;
            if (!C2855es.m15791b(b)) {
                jSONObject = new JSONObject(b);
            }
            m15575b(jSONObject);
        } catch (Throwable th) {
            try {
                C2933hb.postSDKError(th);
            } finally {
                C2832ea.releaseFileLock(f14082i);
            }
        }
    }

    /* renamed from: b */
    private void m15575b(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                if (jSONObject.has("i")) {
                    this.f14098d = jSONObject.getInt("i");
                }
                this.f14101g = new HashSet();
                if (jSONObject.has("c") && jSONObject.has("r")) {
                    JSONArray jSONArray = jSONObject.getJSONArray("c");
                    JSONArray jSONArray2 = jSONObject.getJSONArray("r");
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                        int i2 = jSONObject2.getInt(ConnectionModel.f10389a);
                        for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                            if (i2 == jSONArray2.getInt(i3)) {
                                m15581a(jSONObject2, String.valueOf(i2));
                            }
                            this.f14101g.add(String.valueOf(i2));
                        }
                    }
                    if (jSONArray.length() == 0) {
                        this.f14100f = new HashMap();
                    }
                    m15570g();
                    m15568i();
                }
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }
    }

    /* renamed from: a */
    private void m15581a(JSONObject jSONObject, String str) {
        try {
            JSONArray jSONArray = (JSONArray) jSONObject.remove("clt");
            jSONObject.remove(ConnectionModel.f10389a);
            for (int i = 0; i < jSONArray.length(); i++) {
                String string = jSONArray.getString(i);
                if (!C2855es.m15791b(string)) {
                    if (this.f14100f.containsKey(string)) {
                        ((JSONObject) this.f14100f.get(string)).put(str, jSONObject);
                    } else {
                        this.f14100f.put(string, new JSONObject().put(str, jSONObject));
                    }
                }
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: a */
    public JSONArray m15585a(String str) {
        return m15583a(str, (JSONObject) null, (JSONObject) null);
    }

    /* renamed from: a */
    public JSONArray m15583a(String str, JSONObject jSONObject, JSONObject jSONObject2) {
        JSONArray jSONArray = new JSONArray();
        if (!f14096w) {
            return jSONArray;
        }
        try {
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
        if (C2664ab.f13523q) {
            return jSONArray;
        }
        if (!f14090q || !f14091r) {
            if (this.f14100f.containsKey(str)) {
                if (!(jSONObject == null || jSONObject2 == null)) {
                    if (jSONObject.has(str)) {
                        return jSONArray;
                    }
                    if (jSONObject2.has(str)) {
                        return null;
                    }
                    jSONObject2.put(str, true);
                }
                JSONObject jSONObject3 = (JSONObject) this.f14100f.get(str);
                Iterator<String> keys = jSONObject3.keys();
                ArrayList arrayList = new ArrayList();
                m15570g();
                while (keys.hasNext()) {
                    String next = keys.next();
                    arrayList.add(next);
                    JSONObject jSONObject4 = jSONObject3.getJSONObject(next);
                    long j = jSONObject4.getLong("st");
                    long j2 = jSONObject4.getLong("et");
                    int i = jSONObject4.getInt("cn");
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis >= j && currentTimeMillis <= j2 && i > 0) {
                        if (this.f14102h.has(str)) {
                            JSONObject jSONObject5 = this.f14102h.getJSONObject(str);
                            if (!jSONObject5.has(next)) {
                                this.f14102h.put(str, jSONObject5.put(next, 1));
                                jSONArray.put(Integer.parseInt(next));
                            } else if (jSONObject5.getInt(next) < i) {
                                jSONArray.put(Integer.parseInt(next));
                                jSONObject5.put(next, jSONObject5.getInt(next) + 1);
                                this.f14102h.put(str, jSONObject5);
                            }
                        } else {
                            this.f14102h.put(str, new JSONObject().put(next, 1));
                            jSONArray.put(Integer.parseInt(next));
                        }
                    }
                }
                if (jSONArray.length() == 0) {
                    return null;
                }
                return jSONArray;
            } else if (f14088o) {
                return jSONArray;
            } else {
                return null;
            }
        } else if (f14089p) {
            return null;
        } else {
            return jSONArray;
        }
    }

    /* renamed from: i */
    private void m15568i() {
        try {
            Iterator<String> keys = this.f14102h.keys();
            while (keys.hasNext()) {
                JSONObject jSONObject = this.f14102h.getJSONObject(keys.next());
                Iterator<String> keys2 = jSONObject.keys();
                ArrayList arrayList = new ArrayList();
                while (keys2.hasNext()) {
                    String next = keys2.next();
                    if (!this.f14101g.contains(next)) {
                        arrayList.add(next);
                    }
                }
                for (int i = 0; i < arrayList.size(); i++) {
                    jSONObject.remove((String) arrayList.get(i));
                }
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: a */
    private int m15590a(AbstractC2790d dVar) {
        int index = dVar.index();
        if (index == 3) {
            return 3;
        }
        switch (index) {
            case 0:
                return 1;
            case 1:
                return 2;
            default:
                return -1;
        }
    }

    /* renamed from: a */
    private void m15586a(RandomAccessFile randomAccessFile, String str) {
        try {
            randomAccessFile.setLength(0L);
            randomAccessFile.seek(0L);
            randomAccessFile.write(str.getBytes());
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    private String m15587a(RandomAccessFile randomAccessFile) {
        try {
            byte[] bArr = new byte[(int) randomAccessFile.length()];
            randomAccessFile.seek(0L);
            randomAccessFile.read(bArr);
            return new String(bArr);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: b */
    public String m15578b() {
        return f14082i;
    }

    /* renamed from: b */
    private boolean m15577b(String str) {
        try {
            if (C2855es.m15791b(str)) {
                return false;
            }
            for (int i = 0; i < f14087n.length; i++) {
                if (str.equals(f14087n[i])) {
                    return false;
                }
            }
            return true;
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return true;
        }
    }

    /* renamed from: c */
    public void m15574c() {
        m15586a(C2832ea.m15898b(f14082i), this.f14102h.toString());
        this.f14102h = new JSONObject();
    }
}
