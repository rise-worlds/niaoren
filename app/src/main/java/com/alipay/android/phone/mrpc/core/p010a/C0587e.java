package com.alipay.android.phone.mrpc.core.p010a;

import com.alipay.android.phone.mrpc.core.RpcException;
import com.liulishuo.filedownloader.model.ConnectionModel;
import java.util.ArrayList;
import org.apache.commons.mail.EmailConstants;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import p110z1.C3382ae;

/* renamed from: com.alipay.android.phone.mrpc.core.a.e */
/* loaded from: classes.dex */
public final class C0587e extends AbstractC0584b {

    /* renamed from: c */
    private int f131c;

    /* renamed from: d */
    private Object f132d;

    public C0587e(int i, String str, Object obj) {
        super(str, obj);
        this.f131c = i;
    }

    @Override // com.alipay.android.phone.mrpc.core.p010a.AbstractC0588f
    /* renamed from: a */
    public final void mo25518a(Object obj) {
        this.f132d = obj;
    }

    @Override // com.alipay.android.phone.mrpc.core.p010a.AbstractC0588f
    /* renamed from: a */
    public final byte[] mo25519a() {
        try {
            ArrayList arrayList = new ArrayList();
            if (this.f132d != null) {
                arrayList.add(new BasicNameValuePair("extParam", C3382ae.m14205a(this.f132d)));
            }
            arrayList.add(new BasicNameValuePair("operationType", this.f129a));
            StringBuilder sb = new StringBuilder();
            sb.append(this.f131c);
            arrayList.add(new BasicNameValuePair(ConnectionModel.f10389a, sb.toString()));
            new StringBuilder("mParams is:").append(this.f130b);
            arrayList.add(new BasicNameValuePair("requestData", this.f130b == null ? "[]" : C3382ae.m14205a(this.f130b)));
            return URLEncodedUtils.format(arrayList, EmailConstants.UTF_8).getBytes();
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder("request  =");
            sb2.append(this.f130b);
            sb2.append(":");
            sb2.append(e);
            throw new RpcException(9, sb2.toString() == null ? "" : e.getMessage(), e);
        }
    }
}
