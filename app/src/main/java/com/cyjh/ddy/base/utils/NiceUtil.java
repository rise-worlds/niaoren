package com.cyjh.ddy.base.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.p006v7.app.ActionBar;
import android.support.p006v7.app.AppCompatActivity;
import android.support.p006v7.view.ContextThemeWrapper;
import android.util.TypedValue;
import java.util.Formatter;
import java.util.Locale;
import org.apache.tools.ant.taskdefs.WaitFor;

/* renamed from: com.cyjh.ddy.base.utils.n */
/* loaded from: classes.dex */
public class NiceUtil {
    /* renamed from: a */
    public static Activity m21778a(Context context) {
        if (context == null) {
            return null;
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return m21778a(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    /* renamed from: f */
    private static AppCompatActivity m21770f(Context context) {
        if (context == null) {
            return null;
        }
        if (context instanceof AppCompatActivity) {
            return (AppCompatActivity) context;
        }
        if (context instanceof ContextThemeWrapper) {
            return m21770f(((ContextThemeWrapper) context).getBaseContext());
        }
        return null;
    }

    /* renamed from: b */
    public static void m21774b(Context context) {
        ActionBar supportActionBar = m21770f(context).getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setShowHideAnimationEnabled(false);
            supportActionBar.show();
        }
        m21778a(context).getWindow().clearFlags(1024);
    }

    /* renamed from: c */
    public static void m21773c(Context context) {
        ActionBar supportActionBar = m21770f(context).getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setShowHideAnimationEnabled(false);
            supportActionBar.hide();
        }
        m21778a(context).getWindow().setFlags(1024, 1024);
    }

    /* renamed from: d */
    public static int m21772d(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /* renamed from: e */
    public static int m21771e(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /* renamed from: a */
    public static int m21777a(Context context, float f) {
        return (int) TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }

    /* renamed from: a */
    public static String m21779a(long j) {
        if (j <= 0 || j >= WaitFor.ONE_DAY) {
            return "00:00";
        }
        long j2 = j / 1000;
        long j3 = j2 % 60;
        long j4 = (j2 / 60) % 60;
        long j5 = j2 / 3600;
        Formatter formatter = new Formatter(new StringBuilder(), Locale.getDefault());
        return j5 > 0 ? formatter.format("%d:%02d:%02d", Long.valueOf(j5), Long.valueOf(j4), Long.valueOf(j3)).toString() : formatter.format("%02d:%02d", Long.valueOf(j4), Long.valueOf(j3)).toString();
    }

    /* renamed from: a */
    public static void m21775a(Context context, String str, long j) {
        context.getSharedPreferences("NICE_VIDEO_PALYER_PLAY_POSITION", 0).edit().putLong(str, j).apply();
    }

    /* renamed from: a */
    public static long m21776a(Context context, String str) {
        return context.getSharedPreferences("NICE_VIDEO_PALYER_PLAY_POSITION", 0).getLong(str, 0L);
    }
}
