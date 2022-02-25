package com.lbd.p054xj.device.sersor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import com.common.utils.log.LogUtils;
import com.lbd.p054xj.app.XJApp;

/* renamed from: com.lbd.xj.device.sersor.SensorController */
/* loaded from: classes.dex */
public class SensorController {
    private SensorChangedListener mListener;
    protected Handler sensorEventHandler;
    private SensorEventListener mSensorListener = null;
    private SensorManager mSensormanager = null;
    private Sensor[] sensors = new Sensor[32];
    public int[] Rate = new int[32];
    public int[] Delayms = new int[32];

    /* renamed from: com.lbd.xj.device.sersor.SensorController$AndroidSensor */
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
        MAX_SENSORS
    }

    /* renamed from: com.lbd.xj.device.sersor.SensorController$SensorChangedListener */
    /* loaded from: classes.dex */
    public interface SensorChangedListener {
        void sensorChanged(int i, float f, float f2, float f3);
    }

    public SensorController() {
        initSensors();
    }

    public void setSensorChangedListener(SensorChangedListener sensorChangedListener) {
        this.mListener = sensorChangedListener;
    }

    private void initSensors() {
        if (this.mSensormanager == null) {
            this.mSensormanager = (SensorManager) XJApp.getInstance().getApplicationContext().getSystemService("sensor");
            if (this.mSensormanager == null) {
                LogUtils.m22036e("SHENG", "get SENSOR_SERVICE error");
            }
            this.mSensorListener = new SensorEventListener() { // from class: com.lbd.xj.device.sersor.SensorController.1
                @Override // android.hardware.SensorEventListener
                public void onAccuracyChanged(Sensor sensor, int i) {
                    LogUtils.m22038d("SHENG", "accuracy" + i);
                }

                @Override // android.hardware.SensorEventListener
                public void onSensorChanged(SensorEvent sensorEvent) {
                    int type = sensorEvent.sensor.getType();
                    int idBySensorType = SensorController.getIdBySensorType(type);
                    if (type == 1 || type == 2 || type == 3 || type == 9) {
                        SensorController.this.sensorChanged(idBySensorType, sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);
                    } else if (type == 5) {
                        SensorController.this.sensorChanged(idBySensorType, sensorEvent.values[0], 0.0f, 0.0f);
                    } else if (type == 7) {
                        SensorController.this.sensorChanged(idBySensorType, sensorEvent.values[0], 0.0f, 0.0f);
                    } else if (type == 8) {
                        SensorController.this.sensorChanged(idBySensorType, sensorEvent.values[0], 0.0f, 0.0f);
                    } else if (type != 18 && type != 19) {
                        LogUtils.m22038d("SHENG", "default Sensor type=" + type);
                    }
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sensorChanged(int i, float f, float f2, float f3) {
        SensorChangedListener sensorChangedListener = this.mListener;
        if (sensorChangedListener != null) {
            sensorChangedListener.sensorChanged(i, f, f2, f3);
        }
    }

    public static int getIdBySensorType(int i) {
        switch (i) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
            case 10:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            default:
                LogUtils.m22038d("SHENG", "unknow sensors\n");
                return 0;
            case 5:
                return 6;
            case 6:
                return 7;
            case 7:
                return 4;
            case 8:
                return 5;
            case 9:
                return 9;
            case 12:
                return 8;
            case 18:
                return 11;
            case 19:
                return 10;
        }
    }

    public int getIdBySensorType1(int i) {
        switch (i) {
            case 1:
                return AndroidSensor.ANDROID_SENSOR_ACCELERATION.ordinal();
            case 2:
                return AndroidSensor.ANDROID_SENSOR_MAGNETIC_FIELD.ordinal();
            case 3:
                return AndroidSensor.ANDROID_SENSOR_ORIENTATION.ordinal();
            case 4:
            case 10:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            default:
                LogUtils.m22038d("SHENG", "unspport sensors\n");
                return AndroidSensor.MAX_SENSORS.ordinal();
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
            case 12:
                return AndroidSensor.ANDROID_SENSOR_HUMIDITY.ordinal();
            case 18:
                return AndroidSensor.ANDROID_SENSOR_STEP_DETECTOR.ordinal();
            case 19:
                return AndroidSensor.ANDROID_SENSOR_STEP_COUNTER.ordinal();
        }
    }

    public boolean hasDefaultSensor(int i) {
        return this.mSensormanager.getDefaultSensor(getTypeById(i)) != null;
    }

    public Sensor getDefaultSensor(int i) {
        return this.mSensormanager.getDefaultSensor(getTypeById(i));
    }

    public void registerListener(int i) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.mSensormanager.registerListener(this.mSensorListener, this.sensors[i], this.Rate[i], this.Delayms[i]);
        } else {
            this.mSensormanager.registerListener(this.mSensorListener, this.sensors[i], this.Rate[i]);
        }
    }

    public void unregisterListener(int i) {
        SensorManager sensorManager = this.mSensormanager;
        if (sensorManager != null) {
            Sensor[] sensorArr = this.sensors;
            if (sensorArr[i] != null) {
                sensorManager.unregisterListener(this.mSensorListener, sensorArr[i]);
            }
        }
    }

    public void setRate(int i, int i2) {
        this.Rate[i] = i2;
    }

    public void setDelayms(int i, int i2) {
        this.Delayms[i] = i2;
    }

    private int getTypeById(int i) {
        switch (i) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 7;
            case 5:
                return 8;
            case 6:
                return 5;
            case 7:
                return 6;
            case 8:
                return 12;
            case 9:
                return 9;
            case 10:
                return 19;
            case 11:
                return 18;
            default:
                LogUtils.m22038d("SHENG", "unknow sensors\n");
                return 0;
        }
    }

    public void enableSensors(int i) {
        this.sensors[i] = getDefaultSensor(i);
        if (this.sensors[i] == null) {
            LogUtils.m22036e("SHENG", "主机不支持Sensors");
            return;
        }
        LogUtils.m22038d("SHENG", "enablesensors" + this.sensors[i].toString());
        registerListener(i);
    }

    public void setDelay(int i, int i2) {
        if (i2 == 1000) {
            setRate(i, 0);
        } else if (i2 == 20000) {
            setRate(i, 1);
        } else if (i2 == 66667) {
            setRate(i, 2);
        } else if (i2 != 200000) {
            LogUtils.m22038d("SHENG", "warning get delay error delayms=" + i2);
            setRate(i, 3);
        } else {
            setRate(i, 3);
        }
        setDelayms(i, i2);
    }

    /* renamed from: com.lbd.xj.device.sersor.SensorController$SingletonClassInstance */
    /* loaded from: classes.dex */
    private static class SingletonClassInstance {
        public static final SensorController manage = new SensorController();

        private SingletonClassInstance() {
        }
    }

    public static SensorController getInstance() {
        return SingletonClassInstance.manage;
    }

    public void initializeSensorManager(Object obj) {
        if (obj != null) {
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
    }
}
