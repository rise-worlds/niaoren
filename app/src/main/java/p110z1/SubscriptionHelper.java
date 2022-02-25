package p110z1;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bsk */
/* loaded from: classes3.dex */
public enum SubscriptionHelper implements dby {
    CANCELLED;

    @Override // p110z1.dby
    public void cancel() {
    }

    @Override // p110z1.dby
    public void request(long j) {
    }

    public static boolean validate(dby dbyVar, dby dbyVar2) {
        if (dbyVar2 == null) {
            RxJavaPlugins.m9212a(new NullPointerException("next is null"));
            return false;
        } else if (dbyVar == null) {
            return true;
        } else {
            dbyVar2.cancel();
            reportSubscriptionSet();
            return false;
        }
    }

    public static void reportSubscriptionSet() {
        RxJavaPlugins.m9212a(new ProtocolViolationException("Subscription already set!"));
    }

    public static boolean validate(long j) {
        if (j > 0) {
            return true;
        }
        RxJavaPlugins.m9212a(new IllegalArgumentException("n > 0 required but it was " + j));
        return false;
    }

    public static void reportMoreProduced(long j) {
        RxJavaPlugins.m9212a(new ProtocolViolationException("More produced than requested: " + j));
    }

    public static boolean set(AtomicReference<dby> atomicReference, dby dbyVar) {
        dby dbyVar2;
        do {
            dbyVar2 = atomicReference.get();
            if (dbyVar2 == CANCELLED) {
                if (dbyVar == null) {
                    return false;
                }
                dbyVar.cancel();
                return false;
            }
        } while (!atomicReference.compareAndSet(dbyVar2, dbyVar));
        if (dbyVar2 == null) {
            return true;
        }
        dbyVar2.cancel();
        return true;
    }

    public static boolean setOnce(AtomicReference<dby> atomicReference, dby dbyVar) {
        ObjectHelper.m9873a(dbyVar, "s is null");
        if (atomicReference.compareAndSet(null, dbyVar)) {
            return true;
        }
        dbyVar.cancel();
        if (atomicReference.get() == CANCELLED) {
            return false;
        }
        reportSubscriptionSet();
        return false;
    }

    public static boolean replace(AtomicReference<dby> atomicReference, dby dbyVar) {
        dby dbyVar2;
        do {
            dbyVar2 = atomicReference.get();
            if (dbyVar2 == CANCELLED) {
                if (dbyVar == null) {
                    return false;
                }
                dbyVar.cancel();
                return false;
            }
        } while (!atomicReference.compareAndSet(dbyVar2, dbyVar));
        return true;
    }

    public static boolean cancel(AtomicReference<dby> atomicReference) {
        dby andSet;
        dby dbyVar = atomicReference.get();
        SubscriptionHelper bskVar = CANCELLED;
        if (dbyVar == bskVar || (andSet = atomicReference.getAndSet(bskVar)) == CANCELLED) {
            return false;
        }
        if (andSet == null) {
            return true;
        }
        andSet.cancel();
        return true;
    }

    public static boolean deferredSetOnce(AtomicReference<dby> atomicReference, AtomicLong atomicLong, dby dbyVar) {
        if (!setOnce(atomicReference, dbyVar)) {
            return false;
        }
        long andSet = atomicLong.getAndSet(0L);
        if (andSet == 0) {
            return true;
        }
        dbyVar.request(andSet);
        return true;
    }

    public static void deferredRequest(AtomicReference<dby> atomicReference, AtomicLong atomicLong, long j) {
        dby dbyVar = atomicReference.get();
        if (dbyVar != null) {
            dbyVar.request(j);
        } else if (validate(j)) {
            BackpressureHelper.m9449a(atomicLong, j);
            dby dbyVar2 = atomicReference.get();
            if (dbyVar2 != null) {
                long andSet = atomicLong.getAndSet(0L);
                if (andSet != 0) {
                    dbyVar2.request(andSet);
                }
            }
        }
    }

    public static boolean setOnce(AtomicReference<dby> atomicReference, dby dbyVar, long j) {
        if (!setOnce(atomicReference, dbyVar)) {
            return false;
        }
        dbyVar.request(j);
        return true;
    }
}
