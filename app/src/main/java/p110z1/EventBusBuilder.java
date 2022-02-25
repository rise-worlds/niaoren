package p110z1;

import android.os.Looper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import p110z1.Logger;
import p110z1.MainThreadSupport;

/* renamed from: z1.czg */
/* loaded from: classes3.dex */
public class EventBusBuilder {

    /* renamed from: n */
    private static final ExecutorService f21143n = Executors.newCachedThreadPool();

    /* renamed from: e */
    boolean f21148e;

    /* renamed from: g */
    boolean f21150g;

    /* renamed from: h */
    boolean f21151h;

    /* renamed from: j */
    List<Class<?>> f21153j;

    /* renamed from: k */
    List<SubscriberInfoIndex> f21154k;

    /* renamed from: l */
    Logger f21155l;

    /* renamed from: m */
    MainThreadSupport f21156m;

    /* renamed from: a */
    boolean f21144a = true;

    /* renamed from: b */
    boolean f21145b = true;

    /* renamed from: c */
    boolean f21146c = true;

    /* renamed from: d */
    boolean f21147d = true;

    /* renamed from: f */
    boolean f21149f = true;

    /* renamed from: i */
    ExecutorService f21152i = f21143n;

    /* renamed from: a */
    public EventBusBuilder m3413a(boolean z) {
        this.f21144a = z;
        return this;
    }

    /* renamed from: b */
    public EventBusBuilder m3411b(boolean z) {
        this.f21145b = z;
        return this;
    }

    /* renamed from: c */
    public EventBusBuilder m3409c(boolean z) {
        this.f21146c = z;
        return this;
    }

    /* renamed from: d */
    public EventBusBuilder m3407d(boolean z) {
        this.f21147d = z;
        return this;
    }

    /* renamed from: e */
    public EventBusBuilder m3405e(boolean z) {
        this.f21148e = z;
        return this;
    }

    /* renamed from: f */
    public EventBusBuilder m3404f(boolean z) {
        this.f21149f = z;
        return this;
    }

    /* renamed from: a */
    public EventBusBuilder m3416a(ExecutorService executorService) {
        this.f21152i = executorService;
        return this;
    }

    /* renamed from: a */
    public EventBusBuilder m3417a(Class<?> cls) {
        if (this.f21153j == null) {
            this.f21153j = new ArrayList();
        }
        this.f21153j.add(cls);
        return this;
    }

    /* renamed from: g */
    public EventBusBuilder m3403g(boolean z) {
        this.f21150g = z;
        return this;
    }

    /* renamed from: h */
    public EventBusBuilder m3402h(boolean z) {
        this.f21151h = z;
        return this;
    }

    /* renamed from: a */
    public EventBusBuilder m3414a(SubscriberInfoIndex czyVar) {
        if (this.f21154k == null) {
            this.f21154k = new ArrayList();
        }
        this.f21154k.add(czyVar);
        return this;
    }

    /* renamed from: a */
    public EventBusBuilder m3415a(Logger czjVar) {
        this.f21155l = czjVar;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public Logger m3418a() {
        Logger czjVar = this.f21155l;
        return czjVar != null ? czjVar : (!Logger.C5216a.m3401a() || m3410c() == null) ? new Logger.C5218c() : new Logger.C5216a("EventBus");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public MainThreadSupport m3412b() {
        Object c;
        MainThreadSupport czkVar = this.f21156m;
        if (czkVar != null) {
            return czkVar;
        }
        if (!Logger.C5216a.m3401a() || (c = m3410c()) == null) {
            return null;
        }
        return new MainThreadSupport.C5219a((Looper) c);
    }

    /* renamed from: c */
    Object m3410c() {
        try {
            return Looper.getMainLooper();
        } catch (RuntimeException unused) {
            return null;
        }
    }

    /* renamed from: d */
    public EventBus m3408d() {
        EventBus czfVar;
        synchronized (EventBus.class) {
            if (EventBus.f21114b == null) {
                EventBus.f21114b = m3406e();
                czfVar = EventBus.f21114b;
            } else {
                throw new EventBusException("Default instance already exists. It may be only set once before it's used the first time to ensure consistent behavior.");
            }
        }
        return czfVar;
    }

    /* renamed from: e */
    public EventBus m3406e() {
        return new EventBus(this);
    }
}
