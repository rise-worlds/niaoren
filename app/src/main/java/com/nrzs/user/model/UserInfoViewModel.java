package com.nrzs.user.model;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;
import com.nrzs.data.user.bean.UserInfo;

/* loaded from: classes2.dex */
public class UserInfoViewModel extends AndroidViewModel {

    /* renamed from: a */
    private MutableLiveData<UserInfo> f11349a = new MutableLiveData<>();

    /* renamed from: b */
    private LiveData<UserInfo> f11350b = Transformations.map(this.f11349a, new Function<UserInfo, UserInfo>() { // from class: com.nrzs.user.model.UserInfoViewModel.1
        /* renamed from: a */
        public UserInfo apply(UserInfo userInfo) {
            return userInfo == null ? new UserInfo() : userInfo;
        }
    });

    public UserInfoViewModel(@NonNull Application application) {
        super(application);
    }

    /* renamed from: a */
    public MutableLiveData<UserInfo> m18383a() {
        if (this.f11349a == null) {
            this.f11349a = new MutableLiveData<>();
        }
        return this.f11349a;
    }

    /* renamed from: b */
    public LiveData<UserInfo> m18381b() {
        return this.f11350b;
    }

    /* renamed from: a */
    public void m18382a(String str) {
        UserInfo userInfo = new UserInfo();
        userInfo.UserName = str;
        this.f11349a.setValue(userInfo);
    }
}
