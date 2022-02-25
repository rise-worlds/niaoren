package com.p007a.p008a.p009a;

import android.support.annotation.NonNull;
import java.util.List;

/* renamed from: com.a.a.a.a */
/* loaded from: classes.dex */
public final class CommandResult {
    @NonNull

    /* renamed from: a */
    public final List<String> f111a;
    @NonNull

    /* renamed from: b */
    public final List<String> f112b;

    /* renamed from: c */
    public final int f113c;

    public CommandResult(@NonNull List<String> list, @NonNull List<String> list2, int i) {
        this.f111a = list;
        this.f112b = list2;
        this.f113c = i;
    }

    /* renamed from: a */
    public final String m25526a() {
        List<String> list = this.f111a;
        StringBuilder sb = new StringBuilder();
        if (list != null) {
            String str = "";
            for (String str2 : list) {
                sb.append(str);
                sb.append(str2);
                str = "\n";
            }
        }
        return sb.toString();
    }

    public final String toString() {
        return m25526a();
    }
}
