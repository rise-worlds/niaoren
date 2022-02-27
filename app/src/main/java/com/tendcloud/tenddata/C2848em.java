package com.tendcloud.tenddata;

import android.content.Context;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.helper.ItemTouchHelper;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.em */
/* loaded from: classes2.dex */
public class C2848em {

    /* renamed from: b */
    private static volatile C2848em f13892b = null;

    /* renamed from: g */
    private static final long f13893g = 10000;

    /* renamed from: h */
    private static final int f13894h = 10;

    /* renamed from: a */
    private Context f13895a;

    /* renamed from: i */
    private SensorManager f13900i;

    /* renamed from: c */
    private final int f13896c = ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;

    /* renamed from: d */
    private final int f13897d = 18;

    /* renamed from: e */
    private long f13898e = 0;

    /* renamed from: f */
    private final int f13899f = 5;

    /* renamed from: j */
    private AbstractC2849a f13901j = null;

    /* renamed from: k */
    private Handler f13902k = new HandlerC2850en(this, Looper.getMainLooper());

    /* renamed from: l */
    private SensorEventListener f13903l = new C2851eo(this);

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.em$a */
    /* loaded from: classes2.dex */
    public interface AbstractC2849a {
        void onAddTestDeviceEvent();
    }

    /* renamed from: a */
    public static C2848em m15820a(Context context) {
        if (f13892b == null) {
            synchronized (C2848em.class) {
                if (f13892b == null) {
                    f13892b = new C2848em(context);
                }
            }
        }
        return f13892b;
    }

    private C2848em(Context context) {
        this.f13895a = null;
        try {
            this.f13895a = context;
            this.f13900i = (SensorManager) context.getSystemService("sensor");
            this.f13900i.registerListener(this.f13903l, this.f13900i.getDefaultSensor(1), 1);
            this.f13902k.sendEmptyMessageDelayed(10, f13893g);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    public void registerTestDeviceListener(AbstractC2849a aVar) {
        this.f13901j = aVar;
    }
}
