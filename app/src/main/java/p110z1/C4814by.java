package p110z1;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.alipay.sdk.app.C0650b;
import java.util.Random;
import org.json.JSONObject;

/* renamed from: z1.by */
/* loaded from: classes3.dex */
public class C4814by {

    /* renamed from: a */
    public static final String f20402a = "alipay_tid_storage";

    /* renamed from: b */
    public static final String f20403b = "tidinfo";

    /* renamed from: c */
    public static final String f20404c = "upgraded_from_db";

    /* renamed from: d */
    public static final String f20405d = "tid";

    /* renamed from: e */
    public static final String f20406e = "client_key";

    /* renamed from: f */
    public static final String f20407f = "timestamp";

    /* renamed from: g */
    public static final String f20408g = "vimei";

    /* renamed from: h */
    public static final String f20409h = "vimsi";

    /* renamed from: i */
    private static Context f20410i;

    /* renamed from: o */
    private static C4814by f20411o;

    /* renamed from: j */
    private String f20412j;

    /* renamed from: k */
    private String f20413k;

    /* renamed from: l */
    private long f20414l;

    /* renamed from: m */
    private String f20415m;

    /* renamed from: n */
    private String f20416n;

    /* renamed from: p */
    private boolean f20417p = false;

    /* renamed from: o */
    private void m8359o() {
    }

    /* renamed from: a */
    public static synchronized C4814by m8377a(Context context) {
        C4814by byVar;
        synchronized (C4814by.class) {
            if (f20411o == null) {
                f20411o = new C4814by();
            }
            if (f20410i == null) {
                f20411o.m8372b(context);
            }
            byVar = f20411o;
        }
        return byVar;
    }

