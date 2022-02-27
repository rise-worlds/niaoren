package com.lbd.p054xj.device.location;

import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.http.Headers;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import androidx.annotation.RequiresApi;
import com.common.utils.log.LogUtils;
import com.lbd.p054xj.app.XJApp;
import com.lbd.p054xj.p056ui.activity.XJBaseANativityctivity;

/* renamed from: com.lbd.xj.device.location.LocationController */
/* loaded from: classes.dex */
public class LocationController {
    private static final String TAG = "LocationManagerUtil";
    private GpsChangedListener listener;
    private LocationManager mLocationManager;

    /* renamed from: lm */
    private LocationManager f9452lm = null;
    public LocationListener locationListener = null;
    private Handler mHandlergps = null;
    private Handler mHandlernmea = null;
    private int IsStartGPS = 0;
    private int IsStartnmeaGPS = 0;
    GpsStatus.NmeaListener nmeaListener = null;

    /* renamed from: com.lbd.xj.device.location.LocationController$GpsChangedListener */
    /* loaded from: classes.dex */
    public interface GpsChangedListener {
        void gpsChanged(int i, int i2, double d, double d2, double d3, double d4, double d5, double d6, long j);

        void gpsnmeaChanged(long j, String str);
    }

    public LocationController() {
        initLocation();
    }

    public void setGpsChangedListener(GpsChangedListener gpsChangedListener) {
        this.listener = gpsChangedListener;
    }

