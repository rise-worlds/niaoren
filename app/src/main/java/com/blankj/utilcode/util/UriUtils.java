package com.blankj.utilcode.util;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.p003v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import java.io.File;

/* renamed from: com.blankj.utilcode.util.bd */
/* loaded from: classes.dex */
public final class UriUtils {
    private UriUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static Uri m22990a(@NonNull File file) {
        if (file == null) {
            throw new NullPointerException("Argument 'file' of type File (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (Build.VERSION.SDK_INT < 24) {
            return Uri.fromFile(file);
        } else {
            return FileProvider.getUriForFile(Utils.m24103a(), Utils.m24103a().getPackageName() + ".utilcode.provider", file);
        }
    }

    /* renamed from: a */
    public static File m22993a(@NonNull Uri uri) {
        Uri uri2;
        if (uri != null) {
            Log.d("UriUtils", uri.toString());
            String authority = uri.getAuthority();
            String scheme = uri.getScheme();
            String path = uri.getPath();
            if (Build.VERSION.SDK_INT >= 24 && path != null && path.startsWith("/external/")) {
                return new File(Environment.getExternalStorageDirectory().getAbsolutePath() + path.replace("/external", ""));
            } else if ("file".equals(scheme)) {
                if (path != null) {
                    return new File(path);
                }
                Log.d("UriUtils", uri.toString() + " parse failed. -> 0");
                return null;
            } else if (Build.VERSION.SDK_INT < 19 || !DocumentsContract.isDocumentUri(Utils.m24103a(), uri)) {
                if (ServiceManagerNative.CONTENT.equals(scheme)) {
                    return m22992a(uri, "2");
                }
                Log.d("UriUtils", uri.toString() + " parse failed. -> 3");
                return null;
            } else if ("com.android.externalstorage.documents".equals(authority)) {
                String[] split = DocumentsContract.getDocumentId(uri).split(":");
                if ("primary".equalsIgnoreCase(split[0])) {
                    return new File(Environment.getExternalStorageDirectory() + "/" + split[1]);
                }
                Log.d("UriUtils", uri.toString() + " parse failed. -> 1_0");
                return null;
            } else if ("com.android.providers.downloads.documents".equals(authority)) {
                String documentId = DocumentsContract.getDocumentId(uri);
                if (!TextUtils.isEmpty(documentId)) {
                    if (documentId.startsWith("raw:")) {
                        return new File(documentId.substring(4));
                    }
                    try {
                        return m22992a(ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId).longValue()), "1_1");
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
                Log.d("UriUtils", uri.toString() + " parse failed. -> 1_1");
                return null;
            } else if ("com.android.providers.media.documents".equals(authority)) {
                String[] split2 = DocumentsContract.getDocumentId(uri).split(":");
                String str = split2[0];
                if ("image".equals(str)) {
                    uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(str)) {
                    uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(str)) {
                    uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                } else {
                    Log.d("UriUtils", uri.toString() + " parse failed. -> 1_2");
                    return null;
                }
                return m22991a(uri2, "_id=?", new String[]{split2[1]}, "1_2");
            } else if (ServiceManagerNative.CONTENT.equals(scheme)) {
                return m22992a(uri, "1_3");
            } else {
                Log.d("UriUtils", uri.toString() + " parse failed. -> 1_4");
                return null;
            }
        } else {
            throw new NullPointerException("Argument 'uri' of type Uri (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    private static File m22992a(Uri uri, String str) {
        return m22991a(uri, null, null, str);
    }

    /* renamed from: a */
    private static File m22991a(Uri uri, String str, String[] strArr, String str2) {
        Cursor query = Utils.m24103a().getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
        try {
            if (query == null) {
                Log.d("UriUtils", uri.toString() + " parse failed(cursor is null). -> " + str2);
                return null;
            } else if (query.moveToFirst()) {
                int columnIndex = query.getColumnIndex("_data");
                if (columnIndex > -1) {
                    return new File(query.getString(columnIndex));
                }
                Log.d("UriUtils", uri.toString() + " parse failed(columnIndex: " + columnIndex + " is wrong). -> " + str2);
                return null;
            } else {
                Log.d("UriUtils", uri.toString() + " parse failed(moveToFirst return false). -> " + str2);
                return null;
            }
        } catch (Exception unused) {
            Log.d("UriUtils", uri.toString() + " parse failed. -> " + str2);
            return null;
        } finally {
            query.close();
        }
    }
}
