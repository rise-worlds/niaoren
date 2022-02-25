package com.nrzs.user.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import com.nrzs.data.DataApp;
import com.nrzs.data.game.bean.request.TopicListRequestInfo;
import com.nrzs.data.game.bean.response.ScriptInfoResponse;
import com.nrzs.data.game.bean.response.TopicDetailResponseInfo;
import com.nrzs.http.UICallback;
import p110z1.LoginManager;
import p110z1.TopicInfoRepository;

/* loaded from: classes2.dex */
public class GameTopicModel extends AndroidViewModel {

    /* renamed from: a */
    private MutableLiveData<TopicDetailResponseInfo> f11343a;

    /* renamed from: b */
    private MutableLiveData<ScriptInfoResponse> f11344b;

    /* renamed from: c */
    private UICallback f11345c = new UICallback() { // from class: com.nrzs.user.model.GameTopicModel.1
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            GameTopicModel.this.m18387a().setValue(null);
        }

        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3022a(Object obj) {
            TopicDetailResponseInfo topicDetailResponseInfo = (TopicDetailResponseInfo) obj;
            if (topicDetailResponseInfo != null) {
                GameTopicModel.this.m18387a().setValue(topicDetailResponseInfo);
            } else {
                GameTopicModel.this.m18387a().setValue(null);
            }
        }
    };

    /* renamed from: d */
    private UICallback f11346d = new UICallback() { // from class: com.nrzs.user.model.GameTopicModel.2
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            GameTopicModel.this.m18387a().setValue(null);
        }

        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3022a(Object obj) {
            ScriptInfoResponse scriptInfoResponse = (ScriptInfoResponse) obj;
            if (scriptInfoResponse != null) {
                GameTopicModel.this.m18384b().setValue(scriptInfoResponse);
            } else {
                GameTopicModel.this.m18384b().setValue(null);
            }
        }
    };

    public GameTopicModel(@NonNull Application application) {
        super(application);
    }

    /* renamed from: a */
    public void m18386a(int i, int i2, int i3) {
        TopicListRequestInfo topicListRequestInfo = new TopicListRequestInfo();
        topicListRequestInfo.setTopicID(i);
        topicListRequestInfo.setAuthorId(DataApp.m18939d().f10605h);
        if (LoginManager.m12620d().m12630a() == null) {
            topicListRequestInfo.setUserID(-1);
        } else {
            topicListRequestInfo.setUserID((int) LoginManager.m12620d().m12630a().UserID);
        }
        topicListRequestInfo.setPageSize(i3);
        topicListRequestInfo.setCurrentPage(i2);
        try {
            new TopicInfoRepository().m12706a(topicListRequestInfo, this.f11345c);
        } catch (Exception unused) {
        }
    }

    /* renamed from: a */
    public MutableLiveData<TopicDetailResponseInfo> m18387a() {
        if (this.f11343a == null) {
            this.f11343a = new MutableLiveData<>();
        }
        return this.f11343a;
    }

    /* renamed from: a */
    public void m18385a(String str) {
        try {
            new TopicInfoRepository().m12704a(str, this.f11346d);
        } catch (Exception unused) {
        }
    }

    /* renamed from: b */
    public MutableLiveData<ScriptInfoResponse> m18384b() {
        if (this.f11344b == null) {
            this.f11344b = new MutableLiveData<>();
        }
        return this.f11344b;
    }
}
