package p110z1;

import android.view.View;
import android.widget.AdapterView;

/* renamed from: z1.zj */
/* loaded from: classes3.dex */
final class AdapterViewItemSelectionObservable extends InitialValueObservable<Integer> {

    /* renamed from: a */
    private final AdapterView<?> f23793a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AdapterViewItemSelectionObservable(AdapterView<?> adapterView) {
        this.f23793a = adapterView;
    }

    @Override // p110z1.InitialValueObservable
    /* renamed from: b */
    protected void mo36b(Observer<? super Integer> assVar) {
        if (C5596xi.m125a(assVar)) {
            C5625a aVar = new C5625a(this.f23793a, assVar);
            this.f23793a.setOnItemSelectedListener(aVar);
            assVar.onSubscribe(aVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public Integer mo37a() {
        return Integer.valueOf(this.f23793a.getSelectedItemPosition());
    }

    /* compiled from: AdapterViewItemSelectionObservable.java */
    /* renamed from: z1.zj$a */
    /* loaded from: classes3.dex */
    static final class C5625a extends MainThreadDisposable implements AdapterView.OnItemSelectedListener {

        /* renamed from: a */
        private final AdapterView<?> f23794a;

        /* renamed from: b */
        private final Observer<? super Integer> f23795b;

        C5625a(AdapterView<?> adapterView, Observer<? super Integer> assVar) {
            this.f23794a = adapterView;
            this.f23795b = assVar;
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            if (!isDisposed()) {
                this.f23795b.onNext(Integer.valueOf(i));
            }
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
            if (!isDisposed()) {
                this.f23795b.onNext(-1);
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f23794a.setOnItemSelectedListener(null);
        }
    }
}
