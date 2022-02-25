package p110z1;

import android.view.View;
import android.view.ViewTreeObserver;
import java.util.concurrent.Callable;

/* renamed from: z1.yy */
/* loaded from: classes3.dex */
final class ViewTreeObserverPreDrawObservable extends Observable<Object> {

    /* renamed from: a */
    private final View f23762a;

    /* renamed from: b */
    private final Callable<Boolean> f23763b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewTreeObserverPreDrawObservable(View view, Callable<Boolean> callable) {
        this.f23762a = view;
        this.f23763b = callable;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super Object> assVar) {
        if (C5596xi.m125a(assVar)) {
            ViewTreeObserver$OnPreDrawListenerC5616a aVar = new ViewTreeObserver$OnPreDrawListenerC5616a(this.f23762a, this.f23763b, assVar);
            assVar.onSubscribe(aVar);
            this.f23762a.getViewTreeObserver().addOnPreDrawListener(aVar);
        }
    }

    /* compiled from: ViewTreeObserverPreDrawObservable.java */
    /* renamed from: z1.yy$a */
    /* loaded from: classes3.dex */
    static final class ViewTreeObserver$OnPreDrawListenerC5616a extends MainThreadDisposable implements ViewTreeObserver.OnPreDrawListener {

        /* renamed from: a */
        private final View f23764a;

        /* renamed from: b */
        private final Callable<Boolean> f23765b;

        /* renamed from: c */
        private final Observer<? super Object> f23766c;

        ViewTreeObserver$OnPreDrawListenerC5616a(View view, Callable<Boolean> callable, Observer<? super Object> assVar) {
            this.f23764a = view;
            this.f23765b = callable;
            this.f23766c = assVar;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            if (isDisposed()) {
                return true;
            }
            this.f23766c.onNext(EnumC5595xh.INSTANCE);
            try {
                return this.f23765b.call().booleanValue();
            } catch (Exception e) {
                this.f23766c.onError(e);
                dispose();
                return true;
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f23764a.getViewTreeObserver().removeOnPreDrawListener(this);
        }
    }
}
