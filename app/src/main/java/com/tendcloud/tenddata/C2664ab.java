package com.tendcloud.tenddata;

import android.content.Context;
import android.os.Handler;
import java.io.File;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ab */
/* loaded from: classes2.dex */
public class C2664ab {

    /* renamed from: E */
    public static final int f13488E = 104857600;

    /* renamed from: F */
    static final String f13489F = "WiFi";

    /* renamed from: G */
    static final String f13490G = "All";

    /* renamed from: I */
    public static final String f13492I = "Android+TD+V4.0.30 gp";

    /* renamed from: J */
    static long f13493J = 0;

    /* renamed from: M */
    public static final int f13496M = 1800000;

    /* renamed from: O */
    public static final long f13498O = 30000;

    /* renamed from: P */
    public static final int f13499P = 100;

    /* renamed from: Q */
    public static final String f13500Q = "TD_APP_ID";

    /* renamed from: R */
    public static final String f13501R = "TD_CHANNEL_ID";

    /* renamed from: T */
    private static final String f13503T = "+V";

    /* renamed from: U */
    private static final int f13504U = 120;

    /* renamed from: V */
    private static final int f13505V = 30;

    /* renamed from: W */
    private static final int f13506W = 1000;

    /* renamed from: a */
    public static final boolean f13507a = false;

    /* renamed from: b */
    public static boolean f13508b = false;

    /* renamed from: i */
    public static final int f13515i = 1;

    /* renamed from: j */
    public static final String f13516j = "Android+";

    /* renamed from: k */
    public static FileChannel f13517k = null;

    /* renamed from: r */
    public static final String f13524r = "TD";

    /* renamed from: s */
    public static final String f13525s = "TDLog";

    /* renamed from: t */
    public static final String f13526t = "913";

    /* renamed from: u */
    public static final String f13527u = "TD_app_pefercen_profile";

    /* renamed from: v */
    public static final String f13528v = "TD_appId_";

    /* renamed from: w */
    public static final String f13529w = "TD_channelId";

    /* renamed from: x */
    public static final String f13530x = "TD_sdk_last_send_time_wifi";

    /* renamed from: y */
    public static final String f13531y = "TD_sdk_last_send_time_mobile_data";

    /* renamed from: z */
    public static final String f13532z = "isDeveloper";

    /* renamed from: c */
    public static final AtomicBoolean f13509c = new AtomicBoolean(false);

    /* renamed from: d */
    public static final Map f13510d = new TreeMap();

    /* renamed from: e */
    public static boolean f13511e = false;

    /* renamed from: f */
    public static long f13512f = 0;

    /* renamed from: g */
    public static Context f13513g = null;

    /* renamed from: h */
    public static Handler f13514h = null;

    /* renamed from: l */
    public static long f13518l = 0;

    /* renamed from: m */
    public static boolean f13519m = false;

    /* renamed from: n */
    public static int f13520n = -1;

    /* renamed from: o */
    public static boolean f13521o = true;

    /* renamed from: p */
    public static boolean f13522p = true;

    /* renamed from: q */
    public static boolean f13523q = false;

    /* renamed from: S */
    private static HashMap f13502S = new HashMap();

    /* renamed from: A */
    public static String f13484A = "Default";

    /* renamed from: B */
    public static boolean f13485B = false;

    /* renamed from: C */
    public static String f13486C = null;

    /* renamed from: D */
    public static boolean f13487D = false;

    /* renamed from: H */
    static String f13491H = "WiFi";

    /* renamed from: K */
    public static int f13494K = 2;

    /* renamed from: L */
    public static AtomicInteger f13495L = new AtomicInteger(0);

