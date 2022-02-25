package p110z1;

/* renamed from: z1.bcj */
/* loaded from: classes3.dex */
public final class FlowableRange extends Flowable<Integer> {

    /* renamed from: b */
    final int f18245b;

    /* renamed from: c */
    final int f18246c;

    public FlowableRange(int i, int i2) {
        this.f18245b = i;
        this.f18246c = i + i2;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super Integer> dbxVar) {
        if (dbxVar instanceof ConditionalSubscriber) {
            dbxVar.onSubscribe(new C4146b((ConditionalSubscriber) dbxVar, this.f18245b, this.f18246c));
        } else {
            dbxVar.onSubscribe(new C4147c(dbxVar, this.f18245b, this.f18246c));
        }
    }

    /* compiled from: FlowableRange.java */
    /* renamed from: z1.bcj$a */
    /* loaded from: classes3.dex */
    static abstract class AbstractC4145a extends BasicQueueSubscription<Integer> {
        private static final long serialVersionUID = -2252972430506210021L;
        volatile boolean cancelled;
        final int end;
        int index;

        abstract void fastPath();

        @Override // p110z1.QueueFuseable
        public final int requestFusion(int i) {
            return i & 1;
        }

        abstract void slowPath(long j);

        AbstractC4145a(int i, int i2) {
            this.index = i;
            this.end = i2;
        }

        @Override // p110z1.SimpleQueue
        @atn
        public final Integer poll() {
            int i = this.index;
            if (i == this.end) {
                return null;
            }
            this.index = i + 1;
            return Integer.valueOf(i);
        }

        @Override // p110z1.SimpleQueue
        public final boolean isEmpty() {
            return this.index == this.end;
        }

        @Override // p110z1.SimpleQueue
        public final void clear() {
            this.index = this.end;
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

    /* compiled from: FlowableRange.java */
    /* renamed from: z1.bcj$c */
    /* loaded from: classes3.dex */
    static final class C4147c extends AbstractC4145a {
        private static final long serialVersionUID = 2587302975077663557L;
        final Subscriber<? super Integer> downstream;

        C4147c(Subscriber<? super Integer> dbxVar, int i, int i2) {
            super(i, i2);
            this.downstream = dbxVar;
        }

        @Override // p110z1.FlowableRange.AbstractC4145a
        void fastPath() {
            int i = this.end;
            Subscriber<? super Integer> dbxVar = this.downstream;
            for (int i2 = this.index; i2 != i; i2++) {
                if (!this.cancelled) {
                    dbxVar.onNext(Integer.valueOf(i2));
                } else {
                    return;
                }
            }
            if (!this.cancelled) {
                dbxVar.onComplete();
            }
        }

        @Override // p110z1.FlowableRange.AbstractC4145a
        void slowPath(long j) {
            int i = this.end;
            int i2 = this.index;
            Subscriber<? super Integer> dbxVar = this.downstream;
            long j2 = j;
            long j3 = 0;
            while (true) {
                if (j3 == j2 || i2 == i) {
                    if (i2 != i) {
                        j2 = get();
                        if (j3 == j2) {
                            this.index = i2;
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
                    dbxVar.onNext(Integer.valueOf(i2));
                    j3++;
                    i2++;
                } else {
                    return;
                }
            }
        }
    }

    /* compiled from: FlowableRange.java */
    /* renamed from: z1.bcj$b */
    /* loaded from: classes3.dex */
    static final class C4146b extends AbstractC4145a {
        private static final long serialVersionUID = 2587302975077663557L;
        final ConditionalSubscriber<? super Integer> downstream;

        C4146b(ConditionalSubscriber<? super Integer> aviVar, int i, int i2) {
            super(i, i2);
            this.downstream = aviVar;
        }

        @Override // p110z1.FlowableRange.AbstractC4145a
        void fastPath() {
            int i = this.end;
            ConditionalSubscriber<? super Integer> aviVar = this.downstream;
            for (int i2 = this.index; i2 != i; i2++) {
                if (!this.cancelled) {
                    aviVar.tryOnNext(Integer.valueOf(i2));
                } else {
                    return;
                }
            }
            if (!this.cancelled) {
                aviVar.onComplete();
            }
        }

        @Override // p110z1.FlowableRange.AbstractC4145a
        void slowPath(long j) {
            int i = this.end;
            int i2 = this.index;
            ConditionalSubscriber<? super Integer> aviVar = this.downstream;
            long j2 = j;
            long j3 = 0;
            while (true) {
                if (j3 == j2 || i2 == i) {
                    if (i2 != i) {
                        j2 = get();
                        if (j3 == j2) {
                            this.index = i2;
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
                    if (aviVar.tryOnNext(Integer.valueOf(i2))) {
                        j3++;
                    }
                    i2++;
                } else {
                    return;
                }
            }
        }
    }
}
