package p110z1;

import android.widget.CompoundButton;

/* renamed from: z1.aac */
/* loaded from: classes3.dex */
final class CompoundButtonCheckedChangeObservable extends InitialValueObservable<Boolean> {

    /* renamed from: a */
    private final CompoundButton f15058a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CompoundButtonCheckedChangeObservable(CompoundButton compoundButton) {
        this.f15058a = compoundButton;
    }

    @Override // p110z1.InitialValueObservable
    /* renamed from: b */
    protected void mo36b(Observer<? super Boolean> assVar) {
        if (C5596xi.m125a(assVar)) {
            C3300a aVar = new C3300a(this.f15058a, assVar);
            assVar.onSubscribe(aVar);
            this.f15058a.setOnCheckedChangeListener(aVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public Boolean mo37a() {
        return Boolean.valueOf(this.f15058a.isChecked());
    }

    /* compiled from: CompoundButtonCheckedChangeObservable.java */
    /* renamed from: z1.aac$a */
    /* loaded from: classes3.dex */
    static final class C3300a extends MainThreadDisposable implements CompoundButton.OnCheckedChangeListener {

        /* renamed from: a */
        private final CompoundButton f15059a;

        /* renamed from: b */
        private final Observer<? super Boolean> f15060b;

        C3300a(CompoundButton compoundButton, Observer<? super Boolean> assVar) {
            this.f15059a = compoundButton;
            this.f15060b = assVar;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (!isDisposed()) {
                this.f15060b.onNext(Boolean.valueOf(z));
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f15059a.setOnCheckedChangeListener(null);
        }
    }
}
