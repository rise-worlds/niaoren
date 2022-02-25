package p110z1;

import android.view.View;
import android.widget.AdapterView;

/* renamed from: z1.zd */
/* loaded from: classes3.dex */
final class AdapterViewItemClickEventObservable extends Observable<AdapterViewItemClickEvent> {

    /* renamed from: a */
    private final AdapterView<?> f23777a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AdapterViewItemClickEventObservable(AdapterView<?> adapterView) {
        this.f23777a = adapterView;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super AdapterViewItemClickEvent> assVar) {
        if (C5596xi.m125a(assVar)) {
            C5621a aVar = new C5621a(this.f23777a, assVar);
            assVar.onSubscribe(aVar);
            this.f23777a.setOnItemClickListener(aVar);
        }
    }

    /* compiled from: AdapterViewItemClickEventObservable.java */
    /* renamed from: z1.zd$a */
    /* loaded from: classes3.dex */
    static final class C5621a extends MainThreadDisposable implements AdapterView.OnItemClickListener {

        /* renamed from: a */
        private final AdapterView<?> f23778a;

        /* renamed from: b */
        private final Observer<? super AdapterViewItemClickEvent> f23779b;

        C5621a(AdapterView<?> adapterView, Observer<? super AdapterViewItemClickEvent> assVar) {
            this.f23778a = adapterView;
            this.f23779b = assVar;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (!isDisposed()) {
                this.f23779b.onNext(AdapterViewItemClickEvent.m42a(adapterView, view, i, j));
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f23778a.setOnItemClickListener(null);
        }
    }
}
