package p110z1;

import java.util.Arrays;

/* renamed from: z1.jb */
/* loaded from: classes3.dex */
public final class DefaultPlacement {

    /* renamed from: a */
    public final int f22074a;

    /* renamed from: b */
    public final byte[] f22075b;

    /* renamed from: c */
    private final CharSequence f22076c;

    /* renamed from: d */
    private final int f22077d;

    public DefaultPlacement(CharSequence charSequence, int i, int i2) {
        this.f22076c = charSequence;
        this.f22074a = i;
        this.f22077d = i2;
        this.f22075b = new byte[i * i2];
        Arrays.fill(this.f22075b, (byte) -1);
    }

    /* renamed from: b */
    private int m2342b() {
        return this.f22077d;
    }

    /* renamed from: c */
    private int m2339c() {
        return this.f22074a;
    }

    /* renamed from: d */
    private byte[] m2337d() {
        return this.f22075b;
    }

    /* renamed from: a */
    private boolean m2346a(int i, int i2) {
        return this.f22075b[(i2 * this.f22074a) + i] == 1;
    }

    /* renamed from: a */
    private void m2343a(int i, int i2, boolean z) {
        this.f22075b[(i2 * this.f22074a) + i] = z ? (byte) 1 : (byte) 0;
    }

    /* renamed from: b */
    private boolean m2340b(int i, int i2) {
        return this.f22075b[(i2 * this.f22074a) + i] >= 0;
    }

