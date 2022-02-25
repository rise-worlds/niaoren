package com.tendcloud.tenddata;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ea */
/* loaded from: classes2.dex */
public class C2832ea {

    /* renamed from: a */
    private static ConcurrentHashMap f13835a = new ConcurrentHashMap();

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.ea$b */
    /* loaded from: classes2.dex */
    public enum EnumC2834b {
        Cloud_Control_Lock_File("Cloud_Control_Cache_Param"),
        AntiCheating_Switch_Lock_File("AntiCheating_Switch_Value"),
        App_Lock_File("__App_Synchronous_Lock__"),
        Tracking_Lock_File("__Tracking_Synchronous_Lock__"),
        Env_Lock_File("__Env_Synchronous_Lock__"),
        Game_Lock_File("__Game_Synchronous_Lock__"),
        Push_Lock_File("__Push_Synchronous_Lock__"),
        SMS_Lock_File("__SMS_Synchronous_Lock__"),
        EAuth_Lock_File("__EAuth_Synchronous_Lock__"),
        App_SQL_Lock_File("__AppSQL_Synchronous_Lock__"),
        BG_Lock_File("__BG_Synchronous_Lock__"),
        Fintech_Lock_File("__Fintech_Synchronous_Lock__"),
        CSP_Lock_File("__CSP_Synchronous_Lock__"),
        BIO_Touch_Lock_File("__BIO_Touch_Synchronous_Lock__"),
        BIO_Text_Lock_File("__BIO_Text_Synchronous_Lock__"),
        AntiCheating_Data_Lock_File("AntiCheating_Data_Value"),
        Fintech_Data_Lock_File("_Fintech_Data_Lock"),
        AES_DATA_LOCK("_AES_DATA_LOCK"),
        AES_DATA_ENTRYCP_LOCK("_AES_DATA_ENTCRYPT_LOCK"),
        SMS_Model_Lock_File("__SMS_Model_Lock_File"),
        AES_SALT_LOCK("_AES_SALT_LOCK"),
        AES_IV_LOCK("_AES_IV_LOCK");
        
        private final String filePath;

        EnumC2834b(String str) {
            File filesDir = C2664ab.f13513g.getFilesDir();
            this.filePath = new File(filesDir, C2664ab.f13524r + str).getAbsolutePath();
        }

        public static String getFeatureLockFileName(int i) {
            switch (i) {
                case 0:
                    return App_Lock_File.toString();
                case 1:
                    return Tracking_Lock_File.toString();
                case 2:
                    return Env_Lock_File.toString();
                case 3:
                    return Game_Lock_File.toString();
                case 4:
                    return Push_Lock_File.toString();
                case 5:
                    return SMS_Lock_File.toString();
                case 6:
                    return EAuth_Lock_File.toString();
                case 7:
                    return App_SQL_Lock_File.toString();
                case 8:
                    return BG_Lock_File.toString();
                case 9:
                    return Fintech_Lock_File.toString();
                case 10:
                    return CSP_Lock_File.toString();
                case 11:
                case 13:
                default:
                    return null;
                case 12:
                    return BIO_Touch_Lock_File.toString();
                case 14:
                    return BIO_Text_Lock_File.toString();
            }
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.filePath;
        }
    }

    public static void getFileLock(String str) {
        C2833a aVar;
        RandomAccessFile randomAccessFile;
        Lock lock;
        try {
            if (!C2855es.m15791b(str)) {
                File file = new File(str);
                if (f13835a.containsKey(str)) {
                    aVar = (C2833a) f13835a.get(str);
                    randomAccessFile = aVar.randomAccessFile;
                    lock = aVar.threadLock;
                } else {
                    C2833a aVar2 = new C2833a();
                    randomAccessFile = new RandomAccessFile(file, "rw");
                    lock = new ReentrantLock();
                    aVar2.randomAccessFile = randomAccessFile;
                    aVar2.threadLock = lock;
                    f13835a.put(str, aVar2);
                    aVar = aVar2;
                }
                lock.lock();
                aVar.fileLock = randomAccessFile.getChannel().lock();
                return;
            }
            throw new RuntimeException("LockManager Error: filePath can not be null!");
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    public static boolean m15899a(String str) {
        C2833a aVar;
        RandomAccessFile randomAccessFile;
        Lock lock;
        try {
            if (!C2855es.m15791b(str)) {
                File file = new File(str);
                if (f13835a.containsKey(str)) {
                    aVar = (C2833a) f13835a.get(str);
                    randomAccessFile = aVar.randomAccessFile;
                    lock = aVar.threadLock;
                } else {
                    C2833a aVar2 = new C2833a();
                    randomAccessFile = new RandomAccessFile(file, "rw");
                    lock = new ReentrantLock();
                    aVar2.randomAccessFile = randomAccessFile;
                    aVar2.threadLock = lock;
                    f13835a.put(str, aVar2);
                    aVar = aVar2;
                }
                lock.lock();
                aVar.fileLock = randomAccessFile.getChannel().tryLock();
                return aVar.fileLock != null;
            }
            throw new RuntimeException("LockManager Error: filePath can not be null!");
        } catch (Throwable unused) {
            return false;
        }
    }

    public static void releaseFileLock(String str) {
        try {
            if (C2855es.m15791b(str)) {
                throw new RuntimeException("LockManager Error: filePath can not be null!");
            } else if (f13835a.containsKey(str)) {
                C2833a aVar = (C2833a) f13835a.get(str);
                if (aVar.fileLock != null) {
                    aVar.fileLock.release();
                }
                aVar.threadLock.unlock();
            } else {
                throw new RuntimeException("LockManager Error: there is no information about this file in the cache!");
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: b */
    public static RandomAccessFile m15898b(String str) {
        try {
            if (!C2855es.m15791b(str)) {
                File file = new File(str);
                if (f13835a.containsKey(str)) {
                    return ((C2833a) f13835a.get(str)).randomAccessFile;
                }
                C2833a aVar = new C2833a();
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                ReentrantLock reentrantLock = new ReentrantLock();
                aVar.randomAccessFile = randomAccessFile;
                aVar.threadLock = reentrantLock;
                f13835a.put(str, aVar);
                return randomAccessFile;
            }
            throw new RuntimeException("LockManager Error: filePath can not be null!");
        } catch (Throwable unused) {
            return null;
        }
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.ea$a */
    /* loaded from: classes2.dex */
    static class C2833a {
        private FileLock fileLock;
        private RandomAccessFile randomAccessFile;
        private Lock threadLock;

        private C2833a() {
        }
    }
}
