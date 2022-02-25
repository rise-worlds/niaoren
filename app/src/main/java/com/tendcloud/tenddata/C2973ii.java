package com.tendcloud.tenddata;

import android.text.TextUtils;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.zip.CRC32;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ii */
/* loaded from: classes2.dex */
public class C2973ii {

    /* renamed from: a */
    private static final int f14249a = 25;

    /* renamed from: b */
    private static C2973ii f14250b;

    /* renamed from: c */
    private ExecutorService f14251c;

    /* renamed from: e */
    private CRC32 f14253e;

    /* renamed from: f */
    private Map f14254f;

    /* renamed from: g */
    private Map f14255g;

    /* renamed from: h */
    private Lock f14256h = new ReentrantLock();

    /* renamed from: d */
    private HashMap f14252d = new HashMap();

    /* renamed from: a */
    public static C2973ii m15429a() {
        synchronized (C2973ii.class) {
            if (f14250b == null) {
                f14250b = new C2973ii();
            }
        }
        return f14250b;
    }

    private C2973ii() {
        m15419c();
        for (AbstractC2790d dVar : AbstractC2790d.values()) {
            this.f14252d.put(Integer.valueOf(dVar.index()), new TreeSet());
        }
        this.f14251c = Executors.newSingleThreadExecutor();
        this.f14253e = new CRC32();
    }

