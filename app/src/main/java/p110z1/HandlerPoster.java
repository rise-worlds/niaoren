package p110z1;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;

/* renamed from: z1.czi */
/* loaded from: classes3.dex */
public class HandlerPoster extends Handler implements Poster {

    /* renamed from: a */
    private final PendingPostQueue f21157a = new PendingPostQueue();

    /* renamed from: b */
    private final int f21158b;

    /* renamed from: c */
    private final EventBus f21159c;

    /* renamed from: d */
    private boolean f21160d;

    /* JADX INFO: Access modifiers changed from: protected */
    public HandlerPoster(EventBus czfVar, Looper looper, int i) {
        super(looper);
        this.f21159c = czfVar;
        this.f21158b = i;
    }

    @Override // p110z1.Poster
    /* renamed from: a */
    public void mo3390a(Subscription cztVar, Object obj) {
        PendingPost a = PendingPost.m3394a(cztVar, obj);
        synchronized (this) {
            this.f21157a.m3391a(a);
            if (!this.f21160d) {
                this.f21160d = true;
                if (!sendMessage(obtainMessage())) {
                    throw new EventBusException("Could not send handler message");
                }
            }
        }
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        boolean z = false;
        try {
            long uptimeMillis = SystemClock.uptimeMillis();
            do {
                PendingPost a = this.f21157a.m3393a();
                if (a == null) {
                    synchronized (this) {
                        a = this.f21157a.m3393a();
                        if (a == null) {
                            this.f21160d = z;
                            return;
                        }
                    }
                }
                this.f21159c.m3440a(a);
            } while (SystemClock.uptimeMillis() - uptimeMillis < this.f21158b);
            if (sendMessage(obtainMessage())) {
                z = true;
                return;
            }
            throw new EventBusException("Could not send handler message");
        } finally {
            this.f21160d = z;
        }
    }
}
