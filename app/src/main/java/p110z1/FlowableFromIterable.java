package p110z1;

import java.util.Iterator;

/* renamed from: z1.baz */
/* loaded from: classes3.dex */
public final class FlowableFromIterable<T> extends Flowable<T> {

    /* renamed from: b */
    final Iterable<? extends T> f18111b;

    public FlowableFromIterable(Iterable<? extends T> iterable) {
        this.f18111b = iterable;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super T> dbxVar) {
        try {
            m9792a((Subscriber) dbxVar, (Iterator) this.f18111b.iterator());
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            EmptySubscription.error(th, dbxVar);
        }
    }

    /* renamed from: a */
    public static <T> void m9792a(Subscriber<? super T> dbxVar, Iterator<? extends T> it) {
        try {
            if (!it.hasNext()) {
                EmptySubscription.complete(dbxVar);
            } else if (dbxVar instanceof ConditionalSubscriber) {
                dbxVar.onSubscribe(new C4079b((ConditionalSubscriber) dbxVar, it));
            } else {
                dbxVar.onSubscribe(new C4080c(dbxVar, it));
            }
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            EmptySubscription.error(th, dbxVar);
        }
    }

    /* compiled from: FlowableFromIterable.java */
    /* renamed from: z1.baz$a */
    /* loaded from: classes3.dex */
    static abstract class AbstractC4078a<T> extends BasicQueueSubscription<T> {
        private static final long serialVersionUID = -2252972430506210021L;
        volatile boolean cancelled;

        /* renamed from: it */
        Iterator<? extends T> f18112it;
        boolean once;

        abstract void fastPath();

        @Override // p110z1.QueueFuseable
        public final int requestFusion(int i) {
            return i & 1;
        }

        abstract void slowPath(long j);

        AbstractC4078a(Iterator<? extends T> it) {
            this.f18112it = it;
        }

        @Override // p110z1.SimpleQueue
        @atn
        public final T poll() {
            Iterator<? extends T> it = this.f18112it;
            if (it == null) {
                return null;
            }
            if (!this.once) {
                this.once = true;
            } else if (!it.hasNext()) {
                return null;
            }
            return (T) ObjectHelper.m9873a(this.f18112it.next(), "Iterator.next() returned a null value");
        }

        @Override // p110z1.SimpleQueue
        public final boolean isEmpty() {
            Iterator<? extends T> it = this.f18112it;
            return it == null || !it.hasNext();
        }

        @Override // p110z1.SimpleQueue
        public final void clear() {
            this.f18112it = null;
        }

        @Override // p110z1.dby
        public final void request(long j) {
            if (SubscriptionHelper.validate(j) && BackpressureHelper.m9449a(this, j) == 0) {
                if (j == cjm.f20759b) {
                    fastPath();
                } else {
                    slowPath(j);
                }
            }
        }

