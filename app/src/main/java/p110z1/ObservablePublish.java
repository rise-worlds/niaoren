package p110z1;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bll */
/* loaded from: classes3.dex */
public final class ObservablePublish<T> extends ConnectableObservable<T> implements HasUpstreamObservableSource<T> {

    /* renamed from: a */
    final ObservableSource<T> f19255a;

    /* renamed from: b */
    final AtomicReference<C4504b<T>> f19256b;

    /* renamed from: c */
    final ObservableSource<T> f19257c;

    /* renamed from: w */
    public static <T> ConnectableObservable<T> m9593w(ObservableSource<T> asqVar) {
        AtomicReference atomicReference = new AtomicReference();
        return RxJavaPlugins.m9190a((ConnectableObservable) new ObservablePublish(new C4505c(atomicReference), asqVar, atomicReference));
    }

    private ObservablePublish(ObservableSource<T> asqVar, ObservableSource<T> asqVar2, AtomicReference<C4504b<T>> atomicReference) {
        this.f19257c = asqVar;
        this.f19255a = asqVar2;
        this.f19256b = atomicReference;
    }

    @Override // p110z1.HasUpstreamObservableSource
    /* renamed from: a */
    public ObservableSource<T> mo9587a() {
        return this.f19255a;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        this.f19257c.subscribe(assVar);
    }

    @Override // p110z1.ConnectableObservable
    /* renamed from: k */
    public void mo9358k(Consumer<? super Disposable> aumVar) {
        C4504b<T> bVar;
        while (true) {
            bVar = this.f19256b.get();
            if (bVar != null && !bVar.isDisposed()) {
                break;
            }
            C4504b<T> bVar2 = new C4504b<>(this.f19256b);
            if (this.f19256b.compareAndSet(bVar, bVar2)) {
                bVar = bVar2;
                break;
            }
        }
        boolean z = true;
        if (bVar.f19262e.get() || !bVar.f19262e.compareAndSet(false, true)) {
            z = false;
        }
        try {
            aumVar.accept(bVar);
            if (z) {
                this.f19255a.subscribe(bVar);
            }
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            throw ExceptionHelper.m9432a(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservablePublish.java */
    /* renamed from: z1.bll$b */
    /* loaded from: classes3.dex */
    public static final class C4504b<T> implements Observer<T>, Disposable {

        /* renamed from: b */
        static final C4503a[] f19258b = new C4503a[0];

        /* renamed from: c */
        static final C4503a[] f19259c = new C4503a[0];

        /* renamed from: a */
        final AtomicReference<C4504b<T>> f19260a;

        /* renamed from: f */
        final AtomicReference<Disposable> f19263f = new AtomicReference<>();

        /* renamed from: d */
        final AtomicReference<C4503a<T>[]> f19261d = new AtomicReference<>(f19258b);

        /* renamed from: e */
        final AtomicBoolean f19262e = new AtomicBoolean();

        C4504b(AtomicReference<C4504b<T>> atomicReference) {
            this.f19260a = atomicReference;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (this.f19261d.getAndSet(f19259c) != f19259c) {
                this.f19260a.compareAndSet(this, null);
                DisposableHelper.dispose(this.f19263f);
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19261d.get() == f19259c;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this.f19263f, atrVar);
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            for (C4503a<T> aVar : this.f19261d.get()) {
                aVar.child.onNext(t);
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f19260a.compareAndSet(this, null);
            C4503a<T>[] andSet = this.f19261d.getAndSet(f19259c);
            if (andSet.length != 0) {
                for (C4503a<T> aVar : andSet) {
                    aVar.child.onError(th);
                }
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.f19260a.compareAndSet(this, null);
            for (C4503a<T> aVar : this.f19261d.getAndSet(f19259c)) {
                aVar.child.onComplete();
            }
        }

        /* renamed from: a */
        boolean m9592a(C4503a<T> aVar) {
            C4503a<T>[] aVarArr;
            C4503a<T>[] aVarArr2;
            do {
                aVarArr = this.f19261d.get();
                if (aVarArr == f19259c) {
                    return false;
                }
                int length = aVarArr.length;
                aVarArr2 = new C4503a[length + 1];
                System.arraycopy(aVarArr, 0, aVarArr2, 0, length);
                aVarArr2[length] = aVar;
            } while (!this.f19261d.compareAndSet(aVarArr, aVarArr2));
            return true;
        }

        /* renamed from: b */
        void m9591b(C4503a<T> aVar) {
            C4503a<T>[] aVarArr;
            C4503a<T>[] aVarArr2;
            do {
                aVarArr = this.f19261d.get();
                int length = aVarArr.length;
                if (length != 0) {
                    int i = -1;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            break;
                        } else if (aVarArr[i2].equals(aVar)) {
                            i = i2;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (i >= 0) {
                        if (length == 1) {
                            aVarArr2 = f19258b;
                        } else {
                            C4503a<T>[] aVarArr3 = new C4503a[length - 1];
                            System.arraycopy(aVarArr, 0, aVarArr3, 0, i);
                            System.arraycopy(aVarArr, i + 1, aVarArr3, i, (length - i) - 1);
                            aVarArr2 = aVarArr3;
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } while (!this.f19261d.compareAndSet(aVarArr, aVarArr2));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservablePublish.java */
    /* renamed from: z1.bll$a */
    /* loaded from: classes3.dex */
    public static final class C4503a<T> extends AtomicReference<Object> implements Disposable {
        private static final long serialVersionUID = -1100270633763673112L;
        final Observer<? super T> child;

        C4503a(Observer<? super T> assVar) {
            this.child = assVar;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return get() == this;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            Object andSet = getAndSet(this);
            if (andSet != null && andSet != this) {
                ((C4504b) andSet).m9591b(this);
            }
        }

        void setParent(C4504b<T> bVar) {
            if (!compareAndSet(null, bVar)) {
                bVar.m9591b(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservablePublish.java */
    /* renamed from: z1.bll$c */
    /* loaded from: classes3.dex */
    public static final class C4505c<T> implements ObservableSource<T> {

        /* renamed from: a */
        private final AtomicReference<C4504b<T>> f19264a;

        C4505c(AtomicReference<C4504b<T>> atomicReference) {
            this.f19264a = atomicReference;
        }

        @Override // p110z1.ObservableSource
        public void subscribe(Observer<? super T> assVar) {
            C4503a aVar = new C4503a(assVar);
            assVar.onSubscribe(aVar);
            while (true) {
                C4504b<T> bVar = this.f19264a.get();
                if (bVar == null || bVar.isDisposed()) {
                    C4504b<T> bVar2 = new C4504b<>(this.f19264a);
                    if (!this.f19264a.compareAndSet(bVar, bVar2)) {
                        continue;
                    } else {
                        bVar = bVar2;
                    }
                }
                if (bVar.m9592a(aVar)) {
                    aVar.setParent(bVar);
                    return;
                }
            }
        }
    }
}
