package com.cyjh.ddysdk.order.base.p041a;

import android.text.TextUtils;
import com.cyjh.ddy.base.utils.C1123f;
import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddysdk.order.base.bean.OrderSteamServerRespone;

/* renamed from: com.cyjh.ddysdk.order.base.a.a */
/* loaded from: classes.dex */
public class EncodeServiceManager {

    /* renamed from: a */
    public static final String f8091a = "EncodeServiceManager";

    /* renamed from: c */
    private static final EncodeServiceManager f8092c = new EncodeServiceManager();

    /* renamed from: b */
    public int f8093b;

    /* renamed from: d */
    private int f8094d;

    /* renamed from: e */
    private String f8095e;

    /* renamed from: f */
    private String f8096f;

    /* renamed from: g */
    private int f8097g;

    /* renamed from: h */
    private boolean f8098h = true;

    /* renamed from: a */
    public static EncodeServiceManager m21141a() {
        return f8092c;
    }

    private EncodeServiceManager() {
    }

    /* renamed from: b */
    public void m21137b() {
        this.f8095e = "";
        this.f8094d = 0;
        this.f8093b = 0;
        this.f8096f = "";
        this.f8097g = 0;
    }

    /* renamed from: a */
    public void m21138a(boolean z) {
        this.f8098h = z;
        CLog.m21882i(f8091a, "closeSaveData " + z);
    }

    /* renamed from: a */
    public boolean m21140a(int i) {
        if (this.f8098h || this.f8094d != i || TextUtils.isEmpty(this.f8095e)) {
            return false;
        }
        int i2 = this.f8093b;
        return i2 == 1 || i2 == 2;
    }

    /* renamed from: a */
    public void m21139a(int i, OrderSteamServerRespone orderSteamServerRespone) {
        if (!this.f8098h) {
            this.f8094d = i;
            this.f8093b = orderSteamServerRespone.ServerType;
            this.f8095e = orderSteamServerRespone.AnboxStreamUrl;
            this.f8096f = orderSteamServerRespone.OtherParam;
            this.f8097g = orderSteamServerRespone.TransportMode;
            CLog.m21882i(f8091a, "[INFO]保存数据：" + C1123f.m21845a(orderSteamServerRespone) + " 订单号：" + i);
        }
    }

    /* renamed from: c */
    public OrderSteamServerRespone m21136c() {
        if (this.f8098h) {
            return null;
        }
        OrderSteamServerRespone orderSteamServerRespone = new OrderSteamServerRespone();
        orderSteamServerRespone.AnboxStreamUrl = this.f8095e;
        orderSteamServerRespone.ServerType = this.f8093b;
        orderSteamServerRespone.OtherParam = this.f8096f;
        orderSteamServerRespone.TransportMode = this.f8097g;
        CLog.m21882i(f8091a, "[INFO]获取缓存的数据：" + C1123f.m21845a(orderSteamServerRespone));
        return orderSteamServerRespone;
    }
}
