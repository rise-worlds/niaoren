package p110z1;

import android.view.View;
import android.widget.AdapterView;

/* renamed from: z1.ze */
/* loaded from: classes3.dex */
final class AdapterViewItemClickObservable extends Observable<Integer> {

    /* renamed from: a */
    private final AdapterView<?> f23780a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AdapterViewItemClickObservable(AdapterView<?> adapterView) {
        this.f23780a = adapterView;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super Integer> assVar) {
        if (C5596xi.m125a(assVar)) {
            C5622a aVar = new C5622a(this.f23780a, assVar);
            assVar.onSubscribe(aVar);
            this.f23780a.setOnItemClickListener(aVar);
        }
    }

    /* compiled from: AdapterViewItemClickObservable.java */
    /* renamed from: z1.ze$a */
    /* loaded from: classes3.dex */
    static final class C5622a extends MainThreadDisposable implements AdapterView.OnItemClickListener {

        /* renamed from: a */
        private final AdapterView<?> f23781a;

        /* renamed from: b */
        private final Observer<? super Integer> f23782b;

        C5622a(AdapterView<?> adapterView, Observer<? super Integer> assVar) {
            this.f23781a = adapterView;
            this.f23782b = assVar;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (!isDisposed()) {
                this.f23782b.onNext(Integer.valueOf(i));
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f23781a.setOnItemClickListener(null);
        }
    }
}
