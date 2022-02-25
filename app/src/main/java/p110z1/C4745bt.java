package p110z1;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.SystemClock;
import android.text.TextUtils;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Locale;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: z1.bt */
/* loaded from: classes3.dex */
public class C4745bt {

    /* renamed from: a */
    public static final String f20070a = "\"&";

    /* renamed from: b */
    public static final String f20071b = "&";

    /* renamed from: c */
    public static final String f20072c = "bizcontext=\"";

    /* renamed from: d */
    public static final String f20073d = "bizcontext=";

    /* renamed from: e */
    public static final String f20074e = "\"";

    /* renamed from: f */
    public static final String f20075f = "appkey";

    /* renamed from: g */
    public static final String f20076g = "ty";

    /* renamed from: h */
    public static final String f20077h = "sv";

    /* renamed from: i */
    public static final String f20078i = "an";

    /* renamed from: j */
    public static final String f20079j = "setting";

    /* renamed from: k */
    public static final String f20080k = "av";

    /* renamed from: l */
    public static final String f20081l = "sdk_start_time";

    /* renamed from: m */
    public static final String f20082m = "extInfo";

    /* renamed from: n */
    public static final String f20083n = "ap_link_token";

    /* renamed from: o */
    public static final String f20084o = "UTF-8";

    /* renamed from: p */
    public final String f20085p;

    /* renamed from: q */
    public final long f20086q = SystemClock.elapsedRealtime();

    /* renamed from: r */
    public final String f20087r;

    /* renamed from: s */
    public final C3857aq f20088s;

    /* renamed from: t */
    private String f20089t;

    /* renamed from: u */
    private String f20090u;

    /* renamed from: v */
    private Context f20091v;

    /* renamed from: a */
    public static C4745bt m9418a() {
        return null;
    }

    /* renamed from: z1.bt$a */
    /* loaded from: classes3.dex */
    public static final class C4746a {

        /* renamed from: a */
        private static final HashMap<UUID, C4745bt> f20092a = new HashMap<>();

        /* renamed from: b */
        private static final HashMap<String, C4745bt> f20093b = new HashMap<>();

        /* renamed from: c */
        private static final String f20094c = "i_uuid_b_c";

        /* renamed from: a */
        public static void m9403a(C4745bt btVar, Intent intent) {
            if (btVar != null && intent != null) {
                UUID randomUUID = UUID.randomUUID();
                f20092a.put(randomUUID, btVar);
                intent.putExtra(f20094c, randomUUID);
            }
        }

        /* renamed from: a */
        public static C4745bt m9405a(Intent intent) {
            if (intent == null) {
                return null;
            }
            Serializable serializableExtra = intent.getSerializableExtra(f20094c);
            if (serializableExtra instanceof UUID) {
                return f20092a.remove((UUID) serializableExtra);
            }
            return null;
        }

        /* renamed from: a */
        public static void m9402a(C4745bt btVar, String str) {
            if (btVar != null && !TextUtils.isEmpty(str)) {
                f20093b.put(str, btVar);
            }
        }

        /* renamed from: a */
        public static C4745bt m9404a(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return f20093b.remove(str);
        }
    }

    public C4745bt(Context context, String str, String str2) {
        this.f20089t = "";
        this.f20090u = "";
        this.f20091v = null;
        boolean isEmpty = TextUtils.isEmpty(str2);
        this.f20088s = new C3857aq(context, isEmpty);
        this.f20085p = m9407c(str, this.f20090u);
        this.f20087r = str2;
        if (!isEmpty) {
            C3754ao.m12151b(this, C3857aq.f17251b, "eptyp", str2 + "|" + this.f20085p);
        }
        try {
            this.f20091v = context.getApplicationContext();
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            this.f20089t = packageInfo.versionName;
            this.f20090u = packageInfo.packageName;
        } catch (Exception e) {
            C4921cd.m5618a(e);
        }
        if (!isEmpty) {
            C3754ao.m12151b(this, C3857aq.f17251b, C3857aq.f17217I, "" + SystemClock.elapsedRealtime());
            C3754ao.m12158a(context, this, str, this.f20085p);
        }
    }

    /* renamed from: b */
    public Context m9412b() {
        return this.f20091v;
    }

    /* renamed from: a */
    public String m9417a(String str) {
        if (TextUtils.isEmpty(str) || str.startsWith("new_external_info==")) {
            return str;
        }
        if (m9411b(str)) {
            return m9408c(str);
        }
        return m9406d(str);
    }

    /* renamed from: b */
    private boolean m9411b(String str) {
        return !str.contains(f20070a);
    }

    /* renamed from: c */
    private String m9408c(String str) {
        try {
            String a = m9415a(str, f20071b, f20073d);
            if (TextUtils.isEmpty(a)) {
                str = str + f20071b + m9410b(f20073d, "");
            } else {
                int indexOf = str.indexOf(a);
                str = str.substring(0, indexOf) + m9414a(a, f20073d, "", true) + str.substring(indexOf + a.length());
            }
        } catch (Throwable unused) {
        }
        return str;
    }

