package com.p018b.p029b;

import android.support.v4.media.session.PlaybackStateCompat;

/* renamed from: com.b.b.u */
/* loaded from: classes.dex */
final class SegmentPool {

    /* renamed from: a */
    static Segment f6458a;

    /* renamed from: b */
    static long f6459b;

    private SegmentPool() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static Segment m24269a() {
        synchronized (SegmentPool.class) {
            if (f6458a == null) {
                return new Segment();
            }
            Segment tVar = f6458a;
            f6458a = tVar.f6456f;
            tVar.f6456f = null;
            f6459b -= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
            return tVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m24268a(Segment tVar) {
        if (tVar.f6456f != null || tVar.f6457g != null) {
            throw new IllegalArgumentException();
        } else if (!tVar.f6454d) {
            synchronized (SegmentPool.class) {
                if (f6459b + PlaybackStateCompat.ACTION_PLAY_FROM_URI <= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
                    f6459b += PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                    tVar.f6456f = f6458a;
                    tVar.f6453c = 0;
                    tVar.f6452b = 0;
                    f6458a = tVar;
                }
            }
        }
    }
}
