package com.lbd.p054xj.keeplive;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import com.lbd.p054xj.C1467R;

/* renamed from: com.lbd.xj.keeplive.LiveActivity */
/* loaded from: classes.dex */
public class LiveActivity extends Activity {

    /* renamed from: a */
    public static final String f9458a = "LiveActivity";

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    /* renamed from: a */
    public static void m19777a(Context context) {
        Intent intent = new Intent(context, LiveActivity.class);
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1467R.layout.activity_live);
        Window window = getWindow();
        window.setGravity(8388659);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = 500;
        attributes.height = 500;
        attributes.x = 500;
        attributes.y = 500;
        window.setAttributes(attributes);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            new Handler().postDelayed(new Runnable() { // from class: com.lbd.xj.keeplive.LiveActivity.1
                @Override // java.lang.Runnable
                public void run() {
                    LiveActivity.this.finish();
                }
            }, 200L);
        }
    }
}