    /* renamed from: c */
    private void m15419c() {
        AbstractC2790d[] values;
        this.f14254f = new HashMap();
        this.f14255g = new HashMap();
        try {
            for (AbstractC2790d dVar : AbstractC2790d.values()) {
                File file = new File(C2664ab.f13513g.getFilesDir(), dVar.getRootFolder());
                File file2 = new File(file, dVar.getDataFolder());
                if (file2.exists() || file2.mkdirs()) {
                    this.f14254f.put(Integer.valueOf(dVar.index()), new RandomAccessFile(new File(file, "Lock" + dVar.index()), "rw"));
                } else {
                    return;
                }
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: b */
    public void m15421b() {
        File filesDir = C2664ab.f13513g.getFilesDir();
        try {
            int i = 0;
            for (AbstractC2790d dVar : AbstractC2790d.values()) {
                File file = new File(filesDir, dVar.getDataFolder());
                if (file.exists()) {
                    for (File file2 : m15418c(file)) {
                        file2.delete();
                    }
                }
            }
            AbstractC2790d[] values = AbstractC2790d.values();
            int length = values.length;
            while (i < length) {
                AbstractC2790d dVar2 = values[i];
                File file3 = new File(filesDir, dVar2.getRootFolder());
                File file4 = new File(file3, dVar2.getDataFolder());
                if (file4.exists()) {
                    for (File file5 : m15418c(file4)) {
                        file5.delete();
                    }
                }
                i++;
                filesDir = file3;
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    public void getFileLock(AbstractC2790d dVar) {
        try {
            this.f14256h.lock();
            this.f14255g.put(Integer.valueOf(dVar.index()), ((RandomAccessFile) this.f14254f.get(Integer.valueOf(dVar.index()))).getChannel().lock());
        } catch (Throwable unused) {
        }
    }

    public void releaseFileLock(AbstractC2790d dVar) {
        try {
            if (this.f14255g.get(Integer.valueOf(dVar.index())) != null) {
                ((FileLock) this.f14255g.get(Integer.valueOf(dVar.index()))).release();
                this.f14256h.unlock();
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m15422a(File file, AbstractC2790d dVar) {
        try {
            switch (dVar.getFileLimitType()) {
                case 1:
                    if (m15420b(file) > 25) {
                        m15423a(file);
                        break;
                    }
                    break;
                case 2:
                    if (file.listFiles().length >= 10) {
                        m15423a(file);
                        break;
                    }
                    break;
                case 3:
                    m15423a(file);
                    break;
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: a */
    private void m15423a(File file) {
        try {
            if (file.isDirectory()) {
                m15423a((File) m15418c(file).get(0));
            } else {
                file.delete();
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: b */
    private int m15420b(File file) {
        File[] listFiles;
        if (file == null) {
            return 0;
        }
        try {
            if (!(!file.isDirectory() || (listFiles = file.listFiles()) == null || listFiles.length == 0)) {
                long j = 0;
                for (File file2 : listFiles) {
                    if (file2.isFile()) {
                        j += file2.length();
                    }
                }
                return (int) (j / 1048576);
            }
            return 0;
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return 0;
        }
    }

    /* renamed from: c */
    private List m15418c(File file) {
        List arrayList = new ArrayList();
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return arrayList;
        }
        try {
            arrayList = Arrays.asList(listFiles);
            Collections.sort(arrayList, new C2977ij(this));
            return arrayList;
        } catch (Throwable unused) {
            return arrayList;
        }
    }

    /* renamed from: a */
    public synchronized void m15426a(C2970ih ihVar, C2947ho hoVar) {
        this.f14251c.execute(new RunnableC2976c(this, ihVar, hoVar, null));
    }

    /* renamed from: a */
    public synchronized void m15427a(C2970ih ihVar, AbstractC2790d dVar) {
        if (!(dVar == null || ihVar == null)) {
            try {
                ((TreeSet) this.f14252d.get(Integer.valueOf(dVar.index()))).add(ihVar);
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:49:0x0105
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* renamed from: a */
    public synchronized java.util.TreeSet m15428a(com.tendcloud.tenddata.AbstractC2790d r19, int r20, java.lang.String r21) {
        /*
            Method dump skipped, instructions count: 381
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tendcloud.tenddata.C2973ii.m15428a(com.tendcloud.tenddata.d, int, java.lang.String):java.util.TreeSet");
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.ii$c */
    /* loaded from: classes2.dex */
    class RunnableC2976c implements Runnable {
        private AbstractC2978ik callback;
        private AbstractC2790d features;
        private String mFolderPath;
        private final C2970ih mOperation;

        /* synthetic */ RunnableC2976c(C2973ii iiVar, C2970ih ihVar, C2947ho hoVar, C2977ij ijVar) {
            this(ihVar, hoVar);
        }

        private RunnableC2976c(C2970ih ihVar, C2947ho hoVar) {
            File filesDir = C2664ab.f13513g.getFilesDir();
            this.mFolderPath = filesDir.toString() + File.separator + hoVar.f14180a.getDataFolder();
            this.mFolderPath = filesDir.toString() + File.separator + hoVar.f14180a.getRootFolder() + File.separator + hoVar.f14180a.getDataFolder();
            this.mOperation = ihVar;
            this.callback = hoVar.f14185f;
            this.features = hoVar.f14180a;
        }

        @Override // java.lang.Runnable
        public void run() {
            Throwable th;
            RandomAccessFile randomAccessFile = null;
            try {
                try {
                    File file = new File(this.mFolderPath);
                    if (file.exists() || file.isDirectory() || file.mkdirs()) {
                        C2973ii.this.m15422a(file, this.features);
                        File file2 = new File(this.mFolderPath + File.separator + this.mOperation.m15435b());
                        if (file2.exists() || file2.createNewFile()) {
                            RandomAccessFile randomAccessFile2 = new RandomAccessFile(file2, "rw");
                            try {
                                randomAccessFile2.seek(1L);
                                randomAccessFile2.writeInt(this.mOperation.m15431d());
                                randomAccessFile2.writeInt(this.mOperation.m15430e());
                                randomAccessFile2.write(this.mOperation.m15433c());
                                randomAccessFile2.getFD().sync();
                                if (this.callback != null) {
                                    this.callback.onStoreSuccess();
                                }
                                randomAccessFile2.close();
                            } catch (Throwable th2) {
                                th = th2;
                                randomAccessFile = randomAccessFile2;
                                try {
                                    if (this.callback != null) {
                                        this.callback.onStoreFailed();
                                    }
                                    C2933hb.postSDKError(th);
                                    if (randomAccessFile != null) {
                                        randomAccessFile.close();
                                    }
                                } catch (Throwable th3) {
                                    if (randomAccessFile != null) {
                                        try {
                                            randomAccessFile.close();
                                        } catch (Throwable unused) {
                                        }
                                    }
                                    throw th3;
                                }
                            }
                        }
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            } catch (Throwable unused2) {
            }
        }
    }

    public void confirmRead(AbstractC2790d dVar) {
        new RunnableC2974a(this, dVar, null).run();
    }

    public void clearDataCache(AbstractC2790d dVar) {
        try {
            ((TreeSet) this.f14252d.get(Integer.valueOf(dVar.index()))).clear();
        } catch (Throwable unused) {
        }
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.ii$a */
    /* loaded from: classes2.dex */
    class RunnableC2974a implements Runnable {
        private final String mFolderPath;
        private final String mNewFolder;
        private final TreeSet mQueue;

        /* synthetic */ RunnableC2974a(C2973ii iiVar, AbstractC2790d dVar, C2977ij ijVar) {
            this(dVar);
        }

        private RunnableC2974a(AbstractC2790d dVar) {
            this.mFolderPath = C2664ab.f13513g.getFilesDir() + File.separator + dVar.getDataFolder();
            this.mNewFolder = C2664ab.f13513g.getFilesDir() + File.separator + dVar.getRootFolder() + File.separator + dVar.getDataFolder();
            this.mQueue = (TreeSet) C2973ii.this.f14252d.get(Integer.valueOf(dVar.index()));
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (this.mQueue != null) {
                    this.mQueue.isEmpty();
                    while (!this.mQueue.isEmpty()) {
                        C2970ih ihVar = (C2970ih) this.mQueue.pollFirst();
                        if (ihVar != null) {
                            File file = new File(this.mFolderPath);
                            if (file.exists()) {
                                deleteFile(file.getAbsolutePath(), ihVar);
                            }
                            File file2 = new File(this.mNewFolder);
                            if (file2.exists()) {
                                deleteFile(file2.getAbsolutePath(), ihVar);
                            }
                        }
                    }
                    this.mQueue.clear();
                }
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }

        private void deleteFile(String str, C2970ih ihVar) {
            File file = new File(str + File.separator + ihVar.m15435b());
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.ii$b */
    /* loaded from: classes2.dex */
    class RunnableC2975b implements Runnable {
        private final String mFolderPath;
        private final C2970ih mOperation;

        /* synthetic */ RunnableC2975b(C2973ii iiVar, C2970ih ihVar, AbstractC2790d dVar, String str, C2977ij ijVar) {
            this(ihVar, dVar, str);
        }

        private RunnableC2975b(C2970ih ihVar, AbstractC2790d dVar, String str) {
            String absolutePath = C2664ab.f13513g.getFilesDir().getAbsolutePath();
            if (!TextUtils.isEmpty(str)) {
                absolutePath = absolutePath + File.separator + str;
            }
            this.mFolderPath = absolutePath + File.separator + dVar.getDataFolder();
            this.mOperation = ihVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                File file = new File(this.mFolderPath + File.separator + this.mOperation.m15435b());
                if (file.exists()) {
                    file.delete();
                }
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }
    }
}