    /* renamed from: d */
    private String m9406d(String str) {
        try {
            String a = m9415a(str, f20070a, f20072c);
            if (TextUtils.isEmpty(a)) {
                return str + f20071b + m9410b(f20072c, "\"");
            }
            if (!a.endsWith("\"")) {
                a = a + "\"";
            }
            int indexOf = str.indexOf(a);
            return str.substring(0, indexOf) + m9414a(a, f20072c, "\"", false) + str.substring(indexOf + a.length());
        } catch (Throwable unused) {
            return str;
        }
    }

    /* renamed from: a */
    private String m9415a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(str2);
        for (int i = 0; i < split.length; i++) {
            if (!TextUtils.isEmpty(split[i]) && split[i].startsWith(str3)) {
                return split[i];
            }
        }
        return null;
    }

    /* renamed from: b */
    private String m9410b(String str, String str2) throws JSONException, UnsupportedEncodingException {
        String a = m9416a("", "");
        return str + a + str2;
    }

    /* renamed from: a */
    public String m9416a(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(f20075f, C3876ar.f17429f);
            jSONObject.put(f20076g, "and_lite");
            jSONObject.put(f20077h, "h.a.3.7.3");
            if (!this.f20090u.contains(f20079j) || !C5019co.m4486b(this.f20091v)) {
                jSONObject.put(f20078i, this.f20090u);
            }
            jSONObject.put(f20080k, this.f20089t);
            jSONObject.put(f20081l, System.currentTimeMillis());
            jSONObject.put(f20082m, m9409c());
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put(str, str2);
            }
            return jSONObject.toString();
        } catch (Throwable th) {
            C4921cd.m5618a(th);
            return "";
        }
    }

    /* renamed from: a */
    private String m9414a(String str, String str2, String str3, boolean z) throws JSONException, UnsupportedEncodingException {
        JSONObject jSONObject;
        String substring = str.substring(str2.length());
        boolean z2 = false;
        String substring2 = substring.substring(0, substring.length() - str3.length());
        if (substring2.length() < 2 || !substring2.startsWith("\"") || !substring2.endsWith("\"")) {
            jSONObject = new JSONObject(substring2);
        } else {
            jSONObject = new JSONObject(substring2.substring(1, substring2.length() - 1));
            z2 = true;
        }
        if (!jSONObject.has(f20075f)) {
            jSONObject.put(f20075f, C3876ar.f17429f);
        }
        if (!jSONObject.has(f20076g)) {
            jSONObject.put(f20076g, "and_lite");
        }
        if (!jSONObject.has(f20077h)) {
            jSONObject.put(f20077h, "h.a.3.7.3");
        }
        if (!jSONObject.has(f20078i) && (!this.f20090u.contains(f20079j) || !C5019co.m4486b(this.f20091v))) {
            jSONObject.put(f20078i, this.f20090u);
        }
        if (!jSONObject.has(f20080k)) {
            jSONObject.put(f20080k, this.f20089t);
        }
        if (!jSONObject.has(f20081l)) {
            jSONObject.put(f20081l, System.currentTimeMillis());
        }
        if (!jSONObject.has(f20082m)) {
            jSONObject.put(f20082m, m9409c());
        }
        String jSONObject2 = jSONObject.toString();
        if (z2) {
            jSONObject2 = "\"" + jSONObject2 + "\"";
        }
        return str2 + jSONObject2 + str3;
    }

    /* renamed from: c */
    private JSONObject m9409c() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(f20083n, this.f20085p);
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    /* renamed from: c */
    private static String m9407c(String str, String str2) {
        try {
            Locale locale = Locale.getDefault();
            Object[] objArr = new Object[4];
            if (str == null) {
                str = "";
            }
            objArr[0] = str;
            if (str2 == null) {
                str2 = "";
            }
            objArr[1] = str2;
            objArr[2] = Long.valueOf(System.currentTimeMillis());
            objArr[3] = UUID.randomUUID().toString();
            return String.format("EP%s%s_%s", "1", C5019co.m4472f(String.format(locale, "%s%s%d%s", objArr)), Long.valueOf(System.currentTimeMillis()));
        } catch (Throwable unused) {
            return "-";
        }
    }

    /* renamed from: a */
    public static HashMap<String, String> m9413a(C4745bt btVar) {
        HashMap<String, String> hashMap = new HashMap<>();
        if (btVar != null) {
            hashMap.put("token", btVar.f20085p);
            hashMap.put("call_type", btVar.f20087r);
            hashMap.put("ts_api_invoke", String.valueOf(btVar.f20086q));
        }
        return hashMap;
    }
}
