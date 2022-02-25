package p110z1;

import android.view.KeyEvent;
import android.widget.TextView;

/* renamed from: z1.abn */
/* loaded from: classes3.dex */
final class TextViewEditorActionObservable extends Observable<Integer> {

    /* renamed from: a */
    private final TextView f15102a;

    /* renamed from: b */
    private final Predicate<? super Integer> f15103b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TextViewEditorActionObservable(TextView textView, Predicate<? super Integer> auxVar) {
        this.f15102a = textView;
        this.f15103b = auxVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super Integer> assVar) {
        if (C5596xi.m125a(assVar)) {
            C3314a aVar = new C3314a(this.f15102a, assVar, this.f15103b);
            assVar.onSubscribe(aVar);
            this.f15102a.setOnEditorActionListener(aVar);
        }
    }

    /* compiled from: TextViewEditorActionObservable.java */
    /* renamed from: z1.abn$a */
    /* loaded from: classes3.dex */
    static final class C3314a extends MainThreadDisposable implements TextView.OnEditorActionListener {

        /* renamed from: a */
        private final TextView f15104a;

        /* renamed from: b */
        private final Observer<? super Integer> f15105b;

        /* renamed from: c */
        private final Predicate<? super Integer> f15106c;

        C3314a(TextView textView, Observer<? super Integer> assVar, Predicate<? super Integer> auxVar) {
            this.f15104a = textView;
            this.f15105b = assVar;
            this.f15106c = auxVar;
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            try {
                if (isDisposed() || !this.f15106c.test(Integer.valueOf(i))) {
                    return false;
                }
                this.f15105b.onNext(Integer.valueOf(i));
                return true;
            } catch (Exception e) {
                this.f15105b.onError(e);
                dispose();
                return false;
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f15104a.setOnEditorActionListener(null);
        }
    }
}
