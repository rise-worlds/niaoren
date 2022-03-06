package com.lbd.xj.manager;

import android.text.TextUtils;
import com.blankj.utilcode.util.FileUtils;
import com.common.utils.log.LogUtils;
import com.kaopu.download.BaseDownloadOperate;
import com.kaopu.download.kernel.BaseDownloadInfo;
import com.lbd.xj.app.XJApp;
import com.lbd.xj.downloads.callback.FileDownloadCallback;
import com.lbd.xj.manager.launch.BoxLaunchManager;
import com.nrzs.data.xnkj.bean.AppUpdateInfo;
import com.nrzs.data.xnkj.bean.FeedTagInfo;
import com.nrzs.data.xnkj.bean.response.XJBaseAppReponse;
import com.nrzs.http.UICallback;
import java.io.File;
import p110z1.NRZSFileConfig;
import p110z1.ObjectCallback;
import p110z1.PreSetRepository;
import p110z1.PreferencesUtil;
import p110z1.SharepreferenceUtil;
import p110z1.UpdateRepository;
import p110z1.VersionHelper;
import p110z1.acf;

/* renamed from: com.lbd.xj.manager.d */
/* loaded from: classes.dex */
public enum XnkjPluginManager {
    INSTANCE;
    
    public static final int ROM_EXISTS = 1;
    public static final int ROM_UNDOWNLOAD = 0;
    public static final int ROM_UNZIP = 2;
    public static File rootFile = new File(XJApp.getInstance().getApplicationInfo().dataDir, "osimg");
    private AppUpdateInfo mAppUpdateInfo;
    private String xnkjDownId;

    public static XnkjPluginManager getInstance() {
        return INSTANCE;
    }

    public boolean isRomDownload() {
        String b = SharepreferenceUtil.m13882b(acf.f15178c, "");
        return !TextUtils.isEmpty(b) && FileUtils.m22229b(b);
    }

    public String getXnkjDownId() {
        return this.xnkjDownId;
    }

    public void setXnkjDownId(String str) {
        this.xnkjDownId = str;
    }

    public int checkRomState() {
        if (BoxLaunchManager.isRomExists()) {
            return 1;
        }
        return isRomDownload() ? 2 : 0;
    }

    public AppUpdateInfo getmAppUpdateInfo() {
        return this.mAppUpdateInfo;
    }

