package p110z1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.boi */
/* loaded from: classes3.dex */
public final class ParallelSortedJoin<T> extends Flowable<T> {

    /* renamed from: b */
    final ParallelFlowable<List<T>> f19682b;

    /* renamed from: c */
    final Comparator<? super T> f19683c;

    public ParallelSortedJoin(ParallelFlowable<List<T>> bubVar, Comparator<? super T> comparator) {
        this.f19682b = bubVar;
        this.f19683c = comparator;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        C4651b bVar = new C4651b(dbxVar, this.f19682b.mo9267a(), this.f19683c);
        dbxVar.onSubscribe(bVar);
        this.f19682b.mo9236a(bVar.subscribers);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ParallelSortedJoin.java */
    /* renamed from: z1.boi$b */
    /* loaded from: classes3.dex */
    public static final class C4651b<T> extends AtomicInteger implements dby {
        private static final long serialVersionUID = 3481980673745556697L;
        volatile boolean cancelled;
        final Comparator<? super T> comparator;
        final Subscriber<? super T> downstream;
        final int[] indexes;
        final List<T>[] lists;
        final C4650a<T>[] subscribers;
        final AtomicLong requested = new AtomicLong();
        final AtomicInteger remaining = new AtomicInteger();
        final AtomicReference<Throwable> error = new AtomicReference<>();

        C4651b(Subscriber<? super T> dbxVar, int i, Comparator<? super T> comparator) {
            this.downstream = dbxVar;
            this.comparator = comparator;
            C4650a<T>[] aVarArr = new C4650a[i];
            for (int i2 = 0; i2 < i; i2++) {
                aVarArr[i2] = new C4650a<>(this, i2);
            }
            this.subscribers = aVarArr;
            this.lists = new List[i];
            this.indexes = new int[i];
            this.remaining.lazySet(i);
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.m9449a(this.requested, j);
                if (this.remaining.get() == 0) {
                    drain();
                }
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelAll();
                if (getAndIncrement() == 0) {
                    Arrays.fill(this.lists, (Object) null);
                }
            }
        }

        void cancelAll() {
            for (C4650a<T> aVar : this.subscribers) {
                aVar.cancel();
            }
        }

        void innerNext(List<T> list, int i) {
            this.lists[i] = list;
            if (this.remaining.decrementAndGet() == 0) {
                drain();
            }
        }

        void innerError(Throwable th) {
            if (this.error.compareAndSet(null, th)) {
                drain();
            } else if (th != this.error.get()) {
                RxJavaPlugins.m9212a(th);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:42:0x00a6, code lost:
            if (r15 != 0) goto L_0x00e5;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x00aa, code lost:
            if (r18.cancelled == false) goto L_0x00b0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x00ac, code lost:
            java.util.Arrays.fill(r3, (java.lang.Object) null);
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x00af, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x00b0, code lost:
            r5 = r18.error.get();
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x00b8, code lost:
            if (r5 == null) goto L_0x00c4;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x00ba, code lost:
            cancelAll();
            java.util.Arrays.fill(r3, (java.lang.Object) null);
            r2.onError(r5);
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x00c3, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:0x00c4, code lost:
            r5 = 0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x00c5, code lost:
            if (r5 >= r4) goto L_0x00d7;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x00cf, code lost:
            if (r0[r5] == r3[r5].size()) goto L_0x00d4;
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x00d1, code lost:
            r16 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x00d4, code lost:
            r5 = r5 + 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:57:0x00d7, code lost:
            r16 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x00d9, code lost:
            if (r16 == false) goto L_0x00e2;
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x00db, code lost:
            java.util.Arrays.fill(r3, (java.lang.Object) null);
            r2.onComplete();
         */
        /* JADX WARN: Code restructure failed: missing block: B:60:0x00e1, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:61:0x00e2, code lost:
            r13 = 0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x00e5, code lost:
            r13 = 0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:64:0x00e9, code lost:
            if (r11 == r13) goto L_0x00fa;
         */
        /* JADX WARN: Code restructure failed: missing block: B:66:0x00f2, code lost:
            if (r7 == p110z1.cjm.f20759b) goto L_0x00fa;
         */
        /* JADX WARN: Code restructure failed: missing block: B:67:0x00f4, code lost:
            r18.requested.addAndGet(-r11);
         */
        /* JADX WARN: Code restructure failed: missing block: B:68:0x00fa, code lost:
            r5 = get();
         */
        /* JADX WARN: Code restructure failed: missing block: B:69:0x00fe, code lost:
            if (r5 != r6) goto L_0x010b;
         */
        /* JADX WARN: Code restructure failed: missing block: B:70:0x0100, code lost:
            r5 = addAndGet(-r6);
         */
        /* JADX WARN: Code restructure failed: missing block: B:71:0x0105, code lost:
            if (r5 != 0) goto L_0x0108;
         */
        /* JADX WARN: Code restructure failed: missing block: B:72:0x0107, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:73:0x0108, code lost:
            r6 = r5;
         */
        /* JADX WARN: Code restructure failed: missing block: B:74:0x010b, code lost:
            r6 = r5;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void drain() {
            /*
                Method dump skipped, instructions count: 270
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: p110z1.ParallelSortedJoin.C4651b.drain():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ParallelSortedJoin.java */
    /* renamed from: z1.boi$a */
    /* loaded from: classes3.dex */
    public static final class C4650a<T> extends AtomicReference<dby> implements FlowableSubscriber<List<T>> {
        private static final long serialVersionUID = 6751017204873808094L;
        final int index;
        final C4651b<T> parent;

        @Override // p110z1.Subscriber
        public void onComplete() {
        }

        @Override // p110z1.Subscriber
        public /* bridge */ /* synthetic */ void onNext(Object obj) {
            onNext((List) ((List) obj));
        }

        C4650a(C4651b<T> bVar, int i) {
            this.parent = bVar;
            this.index = i;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            SubscriptionHelper.setOnce(this, dbyVar, cjm.f20759b);
        }

        public void onNext(List<T> list) {
            this.parent.innerNext(list, this.index);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.parent.innerError(th);
        }

        void cancel() {
            SubscriptionHelper.cancel(this);
        }
    }
}
