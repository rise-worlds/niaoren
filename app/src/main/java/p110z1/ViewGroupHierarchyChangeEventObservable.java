package p110z1;

import android.view.View;
import android.view.ViewGroup;

/* renamed from: z1.yj */
/* loaded from: classes3.dex */
final class ViewGroupHierarchyChangeEventObservable extends Observable<ViewGroupHierarchyChangeEvent> {

    /* renamed from: a */
    private final ViewGroup f23721a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewGroupHierarchyChangeEventObservable(ViewGroup viewGroup) {
        this.f23721a = viewGroup;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super ViewGroupHierarchyChangeEvent> assVar) {
        if (C5596xi.m125a(assVar)) {
            ViewGroup$OnHierarchyChangeListenerC5605a aVar = new ViewGroup$OnHierarchyChangeListenerC5605a(this.f23721a, assVar);
            assVar.onSubscribe(aVar);
            this.f23721a.setOnHierarchyChangeListener(aVar);
        }
    }

    /* compiled from: ViewGroupHierarchyChangeEventObservable.java */
    /* renamed from: z1.yj$a */
    /* loaded from: classes3.dex */
    static final class ViewGroup$OnHierarchyChangeListenerC5605a extends MainThreadDisposable implements ViewGroup.OnHierarchyChangeListener {

        /* renamed from: a */
        private final ViewGroup f23722a;

        /* renamed from: b */
        private final Observer<? super ViewGroupHierarchyChangeEvent> f23723b;

        ViewGroup$OnHierarchyChangeListenerC5605a(ViewGroup viewGroup, Observer<? super ViewGroupHierarchyChangeEvent> assVar) {
            this.f23722a = viewGroup;
            this.f23723b = assVar;
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewAdded(View view, View view2) {
            if (!isDisposed()) {
                this.f23723b.onNext(ViewGroupHierarchyChildViewAddEvent.m65a(this.f23722a, view2));
            }
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewRemoved(View view, View view2) {
            if (!isDisposed()) {
                this.f23723b.onNext(ViewGroupHierarchyChildViewRemoveEvent.m64a(this.f23722a, view2));
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f23722a.setOnHierarchyChangeListener(null);
        }
    }
}
