package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bfm */
/* loaded from: classes3.dex */
public final class MaybeEqualSingle<T> extends Single<Boolean> {

    /* renamed from: a */
    final MaybeSource<? extends T> f18595a;

    /* renamed from: b */
    final MaybeSource<? extends T> f18596b;

    /* renamed from: c */
    final BiPredicate<? super T, ? super T> f18597c;

    public MaybeEqualSingle(MaybeSource<? extends T> asiVar, MaybeSource<? extends T> asiVar2, BiPredicate<? super T, ? super T> aujVar) {
        this.f18595a = asiVar;
        this.f18596b = asiVar2;
        this.f18597c = aujVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super Boolean> asxVar) {
        C4284a aVar = new C4284a(asxVar, this.f18597c);
        asxVar.onSubscribe(aVar);
        aVar.subscribe(this.f18595a, this.f18596b);
    }

    /* compiled from: MaybeEqualSingle.java */
    /* renamed from: z1.bfm$a */
    /* loaded from: classes3.dex */
    static final class C4284a<T> extends AtomicInteger implements Disposable {
        final SingleObserver<? super Boolean> downstream;
        final BiPredicate<? super T, ? super T> isEqual;
        final C4285b<T> observer1 = new C4285b<>(this);
        final C4285b<T> observer2 = new C4285b<>(this);

        C4284a(SingleObserver<? super Boolean> asxVar, BiPredicate<? super T, ? super T> aujVar) {
            super(2);
            this.downstream = asxVar;
            this.isEqual = aujVar;
        }

        void subscribe(MaybeSource<? extends T> asiVar, MaybeSource<? extends T> asiVar2) {
            asiVar.mo10646a(this.observer1);
            asiVar2.mo10646a(this.observer2);
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.observer1.dispose();
            this.observer2.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(this.observer1.get());
        }

        void done() {
            if (decrementAndGet() == 0) {
                Object obj = this.observer1.value;
                Object obj2 = this.observer2.value;
                if (obj == null || obj2 == null) {
                    this.downstream.onSuccess(Boolean.valueOf(obj == null && obj2 == null));
                    return;
                }
                try {
                    this.downstream.onSuccess(Boolean.valueOf(this.isEqual.mo9871a(obj, obj2)));
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.downstream.onError(th);
                }
            }
        }

        void error(C4285b<T> bVar, Throwable th) {
            if (getAndSet(0) > 0) {
                C4285b<T> bVar2 = this.observer1;
                if (bVar == bVar2) {
                    this.observer2.dispose();
                } else {
                    bVar2.dispose();
                }
                this.downstream.onError(th);
                return;
            }
            RxJavaPlugins.m9212a(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MaybeEqualSingle.java */
    /* renamed from: z1.bfm$b */
    /* loaded from: classes3.dex */
    public static final class C4285b<T> extends AtomicReference<Disposable> implements MaybeObserver<T> {
        private static final long serialVersionUID = -3031974433025990931L;
        final C4284a<T> parent;
        Object value;

        C4285b(C4284a<T> aVar) {
            this.parent = aVar;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this, atrVar);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            this.value = t;
            this.parent.done();
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.parent.error(this, th);
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            this.parent.done();
        }
    }
}
