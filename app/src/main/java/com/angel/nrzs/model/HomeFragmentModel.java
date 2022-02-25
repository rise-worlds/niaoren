package com.angel.nrzs.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import com.nrzs.data.DataApp;
import com.nrzs.data.game.bean.TopicInfo;
import com.nrzs.data.game.bean.request.TopicRequestInfo;
import com.nrzs.http.UICallback;
import java.util.List;
import p110z1.TopicInfoManager;
import p110z1.TopicRepository;

/* loaded from: classes.dex */
public class HomeFragmentModel extends AndroidViewModel {

    /* renamed from: b */
    private TopicRepository f5362b;

    /* renamed from: c */
    private MutableLiveData<List<TopicInfo>> f5363c;

    /* renamed from: a */
    public MutableLiveData<List<TopicInfo>> f5361a = new MutableLiveData<>();

    /* renamed from: d */
    private UICallback<List<TopicInfo>> f5364d = new UICallback<List<TopicInfo>>() { // from class: com.angel.nrzs.model.HomeFragmentModel.1
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            HomeFragmentModel.this.m25046b().setValue(null);
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(List<TopicInfo> list) {
            HomeFragmentModel.this.m25046b().setValue(list);
        }
    };

    public HomeFragmentModel(@NonNull Application application) {
        super(application);
    }

    /* renamed from: a */
    public void m25047a() {
        if (this.f5362b == null) {
            this.f5362b = new TopicRepository();
        }
        TopicRequestInfo topicRequestInfo = new TopicRequestInfo();
        topicRequestInfo.setAuthorId(DataApp.m18939d().f10605h);
        if (TopicInfoManager.m12726c().m12722f() != 0) {
            topicRequestInfo.setmaxId(TopicInfoManager.m12726c().m12722f());
        }
        this.f5362b.m12699a(topicRequestInfo, this.f5364d);
    }

    /* renamed from: b */
    public MutableLiveData<List<TopicInfo>> m25046b() {
        if (this.f5363c == null) {
            this.f5363c = new MutableLiveData<>();
        }
        return this.f5363c;
    }
}
