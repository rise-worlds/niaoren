package com.nrzs.game.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.Bundle;
import com.blankj.utilcode.util.LogUtils;
import com.cyjh.mobileanjian.screencap.ScreenShoterV3;

/* renamed from: com.nrzs.game.ui.activity.RealScreenShotActivity */
/* loaded from: classes2.dex */
public class RealScreenShotActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getWindow().setDimAmount(0.0f);
        m18598a();
    }

    /* renamed from: a */
    public void m18598a() {
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                LogUtils.m23734c("CKUttt", "免root 模式 - a");
                startActivityForResult(((MediaProjectionManager) getSystemService("media_projection")).createScreenCaptureIntent(), 100);
            } catch (Exception e) {
                e.printStackTrace();
                finish();
            }
        } else {
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtils.m23734c("CKUttt", "免root 模式 1 - requestCode:" + i);
        if (i == 100) {
            LogUtils.m23734c("CKUttt", "免root 模式 2 - resultCode:" + i2);
            if (i2 != -1 || intent == null) {
                m18598a();
                return;
            }
            setResult(-1);
            ScreenShoterV3.getInstance().init(this, intent);
            finish();
        }
    }
}
