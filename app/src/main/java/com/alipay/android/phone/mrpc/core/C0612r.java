package com.alipay.android.phone.mrpc.core;

import java.io.Closeable;
import java.io.IOException;

/* renamed from: com.alipay.android.phone.mrpc.core.r */
/* loaded from: classes.dex */
public final class C0612r {
    /* renamed from: a */
    public static void m25453a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