    /* renamed from: b */
    private void m8372b(Context context) {
        if (context != null) {
            f20410i = context.getApplicationContext();
        }
        if (!this.f20417p) {
            this.f20417p = true;
            m8363k();
            m8362l();
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:18:0x0051
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* renamed from: k */
    private void m8363k() {
        /*
            r6 = this;
            android.content.Context r0 = p110z1.C4814by.f20410i
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            java.lang.String r1 = "alipay_tid_storage"
            java.lang.String r2 = "upgraded_from_db"
            boolean r1 = p110z1.C4814by.C4815a.m8349d(r1, r2)
            if (r1 == 0) goto L_0x0017
            java.lang.String r0 = "mspl"
            java.lang.String r1 = "tid_mig: pass"
            p110z1.C4921cd.m5620a(r0, r1)
            return
        L_0x0017:
            r1 = 0
            java.lang.String r2 = "mspl"
            java.lang.String r3 = "tid_mig: from db"
            p110z1.C4921cd.m5620a(r2, r3)     // Catch: Throwable -> 0x0054
            z1.bx r2 = new z1.bx     // Catch: Throwable -> 0x0054
            r2.<init>(r0)     // Catch: Throwable -> 0x0054
            z1.ca r1 = p110z1.C4873ca.m6517a(r0)     // Catch: Throwable -> 0x004f
            java.lang.String r1 = r1.m6516b()     // Catch: Throwable -> 0x004f
            z1.ca r3 = p110z1.C4873ca.m6517a(r0)     // Catch: Throwable -> 0x004f
            java.lang.String r3 = r3.m6518a()     // Catch: Throwable -> 0x004f
            java.lang.String r4 = r2.m8748a(r1, r3)     // Catch: Throwable -> 0x004f
            java.lang.String r1 = r2.m8747b(r1, r3)     // Catch: Throwable -> 0x004f
            boolean r3 = android.text.TextUtils.isEmpty(r4)     // Catch: Throwable -> 0x004f
            if (r3 != 0) goto L_0x004b
            boolean r3 = android.text.TextUtils.isEmpty(r1)     // Catch: Throwable -> 0x004f
            if (r3 != 0) goto L_0x004b
            r6.m8376a(r4, r1)     // Catch: Throwable -> 0x004f
        L_0x004b:
            r2.close()
            goto L_0x005e
        L_0x004f:
            r1 = move-exception
            goto L_0x0058
        L_0x0051:
            r0 = move-exception
            r2 = r1
            goto L_0x0094
        L_0x0054:
            r2 = move-exception
            r5 = r2
            r2 = r1
            r1 = r5
        L_0x0058:
            p110z1.C4921cd.m5618a(r1)     // Catch: all -> 0x0093
            if (r2 == 0) goto L_0x005e
            goto L_0x004b
        L_0x005e:
            java.lang.String r1 = "mspl"
            java.lang.String r3 = "tid_mig: db purge"
            p110z1.C4921cd.m5620a(r1, r3)     // Catch: Throwable -> 0x0079
            z1.bx r1 = new z1.bx     // Catch: Throwable -> 0x0079
            r1.<init>(r0)     // Catch: Throwable -> 0x0079
            r1.m8749a()     // Catch: Throwable -> 0x0074
            r1.close()
            goto L_0x0082
        L_0x0071:
            r0 = move-exception
            r2 = r1
            goto L_0x008d
        L_0x0074:
            r0 = move-exception
            r2 = r1
            goto L_0x007a
        L_0x0077:
            r0 = move-exception
            goto L_0x008d
        L_0x0079:
            r0 = move-exception
        L_0x007a:
            p110z1.C4921cd.m5618a(r0)     // Catch: all -> 0x0077
            if (r2 == 0) goto L_0x0082
            r2.close()
        L_0x0082:
            java.lang.String r0 = "alipay_tid_storage"
            java.lang.String r1 = "upgraded_from_db"
            java.lang.String r2 = "updated"
            r3 = 0
            p110z1.C4814by.C4815a.m8354a(r0, r1, r2, r3)
            return
        L_0x008d:
            if (r2 == 0) goto L_0x0092
            r2.close()
        L_0x0092:
            throw r0
        L_0x0093:
            r0 = move-exception
        L_0x0094:
            if (r2 == 0) goto L_0x0099
            r2.close()
        L_0x0099:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.C4814by.m8363k():void");
    }

    /* renamed from: a */
    public String m8378a() {
        return this.f20412j;
    }

    /* renamed from: b */
    public String m8373b() {
        return this.f20413k;
    }

    /* renamed from: c */
    public String m8371c() {
        return this.f20415m;
    }

    /* renamed from: d */
    public String m8370d() {
        return this.f20416n;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0076  */
    /* renamed from: l */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m8362l() {
        /*
            r9 = this;
            long r0 = java.lang.System.currentTimeMillis()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r1 = 0
            java.lang.String r2 = "alipay_tid_storage"
            java.lang.String r3 = "tidinfo"
            r4 = 1
            java.lang.String r2 = p110z1.C4814by.C4815a.m8353a(r2, r3, r4)     // Catch: Exception -> 0x005b
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch: Exception -> 0x005b
            if (r3 != 0) goto L_0x0057
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: Exception -> 0x005b
            r3.<init>(r2)     // Catch: Exception -> 0x005b
            java.lang.String r2 = "tid"
            java.lang.String r4 = ""
            java.lang.String r2 = r3.optString(r2, r4)     // Catch: Exception -> 0x005b
            java.lang.String r4 = "client_key"
            java.lang.String r5 = ""
            java.lang.String r4 = r3.optString(r4, r5)     // Catch: Exception -> 0x0054
            java.lang.String r5 = "timestamp"
            long r6 = java.lang.System.currentTimeMillis()     // Catch: Exception -> 0x0051
            long r5 = r3.optLong(r5, r6)     // Catch: Exception -> 0x0051
            java.lang.Long r0 = java.lang.Long.valueOf(r5)     // Catch: Exception -> 0x0051
            java.lang.String r5 = "vimei"
            java.lang.String r6 = ""
            java.lang.String r5 = r3.optString(r5, r6)     // Catch: Exception -> 0x0051
            java.lang.String r6 = "vimsi"
            java.lang.String r7 = ""
            java.lang.String r1 = r3.optString(r6, r7)     // Catch: Exception -> 0x004f
            r8 = r2
            r2 = r1
            r1 = r8
            goto L_0x0065
        L_0x004f:
            r3 = move-exception
            goto L_0x005f
        L_0x0051:
            r3 = move-exception
            r5 = r1
            goto L_0x005f
        L_0x0054:
            r3 = move-exception
            r4 = r1
            goto L_0x005e
        L_0x0057:
            r2 = r1
            r4 = r2
            r5 = r4
            goto L_0x0065
        L_0x005b:
            r3 = move-exception
            r2 = r1
            r4 = r2
        L_0x005e:
            r5 = r4
        L_0x005f:
            p110z1.C4921cd.m5618a(r3)
            r8 = r2
            r2 = r1
            r1 = r8
        L_0x0065:
            java.lang.String r3 = "mspl"
            java.lang.String r6 = "tid_str: load"
            p110z1.C4921cd.m5620a(r3, r6)
            boolean r3 = r9.m8375a(r1, r4, r5, r2)
            if (r3 == 0) goto L_0x0076
            r9.m8361m()
            goto L_0x0084
        L_0x0076:
            r9.f20412j = r1
            r9.f20413k = r4
            long r0 = r0.longValue()
            r9.f20414l = r0
            r9.f20415m = r5
            r9.f20416n = r2
        L_0x0084:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.C4814by.m8362l():void");
    }

    /* renamed from: a */
    private boolean m8375a(String str, String str2, String str3, String str4) {
        return TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4);
    }

    /* renamed from: e */
    public boolean m8369e() {
        return TextUtils.isEmpty(this.f20412j) || TextUtils.isEmpty(this.f20413k) || TextUtils.isEmpty(this.f20415m) || TextUtils.isEmpty(this.f20416n);
    }

    /* renamed from: m */
    private void m8361m() {
        this.f20412j = "";
        this.f20413k = m8368f();
        this.f20414l = System.currentTimeMillis();
        this.f20415m = m8360n();
        this.f20416n = m8360n();
        C4815a.m8351b(f20402a, f20403b);
    }

    /* renamed from: n */
    private String m8360n() {
        String hexString = Long.toHexString(System.currentTimeMillis());
        Random random = new Random();
        return hexString + (random.nextInt(C0650b.f298a) + 1000);
    }

    /* renamed from: f */
    public String m8368f() {
        String hexString = Long.toHexString(System.currentTimeMillis());
        return hexString.length() > 10 ? hexString.substring(hexString.length() - 10) : hexString;
    }

    /* renamed from: g */
    public void m8367g() {
        C4921cd.m5620a(C3876ar.f17447x, "tid_str: del");
        m8361m();
    }

    /* renamed from: h */
    public boolean m8366h() {
        return m8369e();
    }

    /* renamed from: i */
    public Long m8365i() {
        return Long.valueOf(this.f20414l);
    }

    /* renamed from: a */
    public void m8376a(String str, String str2) {
        C4921cd.m5620a(C3876ar.f17447x, "tid_str: save");
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            this.f20412j = str;
            this.f20413k = str2;
            this.f20414l = System.currentTimeMillis();
            m8358p();
            m8359o();
        }
    }

    /* renamed from: a */
    private void m8374a(String str, String str2, String str3, String str4, Long l) {
        if (!m8375a(str, str2, str3, str4)) {
            this.f20412j = str;
            this.f20413k = str2;
            this.f20415m = str3;
            this.f20416n = str4;
            if (l == null) {
                this.f20414l = System.currentTimeMillis();
            } else {
                this.f20414l = l.longValue();
            }
            m8358p();
        }
    }

    /* renamed from: p */
    private void m8358p() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("tid", this.f20412j);
            jSONObject.put(f20406e, this.f20413k);
            jSONObject.put(f20407f, this.f20414l);
            jSONObject.put(f20408g, this.f20415m);
            jSONObject.put(f20409h, this.f20416n);
            C4815a.m8354a(f20402a, f20403b, jSONObject.toString(), true);
        } catch (Exception e) {
            C4921cd.m5618a(e);
        }
    }

    /* renamed from: z1.by$a */
    /* loaded from: classes3.dex */
    public static class C4815a {
        /* renamed from: a */
        private static String m8357a() {
            return "!@#23457";
        }

        /* renamed from: a */
        public static boolean m8356a(String str, String str2) {
            if (C4814by.f20410i == null) {
                return false;
            }
            return C4814by.f20410i.getSharedPreferences(str, 0).contains(str2);
        }

        /* renamed from: b */
        public static void m8351b(String str, String str2) {
            if (C4814by.f20410i != null) {
                C4814by.f20410i.getSharedPreferences(str, 0).edit().remove(str2).apply();
            }
        }

        /* renamed from: c */
        public static String m8350c(String str, String str2) {
            return m8353a(str, str2, true);
        }

        /* renamed from: d */
        public static boolean m8349d(String str, String str2) {
            if (C4814by.f20410i == null) {
                return false;
            }
            return C4814by.f20410i.getSharedPreferences(str, 0).contains(str2);
        }

        /* renamed from: a */
        public static String m8353a(String str, String str2, boolean z) {
            if (C4814by.f20410i == null) {
                return null;
            }
            String string = C4814by.f20410i.getSharedPreferences(str, 0).getString(str2, null);
            if (!TextUtils.isEmpty(string) && z) {
                String b = C3990az.m9818b(string, m8352b());
                if (TextUtils.isEmpty(b)) {
                    String b2 = C3990az.m9818b(string, m8357a());
                    if (!TextUtils.isEmpty(b2)) {
                        m8354a(str, str2, b2, true);
                    }
                    string = b2;
                } else {
                    string = b;
                }
                if (TextUtils.isEmpty(string)) {
                    C4921cd.m5620a(C3876ar.f17447x, "tid_str: pref failed");
                }
            }
            C4921cd.m5620a(C3876ar.f17447x, "tid_str: from local");
            return string;
        }

        /* renamed from: a */
        public static void m8355a(String str, String str2, String str3) {
            m8354a(str, str2, str3, true);
        }

        /* renamed from: a */
        public static void m8354a(String str, String str2, String str3, boolean z) {
            if (C4814by.f20410i != null) {
                SharedPreferences sharedPreferences = C4814by.f20410i.getSharedPreferences(str, 0);
                if (z) {
                    String b = m8352b();
                    String a = C3990az.m9819a(str3, b);
                    if (TextUtils.isEmpty(a)) {
                        String.format("LocalPreference::putLocalPreferences failed %s，%s", str3, b);
                    }
                    str3 = a;
                }
                sharedPreferences.edit().putString(str2, str3).apply();
            }
        }

        /* renamed from: b */
        private static String m8352b() {
            String str = "";
            try {
                str = C4814by.f20410i.getApplicationContext().getPackageName();
            } catch (Throwable th) {
                C4921cd.m5618a(th);
            }
            if (TextUtils.isEmpty(str)) {
                str = "unknow";
            }
            return (str + "00000000").substring(0, 8);
        }
    }
}
