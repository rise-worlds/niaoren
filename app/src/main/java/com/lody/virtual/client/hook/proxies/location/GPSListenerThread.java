package com.lody.virtual.client.hook.proxies.location;

import android.location.Location;
import android.os.Build;
import android.os.Handler;
import com.lody.virtual.client.ipc.VirtualLocationManager;
import com.lody.virtual.remote.vloc.VLocation;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import p110z1.LocationManager;

/* loaded from: classes.dex */
class GPSListenerThread extends TimerTask {
    private static GPSListenerThread INSTANCE = new GPSListenerThread();
    private Handler handler = new Handler();
    private boolean isRunning = false;
    private HashMap<Object, Long> listeners = new HashMap<>();
    private Timer timer = new Timer();

    private void notifyGPSStatus(Map map) {
        if (!(map == null || map.isEmpty())) {
            for (Map.Entry entry : map.entrySet()) {
                try {
                    Object value = entry.getValue();
                    if (value != null) {
                        MockLocationHelper.invokeSvStatusChanged(value);
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyLocation(Map map) {
        VLocation location;
        if (map != null) {
            try {
                if (!(map.isEmpty() || (location = VirtualLocationManager.get().getLocation()) == null)) {
                    Location sysLocation = location.toSysLocation();
                    for (Map.Entry entry : map.entrySet()) {
                        Object value = entry.getValue();
                        if (value != null) {
                            LocationManager.C5162g.onLocationChanged.call(value, sysLocation);
                        }
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private void notifyMNmeaListener(Map map) {
        if (!(map == null || map.isEmpty())) {
            for (Map.Entry entry : map.entrySet()) {
                try {
                    Object value = entry.getValue();
                    if (value != null) {
                        MockLocationHelper.invokeNmeaReceived(value);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addListenerTransport(Object obj) {
        this.listeners.put(obj, Long.valueOf(System.currentTimeMillis()));
        if (!this.isRunning) {
            synchronized (this) {
                if (!this.isRunning) {
                    this.isRunning = true;
                    this.timer.schedule(this, 1000L, 1000L);
                }
            }
        }
    }

    public void removeListenerTransport(Object obj) {
        if (obj != null) {
            this.listeners.remove(obj);
        }
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        Map map;
        if (this.listeners.isEmpty()) {
            return;
        }
        if (VirtualLocationManager.get().getMode() == 0) {
            this.listeners.clear();
            return;
        }
        for (Map.Entry<Object, Long> entry : this.listeners.entrySet()) {
            try {
                Object key = entry.getKey();
                if (Build.VERSION.SDK_INT >= 24) {
                    notifyGPSStatus(LocationManager.mGnssStatusListeners.get(key));
                    notifyMNmeaListener(LocationManager.mGnssNmeaListeners.get(key));
                    map = LocationManager.mGpsStatusListeners.get(key);
                    notifyGPSStatus(map);
                    notifyMNmeaListener(LocationManager.mGpsNmeaListeners.get(key));
                } else {
                    map = LocationManager.mGpsStatusListeners.get(key);
                    notifyGPSStatus(map);
                    notifyMNmeaListener(LocationManager.mNmeaListeners.get(key));
                }
                final HashMap hashMap = LocationManager.mListeners.get(key);
                if (map != null && !map.isEmpty()) {
                    if (hashMap != null && !hashMap.isEmpty()) {
                        notifyLocation(hashMap);
                    }
                    this.handler.postDelayed(new Runnable() { // from class: com.lody.virtual.client.hook.proxies.location.GPSListenerThread.1
                        @Override // java.lang.Runnable
                        public void run() {
                            GPSListenerThread.this.notifyLocation(hashMap);
                        }
                    }, 100L);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        this.timer.cancel();
    }

    public static GPSListenerThread get() {
        return INSTANCE;
    }

    private GPSListenerThread() {
    }
}
