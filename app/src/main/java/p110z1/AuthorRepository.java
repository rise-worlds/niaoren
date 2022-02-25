package p110z1;

import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.game.bean.AuthorInfo;
import com.nrzs.data.game.bean.AuthorPaySetBean;
import com.nrzs.data.game.bean.AuthorScriptBean;
import com.nrzs.data.game.bean.request.AuthorInfoRequstInfo;
import com.nrzs.http.Api;
import com.nrzs.http.BaseRepository;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import java.io.PrintStream;
import java.util.List;

/* renamed from: z1.aks */
/* loaded from: classes3.dex */
public class AuthorRepository {

    /* renamed from: a */
    private BaseRepository<BaseResponse<AuthorInfo>> f16242a;

    /* renamed from: b */
    private ThreadCallback f16243b = new ThreadCallback<BaseResponse<AuthorInfo>, String>() { // from class: z1.aks.1
        /* renamed from: a */
        public BaseResponse<AuthorInfo> onResponse(String str) {
            PrintStream printStream = System.out;
            printStream.println("AuthorInfo  s:" + str);
            BaseResponse<AuthorInfo> baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<AuthorInfo>>() { // from class: z1.aks.1.1
            });
            PrintStream printStream2 = System.out;
            printStream2.println("AuthorInfo:" + baseResponse.data.toString());
            return baseResponse;
        }
    };

    /* renamed from: c */
    private ThreadCallback f16244c = new ThreadCallback<BaseResponse<List<AuthorScriptBean>>, String>() { // from class: z1.aks.2
        /* JADX WARN: Type inference failed for: r0v3, types: [java.util.List, T] */
        /* renamed from: a */
        public BaseResponse<List<AuthorScriptBean>> onResponse(String str) {
            BaseResponse<List<AuthorScriptBean>> baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<List<AuthorScriptBean>>>() { // from class: z1.aks.2.1
            });
            if (!(baseResponse == null || baseResponse.data == null)) {
                baseResponse.data = TopicInfoManager.m12726c().m12731a(baseResponse.data);
            }
            return baseResponse;
        }
    };

    /* renamed from: d */
    private ThreadCallback f16245d = new ThreadCallback<BaseResponse<AuthorPaySetBean>, String>() { // from class: z1.aks.3
        /* renamed from: a */
        public BaseResponse<AuthorPaySetBean> onResponse(String str) {
            return (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<AuthorPaySetBean>>() { // from class: z1.aks.3.1
            });
        }
    };

    /* renamed from: a */
    public void m12717a(long j, UICallback<BaseResponse<AuthorInfo>> oVar) {
        if (this.f16242a == null) {
            this.f16242a = new BaseRepository<>();
        }
        try {
            AuthorInfoRequstInfo authorInfoRequstInfo = new AuthorInfoRequstInfo();
            authorInfoRequstInfo.AuthorId = j;
            this.f16242a.m18568a(Api.m18586a(authorInfoRequstInfo.toGetUrl(HttpVal.f16497M))).m18572a(this.f16243b).m18571a(oVar).m18574a(8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    public void m12716b(long j, UICallback<BaseResponse<List<AuthorScriptBean>>> oVar) {
        if (this.f16242a == null) {
            this.f16242a = new BaseRepository<>();
        }
        try {
            AuthorInfoRequstInfo authorInfoRequstInfo = new AuthorInfoRequstInfo();
            authorInfoRequstInfo.AuthorId = j;
            this.f16242a.m18568a(Api.m18586a(authorInfoRequstInfo.toGetUrl(HttpVal.f16498N))).m18572a(this.f16244c).m18571a(oVar).m18574a(8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m12718a(int i, UICallback<BaseResponse<AuthorPaySetBean>> oVar) {
        if (this.f16242a == null) {
            this.f16242a = new BaseRepository<>();
        }
        try {
            AuthorInfoRequstInfo authorInfoRequstInfo = new AuthorInfoRequstInfo();
            authorInfoRequstInfo.AuthorId = i;
            this.f16242a.m18568a(Api.m18586a(authorInfoRequstInfo.toGetUrl(HttpVal.f16499O))).m18572a(this.f16245d).m18571a(oVar).m18574a(8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
