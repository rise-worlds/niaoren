package p110z1;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: z1.bii */
/* loaded from: classes3.dex */
public final class BlockingObservableMostRecent<T> implements Iterable<T> {

    /* renamed from: a */
    final ObservableSource<T> f18814a;

    /* renamed from: b */
    final T f18815b;

    public BlockingObservableMostRecent(ObservableSource<T> asqVar, T t) {
        this.f18814a = asqVar;
        this.f18815b = t;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        C4379a aVar = new C4379a(this.f18815b);
        this.f18814a.subscribe(aVar);
        return aVar.m9668a();
    }

    /* compiled from: BlockingObservableMostRecent.java */
    /* renamed from: z1.bii$a */
    /* loaded from: classes3.dex */
    static final class C4379a<T> extends DefaultObserver<T> {

        /* renamed from: a */
        volatile Object f18816a;

        C4379a(T t) {
            this.f18816a = NotificationLite.next(t);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.f18816a = NotificationLite.complete();
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f18816a = NotificationLite.error(th);
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            this.f18816a = NotificationLite.next(t);
        }

        /* renamed from: a */
        public C4379a<T>.C4380a m9668a() {
            return new C4380a();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: BlockingObservableMostRecent.java */
        /* renamed from: z1.bii$a$a */
        /* loaded from: classes3.dex */
        public final class C4380a implements Iterator<T> {

            /* renamed from: b */
            private Object f18818b;

            C4380a() {
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                this.f18818b = C4379a.this.f18816a;
                return !NotificationLite.isComplete(this.f18818b);
            }

            @Override // java.util.Iterator
            public T next() {
                try {
                    if (this.f18818b == null) {
                        this.f18818b = C4379a.this.f18816a;
                    }
                    if (NotificationLite.isComplete(this.f18818b)) {
                        throw new NoSuchElementException();
                    } else if (!NotificationLite.isError(this.f18818b)) {
                        return (T) NotificationLite.getValue(this.f18818b);
                    } else {
                        throw ExceptionHelper.m9432a(NotificationLite.getError(this.f18818b));
                    }
                } finally {
                    this.f18818b = null;
                }
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException("Read only iterator");
            }
        }
    }
}
