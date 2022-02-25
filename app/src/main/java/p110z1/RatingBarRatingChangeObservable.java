package p110z1;

import android.widget.RatingBar;

/* renamed from: z1.aai */
/* loaded from: classes3.dex */
final class RatingBarRatingChangeObservable extends InitialValueObservable<Float> {

    /* renamed from: a */
    private final RatingBar f15074a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RatingBarRatingChangeObservable(RatingBar ratingBar) {
        this.f15074a = ratingBar;
    }

    @Override // p110z1.InitialValueObservable
    /* renamed from: b */
    protected void mo36b(Observer<? super Float> assVar) {
        if (C5596xi.m125a(assVar)) {
            C3305a aVar = new C3305a(this.f15074a, assVar);
            this.f15074a.setOnRatingBarChangeListener(aVar);
            assVar.onSubscribe(aVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public Float mo37a() {
        return Float.valueOf(this.f15074a.getRating());
    }

    /* compiled from: RatingBarRatingChangeObservable.java */
    /* renamed from: z1.aai$a */
    /* loaded from: classes3.dex */
    static final class C3305a extends MainThreadDisposable implements RatingBar.OnRatingBarChangeListener {

        /* renamed from: a */
        private final RatingBar f15075a;

        /* renamed from: b */
        private final Observer<? super Float> f15076b;

        C3305a(RatingBar ratingBar, Observer<? super Float> assVar) {
            this.f15075a = ratingBar;
            this.f15076b = assVar;
        }

        @Override // android.widget.RatingBar.OnRatingBarChangeListener
        public void onRatingChanged(RatingBar ratingBar, float f, boolean z) {
            if (!isDisposed()) {
                this.f15076b.onNext(Float.valueOf(f));
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f15075a.setOnRatingBarChangeListener(null);
        }
    }
}
