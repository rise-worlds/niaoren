package com.p018b.p019a.p020a.p025e;

/* renamed from: com.b.a.a.e.b */
/* loaded from: classes.dex */
public enum ErrorCode {
    NO_ERROR(0),
    PROTOCOL_ERROR(1),
    INTERNAL_ERROR(2),
    FLOW_CONTROL_ERROR(3),
    REFUSED_STREAM(7),
    CANCEL(8);
    

    /* renamed from: g */
    public final int f5895g;

    ErrorCode(int i) {
        this.f5895g = i;
    }

    /* renamed from: a */
    public static ErrorCode m24665a(int i) {
        ErrorCode[] values;
        for (ErrorCode bVar : values()) {
            if (bVar.f5895g == i) {
                return bVar;
            }
        }
        return null;
    }
}
