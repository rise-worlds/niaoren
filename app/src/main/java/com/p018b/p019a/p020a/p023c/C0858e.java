package com.p018b.p019a.p020a.p023c;

import com.p018b.p019a.p020a.Util;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* compiled from: HttpDate.java */
/* renamed from: com.b.a.a.c.e */
/* loaded from: classes.dex */
final class C0858e extends ThreadLocal<DateFormat> {
    @Override // java.lang.ThreadLocal
    protected final /* synthetic */ DateFormat initialValue() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        simpleDateFormat.setLenient(false);
        simpleDateFormat.setTimeZone(Util.f5782f);
        return simpleDateFormat;
    }
}
