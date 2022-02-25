package p110z1;

import android.support.annotation.Nullable;
import android.widget.SeekBar;

/* renamed from: z1.abd */
/* loaded from: classes3.dex */
final class SeekBarChangeObservable extends InitialValueObservable<Integer> {

    /* renamed from: a */
    private final SeekBar f15086a;
    @Nullable

    /* renamed from: b */
    private final Boolean f15087b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SeekBarChangeObservable(SeekBar seekBar, @Nullable Boolean bool) {
        this.f15086a = seekBar;
        this.f15087b = bool;
    }

    @Override // p110z1.InitialValueObservable
    /* renamed from: b */
    protected void mo36b(Observer<? super Integer> assVar) {
        if (C5596xi.m125a(assVar)) {
            C3310a aVar = new C3310a(this.f15086a, this.f15087b, assVar);
            this.f15086a.setOnSeekBarChangeListener(aVar);
            assVar.onSubscribe(aVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public Integer mo37a() {
        return Integer.valueOf(this.f15086a.getProgress());
    }

    /* compiled from: SeekBarChangeObservable.java */
    /* renamed from: z1.abd$a */
    /* loaded from: classes3.dex */
    static final class C3310a extends MainThreadDisposable implements SeekBar.OnSeekBarChangeListener {

        /* renamed from: a */
        private final SeekBar f15088a;

        /* renamed from: b */
        private final Boolean f15089b;

        /* renamed from: c */
        private final Observer<? super Integer> f15090c;

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
        }

        C3310a(SeekBar seekBar, Boolean bool, Observer<? super Integer> assVar) {
            this.f15088a = seekBar;
            this.f15089b = bool;
            this.f15090c = assVar;
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (!isDisposed()) {
                Boolean bool = this.f15089b;
                if (bool == null || bool.booleanValue() == z) {
                    this.f15090c.onNext(Integer.valueOf(i));
                }
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f15088a.setOnSeekBarChangeListener(null);
        }
    }
}
