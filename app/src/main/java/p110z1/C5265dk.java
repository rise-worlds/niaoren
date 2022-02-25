package p110z1;

import android.content.Context;
import com.alipay.android.phone.mrpc.core.AbstractC0617w;
import com.alipay.android.phone.mrpc.core.C0589aa;
import com.alipay.android.phone.mrpc.core.C0601h;
import com.alipay.tscenter.biz.rpc.deviceFp.BugTrackMessageService;
import com.alipay.tscenter.biz.rpc.report.general.DataReportService;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportRequest;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportResult;
import org.json.JSONObject;

/* renamed from: z1.dk */
/* loaded from: classes3.dex */
public class C5265dk implements AbstractC5264dj {

    /* renamed from: d */
    private static C5265dk f21307d;

    /* renamed from: e */
    private static DataReportResult f21308e;

    /* renamed from: a */
    private AbstractC0617w f21309a;

    /* renamed from: b */
    private BugTrackMessageService f21310b;

    /* renamed from: c */
    private DataReportService f21311c;

    private C5265dk(Context context, String str) {
        this.f21309a = null;
        this.f21310b = null;
        this.f21311c = null;
        C0589aa aaVar = new C0589aa();
        aaVar.m25516a(str);
        this.f21309a = new C0601h(context);
        this.f21310b = (BugTrackMessageService) this.f21309a.mo25445a(BugTrackMessageService.class, aaVar);
        this.f21311c = (DataReportService) this.f21309a.mo25445a(DataReportService.class, aaVar);
    }

    /* renamed from: a */
    public static synchronized C5265dk m3216a(Context context, String str) {
        C5265dk dkVar;
        synchronized (C5265dk.class) {
            if (f21307d == null) {
                f21307d = new C5265dk(context, str);
            }
            dkVar = f21307d;
        }
        return dkVar;
    }

    @Override // p110z1.AbstractC5264dj
    /* renamed from: a */
    public DataReportResult mo3215a(DataReportRequest dataReportRequest) {
        if (dataReportRequest == null) {
            return null;
        }
        if (this.f21311c != null) {
            f21308e = null;
            new Thread(new RunnableC5266dl(this, dataReportRequest)).start();
            for (int i = C5267dm.f21314a; f21308e == null && i >= 0; i -= 50) {
                Thread.sleep(50L);
            }
        }
        return f21308e;
    }

    @Override // p110z1.AbstractC5264dj
    /* renamed from: a */
    public boolean mo3213a(String str) {
        BugTrackMessageService bugTrackMessageService;
        if (C5097cq.m3699a(str) || (bugTrackMessageService = this.f21310b) == null) {
            return false;
        }
        String str2 = null;
        try {
            str2 = bugTrackMessageService.logCollect(C5097cq.m3690f(str));
        } catch (Throwable unused) {
        }
        if (!C5097cq.m3699a(str2)) {
            return ((Boolean) new JSONObject(str2).get("success")).booleanValue();
        }
        return false;
    }
}
