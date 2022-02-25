package com.lody.virtual.client.fixer;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import p110z1.R_Hide;

/* loaded from: classes.dex */
public final class ActivityFixer {
    private ActivityFixer() {
    }

    public static void fixActivity(Activity activity) {
        Context baseContext = activity.getBaseContext();
        try {
            TypedArray obtainStyledAttributes = activity.obtainStyledAttributes(R_Hide.C5192d.Window.get());
            if (obtainStyledAttributes != null) {
                if (obtainStyledAttributes.getBoolean(R_Hide.C5192d.Window_windowShowWallpaper.get(), false)) {
                    activity.getWindow().setBackgroundDrawable(WallpaperManager.getInstance(activity).getDrawable());
                }
                if (obtainStyledAttributes.getBoolean(R_Hide.C5192d.Window_windowFullscreen.get(), false)) {
                    activity.getWindow().addFlags(1024);
                }
                obtainStyledAttributes.recycle();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 21) {
            Intent intent = activity.getIntent();
            ApplicationInfo applicationInfo = baseContext.getApplicationInfo();
            PackageManager packageManager = activity.getPackageManager();
            if (intent != null && activity.isTaskRoot()) {
                try {
                    String str = ((Object) applicationInfo.loadLabel(packageManager)) + "";
                    Bitmap bitmap = null;
                    Drawable loadIcon = applicationInfo.loadIcon(packageManager);
                    if (loadIcon instanceof BitmapDrawable) {
                        bitmap = ((BitmapDrawable) loadIcon).getBitmap();
                    }
                    activity.setTaskDescription(new ActivityManager.TaskDescription(str, bitmap));
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
            }
        }
    }
}
