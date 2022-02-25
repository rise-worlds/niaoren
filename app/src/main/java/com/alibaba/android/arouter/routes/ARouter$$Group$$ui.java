package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.angel.nrzs.app.activity.RewardActivity;
import com.nrzs.base.router.RouterConstants;
import java.util.Map;

/* loaded from: classes.dex */
public class ARouter$$Group$$ui implements IRouteGroup {
    @Override // com.alibaba.android.arouter.facade.template.IRouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put(RouterConstants.ModuleUser.REWARD, RouteMeta.build(RouteType.ACTIVITY, RewardActivity.class, RouterConstants.ModuleUser.REWARD, "ui", null, -1, Integer.MIN_VALUE));
    }
}
