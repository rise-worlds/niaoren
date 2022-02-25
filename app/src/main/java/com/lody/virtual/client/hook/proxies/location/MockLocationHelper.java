package com.lody.virtual.client.hook.proxies.location;

import android.location.GpsStatus;
import android.os.Build;
import com.lody.virtual.client.env.VirtualGPSSatalines;
import com.lody.virtual.client.ipc.VLocationManager;
import com.lody.virtual.helper.compat.BuildCompat;
import com.lody.virtual.helper.utils.Reflect;
import com.lody.virtual.remote.vloc.VLocation;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import org.slf4j.Marker;
import p110z1.GpsStatusL;
import p110z1.LocationManager;

/* loaded from: classes.dex */
public class MockLocationHelper {
    public static void invokeNmeaReceived(Object obj) {
        if (obj != null) {
            VirtualGPSSatalines virtualGPSSatalines = VirtualGPSSatalines.get();
            try {
                VLocation curAppLocation = VLocationManager.get().getCurAppLocation();
                if (curAppLocation != null) {
                    String format = new SimpleDateFormat("HHmmss:SS", Locale.US).format(new Date());
                    String gPSLat = getGPSLat(curAppLocation.getLatitude());
                    String gPSLat2 = getGPSLat(curAppLocation.getLongitude());
                    String northWest = getNorthWest(curAppLocation);
                    String southEast = getSouthEast(curAppLocation);
                    String checksum = checksum(String.format("$GPGGA,%s,%s,%s,%s,%s,1,%s,692,.00,M,.00,M,,,", format, gPSLat, northWest, gPSLat2, southEast, Integer.valueOf(virtualGPSSatalines.getSvCount())));
                    String checksum2 = checksum(String.format("$GPRMC,%s,A,%s,%s,%s,%s,0,0,260717,,,A,", format, gPSLat, northWest, gPSLat2, southEast));
                    if (LocationManager.C5156a.onNmeaReceived != null) {
                        LocationManager.C5156a.onNmeaReceived.call(obj, Long.valueOf(System.currentTimeMillis()), "$GPGSV,1,1,04,12,05,159,36,15,41,087,15,19,38,262,30,31,56,146,19,*73");
                        LocationManager.C5156a.onNmeaReceived.call(obj, Long.valueOf(System.currentTimeMillis()), checksum);
                        LocationManager.C5156a.onNmeaReceived.call(obj, Long.valueOf(System.currentTimeMillis()), "$GPVTG,0,T,0,M,0,N,0,K,A,*25");
                        LocationManager.C5156a.onNmeaReceived.call(obj, Long.valueOf(System.currentTimeMillis()), checksum2);
                        LocationManager.C5156a.onNmeaReceived.call(obj, Long.valueOf(System.currentTimeMillis()), "$GPGSA,A,2,12,15,19,31,,,,,,,,,604,712,986,*27");
                    } else if (LocationManager.C5158c.onNmeaReceived != null) {
                        LocationManager.C5158c.onNmeaReceived.call(obj, Long.valueOf(System.currentTimeMillis()), "$GPGSV,1,1,04,12,05,159,36,15,41,087,15,19,38,262,30,31,56,146,19,*73");
                        LocationManager.C5158c.onNmeaReceived.call(obj, Long.valueOf(System.currentTimeMillis()), checksum);
                        LocationManager.C5158c.onNmeaReceived.call(obj, Long.valueOf(System.currentTimeMillis()), "$GPVTG,0,T,0,M,0,N,0,K,A,*25");
                        LocationManager.C5158c.onNmeaReceived.call(obj, Long.valueOf(System.currentTimeMillis()), checksum2);
                        LocationManager.C5158c.onNmeaReceived.call(obj, Long.valueOf(System.currentTimeMillis()), "$GPGSA,A,2,12,15,19,31,,,,,,,,,604,712,986,*27");
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static void fakeGpsStatusN(android.location.LocationManager locationManager) {
        if (LocationManager.mGpsStatusListeners != null) {
            Iterator it = LocationManager.mGpsStatusListeners.get(locationManager).values().iterator();
            if (it.hasNext()) {
                invokeSvStatusChanged(it.next());
            }
        }
    }

    public static void fakeGpsStatus(android.location.LocationManager locationManager) {
        if (Build.VERSION.SDK_INT >= 24) {
            fakeGpsStatusN(locationManager);
            return;
        }
        GpsStatus gpsStatus = null;
        try {
            gpsStatus = (GpsStatus) Reflect.m18998on(locationManager).get("mGpsStatus");
        } catch (Throwable unused) {
        }
        if (gpsStatus != null) {
            VirtualGPSSatalines virtualGPSSatalines = VirtualGPSSatalines.get();
            int svCount = virtualGPSSatalines.getSvCount();
            float[] snrs = virtualGPSSatalines.getSnrs();
            int[] prns = virtualGPSSatalines.getPrns();
            float[] elevations = virtualGPSSatalines.getElevations();
            float[] azimuths = virtualGPSSatalines.getAzimuths();
            try {
                if (GpsStatusL.setStatus != null) {
                    int svCount2 = virtualGPSSatalines.getSvCount();
                    int length = virtualGPSSatalines.getPrns().length;
                    float[] elevations2 = virtualGPSSatalines.getElevations();
                    float[] azimuths2 = virtualGPSSatalines.getAzimuths();
                    int[] iArr = new int[length];
                    for (int i = 0; i < length; i++) {
                        iArr[i] = virtualGPSSatalines.getEphemerisMask();
                    }
                    int[] iArr2 = new int[length];
                    for (int i2 = 0; i2 < length; i2++) {
                        iArr2[i2] = virtualGPSSatalines.getAlmanacMask();
                    }
                    int[] iArr3 = new int[length];
                    for (int i3 = 0; i3 < length; i3++) {
                        iArr3[i3] = virtualGPSSatalines.getUsedInFixMask();
                    }
                    GpsStatusL.setStatus.call(gpsStatus, Integer.valueOf(svCount2), prns, snrs, elevations2, azimuths2, iArr, iArr2, iArr3);
                } else if (p110z1.GpsStatus.setStatus != null) {
                    p110z1.GpsStatus.setStatus.call(gpsStatus, Integer.valueOf(svCount), prns, snrs, elevations, azimuths, Integer.valueOf(virtualGPSSatalines.getEphemerisMask()), Integer.valueOf(virtualGPSSatalines.getAlmanacMask()), Integer.valueOf(virtualGPSSatalines.getUsedInFixMask()));
                }
            } catch (Exception unused2) {
            }
        }
    }

    public static void invokeSvStatusChanged(Object obj) {
        if (obj != null) {
            VirtualGPSSatalines virtualGPSSatalines = VirtualGPSSatalines.get();
            try {
                Class<?> cls = obj.getClass();
                if (cls == LocationManager.C5156a.TYPE) {
                    int svCount = virtualGPSSatalines.getSvCount();
                    int[] prnWithFlags = virtualGPSSatalines.getPrnWithFlags();
                    float[] snrs = virtualGPSSatalines.getSnrs();
                    float[] elevations = virtualGPSSatalines.getElevations();
                    float[] azimuths = virtualGPSSatalines.getAzimuths();
                    if (BuildCompat.isOreo()) {
                        try {
                            LocationManager.C5157b.onSvStatusChanged.call(obj, Integer.valueOf(svCount), prnWithFlags, snrs, elevations, azimuths, virtualGPSSatalines.getCarrierFreqs());
                        } catch (NullPointerException unused) {
                        }
                    } else {
                        LocationManager.C5156a.onSvStatusChanged.call(obj, Integer.valueOf(svCount), prnWithFlags, snrs, elevations, azimuths);
                    }
                } else if (cls == LocationManager.C5158c.TYPE) {
                    int svCount2 = virtualGPSSatalines.getSvCount();
                    int[] prns = virtualGPSSatalines.getPrns();
                    float[] snrs2 = virtualGPSSatalines.getSnrs();
                    float[] elevations2 = virtualGPSSatalines.getElevations();
                    float[] azimuths2 = virtualGPSSatalines.getAzimuths();
                    int ephemerisMask = virtualGPSSatalines.getEphemerisMask();
                    int almanacMask = virtualGPSSatalines.getAlmanacMask();
                    int usedInFixMask = virtualGPSSatalines.getUsedInFixMask();
                    if (LocationManager.C5158c.onSvStatusChanged != null) {
                        LocationManager.C5158c.onSvStatusChanged.call(obj, Integer.valueOf(svCount2), prns, snrs2, elevations2, azimuths2, Integer.valueOf(ephemerisMask), Integer.valueOf(almanacMask), Integer.valueOf(usedInFixMask));
                    } else if (LocationManager.C5161f.onSvStatusChanged != null) {
                        LocationManager.C5161f.onSvStatusChanged.call(obj, Integer.valueOf(svCount2), prns, snrs2, elevations2, azimuths2, Integer.valueOf(ephemerisMask), Integer.valueOf(almanacMask), Integer.valueOf(usedInFixMask), new long[svCount2]);
                    } else if (LocationManager.C5160e.onSvStatusChanged != null) {
                        LocationManager.C5160e.onSvStatusChanged.call(obj, Integer.valueOf(svCount2), prns, snrs2, elevations2, azimuths2, Integer.valueOf(ephemerisMask), Integer.valueOf(almanacMask), Integer.valueOf(usedInFixMask), new int[svCount2]);
                    } else if (LocationManager.C5159d.onSvStatusChanged != null) {
                        int length = prns.length;
                        int[] iArr = new int[length];
                        for (int i = 0; i < length; i++) {
                            iArr[i] = virtualGPSSatalines.getEphemerisMask();
                        }
                        int[] iArr2 = new int[length];
                        for (int i2 = 0; i2 < length; i2++) {
                            iArr2[i2] = virtualGPSSatalines.getAlmanacMask();
                        }
                        int[] iArr3 = new int[length];
                        for (int i3 = 0; i3 < length; i3++) {
                            iArr3[i3] = virtualGPSSatalines.getUsedInFixMask();
                        }
                        LocationManager.C5159d.onSvStatusChanged.call(obj, Integer.valueOf(svCount2), prns, snrs2, elevations2, azimuths2, iArr, iArr2, iArr3, Integer.valueOf(svCount2));
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private static String getSouthEast(VLocation vLocation) {
        return vLocation.getLongitude() > 0.0d ? "E" : "W";
    }

    private static String getNorthWest(VLocation vLocation) {
        return vLocation.getLatitude() > 0.0d ? "N" : "S";
    }

    public static String getGPSLat(double d) {
        int i = (int) d;
        double d2 = (d - i) * 60.0d;
        return i + leftZeroPad((int) d2, 2) + ":" + String.valueOf(d2).substring(2);
    }

    private static String leftZeroPad(int i, int i2) {
        return leftZeroPad(String.valueOf(i), i2);
    }

    private static String leftZeroPad(String str, int i) {
        StringBuilder sb = new StringBuilder(i);
        int i2 = 0;
        if (str == null) {
            while (i2 < i) {
                sb.append('0');
                i2++;
            }
        } else {
            while (i2 < i - str.length()) {
                sb.append('0');
                i2++;
            }
            sb.append(str);
        }
        return sb.toString();
    }

    public static String checksum(String str) {
        String substring = str.startsWith("$") ? str.substring(1) : str;
        int i = 0;
        for (int i2 = 0; i2 < substring.length(); i2++) {
            i ^= (byte) substring.charAt(i2);
        }
        return str + Marker.ANY_MARKER + String.format("%02X", Integer.valueOf(i)).toLowerCase();
    }
}
