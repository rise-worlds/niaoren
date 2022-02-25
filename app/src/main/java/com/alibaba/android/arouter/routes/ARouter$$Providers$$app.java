package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IProviderGroup;
import com.nrzs.base.router.RouterConstants;
import java.util.Map;
import p110z1.CarkeyProviderImpl;
import p110z1.Getchannelmpl;
import p110z1.PayProviderlmpl;
import p110z1.PaySuccessProviderImpl;

/* loaded from: classes.dex */
public class ARouter$$Providers$$app implements IProviderGroup {
    @Override // com.alibaba.android.arouter.facade.template.IProviderGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("com.nrzs.base.router.provider.CarkeyProvider", RouteMeta.build(RouteType.PROVIDER, CarkeyProviderImpl.class, RouterConstants.Provider.PROVIDER_APP, "app", null, -1, Integer.MIN_VALUE));
        map.put("com.nrzs.data.router.ChannelProvider", RouteMeta.build(RouteType.PROVIDER, Getchannelmpl.class, RouterConstants.Provider.PROVIDER_GETCHANNEL, "data", null, -1, Integer.MIN_VALUE));
        map.put("com.nrzs.base.router.provider.PayProvider", RouteMeta.build(RouteType.PROVIDER, PayProviderlmpl.class, RouterConstants.Provider.PROVIDER_TOWEB_PAY, "data", null, -1, Integer.MIN_VALUE));
        map.put("com.nrzs.base.router.provider.PsySuccessProvider", RouteMeta.build(RouteType.PROVIDER, PaySuccessProviderImpl.class, RouterConstants.Provider.PROVIDER_PAY, "pay", null, -1, Integer.MIN_VALUE));
    }
}
