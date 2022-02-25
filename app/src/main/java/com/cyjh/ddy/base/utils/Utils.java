package com.cyjh.ddy.base.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.Uri;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.StringUtils;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.stripe.android.view.ShippingInfoWidget;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* renamed from: com.cyjh.ddy.base.utils.u */
/* loaded from: classes.dex */
public class Utils {

    /* renamed from: a */
    public static final int f7148a = 0;

    /* renamed from: b */
    public static final int f7149b = 1;

    /* renamed from: c */
    public static final int f7150c = 2;

    /* renamed from: d */
    public static final int f7151d = 3;

    /* renamed from: e */
    public static final int f7152e = 4;

    /* renamed from: a */
    public static int m21731a(float f, Resources resources) {
        return (int) TypedValue.applyDimension(1, f, resources.getDisplayMetrics());
    }

    /* renamed from: a */
    public static float m21727a(String str) {
        String str2;
        if (str.indexOf("dp") != -1) {
            str2 = str.replace("dp", "");
        } else {
            str2 = str.replace("dip", "");
        }
        return Float.parseFloat(str2);
    }

    /* renamed from: a */
    public static int m21728a(View view) {
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        return rect.top;
    }

    /* renamed from: b */
    public static int m21720b(View view) {
        if (view.getId() == 16908290) {
            return view.getLeft();
        }
        return view.getLeft() + m21720b((View) view.getParent());
    }

    /* renamed from: a */
    public static boolean m21725a(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    /* renamed from: a */
    public static int m21730a(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return 0;
        }
        String typeName = activeNetworkInfo.getTypeName();
        if (typeName.equalsIgnoreCase("WIFI")) {
            return 4;
        }
        if (!typeName.equalsIgnoreCase("MOBILE")) {
            return 0;
        }
        if (TextUtils.isEmpty(Proxy.getDefaultHost())) {
            return m21717c(context) ? 3 : 2;
        }
        return 1;
    }

    /* renamed from: c */
    private static boolean m21717c(Context context) {
        switch (((TelephonyManager) context.getSystemService(ShippingInfoWidget.f12563f)).getNetworkType()) {
            case 0:
                return false;
            case 1:
                return false;
            case 2:
                return false;
            case 3:
                return true;
            case 4:
                return false;
            case 5:
                return true;
            case 6:
                return true;
            case 7:
                return false;
            case 8:
                return true;
            case 9:
                return true;
            case 10:
                return true;
            case 11:
                return false;
            case 12:
                return true;
            case 13:
                return true;
            case 14:
                return true;
            case 15:
                return true;
            default:
                return false;
        }
    }

    /* renamed from: b */
    public static List<String> m21721b(Context context) {
        ArrayList arrayList = new ArrayList();
        Cursor query = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "_data"}, null, null, null);
        if (!query.moveToFirst()) {
            return arrayList;
        }
        int columnIndex = query.getColumnIndex("_data");
        do {
            arrayList.add(query.getString(columnIndex));
        } while (query.moveToNext());
        return arrayList;
    }

    /* renamed from: a */
    public static int m21724a(List list) {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /* renamed from: a */
    public static void m21729a(Context context, String str) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts(ServiceManagerNative.PACKAGE, str, null));
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static boolean m21732a() {
        boolean c = m21718c();
        boolean d = m21715d();
        CLog.m21882i("Utils", "isSupportH265 isEncode=" + c + ", isDecode=" + d);
        return c && d && !SdkKeyUtil.getInstance().isHardCodeH264();
    }

    /* renamed from: c */
    private static boolean m21718c() {
        int codecCount = MediaCodecList.getCodecCount();
        for (int i = 0; i < codecCount; i++) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
            String lowerCase = codecInfoAt.getName().toLowerCase();
            if (codecInfoAt.isEncoder() && lowerCase.contains("hevc")) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: d */
    private static boolean m21715d() {
        int codecCount = MediaCodecList.getCodecCount();
        for (int i = 0; i < codecCount; i++) {
            String lowerCase = MediaCodecList.getCodecInfoAt(i).getName().toLowerCase();
            if (lowerCase.contains("decoder") && lowerCase.contains("hevc")) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    public static String m21722b() {
        return m21719b(com.blankj.utilcode.util.Utils.m24103a().getPackageName());
    }

    /* renamed from: b */
    public static String m21719b(String str) {
        return m21726a(str, "SHA1");
    }

    /* renamed from: c */
    public static Signature[] m21716c(String str) {
        if (StringUtils.m23226b(str)) {
            return null;
        }
        try {
            PackageInfo packageInfo = com.blankj.utilcode.util.Utils.m24103a().getPackageManager().getPackageInfo(str, 64);
            if (packageInfo == null) {
                return null;
            }
            return packageInfo.signatures;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private static String m21726a(String str, String str2) {
        Signature[] c;
        return (!StringUtils.m23226b(str) && (c = m21716c(str)) != null && c.length > 0) ? ConvertUtils.m22445c(m21723a(c[0].toByteArray(), str2)).replaceAll("(?<=[0-9A-F]{2})[0-9A-F]{2}", ":$0") : "";
    }

    /* renamed from: a */
    static byte[] m21723a(byte[] bArr, String str) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            instance.update(bArr);
            return instance.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
