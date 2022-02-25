package p110z1;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

/* renamed from: z1.abi */
/* loaded from: classes3.dex */
final class TextViewAfterTextChangeEventObservable extends InitialValueObservable<TextViewAfterTextChangeEvent> {

    /* renamed from: a */
    private final TextView f15091a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TextViewAfterTextChangeEventObservable(TextView textView) {
        this.f15091a = textView;
    }

    @Override // p110z1.InitialValueObservable
    /* renamed from: b */
    protected void mo36b(Observer<? super TextViewAfterTextChangeEvent> assVar) {
        C3311a aVar = new C3311a(this.f15091a, assVar);
        assVar.onSubscribe(aVar);
        this.f15091a.addTextChangedListener(aVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public TextViewAfterTextChangeEvent mo37a() {
        TextView textView = this.f15091a;
        return TextViewAfterTextChangeEvent.m14452a(textView, textView.getEditableText());
    }

    /* compiled from: TextViewAfterTextChangeEventObservable.java */
    /* renamed from: z1.abi$a */
    /* loaded from: classes3.dex */
    static final class C3311a extends MainThreadDisposable implements TextWatcher {

        /* renamed from: a */
        private final TextView f15092a;

        /* renamed from: b */
        private final Observer<? super TextViewAfterTextChangeEvent> f15093b;

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        C3311a(TextView textView, Observer<? super TextViewAfterTextChangeEvent> assVar) {
            this.f15092a = textView;
            this.f15093b = assVar;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            this.f15093b.onNext(TextViewAfterTextChangeEvent.m14452a(this.f15092a, editable));
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f15092a.removeTextChangedListener(this);
        }
    }
}
