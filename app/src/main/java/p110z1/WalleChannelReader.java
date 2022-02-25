package p110z1;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.io.File;
import java.util.Map;

/* renamed from: z1.aip */
/* loaded from: classes3.dex */
public final class WalleChannelReader {
    private WalleChannelReader() {
    }

    @Nullable
    /* renamed from: a */
    public static String m13042a(@NonNull Context context) {
        return m13041a(context, null);
    }

    @Nullable
    /* renamed from: a */
    public static String m13041a(@NonNull Context context, @NonNull String str) {
        ChannelInfo b = m13040b(context);
        return b == null ? str : b.m13054a();
    }

    @Nullable
    /* renamed from: b */
    public static ChannelInfo m13040b(@NonNull Context context) {
        String d = m13037d(context);
        if (TextUtils.isEmpty(d)) {
            return null;
        }
        return ChannelReader.m13052a(new File(d));
    }

    @Nullable
    /* renamed from: b */
    public static String m13039b(@NonNull Context context, @NonNull String str) {
        Map<String, String> c = m13038c(context);
        if (c == null) {
            return null;
        }
        return c.get(str);
    }

    @Nullable
    /* renamed from: c */
    public static Map<String, String> m13038c(@NonNull Context context) {
        String d = m13037d(context);
        if (TextUtils.isEmpty(d)) {
            return null;
        }
        return ChannelReader.m13051b(new File(d));
    }

    @Nullable
    /* renamed from: d */
    private static String m13037d(@NonNull Context context) {
        try {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            if (applicationInfo == null) {
                return null;
            }
            return applicationInfo.sourceDir;
        } catch (Throwable unused) {
            return null;
        }
    }
}
