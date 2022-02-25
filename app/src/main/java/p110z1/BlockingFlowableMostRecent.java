package p110z1;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: z1.ayt */
/* loaded from: classes3.dex */
public final class BlockingFlowableMostRecent<T> implements Iterable<T> {

    /* renamed from: a */
    final Flowable<T> f17819a;

    /* renamed from: b */
    final T f17820b;

    public BlockingFlowableMostRecent(Flowable<T> arvVar, T t) {
        this.f17819a = arvVar;
        this.f17820b = t;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        C3980a aVar = new C3980a(this.f17820b);
        this.f17819a.m11187a((FlowableSubscriber) aVar);
        return aVar.m9827a();
    }

    /* compiled from: BlockingFlowableMostRecent.java */
    /* renamed from: z1.ayt$a */
    /* loaded from: classes3.dex */
    static final class C3980a<T> extends DefaultSubscriber<T> {

        /* renamed from: a */
        volatile Object f17821a;

        C3980a(T t) {
            this.f17821a = NotificationLite.next(t);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.f17821a = NotificationLite.complete();
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.f17821a = NotificationLite.error(th);
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            this.f17821a = NotificationLite.next(t);
        }

        /* renamed from: a */
        public C3980a<T>.C3981a m9827a() {
            return new C3981a();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: BlockingFlowableMostRecent.java */
        /* renamed from: z1.ayt$a$a */
        /* loaded from: classes3.dex */
        public final class C3981a implements Iterator<T> {

            /* renamed from: b */
            private Object f17823b;

            C3981a() {
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                this.f17823b = C3980a.this.f17821a;
                return !NotificationLite.isComplete(this.f17823b);
            }

            @Override // java.util.Iterator
            public T next() {
                try {
                    if (this.f17823b == null) {
                        this.f17823b = C3980a.this.f17821a;
                    }
                    if (NotificationLite.isComplete(this.f17823b)) {
                        throw new NoSuchElementException();
                    } else if (!NotificationLite.isError(this.f17823b)) {
                        return (T) NotificationLite.getValue(this.f17823b);
                    } else {
                        throw ExceptionHelper.m9432a(NotificationLite.getError(this.f17823b));
                    }
                } finally {
                    this.f17823b = null;
                }
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException("Read only iterator");
            }
        }
    }
}
