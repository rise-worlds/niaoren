package com.lody.virtual.client.env;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import com.lody.virtual.GmsSupport;
import com.lody.virtual.client.VClient;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.helper.utils.Reflect;
import com.lody.virtual.helper.utils.VLog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import p110z1.IWebViewUpdateService;
import p110z1.WebViewFactory;

/* loaded from: classes.dex */
public final class SpecialComponentList {
    private static final String STATIC_PROTECT_ACTION_PREFIX = "_VA_protected_";
    private static final List<ComponentName> GMS_BLOCK_COMPONENT = Arrays.asList(new ComponentName(GmsSupport.GMS_PKG, "com.google.android.gms.update.SystemUpdateService"), new ComponentName(GmsSupport.GSF_PKG, "com.google.android.gsf.update.SystemUpdateService"));
    private static final List<String> GMS_BLOCK_ACTION_LIST = Arrays.asList("com.google.android.gms.update.START_SERVICE");
    private static final List<String> ACTION_BLACK_LIST = new ArrayList(2);
    private static final Map<String, String> PROTECTED_ACTION_MAP = new HashMap(5);
    private static final HashSet<String> WHITE_PERMISSION = new HashSet<>(3);
    private static final HashSet<String> BROADCAST_START_WHITE_LIST = new HashSet<>();
    private static final HashSet<String> INSTRUMENTATION_CONFLICTING = new HashSet<>(2);
    private static final HashSet<String> SPEC_SYSTEM_APP_LIST = new HashSet<>(3);
    private static final Set<String> SYSTEM_BROADCAST_ACTION = new HashSet(7);
    private static final Set<String> PRE_INSTALL_PACKAGES = new HashSet(7);
    private static String PROTECT_ACTION_PREFIX = null;

