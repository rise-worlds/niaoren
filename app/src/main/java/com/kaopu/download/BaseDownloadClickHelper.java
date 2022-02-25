package com.kaopu.download;

import android.content.Context;
import com.kaopu.download.intf.IDownloadClickHelper;
import com.kaopu.download.intf.IDownloadView;
import com.kaopu.download.kernel.BaseDownloadInfo;

/* loaded from: classes.dex */
public abstract class BaseDownloadClickHelper<T extends BaseDownloadInfo> implements IDownloadClickHelper<T> {
    private static final String TAG = "com.kaopu.download.BaseDownloadClickHelper";
    public Context mContext;
    public T mDownloadInfo;
    private IDownloadView<T> mDownloadView;

    @Override // com.kaopu.download.intf.IDownloadClickHelper
    public void onDownloadCancelingClick() {
    }

    @Override // com.kaopu.download.intf.IDownloadClickHelper
    public void onDownloadPausingClick() {
    }

    public BaseDownloadClickHelper(IDownloadView<T> iDownloadView) {
        this.mDownloadView = iDownloadView;
        this.mContext = this.mDownloadView.getContext();
    }

    @Override // com.kaopu.download.intf.IDownloadClickHelper
    public void onDownloadConnectingClick() {
        BaseDownloadOperate.pauseDownloadTask(this.mContext, this.mDownloadInfo);
    }

    @Override // com.kaopu.download.intf.IDownloadClickHelper
    public void onDownloadNewClick() {
        BaseDownloadOperate.addNewDownloadTask(this.mContext, this.mDownloadInfo);
    }

    @Override // com.kaopu.download.intf.IDownloadClickHelper
    public void onDownloadWaitClick() {
        BaseDownloadOperate.pauseDownloadTask(this.mContext, this.mDownloadInfo);
    }

    @Override // com.kaopu.download.intf.IDownloadClickHelper
    public void onDownloadingClick() {
        BaseDownloadOperate.pauseDownloadTask(this.mContext, this.mDownloadInfo);
    }

    @Override // com.kaopu.download.intf.IDownloadClickHelper
    public void onDownloadPausedClick() {
        BaseDownloadOperate.addNewDownloadTask(this.mContext, this.mDownloadInfo);
    }

    @Override // com.kaopu.download.intf.IDownloadClickHelper
    public void onDownloadFailedClick() {
        BaseDownloadOperate.addNewDownloadTask(this.mContext, this.mDownloadInfo);
    }

    @Override // com.kaopu.download.intf.IDownloadClickHelper
    public void onDownloadNoneClick() {
        BaseDownloadOperate.addNewDownloadTask(this.mContext, this.mDownloadInfo);
    }

    @Override // com.kaopu.download.intf.IDownloadClickHelper
    public T getDownloadInfo() {
        return this.mDownloadInfo;
    }

    @Override // com.kaopu.download.intf.IDownloadClickHelper
    public void setDownloadInfo(T t) {
        this.mDownloadInfo = t;
    }
}
