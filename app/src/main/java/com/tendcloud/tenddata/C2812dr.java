package com.tendcloud.tenddata;

import android.content.Context;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.dr */
/* loaded from: classes2.dex */
public class C2812dr {

    /* renamed from: A */
    public static final String f13770A = "TDadditionalVersionCode";

    /* renamed from: B */
    public static final String f13771B = "TDcs";

    /* renamed from: C */
    public static final String f13772C = "TDpref.apps_send_time.key";

    /* renamed from: D */
    private static final String f13773D = "TDtime_set_collect_net";

    /* renamed from: E */
    private static final String f13774E = "TDdeep_link_url";

    /* renamed from: F */
    private static final String f13775F = "TDtd_role_id";

    /* renamed from: G */
    private static final String f13776G = "TDpref.accountid.key";

    /* renamed from: H */
    private static final String f13777H = "TDpref.accountgame.key";

    /* renamed from: I */
    private static final String f13778I = "TDpref.missionid.key";

    /* renamed from: J */
    private static final String f13779J = "TDpref.game.session.startsystem.key";

    /* renamed from: a */
    public static final String f13780a = "TDpref.profile.key";

    /* renamed from: b */
    public static final String f13781b = "TDpref.session.key";

    /* renamed from: c */
    public static final String f13782c = "TDpref.session.backup.key";

    /* renamed from: d */
    public static final String f13783d = "TDpref.lastactivity.key";

    /* renamed from: e */
    public static final String f13784e = "TDpref.start.key";

    /* renamed from: f */
    public static final String f13785f = "TDpref.init.key";

    /* renamed from: g */
    public static final String f13786g = "TDpref.init.flag";

    /* renamed from: h */
    public static final String f13787h = "TDpref.actstart.key";

    /* renamed from: i */
    public static final String f13788i = "TDpref.end.key";

    /* renamed from: j */
    public static final String f13789j = "TDpref.ip";

    /* renamed from: k */
    public static final String f13790k = "TD_CHANNEL_ID";

    /* renamed from: l */
    public static final String f13791l = "TDappcontext_push";

    /* renamed from: m */
    public static final String f13792m = "TDpref.tokensync.key";

    /* renamed from: n */
    public static final String f13793n = "TDpref.push.msgid.key";

    /* renamed from: o */
    public static final String f13794o = "TDpref.running.app.key";

    /* renamed from: p */
    public static final String f13795p = "activities";

    /* renamed from: q */
    public static final String f13796q = "handHolding";

    /* renamed from: r */
    public static final String f13797r = "pref_antiCheatingData";

    /* renamed from: s */
    public static final String f13798s = "TDpref_longtime";

    /* renamed from: t */
    public static final String f13799t = "TDpref_shorttime";

    /* renamed from: u */
    public static final String f13800u = "TDaes_key";

    /* renamed from: v */
    public static final String f13801v = "TDpref_game";

    /* renamed from: w */
    public static final String f13802w = "TD_push_pref_file";

    /* renamed from: x */
    static final String f13803x = "TDisAppQuiting";

    /* renamed from: y */
    public static final String f13804y = "TDpref.last.sdk.check";

    /* renamed from: z */
    public static final String f13805z = "TDadditionalVersionName";

    /* renamed from: a */
    public static String m16034a() {
        if (C2664ab.f13513g == null) {
            return null;
        }
        try {
            return C2843eh.m15840b(C2664ab.f13513g, f13798s, f13800u, (String) null);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return null;
        }
    }

