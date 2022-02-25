package com.nrzs.data.p065en;

import android.content.Context;

/* renamed from: com.nrzs.data.en.Ufc */
/* loaded from: classes2.dex */
public class Ufc {
    private native String native_encrypt(Abcd abcd, Context context);

    public static Ufc getInstance() {
        return new Ufc();
    }

    public String y11(Abcd abcd, Context context) {
        return native_encrypt(abcd, context);
    }

    static {
        System.loadLibrary("ufc");
    }
}
