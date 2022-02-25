package p110z1;

import org.apache.commons.p105io.FilenameUtils;

/* renamed from: z1.od */
/* loaded from: classes3.dex */
public abstract class LuminanceSource {

    /* renamed from: a */
    public final int f22688a;

    /* renamed from: b */
    public final int f22689b;

    /* renamed from: a */
    public abstract byte[] mo1642a();

    /* renamed from: a */
    public abstract byte[] mo1640a(int i, byte[] bArr);

    /* renamed from: b */
    public boolean mo1639b() {
        return false;
    }

    /* renamed from: c */
    public boolean mo1656c() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public LuminanceSource(int i, int i2) {
        this.f22688a = i;
        this.f22689b = i2;
    }

    /* renamed from: g */
    private int m1652g() {
        return this.f22688a;
    }

    /* renamed from: h */
    private int m1651h() {
        return this.f22689b;
    }

    /* renamed from: a */
    public LuminanceSource mo1641a(int i, int i2, int i3, int i4) {
        throw new UnsupportedOperationException("This luminance source does not support cropping.");
    }

    /* renamed from: d */
    public LuminanceSource mo1655d() {
        return new InvertedLuminanceSource(this);
    }

    /* renamed from: e */
    public LuminanceSource mo1654e() {
        throw new UnsupportedOperationException("This luminance source does not support rotation by 90 degrees.");
    }

    /* renamed from: f */
    public LuminanceSource mo1653f() {
        throw new UnsupportedOperationException("This luminance source does not support rotation by 45 degrees.");
    }

    public final String toString() {
        int i = this.f22688a;
        byte[] bArr = new byte[i];
        StringBuilder sb = new StringBuilder(this.f22689b * (i + 1));
        for (int i2 = 0; i2 < this.f22689b; i2++) {
            bArr = mo1640a(i2, bArr);
            for (int i3 = 0; i3 < this.f22688a; i3++) {
                int i4 = bArr[i3] & 255;
                sb.append(i4 < 64 ? '#' : i4 < 128 ? '+' : i4 < 192 ? FilenameUtils.EXTENSION_SEPARATOR : ' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
