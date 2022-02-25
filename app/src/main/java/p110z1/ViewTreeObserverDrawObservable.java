package p110z1;

import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewTreeObserver;

@RequiresApi(16)
/* renamed from: z1.yw */
/* loaded from: classes3.dex */
final class ViewTreeObserverDrawObservable extends Observable<Object> {

    /* renamed from: a */
    private final View f23756a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewTreeObserverDrawObservable(View view) {
        this.f23756a = view;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super Object> assVar) {
        if (C5596xi.m125a(assVar)) {
            ViewTreeObserver$OnDrawListenerC5614a aVar = new ViewTreeObserver$OnDrawListenerC5614a(this.f23756a, assVar);
            assVar.onSubscribe(aVar);
            this.f23756a.getViewTreeObserver().addOnDrawListener(aVar);
        }
    }

    /* compiled from: ViewTreeObserverDrawObservable.java */
    /* renamed from: z1.yw$a */
    /* loaded from: classes3.dex */
    static final class ViewTreeObserver$OnDrawListenerC5614a extends MainThreadDisposable implements ViewTreeObserver.OnDrawListener {

        /* renamed from: a */
        private final View f23757a;

        /* renamed from: b */
        private final Observer<? super Object> f23758b;

        ViewTreeObserver$OnDrawListenerC5614a(View view, Observer<? super Object> assVar) {
            this.f23757a = view;
            this.f23758b = assVar;
        }

        @Override // android.view.ViewTreeObserver.OnDrawListener
        public void onDraw() {
            if (!isDisposed()) {
                this.f23758b.onNext(EnumC5595xh.INSTANCE);
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f23757a.getViewTreeObserver().removeOnDrawListener(this);
        }
    }
}
