package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.ayh */
/* loaded from: classes3.dex */
public final class CompletableResumeNext extends Completable {

    /* renamed from: a */
    final CompletableSource f17777a;

    /* renamed from: b */
    final Function<? super Throwable, ? extends CompletableSource> f17778b;

    public CompletableResumeNext(CompletableSource arsVar, Function<? super Throwable, ? extends CompletableSource> aunVar) {
        this.f17777a = arsVar;
        this.f17778b = aunVar;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        C3967a aVar = new C3967a(arpVar, this.f17778b);
        arpVar.onSubscribe(aVar);
        this.f17777a.mo11309a(aVar);
    }

    /* compiled from: CompletableResumeNext.java */
    /* renamed from: z1.ayh$a */
    /* loaded from: classes3.dex */
    static final class C3967a extends AtomicReference<Disposable> implements CompletableObserver, Disposable {
        private static final long serialVersionUID = 5018523762564524046L;
        final CompletableObserver downstream;
        final Function<? super Throwable, ? extends CompletableSource> errorMapper;
        boolean once;

        C3967a(CompletableObserver arpVar, Function<? super Throwable, ? extends CompletableSource> aunVar) {
            this.downstream = arpVar;
            this.errorMapper = aunVar;
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.replace(this, atrVar);
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
        public void onComplete() {
            this.downstream.onComplete();
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            if (this.once) {
                this.downstream.onError(th);
                return;
            }
            this.once = true;
            try {
                ((CompletableSource) ObjectHelper.m9873a(this.errorMapper.apply(th), "The errorMapper returned a null CompletableSource")).mo11309a(this);
            } catch (Throwable th2) {
                Exceptions.m9983b(th2);
                this.downstream.onError(new CompositeException(th, th2));
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }
    }
}
