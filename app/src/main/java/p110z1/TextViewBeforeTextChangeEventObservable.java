package p110z1;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

/* renamed from: z1.abk */
/* loaded from: classes3.dex */
final class TextViewBeforeTextChangeEventObservable extends InitialValueObservable<TextViewBeforeTextChangeEvent> {

    /* renamed from: a */
    private final TextView f15094a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TextViewBeforeTextChangeEventObservable(TextView textView) {
        this.f15094a = textView;
    }

    @Override // p110z1.InitialValueObservable
    /* renamed from: b */
    protected void mo36b(Observer<? super TextViewBeforeTextChangeEvent> assVar) {
        C3312a aVar = new C3312a(this.f15094a, assVar);
        assVar.onSubscribe(aVar);
        this.f15094a.addTextChangedListener(aVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public TextViewBeforeTextChangeEvent mo37a() {
        TextView textView = this.f15094a;
        return TextViewBeforeTextChangeEvent.m14450a(textView, textView.getText(), 0, 0, 0);
    }

    /* compiled from: TextViewBeforeTextChangeEventObservable.java */
    /* renamed from: z1.abk$a */
    /* loaded from: classes3.dex */
    static final class C3312a extends MainThreadDisposable implements TextWatcher {

        /* renamed from: a */
        private final TextView f15095a;

        /* renamed from: b */
        private final Observer<? super TextViewBeforeTextChangeEvent> f15096b;

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        C3312a(TextView textView, Observer<? super TextViewBeforeTextChangeEvent> assVar) {
            this.f15095a = textView;
            this.f15096b = assVar;
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (!isDisposed()) {
                this.f15096b.onNext(TextViewBeforeTextChangeEvent.m14450a(this.f15095a, charSequence, i, i2, i3));
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f15095a.removeTextChangedListener(this);
        }
    }
}
