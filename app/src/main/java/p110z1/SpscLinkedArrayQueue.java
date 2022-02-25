package p110z1;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* renamed from: z1.bql */
/* loaded from: classes3.dex */
public final class SpscLinkedArrayQueue<T> implements SimplePlainQueue<T> {

    /* renamed from: a */
    static final int f19853a = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096).intValue();

    /* renamed from: j */
    private static final Object f19854j = new Object();

    /* renamed from: c */
    int f19856c;

    /* renamed from: d */
    long f19857d;

    /* renamed from: e */
    final int f19858e;

    /* renamed from: f */
    AtomicReferenceArray<Object> f19859f;

    /* renamed from: g */
    final int f19860g;

    /* renamed from: h */
    AtomicReferenceArray<Object> f19861h;

    /* renamed from: b */
    final AtomicLong f19855b = new AtomicLong();

    /* renamed from: i */
    final AtomicLong f19862i = new AtomicLong();

    /* renamed from: b */
    private static int m9522b(int i) {
        return i;
    }

    public SpscLinkedArrayQueue(int i) {
        int a = Pow2.m9387a(Math.max(8, i));
        int i2 = a - 1;
        AtomicReferenceArray<Object> atomicReferenceArray = new AtomicReferenceArray<>(a + 1);
        this.f19859f = atomicReferenceArray;
        this.f19858e = i2;
        m9532a(a);
        this.f19861h = atomicReferenceArray;
        this.f19860g = i2;
        this.f19857d = i2 - 1;
        m9531a(0L);
    }

    @Override // p110z1.SimpleQueue
    public boolean offer(T t) {
        if (t != null) {
            AtomicReferenceArray<Object> atomicReferenceArray = this.f19859f;
            long e = m9516e();
            int i = this.f19858e;
            int a = m9530a(e, i);
            if (e < this.f19857d) {
                return m9525a(atomicReferenceArray, t, e, a);
            }
            long j = this.f19856c + e;
            if (m9520b(atomicReferenceArray, m9530a(j, i)) == null) {
                this.f19857d = j - 1;
                return m9525a(atomicReferenceArray, t, e, a);
            } else if (m9520b(atomicReferenceArray, m9530a(1 + e, i)) == null) {
                return m9525a(atomicReferenceArray, t, e, a);
            } else {
                m9526a(atomicReferenceArray, e, a, t, i);
                return true;
            }
        } else {
            throw new NullPointerException("Null is not a valid element");
        }
    }

    /* renamed from: a */
    private boolean m9525a(AtomicReferenceArray<Object> atomicReferenceArray, T t, long j, int i) {
        m9528a(atomicReferenceArray, i, t);
        m9531a(j + 1);
        return true;
    }

    /* renamed from: a */
    private void m9526a(AtomicReferenceArray<Object> atomicReferenceArray, long j, int i, T t, long j2) {
        AtomicReferenceArray<Object> atomicReferenceArray2 = new AtomicReferenceArray<>(atomicReferenceArray.length());
        this.f19859f = atomicReferenceArray2;
        this.f19857d = (j2 + j) - 1;
        m9528a(atomicReferenceArray2, i, t);
        m9524a(atomicReferenceArray, atomicReferenceArray2);
        m9528a(atomicReferenceArray, i, f19854j);
        m9531a(j + 1);
    }

    /* renamed from: a */
    private void m9524a(AtomicReferenceArray<Object> atomicReferenceArray, AtomicReferenceArray<Object> atomicReferenceArray2) {
        m9528a(atomicReferenceArray, m9522b(atomicReferenceArray.length() - 1), atomicReferenceArray2);
    }

    /* renamed from: a */
    private AtomicReferenceArray<Object> m9529a(AtomicReferenceArray<Object> atomicReferenceArray, int i) {
        int b = m9522b(i);
        AtomicReferenceArray<Object> atomicReferenceArray2 = (AtomicReferenceArray) m9520b(atomicReferenceArray, b);
        m9528a(atomicReferenceArray, b, (Object) null);
        return atomicReferenceArray2;
    }

    @Override // p110z1.SimplePlainQueue, p110z1.SimpleQueue
    @atn
    public T poll() {
        AtomicReferenceArray<Object> atomicReferenceArray = this.f19861h;
        long f = m9515f();
        int i = this.f19860g;
        int a = m9530a(f, i);
        T t = (T) m9520b(atomicReferenceArray, a);
        boolean z = t == f19854j;
        if (t != null && !z) {
            m9528a(atomicReferenceArray, a, (Object) null);
            m9521b(f + 1);
            return t;
        } else if (z) {
            return m9527a(m9529a(atomicReferenceArray, i + 1), f, i);
        } else {
            return null;
        }
    }

    /* renamed from: a */
    private T m9527a(AtomicReferenceArray<Object> atomicReferenceArray, long j, int i) {
        this.f19861h = atomicReferenceArray;
        int a = m9530a(j, i);
        T t = (T) m9520b(atomicReferenceArray, a);
        if (t != null) {
            m9528a(atomicReferenceArray, a, (Object) null);
            m9521b(j + 1);
        }
        return t;
    }

    /* renamed from: a */
    public T m9533a() {
        AtomicReferenceArray<Object> atomicReferenceArray = this.f19861h;
        long f = m9515f();
        int i = this.f19860g;
        T t = (T) m9520b(atomicReferenceArray, m9530a(f, i));
        return t == f19854j ? m9519b(m9529a(atomicReferenceArray, i + 1), f, i) : t;
    }

    /* renamed from: b */
    private T m9519b(AtomicReferenceArray<Object> atomicReferenceArray, long j, int i) {
        this.f19861h = atomicReferenceArray;
        return (T) m9520b(atomicReferenceArray, m9530a(j, i));
    }

    @Override // p110z1.SimpleQueue
    public void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }

    /* renamed from: b */
    public int m9523b() {
        long d = m9517d();
        while (true) {
            long c = m9518c();
            long d2 = m9517d();
            if (d == d2) {
                return (int) (c - d2);
            }
            d = d2;
        }
    }

    @Override // p110z1.SimpleQueue
    public boolean isEmpty() {
        return m9518c() == m9517d();
    }

    /* renamed from: a */
    private void m9532a(int i) {
        this.f19856c = Math.min(i / 4, f19853a);
    }

    /* renamed from: c */
    private long m9518c() {
        return this.f19855b.get();
    }

    /* renamed from: d */
    private long m9517d() {
        return this.f19862i.get();
    }

    /* renamed from: e */
    private long m9516e() {
        return this.f19855b.get();
    }

    /* renamed from: f */
    private long m9515f() {
        return this.f19862i.get();
    }

    /* renamed from: a */
    private void m9531a(long j) {
        this.f19855b.lazySet(j);
    }

    /* renamed from: b */
    private void m9521b(long j) {
        this.f19862i.lazySet(j);
    }

    /* renamed from: a */
    private static int m9530a(long j, int i) {
        return m9522b(((int) j) & i);
    }

    /* renamed from: a */
    private static void m9528a(AtomicReferenceArray<Object> atomicReferenceArray, int i, Object obj) {
        atomicReferenceArray.lazySet(i, obj);
    }

    /* renamed from: b */
    private static <E> Object m9520b(AtomicReferenceArray<Object> atomicReferenceArray, int i) {
        return atomicReferenceArray.get(i);
    }

    @Override // p110z1.SimpleQueue
    public boolean offer(T t, T t2) {
        AtomicReferenceArray<Object> atomicReferenceArray = this.f19859f;
        long c = m9518c();
        int i = this.f19858e;
        long j = 2 + c;
        if (m9520b(atomicReferenceArray, m9530a(j, i)) == null) {
            int a = m9530a(c, i);
            m9528a(atomicReferenceArray, a + 1, t2);
            m9528a(atomicReferenceArray, a, t);
            m9531a(j);
            return true;
        }
        AtomicReferenceArray<Object> atomicReferenceArray2 = new AtomicReferenceArray<>(atomicReferenceArray.length());
        this.f19859f = atomicReferenceArray2;
        int a2 = m9530a(c, i);
        m9528a(atomicReferenceArray2, a2 + 1, t2);
        m9528a(atomicReferenceArray2, a2, t);
        m9524a(atomicReferenceArray, atomicReferenceArray2);
        m9528a(atomicReferenceArray, a2, f19854j);
        m9531a(j);
        return true;
    }
}
