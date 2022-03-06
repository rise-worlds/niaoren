package com.lbd.xj.ui.p057fw;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.util.Log;
import android.view.InputEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.common.utils.log.LogUtils;
import com.lbd.xj.app.AppConfig;
import com.lbd.xj.app.XJApp;
import com.lbd.xj.manager.launch.Nativebridge;

import p110z1.PreferencesUtil;
import p110z1.aeo;

/* renamed from: com.lbd.xj.ui.fw.XJLiveView */
/* loaded from: classes.dex */
public class XJLiveView extends SurfaceView implements SurfaceHolder.Callback {

    /* renamed from: a */
    public static int f9862a;

    /* renamed from: b */
    public static int f9863b;

    /* renamed from: c */
    public static int f9864c;

    /* renamed from: d */
    Canvas f9865d;

    /* renamed from: e */
    int f9866e;

    /* renamed from: f */
    SurfaceHolder f9867f;

    /* renamed from: i */
    int f9870i;

    /* renamed from: m */
    private XJOutFloatView.AbstractC1652a f9874m;

    /* renamed from: j */
    private boolean f9871j = true;

    /* renamed from: g */
    int f9868g = 1;

    /* renamed from: k */
    private float f9872k = 1.0f;

    /* renamed from: l */
    private float f9873l = 1.0f;

    /* renamed from: h */
    public boolean f9869h = false;

    public static native int setNativeWindow(int i);

    public static native int setSurface(Surface surface);

    public native void setInputTouchEvent(int i, int i2, InputEvent inputEvent);

    static {
        if (AppConfig.f9445l) {
            System.loadLibrary("vmtools");
        } else {
            System.loadLibrary("native-activity");
        }
    }

    public XJLiveView(Context context, int i, int i2) {
        super(context);
        getHolder().addCallback(this);
        LogUtils.m22037e("surface");
    }

    private int getAvailableHeiht() {
        int i = f9862a;
        if (i != 0) {
            return i;
        }
        f9862a = ((Integer) PreferencesUtil.m13937a().m13927b("surfaceH", Integer.valueOf(aeo.m13920a(XJApp.getInstance().getApplicationContext())))).intValue();
        return f9862a;
    }

    private int getAvailableWidth() {
        int i = f9863b;
        if (i != 0) {
            return i;
        }
        f9863b = ((Integer) PreferencesUtil.m13937a().m13927b("surfaceW", Integer.valueOf(aeo.m13916b(XJApp.getInstance().getApplicationContext())))).intValue();
        LogUtils.m22037e("getWidth:" + f9863b);
        return f9863b;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.f9867f = surfaceHolder;
        LogUtils.m22037e("surfaceCreated");
        if (AppConfig.f9445l) {
            Nativebridge.setSurface(surfaceHolder.getSurface());
        } else {
            setSurface(surfaceHolder.getSurface());
        }
        surfaceHolder.setType(3);
    }

    /* renamed from: a */
    public void m19355a() {
        SurfaceHolder surfaceHolder;
        if (this.f9871j && (surfaceHolder = this.f9867f) != null) {
            if (AppConfig.f9445l) {
                Nativebridge.setSurface(surfaceHolder.getSurface());
            } else {
                setSurface(surfaceHolder.getSurface());
            }
            m19352b();
        }
    }

    /* renamed from: b */
    public static void m19352b() {
        f9864c = 2;
        if (AppConfig.f9445l) {
            Nativebridge.setNativeWindow(f9864c, aeo.m13906g(), aeo.m13904h());
        } else {
            setNativeWindow(f9864c);
        }
    }

    /* renamed from: c */
    public static void m19351c() {
        f9864c = 1;
        if (AppConfig.f9445l) {
            Nativebridge.setNativeWindow(f9864c, aeo.m13906g(), aeo.m13904h());
        } else {
            setNativeWindow(f9864c);
        }
    }

    /* renamed from: d */
    private void m19350d() {
        new Thread(new Runnable() { // from class: com.lbd.xj.ui.fw.XJLiveView.1
            @Override // java.lang.Runnable
            public void run() {
                if (XJLiveView.this.f9867f != null) {
                    XJLiveView.this.m19355a();
                }
            }
        });
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        LogUtils.m22037e("surfaceChanged0, format is " + i + ", width is " + i2 + ", height is" + i3 + "holder" + surfaceHolder.getSurface());
        if ((getResources().getConfiguration().orientation == 2 ? 1 : null) == null) {
            surfaceHolder.setFixedSize(getAvailableWidth(), getAvailableHeiht());
            Log.e("surfaceChanged1", "getAvailableWidth: " + getAvailableWidth() + "getAvailableHeiht: " + getAvailableHeiht());
        } else {
            surfaceHolder.setFixedSize(getAvailableWidth(), getAvailableHeiht());
            Log.e("surfaceChanged2", "getAvailableWidth: " + getAvailableWidth() + "getAvailableHeiht: " + getAvailableHeiht());
        }
        this.f9867f = surfaceHolder;
    }

