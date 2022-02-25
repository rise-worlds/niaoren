package com.tendcloud.tenddata;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.SparseArray;
import android.util.SparseIntArray;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.apache.tools.ant.taskdefs.WaitFor;
import org.json.JSONObject;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.fr */
/* loaded from: classes2.dex */
public class C2890fr implements SensorEventListener {

    /* renamed from: a */
    private static volatile C2890fr f13986a = null;

    /* renamed from: b */
    private static final int f13987b = 1;

    /* renamed from: f */
    private C2897fy f13991f;

    /* renamed from: h */
    private SparseArray f13993h;

    /* renamed from: c */
    private long f13988c = 0;

    /* renamed from: d */
    private long f13989d = 0;

    /* renamed from: e */
    private SensorManager f13990e = null;

    /* renamed from: g */
    private boolean f13992g = false;

    /* renamed from: i */
    private Handler f13994i = null;

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    /* renamed from: a */
    public static C2890fr m15707a() {
        if (f13986a == null) {
            synchronized (C2890fr.class) {
                if (f13986a == null) {
                    f13986a = new C2890fr();
                }
            }
        }
        return f13986a;
    }

    private C2890fr() {
    }

    static {
        try {
            C2858ev.m15778a().register(m15707a());
        } catch (Throwable unused) {
        }
    }

