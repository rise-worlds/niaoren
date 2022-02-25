package p110z1;

import android.widget.SearchView;

/* renamed from: z1.aaz */
/* loaded from: classes3.dex */
final class SearchViewQueryTextChangesObservable extends InitialValueObservable<CharSequence> {

    /* renamed from: a */
    private final SearchView f15080a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SearchViewQueryTextChangesObservable(SearchView searchView) {
        this.f15080a = searchView;
    }

    @Override // p110z1.InitialValueObservable
    /* renamed from: b */
    protected void mo36b(Observer<? super CharSequence> assVar) {
        if (C5596xi.m125a(assVar)) {
            C3307a aVar = new C3307a(this.f15080a, assVar);
            this.f15080a.setOnQueryTextListener(aVar);
            assVar.onSubscribe(aVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public CharSequence mo37a() {
        return this.f15080a.getQuery();
    }

    /* compiled from: SearchViewQueryTextChangesObservable.java */
    /* renamed from: z1.aaz$a */
    /* loaded from: classes3.dex */
    static final class C3307a extends MainThreadDisposable implements SearchView.OnQueryTextListener {

        /* renamed from: a */
        private final SearchView f15081a;

        /* renamed from: b */
        private final Observer<? super CharSequence> f15082b;

        @Override // android.widget.SearchView.OnQueryTextListener
        public boolean onQueryTextSubmit(String str) {
            return false;
        }

        C3307a(SearchView searchView, Observer<? super CharSequence> assVar) {
            this.f15081a = searchView;
            this.f15082b = assVar;
        }

        @Override // android.widget.SearchView.OnQueryTextListener
        public boolean onQueryTextChange(String str) {
            if (isDisposed()) {
                return false;
            }
            this.f15082b.onNext(str);
            return true;
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f15081a.setOnQueryTextListener(null);
        }
    }
}
