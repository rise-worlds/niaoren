package p110z1;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* renamed from: z1.qh */
/* loaded from: classes3.dex */
public class PreJava9DateFormatProvider {
    /* renamed from: a */
    public static DateFormat m1356a(int i) {
        return new SimpleDateFormat(m1354b(i), Locale.US);
    }

    /* renamed from: a */
    public static DateFormat m1355a(int i, int i2) {
        return new SimpleDateFormat(m1353c(i) + ExpandableTextView.f6958c + m1352d(i2), Locale.US);
    }

    /* renamed from: b */
    private static String m1354b(int i) {
        switch (i) {
            case 0:
                return "EEEE, MMMM d, y";
            case 1:
                return "MMMM d, y";
            case 2:
                return "MMM d, y";
            case 3:
                return "M/d/yy";
            default:
                throw new IllegalArgumentException("Unknown DateFormat style: " + i);
        }
    }

    /* renamed from: c */
    private static String m1353c(int i) {
        switch (i) {
            case 0:
                return "EEEE, MMMM d, yyyy";
            case 1:
                return "MMMM d, yyyy";
            case 2:
                return "MMM d, yyyy";
            case 3:
                return "M/d/yy";
            default:
                throw new IllegalArgumentException("Unknown DateFormat style: " + i);
        }
    }

    /* renamed from: d */
    private static String m1352d(int i) {
        switch (i) {
            case 0:
            case 1:
                return "h:mm:ss a z";
            case 2:
                return "h:mm:ss a";
            case 3:
                return "h:mm a";
            default:
                throw new IllegalArgumentException("Unknown DateFormat style: " + i);
        }
    }
}
