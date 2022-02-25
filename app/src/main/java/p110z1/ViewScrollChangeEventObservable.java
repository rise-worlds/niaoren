package p110z1;

import android.support.annotation.RequiresApi;
import android.view.View;

@RequiresApi(23)
/* renamed from: z1.yt */
/* loaded from: classes3.dex */
final class ViewScrollChangeEventObservable extends Observable<ViewScrollChangeEvent> {

    /* renamed from: a */
    private final View f23745a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewScrollChangeEventObservable(View view) {
        this.f23745a = view;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super ViewScrollChangeEvent> assVar) {
        if (C5596xi.m125a(assVar)) {
            View$OnScrollChangeListenerC5611a aVar = new View$OnScrollChangeListenerC5611a(this.f23745a, assVar);
            assVar.onSubscribe(aVar);
            this.f23745a.setOnScrollChangeListener(aVar);
        }
    }

    /* compiled from: ViewScrollChangeEventObservable.java */
    /* renamed from: z1.yt$a */
    /* loaded from: classes3.dex */
    static final class View$OnScrollChangeListenerC5611a extends MainThreadDisposable implements View.OnScrollChangeListener {

        /* renamed from: a */
        private final View f23746a;

        /* renamed from: b */
        private final Observer<? super ViewScrollChangeEvent> f23747b;

        View$OnScrollChangeListenerC5611a(View view, Observer<? super ViewScrollChangeEvent> assVar) {
            this.f23746a = view;
            this.f23747b = assVar;
        }

        @Override // android.view.View.OnScrollChangeListener
        public void onScrollChange(View view, int i, int i2, int i3, int i4) {
            if (!isDisposed()) {
                this.f23747b.onNext(ViewScrollChangeEvent.m52a(view, i, i2, i3, i4));
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f23746a.setOnScrollChangeListener(null);
        }
    }
}
