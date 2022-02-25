package com.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.tencent.mmkv.MMKV;

/* loaded from: classes.dex */
public class SharedPreferencesUtil {
    private static SharedPreferencesUtil instance;
    private SharedPreferences.Editor editor;
    private MMKV sharedPreferences;

    public synchronized Boolean getBoolean(String str) {
        return Boolean.valueOf(this.sharedPreferences.getBoolean(str, false));
    }

    public static SharedPreferencesUtil getInstance() {
        if (instance == null) {
            synchronized (SharedPreferencesUtil.class) {
                if (instance == null) {
                    instance = new SharedPreferencesUtil();
                }
            }
        }
        return instance;
    }

    public int getInt(String str) {
        return this.sharedPreferences.getInt(str, 0);
    }

    public Long getLong(String str) {
        return Long.valueOf(this.sharedPreferences.getLong(str, 0L));
    }

    public String getString(String str) {
        return this.sharedPreferences.getString(str, "");
    }

    public void init(Context context) {
        if (this.sharedPreferences == null) {
            this.sharedPreferences = MMKV.mmkvWithID("vos_preferences", 2);
            SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("vos_preferences", 0);
            this.sharedPreferences.importFromSharedPreferences(sharedPreferences);
            sharedPreferences.edit().clear().commit();
        }
    }

    public synchronized void putBooleanValue(String str, boolean z) {
        this.editor = this.sharedPreferences.edit();
        this.editor.putBoolean(str, z);
    }

    public void putIntValue(String str, int i) {
        this.editor = this.sharedPreferences.edit();
        this.editor.putInt(str, i);
    }

    public void putLongValue(String str, Long l) {
        this.editor = this.sharedPreferences.edit();
        this.editor.putLong(str, l.longValue());
    }

    public void putStringValue(String str, String str2) {
        this.editor = this.sharedPreferences.edit();
        this.editor.putString(str, str2);
    }
}
