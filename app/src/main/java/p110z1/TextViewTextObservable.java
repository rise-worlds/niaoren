package p110z1;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

/* renamed from: z1.abq */
/* loaded from: classes3.dex */
final class TextViewTextObservable extends InitialValueObservable<CharSequence> {

    /* renamed from: a */
    private final TextView f15110a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TextViewTextObservable(TextView textView) {
        this.f15110a = textView;
    }

    @Override // p110z1.InitialValueObservable
    /* renamed from: b */
    protected void mo36b(Observer<? super CharSequence> assVar) {
        C3316a aVar = new C3316a(this.f15110a, assVar);
        assVar.onSubscribe(aVar);
        this.f15110a.addTextChangedListener(aVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public CharSequence mo37a() {
        return this.f15110a.getText();
    }

    /* compiled from: TextViewTextObservable.java */
    /* renamed from: z1.abq$a */
    /* loaded from: classes3.dex */
    static final class C3316a extends MainThreadDisposable implements TextWatcher {

        /* renamed from: a */
        private final TextView f15111a;

        /* renamed from: b */
        private final Observer<? super CharSequence> f15112b;

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        C3316a(TextView textView, Observer<? super CharSequence> assVar) {
            this.f15111a = textView;
            this.f15112b = assVar;
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (!isDisposed()) {
                this.f15112b.onNext(charSequence);
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f15111a.removeTextChangedListener(this);
        }
    }
}
