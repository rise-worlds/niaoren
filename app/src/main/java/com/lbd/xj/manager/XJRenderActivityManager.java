package com.lbd.xj.manager;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import com.lbd.xj.app.AppConfig;
import com.lbd.xj.app.XJApp;
import com.lbd.xj.device.lcd.DisplayParam;
import com.lbd.xj.ui.dialog.LaunchFullDialog;
import p110z1.SharepreferenceUtil;
import p110z1.ValReceiver;
import p110z1.acf;

/* renamed from: com.lbd.xj.manager.c */
/* loaded from: classes.dex */
public enum XJRenderActivityManager {
    INSTANCE;
    
    private DisplayParam mDisplay;
    private LaunchFullDialog xjLoadingDialog;

    public boolean showAnimaDialog(Activity activity) {
        if (XJApp.getInstance().systemIsLive) {
            return false;
        }
        if (this.xjLoadingDialog == null) {
            this.xjLoadingDialog = new LaunchFullDialog(activity);
        }
        if (!this.xjLoadingDialog.isShowing()) {
            this.xjLoadingDialog.show();
        }
        XJApp.getInstance().systemIsLive = true;
        return true;
    }

    public LaunchFullDialog getXjLoadingDialog() {
        return this.xjLoadingDialog;
    }

    public void sendBroadcast(Activity activity, String str) {
        Intent intent = new Intent();
        intent.putExtra("key", str);
        intent.setAction(ValReceiver.f15490a);
        activity.sendBroadcast(intent);
    }

    public void initDisplayParam(Activity activity) {
        this.mDisplay = (DisplayParam) activity.getIntent().getParcelableExtra(acf.f15182g);
        if (this.mDisplay == null) {
            this.mDisplay = (DisplayParam) SharepreferenceUtil.m13892a(acf.f15182g, DisplayParam.class);
        }
        if (XJDataManager.INSTANCE.nstatic == 1) {
            XJDataManager.INSTANCE.nstatic = 0;
        }
        String str = AppConfig.f9434a;
        Log.d(str, "initDisplayParam:" + this.mDisplay.toString());
    }

    public void cancelMyFullDialog() {
        LaunchFullDialog cVar = this.xjLoadingDialog;
        if (cVar != null) {
            cVar.cancel();
        }
    }

    public DisplayParam getmDisplay() {
        return this.mDisplay;
    }

    public String getH() {
        DisplayParam displayParam = this.mDisplay;
        if (displayParam != null) {
            return displayParam.getDisplayHeight();
        }
        return null;
    }

    public String getW() {
        DisplayParam displayParam = this.mDisplay;
        if (displayParam != null) {
            return displayParam.getDisplayWidth();
        }
        return null;
    }

    public String getScaleH() {
        DisplayParam displayParam = this.mDisplay;
        if (displayParam != null) {
            return displayParam.getDisplayScaleH();
        }
        return null;
    }

    public String getScaleW() {
        DisplayParam displayParam = this.mDisplay;
        if (displayParam != null) {
            return displayParam.getDisplayScaleW();
        }
        return null;
    }
}
