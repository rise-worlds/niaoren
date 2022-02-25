package com.nrzs.libcommon.util.share;

import android.os.Bundle;
import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* renamed from: com.nrzs.libcommon.util.share.a */
/* loaded from: classes2.dex */
class OpEntry {

    /* renamed from: a */
    static final int f11190a = 1;

    /* renamed from: b */
    static final int f11191b = 2;

    /* renamed from: c */
    static final int f11192c = 3;

    /* renamed from: d */
    static final int f11193d = 4;

    /* renamed from: e */
    static final int f11194e = 5;

    /* renamed from: f */
    static final int f11195f = 6;

    /* renamed from: g */
    static final int f11196g = 1;

    /* renamed from: h */
    static final int f11197h = 2;

    /* renamed from: i */
    static final int f11198i = 3;

    /* renamed from: j */
    static final int f11199j = 4;

    /* renamed from: k */
    static final int f11200k = 5;

    /* renamed from: l */
    static final int f11201l = 6;

    /* renamed from: m */
    static final String f11202m = "key_key";

    /* renamed from: n */
    static final String f11203n = "key_value";

    /* renamed from: o */
    static final String f11204o = "key_value_type";

    /* renamed from: p */
    static final String f11205p = "key_op_type";
    @NonNull

    /* renamed from: q */
    private Bundle f11206q;

    private OpEntry() {
        this.f11206q = new Bundle();
    }

    public OpEntry(@NonNull Bundle bundle) {
        this.f11206q = bundle;
    }

    /* renamed from: a */
    public String m18540a() {
        return this.f11206q.getString(f11202m, null);
    }

    /* renamed from: a */
    public OpEntry m18536a(String str) {
        this.f11206q.putString(f11202m, str);
        return this;
    }

    /* renamed from: b */
    public int m18533b() {
        return this.f11206q.getInt(f11204o, 0);
    }

    /* renamed from: a */
    public OpEntry m18538a(int i) {
        this.f11206q.putInt(f11204o, i);
        return this;
    }

    /* renamed from: c */
    public int m18527c() {
        return this.f11206q.getInt(f11205p, 0);
    }

    /* renamed from: b */
    public OpEntry m18531b(int i) {
        this.f11206q.putInt(f11205p, i);
        return this;
    }

    /* renamed from: b */
    public String m18529b(String str) {
        return this.f11206q.getString(f11203n, str);
    }

    /* renamed from: c */
    public OpEntry m18525c(String str) {
        this.f11206q.putInt(f11204o, 1);
        this.f11206q.putString(f11203n, str);
        return this;
    }

    /* renamed from: c */
    public int m18526c(int i) {
        return this.f11206q.getInt(f11203n, i);
    }

    /* renamed from: d */
    public OpEntry m18523d(int i) {
        this.f11206q.putInt(f11204o, 2);
        this.f11206q.putInt(f11203n, i);
        return this;
    }

    /* renamed from: a */
    public long m18537a(long j) {
        return this.f11206q.getLong(f11203n, j);
    }

    /* renamed from: b */
    public OpEntry m18530b(long j) {
        this.f11206q.putInt(f11204o, 3);
        this.f11206q.putLong(f11203n, j);
        return this;
    }

    /* renamed from: a */
    public float m18539a(float f) {
        return this.f11206q.getFloat(f11203n);
    }

    /* renamed from: b */
    public OpEntry m18532b(float f) {
        this.f11206q.putInt(f11204o, 4);
        this.f11206q.putFloat(f11203n, f);
        return this;
    }

    /* renamed from: a */
    public boolean m18534a(boolean z) {
        return this.f11206q.getBoolean(f11203n, z);
    }

    /* renamed from: b */
    public OpEntry m18528b(boolean z) {
        this.f11206q.putInt(f11204o, 5);
        this.f11206q.putBoolean(f11203n, z);
        return this;
    }

    /* renamed from: d */
    public Set<String> m18524d() {
        ArrayList<String> stringArrayList = this.f11206q.getStringArrayList(f11203n);
        if (stringArrayList == null) {
            return null;
        }
        return new HashSet(stringArrayList);
    }

    /* renamed from: e */
    public Bundle m18521e() {
        return this.f11206q;
    }

    /* renamed from: a */
    public OpEntry m18535a(Set<String> set) {
        this.f11206q.putInt(f11204o, 6);
        this.f11206q.putStringArrayList(f11203n, set == null ? null : new ArrayList<>(set));
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public static OpEntry m18522d(String str) {
        return new OpEntry().m18536a(str).m18531b(1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public static OpEntry m18520e(String str) {
        return new OpEntry().m18536a(str).m18531b(2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: f */
    public static OpEntry m18518f(String str) {
        return new OpEntry().m18536a(str).m18531b(4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: f */
    public static OpEntry m18519f() {
        return new OpEntry().m18531b(3);
    }
}
