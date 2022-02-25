package p110z1;

import android.support.annotation.Nullable;
import com.blankj.utilcode.util.SPUtils;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.other.bean.AdResultInfoItem;
import com.nrzs.data.other.bean.request.GetWelcomeV6RequestInfo;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import java.util.ArrayList;
import java.util.List;
import p110z1.ADStatueEvent;

/* renamed from: z1.aky */
/* loaded from: classes3.dex */
public class ADmanager {

    /* renamed from: a */
    public static ADmanager f16282a;

    /* renamed from: d */
    private List<AdResultInfoItem> f16285d;

    /* renamed from: g */
    private AdResultInfoItem f16288g;

    /* renamed from: b */
    private ADRepository f16283b = null;

    /* renamed from: c */
    private AdResultInfoItem f16284c = null;

    /* renamed from: e */
    private List<AdResultInfoItem> f16286e = null;

    /* renamed from: f */
    private List<AdResultInfoItem> f16287f = null;

    /* renamed from: h */
    private ThreadCallback<List<AdResultInfoItem>, String> f16289h = new ThreadCallback<List<AdResultInfoItem>, String>() { // from class: z1.aky.1
        /* renamed from: a */
        public List<AdResultInfoItem> onResponse(String str) {
            BaseResponse baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<List<AdResultInfoItem>>>() { // from class: z1.aky.1.1
            });
            if (baseResponse != null) {
                return (List) baseResponse.data;
            }
            return null;
        }
    };

    /* renamed from: i */
    private UICallback<List<AdResultInfoItem>> f16290i = new UICallback<List<AdResultInfoItem>>() { // from class: z1.aky.2
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(List<AdResultInfoItem> list) {
            if (list != null) {
                ADmanager.this.f16285d = list;
                ADmanager.this.m12686b();
            }
        }
    };

    /* renamed from: a */
    public static ADmanager m12690a() {
        if (f16282a == null) {
            f16282a = new ADmanager();
        }
        return f16282a;
    }

    /* renamed from: b */
    public void m12686b() {
        SPUtils.m23341a().m23332a(ShareVal.f16605o, new Gson().m1575b(this.f16285d));
        m12688a(this.f16285d);
    }

    /* renamed from: c */
    public void m12685c() {
        try {
            if (this.f16283b == null) {
                this.f16283b = new ADRepository();
            }
            this.f16283b.m12585a(new GetWelcomeV6RequestInfo(), this.f16290i, this.f16289h);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private void m12688a(List<AdResultInfoItem> list) {
        if (list != null) {
            this.f16286e = new ArrayList();
            this.f16287f = new ArrayList();
            for (AdResultInfoItem adResultInfoItem : list) {
                if (adResultInfoItem.AdPosition == 1) {
                    this.f16287f.add(adResultInfoItem);
                }
                if (adResultInfoItem.AdPosition == 2) {
                    this.f16286e.add(adResultInfoItem);
                }
            }
            SPUtils.m23341a().m23332a(ShareVal.f16604n, new Gson().m1575b(this.f16286e));
            EventBus.m3448a().m3427d(new ADStatueEvent.C3550a());
        }
    }

    /* renamed from: d */
    public List<AdResultInfoItem> m12684d() {
        Gson oxVar = new Gson();
        String b = SPUtils.m23341a().m23320b(ShareVal.f16604n, "");
        if (!b.equals("")) {
            return (List) oxVar.m1588a(b, new TypeToken<List<AdResultInfoItem>>() { // from class: z1.aky.3
            }.getType());
        }
        return null;
    }

    /* renamed from: e */
    public List<AdResultInfoItem> m12683e() {
        return this.f16287f;
    }

    /* renamed from: f */
    public AdResultInfoItem m12682f() {
        if (this.f16284c == null) {
            this.f16284c = m12689a(3);
        }
        return this.f16284c;
    }

    @Nullable
    /* renamed from: a */
    private AdResultInfoItem m12689a(int i) {
        if (this.f16285d == null) {
            Gson oxVar = new Gson();
            String b = SPUtils.m23341a().m23320b(ShareVal.f16605o, "");
            if (!b.equals("")) {
                this.f16285d = (List) oxVar.m1588a(b, new TypeToken<List<AdResultInfoItem>>() { // from class: z1.aky.4
                }.getType());
            }
        }
        AdResultInfoItem adResultInfoItem = null;
        List<AdResultInfoItem> list = this.f16285d;
        if (list != null) {
            for (AdResultInfoItem adResultInfoItem2 : list) {
                if (adResultInfoItem2.AdPosition == i) {
                    adResultInfoItem = adResultInfoItem2;
                }
            }
        }
        return adResultInfoItem;
    }
}
