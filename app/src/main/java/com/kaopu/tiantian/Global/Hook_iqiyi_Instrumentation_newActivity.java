package com.kaopu.tiantian.Global;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import com.lody.virtual.helper.utils.Reflect;
import com.lody.virtual.remote.StubActivityRecord;

/* loaded from: classes.dex */
public class Hook_iqiyi_Instrumentation_newActivity {
    public static String className = "com.iqiyi.iflex.a.c";
    public static String methodName = "newActivity";
    public static String methodSig = "(Ljava/lang/ClassLoader;Ljava/lang/String;Landroid/content/Intent;)Landroid/app/Activity;";

    public static Activity hook(Object obj, ClassLoader classLoader, String str, Intent intent) {
        StubActivityRecord stubActivityRecord = new StubActivityRecord(intent);
        if (stubActivityRecord.intent != null) {
            return backup2(obj, classLoader, stubActivityRecord.info.name, stubActivityRecord.intent);
        }
        return backup2(obj, classLoader, str, intent);
    }

    public static Activity backup2(Object obj, ClassLoader classLoader, String str, Intent intent) {
        Activity activity;
        Reflect on = Reflect.m18996on("com.iqiyi.iflex.iFlex", obj.getClass().getClassLoader());
        new String[1][0] = str;
        Object obj2 = Reflect.m18998on(obj).call("a", str).get();
        Object obj3 = Reflect.m18998on(obj).get("f");
        if (((Boolean) on.call("isMultiClassLoader").get()).booleanValue()) {
            try {
                classLoader.loadClass(str);
                activity = (Activity) Reflect.m18998on(obj3).call("newActivity", classLoader, str, intent).get();
            } catch (Exception unused) {
                activity = (Activity) Reflect.m18998on(obj3).call("newActivity", Reflect.m18998on(obj2).call("f").get(), str, intent).get();
            }
        } else {
            try {
                activity = (Activity) Reflect.m18998on(obj3).call("newActivity", classLoader, str, intent).get();
            } catch (Exception unused2) {
                activity = (Activity) Reflect.m18998on(obj3).call("newActivity", Reflect.m18998on(obj2).call("f").get(), str, intent).get();
            }
        }
        Object obj4 = on.call("getResources").get();
        if (obj4 != null) {
            try {
                Reflect.m18998on(activity).set("mResources", obj4);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return activity;
    }

    public static Activity backup(Object obj, ClassLoader classLoader, String str, Intent intent) {
        try {
            Log.w("TianTian", "load should not be here");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
