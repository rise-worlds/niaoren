package p110z1;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

/* renamed from: z1.abp */
/* loaded from: classes3.dex */
final class TextViewTextChangeEventObservable extends InitialValueObservable<TextViewTextChangeEvent> {

    /* renamed from: a */
    private final TextView f15107a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TextViewTextChangeEventObservable(TextView textView) {
        this.f15107a = textView;
    }

    @Override // p110z1.InitialValueObservable
    /* renamed from: b */
    protected void mo36b(Observer<? super TextViewTextChangeEvent> assVar) {
        C3315a aVar = new C3315a(this.f15107a, assVar);
        assVar.onSubscribe(aVar);
        this.f15107a.addTextChangedListener(aVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public TextViewTextChangeEvent mo37a() {
        TextView textView = this.f15107a;
        return TextViewTextChangeEvent.m14443a(textView, textView.getText(), 0, 0, 0);
    }

    /* compiled from: TextViewTextChangeEventObservable.java */
    /* renamed from: z1.abp$a */
    /* loaded from: classes3.dex */
    static final class C3315a extends MainThreadDisposable implements TextWatcher {

        /* renamed from: a */
        private final TextView f15108a;

        /* renamed from: b */
        private final Observer<? super TextViewTextChangeEvent> f15109b;

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        C3315a(TextView textView, Observer<? super TextViewTextChangeEvent> assVar) {
            this.f15108a = textView;
            this.f15109b = assVar;
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (!isDisposed()) {
                this.f15109b.onNext(TextViewTextChangeEvent.m14443a(this.f15108a, charSequence, i, i2, i3));
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f15108a.removeTextChangedListener(this);
        }
    }
}