        @Override // p110z1.dby
        public final void cancel() {
            this.cancelled = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableFromIterable.java */
    /* renamed from: z1.baz$c */
    /* loaded from: classes3.dex */
    public static final class C4080c<T> extends AbstractC4078a<T> {
        private static final long serialVersionUID = -6022804456014692607L;
        final Subscriber<? super T> downstream;

        C4080c(Subscriber<? super T> dbxVar, Iterator<? extends T> it) {
            super(it);
            this.downstream = dbxVar;
        }

        @Override // p110z1.FlowableFromIterable.AbstractC4078a
        void fastPath() {
            Iterator<? extends T> it = this.f18112it;
            Subscriber<? super T> dbxVar = this.downstream;
            while (!this.cancelled) {
                try {
                    Object obj = (Object) it.next();
                    if (!this.cancelled) {
                        if (obj == 0) {
                            dbxVar.onError(new NullPointerException("Iterator.next() returned a null value"));
                            return;
                        }
                        dbxVar.onNext(obj);
                        if (!this.cancelled) {
                            try {
                                if (!it.hasNext()) {
                                    if (!this.cancelled) {
                                        dbxVar.onComplete();
                                        return;
                                    }
                                    return;
                                }
                            } catch (Throwable th) {
                                Exceptions.m9983b(th);
                                dbxVar.onError(th);
                                return;
                            }
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                } catch (Throwable th2) {
                    Exceptions.m9983b(th2);
                    dbxVar.onError(th2);
                    return;
                }
            }
        }

        @Override // p110z1.FlowableFromIterable.AbstractC4078a
        void slowPath(long j) {
            Iterator<? extends T> it = this.f18112it;
            Subscriber<? super T> dbxVar = this.downstream;
            long j2 = j;
            long j3 = 0;
            while (true) {
                if (j3 == j2) {
                    j2 = get();
                    if (j3 == j2) {
                        j2 = addAndGet(-j3);
                        if (j2 != 0) {
                            j3 = 0;
                        } else {
                            return;
                        }
                    } else {
                        continue;
                    }
                } else if (!this.cancelled) {
                    try {
                        Object obj = (Object) it.next();
                        if (!this.cancelled) {
                            if (obj == 0) {
                                dbxVar.onError(new NullPointerException("Iterator.next() returned a null value"));
                                return;
                            }
                            dbxVar.onNext(obj);
                            if (!this.cancelled) {
                                try {
                                    if (it.hasNext()) {
                                        j3++;
                                    } else if (!this.cancelled) {
                                        dbxVar.onComplete();
                                        return;
                                    } else {
                                        return;
                                    }
                                } catch (Throwable th) {
                                    Exceptions.m9983b(th);
                                    dbxVar.onError(th);
                                    return;
                                }
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    } catch (Throwable th2) {
                        Exceptions.m9983b(th2);
                        dbxVar.onError(th2);
                        return;
                    }
                } else {
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableFromIterable.java */
    /* renamed from: z1.baz$b */
    /* loaded from: classes3.dex */
    public static final class C4079b<T> extends AbstractC4078a<T> {
        private static final long serialVersionUID = -6022804456014692607L;
        final ConditionalSubscriber<? super T> downstream;

        C4079b(ConditionalSubscriber<? super T> aviVar, Iterator<? extends T> it) {
            super(it);
            this.downstream = aviVar;
        }

        @Override // p110z1.FlowableFromIterable.AbstractC4078a
        void fastPath() {
            Iterator<? extends T> it = this.f18112it;
            ConditionalSubscriber<? super T> aviVar = this.downstream;
            while (!this.cancelled) {
                try {
                    Object obj = (Object) it.next();
                    if (!this.cancelled) {
                        if (obj == 0) {
                            aviVar.onError(new NullPointerException("Iterator.next() returned a null value"));
                            return;
                        }
                        aviVar.tryOnNext(obj);
                        if (!this.cancelled) {
                            try {
                                if (!it.hasNext()) {
                                    if (!this.cancelled) {
                                        aviVar.onComplete();
                                        return;
                                    }
                                    return;
                                }
                            } catch (Throwable th) {
                                Exceptions.m9983b(th);
                                aviVar.onError(th);
                                return;
                            }
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                } catch (Throwable th2) {
                    Exceptions.m9983b(th2);
                    aviVar.onError(th2);
                    return;
                }
            }
        }

        @Override // p110z1.FlowableFromIterable.AbstractC4078a
        void slowPath(long j) {
            Iterator<? extends T> it = this.f18112it;
            ConditionalSubscriber<? super T> aviVar = this.downstream;
            long j2 = j;
            long j3 = 0;
            while (true) {
                if (j3 == j2) {
                    j2 = get();
                    if (j3 == j2) {
                        j2 = addAndGet(-j3);
                        if (j2 != 0) {
                            j3 = 0;
                        } else {
                            return;
                        }
                    } else {
                        continue;
                    }
                } else if (!this.cancelled) {
                    try {
                        Object obj = (Object) it.next();
                        if (!this.cancelled) {
                            if (obj == 0) {
                                aviVar.onError(new NullPointerException("Iterator.next() returned a null value"));
                                return;
                            }
                            boolean tryOnNext = aviVar.tryOnNext(obj);
                            if (!this.cancelled) {
                                try {
                                    if (!it.hasNext()) {
                                        if (!this.cancelled) {
                                            aviVar.onComplete();
                                            return;
                                        }
                                        return;
                                    } else if (tryOnNext) {
                                        j3++;
                                    }
                                } catch (Throwable th) {
                                    Exceptions.m9983b(th);
                                    aviVar.onError(th);
                                    return;
                                }
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    } catch (Throwable th2) {
                        Exceptions.m9983b(th2);
                        aviVar.onError(th2);
                        return;
                    }
                } else {
                    return;
                }
            }
        }
    }
}
