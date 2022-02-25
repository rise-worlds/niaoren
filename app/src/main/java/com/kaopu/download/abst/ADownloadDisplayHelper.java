package com.kaopu.download.abst;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.kaopu.download.BaseDownloadWorker;
import com.kaopu.download.intf.IDownloadDisplayHelper;
import com.kaopu.download.intf.IDownloadView;
import com.kaopu.download.kernel.BaseDownloadInfo;

/* loaded from: classes.dex */
public abstract class ADownloadDisplayHelper<T extends BaseDownloadInfo> implements IDownloadDisplayHelper<T> {
    private Context mContext;
    private IDownloadView<T> mDownloadView;
    private ADownloadDisplayHelper<T>.DownloadViewNotifyBroadcastReceiver mReceiver;

    public ADownloadDisplayHelper(IDownloadView<T> iDownloadView) {
        this.mDownloadView = iDownloadView;
        this.mContext = this.mDownloadView.getContext();
    }

    public ADownloadDisplayHelper<T>.DownloadViewNotifyBroadcastReceiver getReceiver() {
        return this.mReceiver;
    }

    public void setReceiver(ADownloadDisplayHelper<T>.DownloadViewNotifyBroadcastReceiver downloadViewNotifyBroadcastReceiver) {
        this.mReceiver = downloadViewNotifyBroadcastReceiver;
    }

    @Override // com.kaopu.download.intf.IDownloadDisplayHelper
    public void registerDownloadReceiver() {
        IntentFilter intentFilter = new IntentFilter(BaseDownloadWorker.NOTIFY_VIEW_ACTION);
        if (this.mReceiver == null) {
            this.mReceiver = new DownloadViewNotifyBroadcastReceiver();
        }
        this.mContext.registerReceiver(this.mReceiver, intentFilter);
    }

    @Override // com.kaopu.download.intf.IDownloadDisplayHelper
    public void unregisterDownloadReceiver() {
        ADownloadDisplayHelper<T>.DownloadViewNotifyBroadcastReceiver downloadViewNotifyBroadcastReceiver = this.mReceiver;
        if (downloadViewNotifyBroadcastReceiver != null) {
            this.mContext.unregisterReceiver(downloadViewNotifyBroadcastReceiver);
        }
    }

    /* loaded from: classes.dex */
    private class DownloadViewNotifyBroadcastReceiver extends BroadcastReceiver {
        private DownloadViewNotifyBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ADownloadDisplayHelper.this.onReceiveResult(context, intent);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onReceiveResult(Context context, Intent intent) {
        BaseDownloadInfo baseDownloadInfo = (BaseDownloadInfo) intent.getParcelableExtra(BaseDownloadWorker.NOTIFY_VIEW_ACTION_EXTRA_INFO_KEY);
        try {
            if (this.mDownloadView.checkDownloadState(baseDownloadInfo)) {
                this.mDownloadView.setDownloadInfo(baseDownloadInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
