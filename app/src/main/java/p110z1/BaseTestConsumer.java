package p110z1;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import p110z1.BaseTestConsumer;

/* renamed from: z1.btm */
/* loaded from: classes3.dex */
public abstract class BaseTestConsumer<T, U extends BaseTestConsumer<T, U>> implements Disposable {

    /* renamed from: d */
    protected long f20110d;

    /* renamed from: e */
    protected Thread f20111e;

    /* renamed from: f */
    protected boolean f20112f;

    /* renamed from: g */
    protected int f20113g;

    /* renamed from: h */
    protected int f20114h;

    /* renamed from: i */
    protected CharSequence f20115i;

    /* renamed from: j */
    protected boolean f20116j;

    /* renamed from: b */
    protected final List<T> f20108b = new VolatileSizeArrayList();

    /* renamed from: c */
    protected final List<Throwable> f20109c = new VolatileSizeArrayList();

    /* renamed from: a */
    protected final CountDownLatch f20107a = new CountDownLatch(1);

    /* renamed from: q */
    public abstract U mo8908q();

    /* renamed from: r */
    public abstract U mo8907r();

    /* renamed from: a */
    public final Thread m9354a() {
        return this.f20111e;
    }

    /* renamed from: b */
    public final List<T> m9335b() {
        return this.f20108b;
    }

    /* renamed from: c */
    public final List<Throwable> m9326c() {
        return this.f20109c;
    }

    /* renamed from: d */
    public final long m9321d() {
        return this.f20110d;
    }

    /* renamed from: e */
    public final boolean m9320e() {
        return this.f20107a.getCount() == 0;
    }

    /* renamed from: f */
    public final int m9319f() {
        return this.f20108b.size();
    }

    /* renamed from: g */
    public final int m9318g() {
        return this.f20109c.size();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final AssertionError m9341a(String str) {
        StringBuilder sb = new StringBuilder(str.length() + 64);
        sb.append(str);
        sb.append(" (");
        sb.append("latch = ");
        sb.append(this.f20107a.getCount());
        sb.append(", ");
        sb.append("values = ");
        sb.append(this.f20108b.size());
        sb.append(", ");
        sb.append("errors = ");
        sb.append(this.f20109c.size());
        sb.append(", ");
        sb.append("completions = ");
        sb.append(this.f20110d);
        if (this.f20116j) {
            sb.append(", timeout!");
        }
        if (isDisposed()) {
            sb.append(", disposed!");
        }
        CharSequence charSequence = this.f20115i;
        if (charSequence != null) {
            sb.append(", tag = ");
            sb.append(charSequence);
        }
        sb.append(')');
        AssertionError assertionError = new AssertionError(sb.toString());
        if (!this.f20109c.isEmpty()) {
            if (this.f20109c.size() == 1) {
                assertionError.initCause(this.f20109c.get(0));
            } else {
                assertionError.initCause(new CompositeException(this.f20109c));
            }
        }
        return assertionError;
    }

    /* renamed from: h */
    public final U m9317h() throws InterruptedException {
        if (this.f20107a.getCount() == 0) {
            return this;
        }
        this.f20107a.await();
        return this;
    }

    /* renamed from: a */
    public final boolean m9348a(long j, TimeUnit timeUnit) throws InterruptedException {
        boolean z = this.f20107a.getCount() == 0 || this.f20107a.await(j, timeUnit);
        this.f20116j = !z;
        return z;
    }

    /* renamed from: i */
    public final U m9316i() {
        long j = this.f20110d;
        if (j == 0) {
            throw m9341a("Not completed");
        } else if (j <= 1) {
            return this;
        } else {
            throw m9341a("Multiple completions: " + j);
        }
    }

    /* renamed from: j */
    public final U m9315j() {
        long j = this.f20110d;
        int i = (j > 1L ? 1 : (j == 1L ? 0 : -1));
        if (i == 0) {
            throw m9341a("Completed!");
        } else if (i <= 0) {
            return this;
        } else {
            throw m9341a("Multiple completions: " + j);
        }
    }

    /* renamed from: k */
    public final U m9314k() {
        if (this.f20109c.size() == 0) {
            return this;
        }
        throw m9341a("Error(s) present: " + this.f20109c);
    }

    /* renamed from: a */
    public final U m9340a(Throwable th) {
        return m9338a(Functions.m9908c(th));
    }

    /* renamed from: a */
    public final U m9346a(Class<? extends Throwable> cls) {
        return m9338a(Functions.m9912b((Class) cls));
    }

    /* renamed from: a */
    public final U m9338a(Predicate<Throwable> auxVar) {
        int size = this.f20109c.size();
        if (size != 0) {
            boolean z = false;
            Iterator<Throwable> it = this.f20109c.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                try {
                    if (auxVar.test(it.next())) {
                        z = true;
                        break;
                    }
                } catch (Exception e) {
                    throw ExceptionHelper.m9432a(e);
                }
            }
            if (!z) {
                throw m9341a("Error not present");
            } else if (size == 1) {
                return this;
            } else {
                throw m9341a("Error present but other errors as well");
            }
        } else {
            throw m9341a("No errors");
        }
    }

