package p110z1;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bbe */
/* loaded from: classes3.dex */
public final class FlowableGroupJoin<TLeft, TRight, TLeftEnd, TRightEnd, R> extends AbstractFlowableWithUpstream<TLeft, R> {

    /* renamed from: c */
    final Publisher<? extends TRight> f18127c;

    /* renamed from: d */
    final Function<? super TLeft, ? extends Publisher<TLeftEnd>> f18128d;

    /* renamed from: e */
    final Function<? super TRight, ? extends Publisher<TRightEnd>> f18129e;

    /* renamed from: f */
    final BiFunction<? super TLeft, ? super Flowable<TRight>, ? extends R> f18130f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableGroupJoin.java */
    /* renamed from: z1.bbe$b */
    /* loaded from: classes3.dex */
    public interface AbstractC4089b {
        void innerClose(boolean z, C4090c cVar);

        void innerCloseError(Throwable th);

        void innerComplete(C4091d dVar);

        void innerError(Throwable th);

        void innerValue(boolean z, Object obj);
    }

    public FlowableGroupJoin(Flowable<TLeft> arvVar, Publisher<? extends TRight> dbwVar, Function<? super TLeft, ? extends Publisher<TLeftEnd>> aunVar, Function<? super TRight, ? extends Publisher<TRightEnd>> aunVar2, BiFunction<? super TLeft, ? super Flowable<TRight>, ? extends R> auiVar) {
        super(arvVar);
        this.f18127c = dbwVar;
        this.f18128d = aunVar;
        this.f18129e = aunVar2;
        this.f18130f = auiVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super R> dbxVar) {
        C4088a aVar = new C4088a(dbxVar, this.f18128d, this.f18129e, this.f18130f);
        dbxVar.onSubscribe(aVar);
        C4091d dVar = new C4091d(aVar, true);
        aVar.disposables.mo9939a(dVar);
        C4091d dVar2 = new C4091d(aVar, false);
        aVar.disposables.mo9939a(dVar2);
        this.f17812b.m11187a((FlowableSubscriber) dVar);
        this.f18127c.subscribe(dVar2);
    }

    /* compiled from: FlowableGroupJoin.java */
    /* renamed from: z1.bbe$a */
    /* loaded from: classes3.dex */
    static final class C4088a<TLeft, TRight, TLeftEnd, TRightEnd, R> extends AtomicInteger implements AbstractC4089b, dby {
        private static final long serialVersionUID = -6071216598687999801L;
        volatile boolean cancelled;
        final Subscriber<? super R> downstream;
        final Function<? super TLeft, ? extends Publisher<TLeftEnd>> leftEnd;
        int leftIndex;
        final BiFunction<? super TLeft, ? super Flowable<TRight>, ? extends R> resultSelector;
        final Function<? super TRight, ? extends Publisher<TRightEnd>> rightEnd;
        int rightIndex;
        static final Integer LEFT_VALUE = 1;
        static final Integer RIGHT_VALUE = 2;
        static final Integer LEFT_CLOSE = 3;
        static final Integer RIGHT_CLOSE = 4;
        final AtomicLong requested = new AtomicLong();
        final CompositeDisposable disposables = new CompositeDisposable();
        final SpscLinkedArrayQueue<Object> queue = new SpscLinkedArrayQueue<>(Flowable.m11274a());
        final Map<Integer, UnicastProcessor<TRight>> lefts = new LinkedHashMap();
        final Map<Integer, TRight> rights = new LinkedHashMap();
        final AtomicReference<Throwable> error = new AtomicReference<>();
        final AtomicInteger active = new AtomicInteger(2);

