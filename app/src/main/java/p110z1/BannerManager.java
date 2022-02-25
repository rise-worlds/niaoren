package p110z1;

import android.support.annotation.Nullable;
import android.util.Log;
import com.blankj.utilcode.util.SPUtils;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.database.AppDatabase;
import com.nrzs.data.other.bean.AdResultInfoItem;
import com.nrzs.data.other.bean.request.GetWelcomeV6RequestInfo;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import p110z1.ADStatueEvent;

/* renamed from: z1.akz */
/* loaded from: classes3.dex */
public class BannerManager {

    /* renamed from: a */
    public static BannerManager f16296a;

    /* renamed from: c */
    private List<AdResultInfoItem> f16298c;

    /* renamed from: b */
    private ADRepository f16297b = null;

    /* renamed from: d */
    private ThreadCallback<List<AdResultInfoItem>, String> f16299d = new ThreadCallback<List<AdResultInfoItem>, String>() { // from class: z1.akz.1
        /* renamed from: a */
        public List<AdResultInfoItem> onResponse(String str) {
            BaseResponse baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<List<AdResultInfoItem>>>() { // from class: z1.akz.1.1
            });
            if (baseResponse == null) {
                return null;
            }
            BannerManager.this.m12674a((List) baseResponse.data);
            return (List) baseResponse.data;
        }
    };

    /* renamed from: e */
    private UICallback<List<AdResultInfoItem>> f16300e = new UICallback<List<AdResultInfoItem>>() { // from class: z1.akz.2
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            Log.d("NetEngin", "test2 onError:" + th.getMessage() + "," + Thread.currentThread().getName());
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(List<AdResultInfoItem> list) {
            if (list != null) {
                BannerManager.this.f16298c = list;
            }
        }
    };

    /* renamed from: a */
    public static BannerManager m12679a() {
        if (f16296a == null) {
            f16296a = new BannerManager();
        }
        return f16296a;
    }

    /* renamed from: a */
    public void m12674a(List<AdResultInfoItem> list) {
        if (list != null && list.size() > 0) {
            BannerInfoDao b = AppDatabase.m18933e().mo18929b();
            b.mo12760b();
            b.mo12761a(list);
        }
        EventBus.m3448a().m3427d(new ADStatueEvent.C3550a());
    }

    /* renamed from: b */
    public void m12671b() {
        m12675a(0L);
    }

    /* renamed from: a */
    public void m12675a(long j) {
        try {
            if (this.f16297b == null) {
                this.f16297b = new ADRepository();
            }
            GetWelcomeV6RequestInfo getWelcomeV6RequestInfo = new GetWelcomeV6RequestInfo();
            getWelcomeV6RequestInfo.AuthorId = j;
            this.f16297b.m12585a(getWelcomeV6RequestInfo, null, this.f16299d);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Nullable
    /* renamed from: a */
    public AdResultInfoItem m12678a(int i) {
        if (this.f16298c == null) {
            Gson oxVar = new Gson();
            String b = SPUtils.m23341a().m23320b(ShareVal.f16605o, "");
            if (!b.equals("")) {
                this.f16298c = (List) oxVar.m1588a(b, new TypeToken<List<AdResultInfoItem>>() { // from class: z1.akz.3
                }.getType());
            }
        }
        AdResultInfoItem adResultInfoItem = null;
        List<AdResultInfoItem> list = this.f16298c;
        if (list != null) {
            for (AdResultInfoItem adResultInfoItem2 : list) {
                if (adResultInfoItem2.AdPosition == i) {
                    adResultInfoItem = adResultInfoItem2;
                }
            }
        }
        return adResultInfoItem;
    }

    /* renamed from: a */
    public void m12672a(DoneCallback<List<AdResultInfoItem>> daqVar) {
        m12677a(2, daqVar);
    }

    /* renamed from: b */
    public void m12668b(DoneCallback<List<AdResultInfoItem>> daqVar) {
        m12677a(6, daqVar);
    }

    /* renamed from: c */
    public void m12665c(DoneCallback<List<AdResultInfoItem>> daqVar) {
        m12677a(1, daqVar);
    }

    /* renamed from: a */
    public void m12677a(final int i, DoneCallback<List<AdResultInfoItem>> daqVar) {
        VUiKit.m11713a().mo3332a(new Callable() { // from class: z1.-$$Lambda$akz$RD-jgxB9ZAt0aETjlgr4qKNeyQw
            @Override // java.util.concurrent.Callable
            public final Object call() {
                List e;
                e = BannerManager.m12662e(i);
                return e;
            }
        }).mo3282b(daqVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public static /* synthetic */ List m12662e(int i) throws Exception {
        return AppDatabase.m18933e().mo18929b().mo12764a(i);
    }

    /* renamed from: b */
    public void m12669b(final int i, final DoneCallback<AdResultInfoItem> daqVar) {
        VUiKit.m11713a().mo3332a(new Callable() { // from class: z1.-$$Lambda$akz$K0qLZDPKdE199EY511ttr0PTWTI
            @Override // java.util.concurrent.Callable
            public final Object call() {
                List d;
                d = BannerManager.m12664d(i);
                return d;
            }
        }).mo3282b(new DoneCallback() { // from class: z1.-$$Lambda$akz$K4CtMoXxClsu7ifzTl-KRj0ZDxY
            @Override // p110z1.DoneCallback
            public final void onDone(Object obj) {
                BannerManager.m12676a(i, daqVar, (List) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public static /* synthetic */ List m12664d(int i) throws Exception {
        return AppDatabase.m18933e().mo18929b().mo12764a(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public static /* synthetic */ void m12676a(int i, DoneCallback daqVar, List list) {
        Iterator it = list.iterator();
        AdResultInfoItem adResultInfoItem = null;
        while (it.hasNext()) {
            AdResultInfoItem adResultInfoItem2 = (AdResultInfoItem) it.next();
            if (adResultInfoItem2.AdPosition == i) {
                adResultInfoItem = adResultInfoItem2;
            }
        }
        daqVar.onDone(adResultInfoItem);
    }

    /* renamed from: d */
    public void m12663d(DoneCallback<List<AdResultInfoItem>> daqVar) {
        VUiKit.m11713a().mo3332a($$Lambda$akz$IX6nwKFHgoLSqhJG4Qx5qgzBV2I.INSTANCE).mo3282b(daqVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public static /* synthetic */ List m12667c() throws Exception {
        return AppDatabase.m18933e().mo18929b().mo12765a();
    }

    /* renamed from: e */
    public void m12661e(DoneCallback<AdResultInfoItem> daqVar) {
        m12669b(3, daqVar);
    }

    /* renamed from: f */
    public void m12660f(DoneCallback<AdResultInfoItem> daqVar) {
        m12669b(5, daqVar);
    }

    /* renamed from: b */
    public void m12670b(final int i) {
        VUiKit.m11713a().mo3333a(new Runnable() { // from class: z1.-$$Lambda$akz$UfXbIjRNW-Yb8pIcUEJuDnRQfnM
            @Override // java.lang.Runnable
            public final void run() {
                BannerManager.m12666c(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public static /* synthetic */ void m12666c(int i) {
        AppDatabase.m18933e().mo18929b().mo12759b(i);
    }
}
