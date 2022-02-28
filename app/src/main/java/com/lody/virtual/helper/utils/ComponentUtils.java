package com.lody.virtual.helper.utils;

import android.content.ClipData;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.text.TextUtils;
import com.lody.virtual.GmsSupport;
import com.lody.virtual.client.NativeEngine;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.env.SpecialComponentList;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.lody.virtual.client.stub.ContentProviderProxy;
import com.lody.virtual.client.stub.ShadowPendingActivity;
import com.lody.virtual.client.stub.ShadowPendingReceiver;
import com.lody.virtual.client.stub.ShadowPendingService;
import com.lody.virtual.helper.compat.IntentCompat;
import com.lody.virtual.helper.compat.ObjectsCompat;
import com.lody.virtual.os.VUserHandle;
import com.lody.virtual.remote.BroadcastIntentData;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
public class ComponentUtils {
    public static String getTaskAffinity(ActivityInfo activityInfo) {
        if (activityInfo.launchMode == 3) {
            return "-SingleInstance-" + activityInfo.packageName + "/" + activityInfo.name;
        } else if (activityInfo.taskAffinity == null && activityInfo.applicationInfo.taskAffinity == null) {
            return activityInfo.packageName;
        } else {
            if (activityInfo.taskAffinity != null) {
                return activityInfo.taskAffinity;
            }
            return activityInfo.applicationInfo.taskAffinity;
        }
    }

    public static boolean intentFilterEquals(Intent intent, Intent intent2) {
        if (intent == null || intent2 == null) {
            return true;
        }
        if (!ObjectsCompat.equals(intent.getAction(), intent2.getAction()) || !ObjectsCompat.equals(intent.getData(), intent2.getData()) || !ObjectsCompat.equals(intent.getType(), intent2.getType())) {
            return false;
        }
        String str = intent.getPackage();
        if (str == null && intent.getComponent() != null) {
            str = intent.getComponent().getPackageName();
        }
        String str2 = intent2.getPackage();
        if (str2 == null && intent2.getComponent() != null) {
            str2 = intent2.getComponent().getPackageName();
        }
        return ObjectsCompat.equals(str, str2) && ObjectsCompat.equals(intent.getComponent(), intent2.getComponent()) && ObjectsCompat.equals(intent.getCategories(), intent2.getCategories());
    }

    public static String getProcessName(ComponentInfo componentInfo) {
        String str = componentInfo.processName;
        if (str != null) {
            return str;
        }
        String str2 = componentInfo.packageName;
        componentInfo.processName = str2;
        return str2;
    }

    public static boolean isSameComponent(ComponentInfo componentInfo, ComponentInfo componentInfo2) {
        if (componentInfo == null || componentInfo2 == null) {
            return false;
        }
        String str = componentInfo.packageName + "";
        String str2 = componentInfo2.packageName + "";
        String str3 = componentInfo.name + "";
        StringBuilder sb = new StringBuilder();
        sb.append(componentInfo2.name);
        sb.append("");
        return str.equals(str2) && str3.equals(sb.toString());
    }

    public static ComponentName toComponentName(ComponentInfo componentInfo) {
        return new ComponentName(componentInfo.packageName, componentInfo.name);
    }

    public static boolean isSystemApp(ApplicationInfo applicationInfo) {
        if (applicationInfo == null || GmsSupport.isGoogleAppOrService(applicationInfo.packageName)) {
            return false;
        }
        if (SpecialComponentList.isSpecSystemPackage(applicationInfo.packageName)) {
            return true;
        }
        if (applicationInfo.uid < 10000 || (applicationInfo.flags & 128) == 0) {
            return SpecialComponentList.isSpecSystemPackage(applicationInfo.packageName) || applicationInfo.uid < 10000 || (applicationInfo.flags & 1) != 0;
        }
        return false;
    }

    public static String getComponentAction(ActivityInfo activityInfo) {
        return getComponentAction(activityInfo.packageName, activityInfo.name);
    }

    public static String getComponentAction(ComponentName componentName) {
        return getComponentAction(componentName.getPackageName(), componentName.getClassName());
    }

    public static String getComponentAction(String str, String str2) {
        return String.format("_VA_%s_%s_%s", VirtualCore.get().getHostPkg(), str, str2);
    }

    public static Intent redirectBroadcastIntent(Intent intent, int i) {
        Intent intent2 = new Intent();
        intent2.setDataAndType(intent.getData(), intent.getType());
        Set<String> categories = intent.getCategories();
        if (categories != null) {
            for (String str : categories) {
                intent2.addCategory(str);
            }
        }
        ComponentName component = intent.getComponent();
        String str2 = intent.getPackage();
        if (component != null) {
            intent2.setAction(getComponentAction(component));
            if (str2 == null) {
                str2 = component.getPackageName();
            }
        } else {
            intent2.setAction(SpecialComponentList.protectAction(intent.getAction()));
        }
        intent2.putExtra("_VA_|_data_", new BroadcastIntentData(i, intent, str2));
        return intent2;
    }