    public static void setAESKey(String str) {
        if (C2664ab.f13513g != null) {
            try {
                C2843eh.m15842a(C2664ab.f13513g, f13798s, f13800u, str);
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }
    }

    /* renamed from: a */
    public static String m16032a(AbstractC2790d dVar) {
        if (C2664ab.f13513g == null || dVar == null) {
            return null;
        }
        try {
            Context context = C2664ab.f13513g;
            return C2843eh.m15840b(context, f13798s + dVar.index(), f13781b, (String) null);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return null;
        }
    }

    /* renamed from: a */
    public static void m16030a(String str, AbstractC2790d dVar) {
        if (C2664ab.f13513g != null && dVar != null) {
            try {
                Context context = C2664ab.f13513g;
                C2843eh.m15842a(context, f13798s + dVar.index(), f13781b, str);
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }
    }

    /* renamed from: b */
    public static void m16023b(String str, AbstractC2790d dVar) {
        if (C2664ab.f13513g != null && dVar != null) {
            try {
                Context context = C2664ab.f13513g;
                C2843eh.m15842a(context, f13798s + dVar.index(), f13782c, str);
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }
    }

    /* renamed from: b */
    public static String m16025b(AbstractC2790d dVar) {
        if (C2664ab.f13513g == null || dVar == null) {
            return null;
        }
        try {
            Context context = C2664ab.f13513g;
            return C2843eh.m15840b(context, f13798s + dVar.index(), f13782c, (String) null);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return null;
        }
    }

    /* renamed from: b */
    public static void m16027b() {
        if (C2664ab.f13513g != null) {
            try {
                C2664ab.f13513g.getSharedPreferences("TD_CHANNEL_ID", 0).edit().putBoolean("location_called", true).commit();
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }
    }

    /* renamed from: c */
    public static boolean m16021c() {
        if (C2664ab.f13513g == null) {
            return false;
        }
        try {
            return C2664ab.f13513g.getSharedPreferences("TD_CHANNEL_ID", 0).getBoolean("location_called", false);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return false;
        }
    }

    public static void setLastActivity(String str) {
        if (C2664ab.f13513g != null) {
            try {
                C2843eh.m15842a(C2664ab.f13513g, f13799t, f13783d, str);
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: d */
    public static String m16016d() {
        if (C2664ab.f13513g == null) {
            return "";
        }
        try {
            return C2843eh.m15840b(C2664ab.f13513g, f13799t, f13783d, "");
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: c */
    public static long m16019c(AbstractC2790d dVar) {
        if (C2664ab.f13513g == null || dVar == null) {
            return 0L;
        }
        try {
            Context context = C2664ab.f13513g;
            return C2843eh.m15841b(context, f13798s + dVar.index(), f13784e, 0L);
        } catch (Throwable unused) {
            return 0L;
        }
    }

    /* renamed from: a */
    public static void m16033a(long j, AbstractC2790d dVar) {
        if (C2664ab.f13513g != null && dVar != null) {
            try {
                Context context = C2664ab.f13513g;
                C2843eh.m15843a(context, f13798s + dVar.index(), f13784e, j);
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: b */
    public static void m16026b(long j, AbstractC2790d dVar) {
        if (C2664ab.f13513g != null && dVar != null) {
            try {
                Context context = C2664ab.f13513g;
                C2843eh.m15843a(context, f13798s + dVar.index(), f13785f, j);
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: d */
    public static long m16015d(AbstractC2790d dVar) {
        if (C2664ab.f13513g == null || dVar == null) {
            return 0L;
        }
        try {
            Context context = C2664ab.f13513g;
            return C2843eh.m15841b(context, f13798s + dVar.index(), f13785f, 0L);
        } catch (Throwable unused) {
            return 0L;
        }
    }

    /* renamed from: a */
    public static void m16028a(boolean z, AbstractC2790d dVar) {
        if (C2664ab.f13513g != null && dVar != null) {
            try {
                Context context = C2664ab.f13513g;
                C2843eh.m15843a(context, f13798s + dVar.index(), f13786g, z ? 1L : 0L);
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: e */
    public static long m16013e(AbstractC2790d dVar) {
        if (C2664ab.f13513g == null || dVar == null) {
            return 1L;
        }
        try {
            Context context = C2664ab.f13513g;
            return C2843eh.m15841b(context, f13798s + dVar.index(), f13786g, 1L);
        } catch (Throwable unused) {
            return 1L;
        }
    }

    public static void setInitTime(long j) {
        if (C2664ab.f13513g != null) {
            try {
                C2843eh.m15843a(C2664ab.f13513g, f13798s, f13785f, j);
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: e */
    public static long m16014e() {
        if (C2664ab.f13513g == null) {
            return 0L;
        }
        try {
            return C2843eh.m15841b(C2664ab.f13513g, f13798s, f13785f, 0L);
        } catch (Throwable unused) {
            return 0L;
        }
    }

    public static void setActivityStartTime(long j) {
        if (C2664ab.f13513g != null) {
            try {
                C2843eh.m15843a(C2664ab.f13513g, f13799t, f13787h, j);
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: f */
    public static long m16012f() {
        if (C2664ab.f13513g == null) {
            return 0L;
        }
        try {
            return C2843eh.m15841b(C2664ab.f13513g, f13799t, f13787h, 0L);
        } catch (Throwable unused) {
            return 0L;
        }
    }

    /* renamed from: f */
    public static long m16011f(AbstractC2790d dVar) {
        if (C2664ab.f13513g == null || dVar == null) {
            return 0L;
        }
        try {
            Context context = C2664ab.f13513g;
            return C2843eh.m15841b(context, f13799t + dVar.index(), f13788i, 0L);
        } catch (Throwable unused) {
            return 0L;
        }
    }

    /* renamed from: c */
    public static void m16020c(long j, AbstractC2790d dVar) {
        if (C2664ab.f13513g != null && dVar != null) {
            try {
                Context context = C2664ab.f13513g;
                C2843eh.m15843a(context, f13799t + dVar.index(), f13788i, j);
            } catch (Throwable unused) {
            }
        }
    }

    public static void setPostProfile(boolean z) {
        try {
            C2843eh.m15843a(C2664ab.f13513g, f13798s, f13780a, z ? 1L : 0L);
        } catch (Throwable unused) {
        }
    }

    public static void setCollectRunningTime(long j) {
        try {
            C2843eh.m15843a(C2664ab.f13513g, f13798s, f13794o, j);
        } catch (Throwable unused) {
        }
    }

    /* renamed from: g */
    public static long m16010g() {
        if (C2664ab.f13513g == null) {
            return 0L;
        }
        try {
            return C2843eh.m15841b(C2664ab.f13513g, f13798s, f13794o, 0L);
        } catch (Throwable unused) {
            return 0L;
        }
    }

    public static void setAdditionalVersionName(String str) {
        if (C2664ab.f13513g != null) {
            try {
                C2843eh.m15842a(C2664ab.f13513g, f13798s, f13805z, str);
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: h */
    public static String m16009h() {
        if (C2664ab.f13513g == null) {
            return null;
        }
        try {
            return C2843eh.m15840b(C2664ab.f13513g, f13798s, f13805z, (String) null);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void setAdditionalVersionCode(long j) {
        if (C2664ab.f13513g != null) {
            try {
                C2843eh.m15843a(C2664ab.f13513g, f13798s, f13770A, j);
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: i */
    public static long m16008i() {
        if (C2664ab.f13513g == null) {
            return -1L;
        }
        try {
            return C2843eh.m15841b(C2664ab.f13513g, f13798s, f13770A, -1L);
        } catch (Throwable unused) {
            return -1L;
        }
    }

    public static void setCloudSettings(String str) {
        if (C2664ab.f13513g != null) {
            try {
                C2843eh.m15842a(C2664ab.f13513g, f13798s, f13771B, str);
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: j */
    public static String m16007j() {
        if (C2664ab.f13513g == null) {
            return null;
        }
        try {
            return C2843eh.m15840b(C2664ab.f13513g, f13798s, f13771B, (String) null);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: k */
    public static int m16006k() {
        try {
            if (m16008i() != -1) {
                return Integer.parseInt(String.valueOf(m16008i()));
            }
            return C2810dp.m16048a().m16046b(C2664ab.f13513g);
        } catch (Throwable unused) {
            return -1;
        }
    }

    /* renamed from: l */
    public static String m16005l() {
        try {
            if (m16009h() != null) {
                return m16009h();
            }
            return C2810dp.m16048a().m16045c(C2664ab.f13513g);
        } catch (Throwable unused) {
            return "unknown";
        }
    }

    /* renamed from: a */
    public static void m16029a(String str, String str2) {
        if (C2664ab.f13513g != null) {
            try {
                C2843eh.m15842a(C2664ab.f13513g, f13799t, str, str2);
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: a */
    public static String m16031a(String str) {
        if (C2664ab.f13513g == null) {
            return null;
        }
        try {
            return C2843eh.m15840b(C2664ab.f13513g, f13799t, str, (String) null);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: b */
    public static void m16022b(String str, String str2) {
        if (C2664ab.f13513g != null) {
            try {
                C2843eh.m15842a(C2664ab.f13513g, f13799t, str, str2);
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: b */
    public static String m16024b(String str) {
        if (C2664ab.f13513g == null) {
            return null;
        }
        try {
            return C2843eh.m15840b(C2664ab.f13513g, f13799t, str, (String) null);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void setLastRoleName(String str) {
        if (C2664ab.f13513g != null) {
            try {
                C2843eh.m15842a(C2664ab.f13513g, f13799t, f13775F, str);
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: m */
    public static String m16004m() {
        if (C2664ab.f13513g == null) {
            return null;
        }
        try {
            return C2843eh.m15840b(C2664ab.f13513g, f13799t, f13775F, (String) null);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: n */
    public static long m16003n() {
        if (C2664ab.f13513g == null) {
            return 0L;
        }
        try {
            return C2843eh.m15841b(C2664ab.f13513g, f13799t, f13773D, 0L);
        } catch (Throwable unused) {
            return 0L;
        }
    }

    public static void setCollectNetInfoTime(long j) {
        if (C2664ab.f13513g != null) {
            try {
                C2843eh.m15843a(C2664ab.f13513g, f13799t, f13773D, j);
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: o */
    public static String m16002o() {
        if (C2664ab.f13513g == null) {
            return null;
        }
        try {
            return C2843eh.m15840b(C2664ab.f13513g, f13799t, f13774E, (String) null);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void setDeepLink(String str) {
        if (C2664ab.f13513g != null) {
            try {
                C2843eh.m15842a(C2664ab.f13513g, f13799t, f13774E, str);
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: p */
    public static String m16001p() {
        if (C2664ab.f13513g == null) {
            return "";
        }
        try {
            return C2843eh.m15840b(C2664ab.f13513g, f13801v, f13776G, "");
        } catch (Throwable unused) {
            return "";
        }
    }

    public static void setAccountId(String str) {
        if (C2664ab.f13513g != null) {
            try {
                C2843eh.m15842a(C2664ab.f13513g, f13801v, f13776G, str);
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: c */
    public static String m16018c(String str) {
        if (C2664ab.f13513g == null) {
            return "";
        }
        try {
            Context context = C2664ab.f13513g;
            return C2843eh.m15840b(context, f13801v, str + f13777H, "");
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: c */
    public static void m16017c(String str, String str2) {
        if (C2664ab.f13513g != null) {
            try {
                Context context = C2664ab.f13513g;
                C2843eh.m15842a(context, f13801v, str + f13777H, str2);
            } catch (Throwable unused) {
            }
        }
    }

    public static void setMissionId(String str) {
        if (C2664ab.f13513g != null) {
            try {
                C2843eh.m15842a(C2664ab.f13513g, f13801v, f13778I, str);
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: q */
    public static String m16000q() {
        if (C2664ab.f13513g == null) {
            return "";
        }
        try {
            return C2843eh.m15840b(C2664ab.f13513g, f13801v, f13778I, "");
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: r */
    public static void m15999r() {
        if (C2664ab.f13513g != null) {
            try {
                C2843eh.m15843a(C2664ab.f13513g, f13801v, f13779J, System.currentTimeMillis());
            } catch (Throwable unused) {
            }
        }
    }

    public static void setPushAppContext(String str) {
        if (C2664ab.f13513g != null) {
            try {
                C2843eh.m15842a(C2664ab.f13513g, f13802w, f13791l, str);
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: s */
    public static String m15998s() {
        if (C2664ab.f13513g == null) {
            return "";
        }
        try {
            return C2843eh.m15840b(C2664ab.f13513g, f13802w, f13791l, "");
        } catch (Throwable unused) {
            return "";
        }
    }

    public static void setPushSyncTokenLastTime(long j) {
        if (C2664ab.f13513g != null) {
            try {
                C2843eh.m15843a(C2664ab.f13513g, f13802w, f13792m, j);
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: t */
    public static long m15997t() {
        if (C2664ab.f13513g == null) {
            return 0L;
        }
        try {
            return C2843eh.m15841b(C2664ab.f13513g, f13802w, f13792m, 0L);
        } catch (Throwable unused) {
            return 0L;
        }
    }

    public static void setPushLastMsgId(String str) {
        if (C2664ab.f13513g != null) {
            try {
                C2843eh.m15842a(C2664ab.f13513g, f13802w, f13793n, str);
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: u */
    public static String m15996u() {
        if (C2664ab.f13513g == null) {
            return "";
        }
        try {
            return C2843eh.m15840b(C2664ab.f13513g, f13802w, f13793n, "");
        } catch (Throwable unused) {
            return "";
        }
    }
}
