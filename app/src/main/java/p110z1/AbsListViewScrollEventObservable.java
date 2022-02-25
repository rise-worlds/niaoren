package p110z1;

import android.widget.AbsListView;

/* renamed from: z1.za */
/* loaded from: classes3.dex */
final class AbsListViewScrollEventObservable extends Observable<AbsListViewScrollEvent> {

    /* renamed from: a */
    private final AbsListView f23767a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbsListViewScrollEventObservable(AbsListView absListView) {
        this.f23767a = absListView;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super AbsListViewScrollEvent> assVar) {
        if (C5596xi.m125a(assVar)) {
            C5618a aVar = new C5618a(this.f23767a, assVar);
            assVar.onSubscribe(aVar);
            this.f23767a.setOnScrollListener(aVar);
        }
    }

    /* compiled from: AbsListViewScrollEventObservable.java */
    /* renamed from: z1.za$a */
    /* loaded from: classes3.dex */
    static final class C5618a extends MainThreadDisposable implements AbsListView.OnScrollListener {

        /* renamed from: a */
        private final AbsListView f23768a;

        /* renamed from: b */
        private final Observer<? super AbsListViewScrollEvent> f23769b;

        /* renamed from: c */
        private int f23770c = 0;

        C5618a(AbsListView absListView, Observer<? super AbsListViewScrollEvent> assVar) {
            this.f23768a = absListView;
            this.f23769b = assVar;
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i) {
            this.f23770c = i;
            if (!isDisposed()) {
                AbsListView absListView2 = this.f23768a;
                this.f23769b.onNext(AbsListViewScrollEvent.m47a(absListView2, i, absListView2.getFirstVisiblePosition(), this.f23768a.getChildCount(), this.f23768a.getCount()));
            }
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (!isDisposed()) {
                this.f23769b.onNext(AbsListViewScrollEvent.m47a(this.f23768a, this.f23770c, i, i2, i3));
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f23768a.setOnScrollListener(null);
        }
    }
}
