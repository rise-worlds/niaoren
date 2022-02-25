package p110z1;

import android.view.View;
import android.widget.AdapterView;

/* renamed from: z1.zm */
/* loaded from: classes3.dex */
final class AdapterViewSelectionObservable extends InitialValueObservable<AdapterViewSelectionEvent> {

    /* renamed from: a */
    private final AdapterView<?> f23796a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AdapterViewSelectionObservable(AdapterView<?> adapterView) {
        this.f23796a = adapterView;
    }

    @Override // p110z1.InitialValueObservable
    /* renamed from: b */
    protected void mo36b(Observer<? super AdapterViewSelectionEvent> assVar) {
        if (C5596xi.m125a(assVar)) {
            C5626a aVar = new C5626a(this.f23796a, assVar);
            this.f23796a.setOnItemSelectedListener(aVar);
            assVar.onSubscribe(aVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public AdapterViewSelectionEvent mo37a() {
        int selectedItemPosition = this.f23796a.getSelectedItemPosition();
        if (selectedItemPosition == -1) {
            return AdapterViewNothingSelectionEvent.m38a(this.f23796a);
        }
        return AdapterViewItemSelectionEvent.m40a(this.f23796a, this.f23796a.getSelectedView(), selectedItemPosition, this.f23796a.getSelectedItemId());
    }

    /* compiled from: AdapterViewSelectionObservable.java */
    /* renamed from: z1.zm$a */
    /* loaded from: classes3.dex */
    static final class C5626a extends MainThreadDisposable implements AdapterView.OnItemSelectedListener {

        /* renamed from: a */
        private final AdapterView<?> f23797a;

        /* renamed from: b */
        private final Observer<? super AdapterViewSelectionEvent> f23798b;

        C5626a(AdapterView<?> adapterView, Observer<? super AdapterViewSelectionEvent> assVar) {
            this.f23797a = adapterView;
            this.f23798b = assVar;
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            if (!isDisposed()) {
                this.f23798b.onNext(AdapterViewItemSelectionEvent.m40a(adapterView, view, i, j));
            }
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
            if (!isDisposed()) {
                this.f23798b.onNext(AdapterViewNothingSelectionEvent.m38a(adapterView));
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f23797a.setOnItemSelectedListener(null);
        }
    }
}
