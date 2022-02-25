package com.p018b.p029b;

import java.util.zip.Inflater;

/* renamed from: com.b.b.m */
/* loaded from: classes.dex */
public final class InflaterSource implements Source {

    /* renamed from: a */
    private final BufferedSource f6435a;

    /* renamed from: b */
    private final Inflater f6436b;

    /* renamed from: c */
    private int f6437c;

    /* renamed from: d */
    private boolean f6438d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public InflaterSource(BufferedSource hVar, Inflater inflater) {
        if (hVar == null) {
            throw new IllegalArgumentException("source == null");
        } else if (inflater != null) {
            this.f6435a = hVar;
            this.f6436b = inflater;
        } else {
            throw new IllegalArgumentException("inflater == null");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0083, code lost:
        m24307b();
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x008a, code lost:
        if (r6.f6452b != r6.f6453c) goto L_?;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x008c, code lost:
        r5.f6421a = r6.m24272a();
        com.p018b.p029b.SegmentPool.m24268a(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0095, code lost:
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:?, code lost:
        return -1;
     */
    @Override // com.p018b.p029b.Source
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long mo24249a(com.p018b.p029b.Buffer r5, long r6) {
        /*
            r4 = this;
            r0 = 0
            int r2 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r2 < 0) goto L_0x00af
            boolean r6 = r4.f6438d
            if (r6 != 0) goto L_0x00a7
            if (r2 != 0) goto L_0x000d
            return r0
        L_0x000d:
            java.util.zip.Inflater r6 = r4.f6436b
            boolean r6 = r6.needsInput()
            r7 = 1
            r0 = 0
            if (r6 != 0) goto L_0x0018
            goto L_0x0047
        L_0x0018:
            r4.m24307b()
            java.util.zip.Inflater r6 = r4.f6436b
            int r6 = r6.getRemaining()
            if (r6 != 0) goto L_0x009f
            com.b.b.h r6 = r4.f6435a
            boolean r6 = r6.mo24282d()
            if (r6 == 0) goto L_0x002d
            r0 = 1
            goto L_0x0047
        L_0x002d:
            com.b.b.h r6 = r4.f6435a
            com.b.b.f r6 = r6.mo24284c()
            com.b.b.t r6 = r6.f6421a
            int r1 = r6.f6453c
            int r2 = r6.f6452b
            int r1 = r1 - r2
            r4.f6437c = r1
            java.util.zip.Inflater r1 = r4.f6436b
            byte[] r2 = r6.f6451a
            int r6 = r6.f6452b
            int r3 = r4.f6437c
            r1.setInput(r2, r6, r3)
        L_0x0047:
            com.b.b.t r6 = r5.m24324e(r7)     // Catch: DataFormatException -> 0x0098
            java.util.zip.Inflater r7 = r4.f6436b     // Catch: DataFormatException -> 0x0098
            byte[] r1 = r6.f6451a     // Catch: DataFormatException -> 0x0098
            int r2 = r6.f6453c     // Catch: DataFormatException -> 0x0098
            int r3 = r6.f6453c     // Catch: DataFormatException -> 0x0098
            int r3 = 8192 - r3
            int r7 = r7.inflate(r1, r2, r3)     // Catch: DataFormatException -> 0x0098
            if (r7 <= 0) goto L_0x0067
            int r0 = r6.f6453c     // Catch: DataFormatException -> 0x0098
            int r0 = r0 + r7
            r6.f6453c = r0     // Catch: DataFormatException -> 0x0098
            long r0 = r5.f6422b     // Catch: DataFormatException -> 0x0098
            long r6 = (long) r7     // Catch: DataFormatException -> 0x0098
            long r0 = r0 + r6
            r5.f6422b = r0     // Catch: DataFormatException -> 0x0098
            return r6
        L_0x0067:
            java.util.zip.Inflater r7 = r4.f6436b     // Catch: DataFormatException -> 0x0098
            boolean r7 = r7.finished()     // Catch: DataFormatException -> 0x0098
            if (r7 != 0) goto L_0x0083
            java.util.zip.Inflater r7 = r4.f6436b     // Catch: DataFormatException -> 0x0098
            boolean r7 = r7.needsDictionary()     // Catch: DataFormatException -> 0x0098
            if (r7 == 0) goto L_0x0078
            goto L_0x0083
        L_0x0078:
            if (r0 != 0) goto L_0x007b
            goto L_0x000d
        L_0x007b:
            java.io.EOFException r5 = new java.io.EOFException     // Catch: DataFormatException -> 0x0098
            java.lang.String r6 = "source exhausted prematurely"
            r5.<init>(r6)     // Catch: DataFormatException -> 0x0098
            throw r5     // Catch: DataFormatException -> 0x0098
        L_0x0083:
            r4.m24307b()     // Catch: DataFormatException -> 0x0098
            int r7 = r6.f6452b     // Catch: DataFormatException -> 0x0098
            int r0 = r6.f6453c     // Catch: DataFormatException -> 0x0098
            if (r7 != r0) goto L_0x0095
            com.b.b.t r7 = r6.m24272a()     // Catch: DataFormatException -> 0x0098
            r5.f6421a = r7     // Catch: DataFormatException -> 0x0098
            com.p018b.p029b.SegmentPool.m24268a(r6)     // Catch: DataFormatException -> 0x0098
        L_0x0095:
            r5 = -1
            return r5
        L_0x0098:
            r5 = move-exception
            java.io.IOException r6 = new java.io.IOException
            r6.<init>(r5)
            throw r6
        L_0x009f:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "?"
            r5.<init>(r6)
            throw r5
        L_0x00a7:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "closed"
            r5.<init>(r6)
            throw r5
        L_0x00af:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "byteCount < 0: "
            r0.<init>(r1)
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p018b.p029b.InflaterSource.mo24249a(com.b.b.f, long):long");
    }

    /* renamed from: b */
    private void m24307b() {
        int i = this.f6437c;
        if (i != 0) {
            int remaining = i - this.f6436b.getRemaining();
            this.f6437c -= remaining;
            this.f6435a.mo24278f(remaining);
        }
    }

    @Override // com.p018b.p029b.Source
    /* renamed from: a */
    public final Timeout mo24250a() {
        return this.f6435a.mo24250a();
    }

    @Override // com.p018b.p029b.Source, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (!this.f6438d) {
            this.f6436b.end();
            this.f6438d = true;
            this.f6435a.close();
        }
    }
}
