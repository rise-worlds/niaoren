package com.tendcloud.tenddata;

import android.support.p003v4.app.NotificationCompat;
import com.tendcloud.tenddata.C2945hn;
import com.tendcloud.tenddata.C3034zz;
import java.util.Map;
import org.apache.http.cookie.ClientCookie;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.gs */
/* loaded from: classes2.dex */
public class C2921gs {

    /* renamed from: a */
    private static volatile C2921gs f14119a;

    public final void onTDEBEventIAP(C3034zz.C3035a aVar) {
        if (aVar != null) {
            try {
                if (aVar.paraMap != null && Integer.parseInt(String.valueOf(aVar.paraMap.get("apiType"))) == 8) {
                    C2947ho hoVar = new C2947ho();
                    Object obj = aVar.paraMap.get("data");
                    AbstractC2790d dVar = (AbstractC2790d) aVar.paraMap.get(NotificationCompat.CATEGORY_SERVICE);
                    hoVar.f14181b = String.valueOf(aVar.paraMap.get(ClientCookie.DOMAIN_ATTR));
                    hoVar.f14182c = String.valueOf(aVar.paraMap.get("action"));
                    if (obj != null && (obj instanceof Map)) {
                        hoVar.f14183d = (Map) obj;
                    }
                    hoVar.f14180a = dVar;
                    C2858ev.m15778a().post(hoVar);
                    if ((aVar.paraMap.get("action") != null && (aVar.paraMap.get("action").equals("addItem") || aVar.paraMap.get("action").equals("recharge") || aVar.paraMap.get("action").equals("currencyPurchase") || aVar.paraMap.get("action").equals("placeOrder") || aVar.paraMap.get("action").equals("deeplink") || aVar.paraMap.get("action").equals("viewItem") || aVar.paraMap.get("action").equals("viewItems") || aVar.paraMap.get("action").equals("reward") || aVar.paraMap.get("action").equals("onRechargeSucc") || aVar.paraMap.get("action").equals("virtualCurrencyPurchase") || aVar.paraMap.get("action").equals("itemUsedFor") || aVar.paraMap.get("action").equals("pay") || aVar.paraMap.get("action").equals("apply") || aVar.paraMap.get("action").equals("activate"))) || aVar.paraMap.get("action").equals("search")) {
                        C2945hn hnVar = new C2945hn();
                        hnVar.f14178a = dVar;
                        hnVar.f14179b = C2945hn.EnumC2946a.IMMEDIATELY;
                        C2858ev.m15778a().post(hnVar);
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: a */
    public static C2921gs m15556a() {
        if (f14119a == null) {
            synchronized (C2921gs.class) {
                if (f14119a == null) {
                    f14119a = new C2921gs();
                }
            }
        }
        return f14119a;
    }

    private C2921gs() {
    }

    static {
        try {
            C2858ev.m15778a().register(m15556a());
        } catch (Throwable unused) {
        }
    }
}
