package com.kaopu.download;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import com.kaopu.download.abst.ADownloadWorker;
import com.kaopu.download.kernel.BaseDownloadInfo;
import com.kaopu.download.kernel.DownloadWorkerSupervisor;

/* loaded from: classes.dex */
public class BaseDownloadWorker<T extends BaseDownloadInfo> extends ADownloadWorker<T> {
    public static final String NOTIFY_VIEW_ACTION = "com.kaopu.core.download.BaseDownloadWorker.fw_notify_view_action";
    public static final String NOTIFY_VIEW_ACTION_EXTRA_INFO_KEY = "fw_info_key";
    public static final String NOTIFY_VIEW_ACTION_PERMISSION = "com.android.permission.RECV.DOWNLOAD";
    private DownloadCallBack mCallBack;
    protected Context mContext;
    private long sizeOld;
    private long timeOld;

    /* loaded from: classes.dex */
    public interface DownloadCallBack extends Parcelable {
        void onDownloadCanceled(String str);

        void onDownloadCanceling(String str);

        void onDownloadCompleted(String str, String str2, long j);

        void onDownloadFailed(String str);

        void onDownloadPaused(String str);

        void onDownloadPausing(String str);

        void onDownloadStart(String str, long j);

        void onDownloadWait(String str);

        void onDownloadWorking(String str, long j, long j2, int i);
    }

    public BaseDownloadWorker(Context context, T t) {
        super(t);
        this.mContext = context;
        this.mCallBack = t.getCallBack();
    }

    @Override // com.kaopu.download.abst.ADownloadWorker
    protected void onDownloadCanceled(String str) {
        this.mDownloadInfo.setState(BaseDownloadStateFactory.getDownloadNewState());
        DownloadCallBack downloadCallBack = this.mCallBack;
        if (downloadCallBack != null) {
            downloadCallBack.onDownloadCanceled(str);
        }
        DownloadWorkerSupervisor.remove(str);
        sendNotifyBroadcast();
    }

    @Override // com.kaopu.download.abst.ADownloadWorker
    protected void onDownloadPaused(String str) {
        this.mDownloadInfo.setState(BaseDownloadStateFactory.getDownloadPausedState());
        DownloadCallBack downloadCallBack = this.mCallBack;
        if (downloadCallBack != null) {
            downloadCallBack.onDownloadPaused(str);
        }
        DownloadWorkerSupervisor.remove(str);
        sendNotifyBroadcast();
    }

    @Override // com.kaopu.download.abst.ADownloadWorker
    protected void onDownloadWorking(String str, long j, long j2, int i) {
        this.mDownloadInfo.setState(BaseDownloadStateFactory.getDownloadingState());
        this.mDownloadInfo.setfSize(j);
        this.mDownloadInfo.setdSize(j2);
        if (this.timeOld == 0) {
            this.timeOld = System.currentTimeMillis();
            this.sizeOld = j2;
            this.mDownloadInfo.setSpeed(0L);
        } else {
            this.mDownloadInfo.setSpeed((j2 - this.sizeOld) / (System.currentTimeMillis() - this.timeOld));
        }
        DownloadCallBack downloadCallBack = this.mCallBack;
        if (downloadCallBack != null) {
            downloadCallBack.onDownloadWorking(str, j, j2, i);
        }
        sendNotifyBroadcast();
    }

    @Override // com.kaopu.download.abst.ADownloadWorker
    protected void onDownloadCompleted(String str, String str2, long j) {
        this.mDownloadInfo.setState(BaseDownloadStateFactory.getDownloadedState());
        this.mDownloadInfo.setDownloadedTime(System.currentTimeMillis());
        DownloadCallBack downloadCallBack = this.mCallBack;
        if (downloadCallBack != null) {
            downloadCallBack.onDownloadCompleted(str, str2, j);
        }
        DownloadWorkerSupervisor.remove(str);
        sendNotifyBroadcast();
    }

    @Override // com.kaopu.download.abst.ADownloadWorker
    protected void onDownloadConnecting(String str, long j) {
        this.mDownloadInfo.setState(BaseDownloadStateFactory.getDownloadConnectingState());
        this.mDownloadInfo.setdSize(j);
        if (j == 0) {
            this.mDownloadInfo.setCreateTime(System.currentTimeMillis());
        }
        DownloadCallBack downloadCallBack = this.mCallBack;
        if (downloadCallBack != null) {
            downloadCallBack.onDownloadStart(str, j);
        }
        sendNotifyBroadcast();
    }

    @Override // com.kaopu.download.abst.ADownloadWorker
    protected void onDownloadWait(String str) {
        this.mDownloadInfo.setState(BaseDownloadStateFactory.getDownloadWaitState());
        DownloadCallBack downloadCallBack = this.mCallBack;
        if (downloadCallBack != null) {
            downloadCallBack.onDownloadWait(str);
        }
        sendNotifyBroadcast();
    }

    @Override // com.kaopu.download.abst.ADownloadWorker
    protected void onDownloadPausing(String str) {
        this.mDownloadInfo.setState(BaseDownloadStateFactory.getDownloadPausingState());
        DownloadCallBack downloadCallBack = this.mCallBack;
        if (downloadCallBack != null) {
            downloadCallBack.onDownloadPausing(str);
        }
        sendNotifyBroadcast();
    }

    @Override // com.kaopu.download.abst.ADownloadWorker
    protected void onDownloadCanceling(String str) {
        this.mDownloadInfo.setState(BaseDownloadStateFactory.getDownloadCancelingState());
        DownloadCallBack downloadCallBack = this.mCallBack;
        if (downloadCallBack != null) {
            downloadCallBack.onDownloadCanceling(str);
        }
        sendNotifyBroadcast();
    }

    @Override // com.kaopu.download.abst.ADownloadWorker
    protected void onDownloadFailed(String str) {
        this.mDownloadInfo.setState(BaseDownloadStateFactory.getDownloadFailedState());
        DownloadCallBack downloadCallBack = this.mCallBack;
        if (downloadCallBack != null) {
            downloadCallBack.onDownloadFailed(str);
        }
        DownloadWorkerSupervisor.remove(str);
        sendNotifyBroadcast();
    }

    private void sendNotifyBroadcast() {
        Intent intent = new Intent(NOTIFY_VIEW_ACTION);
        intent.putExtra(NOTIFY_VIEW_ACTION_EXTRA_INFO_KEY, this.mDownloadInfo);
        this.mContext.sendBroadcast(intent, "com.android.permission.RECV.DOWNLOAD");
    }
}