    /* renamed from: a */
    public final U m9342a(T t) {
        if (this.f20108b.size() == 1) {
            T t2 = this.f20108b.get(0);
            if (ObjectHelper.m9874a(t, t2)) {
                return this;
            }
            throw m9341a("expected: " + m9324c(t) + " but was: " + m9324c(t2));
        }
        throw m9341a("expected: " + m9324c(t) + " but was: " + this.f20108b);
    }

    /* renamed from: b */
    public final U m9331b(T t) {
        int size = this.f20108b.size();
        for (int i = 0; i < size; i++) {
            if (ObjectHelper.m9874a(this.f20108b.get(i), t)) {
                throw m9341a("Value at position " + i + " is equal to " + m9324c(t) + "; Expected them to be different");
            }
        }
        return this;
    }

    /* renamed from: b */
    public final U m9328b(Predicate<T> auxVar) {
        m9349a(0, (Predicate) auxVar);
        if (this.f20108b.size() <= 1) {
            return this;
        }
        throw m9341a("Value present but other values as well");
    }

    /* renamed from: c */
    public final U m9323c(Predicate<? super T> auxVar) {
        int size = this.f20108b.size();
        for (int i = 0; i < size; i++) {
            try {
                if (auxVar.test((T) this.f20108b.get(i))) {
                    throw m9341a("Value at position " + i + " matches predicate " + auxVar.toString() + ", which was not expected.");
                }
            } catch (Exception e) {
                throw ExceptionHelper.m9432a(e);
            }
        }
        return this;
    }

    /* renamed from: a */
    public final U m9352a(int i, T t) {
        int size = this.f20108b.size();
        if (size == 0) {
            throw m9341a("No values");
        } else if (i < size) {
            T t2 = this.f20108b.get(i);
            if (ObjectHelper.m9874a(t, t2)) {
                return this;
            }
            throw m9341a("expected: " + m9324c(t) + " but was: " + m9324c(t2));
        } else {
            throw m9341a("Invalid index: " + i);
        }
    }

    /* renamed from: a */
    public final U m9349a(int i, Predicate<T> auxVar) {
        if (this.f20108b.size() == 0) {
            throw m9341a("No values");
        } else if (i < this.f20108b.size()) {
            try {
                if (auxVar.test(this.f20108b.get(i))) {
                    return this;
                }
                throw m9341a("Value not present");
            } catch (Exception e) {
                throw ExceptionHelper.m9432a(e);
            }
        } else {
            throw m9341a("Invalid index: " + i);
        }
    }

    /* renamed from: c */
    public static String m9324c(Object obj) {
        if (obj == null) {
            return "null";
        }
        return obj + " (class: " + obj.getClass().getSimpleName() + ")";
    }

    /* renamed from: a */
    public final U m9353a(int i) {
        int size = this.f20108b.size();
        if (size == i) {
            return this;
        }
        throw m9341a("Value counts differ; expected: " + i + " but was: " + size);
    }

    /* renamed from: l */
    public final U m9313l() {
        return m9353a(0);
    }

