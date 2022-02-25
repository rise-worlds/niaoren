package p110z1;

import p110z1.Promise;

/* renamed from: z1.dbd */
/* loaded from: classes3.dex */
public class DeferredObject<D, F, P> extends AbstractPromise<D, F, P> implements Deferred<D, F, P> {
    @Override // p110z1.Deferred
    /* renamed from: a */
    public Promise<D, F, P> mo3300a() {
        return this;
    }

    @Override // p110z1.Deferred
    /* renamed from: a */
    public Deferred<D, F, P> mo3299a(D d) {
        synchronized (this) {
            if (mo3281c()) {
                this.f21234c = Promise.EnumC5239a.RESOLVED;
                this.f21239h = d;
                m3313e(d);
                m3314a(this.f21234c, (Promise.EnumC5239a) d, (D) null);
            } else {
                throw new IllegalStateException("Deferred object already finished, cannot resolve again");
            }
        }
        return this;
    }

    @Override // p110z1.Deferred
    /* renamed from: c */
    public Deferred<D, F, P> mo3297c(P p) {
        synchronized (this) {
            if (mo3281c()) {
                m3311g(p);
            } else {
                throw new IllegalStateException("Deferred object already finished, cannot notify progress");
            }
        }
        return this;
    }

    @Override // p110z1.Deferred
    /* renamed from: b */
    public Deferred<D, F, P> mo3298b(F f) {
        synchronized (this) {
            if (mo3281c()) {
                this.f21234c = Promise.EnumC5239a.REJECTED;
                this.f21240i = f;
                m3312f(f);
                m3314a(this.f21234c, (Promise.EnumC5239a) null, (D) f);
            } else {
                throw new IllegalStateException("Deferred object already finished, cannot reject again");
            }
        }
        return this;
    }
}
