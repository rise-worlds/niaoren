package p110z1;

import android.widget.RatingBar;

/* renamed from: z1.aah */
/* loaded from: classes3.dex */
final class RatingBarRatingChangeEventObservable extends InitialValueObservable<RatingBarChangeEvent> {

    /* renamed from: a */
    private final RatingBar f15071a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RatingBarRatingChangeEventObservable(RatingBar ratingBar) {
        this.f15071a = ratingBar;
    }

    @Override // p110z1.InitialValueObservable
    /* renamed from: b */
    protected void mo36b(Observer<? super RatingBarChangeEvent> assVar) {
        if (C5596xi.m125a(assVar)) {
            C3304a aVar = new C3304a(this.f15071a, assVar);
            this.f15071a.setOnRatingBarChangeListener(aVar);
            assVar.onSubscribe(aVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public RatingBarChangeEvent mo37a() {
        RatingBar ratingBar = this.f15071a;
        return RatingBarChangeEvent.m14529a(ratingBar, ratingBar.getRating(), false);
    }

    /* compiled from: RatingBarRatingChangeEventObservable.java */
    /* renamed from: z1.aah$a */
    /* loaded from: classes3.dex */
    static final class C3304a extends MainThreadDisposable implements RatingBar.OnRatingBarChangeListener {

        /* renamed from: a */
        private final RatingBar f15072a;

        /* renamed from: b */
        private final Observer<? super RatingBarChangeEvent> f15073b;

        C3304a(RatingBar ratingBar, Observer<? super RatingBarChangeEvent> assVar) {
            this.f15072a = ratingBar;
            this.f15073b = assVar;
        }

        @Override // android.widget.RatingBar.OnRatingBarChangeListener
        public void onRatingChanged(RatingBar ratingBar, float f, boolean z) {
            if (!isDisposed()) {
                this.f15073b.onNext(RatingBarChangeEvent.m14529a(ratingBar, f, z));
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f15072a.setOnRatingBarChangeListener(null);
        }
    }
}