    /* renamed from: a */
    public final U m9336a(T... tArr) {
        int size = this.f20108b.size();
        if (size == tArr.length) {
            for (int i = 0; i < size; i++) {
                T t = this.f20108b.get(i);
                T t2 = tArr[i];
                if (!ObjectHelper.m9874a(t2, t)) {
                    throw m9341a("Values at position " + i + " differ; expected: " + m9324c(t2) + " but was: " + m9324c(t));
                }
            }
            return this;
        }
        throw m9341a("Value count differs; expected: " + tArr.length + ExpandableTextView.f6958c + Arrays.toString(tArr) + " but was: " + size + ExpandableTextView.f6958c + this.f20108b);
    }

    /* renamed from: b */
    public final U m9327b(T... tArr) {
        return (U) mo8908q().m9336a(tArr).m9314k().m9315j();
    }

    /* renamed from: a */
    public final U m9339a(Collection<? extends T> collection) {
        if (collection.isEmpty()) {
            m9313l();
            return this;
        }
        for (T t : this.f20108b) {
            if (!collection.contains(t)) {
                throw m9341a("Value not in the expected collection: " + m9324c(t));
            }
        }
        return this;
    }

    /* renamed from: b */
    public final U m9329b(Collection<? extends T> collection) {
        return (U) mo8908q().m9339a(collection).m9314k().m9315j();
    }

    /* renamed from: a */
    public final U m9343a(Iterable<? extends T> iterable) {
        boolean hasNext;
        boolean hasNext2;
        Iterator<T> it = this.f20108b.iterator();
        Iterator<? extends T> it2 = iterable.iterator();
        int i = 0;
        while (true) {
            hasNext = it2.hasNext();
            hasNext2 = it.hasNext();
            if (!hasNext2 || !hasNext) {
                break;
            }
            Object next = it2.next();
            T next2 = it.next();
            if (ObjectHelper.m9874a(next, next2)) {
                i++;
            } else {
                throw m9341a("Values at position " + i + " differ; expected: " + m9324c(next) + " but was: " + m9324c(next2));
            }
        }
        if (hasNext2) {
            throw m9341a("More values received than expected (" + i + ")");
        } else if (!hasNext) {
            return this;
        } else {
            throw m9341a("Fewer values received than expected (" + i + ")");
        }
    }

    /* renamed from: b */
    public final U m9332b(Iterable<? extends T> iterable) {
        return (U) mo8908q().m9343a(iterable).m9314k().m9315j();
    }

    /* renamed from: m */
    public final U m9312m() {
        if (this.f20107a.getCount() == 0) {
            long j = this.f20110d;
            if (j <= 1) {
                int size = this.f20109c.size();
                if (size > 1) {
                    throw m9341a("Terminated with multiple errors: " + size);
                } else if (j == 0 || size == 0) {
                    return this;
                } else {
                    throw m9341a("Terminated with multiple completions and errors: " + j);
                }
            } else {
                throw m9341a("Terminated with multiple completions: " + j);
            }
        } else {
            throw m9341a("Subscriber still running!");
        }
    }

    /* renamed from: n */
    public final U m9311n() {
        if (this.f20107a.getCount() != 0) {
            return this;
        }
        throw m9341a("Subscriber terminated!");
    }

