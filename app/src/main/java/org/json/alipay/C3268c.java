package org.json.alipay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/* renamed from: org.json.alipay.c */
/* loaded from: classes2.dex */
public final class C3268c {

    /* renamed from: a */
    private int f14856a;

    /* renamed from: b */
    private Reader f14857b;

    /* renamed from: c */
    private char f14858c;

    /* renamed from: d */
    private boolean f14859d;

    private C3268c(Reader reader) {
        this.f14857b = !reader.markSupported() ? new BufferedReader(reader) : reader;
        this.f14859d = false;
        this.f14856a = 0;
    }

    public C3268c(String str) {
        this(new StringReader(str));
    }

    /* renamed from: a */
    private String m14751a(int i) {
        if (i == 0) {
            return "";
        }
        char[] cArr = new char[i];
        int i2 = 0;
        if (this.f14859d) {
            this.f14859d = false;
            cArr[0] = this.f14858c;
            i2 = 1;
        }
        while (i2 < i) {
            try {
                int read = this.f14857b.read(cArr, i2, i - i2);
                if (read == -1) {
                    break;
                }
                i2 += read;
            } catch (IOException e) {
                throw new JSONException(e);
            }
        }
        this.f14856a += i2;
        if (i2 >= i) {
            this.f14858c = cArr[i - 1];
            return new String(cArr);
        }
        throw m14750a("Substring bounds error");
    }

    /* renamed from: a */
    public final JSONException m14750a(String str) {
        return new JSONException(str + toString());
    }

    /* renamed from: a */
    public final void m14752a() {
        int i;
        if (this.f14859d || (i = this.f14856a) <= 0) {
            throw new JSONException("Stepping back two steps is not supported");
        }
        this.f14856a = i - 1;
        this.f14859d = true;
    }

    /* renamed from: b */
    public final char m14749b() {
        if (this.f14859d) {
            this.f14859d = false;
            if (this.f14858c != 0) {
                this.f14856a++;
            }
            return this.f14858c;
        }
        try {
            int read = this.f14857b.read();
            if (read <= 0) {
                this.f14858c = (char) 0;
                return (char) 0;
            }
            this.f14856a++;
            this.f14858c = (char) read;
            return this.f14858c;
        } catch (IOException e) {
            throw new JSONException(e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0053, code lost:
        return r0;
     */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final char m14748c() {
        /*
            r5 = this;
        L_0x0000:
            char r0 = r5.m14749b()
            r1 = 13
            r2 = 10
            r3 = 47
            if (r0 != r3) goto L_0x003e
            char r0 = r5.m14749b()
            r4 = 42
            if (r0 == r4) goto L_0x0025
            if (r0 == r3) goto L_0x001a
            r5.m14752a()
            return r3
        L_0x001a:
            char r0 = r5.m14749b()
            if (r0 == r2) goto L_0x0000
            if (r0 == r1) goto L_0x0000
            if (r0 != 0) goto L_0x001a
            goto L_0x0000
        L_0x0025:
            char r0 = r5.m14749b()
            if (r0 == 0) goto L_0x0037
            if (r0 != r4) goto L_0x0025
            char r0 = r5.m14749b()
            if (r0 == r3) goto L_0x0000
            r5.m14752a()
            goto L_0x0025
        L_0x0037:
            java.lang.String r0 = "Unclosed comment"
            org.json.alipay.JSONException r0 = r5.m14750a(r0)
            throw r0
        L_0x003e:
            r3 = 35
            if (r0 != r3) goto L_0x004d
        L_0x0042:
            char r0 = r5.m14749b()
            if (r0 == r2) goto L_0x0000
            if (r0 == r1) goto L_0x0000
            if (r0 != 0) goto L_0x0042
            goto L_0x0000
        L_0x004d:
            if (r0 == 0) goto L_0x0053
            r1 = 32
            if (r0 <= r1) goto L_0x0000
        L_0x0053:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.alipay.C3268c.m14748c():char");
    }

    /* JADX WARN: Code restructure failed: missing block: B:96:0x013d, code lost:
        throw m14750a("Unterminated string");
     */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object m14747d() {
        /*
            Method dump skipped, instructions count: 334
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.alipay.C3268c.m14747d():java.lang.Object");
    }

    public final String toString() {
        return " at character " + this.f14856a;
    }
}
