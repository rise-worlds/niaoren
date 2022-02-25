package p110z1;

import android.database.DataSetObserver;
import android.widget.Adapter;

/* renamed from: z1.zb */
/* loaded from: classes3.dex */
final class AdapterDataChangeObservable<T extends Adapter> extends InitialValueObservable<T> {

    /* renamed from: a */
    private final T f23771a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AdapterDataChangeObservable(T t) {
        this.f23771a = t;
    }

    @Override // p110z1.InitialValueObservable
    /* renamed from: b */
    protected void mo36b(Observer<? super T> assVar) {
        if (C5596xi.m125a(assVar)) {
            C5619a aVar = new C5619a(this.f23771a, assVar);
            this.f23771a.registerDataSetObserver(aVar.f23772a);
            assVar.onSubscribe(aVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public T mo37a() {
        return this.f23771a;
    }

    /* compiled from: AdapterDataChangeObservable.java */
    /* renamed from: z1.zb$a */
    /* loaded from: classes3.dex */
    static final class C5619a<T extends Adapter> extends MainThreadDisposable {

        /* renamed from: a */
        final DataSetObserver f23772a;

        /* renamed from: b */
        private final T f23773b;

        C5619a(final T t, final Observer<? super T> assVar) {
            this.f23773b = t;
            this.f23772a = new DataSetObserver() { // from class: z1.zb.a.1
                @Override // android.database.DataSetObserver
                public void onChanged() {
                    if (!C5619a.this.isDisposed()) {
                        assVar.onNext(t);
                    }
                }
            };
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f23773b.unregisterDataSetObserver(this.f23772a);
        }
    }
}
