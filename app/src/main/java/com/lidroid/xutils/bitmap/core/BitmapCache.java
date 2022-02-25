package com.lidroid.xutils.bitmap.core;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.BitmapGlobalConfig;
import com.lidroid.xutils.bitmap.factory.BitmapFactory;
import com.lidroid.xutils.cache.FileNameGenerator;
import com.lidroid.xutils.cache.LruDiskCache;
import com.lidroid.xutils.cache.LruMemoryCache;
import com.lidroid.xutils.util.IOUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.util.OtherUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/* loaded from: classes.dex */
public class BitmapCache {
    private BitmapGlobalConfig globalConfig;
    private LruDiskCache mDiskLruCache;
    private LruMemoryCache<MemoryCacheKey, Bitmap> mMemoryCache;
    private final int DISK_CACHE_INDEX = 0;
    private final Object mDiskCacheLock = new Object();

    public BitmapCache(BitmapGlobalConfig bitmapGlobalConfig) {
        if (bitmapGlobalConfig != null) {
            this.globalConfig = bitmapGlobalConfig;
            return;
        }
        throw new IllegalArgumentException("globalConfig may not be null");
    }

    public void initMemoryCache() {
        if (this.globalConfig.isMemoryCacheEnabled()) {
            if (this.mMemoryCache != null) {
                try {
                    clearMemoryCache();
                } catch (Throwable unused) {
                }
            }
            this.mMemoryCache = new LruMemoryCache<MemoryCacheKey, Bitmap>(this.globalConfig.getMemoryCacheSize()) { // from class: com.lidroid.xutils.bitmap.core.BitmapCache.1
                /* JADX INFO: Access modifiers changed from: protected */
                public int sizeOf(MemoryCacheKey memoryCacheKey, Bitmap bitmap) {
                    if (bitmap == null) {
                        return 0;
                    }
                    return bitmap.getRowBytes() * bitmap.getHeight();
                }
            };
        }
    }

    public void initDiskCache() {
        synchronized (this.mDiskCacheLock) {
            if (this.globalConfig.isDiskCacheEnabled() && (this.mDiskLruCache == null || this.mDiskLruCache.isClosed())) {
                File file = new File(this.globalConfig.getDiskCachePath());
                if (file.exists() || file.mkdirs()) {
                    long availableSpace = OtherUtils.getAvailableSpace(file);
                    long diskCacheSize = this.globalConfig.getDiskCacheSize();
                    if (availableSpace > diskCacheSize) {
                        availableSpace = diskCacheSize;
                    }
                    this.mDiskLruCache = LruDiskCache.open(file, 1, 1, availableSpace);
                    this.mDiskLruCache.setFileNameGenerator(this.globalConfig.getFileNameGenerator());
                    LogUtils.m19222d("create disk cache success");
                }
            }
        }
    }

    public void setMemoryCacheSize(int i) {
        LruMemoryCache<MemoryCacheKey, Bitmap> lruMemoryCache = this.mMemoryCache;
        if (lruMemoryCache != null) {
            lruMemoryCache.setMaxSize(i);
        }
    }

    public void setDiskCacheSize(int i) {
        synchronized (this.mDiskCacheLock) {
            if (this.mDiskLruCache != null) {
                this.mDiskLruCache.setMaxSize(i);
            }
        }
    }

