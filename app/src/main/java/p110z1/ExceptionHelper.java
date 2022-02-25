package p110z1;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bsv */
/* loaded from: classes3.dex */
public final class ExceptionHelper {

    /* renamed from: a */
    public static final Throwable f20064a = new C4744a();

    private ExceptionHelper() {
        throw new IllegalStateException("No instances!");
    }

    /* renamed from: a */
    public static RuntimeException m9432a(Throwable th) {
        if (th instanceof Error) {
            throw ((Error) th);
        } else if (th instanceof RuntimeException) {
            return (RuntimeException) th;
        } else {
            return new RuntimeException(th);
        }
    }

    /* renamed from: a */
    public static <T> boolean m9430a(AtomicReference<Throwable> atomicReference, Throwable th) {
        Throwable th2;
        do {
            th2 = atomicReference.get();
            if (th2 == f20064a) {
                return false;
            }
        } while (!atomicReference.compareAndSet(th2, th2 == null ? th : new CompositeException(th2, th)));
        return true;
    }

    /* renamed from: a */
    public static <T> Throwable m9431a(AtomicReference<Throwable> atomicReference) {
        Throwable th = atomicReference.get();
        Throwable th2 = f20064a;
        return th != th2 ? atomicReference.getAndSet(th2) : th;
    }

    /* renamed from: b */
    public static List<Throwable> m9429b(Throwable th) {
        ArrayList arrayList = new ArrayList();
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.offer(th);
        while (!arrayDeque.isEmpty()) {
            Throwable th2 = (Throwable) arrayDeque.removeFirst();
            if (th2 instanceof CompositeException) {
                List<Throwable> exceptions = ((CompositeException) th2).getExceptions();
                for (int size = exceptions.size() - 1; size >= 0; size--) {
                    arrayDeque.offerFirst(exceptions.get(size));
                }
            } else {
                arrayList.add(th2);
            }
        }
        return arrayList;
    }

    /* renamed from: c */
    public static <E extends Throwable> Exception m9428c(Throwable th) throws Throwable {
        if (th instanceof Exception) {
            return (Exception) th;
        }
        throw th;
    }

    /* renamed from: a */
    public static String m9433a(long j, TimeUnit timeUnit) {
        return "The source did not signal an event for " + j + ExpandableTextView.f6958c + timeUnit.toString().toLowerCase() + " and has been terminated.";
    }

    /* compiled from: ExceptionHelper.java */
    /* renamed from: z1.bsv$a */
    /* loaded from: classes3.dex */
    static final class C4744a extends Throwable {
        private static final long serialVersionUID = -4649703670690200604L;

        @Override // java.lang.Throwable
        public Throwable fillInStackTrace() {
            return this;
        }

        C4744a() {
            super("No further exceptions");
        }
    }
}
