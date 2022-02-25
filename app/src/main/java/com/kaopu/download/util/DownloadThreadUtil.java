package com.kaopu.download.util;

import com.kaopu.download.abst.ADownloadWorker;
import com.kaopu.download.kernel.BaseDownloadInfo;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class DownloadThreadUtil {
    private static final int DEFAULT_DOWNLOAD_SIZE = 5;
    private static ThreadPoolExecutor mDownloadThreadPoolExecutor = new ThreadPoolExecutor(5, 5, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());

    public static void excuteDownload(Runnable runnable) {
        mDownloadThreadPoolExecutor.execute(runnable);
    }

    public static List<Runnable> clearAllDownloadTask() {
        return mDownloadThreadPoolExecutor.shutdownNow();
    }

    public static boolean remove(ADownloadWorker<? extends BaseDownloadInfo> aDownloadWorker) {
        return mDownloadThreadPoolExecutor.remove(aDownloadWorker);
    }
}
