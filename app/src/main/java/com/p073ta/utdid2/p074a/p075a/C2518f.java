package com.p073ta.utdid2.p074a.p075a;

/* renamed from: com.ta.utdid2.a.a.f */
/* loaded from: classes2.dex */
public class C2518f {

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.ta.utdid2.a.a.f$a */
    /* loaded from: classes2.dex */
    public static class C2520a {

        /* renamed from: d */
        public int[] f12682d;

        /* renamed from: x */
        public int f12683x;

        /* renamed from: y */
        public int f12684y;

        private C2520a() {
            this.f12682d = new int[256];
        }
    }

    /* renamed from: a */
    public static byte[] m17169a(byte[] bArr) {
        C2520a a;
        if (bArr == null || (a = m17170a("QrMgt8GGYI6T52ZY5AnhtxkLzb8egpFn3j5JELI8H6wtACbUnZ5cc3aYTsTRbmkAkRJeYbtx92LPBWm7nBO9UIl7y5i5MQNmUZNf5QENurR5tGyo7yJ2G0MBjWvy6iAtlAbacKP0SwOUeUWx5dsBdyhxa7Id1APtybSdDgicBDuNjI0mlZFUzZSS9dmN8lBD0WTVOMz0pRZbR3cysomRXOO1ghqjJdTcyDIxzpNAEszN8RMGjrzyU7Hjbmwi6YNK")) == null) {
            return null;
        }
        return m17168a(bArr, a);
    }

    /* renamed from: a */
    private static C2520a m17170a(String str) {
        if (str == null) {
            return null;
        }
        C2520a aVar = new C2520a();
        for (int i = 0; i < 256; i++) {
            aVar.f12682d[i] = i;
        }
        aVar.f12683x = 0;
        aVar.f12684y = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < 256; i4++) {
            try {
                i3 = ((str.charAt(i2) + aVar.f12682d[i4]) + i3) % 256;
                int i5 = aVar.f12682d[i4];
                aVar.f12682d[i4] = aVar.f12682d[i3];
                aVar.f12682d[i3] = i5;
                i2 = (i2 + 1) % str.length();
            } catch (Exception unused) {
                return null;
            }
        }
        return aVar;
    }

    /* renamed from: a */
    private static byte[] m17168a(byte[] bArr, C2520a aVar) {
        if (bArr == null || aVar == null) {
            return null;
        }
        int i = aVar.f12683x;
        int i2 = aVar.f12684y;
        for (int i3 = 0; i3 < bArr.length; i3++) {
            i = (i + 1) % 256;
            i2 = (aVar.f12682d[i] + i2) % 256;
            int i4 = aVar.f12682d[i];
            aVar.f12682d[i] = aVar.f12682d[i2];
            aVar.f12682d[i2] = i4;
            bArr[i3] = (byte) (aVar.f12682d[(aVar.f12682d[i] + aVar.f12682d[i2]) % 256] ^ bArr[i3]);
        }
        aVar.f12683x = i;
        aVar.f12684y = i2;
        return bArr;
    }
}
