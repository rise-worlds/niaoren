package com.lbd.xj.ui.activity;

import android.app.ActivityManager;
import android.app.NativeActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.hardware.Camera;
import android.location.Criteria;
import android.location.LocationManager;
import android.net.LocalServerSocket;
import android.net.LocalSocket;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatDelegate;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import com.blankj.utilcode.util.LogUtils;
import com.common.utils.SharedPreferencesUtil;
import com.lbd.xj.app.XJApp;
import com.lbd.xj.device.camera.CameraController;
import com.lbd.xj.device.lcd.DisplayParam;
import com.lbd.xj.device.location.LocationController;
import com.lbd.xj.device.sersor.SensorController;
import com.lbd.xj.device.wifi.WifiController;
import com.lbd.xj.keeplive.DaemonService;
import com.lbd.xj.keeplive.LiveActivity;
import com.lbd.xj.keeplive.PlayerMusicService;
import com.lbd.xj.manager.launch.BoxLaunchManager;
import com.lbd.xj.ui.dialog.AppRomUpdateDialog;
import com.lbd.xj.ui.dialog.LaunchFullDialog;
import com.lbd.xj.ui.p057fw.XJLiveView;
import com.lbd.xj.receiver.KeyCodeReceiver;
import com.lbd.xj.service.XJFloatService;
import com.lbd.xj.socket.SocketManagerServer;
import com.lbd.xj.utils.vmlog.VMLogUtils;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.nrzs.base.router.RouterUtils;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import org.apache.tools.ant.taskdefs.WaitFor;
import p110z1.AppUpdateEvent;
import p110z1.EventBus;
import p110z1.EventBusMessage;
import p110z1.EventCollectManager;
import p110z1.EventConstants;
import p110z1.ExistXJEvent;
import p110z1.FloatingPermissionCompat;
import p110z1.FwManager;
import p110z1.KeyCodeEvent;
import p110z1.PermissionUtil;
import p110z1.PreferencesUtil;
import p110z1.SharepreferenceUtil;
import p110z1.Subscribe;
import p110z1.ThreadMode;
import p110z1.ValReceiver;
import p110z1.XJLiveInitTask2;
import p110z1.acf;
import p110z1.aej;
import p110z1.aeo;

