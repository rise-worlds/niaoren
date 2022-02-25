package p110z1;

import android.view.MotionEvent;
import android.view.View;

/* renamed from: z1.yv */
/* loaded from: classes3.dex */
final class ViewTouchObservable extends Observable<MotionEvent> {

    /* renamed from: a */
    private final View f23751a;

    /* renamed from: b */
    private final Predicate<? super MotionEvent> f23752b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewTouchObservable(View view, Predicate<? super MotionEvent> auxVar) {
        this.f23751a = view;
        this.f23752b = auxVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super MotionEvent> assVar) {
        if (C5596xi.m125a(assVar)) {
            View$OnTouchListenerC5613a aVar = new View$OnTouchListenerC5613a(this.f23751a, this.f23752b, assVar);
            assVar.onSubscribe(aVar);
            this.f23751a.setOnTouchListener(aVar);
        }
    }

    /* compiled from: ViewTouchObservable.java */
    /* renamed from: z1.yv$a */
    /* loaded from: classes3.dex */
    static final class View$OnTouchListenerC5613a extends MainThreadDisposable implements View.OnTouchListener {

        /* renamed from: a */
        private final View f23753a;

        /* renamed from: b */
        private final Predicate<? super MotionEvent> f23754b;

        /* renamed from: c */
        private final Observer<? super MotionEvent> f23755c;

        View$OnTouchListenerC5613a(View view, Predicate<? super MotionEvent> auxVar, Observer<? super MotionEvent> assVar) {
            this.f23753a = view;
            this.f23754b = auxVar;
            this.f23755c = assVar;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (isDisposed()) {
                return false;
            }
            try {
                if (!this.f23754b.test(motionEvent)) {
                    return false;
                }
                this.f23755c.onNext(motionEvent);
                return true;
            } catch (Exception e) {
                this.f23755c.onError(e);
                dispose();
                return false;
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f23753a.setOnTouchListener(null);
        }
    }
}
