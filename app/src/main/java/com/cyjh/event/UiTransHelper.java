package com.cyjh.event;

import com.google.protobuf.ByteString;
import java.util.concurrent.ArrayBlockingQueue;

/* renamed from: com.cyjh.event.c */
/* loaded from: classes.dex */
public final class UiTransHelper {

    /* renamed from: a */
    private static ArrayBlockingQueue<ByteString> f8188a = new ArrayBlockingQueue<>(16);

    /* renamed from: b */
    private static ArrayBlockingQueue<ByteString> f8189b = new ArrayBlockingQueue<>(1024);

    /* renamed from: c */
    private static ArrayBlockingQueue<ByteString> f8190c = new ArrayBlockingQueue<>(1024);

    /* renamed from: a */
    public static byte[] m21094a() {
        try {
            return f8188a.take().toByteArray();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static void m21093a(ByteString byteString) {
        try {
            f8188a.put(byteString);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    public static byte[] m21092b() {
        try {
            return f8189b.take().toByteArray();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public static void m21091b(ByteString byteString) {
        try {
            f8189b.put(byteString);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: c */
    public static void m21090c() {
        f8189b.clear();
        f8190c.clear();
    }

    /* renamed from: d */
    public static ByteString m21088d() {
        try {
            return f8190c.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: c */
    private static void m21089c(ByteString byteString) {
        try {
            f8190c.put(byteString);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: e */
    public static void m21087e() {
        try {
            f8190c.put(ByteString.copyFrom("floatEventThreadExit".getBytes()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: f */
    private static void m21086f() {
        f8190c.clear();
    }
}
