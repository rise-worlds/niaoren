package p110z1;

import com.alipay.tscenter.biz.rpc.report.general.model.DataReportRequest;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportResult;
import java.util.HashMap;
import java.util.Map;

/* renamed from: z1.dp */
/* loaded from: classes3.dex */
public class C5270dp {
    /* renamed from: a */
    public static DataReportRequest m3207a(C5272dr drVar) {
        DataReportRequest dataReportRequest = new DataReportRequest();
        if (drVar == null) {
            return null;
        }
        dataReportRequest.f416os = drVar.f21332a;
        dataReportRequest.rpcVersion = drVar.f21341j;
        dataReportRequest.bizType = "1";
        dataReportRequest.bizData = new HashMap();
        dataReportRequest.bizData.put("apdid", drVar.f21333b);
        dataReportRequest.bizData.put("apdidToken", drVar.f21334c);
        dataReportRequest.bizData.put("umidToken", drVar.f21335d);
        dataReportRequest.bizData.put("dynamicKey", drVar.f21336e);
        dataReportRequest.deviceData = drVar.f21337f;
        return dataReportRequest;
    }

    /* renamed from: a */
    public static C5271dq m3208a(DataReportResult dataReportResult) {
        C5271dq dqVar = new C5271dq();
        if (dataReportResult == null) {
            return null;
        }
        dqVar.f21316a = dataReportResult.success;
        dqVar.f21317b = dataReportResult.resultCode;
        Map<String, String> map = dataReportResult.resultData;
        if (map != null) {
            dqVar.f21323h = map.get("apdid");
            dqVar.f21324i = map.get("apdidToken");
            dqVar.f21327l = map.get("dynamicKey");
            dqVar.f21328m = map.get("timeInterval");
            dqVar.f21329n = map.get("webrtcUrl");
            dqVar.f21330o = "";
            String str = map.get("drmSwitch");
            if (C5097cq.m3695b(str)) {
                if (str.length() > 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str.charAt(0));
                    dqVar.f21325j = sb.toString();
                }
                if (str.length() >= 3) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str.charAt(2));
                    dqVar.f21326k = sb2.toString();
                }
            }
            if (map.containsKey("apse_degrade")) {
                dqVar.f21331p = map.get("apse_degrade");
            }
        }
        return dqVar;
    }
}
