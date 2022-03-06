package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.common.router.XNKJRunProviderImpl;
import com.lbd.xj.ui.activity.XJActivity;
import com.lbd.xj.ui.fragment.XnkjFragment;
import com.nrzs.base.router.RouterConstants;
import java.util.Map;

/* loaded from: classes.dex */
public class ARouter$$Group$$xnkj implements IRouteGroup {
    @Override // com.alibaba.android.arouter.facade.template.IRouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put(RouterConstants.ModuleXNKJ.START, RouteMeta.build(RouteType.ACTIVITY, XJActivity.class, RouterConstants.ModuleXNKJ.START, "xnkj", null, -1, Integer.MIN_VALUE));
        map.put(RouterConstants.ModuleXNKJ.XNKJ, RouteMeta.build(RouteType.FRAGMENT, XnkjFragment.class, RouterConstants.ModuleXNKJ.XNKJ, "xnkj", null, -1, Integer.MIN_VALUE));
        map.put(RouterConstants.Provider.PROVIDER_XNKJ_RUN, RouteMeta.build(RouteType.PROVIDER, XNKJRunProviderImpl.class, RouterConstants.Provider.PROVIDER_XNKJ_RUN, "xnkj", null, -1, Integer.MIN_VALUE));
    }
}
