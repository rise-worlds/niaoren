package p110z1;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

/* renamed from: z1.zn */
/* loaded from: classes3.dex */
final class AutoCompleteTextViewItemClickEventObservable extends Observable<AdapterViewItemClickEvent> {

    /* renamed from: a */
    private final AutoCompleteTextView f23799a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoCompleteTextViewItemClickEventObservable(AutoCompleteTextView autoCompleteTextView) {
        this.f23799a = autoCompleteTextView;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super AdapterViewItemClickEvent> assVar) {
        if (C5596xi.m125a(assVar)) {
            C5627a aVar = new C5627a(this.f23799a, assVar);
            assVar.onSubscribe(aVar);
            this.f23799a.setOnItemClickListener(aVar);
        }
    }

    /* compiled from: AutoCompleteTextViewItemClickEventObservable.java */
    /* renamed from: z1.zn$a */
    /* loaded from: classes3.dex */
    static final class C5627a extends MainThreadDisposable implements AdapterView.OnItemClickListener {

        /* renamed from: a */
        private final AutoCompleteTextView f23800a;

        /* renamed from: b */
        private final Observer<? super AdapterViewItemClickEvent> f23801b;

        C5627a(AutoCompleteTextView autoCompleteTextView, Observer<? super AdapterViewItemClickEvent> assVar) {
            this.f23800a = autoCompleteTextView;
            this.f23801b = assVar;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (!isDisposed()) {
                this.f23801b.onNext(AdapterViewItemClickEvent.m42a(adapterView, view, i, j));
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f23800a.setOnItemClickListener(null);
        }
    }
}
