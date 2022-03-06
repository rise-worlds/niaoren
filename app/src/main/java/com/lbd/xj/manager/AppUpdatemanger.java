package com.lbd.xj.manager;

import com.blankj.utilcode.util.ToastUtils;
import com.common.utils.log.LogUtils;
import com.nrzs.data.xnkj.bean.AppUpdateInfo;
import com.nrzs.data.xnkj.bean.response.XJBaseAppReponse;
import com.nrzs.http.UICallback;
import p110z1.AppUpdateEvent;
import p110z1.EventBus;
import p110z1.UpdateRepository;

/* renamed from: com.lbd.xj.manager.a */
/* loaded from: classes.dex */
public enum AppUpdatemanger {
    INSTANCE;
    
    private UpdateRepository mRepository = new UpdateRepository();

    /* compiled from: AppUpdatemanger.java */
    /* renamed from: com.lbd.xj.manager.a$a */
    /* loaded from: classes.dex */
    public interface AbstractC1501a {
        /* renamed from: a */
        void mo19667a(boolean z);
    }

    AppUpdatemanger() {
    }

    public void checkAppUpdate(final boolean z) {
        this.mRepository.m12505a(0, new UICallback<XJBaseAppReponse<AppUpdateInfo>>() { // from class: com.lbd.xj.manager.a.1
            @Override // com.nrzs.http.UICallback
            /* renamed from: a */
            public void mo3021a(Throwable th) {
                LogUtils.m22034i("UpdateRepository", "msg:" + th.getMessage());
            }

            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo3022a(XJBaseAppReponse<AppUpdateInfo> xJBaseAppReponse) {
                LogUtils.m22034i("UpdateRepository", "onSuccess" + xJBaseAppReponse.toString());
                if (xJBaseAppReponse.Code == 1) {
                    EventBus.m3448a().m3427d(new AppUpdateEvent(xJBaseAppReponse.Data, z));
                } else if (z) {
                    ToastUtils.m23030a("已是最新版本");
                }
            }
        });
    }

    public void isNewVersion(final AbstractC1501a aVar) {
        this.mRepository.m12505a(0, new UICallback<XJBaseAppReponse<AppUpdateInfo>>() { // from class: com.lbd.xj.manager.a.2
            @Override // com.nrzs.http.UICallback
            /* renamed from: a */
            public void mo3021a(Throwable th) {
                LogUtils.m22034i("UpdateRepository", "msg:" + th.getMessage());
                aVar.mo19667a(false);
            }

            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo3022a(XJBaseAppReponse<AppUpdateInfo> xJBaseAppReponse) {
                LogUtils.m22034i("UpdateRepository", "onSuccess" + xJBaseAppReponse.toString());
                if (xJBaseAppReponse.Code != 1 || xJBaseAppReponse.Data == null || xJBaseAppReponse.Data.getVersionCode() <= 135) {
                    aVar.mo19667a(false);
                } else {
                    aVar.mo19667a(true);
                }
            }
        });
    }
}
