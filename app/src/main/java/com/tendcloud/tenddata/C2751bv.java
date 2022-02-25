package com.tendcloud.tenddata;

import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.bv */
/* loaded from: classes2.dex */
public class C2751bv {
    /* renamed from: a */
    public static boolean m16198a(ByteBuffer byteBuffer, C2757bz bzVar, ByteChannel byteChannel) {
        byteBuffer.clear();
        int read = byteChannel.read(byteBuffer);
        byteBuffer.flip();
        if (read != -1) {
            return read != 0;
        }
        bzVar.m16190b();
        return false;
    }

    /* renamed from: a */
    public static boolean m16199a(ByteBuffer byteBuffer, C2757bz bzVar, AbstractC2760cb cbVar) {
        byteBuffer.clear();
        int a = cbVar.mo16183a(byteBuffer);
        byteBuffer.flip();
        if (a != -1) {
            return cbVar.mo16181c();
        }
        bzVar.m16190b();
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0051 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean m16200a(com.tendcloud.tenddata.C2757bz r4, java.nio.channels.ByteChannel r5) {
        /*
            java.util.concurrent.BlockingQueue r0 = r4.f13646h
            java.lang.Object r0 = r0.peek()
            java.nio.ByteBuffer r0 = (java.nio.ByteBuffer) r0
            r1 = 0
            if (r0 != 0) goto L_0x001c
            boolean r0 = r5 instanceof com.tendcloud.tenddata.AbstractC2760cb
            if (r0 == 0) goto L_0x0035
            r0 = r5
            com.tendcloud.tenddata.cb r0 = (com.tendcloud.tenddata.AbstractC2760cb) r0
            boolean r2 = r0.mo16184a()
            if (r2 == 0) goto L_0x0036
            r0.mo16182b()
            goto L_0x0036
        L_0x001c:
            r5.write(r0)
            int r0 = r0.remaining()
            if (r0 <= 0) goto L_0x0026
            return r1
        L_0x0026:
            java.util.concurrent.BlockingQueue r0 = r4.f13646h
            r0.poll()
            java.util.concurrent.BlockingQueue r0 = r4.f13646h
            java.lang.Object r0 = r0.peek()
            java.nio.ByteBuffer r0 = (java.nio.ByteBuffer) r0
            if (r0 != 0) goto L_0x001c
        L_0x0035:
            r0 = 0
        L_0x0036:
            java.util.concurrent.BlockingQueue r2 = r4.f13646h
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x0059
            boolean r2 = r4.isFlushAndClose()
            if (r2 == 0) goto L_0x0059
            com.tendcloud.tenddata.cf r2 = r4.getDraft()
            com.tendcloud.tenddata.bw$b r2 = r2.m16170d()
            com.tendcloud.tenddata.bw$b r3 = com.tendcloud.tenddata.AbstractC2752bw.EnumC2754b.SERVER
            if (r2 != r3) goto L_0x0059
            monitor-enter(r4)
            r4.m16197a()     // Catch: all -> 0x0056
            monitor-exit(r4)     // Catch: all -> 0x0056
            goto L_0x0059
        L_0x0056:
            r5 = move-exception
            monitor-exit(r4)     // Catch: all -> 0x0056
            throw r5
        L_0x0059:
            r4 = 1
            if (r0 == 0) goto L_0x0066
            com.tendcloud.tenddata.cb r5 = (com.tendcloud.tenddata.AbstractC2760cb) r5
            boolean r5 = r5.mo16184a()
            if (r5 != 0) goto L_0x0065
            goto L_0x0066
        L_0x0065:
            r4 = 0
        L_0x0066:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tendcloud.tenddata.C2751bv.m16200a(com.tendcloud.tenddata.bz, java.nio.channels.ByteChannel):boolean");
    }
}
