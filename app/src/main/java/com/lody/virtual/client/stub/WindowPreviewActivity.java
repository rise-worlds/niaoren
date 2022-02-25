package com.lody.virtual.client.stub;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.WindowManager;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.helper.utils.VLog;
import com.lody.virtual.server.p062am.AttributeCache;
import p110z1.LayerDrawable;
import p110z1.R_Hide;

/* loaded from: classes.dex */
public class WindowPreviewActivity extends Activity {
    private long startTime;

    public static void previewActivity(int i, ActivityInfo activityInfo) {
        Context context = VirtualCore.get().getContext();
        Intent intent = new Intent(context, WindowPreviewActivity.class);
        intent.putExtra("_VA_|user_id", i);
        intent.putExtra("_VA_|activity_info", activityInfo);
        intent.addFlags(268435456);
        intent.addFlags(65536);
        context.startActivity(intent);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        AttributeCache.Entry entry;
        this.startTime = System.currentTimeMillis();
        overridePendingTransition(0, 0);
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        ActivityInfo activityInfo = (ActivityInfo) intent.getParcelableExtra("_VA_|activity_info");
        int intExtra = intent.getIntExtra("_VA_|user_id", -1);
        if (activityInfo == null || intExtra == -1) {
            finish();
            return;
        }
        int i = activityInfo.theme;
        if (i == 0) {
            i = activityInfo.applicationInfo.theme;
        }
        AttributeCache.Entry entry2 = AttributeCache.instance().get(activityInfo.packageName, i, R_Hide.C5192d.Window.get());
        if (entry2 != null) {
            boolean z = entry2.array.getBoolean(R_Hide.C5192d.Window_windowFullscreen.get(), false);
            boolean z2 = entry2.array.getBoolean(R_Hide.C5192d.Window_windowIsTranslucent.get(), false);
            if (!entry2.array.getBoolean(R_Hide.C5192d.Window_windowDisablePreview.get(), false)) {
                if (z) {
                    getWindow().addFlags(1024);
                }
                Drawable drawable = null;
                try {
                    drawable = entry2.array.getDrawable(R_Hide.C5192d.Window_windowBackground.get());
                } catch (Throwable unused) {
                }
                if (drawable == null && (entry = AttributeCache.instance().get(activityInfo.packageName, activityInfo.theme, R_Hide.C5192d.View.get())) != null) {
                    try {
                        drawable = entry.array.getDrawable(R_Hide.C5192d.View_background.get());
                    } catch (Throwable unused2) {
                    }
                }
                if (drawable == null || isDrawableBroken(drawable)) {
                    if (!z2) {
                        getWindow().setBackgroundDrawable(new ColorDrawable(-1));
                    }
                    WindowManager.LayoutParams attributes = getWindow().getAttributes();
                    attributes.dimAmount = 0.4f;
                    getWindow().setAttributes(attributes);
                    getWindow().addFlags(2);
                    return;
                }
                getWindow().setBackgroundDrawable(drawable);
            }
        }
    }

    private boolean isDrawableBroken(Drawable drawable) {
        if (!LayerDrawable.TYPE.isInstance(drawable) || LayerDrawable.isProjected == null) {
            return false;
        }
        try {
            LayerDrawable.isProjected.callWithException(drawable, new Object[0]);
            return false;
        } catch (Throwable th) {
            VLog.m18991e("WindowPreviewActivity", "Bad preview background!", th);
            return true;
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (System.currentTimeMillis() - this.startTime > 5000) {
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        finish();
    }
}
