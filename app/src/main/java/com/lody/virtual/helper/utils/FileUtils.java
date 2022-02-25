package com.lody.virtual.helper.utils;

import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.system.Os;
import android.text.TextUtils;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import p110z1.Consts;

/* loaded from: classes.dex */
public class FileUtils {

    /* loaded from: classes.dex */
    public interface FileMode {
        public static final int MODE_755 = 493;
        public static final int MODE_IRGRP = 32;
        public static final int MODE_IROTH = 4;
        public static final int MODE_IRUSR = 256;
        public static final int MODE_ISGID = 1024;
        public static final int MODE_ISUID = 2048;
        public static final int MODE_ISVTX = 512;
        public static final int MODE_IWGRP = 16;
        public static final int MODE_IWOTH = 2;
        public static final int MODE_IWUSR = 128;
        public static final int MODE_IXGRP = 8;
        public static final int MODE_IXOTH = 1;
        public static final int MODE_IXUSR = 64;
    }

    private static boolean isValidExtFilenameChar(char c) {
        return (c == 0 || c == '/') ? false : true;
    }

    public static String getFilenameExt(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf == -1 ? "" : str.substring(lastIndexOf + 1);
    }

    public static File changeExt(File file, String str) {
        int lastIndexOf;
        String str2;
        String absolutePath = file.getAbsolutePath();
        if (getFilenameExt(absolutePath).equals(str)) {
            return file;
        }
        if (absolutePath.lastIndexOf(Consts.f23430h) > 0) {
            str2 = absolutePath.substring(0, lastIndexOf + 1) + str;
        } else {
            str2 = absolutePath + Consts.f23430h + str;
        }
        return new File(str2);
    }

