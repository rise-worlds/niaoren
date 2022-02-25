package p110z1;

import android.view.View;
import android.widget.AdapterView;

/* renamed from: z1.zg */
/* loaded from: classes3.dex */
final class AdapterViewItemLongClickEventObservable extends Observable<AdapterViewItemLongClickEvent> {

    /* renamed from: a */
    private final AdapterView<?> f23783a;

    /* renamed from: b */
    private final Predicate<? super AdapterViewItemLongClickEvent> f23784b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AdapterViewItemLongClickEventObservable(AdapterView<?> adapterView, Predicate<? super AdapterViewItemLongClickEvent> auxVar) {
        this.f23783a = adapterView;
        this.f23784b = auxVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super AdapterViewItemLongClickEvent> assVar) {
        if (C5596xi.m125a(assVar)) {
            C5623a aVar = new C5623a(this.f23783a, assVar, this.f23784b);
            assVar.onSubscribe(aVar);
            this.f23783a.setOnItemLongClickListener(aVar);
        }
    }

    /* compiled from: AdapterViewItemLongClickEventObservable.java */
    /* renamed from: z1.zg$a */
    /* loaded from: classes3.dex */
    static final class C5623a extends MainThreadDisposable implements AdapterView.OnItemLongClickListener {

        /* renamed from: a */
        private final AdapterView<?> f23785a;

        /* renamed from: b */
        private final Observer<? super AdapterViewItemLongClickEvent> f23786b;

        /* renamed from: c */
        private final Predicate<? super AdapterViewItemLongClickEvent> f23787c;

        C5623a(AdapterView<?> adapterView, Observer<? super AdapterViewItemLongClickEvent> assVar, Predicate<? super AdapterViewItemLongClickEvent> auxVar) {
            this.f23785a = adapterView;
            this.f23786b = assVar;
            this.f23787c = auxVar;
        }

        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (isDisposed()) {
                return false;
            }
            AdapterViewItemLongClickEvent a = AdapterViewItemLongClickEvent.m41a(adapterView, view, i, j);
            try {
                if (!this.f23787c.test(a)) {
                    return false;
                }
                this.f23786b.onNext(a);
                return true;
            } catch (Exception e) {
                this.f23786b.onError(e);
                dispose();
                return false;
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f23785a.setOnItemLongClickListener(null);
        }
    }
}
