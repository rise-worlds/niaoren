package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bst */
/* loaded from: classes3.dex */
public final class EndConsumerHelper {
    private EndConsumerHelper() {
        throw new IllegalStateException("No instances!");
    }

    /* renamed from: a */
    public static boolean m9435a(Disposable atrVar, Disposable atrVar2, Class<?> cls) {
        ObjectHelper.m9873a(atrVar2, "next is null");
        if (atrVar == null) {
            return true;
        }
        atrVar2.dispose();
        if (atrVar == DisposableHelper.DISPOSED) {
            return false;
        }
        m9439a(cls);
        return false;
    }

    /* renamed from: a */
    public static boolean m9437a(AtomicReference<Disposable> atomicReference, Disposable atrVar, Class<?> cls) {
        ObjectHelper.m9873a(atrVar, "next is null");
        if (atomicReference.compareAndSet(null, atrVar)) {
            return true;
        }
        atrVar.dispose();
        if (atomicReference.get() == DisposableHelper.DISPOSED) {
            return false;
        }
        m9439a(cls);
        return false;
    }

    /* renamed from: a */
    public static boolean m9434a(dby dbyVar, dby dbyVar2, Class<?> cls) {
        ObjectHelper.m9873a(dbyVar2, "next is null");
        if (dbyVar == null) {
            return true;
        }
        dbyVar2.cancel();
        if (dbyVar == SubscriptionHelper.CANCELLED) {
            return false;
        }
        m9439a(cls);
        return false;
    }

    /* renamed from: a */
    public static boolean m9436a(AtomicReference<dby> atomicReference, dby dbyVar, Class<?> cls) {
        ObjectHelper.m9873a(dbyVar, "next is null");
        if (atomicReference.compareAndSet(null, dbyVar)) {
            return true;
        }
        dbyVar.cancel();
        if (atomicReference.get() == SubscriptionHelper.CANCELLED) {
            return false;
        }
        m9439a(cls);
        return false;
    }

    /* renamed from: a */
    public static String m9438a(String str) {
        return "It is not allowed to subscribe with a(n) " + str + " multiple times. Please create a fresh instance of " + str + " and subscribe that to the target source instead.";
    }

    /* renamed from: a */
    public static void m9439a(Class<?> cls) {
        RxJavaPlugins.m9212a(new ProtocolViolationException(m9438a(cls.getName())));
    }
}
