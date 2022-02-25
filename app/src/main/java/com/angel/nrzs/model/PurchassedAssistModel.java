package com.angel.nrzs.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.other.bean.PurchassedAssistinfo;
import com.nrzs.data.other.bean.request.PurchassedAssistRequestInfo;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import java.util.List;
import p110z1.LoginManager;
import p110z1.PurchassedAssistRepository;
import p110z1.TypeToken;
import p110z1.apa;

/* loaded from: classes.dex */
public class PurchassedAssistModel extends AndroidViewModel {

    /* renamed from: a */
    private PurchassedAssistRepository f5366a;

    /* renamed from: b */
    private MutableLiveData<List<PurchassedAssistinfo>> f5367b;

    /* renamed from: c */
    private ThreadCallback<BaseResponse<List<PurchassedAssistinfo>>, String> f5368c = new ThreadCallback<BaseResponse<List<PurchassedAssistinfo>>, String>() { // from class: com.angel.nrzs.model.PurchassedAssistModel.1
        /* renamed from: a */
        public BaseResponse<List<PurchassedAssistinfo>> onResponse(String str) {
            BaseResponse<List<PurchassedAssistinfo>> baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<List<PurchassedAssistinfo>>>() { // from class: com.angel.nrzs.model.PurchassedAssistModel.1.1
            });
            if (baseResponse != null) {
                return baseResponse;
            }
            return null;
        }
    };

    /* renamed from: d */
    private UICallback<BaseResponse<List<PurchassedAssistinfo>>> f5369d = new UICallback<BaseResponse<List<PurchassedAssistinfo>>>() { // from class: com.angel.nrzs.model.PurchassedAssistModel.2
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            Log.d("NetEngin", "test2 onError:" + th.getMessage() + "," + Thread.currentThread().getName());
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse<List<PurchassedAssistinfo>> baseResponse) {
            if (baseResponse != null) {
                PurchassedAssistModel.this.m25043b().setValue(baseResponse.data);
            }
        }
    };

    public PurchassedAssistModel(@NonNull Application application) {
        super(application);
    }

    /* renamed from: a */
    public void m25044a() {
        try {
            if (this.f5366a == null) {
                this.f5366a = new PurchassedAssistRepository();
            }
            PurchassedAssistRequestInfo purchassedAssistRequestInfo = new PurchassedAssistRequestInfo();
            purchassedAssistRequestInfo.UserID = LoginManager.m12620d().m12630a().UserID;
            this.f5366a.m12581a(purchassedAssistRequestInfo, this.f5369d, this.f5368c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    public MutableLiveData<List<PurchassedAssistinfo>> m25043b() {
        if (this.f5367b == null) {
            this.f5367b = new MutableLiveData<>();
        }
        return this.f5367b;
    }
}
