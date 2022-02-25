package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IProviderGroup;
import com.nrzs.base.router.RouterConstants;
import java.util.Map;
import p110z1.InvokeProviderImpl;

/* loaded from: classes.dex */
public class ARouter$$Providers$$modulegame implements IProviderGroup {
    @Override // com.alibaba.android.arouter.facade.template.IProviderGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("com.nrzs.base.router.provider.InvokeProvider", RouteMeta.build(RouteType.PROVIDER, InvokeProviderImpl.class, RouterConstants.Provider.PROVIDER_GAME, "game", null, -1, Integer.MIN_VALUE));
    }
}
