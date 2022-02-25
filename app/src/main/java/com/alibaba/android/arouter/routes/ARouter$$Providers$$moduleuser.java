package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IProviderGroup;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.nrzs.base.router.RouterConstants;
import java.util.Map;
import p110z1.aqa;

/* loaded from: classes.dex */
public class ARouter$$Providers$$moduleuser implements IProviderGroup {
    @Override // com.alibaba.android.arouter.facade.template.IProviderGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("com.nrzs.base.router.provider.InvokeProvider", RouteMeta.build(RouteType.PROVIDER, aqa.class, RouterConstants.Provider.PROVIDER_USER, ServiceManagerNative.USER, null, -1, Integer.MIN_VALUE));
    }
}
