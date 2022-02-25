package com.lbd.p054xj.device.sersor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.HandlerThread;
import com.common.utils.log.LogUtils;
import com.lbd.p054xj.manager.FullJniUtil;

/* renamed from: com.lbd.xj.device.sersor.SensorManagerUtil */
/* loaded from: classes.dex */
public class SensorManagerUtil implements SensorEventListener {
    private static final String TAG = "SensorManagerUtil";
    private int[] Delayms;
    private int[] Rate;
    private SensorManager mSensormanager;
    protected Handler sensorEventHandler;
    private Sensor[] sensors;

    /* renamed from: com.lbd.xj.device.sersor.SensorManagerUtil$AndroidSensor */
    /* loaded from: classes.dex */
    public enum AndroidSensor {
        ANDROID_SENSOR_ACCELERATION,
        ANDROID_SENSOR_MAGNETIC_FIELD,
        ANDROID_SENSOR_ORIENTATION,
        ANDROID_SENSOR_TEMPERATURE,
        ANDROID_SENSOR_PROXIMITY,
        ANDROID_SENSOR_LIGHT,
        ANDROID_SENSOR_PRESSURE,
        ANDROID_SENSOR_HUMIDITY,
        ANDROID_SENSOR_GRAVITY,
        ANDROID_SENSOR_STEP_COUNTER,
        ANDROID_SENSOR_STEP_DETECTOR,
        ANDROID_SENSOR_GYROSCOPE,
        MAX_SENSORS
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.lbd.xj.device.sersor.SensorManagerUtil$SingletonClassInstance */
    /* loaded from: classes.dex */
    public static class SingletonClassInstance {
        public static final SensorManagerUtil manage = new SensorManagerUtil();

        private SingletonClassInstance() {
        }
    }

    public static SensorManagerUtil getInstance() {
        return SingletonClassInstance.manage;
    }

    public void initializeSensorManager(Object obj) {
        if (obj == null) {
            LogUtils.m22036e(TAG, "get SENSOR_SERVICE error");
            return;
        }
        if (obj instanceof SensorManager) {
            this.mSensormanager = (SensorManager) obj;
        }
        this.sensors = new Sensor[32];
        this.Rate = new int[32];
        this.Delayms = new int[32];
        HandlerThread handlerThread = new HandlerThread("sensorEventHandler");
        handlerThread.start();
        this.sensorEventHandler = new Handler(handlerThread.getLooper());
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        int type = sensorEvent.sensor.getType();
        final int GetIdByType = GetIdByType(type);
        if (type == 1 || type == 2 || type == 3 || type == 4) {
            final float f = sensorEvent.values[0];
            final float f2 = sensorEvent.values[1];
            final float f3 = sensorEvent.values[2];
            this.sensorEventHandler.post(new Runnable() { // from class: com.lbd.xj.device.sersor.SensorManagerUtil.4
                @Override // java.lang.Runnable
                public void run() {
                    FullJniUtil.m19759a().SensorChanged(GetIdByType, f, f2, f3);
                }
            });
            return;
        }
        if (type != 5) {
            if (type == 7) {
                final float f4 = sensorEvent.values[0];
                this.sensorEventHandler.post(new Runnable() { // from class: com.lbd.xj.device.sersor.SensorManagerUtil.1
                    @Override // java.lang.Runnable
                    public void run() {
                        FullJniUtil.m19759a().SensorChanged(GetIdByType, f4, 0.0f, 0.0f);
                    }
                });
                return;
            } else if (type == 8) {
                final float f5 = sensorEvent.values[0];
                this.sensorEventHandler.post(new Runnable() { // from class: com.lbd.xj.device.sersor.SensorManagerUtil.2
                    @Override // java.lang.Runnable
                    public void run() {
                        FullJniUtil.m19759a().SensorChanged(GetIdByType, f5, 0.0f, 0.0f);
                    }
                });
                return;
            } else if (!(type == 9 || type == 18 || type == 19)) {
                LogUtils.m22038d(TAG, "default Sensor type=" + type);
                return;
            }
        }
        final float f6 = sensorEvent.values[0];
        this.sensorEventHandler.post(new Runnable() { // from class: com.lbd.xj.device.sersor.SensorManagerUtil.3
            @Override // java.lang.Runnable
            public void run() {
                FullJniUtil.m19759a().SensorChanged(GetIdByType, f6, 0.0f, 0.0f);
            }
        });
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
        LogUtils.m22038d(TAG, "accuracy" + i);
    }