    /* renamed from: a */
    public final void m2348a() {
        int i;
        int i2;
        int i3 = 4;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            int i6 = this.f22077d;
            if (i3 == i6 && i4 == 0) {
                i5++;
                m2344a(i6 - 1, 0, i5, 1);
                m2344a(this.f22077d - 1, 1, i5, 2);
                m2344a(this.f22077d - 1, 2, i5, 3);
                m2344a(0, this.f22074a - 2, i5, 4);
                m2344a(0, this.f22074a - 1, i5, 5);
                m2344a(1, this.f22074a - 1, i5, 6);
                m2344a(2, this.f22074a - 1, i5, 7);
                m2344a(3, this.f22074a - 1, i5, 8);
            }
            int i7 = this.f22077d;
            if (i3 == i7 - 2 && i4 == 0 && this.f22074a % 4 != 0) {
                i5++;
                m2344a(i7 - 3, 0, i5, 1);
                m2344a(this.f22077d - 2, 0, i5, 2);
                m2344a(this.f22077d - 1, 0, i5, 3);
                m2344a(0, this.f22074a - 4, i5, 4);
                m2344a(0, this.f22074a - 3, i5, 5);
                m2344a(0, this.f22074a - 2, i5, 6);
                m2344a(0, this.f22074a - 1, i5, 7);
                m2344a(1, this.f22074a - 1, i5, 8);
            }
            int i8 = this.f22077d;
            if (i3 == i8 - 2 && i4 == 0 && this.f22074a % 8 == 4) {
                i5++;
                m2344a(i8 - 3, 0, i5, 1);
                m2344a(this.f22077d - 2, 0, i5, 2);
                m2344a(this.f22077d - 1, 0, i5, 3);
                m2344a(0, this.f22074a - 2, i5, 4);
                m2344a(0, this.f22074a - 1, i5, 5);
                m2344a(1, this.f22074a - 1, i5, 6);
                m2344a(2, this.f22074a - 1, i5, 7);
                m2344a(3, this.f22074a - 1, i5, 8);
            }
            int i9 = this.f22077d;
            if (i3 == i9 + 4 && i4 == 2 && this.f22074a % 8 == 0) {
                i5++;
                m2344a(i9 - 1, 0, i5, 1);
                m2344a(this.f22077d - 1, this.f22074a - 1, i5, 2);
                m2344a(0, this.f22074a - 3, i5, 3);
                m2344a(0, this.f22074a - 2, i5, 4);
                m2344a(0, this.f22074a - 1, i5, 5);
                m2344a(1, this.f22074a - 3, i5, 6);
                m2344a(1, this.f22074a - 2, i5, 7);
                m2344a(1, this.f22074a - 1, i5, 8);
            }
            do {
                if (i3 < this.f22077d && i4 >= 0 && !m2340b(i4, i3)) {
                    i5++;
                    m2345a(i3, i4, i5);
                }
                i3 -= 2;
                i4 += 2;
                if (i3 < 0) {
                    break;
                }
            } while (i4 < this.f22074a);
            int i10 = i3 + 1;
            int i11 = i4 + 3;
            do {
                if (i10 >= 0 && i11 < this.f22074a && !m2340b(i11, i10)) {
                    i5++;
                    m2345a(i10, i11, i5);
                }
                i10 += 2;
                i11 -= 2;
                if (i10 >= this.f22077d) {
                    break;
                }
            } while (i11 >= 0);
            i3 = i10 + 3;
            i4 = i11 + 1;
            i = this.f22077d;
            if (i3 >= i && i4 >= (i2 = this.f22074a)) {
                break;
            }
        }
        if (!m2340b(i2 - 1, i - 1)) {
            m2343a(this.f22074a - 1, this.f22077d - 1, true);
            m2343a(this.f22074a - 2, this.f22077d - 2, true);
        }
    }

    /* renamed from: a */
    private void m2344a(int i, int i2, int i3, int i4) {
        if (i < 0) {
            int i5 = this.f22077d;
            i += i5;
            i2 += 4 - ((i5 + 4) % 8);
        }
        if (i2 < 0) {
            int i6 = this.f22074a;
            i2 += i6;
            i += 4 - ((i6 + 4) % 8);
        }
        boolean z = true;
        if ((this.f22076c.charAt(i3) & (1 << (8 - i4))) == 0) {
            z = false;
        }
        m2343a(i2, i, z);
    }

    /* renamed from: a */
    private void m2345a(int i, int i2, int i3) {
        int i4 = i - 2;
        int i5 = i2 - 2;
        m2344a(i4, i5, i3, 1);
        int i6 = i2 - 1;
        m2344a(i4, i6, i3, 2);
        int i7 = i - 1;
        m2344a(i7, i5, i3, 3);
        m2344a(i7, i6, i3, 4);
        m2344a(i7, i2, i3, 5);
        m2344a(i, i5, i3, 6);
        m2344a(i, i6, i3, 7);
        m2344a(i, i2, i3, 8);
    }

    /* renamed from: a */
    private void m2347a(int i) {
        m2344a(this.f22077d - 1, 0, i, 1);
        m2344a(this.f22077d - 1, 1, i, 2);
        m2344a(this.f22077d - 1, 2, i, 3);
        m2344a(0, this.f22074a - 2, i, 4);
        m2344a(0, this.f22074a - 1, i, 5);
        m2344a(1, this.f22074a - 1, i, 6);
        m2344a(2, this.f22074a - 1, i, 7);
        m2344a(3, this.f22074a - 1, i, 8);
    }

    /* renamed from: b */
    private void m2341b(int i) {
        m2344a(this.f22077d - 3, 0, i, 1);
        m2344a(this.f22077d - 2, 0, i, 2);
        m2344a(this.f22077d - 1, 0, i, 3);
        m2344a(0, this.f22074a - 4, i, 4);
        m2344a(0, this.f22074a - 3, i, 5);
        m2344a(0, this.f22074a - 2, i, 6);
        m2344a(0, this.f22074a - 1, i, 7);
        m2344a(1, this.f22074a - 1, i, 8);
    }

    /* renamed from: c */
    private void m2338c(int i) {
        m2344a(this.f22077d - 3, 0, i, 1);
        m2344a(this.f22077d - 2, 0, i, 2);
        m2344a(this.f22077d - 1, 0, i, 3);
        m2344a(0, this.f22074a - 2, i, 4);
        m2344a(0, this.f22074a - 1, i, 5);
        m2344a(1, this.f22074a - 1, i, 6);
        m2344a(2, this.f22074a - 1, i, 7);
        m2344a(3, this.f22074a - 1, i, 8);
    }

    /* renamed from: d */
    private void m2336d(int i) {
        m2344a(this.f22077d - 1, 0, i, 1);
        m2344a(this.f22077d - 1, this.f22074a - 1, i, 2);
        m2344a(0, this.f22074a - 3, i, 3);
        m2344a(0, this.f22074a - 2, i, 4);
        m2344a(0, this.f22074a - 1, i, 5);
        m2344a(1, this.f22074a - 3, i, 6);
        m2344a(1, this.f22074a - 2, i, 7);
        m2344a(1, this.f22074a - 1, i, 8);
    }
}