    /* renamed from: N */
    public static final AtomicBoolean f13497N = new AtomicBoolean(false);

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.ab$b */
    /* loaded from: classes2.dex */
    public static class C2666b {
        public static final int API_MYNA_INIT = 90;
        public static final int API_MYNA_START = 91;
        public static final int API_MYNA_STOP = 92;
        public static final int API_ON_ERROR = 3;
        public static final int API_ON_EVENT = 2;
        public static final int API_ON_INIT = 1;
        public static final int API_ON_PAGE_END = 5;
        public static final int API_ON_PAGE_START = 4;
        public static final int API_TYPE_ACCOUNT = 9;
        public static final int API_TYPE_BRAND_GROWTH = 16;
        public static final int API_TYPE_CUST_LOCATION = 14;
        public static final int API_TYPE_GAME = 6;
        public static final int API_TYPE_GAME_SESSION_END = 15;
        public static final int API_TYPE_GAME_SESSION_START = 12;
        public static final int API_TYPE_IAP = 8;
        public static final int API_TYPE_SESSION_END = 11;
        public static final int API_TYPE_SESSION_PAUSE = 13;
        public static final int API_TYPE_SESSION_START = 10;
        public static final int API_TYPE_TRACKING = 7;
    }

    /* renamed from: a */
    public static String m16358a(Context context, AbstractC2790d dVar) {
        if (context == null || dVar == null) {
            C2811dq.dForInternal("Context or Service is null");
            return "";
        }
        String str = (String) f13502S.get(dVar.name());
        if (!C2855es.m15791b(str)) {
            return str;
        }
        return C2843eh.m15840b(context, f13527u, f13528v + dVar.name(), "");
    }

    /* renamed from: a */
    public static void m16356a(String str, AbstractC2790d dVar) {
        if (dVar != null) {
            f13502S.put(dVar.name(), str);
            Context context = f13513g;
            C2843eh.m15842a(context, f13527u, f13528v + dVar.name(), str);
        }
    }

    /* renamed from: b */
    public static String m16353b(Context context, AbstractC2790d dVar) {
        if (C2855es.m15791b(f13484A) || f13484A.equals("Default")) {
            f13484A = C2843eh.m15840b(context, f13527u, f13529w + dVar.name(), "Default");
        }
        return f13484A;
    }

    /* renamed from: b */
    public static void m16351b(String str, AbstractC2790d dVar) {
        Context context = f13513g;
        C2843eh.m15842a(context, f13527u, f13529w + dVar.name(), str);
    }

    /* renamed from: a */
    public static int[] m16361a() {
        return new int[]{120000, 30000};
    }

