package p110z1;

import android.os.Environment;
import java.io.File;

/* renamed from: z1.ame */
/* loaded from: classes3.dex */
public class GlobalConstants {

    /* renamed from: a */
    public static final String f16478a = Environment.getExternalStorageDirectory().getPath() + File.separatorChar + ShareVal.f16591a + File.separatorChar;

    /* renamed from: b */
    public static final String f16479b;

    /* renamed from: c */
    public static final String f16480c = "/nrzs";

    /* renamed from: d */
    public static final String f16481d = "TOPIC_TO_HTML5_INTENT";

    /* renamed from: e */
    public static final String f16482e = "TO_AD_FROM_WHERE";

    /* renamed from: f */
    public static final String f16483f = "TO_AD_CLOSE";

    /* renamed from: g */
    public static final String f16484g = "payBusType";

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(f16478a);
        sb.append("apk");
        sb.append(File.separatorChar);
        f16479b = sb.toString();
    }
}