    public void startDownloadRom(final ObjectCallback<BaseDownloadInfo> acxVar) {
        preSet(new ObjectCallback() { // from class: com.lbd.xj.manager.-$$Lambda$d$sMDd3aOxAgv6EGcC7gLBzgS4RVk
            @Override // p110z1.ObjectCallback
            public final void call(Object obj) {
                XnkjPluginManager.lambda$startDownloadRom$0(XnkjPluginManager.this, acxVar, (AppUpdateInfo) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$startDownloadRom$0(XnkjPluginManager dVar, ObjectCallback acxVar, AppUpdateInfo appUpdateInfo) {
        if (appUpdateInfo != null) {
            BaseDownloadInfo addDownloadTask = dVar.addDownloadTask(NRZSFileConfig.f16561s, appUpdateInfo.getInstallUrl());
            dVar.xnkjDownId = addDownloadTask.getIdentification();
            if (acxVar != null) {
                acxVar.call(addDownloadTask);
            }
        } else if (acxVar != null) {
            acxVar.call(null);
        }
    }

    public void startPatchloadRom(AppUpdateInfo appUpdateInfo, ObjectCallback<BaseDownloadInfo> acxVar) {
        BaseDownloadInfo addDownloadTask = addDownloadTask(NRZSFileConfig.f16562t, appUpdateInfo.getInstallUrl());
        if (acxVar != null) {
            acxVar.call(addDownloadTask);
        }
    }

    private BaseDownloadInfo addDownloadTask(String str, String str2) {
        BaseDownloadInfo downloadInfo = BaseDownloadOperate.getDownloadInfo(XJApp.getInstance().getApplicationContext(), str2);
        if (downloadInfo == null) {
            downloadInfo = createDownloadInfo(str, str2);
            if (isDownload(downloadInfo)) {
                addNewDownloadTask(downloadInfo);
            }
        }
        return downloadInfo;
    }

    private BaseDownloadInfo createDownloadInfo(String str, String str2) {
        BaseDownloadInfo baseDownloadInfo = new BaseDownloadInfo();
        baseDownloadInfo.setIdentification(str2);
        baseDownloadInfo.setSaveDir(str);
        String substring = str2.substring(str2.lastIndexOf("/") + 1);
        if (TextUtils.isEmpty(substring)) {
            substring = "roots.zip";
        }
        baseDownloadInfo.setSaveName(substring);
        baseDownloadInfo.setUrl(str2);
        baseDownloadInfo.setCallBack(new FileDownloadCallback());
        return baseDownloadInfo;
    }

    private void addNewDownloadTask(BaseDownloadInfo baseDownloadInfo) {
        BaseDownloadOperate.addNewDownloadTask(XJApp.getInstance().getApplicationContext(), baseDownloadInfo);
    }

    private boolean isDownload(BaseDownloadInfo baseDownloadInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append(baseDownloadInfo.getSaveDir());
        sb.append(baseDownloadInfo.getSaveName());
        return !FileUtils.m22229b(sb.toString());
    }

    public void updateVersionRom(final ObjectCallback<AppUpdateInfo> acxVar) {
        int a = PreferencesUtil.m13937a().m13933a(acf.f15181f, 1);
        if (a == 1) {
            a = VersionHelper.m13827c().f15480b;
        }
        UpdateRepository amnVar = new UpdateRepository();
        LogUtils.m22034i("updateVersionRom", "versionCode:" + a);
        amnVar.m12505a(a, new UICallback<XJBaseAppReponse<AppUpdateInfo>>() { // from class: com.lbd.xj.manager.d.1
            @Override // com.nrzs.http.UICallback
            /* renamed from: a */
            public void mo3021a(Throwable th) {
                LogUtils.m22034i("UpdateRepository", "msg:" + th.getMessage());
                acxVar.call(null);
            }

            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo3022a(XJBaseAppReponse<AppUpdateInfo> xJBaseAppReponse) {
                LogUtils.m22034i("UpdateRepository", "onSuccess" + xJBaseAppReponse.toString());
                if (xJBaseAppReponse.Code == 1) {
                    acxVar.call(xJBaseAppReponse.Data);
                } else {
                    acxVar.call(null);
                }
            }
        });
    }

    private void preSet(final ObjectCallback<AppUpdateInfo> acxVar) {
        AppUpdateInfo appUpdateInfo = this.mAppUpdateInfo;
        if (appUpdateInfo != null) {
            acxVar.call(appUpdateInfo);
        } else {
            new PreSetRepository().m12507a(new UICallback<XJBaseAppReponse<FeedTagInfo>>() { // from class: com.lbd.xj.manager.d.2
                @Override // com.nrzs.http.UICallback
                /* renamed from: a */
                public void mo3021a(Throwable th) {
                    acxVar.call(null);
                }

                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo3022a(XJBaseAppReponse<FeedTagInfo> xJBaseAppReponse) {
                    if (xJBaseAppReponse != null && xJBaseAppReponse.Data != null) {
                        XnkjPluginManager.this.mAppUpdateInfo = xJBaseAppReponse.Data.getFullAppUpdateResult();
                        com.blankj.utilcode.util.LogUtils.m23734c("getFullAppUpdateResult", xJBaseAppReponse.Data.getFullAppUpdateResult().toString());
                        acxVar.call(XnkjPluginManager.this.mAppUpdateInfo);
                    }
                }
            });
        }
    }

    public void saveDownloadComplete(String str) {
        LogUtils.m22037e("path:" + str);
        SharepreferenceUtil.m13891a(acf.f15178c, str);
        SharepreferenceUtil.m13894a(acf.f15179d, this.mAppUpdateInfo.getVersionCode());
    }
}
