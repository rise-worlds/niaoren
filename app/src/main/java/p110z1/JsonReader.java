package p110z1;

import com.tencent.smtt.sdk.TbsListener;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import org.apache.commons.p105io.FilenameUtils;

/* renamed from: z1.re */
/* loaded from: classes3.dex */
public class JsonReader implements Closeable {

    /* renamed from: A */
    private static final int f23060A = 5;

    /* renamed from: B */
    private static final int f23061B = 6;

    /* renamed from: C */
    private static final int f23062C = 7;

    /* renamed from: b */
    private static final char[] f23063b = ")]}'\n".toCharArray();

    /* renamed from: c */
    private static final long f23064c = -922337203685477580L;

    /* renamed from: d */
    private static final int f23065d = 0;

    /* renamed from: e */
    private static final int f23066e = 1;

    /* renamed from: f */
    private static final int f23067f = 2;

    /* renamed from: g */
    private static final int f23068g = 3;

    /* renamed from: h */
    private static final int f23069h = 4;

    /* renamed from: i */
    private static final int f23070i = 5;

    /* renamed from: j */
    private static final int f23071j = 6;

    /* renamed from: k */
    private static final int f23072k = 7;

    /* renamed from: l */
    private static final int f23073l = 8;

    /* renamed from: m */
    private static final int f23074m = 9;

    /* renamed from: n */
    private static final int f23075n = 10;

    /* renamed from: o */
    private static final int f23076o = 11;

    /* renamed from: p */
    private static final int f23077p = 12;

    /* renamed from: q */
    private static final int f23078q = 13;

    /* renamed from: r */
    private static final int f23079r = 14;

    /* renamed from: s */
    private static final int f23080s = 15;

    /* renamed from: t */
    private static final int f23081t = 16;

    /* renamed from: u */
    private static final int f23082u = 17;

    /* renamed from: v */
    private static final int f23083v = 0;

    /* renamed from: w */
    private static final int f23084w = 1;

    /* renamed from: x */
    private static final int f23085x = 2;

    /* renamed from: y */
    private static final int f23086y = 3;

    /* renamed from: z */
    private static final int f23087z = 4;

    /* renamed from: D */
    private final Reader f23088D;

    /* renamed from: K */
    private long f23095K;

    /* renamed from: L */
    private int f23096L;

    /* renamed from: M */
    private String f23097M;

    /* renamed from: O */
    private int f23099O;

    /* renamed from: E */
    private boolean f23089E = false;

    /* renamed from: F */
    private final char[] f23090F = new char[1024];

    /* renamed from: G */
    private int f23091G = 0;

    /* renamed from: H */
    private int f23092H = 0;

    /* renamed from: I */
    private int f23093I = 0;

    /* renamed from: J */
    private int f23094J = 0;

    /* renamed from: a */
    int f23102a = 0;

    /* renamed from: N */
    private int[] f23098N = new int[32];

    /* renamed from: P */
    private String[] f23100P = new String[32];

    /* renamed from: Q */
    private int[] f23101Q = new int[32];

    static {
        JsonReaderInternalAccess.f22857a = new JsonReaderInternalAccess() { // from class: z1.re.1
            @Override // p110z1.JsonReaderInternalAccess
            /* renamed from: a */
            public void mo1184a(JsonReader reVar) throws IOException {
                if (reVar instanceof JsonTreeReader) {
                    ((JsonTreeReader) reVar).m1332o();
                    return;
                }
                int i = reVar.f23102a;
                if (i == 0) {
                    i = reVar.m1193r();
                }
                if (i == 13) {
                    reVar.f23102a = 9;
                } else if (i == 12) {
                    reVar.f23102a = 8;
                } else if (i == 14) {
                    reVar.f23102a = 10;
                } else {
                    throw new IllegalStateException("Expected a name but was " + reVar.mo1205f() + reVar.m1192s());
                }
            }
        };
    }

    public JsonReader(Reader reader) {
        this.f23099O = 0;
        int[] iArr = this.f23098N;
        int i = this.f23099O;
        this.f23099O = i + 1;
        iArr[i] = 6;
        if (reader != null) {
            this.f23088D = reader;
            return;
        }
        throw new NullPointerException("in == null");
    }

    /* renamed from: a */
    public final void m1215a(boolean z) {
        this.f23089E = z;
    }

    /* renamed from: q */
    public final boolean m1194q() {
        return this.f23089E;
    }

