package p110z1;

import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.tendcloud.tenddata.C2771ci;
import org.apache.commons.p105io.IOUtils;
import org.apache.tools.tar.TarConstants;

/* renamed from: z1.cr */
/* loaded from: classes3.dex */
public final class C5103cr {

    /* renamed from: a */
    private static char[] f21098a = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', IOUtils.DIR_SEPARATOR_UNIX};

    /* renamed from: b */
    private static byte[] f21099b = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, FileDownloadStatus.f10400b, 12, C2771ci.f13672f, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, TarConstants.LF_NORMAL, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, -1, -1, -1, -1, -1};

    /* JADX WARN: Code restructure failed: missing block: B:31:0x007c, code lost:
        if (r2 == (-1)) goto L_0x0045;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x007e, code lost:
        r0.append((char) (r2 | ((r5 & 3) << 6)));
        r2 = r4;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] m3523a(java.lang.String r8) {
        /*
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.lang.String r1 = "US-ASCII"
            byte[] r8 = r8.getBytes(r1)
            int r1 = r8.length
            r2 = 0
        L_0x000d:
            if (r2 >= r1) goto L_0x0045
        L_0x000f:
            byte[] r3 = p110z1.C5103cr.f21099b
            int r4 = r2 + 1
            byte r2 = r8[r2]
            byte r2 = r3[r2]
            r3 = -1
            if (r4 >= r1) goto L_0x001f
            if (r2 == r3) goto L_0x001d
            goto L_0x001f
        L_0x001d:
            r2 = r4
            goto L_0x000f
        L_0x001f:
            if (r2 == r3) goto L_0x0045
        L_0x0021:
            byte[] r5 = p110z1.C5103cr.f21099b
            int r6 = r4 + 1
            byte r4 = r8[r4]
            byte r4 = r5[r4]
            if (r6 >= r1) goto L_0x0030
            if (r4 == r3) goto L_0x002e
            goto L_0x0030
        L_0x002e:
            r4 = r6
            goto L_0x0021
        L_0x0030:
            if (r4 == r3) goto L_0x0045
            int r2 = r2 << 2
            r5 = r4 & 48
            int r5 = r5 >>> 4
            r2 = r2 | r5
            char r2 = (char) r2
            r0.append(r2)
        L_0x003d:
            int r2 = r6 + 1
            byte r5 = r8[r6]
            r6 = 61
            if (r5 != r6) goto L_0x0050
        L_0x0045:
            java.lang.String r8 = r0.toString()
            java.lang.String r0 = "iso8859-1"
            byte[] r8 = r8.getBytes(r0)
            return r8
        L_0x0050:
            byte[] r7 = p110z1.C5103cr.f21099b
            byte r5 = r7[r5]
            if (r2 >= r1) goto L_0x005b
            if (r5 == r3) goto L_0x0059
            goto L_0x005b
        L_0x0059:
            r6 = r2
            goto L_0x003d
        L_0x005b:
            if (r5 == r3) goto L_0x0045
            r4 = r4 & 15
            int r4 = r4 << 4
            r7 = r5 & 60
            int r7 = r7 >>> 2
            r4 = r4 | r7
            char r4 = (char) r4
            r0.append(r4)
        L_0x006a:
            int r4 = r2 + 1
            byte r2 = r8[r2]
            if (r2 != r6) goto L_0x0071
            goto L_0x0045
        L_0x0071:
            byte[] r7 = p110z1.C5103cr.f21099b
            byte r2 = r7[r2]
            if (r4 >= r1) goto L_0x007c
            if (r2 == r3) goto L_0x007a
            goto L_0x007c
        L_0x007a:
            r2 = r4
            goto L_0x006a
        L_0x007c:
            if (r2 == r3) goto L_0x0045
            r3 = r5 & 3
            int r3 = r3 << 6
            r2 = r2 | r3
            char r2 = (char) r2
            r0.append(r2)
            r2 = r4
            goto L_0x000d
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.C5103cr.m3523a(java.lang.String):byte[]");
    }
}