/* renamed from: com.lbd.xj.ui.activity.XJFullActivity */
/* loaded from: classes.dex */
public class XJFullActivity extends NativeActivity {
    private static boolean DBG = false;
    private static final String TAG = "SHENG";
    private static final long checkTime = 3000;
    public static boolean fullActivityIsStart = false;
    private static CameraController mCameraController = null;
    private static LocationController mLocationController = null;
    private static SensorController mSensorController = null;
    private static WifiController mWifiController = null;
    static int nstatic = 1;
    private PlayerMusicService countService;
    private boolean isInitBoot;
    private DisplayParam mDisplay;
    private KeyCodeReceiver mLocationReceiver;
    String m_getSetH;
    String m_getSetW;
    LaunchFullDialog myFullDialog;
    private Intent playerMusicService;
    private int time;
    private boolean isSendMessage = false;
    private boolean isWaitiing = false;
    private boolean isStarting = true;
    private boolean isfirst = true;
    private Handler mLiveHandler = new Handler();
    private Handler foregroundHandler = new Handler();
    private Runnable runnable = new Runnable() { // from class: com.lbd.xj.ui.activity.XJFullActivity.1
        @Override // java.lang.Runnable
        public void run() {
            LiveActivity.m19777a(XJFullActivity.this);
            XJFullActivity.this.isSendMessage = true;
        }
    };
    private Runnable checkForeground = new Runnable() { // from class: com.lbd.xj.ui.activity.XJFullActivity.8
        @Override // java.lang.Runnable
        public void run() {
            if (XJFullActivity.this.isAppOnForeground()) {
                XJFullActivity.this.mLiveHandler.removeCallbacks(XJFullActivity.this.runnable);
                XJFullActivity.this.mLiveHandler.postDelayed(XJFullActivity.this.checkForeground, XJFullActivity.checkTime);
                XJFullActivity.this.isSendMessage = false;
            } else if (!XJFullActivity.this.isSendMessage) {
                XJFullActivity.this.mLiveHandler.postDelayed(XJFullActivity.this.runnable, XJFullActivity.this.time);
                XJFullActivity.this.isSendMessage = true;
            }
        }
    };
    private ServiceConnection conn = new ServiceConnection() { // from class: com.lbd.xj.ui.activity.XJFullActivity.9
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            XJFullActivity.this.countService = ((PlayerMusicService.BinderC1497a) iBinder).getService();
            XJFullActivity.this.countService.m19772b();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            XJFullActivity.this.countService = null;
            XJFullActivity.this.startPlayMusicService();
        }
    };

    public static native void CameraOnStop(String str);

    public static native void CameraPreview(String str, byte[] bArr);

    private void SetGPSInfo() {
    }

    public static native int getbootstats(String str);

    /* JADX INFO: Access modifiers changed from: private */
    public void initUpdate() {
    }

    public native void GpsChanged(int i, int i2, double d, double d2, double d3, double d4, double d5, double d6, long j);

    public native void GpsnmeaChanged(long j, String str);

    public native void MyWIFIChanged(String str);

    public native void SensorChanged(int i, float f, float f2, float f3);

    public native int start_pipe(String str);

    public native int test_linker();

    static {
        System.loadLibrary("rfbox");
        System.loadLibrary("native-lib");
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public boolean isAppOnForeground() {
        String packageName = getPackageName();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) getSystemService(ServiceManagerNative.ACTIVITY)).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.processName.equals(packageName) && runningAppProcessInfo.importance == 100) {
                return true;
            }
        }
        return false;
    }

    @Override // android.app.NativeActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        getWindow().setFlags(128, 128);
        super.onCreate(bundle);
        LogUtils.m23734c("XJActivity", "XJFullActivity - initData");
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        if (Build.VERSION.SDK_INT >= 28) {
            attributes.layoutInDisplayCutoutMode = 2;
            getWindow().setAttributes(attributes);
        }
        EventBus.m3448a().m3446a(this);
        SocketManagerServer.m19692b().m19703a(new SocketManagerServer.AbstractC1534a() { // from class: com.lbd.xj.ui.activity.XJFullActivity.10
            @Override // com.lbd.xj.socket.SocketManagerServer.AbstractC1534a
            /* renamed from: a */
            public void mo19536a() {
                XJFullActivity.this.closeFullActivity();
            }
        });
        VMLogUtils.m19277b().m19276c();
        if (nstatic == 1) {
            startHWPipe();
        }
        requestPermission();
        startPlayMusicService();
        if (PreferencesUtil.m13937a().m13929a("isOpen", false)) {
            this.time = PreferencesUtil.m13937a().m13933a("time", 0);
            startForegroundCheck();
        }
        initReceiver();
        initDisplayParam();
        showAnimaDialog();
        fullActivityIsStart = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initWindowService() {
        if (!FloatingPermissionCompat.m14338a().m14337a(this)) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            startForegroundService(new Intent(this, XJFloatService.class));
        } else {
            startService(new Intent(this, XJFloatService.class));
        }
    }

    private void initDevice() {
        initSensors();
        initCamera();
        InitWifi();
        InitGPS();
    }

    private void requestPermission() {
        switch (PermissionUtil.m11847a(this, "android.permission.ACCESS_FINE_LOCATION")) {
            case DENIED:
            default:
                return;
            case GRANTED:
                initDevice();
                return;
        }
    }

    private void initReceiver() {
        this.mLocationReceiver = new KeyCodeReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("XJKEYACTION");
        registerReceiver(this.mLocationReceiver, intentFilter);
    }

    private void unRegisterReceiver() {
        KeyCodeReceiver keyCodeReceiver = this.mLocationReceiver;
        if (keyCodeReceiver != null) {
            unregisterReceiver(keyCodeReceiver);
        }
    }

    private void initDisplayParam() {
        this.mDisplay = (DisplayParam) getIntent().getParcelableExtra(acf.f15182g);
        if (this.mDisplay == null) {
            this.mDisplay = (DisplayParam) SharepreferenceUtil.m13892a(acf.f15182g, DisplayParam.class);
        }
        if (nstatic == 1) {
            nstatic = 0;
            this.m_getSetH = String.valueOf(aeo.m13904h());
            this.m_getSetW = String.valueOf(aeo.m13906g());
        }
        if (DBG) {
            com.common.utils.log.LogUtils.m22038d(TAG, "MY_DEBUG_VA2:port=" + this.mDisplay.getPort());
        }
    }

    private void showAnimaDialog() {
        if (!XJApp.getInstance().systemIsLive) {
            this.myFullDialog = new LaunchFullDialog(this);
            this.myFullDialog.show();
            this.myFullDialog.getWindow().getDecorView().setSystemUiVisibility(2);
            this.myFullDialog.getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.lbd.xj.ui.activity.XJFullActivity.11
                @Override // android.view.View.OnSystemUiVisibilityChangeListener
                public void onSystemUiVisibilityChange(int i) {
                    XJFullActivity.this.myFullDialog.getWindow().getDecorView().setSystemUiVisibility(Build.VERSION.SDK_INT >= 19 ? 5894 : 1799);
                }
            });
            initCompliteThread();
        }
        XJApp.getInstance().systemIsLive = true;
    }

    /* JADX WARN: Type inference failed for: r0v8, types: [com.lbd.xj.ui.activity.XJFullActivity$12] */
    private void initCompliteThread() {
        long j;
        this.isInitBoot = SharedPreferencesUtil.getInstance().getBoolean(acf.f15186k).booleanValue();
        if (this.isInitBoot) {
            j = SharedPreferencesUtil.getInstance().getLong(acf.f15187l).longValue();
            if (j == 0) {
                this.isInitBoot = false;
            }
        } else {
            j = WaitFor.ONE_MINUTE;
        }
        LaunchFullDialog cVar = this.myFullDialog;
        if (cVar != null) {
            cVar.m19458a(j);
        }
        LogUtils.m23742b("SHENG DEBUG", "isWaitiing:" + this.isWaitiing);
        if (!this.isWaitiing) {
            this.isWaitiing = true;
            new Thread() { // from class: com.lbd.xj.ui.activity.XJFullActivity.12
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        LocalServerSocket localServerSocket = new LocalServerSocket("runstatus");
                        LogUtils.m23742b("SHENG DEBUG", "isStarting:" + XJFullActivity.this.isStarting);
                        while (XJFullActivity.this.isStarting) {
                            XJFullActivity.this.listeningStartSocket(localServerSocket.accept());
                        }
                    } catch (IOException e) {
                        com.common.utils.log.LogUtils.m22038d("SHENG DEBUG", "initCompliteThread e" + e.toString());
                        EventBus.m3448a().m3423f(new EventBusMessage(acf.f15183h));
                        e.printStackTrace();
                    }
                    XJFullActivity.this.isWaitiing = false;
                }
            }.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.lbd.xj.ui.activity.XJFullActivity$13] */
    public void listeningStartSocket(LocalSocket localSocket) {
        new XJLiveInitTask2(localSocket, this.isInitBoot) { // from class: com.lbd.xj.ui.activity.XJFullActivity.13
            @Override // p110z1.XJLiveInitTask2
            /* renamed from: a */
            public void mo14299a(int i) {
                LogUtils.m23742b("SHENG DEBUG", "isWaitiing:" + i);
                if (i == 666666) {
                    XJFullActivity.this.isStarting = false;
                    XJFullActivity.this.initUpdate();
                    XJFullActivity.this.initWindowService();
                    EventCollectManager.m12642a().m12640a(XJFullActivity.this, "后台挂机虚拟桌面", "后台挂机虚拟桌面", EventConstants.f16405I);
                } else if (!XJFullActivity.this.isFinishing() && XJFullActivity.this.myFullDialog != null) {
                    XJFullActivity.this.myFullDialog.m19460a(i);
                }
            }
        }.start();
    }

    private int getWidth() {
        int b = aeo.m13916b(XJApp.getInstance().getApplicationContext());
        try {
            b = ((Integer) PreferencesUtil.m13937a().m13927b("surfaceW", Integer.valueOf(b))).intValue();
            com.common.utils.log.LogUtils.m22037e("getWiidth:" + b);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
            return b;
        }
    }

    private int getHeight() {
        int a = aeo.m13920a(XJApp.getInstance().getApplicationContext());
        try {
            return ((Integer) PreferencesUtil.m13937a().m13927b("surfaceH", Integer.valueOf(a))).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return a;
        }
    }

    private void startForegroundCheck() {
        this.foregroundHandler.postDelayed(this.checkForeground, checkTime);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startPlayMusicService() {
        this.playerMusicService = new Intent(this, PlayerMusicService.class);
        if (Build.VERSION.SDK_INT >= 26) {
            startForegroundService(this.playerMusicService);
        } else {
            startService(this.playerMusicService);
        }
        bindService(this.playerMusicService, this.conn, 1);
    }

    void InitWifi() {
        if (mWifiController == null) {
            mWifiController = new WifiController(new WifiController.WIFIChangedListener() { // from class: com.lbd.xj.ui.activity.XJFullActivity.14
                @Override // com.lbd.xj.device.wifi.WifiController.WIFIChangedListener
                public void wifiChanged(String str) {
                    XJFullActivity.this.MyWIFIChanged(str);
                }
            });
        }
    }

    void GetWIFIInfo() {
        mWifiController.getWIFIInfo();
    }

    private void InitGPS() {
        if (mLocationController == null) {
            mLocationController = new LocationController();
            mLocationController.setGpsChangedListener(new LocationController.GpsChangedListener() { // from class: com.lbd.xj.ui.activity.XJFullActivity.15
                @Override // com.lbd.xj.device.location.LocationController.GpsChangedListener
                public void gpsChanged(int i, int i2, double d, double d2, double d3, double d4, double d5, double d6, long j) {
                    XJFullActivity.this.GpsChanged(i, i2, d, d2, d3, d4, d5, d6, j);
                }

                @Override // com.lbd.xj.device.location.LocationController.GpsChangedListener
                public void gpsnmeaChanged(long j, String str) {
                    XJFullActivity.this.GpsnmeaChanged(j, str);
                }
            });
        }
    }

    public void initCamera() {
        if (mCameraController == null) {
            mCameraController = new CameraController();
        }
    }

    void initSensors() {
        if (mSensorController == null) {
            mSensorController = new SensorController();
            SensorController sensorController = mSensorController;
            if (sensorController == null) {
                com.common.utils.log.LogUtils.m22036e(TAG, "get SENSOR_SERVICE error");
            } else {
                sensorController.setSensorChangedListener(new SensorController.SensorChangedListener() { // from class: com.lbd.xj.ui.activity.XJFullActivity.2
                    @Override // com.lbd.xj.device.sersor.SensorController.SensorChangedListener
                    public void sensorChanged(int i, float f, float f2, float f3) {
                        XJFullActivity.this.SensorChanged(i, f, f2, f3);
                    }
                });
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.lbd.xj.ui.activity.XJFullActivity$3] */
    public void startHWPipe() {
        new Thread() { // from class: com.lbd.xj.ui.activity.XJFullActivity.3
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                XJFullActivity xJFullActivity = XJFullActivity.this;
                xJFullActivity.start_pipe(xJFullActivity.getApplicationInfo().nativeLibraryDir);
            }
        }.start();
    }

    public void closeFullActivity() {
        finish();
    }

    @Subscribe(m3389a = ThreadMode.MAIN, m3388b = true)
    public void event(EventBusMessage acmVar) {
        char c;
        String a = acmVar.m14349a();
        int hashCode = a.hashCode();
        if (hashCode != -291747593) {
            if (hashCode == 2123504429 && a.equals(acf.f15183h)) {
                c = 0;
            }
            c = 65535;
        } else {
            if (a.equals(acf.f15184i)) {
                c = 1;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
                LaunchFullDialog cVar = this.myFullDialog;
                if (cVar != null) {
                    cVar.cancel();
                    return;
                }
                return;
            case 1:
                if (this.myFullDialog != null) {
                    if (this.isfirst) {
                        this.isfirst = false;
                    }
                    this.myFullDialog.cancel();
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        switch (PermissionUtil.m11847a(this, "android.permission.ACCESS_FINE_LOCATION")) {
            case DENIED:
            default:
                return;
            case GRANTED:
                InitGPS();
                return;
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (3 != i) {
            return super.onKeyDown(i, keyEvent);
        }
        Toast.makeText(getApplicationContext(), "HOME 键已被禁用...", 0).show();
        return true;
    }

    @Override // android.app.NativeActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        FwManager.getInstance().isFront = true;
        sendBroadcast("onPause");
    }

    @Override // android.app.NativeActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        FwManager.getInstance().isFront = false;
        sendBroadcast("onResume");
        if (XJLiveView.f9864c == 2) {
            XJLiveView.m19351c();
        }
    }

    @Override // android.app.NativeActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
    }

    @Override // android.app.NativeActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            getWindow().getDecorView().setSystemUiVisibility(5894);
        }
    }

    private void sendBroadcast(String str) {
        Intent intent = new Intent();
        intent.putExtra("key", str);
        intent.setAction(ValReceiver.f15490a);
        sendBroadcast(intent);
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    public void onEvent(KeyCodeEvent acpVar) {
        if (KeyCodeReceiver.f9484a.equals(acpVar.m14345a())) {
            onBackPressed();
        } else if (KeyCodeReceiver.f9485b.equals(acpVar.m14345a())) {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setFlags(268435456);
            intent.addCategory("android.intent.category.HOME");
            startActivity(intent);
        }
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    public void onEvent(ExistXJEvent acnVar) {
        RouterUtils.toMain(0);
        BoxLaunchManager.none = 0;
        finish();
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    public void onEvent(AppUpdateEvent aclVar) {
        if (aclVar != null && aclVar.m14350b() != null) {
            new AppRomUpdateDialog(this, aclVar.m14350b()).show();
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    static int CameraConnect(String str) {
        return mCameraController.cameraConnect();
    }

    static int CameraDisConnect(String str) {
        return mCameraController.cameraDisConnect();
    }

    static int CameraFrame(String str, float f, float f2, float f3, float f4, int i) {
        return mCameraController.cameraFrame(i);
    }

    static String CameraList() {
        return mCameraController.cameraList();
    }

    public static boolean CheckSensorsSupport(int i) {
        return mSensorController.hasDefaultSensor(i);
    }

    public static void DisableSensors(int i) {
        mSensorController.unregisterListener(i);
    }

    public static void EnableSensors(int i) {
        mSensorController.enableSensors(i);
    }

    public static long Jni_method_invoke(int i, int i2, int i3, int i4) {
        try {
            Class<?> cls = Class.forName(Build.VERSION.SDK_INT >= 26 ? "android.graphics.GraphicBuffer" : "android.view.GraphicBuffer");
            com.common.utils.log.LogUtils.m22038d(TAG, "ownerclass is " + cls);
            Method declaredMethod = cls.getDeclaredMethod("create", Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE);
            Field declaredField = cls.getDeclaredField("mNativeObject");
            declaredField.setAccessible(true);
            new Object();
            com.common.utils.log.LogUtils.m22038d(TAG, "xx=" + declaredField.getName());
            com.common.utils.log.LogUtils.m22034i(TAG, "method is " + declaredMethod);
            declaredMethod.setAccessible(true);
            return declaredField.getLong(declaredMethod.invoke(null, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)));
        } catch (Exception e) {
            e.printStackTrace();
            com.common.utils.log.LogUtils.m22034i(TAG, "error " + e.toString());
            return 0L;
        }
    }

    public static void SetDelay(int i, int i2) {
        mSensorController.setDelay(i, i2);
    }

    static void SetGpsStart(int i, int i2) {
        mLocationController.setGpsStart();
    }

    static void SetGpsStop(int i, int i2) {
        mLocationController.setGpsStop();
    }

    static void SetGpsnmeaStart(int i, int i2) {
        mLocationController.setGpsnmeaStart();
    }

    static void SetGpsnmeaStop(int i, int i2) {
        mLocationController.setGpsnmeaStop();
    }

    static void SetWifiStart(int i, int i2) {
        mWifiController.setWifiStart(1);
    }

    static int getIsStartSendMywifiData() {
        return mWifiController.getIsStartSendMywifiData();
    }

    static int setIsStartSendMywifiData(int i) {
        mWifiController.setWifiStart(i);
        return i;
    }

    static int getIsStartGPS() {
        return mLocationController.getIsStartGPS();
    }

    static LocationManager getlm() {
        return mLocationController.getLocationManager();
    }

    static int getIsStartnmeaGPS() {
        return mLocationController.getIsStartnmeaGPS();
    }

    public void enterWhiteListSetting(Context context) {
        try {
            context.startActivity(aej.m13958a());
        } catch (Exception unused) {
            context.startActivity(new Intent("android.settings.SETTINGS"));
        }
    }

    private Criteria getCriteria() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(1);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(false);
        criteria.setBearingRequired(false);
        criteria.setAltitudeRequired(false);
        criteria.setPowerRequirement(1);
        return criteria;
    }

    public String getPackageDataName() {
        return getApplicationInfo().dataDir;
    }

    public String getPackageDataNativeLibraryDir() {
        return getApplicationInfo().nativeLibraryDir;
    }

    public String getSeth() {
        LogUtils.m23742b("getSeth", this.m_getSetH);
        return this.m_getSetH;
    }

    public String getSetw() {
        LogUtils.m23742b("getSetw", this.m_getSetW);
        return this.m_getSetW;
    }

    public String getforce() {
        LogUtils.m23742b("getforce", this.mDisplay.getIsForceMode());
        return this.mDisplay.getIsForceMode();
    }

    public String getw() {
        LogUtils.m23742b("getw", this.mDisplay.getDisplayWidth());
        return this.mDisplay.getDisplayWidth();
    }

    public String geth() {
        LogUtils.m23742b("geth", this.mDisplay.getDisplayHeight());
        return this.mDisplay.getDisplayHeight();
    }

    public String getport() {
        LogUtils.m23742b("getport", this.mDisplay.getPort());
        return this.mDisplay.getPort();
    }

    public float getscaleh() {
        LogUtils.m23742b("getscaleh", Float.valueOf(this.mDisplay.getScaleH()));
        return this.mDisplay.getScaleH();
    }

    public float getscalew() {
        LogUtils.m23742b("getscalew", Float.valueOf(this.mDisplay.getScaleW()));
        return this.mDisplay.getScaleW();
    }

    public String getsh() {
        LogUtils.m23742b("getsh", this.mDisplay.getDisplayScaleH());
        return this.mDisplay.getDisplayScaleH();
    }

    public String getsw() {
        return this.mDisplay.getDisplayScaleW();
    }

    static CameraController.CameraState getmCameraState() {
        return mCameraController.getCameraState();
    }

    static CameraController.CameraState setmCameraState(CameraController.CameraState cameraState) {
        mCameraController.setCameraState(cameraState);
        return cameraState;
    }

    static String getmCameraId() {
        return mCameraController.getCameraId();
    }

    static Camera getmCamera() {
        return mCameraController.getCamera();
    }

    static void getPreViewImage() {
        mCameraController.getPreViewImage(new CameraController.CameraPreviewListener() { // from class: com.lbd.xj.ui.activity.XJFullActivity.4
            @Override // com.lbd.xj.device.camera.CameraController.CameraPreviewListener
            public void cameraPreview(String str, byte[] bArr) {
                XJFullActivity.CameraPreview(str, bArr);
            }
        });
    }

    private void initCameraLooper() {
        mCameraController.initCameraLooper();
    }

    public static void reConnectCamera() {
        mCameraController.reConnectCamera(new CameraController.CameraPreviewListener() { // from class: com.lbd.xj.ui.activity.XJFullActivity.5
            @Override // com.lbd.xj.device.camera.CameraController.CameraPreviewListener
            public void cameraPreview(String str, byte[] bArr) {
                XJFullActivity.CameraPreview(str, bArr);
            }
        });
    }

    public static void setCameraDisplayOrientation(int i, Camera camera) {
        mCameraController.setCameraDisplayOrientation(i, camera);
    }

    static int CameraStart(String str, int i, int i2, int i3) {
        return mCameraController.cameraStart(str, i, i2, i3, new CameraController.CameraPreviewListener() { // from class: com.lbd.xj.ui.activity.XJFullActivity.6
            @Override // com.lbd.xj.device.camera.CameraController.CameraPreviewListener
            public void cameraPreview(String str2, byte[] bArr) {
                XJFullActivity.CameraPreview(str2, bArr);
            }
        });
    }

    static int CameraStop(String str) {
        return mCameraController.cameraStop(str);
    }

    private void startDaemonService() {
        startService(new Intent(this, DaemonService.class));
    }

    @Override // android.app.NativeActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        com.common.utils.log.LogUtils.m22037e("Full onDestroy");
        if (this.playerMusicService != null) {
            unbindService(this.conn);
        }
        this.mLiveHandler.removeCallbacksAndMessages(null);
        this.foregroundHandler.removeCallbacksAndMessages(null);
        unRegisterReceiver();
        EventBus.m3448a().m3430c(this);
    }
}
