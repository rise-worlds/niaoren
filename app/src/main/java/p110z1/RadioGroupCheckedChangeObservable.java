package p110z1;

import android.widget.RadioGroup;

/* renamed from: z1.aaf */
/* loaded from: classes3.dex */
final class RadioGroupCheckedChangeObservable extends InitialValueObservable<Integer> {

    /* renamed from: a */
    private final RadioGroup f15067a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RadioGroupCheckedChangeObservable(RadioGroup radioGroup) {
        this.f15067a = radioGroup;
    }

    @Override // p110z1.InitialValueObservable
    /* renamed from: b */
    protected void mo36b(Observer<? super Integer> assVar) {
        if (C5596xi.m125a(assVar)) {
            C3303a aVar = new C3303a(this.f15067a, assVar);
            this.f15067a.setOnCheckedChangeListener(aVar);
            assVar.onSubscribe(aVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public Integer mo37a() {
        return Integer.valueOf(this.f15067a.getCheckedRadioButtonId());
    }

    /* compiled from: RadioGroupCheckedChangeObservable.java */
    /* renamed from: z1.aaf$a */
    /* loaded from: classes3.dex */
    static final class C3303a extends MainThreadDisposable implements RadioGroup.OnCheckedChangeListener {

        /* renamed from: a */
        private final RadioGroup f15068a;

        /* renamed from: b */
        private final Observer<? super Integer> f15069b;

        /* renamed from: c */
        private int f15070c = -1;

        C3303a(RadioGroup radioGroup, Observer<? super Integer> assVar) {
            this.f15068a = radioGroup;
            this.f15069b = assVar;
        }

        @Override // android.widget.RadioGroup.OnCheckedChangeListener
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if (!isDisposed() && i != this.f15070c) {
                this.f15070c = i;
                this.f15069b.onNext(Integer.valueOf(i));
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f15068a.setOnCheckedChangeListener(null);
        }
    }
}
