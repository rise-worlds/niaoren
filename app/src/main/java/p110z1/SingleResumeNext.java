package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bpy */
/* loaded from: classes3.dex */
public final class SingleResumeNext<T> extends Single<T> {

    /* renamed from: a */
    final SingleSource<? extends T> f19822a;

    /* renamed from: b */
    final Function<? super Throwable, ? extends SingleSource<? extends T>> f19823b;

    public SingleResumeNext(SingleSource<? extends T> ataVar, Function<? super Throwable, ? extends SingleSource<? extends T>> aunVar) {
        this.f19822a = ataVar;
        this.f19823b = aunVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        this.f19822a.mo10018a(new C4694a(asxVar, this.f19823b));
    }

    /* compiled from: SingleResumeNext.java */
    /* renamed from: z1.bpy$a */
    /* loaded from: classes3.dex */
    static final class C4694a<T> extends AtomicReference<Disposable> implements SingleObserver<T>, Disposable {
        private static final long serialVersionUID = -5314538511045349925L;
        final SingleObserver<? super T> downstream;
        final Function<? super Throwable, ? extends SingleSource<? extends T>> nextFunction;

        C4694a(SingleObserver<? super T> asxVar, Function<? super Throwable, ? extends SingleSource<? extends T>> aunVar) {
            this.downstream = asxVar;
            this.nextFunction = aunVar;
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.setOnce(this, atrVar)) {
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            this.downstream.onSuccess(t);
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            try {
                ((SingleSource) ObjectHelper.m9873a(this.nextFunction.apply(th), "The nextFunction returned a null SingleSource.")).mo10018a(new ResumeSingleObserver(this, this.downstream));
            } catch (Throwable th2) {
                Exceptions.m9983b(th2);
                this.downstream.onError(new CompositeException(th, th2));
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }
    }
}
