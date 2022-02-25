package com.lbd.p054xj.p056ui.p057fw;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.InputEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.blankj.utilcode.util.LogUtils;
import com.lbd.p054xj.app.XJApp;
import com.lbd.p054xj.p056ui.p057fw.XJOutFloatView;
import p110z1.PreferencesUtil;
import p110z1.aeo;

/* renamed from: com.lbd.xj.ui.fw.XJLiveView_old */
/* loaded from: classes.dex */
public class XJLiveView_old extends SurfaceView implements SurfaceHolder.Callback {

    /* renamed from: b */
    public static int f9876b;

    /* renamed from: c */
    public static int f9877c;

    /* renamed from: d */
    public static int f9878d;

    /* renamed from: a */
    SurfaceHolder f9879a;

    /* renamed from: e */
    Canvas f9880e;

    /* renamed from: f */
    int f9881f;

    /* renamed from: i */
    int f9884i;

    /* renamed from: m */
    private XJOutFloatView.AbstractC1652a f9888m;

    /* renamed from: j */
    private boolean f9885j = true;

    /* renamed from: g */
    int f9882g = 1;

    /* renamed from: k */
    private float f9886k = 1.0f;

    /* renamed from: l */
    private float f9887l = 1.0f;

    /* renamed from: h */
    public boolean f9883h = false;

    public static native int setNativeWindow(int i);

    public static native int setSurface(Surface surface);

    public native void setInputTouchEvent(int i, int i2, InputEvent inputEvent);

    public XJLiveView_old(Context context, int i, int i2) {
        super(context);
        getHolder().addCallback(this);
    }

    public XJLiveView_old(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        getHolder().addCallback(this);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        LogUtils.m23734c("XJLiveView", "surfaceCreated");
        this.f9879a = surfaceHolder;
        setSurface(surfaceHolder.getSurface());
        surfaceHolder.setType(3);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        LogUtils.m23720e("surfaceChanged0, format is " + i + ", width is " + i2 + ", height is" + i3 + "holder" + surfaceHolder.getSurface());
        if ((getResources().getConfiguration().orientation == 2 ? 1 : null) == null) {
            int availableHeiht = getAvailableHeiht();
            int availableWidth = getAvailableWidth();
            LogUtils.m23720e("surfaceChanged", "1=>avaiHeight:" + availableHeiht + ",avaiWidth:" + availableWidth);
            surfaceHolder.setFixedSize(availableWidth, availableHeiht);
        } else {
            surfaceHolder.setFixedSize(getAvailableWidth(), getAvailableHeiht());
            StringBuilder sb = new StringBuilder();
            sb.append("2=>");
            sb.append("getAvailableWidth: " + getAvailableWidth() + "getAvailableHeiht: " + getAvailableHeiht());
            LogUtils.m23720e("surfaceChanged", sb.toString());
        }
        this.f9879a = surfaceHolder;
    }

    private int getAvailableHeiht() {
        int i = f9877c;
        if (i != 0) {
            return i;
        }
        f9877c = ((Integer) PreferencesUtil.m13937a().m13927b("surfaceH", Integer.valueOf(aeo.m13920a(XJApp.getInstance().getApplicationContext())))).intValue();
        com.common.utils.log.LogUtils.m22037e("getHeiht:" + f9877c);
        return f9877c;
    }

    private int getAvailableWidth() {
        int i = f9878d;
        if (i != 0) {
            return i;
        }
        f9878d = ((Integer) PreferencesUtil.m13937a().m13927b("surfaceW", Integer.valueOf(aeo.m13916b(XJApp.getInstance().getApplicationContext())))).intValue();
        com.common.utils.log.LogUtils.m22037e("getWidth:" + f9878d);
        return f9878d;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        LogUtils.m23734c("XJLiveView", "surfaceDestroyed");
    }

