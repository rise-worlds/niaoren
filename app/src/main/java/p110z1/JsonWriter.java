package p110z1;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

/* renamed from: z1.rh */
/* loaded from: classes3.dex */
public class JsonWriter implements Closeable, Flushable {

    /* renamed from: a */
    private static final String[] f23111a = new String[128];

    /* renamed from: b */
    private static final String[] f23112b;

    /* renamed from: c */
    private final Writer f23113c;

    /* renamed from: f */
    private String f23116f;

    /* renamed from: h */
    private boolean f23118h;

    /* renamed from: i */
    private boolean f23119i;

    /* renamed from: j */
    private String f23120j;

    /* renamed from: d */
    private int[] f23114d = new int[32];

    /* renamed from: e */
    private int f23115e = 0;

    /* renamed from: g */
    private String f23117g = ":";

    /* renamed from: k */
    private boolean f23121k = true;

    static {
        for (int i = 0; i <= 31; i++) {
            f23111a[i] = String.format("\\u%04x", Integer.valueOf(i));
        }
        String[] strArr = f23111a;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        f23112b = (String[]) strArr.clone();
        String[] strArr2 = f23112b;
        strArr2[60] = "\\u003c";
        strArr2[62] = "\\u003e";
        strArr2[38] = "\\u0026";
        strArr2[61] = "\\u003d";
        strArr2[39] = "\\u0027";
    }

    public JsonWriter(Writer writer) {
        m1181a(6);
        if (writer != null) {
            this.f23113c = writer;
            return;
        }
        throw new NullPointerException("out == null");
    }

    /* renamed from: c */
    public final void m1168c(String str) {
        if (str.length() == 0) {
            this.f23116f = null;
            this.f23117g = ":";
            return;
        }
        this.f23116f = str;
        this.f23117g = ": ";
    }

    /* renamed from: b */
    public final void m1170b(boolean z) {
        this.f23118h = z;
    }

    /* renamed from: g */
    public boolean m1160g() {
        return this.f23118h;
    }

    /* renamed from: c */
    public final void m1167c(boolean z) {
        this.f23119i = z;
    }

    /* renamed from: h */
    public final boolean m1159h() {
        return this.f23119i;
    }

    /* renamed from: d */
    public final void m1164d(boolean z) {
        this.f23121k = z;
    }

    /* renamed from: i */
    public final boolean m1158i() {
        return this.f23121k;
    }

    /* renamed from: b */
    public JsonWriter mo1173b() throws IOException {
        m1157j();
        return m1179a(1, "[");
    }

    /* renamed from: c */
    public JsonWriter mo1169c() throws IOException {
        return m1180a(1, 2, "]");
    }

    /* renamed from: d */
    public JsonWriter mo1166d() throws IOException {
        m1157j();
        return m1179a(3, "{");
    }

    /* renamed from: e */
    public JsonWriter mo1163e() throws IOException {
        return m1180a(3, 5, C4963cj.f20747d);
    }

    /* renamed from: a */
    private JsonWriter m1179a(int i, String str) throws IOException {
        m1154m();
        m1181a(i);
        this.f23113c.write(str);
        return this;
    }

    /* renamed from: a */
    private JsonWriter m1180a(int i, int i2, String str) throws IOException {
        int a = m1183a();
        if (a != i2 && a != i) {
            throw new IllegalStateException("Nesting problem.");
        } else if (this.f23120j == null) {
            this.f23115e--;
            if (a == i2) {
                m1156k();
            }
            this.f23113c.write(str);
            return this;
        } else {
            throw new IllegalStateException("Dangling name: " + this.f23120j);
        }
    }

    /* renamed from: a */
    private void m1181a(int i) {
        int i2 = this.f23115e;
        int[] iArr = this.f23114d;
        if (i2 == iArr.length) {
            int[] iArr2 = new int[i2 * 2];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            this.f23114d = iArr2;
        }
        int[] iArr3 = this.f23114d;
        int i3 = this.f23115e;
        this.f23115e = i3 + 1;
        iArr3[i3] = i;
    }

