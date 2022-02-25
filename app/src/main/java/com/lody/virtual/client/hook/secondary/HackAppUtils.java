package com.lody.virtual.client.hook.secondary;

import com.lody.virtual.helper.utils.Reflect;
import com.lody.virtual.helper.utils.ReflectException;
import com.tencent.smtt.sdk.TbsConfig;

/* loaded from: classes.dex */
public class HackAppUtils {
    public static void enableQQLogOutput(String str, ClassLoader classLoader) {
        if (TbsConfig.APP_QQ.equals(str)) {
            try {
                Reflect.m18996on("com.tencent.qphone.base.util.QLog", classLoader).set("UIN_REPORTLOG_LEVEL", 100);
            } catch (ReflectException unused) {
            }
        }
    }
}
