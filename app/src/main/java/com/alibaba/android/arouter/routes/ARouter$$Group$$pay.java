package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.nrzs.base.router.RouterConstants;
import java.util.Map;
import p110z1.PaySuccessProviderImpl;

/* loaded from: classes.dex */
public class ARouter$$Group$$pay implements IRouteGroup {
    @Override // com.alibaba.android.arouter.facade.template.IRouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put(RouterConstants.Provider.PROVIDER_PAY, RouteMeta.build(RouteType.PROVIDER, PaySuccessProviderImpl.class, RouterConstants.Provider.PROVIDER_PAY, "pay", null, -1, Integer.MIN_VALUE));
    }
}