    /* renamed from: a */
    public void mo1219a() throws IOException {
        int i = this.f23102a;
        if (i == 0) {
            i = m1193r();
        }
        if (i == 3) {
            m1217a(1);
            this.f23101Q[this.f23099O - 1] = 0;
            this.f23102a = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_ARRAY but was " + mo1205f() + m1192s());
    }

    /* renamed from: b */
    public void mo1214b() throws IOException {
        int i = this.f23102a;
        if (i == 0) {
            i = m1193r();
        }
        if (i == 4) {
            this.f23099O--;
            int[] iArr = this.f23101Q;
            int i2 = this.f23099O - 1;
            iArr[i2] = iArr[i2] + 1;
            this.f23102a = 0;
            return;
        }
        throw new IllegalStateException("Expected END_ARRAY but was " + mo1205f() + m1192s());
    }

    /* renamed from: c */
    public void mo1209c() throws IOException {
        int i = this.f23102a;
        if (i == 0) {
            i = m1193r();
        }
        if (i == 1) {
            m1217a(3);
            this.f23102a = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_OBJECT but was " + mo1205f() + m1192s());
    }

    /* renamed from: d */
    public void mo1207d() throws IOException {
        int i = this.f23102a;
        if (i == 0) {
            i = m1193r();
        }
        if (i == 2) {
            this.f23099O--;
            String[] strArr = this.f23100P;
            int i2 = this.f23099O;
            strArr[i2] = null;
            int[] iArr = this.f23101Q;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
            this.f23102a = 0;
            return;
        }
        throw new IllegalStateException("Expected END_OBJECT but was " + mo1205f() + m1192s());
    }

    /* renamed from: e */
    public boolean mo1206e() throws IOException {
        int i = this.f23102a;
        if (i == 0) {
            i = m1193r();
        }
        return (i == 2 || i == 4) ? false : true;
    }

    /* renamed from: f */
    public JsonToken mo1205f() throws IOException {
        int i = this.f23102a;
        if (i == 0) {
            i = m1193r();
        }
        switch (i) {
            case 1:
                return JsonToken.BEGIN_OBJECT;
            case 2:
                return JsonToken.END_OBJECT;
            case 3:
                return JsonToken.BEGIN_ARRAY;
            case 4:
                return JsonToken.END_ARRAY;
            case 5:
            case 6:
                return JsonToken.BOOLEAN;
            case 7:
                return JsonToken.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return JsonToken.STRING;
            case 12:
            case 13:
            case 14:
                return JsonToken.NAME;
            case 15:
            case 16:
                return JsonToken.NUMBER;
            case 17:
                return JsonToken.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    /* renamed from: r */
    int m1193r() throws IOException {
        int b;
        int[] iArr = this.f23098N;
        int i = this.f23099O;
        int i2 = iArr[i - 1];
        if (i2 == 1) {
            iArr[i - 1] = 2;
        } else if (i2 == 2) {
            int b2 = m1210b(true);
            if (b2 != 44) {
                if (b2 == 59) {
                    m1188w();
                } else if (b2 == 93) {
                    this.f23102a = 4;
                    return 4;
                } else {
                    throw m1211b("Unterminated array");
                }
            }
        } else if (i2 == 3 || i2 == 5) {
            this.f23098N[this.f23099O - 1] = 4;
            if (i2 == 5 && (b = m1210b(true)) != 44) {
                if (b == 59) {
                    m1188w();
                } else if (b == 125) {
                    this.f23102a = 2;
                    return 2;
                } else {
                    throw m1211b("Unterminated object");
                }
            }
            int b3 = m1210b(true);
            if (b3 == 34) {
                this.f23102a = 13;
                return 13;
            } else if (b3 == 39) {
                m1188w();
                this.f23102a = 12;
                return 12;
            } else if (b3 != 125) {
                m1188w();
                this.f23091G--;
                if (m1218a((char) b3)) {
                    this.f23102a = 14;
                    return 14;
                }
                throw m1211b("Expected name");
            } else if (i2 != 5) {
                this.f23102a = 2;
                return 2;
            } else {
                throw m1211b("Expected name");
            }
        } else if (i2 == 4) {
            iArr[i - 1] = 5;
            int b4 = m1210b(true);
            if (b4 != 58) {
                if (b4 == 61) {
                    m1188w();
                    if (this.f23091G < this.f23092H || m1212b(1)) {
                        char[] cArr = this.f23090F;
                        int i3 = this.f23091G;
                        if (cArr[i3] == '>') {
                            this.f23091G = i3 + 1;
                        }
                    }
                } else {
                    throw m1211b("Expected ':'");
                }
            }
        } else if (i2 == 6) {
            if (this.f23089E) {
                m1185z();
            }
            this.f23098N[this.f23099O - 1] = 7;
        } else if (i2 == 7) {
            if (m1210b(false) == -1) {
                this.f23102a = 17;
                return 17;
            }
            m1188w();
            this.f23091G--;
        } else if (i2 == 8) {
            throw new IllegalStateException("JsonReader is closed");
        }
        int b5 = m1210b(true);
        if (b5 == 34) {
            this.f23102a = 9;
            return 9;
        } else if (b5 != 39) {
            if (!(b5 == 44 || b5 == 59)) {
                if (b5 == 91) {
                    this.f23102a = 3;
                    return 3;
                } else if (b5 != 93) {
                    if (b5 != 123) {
                        this.f23091G--;
                        int o = m1196o();
                        if (o != 0) {
                            return o;
                        }
                        int t = m1191t();
                        if (t != 0) {
                            return t;
                        }
                        if (m1218a(this.f23090F[this.f23091G])) {
                            m1188w();
                            this.f23102a = 10;
                            return 10;
                        }
                        throw m1211b("Expected value");
                    }
                    this.f23102a = 1;
                    return 1;
                } else if (i2 == 1) {
                    this.f23102a = 4;
                    return 4;
                }
            }
            if (i2 == 1 || i2 == 2) {
                m1188w();
                this.f23091G--;
                this.f23102a = 7;
                return 7;
            }
            throw m1211b("Unexpected value");
        } else {
            m1188w();
            this.f23102a = 8;
            return 8;
        }
    }

    /* renamed from: o */
    private int m1196o() throws IOException {
        int i;
        String str;
        String str2;
        char c = this.f23090F[this.f23091G];
        if (c == 't' || c == 'T') {
            str2 = "true";
            str = "TRUE";
            i = 5;
        } else if (c == 'f' || c == 'F') {
            str2 = "false";
            str = "FALSE";
            i = 6;
        } else if (c != 'n' && c != 'N') {
            return 0;
        } else {
            str2 = "null";
            str = "NULL";
            i = 7;
        }
        int length = str2.length();
        for (int i2 = 1; i2 < length; i2++) {
            if (this.f23091G + i2 >= this.f23092H && !m1212b(i2 + 1)) {
                return 0;
            }
            char c2 = this.f23090F[this.f23091G + i2];
            if (!(c2 == str2.charAt(i2) || c2 == str.charAt(i2))) {
                return 0;
            }
        }
        if ((this.f23091G + length < this.f23092H || m1212b(length + 1)) && m1218a(this.f23090F[this.f23091G + length])) {
            return 0;
        }
        this.f23091G += length;
        this.f23102a = i;
        return i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x00a0, code lost:
        if (m1218a(r14) != false) goto L_0x00d8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00a2, code lost:
        if (r9 != 2) goto L_0x00c6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00a4, code lost:
        if (r10 == false) goto L_0x00c6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00aa, code lost:
        if (r11 != Long.MIN_VALUE) goto L_0x00ae;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00ac, code lost:
        if (r13 == false) goto L_0x00c6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00b2, code lost:
        if (r11 != 0) goto L_0x00b6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00b4, code lost:
        if (r13 != false) goto L_0x00c6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00b6, code lost:
        if (r13 == false) goto L_0x00b9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00b9, code lost:
        r11 = -r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00ba, code lost:
        r18.f23095K = r11;
        r18.f23091G += r3;
        r18.f23102a = 15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00c5, code lost:
        return 15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00c6, code lost:
        if (r9 == 2) goto L_0x00d1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00c9, code lost:
        if (r9 == 4) goto L_0x00d1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00cc, code lost:
        if (r9 != 7) goto L_0x00cf;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00cf, code lost:
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00d1, code lost:
        r18.f23096L = r3;
        r18.f23102a = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00d7, code lost:
        return 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00d8, code lost:
        return 0;
     */
    /* renamed from: t */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int m1191t() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 272
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.JsonReader.m1191t():int");
    }

    /* renamed from: a */
    private boolean m1218a(char c) throws IOException {
        switch (c) {
            case '\t':
            case '\n':
            case '\f':
            case '\r':
            case ' ':
            case ',':
            case ':':
            case '[':
            case ']':
            case TbsListener.ErrorCode.DOWNLOAD_RETRYTIMES302_EXCEED /* 123 */:
            case TbsListener.ErrorCode.DOWNLOAD_THROWABLE /* 125 */:
                return false;
            case '#':
            case '/':
            case ';':
            case '=':
            case '\\':
                m1188w();
                return false;
            default:
                return true;
        }
    }

    /* renamed from: g */
    public String mo1204g() throws IOException {
        String str;
        int i = this.f23102a;
        if (i == 0) {
            i = m1193r();
        }
        if (i == 14) {
            str = m1190u();
        } else if (i == 12) {
            str = m1213b('\'');
        } else if (i == 13) {
            str = m1213b(Typography.f21049a);
        } else {
            throw new IllegalStateException("Expected a name but was " + mo1205f() + m1192s());
        }
        this.f23102a = 0;
        this.f23100P[this.f23099O - 1] = str;
        return str;
    }

    /* renamed from: h */
    public String mo1203h() throws IOException {
        String str;
        int i = this.f23102a;
        if (i == 0) {
            i = m1193r();
        }
        if (i == 10) {
            str = m1190u();
        } else if (i == 8) {
            str = m1213b('\'');
        } else if (i == 9) {
            str = m1213b(Typography.f21049a);
        } else if (i == 11) {
            str = this.f23097M;
            this.f23097M = null;
        } else if (i == 15) {
            str = Long.toString(this.f23095K);
        } else if (i == 16) {
            str = new String(this.f23090F, this.f23091G, this.f23096L);
            this.f23091G += this.f23096L;
        } else {
            throw new IllegalStateException("Expected a string but was " + mo1205f() + m1192s());
        }
        this.f23102a = 0;
        int[] iArr = this.f23101Q;
        int i2 = this.f23099O - 1;
        iArr[i2] = iArr[i2] + 1;
        return str;
    }

    /* renamed from: i */
    public boolean mo1202i() throws IOException {
        int i = this.f23102a;
        if (i == 0) {
            i = m1193r();
        }
        if (i == 5) {
            this.f23102a = 0;
            int[] iArr = this.f23101Q;
            int i2 = this.f23099O - 1;
            iArr[i2] = iArr[i2] + 1;
            return true;
        } else if (i == 6) {
            this.f23102a = 0;
            int[] iArr2 = this.f23101Q;
            int i3 = this.f23099O - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return false;
        } else {
            throw new IllegalStateException("Expected a boolean but was " + mo1205f() + m1192s());
        }
    }

    /* renamed from: j */
    public void mo1201j() throws IOException {
        int i = this.f23102a;
        if (i == 0) {
            i = m1193r();
        }
        if (i == 7) {
            this.f23102a = 0;
            int[] iArr = this.f23101Q;
            int i2 = this.f23099O - 1;
            iArr[i2] = iArr[i2] + 1;
            return;
        }
        throw new IllegalStateException("Expected null but was " + mo1205f() + m1192s());
    }

    /* renamed from: k */
    public double mo1200k() throws IOException {
        int i = this.f23102a;
        if (i == 0) {
            i = m1193r();
        }
        if (i == 15) {
            this.f23102a = 0;
            int[] iArr = this.f23101Q;
            int i2 = this.f23099O - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.f23095K;
        }
        if (i == 16) {
            this.f23097M = new String(this.f23090F, this.f23091G, this.f23096L);
            this.f23091G += this.f23096L;
        } else if (i == 8 || i == 9) {
            this.f23097M = m1213b(i == 8 ? '\'' : Typography.f21049a);
        } else if (i == 10) {
            this.f23097M = m1190u();
        } else if (i != 11) {
            throw new IllegalStateException("Expected a double but was " + mo1205f() + m1192s());
        }
        this.f23102a = 11;
        double parseDouble = Double.parseDouble(this.f23097M);
        if (this.f23089E || (!Double.isNaN(parseDouble) && !Double.isInfinite(parseDouble))) {
            this.f23097M = null;
            this.f23102a = 0;
            int[] iArr2 = this.f23101Q;
            int i3 = this.f23099O - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return parseDouble;
        }
        throw new MalformedJsonException("JSON forbids NaN and infinities: " + parseDouble + m1192s());
    }

    /* renamed from: l */
    public long mo1199l() throws IOException {
        int i = this.f23102a;
        if (i == 0) {
            i = m1193r();
        }
        if (i == 15) {
            this.f23102a = 0;
            int[] iArr = this.f23101Q;
            int i2 = this.f23099O - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.f23095K;
        }
        if (i == 16) {
            this.f23097M = new String(this.f23090F, this.f23091G, this.f23096L);
            this.f23091G += this.f23096L;
        } else if (i == 8 || i == 9 || i == 10) {
            if (i == 10) {
                this.f23097M = m1190u();
            } else {
                this.f23097M = m1213b(i == 8 ? '\'' : Typography.f21049a);
            }
            try {
                long parseLong = Long.parseLong(this.f23097M);
                this.f23102a = 0;
                int[] iArr2 = this.f23101Q;
                int i3 = this.f23099O - 1;
                iArr2[i3] = iArr2[i3] + 1;
                return parseLong;
            } catch (NumberFormatException unused) {
            }
        } else {
            throw new IllegalStateException("Expected a long but was " + mo1205f() + m1192s());
        }
        this.f23102a = 11;
        double parseDouble = Double.parseDouble(this.f23097M);
        long j = (long) parseDouble;
        if (j == parseDouble) {
            this.f23097M = null;
            this.f23102a = 0;
            int[] iArr3 = this.f23101Q;
            int i4 = this.f23099O - 1;
            iArr3[i4] = iArr3[i4] + 1;
            return j;
        }
        throw new NumberFormatException("Expected a long but was " + this.f23097M + m1192s());
    }

    /* renamed from: b */
    private String m1213b(char c) throws IOException {
        char[] cArr = this.f23090F;
        StringBuilder sb = null;
        do {
            int i = this.f23091G;
            int i2 = this.f23092H;
            int i3 = i;
            while (i < i2) {
                int i4 = i + 1;
                char c2 = cArr[i];
                if (c2 == c) {
                    this.f23091G = i4;
                    int i5 = (i4 - i3) - 1;
                    if (sb == null) {
                        return new String(cArr, i3, i5);
                    }
                    sb.append(cArr, i3, i5);
                    return sb.toString();
                } else if (c2 == '\\') {
                    this.f23091G = i4;
                    int i6 = (i4 - i3) - 1;
                    if (sb == null) {
                        sb = new StringBuilder(Math.max((i6 + 1) * 2, 16));
                    }
                    sb.append(cArr, i3, i6);
                    sb.append(m1186y());
                    i = this.f23091G;
                    i2 = this.f23092H;
                    i3 = i;
                } else {
                    if (c2 == '\n') {
                        this.f23093I++;
                        this.f23094J = i4;
                    }
                    i = i4;
                }
            }
            if (sb == null) {
                sb = new StringBuilder(Math.max((i - i3) * 2, 16));
            }
            sb.append(cArr, i3, i - i3);
            this.f23091G = i;
        } while (m1212b(1));
        throw m1211b("Unterminated string");
    }

    /* renamed from: u */
    private String m1190u() throws IOException {
        String str;
        int i = 0;
        StringBuilder sb = null;
        int i2 = 0;
        while (true) {
            int i3 = this.f23091G;
            if (i3 + i2 < this.f23092H) {
                switch (this.f23090F[i3 + i2]) {
                    case '\t':
                    case '\n':
                    case '\f':
                    case '\r':
                    case ' ':
                    case ',':
                    case ':':
                    case '[':
                    case ']':
                    case TbsListener.ErrorCode.DOWNLOAD_RETRYTIMES302_EXCEED /* 123 */:
                    case TbsListener.ErrorCode.DOWNLOAD_THROWABLE /* 125 */:
                        break;
                    case '#':
                    case '/':
                    case ';':
                    case '=':
                    case '\\':
                        m1188w();
                        break;
                    default:
                        i2++;
                }
            } else if (i2 >= this.f23090F.length) {
                if (sb == null) {
                    sb = new StringBuilder(Math.max(i2, 16));
                }
                sb.append(this.f23090F, this.f23091G, i2);
                this.f23091G += i2;
                if (m1212b(1)) {
                    i2 = 0;
                }
            } else if (m1212b(i2 + 1)) {
            }
        }
        i = i2;
        if (sb == null) {
            str = new String(this.f23090F, this.f23091G, i);
        } else {
            sb.append(this.f23090F, this.f23091G, i);
            str = sb.toString();
        }
        this.f23091G += i;
        return str;
    }

    /* renamed from: c */
    private void m1208c(char c) throws IOException {
        char[] cArr = this.f23090F;
        do {
            int i = this.f23091G;
            int i2 = this.f23092H;
            while (i < i2) {
                int i3 = i + 1;
                char c2 = cArr[i];
                if (c2 == c) {
                    this.f23091G = i3;
                    return;
                } else if (c2 == '\\') {
                    this.f23091G = i3;
                    m1186y();
                    i = this.f23091G;
                    i2 = this.f23092H;
                } else {
                    if (c2 == '\n') {
                        this.f23093I++;
                        this.f23094J = i3;
                    }
                    i = i3;
                }
            }
            this.f23091G = i;
        } while (m1212b(1));
        throw m1211b("Unterminated string");
    }

    /* renamed from: v */
    private void m1189v() throws IOException {
        do {
            int i = 0;
            while (true) {
                int i2 = this.f23091G;
                if (i2 + i < this.f23092H) {
                    switch (this.f23090F[i2 + i]) {
                        case '\t':
                        case '\n':
                        case '\f':
                        case '\r':
                        case ' ':
                        case ',':
                        case ':':
                        case '[':
                        case ']':
                        case TbsListener.ErrorCode.DOWNLOAD_RETRYTIMES302_EXCEED /* 123 */:
                        case TbsListener.ErrorCode.DOWNLOAD_THROWABLE /* 125 */:
                            break;
                        case '#':
                        case '/':
                        case ';':
                        case '=':
                        case '\\':
                            m1188w();
                            break;
                        default:
                            i++;
                    }
                } else {
                    this.f23091G = i2 + i;
                }
            }
            this.f23091G += i;
            return;
        } while (m1212b(1));
    }

    /* renamed from: m */
    public int mo1198m() throws IOException {
        int i = this.f23102a;
        if (i == 0) {
            i = m1193r();
        }
        if (i == 15) {
            long j = this.f23095K;
            int i2 = (int) j;
            if (j == i2) {
                this.f23102a = 0;
                int[] iArr = this.f23101Q;
                int i3 = this.f23099O - 1;
                iArr[i3] = iArr[i3] + 1;
                return i2;
            }
            throw new NumberFormatException("Expected an int but was " + this.f23095K + m1192s());
        }
        if (i == 16) {
            this.f23097M = new String(this.f23090F, this.f23091G, this.f23096L);
            this.f23091G += this.f23096L;
        } else if (i == 8 || i == 9 || i == 10) {
            if (i == 10) {
                this.f23097M = m1190u();
            } else {
                this.f23097M = m1213b(i == 8 ? '\'' : Typography.f21049a);
            }
            try {
                int parseInt = Integer.parseInt(this.f23097M);
                this.f23102a = 0;
                int[] iArr2 = this.f23101Q;
                int i4 = this.f23099O - 1;
                iArr2[i4] = iArr2[i4] + 1;
                return parseInt;
            } catch (NumberFormatException unused) {
            }
        } else {
            throw new IllegalStateException("Expected an int but was " + mo1205f() + m1192s());
        }
        this.f23102a = 11;
        double parseDouble = Double.parseDouble(this.f23097M);
        int i5 = (int) parseDouble;
        if (i5 == parseDouble) {
            this.f23097M = null;
            this.f23102a = 0;
            int[] iArr3 = this.f23101Q;
            int i6 = this.f23099O - 1;
            iArr3[i6] = iArr3[i6] + 1;
            return i5;
        }
        throw new NumberFormatException("Expected an int but was " + this.f23097M + m1192s());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f23102a = 0;
        this.f23098N[0] = 8;
        this.f23099O = 1;
        this.f23088D.close();
    }

    /* renamed from: n */
    public void mo1197n() throws IOException {
        int i = 0;
        do {
            int i2 = this.f23102a;
            if (i2 == 0) {
                i2 = m1193r();
            }
            if (i2 == 3) {
                m1217a(1);
                i++;
            } else if (i2 == 1) {
                m1217a(3);
                i++;
            } else if (i2 == 4) {
                this.f23099O--;
                i--;
            } else if (i2 == 2) {
                this.f23099O--;
                i--;
            } else if (i2 == 14 || i2 == 10) {
                m1189v();
            } else if (i2 == 8 || i2 == 12) {
                m1208c('\'');
            } else if (i2 == 9 || i2 == 13) {
                m1208c(Typography.f21049a);
            } else if (i2 == 16) {
                this.f23091G += this.f23096L;
            }
            this.f23102a = 0;
        } while (i != 0);
        int[] iArr = this.f23101Q;
        int i3 = this.f23099O;
        int i4 = i3 - 1;
        iArr[i4] = iArr[i4] + 1;
        this.f23100P[i3 - 1] = "null";
    }

    /* renamed from: a */
    private void m1217a(int i) {
        int i2 = this.f23099O;
        int[] iArr = this.f23098N;
        if (i2 == iArr.length) {
            int[] iArr2 = new int[i2 * 2];
            int[] iArr3 = new int[i2 * 2];
            String[] strArr = new String[i2 * 2];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            System.arraycopy(this.f23101Q, 0, iArr3, 0, this.f23099O);
            System.arraycopy(this.f23100P, 0, strArr, 0, this.f23099O);
            this.f23098N = iArr2;
            this.f23101Q = iArr3;
            this.f23100P = strArr;
        }
        int[] iArr4 = this.f23098N;
        int i3 = this.f23099O;
        this.f23099O = i3 + 1;
        iArr4[i3] = i;
    }

    /* renamed from: b */
    private boolean m1212b(int i) throws IOException {
        int i2;
        char[] cArr = this.f23090F;
        int i3 = this.f23094J;
        int i4 = this.f23091G;
        this.f23094J = i3 - i4;
        int i5 = this.f23092H;
        if (i5 != i4) {
            this.f23092H = i5 - i4;
            System.arraycopy(cArr, i4, cArr, 0, this.f23092H);
        } else {
            this.f23092H = 0;
        }
        this.f23091G = 0;
        do {
            Reader reader = this.f23088D;
            int i6 = this.f23092H;
            int read = reader.read(cArr, i6, cArr.length - i6);
            if (read == -1) {
                return false;
            }
            this.f23092H += read;
            if (this.f23093I == 0 && (i2 = this.f23094J) == 0 && this.f23092H > 0 && cArr[0] == 65279) {
                this.f23091G++;
                this.f23094J = i2 + 1;
                i++;
            }
        } while (this.f23092H < i);
        return true;
    }

    /* renamed from: b */
    private int m1210b(boolean z) throws IOException {
        char[] cArr = this.f23090F;
        int i = this.f23091G;
        int i2 = this.f23092H;
        while (true) {
            if (i == i2) {
                this.f23091G = i;
                if (m1212b(1)) {
                    i = this.f23091G;
                    i2 = this.f23092H;
                } else if (!z) {
                    return -1;
                } else {
                    throw new EOFException("End of input" + m1192s());
                }
            }
            int i3 = i + 1;
            char c = cArr[i];
            if (c == '\n') {
                this.f23093I++;
                this.f23094J = i3;
            } else if (!(c == ' ' || c == '\r' || c == '\t')) {
                if (c == '/') {
                    this.f23091G = i3;
                    if (i3 == i2) {
                        this.f23091G--;
                        boolean b = m1212b(2);
                        this.f23091G++;
                        if (!b) {
                            return c;
                        }
                    }
                    m1188w();
                    int i4 = this.f23091G;
                    char c2 = cArr[i4];
                    if (c2 == '*') {
                        this.f23091G = i4 + 1;
                        if (m1216a("*/")) {
                            i = this.f23091G + 2;
                            i2 = this.f23092H;
                        } else {
                            throw m1211b("Unterminated comment");
                        }
                    } else if (c2 != '/') {
                        return c;
                    } else {
                        this.f23091G = i4 + 1;
                        m1187x();
                        i = this.f23091G;
                        i2 = this.f23092H;
                    }
                } else if (c == '#') {
                    this.f23091G = i3;
                    m1188w();
                    m1187x();
                    i = this.f23091G;
                    i2 = this.f23092H;
                } else {
                    this.f23091G = i3;
                    return c;
                }
            }
            i = i3;
        }
    }

    /* renamed from: w */
    private void m1188w() throws IOException {
        if (!this.f23089E) {
            throw m1211b("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    /* renamed from: x */
    private void m1187x() throws IOException {
        char c;
        do {
            if (this.f23091G < this.f23092H || m1212b(1)) {
                char[] cArr = this.f23090F;
                int i = this.f23091G;
                this.f23091G = i + 1;
                c = cArr[i];
                if (c == '\n') {
                    this.f23093I++;
                    this.f23094J = this.f23091G;
                    return;
                }
            } else {
                return;
            }
        } while (c != '\r');
    }

    /* renamed from: a */
    private boolean m1216a(String str) throws IOException {
        int length = str.length();
        while (true) {
            if (this.f23091G + length > this.f23092H && !m1212b(length)) {
                return false;
            }
            char[] cArr = this.f23090F;
            int i = this.f23091G;
            if (cArr[i] == '\n') {
                this.f23093I++;
                this.f23094J = i + 1;
            } else {
                for (int i2 = 0; i2 < length; i2++) {
                    if (this.f23090F[this.f23091G + i2] != str.charAt(i2)) {
                        break;
                    }
                }
                return true;
            }
            this.f23091G++;
        }
    }

    public String toString() {
        return getClass().getSimpleName() + m1192s();
    }

    /* renamed from: s */
    String m1192s() {
        return " at line " + (this.f23093I + 1) + " column " + ((this.f23091G - this.f23094J) + 1) + " path " + mo1195p();
    }

    /* renamed from: p */
    public String mo1195p() {
        StringBuilder sb = new StringBuilder();
        sb.append(Typography.f21050b);
        int i = this.f23099O;
        for (int i2 = 0; i2 < i; i2++) {
            switch (this.f23098N[i2]) {
                case 1:
                case 2:
                    sb.append('[');
                    sb.append(this.f23101Q[i2]);
                    sb.append(']');
                    break;
                case 3:
                case 4:
                case 5:
                    sb.append(FilenameUtils.EXTENSION_SEPARATOR);
                    String[] strArr = this.f23100P;
                    if (strArr[i2] != null) {
                        sb.append(strArr[i2]);
                        break;
                    } else {
                        break;
                    }
            }
        }
        return sb.toString();
    }

    /* renamed from: y */
    private char m1186y() throws IOException {
        int i;
        if (this.f23091G != this.f23092H || m1212b(1)) {
            char[] cArr = this.f23090F;
            int i2 = this.f23091G;
            this.f23091G = i2 + 1;
            char c = cArr[i2];
            if (c == '\n') {
                this.f23093I++;
                this.f23094J = this.f23091G;
            } else if (!(c == '\"' || c == '\'' || c == '/' || c == '\\')) {
                if (c == 'b') {
                    return '\b';
                }
                if (c == 'f') {
                    return '\f';
                }
                if (c == 'n') {
                    return '\n';
                }
                if (c == 'r') {
                    return '\r';
                }
                switch (c) {
                    case 't':
                        return '\t';
                    case 'u':
                        if (this.f23091G + 4 <= this.f23092H || m1212b(4)) {
                            char c2 = 0;
                            int i3 = this.f23091G;
                            int i4 = i3 + 4;
                            while (i3 < i4) {
                                char c3 = this.f23090F[i3];
                                char c4 = (char) (c2 << 4);
                                if (c3 >= '0' && c3 <= '9') {
                                    i = c3 - '0';
                                } else if (c3 >= 'a' && c3 <= 'f') {
                                    i = (c3 - 'a') + 10;
                                } else if (c3 < 'A' || c3 > 'F') {
                                    throw new NumberFormatException("\\u" + new String(this.f23090F, this.f23091G, 4));
                                } else {
                                    i = (c3 - 'A') + 10;
                                }
                                c2 = (char) (c4 + i);
                                i3++;
                            }
                            this.f23091G += 4;
                            return c2;
                        }
                        throw m1211b("Unterminated escape sequence");
                    default:
                        throw m1211b("Invalid escape sequence");
                }
            }
            return c;
        }
        throw m1211b("Unterminated escape sequence");
    }

    /* renamed from: b */
    private IOException m1211b(String str) throws IOException {
        throw new MalformedJsonException(str + m1192s());
    }

    /* renamed from: z */
    private void m1185z() throws IOException {
        m1210b(true);
        this.f23091G--;
        int i = this.f23091G;
        char[] cArr = f23063b;
        if (i + cArr.length <= this.f23092H || m1212b(cArr.length)) {
            int i2 = 0;
            while (true) {
                char[] cArr2 = f23063b;
                if (i2 >= cArr2.length) {
                    this.f23091G += cArr2.length;
                    return;
                } else if (this.f23090F[this.f23091G + i2] == cArr2[i2]) {
                    i2++;
                } else {
                    return;
                }
            }
        }
    }
}
