package com.p018b.p019a.p020a.p025e;

import com.p018b.p019a.p020a.Util;
import com.p018b.p029b.ByteString;
import java.io.IOException;

/* renamed from: com.b.a.a.e.g */
/* loaded from: classes.dex */
public final class Http2 {

    /* renamed from: a */
    static final ByteString f5925a = ByteString.m24315a("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");

    /* renamed from: d */
    private static final String[] f5928d = {"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};

    /* renamed from: b */
    static final String[] f5926b = new String[64];

    /* renamed from: c */
    static final String[] f5927c = new String[256];

    static {
        int i;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            String[] strArr = f5927c;
            if (i3 >= strArr.length) {
                break;
            }
            strArr[i3] = Util.m24755a("%8s", Integer.toBinaryString(i3)).replace(' ', '0');
            i3++;
        }
        String[] strArr2 = f5926b;
        strArr2[0] = "";
        strArr2[1] = "END_STREAM";
        int[] iArr = {1};
        strArr2[8] = "PADDED";
        for (int i4 = 0; i4 <= 0; i4++) {
            f5926b[iArr[i4] | 8] = f5926b[i] + "|PADDED";
        }
        String[] strArr3 = f5926b;
        strArr3[4] = "END_HEADERS";
        strArr3[32] = "PRIORITY";
        strArr3[36] = "END_HEADERS|PRIORITY";
        int[] iArr2 = {4, 32, 36};
        for (int i5 = 0; i5 < 3; i5++) {
            int i6 = iArr2[i5];
            for (int i7 = 0; i7 <= 0; i7++) {
                int i8 = iArr[i7];
                int i9 = i8 | i6;
                f5926b[i9] = f5926b[i8] + '|' + f5926b[i6];
                f5926b[i9 | 8] = f5926b[i8] + '|' + f5926b[i6] + "|PADDED";
            }
        }
        while (true) {
            String[] strArr4 = f5926b;
            if (i2 < strArr4.length) {
                if (strArr4[i2] == null) {
                    strArr4[i2] = f5927c[i2];
                }
                i2++;
            } else {
                return;
            }
        }
    }

    private Http2() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static IllegalArgumentException m24644a(String str, Object... objArr) {
        throw new IllegalArgumentException(Util.m24755a(str, objArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static IOException m24642b(String str, Object... objArr) {
        throw new IOException(Util.m24755a(str, objArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m24643a(boolean z, int i, int i2, byte b, byte b2) {
        String str;
        String[] strArr = f5928d;
        String a = b < strArr.length ? strArr[b] : Util.m24755a("0x%02x", Byte.valueOf(b));
        if (b2 != 0) {
            switch (b) {
                case 2:
                case 3:
                case 7:
                case 8:
                    str = f5927c[b2];
                    break;
                case 4:
                case 6:
                    if (b2 != 1) {
                        str = f5927c[b2];
                        break;
                    } else {
                        str = "ACK";
                        break;
                    }
                case 5:
                default:
                    String[] strArr2 = f5926b;
                    String str2 = b2 < strArr2.length ? strArr2[b2] : f5927c[b2];
                    if (b != 5 || (b2 & 4) == 0) {
                        if (b == 0 && (b2 & 32) != 0) {
                            str = str2.replace("PRIORITY", "COMPRESSED");
                            break;
                        } else {
                            str = str2;
                            break;
                        }
                    } else {
                        str = str2.replace("HEADERS", "PUSH_PROMISE");
                        break;
                    }
            }
        } else {
            str = "";
        }
        Object[] objArr = new Object[5];
        objArr[0] = z ? "<<" : ">>";
        objArr[1] = Integer.valueOf(i);
        objArr[2] = Integer.valueOf(i2);
        objArr[3] = a;
        objArr[4] = str;
        return Util.m24755a("%s 0x%08x %5d %-13s %s", objArr);
    }
}
