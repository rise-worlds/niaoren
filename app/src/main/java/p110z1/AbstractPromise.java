package p110z1;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import p110z1.Promise;

/* renamed from: z1.dbb */
/* loaded from: classes3.dex */
public abstract class AbstractPromise<D, F, P> implements Promise<D, F, P> {

    /* renamed from: b */
    protected final Logger f21233b = LoggerFactory.getLogger(AbstractPromise.class);

    /* renamed from: c */
    protected volatile Promise.EnumC5239a f21234c = Promise.EnumC5239a.PENDING;

    /* renamed from: d */
    protected final List<DoneCallback<D>> f21235d = new CopyOnWriteArrayList();

    /* renamed from: e */
    protected final List<FailCallback<F>> f21236e = new CopyOnWriteArrayList();

    /* renamed from: f */
    protected final List<ProgressCallback<P>> f21237f = new CopyOnWriteArrayList();

    /* renamed from: g */
    protected final List<AlwaysCallback<D, F>> f21238g = new CopyOnWriteArrayList();

    /* renamed from: h */
    protected D f21239h;

    /* renamed from: i */
    protected F f21240i;

    @Override // p110z1.Promise
    /* renamed from: b */
    public Promise.EnumC5239a mo3283b() {
        return this.f21234c;
    }

    @Override // p110z1.Promise
    /* renamed from: b */
    public Promise<D, F, P> mo3282b(DoneCallback<D> daqVar) {
        synchronized (this) {
            if (mo3280d()) {
                mo3317a((DoneCallback<DoneCallback<D>>) daqVar, (DoneCallback<D>) this.f21239h);
            } else {
                this.f21235d.add(daqVar);
            }
        }
        return this;
    }

    @Override // p110z1.Promise
    /* renamed from: a */
    public Promise<D, F, P> mo3285a(FailCallback<F> datVar) {
        synchronized (this) {
            if (mo3279e()) {
                mo3316a((FailCallback<FailCallback<F>>) datVar, (FailCallback<F>) this.f21240i);
            } else {
                this.f21236e.add(datVar);
            }
        }
        return this;
    }