    public void setDiskCacheFileNameGenerator(FileNameGenerator fileNameGenerator) {
        synchronized (this.mDiskCacheLock) {
            if (!(this.mDiskLruCache == null || fileNameGenerator == null)) {
                this.mDiskLruCache.setFileNameGenerator(fileNameGenerator);
            }
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:22:0x0061
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    public android.graphics.Bitmap downloadBitmap(java.lang.String r15, com.lidroid.xutils.bitmap.BitmapDisplayConfig r16, com.lidroid.xutils.BitmapUtils.BitmapLoadTask<?> r17) {
        /*
            Method dump skipped, instructions count: 259
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lidroid.xutils.bitmap.core.BitmapCache.downloadBitmap(java.lang.String, com.lidroid.xutils.bitmap.BitmapDisplayConfig, com.lidroid.xutils.BitmapUtils$BitmapLoadTask):android.graphics.Bitmap");
    }

    public Bitmap addBitmapToMemoryCache(String str, BitmapDisplayConfig bitmapDisplayConfig, Bitmap bitmap, long j) throws IOException {
        BitmapFactory bitmapFactory;
        if (!(bitmapDisplayConfig == null || (bitmapFactory = bitmapDisplayConfig.getBitmapFactory()) == null)) {
            bitmap = bitmapFactory.cloneNew().createBitmap(bitmap);
        }
        if (!(str == null || bitmap == null || !this.globalConfig.isMemoryCacheEnabled() || this.mMemoryCache == null)) {
            this.mMemoryCache.put(new MemoryCacheKey(this, str, bitmapDisplayConfig, null), bitmap, j);
        }
        return bitmap;
    }

    public Bitmap getBitmapFromMemCache(String str, BitmapDisplayConfig bitmapDisplayConfig) {
        if (this.mMemoryCache == null || !this.globalConfig.isMemoryCacheEnabled()) {
            return null;
        }
        return this.mMemoryCache.get(new MemoryCacheKey(this, str, bitmapDisplayConfig, null));
    }

    public File getBitmapFileFromDiskCache(String str) {
        synchronized (this.mDiskCacheLock) {
            if (this.mDiskLruCache == null) {
                return null;
            }
            return this.mDiskLruCache.getCacheFile(str, 0);
        }
    }

    public Bitmap getBitmapFromDiskCache(String str, BitmapDisplayConfig bitmapDisplayConfig) {
        Throwable th;
        LruDiskCache.Snapshot snapshot;
        Bitmap decodeSampledBitmapFromDescriptor;
        if (str == null || !this.globalConfig.isDiskCacheEnabled()) {
            return null;
        }
        if (this.mDiskLruCache == null) {
            initDiskCache();
        }
        LruDiskCache lruDiskCache = this.mDiskLruCache;
        if (lruDiskCache != null) {
            try {
                snapshot = lruDiskCache.get(str);
                if (snapshot != null) {
                    if (bitmapDisplayConfig != null) {
                        try {
                            if (!bitmapDisplayConfig.isShowOriginal()) {
                                decodeSampledBitmapFromDescriptor = BitmapDecoder.decodeSampledBitmapFromDescriptor(snapshot.getInputStream(0).getFD(), bitmapDisplayConfig.getBitmapMaxSize(), bitmapDisplayConfig.getBitmapConfig());
                                return addBitmapToMemoryCache(str, bitmapDisplayConfig, rotateBitmapIfNeeded(str, bitmapDisplayConfig, decodeSampledBitmapFromDescriptor), this.mDiskLruCache.getExpiryTimestamp(str));
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            try {
                                LogUtils.m19219e(th.getMessage(), th);
                                return null;
                            } finally {
                                IOUtils.closeQuietly(snapshot);
                            }
                        }
                    }
                    decodeSampledBitmapFromDescriptor = BitmapDecoder.decodeFileDescriptor(snapshot.getInputStream(0).getFD());
                    return addBitmapToMemoryCache(str, bitmapDisplayConfig, rotateBitmapIfNeeded(str, bitmapDisplayConfig, decodeSampledBitmapFromDescriptor), this.mDiskLruCache.getExpiryTimestamp(str));
                }
            } catch (Throwable th3) {
                th = th3;
                snapshot = null;
            }
        }
        return null;
    }

    public void clearCache() {
        clearMemoryCache();
        clearDiskCache();
    }

    public void clearMemoryCache() {
        LruMemoryCache<MemoryCacheKey, Bitmap> lruMemoryCache = this.mMemoryCache;
        if (lruMemoryCache != null) {
            lruMemoryCache.evictAll();
        }
    }

    public void clearDiskCache() {
        synchronized (this.mDiskCacheLock) {
            if (this.mDiskLruCache != null && !this.mDiskLruCache.isClosed()) {
                this.mDiskLruCache.delete();
                this.mDiskLruCache.close();
                this.mDiskLruCache = null;
            }
        }
        initDiskCache();
    }

    public void clearCache(String str) {
        clearMemoryCache(str);
        clearDiskCache(str);
    }

    public void clearMemoryCache(String str) {
        MemoryCacheKey memoryCacheKey = new MemoryCacheKey(this, str, null, null);
        if (this.mMemoryCache != null) {
            while (this.mMemoryCache.containsKey(memoryCacheKey)) {
                this.mMemoryCache.remove(memoryCacheKey);
            }
        }
    }

    public void clearDiskCache(String str) {
        synchronized (this.mDiskCacheLock) {
            if (this.mDiskLruCache != null && !this.mDiskLruCache.isClosed()) {
                this.mDiskLruCache.remove(str);
            }
        }
    }

    public void flush() {
        synchronized (this.mDiskCacheLock) {
            if (this.mDiskLruCache != null) {
                this.mDiskLruCache.flush();
            }
        }
    }

    public void close() {
        synchronized (this.mDiskCacheLock) {
            if (this.mDiskLruCache != null) {
                if (!this.mDiskLruCache.isClosed()) {
                    this.mDiskLruCache.close();
                }
                this.mDiskLruCache = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class BitmapMeta {
        public byte[] data;
        public long expiryTimestamp;
        public FileInputStream inputStream;

        private BitmapMeta() {
        }

        /* synthetic */ BitmapMeta(BitmapCache bitmapCache, BitmapMeta bitmapMeta) {
            this();
        }
    }

    private Bitmap decodeBitmapMeta(BitmapMeta bitmapMeta, BitmapDisplayConfig bitmapDisplayConfig) throws IOException {
        if (bitmapMeta == null) {
            return null;
        }
        if (bitmapMeta.inputStream != null) {
            if (bitmapDisplayConfig == null || bitmapDisplayConfig.isShowOriginal()) {
                return BitmapDecoder.decodeFileDescriptor(bitmapMeta.inputStream.getFD());
            }
            return BitmapDecoder.decodeSampledBitmapFromDescriptor(bitmapMeta.inputStream.getFD(), bitmapDisplayConfig.getBitmapMaxSize(), bitmapDisplayConfig.getBitmapConfig());
        } else if (bitmapMeta.data == null) {
            return null;
        } else {
            if (bitmapDisplayConfig == null || bitmapDisplayConfig.isShowOriginal()) {
                return BitmapDecoder.decodeByteArray(bitmapMeta.data);
            }
            return BitmapDecoder.decodeSampledBitmapFromByteArray(bitmapMeta.data, bitmapDisplayConfig.getBitmapMaxSize(), bitmapDisplayConfig.getBitmapConfig());
        }
    }

    private synchronized Bitmap rotateBitmapIfNeeded(String str, BitmapDisplayConfig bitmapDisplayConfig, Bitmap bitmap) {
        File bitmapFileFromDiskCache;
        Bitmap createBitmap;
        if (bitmapDisplayConfig != null) {
            if (bitmapDisplayConfig.isAutoRotation() && (bitmapFileFromDiskCache = getBitmapFileFromDiskCache(str)) != null && bitmapFileFromDiskCache.exists()) {
                try {
                    int i = 0;
                    int attributeInt = new ExifInterface(bitmapFileFromDiskCache.getPath()).getAttributeInt("Orientation", 0);
                    if (attributeInt == 3) {
                        i = 180;
                    } else if (attributeInt == 6) {
                        i = 90;
                    } else if (attributeInt == 8) {
                        i = 270;
                    }
                    if (i != 0) {
                        Matrix matrix = new Matrix();
                        matrix.postRotate(i);
                        createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                        bitmap.recycle();
                    }
                } catch (Throwable unused) {
                    return bitmap;
                }
            }
        }
        createBitmap = bitmap;
        return createBitmap;
    }

    /* loaded from: classes.dex */
    public class MemoryCacheKey {
        private String subKey;
        private String uri;

        private MemoryCacheKey(String str, BitmapDisplayConfig bitmapDisplayConfig) {
            this.uri = str;
            this.subKey = bitmapDisplayConfig == null ? null : bitmapDisplayConfig.toString();
        }

        /* synthetic */ MemoryCacheKey(BitmapCache bitmapCache, String str, BitmapDisplayConfig bitmapDisplayConfig, MemoryCacheKey memoryCacheKey) {
            this(str, bitmapDisplayConfig);
        }

        public boolean equals(Object obj) {
            String str;
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MemoryCacheKey)) {
                return false;
            }
            MemoryCacheKey memoryCacheKey = (MemoryCacheKey) obj;
            if (!this.uri.equals(memoryCacheKey.uri)) {
                return false;
            }
            String str2 = this.subKey;
            if (str2 == null || (str = memoryCacheKey.subKey) == null) {
                return true;
            }
            return str2.equals(str);
        }

        public int hashCode() {
            return this.uri.hashCode();
        }
    }
}
