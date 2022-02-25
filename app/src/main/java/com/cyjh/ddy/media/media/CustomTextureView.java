package com.cyjh.ddy.media.media;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.ViewGroup;
import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddy.base.utils.SdkUtils;
import com.cyjh.ddy.media.bean.TouchPoint;
import com.cyjh.ddy.media.media.listener.OnKeyEventListener;
import com.cyjh.ddy.media.media.listener.OnTouchEventListener;
import com.cyjh.ddy.media.oksocket.SocketHelper;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class CustomTextureView extends TextureView {
    public static final int ROTATE_TYPE_HORIZONTAL = 2;
    public static final int ROTATE_TYPE_VERTICAL = 1;
    private boolean bInit;
    private boolean isSwitchControllerKey;
    ViewGroup.LayoutParams layoutParams;
    private OnKeyEventListener mOnKeyEventListener;
    private OnTouchEventListener mOnTouchEventListener;
    private long moveTime;
    private int rotateType;
    private int sHeight;
    private int sWidth;
    private int sendCount;

    private boolean isBackKeyDown(int i) {
        return i == 4;
    }

    private boolean isDpadKey(int i) {
        return i == 20 || i == 19 || i == 21 || i == 22 || i == 23;
    }

    private boolean isMenuKey(int i) {
        return i == 82;
    }

    private boolean isVolumeKey(int i) {
        return i == 24 || i == 25;
    }

    public CustomTextureView(Context context) {
        this(context, null, 0);
    }

    public CustomTextureView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CustomTextureView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.sendCount = 1;
        this.isSwitchControllerKey = false;
        init();
    }

    public void init() {
        post(new Runnable() { // from class: com.cyjh.ddy.media.media.CustomTextureView.1
            @Override // java.lang.Runnable
            public void run() {
                CustomTextureView.this.initSurfaceDimensions();
            }
        });
    }

    @Override // android.view.TextureView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    public void initSurfaceDimensions() {
        setFocusable(true);
        setKeepScreenOn(true);
        onParentSizeChanged();
        this.bInit = true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        handleTouchEvent(motionEvent);
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.isSwitchControllerKey && this.mOnKeyEventListener != null && (isDpadKey(i) || isBackKeyDown(i) || isMenuKey(i) || isVolumeKey(i))) {
            this.mOnKeyEventListener.mo21649a(i);
        }
        return super.onKeyDown(i, keyEvent);
    }

    private void setScreenXY() {
        if (this.rotateType == 2) {
            setX(0.0f);
            setY(0.0f);
            setRotation(0.0f);
            return;
        }
        setX((this.sHeight - this.sWidth) / 2.0f);
        setY((this.sWidth - this.sHeight) / 2.0f);
        setRotation(90.0f);
    }

    public void setScreenRotate(int i) {
        CLog.m21882i("jason", String.format("setScreenXY bInit : %b, rotateType: %d, rotate:%d", Boolean.valueOf(this.bInit), Integer.valueOf(this.rotateType), Integer.valueOf(i)));
        if (this.bInit && i != this.rotateType) {
            this.rotateType = i;
            setScreenXY();
        }
        CLog.m21882i("LiveTextureView", "SCREEN_SIZE_S:\t" + this.sWidth + "," + this.sHeight);
    }

    public void onParentSizeChanged() {
        if (getParent() != null) {
            int width = ((ViewGroup) getParent()).getWidth();
            int height = ((ViewGroup) getParent()).getHeight();
            CLog.m21882i("LiveTextureView", String.format("screenWidth : %d, screenHeight:%d", Integer.valueOf(width), Integer.valueOf(height)));
            if (width < height) {
                this.sWidth = height;
                this.sHeight = width;
                this.rotateType = 1;
            } else {
                this.sWidth = width;
                this.sHeight = height;
                this.rotateType = 2;
            }
            CLog.m21882i("LiveTextureView", String.format("sWidth : %d, sHeight:%d, rotate:%d", Integer.valueOf(this.sWidth), Integer.valueOf(this.sHeight), Integer.valueOf(this.rotateType)));
            this.layoutParams = getLayoutParams();
            ViewGroup.LayoutParams layoutParams = this.layoutParams;
            layoutParams.width = this.sWidth;
            layoutParams.height = this.sHeight;
            setLayoutParams(layoutParams);
            setScreenXY();
        }
    }

    private void handleTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getActionMasked()) {
            case 0:
                this.sendCount = 1;
                handleActionDown(motionEvent);
                return;
            case 1:
                this.sendCount = 1;
                handleActionDown(motionEvent);
                return;
            case 2:
                this.sendCount++;
                if (SdkUtils.m21761a()) {
                    handleActionDown(motionEvent);
                    return;
                } else if (System.currentTimeMillis() - this.moveTime >= 10) {
                    this.moveTime = System.currentTimeMillis();
                    this.sendCount = 0;
                    handleActionDown(motionEvent);
                    return;
                } else {
                    return;
                }
            case 3:
            case 4:
            default:
                return;
            case 5:
                this.sendCount = 1;
                handleActionDown(motionEvent);
                return;
            case 6:
                this.sendCount = 1;
                handleActionDown(motionEvent);
                return;
        }
    }

    private void handleActionDown(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < motionEvent.getPointerCount(); i++) {
            int pointerId = motionEvent.getPointerId(i);
            if (!(actionMasked == 5 || actionMasked == 6) || pointerId == motionEvent.getActionIndex()) {
                arrayList.add(new TouchPoint(pointerId, motionEvent.getX(i) / this.sWidth, motionEvent.getY(i) / this.sHeight));
            }
        }
        String a = SocketHelper.m21525a(arrayList.size(), actionMasked, motionEvent.getActionIndex(), arrayList);
        OnTouchEventListener kVar = this.mOnTouchEventListener;
        if (kVar != null) {
            kVar.mo21647b(a);
        }
    }

    public void switchControllerKeyEvent(boolean z) {
        this.isSwitchControllerKey = z;
    }

    public void setOnTouchEventListener(OnTouchEventListener kVar) {
        this.mOnTouchEventListener = kVar;
    }

    public void setOnKeyEventListener(OnKeyEventListener iVar) {
        this.mOnKeyEventListener = iVar;
    }
}
