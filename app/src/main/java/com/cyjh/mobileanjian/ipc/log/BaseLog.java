package com.cyjh.mobileanjian.ipc.log;

import android.content.Context;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;

/* renamed from: com.cyjh.mobileanjian.ipc.log.a */
/* loaded from: classes.dex */
public abstract class BaseLog {

    /* renamed from: a */
    protected static final String f8282a = "http://api4.mobileanjian.com/api";

    /* renamed from: b */
    protected Context f8283b;

    /* renamed from: c */
    protected String f8284c;

    /* renamed from: a */
    protected abstract void mo20988a();

    /* renamed from: b */
    protected abstract String mo20987b();

    public BaseLog(Context context) {
        this.f8283b = context;
    }

    public void commit() {
        mo20988a();
        HttpUtils httpUtils = new HttpUtils();
        RequestParams requestParams = new RequestParams();
        requestParams.addQueryStringParameter("Data", mo20987b());
        httpUtils.send(HttpRequest.HttpMethod.GET, this.f8284c, requestParams, null);
    }
}
