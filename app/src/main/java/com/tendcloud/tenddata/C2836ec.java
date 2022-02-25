package com.tendcloud.tenddata;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.http.Headers;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellLocation;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.NeighboringCellInfo;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.cyjh.mobileanjian.ipc.AppAgent;
import com.liulishuo.filedownloader.model.ConnectionModel;
import com.stripe.android.model.SourceCardData;
import com.stripe.android.view.ShippingInfoWidget;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.apache.commons.logging.LogFactory;
import org.apache.tools.ant.taskdefs.condition.C3209Os;
import org.apache.tools.ant.taskdefs.optional.vss.MSVSSConstants;
import org.apache.tools.ant.util.ProxySetup;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ec */
/* loaded from: classes2.dex */
public class C2836ec {

    /* renamed from: a */
    static final String f13836a = "www.talkingdata.net";

    /* renamed from: b */
    static final int f13837b = 80;

    /* renamed from: c */
    private static TelephonyManager f13838c = null;

    /* renamed from: h */
    private static final long f13843h = 300000;

    /* renamed from: d */
    private static final String[] f13839d = {"UNKNOWN", "GPRS", "EDGE", "UMTS", "CDMA", "EVDO_0", "EVDO_A", "1xRTT", "HSDPA", "HSUPA", "HSPA", "IDEN", "EVDO_B", "LTE", "EHRPD", "HSPAP"};

    /* renamed from: e */
    private static final String[] f13840e = {"NONE", "GSM", "CDMA", "SIP"};

    /* renamed from: f */
    private static RunnableC2837a f13841f = null;

    /* renamed from: g */
    private static boolean f13842g = false;

    /* renamed from: i */
    private static long f13844i = -300000;

    /* renamed from: j */
    private static boolean f13845j = false;

    /* renamed from: k */
    private static C2838b f13846k = new C2838b();

    /* renamed from: b */
    private static String m15874b(int i) {
        switch (i) {
            case 0:
            default:
                return "UNKNOWN";
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
            case 16:
                return AppAgent.f8193e;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 17:
                return AppAgent.f8194f;
            case 13:
            case 18:
                return AppAgent.f8195g;
        }
    }

    /* renamed from: a */
    static void m15884a(Context context) {
        try {
            f13838c = (TelephonyManager) context.getSystemService(ShippingInfoWidget.f12563f);
        } catch (Throwable unused) {
        }
    }

