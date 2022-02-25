package p110z1;

import android.util.Log;
import com.cyjh.ddysdk.order.DdyOrderContract;
import com.cyjh.ddysdk.order.DdyOrderHelper;
import com.cyjh.ddysdk.order.base.bean.SdkLoginRespone;
import com.cyjh.ddysdk.order.base.constants.DdyOrderErrorConstants;

/* renamed from: z1.ej */
/* loaded from: classes3.dex */
public class DdyManager {

    /* renamed from: a */
    private static DdyManager f21456a;

    /* renamed from: b */
    private boolean f21457b = false;

    /* renamed from: c */
    private String f21458c;

    /* renamed from: a */
    public boolean m3128a() {
        return this.f21457b;
    }

    /* renamed from: a */
    public void m3124a(boolean z) {
        this.f21457b = z;
    }

    /* renamed from: b */
    public String m3123b() {
        return this.f21458c;
    }

    /* renamed from: a */
    public void m3127a(String str) {
        this.f21458c = str;
    }

    /* renamed from: c */
    public static DdyManager m3122c() {
        if (f21456a == null) {
            synchronized (PreSetListManager.class) {
                if (f21456a == null) {
                    f21456a = new DdyManager();
                }
            }
        }
        return f21456a;
    }

    /* renamed from: d */
    public void m3121d() {
        if (LoginManager.m12620d().m12606r()) {
            DdyOrderContract.IPresenter instance = DdyOrderHelper.getInstance();
            instance.requestSDKLogin(LoginManager.m12620d().m12614j() + "", new DdyOrderContract.TCallback<SdkLoginRespone>() { // from class: z1.ej.1
                /* renamed from: a */
                public void onSuccess(SdkLoginRespone sdkLoginRespone) {
                    DdyManager.this.f21457b = true;
                    DdyManager.this.f21458c = sdkLoginRespone.UCID;
                }

                @Override // com.cyjh.ddysdk.order.DdyOrderContract.TCallback
                public void onFail(DdyOrderErrorConstants ddyOrderErrorConstants, String str) {
                    Log.e("d", str);
                }
            });
        }
    }
}
