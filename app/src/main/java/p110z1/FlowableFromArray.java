package p110z1;

/* renamed from: z1.baw */
/* loaded from: classes3.dex */
public final class FlowableFromArray<T> extends Flowable<T> {

    /* renamed from: b */
    final T[] f18106b;

    public FlowableFromArray(T[] tArr) {
        this.f18106b = tArr;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super T> dbxVar) {
        if (dbxVar instanceof ConditionalSubscriber) {
            dbxVar.onSubscribe(new C4075a((ConditionalSubscriber) dbxVar, this.f18106b));
        } else {
            dbxVar.onSubscribe(new C4076b(dbxVar, this.f18106b));
        }
    }

    /* compiled from: FlowableFromArray.java */
    /* renamed from: z1.baw$c */
    /* loaded from: classes3.dex */
    static abstract class AbstractC4077c<T> extends BasicQueueSubscription<T> {
        private static final long serialVersionUID = -2252972430506210021L;
        final T[] array;
        volatile boolean cancelled;
        int index;

        abstract void fastPath();

        @Override // p110z1.QueueFuseable
        public final int requestFusion(int i) {
            return i & 1;
        }

        abstract void slowPath(long j);

        AbstractC4077c(T[] tArr) {
            this.array = tArr;
        }

        @Override // p110z1.SimpleQueue
        @atn
        public final T poll() {
            int i = this.index;
            T[] tArr = this.array;
            if (i == tArr.length) {
                return null;
            }
            this.index = i + 1;
            return (T) ObjectHelper.m9873a((Object) tArr[i], "array element is null");
        }

        @Override // p110z1.SimpleQueue
        public final boolean isEmpty() {
            return this.index == this.array.length;
        }

        @Override // p110z1.SimpleQueue
        public final void clear() {
            this.index = this.array.length;
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

    /* compiled from: FlowableFromArray.java */
    /* renamed from: z1.baw$b */
    /* loaded from: classes3.dex */
    static final class C4076b<T> extends AbstractC4077c<T> {
        private static final long serialVersionUID = 2587302975077663557L;
        final Subscriber<? super T> downstream;

        C4076b(Subscriber<? super T> dbxVar, T[] tArr) {
            super(tArr);
            this.downstream = dbxVar;
        }

        @Override // p110z1.FlowableFromArray.AbstractC4077c
        void fastPath() {
            T[] tArr = this.array;
            int length = tArr.length;
            Subscriber<? super T> dbxVar = this.downstream;
            for (int i = this.index; i != length; i++) {
                if (!this.cancelled) {
                    T t = tArr[i];
                    if (t == null) {
                        dbxVar.onError(new NullPointerException("The element at index " + i + " is null"));
                        return;
                    }
                    dbxVar.onNext(t);
                } else {
                    return;
                }
            }
            if (!this.cancelled) {
                dbxVar.onComplete();
            }
        }

        @Override // p110z1.FlowableFromArray.AbstractC4077c
        void slowPath(long j) {
            T[] tArr = this.array;
            int length = tArr.length;
            int i = this.index;
            Subscriber<? super T> dbxVar = this.downstream;
            long j2 = j;
            long j3 = 0;
            while (true) {
                if (j3 == j2 || i == length) {
                    if (i != length) {
                        j2 = get();
                        if (j3 == j2) {
                            this.index = i;
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
                        dbxVar.onComplete();
                        return;
                    } else {
                        return;
                    }
                } else if (!this.cancelled) {
                    T t = tArr[i];
                    if (t == null) {
                        dbxVar.onError(new NullPointerException("The element at index " + i + " is null"));
                        return;
                    }
                    dbxVar.onNext(t);
                    j3++;
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    /* compiled from: FlowableFromArray.java */
    /* renamed from: z1.baw$a */
    /* loaded from: classes3.dex */
    static final class C4075a<T> extends AbstractC4077c<T> {
        private static final long serialVersionUID = 2587302975077663557L;
        final ConditionalSubscriber<? super T> downstream;

        C4075a(ConditionalSubscriber<? super T> aviVar, T[] tArr) {
            super(tArr);
            this.downstream = aviVar;
        }

        @Override // p110z1.FlowableFromArray.AbstractC4077c
        void fastPath() {
            T[] tArr = this.array;
            int length = tArr.length;
            ConditionalSubscriber<? super T> aviVar = this.downstream;
            for (int i = this.index; i != length; i++) {
                if (!this.cancelled) {
                    T t = tArr[i];
                    if (t == null) {
                        aviVar.onError(new NullPointerException("The element at index " + i + " is null"));
                        return;
                    }
                    aviVar.tryOnNext(t);
                } else {
                    return;
                }
            }
            if (!this.cancelled) {
                aviVar.onComplete();
            }
        }

        @Override // p110z1.FlowableFromArray.AbstractC4077c
        void slowPath(long j) {
            T[] tArr = this.array;
            int length = tArr.length;
            int i = this.index;
            ConditionalSubscriber<? super T> aviVar = this.downstream;
            long j2 = j;
            long j3 = 0;
            while (true) {
                if (j3 == j2 || i == length) {
                    if (i != length) {
                        j2 = get();
                        if (j3 == j2) {
                            this.index = i;
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
                        aviVar.onComplete();
                        return;
                    } else {
                        return;
                    }
                } else if (!this.cancelled) {
                    T t = tArr[i];
                    if (t == null) {
                        aviVar.onError(new NullPointerException("The element at index " + i + " is null"));
                        return;
                    }
                    if (aviVar.tryOnNext(t)) {
                        j3++;
                    }
                    i++;
                } else {
                    return;
                }
            }
        }
    }
}
