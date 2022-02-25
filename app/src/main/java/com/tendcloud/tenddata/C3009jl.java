package com.tendcloud.tenddata;

import java.io.IOException;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.jl */
/* loaded from: classes2.dex */
public class C3009jl extends IOException {

    /* renamed from: a */
    private static final long f14329a = -1616151763072450476L;

    public C3009jl(String str) {
        super(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static C3009jl m15267a() {
        return new C3009jl("While parsing a protocol message, the input ended unexpectedly in the middle of a field. This could mean either than the input has been truncated or that an embedded message misreported its own length.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static C3009jl m15266b() {
        return new C3009jl("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public static C3009jl m15265c() {
        return new C3009jl("CodedInputStream encountered a malformed varint.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public static C3009jl m15264d() {
        return new C3009jl("Protocol message contained an invalid tag (zero).");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public static C3009jl m15263e() {
        return new C3009jl("Protocol message end-group tag did not match expected tag.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: f */
    public static C3009jl m15262f() {
        return new C3009jl("Protocol message tag had invalid wire type.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: g */
    public static C3009jl m15261g() {
        return new C3009jl("Protocol message had too many levels of nesting. May be malicious. Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }
}