    /* renamed from: o */
    public final boolean m9310o() {
        try {
            m9317h();
            return true;
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    /* renamed from: b */
    public final boolean m9333b(long j, TimeUnit timeUnit) {
        try {
            return m9348a(j, timeUnit);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    /* renamed from: b */
    public final U m9330b(String str) {
        int size = this.f20109c.size();
        if (size == 0) {
            throw m9341a("No errors");
        } else if (size == 1) {
            String message = this.f20109c.get(0).getMessage();
            if (ObjectHelper.m9874a((Object) str, (Object) message)) {
                return this;
            }
            throw m9341a("Error message differs; exptected: " + str + " but was: " + message);
        } else {
            throw m9341a("Multiple errors");
        }
    }

    /* renamed from: p */
    public final List<List<Object>> m9309p() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(m9335b());
        arrayList.add(m9326c());
        ArrayList arrayList2 = new ArrayList();
        for (long j = 0; j < this.f20110d; j++) {
            arrayList2.add(Notification.m10637f());
        }
        arrayList.add(arrayList2);
        return arrayList;
    }

    /* renamed from: c */
    public final U m9322c(T... tArr) {
        return (U) mo8908q().m9336a(tArr).m9314k().m9316i();
    }

    /* renamed from: a */
    public final U m9344a(Class<? extends Throwable> cls, T... tArr) {
        return (U) mo8908q().m9336a(tArr).m9346a(cls).m9315j();
    }

    /* renamed from: a */
    public final U m9337a(Predicate<Throwable> auxVar, T... tArr) {
        return (U) mo8908q().m9336a(tArr).m9338a(auxVar).m9315j();
    }

    /* renamed from: a */
    public final U m9345a(Class<? extends Throwable> cls, String str, T... tArr) {
        return (U) mo8908q().m9336a(tArr).m9346a(cls).m9330b(str).m9315j();
    }

    /* renamed from: c */
    public final U m9325c(long j, TimeUnit timeUnit) {
        try {
            if (!this.f20107a.await(j, timeUnit)) {
                this.f20116j = true;
                dispose();
            }
            return this;
        } catch (InterruptedException e) {
            dispose();
            throw ExceptionHelper.m9432a(e);
        }
    }

    /* renamed from: s */
    public final U m9308s() {
        return (U) mo8908q().m9313l().m9314k().m9315j();
    }

    /* renamed from: a */
    public final U m9347a(CharSequence charSequence) {
        this.f20115i = charSequence;
        return this;
    }

    /* compiled from: BaseTestConsumer.java */
    /* renamed from: z1.btm$a */
    /* loaded from: classes3.dex */
    public enum EnumC4751a implements Runnable {
        SPIN { // from class: z1.btm.a.1
            @Override // p110z1.BaseTestConsumer.EnumC4751a, java.lang.Runnable
            public void run() {
            }
        },
        YIELD { // from class: z1.btm.a.2
            @Override // p110z1.BaseTestConsumer.EnumC4751a, java.lang.Runnable
            public void run() {
                Thread.yield();
            }
        },
        SLEEP_1MS { // from class: z1.btm.a.3
            @Override // p110z1.BaseTestConsumer.EnumC4751a, java.lang.Runnable
            public void run() {
                sleep(1);
            }
        },
        SLEEP_10MS { // from class: z1.btm.a.4
            @Override // p110z1.BaseTestConsumer.EnumC4751a, java.lang.Runnable
            public void run() {
                sleep(10);
            }
        },
        SLEEP_100MS { // from class: z1.btm.a.5
            @Override // p110z1.BaseTestConsumer.EnumC4751a, java.lang.Runnable
            public void run() {
                sleep(100);
            }
        },
        SLEEP_1000MS { // from class: z1.btm.a.6
            @Override // p110z1.BaseTestConsumer.EnumC4751a, java.lang.Runnable
            public void run() {
                sleep(1000);
            }
        };

        @Override // java.lang.Runnable
        public abstract void run();

        static void sleep(int i) {
            try {
                Thread.sleep(i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /* renamed from: b */
    public final U m9334b(int i) {
        return m9350a(i, EnumC4751a.SLEEP_10MS, 5000L);
    }

    /* renamed from: a */
    public final U m9351a(int i, Runnable runnable) {
        return m9350a(i, runnable, 5000L);
    }

    /* renamed from: a */
    public final U m9350a(int i, Runnable runnable, long j) {
        long currentTimeMillis = System.currentTimeMillis();
        while (true) {
            if (j <= 0 || System.currentTimeMillis() - currentTimeMillis < j) {
                if (this.f20107a.getCount() == 0 || this.f20108b.size() >= i) {
                    break;
                }
                runnable.run();
            } else {
                this.f20116j = true;
                break;
            }
        }
        return this;
    }

    /* renamed from: t */
    public final boolean m9307t() {
        return this.f20116j;
    }

    /* renamed from: u */
    public final U m9306u() {
        this.f20116j = false;
        return this;
    }

    /* renamed from: v */
    public final U m9305v() {
        if (this.f20116j) {
            return this;
        }
        throw m9341a("No timeout?!");
    }

    /* renamed from: w */
    public final U m9304w() {
        if (!this.f20116j) {
            return this;
        }
        throw m9341a("Timeout?!");
    }
}
