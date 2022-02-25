package p110z1;

import android.view.View;
import android.view.ViewTreeObserver;

/* renamed from: z1.yx */
/* loaded from: classes3.dex */
final class ViewTreeObserverGlobalLayoutObservable extends Observable<Object> {

    /* renamed from: a */
    private final View f23759a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewTreeObserverGlobalLayoutObservable(View view) {
        this.f23759a = view;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super Object> assVar) {
        if (C5596xi.m125a(assVar)) {
            ViewTreeObserver$OnGlobalLayoutListenerC5615a aVar = new ViewTreeObserver$OnGlobalLayoutListenerC5615a(this.f23759a, assVar);
            assVar.onSubscribe(aVar);
            this.f23759a.getViewTreeObserver().addOnGlobalLayoutListener(aVar);
        }
    }

    /* compiled from: ViewTreeObserverGlobalLayoutObservable.java */
    /* renamed from: z1.yx$a */
    /* loaded from: classes3.dex */
    static final class ViewTreeObserver$OnGlobalLayoutListenerC5615a extends MainThreadDisposable implements ViewTreeObserver.OnGlobalLayoutListener {

        /* renamed from: a */
        private final View f23760a;

        /* renamed from: b */
        private final Observer<? super Object> f23761b;

        ViewTreeObserver$OnGlobalLayoutListenerC5615a(View view, Observer<? super Object> assVar) {
            this.f23760a = view;
            this.f23761b = assVar;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!isDisposed()) {
                this.f23761b.onNext(EnumC5595xh.INSTANCE);
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f23760a.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        }
    }
}
