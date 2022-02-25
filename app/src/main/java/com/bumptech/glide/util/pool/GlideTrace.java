package com.bumptech.glide.util.pool;

import com.tencent.smtt.sdk.TbsListener;

/* loaded from: classes.dex */
public final class GlideTrace {
    private static final int MAX_LENGTH = 127;
    private static final boolean TRACING_ENABLED = false;

    public static void beginSection(String str) {
    }

    public static void beginSectionFormat(String str, Object obj) {
    }

    public static void beginSectionFormat(String str, Object obj, Object obj2) {
    }

    public static void beginSectionFormat(String str, Object obj, Object obj2, Object obj3) {
    }

    public static void endSection() {
    }

    private GlideTrace() {
    }

    private static String truncateTag(String str) {
        return str.length() > MAX_LENGTH ? str.substring(0, TbsListener.ErrorCode.PV_UPLOAD_ERROR) : str;
    }
}