    public void EnableSensors(int i) {
        SensorManager sensorManager = this.mSensormanager;
        if (sensorManager != null) {
            this.sensors[i] = sensorManager.getDefaultSensor(GetTypeById(i));
            if (this.sensors[i] != null) {
                LogUtils.m22038d(TAG, "enablesensors" + this.sensors[i].toString());
                this.mSensormanager.registerListener(this, this.sensors[i], this.Rate[i], this.Delayms[i]);
                return;
            }
            LogUtils.m22036e(TAG, "主机不支持Sensors");
        }
    }

    public boolean CheckSensorsSupport(int i) {
        SensorManager sensorManager = this.mSensormanager;
        return (sensorManager == null || sensorManager.getDefaultSensor(GetTypeById(i)) == null) ? false : true;
    }

    public void DisableSensors(int i) {
        SensorManager sensorManager = this.mSensormanager;
        if (sensorManager != null) {
            Sensor[] sensorArr = this.sensors;
            if (sensorArr[i] != null) {
                sensorManager.unregisterListener(this, sensorArr[i]);
            }
        }
    }

    public void SetDelay(int i, int i2) {
        if (i2 == 1000) {
            this.Rate[i] = 0;
        } else if (i2 == 20000) {
            this.Rate[i] = 1;
        } else if (i2 == 66667) {
            this.Rate[i] = 2;
        } else if (i2 != 200000) {
            LogUtils.m22038d(TAG, "warning get delay error delayms=" + i2);
            this.Rate[i] = 3;
        } else {
            this.Rate[i] = 3;
        }
        this.Delayms[i] = i2;
    }

    private int GetIdByType(int i) {
        int ordinal = AndroidSensor.MAX_SENSORS.ordinal();
        if (i == 12) {
            return AndroidSensor.ANDROID_SENSOR_HUMIDITY.ordinal();
        }
        if (i == 18) {
            return AndroidSensor.ANDROID_SENSOR_STEP_DETECTOR.ordinal();
        }
        if (i == 19) {
            return AndroidSensor.ANDROID_SENSOR_STEP_COUNTER.ordinal();
        }
        switch (i) {
            case 1:
                return AndroidSensor.ANDROID_SENSOR_ACCELERATION.ordinal();
            case 2:
                return AndroidSensor.ANDROID_SENSOR_MAGNETIC_FIELD.ordinal();
            case 3:
                return AndroidSensor.ANDROID_SENSOR_ORIENTATION.ordinal();
            case 4:
                return AndroidSensor.ANDROID_SENSOR_GYROSCOPE.ordinal();
            case 5:
                return AndroidSensor.ANDROID_SENSOR_LIGHT.ordinal();
            case 6:
                return AndroidSensor.ANDROID_SENSOR_PRESSURE.ordinal();
            case 7:
                return AndroidSensor.ANDROID_SENSOR_TEMPERATURE.ordinal();
            case 8:
                return AndroidSensor.ANDROID_SENSOR_PROXIMITY.ordinal();
            case 9:
                return AndroidSensor.ANDROID_SENSOR_GRAVITY.ordinal();
            default:
                LogUtils.m22038d(TAG, "unspport sensors\n");
                return ordinal;
        }
    }

    private int GetTypeById(int i) {
        switch (AndroidSensor.values()[i]) {
            case ANDROID_SENSOR_GYROSCOPE:
                return 4;
            case ANDROID_SENSOR_ACCELERATION:
                return 1;
            case ANDROID_SENSOR_MAGNETIC_FIELD:
                return 2;
            case ANDROID_SENSOR_ORIENTATION:
                return 3;
            case ANDROID_SENSOR_TEMPERATURE:
                return 7;
            case ANDROID_SENSOR_PROXIMITY:
                return 8;
            case ANDROID_SENSOR_LIGHT:
                return 5;
            case ANDROID_SENSOR_PRESSURE:
                return 6;
            case ANDROID_SENSOR_HUMIDITY:
                return 12;
            case ANDROID_SENSOR_GRAVITY:
                return 9;
            case ANDROID_SENSOR_STEP_COUNTER:
                return 19;
            case ANDROID_SENSOR_STEP_DETECTOR:
                return 18;
            default:
                LogUtils.m22038d(TAG, "unknow sensors\n");
                return 0;
        }
    }
}
