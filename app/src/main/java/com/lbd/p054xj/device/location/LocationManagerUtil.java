package com.lbd.p054xj.device.location;

import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import com.common.utils.log.LogUtils;
import com.lbd.p054xj.manager.FullJniUtil;
import com.lbd.p054xj.p056ui.activity.XJBaseANativityctivity;
import p110z1.ciq;

/* renamed from: com.lbd.xj.device.location.LocationManagerUtil */
/* loaded from: classes.dex */
public class LocationManagerUtil implements GpsStatus.NmeaListener, LocationListener {
    private static final String TAG = "LocationManagerUtil";
    private int IsStartGPS = 0;
    private int IsStartnmeaGPS = 0;
    private boolean isPermission;
    private XJBaseANativityctivity mContext;
    private LocationManager mLocationManager;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.lbd.xj.device.location.LocationManagerUtil$SingletonClassInstance */
    /* loaded from: classes.dex */
    public static class SingletonClassInstance {
        public static final LocationManagerUtil manage = new LocationManagerUtil();

        private SingletonClassInstance() {
        }
    }

    public static LocationManagerUtil getInstance() {
        return SingletonClassInstance.manage;
    }

    public void initializeSensorManager(Object obj, XJBaseANativityctivity xJBaseANativityctivity) {
        if (obj == null) {
            LogUtils.m22034i(TAG, "定位 LocationManager = null");
            return;
        }
        if (obj instanceof LocationManager) {
            this.mLocationManager = (LocationManager) obj;
        }
        this.mContext = xJBaseANativityctivity;
        if (ActivityCompat.checkSelfPermission(this.mContext, "android.permission.ACCESS_FINE_LOCATION") == 0 && ActivityCompat.checkSelfPermission(this.mContext, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            this.isPermission = true;
        } else {
            LogUtils.m22034i(TAG, "GPS定位 无权限");
        }
    }

    public void SetGpsStart(int i, int i2) {
        XJBaseANativityctivity xJBaseANativityctivity = this.mContext;
        if (xJBaseANativityctivity != null && !xJBaseANativityctivity.isFinishing()) {
            LogUtils.m22034i(TAG, "yuelog0426 xunidashi SetGpsStart requestLocationUpdates ");
            this.IsStartGPS = 1;
            Message message = new Message();
            message.what = 2;
            message.obj = "SetGpsStart";
            this.mContext.f9609d.sendMessage(message);
        }
    }

    public void SetGpsStop(int i, int i2) {
        XJBaseANativityctivity xJBaseANativityctivity = this.mContext;
        if (xJBaseANativityctivity != null && !xJBaseANativityctivity.isFinishing()) {
            LogUtils.m22034i(TAG, "yuelog0426 xunidashi SetGpsStop IsStartSendData = 0 1 && freee  requestLocationUpdates ");
            this.IsStartGPS = 2;
            Message message = new Message();
            message.what = 2;
            message.obj = "SetGpsStop";
            this.mContext.f9609d.sendMessage(message);
        }
    }

    public void SetGpsnmeaStart(int i, int i2) {
        XJBaseANativityctivity xJBaseANativityctivity = this.mContext;
        if (xJBaseANativityctivity != null && !xJBaseANativityctivity.isFinishing()) {
            LogUtils.m22034i(TAG, "yuelog0426 XUNIDASHInmea SetGpsnmeaStart IsStartnmeaGPS=1 ");
            this.IsStartnmeaGPS = 1;
            Message message = new Message();
            message.what = 2;
            message.obj = "SetGpsnmeaStart";
            this.mContext.f9609d.sendMessage(message);
        }
    }

    public void SetGpsnmeaStop(int i, int i2) {
        XJBaseANativityctivity xJBaseANativityctivity = this.mContext;
        if (xJBaseANativityctivity != null && !xJBaseANativityctivity.isFinishing()) {
            LogUtils.m22034i(TAG, "yuelog0426 XUNIDASHInmea SetGpsnmeaStop IsStartnmeaGPS =2 ");
            this.IsStartnmeaGPS = 2;
            Message message = new Message();
            message.what = 2;
            message.obj = "SetGpsnmeaStop";
            this.mContext.f9609d.sendMessage(message);
        }
    }

    @Override // android.location.GpsStatus.NmeaListener
    public void onNmeaReceived(long j, String str) {
        if (this.IsStartnmeaGPS == 1) {
            FullJniUtil.m19759a().GpsnmeaChanged(j, str);
        } else {
            LogUtils.m22034i(TAG, "yuelog0426 XUNIDASHInmea 暂时不允许发送数据");
        }
    }

    public void mHandlerMsg(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtils.m22037e("定位无消息类型");
        } else if (!this.isPermission) {
            LogUtils.m22037e("无定位权限");
        } else if (this.mLocationManager == null) {
            LogUtils.m22037e("无定位LocationManager");
        } else {
            char c = ciq.f20716b;
            try {
                if (str.hashCode() == 911019306 && str.equals("SetGpsStop")) {
                    c = 0;
                }
                if (c == 0) {
                    LogUtils.m22034i(TAG, "yuelog0426 XUNIDASHInmea SetGpsStop removeUpdates 1");
                    this.mLocationManager.removeUpdates(this);
                    LogUtils.m22034i(TAG, "yuelog0426 XUNIDASHInmea SetGpsStop removeUpdates 2");
                }
            } catch (Exception e) {
                LogUtils.m22034i(TAG, "添加定位接口错误 " + e.toString());
            }
        }
    }

    @Override // android.location.LocationListener
    public void onLocationChanged(Location location) {
        if (this.IsStartGPS == 1) {
            FullJniUtil.m19759a().GpsChanged(64, 19, location.getLatitude(), location.getLongitude(), location.getAltitude(), location.getSpeed(), location.getBearing(), location.getAccuracy(), location.getTime());
        } else {
            LogUtils.m22034i(TAG, "yuelog0426 XUNIDASHI 暂时不允许发送数据");
        }
    }

    @Override // android.location.LocationListener
    public void onStatusChanged(String str, int i, Bundle bundle) {
        if (i == 0) {
            LogUtils.m22034i(TAG, "XUNIDASHI 当前GPS状态为服务区外状态");
        } else if (i == 1) {
            LogUtils.m22034i(TAG, "XUNIDASHI 当前GPS状态为暂停服务状态");
        } else if (i == 2) {
            LogUtils.m22034i(TAG, "XUNIDASHI 当前GPS状态为可见状态");
        }
    }

    @Override // android.location.LocationListener
    public void onProviderEnabled(String str) {
        XJBaseANativityctivity xJBaseANativityctivity = this.mContext;
        if (xJBaseANativityctivity != null && !xJBaseANativityctivity.isFinishing()) {
            LogUtils.m22034i(TAG, "yuelog0426 XUNIDASHI GPS开启时触发 onProviderEnabled");
            if (ActivityCompat.checkSelfPermission(this.mContext, "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(this.mContext, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                this.mLocationManager.getLastKnownLocation(str);
                return;
            }
            LogUtils.m22034i(TAG, "XUNIDASHI onProviderEnabled 权限不够");
            this.isPermission = false;
        }
    }

    @Override // android.location.LocationListener
    public void onProviderDisabled(String str) {
        LogUtils.m22034i(TAG, "yuelog0426 XUNIDASHI GPS禁用时触发 onProviderDisabled");
    }
}
