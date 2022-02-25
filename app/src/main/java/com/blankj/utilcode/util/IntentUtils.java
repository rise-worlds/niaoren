package com.blankj.utilcode.util;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;
import android.support.p003v4.content.FileProvider;
import com.tencent.smtt.sdk.WebView;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* renamed from: com.blankj.utilcode.util.ab */
/* loaded from: classes.dex */
public final class IntentUtils {
    private IntentUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static boolean m23863a(Intent intent) {
        return Utils.m24103a().getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }

    /* renamed from: a */
    public static Intent m23857a(String str) {
        return m23858a(m23823h(str), false);
    }

    /* renamed from: a */
    public static Intent m23859a(File file) {
        return m23858a(file, false);
    }

    /* renamed from: a */
    public static Intent m23842a(String str, boolean z) {
        return m23858a(m23823h(str), z);
    }

    /* renamed from: a */
    public static Intent m23858a(File file, boolean z) {
        Uri uri;
        if (file == null) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        if (Build.VERSION.SDK_INT < 24) {
            uri = Uri.fromFile(file);
        } else {
            intent.setFlags(1);
            uri = FileProvider.getUriForFile(Utils.m24103a(), Utils.m24103a().getPackageName() + ".utilcode.provider", file);
        }
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        return m23862a(intent, z);
    }

    /* renamed from: b */
    public static Intent m23839b(String str) {
        return m23836b(str, false);
    }

    /* renamed from: b */
    public static Intent m23836b(String str, boolean z) {
        Intent intent = new Intent("android.intent.action.DELETE");
        intent.setData(Uri.parse("package:" + str));
        return m23862a(intent, z);
    }

    /* renamed from: c */
    public static Intent m23835c(String str) {
        return m23832c(str, false);
    }

