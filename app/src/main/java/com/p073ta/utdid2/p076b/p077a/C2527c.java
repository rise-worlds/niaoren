package com.p073ta.utdid2.p076b.p077a;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import com.p073ta.utdid2.p074a.p075a.C2521g;
import com.p073ta.utdid2.p076b.p077a.AbstractC2524b;
import java.io.File;
import java.util.Map;

/* renamed from: com.ta.utdid2.b.a.c */
/* loaded from: classes2.dex */
public class C2527c {

    /* renamed from: a */
    private SharedPreferences f12695a;

    /* renamed from: a */
    private AbstractC2524b f12697a;

    /* renamed from: a */
    private C2528d f12698a;

    /* renamed from: b */
    private String f12699b;

    /* renamed from: c */
    private String f12700c;

    /* renamed from: f */
    private boolean f12701f;

    /* renamed from: g */
    private boolean f12702g;

    /* renamed from: h */
    private boolean f12703h;

    /* renamed from: i */
    private boolean f12704i;
    private Context mContext;

    /* renamed from: a */
    private SharedPreferences.Editor f12694a = null;

    /* renamed from: a */
    private AbstractC2524b.AbstractC2525a f12696a = null;

    /* JADX WARN: Removed duplicated region for block: B:106:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x016e A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x018c A[Catch: Exception -> 0x019a, TRY_LEAVE, TryCatch #1 {Exception -> 0x019a, blocks: (B:81:0x0188, B:83:0x018c), top: B:90:0x0188 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public C2527c(android.content.Context r9, java.lang.String r10, java.lang.String r11, boolean r12, boolean r13) {
        /*
            Method dump skipped, instructions count: 411
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p073ta.utdid2.p076b.p077a.C2527c.<init>(android.content.Context, java.lang.String, java.lang.String, boolean, boolean):void");
    }

    /* renamed from: a */
    private C2528d m17157a(String str) {
        File a = m17156a(str);
        if (a == null) {
            return null;
        }
        this.f12698a = new C2528d(a.getAbsolutePath());
        return this.f12698a;
    }

