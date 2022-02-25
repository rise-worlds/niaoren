package p110z1;

import android.util.Log;
import com.blankj.utilcode.util.LogUtils;
import com.nrzs.data.p066ft.bean.EnginInteraRequestInfo;

/* renamed from: z1.ano */
/* loaded from: classes3.dex */
public class AssistInfoModel {
    /* renamed from: a */
    public void m12259a() {
    }

    /* renamed from: a */
    public void m12258a(long j, long j2, String str) {
        LogUtils.m23734c("newEngin", "通知引擎加载权限接口:" + j + ",scriptId:" + j2 + ",onlyId:" + str);
        EnginInteraRequestInfo enginInteraRequestInfo = new EnginInteraRequestInfo();
        enginInteraRequestInfo.Command = 5;
        EnginInteraRequestInfo.EnginInteraParams enginInteraParams = new EnginInteraRequestInfo.EnginInteraParams();
        enginInteraParams.TopicId = j;
        enginInteraParams.ToolId = j2;
        enginInteraParams.OnlyId = str;
        enginInteraRequestInfo.Param = enginInteraParams;
        Log.d("LBS_ENGIN", "loadRunperm:" + enginInteraRequestInfo.Param.toString());
        FloatAssistManager.m12397i().m12421a(enginInteraRequestInfo);
    }

    /* renamed from: b */
    public void m12257b(long j, long j2, String str) {
        EnginInteraRequestInfo enginInteraRequestInfo = new EnginInteraRequestInfo();
        enginInteraRequestInfo.Command = 2;
        EnginInteraRequestInfo.EnginInteraParams enginInteraParams = new EnginInteraRequestInfo.EnginInteraParams();
        enginInteraParams.TopicId = j;
        enginInteraParams.ToolId = j2;
        enginInteraParams.OnlyId = str;
        enginInteraRequestInfo.Param = enginInteraParams;
        FloatAssistManager.m12397i().m12411b(enginInteraRequestInfo);
    }
}