    public static String readToString(String str) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(str);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = fileInputStream.read();
            if (read == -1) {
                return byteArrayOutputStream.toString();
            }
            byteArrayOutputStream.write(read);
        }
    }

    public static long fileSize(String str) {
        File file = new File(str);
        if (!file.exists()) {
            return 0L;
        }
        return file.length();
    }

    public static void chmod(String str, int i) throws Exception {
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                Os.chmod(str, i);
                return;
            } catch (Exception unused) {
            }
        }
        String str2 = "chmod ";
        if (new File(str).isDirectory()) {
            str2 = str2 + " -R ";
        }
        String format = String.format("%o", Integer.valueOf(i));
        Runtime.getRuntime().exec(str2 + format + ExpandableTextView.f6958c + str).waitFor();
    }

    public static void createSymlink(String str, String str2) throws Exception {
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("ln -s " + str + ExpandableTextView.f6958c + str2).waitFor();
    }

    public static boolean isSymlink(File file) throws IOException {
        if (file != null) {
            if (file.getParent() != null) {
                file = new File(file.getParentFile().getCanonicalFile(), file.getName());
            }
            return !file.getCanonicalFile().equals(file.getAbsoluteFile());
        }
        throw new NullPointerException("File must not be null");
    }

    public static void writeParcelToFile(Parcel parcel, File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(parcel.marshall());
        fileOutputStream.close();
    }

    public static byte[] toByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[100];
        while (true) {
            int read = inputStream.read(bArr, 0, 100);
            if (read <= 0) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    public static int deleteDir(File file) {
        boolean z;
        int i = 0;
        if (file.isDirectory()) {
            try {
                z = isSymlink(file);
            } catch (Exception unused) {
                z = false;
            }
            if (!z) {
                String[] list = file.list();
                int length = list.length;
                int i2 = 0;
                while (i < length) {
                    i2 += deleteDir(new File(file, list[i]));
                    i++;
                }
                i = i2;
            }
        }
        return file.delete() ? i + 1 : i;
    }

    public static int deleteDir(String str) {
        return deleteDir(new File(str));
    }

    public static void writeToFile(InputStream inputStream, File file) throws IOException {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr, 0, 1024);
            if (read != -1) {
                bufferedOutputStream.write(bArr, 0, read);
            } else {
                bufferedOutputStream.close();
                return;
            }
        }
    }

    public static void writeToFile(byte[] bArr, File file) throws IOException {
        FileChannel fileChannel;
        Throwable th;
        ReadableByteChannel readableByteChannel;
        FileOutputStream fileOutputStream = null;
        try {
            readableByteChannel = Channels.newChannel(new ByteArrayInputStream(bArr));
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                try {
                    fileChannel = fileOutputStream2.getChannel();
                    try {
                        fileChannel.transferFrom(readableByteChannel, 0L, bArr.length);
                        fileOutputStream2.close();
                        if (readableByteChannel != null) {
                            readableByteChannel.close();
                        }
                        if (fileChannel != null) {
                            fileChannel.close();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        if (readableByteChannel != null) {
                            readableByteChannel.close();
                        }
                        if (fileChannel != null) {
                            fileChannel.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileChannel = null;
                }
            } catch (Throwable th4) {
                th = th4;
                fileChannel = null;
            }
        } catch (Throwable th5) {
            th = th5;
            fileChannel = null;
            readableByteChannel = null;
        }
    }

    public static void copyFile(File file, File file2) throws IOException {
        Throwable th;
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
                try {
                    FileChannel channel = fileInputStream.getChannel();
                    FileChannel channel2 = fileOutputStream.getChannel();
                    ByteBuffer allocate = ByteBuffer.allocate(1024);
                    while (true) {
                        allocate.clear();
                        if (channel.read(allocate) == -1) {
                            closeQuietly(fileInputStream);
                            closeQuietly(fileOutputStream);
                            return;
                        }
                        allocate.limit(allocate.position());
                        allocate.position(0);
                        channel2.write(allocate);
                    }
                } catch (Throwable th2) {
                    th = th2;
                    closeQuietly(fileInputStream);
                    closeQuietly(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
            }
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
            fileInputStream = null;
        }
    }

    public static void copyFileFromAssets(Context context, String str, File file) throws IOException {
        Throwable th;
        InputStream inputStream;
        FileOutputStream fileOutputStream = null;
        try {
            inputStream = context.getResources().getAssets().open(str);
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read > 0) {
                            fileOutputStream2.write(bArr, 0, read);
                        } else {
                            closeQuietly(inputStream);
                            closeQuietly(fileOutputStream2);
                            return;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream = fileOutputStream2;
                    closeQuietly(inputStream);
                    closeQuietly(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
            inputStream = null;
        }
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }

    public static int peekInt(byte[] bArr, int i, ByteOrder byteOrder) {
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            int i2 = i + 1;
            int i3 = i2 + 1;
            int i4 = (bArr[i] & 255) << 24;
            return (bArr[i3 + 1] & 255) | i4 | ((bArr[i2] & 255) << 16) | ((bArr[i3] & 255) << 8);
        }
        int i5 = i + 1;
        int i6 = i5 + 1;
        int i7 = bArr[i] & 255;
        return ((bArr[i6 + 1] & 255) << 24) | i7 | ((bArr[i5] & 255) << 8) | ((bArr[i6] & 255) << 16);
    }

    public static boolean isValidExtFilename(String str) {
        return str != null && str.equals(buildValidExtFilename(str));
    }

    public static String buildValidExtFilename(String str) {
        if (TextUtils.isEmpty(str) || Consts.f23430h.equals(str) || "..".equals(str)) {
            return "(invalid)";
        }
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (isValidExtFilenameChar(charAt)) {
                sb.append(charAt);
            } else {
                sb.append('_');
            }
        }
        return sb.toString();
    }

    public static boolean isExist(String str) {
        return new File(str).exists();
    }

    public static boolean canRead(String str) {
        return new File(str).canRead();
    }

    /* loaded from: classes.dex */
    public static class FileLock {
        private static FileLock singleton;
        private Map<String, FileLockCount> mRefCountMap = new ConcurrentHashMap();

        public static FileLock getInstance() {
            if (singleton == null) {
                singleton = new FileLock();
            }
            return singleton;
        }

        private int RefCntInc(String str, java.nio.channels.FileLock fileLock, RandomAccessFile randomAccessFile, FileChannel fileChannel) {
            if (this.mRefCountMap.containsKey(str)) {
                FileLockCount fileLockCount = this.mRefCountMap.get(str);
                int i = fileLockCount.mRefCount;
                fileLockCount.mRefCount = i + 1;
                return i;
            }
            this.mRefCountMap.put(str, new FileLockCount(fileLock, 1, randomAccessFile, fileChannel));
            return 1;
        }

        private int RefCntDec(String str) {
            if (!this.mRefCountMap.containsKey(str)) {
                return 0;
            }
            FileLockCount fileLockCount = this.mRefCountMap.get(str);
            int i = fileLockCount.mRefCount - 1;
            fileLockCount.mRefCount = i;
            if (i > 0) {
                return i;
            }
            this.mRefCountMap.remove(str);
            return i;
        }

        public boolean LockExclusive(File file) {
            if (file == null) {
                return false;
            }
            try {
                File file2 = new File(file.getParentFile().getAbsolutePath().concat("/lock"));
                if (!file2.exists()) {
                    file2.createNewFile();
                }
                RandomAccessFile randomAccessFile = new RandomAccessFile(file2.getAbsolutePath(), "rw");
                FileChannel channel = randomAccessFile.getChannel();
                java.nio.channels.FileLock lock = channel.lock();
                if (!lock.isValid()) {
                    return false;
                }
                RefCntInc(file2.getAbsolutePath(), lock, randomAccessFile, channel);
                return true;
            } catch (Exception unused) {
                return false;
            }
        }

        public void unLock(File file) {
            FileLockCount fileLockCount;
            File file2 = new File(file.getParentFile().getAbsolutePath().concat("/lock"));
            if (file2.exists() && this.mRefCountMap.containsKey(file2.getAbsolutePath()) && (fileLockCount = this.mRefCountMap.get(file2.getAbsolutePath())) != null) {
                java.nio.channels.FileLock fileLock = fileLockCount.mFileLock;
                RandomAccessFile randomAccessFile = fileLockCount.fOs;
                FileChannel fileChannel = fileLockCount.fChannel;
                try {
                    if (RefCntDec(file2.getAbsolutePath()) <= 0) {
                        if (fileLock != null && fileLock.isValid()) {
                            fileLock.release();
                        }
                        if (randomAccessFile != null) {
                            randomAccessFile.close();
                        }
                        if (fileChannel != null) {
                            fileChannel.close();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public class FileLockCount {
            FileChannel fChannel;
            RandomAccessFile fOs;
            java.nio.channels.FileLock mFileLock;
            int mRefCount;

            FileLockCount(java.nio.channels.FileLock fileLock, int i, RandomAccessFile randomAccessFile, FileChannel fileChannel) {
                this.mFileLock = fileLock;
                this.mRefCount = i;
                this.fOs = randomAccessFile;
                this.fChannel = fileChannel;
            }
        }
    }
}
