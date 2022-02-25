package com.angel.nrzs.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import com.nrzs.data.base.BaseRequest;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.other.bean.request.GetWelcomeV6RequestInfo;
import com.nrzs.data.other.bean.response.PreSetListResponse;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import p110z1.BannerManager;
import p110z1.ChannelDataManager;
import p110z1.PreSetListManager;
import p110z1.WelcomRepository;

/* loaded from: classes.dex */
public class SplashActivityModel extends AndroidViewModel {

    /* renamed from: a */
    private WelcomRepository f5373a;

    /* renamed from: b */
    private UICallback<BaseResponse<PreSetListResponse>> f5374b = new UICallback<BaseResponse<PreSetListResponse>>() { // from class: com.angel.nrzs.model.SplashActivityModel.1
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse<PreSetListResponse> baseResponse) {
            if (baseResponse != null) {
                PreSetListManager.m12116a().m12115a(baseResponse.data);
            }
        }
    };

    public SplashActivityModel(@NonNull Application application) {
        super(application);
    }

    /* renamed from: a */
    public void m25039a(UICallback oVar, ThreadCallback nVar) {
        if (this.f5373a == null) {
            this.f5373a = new WelcomRepository();
        }
        this.f5373a.m12577a(new GetWelcomeV6RequestInfo(), oVar, nVar);
    }

    /* renamed from: a */
    public void m25040a() {
        if (this.f5373a == null) {
            this.f5373a = new WelcomRepository();
        }
        this.f5373a.m12576a(this.f5374b);
    }

    /* renamed from: b */
    public void m25037b(UICallback oVar, ThreadCallback nVar) {
        if (this.f5373a == null) {
            this.f5373a = new WelcomRepository();
        }
        this.f5373a.m12578a(new BaseRequest(), oVar, nVar);
    }

    /* renamed from: b */
    public void m25038b() {
        ChannelDataManager.m12655a().m12652b();
    }

    /* renamed from: c */
    public void m25036c() {
        BannerManager.m12679a().m12671b();
    }
}
