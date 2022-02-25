package com.cyjh.mobileanjian.screencap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

/* loaded from: classes.dex */
public class ForScreenShotActivity extends Activity {

    /* renamed from: a */
    public static final int f8760a = 32896;

    /* renamed from: b */
    private static final String f8761b = "ForScreenShotActivity";

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getWindow().setDimAmount(0.0f);
        m20583a();
    }

    /* renamed from: a */
    private void m20583a() {
        if (Build.VERSION.SDK_INT >= 21) {
            startActivityForResult(((MediaProjectionManager) getSystemService("media_projection")).createScreenCaptureIntent(), f8760a);
            return;
        }
        Log.e(f8761b, "The API version is too low,required is >= 21.");
        finish();
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 32896) {
            if (i2 != -1 || intent == null) {
                m20583a();
                setResult(0);
                return;
            }
            setResult(-1);
            ScreenShoterV3.getInstance().init(this, intent);
            finish();
        }
    }
}
