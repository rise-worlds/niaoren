package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.nrzs.base.router.RouterConstants;
import com.nrzs.game.ui.activity.GameAllActivity;
import com.nrzs.game.ui.activity.GameScriptInfoActivity;
import com.nrzs.game.ui.activity.GameSearchActivity;
import com.nrzs.game.ui.activity.GameTopicActivity;
import java.util.Map;
import p110z1.InvokeProviderImpl;

/* loaded from: classes.dex */
public class ARouter$$Group$$game implements IRouteGroup {
    @Override // com.alibaba.android.arouter.facade.template.IRouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put(RouterConstants.ModuleGame.ALL, RouteMeta.build(RouteType.ACTIVITY, GameAllActivity.class, RouterConstants.ModuleGame.ALL, "game", null, -1, Integer.MIN_VALUE));
        map.put(RouterConstants.ModuleGame.SCRIPT_INFO, RouteMeta.build(RouteType.ACTIVITY, GameScriptInfoActivity.class, RouterConstants.ModuleGame.SCRIPT_INFO, "game", null, -1, Integer.MIN_VALUE));
        map.put(RouterConstants.ModuleGame.SEARCH, RouteMeta.build(RouteType.ACTIVITY, GameSearchActivity.class, RouterConstants.ModuleGame.SEARCH, "game", null, -1, Integer.MIN_VALUE));
        map.put(RouterConstants.ModuleGame.TOPIC, RouteMeta.build(RouteType.ACTIVITY, GameTopicActivity.class, RouterConstants.ModuleGame.TOPIC, "game", null, -1, Integer.MIN_VALUE));
        map.put(RouterConstants.Provider.PROVIDER_GAME, RouteMeta.build(RouteType.PROVIDER, InvokeProviderImpl.class, RouterConstants.Provider.PROVIDER_GAME, "game", null, -1, Integer.MIN_VALUE));
    }
}
