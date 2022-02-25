package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.nrzs.base.router.RouterConstants;
import java.util.Map;
import p110z1.Getchannelmpl;
import p110z1.PayProviderlmpl;

/* loaded from: classes.dex */
public class ARouter$$Group$$data implements IRouteGroup {
    @Override // com.alibaba.android.arouter.facade.template.IRouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put(RouterConstants.Provider.PROVIDER_GETCHANNEL, RouteMeta.build(RouteType.PROVIDER, Getchannelmpl.class, RouterConstants.Provider.PROVIDER_GETCHANNEL, "data", null, -1, Integer.MIN_VALUE));
        map.put(RouterConstants.Provider.PROVIDER_TOWEB_PAY, RouteMeta.build(RouteType.PROVIDER, PayProviderlmpl.class, RouterConstants.Provider.PROVIDER_TOWEB_PAY, "data", null, -1, Integer.MIN_VALUE));
    }
}
