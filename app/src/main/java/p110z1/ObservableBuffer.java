package p110z1;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: z1.bir */
/* loaded from: classes3.dex */
public final class ObservableBuffer<T, U extends Collection<? super T>> extends AbstractObservableWithUpstream<T, U> {

    /* renamed from: b */
    final int f18860b;

    /* renamed from: c */
    final int f18861c;

    /* renamed from: d */
    final Callable<U> f18862d;

    public ObservableBuffer(ObservableSource<T> asqVar, int i, int i2, Callable<U> callable) {
        super(asqVar);
        this.f18860b = i;
        this.f18861c = i2;
        this.f18862d = callable;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super U> assVar) {
        int i = this.f18861c;
        int i2 = this.f18860b;
        if (i == i2) {
            C4389a aVar = new C4389a(assVar, i2, this.f18862d);
            if (aVar.m9658a()) {
                this.f18807a.subscribe(aVar);
                return;
            }
            return;
        }
        this.f18807a.subscribe(new C4390b(assVar, this.f18860b, this.f18861c, this.f18862d));
    }

    /* compiled from: ObservableBuffer.java */
    /* renamed from: z1.bir$a */
    /* loaded from: classes3.dex */
    static final class C4389a<T, U extends Collection<? super T>> implements Observer<T>, Disposable {

        /* renamed from: a */
        final Observer<? super U> f18863a;

        /* renamed from: b */
        final int f18864b;

        /* renamed from: c */
        final Callable<U> f18865c;

        /* renamed from: d */
        U f18866d;

        /* renamed from: e */
        int f18867e;

        /* renamed from: f */
        Disposable f18868f;

        C4389a(Observer<? super U> assVar, int i, Callable<U> callable) {
            this.f18863a = assVar;
            this.f18864b = i;
            this.f18865c = callable;
        }

        /* renamed from: a */
        boolean m9658a() {
            try {
                this.f18866d = (U) ((Collection) ObjectHelper.m9873a(this.f18865c.call(), "Empty buffer supplied"));
                return true;
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.f18866d = null;
                Disposable atrVar = this.f18868f;
                if (atrVar == null) {
                    EmptyDisposable.error(th, this.f18863a);
                    return false;
                }
                atrVar.dispose();
                this.f18863a.onError(th);
                return false;
            }
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18868f, atrVar)) {
                this.f18868f = atrVar;
                this.f18863a.onSubscribe(this);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18868f.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18868f.isDisposed();
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            U u = this.f18866d;
            if (u != null) {
                u.add(t);
                int i = this.f18867e + 1;
                this.f18867e = i;
                if (i >= this.f18864b) {
                    this.f18863a.onNext(u);
                    this.f18867e = 0;
                    m9658a();
                }
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f18866d = null;
            this.f18863a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            U u = this.f18866d;
            if (u != null) {
                this.f18866d = null;
                if (!u.isEmpty()) {
                    this.f18863a.onNext(u);
                }
                this.f18863a.onComplete();
            }
        }
    }

    /* compiled from: ObservableBuffer.java */
    /* renamed from: z1.bir$b */
    /* loaded from: classes3.dex */
    static final class C4390b<T, U extends Collection<? super T>> extends AtomicBoolean implements Observer<T>, Disposable {
        private static final long serialVersionUID = -8223395059921494546L;
        final Callable<U> bufferSupplier;
        final ArrayDeque<U> buffers = new ArrayDeque<>();
        final int count;
        final Observer<? super U> downstream;
        long index;
        final int skip;
        Disposable upstream;

        C4390b(Observer<? super U> assVar, int i, int i2, Callable<U> callable) {
            this.downstream = assVar;
            this.count = i;
            this.skip = i2;
            this.bufferSupplier = callable;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.upstream.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p110z1.Observer
        public void onNext(T t) {
            long j = this.index;
            this.index = 1 + j;
            if (j % this.skip == 0) {
                try {
                    this.buffers.offer((Collection) ObjectHelper.m9873a(this.bufferSupplier.call(), "The bufferSupplier returned a null collection. Null values are generally not allowed in 2.x operators and sources."));
                } catch (Throwable th) {
                    this.buffers.clear();
                    this.upstream.dispose();
                    this.downstream.onError(th);
                    return;
                }
            }
            Iterator<U> it = this.buffers.iterator();
            while (it.hasNext()) {
                U next = it.next();
                next.add(t);
                if (this.count <= next.size()) {
                    it.remove();
                    this.downstream.onNext(next);
                }
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.buffers.clear();
            this.downstream.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            while (!this.buffers.isEmpty()) {
                this.downstream.onNext(this.buffers.poll());
            }
            this.downstream.onComplete();
        }
    }
}
