package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bqj */
/* loaded from: classes3.dex */
public final class MpscLinkedQueue<T> implements SimplePlainQueue<T> {

    /* renamed from: a */
    private final AtomicReference<C4710a<T>> f19851a = new AtomicReference<>();

    /* renamed from: b */
    private final AtomicReference<C4710a<T>> f19852b = new AtomicReference<>();

    public MpscLinkedQueue() {
        C4710a<T> aVar = new C4710a<>();
        m9535b(aVar);
        m9537a(aVar);
    }

    @Override // p110z1.SimpleQueue
    public boolean offer(T t) {
        if (t != null) {
            C4710a<T> aVar = new C4710a<>(t);
            m9537a(aVar).soNext(aVar);
            return true;
        }
        throw new NullPointerException("Null is not a valid element");
    }

    @Override // p110z1.SimplePlainQueue, p110z1.SimpleQueue
    @atn
    public T poll() {
        C4710a<T> lvNext;
        C4710a<T> c = m9534c();
        C4710a<T> lvNext2 = c.lvNext();
        if (lvNext2 != null) {
            T andNullValue = lvNext2.getAndNullValue();
            m9535b(lvNext2);
            return andNullValue;
        } else if (c == m9538a()) {
            return null;
        } else {
            do {
                lvNext = c.lvNext();
            } while (lvNext == null);
            T andNullValue2 = lvNext.getAndNullValue();
            m9535b(lvNext);
            return andNullValue2;
        }
    }

    @Override // p110z1.SimpleQueue
    public boolean offer(T t, T t2) {
        offer(t);
        offer(t2);
        return true;
    }

    @Override // p110z1.SimpleQueue
    public void clear() {
        while (poll() != null && !isEmpty()) {
        }
    }

    /* renamed from: a */
    C4710a<T> m9538a() {
        return this.f19851a.get();
    }

    /* renamed from: a */
    C4710a<T> m9537a(C4710a<T> aVar) {
        return this.f19851a.getAndSet(aVar);
    }

    /* renamed from: b */
    C4710a<T> m9536b() {
        return this.f19852b.get();
    }

    /* renamed from: c */
    C4710a<T> m9534c() {
        return this.f19852b.get();
    }

    /* renamed from: b */
    void m9535b(C4710a<T> aVar) {
        this.f19852b.lazySet(aVar);
    }

    @Override // p110z1.SimpleQueue
    public boolean isEmpty() {
        return m9536b() == m9538a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MpscLinkedQueue.java */
    /* renamed from: z1.bqj$a */
    /* loaded from: classes3.dex */
    public static final class C4710a<E> extends AtomicReference<C4710a<E>> {
        private static final long serialVersionUID = 2404266111789071508L;
        private E value;

        C4710a() {
        }

        C4710a(E e) {
            spValue(e);
        }

        public E getAndNullValue() {
            E lpValue = lpValue();
            spValue(null);
            return lpValue;
        }

        public E lpValue() {
            return this.value;
        }

        public void spValue(E e) {
            this.value = e;
        }

        public void soNext(C4710a<E> aVar) {
            lazySet(aVar);
        }

        public C4710a<E> lvNext() {
            return get();
        }
    }
}
