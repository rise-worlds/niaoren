package p110z1;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/* renamed from: z1.aqd */
/* loaded from: classes3.dex */
public class PhoneUtill {
    /* renamed from: a */
    public static boolean m11598a(String str) throws PatternSyntaxException {
        return Pattern.compile("^1\\d{10}$").matcher(str).matches();
    }
}