    /* renamed from: c */
    public static Intent m23832c(String str, boolean z) {
        Intent launchIntentForPackage = Utils.m24103a().getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage == null) {
            return null;
        }
        return m23862a(launchIntentForPackage, z);
    }

    /* renamed from: d */
    public static Intent m23831d(String str) {
        return m23830d(str, false);
    }

    /* renamed from: d */
    public static Intent m23830d(String str, boolean z) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + str));
        return m23862a(intent, z);
    }

    /* renamed from: e */
    public static Intent m23829e(String str) {
        return m23828e(str, false);
    }

    /* renamed from: e */
    public static Intent m23828e(String str, boolean z) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", str);
        return m23862a(intent, z);
    }

    /* renamed from: a */
    public static Intent m23852a(String str, String str2) {
        return m23849a(str, str2, false);
    }

    /* renamed from: a */
    public static Intent m23849a(String str, String str2, boolean z) {
        if (str2 == null || str2.length() == 0) {
            return null;
        }
        return m23853a(str, new File(str2), z);
    }

    /* renamed from: a */
    public static Intent m23854a(String str, File file) {
        return m23853a(str, file, false);
    }

    /* renamed from: a */
    public static Intent m23853a(String str, File file, boolean z) {
        if (file == null || !file.isFile()) {
            return null;
        }
        return m23855a(str, m23840b(file), z);
    }

    /* renamed from: a */
    public static Intent m23856a(String str, Uri uri) {
        return m23855a(str, uri, false);
    }

    /* renamed from: a */
    public static Intent m23855a(String str, Uri uri, boolean z) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.TEXT", str);
        intent.putExtra("android.intent.extra.STREAM", uri);
        intent.setType("image/*");
        return m23862a(intent, z);
    }

    /* renamed from: a */
    public static Intent m23846a(String str, LinkedList<String> linkedList) {
        return m23845a(str, linkedList, false);
    }

    /* renamed from: a */
    public static Intent m23845a(String str, LinkedList<String> linkedList, boolean z) {
        if (linkedList == null || linkedList.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = linkedList.iterator();
        while (it.hasNext()) {
            arrayList.add(new File(it.next()));
        }
        return m23843a(str, (List<File>) arrayList, z);
    }

    /* renamed from: a */
    public static Intent m23844a(String str, List<File> list) {
        return m23843a(str, list, false);
    }

    /* renamed from: a */
    public static Intent m23843a(String str, List<File> list, boolean z) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (File file : list) {
            if (file.isFile()) {
                arrayList.add(m23840b(file));
            }
        }
        return m23847a(str, (ArrayList<Uri>) arrayList, z);
    }

    /* renamed from: a */
    public static Intent m23848a(String str, ArrayList<Uri> arrayList) {
        return m23847a(str, arrayList, false);
    }

    /* renamed from: a */
    public static Intent m23847a(String str, ArrayList<Uri> arrayList, boolean z) {
        Intent intent = new Intent("android.intent.action.SEND_MULTIPLE");
        intent.putExtra("android.intent.extra.TEXT", str);
        intent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList);
        intent.setType("image/*");
        return m23862a(intent, z);
    }

    /* renamed from: b */
    public static Intent m23838b(String str, String str2) {
        return m23850a(str, str2, null, false);
    }

    /* renamed from: b */
    public static Intent m23837b(String str, String str2, boolean z) {
        return m23850a(str, str2, null, z);
    }

    /* renamed from: a */
    public static Intent m23851a(String str, String str2, Bundle bundle) {
        return m23850a(str, str2, bundle, false);
    }

    /* renamed from: a */
    public static Intent m23850a(String str, String str2, Bundle bundle, boolean z) {
        Intent intent = new Intent("android.intent.action.VIEW");
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setComponent(new ComponentName(str, str2));
        return m23862a(intent, z);
    }

    /* renamed from: a */
    public static Intent m23864a() {
        return m23841a(false);
    }

    /* renamed from: a */
    public static Intent m23841a(boolean z) {
        Intent intent = new Intent("android.intent.action.ACTION_REQUEST_SHUTDOWN");
        intent.putExtra("android.intent.extra.KEY_CONFIRM", false);
        return m23862a(intent, z);
    }

    /* renamed from: f */
    public static Intent m23827f(String str) {
        return m23826f(str, false);
    }

    /* renamed from: f */
    public static Intent m23826f(String str, boolean z) {
        return m23862a(new Intent("android.intent.action.DIAL", Uri.parse(WebView.SCHEME_TEL + str)), z);
    }

    @RequiresPermission("android.permission.CALL_PHONE")
    /* renamed from: g */
    public static Intent m23825g(String str) {
        return m23824g(str, false);
    }

    @RequiresPermission("android.permission.CALL_PHONE")
    /* renamed from: g */
    public static Intent m23824g(String str, boolean z) {
        return m23862a(new Intent("android.intent.action.CALL", Uri.parse(WebView.SCHEME_TEL + str)), z);
    }

    /* renamed from: c */
    public static Intent m23834c(String str, String str2) {
        return m23833c(str, str2, false);
    }

    /* renamed from: c */
    public static Intent m23833c(String str, String str2, boolean z) {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + str));
        intent.putExtra("sms_body", str2);
        return m23862a(intent, z);
    }

    /* renamed from: a */
    public static Intent m23861a(Uri uri) {
        return m23860a(uri, false);
    }

    /* renamed from: a */
    public static Intent m23860a(Uri uri, boolean z) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("output", uri);
        intent.addFlags(1);
        return m23862a(intent, z);
    }

    /* renamed from: a */
    private static Intent m23862a(Intent intent, boolean z) {
        return z ? intent.addFlags(268435456) : intent;
    }

    /* renamed from: h */
    private static File m23823h(String str) {
        if (m23822i(str)) {
            return null;
        }
        return new File(str);
    }

    /* renamed from: i */
    private static boolean m23822i(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: b */
    private static Uri m23840b(File file) {
        if (file == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT < 24) {
            return Uri.fromFile(file);
        }
        return FileProvider.getUriForFile(Utils.m24103a(), Utils.m24103a().getPackageName() + ".utilcode.provider", file);
    }
}
