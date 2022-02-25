package com.p018b.p029b;

/* renamed from: com.b.b.g */
/* loaded from: classes.dex */
public interface BufferedSink extends Sink {
    /* renamed from: b */
    BufferedSink mo24298b(String str);

    /* renamed from: b */
    BufferedSink mo24297b(byte[] bArr);

    /* renamed from: b */
    BufferedSink mo24296b(byte[] bArr, int i, int i2);

    /* renamed from: c */
    Buffer mo24284c();

    /* renamed from: f */
    BufferedSink mo24295f(int i);

    @Override // com.p018b.p029b.Sink, java.io.Flushable
    void flush();

    /* renamed from: g */
    BufferedSink mo24294g(int i);

    /* renamed from: h */
    BufferedSink mo24293h(int i);

    /* renamed from: i */
    BufferedSink mo24292i(long j);

    /* renamed from: j */
    BufferedSink mo24291j(long j);

    /* renamed from: p */
    BufferedSink mo24290p();
}