    private void initLocation() {
        this.f9452lm = (LocationManager) XJApp.getInstance().getApplicationContext().getSystemService(Headers.LOCATION);
        if (this.f9452lm == null) {
            LogUtils.m22034i("SHENG", "xunidashi getSystemService gps  == null");
            return;
        }
        this.locationListener = new LocationListener() { // from class: com.lbd.xj.device.location.LocationController.1
            @Override // android.location.LocationListener
            public void onLocationChanged(Location location) {
                if (LocationController.this.IsStartGPS != 1) {
                    LogUtils.m22034i("SHENG", "yuelog0426 XUNIDASHI 暂时不允许发送数据");
                } else if (LocationController.this.listener != null) {
                    LocationController.this.listener.gpsChanged(64, 19, location.getLatitude(), location.getLongitude(), location.getAltitude(), location.getSpeed(), location.getBearing(), location.getAccuracy(), location.getTime());
                }
            }

            @Override // android.location.LocationListener
            public void onProviderDisabled(String str) {
                LogUtils.m22034i("SHENG", "yuelog0426 XUNIDASHI GPS禁用时触发 onProviderDisabled");
            }

            @Override // android.location.LocationListener
            public void onProviderEnabled(String str) {
                LogUtils.m22034i("SHENG", "yuelog0426 XUNIDASHI GPS开启时触发 onProviderEnabled");
                if (ActivityCompat.checkSelfPermission(XJApp.getInstance().getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(XJApp.getInstance().getApplicationContext(), "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                    LocationController.this.f9452lm.getLastKnownLocation(str);
                } else {
                    LogUtils.m22034i("SHENG", "XUNIDASHI onProviderEnabled 权限不够");
                }
            }

            @Override // android.location.LocationListener
            public void onStatusChanged(String str, int i, Bundle bundle) {
                if (i == 0) {
                    LogUtils.m22034i("SHENG", "XUNIDASHI 当前GPS状态为服务区外状态");
                } else if (i == 1) {
                    LogUtils.m22034i("SHENG", "XUNIDASHI 当前GPS状态为暂停服务状态");
                } else if (i == 2) {
                    LogUtils.m22034i("SHENG", "XUNIDASHI 当前GPS状态为可见状态");
                }
            }
        };
        this.nmeaListener = new GpsStatus.NmeaListener() { // from class: com.lbd.xj.device.location.LocationController.2
            @Override // android.location.GpsStatus.NmeaListener
            public void onNmeaReceived(long j, String str) {
                if (LocationController.this.IsStartnmeaGPS != 1) {
                    LogUtils.m22034i("SHENG", "yuelog0426 XUNIDASHInmea 暂时不允许发送数据");
                } else if (LocationController.this.listener != null) {
                    LocationController.this.listener.gpsnmeaChanged(j, str);
                }
            }
        };
        this.mHandlernmea = new Handler() { // from class: com.lbd.xj.device.location.LocationController.3
            @Override // android.os.Handler
            @RequiresApi(m25682b = 24)
            public void handleMessage(Message message) {
                String str = (String) message.obj;
                if (!(!"SetGpsnmeaStart".equals(str) || LocationController.this.f9452lm == null || LocationController.this.nmeaListener == null)) {
                    LogUtils.m22034i("SHENG", "yuelog0426 XUNIDASHInmea SetGpsnmeaStart addNmeaListenerok str.equals = SetGpsnmeaStart " + LocationController.this.nmeaListener);
                    try {
                        LocationController.this.f9452lm.addNmeaListener(LocationController.this.nmeaListener);
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    }
                    LogUtils.m22034i("SHENG", "yuelog0426 XUNIDASHInmea SetGpsnmeaStart addNmeaListenerok 1");
                }
                if ("SetGpsnmeaStop".equals(str) && LocationController.this.f9452lm != null && LocationController.this.nmeaListener != null) {
                    LogUtils.m22034i("SHENG", "yuelog0426 XUNIDASHInmea SetGpsnmeaStop removeNmeaListener 1");
                    LocationController.this.f9452lm.removeNmeaListener(LocationController.this.nmeaListener);
                    LogUtils.m22034i("SHENG", "yuelog0426 XUNIDASHInmea SetGpsnmeaStop removeNmeaListener 2");
                }
            }
        };
        this.mHandlergps = new Handler() { // from class: com.lbd.xj.device.location.LocationController.4
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                String str = (String) message.obj;
                if (!(!"SetGpsStart".equals(str) || LocationController.this.f9452lm == null || LocationController.this.locationListener == null)) {
                    LogUtils.m22034i("SHENG", "yuelog0426 XUNIDASHInmea SetGpsStart requestLocationUpdates str.equals = SetGpsStart " + LocationController.this.locationListener);
                    try {
                        LocationController.this.f9452lm.requestLocationUpdates("gps", 10L, 0.0f, LocationController.this.locationListener);
                    } catch (IllegalArgumentException e) {
                        LogUtils.m22034i("SHENG", "GPS 模块不存在 " + e.toString());
                    } catch (SecurityException e2) {
                        e2.printStackTrace();
                    }
                    LogUtils.m22034i("SHENG", "yuelog0426 XUNIDASHInmea SetGpsStart requestLocationUpdates 1");
                }
                if ("SetGpsStop".equals(str) && LocationController.this.f9452lm != null && LocationController.this.locationListener != null) {
                    LogUtils.m22034i("SHENG", "yuelog0426 XUNIDASHInmea SetGpsStop removeUpdates 1");
                    LocationController.this.f9452lm.removeUpdates(LocationController.this.locationListener);
                    LogUtils.m22034i("SHENG", "yuelog0426 XUNIDASHInmea SetGpsStop removeUpdates 2");
                }
            }
        };
    }

    public void setStartGPS(int i) {
        this.IsStartGPS = i;
    }

    public void setGpsStart() {
        LogUtils.m22034i("SHENG", "yuelog0426 xunidashi SetGpsStart requestLocationUpdates ");
        this.IsStartGPS = 1;
        Message message = new Message();
        message.obj = "SetGpsStart";
        this.mHandlergps.sendMessage(message);
    }

    public void setGpsStop() {
        LogUtils.m22034i("SHENG", "yuelog0426 xunidashi SetGpsStop IsStartSendData = 0 1 && freee  requestLocationUpdates ");
        this.IsStartGPS = 2;
        Message message = new Message();
        message.obj = "SetGpsStop";
        this.mHandlergps.sendMessage(message);
    }

    public void setGpsnmeaStart() {
        LogUtils.m22034i("SHENG", "yuelog0426 XUNIDASHInmea SetGpsnmeaStart IsStartnmeaGPS=1 ");
        this.IsStartnmeaGPS = 1;
        Message message = new Message();
        message.obj = "SetGpsnmeaStart";
        this.mHandlernmea.sendMessage(message);
    }

    public void setGpsnmeaStop() {
        LogUtils.m22034i("SHENG", "yuelog0426 XUNIDASHInmea SetGpsnmeaStop IsStartnmeaGPS =2 ");
        this.IsStartnmeaGPS = 2;
        Message message = new Message();
        message.obj = "SetGpsnmeaStop";
        this.mHandlernmea.sendMessage(message);
    }

    public int getIsStartGPS() {
        return this.IsStartGPS;
    }

    public int getIsStartnmeaGPS() {
        return this.IsStartnmeaGPS;
    }

    public LocationManager getLocationManager() {
        return this.f9452lm;
    }

    /* renamed from: com.lbd.xj.device.location.LocationController$SingletonClassInstance */
    /* loaded from: classes.dex */
    private static class SingletonClassInstance {
        public static final LocationController manage = new LocationController();

        private SingletonClassInstance() {
        }
    }

    public static LocationController getInstance() {
        return SingletonClassInstance.manage;
    }

    public void initializeSensorManager(Object obj, XJBaseANativityctivity xJBaseANativityctivity) {
        if (obj == null) {
            LogUtils.m22034i(TAG, "定位 LocationManager = null");
        } else if (obj instanceof LocationManager) {
            this.mLocationManager = (LocationManager) obj;
        }
    }
}
