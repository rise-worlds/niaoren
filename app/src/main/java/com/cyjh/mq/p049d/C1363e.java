package com.cyjh.mq.p049d;

import java.util.List;

/* compiled from: ShellUtils.java */
/* renamed from: com.cyjh.mq.d.e */
/* loaded from: classes.dex */
public final class C1363e {

    /* renamed from: a */
    public static final String f8870a = "su";

    /* renamed from: b */
    public static final String f8871b = "sh";

    /* renamed from: c */
    public static final String f8872c = "exit\n";

    /* renamed from: d */
    public static final String f8873d = "\n";

    /* renamed from: e */
    private static boolean f8874e = false;

    private C1363e() {
        throw new AssertionError();
    }

    /* renamed from: a */
    public static C1364a m20438a(String str) {
        return m20434a(new String[]{str}, false, true, 100);
    }

    /* renamed from: a */
    private static C1364a m20437a(List<String> list, boolean z, int i) {
        return m20434a(list == null ? null : (String[]) list.toArray(new String[0]), z, true, i);
    }

    /* renamed from: a */
    private static C1364a m20435a(String[] strArr, boolean z, int i) {
        return m20434a(strArr, z, true, i);
    }

    /* renamed from: b */
    private static C1364a m20432b(String str) {
        return m20434a(new String[]{str}, true, false, 0);
    }

