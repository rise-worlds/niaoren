package com.lody.virtual.helper.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Process;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ACache {
    private static final int MAX_COUNT = Integer.MAX_VALUE;
    private static final int MAX_SIZE = 50000000;
    public static final int TIME_DAY = 86400;
    public static final int TIME_HOUR = 3600;
    private static Map<String, ACache> mInstanceMap = new HashMap();
    private ACacheManager mCache;

    public static ACache get(Context context) {
        return get(context, "ACache");
    }

    public static ACache get(Context context, String str) {
        return get(new File(context.getCacheDir(), str), 50000000L, Integer.MAX_VALUE);
    }

    public static ACache get(File file) {
        return get(file, 50000000L, Integer.MAX_VALUE);
    }

    public static ACache get(Context context, long j, int i) {
        return get(new File(context.getCacheDir(), "ACache"), j, i);
    }

    public static ACache get(File file, long j, int i) {
        Map<String, ACache> map = mInstanceMap;
        ACache aCache = map.get(file.getAbsoluteFile() + myPid());
        if (aCache != null) {
            return aCache;
        }
        ACache aCache2 = new ACache(file, j, i);
        Map<String, ACache> map2 = mInstanceMap;
        map2.put(file.getAbsolutePath() + myPid(), aCache2);
        return aCache2;
    }

    private static String myPid() {
        return "_" + Process.myPid();
    }

    private ACache(File file, long j, int i) {
        if (file.exists() || file.mkdirs()) {
            this.mCache = new ACacheManager(file, j, i);
            return;
        }
        throw new RuntimeException("can't make dirs in " + file.getAbsolutePath());
    }

    /* loaded from: classes.dex */
    class xFileOutputStream extends FileOutputStream {
        File file;

        public xFileOutputStream(File file) throws FileNotFoundException {
            super(file);
            this.file = file;
        }

        @Override // java.io.FileOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            super.close();
            ACache.this.mCache.put(this.file);
        }
    }

    public void put(String str, String str2) {
        Throwable th;
        IOException e;
        IOException e2;
        BufferedWriter bufferedWriter;
        File newFile = this.mCache.newFile(str);
        BufferedWriter bufferedWriter2 = null;
        try {
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(newFile), 1024);
            } catch (IOException e3) {
                e2 = e3;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            bufferedWriter.write(str2);
            try {
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException e4) {
                e = e4;
                e.printStackTrace();
                this.mCache.put(newFile);
            }
        } catch (IOException e5) {
            e2 = e5;
            bufferedWriter2 = bufferedWriter;
            e2.printStackTrace();
            if (bufferedWriter2 != null) {
                try {
                    bufferedWriter2.flush();
                    bufferedWriter2.close();
                } catch (IOException e6) {
                    e = e6;
                    e.printStackTrace();
                    this.mCache.put(newFile);
                }
            }
            this.mCache.put(newFile);
        } catch (Throwable th3) {
            th = th3;
            bufferedWriter2 = bufferedWriter;
            if (bufferedWriter2 != null) {
                try {
                    bufferedWriter2.flush();
                    bufferedWriter2.close();
                } catch (IOException e7) {
                    e7.printStackTrace();
                }
            }
            this.mCache.put(newFile);
            throw th;
        }
        this.mCache.put(newFile);
    }

    public void put(String str, String str2, int i) {
        put(str, Utils.newStringWithDateInfo(i, str2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String getAsString(java.lang.String r6) {
        /*
            r5 = this;
            com.lody.virtual.helper.utils.ACache$ACacheManager r0 = r5.mCache
            java.io.File r0 = com.lody.virtual.helper.utils.ACache.ACacheManager.access$500(r0, r6)
            boolean r1 = r0.exists()
            r2 = 0
            if (r1 != 0) goto L_0x000e
            return r2
        L_0x000e:
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: all -> 0x0051, IOException -> 0x0054
            java.io.FileReader r3 = new java.io.FileReader     // Catch: all -> 0x0051, IOException -> 0x0054
            r3.<init>(r0)     // Catch: all -> 0x0051, IOException -> 0x0054
            r1.<init>(r3)     // Catch: all -> 0x0051, IOException -> 0x0054
            java.lang.String r0 = ""
        L_0x001a:
            java.lang.String r3 = r1.readLine()     // Catch: IOException -> 0x004f, all -> 0x0064
            if (r3 == 0) goto L_0x0030
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: IOException -> 0x004f, all -> 0x0064
            r4.<init>()     // Catch: IOException -> 0x004f, all -> 0x0064
            r4.append(r0)     // Catch: IOException -> 0x004f, all -> 0x0064
            r4.append(r3)     // Catch: IOException -> 0x004f, all -> 0x0064
            java.lang.String r0 = r4.toString()     // Catch: IOException -> 0x004f, all -> 0x0064
            goto L_0x001a
        L_0x0030:
            boolean r3 = com.lody.virtual.helper.utils.ACache.Utils.access$600(r0)     // Catch: IOException -> 0x004f, all -> 0x0064
            if (r3 != 0) goto L_0x0043
            java.lang.String r6 = com.lody.virtual.helper.utils.ACache.Utils.access$700(r0)     // Catch: IOException -> 0x004f, all -> 0x0064
            r1.close()     // Catch: IOException -> 0x003e
            goto L_0x0042
        L_0x003e:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0042:
            return r6
        L_0x0043:
            r1.close()     // Catch: IOException -> 0x0047
            goto L_0x004b
        L_0x0047:
            r0 = move-exception
            r0.printStackTrace()
        L_0x004b:
            r5.remove(r6)
            return r2
        L_0x004f:
            r6 = move-exception
            goto L_0x0056
        L_0x0051:
            r6 = move-exception
            r1 = r2
            goto L_0x0065
        L_0x0054:
            r6 = move-exception
            r1 = r2
        L_0x0056:
            r6.printStackTrace()     // Catch: all -> 0x0064
            if (r1 == 0) goto L_0x0063
            r1.close()     // Catch: IOException -> 0x005f
            goto L_0x0063
        L_0x005f:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0063:
            return r2
        L_0x0064:
            r6 = move-exception
        L_0x0065:
            if (r1 == 0) goto L_0x006f
            r1.close()     // Catch: IOException -> 0x006b
            goto L_0x006f
        L_0x006b:
            r0 = move-exception
            r0.printStackTrace()
        L_0x006f:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lody.virtual.helper.utils.ACache.getAsString(java.lang.String):java.lang.String");
    }

    public void put(String str, JSONObject jSONObject) {
        put(str, jSONObject.toString());
    }

    public void put(String str, JSONObject jSONObject, int i) {
        put(str, jSONObject.toString(), i);
    }

    public JSONObject getAsJSONObject(String str) {
        try {
            return new JSONObject(getAsString(str));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void put(String str, JSONArray jSONArray) {
        put(str, jSONArray.toString());
    }

    public void put(String str, JSONArray jSONArray, int i) {
        put(str, jSONArray.toString(), i);
    }

    public JSONArray getAsJSONArray(String str) {
        try {
            return new JSONArray(getAsString(str));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void put(String str, byte[] bArr) {
        Throwable th;
        IOException e;
        Exception e2;
        FileOutputStream fileOutputStream;
        File newFile = this.mCache.newFile(str);
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(newFile);
            } catch (Exception e3) {
                e2 = e3;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            fileOutputStream.write(bArr);
            try {
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e4) {
                e = e4;
                e.printStackTrace();
                this.mCache.put(newFile);
            }
        } catch (Exception e5) {
            e2 = e5;
            fileOutputStream2 = fileOutputStream;
            e2.printStackTrace();
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.flush();
                    fileOutputStream2.close();
                } catch (IOException e6) {
                    e = e6;
                    e.printStackTrace();
                    this.mCache.put(newFile);
                }
            }
            this.mCache.put(newFile);
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.flush();
                    fileOutputStream2.close();
                } catch (IOException e7) {
                    e7.printStackTrace();
                }
            }
            this.mCache.put(newFile);
            throw th;
        }
        this.mCache.put(newFile);
    }

    public OutputStream put(String str) throws FileNotFoundException {
        return new xFileOutputStream(this.mCache.newFile(str));
    }

    public InputStream get(String str) throws FileNotFoundException {
        File file = this.mCache.get(str);
        if (!file.exists()) {
            return null;
        }
        return new FileInputStream(file);
    }

    public void put(String str, byte[] bArr, int i) {
        put(str, Utils.newByteArrayWithDateInfo(i, bArr));
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0053: MOVE  (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:30:0x0053 */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0056 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public byte[] getAsBinary(java.lang.String r6) {
        /*
            r5 = this;
            r0 = 0
            com.lody.virtual.helper.utils.ACache$ACacheManager r1 = r5.mCache     // Catch: all -> 0x0040, Exception -> 0x0042
            java.io.File r1 = com.lody.virtual.helper.utils.ACache.ACacheManager.access$500(r1, r6)     // Catch: all -> 0x0040, Exception -> 0x0042
            boolean r2 = r1.exists()     // Catch: all -> 0x0040, Exception -> 0x0042
            if (r2 != 0) goto L_0x000e
            return r0
        L_0x000e:
            java.io.RandomAccessFile r2 = new java.io.RandomAccessFile     // Catch: all -> 0x0040, Exception -> 0x0042
            java.lang.String r3 = "r"
            r2.<init>(r1, r3)     // Catch: all -> 0x0040, Exception -> 0x0042
            long r3 = r2.length()     // Catch: Exception -> 0x003e, all -> 0x0052
            int r1 = (int) r3     // Catch: Exception -> 0x003e, all -> 0x0052
            byte[] r1 = new byte[r1]     // Catch: Exception -> 0x003e, all -> 0x0052
            r2.read(r1)     // Catch: Exception -> 0x003e, all -> 0x0052
            boolean r3 = com.lody.virtual.helper.utils.ACache.Utils.access$900(r1)     // Catch: Exception -> 0x003e, all -> 0x0052
            if (r3 != 0) goto L_0x0032
            byte[] r6 = com.lody.virtual.helper.utils.ACache.Utils.access$1000(r1)     // Catch: Exception -> 0x003e, all -> 0x0052
            r2.close()     // Catch: IOException -> 0x002d
            goto L_0x0031
        L_0x002d:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0031:
            return r6
        L_0x0032:
            r2.close()     // Catch: IOException -> 0x0036
            goto L_0x003a
        L_0x0036:
            r1 = move-exception
            r1.printStackTrace()
        L_0x003a:
            r5.remove(r6)
            return r0
        L_0x003e:
            r6 = move-exception
            goto L_0x0044
        L_0x0040:
            r6 = move-exception
            goto L_0x0054
        L_0x0042:
            r6 = move-exception
            r2 = r0
        L_0x0044:
            r6.printStackTrace()     // Catch: all -> 0x0052
            if (r2 == 0) goto L_0x0051
            r2.close()     // Catch: IOException -> 0x004d
            goto L_0x0051
        L_0x004d:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0051:
            return r0
        L_0x0052:
            r6 = move-exception
            r0 = r2
        L_0x0054:
            if (r0 == 0) goto L_0x005e
            r0.close()     // Catch: IOException -> 0x005a
            goto L_0x005e
        L_0x005a:
            r0 = move-exception
            r0.printStackTrace()
        L_0x005e:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lody.virtual.helper.utils.ACache.getAsBinary(java.lang.String):byte[]");
    }

    public void put(String str, Serializable serializable) {
        put(str, serializable, -1);
    }

    public void put(String str, Serializable serializable, int i) {
        Throwable th;
        Exception e;
        ByteArrayOutputStream byteArrayOutputStream;
        ObjectOutputStream objectOutputStream;
        ObjectOutputStream objectOutputStream2 = null;
        try {
            try {
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e2) {
                e = e2;
            }
            try {
                objectOutputStream.writeObject(serializable);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                if (i != -1) {
                    put(str, byteArray, i);
                } else {
                    put(str, byteArray);
                }
                objectOutputStream.close();
            } catch (Exception e3) {
                e = e3;
                objectOutputStream2 = objectOutputStream;
                e.printStackTrace();
                objectOutputStream2.close();
            } catch (Throwable th3) {
                th = th3;
                objectOutputStream2 = objectOutputStream;
                try {
                    objectOutputStream2.close();
                } catch (IOException unused) {
                }
                throw th;
            }
        } catch (IOException unused2) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x0051 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x005b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object getAsObject(java.lang.String r5) {
        /*
            r4 = this;
            byte[] r5 = r4.getAsBinary(r5)
            r0 = 0
            if (r5 == 0) goto L_0x0064
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch: all -> 0x002d, Exception -> 0x0030
            r1.<init>(r5)     // Catch: all -> 0x002d, Exception -> 0x0030
            java.io.ObjectInputStream r5 = new java.io.ObjectInputStream     // Catch: all -> 0x0028, Exception -> 0x002a
            r5.<init>(r1)     // Catch: all -> 0x0028, Exception -> 0x002a
            java.lang.Object r0 = r5.readObject()     // Catch: Exception -> 0x0026, all -> 0x004b
            r1.close()     // Catch: IOException -> 0x0019
            goto L_0x001d
        L_0x0019:
            r1 = move-exception
            r1.printStackTrace()
        L_0x001d:
            r5.close()     // Catch: IOException -> 0x0021
            goto L_0x0025
        L_0x0021:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0025:
            return r0
        L_0x0026:
            r2 = move-exception
            goto L_0x0033
        L_0x0028:
            r5 = move-exception
            goto L_0x004f
        L_0x002a:
            r2 = move-exception
            r5 = r0
            goto L_0x0033
        L_0x002d:
            r5 = move-exception
            r1 = r0
            goto L_0x004f
        L_0x0030:
            r2 = move-exception
            r5 = r0
            r1 = r5
        L_0x0033:
            r2.printStackTrace()     // Catch: all -> 0x004b
            if (r1 == 0) goto L_0x0040
            r1.close()     // Catch: IOException -> 0x003c
            goto L_0x0040
        L_0x003c:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0040:
            if (r5 == 0) goto L_0x004a
            r5.close()     // Catch: IOException -> 0x0046
            goto L_0x004a
        L_0x0046:
            r5 = move-exception
            r5.printStackTrace()
        L_0x004a:
            return r0
        L_0x004b:
            r0 = move-exception
            r3 = r0
            r0 = r5
            r5 = r3
        L_0x004f:
            if (r1 == 0) goto L_0x0059
            r1.close()     // Catch: IOException -> 0x0055
            goto L_0x0059
        L_0x0055:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0059:
            if (r0 == 0) goto L_0x0063
            r0.close()     // Catch: IOException -> 0x005f
            goto L_0x0063
        L_0x005f:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0063:
            throw r5
        L_0x0064:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lody.virtual.helper.utils.ACache.getAsObject(java.lang.String):java.lang.Object");
    }

    public void put(String str, Bitmap bitmap) {
        put(str, Utils.Bitmap2Bytes(bitmap));
    }

    public void put(String str, Bitmap bitmap, int i) {
        put(str, Utils.Bitmap2Bytes(bitmap), i);
    }

    public Bitmap getAsBitmap(String str) {
        if (getAsBinary(str) == null) {
            return null;
        }
        return Utils.Bytes2Bimap(getAsBinary(str));
    }

    public void put(String str, Drawable drawable) {
        put(str, Utils.drawable2Bitmap(drawable));
    }

    public void put(String str, Drawable drawable, int i) {
        put(str, Utils.drawable2Bitmap(drawable), i);
    }

    public Drawable getAsDrawable(String str) {
        if (getAsBinary(str) == null) {
            return null;
        }
        return Utils.bitmap2Drawable(Utils.Bytes2Bimap(getAsBinary(str)));
    }

    public File file(String str) {
        File newFile = this.mCache.newFile(str);
        if (newFile.exists()) {
            return newFile;
        }
        return null;
    }

    public boolean remove(String str) {
        return this.mCache.remove(str);
    }

    public void clear() {
        this.mCache.clear();
    }

    /* loaded from: classes.dex */
    public class ACacheManager {
        private final AtomicInteger cacheCount;
        protected File cacheDir;
        private final AtomicLong cacheSize;
        private final int countLimit;
        private final Map<File, Long> lastUsageDates;
        private final long sizeLimit;

        private ACacheManager(File file, long j, int i) {
            this.lastUsageDates = Collections.synchronizedMap(new HashMap());
            this.cacheDir = file;
            this.sizeLimit = j;
            this.countLimit = i;
            this.cacheSize = new AtomicLong();
            this.cacheCount = new AtomicInteger();
            calculateCacheSizeAndCacheCount();
        }

        private void calculateCacheSizeAndCacheCount() {
            new Thread(new Runnable() { // from class: com.lody.virtual.helper.utils.ACache.ACacheManager.1
                @Override // java.lang.Runnable
                public void run() {
                    File[] listFiles = ACacheManager.this.cacheDir.listFiles();
                    if (listFiles != null) {
                        int i = 0;
                        int i2 = 0;
                        for (File file : listFiles) {
                            i = (int) (i + ACacheManager.this.calculateSize(file));
                            i2++;
                            ACacheManager.this.lastUsageDates.put(file, Long.valueOf(file.lastModified()));
                        }
                        ACacheManager.this.cacheSize.set(i);
                        ACacheManager.this.cacheCount.set(i2);
                    }
                }
            }).start();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void put(File file) {
            int i = this.cacheCount.get();
            while (i + 1 > this.countLimit) {
                this.cacheSize.addAndGet(-removeNext());
                i = this.cacheCount.addAndGet(-1);
            }
            this.cacheCount.addAndGet(1);
            long calculateSize = calculateSize(file);
            long j = this.cacheSize.get();
            while (j + calculateSize > this.sizeLimit) {
                j = this.cacheSize.addAndGet(-removeNext());
            }
            this.cacheSize.addAndGet(calculateSize);
            Long valueOf = Long.valueOf(System.currentTimeMillis());
            file.setLastModified(valueOf.longValue());
            this.lastUsageDates.put(file, valueOf);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public File get(String str) {
            File newFile = newFile(str);
            Long valueOf = Long.valueOf(System.currentTimeMillis());
            newFile.setLastModified(valueOf.longValue());
            this.lastUsageDates.put(newFile, valueOf);
            return newFile;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public File newFile(String str) {
            File file = this.cacheDir;
            return new File(file, str.hashCode() + "");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean remove(String str) {
            return get(str).delete();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clear() {
            this.lastUsageDates.clear();
            this.cacheSize.set(0L);
            File[] listFiles = this.cacheDir.listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
                    file.delete();
                }
            }
        }

        private long removeNext() {
            File file;
            if (this.lastUsageDates.isEmpty()) {
                return 0L;
            }
            Set<Map.Entry<File, Long>> entrySet = this.lastUsageDates.entrySet();
            synchronized (this.lastUsageDates) {
                file = null;
                Long l = null;
                for (Map.Entry<File, Long> entry : entrySet) {
                    if (file == null) {
                        file = entry.getKey();
                        l = entry.getValue();
                    } else {
                        Long value = entry.getValue();
                        if (value.longValue() < l.longValue()) {
                            file = entry.getKey();
                            l = value;
                        }
                    }
                }
            }
            long calculateSize = calculateSize(file);
            if (file.delete()) {
                this.lastUsageDates.remove(file);
            }
            return calculateSize;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public long calculateSize(File file) {
            return file.length();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Utils {
        private static final char mSeparator = ' ';

        private Utils() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isDue(String str) {
            return isDue(str.getBytes());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isDue(byte[] bArr) {
            String[] dateInfoFromDate = getDateInfoFromDate(bArr);
            if (dateInfoFromDate != null && dateInfoFromDate.length == 2) {
                String str = dateInfoFromDate[0];
                while (str.startsWith(ResultTypeConstant.f7213z)) {
                    str = str.substring(1, str.length());
                }
                if (System.currentTimeMillis() > Long.valueOf(str).longValue() + (Long.valueOf(dateInfoFromDate[1]).longValue() * 1000)) {
                    return true;
                }
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String newStringWithDateInfo(int i, String str) {
            return createDateInfo(i) + str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static byte[] newByteArrayWithDateInfo(int i, byte[] bArr) {
            byte[] bytes = createDateInfo(i).getBytes();
            byte[] bArr2 = new byte[bytes.length + bArr.length];
            System.arraycopy(bytes, 0, bArr2, 0, bytes.length);
            System.arraycopy(bArr, 0, bArr2, bytes.length, bArr.length);
            return bArr2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String clearDateInfo(String str) {
            return (str == null || !hasDateInfo(str.getBytes())) ? str : str.substring(str.indexOf(32) + 1, str.length());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static byte[] clearDateInfo(byte[] bArr) {
            return hasDateInfo(bArr) ? copyOfRange(bArr, indexOf(bArr, mSeparator) + 1, bArr.length) : bArr;
        }

        private static boolean hasDateInfo(byte[] bArr) {
            return bArr != null && bArr.length > 15 && bArr[13] == 45 && indexOf(bArr, mSeparator) > 14;
        }

        private static String[] getDateInfoFromDate(byte[] bArr) {
            if (hasDateInfo(bArr)) {
                return new String[]{new String(copyOfRange(bArr, 0, 13)), new String(copyOfRange(bArr, 14, indexOf(bArr, mSeparator)))};
            }
            return null;
        }

        private static int indexOf(byte[] bArr, char c) {
            for (int i = 0; i < bArr.length; i++) {
                if (bArr[i] == c) {
                    return i;
                }
            }
            return -1;
        }

        private static byte[] copyOfRange(byte[] bArr, int i, int i2) {
            int i3 = i2 - i;
            if (i3 >= 0) {
                byte[] bArr2 = new byte[i3];
                System.arraycopy(bArr, i, bArr2, 0, Math.min(bArr.length - i, i3));
                return bArr2;
            }
            throw new IllegalArgumentException(i + " > " + i2);
        }

        private static String createDateInfo(int i) {
            String str = System.currentTimeMillis() + "";
            while (str.length() < 13) {
                str = ResultTypeConstant.f7213z + str;
            }
            return str + "-" + i + mSeparator;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static byte[] Bitmap2Bytes(Bitmap bitmap) {
            if (bitmap == null) {
                return null;
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Bitmap Bytes2Bimap(byte[] bArr) {
            if (bArr.length == 0) {
                return null;
            }
            return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Bitmap drawable2Bitmap(Drawable drawable) {
            if (drawable == null) {
                return null;
            }
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
            drawable.draw(canvas);
            return createBitmap;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Drawable bitmap2Drawable(Bitmap bitmap) {
            if (bitmap == null) {
                return null;
            }
            new BitmapDrawable(bitmap).setTargetDensity(bitmap.getDensity());
            return new BitmapDrawable(bitmap);
        }
    }
}
