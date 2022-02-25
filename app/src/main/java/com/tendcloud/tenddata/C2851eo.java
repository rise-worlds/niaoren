package com.tendcloud.tenddata;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import com.tendcloud.tenddata.C2848em;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.eo */
/* loaded from: classes2.dex */
public class C2851eo implements SensorEventListener {
    private int shakeCount = 0;
    final /* synthetic */ C2848em this$0;

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2851eo(C2848em emVar) {
        this.this$0 = emVar;
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        long j;
        C2848em.AbstractC2849a aVar;
        Context context;
        C2848em.AbstractC2849a aVar2;
        SensorManager sensorManager;
        SensorManager sensorManager2;
        SensorEventListener sensorEventListener;
        Context context2;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            int type = sensorEvent.sensor.getType();
            j = this.this$0.f13898e;
            if (currentTimeMillis - j > 250 && type == 1) {
                this.this$0.f13898e = currentTimeMillis;
                float[] fArr = sensorEvent.values;
                if (Math.abs(fArr[0]) > 18.0f || Math.abs(fArr[1]) > 18.0f || Math.abs(fArr[2]) > 18.0f) {
                    this.shakeCount++;
                }
                if (this.shakeCount >= 5) {
                    aVar = this.this$0.f13901j;
                    if (aVar != null) {
                        context = this.this$0.f13895a;
                        if (C2855es.m15792b(context, "android.permission.VIBRATE")) {
                            context2 = this.this$0.f13895a;
                            ((Vibrator) context2.getSystemService("vibrator")).vibrate(100L);
                        }
                        aVar2 = this.this$0.f13901j;
                        aVar2.onAddTestDeviceEvent();
                        sensorManager = this.this$0.f13900i;
                        if (sensorManager != null) {
                            sensorManager2 = this.this$0.f13900i;
                            sensorEventListener = this.this$0.f13903l;
                            sensorManager2.unregisterListener(sensorEventListener);
                        }
                    }
                    this.shakeCount = 0;
                }
            }
        } catch (Throwable unused) {
        }
    }
}
