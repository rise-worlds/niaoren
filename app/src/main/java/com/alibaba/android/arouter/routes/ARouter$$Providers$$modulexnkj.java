package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IProviderGroup;
import com.common.router.XNKJRunProviderImpl;
import com.nrzs.base.router.RouterConstants;
import java.util.Map;

/* loaded from: classes.dex */
public class ARouter$$Providers$$modulexnkj implements IProviderGroup {
    @Override // com.alibaba.android.arouter.facade.template.IProviderGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("com.nrzs.base.router.provider.XNKJRunProvider", RouteMeta.build(RouteType.PROVIDER, XNKJRunProviderImpl.class, RouterConstants.Provider.PROVIDER_XNKJ_RUN, "xnkj", null, -1, Integer.MIN_VALUE));
    }
}
