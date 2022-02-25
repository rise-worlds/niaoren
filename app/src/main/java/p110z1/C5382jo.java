package p110z1;

import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.tencent.smtt.sdk.TbsListener;
import com.tendcloud.tenddata.C2771ci;
import java.text.DecimalFormat;
import org.apache.tools.tar.TarConstants;

/* compiled from: DecodedBitStreamParser.java */
/* renamed from: z1.jo */
/* loaded from: classes3.dex */
final class C5382jo {

    /* renamed from: a */
    private static final char f22145a = 65520;

    /* renamed from: b */
    private static final char f22146b = 65521;

    /* renamed from: c */
    private static final char f22147c = 65522;

    /* renamed from: d */
    private static final char f22148d = 65523;

    /* renamed from: e */
    private static final char f22149e = 65524;

    /* renamed from: f */
    private static final char f22150f = 65525;

    /* renamed from: g */
    private static final char f22151g = 65526;

    /* renamed from: h */
    private static final char f22152h = 65527;

    /* renamed from: i */
    private static final char f22153i = 65528;

    /* renamed from: j */
    private static final char f22154j = 65529;

    /* renamed from: k */
    private static final char f22155k = 65530;

    /* renamed from: l */
    private static final char f22156l = 65531;

    /* renamed from: m */
    private static final char f22157m = 65532;

    /* renamed from: n */
    private static final char f22158n = 28;

    /* renamed from: o */
    private static final char f22159o = 29;

    /* renamed from: p */
    private static final char f22160p = 30;

    /* renamed from: q */
    private static final String[] f22161q = {"\nABCDEFGHIJKLMNOPQRSTUVWXYZ\ufffa\u001c\u001d\u001e\ufffb ￼\"#$%&'()*+,-./0123456789:\ufff1\ufff2\ufff3\ufff4\ufff8", "`abcdefghijklmnopqrstuvwxyz\ufffa\u001c\u001d\u001e\ufffb{￼}~\u007f;<=>?[\\]^_ ,./:@!|￼\ufff5\ufff6￼\ufff0\ufff2\ufff3\ufff4\ufff7", "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖ×ØÙÚ\ufffa\u001c\u001d\u001eÛÜÝÞßª¬±²³µ¹º¼½¾\u0080\u0081\u0082\u0083\u0084\u0085\u0086\u0087\u0088\u0089\ufff7 \ufff9\ufff3\ufff4\ufff8", "àáâãäåæçèéêëìíîïðñòóôõö÷øùú\ufffa\u001c\u001d\u001e\ufffbûüýþÿ¡¨«¯°´·¸»¿\u008a\u008b\u008c\u008d\u008e\u008f\u0090\u0091\u0092\u0093\u0094\ufff7 \ufff2\ufff9\ufff4\ufff8", "\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\ufffa￼￼\u001b\ufffb\u001c\u001d\u001e\u001f\u009f ¢£¤¥¦§©\u00ad®¶\u0095\u0096\u0097\u0098\u0099\u009a\u009b\u009c\u009d\u009e\ufff7 \ufff2\ufff3\ufff9\ufff8", "\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&'()*+,-./0123456789:;<=>?"};

    private C5382jo() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static DecoderResult m2266a(byte[] bArr, int i) {
        String str;
        StringBuilder sb = new StringBuilder((int) TbsListener.ErrorCode.NEEDDOWNLOAD_5);
        switch (i) {
            case 2:
            case 3:
                if (i == 2) {
                    str = new DecimalFormat("0000000000".substring(0, m2264a(bArr, new byte[]{39, 40, 41, 42, 31, 32}))).format(m2264a(bArr, new byte[]{33, 34, 35, 36, 25, 26, 27, 28, 29, 30, 19, 20, 21, 22, 23, 24, C2771ci.f13672f, 14, 15, 16, 17, 18, 7, 8, 9, 10, FileDownloadStatus.f10400b, 12, 1, 2}));
                } else {
                    str = String.valueOf(new char[]{f22161q[0].charAt(m2264a(bArr, new byte[]{39, 40, 41, 42, 31, 32})), f22161q[0].charAt(m2264a(bArr, new byte[]{33, 34, 35, 36, 25, 26})), f22161q[0].charAt(m2264a(bArr, new byte[]{27, 28, 29, 30, 19, 20})), f22161q[0].charAt(m2264a(bArr, new byte[]{21, 22, 23, 24, C2771ci.f13672f, 14})), f22161q[0].charAt(m2264a(bArr, new byte[]{15, 16, 17, 18, 7, 8})), f22161q[0].charAt(m2264a(bArr, new byte[]{9, 10, FileDownloadStatus.f10400b, 12, 1, 2}))});
                }
                DecimalFormat decimalFormat = new DecimalFormat("000");
                String format = decimalFormat.format(m2264a(bArr, new byte[]{TarConstants.LF_DIR, TarConstants.LF_FIFO, 43, 44, 45, 46, 47, TarConstants.LF_NORMAL, 37, 38}));
                String format2 = decimalFormat.format(m2264a(bArr, new byte[]{TarConstants.LF_CONTIG, 56, 57, 58, 59, 60, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, TarConstants.LF_BLK}));
                sb.append(m2265a(bArr, 10, 84));
                if (!sb.toString().startsWith("[)>\u001e01\u001d")) {
                    sb.insert(0, str + f22159o + format + f22159o + format2 + f22159o);
                    break;
                } else {
                    sb.insert(9, str + f22159o + format + f22159o + format2 + f22159o);
                    break;
                }
            case 4:
                sb.append(m2265a(bArr, 1, 93));
                break;
            case 5:
                sb.append(m2265a(bArr, 1, 77));
                break;
        }
        return new DecoderResult(bArr, sb.toString(), null, String.valueOf(i));
    }

