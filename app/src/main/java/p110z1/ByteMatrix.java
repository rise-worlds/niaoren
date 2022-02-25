package p110z1;

import java.lang.reflect.Array;
import java.util.Arrays;

/* renamed from: z1.ny */
/* loaded from: classes3.dex */
public final class ByteMatrix {

    /* renamed from: a */
    final byte[][] f22660a;

    /* renamed from: b */
    public final int f22661b;

    /* renamed from: c */
    public final int f22662c;

    public ByteMatrix(int i, int i2) {
        this.f22660a = (byte[][]) Array.newInstance(byte.class, i2, i);
        this.f22661b = i;
        this.f22662c = i2;
    }

    /* renamed from: a */
    private int m1746a() {
        return this.f22662c;
    }

    /* renamed from: b */
    private int m1741b() {
        return this.f22661b;
    }

    /* renamed from: a */
    public final byte m1745a(int i, int i2) {
        return this.f22660a[i2][i];
    }

    /* renamed from: c */
    private byte[][] m1740c() {
        return this.f22660a;
    }

    /* renamed from: a */
    private void m1744a(int i, int i2, byte b) {
        this.f22660a[i2][i] = b;
    }

    /* renamed from: a */
    public final void m1743a(int i, int i2, int i3) {
        this.f22660a[i2][i] = (byte) i3;
    }

    /* renamed from: a */
    public final void m1742a(int i, int i2, boolean z) {
        this.f22660a[i2][i] = z ? (byte) 1 : (byte) 0;
    }

    /* renamed from: d */
    private void m1739d() {
        for (byte[] bArr : this.f22660a) {
            Arrays.fill(bArr, (byte) -1);
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder((this.f22661b * 2 * this.f22662c) + 2);
        for (int i = 0; i < this.f22662c; i++) {
            byte[] bArr = this.f22660a[i];
            for (int i2 = 0; i2 < this.f22661b; i2++) {
                switch (bArr[i2]) {
                    case 0:
                        sb.append(" 0");
                        break;
                    case 1:
                        sb.append(" 1");
                        break;
                    default:
                        sb.append("  ");
                        break;
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