    static {
        SYSTEM_BROADCAST_ACTION.add("android.intent.action.SCREEN_ON");
        SYSTEM_BROADCAST_ACTION.add("android.intent.action.SCREEN_OFF");
        SYSTEM_BROADCAST_ACTION.add("android.intent.action.NEW_OUTGOING_CALL");
        SYSTEM_BROADCAST_ACTION.add("android.intent.action.TIME_TICK");
        SYSTEM_BROADCAST_ACTION.add("android.intent.action.TIME_SET");
        SYSTEM_BROADCAST_ACTION.add("android.intent.action.TIMEZONE_CHANGED");
        SYSTEM_BROADCAST_ACTION.add("android.intent.action.BATTERY_CHANGED");
        SYSTEM_BROADCAST_ACTION.add("android.intent.action.BATTERY_LOW");
        SYSTEM_BROADCAST_ACTION.add("android.intent.action.BATTERY_OKAY");
        SYSTEM_BROADCAST_ACTION.add("android.intent.action.ACTION_POWER_CONNECTED");
        SYSTEM_BROADCAST_ACTION.add("android.intent.action.ACTION_POWER_DISCONNECTED");
        SYSTEM_BROADCAST_ACTION.add("android.intent.action.USER_PRESENT");
        SYSTEM_BROADCAST_ACTION.add("android.provider.Telephony.SMS_RECEIVED");
        SYSTEM_BROADCAST_ACTION.add("android.provider.Telephony.SMS_DELIVER");
        SYSTEM_BROADCAST_ACTION.add("android.net.wifi.STATE_CHANGE");
        SYSTEM_BROADCAST_ACTION.add("android.net.wifi.SCAN_RESULTS");
        SYSTEM_BROADCAST_ACTION.add("android.net.wifi.WIFI_STATE_CHANGED");
        SYSTEM_BROADCAST_ACTION.add("android.net.conn.CONNECTIVITY_CHANGE");
        SYSTEM_BROADCAST_ACTION.add("android.intent.action.ANY_DATA_STATE");
        SYSTEM_BROADCAST_ACTION.add("android.intent.action.SIM_STATE_CHANGED");
        SYSTEM_BROADCAST_ACTION.add("android.location.PROVIDERS_CHANGED");
        SYSTEM_BROADCAST_ACTION.add("android.location.MODE_CHANGED");
        SYSTEM_BROADCAST_ACTION.add("android.intent.action.HEADSET_PLUG");
        SYSTEM_BROADCAST_ACTION.add("android.media.VOLUME_CHANGED_ACTION");
        SYSTEM_BROADCAST_ACTION.add("android.intent.action.CONFIGURATION_CHANGED");
        SYSTEM_BROADCAST_ACTION.add("android.intent.action.DYNAMIC_SENSOR_CHANGED");
        SYSTEM_BROADCAST_ACTION.add("dynamic_sensor_change");
        ACTION_BLACK_LIST.add("android.appwidget.action.APPWIDGET_UPDATE");
        ACTION_BLACK_LIST.add("android.appwidget.action.APPWIDGET_CONFIGURE");
        WHITE_PERMISSION.add("com.google.android.gms.settings.SECURITY_SETTINGS");
        WHITE_PERMISSION.add("com.google.android.apps.plus.PRIVACY_SETTINGS");
        WHITE_PERMISSION.add("android.permission.ACCOUNT_MANAGER");
        PROTECTED_ACTION_MAP.put("android.intent.action.PACKAGE_ADDED", Constants.ACTION_PACKAGE_ADDED);
        PROTECTED_ACTION_MAP.put("android.intent.action.PACKAGE_REMOVED", Constants.ACTION_PACKAGE_REMOVED);
        PROTECTED_ACTION_MAP.put("android.intent.action.PACKAGE_CHANGED", Constants.ACTION_PACKAGE_CHANGED);
        PROTECTED_ACTION_MAP.put("android.intent.action.USER_ADDED", Constants.ACTION_USER_ADDED);
        PROTECTED_ACTION_MAP.put("android.intent.action.USER_REMOVED", Constants.ACTION_USER_REMOVED);
        PROTECTED_ACTION_MAP.put("android.intent.action.MEDIA_SCANNER_SCAN_FILE", "android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        INSTRUMENTATION_CONFLICTING.add("com.qihoo.magic");
        INSTRUMENTATION_CONFLICTING.add("com.qihoo.magic_mutiple");
        INSTRUMENTATION_CONFLICTING.add("com.facebook.katana");
        SPEC_SYSTEM_APP_LIST.add("android");
        SPEC_SYSTEM_APP_LIST.add("com.google.android.webview");
        try {
            if (WebViewFactory.sWebViewSupported != null) {
                WebViewFactory.sWebViewSupported.set(true);
            }
            PackageInfo packageInfo = (PackageInfo) Reflect.m18998on(IWebViewUpdateService.waitForAndGetProvider.call(WebViewFactory.getUpdateService.call(new Object[0]), new Object[0])).get("packageInfo");
            VLog.m18993d("WebView", "Load WebView provider: " + packageInfo.packageName, new Object[0]);
            SPEC_SYSTEM_APP_LIST.add(packageInfo.packageName);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        PRE_INSTALL_PACKAGES.add("com.huawei.hwid");
    }

    public static Set<String> getPreInstallPackages() {
        return PRE_INSTALL_PACKAGES;
    }

    public static void addStaticBroadCastWhiteList(String str) {
        BROADCAST_START_WHITE_LIST.add(str);
    }

    public static boolean isSpecSystemPackage(String str) {
        return SPEC_SYSTEM_APP_LIST.contains(str);
    }

    public static boolean isConflictingInstrumentation(String str) {
        return INSTRUMENTATION_CONFLICTING.contains(str);
    }

    public static boolean shouldBlockIntent(Intent intent) {
        ComponentName component = intent.getComponent();
        if (component != null && GMS_BLOCK_COMPONENT.contains(component)) {
            return true;
        }
        String action = intent.getAction();
        return action != null && GMS_BLOCK_ACTION_LIST.contains(action);
    }

    public static boolean isActionInBlackList(String str) {
        return ACTION_BLACK_LIST.contains(str);
    }

    public static void addBlackAction(String str) {
        ACTION_BLACK_LIST.add(str);
    }

    public static void protectIntentFilter(IntentFilter intentFilter) {
        if (intentFilter != null) {
            ListIterator<String> listIterator = p110z1.IntentFilter.mActions.get(intentFilter).listIterator();
            while (listIterator.hasNext()) {
                String next = listIterator.next();
                if (isActionInBlackList(next)) {
                    listIterator.remove();
                } else {
                    String protectAction = protectAction(next);
                    if (protectAction != null) {
                        listIterator.set(protectAction);
                    }
                }
            }
        }
    }

    public static void protectIntent(Intent intent) {
        String protectAction = protectAction(intent.getAction());
        if (protectAction != null) {
            intent.setAction(protectAction);
        }
    }

    public static void unprotectIntent(Intent intent) {
        String unprotectAction = unprotectAction(intent.getAction());
        if (unprotectAction != null) {
            intent.setAction(unprotectAction);
        }
    }

    public static String getProtectActionPrefix() {
        if (PROTECT_ACTION_PREFIX == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(STATIC_PROTECT_ACTION_PREFIX);
            sb.append(VirtualCore.get().isMainProcess() ? 0 : VClient.get().getVUserHandle());
            PROTECT_ACTION_PREFIX = sb.toString();
        }
        return PROTECT_ACTION_PREFIX;
    }

    public static String protectAction(String str) {
        if (SYSTEM_BROADCAST_ACTION.contains(str)) {
            return str;
        }
        if (str == null) {
            return null;
        }
        if (str.startsWith(getProtectActionPrefix())) {
            return str;
        }
        String str2 = PROTECTED_ACTION_MAP.get(str);
        if (str2 != null) {
            return str2;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getProtectActionPrefix());
        sb.append(VirtualCore.get().isMainProcess() ? 0 : VClient.get().getVUserHandle());
        sb.append(str);
        return sb.toString();
    }

    public static String unprotectAction(String str) {
        if (str == null) {
            return null;
        }
        if (str.startsWith(getProtectActionPrefix())) {
            return str.substring(getProtectActionPrefix().length());
        }
        for (Map.Entry<String, String> entry : PROTECTED_ACTION_MAP.entrySet()) {
            if (entry.getValue().equals(str)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static boolean isWhitePermission(String str) {
        return WHITE_PERMISSION.contains(str);
    }

    public static boolean allowedStartFromBroadcast(String str) {
        return BROADCAST_START_WHITE_LIST.contains(str);
    }
}
