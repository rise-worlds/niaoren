package com.kaopu.download;

import android.content.Context;
import com.kaopu.download.kernel.BaseDownloadInfo;
import com.kaopu.download.kernel.DownloadServiceConnection;
import com.kaopu.download.util.DownloadObjectUtil;
import java.util.Map;

/* loaded from: classes.dex */
public class BaseDownloadOperate {
    public static void addNewDownloadTask(Context context, BaseDownloadInfo baseDownloadInfo) {
        new DownloadServiceConnection(context).addDownloadTask(DownloadObjectUtil.object2Map(baseDownloadInfo));
    }

    public static void pauseDownloadTask(Context context, BaseDownloadInfo baseDownloadInfo) {
        new DownloadServiceConnection(context).pause(DownloadObjectUtil.object2Map(baseDownloadInfo));
    }

    public static void cancelDownloadTask(Context context, BaseDownloadInfo baseDownloadInfo) {
        new DownloadServiceConnection(context).cancel(DownloadObjectUtil.object2Map(baseDownloadInfo));
    }

    public static BaseDownloadInfo getDownloadInfo(Context context, String str) {
        Map downloadInfo = new DownloadServiceConnection(context).getDownloadInfo(str);
        if (downloadInfo == null) {
            return null;
        }
        return DownloadObjectUtil.map2DownloadInfo(downloadInfo);
    }

    public static boolean isBind(Context context, String str) {
        return new DownloadServiceConnection(context).isBind();
    }

    public static void clearAllDownloadTask(Context context) {
        new DownloadServiceConnection(context).clearAllDownloadTask();
    }

    public static Map getDownloadInfos(Context context) {
        return new DownloadServiceConnection(context).getDownloadInfos();
    }

    public static int getTaskCount(Context context) {
        return new DownloadServiceConnection(context).getTaskCount();
    }

    public static boolean continueDownload(Context context, BaseDownloadInfo baseDownloadInfo) {
        return new DownloadServiceConnection(context).continueDownload(DownloadObjectUtil.object2Map(baseDownloadInfo));
    }
}
