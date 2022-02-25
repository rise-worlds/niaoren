package com.p073ta.utdid2.p074a.p075a;

import java.util.regex.Pattern;

/* renamed from: com.ta.utdid2.a.a.g */
/* loaded from: classes2.dex */
public class C2521g {

    /* renamed from: a */
    private static final Pattern f12685a = Pattern.compile("([\t\r\n])+");

    /* renamed from: a */
    public static boolean m17166a(String str) {
        return str == null || str.length() <= 0;
    }

    /* renamed from: a */
    public static int m17167a(String str) {
        if (str.length() <= 0) {
            return 0;
        }
        int i = 0;
        for (char c : str.toCharArray()) {
            i = (i * 31) + c;
        }
        return i;
    }
}
