package p110z1;

import android.util.Log;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.nrzs.data.database.AppDatabase;
import com.nrzs.data.redbag.bean.MoneyInfo;
import java.util.ArrayList;
import java.util.List;

/* renamed from: z1.aqp */
/* loaded from: classes3.dex */
public class RedManager {

    /* renamed from: a */
    public static RedManager f17376a;

    /* renamed from: b */
    private List<MoneyInfo> f17377b = new ArrayList();

    /* renamed from: c */
    private String f17378c = ResultTypeConstant.f7213z;

    /* renamed from: d */
    private String f17379d = ResultTypeConstant.f7213z;

    /* compiled from: RedManager.java */
    /* renamed from: z1.aqp$a */
    /* loaded from: classes3.dex */
    public interface AbstractC3867a {
        /* renamed from: a */
        void mo11530a();

        /* renamed from: b */
        void mo11529b();
    }

    /* renamed from: a */
    public String m11543a() {
        return this.f17378c;
    }

    /* renamed from: a */
    public void m11541a(String str) {
        this.f17378c = str;
    }

    /* renamed from: b */
    public String m11537b() {
        return this.f17379d;
    }

    /* renamed from: b */
    public void m11535b(String str) {
        this.f17379d = str;
    }

    /* renamed from: c */
    public static RedManager m11534c() {
        if (f17376a == null) {
            f17376a = new RedManager();
        }
        return f17376a;
    }

    /* renamed from: a */
    public void m11540a(final AbstractC3867a aVar) {
        m11538a(new DoneCallback<List<MoneyInfo>>() { // from class: z1.aqp.1
            /* renamed from: a */
            public void onDone(List<MoneyInfo> list) {
                if (list != null) {
                    RedManager.this.f17377b = list;
                    aVar.mo11530a();
                    return;
                }
                aVar.mo11529b();
            }
        });
    }

    /* renamed from: d */
    public List<MoneyInfo> m11533d() {
        return this.f17377b;
    }

    /* renamed from: a */
    public void m11542a(final MoneyInfo moneyInfo) {
        List<MoneyInfo> list = this.f17377b;
        if (list != null) {
            list.add(moneyInfo);
        }
        VUiKit.m11713a().mo3333a(new Runnable() { // from class: z1.-$$Lambda$aqp$ubyOica_MNTjF9_EUezlOy1yQJg
            @Override // java.lang.Runnable
            public final void run() {
                RedManager.m11536b(MoneyInfo.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static /* synthetic */ void m11536b(MoneyInfo moneyInfo) {
        try {
            Log.e("红包", "插入");
            AppDatabase.m18933e().mo18926c().mo12571b(moneyInfo);
        } catch (Exception unused) {
            Log.e("红包", "插入出错");
        }
    }

    /* renamed from: a */
    public void m11538a(DoneCallback<List<MoneyInfo>> daqVar) {
        VUiKit.m11713a().mo3332a($$Lambda$aqp$8w6OU9j2p8rzOjRDs4b3HMmR2_U.INSTANCE).mo3282b(daqVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public static /* synthetic */ List m11532e() throws Exception {
        return AppDatabase.m18933e().mo18926c().mo12574a();
    }
}
