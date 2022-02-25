package com.nrzs.moudlepay;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.alipay.sdk.app.PayTask;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.Stripe;
import com.stripe.android.model.C2395g;
import java.util.Map;
import p110z1.C4985cm;
import p110z1.EventBus;
import p110z1.PayEvent;

/* renamed from: com.nrzs.moudlepay.a */
/* loaded from: classes2.dex */
public class AliPayManeger {

    /* renamed from: a */
    private static AliPayManeger f11223a = null;

    /* renamed from: e */
    private static final int f11224e = 1;

    /* renamed from: b */
    private String f11225b;

    /* renamed from: c */
    private String f11226c;

    /* renamed from: d */
    private Context f11227d;

    /* renamed from: f */
    private Handler f11228f = new Handler(Looper.getMainLooper()) { // from class: com.nrzs.moudlepay.a.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 1) {
                Map map = (Map) message.obj;
                String str = (String) map.get(C4985cm.f20833c);
                if (TextUtils.equals((String) map.get(C4985cm.f20831a), "9000")) {
                    AliPayManeger aVar = AliPayManeger.this;
                    aVar.f11225b = AliPayManeger.this.f11225b + "?orderId=" + AliPayManeger.this.f11226c;
                    EventBus.m3448a().m3427d(new PayEvent.C3680a(AliPayManeger.this.f11225b));
                    return;
                }
                Toast.makeText(AliPayManeger.this.f11227d, "支付失败,请稍后重试", 0).show();
            }
        }
    };

    /* renamed from: a */
    public static AliPayManeger m18507a() {
        AliPayManeger aVar = f11223a;
        if (aVar == null) {
            synchronized (AliPayManeger.class) {
                aVar = f11223a;
                if (aVar == null) {
                    aVar = new AliPayManeger();
                    f11223a = aVar;
                }
            }
        }
        return aVar;
    }

    /* renamed from: a */
    public void m18506a(Context context) {
        this.f11227d = context;
        PaymentConfiguration.m17993a("pk_live_dNjyreItgVv2ww4FIuPU7jan00R1X2CvDt");
    }

    /* renamed from: a */
    public void m18502a(String str, String str2, Activity activity, String str3) {
        Log.e("paysueesee", "去支付");
        Stripe qVar = new Stripe(this.f11227d, "pk_live_dNjyreItgVv2ww4FIuPU7jan00R1X2CvDt");
        try {
            this.f11225b = str3;
            this.f11226c = str;
            m18503a(qVar.m17535b(str, str2), activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m18503a(C2395g gVar, final Activity activity) {
        final String str = (String) gVar.m17759o().get("data_string");
        new Thread(new Runnable() { // from class: com.nrzs.moudlepay.a.2
            @Override // java.lang.Runnable
            public void run() {
                Map<String, String> payV2 = new PayTask(activity).payV2(str, true);
                Message message = new Message();
                message.what = 1;
                message.obj = payV2;
                AliPayManeger.this.f11228f.sendMessage(message);
            }
        }).start();
    }
}
