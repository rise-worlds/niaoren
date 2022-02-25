package com.p073ta.utdid2.p076b.p077a;

import com.p073ta.utdid2.p076b.p077a.AbstractC2524b;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.WeakHashMap;

/* renamed from: com.ta.utdid2.b.a.d */
/* loaded from: classes2.dex */
public class C2528d {

    /* renamed from: b */
    private static final Object f12705b = new Object();

    /* renamed from: a */
    private File f12706a;

    /* renamed from: a */
    private final Object f12707a = new Object();

    /* renamed from: a */
    private HashMap<File, C2529a> f12708a = new HashMap<>();

    public C2528d(String str) {
        if (str == null || str.length() <= 0) {
            throw new RuntimeException("Directory can not be empty");
        }
        this.f12706a = new File(str);
    }

    /* renamed from: a */
    private File m17150a(File file, String str) {
        if (str.indexOf(File.separatorChar) < 0) {
            return new File(file, str);
        }
        throw new IllegalArgumentException("File " + str + " contains a path separator");
    }

    /* renamed from: a */
    private File m17153a() {
        File file;
        synchronized (this.f12707a) {
            file = this.f12706a;
        }
        return file;
    }

    /* renamed from: b */
    private File m17147b(String str) {
        File a = m17153a();
        return m17150a(a, str + ".xml");
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x004f, code lost:
        if (r0 == null) goto L_0x0083;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0070, code lost:
        if (r3 == null) goto L_0x007e;
     */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0086 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.p073ta.utdid2.p076b.p077a.AbstractC2524b m17149a(java.lang.String r5, int r6) {
        /*
            r4 = this;
            java.io.File r5 = r4.m17147b(r5)
            java.lang.Object r0 = com.p073ta.utdid2.p076b.p077a.C2528d.f12705b
            monitor-enter(r0)
            java.util.HashMap<java.io.File, com.ta.utdid2.b.a.d$a> r1 = r4.f12708a     // Catch: all -> 0x00a6
            java.lang.Object r1 = r1.get(r5)     // Catch: all -> 0x00a6
            com.ta.utdid2.b.a.d$a r1 = (com.p073ta.utdid2.p076b.p077a.C2528d.C2529a) r1     // Catch: all -> 0x00a6
            if (r1 == 0) goto L_0x0019
            boolean r2 = r1.m17138d()     // Catch: all -> 0x00a6
            if (r2 != 0) goto L_0x0019
            monitor-exit(r0)     // Catch: all -> 0x00a6
            return r1
        L_0x0019:
            monitor-exit(r0)     // Catch: all -> 0x00a6
            java.io.File r0 = m17151a(r5)
            boolean r2 = r0.exists()
            if (r2 == 0) goto L_0x002a
            r5.delete()
            r0.renameTo(r5)
        L_0x002a:
            boolean r0 = r5.exists()
            r2 = 0
            if (r0 == 0) goto L_0x0083
            boolean r0 = r5.canRead()
            if (r0 == 0) goto L_0x0083
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch: all -> 0x004c, Exception -> 0x004e, XmlPullParserException -> 0x0052
            r0.<init>(r5)     // Catch: all -> 0x004c, Exception -> 0x004e, XmlPullParserException -> 0x0052
            java.util.HashMap r2 = com.p073ta.utdid2.p076b.p077a.C2531e.m17129a(r0)     // Catch: all -> 0x0047, Exception -> 0x004a, XmlPullParserException -> 0x0053
            r0.close()     // Catch: all -> 0x0047, Exception -> 0x004a, XmlPullParserException -> 0x0053
        L_0x0043:
            r0.close()     // Catch: Throwable -> 0x0083
            goto L_0x0083
        L_0x0047:
            r5 = move-exception
            r2 = r0
            goto L_0x0078
        L_0x004a:
            goto L_0x004f
        L_0x004c:
            r5 = move-exception
            goto L_0x0078
        L_0x004e:
            r0 = r2
        L_0x004f:
            if (r0 == 0) goto L_0x0083
            goto L_0x0043
        L_0x0052:
            r0 = r2
        L_0x0053:
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: all -> 0x0067, Exception -> 0x006f
            r3.<init>(r5)     // Catch: all -> 0x0067, Exception -> 0x006f
            int r0 = r3.available()     // Catch: all -> 0x0062, Exception -> 0x0065
            byte[] r0 = new byte[r0]     // Catch: all -> 0x0062, Exception -> 0x0065
            r3.read(r0)     // Catch: all -> 0x0062, Exception -> 0x0065
            goto L_0x0072
        L_0x0062:
            r5 = move-exception
            r2 = r3
            goto L_0x0069
        L_0x0065:
            goto L_0x0070
        L_0x0067:
            r5 = move-exception
            r2 = r0
        L_0x0069:
            if (r2 == 0) goto L_0x006e
            r2.close()     // Catch: Throwable -> 0x006e
        L_0x006e:
            throw r5     // Catch: all -> 0x004c
        L_0x006f:
            r3 = r0
        L_0x0070:
            if (r3 == 0) goto L_0x007e
        L_0x0072:
            r3.close()     // Catch: Throwable -> 0x007e
            goto L_0x007e
        L_0x0076:
            r5 = move-exception
            r2 = r3
        L_0x0078:
            if (r2 == 0) goto L_0x007d
            r2.close()     // Catch: Throwable -> 0x007d
        L_0x007d:
            throw r5
        L_0x007e:
            if (r3 == 0) goto L_0x0083
            r3.close()     // Catch: Throwable -> 0x0083
        L_0x0083:
            java.lang.Object r3 = com.p073ta.utdid2.p076b.p077a.C2528d.f12705b
            monitor-enter(r3)
            if (r1 == 0) goto L_0x008c
            r1.m17141a(r2)     // Catch: all -> 0x00a3
            goto L_0x00a1
        L_0x008c:
            java.util.HashMap<java.io.File, com.ta.utdid2.b.a.d$a> r0 = r4.f12708a     // Catch: all -> 0x00a3
            java.lang.Object r0 = r0.get(r5)     // Catch: all -> 0x00a3
            r1 = r0
            com.ta.utdid2.b.a.d$a r1 = (com.p073ta.utdid2.p076b.p077a.C2528d.C2529a) r1     // Catch: all -> 0x00a3
            if (r1 != 0) goto L_0x00a1
            com.ta.utdid2.b.a.d$a r1 = new com.ta.utdid2.b.a.d$a     // Catch: all -> 0x00a3
            r1.<init>(r5, r6, r2)     // Catch: all -> 0x00a3
            java.util.HashMap<java.io.File, com.ta.utdid2.b.a.d$a> r6 = r4.f12708a     // Catch: all -> 0x00a3
            r6.put(r5, r1)     // Catch: all -> 0x00a3
        L_0x00a1:
            monitor-exit(r3)     // Catch: all -> 0x00a3
            return r1
        L_0x00a3:
            r5 = move-exception
            monitor-exit(r3)     // Catch: all -> 0x00a3
            throw r5
        L_0x00a6:
            r5 = move-exception
            monitor-exit(r0)     // Catch: all -> 0x00a6
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p073ta.utdid2.p076b.p077a.C2528d.m17149a(java.lang.String, int):com.ta.utdid2.b.a.b");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public static File m17151a(File file) {
        return new File(file.getPath() + ".bak");
    }

    /* renamed from: com.ta.utdid2.b.a.d$a */
    /* loaded from: classes2.dex */
    private static final class C2529a implements AbstractC2524b {

        /* renamed from: c */
        private static final Object f12709c = new Object();

        /* renamed from: a */
        private Map f12710a;

        /* renamed from: a */
        private WeakHashMap<AbstractC2524b.AbstractC2526b, Object> f12711a;

        /* renamed from: b */
        private final File f12712b;

        /* renamed from: c */
        private final int f12713c;

        /* renamed from: c */
        private final File f12714c;

        /* renamed from: j */
        private boolean f12715j = false;

        C2529a(File file, int i, Map map) {
            this.f12712b = file;
            this.f12714c = C2528d.m17151a(file);
            this.f12713c = i;
            this.f12710a = map == null ? new HashMap() : map;
            this.f12711a = new WeakHashMap<>();
        }

        @Override // com.p073ta.utdid2.p076b.p077a.AbstractC2524b
        /* renamed from: b */
        public boolean mo17139b() {
            File file = this.f12712b;
            return file != null && new File(file.getAbsolutePath()).exists();
        }

        /* renamed from: a */
        public void m17140a(boolean z) {
            synchronized (this) {
                this.f12715j = z;
            }
        }

        /* renamed from: d */
        public boolean m17138d() {
            boolean z;
            synchronized (this) {
                z = this.f12715j;
            }
            return z;
        }

        /* renamed from: a */
        public void m17141a(Map map) {
            if (map != null) {
                synchronized (this) {
                    this.f12710a = map;
                }
            }
        }

        @Override // com.p073ta.utdid2.p076b.p077a.AbstractC2524b
        public Map<String, ?> getAll() {
            HashMap hashMap;
            synchronized (this) {
                hashMap = new HashMap(this.f12710a);
            }
            return hashMap;
        }

        @Override // com.p073ta.utdid2.p076b.p077a.AbstractC2524b
        public String getString(String str, String str2) {
            String str3;
            synchronized (this) {
                str3 = (String) this.f12710a.get(str);
                if (str3 == null) {
                    str3 = str2;
                }
            }
            return str3;
        }

        @Override // com.p073ta.utdid2.p076b.p077a.AbstractC2524b
        public long getLong(String str, long j) {
            synchronized (this) {
                Long l = (Long) this.f12710a.get(str);
                if (l != null) {
                    j = l.longValue();
                }
            }
            return j;
        }

        /* renamed from: com.ta.utdid2.b.a.d$a$a */
        /* loaded from: classes2.dex */
        public final class C2530a implements AbstractC2524b.AbstractC2525a {

            /* renamed from: b */
            private final Map<String, Object> f12717b = new HashMap();

            /* renamed from: k */
            private boolean f12718k = false;

            public C2530a() {
            }

            @Override // com.p073ta.utdid2.p076b.p077a.AbstractC2524b.AbstractC2525a
            /* renamed from: a */
            public AbstractC2524b.AbstractC2525a mo17132a(String str, String str2) {
                synchronized (this) {
                    this.f12717b.put(str, str2);
                }
                return this;
            }

            @Override // com.p073ta.utdid2.p076b.p077a.AbstractC2524b.AbstractC2525a
            /* renamed from: a */
            public AbstractC2524b.AbstractC2525a mo17134a(String str, int i) {
                synchronized (this) {
                    this.f12717b.put(str, Integer.valueOf(i));
                }
                return this;
            }

            @Override // com.p073ta.utdid2.p076b.p077a.AbstractC2524b.AbstractC2525a
            /* renamed from: a */
            public AbstractC2524b.AbstractC2525a mo17133a(String str, long j) {
                synchronized (this) {
                    this.f12717b.put(str, Long.valueOf(j));
                }
                return this;
            }

            @Override // com.p073ta.utdid2.p076b.p077a.AbstractC2524b.AbstractC2525a
            /* renamed from: a */
            public AbstractC2524b.AbstractC2525a mo17135a(String str, float f) {
                synchronized (this) {
                    this.f12717b.put(str, Float.valueOf(f));
                }
                return this;
            }

            @Override // com.p073ta.utdid2.p076b.p077a.AbstractC2524b.AbstractC2525a
            /* renamed from: a */
            public AbstractC2524b.AbstractC2525a mo17131a(String str, boolean z) {
                synchronized (this) {
                    this.f12717b.put(str, Boolean.valueOf(z));
                }
                return this;
            }

            @Override // com.p073ta.utdid2.p076b.p077a.AbstractC2524b.AbstractC2525a
            /* renamed from: a */
            public AbstractC2524b.AbstractC2525a mo17136a(String str) {
                synchronized (this) {
                    this.f12717b.put(str, this);
                }
                return this;
            }

            @Override // com.p073ta.utdid2.p076b.p077a.AbstractC2524b.AbstractC2525a
            /* renamed from: b */
            public AbstractC2524b.AbstractC2525a mo17130b() {
                synchronized (this) {
                    this.f12718k = true;
                }
                return this;
            }

            @Override // com.p073ta.utdid2.p076b.p077a.AbstractC2524b.AbstractC2525a
            public boolean commit() {
                boolean z;
                ArrayList arrayList;
                HashSet<AbstractC2524b.AbstractC2526b> hashSet;
                boolean e;
                synchronized (C2528d.f12705b) {
                    z = C2529a.this.f12711a.size() > 0;
                    arrayList = null;
                    if (z) {
                        arrayList = new ArrayList();
                        hashSet = new HashSet(C2529a.this.f12711a.keySet());
                    } else {
                        hashSet = null;
                    }
                    synchronized (this) {
                        if (this.f12718k) {
                            C2529a.this.f12710a.clear();
                            this.f12718k = false;
                        }
                        for (Map.Entry<String, Object> entry : this.f12717b.entrySet()) {
                            String key = entry.getKey();
                            Object value = entry.getValue();
                            if (value == this) {
                                C2529a.this.f12710a.remove(key);
                            } else {
                                C2529a.this.f12710a.put(key, value);
                            }
                            if (z) {
                                arrayList.add(key);
                            }
                        }
                        this.f12717b.clear();
                    }
                    e = C2529a.this.m17137e();
                    if (e) {
                        C2529a.this.m17140a(true);
                    }
                }
                if (z) {
                    for (int size = arrayList.size() - 1; size >= 0; size--) {
                        String str = (String) arrayList.get(size);
                        for (AbstractC2524b.AbstractC2526b bVar : hashSet) {
                            if (bVar != null) {
                                bVar.m17160a(C2529a.this, str);
                            }
                        }
                    }
                }
                return e;
            }
        }

        @Override // com.p073ta.utdid2.p076b.p077a.AbstractC2524b
        /* renamed from: a */
        public AbstractC2524b.AbstractC2525a mo17146a() {
            return new C2530a();
        }

        /* renamed from: a */
        private FileOutputStream m17142a(File file) {
            try {
                return new FileOutputStream(file);
            } catch (FileNotFoundException unused) {
                if (!file.getParentFile().mkdir()) {
                    return null;
                }
                try {
                    return new FileOutputStream(file);
                } catch (FileNotFoundException unused2) {
                    return null;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: e */
        public boolean m17137e() {
            if (this.f12712b.exists()) {
                if (this.f12714c.exists()) {
                    this.f12712b.delete();
                } else if (!this.f12712b.renameTo(this.f12714c)) {
                    return false;
                }
            }
            try {
                FileOutputStream a = m17142a(this.f12712b);
                if (a == null) {
                    return false;
                }
                C2531e.m17126a(this.f12710a, a);
                a.close();
                this.f12714c.delete();
                return true;
            } catch (Exception unused) {
                if (this.f12712b.exists()) {
                    this.f12712b.delete();
                }
                return false;
            }
        }
    }
}
