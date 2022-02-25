package com.tencent.smtt.utils;

import android.os.Build;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.tencent.smtt.utils.p */
/* loaded from: classes2.dex */
public class TbsCopyVerify {

    /* renamed from: a */
    private C2661b f13410a = null;

    /* renamed from: b */
    private C2661b f13411b = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: TbsCopyVerify.java */
    /* renamed from: com.tencent.smtt.utils.p$a */
    /* loaded from: classes2.dex */
    public class C2660a {

        /* renamed from: b */
        private String f13413b;

        /* renamed from: c */
        private long f13414c;

        /* renamed from: d */
        private long f13415d;

        C2660a(String str, long j, long j2) {
            this.f13413b = str;
            this.f13414c = j;
            this.f13415d = j2;
        }

        /* renamed from: a */
        long m16370a() {
            return this.f13414c;
        }

        /* renamed from: b */
        long m16369b() {
            return this.f13415d;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: TbsCopyVerify.java */
    /* renamed from: com.tencent.smtt.utils.p$b */
    /* loaded from: classes2.dex */
    public class C2661b {

        /* renamed from: b */
        private Map<String, C2660a> f13417b = new HashMap();

        /* renamed from: a */
        Map<String, C2660a> m16368a() {
            return this.f13417b;
        }

        C2661b(File file) {
            this.f13417b.clear();
            m16367a(file);
        }

        /* renamed from: a */
        private void m16367a(File file) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null || Build.VERSION.SDK_INT < 26) {
                    for (File file2 : listFiles) {
                        m16367a(file2);
                    }
                }
            } else if (file.isFile()) {
                m16366a(file.getName(), file.length(), file.lastModified());
            }
        }

        /* renamed from: a */
        private void m16366a(String str, long j, long j2) {
            if (str != null && str.length() > 0 && j > 0 && j2 > 0) {
                C2660a aVar = new C2660a(str, j, j2);
                if (!this.f13417b.containsKey(str)) {
                    this.f13417b.put(str, aVar);
                }
            }
        }
    }

    /* renamed from: a */
    public void m16372a(File file) {
        this.f13410a = new C2661b(file);
    }

    /* renamed from: b */
    public void m16371b(File file) {
        this.f13411b = new C2661b(file);
    }

    /* renamed from: a */
    public boolean m16374a() {
        C2661b bVar = this.f13411b;
        return bVar != null && this.f13410a != null && bVar.m16368a().size() == this.f13410a.m16368a().size() && m16373a(this.f13410a, this.f13411b);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0027  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean m16373a(com.tencent.smtt.utils.TbsCopyVerify.C2661b r8, com.tencent.smtt.utils.TbsCopyVerify.C2661b r9) {
        /*
            r7 = this;
            r0 = 0
            if (r8 == 0) goto L_0x0061
            java.util.Map r1 = r8.m16368a()
            if (r1 == 0) goto L_0x0061
            if (r9 == 0) goto L_0x0061
            java.util.Map r1 = r9.m16368a()
            if (r1 == 0) goto L_0x0061
            java.util.Map r8 = r8.m16368a()
            java.util.Map r9 = r9.m16368a()
            java.util.Set r8 = r8.entrySet()
            java.util.Iterator r8 = r8.iterator()
        L_0x0021:
            boolean r1 = r8.hasNext()
            if (r1 == 0) goto L_0x005f
            java.lang.Object r1 = r8.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r2 = r1.getKey()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r1 = r1.getValue()
            com.tencent.smtt.utils.p$a r1 = (com.tencent.smtt.utils.TbsCopyVerify.C2660a) r1
            boolean r3 = r9.containsKey(r2)
            if (r3 == 0) goto L_0x005e
            java.lang.Object r2 = r9.get(r2)
            com.tencent.smtt.utils.p$a r2 = (com.tencent.smtt.utils.TbsCopyVerify.C2660a) r2
            long r3 = r1.m16370a()
            long r5 = r2.m16370a()
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 != 0) goto L_0x005d
            long r3 = r1.m16369b()
            long r1 = r2.m16369b()
            int r1 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r1 == 0) goto L_0x0021
        L_0x005d:
            return r0
        L_0x005e:
            return r0
        L_0x005f:
            r8 = 1
            return r8
        L_0x0061:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.TbsCopyVerify.m16373a(com.tencent.smtt.utils.p$b, com.tencent.smtt.utils.p$b):boolean");
    }
}