    /* renamed from: a */
    private static C1364a m20436a(List<String> list, boolean z, boolean z2, int i) {
        return m20434a(list == null ? null : (String[]) list.toArray(new String[0]), z, z2, i);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(14:6|(13:159|7|(1:9)(1:10)|11|176|12|166|13|(3:15|(2:17|182)(1:183)|18)|181|19|(1:21)|22)|(19:24|170|25|172|26|179|27|(3:174|28|(1:30))|(2:31|(1:33)(0))|61|(1:63)|(1:65)|(1:69)|70|133|(1:135)(1:136)|(1:138)|139|140)(1:60)|164|61|(0)|(0)|(0)|70|133|(0)(0)|(0)|139|140) */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0164, code lost:
        if (r8 == null) goto L_0x01b0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x0166, code lost:
        r8.destroy();
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x018a, code lost:
        if (r8 == null) goto L_0x01b0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x01ad, code lost:
        if (r8 == null) goto L_0x01b0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x010c, code lost:
        r10 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x010d, code lost:
        r10.printStackTrace();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:0x015d A[Catch: IOException -> 0x0154, TRY_LEAVE, TryCatch #18 {IOException -> 0x0154, blocks: (B:95:0x0150, B:99:0x0158, B:101:0x015d), top: B:160:0x0150 }] */
    /* JADX WARN: Removed duplicated region for block: B:114:0x017e A[Catch: IOException -> 0x017a, TryCatch #0 {IOException -> 0x017a, blocks: (B:110:0x0176, B:114:0x017e, B:116:0x0183), top: B:157:0x0176 }] */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0183 A[Catch: IOException -> 0x017a, TRY_LEAVE, TryCatch #0 {IOException -> 0x017a, blocks: (B:110:0x0176, B:114:0x017e, B:116:0x0183), top: B:157:0x0176 }] */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01a1 A[Catch: IOException -> 0x019d, TryCatch #30 {IOException -> 0x019d, blocks: (B:124:0x0199, B:128:0x01a1, B:130:0x01a6), top: B:168:0x0199 }] */
    /* JADX WARN: Removed duplicated region for block: B:130:0x01a6 A[Catch: IOException -> 0x019d, TRY_LEAVE, TryCatch #30 {IOException -> 0x019d, blocks: (B:124:0x0199, B:128:0x01a1, B:130:0x01a6), top: B:168:0x0199 }] */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01d1 A[Catch: IOException -> 0x01cd, TryCatch #20 {IOException -> 0x01cd, blocks: (B:144:0x01c9, B:148:0x01d1, B:150:0x01d6), top: B:162:0x01c9 }] */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01d6 A[Catch: IOException -> 0x01cd, TRY_LEAVE, TryCatch #20 {IOException -> 0x01cd, blocks: (B:144:0x01c9, B:148:0x01d1, B:150:0x01d6), top: B:162:0x01c9 }] */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01df  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0176 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0150 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:162:0x01c9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0199 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0103 A[Catch: IOException -> 0x010c, TryCatch #23 {IOException -> 0x010c, blocks: (B:61:0x00fe, B:63:0x0103, B:65:0x0108), top: B:164:0x00fe }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0108 A[Catch: IOException -> 0x010c, TRY_LEAVE, TryCatch #23 {IOException -> 0x010c, blocks: (B:61:0x00fe, B:63:0x0103, B:65:0x0108), top: B:164:0x00fe }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0158 A[Catch: IOException -> 0x0154, TryCatch #18 {IOException -> 0x0154, blocks: (B:95:0x0150, B:99:0x0158, B:101:0x015d), top: B:160:0x0150 }] */
    /* JADX WARN: Type inference failed for: r10v0, types: [int] */
    /* JADX WARN: Type inference failed for: r10v24, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v23 */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v25 */
    /* JADX WARN: Type inference failed for: r2v28 */
    /* JADX WARN: Type inference failed for: r2v30 */
    /* JADX WARN: Type inference failed for: r2v32 */
    /* JADX WARN: Type inference failed for: r2v37 */
    /* JADX WARN: Type inference failed for: r2v39 */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r2v41 */
    /* JADX WARN: Type inference failed for: r2v43 */
    /* JADX WARN: Type inference failed for: r2v44 */
    /* JADX WARN: Type inference failed for: r2v6, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r2v8, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Type inference failed for: r7v32 */
    /* JADX WARN: Type inference failed for: r7v33, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r8v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r8v19, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v11 */
    /* JADX WARN: Type inference failed for: r9v12 */
    /* JADX WARN: Type inference failed for: r9v13 */
    /* JADX WARN: Type inference failed for: r9v14 */
    /* JADX WARN: Type inference failed for: r9v15 */
    /* JADX WARN: Type inference failed for: r9v16 */
    /* JADX WARN: Type inference failed for: r9v17 */
    /* JADX WARN: Type inference failed for: r9v18 */
    /* JADX WARN: Type inference failed for: r9v2, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r9v20 */
    /* JADX WARN: Type inference failed for: r9v23 */
    /* JADX WARN: Type inference failed for: r9v25 */
    /* JADX WARN: Type inference failed for: r9v27 */
    /* JADX WARN: Type inference failed for: r9v29 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v30 */
    /* JADX WARN: Type inference failed for: r9v31 */
    /* JADX WARN: Type inference failed for: r9v32 */
    /* JADX WARN: Type inference failed for: r9v33 */
    /* JADX WARN: Type inference failed for: r9v34 */
    /* JADX WARN: Type inference failed for: r9v35 */
    /* JADX WARN: Type inference failed for: r9v36 */
    /* JADX WARN: Type inference failed for: r9v37 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v8 */
    /* JADX WARN: Type inference failed for: r9v9 */
    /* JADX WARN: Unknown variable types count: 2 */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.cyjh.mq.p049d.C1363e.C1364a m20434a(java.lang.String[] r7, boolean r8, boolean r9, int r10) {
        /*
            Method dump skipped, instructions count: 489
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cyjh.mq.p049d.C1363e.m20434a(java.lang.String[], boolean, boolean, int):com.cyjh.mq.d.e$a");
    }

    /* compiled from: ShellUtils.java */
    /* renamed from: com.cyjh.mq.d.e$a */
    /* loaded from: classes.dex */
    public static class C1364a {

        /* renamed from: a */
        public int f8875a;

        /* renamed from: b */
        public String f8876b;

        /* renamed from: c */
        public String f8877c;

        private C1364a(int i) {
            this.f8875a = i;
        }

        public C1364a(int i, String str, String str2) {
            this.f8875a = i;
            this.f8876b = str;
            this.f8877c = str2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ShellUtils.java */
    /* renamed from: com.cyjh.mq.d.e$b */
    /* loaded from: classes.dex */
    public static class C1365b extends Thread {

        /* renamed from: a */
        private Process f8878a;

        /* renamed from: b */
        private int f8879b;

        public C1365b(Process process, int i) {
            this.f8878a = process;
            this.f8879b = i;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            try {
                sleep(this.f8879b * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!C1363e.f8874e) {
                this.f8878a.destroy();
            }
        }
    }

    /* renamed from: b */
    private static boolean m20433b() {
        return m20434a(new String[]{"echo root"}, true, false, 0).f8875a == 0;
    }
}