    public final void onTDEBEventMyna(C2943hl hlVar) {
        if (hlVar != null) {
            try {
                if (hlVar.f14173m != null) {
                    int parseInt = Integer.parseInt(String.valueOf(hlVar.f14173m.get("eventType")));
                    if (parseInt == 13 || parseInt == 14) {
                        if (parseInt == 13) {
                            m15682l();
                            if (this.f13988c > 0 && this.f13989d > 0) {
                                m15706a(parseInt);
                            }
                        } else {
                            m15706a(parseInt);
                        }
                    }
                }
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }
    }

    /* renamed from: a */
    private void m15706a(int i) {
        try {
            switch (i) {
                case 13:
                    m15697b();
                    break;
                case 14:
                    m15686h();
                    break;
                default:
                    return;
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: b */
    private void m15697b() {
        try {
            m15689e();
            this.f13990e = (SensorManager) C2664ab.f13513g.getSystemService("sensor");
            this.f13991f = new C2897fy();
            this.f13993h = new SparseArray();
            m15691d();
            m15694c();
            this.f13994i.sendEmptyMessage(13);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: c */
    private synchronized void m15694c() {
        C2887fp fpVar = new C2887fp(new C2886fo());
        fpVar.m15676b(128);
        fpVar.m15680a(50);
        this.f13993h.put(1, fpVar);
    }

    /* renamed from: d */
    private synchronized void m15691d() {
        C2888fq fqVar = new C2888fq(new C2895fw());
        fqVar.m15676b(128);
        fqVar.m15680a(50);
        fqVar.m15674c(4);
        fqVar.m15674c(9);
        fqVar.m15674c(2);
        this.f13993h.put(0, fqVar);
    }

    /* renamed from: e */
    private void m15689e() {
        HandlerThread handlerThread = new HandlerThread("backgroundHandlerThread");
        handlerThread.start();
        this.f13994i = new HandlerC2891fs(this, handlerThread.getLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public void m15688f() {
        try {
            if (!this.f13992g) {
                m15684j();
                this.f13992g = true;
                m15701a((AbstractC2894fv) this.f13993h.get(0));
                m15701a((AbstractC2894fv) this.f13993h.get(1));
                this.f13994i.sendEmptyMessageDelayed(14, this.f13989d);
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g */
    public void m15687g() {
        try {
            if (this.f13992g) {
                m15683k();
                m15685i();
                m15682l();
                this.f13994i.sendEmptyMessageDelayed(13, this.f13988c);
                this.f13992g = false;
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: h */
    private void m15686h() {
        try {
            this.f13992g = false;
            if (this.f13994i != null) {
                this.f13994i.removeCallbacksAndMessages(null);
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: a */
    private void m15701a(AbstractC2894fv fvVar) {
        C2811dq.iForDeveloper("rHAR Starting recognizer work thread...");
        fvVar.f13997c.post(new RunnableC2892ft(this, fvVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m15700a(AbstractC2894fv fvVar, JSONObject jSONObject) {
        try {
            fvVar.m15679a(jSONObject);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: i */
    private void m15685i() {
        try {
            Intent intent = new Intent("com.anticheating.sdk.updataData");
            C2807dm dmVar = new C2807dm();
            Bundle bundle = new Bundle();
            HashMap hashMap = new HashMap();
            hashMap.put(C2812dr.f13795p, ((AbstractC2894fv) this.f13993h.get(0)).m15672e());
            dmVar.setActivities(((AbstractC2894fv) this.f13993h.get(0)).m15672e().toString());
            ((AbstractC2894fv) this.f13993h.get(0)).m15671f();
            hashMap.put(C2812dr.f13796q, ((AbstractC2894fv) this.f13993h.get(1)).m15672e());
            dmVar.setHandHolding(((AbstractC2894fv) this.f13993h.get(1)).m15672e().toString());
            ((AbstractC2894fv) this.f13993h.get(1)).m15671f();
            bundle.putSerializable("data", dmVar);
            intent.putExtras(bundle);
            C2664ab.f13513g.sendBroadcast(intent);
            C2947ho hoVar = new C2947ho();
            hoVar.f14181b = "antiCheating";
            hoVar.f14182c = "activityRecognition";
            hoVar.f14183d = hashMap;
            hoVar.f14180a = AbstractC2790d.ENV;
            C2858ev.m15778a().post(hoVar);
            m15696b(15);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: b */
    private void m15696b(int i) {
        try {
            C2943hl hlVar = new C2943hl();
            hlVar.f14173m.put("eventType", Integer.valueOf(i));
            C2858ev.m15778a().post(hlVar);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: j */
    private void m15684j() {
        for (int i = 0; i < this.f13993h.size(); i++) {
            try {
                SparseIntArray b = ((AbstractC2894fv) this.f13993h.valueAt(i)).m15677b();
                for (int i2 = 0; i2 < b.size(); i2++) {
                    m15693c(b.keyAt(i2));
                }
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
                return;
            }
        }
    }

    /* renamed from: c */
    private void m15693c(int i) {
        try {
            List<Sensor> sensorList = this.f13990e.getSensorList(-1);
            for (int i2 = 0; i2 < sensorList.size(); i2++) {
                Sensor sensor = sensorList.get(i2);
                if (sensor.getType() == i) {
                    this.f13990e.registerListener(this, sensor, 0);
                }
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: k */
    private void m15683k() {
        try {
            this.f13990e.unregisterListener(this);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        try {
            Message obtain = Message.obtain();
            obtain.what = 1;
            obtain.obj = sensorEvent;
            this.f13994i.sendMessage(obtain);
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m15705a(SensorEvent sensorEvent) {
        try {
            if (sensorEvent.sensor.getType() == 1) {
                m15698a(this.f13991f.f14014a, sensorEvent);
            } else if (sensorEvent.sensor.getType() == 4) {
                m15698a(this.f13991f.f14015b, sensorEvent);
            } else if (sensorEvent.sensor.getType() == 2) {
                m15698a(this.f13991f.f14017d, sensorEvent);
            } else if (sensorEvent.sensor.getType() == 9) {
                m15698a(this.f13991f.f14016c, sensorEvent);
            } else if (sensorEvent.sensor.getType() == 15) {
                m15698a(this.f13991f.f14019f, sensorEvent);
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: a */
    private void m15698a(float[] fArr, SensorEvent sensorEvent) {
        if (fArr == null) {
            try {
                fArr = new float[3];
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
                return;
            }
        }
        System.arraycopy(sensorEvent.values, 0, fArr, 0, 3);
    }

    /* renamed from: l */
    private void m15682l() {
        this.f13989d = WaitFor.ONE_MINUTE;
        this.f13988c = 1800000L;
        try {
            Date date = new Date();
            if (m15699a(date, 6, 23)) {
                this.f13988c = 1800000L;
            } else if (m15699a(date, 0, 5)) {
                this.f13988c = 7200000L;
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: a */
    private boolean m15699a(Date date, int i, int i2) {
        int parseInt = Integer.parseInt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(date).substring(11, 13));
        return parseInt >= i && parseInt <= i2;
    }
}
