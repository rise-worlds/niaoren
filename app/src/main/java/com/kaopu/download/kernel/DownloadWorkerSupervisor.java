package com.kaopu.download.kernel;

import android.util.Log;
import com.kaopu.download.abst.ADownloadWorker;
import com.kaopu.download.util.DownloadThreadUtil;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import org.apache.http.HttpHost;

/* loaded from: classes.dex */
public class DownloadWorkerSupervisor {
    private static final Hashtable<String, ADownloadWorker<? extends BaseDownloadInfo>> works = new Hashtable<>();

    public static boolean add(String str, ADownloadWorker<? extends BaseDownloadInfo> aDownloadWorker) {
        if (!works.containsKey(str)) {
            works.put(str, aDownloadWorker);
            return true;
        }
        Log.e(HttpHost.DEFAULT_SCHEME_NAME, "download mission has been in the queue -> " + str);
        return false;
    }

    public static boolean remove(String str) {
        return works.remove(str) != null;
    }

    public static boolean isDownloading(String str) {
        return works.containsKey(str);
    }

    public static ADownloadWorker<? extends BaseDownloadInfo> getDownloadWorkerById(String str) {
        return works.get(str);
    }

    public static BaseDownloadInfo getDownloadInfo(String str) {
        ADownloadWorker<? extends BaseDownloadInfo> downloadWorkerById = getDownloadWorkerById(str);
        if (downloadWorkerById == null) {
            return null;
        }
        return downloadWorkerById.getBaseDownloadInfo();
    }

    public static Map<String, BaseDownloadInfo> getDownloadInfos() {
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, ADownloadWorker<? extends BaseDownloadInfo>> entry : works.entrySet()) {
            hashMap.put(entry.getKey(), entry.getValue().getBaseDownloadInfo());
        }
        return hashMap;
    }

    public static void clearAllDownloadTask() {
        DownloadThreadUtil.clearAllDownloadTask();
    }

    public static int getTaskCount() {
        return works.size();
    }
}
