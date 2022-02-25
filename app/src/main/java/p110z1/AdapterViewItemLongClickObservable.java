package p110z1;

import android.view.View;
import android.widget.AdapterView;
import java.util.concurrent.Callable;

/* renamed from: z1.zh */
/* loaded from: classes3.dex */
final class AdapterViewItemLongClickObservable extends Observable<Integer> {

    /* renamed from: a */
    private final AdapterView<?> f23788a;

    /* renamed from: b */
    private final Callable<Boolean> f23789b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AdapterViewItemLongClickObservable(AdapterView<?> adapterView, Callable<Boolean> callable) {
        this.f23788a = adapterView;
        this.f23789b = callable;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super Integer> assVar) {
        if (C5596xi.m125a(assVar)) {
            C5624a aVar = new C5624a(this.f23788a, assVar, this.f23789b);
            assVar.onSubscribe(aVar);
            this.f23788a.setOnItemLongClickListener(aVar);
        }
    }

    /* compiled from: AdapterViewItemLongClickObservable.java */
    /* renamed from: z1.zh$a */
    /* loaded from: classes3.dex */
    static final class C5624a extends MainThreadDisposable implements AdapterView.OnItemLongClickListener {

        /* renamed from: a */
        private final AdapterView<?> f23790a;

        /* renamed from: b */
        private final Observer<? super Integer> f23791b;

        /* renamed from: c */
        private final Callable<Boolean> f23792c;

        C5624a(AdapterView<?> adapterView, Observer<? super Integer> assVar, Callable<Boolean> callable) {
            this.f23790a = adapterView;
            this.f23791b = assVar;
            this.f23792c = callable;
        }

        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (isDisposed()) {
                return false;
            }
            try {
                if (!this.f23792c.call().booleanValue()) {
                    return false;
                }
                this.f23791b.onNext(Integer.valueOf(i));
                return true;
            } catch (Exception e) {
                this.f23791b.onError(e);
                dispose();
                return false;
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f23790a.setOnItemLongClickListener(null);
        }
    }
}