    /* renamed from: a */
    public void m19354a(int i, int i2) {
        this.f9867f.setFixedSize(i, i2);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        LogUtils.m22037e("surfaceDestroyed");
    }

    /* renamed from: e */
    private int m19349e() {
        return getResources().getConfiguration().orientation == 2 ? 1 : 0;
    }

    /* renamed from: a */
    private boolean m19353a(MotionEvent motionEvent, int i, Configuration configuration) {
        if (i == 2) {
            Log.e("onTouchEvent", "onTouchEvent: 1");
            this.f9872k = (getAvailableHeiht() * 1.0f) / getHeight();
            this.f9873l = (getAvailableWidth() * 1.0f) / getWidth();
            Log.e("横屏", "onChildTouchEvent: scale_x " + this.f9872k + "  scale_y  " + this.f9873l + "getHeight" + getHeight());
        } else if (i == 1) {
            this.f9872k = (getAvailableWidth() * 1.0f) / getWidth();
            this.f9873l = (getAvailableHeiht() * 1.0f) / getHeight();
            Log.e("onTouchEvent", "onTouchEvent: 2");
            Log.e("竖屏", "onChildTouchEvent: scale_x " + this.f9872k + "  scale_y  " + this.f9873l + "getHeight" + getHeight());
        }
        int i2 = 0;
        if (((Boolean) PreferencesUtil.m13937a().m13927b("isRotation", true)).booleanValue()) {
            Log.e("onTouchEvent", "onTouchEvent: 3 PointerCount=" + motionEvent.getPointerCount());
            MotionEvent.PointerCoords[] pointerCoordsArr = new MotionEvent.PointerCoords[motionEvent.getPointerCount()];
            MotionEvent.PointerProperties[] pointerPropertiesArr = new MotionEvent.PointerProperties[motionEvent.getPointerCount()];
            while (i2 < motionEvent.getPointerCount()) {
                MotionEvent.PointerCoords pointerCoords = new MotionEvent.PointerCoords();
                MotionEvent.PointerProperties pointerProperties = new MotionEvent.PointerProperties();
                motionEvent.getPointerCoords(i2, pointerCoords);
                motionEvent.getPointerProperties(i2, pointerProperties);
                pointerCoords.x *= this.f9872k;
                pointerCoords.y *= this.f9873l;
                pointerCoordsArr[i2] = pointerCoords;
                pointerPropertiesArr[i2] = pointerProperties;
                StringBuilder sb = new StringBuilder();
                sb.append("");
                sb.append(pointerCoords.toString());
                sb.append(pointerCoords.x);
                Log.e("getPointerCoords", pointerCoords + " i=" + i2 + " x=" + pointerCoords.x + " y=" + pointerCoords.y);
                i2++;
            }
            MotionEvent obtain = MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), motionEvent.getAction(), motionEvent.getPointerCount(), pointerPropertiesArr, pointerCoordsArr, motionEvent.getMetaState(), motionEvent.getButtonState(), motionEvent.getXPrecision(), motionEvent.getYPrecision(), motionEvent.getDeviceId(), motionEvent.getEdgeFlags(), motionEvent.getSource(), motionEvent.getFlags());
            Log.d("sunya", "newmotionEvent=" + obtain);
            Log.d("sunya", "newmotionEvent.x = " + obtain.getX());
            Log.d("sunya", " newmotionEvent.y=" + obtain.getY());
            Log.d("sunya", "newmotionEvent.action = " + obtain.getAction());
            if (AppConfig.f9445l) {
                Nativebridge.m19712a().setInputTouchEvent((int) motionEvent.getX(), (int) motionEvent.getY(), obtain);
            } else {
                setInputTouchEvent((int) motionEvent.getX(), (int) motionEvent.getY(), obtain);
            }
            Log.d("sunya", "after sendinputTouchEvent");
            return true;
        }
        if (i == 2) {
            this.f9872k = (getAvailableHeiht() * 1.0f) / getWidth();
            this.f9873l = (getAvailableWidth() * 1.0f) / getHeight();
            if (!((Boolean) PreferencesUtil.m13937a().m13927b("isRotation", true)).booleanValue()) {
                MotionEvent.PointerCoords[] pointerCoordsArr2 = new MotionEvent.PointerCoords[motionEvent.getPointerCount()];
                MotionEvent.PointerProperties[] pointerPropertiesArr2 = new MotionEvent.PointerProperties[motionEvent.getPointerCount()];
                while (i2 < motionEvent.getPointerCount()) {
                    MotionEvent.PointerCoords pointerCoords2 = new MotionEvent.PointerCoords();
                    MotionEvent.PointerProperties pointerProperties2 = new MotionEvent.PointerProperties();
                    motionEvent.getPointerCoords(i2, pointerCoords2);
                    motionEvent.getPointerProperties(i2, pointerProperties2);
                    float f = pointerCoords2.x;
                    pointerCoords2.x = getAvailableWidth() - (pointerCoords2.y * this.f9873l);
                    pointerCoords2.y = f * this.f9872k;
                    pointerCoordsArr2[i2] = pointerCoords2;
                    pointerPropertiesArr2[i2] = pointerProperties2;
                    Log.e("getPointerCoords", "" + pointerCoords2.toString() + pointerCoords2.x);
                    i2++;
                }
                InputEvent obtain2 = MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), motionEvent.getAction(), motionEvent.getPointerCount(), pointerPropertiesArr2, pointerCoordsArr2, motionEvent.getMetaState(), motionEvent.getButtonState(), motionEvent.getXPrecision(), motionEvent.getYPrecision(), motionEvent.getDeviceId(), motionEvent.getEdgeFlags(), motionEvent.getSource(), motionEvent.getFlags());
                if (AppConfig.f9445l) {
                    Nativebridge.m19712a().setInputTouchEvent((int) motionEvent.getX(), (int) motionEvent.getY(), obtain2);
                } else {
                    setInputTouchEvent((int) motionEvent.getX(), (int) motionEvent.getY(), obtain2);
                }
                return true;
            }
        } else if (i == 1) {
            this.f9872k = (getAvailableWidth() * 1.0f) / getHeight();
            this.f9873l = (getAvailableHeiht() * 1.0f) / getWidth();
            if (!((Boolean) PreferencesUtil.m13937a().m13927b("isRotation", true)).booleanValue()) {
                MotionEvent.PointerCoords[] pointerCoordsArr3 = new MotionEvent.PointerCoords[motionEvent.getPointerCount()];
                MotionEvent.PointerProperties[] pointerPropertiesArr3 = new MotionEvent.PointerProperties[motionEvent.getPointerCount()];
                while (i2 < motionEvent.getPointerCount()) {
                    MotionEvent.PointerCoords pointerCoords3 = new MotionEvent.PointerCoords();
                    MotionEvent.PointerProperties pointerProperties3 = new MotionEvent.PointerProperties();
                    motionEvent.getPointerCoords(i2, pointerCoords3);
                    motionEvent.getPointerProperties(i2, pointerProperties3);
                    float f2 = pointerCoords3.x;
                    pointerCoords3.x = getAvailableWidth() - (pointerCoords3.y * this.f9872k);
                    pointerCoords3.y = f2 * this.f9873l;
                    pointerCoordsArr3[i2] = pointerCoords3;
                    pointerPropertiesArr3[i2] = pointerProperties3;
                    Log.e("getPointerCoords", "" + pointerCoords3.toString() + pointerCoords3.x);
                    i2++;
                }
                if (AppConfig.f9445l) {
                    Nativebridge.m19712a().setInputTouchEvent((int) motionEvent.getX(), (int) motionEvent.getY(), MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), motionEvent.getAction(), motionEvent.getPointerCount(), pointerPropertiesArr3, pointerCoordsArr3, motionEvent.getMetaState(), motionEvent.getButtonState(), motionEvent.getXPrecision(), motionEvent.getYPrecision(), motionEvent.getDeviceId(), motionEvent.getEdgeFlags(), motionEvent.getSource(), motionEvent.getFlags()));
                } else {
                    setInputTouchEvent((int) motionEvent.getX(), (int) motionEvent.getY(), MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), motionEvent.getAction(), motionEvent.getPointerCount(), pointerPropertiesArr3, pointerCoordsArr3, motionEvent.getMetaState(), motionEvent.getButtonState(), motionEvent.getXPrecision(), motionEvent.getYPrecision(), motionEvent.getDeviceId(), motionEvent.getEdgeFlags(), motionEvent.getSource(), motionEvent.getFlags()));
                }
            }
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        motionEvent.getActionMasked();
        Configuration configuration = getResources().getConfiguration();
        int i = configuration.orientation;
        int actionIndex = motionEvent.getActionIndex();
        Log.e("onTouchEvent", "this.getHeight(): " + getHeight() + "this.getWidth(): " + getWidth() + "   index  " + (actionIndex + 1) + "  getActionMasked  " + motionEvent.getActionMasked());
        if (!this.f9869h) {
            return super.onTouchEvent(motionEvent);
        }
        m19353a(motionEvent, i, configuration);
        XJOutFloatView.AbstractC1652a aVar = this.f9874m;
        if (aVar != null) {
            aVar.mo19302a();
        }
        return true;
    }

    public void setTudingStatus(boolean z) {
        this.f9869h = z;
    }

    public void setOnTouchCallBack(XJOutFloatView.AbstractC1652a aVar) {
        this.f9874m = aVar;
    }
}