    /* renamed from: b */
    public static String m15873b(Context context) {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress nextElement = inetAddresses.nextElement();
                    if (!nextElement.isLoopbackAddress() && (nextElement instanceof Inet4Address)) {
                        return nextElement.getHostAddress().toString();
                    }
                }
            }
            return null;
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x006f, code lost:
        if (r6 == null) goto L_0x007c;
     */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean m15869c(android.content.Context r6) {
        /*
            java.lang.String r0 = "android.permission.ACCESS_NETWORK_STATE"
            boolean r0 = com.tendcloud.tenddata.C2855es.m15792b(r6, r0)     // Catch: Throwable -> 0x0078
            r1 = 0
            if (r0 == 0) goto L_0x0030
            java.lang.String r0 = "connectivity"
            java.lang.Object r6 = r6.getSystemService(r0)     // Catch: Throwable -> 0x0078
            android.net.ConnectivityManager r6 = (android.net.ConnectivityManager) r6     // Catch: Throwable -> 0x0078
            android.net.NetworkInfo r0 = r6.getActiveNetworkInfo()     // Catch: Throwable -> 0x0078
            if (r0 == 0) goto L_0x001c
            boolean r6 = r0.isConnected()     // Catch: Throwable -> 0x0078
            return r6
        L_0x001c:
            android.net.NetworkInfo r6 = r6.getNetworkInfo(r1)     // Catch: Throwable -> 0x0078
            if (r6 == 0) goto L_0x002f
            android.net.NetworkInfo$State r6 = r6.getState()     // Catch: Throwable -> 0x0078
            android.net.NetworkInfo$State r0 = android.net.NetworkInfo.State.UNKNOWN     // Catch: Throwable -> 0x0078
            boolean r6 = r6.equals(r0)     // Catch: Throwable -> 0x0078
            if (r6 == 0) goto L_0x002f
            goto L_0x0030
        L_0x002f:
            return r1
        L_0x0030:
            long r2 = android.os.SystemClock.elapsedRealtime()     // Catch: Throwable -> 0x0078
            long r4 = com.tendcloud.tenddata.C2836ec.f13844i     // Catch: Throwable -> 0x0078
            long r2 = r2 - r4
            r4 = 300000(0x493e0, double:1.482197E-318)
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x007c
            long r2 = android.os.SystemClock.elapsedRealtime()     // Catch: Throwable -> 0x0078
            com.tendcloud.tenddata.C2836ec.f13844i = r2     // Catch: Throwable -> 0x0078
            r6 = 0
            boolean r0 = m15887a()     // Catch: Throwable -> 0x006d
            if (r0 == 0) goto L_0x005a
            java.net.Socket r0 = new java.net.Socket     // Catch: Throwable -> 0x006d
            java.lang.String r2 = android.net.Proxy.getDefaultHost()     // Catch: Throwable -> 0x006d
            int r3 = android.net.Proxy.getDefaultPort()     // Catch: Throwable -> 0x006d
            r0.<init>(r2, r3)     // Catch: Throwable -> 0x006d
            r6 = r0
            goto L_0x0064
        L_0x005a:
            java.net.Socket r0 = new java.net.Socket     // Catch: Throwable -> 0x006d
            java.lang.String r2 = "www.talkingdata.net"
            r3 = 80
            r0.<init>(r2, r3)     // Catch: Throwable -> 0x006d
            r6 = r0
        L_0x0064:
            r0 = 1
            com.tendcloud.tenddata.C2836ec.f13842g = r0     // Catch: Throwable -> 0x006d
        L_0x0067:
            r6.close()     // Catch: Throwable -> 0x007c
            goto L_0x007c
        L_0x006b:
            r0 = move-exception
            goto L_0x0072
        L_0x006d:
            com.tendcloud.tenddata.C2836ec.f13842g = r1     // Catch: all -> 0x006b
            if (r6 == 0) goto L_0x007c
            goto L_0x0067
        L_0x0072:
            if (r6 == 0) goto L_0x0077
            r6.close()     // Catch: Throwable -> 0x0077
        L_0x0077:
            throw r0     // Catch: Throwable -> 0x0078
        L_0x0078:
            r6 = move-exception
            com.tendcloud.tenddata.C2933hb.postSDKError(r6)
        L_0x007c:
            boolean r6 = com.tendcloud.tenddata.C2836ec.f13842g
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tendcloud.tenddata.C2836ec.m15869c(android.content.Context):boolean");
    }

    /* renamed from: d */
    public static boolean m15868d(Context context) {
        NetworkInfo activeNetworkInfo;
        try {
            if (!C2855es.m15792b(context, "android.permission.ACCESS_NETWORK_STATE") || (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) == null) {
                return false;
            }
            return activeNetworkInfo.isAvailable();
        } catch (Throwable unused) {
        }
        return false;
    }

    /* renamed from: e */
    public static boolean m15867e(Context context) {
        try {
            if (m15862j(context)) {
                return true;
            }
            return ((WifiManager) context.getSystemService("wifi")).isWifiEnabled();
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return false;
        }
    }

    /* renamed from: f */
    public static boolean m15866f(Context context) {
        try {
            if (f13838c == null) {
                m15884a(context);
            }
            return f13838c.getSimState() == 5;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: g */
    public static boolean m15865g(Context context) {
        try {
            Method declaredMethod = ConnectivityManager.class.getDeclaredMethod("getMobileDataEnabled", new Class[0]);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke((ConnectivityManager) context.getSystemService("connectivity"), new Object[0])).booleanValue();
        } catch (Throwable unused) {
            return true;
        }
    }

    /* renamed from: h */
    public static boolean m15864h(Context context) {
        try {
            return C2855es.m15807a(17) ? Settings.System.getInt(context.getContentResolver(), "airplane_mode_on", 0) == 1 : Settings.System.getInt(context.getContentResolver(), "airplane_mode_on", 0) == 1;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: i */
    public static boolean m15863i(Context context) {
        try {
            if (f13838c == null) {
                m15884a(context);
            }
            int simState = f13838c.getSimState();
            return (1 == simState || simState == 0) ? false : true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: j */
    public static boolean m15862j(Context context) {
        NetworkInfo activeNetworkInfo;
        if (context == null) {
            if (C2664ab.f13513g == null) {
                return false;
            }
            context = C2664ab.f13513g;
        }
        try {
            if (!C2855es.m15792b(context, "android.permission.ACCESS_NETWORK_STATE") || (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) == null || 1 != activeNetworkInfo.getType()) {
                return false;
            }
            return activeNetworkInfo.isConnected();
        } catch (Throwable unused) {
        }
        return false;
    }

    /* renamed from: k */
    public static boolean m15861k(Context context) {
        if (context == null) {
            if (C2664ab.f13513g == null) {
                return false;
            }
            context = C2664ab.f13513g;
        }
        try {
            if (f13838c == null) {
                m15884a(context);
            }
            return f13838c.getDataState() == 2;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m15887a() {
        try {
            if (C2855es.m15807a(11)) {
                return !TextUtils.isEmpty(System.getProperty(ProxySetup.HTTP_PROXY_HOST));
            }
            return !TextUtils.isEmpty(Proxy.getDefaultHost());
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: l */
    public static String m15860l(Context context) {
        return !m15869c(context) ? "OFFLINE" : m15862j(context) ? "WIFI" : m15886a(m15857o(context));
    }

    /* renamed from: m */
    public static String m15859m(Context context) {
        return !m15869c(context) ? "offline" : m15862j(context) ? "wifi" : "cellular";
    }

    /* renamed from: n */
    public static String m15858n(Context context) {
        if (context == null) {
            if (C2664ab.f13513g == null) {
                return "UNKNOWN";
            }
            context = C2664ab.f13513g;
        }
        return !m15869c(context) ? "UNKNOWN" : m15862j(context) ? "WIFI" : m15874b(m15857o(context));
    }

    /* renamed from: o */
    public static int m15857o(Context context) {
        if (context == null) {
            try {
                if (C2664ab.f13513g == null) {
                    return 0;
                }
                context = C2664ab.f13513g;
            } catch (Throwable unused) {
                return 0;
            }
        }
        if (f13838c == null) {
            m15884a(context);
        }
        return f13838c.getNetworkType();
    }

    /* renamed from: a */
    private static String m15886a(int i) {
        if (i >= 0) {
            String[] strArr = f13839d;
            if (i < strArr.length) {
                return strArr[i];
            }
        }
        return String.valueOf(i);
    }

    /* renamed from: c */
    private static String m15870c(int i) {
        if (i >= 0) {
            String[] strArr = f13840e;
            if (i < strArr.length) {
                return strArr[i];
            }
        }
        return String.valueOf(i);
    }

    /* renamed from: p */
    public static String m15856p(Context context) {
        try {
            if (f13838c == null) {
                m15884a(context);
            }
            return f13838c.getNetworkOperator();
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: q */
    public static String m15855q(Context context) {
        try {
            if (f13838c == null) {
                m15884a(context);
            }
            return f13838c.getSimOperator();
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: r */
    public static String m15854r(Context context) {
        try {
            if (f13838c == null) {
                m15884a(context);
            }
            return f13838c.getNetworkCountryIso();
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: s */
    public static String m15853s(Context context) {
        try {
            if (f13838c == null) {
                m15884a(context);
            }
            return f13838c.getSimCountryIso();
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: t */
    public static String m15852t(Context context) {
        try {
            if ((!C2855es.m15807a(23) || context.checkSelfPermission("android.permission.READ_PHONE_STATE") == 0) && C2855es.m15792b(context, "android.permission.READ_PHONE_STATE") && Build.VERSION.SDK_INT >= 18) {
                if (f13838c == null) {
                    m15884a(context);
                }
                return f13838c.getGroupIdLevel1();
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    /* renamed from: u */
    public static String m15851u(Context context) {
        try {
            if (f13838c == null) {
                m15884a(context);
            }
            return f13838c.getNetworkOperatorName();
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: v */
    public static String m15850v(Context context) {
        try {
            if (f13838c == null) {
                m15884a(context);
            }
            return f13838c.getSimOperatorName();
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: w */
    public static JSONArray m15849w(Context context) {
        JSONArray jSONArray = new JSONArray();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", "wifi");
            jSONObject.put("available", m15867e(context));
            jSONObject.put("connected", m15862j(context));
            jSONObject.put(MSVSSConstants.TIME_CURRENT, m15897A(context));
            jSONObject.put("scannable", m15896B(context));
            jSONObject.put("configured", m15846z(context));
            jSONArray.put(jSONObject);
        } catch (Throwable unused) {
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("type", "cellular");
            jSONObject2.put("available", m15866f(context));
            jSONObject2.put("connected", m15861k(context));
            jSONObject2.put(MSVSSConstants.TIME_CURRENT, m15883a(context, false));
            jSONObject2.put("scannable", m15848x(context));
            jSONArray.put(jSONObject2);
        } catch (Throwable unused2) {
        }
        if (jSONArray.length() > 0) {
            return jSONArray;
        }
        return null;
    }

    /* renamed from: a */
    public static JSONArray m15883a(Context context, boolean z) {
        CdmaCellLocation cdmaCellLocation;
        if (context == null) {
            try {
                if (C2664ab.f13513g == null) {
                    return null;
                }
                context = C2664ab.f13513g;
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
                return null;
            }
        }
        boolean z2 = true;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", m15857o(context));
        jSONObject.put("mcc", m15856p(context));
        jSONObject.put("operator", m15851u(context));
        jSONObject.put(SourceCardData.f12158h, m15854r(context));
        if (!(!C2855es.m15807a(23) || context.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0 || context.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0)) {
            z2 = false;
        }
        if (!C2855es.m15792b(context, "android.permission.ACCESS_COARSE_LOCATION") && !C2855es.m15792b(context, "android.permission.ACCESS_FINE_LOCATION")) {
            z2 = false;
        }
        if (z2) {
            if (f13838c == null) {
                m15884a(context);
            }
            if (C2855es.f13913c || z) {
                CellLocation cellLocation = f13838c.getCellLocation();
                if (cellLocation instanceof GsmCellLocation) {
                    GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                    if (gsmCellLocation != null) {
                        jSONObject.put("systemId", gsmCellLocation.getLac());
                        jSONObject.put("networkId", gsmCellLocation.getCid());
                        if (C2855es.m15807a(9)) {
                            jSONObject.put("basestationId", gsmCellLocation.getPsc());
                        }
                    }
                } else if ((cellLocation instanceof CdmaCellLocation) && (cdmaCellLocation = (CdmaCellLocation) cellLocation) != null) {
                    jSONObject.put("systemId", cdmaCellLocation.getSystemId());
                    jSONObject.put("networkId", cdmaCellLocation.getNetworkId());
                    jSONObject.put("basestationId", cdmaCellLocation.getBaseStationId());
                    jSONObject.put(Headers.LOCATION, m15885a(cdmaCellLocation.getBaseStationLatitude(), cdmaCellLocation.getBaseStationLongitude()));
                }
            }
        }
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(jSONObject);
        return jSONArray;
    }

    @TargetApi(18)
    /* renamed from: x */
    public static JSONArray m15848x(Context context) {
        List<NeighboringCellInfo> neighboringCellInfo;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        String str;
        CellSignalStrengthGsm cellSignalStrengthGsm;
        if (context == null) {
            if (C2664ab.f13513g == null) {
                return null;
            }
            context = C2664ab.f13513g;
        }
        if (C2855es.m15807a(23) && context.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") != 0) {
            return null;
        }
        if (C2855es.m15792b(context, "android.permission.ACCESS_COARSE_LOCATION") || C2855es.m15792b(context, "android.permission.ACCESS_FINE_LOCATION")) {
            try {
                if (f13838c == null) {
                    m15884a(context);
                }
                JSONArray jSONArray = new JSONArray();
                if (C2855es.m15807a(17)) {
                    List<CellInfo> allCellInfo = f13838c.getAllCellInfo();
                    if (allCellInfo != null) {
                        for (CellInfo cellInfo : allCellInfo) {
                            try {
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("registered", cellInfo.isRegistered());
                                jSONObject.put("ts", cellInfo.getTimeStamp());
                                if (cellInfo instanceof CellInfoGsm) {
                                    str = "GSM";
                                    CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
                                    CellIdentityGsm cellIdentity = cellInfoGsm.getCellIdentity();
                                    i4 = cellIdentity.getLac();
                                    i3 = cellIdentity.getCid();
                                    int mcc = cellIdentity.getMcc();
                                    i5 = cellIdentity.getMnc();
                                    i = mcc;
                                    i2 = -1;
                                    cellSignalStrengthGsm = cellInfoGsm.getCellSignalStrength();
                                } else if (cellInfo instanceof CellInfoCdma) {
                                    str = "CDMA";
                                    CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
                                    CellIdentityCdma cellIdentity2 = cellInfoCdma.getCellIdentity();
                                    i4 = cellIdentity2.getSystemId();
                                    i3 = cellIdentity2.getNetworkId();
                                    i2 = cellIdentity2.getBasestationId();
                                    CellSignalStrengthCdma cellSignalStrength = cellInfoCdma.getCellSignalStrength();
                                    jSONObject.put("cdmaDbm", cellSignalStrength.getCdmaDbm());
                                    jSONObject.put("cdmaDbm", cellSignalStrength.getCdmaDbm());
                                    jSONObject.put("cdmaEcio", cellSignalStrength.getCdmaEcio());
                                    jSONObject.put("evdoDbm", cellSignalStrength.getEvdoDbm());
                                    jSONObject.put("evdoEcio", cellSignalStrength.getEvdoEcio());
                                    jSONObject.put("evdoSnr", cellSignalStrength.getEvdoSnr());
                                    jSONObject.put(Headers.LOCATION, m15885a(cellIdentity2.getLatitude(), cellIdentity2.getLongitude()));
                                    i5 = -1;
                                    i = -1;
                                    cellSignalStrengthGsm = cellSignalStrength;
                                } else if (cellInfo instanceof CellInfoWcdma) {
                                    str = "WCDMA";
                                    CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
                                    CellIdentityWcdma cellIdentity3 = cellInfoWcdma.getCellIdentity();
                                    i4 = cellIdentity3.getLac();
                                    i3 = cellIdentity3.getCid();
                                    i2 = cellIdentity3.getPsc();
                                    i = cellIdentity3.getMcc();
                                    i5 = cellIdentity3.getMnc();
                                    cellSignalStrengthGsm = cellInfoWcdma.getCellSignalStrength();
                                } else if (cellInfo instanceof CellInfoLte) {
                                    str = "LTE";
                                    CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
                                    CellIdentityLte cellIdentity4 = cellInfoLte.getCellIdentity();
                                    i4 = cellIdentity4.getTac();
                                    i3 = cellIdentity4.getPci();
                                    i2 = cellIdentity4.getCi();
                                    i = cellIdentity4.getMcc();
                                    i5 = cellIdentity4.getMnc();
                                    cellSignalStrengthGsm = cellInfoLte.getCellSignalStrength();
                                } else {
                                    cellSignalStrengthGsm = null;
                                    str = null;
                                    i5 = -1;
                                    i4 = -1;
                                    i3 = -1;
                                    i2 = -1;
                                    i = -1;
                                }
                                if (i4 != -1) {
                                    jSONObject.put("systemId", i4);
                                }
                                if (i3 != -1) {
                                    jSONObject.put("networkId", i3);
                                }
                                if (i2 != -1) {
                                    jSONObject.put("basestationId", i2);
                                }
                                if (i != -1) {
                                    jSONObject.put("mcc", i);
                                }
                                if (i5 != -1) {
                                    jSONObject.put("mnc", i5);
                                }
                                if (cellSignalStrengthGsm != null) {
                                    jSONObject.put("asuLevel", cellSignalStrengthGsm.getAsuLevel());
                                    jSONObject.put("dbm", cellSignalStrengthGsm.getDbm());
                                }
                                jSONObject.put("type", str);
                                jSONArray.put(jSONObject);
                            } catch (Throwable unused) {
                            }
                        }
                    }
                } else if (C2855es.m15807a(5) && C2855es.f13913c && (neighboringCellInfo = f13838c.getNeighboringCellInfo()) != null) {
                    for (NeighboringCellInfo neighboringCellInfo2 : neighboringCellInfo) {
                        try {
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("systemId", neighboringCellInfo2.getLac());
                            jSONObject2.put("netId", neighboringCellInfo2.getCid());
                            jSONObject2.put("basestationId", neighboringCellInfo2.getPsc());
                            jSONObject2.put("asuLevel", neighboringCellInfo2.getRssi());
                            jSONObject2.put("type", neighboringCellInfo2.getNetworkType());
                            jSONArray.put(jSONObject2);
                        } catch (Throwable unused2) {
                        }
                    }
                }
                return m15876a(jSONArray, 20);
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }
        return null;
    }

    /* renamed from: a */
    public static JSONArray m15876a(JSONArray jSONArray, int i) {
        try {
            if (jSONArray.length() <= 0) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                arrayList.add(jSONArray.optJSONObject(i2));
            }
            Collections.sort(arrayList, new C2839ed());
            if (arrayList.size() <= i) {
                i = arrayList.size();
            }
            return new JSONArray((Collection) arrayList.subList(0, i));
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: a */
    public static JSONObject m15885a(int i, int i2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("lat", i);
            jSONObject.put("lng", i2);
            jSONObject.put("unit", "qd");
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    /* renamed from: y */
    public static String m15847y(Context context) {
        WifiInfo connectionInfo;
        try {
            if (!C2855es.m15792b(context, "android.permission.ACCESS_WIFI_STATE")) {
                return null;
            }
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (!wifiManager.isWifiEnabled() || !m15862j(context) || (connectionInfo = wifiManager.getConnectionInfo()) == null || connectionInfo.getBSSID() == null) {
                return null;
            }
            return connectionInfo.getSSID();
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: z */
    public static JSONArray m15846z(Context context) {
        List<WifiConfiguration> configuredNetworks;
        try {
            if (!C2855es.m15792b(context, "android.permission.ACCESS_WIFI_STATE") || (configuredNetworks = ((WifiManager) context.getSystemService("wifi")).getConfiguredNetworks()) == null) {
                return null;
            }
            JSONArray jSONArray = new JSONArray();
            for (WifiConfiguration wifiConfiguration : configuredNetworks) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("networkId", wifiConfiguration.networkId);
                    jSONObject.put(LogFactory.PRIORITY_KEY, wifiConfiguration.priority);
                    jSONObject.put("name", wifiConfiguration.SSID);
                    jSONObject.put(ConnectionModel.f10389a, wifiConfiguration.BSSID);
                    jSONObject.put("allowedKeyManagement", m15877a(wifiConfiguration.allowedKeyManagement));
                    jSONObject.put("allowedAuthAlgorithms", m15877a(wifiConfiguration.allowedAuthAlgorithms));
                    jSONObject.put("allowedGroupCiphers", m15877a(wifiConfiguration.allowedGroupCiphers));
                    jSONObject.put("allowedPairwiseCiphers", m15877a(wifiConfiguration.allowedPairwiseCiphers));
                    jSONArray.put(jSONObject);
                } catch (Throwable unused) {
                }
            }
            return m15871b(jSONArray, 30);
        } catch (Throwable unused2) {
            return null;
        }
    }

    /* renamed from: b */
    public static JSONArray m15871b(JSONArray jSONArray, int i) {
        try {
            if (jSONArray.length() <= 0) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                arrayList.add(jSONArray.optJSONObject(i2));
            }
            Collections.sort(arrayList, new C2840ee());
            if (arrayList.size() <= i) {
                i = arrayList.size();
            }
            return new JSONArray((Collection) arrayList.subList(0, i));
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: A */
    public static JSONArray m15897A(Context context) {
        WifiInfo connectionInfo;
        try {
            if (!C2855es.m15792b(context, "android.permission.ACCESS_WIFI_STATE")) {
                return null;
            }
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (!wifiManager.isWifiEnabled() || (connectionInfo = wifiManager.getConnectionInfo()) == null || connectionInfo.getBSSID() == null) {
                return null;
            }
            String bssid = connectionInfo.getBSSID();
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("name", connectionInfo.getSSID());
                jSONObject.put(ConnectionModel.f10389a, bssid);
                jSONObject.put("level", connectionInfo.getRssi());
                jSONObject.put("hidden", connectionInfo.getHiddenSSID());
                jSONObject.put("ip", connectionInfo.getIpAddress());
                jSONObject.put("speed", connectionInfo.getLinkSpeed());
                jSONObject.put("networkId", connectionInfo.getNetworkId());
                jSONObject.put(C3209Os.FAMILY_MAC, connectionInfo.getMacAddress());
                DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();
                if (dhcpInfo != null) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("dns1", dhcpInfo.dns1);
                    jSONObject2.put("dns2", dhcpInfo.dns2);
                    jSONObject2.put("gw", dhcpInfo.gateway);
                    jSONObject2.put("ip", dhcpInfo.ipAddress);
                    jSONObject2.put("mask", dhcpInfo.netmask);
                    jSONObject2.put("server", dhcpInfo.serverAddress);
                    jSONObject2.put("leaseDuration", dhcpInfo.leaseDuration);
                    jSONObject.put("dhcp", jSONObject2);
                }
            } catch (Throwable unused) {
            }
            jSONArray.put(jSONObject);
            return jSONArray;
        } catch (Throwable unused2) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.ec$b */
    /* loaded from: classes2.dex */
    public static class C2838b extends BroadcastReceiver {
        private CountDownLatch latch = new CountDownLatch(1);

        C2838b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.net.wifi.SCAN_RESULTS".equals(intent.getAction()) && this.latch.getCount() > 0) {
                this.latch.countDown();
            }
        }

        void reset() {
            this.latch = new CountDownLatch(1);
        }

        void await() {
            try {
                this.latch.await(2000L, TimeUnit.MILLISECONDS);
            } catch (Throwable th) {
                C2811dq.eForInternal(th);
            }
        }
    }

    /* renamed from: B */
    public static synchronized JSONArray m15896B(Context context) {
        synchronized (C2836ec.class) {
            if (!C2855es.f13913c) {
                return null;
            }
            try {
                if (C2855es.m15792b(context, "android.permission.ACCESS_WIFI_STATE")) {
                    WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
                    if (wifiManager.isWifiEnabled() || (Build.VERSION.SDK_INT >= 18 && wifiManager.isScanAlwaysAvailable())) {
                        if (C2855es.m15792b(context, "android.permission.CHANGE_WIFI_STATE") && !f13845j) {
                            try {
                                context.registerReceiver(f13846k, new IntentFilter("android.net.wifi.SCAN_RESULTS"));
                            } catch (Throwable unused) {
                            }
                            f13845j = true;
                        }
                        wifiManager.startScan();
                        f13846k.await();
                        List<ScanResult> scanResults = wifiManager.getScanResults();
                        f13846k.reset();
                        if (scanResults != null) {
                            ArrayList arrayList = new ArrayList();
                            for (ScanResult scanResult : scanResults) {
                                if (scanResult.level >= -85) {
                                    JSONObject jSONObject = new JSONObject();
                                    try {
                                        jSONObject.put(ConnectionModel.f10389a, scanResult.BSSID);
                                        jSONObject.put("name", scanResult.SSID);
                                        jSONObject.put("level", scanResult.level);
                                        jSONObject.put("freq", scanResult.frequency);
                                        if (C2855es.m15807a(17)) {
                                            jSONObject.put("ts", scanResult.timestamp);
                                            jSONObject.put("scanTs", (System.currentTimeMillis() - SystemClock.elapsedRealtime()) + (scanResult.timestamp / 1000));
                                        }
                                        arrayList.add(jSONObject);
                                    } catch (Throwable unused2) {
                                    }
                                }
                            }
                            return m15878a(arrayList, 20);
                        }
                    }
                }
            } catch (Throwable unused3) {
            }
            return null;
        }
    }

    /* renamed from: a */
    public static JSONArray m15878a(ArrayList arrayList, int i) {
        try {
            Collections.sort(arrayList, new C2841ef());
            if (arrayList.size() <= i) {
                i = arrayList.size();
            }
            return new JSONArray((Collection) arrayList.subList(0, i));
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: a */
    private static JSONArray m15877a(BitSet bitSet) {
        if (bitSet == null || bitSet.cardinality() < 1) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        int i = 0;
        while (true) {
            int nextSetBit = bitSet.nextSetBit(i);
            if (nextSetBit < 0) {
                return jSONArray;
            }
            jSONArray.put(nextSetBit);
            i = nextSetBit + 1;
        }
    }

    /* renamed from: C */
    public static JSONArray m15895C(Context context) {
        JSONArray jSONArray = new JSONArray();
        if (context == null) {
            if (C2664ab.f13513g == null) {
                return jSONArray;
            }
            context = C2664ab.f13513g;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(ShippingInfoWidget.f12563f);
            ArrayList arrayList = new ArrayList();
            if (C2855es.m15807a(22)) {
                SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService("telephony_subscription_service");
                try {
                    JSONObject a = m15882a(telephonyManager, subscriptionManager, 0);
                    if (C2855es.m15792b(context, "android.permission.READ_PHONE_STATE") && telephonyManager != null) {
                        a.put("imei", telephonyManager.getDeviceId());
                    }
                    if (a.length() > 0) {
                        jSONArray.put(a);
                    }
                } catch (Throwable unused) {
                }
                JSONObject a2 = m15882a(telephonyManager, subscriptionManager, 1);
                if (C2855es.m15792b(context, "android.permission.READ_PHONE_STATE") && telephonyManager != null) {
                    a2.put("imei", (!C2855es.m15807a(23) || telephonyManager.getPhoneCount() != 2) ? null : telephonyManager.getDeviceId(1));
                }
                if (a2.length() > 0) {
                    jSONArray.put(a2);
                }
            } else {
                String str = "";
                if (C2855es.m15792b(context, "android.permission.READ_PHONE_STATE") && telephonyManager != null) {
                    str = telephonyManager.getDeviceId();
                }
                if (m15872b(str.trim()).booleanValue()) {
                    arrayList.add(str.trim());
                    JSONObject a3 = m15881a(telephonyManager, str);
                    if (a3 != null) {
                        jSONArray.put(a3);
                    }
                }
                try {
                    TelephonyManager telephonyManager2 = (TelephonyManager) context.getSystemService("phone1");
                    String str2 = "";
                    if (C2855es.m15792b(context, "android.permission.READ_PHONE_STATE") && telephonyManager2 != null) {
                        str2 = telephonyManager2.getDeviceId();
                    }
                    if (str2 != null && m15872b(str2).booleanValue() && !arrayList.contains(str2)) {
                        arrayList.add(str2);
                        JSONObject a4 = m15881a(telephonyManager2, str2);
                        if (a4 != null) {
                            jSONArray.put(a4);
                        }
                    }
                } catch (Throwable unused2) {
                }
                try {
                    TelephonyManager telephonyManager3 = (TelephonyManager) context.getSystemService("phone2");
                    String str3 = "";
                    if (C2855es.m15792b(context, "android.permission.READ_PHONE_STATE") && telephonyManager3 != null) {
                        str3 = telephonyManager3.getDeviceId();
                    }
                    if (str3 != null && m15872b(str3).booleanValue() && !arrayList.contains(str3)) {
                        arrayList.add(str3);
                        JSONObject a5 = m15881a(telephonyManager3, str3);
                        if (a5 != null) {
                            jSONArray.put(a5);
                        }
                    }
                } catch (Throwable unused3) {
                }
                JSONArray J = m15888J(context);
                JSONArray I = m15889I(context);
                if (I != null) {
                    J = I;
                }
                JSONArray H = m15890H(context);
                if (H != null) {
                    J = H;
                }
                J = m15891G(context);
                if (J != null) {
                }
                if (J != null && J.length() > 0) {
                    for (int i = 0; i < J.length(); i++) {
                        JSONObject jSONObject = J.getJSONObject(i);
                        String string = jSONObject.getString("imei");
                        if (!arrayList.contains(string)) {
                            arrayList.add(string);
                            jSONArray.put(jSONObject);
                        }
                    }
                }
            }
        } catch (Throwable unused4) {
        }
        return jSONArray;
    }

    /* renamed from: D */
    public static int m15894D(Context context) {
        try {
            if (!C2855es.m15807a(23)) {
                return m15895C(context).length();
            }
            if (f13838c == null) {
                m15884a(context);
            }
            return f13838c.getPhoneCount();
        } catch (Throwable unused) {
            return 0;
        }
    }

    /* renamed from: a */
    private static JSONObject m15881a(TelephonyManager telephonyManager, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("imei", str.trim());
            jSONObject.put("subscriberId", telephonyManager.getSubscriberId() == null ? "" : telephonyManager.getSubscriberId());
            jSONObject.put("simSerialNumber", telephonyManager.getSimSerialNumber() == null ? "" : telephonyManager.getSimSerialNumber());
            jSONObject.put("dataState", telephonyManager.getDataState());
            jSONObject.put("networkType", m15886a(telephonyManager.getNetworkType()));
            jSONObject.put("networkOperator", telephonyManager.getNetworkOperator());
            jSONObject.put("phoneType", m15870c(telephonyManager.getPhoneType()));
            jSONObject.put("simOperator", telephonyManager.getSimOperator() == null ? "" : telephonyManager.getSimOperator());
            jSONObject.put("simOperatorName", telephonyManager.getSimOperatorName() == null ? "" : telephonyManager.getSimOperatorName());
            jSONObject.put("simCountryIso", telephonyManager.getSimCountryIso() == null ? "" : telephonyManager.getSimCountryIso());
            return jSONObject;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: a */
    private static JSONObject m15882a(TelephonyManager telephonyManager, SubscriptionManager subscriptionManager, int i) {
        SubscriptionInfo activeSubscriptionInfoForSimSlotIndex;
        JSONObject jSONObject = new JSONObject();
        try {
            if (C2855es.m15807a(22) && (activeSubscriptionInfoForSimSlotIndex = subscriptionManager.getActiveSubscriptionInfoForSimSlotIndex(i)) != null) {
                jSONObject.put("simSerialNumber", activeSubscriptionInfoForSimSlotIndex.getIccId() == null ? "" : activeSubscriptionInfoForSimSlotIndex.getIccId());
                jSONObject.put("simOperator", activeSubscriptionInfoForSimSlotIndex.getMcc() + ResultTypeConstant.f7213z + activeSubscriptionInfoForSimSlotIndex.getMnc());
                jSONObject.put("simOperatorName", activeSubscriptionInfoForSimSlotIndex.getCarrierName() == null ? "" : activeSubscriptionInfoForSimSlotIndex.getCarrierName());
                jSONObject.put("simCountryIso", activeSubscriptionInfoForSimSlotIndex.getCountryIso() == null ? "" : activeSubscriptionInfoForSimSlotIndex.getCountryIso());
                activeSubscriptionInfoForSimSlotIndex.getSubscriptionId();
                String subscriberId = telephonyManager.getSubscriberId();
                if (subscriberId == null) {
                    subscriberId = "";
                }
                jSONObject.put("subscriberId", subscriberId);
            }
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    /* renamed from: E */
    public static JSONObject m15893E(Context context) {
        try {
            JSONObject jSONObject = new JSONObject();
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(ShippingInfoWidget.f12563f);
                jSONObject.put("dataState", telephonyManager.getDataState());
                jSONObject.put("networkType", m15886a(telephonyManager.getNetworkType()));
                jSONObject.put("networkOperator", telephonyManager.getNetworkOperator());
                jSONObject.put("phoneType", m15870c(telephonyManager.getPhoneType()));
                return jSONObject;
            } catch (Throwable unused) {
                return jSONObject;
            }
        } catch (Throwable unused2) {
            return null;
        }
    }

    /* renamed from: a */
    private static Boolean m15879a(String str) {
        char c = '0';
        try {
            if (str.length() > 0) {
                c = str.charAt(0);
            }
            for (int i = 0; i < str.length(); i++) {
                if (c != str.charAt(i)) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: b */
    private static Boolean m15872b(String str) {
        try {
            Integer valueOf = Integer.valueOf(str.length());
            if (valueOf.intValue() > 10 && valueOf.intValue() < 20 && !m15879a(str.trim()).booleanValue()) {
                return true;
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    /* renamed from: G */
    private static JSONArray m15891G(Context context) {
        try {
            JSONArray jSONArray = new JSONArray();
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(ShippingInfoWidget.f12563f);
            Class<?> cls = Class.forName("com.android.internal.telephony.Phone");
            Field field = cls.getField("GEMINI_SIM_1");
            field.setAccessible(true);
            Integer num = (Integer) field.get(null);
            Field field2 = cls.getField("GEMINI_SIM_2");
            field2.setAccessible(true);
            Integer num2 = (Integer) field2.get(null);
            Method declaredMethod = TelephonyManager.class.getDeclaredMethod("getDeviceIdGemini", Integer.TYPE);
            if (!(telephonyManager == null || declaredMethod == null)) {
                String trim = ((String) declaredMethod.invoke(telephonyManager, num)).trim();
                String trim2 = ((String) declaredMethod.invoke(telephonyManager, num2)).trim();
                if (m15872b(trim).booleanValue()) {
                    jSONArray.put(m15880a(TelephonyManager.class, telephonyManager, num, trim, "Gemini"));
                }
                if (m15872b(trim2).booleanValue()) {
                    jSONArray.put(m15880a(TelephonyManager.class, telephonyManager, num2, trim2, "Gemini"));
                }
                return jSONArray;
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: H */
    private static JSONArray m15890H(Context context) {
        JSONObject a;
        JSONObject a2;
        try {
            JSONArray jSONArray = new JSONArray();
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(ShippingInfoWidget.f12563f);
            Class<?> cls = Class.forName("com.android.internal.telephony.Phone");
            Field field = cls.getField("GEMINI_SIM_1");
            field.setAccessible(true);
            Integer num = (Integer) field.get(null);
            Field field2 = cls.getField("GEMINI_SIM_2");
            field2.setAccessible(true);
            Integer num2 = (Integer) field2.get(null);
            Method method = TelephonyManager.class.getMethod("getDefault", Integer.TYPE);
            TelephonyManager telephonyManager2 = (TelephonyManager) method.invoke(telephonyManager, num);
            TelephonyManager telephonyManager3 = (TelephonyManager) method.invoke(telephonyManager, num2);
            String trim = telephonyManager2.getDeviceId().trim();
            String trim2 = telephonyManager3.getDeviceId().trim();
            if (m15872b(trim).booleanValue() && (a2 = m15881a(telephonyManager2, trim)) != null) {
                jSONArray.put(a2);
            }
            if (m15872b(trim2).booleanValue() && (a = m15881a(telephonyManager3, trim2)) != null) {
                jSONArray.put(a);
            }
            return jSONArray;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: I */
    private static JSONArray m15889I(Context context) {
        JSONObject a;
        JSONObject a2;
        try {
            JSONArray jSONArray = new JSONArray();
            Class<?> cls = Class.forName("com.android.internal.telephony.PhoneFactory");
            Method method = cls.getMethod("getServiceName", String.class, Integer.TYPE);
            Object[] objArr = {ShippingInfoWidget.f12563f, 1};
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(ShippingInfoWidget.f12563f);
            String trim = telephonyManager.getDeviceId().trim();
            TelephonyManager telephonyManager2 = (TelephonyManager) context.getSystemService((String) method.invoke(cls, objArr));
            String trim2 = telephonyManager2.getDeviceId().trim();
            if (m15872b(trim).booleanValue() && (a2 = m15881a(telephonyManager, trim)) != null) {
                jSONArray.put(a2);
            }
            if (m15872b(trim2).booleanValue() && (a = m15881a(telephonyManager2, trim2)) != null) {
                jSONArray.put(a);
            }
            return jSONArray;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: J */
    private static JSONArray m15888J(Context context) {
        try {
            JSONArray jSONArray = new JSONArray();
            Class<?> cls = Class.forName("android.telephony.MSimTelephonyManager");
            Object systemService = context.getSystemService("phone_msim");
            Method method = cls.getMethod("getDeviceId", Integer.TYPE);
            String trim = ((String) method.invoke(systemService, 0)).trim();
            String trim2 = ((String) method.invoke(systemService, 1)).trim();
            if (m15872b(trim).booleanValue()) {
                jSONArray.put(m15880a(cls, systemService, 0, trim, ""));
            }
            if (m15872b(trim2).booleanValue()) {
                jSONArray.put(m15880a(cls, systemService, 1, trim2, ""));
            }
            return jSONArray;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: a */
    private static JSONObject m15880a(Class cls, Object obj, Integer num, String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("imei", str);
        try {
            Method method = cls.getMethod("getSubscriberId" + str2, Integer.TYPE);
            jSONObject.put("subscriberId", method.invoke(obj, num) == null ? "" : ((String) method.invoke(obj, num)).trim());
        } catch (Throwable unused) {
        }
        try {
            Method method2 = cls.getMethod("getSimSerialNumber" + str2, Integer.TYPE);
            jSONObject.put("simSerialNumber", method2.invoke(obj, num) == null ? "" : ((String) method2.invoke(obj, num)).trim());
        } catch (Throwable unused2) {
        }
        try {
            jSONObject.put("dataState", (Integer) cls.getMethod("getDataState" + str2, Integer.TYPE).invoke(obj, num));
        } catch (Throwable unused3) {
        }
        try {
            jSONObject.put("networkType", m15886a(((Integer) cls.getMethod("getNetworkType" + str2, Integer.TYPE).invoke(obj, num)).intValue()));
        } catch (Throwable unused4) {
        }
        try {
            jSONObject.put("networkOperator", (String) cls.getMethod("getNetworkOperator" + str2, Integer.TYPE).invoke(obj, num));
        } catch (Throwable unused5) {
        }
        try {
            jSONObject.put("phoneType", m15870c(((Integer) cls.getMethod("getPhoneType" + str2, Integer.TYPE).invoke(obj, num)).intValue()));
        } catch (Throwable unused6) {
        }
        try {
            Method method3 = cls.getMethod("getSimOperator" + str2, Integer.TYPE);
            jSONObject.put("simOperator", method3.invoke(obj, num) == null ? "" : ((String) method3.invoke(obj, num)).trim());
        } catch (Throwable unused7) {
        }
        try {
            Method method4 = cls.getMethod("getSimOperatorName" + str2, Integer.TYPE);
            jSONObject.put("simOperatorName", method4.invoke(obj, num) == null ? "" : ((String) method4.invoke(obj, num)).trim());
        } catch (Throwable unused8) {
        }
        return jSONObject;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:21:0x0067
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* renamed from: F */
    public static java.util.Map m15892F(android.content.Context r6) {
        /*
            boolean r0 = m15867e(r6)
            r1 = 0
            if (r0 == 0) goto L_0x0077
            java.lang.String r6 = m15873b(r6)     // Catch: Throwable -> 0x0071
            if (r6 == 0) goto L_0x0057
            r0 = 46
            int r0 = r6.lastIndexOf(r0)     // Catch: Throwable -> 0x0071
            r2 = 1
            int r0 = r0 + r2
            r3 = 0
            java.lang.String r6 = r6.substring(r3, r0)     // Catch: Throwable -> 0x0071
            java.net.DatagramPacket r0 = new java.net.DatagramPacket     // Catch: Throwable -> 0x0071
            byte[] r4 = new byte[r3]     // Catch: Throwable -> 0x0071
            r0.<init>(r4, r3, r3)     // Catch: Throwable -> 0x0071
            java.net.DatagramSocket r3 = new java.net.DatagramSocket     // Catch: Throwable -> 0x0071
            r3.<init>()     // Catch: Throwable -> 0x0071
        L_0x0026:
            r4 = 255(0xff, float:3.57E-43)
            if (r2 >= r4) goto L_0x0053
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: Throwable -> 0x0072
            r4.<init>()     // Catch: Throwable -> 0x0072
            r4.append(r6)     // Catch: Throwable -> 0x0072
            r4.append(r2)     // Catch: Throwable -> 0x0072
            java.lang.String r4 = r4.toString()     // Catch: Throwable -> 0x0072
            java.net.InetAddress r4 = java.net.InetAddress.getByName(r4)     // Catch: Throwable -> 0x0072
            r0.setAddress(r4)     // Catch: Throwable -> 0x0050
            r3.send(r0)     // Catch: Throwable -> 0x0050
            r4 = 125(0x7d, float:1.75E-43)
            if (r2 != r4) goto L_0x0050
            r3.close()     // Catch: Throwable -> 0x0050
            java.net.DatagramSocket r4 = new java.net.DatagramSocket     // Catch: Throwable -> 0x0050
            r4.<init>()     // Catch: Throwable -> 0x0050
            r3 = r4
        L_0x0050:
            int r2 = r2 + 1
            goto L_0x0026
        L_0x0053:
            r3.close()     // Catch: Throwable -> 0x0072
            goto L_0x0058
        L_0x0057:
            r3 = r1
        L_0x0058:
            r4 = 5000(0x1388, double:2.4703E-320)
            java.lang.Thread.sleep(r4)     // Catch: Throwable -> 0x0072
            java.util.Map r6 = m15875b()     // Catch: Throwable -> 0x0072
            if (r3 == 0) goto L_0x0066
            r3.close()     // Catch: Throwable -> 0x0066
        L_0x0066:
            return r6
        L_0x0067:
            r6 = move-exception
            goto L_0x006b
        L_0x0069:
            r6 = move-exception
            r3 = r1
        L_0x006b:
            if (r3 == 0) goto L_0x0070
            r3.close()     // Catch: Throwable -> 0x0070
        L_0x0070:
            throw r6
        L_0x0071:
            r3 = r1
        L_0x0072:
            if (r3 == 0) goto L_0x0077
            r3.close()     // Catch: Throwable -> 0x0077
        L_0x0077:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tendcloud.tenddata.C2836ec.m15892F(android.content.Context):java.util.Map");
    }

    /* renamed from: b */
    private static Map m15875b() {
        try {
            HashMap hashMap = new HashMap();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/proc/net/arp")));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    String[] split = readLine.split("[ ]+");
                    if (!split[0].matches("IP")) {
                        String str = split[0];
                        String str2 = split[3];
                        if (!hashMap.containsKey(str) && !str2.equals("00:00:00:00:00:00")) {
                            hashMap.put(str, str2);
                        }
                    }
                } else {
                    bufferedReader.close();
                    return hashMap;
                }
            }
        } catch (Throwable unused) {
            return null;
        }
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.ec$a */
    /* loaded from: classes2.dex */
    public static class RunnableC2837a implements Runnable {
        private Context context;
        private Object lock;
        private BroadcastReceiver receiver;

        public RunnableC2837a(Context context, Object obj, BroadcastReceiver broadcastReceiver) {
            this.context = context;
            this.lock = obj;
            this.receiver = broadcastReceiver;
        }

        public void unRegisterReceiver() {
            BroadcastReceiver broadcastReceiver = this.receiver;
            if (broadcastReceiver != null) {
                try {
                    this.context.unregisterReceiver(broadcastReceiver);
                } catch (Throwable unused) {
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                synchronized (this.lock) {
                    this.lock.notifyAll();
                    this.context.unregisterReceiver(this.receiver);
                }
            } catch (Throwable unused) {
            }
        }
    }
}
