package android.support.p003v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.os.Process;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v4.graphics.TypefaceCompatUtil */
/* loaded from: classes.dex */
public class TypefaceCompatUtil {
    private static final String CACHE_FILE_PREFIX = ".font";
    private static final String TAG = "TypefaceCompatUtil";

    private TypefaceCompatUtil() {
    }

    @Nullable
    public static File getTempFile(Context context) {
        String str = CACHE_FILE_PREFIX + Process.myPid() + "-" + Process.myTid() + "-";
        for (int i = 0; i < 100; i++) {
            File file = new File(context.getCacheDir(), str + i);
            if (file.createNewFile()) {
                return file;
            }
        }
        return null;
    }

    @RequiresApi(19)
    @Nullable
    private static ByteBuffer mmap(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            FileChannel channel = fileInputStream.getChannel();
            MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0L, channel.size());
            fileInputStream.close();
            return map;
        } catch (IOException unused) {
            return null;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:14:0x0035
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    @android.support.annotation.RequiresApi(19)
    @android.support.annotation.Nullable
    public static java.nio.ByteBuffer mmap(android.content.Context r8, android.os.CancellationSignal r9, android.net.Uri r10) {
        /*
            android.content.ContentResolver r8 = r8.getContentResolver()
            r0 = 0
            java.lang.String r1 = "r"
            android.os.ParcelFileDescriptor r8 = r8.openFileDescriptor(r10, r1, r9)     // Catch: IOException -> 0x0067
            if (r8 != 0) goto L_0x0013
            if (r8 == 0) goto L_0x0012
            r8.close()     // Catch: IOException -> 0x0067
        L_0x0012:
            return r0
        L_0x0013:
            java.io.FileInputStream r9 = new java.io.FileInputStream     // Catch: Throwable -> 0x0050
            java.io.FileDescriptor r10 = r8.getFileDescriptor()     // Catch: Throwable -> 0x0050
            r9.<init>(r10)     // Catch: Throwable -> 0x0050
            java.nio.channels.FileChannel r1 = r9.getChannel()     // Catch: Throwable -> 0x0038
            long r5 = r1.size()     // Catch: Throwable -> 0x0038
            java.nio.channels.FileChannel$MapMode r2 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch: Throwable -> 0x0038
            r3 = 0
            java.nio.MappedByteBuffer r10 = r1.map(r2, r3, r5)     // Catch: Throwable -> 0x0038
            r9.close()     // Catch: Throwable -> 0x0050
            if (r8 == 0) goto L_0x0034
            r8.close()     // Catch: IOException -> 0x0067
        L_0x0034:
            return r10
        L_0x0035:
            r10 = move-exception
            r1 = r0
            goto L_0x003e
        L_0x0038:
            r10 = move-exception
            throw r10     // Catch: all -> 0x003a
        L_0x003a:
            r1 = move-exception
            r7 = r1
            r1 = r10
            r10 = r7
        L_0x003e:
            if (r1 == 0) goto L_0x0049
            r9.close()     // Catch: Throwable -> 0x0044
            goto L_0x004c
        L_0x0044:
            r9 = move-exception
            r1.addSuppressed(r9)     // Catch: Throwable -> 0x0050
            goto L_0x004c
        L_0x0049:
            r9.close()     // Catch: Throwable -> 0x0050
        L_0x004c:
            throw r10     // Catch: Throwable -> 0x0050
        L_0x004d:
            r9 = move-exception
            r10 = r0
            goto L_0x0056
        L_0x0050:
            r9 = move-exception
            throw r9     // Catch: all -> 0x0052
        L_0x0052:
            r10 = move-exception
            r7 = r10
            r10 = r9
            r9 = r7
        L_0x0056:
            if (r8 == 0) goto L_0x0066
            if (r10 == 0) goto L_0x0063
            r8.close()     // Catch: Throwable -> 0x005e
            goto L_0x0066
        L_0x005e:
            r8 = move-exception
            r10.addSuppressed(r8)     // Catch: IOException -> 0x0067
            goto L_0x0066
        L_0x0063:
            r8.close()     // Catch: IOException -> 0x0067
        L_0x0066:
            throw r9     // Catch: IOException -> 0x0067
        L_0x0067:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v4.graphics.TypefaceCompatUtil.mmap(android.content.Context, android.os.CancellationSignal, android.net.Uri):java.nio.ByteBuffer");
    }

    @RequiresApi(19)
    @Nullable
    public static ByteBuffer copyToDirectBuffer(Context context, Resources resources, int i) {
        File tempFile = getTempFile(context);
        if (tempFile == null) {
            return null;
        }
        try {
            if (!copyToFile(tempFile, resources, i)) {
                return null;
            }
            return mmap(tempFile);
        } finally {
            tempFile.delete();
        }
    }

    public static boolean copyToFile(File file, InputStream inputStream) {
        Throwable th;
        IOException e;
        FileOutputStream fileOutputStream;
        StrictMode.ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(file, false);
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    closeQuietly(fileOutputStream);
                    StrictMode.setThreadPolicy(allowThreadDiskWrites);
                    return true;
                }
            }
        } catch (IOException e3) {
            e = e3;
            fileOutputStream2 = fileOutputStream;
            Log.e(TAG, "Error copying resource contents to temp file: " + e.getMessage());
            closeQuietly(fileOutputStream2);
            StrictMode.setThreadPolicy(allowThreadDiskWrites);
            return false;
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream2 = fileOutputStream;
            closeQuietly(fileOutputStream2);
            StrictMode.setThreadPolicy(allowThreadDiskWrites);
            throw th;
        }
    }

    public static boolean copyToFile(File file, Resources resources, int i) {
        InputStream inputStream;
        Throwable th;
        try {
            inputStream = resources.openRawResource(i);
            try {
                boolean copyToFile = copyToFile(file, inputStream);
                closeQuietly(inputStream);
                return copyToFile;
            } catch (Throwable th2) {
                th = th2;
                closeQuietly(inputStream);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            inputStream = null;
        }
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
