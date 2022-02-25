package com.tendcloud.tenddata;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.net.http.Headers;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.er */
/* loaded from: classes2.dex */
public class C2854er {
    /* renamed from: a */
    public static List m15813a(Context context) {
        boolean z;
        ArrayList arrayList = new ArrayList();
        if (C2855es.m15807a(23) && context.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") != 0) {
            return arrayList;
        }
        LocationManager locationManager = (LocationManager) C2664ab.f13513g.getSystemService(Headers.LOCATION);
        boolean z2 = false;
        if (locationManager != null) {
            z2 = locationManager.isProviderEnabled("gps");
            z = locationManager.isProviderEnabled("network");
        } else {
            z = false;
        }
        if (z2 || z) {
            arrayList.add(locationManager.getLastKnownLocation("passive"));
        }
        return arrayList;
    }

    /* renamed from: b */
    public static String m15812b(Context context) {
        try {
            List<Location> a = m15813a(context);
            StringBuilder sb = new StringBuilder();
            for (Location location : a) {
                sb.append(location.getLatitude());
                sb.append(',');
                sb.append(location.getLongitude());
                sb.append(',');
                sb.append(location.hasAltitude() ? Double.valueOf(location.getAltitude()) : "");
                sb.append(',');
                sb.append(location.getTime());
                sb.append(',');
                sb.append(location.hasAccuracy() ? Float.valueOf(location.getAccuracy()) : "");
                sb.append(',');
                sb.append(location.hasBearing() ? Float.valueOf(location.getBearing()) : "");
                sb.append(',');
                sb.append(location.hasSpeed() ? Float.valueOf(location.getSpeed()) : "");
                sb.append(',');
                sb.append(location.getProvider());
                sb.append(':');
            }
            return sb.toString();
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: c */
    public static JSONArray m15811c(Context context) {
        boolean z;
        JSONArray jSONArray = new JSONArray();
        if (C2855es.m15807a(23) && context.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") != 0) {
            return jSONArray;
        }
        try {
            LocationManager locationManager = (LocationManager) C2664ab.f13513g.getSystemService(Headers.LOCATION);
            boolean z2 = false;
            if (locationManager != null) {
                z2 = locationManager.isProviderEnabled("gps");
                z = locationManager.isProviderEnabled("network");
            } else {
                z = false;
            }
            if (z2 || z) {
                Location lastKnownLocation = locationManager.getLastKnownLocation("passive");
                if (lastKnownLocation != null) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("lat", lastKnownLocation.getLatitude());
                        jSONObject.put("lng", lastKnownLocation.getLongitude());
                        jSONObject.put("ts", lastKnownLocation.getTime());
                        if (C2855es.m15807a(17)) {
                            jSONObject.put("elapsed", lastKnownLocation.getElapsedRealtimeNanos());
                        }
                        if (lastKnownLocation.hasAltitude()) {
                            jSONObject.put("altitude", lastKnownLocation.getAltitude());
                        }
                        if (lastKnownLocation.hasAccuracy()) {
                            jSONObject.put("hAccuracy", lastKnownLocation.getAccuracy());
                        }
                        if (lastKnownLocation.hasBearing()) {
                            jSONObject.put("bearing", lastKnownLocation.getBearing());
                        }
                        if (lastKnownLocation.hasSpeed()) {
                            jSONObject.put("speed", lastKnownLocation.getSpeed());
                        }
                        jSONObject.put("provider", lastKnownLocation.getProvider());
                        jSONArray.put(jSONObject);
                    } catch (Throwable unused) {
                    }
                }
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
        return jSONArray;
    }

    /* renamed from: d */
    public static JSONArray m15810d(Context context) {
        try {
            JSONArray jSONArray = new JSONArray();
            JSONArray C = C2836ec.m15895C(context);
            if (C.length() > 0) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("type", "sim");
                jSONObject.put("extra", C);
                jSONArray.put(jSONObject);
            }
            if (jSONArray.length() > 0) {
                return jSONArray;
            }
            return null;
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return null;
        }
    }

    /* renamed from: e */
    public static Long[][] m15809e(Context context) {
        return new Long[3];
    }
}
