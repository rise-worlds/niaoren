package p110z1;

import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z1.bsw */
/* loaded from: classes3.dex */
public final class HalfSerializer {
    private HalfSerializer() {
        throw new IllegalStateException("No instances!");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public static <T> void m9424a(Subscriber<? super T> dbxVar, T t, AtomicInteger atomicInteger, AtomicThrowable bsnVar) {
        if (atomicInteger.get() == 0 && atomicInteger.compareAndSet(0, 1)) {
            dbxVar.onNext(t);
            if (atomicInteger.decrementAndGet() != 0) {
                Throwable terminate = bsnVar.terminate();
                if (terminate != null) {
                    dbxVar.onError(terminate);
                } else {
                    dbxVar.onComplete();
                }
            }
        }
    }

    /* renamed from: a */
    public static void m9423a(Subscriber<?> dbxVar, Throwable th, AtomicInteger atomicInteger, AtomicThrowable bsnVar) {
        if (!bsnVar.addThrowable(th)) {
            RxJavaPlugins.m9212a(th);
        } else if (atomicInteger.getAndIncrement() == 0) {
            dbxVar.onError(bsnVar.terminate());
        }
    }

    /* renamed from: a */
    public static void m9422a(Subscriber<?> dbxVar, AtomicInteger atomicInteger, AtomicThrowable bsnVar) {
        if (atomicInteger.getAndIncrement() == 0) {
            Throwable terminate = bsnVar.terminate();
            if (terminate != null) {
                dbxVar.onError(terminate);
            } else {
                dbxVar.onComplete();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public static <T> void m9427a(Observer<? super T> assVar, T t, AtomicInteger atomicInteger, AtomicThrowable bsnVar) {
        if (atomicInteger.get() == 0 && atomicInteger.compareAndSet(0, 1)) {
            assVar.onNext(t);
            if (atomicInteger.decrementAndGet() != 0) {
                Throwable terminate = bsnVar.terminate();
                if (terminate != null) {
                    assVar.onError(terminate);
                } else {
                    assVar.onComplete();
                }
            }
        }
    }

    /* renamed from: a */
    public static void m9426a(Observer<?> assVar, Throwable th, AtomicInteger atomicInteger, AtomicThrowable bsnVar) {
        if (!bsnVar.addThrowable(th)) {
            RxJavaPlugins.m9212a(th);
        } else if (atomicInteger.getAndIncrement() == 0) {
            assVar.onError(bsnVar.terminate());
        }
    }

    /* renamed from: a */
    public static void m9425a(Observer<?> assVar, AtomicInteger atomicInteger, AtomicThrowable bsnVar) {
        if (atomicInteger.getAndIncrement() == 0) {
            Throwable terminate = bsnVar.terminate();
            if (terminate != null) {
                assVar.onError(terminate);
            } else {
                assVar.onComplete();
            }
        }
    }
}
