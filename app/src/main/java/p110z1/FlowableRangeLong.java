package p110z1;

/* renamed from: z1.bck */
/* loaded from: classes3.dex */
public final class FlowableRangeLong extends Flowable<Long> {

    /* renamed from: b */
    final long f18247b;

    /* renamed from: c */
    final long f18248c;

    public FlowableRangeLong(long j, long j2) {
        this.f18247b = j;
        this.f18248c = j + j2;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super Long> dbxVar) {
        if (dbxVar instanceof ConditionalSubscriber) {
            dbxVar.onSubscribe(new C4149b((ConditionalSubscriber) dbxVar, this.f18247b, this.f18248c));
        } else {
            dbxVar.onSubscribe(new C4150c(dbxVar, this.f18247b, this.f18248c));
        }
    }

    /* compiled from: FlowableRangeLong.java */
    /* renamed from: z1.bck$a */
    /* loaded from: classes3.dex */
    static abstract class AbstractC4148a extends BasicQueueSubscription<Long> {
        private static final long serialVersionUID = -2252972430506210021L;
        volatile boolean cancelled;
        final long end;
        long index;

        abstract void fastPath();

        @Override // p110z1.QueueFuseable
        public final int requestFusion(int i) {
            return i & 1;
        }

        abstract void slowPath(long j);

        AbstractC4148a(long j, long j2) {
            this.index = j;
            this.end = j2;
        }

        @Override // p110z1.SimpleQueue
        @atn
        public final Long poll() {
            long j = this.index;
            if (j == this.end) {
                return null;
            }
            this.index = 1 + j;
            return Long.valueOf(j);
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

    /* compiled from: FlowableRangeLong.java */
    /* renamed from: z1.bck$c */
    /* loaded from: classes3.dex */
    static final class C4150c extends AbstractC4148a {
        private static final long serialVersionUID = 2587302975077663557L;
        final Subscriber<? super Long> downstream;

        C4150c(Subscriber<? super Long> dbxVar, long j, long j2) {
            super(j, j2);
            this.downstream = dbxVar;
        }

        @Override // p110z1.FlowableRangeLong.AbstractC4148a
        void fastPath() {
            long j = this.end;
            Subscriber<? super Long> dbxVar = this.downstream;
            for (long j2 = this.index; j2 != j; j2++) {
                if (!this.cancelled) {
                    dbxVar.onNext(Long.valueOf(j2));
                } else {
                    return;
                }
            }
            if (!this.cancelled) {
                dbxVar.onComplete();
            }
        }

        @Override // p110z1.FlowableRangeLong.AbstractC4148a
        void slowPath(long j) {
            long j2 = this.end;
            long j3 = this.index;
            Subscriber<? super Long> dbxVar = this.downstream;
            long j4 = j3;
            long j5 = j;
            long j6 = 0;
            while (true) {
                if (j6 == j5 || j4 == j2) {
                    if (j4 != j2) {
                        j5 = get();
                        if (j6 == j5) {
                            this.index = j4;
                            j5 = addAndGet(-j6);
                            if (j5 != 0) {
                                j6 = 0;
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
                    dbxVar.onNext(Long.valueOf(j4));
                    j6++;
                    j4++;
                } else {
                    return;
                }
            }
        }
    }

    /* compiled from: FlowableRangeLong.java */
    /* renamed from: z1.bck$b */
    /* loaded from: classes3.dex */
    static final class C4149b extends AbstractC4148a {
        private static final long serialVersionUID = 2587302975077663557L;
        final ConditionalSubscriber<? super Long> downstream;

        C4149b(ConditionalSubscriber<? super Long> aviVar, long j, long j2) {
            super(j, j2);
            this.downstream = aviVar;
        }

        @Override // p110z1.FlowableRangeLong.AbstractC4148a
        void fastPath() {
            long j = this.end;
            ConditionalSubscriber<? super Long> aviVar = this.downstream;
            for (long j2 = this.index; j2 != j; j2++) {
                if (!this.cancelled) {
                    aviVar.tryOnNext(Long.valueOf(j2));
                } else {
                    return;
                }
            }
            if (!this.cancelled) {
                aviVar.onComplete();
            }
        }

        @Override // p110z1.FlowableRangeLong.AbstractC4148a
        void slowPath(long j) {
            long j2 = this.end;
            long j3 = this.index;
            ConditionalSubscriber<? super Long> aviVar = this.downstream;
            long j4 = j3;
            long j5 = j;
            long j6 = 0;
            while (true) {
                if (j6 == j5 || j4 == j2) {
                    if (j4 != j2) {
                        j5 = get();
                        if (j6 == j5) {
                            this.index = j4;
                            j5 = addAndGet(-j6);
                            if (j5 != 0) {
                                j6 = 0;
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
                    if (aviVar.tryOnNext(Long.valueOf(j4))) {
                        j6++;
                    }
                    j4++;
                } else {
                    return;
                }
            }
        }
    }
}
