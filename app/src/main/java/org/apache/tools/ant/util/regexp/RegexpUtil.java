package org.apache.tools.ant.util.regexp;

/* loaded from: classes2.dex */
public class RegexpUtil {
    public static int asOptions(boolean z, boolean z2, boolean z3) {
        int i = !z ? 256 : 0;
        if (z2) {
            i |= 4096;
        }
        return z3 ? i | 65536 : i;
    }

    public static boolean hasFlag(int i, int i2) {
        return (i & i2) > 0;
    }

    public static int removeFlag(int i, int i2) {
        return i & ((-1) - i2);
    }

    public static int asOptions(String str) {
        boolean z = false;
        if (str == null) {
            return 0;
        }
        boolean z2 = str.indexOf(105) == -1;
        boolean z3 = str.indexOf(109) != -1;
        if (str.indexOf(115) != -1) {
            z = true;
        }
        int asOptions = asOptions(z2, z3, z);
        return str.indexOf(103) != -1 ? asOptions | 16 : asOptions;
    }

    public static int asOptions(boolean z) {
        return asOptions(z, false, false);
    }
}
