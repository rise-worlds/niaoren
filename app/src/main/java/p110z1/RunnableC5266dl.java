package p110z1;

import com.alipay.tscenter.biz.rpc.report.general.DataReportService;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportRequest;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportResult;

/* renamed from: z1.dl */
/* loaded from: classes3.dex */
class RunnableC5266dl implements Runnable {

    /* renamed from: a */
    final /* synthetic */ DataReportRequest f21312a;

    /* renamed from: b */
    final /* synthetic */ C5265dk f21313b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC5266dl(C5265dk dkVar, DataReportRequest dataReportRequest) {
        this.f21313b = dkVar;
        this.f21312a = dataReportRequest;
    }

    @Override // java.lang.Runnable
    public void run() {
        DataReportResult dataReportResult;
        DataReportResult dataReportResult2;
        DataReportService dataReportService;
        try {
            dataReportService = this.f21313b.f21311c;
            DataReportResult unused = C5265dk.f21308e = dataReportService.reportData(this.f21312a);
        } catch (Throwable th) {
            DataReportResult unused2 = C5265dk.f21308e = new DataReportResult();
            dataReportResult = C5265dk.f21308e;
            dataReportResult.success = false;
            dataReportResult2 = C5265dk.f21308e;
            dataReportResult2.resultCode = "static data rpc upload error, " + C5097cq.m3697a(th);
            new StringBuilder("rpc failed:").append(C5097cq.m3697a(th));
        }
    }
}