    /* renamed from: a */
    private boolean m19347a(MotionEvent motionEvent, int i, Configuration configuration) {
        if (i == 2) {
            LogUtils.m23720e("onTouchEvent", "onTouchEvent: 1");
            this.f9886k = (getAvailableHeiht() * 1.0f) / getHeight();
            this.f9887l = (getAvailableWidth() * 1.0f) / getWidth();
            LogUtils.m23720e("横屏", "onChildTouchEvent: scale_x " + this.f9886k + "  scale_y  " + this.f9887l + "getHeight" + getHeight());
        } else if (i == 1) {
            this.f9886k = (getAvailableWidth() * 1.0f) / getWidth();
            this.f9887l = (getAvailableHeiht() * 1.0f) / getHeight();
            LogUtils.m23720e("onTouchEvent", "onTouchEvent: 2");
            LogUtils.m23720e("竖屏", "onChildTouchEvent: scale_x " + this.f9886k + "  scale_y  " + this.f9887l + "getHeight" + getHeight());
        }
        if (((Boolean) PreferencesUtil.m13937a().m13927b("isRotation", true)).booleanValue()) {
            LogUtils.m23720e("onTouchEvent", "onTouchEvent: 3");
            MotionEvent.PointerCoords[] pointerCoordsArr = new MotionEvent.PointerCoords[motionEvent.getPointerCount()];
            MotionEvent.PointerProperties[] pointerPropertiesArr = new MotionEvent.PointerProperties[motionEvent.getPointerCount()];
            for (int i2 = 0; i2 < motionEvent.getPointerCount(); i2++) {
                MotionEvent.PointerCoords pointerCoords = new MotionEvent.PointerCoords();
                MotionEvent.PointerProperties pointerProperties = new MotionEvent.PointerProperties();
                motionEvent.getPointerCoords(i2, pointerCoords);
                motionEvent.getPointerProperties(i2, pointerProperties);
                pointerCoords.x *= this.f9886k;
                pointerCoords.y *= this.f9887l;
                pointerCoordsArr[i2] = pointerCoords;
                pointerPropertiesArr[i2] = pointerProperties;
                StringBuilder sb = new StringBuilder();
                sb.append("");
                sb.append(pointerCoords.toString());
                sb.append("   x:" + pointerCoords.x);
                LogUtils.m23720e("getPointerCoords", sb.toString());
            }
            setInputTouchEvent((int) motionEvent.getX(), (int) motionEvent.getY(), MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), motionEvent.getAction(), motionEvent.getPointerCount(), pointerPropertiesArr, pointerCoordsArr, motionEvent.getMetaState(), motionEvent.getButtonState(), motionEvent.getXPrecision(), motionEvent.getYPrecision(), motionEvent.getDeviceId(), motionEvent.getEdgeFlags(), motionEvent.getSource(), motionEvent.getFlags()));
            return true;
        }
        if (i == 2) {
            this.f9886k = (getAvailableHeiht() * 1.0f) / getWidth();
            this.f9887l = (getAvailableWidth() * 1.0f) / getHeight();
            if (!((Boolean) PreferencesUtil.m13937a().m13927b("isRotation", true)).booleanValue()) {
                MotionEvent.PointerCoords[] pointerCoordsArr2 = new MotionEvent.PointerCoords[motionEvent.getPointerCount()];
                MotionEvent.PointerProperties[] pointerPropertiesArr2 = new MotionEvent.PointerProperties[motionEvent.getPointerCount()];
                for (int i3 = 0; i3 < motionEvent.getPointerCount(); i3++) {
                    MotionEvent.PointerCoords pointerCoords2 = new MotionEvent.PointerCoords();
                    MotionEvent.PointerProperties pointerProperties2 = new MotionEvent.PointerProperties();
                    motionEvent.getPointerCoords(i3, pointerCoords2);
                    motionEvent.getPointerProperties(i3, pointerProperties2);
                    float f = pointerCoords2.x;
                    pointerCoords2.x = getAvailableWidth() - (pointerCoords2.y * this.f9887l);
                    pointerCoords2.y = f * this.f9886k;
                    pointerCoordsArr2[i3] = pointerCoords2;
                    pointerPropertiesArr2[i3] = pointerProperties2;
                    LogUtils.m23720e("getPointerCoords", "" + pointerCoords2.toString() + pointerCoords2.x);
                }
                setInputTouchEvent((int) motionEvent.getX(), (int) motionEvent.getY(), MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), motionEvent.getAction(), motionEvent.getPointerCount(), pointerPropertiesArr2, pointerCoordsArr2, motionEvent.getMetaState(), motionEvent.getButtonState(), motionEvent.getXPrecision(), motionEvent.getYPrecision(), motionEvent.getDeviceId(), motionEvent.getEdgeFlags(), motionEvent.getSource(), motionEvent.getFlags()));
                return true;
            }
        } else if (i == 1) {
            this.f9886k = (getAvailableWidth() * 1.0f) / getHeight();
            this.f9887l = (getAvailableHeiht() * 1.0f) / getWidth();
            if (!((Boolean) PreferencesUtil.m13937a().m13927b("isRotation", true)).booleanValue()) {
                MotionEvent.PointerCoords[] pointerCoordsArr3 = new MotionEvent.PointerCoords[motionEvent.getPointerCount()];
                MotionEvent.PointerProperties[] pointerPropertiesArr3 = new MotionEvent.PointerProperties[motionEvent.getPointerCount()];
                for (int i4 = 0; i4 < motionEvent.getPointerCount(); i4++) {
                    MotionEvent.PointerCoords pointerCoords3 = new MotionEvent.PointerCoords();
                    MotionEvent.PointerProperties pointerProperties3 = new MotionEvent.PointerProperties();
                    motionEvent.getPointerCoords(i4, pointerCoords3);
                    motionEvent.getPointerProperties(i4, pointerProperties3);
                    float f2 = pointerCoords3.x;
                    pointerCoords3.x = getAvailableWidth() - (pointerCoords3.y * this.f9886k);
                    pointerCoords3.y = f2 * this.f9887l;
                    pointerCoordsArr3[i4] = pointerCoords3;
                    pointerPropertiesArr3[i4] = pointerProperties3;
                    LogUtils.m23720e("getPointerCoords", "" + pointerCoords3.toString() + pointerCoords3.x);
                }
                setInputTouchEvent((int) motionEvent.getX(), (int) motionEvent.getY(), MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), motionEvent.getAction(), motionEvent.getPointerCount(), pointerPropertiesArr3, pointerCoordsArr3, motionEvent.getMetaState(), motionEvent.getButtonState(), motionEvent.getXPrecision(), motionEvent.getYPrecision(), motionEvent.getDeviceId(), motionEvent.getEdgeFlags(), motionEvent.getSource(), motionEvent.getFlags()));
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
        LogUtils.m23720e("onTouchEvent", "this.getHeight(): " + getHeight() + "this.getWidth(): " + getWidth() + "   index  " + (actionIndex + 1) + "  getActionMasked  " + motionEvent.getActionMasked());
        if (!this.f9883h) {
            return super.onTouchEvent(motionEvent);
        }
        m19347a(motionEvent, i, configuration);
        XJOutFloatView.AbstractC1652a aVar = this.f9888m;
        if (aVar != null) {
            aVar.mo19302a();
        }
        return true;
    }

    /* renamed from: a */
    public void m19348a() {
        if (this.f9885j) {
            LogUtils.m23734c("XJLiveView", "setsurface");
            SurfaceHolder surfaceHolder = this.f9879a;
            if (surfaceHolder != null) {
                setSurface(surfaceHolder.getSurface());
                m19346b();
            }
        }
    }

    /* renamed from: b */
    public static void m19346b() {
        f9876b = 2;
        setNativeWindow(f9876b);
    }

    /* renamed from: c */
    public static void m19345c() {
        f9876b = 1;
        setNativeWindow(f9876b);
    }

    public void setTudingStatus(boolean z) {
        this.f9883h = z;
    }

    static {
        System.loadLibrary("native-activity");
    }

    public void setOnTouchCallBack(XJOutFloatView.AbstractC1652a aVar) {
        this.f9888m = aVar;
    }
}
