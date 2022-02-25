package com.cyjh.ddy.base.utils;

import android.os.Build;
import android.support.p003v4.app.ActivityCompat;
import android.text.TextUtils;
import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;

/* renamed from: com.cyjh.ddy.base.utils.d */
/* loaded from: classes.dex */
public class DeviceImeiUtil {
    /* renamed from: a */
    public static String m21867a() {
        if (Build.VERSION.SDK_INT >= 29) {
            return m21865b();
        }
        try {
            if (ActivityCompat.checkSelfPermission(Utils.m24103a(), "android.permission.READ_PHONE_STATE") != 0) {
                return m21865b();
            }
        } catch (Throwable unused) {
        }
        String str = "";
        try {
            str = PhoneUtils.m23538d();
        } catch (Throwable unused2) {
        }
        return TextUtils.isEmpty(str) ? m21865b() : str;
    }

    /* renamed from: b */
    public static String m21865b() {
        String b = SPUtils.m23341a().m23320b("UniqueID", "");
        if (TextUtils.isEmpty(b)) {
            String str = "86632902" + String.valueOf((int) (((Math.random() * 9.0d) + 1.0d) * 100000.0d));
            int a = 10 - (m21866a(str + ResultTypeConstant.f7213z) % 10);
            if (a == 10) {
                b = str + ResultTypeConstant.f7213z;
            } else {
                b = str + a;
            }
            SPUtils.m23341a().m23332a("UniqueID", b);
        }
        return b;
    }

    /* renamed from: a */
    public static int m21866a(String str) {
        int[] iArr = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            iArr[i] = Integer.valueOf(String.valueOf(str.charAt(i))).intValue();
        }
        for (int length = iArr.length - 2; length >= 0; length -= 2) {
            iArr[length] = iArr[length] << 1;
            iArr[length] = (iArr[length] / 10) + (iArr[length] % 10);
        }
        int i2 = 0;
        for (int i3 : iArr) {
            i2 += i3;
        }
        return i2;
    }
}
