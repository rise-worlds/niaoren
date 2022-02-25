package com.p018b.p019a.p020a.p025e;

import java.io.IOException;

/* renamed from: com.b.a.a.e.am */
/* loaded from: classes.dex */
public final class StreamResetException extends IOException {

    /* renamed from: a */
    public final ErrorCode f5887a;

    public StreamResetException(ErrorCode bVar) {
        super("stream was reset: " + bVar);
        this.f5887a = bVar;
    }
}
