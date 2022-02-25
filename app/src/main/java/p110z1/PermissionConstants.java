package p110z1;

import android.annotation.SuppressLint;
import android.os.Build;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SuppressLint({"InlinedApi"})
/* renamed from: z1.fm */
/* loaded from: classes3.dex */
public final class PermissionConstants {

    /* renamed from: a */
    public static final String f21647a = "android.permission-group.CALENDAR";

    /* renamed from: b */
    public static final String f21648b = "android.permission-group.CAMERA";

    /* renamed from: c */
    public static final String f21649c = "android.permission-group.CONTACTS";

    /* renamed from: d */
    public static final String f21650d = "android.permission-group.LOCATION";

    /* renamed from: e */
    public static final String f21651e = "android.permission-group.MICROPHONE";

    /* renamed from: f */
    public static final String f21652f = "android.permission-group.PHONE";

    /* renamed from: g */
    public static final String f21653g = "android.permission-group.SENSORS";

    /* renamed from: h */
    public static final String f21654h = "android.permission-group.SMS";

    /* renamed from: i */
    public static final String f21655i = "android.permission-group.STORAGE";

    /* renamed from: j */
    private static final String[] f21656j = {"android.permission.READ_CALENDAR", "android.permission.WRITE_CALENDAR"};

    /* renamed from: k */
    private static final String[] f21657k = {"android.permission.CAMERA"};

    /* renamed from: l */
    private static final String[] f21658l = {"android.permission.READ_CONTACTS", "android.permission.WRITE_CONTACTS", "android.permission.GET_ACCOUNTS"};

    /* renamed from: m */
    private static final String[] f21659m = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};

    /* renamed from: n */
    private static final String[] f21660n = {"android.permission.RECORD_AUDIO"};

    /* renamed from: o */
    private static final String[] f21661o = {"android.permission.READ_PHONE_STATE", "android.permission.READ_PHONE_NUMBERS", "android.permission.CALL_PHONE", "android.permission.READ_CALL_LOG", "android.permission.WRITE_CALL_LOG", "com.android.voicemail.permission.ADD_VOICEMAIL", "android.permission.USE_SIP", "android.permission.PROCESS_OUTGOING_CALLS", "android.permission.ANSWER_PHONE_CALLS"};

    /* renamed from: p */
    private static final String[] f21662p = {"android.permission.READ_PHONE_STATE", "android.permission.READ_PHONE_NUMBERS", "android.permission.CALL_PHONE", "android.permission.READ_CALL_LOG", "android.permission.WRITE_CALL_LOG", "com.android.voicemail.permission.ADD_VOICEMAIL", "android.permission.USE_SIP", "android.permission.PROCESS_OUTGOING_CALLS"};

    /* renamed from: q */
    private static final String[] f21663q = {"android.permission.BODY_SENSORS"};

    /* renamed from: r */
    private static final String[] f21664r = {"android.permission.SEND_SMS", "android.permission.RECEIVE_SMS", "android.permission.READ_SMS", "android.permission.RECEIVE_WAP_PUSH", "android.permission.RECEIVE_MMS"};

    /* renamed from: s */
    private static final String[] f21665s = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};

    /* compiled from: PermissionConstants.java */
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: z1.fm$a */
    /* loaded from: classes3.dex */
    public @interface AbstractC5356a {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: a */
    public static String[] m2833a(String str) {
        char c;
        switch (str.hashCode()) {
            case -1639857183:
                if (str.equals(f21649c)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1410061184:
                if (str.equals(f21652f)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -1250730292:
                if (str.equals(f21647a)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1140935117:
                if (str.equals(f21648b)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 421761675:
                if (str.equals(f21653g)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 828638019:
                if (str.equals(f21650d)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 852078861:
                if (str.equals(f21655i)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1581272376:
                if (str.equals(f21651e)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1795181803:
                if (str.equals(f21654h)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return f21656j;
            case 1:
                return f21657k;
            case 2:
                return f21658l;
            case 3:
                return f21659m;
            case 4:
                return f21660n;
            case 5:
                if (Build.VERSION.SDK_INT < 26) {
                    return f21662p;
                }
                return f21661o;
            case 6:
                return f21663q;
            case 7:
                return f21664r;
            case '\b':
                return f21665s;
            default:
                return new String[]{str};
        }
    }
}
