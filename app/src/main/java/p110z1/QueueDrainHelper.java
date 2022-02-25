package p110z1;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: z1.btg */
/* loaded from: classes3.dex */
public final class QueueDrainHelper {

    /* renamed from: a */
    static final long f20103a = Long.MIN_VALUE;

    /* renamed from: b */
    static final long f20104b = Long.MAX_VALUE;

    private QueueDrainHelper() {
        throw new IllegalStateException("No instances!");
    }

    /* renamed from: a */
    public static <T, U> void m9373a(SimplePlainQueue<T> avvVar, Subscriber<? super U> dbxVar, boolean z, Disposable atrVar, QueueDrain<T, U> btfVar) {
        int i = 1;
        while (true) {
            boolean d = btfVar.mo9381d();
            T poll = avvVar.poll();
            boolean z2 = poll == null;
            if (m9369a(d, z2, dbxVar, z, avvVar, btfVar)) {
                if (atrVar != null) {
                    atrVar.dispose();
                    return;
                }
                return;
            } else if (z2) {
                i = btfVar.mo9385a(-i);
                if (i == 0) {
                    return;
                }
            } else {
                long h = btfVar.mo9378h();
                if (h == 0) {
                    avvVar.clear();
                    if (atrVar != null) {
                        atrVar.dispose();
                    }
                    dbxVar.onError(new MissingBackpressureException("Could not emit value due to lack of requests."));
                    return;
                } else if (btfVar.mo9383a(dbxVar, poll) && h != Long.MAX_VALUE) {
                    btfVar.mo9384a(1L);
                }
            }
        }
    }

    /* renamed from: a */
    public static <T, U> boolean m9369a(boolean z, boolean z2, Subscriber<?> dbxVar, boolean z3, SimpleQueue<?> avwVar, QueueDrain<T, U> btfVar) {
        if (btfVar.mo9382c()) {
            avwVar.clear();
            return true;
        } else if (!z) {
            return false;
        } else {
            if (!z3) {
                Throwable g = btfVar.mo9379g();
                if (g != null) {
                    avwVar.clear();
                    dbxVar.onError(g);
                    return true;
                } else if (!z2) {
                    return false;
                } else {
                    dbxVar.onComplete();
                    return true;
                }
            } else if (!z2) {
                return false;
            } else {
                Throwable g2 = btfVar.mo9379g();
                if (g2 != null) {
                    dbxVar.onError(g2);
                } else {
                    dbxVar.onComplete();
                }
                return true;
            }
        }
    }

    /* renamed from: a */
    public static <T, U> void m9374a(SimplePlainQueue<T> avvVar, Observer<? super U> assVar, boolean z, Disposable atrVar, ObservableQueueDrain<T, U> btcVar) {
        int i = 1;
        while (!m9370a(btcVar.mo9397b(), avvVar.isEmpty(), assVar, z, avvVar, atrVar, btcVar)) {
            while (true) {
                boolean b = btcVar.mo9397b();
                T poll = avvVar.poll();
                boolean z2 = poll == null;
                if (!m9370a(b, z2, assVar, z, avvVar, atrVar, btcVar)) {
                    if (z2) {
                        i = btcVar.mo9399a(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        btcVar.mo9398a(assVar, poll);
                    }
                } else {
                    return;
                }
            }
        }
    }

    /* renamed from: a */
    public static <T, U> boolean m9370a(boolean z, boolean z2, Observer<?> assVar, boolean z3, SimpleQueue<?> avwVar, Disposable atrVar, ObservableQueueDrain<T, U> btcVar) {
        if (btcVar.mo9400a()) {
            avwVar.clear();
            atrVar.dispose();
            return true;
        } else if (!z) {
            return false;
        } else {
            if (!z3) {
                Throwable e = btcVar.mo9395e();
                if (e != null) {
                    avwVar.clear();
                    if (atrVar != null) {
                        atrVar.dispose();
                    }
                    assVar.onError(e);
                    return true;
                } else if (!z2) {
                    return false;
                } else {
                    if (atrVar != null) {
                        atrVar.dispose();
                    }
                    assVar.onComplete();
                    return true;
                }
            } else if (!z2) {
                return false;
            } else {
                if (atrVar != null) {
                    atrVar.dispose();
                }
                Throwable e2 = btcVar.mo9395e();
                if (e2 != null) {
                    assVar.onError(e2);
                } else {
                    assVar.onComplete();
                }
                return true;
            }
        }
    }

    /* renamed from: a */
    public static <T> SimpleQueue<T> m9377a(int i) {
        if (i < 0) {
            return new SpscLinkedArrayQueue(-i);
        }
        return new SpscArrayQueue(i);
    }

    /* renamed from: a */
    public static void m9371a(dby dbyVar, int i) {
        dbyVar.request(i < 0 ? Long.MAX_VALUE : i);
    }

    /* renamed from: a */
    public static <T> boolean m9376a(long j, Subscriber<? super T> dbxVar, Queue<T> queue, AtomicLong atomicLong, BooleanSupplier aukVar) {
        long j2;
        do {
            j2 = atomicLong.get();
        } while (!atomicLong.compareAndSet(j2, BackpressureHelper.m9450a(Long.MAX_VALUE & j2, j) | (j2 & Long.MIN_VALUE)));
        if (j2 != Long.MIN_VALUE) {
            return false;
        }
        m9368b(j | Long.MIN_VALUE, dbxVar, queue, atomicLong, aukVar);
        return true;
    }

    /* renamed from: a */
    static boolean m9375a(BooleanSupplier aukVar) {
        try {
            return aukVar.getAsBoolean();
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            return true;
        }
    }

    /* renamed from: b */
    static <T> boolean m9368b(long j, Subscriber<? super T> dbxVar, Queue<T> queue, AtomicLong atomicLong, BooleanSupplier aukVar) {
        long j2 = j & Long.MIN_VALUE;
        while (true) {
            if (j2 != j) {
                if (m9375a(aukVar)) {
                    return true;
                }
                Object obj = (T) queue.poll();
                if (obj == null) {
                    dbxVar.onComplete();
                    return true;
                }
                dbxVar.onNext(obj);
                j2++;
            } else if (m9375a(aukVar)) {
                return true;
            } else {
                if (queue.isEmpty()) {
                    dbxVar.onComplete();
                    return true;
                }
                j = atomicLong.get();
                if (j == j2) {
                    long addAndGet = atomicLong.addAndGet(-(j2 & Long.MAX_VALUE));
                    if ((Long.MAX_VALUE & addAndGet) == 0) {
                        return false;
                    }
                    j2 = addAndGet & Long.MIN_VALUE;
                    j = addAndGet;
                } else {
                    continue;
                }
            }
        }
    }

    /* renamed from: a */
    public static <T> void m9372a(Subscriber<? super T> dbxVar, Queue<T> queue, AtomicLong atomicLong, BooleanSupplier aukVar) {
        long j;
        long j2;
        if (queue.isEmpty()) {
            dbxVar.onComplete();
        } else if (!m9368b(atomicLong.get(), dbxVar, queue, atomicLong, aukVar)) {
            do {
                j = atomicLong.get();
                if ((j & Long.MIN_VALUE) == 0) {
                    j2 = j | Long.MIN_VALUE;
                } else {
                    return;
                }
            } while (!atomicLong.compareAndSet(j, j2));
            if (j != 0) {
                m9368b(j2, dbxVar, queue, atomicLong, aukVar);
            }
        }
    }
}
