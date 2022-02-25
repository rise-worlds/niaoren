package com.tencent.smtt.export.external.interfaces;

/* loaded from: classes2.dex */
public abstract class QuicException extends NetworkException {
    public abstract int getQuicDetailedErrorCode();

    protected QuicException(String str, Throwable th) {
        super(str, th);
    }
}
