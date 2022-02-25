package com.p018b.p029b;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/* renamed from: com.b.b.i */
/* loaded from: classes.dex */
public class ByteString implements Serializable, Comparable<ByteString> {

    /* renamed from: a */
    static final char[] f6423a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* renamed from: b */
    public static final ByteString f6424b = m24314a(new byte[0]);

    /* renamed from: c */
    final byte[] f6425c;

    /* renamed from: d */
    transient int f6426d;

    /* renamed from: e */
    transient String f6427e;

    @Override // java.lang.Comparable
    public /* synthetic */ int compareTo(ByteString iVar) {
        ByteString iVar2 = iVar;
        int g = mo24255g();
        int g2 = iVar2.mo24255g();
        int min = Math.min(g, g2);
        for (int i = 0; i < min; i++) {
            int a = mo24266a(i) & 255;
            int a2 = iVar2.mo24266a(i) & 255;
            if (a != a2) {
                return a < a2 ? -1 : 1;
            }
        }
        if (g == g2) {
            return 0;
        }
        return g < g2 ? -1 : 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteString(byte[] bArr) {
        this.f6425c = bArr;
    }

    /* renamed from: a */
    public static ByteString m24314a(byte... bArr) {
        if (bArr != null) {
            return new ByteString((byte[]) bArr.clone());
        }
        throw new IllegalArgumentException("data == null");
    }

    /* renamed from: a */
    public static ByteString m24315a(String str) {
        if (str != null) {
            ByteString iVar = new ByteString(str.getBytes(C0916aa.f6413a));
            iVar.f6427e = str;
            return iVar;
        }
        throw new IllegalArgumentException("s == null");
    }

    /* renamed from: a */
    public String mo24267a() {
        String str = this.f6427e;
        if (str != null) {
            return str;
        }
        String str2 = new String(this.f6425c, C0916aa.f6413a);
        this.f6427e = str2;
        return str2;
    }

    /* renamed from: b */
    public String mo24261b() {
        return Base64.m24343a(this.f6425c);
    }

    /* renamed from: c */
    public ByteString mo24259c() {
        return m24312c("SHA-1");
    }

    /* renamed from: d */
    public ByteString mo24258d() {
        return m24312c("SHA-256");
    }

    /* renamed from: c */
    private ByteString m24312c(String str) {
        try {
            return m24314a(MessageDigest.getInstance(str).digest(this.f6425c));
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: e */
    public String mo24257e() {
        byte[] bArr = this.f6425c;
        char[] cArr = new char[bArr.length * 2];
        int i = 0;
        for (byte b : bArr) {
            int i2 = i + 1;
            char[] cArr2 = f6423a;
            cArr[i] = cArr2[(b >> 4) & 15];
            i = i2 + 1;
            cArr[i2] = cArr2[b & 15];
        }
        return new String(cArr);
    }

    /* renamed from: b */
    public static ByteString m24313b(String str) {
        if (str.length() % 2 == 0) {
            byte[] bArr = new byte[str.length() / 2];
            for (int i = 0; i < bArr.length; i++) {
                int i2 = i * 2;
                bArr[i] = (byte) ((m24316a(str.charAt(i2)) << 4) + m24316a(str.charAt(i2 + 1)));
            }
            return m24314a(bArr);
        }
        throw new IllegalArgumentException("Unexpected hex string: " + str);
    }

    /* renamed from: a */
    private static int m24316a(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'a' && c <= 'f') {
            return (c - 'a') + 10;
        }
        if (c >= 'A' && c <= 'F') {
            return (c - 'A') + 10;
        }
        throw new IllegalArgumentException("Unexpected hex digit: " + c);
    }

    /* renamed from: f */
    public ByteString mo24256f() {
        int i = 0;
        while (true) {
            byte[] bArr = this.f6425c;
            if (i >= bArr.length) {
                return this;
            }
            byte b = bArr[i];
            if (b < 65 || b > 90) {
                i++;
            } else {
                byte[] bArr2 = (byte[]) bArr.clone();
                bArr2[i] = (byte) (b + 32);
                for (int i2 = i + 1; i2 < bArr2.length; i2++) {
                    byte b2 = bArr2[i2];
                    if (b2 >= 65 && b2 <= 90) {
                        bArr2[i2] = (byte) (b2 + 32);
                    }
                }
                return new ByteString(bArr2);
            }
        }
    }

    /* renamed from: a */
    public ByteString mo24265a(int i, int i2) {
        if (i >= 0) {
            byte[] bArr = this.f6425c;
            if (i2 <= bArr.length) {
                int i3 = i2 - i;
                if (i3 < 0) {
                    throw new IllegalArgumentException("endIndex < beginIndex");
                } else if (i == 0 && i2 == bArr.length) {
                    return this;
                } else {
                    byte[] bArr2 = new byte[i3];
                    System.arraycopy(this.f6425c, i, bArr2, 0, i3);
                    return new ByteString(bArr2);
                }
            } else {
                throw new IllegalArgumentException("endIndex > length(" + this.f6425c.length + ")");
            }
        } else {
            throw new IllegalArgumentException("beginIndex < 0");
        }
    }

    /* renamed from: a */
    public byte mo24266a(int i) {
        return this.f6425c[i];
    }

    /* renamed from: g */
    public int mo24255g() {
        return this.f6425c.length;
    }

    /* renamed from: h */
    public byte[] mo24254h() {
        return (byte[]) this.f6425c.clone();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo24262a(Buffer fVar) {
        byte[] bArr = this.f6425c;
        fVar.mo24296b(bArr, 0, bArr.length);
    }

    /* renamed from: a */
    public boolean mo24264a(int i, ByteString iVar, int i2, int i3) {
        return iVar.mo24263a(0, this.f6425c, 0, i3);
    }

    /* renamed from: a */
    public boolean mo24263a(int i, byte[] bArr, int i2, int i3) {
        if (i < 0) {
            return false;
        }
        byte[] bArr2 = this.f6425c;
        return i <= bArr2.length - i3 && i2 >= 0 && i2 <= bArr.length - i3 && C0916aa.m24344a(bArr2, i, bArr, i2, i3);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString iVar = (ByteString) obj;
            int g = iVar.mo24255g();
            byte[] bArr = this.f6425c;
            if (g == bArr.length && iVar.mo24263a(0, bArr, 0, bArr.length)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = this.f6426d;
        if (i != 0) {
            return i;
        }
        int hashCode = Arrays.hashCode(this.f6425c);
        this.f6426d = hashCode;
        return hashCode;
    }

    public String toString() {
        StringBuilder sb;
        String str;
        if (this.f6425c.length == 0) {
            return "[size=0]";
        }
        String a = mo24267a();
        int length = a.length();
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i >= length) {
                i = a.length();
                break;
            } else if (i2 == 64) {
                break;
            } else {
                int codePointAt = a.codePointAt(i);
                if ((!Character.isISOControl(codePointAt) || codePointAt == 10 || codePointAt == 13) && codePointAt != 65533) {
                    i2++;
                    i += Character.charCount(codePointAt);
                }
            }
        }
        i = -1;
        if (i != -1) {
            String replace = a.substring(0, i).replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r");
            if (i < a.length()) {
                sb = new StringBuilder("[size=");
                sb.append(this.f6425c.length);
                sb.append(" text=");
                sb.append(replace);
                str = "…]";
            } else {
                sb = new StringBuilder("[text=");
                sb.append(replace);
                str = "]";
            }
            sb.append(str);
            return sb.toString();
        } else if (this.f6425c.length <= 64) {
            return "[hex=" + mo24257e() + "]";
        } else {
            return "[size=" + this.f6425c.length + " hex=" + mo24265a(0, 64).mo24257e() + "…]";
        }
    }
}
