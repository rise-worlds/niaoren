package org.jdeferred.android;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import p110z1.AlwaysCallback;
import p110z1.Deferred;
import p110z1.DeferredObject;
import p110z1.DoneCallback;
import p110z1.FailCallback;
import p110z1.ProgressCallback;
import p110z1.Promise;

/* renamed from: org.jdeferred.android.c */
/* loaded from: classes2.dex */
public class AndroidDeferredObject<D, F, P> extends DeferredObject<D, F, P> {

    /* renamed from: j */
    private static final HandlerC3264b f14832j = new HandlerC3264b();

    /* renamed from: k */
    private static final int f14833k = 1;

    /* renamed from: l */
    private static final int f14834l = 2;

    /* renamed from: m */
    private static final int f14835m = 3;

    /* renamed from: n */
    private static final int f14836n = 4;

    /* renamed from: a */
    protected final Logger f14837a;

    /* renamed from: o */
    private final AndroidExecutionScope f14838o;

    public AndroidDeferredObject(Promise<D, F, P> dazVar) {
        this(dazVar, AndroidExecutionScope.UI);
    }

    public AndroidDeferredObject(Promise<D, F, P> dazVar, AndroidExecutionScope eVar) {
        this.f14837a = LoggerFactory.getLogger(AndroidDeferredObject.class);
        this.f14838o = eVar;
        dazVar.mo3282b(new DoneCallback<D>() { // from class: org.jdeferred.android.c.3
            @Override // p110z1.DoneCallback
            public void onDone(D d) {
                AndroidDeferredObject.this.mo3299a((AndroidDeferredObject) d);
            }
        }).mo3284a(new ProgressCallback<P>() { // from class: org.jdeferred.android.c.2
            @Override // p110z1.ProgressCallback
            /* renamed from: a */
            public void mo3266a(P p) {
                AndroidDeferredObject.this.mo3297c(p);
            }
        }).mo3285a(new FailCallback<F>() { // from class: org.jdeferred.android.c.1
            @Override // p110z1.FailCallback
            public void onFail(F f) {
                AndroidDeferredObject.this.mo3298b((AndroidDeferredObject) f);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AndroidDeferredObject.java */
    /* renamed from: org.jdeferred.android.c$b */
    /* loaded from: classes2.dex */
    public static class HandlerC3264b extends Handler {
        public HandlerC3264b() {
            super(Looper.getMainLooper());
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            C3263a aVar = (C3263a) message.obj;
            switch (message.what) {
                case 1:
                    ((DoneCallback) aVar.f14843b).onDone(aVar.f14844c);
                    return;
                case 2:
                    ((ProgressCallback) aVar.f14843b).mo3266a(aVar.f14846e);
                    return;
                case 3:
                    ((FailCallback) aVar.f14843b).onFail(aVar.f14845d);
                    return;
                case 4:
                    ((AlwaysCallback) aVar.f14843b).m3347a(aVar.f14847f, aVar.f14844c, aVar.f14845d);
                    return;
                default:
                    return;
            }
        }
    }

    @Override // p110z1.AbstractPromise
    /* renamed from: a */
    protected void mo3317a(DoneCallback<D> daqVar, D d) {
        if (m14767d(daqVar) == AndroidExecutionScope.UI) {
            m14768a(1, daqVar, Promise.EnumC5239a.RESOLVED, d, null, null);
        } else {
            super.mo3317a((DoneCallback<DoneCallback<D>>) daqVar, (DoneCallback<D>) d);
        }
    }

    @Override // p110z1.AbstractPromise
    /* renamed from: a */
    protected void mo3316a(FailCallback<F> datVar, F f) {
        if (m14767d(datVar) == AndroidExecutionScope.UI) {
            m14768a(3, datVar, Promise.EnumC5239a.REJECTED, null, f, null);
        } else {
            super.mo3316a((FailCallback<FailCallback<F>>) datVar, (FailCallback<F>) f);
        }
    }

    @Override // p110z1.AbstractPromise
    /* renamed from: a */
    protected void mo3315a(ProgressCallback<P> dawVar, P p) {
        if (m14767d(dawVar) == AndroidExecutionScope.UI) {
            m14768a(2, dawVar, Promise.EnumC5239a.PENDING, null, null, p);
        } else {
            super.mo3315a((ProgressCallback<ProgressCallback<P>>) dawVar, (ProgressCallback<P>) p);
        }
    }

    @Override // p110z1.AbstractPromise
    /* renamed from: a */
    protected void mo3318a(AlwaysCallback<D, F> dakVar, Promise.EnumC5239a aVar, D d, F f) {
        if (m14767d(dakVar) == AndroidExecutionScope.UI) {
            m14768a(4, dakVar, aVar, d, f, null);
        } else {
            super.mo3318a(dakVar, aVar, d, f);
        }
    }

    /* renamed from: a */
    protected <Callback> void m14768a(int i, Callback callback, Promise.EnumC5239a aVar, D d, F f, P p) {
        f14832j.obtainMessage(i, new C3263a(this, callback, aVar, d, f, p)).sendToTarget();
    }

    /* renamed from: d */
    protected AndroidExecutionScope m14767d(Object obj) {
        AndroidExecutionScope a = obj instanceof AndroidExecutionScopeable ? ((AndroidExecutionScopeable) obj).m14766a() : null;
        return a == null ? this.f14838o : a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AndroidDeferredObject.java */
    /* renamed from: org.jdeferred.android.c$a */
    /* loaded from: classes2.dex */
    public static class C3263a<Callback, D, F, P> {

        /* renamed from: a */
        final Deferred f14842a;

        /* renamed from: b */
        final Callback f14843b;

        /* renamed from: c */
        final D f14844c;

        /* renamed from: d */
        final F f14845d;

        /* renamed from: e */
        final P f14846e;

        /* renamed from: f */
        final Promise.EnumC5239a f14847f;

        C3263a(Deferred dalVar, Callback callback, Promise.EnumC5239a aVar, D d, F f, P p) {
            this.f14842a = dalVar;
            this.f14843b = callback;
            this.f14847f = aVar;
            this.f14844c = d;
            this.f14845d = f;
            this.f14846e = p;
        }
    }
}
