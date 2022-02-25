package p110z1;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.IInterface;
import java.util.HashMap;
import java.util.Map;
import mirror.MethodParams;
import mirror.RefClass;
import mirror.RefMethod;
import mirror.RefObject;

/* renamed from: z1.cvl */
/* loaded from: classes3.dex */
public class LocationManager {
    public static Class<?> TYPE = RefClass.load(LocationManager.class, "android.location.LocationManager");
    public static RefObject<Map> mGnssNmeaListeners;
    public static RefObject<Map> mGnssStatusListeners;
    public static RefObject<Map> mGpsNmeaListeners;
    public static RefObject<Map> mGpsStatusListeners;
    public static RefObject<HashMap> mListeners;
    public static RefObject<HashMap> mNmeaListeners;
    public static RefObject<IInterface> mService;

    /* compiled from: LocationManager.java */
    /* renamed from: z1.cvl$a */
    /* loaded from: classes3.dex */
    public static class C5156a {
        public static Class<?> TYPE = RefClass.load(C5156a.class, "android.location.LocationManager$GnssStatusListenerTransport");
        public static RefObject<Object> mGpsListener;
        public static RefObject<Object> mGpsNmeaListener;
        @MethodParams({int.class})
        public static RefMethod<Void> onFirstFix;
        public static RefMethod<Void> onGnssStarted;
        @MethodParams({long.class, String.class})
        public static RefMethod<Void> onNmeaReceived;
        @MethodParams({int.class, int[].class, float[].class, float[].class, float[].class})
        public static RefMethod<Void> onSvStatusChanged;
        public static RefObject<Object> this$0;
    }

    /* compiled from: LocationManager.java */
    /* renamed from: z1.cvl$b */
    /* loaded from: classes3.dex */
    public static class C5157b {
        public static Class<?> TYPE = RefClass.load(C5157b.class, "android.location.LocationManager$GnssStatusListenerTransport");
        @MethodParams({int.class, int[].class, float[].class, float[].class, float[].class, float[].class})
        public static RefMethod<Void> onSvStatusChanged;
    }

    /* compiled from: LocationManager.java */
    /* renamed from: z1.cvl$c */
    /* loaded from: classes3.dex */
    public static class C5158c {
        public static Class<?> TYPE = RefClass.load(C5158c.class, "android.location.LocationManager$GpsStatusListenerTransport");
        public static RefObject<Object> mListener;
        public static RefObject<Object> mNmeaListener;
        @MethodParams({int.class})
        public static RefMethod<Void> onFirstFix;
        public static RefMethod<Void> onGpsStarted;
        @MethodParams({long.class, String.class})
        public static RefMethod<Void> onNmeaReceived;
        @MethodParams({int.class, int[].class, float[].class, float[].class, float[].class, int.class, int.class, int.class})
        public static RefMethod<Void> onSvStatusChanged;
        public static RefObject<Object> this$0;
    }

    /* compiled from: LocationManager.java */
    /* renamed from: z1.cvl$d */
    /* loaded from: classes3.dex */
    public static class C5159d {
        public static Class<?> TYPE = RefClass.load(C5159d.class, "android.location.LocationManager$GpsStatusListenerTransport");
        @MethodParams({int.class, int[].class, float[].class, float[].class, float[].class, int[].class, int[].class, int[].class, int.class})
        public static RefMethod<Void> onSvStatusChanged;
    }

    /* compiled from: LocationManager.java */
    /* renamed from: z1.cvl$e */
    /* loaded from: classes3.dex */
    public static class C5160e {
        public static Class<?> TYPE = RefClass.load(C5160e.class, "android.location.LocationManager$GpsStatusListenerTransport");
        @MethodParams({int.class, int[].class, float[].class, float[].class, float[].class, int.class, int.class, int.class, int[].class})
        public static RefMethod<Void> onSvStatusChanged;
    }

    /* compiled from: LocationManager.java */
    /* renamed from: z1.cvl$f */
    /* loaded from: classes3.dex */
    public static class C5161f {
        public static Class<?> TYPE = RefClass.load(C5161f.class, "android.location.LocationManager$GpsStatusListenerTransport");
        @MethodParams({int.class, int[].class, float[].class, float[].class, float[].class, int.class, int.class, int.class, long[].class})
        public static RefMethod<Void> onSvStatusChanged;
    }

    /* compiled from: LocationManager.java */
    /* renamed from: z1.cvl$g */
    /* loaded from: classes3.dex */
    public static class C5162g {
        public static Class<?> TYPE = RefClass.load(C5162g.class, "android.location.LocationManager$ListenerTransport");
        public static RefObject<LocationListener> mListener;
        @MethodParams({Location.class})
        public static RefMethod<Void> onLocationChanged;
        @MethodParams({String.class})
        public static RefMethod<Void> onProviderDisabled;
        @MethodParams({String.class})
        public static RefMethod<Void> onProviderEnabled;
        @MethodParams({String.class, int.class, Bundle.class})
        public static RefMethod<Void> onStatusChanged;
        public static RefObject<Object> this$0;
    }
}
