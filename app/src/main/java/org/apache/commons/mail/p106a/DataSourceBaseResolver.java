package org.apache.commons.mail.p106a;

import org.apache.commons.mail.DataSourceResolver;

/* renamed from: org.apache.commons.mail.a.a */
/* loaded from: classes2.dex */
public abstract class DataSourceBaseResolver implements DataSourceResolver {

    /* renamed from: a */
    final boolean f14711a;

    public DataSourceBaseResolver() {
        this.f14711a = false;
    }

    public DataSourceBaseResolver(boolean z) {
        this.f14711a = z;
    }

    /* renamed from: a */
    private boolean m14863a() {
        return this.f14711a;
    }

    /* renamed from: b */
    private static boolean m14861b(String str) {
        return str.startsWith("cid:");
    }

    /* renamed from: c */
    private static boolean m14860c(String str) {
        return str.startsWith("file:/");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static boolean m14862a(String str) {
        return str.startsWith("http://") || str.startsWith("https://");
    }
}
