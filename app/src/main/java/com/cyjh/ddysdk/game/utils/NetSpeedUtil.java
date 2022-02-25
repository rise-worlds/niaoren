package com.cyjh.ddysdk.game.utils;

import com.cyjh.ddy.base.utils.CLog;
import java.net.Socket;

/* loaded from: classes.dex */
public class NetSpeedUtil {

    /* renamed from: a */
    public static final String f8039a = "NetSpeedUtil";

    /* renamed from: b */
    private volatile boolean f8040b;

    /* renamed from: c */
    private Socket f8041c;

    /* renamed from: d */
    private long f8042d;

    /* renamed from: e */
    private long f8043e;

    /* renamed from: f */
    private long f8044f;

    /* loaded from: classes.dex */
    public interface INetCallback {
        void onResult(boolean z, long j, long j2);
    }

    private NetSpeedUtil() {
    }

    /* loaded from: classes.dex */
    private static class LazyHolder {

        /* renamed from: a */
        private static final NetSpeedUtil f8051a = new NetSpeedUtil();

        private LazyHolder() {
        }
    }

    /* renamed from: a */
    public static NetSpeedUtil m21162a() {
        return LazyHolder.f8051a;
    }

    /* renamed from: b */
    public void m21156b() {
        this.f8040b = false;
    }

    /* renamed from: a */
    public void m21157a(final String str, final int i, int i2, final boolean z, final INetCallback iNetCallback) {
        if (!this.f8040b) {
            this.f8040b = true;
            final int i3 = i2 > 5120 ? 5120 : i2 < 1 ? 1 : i2;
            new Thread(new Runnable() { // from class: com.cyjh.ddysdk.game.utils.NetSpeedUtil.1
                /* JADX WARN: Removed duplicated region for block: B:50:0x01e3 A[Catch: IOException -> 0x01df, TryCatch #4 {IOException -> 0x01df, blocks: (B:46:0x01db, B:50:0x01e3, B:51:0x01e6), top: B:57:0x01db }] */
                /* JADX WARN: Removed duplicated region for block: B:57:0x01db A[EXC_TOP_SPLITTER, SYNTHETIC] */
                @Override // java.lang.Runnable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public void run() {
                    /*
                        Method dump skipped, instructions count: 500
                        To view this dump change 'Code comments level' option to 'DEBUG'
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.cyjh.ddysdk.game.utils.NetSpeedUtil.RunnableC12391.run():void");
                }
            }).start();
            return;
        }
        iNetCallback.onResult(z, 0L, this.f8042d);
        CLog.m21882i(f8039a, "稍等，已有任务运行中");
    }
}