    public static Intent redirectIntentSender(int i, String str, Intent intent) {
        if (i == 3) {
            return null;
        }
        Intent intent2 = new Intent();
        intent2.setSourceBounds(intent.getSourceBounds());
        if (Build.VERSION.SDK_INT >= 16) {
            intent2.setClipData(intent.getClipData());
        }
        intent2.addFlags(intent.getFlags() & IntentCompat.IMMUTABLE_FLAGS);
        String type = intent2.getType();
        ComponentName component = intent2.getComponent();
        if (type != null) {
            str = type + ":" + str;
        }
        if (component != null) {
            str = str + ":" + component.flattenToString();
        }
        intent2.setDataAndType(intent2.getData(), str);
        String hostPackageName = VirtualCore.getConfig().getHostPackageName();
        if (i != 4) {
            switch (i) {
                case 1:
                    intent2.setClassName(hostPackageName, ShadowPendingReceiver.class.getName());
                    break;
                case 2:
                    intent2.addFlags(268435456);
                    intent2.setClassName(hostPackageName, ShadowPendingActivity.class.getName());
                    break;
                default:
                    return null;
            }
        } else {
            intent2.setClassName(hostPackageName, ShadowPendingService.class.getName());
        }
        Intent intent3 = new Intent();
        intent3.putExtra("_VA_|_intent_", intent);
        intent3.putExtra("_VA_|_userId_", VUserHandle.myUserId());
        intent2.setSelector(intent3);
        return intent2;
    }

    public static Intent processOutsideIntent(int i, boolean z, Intent intent) {
        ClipData.Item itemAt;
        Uri uri;
        Uri processOutsideUri;
        Uri data = intent.getData();
        if (data != null) {
            intent.setDataAndType(processOutsideUri(i, z, data), intent.getType());
        }
        if (Build.VERSION.SDK_INT >= 16 && intent.getClipData() != null) {
            ClipData clipData = intent.getClipData();
            if (!(clipData.getItemCount() < 0 || (uri = (itemAt = clipData.getItemAt(0)).getUri()) == null || (processOutsideUri = processOutsideUri(i, z, uri)) == uri)) {
                ClipData clipData2 = new ClipData(clipData.getDescription(), new ClipData.Item(itemAt.getText(), itemAt.getHtmlText(), itemAt.getIntent(), processOutsideUri));
                for (int i2 = 1; i2 < clipData.getItemCount(); i2++) {
                    ClipData.Item itemAt2 = clipData.getItemAt(i2);
                    Uri uri2 = itemAt2.getUri();
                    if (uri2 != null) {
                        uri2 = processOutsideUri(i, z, uri2);
                    }
                    clipData2.addItem(new ClipData.Item(itemAt2.getText(), itemAt2.getHtmlText(), itemAt2.getIntent(), uri2));
                }
                intent.setClipData(clipData2);
            }
        }
        if (intent.hasExtra("output")) {
            Parcelable parcelableExtra = intent.getParcelableExtra("output");
            if (parcelableExtra instanceof Uri) {
                intent.putExtra("output", processOutsideUri(i, z, (Uri) parcelableExtra));
            } else if (parcelableExtra instanceof ArrayList) {
                ArrayList arrayList = new ArrayList();
                Iterator it = ((ArrayList) parcelableExtra).iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    if (!(next instanceof Uri)) {
                        break;
                    }
                    arrayList.add(processOutsideUri(i, z, (Uri) next));
                }
                if (!arrayList.isEmpty()) {
                    intent.putExtra("output", arrayList);
                }
            }
        }
        if (intent.hasExtra("android.intent.extra.STREAM")) {
            Parcelable parcelableExtra2 = intent.getParcelableExtra("android.intent.extra.STREAM");
            if (parcelableExtra2 instanceof Uri) {
                intent.putExtra("android.intent.extra.STREAM", processOutsideUri(i, z, (Uri) parcelableExtra2));
            } else if (parcelableExtra2 instanceof ArrayList) {
                ArrayList arrayList2 = new ArrayList();
                Iterator it2 = ((ArrayList) parcelableExtra2).iterator();
                while (it2.hasNext()) {
                    Object next2 = it2.next();
                    if (!(next2 instanceof Uri)) {
                        break;
                    }
                    arrayList2.add(processOutsideUri(i, z, (Uri) next2));
                }
                if (!arrayList2.isEmpty()) {
                    intent.putExtra("android.intent.extra.STREAM", arrayList2);
                }
            }
        }
        return intent;
    }

    private static Uri processOutsideUri(int i, boolean z, Uri uri) {
        String authority;
        if (TextUtils.equals(uri.getScheme(), "file")) {
            return Uri.fromFile(new File(NativeEngine.resverseRedirectedPath(uri.getPath())));
        }
        return (!TextUtils.equals(uri.getScheme(), ServiceManagerNative.CONTENT) || (authority = uri.getAuthority()) == null || VirtualCore.get().getUnHookPackageManager().resolveContentProvider(authority, 0) == null) ? uri : ContentProviderProxy.buildProxyUri(i, z, authority, uri);
    }
}
