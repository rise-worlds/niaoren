package com.lody.virtual.client.hook.providers;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.hook.base.MethodBox;
import com.lody.virtual.helper.compat.BuildCompat;
import com.lody.virtual.helper.utils.VLog;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/* loaded from: classes.dex */
class DownloadProviderHook extends ExternalProviderHook {
    private static final String COLUMN_COOKIE_DATA = "cookiedata";
    private static final String COLUMN_IS_PUBLIC_API = "is_public_api";
    private static final String COLUMN_NOTIFICATION_PACKAGE = "notificationpackage";
    private static final String INSERT_KEY_PREFIX = "http_header_";
    private static final String TAG = "DownloadProviderHook";
    private static final String COLUMN_OTHER_UID = "otheruid";
    private static final String COLUMN_NOTIFICATION_CLASS = "notificationclass";
    private static final String[] ENFORCE_REMOVE_COLUMNS = {COLUMN_OTHER_UID, COLUMN_NOTIFICATION_CLASS};

    /* JADX INFO: Access modifiers changed from: package-private */
    public DownloadProviderHook(Object obj) {
        super(obj);
    }

    @Override // com.lody.virtual.client.hook.providers.ProviderHook
    public Uri insert(MethodBox methodBox, Uri uri, ContentValues contentValues) throws InvocationTargetException {
        VLog.m18992e("DownloadManager", "insert: " + contentValues);
        if (contentValues.getAsString(COLUMN_NOTIFICATION_PACKAGE) == null) {
            return (Uri) methodBox.call();
        }
        contentValues.put(COLUMN_NOTIFICATION_PACKAGE, VirtualCore.get().getHostPkg());
        if (contentValues.containsKey(COLUMN_COOKIE_DATA)) {
            String asString = contentValues.getAsString(COLUMN_COOKIE_DATA);
            contentValues.remove(COLUMN_COOKIE_DATA);
            int i = 0;
            while (true) {
                if (!contentValues.containsKey(INSERT_KEY_PREFIX + i)) {
                    break;
                }
                i++;
            }
            contentValues.put(INSERT_KEY_PREFIX + i, "Cookie: " + asString);
        }
        if (!contentValues.containsKey(COLUMN_IS_PUBLIC_API)) {
            contentValues.put(COLUMN_IS_PUBLIC_API, (Boolean) true);
        }
        for (String str : ENFORCE_REMOVE_COLUMNS) {
            contentValues.remove(str);
        }
        contentValues.put("description", VirtualCore.get().getHostPkg());
        return super.insert(methodBox, uri, contentValues);
    }

    @Override // com.lody.virtual.client.hook.providers.ProviderHook
    public Cursor query(MethodBox methodBox, Uri uri, String[] strArr, String str, String[] strArr2, String str2, Bundle bundle) throws InvocationTargetException {
        String[] strArr3;
        String str3;
        VLog.m18992e("DownloadManager", "query : selection: " + str + ", args: " + Arrays.toString(strArr2));
        if ("notificationclass=?".equals(str)) {
            int i = 1;
            String[] strArr4 = new String[1];
            strArr4[0] = VirtualCore.get().getHostPkg();
            if (BuildCompat.isOreo()) {
                bundle.remove(ProviderHook.QUERY_ARG_SQL_SELECTION);
                bundle.remove(ProviderHook.QUERY_ARG_SQL_SELECTION_ARGS);
                bundle.putString(ProviderHook.QUERY_ARG_SQL_SELECTION, "description=?");
                bundle.putStringArray(ProviderHook.QUERY_ARG_SQL_SELECTION_ARGS, strArr4);
            } else {
                if (Build.VERSION.SDK_INT < 18) {
                    i = 0;
                }
                methodBox.args[i + 2] = "description=?";
                methodBox.args[i + 3] = strArr4;
            }
            str3 = "description=?";
            strArr3 = strArr4;
        } else {
            str3 = str;
            strArr3 = strArr2;
        }
        return super.query(methodBox, uri, strArr, str3, strArr3, str2, bundle);
    }
}
