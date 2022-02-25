package p110z1;

import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.ddy.bean.request.AlterNameInfo;
import com.nrzs.data.ddy.bean.request.CheasseGroup;
import com.nrzs.data.ddy.bean.request.GetDeviteToken;
import com.nrzs.data.ddy.bean.request.GetGrouplist;
import com.nrzs.data.ddy.bean.request.Getorderlist;
import com.nrzs.data.ddy.bean.request.MoveGrouplist;
import com.nrzs.data.ddy.bean.respond.DeviceToken;
import com.nrzs.data.ddy.bean.respond.GroupInfo;
import com.nrzs.data.ddy.bean.respond.OrderDaileInfo;
import com.nrzs.http.Api;
import com.nrzs.http.BaseRepository;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import java.util.List;

/* renamed from: z1.ajq */
/* loaded from: classes3.dex */
public class DdyRepository {

    /* renamed from: a */
    private BaseRepository<List<OrderDaileInfo>> f16078a;

    /* renamed from: b */
    private BaseRepository<List<GroupInfo>> f16079b;

    /* renamed from: c */
    private BaseRepository<DeviceToken> f16080c;

    /* renamed from: d */
    private BaseRepository<BaseResponse<Object>> f16081d;

    /* renamed from: e */
    private ThreadCallback f16082e = new ThreadCallback<List<OrderDaileInfo>, String>() { // from class: z1.ajq.1
        /* renamed from: a */
        public List<OrderDaileInfo> onResponse(String str) {
            BaseResponse baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<List<OrderDaileInfo>>>() { // from class: z1.ajq.1.1
            });
            if (baseResponse == null || baseResponse.data == 0) {
                return null;
            }
            return (List) baseResponse.data;
        }
    };

    /* renamed from: f */
    private ThreadCallback f16083f = new ThreadCallback<List<Long>, String>() { // from class: z1.ajq.2
        /* renamed from: a */
        public List<Long> onResponse(String str) {
            BaseResponse baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<List<Long>>>() { // from class: z1.ajq.2.1
            });
            if (baseResponse == null || baseResponse.data == 0) {
                return null;
            }
            return (List) baseResponse.data;
        }
    };

    /* renamed from: g */
    private ThreadCallback f16084g = new ThreadCallback<List<GroupInfo>, String>() { // from class: z1.ajq.3
        /* renamed from: a */
        public List<GroupInfo> onResponse(String str) {
            BaseResponse baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<List<GroupInfo>>>() { // from class: z1.ajq.3.1
            });
            if (baseResponse == null || baseResponse.data == 0) {
                return null;
            }
            return (List) baseResponse.data;
        }
    };

    /* renamed from: h */
    private ThreadCallback f16085h = new ThreadCallback<DeviceToken, String>() { // from class: z1.ajq.4
        /* renamed from: a */
        public DeviceToken onResponse(String str) {
            BaseResponse baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<DeviceToken>>() { // from class: z1.ajq.4.1
            });
            if (baseResponse.data != 0) {
                return (DeviceToken) baseResponse.data;
            }
            return null;
        }
    };

    /* renamed from: i */
    private ThreadCallback f16086i = new ThreadCallback<BaseResponse<Object>, String>() { // from class: z1.ajq.5
        /* renamed from: a */
        public BaseResponse<Object> onResponse(String str) {
            return (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<Object>>() { // from class: z1.ajq.5.1
            });
        }
    };

    /* renamed from: a */
    public void m12876a(CheasseGroup cheasseGroup, UICallback<BaseResponse<Object>> oVar, ThreadCallback nVar) {
        try {
            if (this.f16078a == null) {
                this.f16078a = new BaseRepository<>();
            }
            this.f16078a.m18568a(Api.m18586a(cheasseGroup.toGetUrl(HttpVal.f16505U))).m18572a(nVar).m18571a(oVar).m18574a(17);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m12873a(Getorderlist getorderlist, UICallback<List<OrderDaileInfo>> oVar) {
        try {
            if (this.f16078a == null) {
                this.f16078a = new BaseRepository<>();
            }
            this.f16078a.m18568a(Api.m18586a(getorderlist.toGetUrl(HttpVal.f16503S))).m18572a(this.f16082e).m18571a(oVar).m18574a(17);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m12874a(GetGrouplist getGrouplist, UICallback<List<GroupInfo>> oVar) {
        try {
            if (this.f16079b == null) {
                this.f16079b = new BaseRepository<>();
            }
            this.f16079b.m18568a(Api.m18586a(getGrouplist.toGetUrl(HttpVal.f16504T))).m18572a(this.f16084g).m18571a(oVar).m18574a(17);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m12872a(MoveGrouplist moveGrouplist, UICallback<List<Long>> oVar) {
        try {
            if (this.f16079b == null) {
                this.f16079b = new BaseRepository<>();
            }
            this.f16079b.m18568a(Api.m18586a(moveGrouplist.toGetUrl(HttpVal.f16506V))).m18572a(this.f16083f).m18571a(oVar).m18574a(17);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m12875a(GetDeviteToken getDeviteToken, UICallback<DeviceToken> oVar) {
        try {
            if (this.f16080c == null) {
                this.f16080c = new BaseRepository<>();
            }
            this.f16080c.m18568a(Api.m18586a(getDeviteToken.toGetUrl(HttpVal.f16507W))).m18572a(this.f16085h).m18571a(oVar).m18574a(17);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m12877a(AlterNameInfo alterNameInfo, UICallback<BaseResponse<Object>> oVar) {
        try {
            if (this.f16081d == null) {
                this.f16081d = new BaseRepository<>();
            }
            this.f16081d.m18568a(Api.m18586a(alterNameInfo.toGetUrl(HttpVal.f16508X))).m18572a(this.f16086i).m18571a(oVar).m18574a(17);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m12878a() {
        if (this.f16078a != null) {
            this.f16078a = null;
        }
    }
}
