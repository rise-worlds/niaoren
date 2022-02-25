package com.tendcloud.tenddata;

import android.os.Process;
import java.util.Properties;
import java.util.zip.CRC32;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ih */
/* loaded from: classes2.dex */
public class C2970ih extends Properties implements Comparable {

    /* renamed from: a */
    private String f14244a;

    /* renamed from: b */
    private byte[] f14245b;

    /* renamed from: c */
    private int f14246c;

    /* renamed from: d */
    private int f14247d;

    /* renamed from: e */
    private CRC32 f14248e;

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.ih$a */
    /* loaded from: classes2.dex */
    public static final class C2971a {
        public static final String DATA = "data";
        public static final String LENGTH = "length";
        public static final String RCS32 = "rcs32";
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.ih$b */
    /* loaded from: classes2.dex */
    public static final class C2972b {
        public static final String TYPE_ANTICHEATING = "Type";
    }

    public C2970ih(String str) {
        this.f14244a = str;
    }

    public C2970ih(byte[] bArr) {
        this(m15441a(), bArr);
    }

    private C2970ih(String str, byte[] bArr) {
        this(str);
        this.f14248e = new CRC32();
        writeData(bArr);
    }

    /* renamed from: a */
    public static String m15441a() {
        return System.currentTimeMillis() + "_" + Long.toString(Process.myPid());
    }

    /* renamed from: b */
    public String m15435b() {
        return this.f14244a;
    }

    /* renamed from: c */
    public byte[] m15433c() {
        return this.f14245b;
    }

    /* renamed from: d */
    public int m15431d() {
        return this.f14246c;
    }

    /* renamed from: e */
    public int m15430e() {
        return this.f14247d;
    }

    /* renamed from: a */
    public int m15438a(String str, int i) {
        String str2 = (String) setProperty(str, String.valueOf(i));
        if (str2 == null) {
            return 0;
        }
        return Integer.parseInt(str2);
    }

    /* renamed from: a */
    public byte[] m15437a(String str, byte[] bArr) {
        String str2 = (String) setProperty(str, m15436a(bArr));
        if (str2 == null) {
            return null;
        }
        return m15432c(str2);
    }

    /* renamed from: a */
    public int m15439a(String str) {
        return Integer.parseInt(super.getProperty(str));
    }

    /* renamed from: b */
    public byte[] m15434b(String str) {
        return m15432c(super.getProperty(str));
    }

    public final void writeData(byte[] bArr) {
        if (bArr != null) {
            if (this.f14248e == null) {
                this.f14248e = new CRC32();
            }
            this.f14245b = new byte[bArr.length];
            System.arraycopy(bArr, 0, this.f14245b, 0, bArr.length);
            this.f14247d = this.f14245b.length;
            this.f14248e.reset();
            this.f14248e.update(this.f14245b);
            this.f14246c = (int) this.f14248e.getValue();
        }
    }

    /* renamed from: c */
    public byte[] m15432c(String str) {
        if (str != null) {
            return str.getBytes();
        }
        return null;
    }

    /* renamed from: a */
    public String m15436a(byte[] bArr) {
        return new String(bArr);
    }

    /* renamed from: a */
    public int compareTo(C2970ih ihVar) {
        return m15435b().compareTo(ihVar.m15435b());
    }
}
