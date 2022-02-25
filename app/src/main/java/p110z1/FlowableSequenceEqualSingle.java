package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import p110z1.FlowableSequenceEqual;

/* renamed from: z1.bdd */
/* loaded from: classes3.dex */
public final class FlowableSequenceEqualSingle<T> extends Single<Boolean> implements FuseToFlowable<Boolean> {

    /* renamed from: a */
    final Publisher<? extends T> f18327a;

    /* renamed from: b */
    final Publisher<? extends T> f18328b;

    /* renamed from: c */
    final BiPredicate<? super T, ? super T> f18329c;

    /* renamed from: d */
    final int f18330d;

    public FlowableSequenceEqualSingle(Publisher<? extends T> dbwVar, Publisher<? extends T> dbwVar2, BiPredicate<? super T, ? super T> aujVar, int i) {
        this.f18327a = dbwVar;
        this.f18328b = dbwVar2;
        this.f18329c = aujVar;
        this.f18330d = i;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    public void mo8961b(SingleObserver<? super Boolean> asxVar) {
        C4193a aVar = new C4193a(asxVar, this.f18330d, this.f18329c);
        asxVar.onSubscribe(aVar);
        aVar.subscribe(this.f18327a, this.f18328b);
    }

    @Override // p110z1.FuseToFlowable
    /* renamed from: r_ */
    public Flowable<Boolean> mo9727r_() {
        return RxJavaPlugins.m9207a(new FlowableSequenceEqual(this.f18327a, this.f18328b, this.f18329c, this.f18330d));
    }

    /* compiled from: FlowableSequenceEqualSingle.java */
    /* renamed from: z1.bdd$a */
    /* loaded from: classes3.dex */
    static final class C4193a<T> extends AtomicInteger implements Disposable, FlowableSequenceEqual.AbstractC4191b {
        private static final long serialVersionUID = -6178010334400373240L;
        final BiPredicate<? super T, ? super T> comparer;
        final SingleObserver<? super Boolean> downstream;
        final AtomicThrowable error = new AtomicThrowable();
        final FlowableSequenceEqual.C4192c<T> first;
        final FlowableSequenceEqual.C4192c<T> second;

        /* renamed from: v1 */
        T f18331v1;

        /* renamed from: v2 */
        T f18332v2;

        C4193a(SingleObserver<? super Boolean> asxVar, int i, BiPredicate<? super T, ? super T> aujVar) {
            this.downstream = asxVar;
            this.comparer = aujVar;
            this.first = new FlowableSequenceEqual.C4192c<>(this, i);
            this.second = new FlowableSequenceEqual.C4192c<>(this, i);
        }

        void subscribe(Publisher<? extends T> dbwVar, Publisher<? extends T> dbwVar2) {
            dbwVar.subscribe(this.first);
            dbwVar2.subscribe(this.second);
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.first.cancel();
            this.second.cancel();
            if (getAndIncrement() == 0) {
                this.first.clear();
                this.second.clear();
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.first.get() == SubscriptionHelper.CANCELLED;
        }

        void cancelAndClear() {
            this.first.cancel();
            this.first.clear();
            this.second.cancel();
            this.second.clear();
        }

        @Override // p110z1.FlowableSequenceEqual.AbstractC4191b
        public void drain() {
            if (getAndIncrement() == 0) {
                int i = 1;
                do {
                    SimpleQueue<T> avwVar = this.first.queue;
                    SimpleQueue<T> avwVar2 = this.second.queue;
                    if (avwVar != null && avwVar2 != null) {
                        while (!isDisposed()) {
                            if (this.error.get() != null) {
                                cancelAndClear();
                                this.downstream.onError(this.error.terminate());
                                return;
                            }
                            boolean z = this.first.done;
                            T t = this.f18331v1;
                            if (t == null) {
                                try {
                                    t = avwVar.poll();
                                    this.f18331v1 = t;
                                } catch (Throwable th) {
                                    Exceptions.m9983b(th);
                                    cancelAndClear();
                                    this.error.addThrowable(th);
                                    this.downstream.onError(this.error.terminate());
                                    return;
                                }
                            }
                            boolean z2 = t == null;
                            boolean z3 = this.second.done;
                            T t2 = this.f18332v2;
                            if (t2 == null) {
                                try {
                                    t2 = avwVar2.poll();
                                    this.f18332v2 = t2;
                                } catch (Throwable th2) {
                                    Exceptions.m9983b(th2);
                                    cancelAndClear();
                                    this.error.addThrowable(th2);
                                    this.downstream.onError(this.error.terminate());
                                    return;
                                }
                            }
                            boolean z4 = t2 == null;
                            if (z && z3 && z2 && z4) {
                                this.downstream.onSuccess(true);
                                return;
                            } else if (z && z3 && z2 != z4) {
                                cancelAndClear();
                                this.downstream.onSuccess(false);
                                return;
                            } else if (!z2 && !z4) {
                                try {
                                    if (!this.comparer.mo9871a(t, t2)) {
                                        cancelAndClear();
                                        this.downstream.onSuccess(false);
                                        return;
                                    }
                                    this.f18331v1 = null;
                                    this.f18332v2 = null;
                                    this.first.request();
                                    this.second.request();
                                } catch (Throwable th3) {
                                    Exceptions.m9983b(th3);
                                    cancelAndClear();
                                    this.error.addThrowable(th3);
                                    this.downstream.onError(this.error.terminate());
                                    return;
                                }
                            }
                        }
                        this.first.clear();
                        this.second.clear();
                        return;
                    } else if (isDisposed()) {
                        this.first.clear();
                        this.second.clear();
                        return;
                    } else if (this.error.get() != null) {
                        cancelAndClear();
                        this.downstream.onError(this.error.terminate());
                        return;
                    }
                    i = addAndGet(-i);
                } while (i != 0);
            }
        }

        @Override // p110z1.FlowableSequenceEqual.AbstractC4191b
        public void innerError(Throwable th) {
            if (this.error.addThrowable(th)) {
                drain();
            } else {
                RxJavaPlugins.m9212a(th);
            }
        }
    }
}
