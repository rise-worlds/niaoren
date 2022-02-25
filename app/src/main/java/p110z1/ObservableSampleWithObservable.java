package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bmb */
/* loaded from: classes3.dex */
public final class ObservableSampleWithObservable<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final ObservableSource<?> f19324b;

    /* renamed from: c */
    final boolean f19325c;

    public ObservableSampleWithObservable(ObservableSource<T> asqVar, ObservableSource<?> asqVar2, boolean z) {
        super(asqVar);
        this.f19324b = asqVar2;
        this.f19325c = z;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        SerializedObserver btyVar = new SerializedObserver(assVar);
        if (this.f19325c) {
            this.f18807a.subscribe(new C4542a(btyVar, this.f19324b));
        } else {
            this.f18807a.subscribe(new C4543b(btyVar, this.f19324b));
        }
    }

    /* compiled from: ObservableSampleWithObservable.java */
    /* renamed from: z1.bmb$c */
    /* loaded from: classes3.dex */
    static abstract class AbstractC4544c<T> extends AtomicReference<T> implements Observer<T>, Disposable {
        private static final long serialVersionUID = -3517602651313910099L;
        final Observer<? super T> downstream;
        final AtomicReference<Disposable> other = new AtomicReference<>();
        final ObservableSource<?> sampler;
        Disposable upstream;

        abstract void completion();

        abstract void run();

        AbstractC4544c(Observer<? super T> assVar, ObservableSource<?> asqVar) {
            this.downstream = assVar;
            this.sampler = asqVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                this.downstream.onSubscribe(this);
                if (this.other.get() == null) {
                    this.sampler.subscribe(new C4545d(this));
                }
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            lazySet(t);
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            DisposableHelper.dispose(this.other);
            this.downstream.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            DisposableHelper.dispose(this.other);
            completion();
        }

        boolean setOther(Disposable atrVar) {
            return DisposableHelper.setOnce(this.other, atrVar);
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this.other);
            this.upstream.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.other.get() == DisposableHelper.DISPOSED;
        }

        public void error(Throwable th) {
            this.upstream.dispose();
            this.downstream.onError(th);
        }

        public void complete() {
            this.upstream.dispose();
            completion();
        }

        void emit() {
            T andSet = getAndSet(null);
            if (andSet != null) {
                this.downstream.onNext(andSet);
            }
        }
    }

    /* compiled from: ObservableSampleWithObservable.java */
    /* renamed from: z1.bmb$d */
    /* loaded from: classes3.dex */
    static final class C4545d<T> implements Observer<Object> {

        /* renamed from: a */
        final AbstractC4544c<T> f19326a;

        C4545d(AbstractC4544c<T> cVar) {
            this.f19326a = cVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            this.f19326a.setOther(atrVar);
        }

        @Override // p110z1.Observer
        public void onNext(Object obj) {
            this.f19326a.run();
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f19326a.error(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.f19326a.complete();
        }
    }

    /* compiled from: ObservableSampleWithObservable.java */
    /* renamed from: z1.bmb$b */
    /* loaded from: classes3.dex */
    static final class C4543b<T> extends AbstractC4544c<T> {
        private static final long serialVersionUID = -3029755663834015785L;

        C4543b(Observer<? super T> assVar, ObservableSource<?> asqVar) {
            super(assVar, asqVar);
        }

        @Override // p110z1.ObservableSampleWithObservable.AbstractC4544c
        void completion() {
            this.downstream.onComplete();
        }

        @Override // p110z1.ObservableSampleWithObservable.AbstractC4544c
        void run() {
            emit();
        }
    }

    /* compiled from: ObservableSampleWithObservable.java */
    /* renamed from: z1.bmb$a */
    /* loaded from: classes3.dex */
    static final class C4542a<T> extends AbstractC4544c<T> {
        private static final long serialVersionUID = -3029755663834015785L;
        volatile boolean done;
        final AtomicInteger wip = new AtomicInteger();

        C4542a(Observer<? super T> assVar, ObservableSource<?> asqVar) {
            super(assVar, asqVar);
        }

        @Override // p110z1.ObservableSampleWithObservable.AbstractC4544c
        void completion() {
            this.done = true;
            if (this.wip.getAndIncrement() == 0) {
                emit();
                this.downstream.onComplete();
            }
        }

        @Override // p110z1.ObservableSampleWithObservable.AbstractC4544c
        void run() {
            if (this.wip.getAndIncrement() == 0) {
                do {
                    boolean z = this.done;
                    emit();
                    if (z) {
                        this.downstream.onComplete();
                        return;
                    }
                } while (this.wip.decrementAndGet() != 0);
            }
        }
    }
}