    /* renamed from: a */
    public static void m16355a(String str, String str2, AbstractC2790d dVar) {
        Context context = f13513g;
        if (context != null) {
            f13514h = new Handler(context.getMainLooper());
        }
        f13512f = System.currentTimeMillis();
        if (str != null && !str.trim().isEmpty() && str.contains("-") && !dVar.name().equals("FINTECH")) {
            str = null;
            try {
                str = str.split("-")[1];
            } catch (Throwable unused) {
            }
        }
        if (str2 != null && !str2.trim().isEmpty()) {
            f13484A = str2;
        }
        m16356a(str, dVar);
        m16351b(f13484A, dVar);
        C2982io.m15416a().m15414a((Object) str, dVar);
        C2982io.m15416a().m15412b(f13484A, dVar);
        m16360a(C2830dz.f13834a);
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.ab$a */
    /* loaded from: classes2.dex */
    public class C2665a {
        public static final int DST_FILE = 2;
        public static final int DST_SQLITE = 1;

        public C2665a() {
        }
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.ab$c */
    /* loaded from: classes2.dex */
    public class C2667c {
        public static final int SDT_JSON = 2;
        public static final int SDT_MP = 1;
        public static final int SDT_PB = 3;
        public static final int SDT_UNKNOWN = -1;

        public C2667c() {
        }
    }

    public static void setDeveloperMode(boolean z) {
        try {
            C2843eh.m15843a(f13513g, f13527u, f13532z, z ? 1L : 0L);
        } catch (Throwable unused) {
        }
    }

    /* renamed from: b */
    public static boolean m16354b() {
        try {
            return C2843eh.m15841b(f13513g, f13527u, f13532z, 0L) != 0;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: a */
    private static void m16360a(int i) {
        try {
            C2985ir irVar = new C2985ir();
            switch (i) {
                case 1:
                    irVar.setFrameWork("Cocos2d");
                    break;
                case 2:
                    irVar.setFrameWork("Unity");
                    break;
                case 3:
                    irVar.setFrameWork("AIR");
                    break;
                case 4:
                    irVar.setFrameWork("PhoneGap");
                    break;
                default:
                    irVar.setFrameWork("Native");
                    break;
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: c */
    public static String m16350c() {
        try {
            return new C2985ir().m15406b();
        } catch (Throwable unused) {
            return "Native";
        }
    }

    /* renamed from: d */
    public static boolean m16347d() {
        return C2685at.m16298b();
    }

    /* renamed from: a */
    public static boolean m16357a(AbstractC2790d dVar) {
        if (f13513g == null || dVar == null) {
            return false;
        }
        if (m16359a(f13513g)) {
            return C2812dr.m16013e(dVar) == 1;
        }
        if (C2812dr.m16013e(dVar) == 1) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - C2812dr.m16015d(dVar) > 5000) {
                C2812dr.m16028a(false, dVar);
            } else if (currentTimeMillis - C2812dr.m16014e() <= 5000) {
                return m16349c(f13513g, dVar);
            } else {
                if (m16352b(dVar) || m16346d(dVar) || m16348c(dVar)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* renamed from: b */
    private static boolean m16352b(AbstractC2790d dVar) {
        if (f13513g == null || !dVar.name().equals("TRACKING")) {
            return false;
        }
        File filesDir = f13513g.getFilesDir();
        try {
            File file = new File(filesDir, "td_database3SaaS");
            File file2 = new File(filesDir, "td_database0SaaS");
            File file3 = new File(filesDir, C2663aa.f13453W);
            File file4 = new File(file3, "td_database3SaaS");
            File file5 = new File(file3, "td_database0SaaS");
            if (!file.exists() && !file2.exists() && !file4.exists()) {
                if (!file5.exists()) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: c */
    private static boolean m16348c(AbstractC2790d dVar) {
        if (f13513g == null || !dVar.name().equals("APP")) {
            return false;
        }
        File filesDir = f13513g.getFilesDir();
        try {
            File file = new File(filesDir, "td_database3SaaS");
            File file2 = new File(filesDir, "td_database1SaaS");
            File file3 = new File(filesDir, C2663aa.f13453W);
            File file4 = new File(file3, "td_database3SaaS");
            File file5 = new File(file3, "td_database1SaaS");
            if (!file.exists() && !file2.exists() && !file4.exists()) {
                if (!file5.exists()) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: d */
    private static boolean m16346d(AbstractC2790d dVar) {
        if (f13513g == null || !dVar.name().equals("GAME")) {
            return false;
        }
        File filesDir = f13513g.getFilesDir();
        try {
            File file = new File(filesDir, "td_database0SaaS");
            File file2 = new File(filesDir, "td_database1SaaS");
            File file3 = new File(filesDir, C2663aa.f13453W);
            File file4 = new File(file3, "td_database0SaaS");
            File file5 = new File(file3, "td_database1SaaS");
            if (!file2.exists() && !file.exists() && !file5.exists()) {
                if (!file4.exists()) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: c */
    private static boolean m16349c(Context context, AbstractC2790d dVar) {
        try {
            if (f13513g == null || !dVar.name().equals("TRACKING") || !context.getSharedPreferences("talkingdata_file_prefence", 0).getBoolean("actived", false)) {
                return true;
            }
            C2812dr.m16028a(false, dVar);
            return false;
        } catch (Throwable unused) {
            return true;
        }
    }

    /* renamed from: a */
    private static boolean m16359a(Context context) {
        try {
            return C2810dp.m16048a().m16044d(context) == C2810dp.m16048a().m16043e(context);
        } catch (Throwable unused) {
            return false;
        }
    }
}
