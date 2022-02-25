package p110z1;

import android.view.KeyEvent;
import android.widget.TextView;

/* renamed from: z1.abm */
/* loaded from: classes3.dex */
final class TextViewEditorActionEventObservable extends Observable<TextViewEditorActionEvent> {

    /* renamed from: a */
    private final TextView f15097a;

    /* renamed from: b */
    private final Predicate<? super TextViewEditorActionEvent> f15098b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TextViewEditorActionEventObservable(TextView textView, Predicate<? super TextViewEditorActionEvent> auxVar) {
        this.f15097a = textView;
        this.f15098b = auxVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super TextViewEditorActionEvent> assVar) {
        if (C5596xi.m125a(assVar)) {
            C3313a aVar = new C3313a(this.f15097a, assVar, this.f15098b);
            assVar.onSubscribe(aVar);
            this.f15097a.setOnEditorActionListener(aVar);
        }
    }

    /* compiled from: TextViewEditorActionEventObservable.java */
    /* renamed from: z1.abm$a */
    /* loaded from: classes3.dex */
    static final class C3313a extends MainThreadDisposable implements TextView.OnEditorActionListener {

        /* renamed from: a */
        private final TextView f15099a;

        /* renamed from: b */
        private final Observer<? super TextViewEditorActionEvent> f15100b;

        /* renamed from: c */
        private final Predicate<? super TextViewEditorActionEvent> f15101c;

        C3313a(TextView textView, Observer<? super TextViewEditorActionEvent> assVar, Predicate<? super TextViewEditorActionEvent> auxVar) {
            this.f15099a = textView;
            this.f15100b = assVar;
            this.f15101c = auxVar;
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            TextViewEditorActionEvent a = TextViewEditorActionEvent.m14447a(this.f15099a, i, keyEvent);
            try {
                if (isDisposed() || !this.f15101c.test(a)) {
                    return false;
                }
                this.f15100b.onNext(a);
                return true;
            } catch (Exception e) {
                this.f15100b.onError(e);
                dispose();
                return false;
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f15099a.setOnEditorActionListener(null);
        }
    }
}
