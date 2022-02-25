package p110z1;

import com.cyjh.p045mq.sdk.MqRunner;
import com.cyjh.p045mq.sdk.MqRunnerLite;
import com.nrzs.data.DataApp;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.p066ft.bean.AssistInfo;
import com.nrzs.data.p066ft.bean.AssistRunTJInfo;
import com.nrzs.data.p066ft.bean.request.AssistTJRequestInfo;
import com.nrzs.http.Api;
import com.nrzs.http.BaseRepository;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;

/* renamed from: z1.akj */
/* loaded from: classes3.dex */
public class AssistRunTJRepository {

    /* renamed from: f */
    private static final Object f16181f = new Object();

    /* renamed from: g */
    private static AssistRunTJRepository f16182g;

    /* renamed from: a */
    private AssistTJRequestInfo f16183a;

    /* renamed from: b */
    private BaseRepository<BaseResponse<AssistRunTJInfo>> f16184b;

    /* renamed from: c */
    private UICallback f16185c;

    /* renamed from: d */
    private ThreadCallback f16186d = new ThreadCallback<BaseResponse<AssistRunTJInfo>, String>() { // from class: z1.akj.1
        /* renamed from: a */
        public BaseResponse<AssistRunTJInfo> onResponse(String str) {
            BaseResponse<AssistRunTJInfo> baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<AssistRunTJInfo>>() { // from class: z1.akj.1.1
            });
            if (baseResponse == null || baseResponse.data == null) {
                return null;
            }
            return baseResponse;
        }
    };

    /* renamed from: e */
    private UICallback f16187e = new UICallback<BaseResponse<AssistRunTJInfo>>() { // from class: z1.akj.2
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse<AssistRunTJInfo> baseResponse) {
            AssistRunTJInfo assistRunTJInfo = baseResponse.data;
            if (AssistRunTJRepository.this.f16183a != null && AssistRunTJRepository.this.f16183a.StartOrStop == 1) {
                AssistRunTJRepository.this.f16183a.SID = assistRunTJInfo.SID;
                MqRunner.getInstance().setSid(AssistRunTJRepository.this.f16183a.SID);
                MqRunnerLite.getInstance().setSid(AssistRunTJRepository.this.f16183a.SID);
                if (AssistRunTJRepository.this.f16185c != null) {
                    AssistRunTJRepository.this.f16185c.mo3022a((UICallback) Long.valueOf(AssistRunTJRepository.this.f16183a.SID));
                }
            }
        }
    };

    /* renamed from: a */
    public void m12783a(UICallback oVar) {
        this.f16185c = oVar;
    }

    /* renamed from: a */
    public void m12784a(AssistInfo assistInfo, int i) {
        this.f16183a = new AssistTJRequestInfo();
        this.f16183a.ScriptAuthorID = assistInfo.ScriptAuthor;
        this.f16183a.ScriptID = assistInfo.ScriptID;
        this.f16183a.TopicID = assistInfo.TopicId;
        this.f16183a.OnlyID = assistInfo.OnlyID;
        this.f16183a.ScriptName = assistInfo.ScriptName;
        this.f16183a.UserID = FtUserManager.m12790g().m12797a();
        AssistTJRequestInfo assistTJRequestInfo = this.f16183a;
        assistTJRequestInfo.StartOrStop = 1;
        assistTJRequestInfo.Key = "32root";
        if (i == 2) {
            assistTJRequestInfo.Key = "32mianroot";
        } else if (i == 3) {
            assistTJRequestInfo.Key = "32xnkj";
        }
        m12778d();
    }

    /* renamed from: a */
    public void m12785a() {
        AssistTJRequestInfo assistTJRequestInfo = this.f16183a;
        if (assistTJRequestInfo != null) {
            assistTJRequestInfo.StartOrStop = 0;
            m12778d();
        }
    }

    /* renamed from: d */
    private void m12778d() {
        if (this.f16184b == null) {
            this.f16184b = new BaseRepository<>();
        }
        try {
            this.f16184b.m18568a(Api.m18586a(this.f16183a.toGetUrl(apf.m11837b(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16587R, "45.158.183.252")))).m18572a(this.f16186d).m18571a(this.f16187e).m18574a(4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    public void m12781b() {
        this.f16183a = null;
    }

    /* renamed from: c */
    public static AssistRunTJRepository m12779c() {
        AssistRunTJRepository akjVar;
        synchronized (f16181f) {
            if (f16182g == null) {
                f16182g = new AssistRunTJRepository();
            }
            akjVar = f16182g;
        }
        return akjVar;
    }
}
