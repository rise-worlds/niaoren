package p110z1;

import java.util.concurrent.Callable;

/* renamed from: z1.bcz */
/* loaded from: classes3.dex */
public final class FlowableScalarXMap {
    private FlowableScalarXMap() {
        throw new IllegalStateException("No instances!");
    }

    /* renamed from: a */
    public static <T, R> boolean m9735a(Publisher<T> dbwVar, Subscriber<? super R> dbxVar, Function<? super T, ? extends Publisher<? extends R>> aunVar) {
        if (!(dbwVar instanceof Callable)) {
            return false;
        }
        try {
            Object obj = (Object) ((Callable) dbwVar).call();
            if (obj == 0) {
                EmptySubscription.complete(dbxVar);
                return true;
            }
            try {
                Publisher dbwVar2 = (Publisher) ObjectHelper.m9873a(aunVar.apply(obj), "The mapper returned a null Publisher");
                if (dbwVar2 instanceof Callable) {
                    try {
                        Object call = ((Callable) dbwVar2).call();
                        if (call == null) {
                            EmptySubscription.complete(dbxVar);
                            return true;
                        }
                        dbxVar.onSubscribe(new ScalarSubscription(dbxVar, call));
                    } catch (Throwable th) {
                        Exceptions.m9983b(th);
                        EmptySubscription.error(th, dbxVar);
                        return true;
                    }
                } else {
                    dbwVar2.subscribe(dbxVar);
                }
                return true;
            } catch (Throwable th2) {
                Exceptions.m9983b(th2);
                EmptySubscription.error(th2, dbxVar);
                return true;
            }
        } catch (Throwable th3) {
            Exceptions.m9983b(th3);
            EmptySubscription.error(th3, dbxVar);
            return true;
        }
    }

    /* renamed from: a */
    public static <T, U> Flowable<U> m9736a(T t, Function<? super T, ? extends Publisher<? extends U>> aunVar) {
        return RxJavaPlugins.m9207a(new C4186a(t, aunVar));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableScalarXMap.java */
    /* renamed from: z1.bcz$a */
    /* loaded from: classes3.dex */
    public static final class C4186a<T, R> extends Flowable<R> {

        /* renamed from: b */
        final T f18310b;

        /* renamed from: c */
        final Function<? super T, ? extends Publisher<? extends R>> f18311c;

        C4186a(T t, Function<? super T, ? extends Publisher<? extends R>> aunVar) {
            this.f18310b = t;
            this.f18311c = aunVar;
        }

        @Override // p110z1.Flowable
        /* renamed from: d */
        public void mo9054d(Subscriber<? super R> dbxVar) {
            try {
                Publisher dbwVar = (Publisher) ObjectHelper.m9873a(this.f18311c.apply((T) this.f18310b), "The mapper returned a null Publisher");
                if (dbwVar instanceof Callable) {
                    try {
                        Object call = ((Callable) dbwVar).call();
                        if (call == null) {
                            EmptySubscription.complete(dbxVar);
                        } else {
                            dbxVar.onSubscribe(new ScalarSubscription(dbxVar, call));
                        }
                    } catch (Throwable th) {
                        Exceptions.m9983b(th);
                        EmptySubscription.error(th, dbxVar);
                    }
                } else {
                    dbwVar.subscribe(dbxVar);
                }
            } catch (Throwable th2) {
                EmptySubscription.error(th2, dbxVar);
            }
        }
    }
}
