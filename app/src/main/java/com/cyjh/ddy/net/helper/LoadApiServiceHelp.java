package com.cyjh.ddy.net.helper;

import com.cyjh.ddy.net.bean.base.BaseApiService;
import com.cyjh.ddy.net.utils.RetrofitUtils;

/* renamed from: com.cyjh.ddy.net.helper.c */
/* loaded from: classes.dex */
public class LoadApiServiceHelp {
    /* renamed from: a */
    public static BaseApiService m21421a() {
        return (BaseApiService) RetrofitUtils.m21399b().m21401a().create(BaseApiService.class);
    }

    /* renamed from: a */
    public static BaseApiService m21420a(String str) {
        return (BaseApiService) RetrofitUtils.m21399b().m21400a(str).create(BaseApiService.class);
    }
}