    /* renamed from: a */
    private static int m2268a(int i, byte[] bArr) {
        int i2 = i - 1;
        return ((1 << (5 - (i2 % 6))) & bArr[i2 / 6]) == 0 ? 0 : 1;
    }

    /* renamed from: a */
    private static int m2264a(byte[] bArr, byte[] bArr2) {
        if (bArr2.length != 0) {
            int i = 0;
            for (int i2 = 0; i2 < bArr2.length; i2++) {
                int i3 = bArr2[i2] - 1;
                i += (((1 << (5 - (i3 % 6))) & bArr[i3 / 6]) == 0 ? 0 : 1) << ((bArr2.length - i2) - 1);
            }
            return i;
        }
        throw new IllegalArgumentException();
    }

    /* renamed from: a */
    private static int m2267a(byte[] bArr) {
        return m2264a(bArr, new byte[]{TarConstants.LF_DIR, TarConstants.LF_FIFO, 43, 44, 45, 46, 47, TarConstants.LF_NORMAL, 37, 38});
    }

    /* renamed from: b */
    private static int m2263b(byte[] bArr) {
        return m2264a(bArr, new byte[]{TarConstants.LF_CONTIG, 56, 57, 58, 59, 60, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, TarConstants.LF_BLK});
    }

    /* renamed from: c */
    private static int m2262c(byte[] bArr) {
        return m2264a(bArr, new byte[]{39, 40, 41, 42, 31, 32});
    }

    /* renamed from: d */
    private static int m2261d(byte[] bArr) {
        return m2264a(bArr, new byte[]{33, 34, 35, 36, 25, 26, 27, 28, 29, 30, 19, 20, 21, 22, 23, 24, C2771ci.f13672f, 14, 15, 16, 17, 18, 7, 8, 9, 10, FileDownloadStatus.f10400b, 12, 1, 2});
    }

    /* renamed from: e */
    private static String m2260e(byte[] bArr) {
        return String.valueOf(new char[]{f22161q[0].charAt(m2264a(bArr, new byte[]{39, 40, 41, 42, 31, 32})), f22161q[0].charAt(m2264a(bArr, new byte[]{33, 34, 35, 36, 25, 26})), f22161q[0].charAt(m2264a(bArr, new byte[]{27, 28, 29, 30, 19, 20})), f22161q[0].charAt(m2264a(bArr, new byte[]{21, 22, 23, 24, C2771ci.f13672f, 14})), f22161q[0].charAt(m2264a(bArr, new byte[]{15, 16, 17, 18, 7, 8})), f22161q[0].charAt(m2264a(bArr, new byte[]{9, 10, FileDownloadStatus.f10400b, 12, 1, 2}))});
    }

    /* renamed from: a */
    private static String m2265a(byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        StringBuilder sb = new StringBuilder();
        int i7 = i;
        int i8 = 0;
        int i9 = -1;
        int i10 = 0;
        while (i7 < i + i2) {
            char charAt = f22161q[i8].charAt(bArr[i7]);
            switch (charAt) {
                case 65520:
                case 65521:
                case 65522:
                case 65523:
                case 65524:
                    i8 = charAt - f22145a;
                    i10 = i8;
                    i9 = 1;
                    break;
                case 65525:
                    i9 = 2;
                    i10 = i8;
                    i8 = 0;
                    break;
                case 65526:
                    i9 = 3;
                    i10 = i8;
                    i8 = 0;
                    break;
                case 65527:
                    i8 = 0;
                    i9 = -1;
                    break;
                case 65528:
                    i8 = 1;
                    i9 = -1;
                    break;
                case 65529:
                    i9 = -1;
                    break;
                case 65530:
                default:
                    sb.append(charAt);
                    break;
                case 65531:
                    i7 = i7 + 1 + 1 + 1 + 1 + 1;
                    sb.append(new DecimalFormat("000000000").format((bArr[i3] << 24) + (bArr[i4] << 18) + (bArr[i5] << 12) + (bArr[i6] << 6) + bArr[i7]));
                    break;
            }
            i9--;
            if (i9 == 0) {
                i8 = i10;
            }
            i7++;
        }
        while (sb.length() > 0 && sb.charAt(sb.length() - 1) == 65532) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
}
