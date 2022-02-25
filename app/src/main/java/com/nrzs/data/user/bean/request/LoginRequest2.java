package com.nrzs.data.user.bean.request;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.nrzs.data.DataApp;
import com.nrzs.data.base.BaseRequest;
import com.nrzs.data.p065en.Abcd;
import com.nrzs.data.p065en.Ufc;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;
import p110z1.Config;
import p110z1.PackageUtil;
import p110z1.ShareVal;
import p110z1.StringUtil;
import p110z1.apf;

/* loaded from: classes2.dex */
public class LoginRequest2 extends BaseRequest {
    public String DeviceModel = getDeviceModel();
    public String DeviceCode = Config.m12527a();

    public static String getDeviceCode() {
        try {
            return URLEncoder.encode(getUUID(DataApp.m18939d().m18947a()), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String getUUID(Context context) throws IOException {
        try {
            long j = PackageUtil.m11850a(context, context.getPackageName()).firstInstallTime;
            String b = apf.m11837b(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16574E, "");
            String sign = getSign(b, 1, 2, context);
            if (!TextUtils.isEmpty(sign)) {
                if (j == Long.valueOf(sign.substring(sign.length() - 13, sign.length())).longValue()) {
                    return b;
                }
            }
            return createUUid(context);
        } catch (Exception e) {
            e.printStackTrace();
            return createUUid(context);
        }
    }

    private static String createUUid(Context context) throws IOException {
        try {
            long j = PackageUtil.m11850a(context, context.getPackageName()).firstInstallTime;
            String uuid = UUID.randomUUID().toString();
            String sign = getSign(uuid + j, 1, 1, context);
            apf.m11842a(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16574E, sign);
            return sign;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String getSign(String str, int i, int i2, Context context) throws Exception {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        Abcd abcd = new Abcd();
        abcd.setSource(str);
        abcd.setIndex(i);
        abcd.setCryptType(i2);
        String y11 = new Ufc().y11(abcd, context);
        return TextUtils.isEmpty(y11) ? "" : y11;
    }

    public static String getDeviceModel() {
        if (Build.CPU_ABI.equals("x86")) {
            return "模拟器";
        }
        String str = Build.MODEL;
        return !StringUtil.m11795c((CharSequence) str) ? str.replace("unknown", ExpandableTextView.f6958c).replace("universal", ExpandableTextView.f6958c) : "未知";
    }
}
