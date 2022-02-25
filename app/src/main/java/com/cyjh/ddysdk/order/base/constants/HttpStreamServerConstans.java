package com.cyjh.ddysdk.order.base.constants;

import com.cyjh.ddy.net.utils.DomainUtils;

/* renamed from: com.cyjh.ddysdk.order.base.constants.a */
/* loaded from: classes.dex */
public class HttpStreamServerConstans {

    /* renamed from: a */
    public final String f8101a;

    /* renamed from: b */
    public final String f8102b;

    /* renamed from: c */
    public final String f8103c;

    /* renamed from: d */
    public String f8104d;

    /* renamed from: e */
    public final String f8105e;

    /* renamed from: f */
    private boolean f8106f;

    public HttpStreamServerConstans() {
        this.f8101a = DomainUtils.m21392b() + "api/EncodeService";
        this.f8102b = DomainUtils.m21392b() + "api/v1/EncodeService";
        this.f8103c = "http://121.37.208.5:8080/api/v3/EncodeService";
        this.f8105e = DomainUtils.m21392b() + "api/SetDebugOrder";
        this.f8106f = false;
        this.f8104d = this.f8101a;
    }

    public HttpStreamServerConstans(boolean z) {
        this.f8101a = DomainUtils.m21392b() + "api/EncodeService";
        this.f8102b = DomainUtils.m21392b() + "api/v1/EncodeService";
        this.f8103c = "http://121.37.208.5:8080/api/v3/EncodeService";
        this.f8105e = DomainUtils.m21392b() + "api/SetDebugOrder";
        this.f8106f = z;
        if (this.f8106f) {
            this.f8104d = this.f8102b;
        } else {
            this.f8104d = this.f8101a;
        }
    }
}
