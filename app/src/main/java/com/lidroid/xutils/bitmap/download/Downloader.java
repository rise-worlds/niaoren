package com.lidroid.xutils.bitmap.download;

import android.content.Context;
import com.lidroid.xutils.BitmapUtils;
import java.io.OutputStream;

/* loaded from: classes.dex */
public abstract class Downloader {
    private Context context;
    private int defaultConnectTimeout;
    private long defaultExpiry;
    private int defaultReadTimeout;

    public abstract long downloadToStream(String str, OutputStream outputStream, BitmapUtils.BitmapLoadTask<?> bitmapLoadTask);

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setDefaultExpiry(long j) {
        this.defaultExpiry = j;
    }

    public long getDefaultExpiry() {
        return this.defaultExpiry;
    }

    public int getDefaultConnectTimeout() {
        return this.defaultConnectTimeout;
    }

    public void setDefaultConnectTimeout(int i) {
        this.defaultConnectTimeout = i;
    }

    public int getDefaultReadTimeout() {
        return this.defaultReadTimeout;
    }

    public void setDefaultReadTimeout(int i) {
        this.defaultReadTimeout = i;
    }
}
