package p110z1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z1.azl */
/* loaded from: classes3.dex */
public final class FlowableConcatArray<T> extends Flowable<T> {

    /* renamed from: b */
    final Publisher<? extends T>[] f17951b;

    /* renamed from: c */
    final boolean f17952c;

    public FlowableConcatArray(Publisher<? extends T>[] dbwVarArr, boolean z) {
        this.f17951b = dbwVarArr;
        this.f17952c = z;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        C4012a aVar = new C4012a(this.f17951b, this.f17952c, dbxVar);
        dbxVar.onSubscribe(aVar);
        aVar.onComplete();
    }

    /* compiled from: FlowableConcatArray.java */
    /* renamed from: z1.azl$a */
    /* loaded from: classes3.dex */
    static final class C4012a<T> extends SubscriptionArbiter implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -8158322871608889516L;
        final boolean delayError;
        final Subscriber<? super T> downstream;
        List<Throwable> errors;
        int index;
        long produced;
        final Publisher<? extends T>[] sources;
        final AtomicInteger wip = new AtomicInteger();

        C4012a(Publisher<? extends T>[] dbwVarArr, boolean z, Subscriber<? super T> dbxVar) {
            super(false);
            this.downstream = dbxVar;
            this.sources = dbwVarArr;
            this.delayError = z;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            setSubscription(dbyVar);
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            this.produced++;
            this.downstream.onNext(t);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.delayError) {
                List list = this.errors;
                if (list == null) {
                    list = new ArrayList((this.sources.length - this.index) + 1);
                    this.errors = list;
                }
                list.add(th);
                onComplete();
                return;
            }
            this.downstream.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (this.wip.getAndIncrement() == 0) {
                Publisher<? extends T>[] dbwVarArr = this.sources;
                int length = dbwVarArr.length;
                int i = this.index;
                while (i != length) {
                    Publisher<? extends T> dbwVar = dbwVarArr[i];
                    if (dbwVar == null) {
                        NullPointerException nullPointerException = new NullPointerException("A Publisher entry is null");
                        if (this.delayError) {
                            List list = this.errors;
                            if (list == null) {
                                list = new ArrayList((length - i) + 1);
                                this.errors = list;
                            }
                            list.add(nullPointerException);
                            i++;
                        } else {
                            this.downstream.onError(nullPointerException);
                            return;
                        }
                    } else {
                        long j = this.produced;
                        if (j != 0) {
                            this.produced = 0L;
                            produced(j);
                        }
                        dbwVar.subscribe(this);
                        i++;
                        this.index = i;
                        if (this.wip.decrementAndGet() == 0) {
                            return;
                        }
                    }
                }
                List<Throwable> list2 = this.errors;
                if (list2 == null) {
                    this.downstream.onComplete();
                } else if (list2.size() == 1) {
                    this.downstream.onError(list2.get(0));
                } else {
                    this.downstream.onError(new CompositeException(list2));
                }
            }
        }
    }
}
