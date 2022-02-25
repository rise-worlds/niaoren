package com.lody.virtual.helper.compat;

import android.content.ContentProviderClient;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;

/* loaded from: classes.dex */
public class ContentProviderCompat {
    public static Bundle call(Context context, Uri uri, String str, String str2, Bundle bundle, int i) throws IllegalAccessException {
        if (Build.VERSION.SDK_INT < 17) {
            return context.getContentResolver().call(uri, str, str2, bundle);
        }
        ContentProviderClient acquireContentProviderClientRetry = acquireContentProviderClientRetry(context, uri, i);
        try {
            try {
                if (acquireContentProviderClientRetry != null) {
                    return acquireContentProviderClientRetry.call(str, str2, bundle);
                }
                throw new IllegalAccessException();
            } catch (RemoteException e) {
                throw new IllegalAccessException(e.getMessage());
            }
        } finally {
            releaseQuietly(acquireContentProviderClientRetry);
        }
    }

    private static ContentProviderClient acquireContentProviderClient(Context context, Uri uri) {
        try {
            if (Build.VERSION.SDK_INT >= 16) {
                return context.getContentResolver().acquireUnstableContentProviderClient(uri);
            }
            return context.getContentResolver().acquireContentProviderClient(uri);
        } catch (SecurityException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ContentProviderClient acquireContentProviderClientRetry(Context context, Uri uri, int i) {
        ContentProviderClient acquireContentProviderClient = acquireContentProviderClient(context, uri);
        if (acquireContentProviderClient == null) {
            int i2 = 0;
            while (i2 < i && acquireContentProviderClient == null) {
                SystemClock.sleep(100L);
                i2++;
                acquireContentProviderClient = acquireContentProviderClient(context, uri);
            }
        }
        return acquireContentProviderClient;
    }

    public static ContentProviderClient acquireContentProviderClientRetry(Context context, String str, int i) {
        ContentProviderClient acquireContentProviderClient = acquireContentProviderClient(context, str);
        if (acquireContentProviderClient == null) {
            int i2 = 0;
            while (i2 < i && acquireContentProviderClient == null) {
                SystemClock.sleep(100L);
                i2++;
                acquireContentProviderClient = acquireContentProviderClient(context, str);
            }
        }
        return acquireContentProviderClient;
    }

    private static ContentProviderClient acquireContentProviderClient(Context context, String str) {
        if (Build.VERSION.SDK_INT >= 16) {
            return context.getContentResolver().acquireUnstableContentProviderClient(str);
        }
        return context.getContentResolver().acquireContentProviderClient(str);
    }

    private static void releaseQuietly(ContentProviderClient contentProviderClient) {
        if (contentProviderClient != null) {
            try {
                if (Build.VERSION.SDK_INT >= 24) {
                    contentProviderClient.close();
                } else {
                    contentProviderClient.release();
                }
            } catch (Exception unused) {
            }
        }
    }
}