    /* renamed from: a */
    private File m17156a(String str) {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory == null) {
            return null;
        }
        File file = new File(String.format("%s%s%s", externalStorageDirectory.getAbsolutePath(), File.separator, str));
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    /* renamed from: a */
    private void m17159a(SharedPreferences sharedPreferences, AbstractC2524b bVar) {
        AbstractC2524b.AbstractC2525a a;
        if (sharedPreferences != null && bVar != null && (a = bVar.mo17146a()) != null) {
            a.mo17130b();
            for (Map.Entry<String, ?> entry : sharedPreferences.getAll().entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value instanceof String) {
                    a.mo17132a(key, (String) value);
                } else if (value instanceof Integer) {
                    a.mo17134a(key, ((Integer) value).intValue());
                } else if (value instanceof Long) {
                    a.mo17133a(key, ((Long) value).longValue());
                } else if (value instanceof Float) {
                    a.mo17135a(key, ((Float) value).floatValue());
                } else if (value instanceof Boolean) {
                    a.mo17131a(key, ((Boolean) value).booleanValue());
                }
            }
            try {
                a.commit();
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: a */
    private void m17158a(AbstractC2524b bVar, SharedPreferences sharedPreferences) {
        SharedPreferences.Editor edit;
        if (!(bVar == null || sharedPreferences == null || (edit = sharedPreferences.edit()) == null)) {
            edit.clear();
            for (Map.Entry<String, ?> entry : bVar.getAll().entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value instanceof String) {
                    edit.putString(key, (String) value);
                } else if (value instanceof Integer) {
                    edit.putInt(key, ((Integer) value).intValue());
                } else if (value instanceof Long) {
                    edit.putLong(key, ((Long) value).longValue());
                } else if (value instanceof Float) {
                    edit.putFloat(key, ((Float) value).floatValue());
                } else if (value instanceof Boolean) {
                    edit.putBoolean(key, ((Boolean) value).booleanValue());
                }
            }
            edit.commit();
        }
    }

    /* renamed from: c */
    private boolean m17154c() {
        AbstractC2524b bVar = this.f12697a;
        if (bVar == null) {
            return false;
        }
        boolean b = bVar.mo17139b();
        if (!b) {
            commit();
        }
        return b;
    }

    /* renamed from: b */
    private void m17155b() {
        AbstractC2524b bVar;
        SharedPreferences sharedPreferences;
        if (this.f12694a == null && (sharedPreferences = this.f12695a) != null) {
            this.f12694a = sharedPreferences.edit();
        }
        if (this.f12703h && this.f12696a == null && (bVar = this.f12697a) != null) {
            this.f12696a = bVar.mo17146a();
        }
        m17154c();
    }

    public void putString(String str, String str2) {
        if (!C2521g.m17166a(str) && !str.equals("t")) {
            m17155b();
            SharedPreferences.Editor editor = this.f12694a;
            if (editor != null) {
                editor.putString(str, str2);
            }
            AbstractC2524b.AbstractC2525a aVar = this.f12696a;
            if (aVar != null) {
                aVar.mo17132a(str, str2);
            }
        }
    }

    public void remove(String str) {
        if (!C2521g.m17166a(str) && !str.equals("t")) {
            m17155b();
            SharedPreferences.Editor editor = this.f12694a;
            if (editor != null) {
                editor.remove(str);
            }
            AbstractC2524b.AbstractC2525a aVar = this.f12696a;
            if (aVar != null) {
                aVar.mo17136a(str);
            }
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:2|(4:4|(1:8)|9|(9:11|13|(1:17)|18|56|19|22|(4:24|(2:26|(2:28|(3:30|(1:32)(1:33)|34))(3:54|35|(2:37|(1:39))))|41|(3:52|47|(1:49)))|50))|12|13|(2:15|17)|18|56|19|22|(0)|50) */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0037, code lost:
        r2 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0038, code lost:
        r2.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean commit() {
        /*
            r5 = this;
            long r0 = java.lang.System.currentTimeMillis()
            android.content.SharedPreferences$Editor r2 = r5.f12694a
            r3 = 0
            if (r2 == 0) goto L_0x0020
            boolean r4 = r5.f12704i
            if (r4 != 0) goto L_0x0016
            android.content.SharedPreferences r4 = r5.f12695a
            if (r4 == 0) goto L_0x0016
            java.lang.String r4 = "t"
            r2.putLong(r4, r0)
        L_0x0016:
            android.content.SharedPreferences$Editor r0 = r5.f12694a
            boolean r0 = r0.commit()
            if (r0 != 0) goto L_0x0020
            r0 = 0
            goto L_0x0021
        L_0x0020:
            r0 = 1
        L_0x0021:
            android.content.SharedPreferences r1 = r5.f12695a
            if (r1 == 0) goto L_0x0031
            android.content.Context r1 = r5.mContext
            if (r1 == 0) goto L_0x0031
            java.lang.String r2 = r5.f12699b
            android.content.SharedPreferences r1 = r1.getSharedPreferences(r2, r3)
            r5.f12695a = r1
        L_0x0031:
            r1 = 0
            java.lang.String r1 = android.os.Environment.getExternalStorageState()     // Catch: Exception -> 0x0037
            goto L_0x003b
        L_0x0037:
            r2 = move-exception
            r2.printStackTrace()
        L_0x003b:
            boolean r2 = com.p073ta.utdid2.p074a.p075a.C2521g.m17166a(r1)
            if (r2 != 0) goto L_0x00aa
            java.lang.String r2 = "mounted"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0088
            com.ta.utdid2.b.a.b r2 = r5.f12697a
            if (r2 != 0) goto L_0x0079
            java.lang.String r2 = r5.f12700c
            com.ta.utdid2.b.a.d r2 = r5.m17157a(r2)
            if (r2 == 0) goto L_0x0088
            java.lang.String r4 = r5.f12699b
            com.ta.utdid2.b.a.b r2 = r2.m17149a(r4, r3)
            r5.f12697a = r2
            boolean r2 = r5.f12704i
            if (r2 != 0) goto L_0x0069
            android.content.SharedPreferences r2 = r5.f12695a
            com.ta.utdid2.b.a.b r4 = r5.f12697a
            r5.m17159a(r2, r4)
            goto L_0x0070
        L_0x0069:
            com.ta.utdid2.b.a.b r2 = r5.f12697a
            android.content.SharedPreferences r4 = r5.f12695a
            r5.m17158a(r2, r4)
        L_0x0070:
            com.ta.utdid2.b.a.b r2 = r5.f12697a
            com.ta.utdid2.b.a.b$a r2 = r2.mo17146a()
            r5.f12696a = r2
            goto L_0x0088
        L_0x0079:
            com.ta.utdid2.b.a.b$a r2 = r5.f12696a     // Catch: Exception -> 0x0087
            if (r2 == 0) goto L_0x0088
            com.ta.utdid2.b.a.b$a r2 = r5.f12696a     // Catch: Exception -> 0x0087
            boolean r2 = r2.commit()     // Catch: Exception -> 0x0087
            if (r2 != 0) goto L_0x0088
            r0 = 0
            goto L_0x0088
        L_0x0087:
            r0 = 0
        L_0x0088:
            java.lang.String r2 = "mounted"
            boolean r2 = r1.equals(r2)
            if (r2 != 0) goto L_0x009c
            java.lang.String r2 = "mounted_ro"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x00aa
            com.ta.utdid2.b.a.b r1 = r5.f12697a
            if (r1 == 0) goto L_0x00aa
        L_0x009c:
            com.ta.utdid2.b.a.d r1 = r5.f12698a     // Catch: Exception -> 0x00aa
            if (r1 == 0) goto L_0x00aa
            com.ta.utdid2.b.a.d r1 = r5.f12698a     // Catch: Exception -> 0x00aa
            java.lang.String r2 = r5.f12699b     // Catch: Exception -> 0x00aa
            com.ta.utdid2.b.a.b r1 = r1.m17149a(r2, r3)     // Catch: Exception -> 0x00aa
            r5.f12697a = r1     // Catch: Exception -> 0x00aa
        L_0x00aa:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p073ta.utdid2.p076b.p077a.C2527c.commit():boolean");
    }

    public String getString(String str) {
        m17154c();
        SharedPreferences sharedPreferences = this.f12695a;
        if (sharedPreferences != null) {
            String string = sharedPreferences.getString(str, "");
            if (!C2521g.m17166a(string)) {
                return string;
            }
        }
        AbstractC2524b bVar = this.f12697a;
        return bVar != null ? bVar.getString(str, "") : "";
    }
}
