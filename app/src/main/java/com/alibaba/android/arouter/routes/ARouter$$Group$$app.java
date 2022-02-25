package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.angel.nrzs.p017ui.activity.MainActivity;
import com.angel.nrzs.p017ui.activity.NRZSWebviewActivity;
import com.nrzs.base.router.RouterConstants;
import java.util.Map;
import p110z1.CarkeyProviderImpl;

/* loaded from: classes.dex */
public class ARouter$$Group$$app implements IRouteGroup {
    @Override // com.alibaba.android.arouter.facade.template.IRouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put(RouterConstants.MAIN, RouteMeta.build(RouteType.ACTIVITY, MainActivity.class, RouterConstants.MAIN, "app", null, -1, Integer.MIN_VALUE));
        map.put(RouterConstants.WEB, RouteMeta.build(RouteType.ACTIVITY, NRZSWebviewActivity.class, RouterConstants.WEB, "app", null, -1, Integer.MIN_VALUE));
        map.put(RouterConstants.Provider.PROVIDER_APP, RouteMeta.build(RouteType.PROVIDER, CarkeyProviderImpl.class, RouterConstants.Provider.PROVIDER_APP, "app", null, -1, Integer.MIN_VALUE));
    }
}
