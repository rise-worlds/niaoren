package p110z1;

import android.view.DragEvent;
import android.view.View;

/* renamed from: z1.yg */
/* loaded from: classes3.dex */
final class ViewDragObservable extends Observable<DragEvent> {

    /* renamed from: a */
    private final View f23713a;

    /* renamed from: b */
    private final Predicate<? super DragEvent> f23714b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewDragObservable(View view, Predicate<? super DragEvent> auxVar) {
        this.f23713a = view;
        this.f23714b = auxVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super DragEvent> assVar) {
        if (C5596xi.m125a(assVar)) {
            View$OnDragListenerC5603a aVar = new View$OnDragListenerC5603a(this.f23713a, this.f23714b, assVar);
            assVar.onSubscribe(aVar);
            this.f23713a.setOnDragListener(aVar);
        }
    }

    /* compiled from: ViewDragObservable.java */
    /* renamed from: z1.yg$a */
    /* loaded from: classes3.dex */
    static final class View$OnDragListenerC5603a extends MainThreadDisposable implements View.OnDragListener {

        /* renamed from: a */
        private final View f23715a;

        /* renamed from: b */
        private final Predicate<? super DragEvent> f23716b;

        /* renamed from: c */
        private final Observer<? super DragEvent> f23717c;

        View$OnDragListenerC5603a(View view, Predicate<? super DragEvent> auxVar, Observer<? super DragEvent> assVar) {
            this.f23715a = view;
            this.f23716b = auxVar;
            this.f23717c = assVar;
        }

        @Override // android.view.View.OnDragListener
        public boolean onDrag(View view, DragEvent dragEvent) {
            if (isDisposed()) {
                return false;
            }
            try {
                if (!this.f23716b.test(dragEvent)) {
                    return false;
                }
                this.f23717c.onNext(dragEvent);
                return true;
            } catch (Exception e) {
                this.f23717c.onError(e);
                dispose();
                return false;
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f23715a.setOnDragListener(null);
        }
    }
}