        C4088a(Subscriber<? super R> dbxVar, Function<? super TLeft, ? extends Publisher<TLeftEnd>> aunVar, Function<? super TRight, ? extends Publisher<TRightEnd>> aunVar2, BiFunction<? super TLeft, ? super Flowable<TRight>, ? extends R> auiVar) {
            this.downstream = dbxVar;
            this.leftEnd = aunVar;
            this.rightEnd = aunVar2;
            this.resultSelector = auiVar;
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.m9449a(this.requested, j);
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelAll();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        void cancelAll() {
            this.disposables.dispose();
        }

        void errorAll(Subscriber<?> dbxVar) {
            Throwable a = ExceptionHelper.m9431a(this.error);
            for (UnicastProcessor<TRight> bumVar : this.lefts.values()) {
                bumVar.onError(a);
            }
            this.lefts.clear();
            this.rights.clear();
            dbxVar.onError(a);
        }

        void fail(Throwable th, Subscriber<?> dbxVar, SimpleQueue<?> avwVar) {
            Exceptions.m9983b(th);
            ExceptionHelper.m9430a(this.error, th);
            avwVar.clear();
            cancelAll();
            errorAll(dbxVar);
        }

        /* JADX WARN: Multi-variable type inference failed */
        void drain() {
            if (getAndIncrement() == 0) {
                SpscLinkedArrayQueue<Object> bqlVar = this.queue;
                Subscriber<? super R> dbxVar = this.downstream;
                int i = 1;
                while (!this.cancelled) {
                    if (this.error.get() != null) {
                        bqlVar.clear();
                        cancelAll();
                        errorAll(dbxVar);
                        return;
                    }
                    boolean z = this.active.get() == 0;
                    Integer num = (Integer) bqlVar.poll();
                    boolean z2 = num == null;
                    if (z && z2) {
                        for (UnicastProcessor<TRight> bumVar : this.lefts.values()) {
                            bumVar.onComplete();
                        }
                        this.lefts.clear();
                        this.rights.clear();
                        this.disposables.dispose();
                        dbxVar.onComplete();
                        return;
                    } else if (z2) {
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        Object poll = bqlVar.poll();
                        if (num == LEFT_VALUE) {
                            UnicastProcessor<TRight> T = UnicastProcessor.m9065T();
                            int i2 = this.leftIndex;
                            this.leftIndex = i2 + 1;
                            this.lefts.put(Integer.valueOf(i2), T);
                            try {
                                Publisher dbwVar = (Publisher) ObjectHelper.m9873a(this.leftEnd.apply(poll), "The leftEnd returned a null Publisher");
                                C4090c cVar = new C4090c(this, true, i2);
                                this.disposables.mo9939a(cVar);
                                dbwVar.subscribe(cVar);
                                if (this.error.get() != null) {
                                    bqlVar.clear();
                                    cancelAll();
                                    errorAll(dbxVar);
                                    return;
                                }
                                try {
                                    Object obj = (Object) ObjectHelper.m9873a(this.resultSelector.apply(poll, T), "The resultSelector returned a null value");
                                    if (this.requested.get() != 0) {
                                        dbxVar.onNext(obj);
                                        BackpressureHelper.m9446c(this.requested, 1L);
                                        for (TRight tright : this.rights.values()) {
                                            T.onNext(tright);
                                        }
                                    } else {
                                        fail(new MissingBackpressureException("Could not emit value due to lack of requests"), dbxVar, bqlVar);
                                        return;
                                    }
                                } catch (Throwable th) {
                                    fail(th, dbxVar, bqlVar);
                                    return;
                                }
                            } catch (Throwable th2) {
                                fail(th2, dbxVar, bqlVar);
                                return;
                            }
                        } else if (num == RIGHT_VALUE) {
                            int i3 = this.rightIndex;
                            this.rightIndex = i3 + 1;
                            this.rights.put(Integer.valueOf(i3), poll);
                            try {
                                Publisher dbwVar2 = (Publisher) ObjectHelper.m9873a(this.rightEnd.apply(poll), "The rightEnd returned a null Publisher");
                                C4090c cVar2 = new C4090c(this, false, i3);
                                this.disposables.mo9939a(cVar2);
                                dbwVar2.subscribe(cVar2);
                                if (this.error.get() != null) {
                                    bqlVar.clear();
                                    cancelAll();
                                    errorAll(dbxVar);
                                    return;
                                }
                                for (UnicastProcessor<TRight> bumVar2 : this.lefts.values()) {
                                    bumVar2.onNext(poll);
                                }
                            } catch (Throwable th3) {
                                fail(th3, dbxVar, bqlVar);
                                return;
                            }
                        } else if (num == LEFT_CLOSE) {
                            C4090c cVar3 = (C4090c) poll;
                            UnicastProcessor<TRight> remove = this.lefts.remove(Integer.valueOf(cVar3.index));
                            this.disposables.mo9937b(cVar3);
                            if (remove != null) {
                                remove.onComplete();
                            }
                        } else if (num == RIGHT_CLOSE) {
                            C4090c cVar4 = (C4090c) poll;
                            this.rights.remove(Integer.valueOf(cVar4.index));
                            this.disposables.mo9937b(cVar4);
                        }
                    }
                }
                bqlVar.clear();
            }
        }

        @Override // p110z1.FlowableGroupJoin.AbstractC4089b
        public void innerError(Throwable th) {
            if (ExceptionHelper.m9430a(this.error, th)) {
                this.active.decrementAndGet();
                drain();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.FlowableGroupJoin.AbstractC4089b
        public void innerComplete(C4091d dVar) {
            this.disposables.mo9936c(dVar);
            this.active.decrementAndGet();
            drain();
        }

        @Override // p110z1.FlowableGroupJoin.AbstractC4089b
        public void innerValue(boolean z, Object obj) {
            synchronized (this) {
                this.queue.offer(z ? LEFT_VALUE : RIGHT_VALUE, obj);
            }
            drain();
        }

        @Override // p110z1.FlowableGroupJoin.AbstractC4089b
        public void innerClose(boolean z, C4090c cVar) {
            synchronized (this) {
                this.queue.offer(z ? LEFT_CLOSE : RIGHT_CLOSE, cVar);
            }
            drain();
        }

        @Override // p110z1.FlowableGroupJoin.AbstractC4089b
        public void innerCloseError(Throwable th) {
            if (ExceptionHelper.m9430a(this.error, th)) {
                drain();
            } else {
                RxJavaPlugins.m9212a(th);
            }
        }
    }

    /* compiled from: FlowableGroupJoin.java */
    /* renamed from: z1.bbe$d */
    /* loaded from: classes3.dex */
    static final class C4091d extends AtomicReference<dby> implements FlowableSubscriber<Object>, Disposable {
        private static final long serialVersionUID = 1883890389173668373L;
        final boolean isLeft;
        final AbstractC4089b parent;

        /* JADX INFO: Access modifiers changed from: package-private */
        public C4091d(AbstractC4089b bVar, boolean z) {
            this.parent = bVar;
            this.isLeft = z;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            SubscriptionHelper.cancel(this);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return get() == SubscriptionHelper.CANCELLED;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            SubscriptionHelper.setOnce(this, dbyVar, cjm.f20759b);
        }

        @Override // p110z1.Subscriber
        public void onNext(Object obj) {
            this.parent.innerValue(this.isLeft, obj);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.parent.innerError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.parent.innerComplete(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableGroupJoin.java */
    /* renamed from: z1.bbe$c */
    /* loaded from: classes3.dex */
    public static final class C4090c extends AtomicReference<dby> implements FlowableSubscriber<Object>, Disposable {
        private static final long serialVersionUID = 1883890389173668373L;
        final int index;
        final boolean isLeft;
        final AbstractC4089b parent;

        /* JADX INFO: Access modifiers changed from: package-private */
        public C4090c(AbstractC4089b bVar, boolean z, int i) {
            this.parent = bVar;
            this.isLeft = z;
            this.index = i;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            SubscriptionHelper.cancel(this);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return get() == SubscriptionHelper.CANCELLED;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            SubscriptionHelper.setOnce(this, dbyVar, cjm.f20759b);
        }

        @Override // p110z1.Subscriber
        public void onNext(Object obj) {
            if (SubscriptionHelper.cancel(this)) {
                this.parent.innerClose(this.isLeft, this);
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.parent.innerCloseError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.parent.innerClose(this.isLeft, this);
        }
    }
}
