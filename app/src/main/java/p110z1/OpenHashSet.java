package p110z1;

/* renamed from: z1.btd */
/* loaded from: classes3.dex */
public final class OpenHashSet<T> {

    /* renamed from: f */
    private static final int f20097f = -1640531527;

    /* renamed from: a */
    final float f20098a;

    /* renamed from: b */
    int f20099b;

    /* renamed from: c */
    int f20100c;

    /* renamed from: d */
    int f20101d;

    /* renamed from: e */
    T[] f20102e;

    /* renamed from: a */
    static int m9393a(int i) {
        int i2 = i * f20097f;
        return i2 ^ (i2 >>> 16);
    }

    public OpenHashSet() {
        this(16, 0.75f);
    }

    public OpenHashSet(int i) {
        this(i, 0.75f);
    }

    public OpenHashSet(int i, float f) {
        this.f20098a = f;
        int a = Pow2.m9387a(i);
        this.f20099b = a - 1;
        this.f20101d = (int) (f * a);
        this.f20102e = (T[]) new Object[a];
    }

    /* renamed from: a */
    public boolean m9391a(T t) {
        T t2;
        T[] tArr = this.f20102e;
        int i = this.f20099b;
        int a = m9393a(t.hashCode()) & i;
        T t3 = tArr[a];
        if (t3 != null) {
            if (t3.equals(t)) {
                return false;
            }
            do {
                a = (a + 1) & i;
                t2 = tArr[a];
                if (t2 == null) {
                }
            } while (!t2.equals(t));
            return false;
        }
        tArr[a] = t;
        int i2 = this.f20100c + 1;
        this.f20100c = i2;
        if (i2 >= this.f20101d) {
            m9394a();
        }
        return true;
    }

    /* renamed from: b */
    public boolean m9389b(T t) {
        T t2;
        T[] tArr = this.f20102e;
        int i = this.f20099b;
        int a = m9393a(t.hashCode()) & i;
        T t3 = tArr[a];
        if (t3 == null) {
            return false;
        }
        if (t3.equals(t)) {
            return m9392a(a, tArr, i);
        }
        do {
            a = (a + 1) & i;
            t2 = tArr[a];
            if (t2 == null) {
                return false;
            }
        } while (!t2.equals(t));
        return m9392a(a, tArr, i);
    }

    /* renamed from: a */
    boolean m9392a(int i, T[] tArr, int i2) {
        int i3;
        T t;
        this.f20100c--;
        while (true) {
            int i4 = i + 1;
            while (true) {
                i3 = i4 & i2;
                t = tArr[i3];
                if (t == null) {
                    tArr[i] = null;
                    return true;
                }
                int a = m9393a(t.hashCode()) & i2;
                if (i > i3) {
                    if (i >= a && a > i3) {
                        break;
                    }
                    i4 = i3 + 1;
                } else if (i < a && a <= i3) {
                    i4 = i3 + 1;
                }
            }
            tArr[i] = t;
            i = i3;
        }
    }

    /* renamed from: a */
    void m9394a() {
        T[] tArr = this.f20102e;
        int length = tArr.length;
        int i = length << 1;
        int i2 = i - 1;
        T[] tArr2 = (T[]) new Object[i];
        int i3 = this.f20100c;
        while (true) {
            i3--;
            if (i3 != 0) {
                do {
                    length--;
                } while (tArr[length] == null);
                int a = m9393a(tArr[length].hashCode()) & i2;
                if (tArr2[a] != null) {
                    do {
                        a = (a + 1) & i2;
                    } while (tArr2[a] != null);
                }
                tArr2[a] = tArr[length];
            } else {
                this.f20099b = i2;
                this.f20101d = (int) (i * this.f20098a);
                this.f20102e = tArr2;
                return;
            }
        }
    }

    /* renamed from: b */
    public Object[] m9390b() {
        return this.f20102e;
    }

    /* renamed from: c */
    public int m9388c() {
        return this.f20100c;
    }
}
