package com.cyjh.mobileanjian.ipc.uip;

import android.os.Handler;
import android.os.Process;
import com.google.protobuf.ByteString;
import java.util.concurrent.ArrayBlockingQueue;

/* loaded from: classes.dex */
public class UipEventStub {

    /* renamed from: a */
    private static Handler f8616a;

    /* renamed from: b */
    private static ArrayBlockingQueue<ByteString> f8617b = new ArrayBlockingQueue<>(1);

    /* renamed from: c */
    private static ArrayBlockingQueue<ByteString> f8618c = new ArrayBlockingQueue<>(256);

    public static void registerHandler(Handler handler) {
        f8616a = handler;
    }

    /* renamed from: a */
    private static boolean m20717a() {
        return (Process.myUid() == 0 || Process.myUid() == 2000) ? false : true;
    }

    public static byte[] SendUiRequest(byte[] bArr) {
        if (!m20717a()) {
            return null;
        }
        f8616a.obtainMessage(0, ByteString.copyFrom(bArr)).sendToTarget();
        try {
            return f8617b.take().toByteArray();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void UiRequestReturn(ByteString byteString) {
        try {
            f8617b.put(byteString);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static byte[] GetUiEvent() {
        if (!m20717a()) {
            return null;
        }
        try {
            return f8618c.take().toByteArray();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void hasEvent(ByteString byteString) {
        try {
            if (f8618c.size() < 256) {
                f8618c.put(byteString);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void clear() {
        f8618c.clear();
    }
}
