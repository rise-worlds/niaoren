package p110z1;

import java.text.DecimalFormat;
import org.apache.commons.p105io.FileUtils;

/* renamed from: z1.aer */
/* loaded from: classes3.dex */
public class UnitUtils {

    /* renamed from: a */
    private static final int f15464a = 1073741824;

    /* renamed from: b */
    private static final int f15465b = 1048576;

    /* renamed from: c */
    private static final int f15466c = 1024;

    /* renamed from: a */
    public static String m13875a(long j) {
        DecimalFormat decimalFormat = new DecimalFormat("###.0");
        long j2 = j / FileUtils.ONE_GB;
        if (j2 >= 1) {
            return decimalFormat.format(j2) + "GB";
        }
        long j3 = j / 1048576;
        if (j3 >= 1) {
            return decimalFormat.format(j3) + "MB";
        }
        long j4 = j / 1024;
        if (j4 >= 1) {
            return decimalFormat.format(j4) + "KB";
        }
        return j + "B";
    }
}
