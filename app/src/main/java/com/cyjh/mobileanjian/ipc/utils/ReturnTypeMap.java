package com.cyjh.mobileanjian.ipc.utils;

import com.cyjh.mobileanjian.ipc.share.proto.Ipc;
import java.util.HashMap;

/* renamed from: com.cyjh.mobileanjian.ipc.utils.l */
/* loaded from: classes.dex */
public final class ReturnTypeMap {

    /* renamed from: a */
    private static final HashMap<String, Ipc.FundType> f8690a = new HashMap<String, Ipc.FundType>() { // from class: com.cyjh.mobileanjian.ipc.utils.l.1
        {
            put("void", Ipc.FundType.VOID);
            put("boolean", Ipc.FundType.BOOLEAN);
            put("int", Ipc.FundType.INT);
            put("long", Ipc.FundType.LONG);
            put("float", Ipc.FundType.FLOAT);
            put("double", Ipc.FundType.DOUBLE);
            put("String", Ipc.FundType.STRING);
        }
    };

    /* renamed from: a */
    public static Ipc.FundType m20624a(String str) {
        if (C1340p.m20620a(str)) {
            return Ipc.FundType.VOID;
        }
        return f8690a.get(str);
    }
}
