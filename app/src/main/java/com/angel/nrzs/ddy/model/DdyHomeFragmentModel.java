package com.angel.nrzs.ddy.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import com.nrzs.data.ddy.bean.request.GetGrouplist;
import com.nrzs.data.ddy.bean.request.Getorderlist;
import com.nrzs.data.ddy.bean.request.MoveGrouplist;
import com.nrzs.data.ddy.bean.respond.GroupInfo;
import com.nrzs.data.ddy.bean.respond.OrderDaileInfo;
import com.nrzs.http.UICallback;
import java.util.List;
import p110z1.DdyRepository;
import p110z1.LoginManager;

/* loaded from: classes.dex */
public class DdyHomeFragmentModel extends AndroidViewModel {

    /* renamed from: c */
    private DdyRepository f5306c;

    /* renamed from: d */
    private MutableLiveData<List<GroupInfo>> f5307d = new MutableLiveData<>();

    /* renamed from: a */
    public MutableLiveData<List<OrderDaileInfo>> f5304a = new MutableLiveData<>();

    /* renamed from: b */
    public MutableLiveData<List<Long>> f5305b = new MutableLiveData<>();

    /* renamed from: e */
    private UICallback<List<OrderDaileInfo>> f5308e = new UICallback<List<OrderDaileInfo>>() { // from class: com.angel.nrzs.ddy.model.DdyHomeFragmentModel.1
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            DdyHomeFragmentModel.this.m25090c().setValue(null);
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(List<OrderDaileInfo> list) {
            DdyHomeFragmentModel.this.m25090c().setValue(list);
        }
    };

    /* renamed from: f */
    private UICallback<List<GroupInfo>> f5309f = new UICallback<List<GroupInfo>>() { // from class: com.angel.nrzs.ddy.model.DdyHomeFragmentModel.2
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            DdyHomeFragmentModel.this.m25089d().setValue(null);
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(List<GroupInfo> list) {
            DdyHomeFragmentModel.this.m25089d().setValue(list);
        }
    };

    /* renamed from: g */
    private UICallback<List<Long>> f5310g = new UICallback<List<Long>>() { // from class: com.angel.nrzs.ddy.model.DdyHomeFragmentModel.3
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            DdyHomeFragmentModel.this.m25088e().setValue(null);
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(List<Long> list) {
            DdyHomeFragmentModel.this.m25088e().setValue(list);
        }
    };

    public DdyHomeFragmentModel(@NonNull Application application) {
        super(application);
    }

    /* renamed from: a */
    public void m25093a() {
        if (this.f5306c == null) {
            this.f5306c = new DdyRepository();
        }
        Getorderlist getorderlist = new Getorderlist();
        getorderlist.UCID = LoginManager.m12620d().m12614j();
        this.f5306c.m12873a(getorderlist, this.f5308e);
    }

    /* renamed from: b */
    public void m25091b() {
        if (this.f5306c == null) {
            this.f5306c = new DdyRepository();
        }
        GetGrouplist getGrouplist = new GetGrouplist();
        getGrouplist.UserId = LoginManager.m12620d().m12614j();
        this.f5306c.m12874a(getGrouplist, this.f5309f);
    }

    /* renamed from: a */
    public void m25092a(String str, Long l) {
        if (this.f5306c == null) {
            this.f5306c = new DdyRepository();
        }
        MoveGrouplist moveGrouplist = new MoveGrouplist();
        moveGrouplist.UserId = LoginManager.m12620d().m12614j();
        moveGrouplist.OrderIds = str;
        moveGrouplist.GroupId = l.longValue();
        this.f5306c.m12872a(moveGrouplist, this.f5310g);
    }

    /* renamed from: c */
    public MutableLiveData<List<OrderDaileInfo>> m25090c() {
        if (this.f5304a == null) {
            this.f5304a = new MutableLiveData<>();
        }
        return this.f5304a;
    }

    /* renamed from: d */
    public MutableLiveData<List<GroupInfo>> m25089d() {
        if (this.f5307d == null) {
            this.f5307d = new MutableLiveData<>();
        }
        return this.f5307d;
    }

    /* renamed from: e */
    public MutableLiveData<List<Long>> m25088e() {
        if (this.f5305b == null) {
            this.f5305b = new MutableLiveData<>();
        }
        return this.f5305b;
    }
}
