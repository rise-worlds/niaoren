package com.angel.nrzs.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.game.bean.AuthorInfo;
import com.nrzs.data.game.bean.AuthorScriptBean;
import com.nrzs.http.UICallback;
import java.util.ArrayList;
import java.util.List;
import p110z1.AuthorRepository;
import p110z1.BannerManager;

/* loaded from: classes.dex */
public class ChannelFragmentModel extends AndroidViewModel {

    /* renamed from: a */
    private AuthorRepository f5354a;

    /* renamed from: b */
    private MutableLiveData<List<AuthorInfo>> f5355b;

    /* renamed from: c */
    private MutableLiveData<List<AuthorScriptBean>> f5356c;

    /* renamed from: d */
    private UICallback<BaseResponse<AuthorInfo>> f5357d = new UICallback<BaseResponse<AuthorInfo>>() { // from class: com.angel.nrzs.model.ChannelFragmentModel.1
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            ChannelFragmentModel.this.m25053a().setValue(null);
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse<AuthorInfo> baseResponse) {
            if (baseResponse.data != null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(baseResponse.data);
                ChannelFragmentModel.this.m25053a().setValue(arrayList);
            }
        }
    };

    /* renamed from: e */
    private UICallback<BaseResponse<List<AuthorScriptBean>>> f5358e = new UICallback<BaseResponse<List<AuthorScriptBean>>>() { // from class: com.angel.nrzs.model.ChannelFragmentModel.2
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            ChannelFragmentModel.this.m25051b().setValue(null);
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse<List<AuthorScriptBean>> baseResponse) {
            if (baseResponse == null || baseResponse.data == null) {
                ChannelFragmentModel.this.m25051b().setValue(null);
            } else {
                ChannelFragmentModel.this.m25051b().setValue(baseResponse.data);
            }
        }
    };

    public ChannelFragmentModel(@NonNull Application application) {
        super(application);
    }

    /* renamed from: a */
    public void m25052a(long j) {
        BannerManager.m12679a().m12675a(j);
        if (this.f5354a == null) {
            this.f5354a = new AuthorRepository();
        }
        this.f5354a.m12717a(j, this.f5357d);
    }

    /* renamed from: b */
    public void m25050b(long j) {
        if (this.f5354a == null) {
            this.f5354a = new AuthorRepository();
        }
        this.f5354a.m12716b(j, this.f5358e);
    }

    /* renamed from: a */
    public MutableLiveData<List<AuthorInfo>> m25053a() {
        if (this.f5355b == null) {
            this.f5355b = new MutableLiveData<>();
        }
        return this.f5355b;
    }

    /* renamed from: b */
    public MutableLiveData<List<AuthorScriptBean>> m25051b() {
        if (this.f5356c == null) {
            this.f5356c = new MutableLiveData<>();
        }
        return this.f5356c;
    }
}