    @Override // p110z1.Promise
    /* renamed from: a */
    public Promise<D, F, P> mo3295a(AlwaysCallback<D, F> dakVar) {
        synchronized (this) {
            if (mo3281c()) {
                this.f21238g.add(dakVar);
            } else {
                mo3318a(dakVar, this.f21234c, this.f21239h, this.f21240i);
            }
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: e */
    public void m3313e(D d) {
        for (DoneCallback<D> daqVar : this.f21235d) {
            try {
                mo3317a((DoneCallback<DoneCallback<D>>) daqVar, (DoneCallback<D>) d);
            } catch (Exception e) {
                this.f21233b.error("an uncaught exception occured in a DoneCallback", (Throwable) e);
            }
        }
        this.f21235d.clear();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void mo3317a(DoneCallback<D> daqVar, D d) {
        daqVar.onDone(d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: f */
    public void m3312f(F f) {
        for (FailCallback<F> datVar : this.f21236e) {
            try {
                mo3316a((FailCallback<FailCallback<F>>) datVar, (FailCallback<F>) f);
            } catch (Exception e) {
                this.f21233b.error("an uncaught exception occured in a FailCallback", (Throwable) e);
            }
        }
        this.f21236e.clear();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void mo3316a(FailCallback<F> datVar, F f) {
        datVar.onFail(f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: g */
    public void m3311g(P p) {
        for (ProgressCallback<P> dawVar : this.f21237f) {
            try {
                mo3315a((ProgressCallback<ProgressCallback<P>>) dawVar, (ProgressCallback<P>) p);
            } catch (Exception e) {
                this.f21233b.error("an uncaught exception occured in a ProgressCallback", (Throwable) e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void mo3315a(ProgressCallback<P> dawVar, P p) {
        dawVar.mo3266a(p);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m3314a(Promise.EnumC5239a aVar, D d, F f) {
        for (AlwaysCallback<D, F> dakVar : this.f21238g) {
            try {
                mo3318a(dakVar, aVar, d, f);
            } catch (Exception e) {
                this.f21233b.error("an uncaught exception occured in a AlwaysCallback", (Throwable) e);
            }
        }
        this.f21238g.clear();
        synchronized (this) {
            notifyAll();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void mo3318a(AlwaysCallback<D, F> dakVar, Promise.EnumC5239a aVar, D d, F f) {
        dakVar.m3347a(aVar, d, f);
    }

    @Override // p110z1.Promise
    /* renamed from: a */
    public Promise<D, F, P> mo3284a(ProgressCallback<P> dawVar) {
        this.f21237f.add(dawVar);
        return this;
    }

    @Override // p110z1.Promise
    /* renamed from: a */
    public Promise<D, F, P> mo3294a(DoneCallback<D> daqVar) {
        return mo3282b(daqVar);
    }

    @Override // p110z1.Promise
    /* renamed from: a */
    public Promise<D, F, P> mo3293a(DoneCallback<D> daqVar, FailCallback<F> datVar) {
        mo3282b(daqVar);
        mo3285a(datVar);
        return this;
    }

    @Override // p110z1.Promise
    /* renamed from: a */
    public Promise<D, F, P> mo3292a(DoneCallback<D> daqVar, FailCallback<F> datVar, ProgressCallback<P> dawVar) {
        mo3282b(daqVar);
        mo3285a(datVar);
        mo3284a(dawVar);
        return this;
    }

    @Override // p110z1.Promise
    /* renamed from: a */
    public <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> mo3291a(DoneFilter<D, D_OUT> darVar) {
        return new FilteredPromise(this, darVar, null, null);
    }

    @Override // p110z1.Promise
    /* renamed from: a */
    public <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> mo3290a(DoneFilter<D, D_OUT> darVar, FailFilter<F, F_OUT> dauVar) {
        return new FilteredPromise(this, darVar, dauVar, null);
    }

    @Override // p110z1.Promise
    /* renamed from: a */
    public <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> mo3289a(DoneFilter<D, D_OUT> darVar, FailFilter<F, F_OUT> dauVar, ProgressFilter<P, P_OUT> daxVar) {
        return new FilteredPromise(this, darVar, dauVar, daxVar);
    }

    @Override // p110z1.Promise
    /* renamed from: a */
    public <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> mo3288a(DonePipe<D, D_OUT, F_OUT, P_OUT> dasVar) {
        return new PipedPromise(this, dasVar, null, null);
    }

    @Override // p110z1.Promise
    /* renamed from: a */
    public <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> mo3287a(DonePipe<D, D_OUT, F_OUT, P_OUT> dasVar, FailPipe<F, D_OUT, F_OUT, P_OUT> davVar) {
        return new PipedPromise(this, dasVar, davVar, null);
    }

    @Override // p110z1.Promise
    /* renamed from: a */
    public <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> mo3286a(DonePipe<D, D_OUT, F_OUT, P_OUT> dasVar, FailPipe<F, D_OUT, F_OUT, P_OUT> davVar, ProgressPipe<P, D_OUT, F_OUT, P_OUT> dayVar) {
        return new PipedPromise(this, dasVar, davVar, dayVar);
    }

    @Override // p110z1.Promise
    /* renamed from: c */
    public boolean mo3281c() {
        return this.f21234c == Promise.EnumC5239a.PENDING;
    }

    @Override // p110z1.Promise
    /* renamed from: d */
    public boolean mo3280d() {
        return this.f21234c == Promise.EnumC5239a.RESOLVED;
    }

    @Override // p110z1.Promise
    /* renamed from: e */
    public boolean mo3279e() {
        return this.f21234c == Promise.EnumC5239a.REJECTED;
    }

    @Override // p110z1.Promise
    /* renamed from: f */
    public void mo3278f() throws InterruptedException {
        mo3296a(-1L);
    }

    @Override // p110z1.Promise
    /* renamed from: a */
    public void mo3296a(long j) throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (this) {
            while (mo3281c()) {
                int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
                if (i <= 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw e;
                    }
                } else {
                    wait(j - (System.currentTimeMillis() - currentTimeMillis));
                }
                if (i > 0 && System.currentTimeMillis() - currentTimeMillis >= j) {
                    return;
                }
            }
        }
    }
}
