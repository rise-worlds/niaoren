package p110z1;

import org.apache.commons.p105io.IOUtils;

/* renamed from: z1.aoh */
/* loaded from: classes3.dex */
public class FloatUtils {

    /* renamed from: a */
    private static final char[] f16954a = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    /* renamed from: b */
    private static final char[] f16955b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', IOUtils.DIR_SEPARATOR_UNIX, '='};

    /* renamed from: a */
    public static String m12117a() {
        return String.format("%c%c%c%c%c%c%c%c%c%c", Character.valueOf(f16954a[46]), Character.valueOf(f16954a[4]), Character.valueOf(f16954a[22]), Character.valueOf(f16955b[10]), Character.valueOf(f16954a[24]), Character.valueOf(f16954a[50]), Character.valueOf(f16954a[34]), Character.valueOf(f16954a[8]), Character.valueOf(f16954a[22]), Character.valueOf(f16954a[26])) + String.format("%c%c%c%c%c%c%c%c%c%c", Character.valueOf(f16954a[7]), Character.valueOf(f16954a[8]), Character.valueOf(f16954a[9]), Character.valueOf(f16955b[4]), Character.valueOf(f16954a[9]), Character.valueOf(f16954a[49]), Character.valueOf(f16954a[39]), Character.valueOf(f16954a[48]), Character.valueOf(f16954a[25]), Character.valueOf(f16954a[32])) + String.format("%c%c%c%c", Character.valueOf(f16954a[17]), Character.valueOf(f16954a[0]), Character.valueOf(f16955b[11]), Character.valueOf(f16955b[11]));
    }
}
