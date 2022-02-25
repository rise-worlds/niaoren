package p110z1;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: z1.bbc */
/* loaded from: classes3.dex */
public final class FlowableGenerate<T, S> extends Flowable<T> {

    /* renamed from: b */
    final Callable<S> f18117b;

    /* renamed from: c */
    final BiFunction<S, Emitter<T>, S> f18118c;

    /* renamed from: d */
    final Consumer<? super S> f18119d;

    public FlowableGenerate(Callable<S> callable, BiFunction<S, Emitter<T>, S> auiVar, Consumer<? super S> aumVar) {
        this.f18117b = callable;
        this.f18118c = auiVar;
        this.f18119d = aumVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super T> dbxVar) {
        try {
            dbxVar.onSubscribe(new C4083a(dbxVar, this.f18118c, this.f18119d, this.f18117b.call()));
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            EmptySubscription.error(th, dbxVar);
        }
    }

    /* compiled from: FlowableGenerate.java */
    /* renamed from: z1.bbc$a */
    /* loaded from: classes3.dex */
    static final class C4083a<T, S> extends AtomicLong implements Emitter<T>, dby {
        private static final long serialVersionUID = 7565982551505011832L;
        volatile boolean cancelled;
        final Consumer<? super S> disposeState;
        final Subscriber<? super T> downstream;
        final BiFunction<S, ? super Emitter<T>, S> generator;
        boolean hasNext;
        S state;
        boolean terminate;

        C4083a(Subscriber<? super T> dbxVar, BiFunction<S, ? super Emitter<T>, S> auiVar, Consumer<? super S> aumVar, S s) {
            this.downstream = dbxVar;
            this.generator = auiVar;
            this.disposeState = aumVar;
            this.state = s;
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (SubscriptionHelper.validate(j) && BackpressureHelper.m9449a(this, j) == 0) {
                S s = this.state;
                BiFunction<S, ? super Emitter<T>, S> auiVar = this.generator;
                long j2 = j;
                long j3 = 0;
                while (true) {
                    if (j3 == j2) {
                        j2 = get();
                        if (j3 == j2) {
                            this.state = s;
                            j2 = addAndGet(-j3);
                            if (j2 != 0) {
                                j3 = 0;
                            } else {
                                return;
                            }
                        } else {
                            continue;
                        }
                    } else if (this.cancelled) {
                        this.state = null;
                        dispose(s);
                        return;
                    } else {
                        this.hasNext = false;
                        try {
                            s = auiVar.apply(s, this);
                            if (this.terminate) {
                                this.cancelled = true;
                                this.state = null;
                                dispose(s);
                                return;
                            }
                            j3++;
                        } catch (Throwable th) {
                            Exceptions.m9983b(th);
                            this.cancelled = true;
                            this.state = null;
                            onError(th);
                            dispose(s);
                            return;
                        }
                    }
                }
            }
        }

        private void dispose(S s) {
            try {
                this.disposeState.accept(s);
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                RxJavaPlugins.m9212a(th);
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                if (BackpressureHelper.m9449a(this, 1L) == 0) {
                    S s = this.state;
                    this.state = null;
                    dispose(s);
                }
            }
        }

        @Override // p110z1.Emitter
        public void onNext(T t) {
            if (this.terminate) {
                return;
            }
            if (this.hasNext) {
                onError(new IllegalStateException("onNext already called in this generate turn"));
            } else if (t == null) {
                onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
            } else {
                this.hasNext = true;
                this.downstream.onNext(t);
            }
        }

        @Override // p110z1.Emitter
        public void onError(Throwable th) {
            if (this.terminate) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            if (th == null) {
                th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            this.terminate = true;
            this.downstream.onError(th);
        }

        @Override // p110z1.Emitter
        public void onComplete() {
            if (!this.terminate) {
                this.terminate = true;
                this.downstream.onComplete();
            }
        }
    }
}