    /* renamed from: a */
    private int m1183a() {
        int i = this.f23115e;
        if (i != 0) {
            return this.f23114d[i - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    /* renamed from: b */
    private void m1172b(int i) {
        this.f23114d[this.f23115e - 1] = i;
    }

    /* renamed from: a */
    public JsonWriter mo1175a(String str) throws IOException {
        if (str == null) {
            throw new NullPointerException("name == null");
        } else if (this.f23120j != null) {
            throw new IllegalStateException();
        } else if (this.f23115e != 0) {
            this.f23120j = str;
            return this;
        } else {
            throw new IllegalStateException("JsonWriter is closed.");
        }
    }

    /* renamed from: j */
    private void m1157j() throws IOException {
        if (this.f23120j != null) {
            m1155l();
            m1162e(this.f23120j);
            this.f23120j = null;
        }
    }

    /* renamed from: b */
    public JsonWriter mo1171b(String str) throws IOException {
        if (str == null) {
            return mo1161f();
        }
        m1157j();
        m1154m();
        m1162e(str);
        return this;
    }

    /* renamed from: d */
    public JsonWriter m1165d(String str) throws IOException {
        if (str == null) {
            return mo1161f();
        }
        m1157j();
        m1154m();
        this.f23113c.append((CharSequence) str);
        return this;
    }

    /* renamed from: f */
    public JsonWriter mo1161f() throws IOException {
        if (this.f23120j != null) {
            if (this.f23121k) {
                m1157j();
            } else {
                this.f23120j = null;
                return this;
            }
        }
        m1154m();
        this.f23113c.write("null");
        return this;
    }

    /* renamed from: a */
    public JsonWriter mo1174a(boolean z) throws IOException {
        m1157j();
        m1154m();
        this.f23113c.write(z ? "true" : "false");
        return this;
    }

    /* renamed from: a */
    public JsonWriter mo1177a(Boolean bool) throws IOException {
        if (bool == null) {
            return mo1161f();
        }
        m1157j();
        m1154m();
        this.f23113c.write(bool.booleanValue() ? "true" : "false");
        return this;
    }

    /* renamed from: a */
    public JsonWriter mo1182a(double d) throws IOException {
        m1157j();
        if (this.f23118h || (!Double.isNaN(d) && !Double.isInfinite(d))) {
            m1154m();
            this.f23113c.append((CharSequence) Double.toString(d));
            return this;
        }
        throw new IllegalArgumentException("Numeric values must be finite, but was " + d);
    }

    /* renamed from: a */
    public JsonWriter mo1178a(long j) throws IOException {
        m1157j();
        m1154m();
        this.f23113c.write(Long.toString(j));
        return this;
    }

    /* renamed from: a */
    public JsonWriter mo1176a(Number number) throws IOException {
        if (number == null) {
            return mo1161f();
        }
        m1157j();
        String obj = number.toString();
        if (this.f23118h || (!obj.equals("-Infinity") && !obj.equals("Infinity") && !obj.equals("NaN"))) {
            m1154m();
            this.f23113c.append((CharSequence) obj);
            return this;
        }
        throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
    }

    public void flush() throws IOException {
        if (this.f23115e != 0) {
            this.f23113c.flush();
            return;
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f23113c.close();
        int i = this.f23115e;
        if (i > 1 || (i == 1 && this.f23114d[i - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.f23115e = 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0034  */
    /* renamed from: e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m1162e(java.lang.String r8) throws java.io.IOException {
        /*
            r7 = this;
            boolean r0 = r7.f23119i
            if (r0 == 0) goto L_0x0007
            java.lang.String[] r0 = p110z1.JsonWriter.f23112b
            goto L_0x0009
        L_0x0007:
            java.lang.String[] r0 = p110z1.JsonWriter.f23111a
        L_0x0009:
            java.io.Writer r1 = r7.f23113c
            java.lang.String r2 = "\""
            r1.write(r2)
            int r1 = r8.length()
            r2 = 0
            r3 = 0
        L_0x0016:
            if (r2 >= r1) goto L_0x0045
            char r4 = r8.charAt(r2)
            r5 = 128(0x80, float:1.794E-43)
            if (r4 >= r5) goto L_0x0025
            r4 = r0[r4]
            if (r4 != 0) goto L_0x0032
            goto L_0x0042
        L_0x0025:
            r5 = 8232(0x2028, float:1.1535E-41)
            if (r4 != r5) goto L_0x002c
            java.lang.String r4 = "\\u2028"
            goto L_0x0032
        L_0x002c:
            r5 = 8233(0x2029, float:1.1537E-41)
            if (r4 != r5) goto L_0x0042
            java.lang.String r4 = "\\u2029"
        L_0x0032:
            if (r3 >= r2) goto L_0x003b
            java.io.Writer r5 = r7.f23113c
            int r6 = r2 - r3
            r5.write(r8, r3, r6)
        L_0x003b:
            java.io.Writer r3 = r7.f23113c
            r3.write(r4)
            int r3 = r2 + 1
        L_0x0042:
            int r2 = r2 + 1
            goto L_0x0016
        L_0x0045:
            if (r3 >= r1) goto L_0x004d
            java.io.Writer r0 = r7.f23113c
            int r1 = r1 - r3
            r0.write(r8, r3, r1)
        L_0x004d:
            java.io.Writer r8 = r7.f23113c
            java.lang.String r0 = "\""
            r8.write(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.JsonWriter.m1162e(java.lang.String):void");
    }

    /* renamed from: k */
    private void m1156k() throws IOException {
        if (this.f23116f != null) {
            this.f23113c.write("\n");
            int i = this.f23115e;
            for (int i2 = 1; i2 < i; i2++) {
                this.f23113c.write(this.f23116f);
            }
        }
    }

    /* renamed from: l */
    private void m1155l() throws IOException {
        int a = m1183a();
        if (a == 5) {
            this.f23113c.write(44);
        } else if (a != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        m1156k();
        m1172b(4);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: m */
    private void m1154m() throws IOException {
        switch (m1183a()) {
            case 1:
                m1172b(2);
                m1156k();
                return;
            case 2:
                this.f23113c.append(',');
                m1156k();
                return;
            case 3:
            case 5:
            default:
                throw new IllegalStateException("Nesting problem.");
            case 4:
                this.f23113c.append((CharSequence) this.f23117g);
                m1172b(5);
                return;
            case 6:
                break;
            case 7:
                if (!this.f23118h) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
                break;
        }
        m1172b(7);
    }
}
