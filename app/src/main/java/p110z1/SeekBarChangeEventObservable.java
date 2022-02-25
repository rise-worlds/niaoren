package p110z1;

import android.widget.SeekBar;

/* renamed from: z1.abc */
/* loaded from: classes3.dex */
final class SeekBarChangeEventObservable extends InitialValueObservable<SeekBarChangeEvent> {

    /* renamed from: a */
    private final SeekBar f15083a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SeekBarChangeEventObservable(SeekBar seekBar) {
        this.f15083a = seekBar;
    }

    @Override // p110z1.InitialValueObservable
    /* renamed from: b */
    protected void mo36b(Observer<? super SeekBarChangeEvent> assVar) {
        if (C5596xi.m125a(assVar)) {
            C3309a aVar = new C3309a(this.f15083a, assVar);
            this.f15083a.setOnSeekBarChangeListener(aVar);
            assVar.onSubscribe(aVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public SeekBarChangeEvent mo37a() {
        SeekBar seekBar = this.f15083a;
        return SeekBarProgressChangeEvent.m14455a(seekBar, seekBar.getProgress(), false);
    }

    /* compiled from: SeekBarChangeEventObservable.java */
    /* renamed from: z1.abc$a */
    /* loaded from: classes3.dex */
    static final class C3309a extends MainThreadDisposable implements SeekBar.OnSeekBarChangeListener {

        /* renamed from: a */
        private final SeekBar f15084a;

        /* renamed from: b */
        private final Observer<? super SeekBarChangeEvent> f15085b;

        C3309a(SeekBar seekBar, Observer<? super SeekBarChangeEvent> assVar) {
            this.f15084a = seekBar;
            this.f15085b = assVar;
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (!isDisposed()) {
                this.f15085b.onNext(SeekBarProgressChangeEvent.m14455a(seekBar, i, z));
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            if (!isDisposed()) {
                this.f15085b.onNext(SeekBarStartChangeEvent.m14454a(seekBar));
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            if (!isDisposed()) {
                this.f15085b.onNext(SeekBarStopChangeEvent.m14453a(seekBar));
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f15084a.setOnSeekBarChangeListener(null);
        }
    }
}
