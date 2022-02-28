package com.lody.virtual.client.ipc;

import android.app.PendingIntent;
import android.location.Location;
import android.location.LocationManager;
import android.net.http.Headers;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import com.lody.virtual.client.VClient;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.hook.proxies.location.MockLocationHelper;
import com.lody.virtual.client.hook.utils.MethodParameterUtils;
import com.lody.virtual.helper.utils.Reflect;
import com.lody.virtual.os.VUserHandle;
import com.lody.virtual.remote.vloc.VLocation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.tools.ant.taskdefs.WaitFor;
import p110z1.LocationManager;

/* loaded from: classes.dex */
public class VLocationManager {
    private static VLocationManager sVLocationManager = new VLocationManager();
    private HandlerThread mHandlerThread;
    private Handler mWorkHandler;
    private final List<Object> mGpsListeners = new ArrayList();
    private Runnable mUpdateGpsStatusTask = new Runnable() { // from class: com.lody.virtual.client.ipc.VLocationManager.1
        @Override // java.lang.Runnable
        public void run() {
            synchronized (VLocationManager.this.mGpsListeners) {
                for (Object obj : VLocationManager.this.mGpsListeners) {
                    VLocationManager.this.notifyGpsStatus(obj);
                }
            }
            VLocationManager.this.mWorkHandler.postDelayed(VLocationManager.this.mUpdateGpsStatusTask, 8000L);
        }
    };
    private final Map<Object, UpdateLocationTask> mLocationTaskMap = new HashMap();

    private VLocationManager() {
        MockLocationHelper.fakeGpsStatus((LocationManager) VirtualCore.get().getContext().getSystemService(Headers.LOCATION));
    }

    public static VLocationManager get() {
        return sVLocationManager;
    }

    private void checkWork() {
        if (this.mHandlerThread == null) {
            synchronized (this) {
                if (this.mHandlerThread == null) {
                    this.mHandlerThread = new HandlerThread("loc_thread");
                    this.mHandlerThread.start();
                }
            }
        }
        if (this.mWorkHandler == null) {
            synchronized (this) {
                if (this.mWorkHandler == null) {
                    this.mWorkHandler = new Handler(this.mHandlerThread.getLooper());
                }
            }
        }
    }

    private void stopGpsTask() {
        Handler handler = this.mWorkHandler;
        if (handler != null) {
            handler.removeCallbacks(this.mUpdateGpsStatusTask);
        }
    }

    private void startGpsTask() {
        checkWork();
        stopGpsTask();
        this.mWorkHandler.postDelayed(this.mUpdateGpsStatusTask, 5000L);
    }

    public boolean hasVirtualLocation(String str, int i) {
        try {
            return VirtualLocationManager.get().getMode(i, str) != 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isProviderEnabled(String str) {
        return "gps".equals(str);
    }

    public VLocation getLocation(String str, int i) {
        return getVirtualLocation(str, null, i);
    }

    public VLocation getCurAppLocation() {
        return getVirtualLocation(VClient.get().getCurrentPackage(), null, VUserHandle.myUserId());
    }

    public VLocation getVirtualLocation(String str, Location location, int i) {
        try {
            if (VirtualLocationManager.get().getMode(i, str) == 1) {
                return VirtualLocationManager.get().getGlobalLocation();
            }
            return VirtualLocationManager.get().getLocation(i, str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getPackageName() {
        return VClient.get().getCurrentPackage();
    }

    public void removeGpsStatusListener(Object[] objArr) {
        boolean z = false;
        if (!(objArr[0] instanceof PendingIntent)) {
            synchronized (this.mGpsListeners) {
                this.mGpsListeners.remove(objArr[0]);
                if (this.mGpsListeners.size() == 0) {
                    z = true;
                }
            }
            if (z) {
                stopGpsTask();
            }
        }
    }

    public void addGpsStatusListener(Object[] objArr) {
        Object obj = objArr[0];
        MockLocationHelper.invokeSvStatusChanged(obj);
        if (obj != null) {
            synchronized (this.mGpsListeners) {
                this.mGpsListeners.add(obj);
            }
        }
        checkWork();
        notifyGpsStatus(obj);
        startGpsTask();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyGpsStatus(final Object obj) {
        if (obj != null) {
            this.mWorkHandler.post(new Runnable() { // from class: com.lody.virtual.client.ipc.VLocationManager.2
                @Override // java.lang.Runnable
                public void run() {
                    MockLocationHelper.invokeSvStatusChanged(obj);
                    MockLocationHelper.invokeNmeaReceived(obj);
                }
            });
        }
    }

    public void removeUpdates(Object[] objArr) {
        UpdateLocationTask task;
        if (objArr[0] != null && (task = getTask(objArr[0])) != null) {
            task.stop();
        }
    }

    public void requestLocationUpdates(Object[] objArr) {
        long j;
        Object obj = objArr[Build.VERSION.SDK_INT >= 17 ? 1 : objArr.length - 1];
        if (obj == null) {
            Log.e("VLoc", "ListenerTransport:null");
            return;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            try {
                j = ((Long) Reflect.m18998on(objArr[0]).get("mInterval")).longValue();
            } catch (Throwable unused) {
                j = WaitFor.ONE_MINUTE;
            }
        } else {
            j = ((Long) MethodParameterUtils.getFirstParam(objArr, Long.class)).longValue();
        }
        VLocation curAppLocation = getCurAppLocation();
        checkWork();
        notifyLocation(obj, curAppLocation.toSysLocation(), true);
        UpdateLocationTask task = getTask(obj);
        if (task == null) {
            synchronized (this.mLocationTaskMap) {
                task = new UpdateLocationTask(obj, j);
                this.mLocationTaskMap.put(obj, task);
            }
        }
        task.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean notifyLocation(final Object obj, final Location location, boolean z) {
        if (obj == null) {
            return false;
        }
        if (!z) {
            try {
                LocationManager.C5162g.onLocationChanged.call(obj, location);
                return true;
            } catch (Throwable th) {
                th.printStackTrace();
                return false;
            }
        } else {
            this.mWorkHandler.post(new Runnable() { // from class: com.lody.virtual.client.ipc.VLocationManager.3
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        LocationManager.C5162g.onLocationChanged.call(obj, location);
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                }
            });
            return true;
        }
    }

    private UpdateLocationTask getTask(Object obj) {
        UpdateLocationTask updateLocationTask;
        synchronized (this.mLocationTaskMap) {
            updateLocationTask = this.mLocationTaskMap.get(obj);
        }
        return updateLocationTask;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class UpdateLocationTask implements Runnable {
        private Object mListenerTransport;
        private volatile boolean mRunning;
        private long mTime;

        private UpdateLocationTask(Object obj, long j) {
            this.mListenerTransport = obj;
            this.mTime = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            VLocation curAppLocation;
            if (this.mRunning && (curAppLocation = VLocationManager.this.getCurAppLocation()) != null && VLocationManager.this.notifyLocation(this.mListenerTransport, curAppLocation.toSysLocation(), false)) {
                start();
            }
        }

        public void start() {
            this.mRunning = true;
            VLocationManager.this.mWorkHandler.removeCallbacks(this);
            if (this.mTime > 0) {
                VLocationManager.this.mWorkHandler.postDelayed(this, this.mTime);
            } else {
                VLocationManager.this.mWorkHandler.post(this);
            }
        }

        public void stop() {
            this.mRunning = false;
            VLocationManager.this.mWorkHandler.removeCallbacks(this);
        }
    }
}
