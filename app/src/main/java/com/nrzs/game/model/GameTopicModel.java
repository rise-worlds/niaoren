package com.nrzs.game.model;

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
    private MutableLiveData<TopicDetailResponseInfo> f10884a;

    /* renamed from: b */
    private MutableLiveData<ScriptInfoResponse> f10885b;

    /* renamed from: c */
    private UICallback f10886c = new UICallback() { // from class: com.nrzs.game.model.GameTopicModel.1
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            GameTopicModel.this.m18807a().setValue(null);
        }

        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3022a(Object obj) {
            TopicDetailResponseInfo topicDetailResponseInfo = (TopicDetailResponseInfo) obj;
            if (topicDetailResponseInfo != null) {
                GameTopicModel.this.m18807a().setValue(topicDetailResponseInfo);
            } else {
                GameTopicModel.this.m18807a().setValue(null);
            }
        }
    };

    /* renamed from: d */
    private UICallback f10887d = new UICallback() { // from class: com.nrzs.game.model.GameTopicModel.2
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            GameTopicModel.this.m18807a().setValue(null);
        }

        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3022a(Object obj) {
            ScriptInfoResponse scriptInfoResponse = (ScriptInfoResponse) obj;
            if (scriptInfoResponse != null) {
                GameTopicModel.this.m18804b().setValue(scriptInfoResponse);
            } else {
                GameTopicModel.this.m18804b().setValue(null);
            }
        }
    };

    public GameTopicModel(@NonNull Application application) {
        super(application);
    }

    /* renamed from: a */
    public void m18806a(int i, int i2, int i3) {
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
            new TopicInfoRepository().m12706a(topicListRequestInfo, this.f10886c);
        } catch (Exception unused) {
        }
    }

    /* renamed from: a */
    public MutableLiveData<TopicDetailResponseInfo> m18807a() {
        if (this.f10884a == null) {
            this.f10884a = new MutableLiveData<>();
        }
        return this.f10884a;
    }

    /* renamed from: a */
    public void m18805a(String str) {
        try {
            new TopicInfoRepository().m12704a(str, this.f10887d);
        } catch (Exception unused) {
        }
    }

    /* renamed from: b */
    public MutableLiveData<ScriptInfoResponse> m18804b() {
        if (this.f10885b == null) {
            this.f10885b = new MutableLiveData<>();
        }
        return this.f10885b;
    }
}
