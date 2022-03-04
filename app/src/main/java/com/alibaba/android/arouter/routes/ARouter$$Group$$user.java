package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.nrzs.base.router.RouterConstants;
import com.nrzs.user.ui.activity.AlterPasswordActivity;
import com.nrzs.user.ui.activity.ChooseGameActivity;
import com.nrzs.user.ui.activity.ChooseScriptActivity;
import com.nrzs.user.ui.activity.KickOutActivity;
import com.nrzs.user.ui.activity.LoginActivity;
import com.nrzs.user.ui.activity.QuestionActivity;
import com.nrzs.user.ui.activity.RegisterActivity;
import java.util.Map;
import p110z1.aqa;

/* loaded from: classes.dex */
public class ARouter$$Group$$user implements IRouteGroup {
    @Override // com.alibaba.android.arouter.facade.template.IRouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put(RouterConstants.ModuleUser.ALTER_PASSWORD, RouteMeta.build(RouteType.ACTIVITY, AlterPasswordActivity.class, RouterConstants.ModuleUser.ALTER_PASSWORD, ServiceManagerNative.USER, null, -1, Integer.MIN_VALUE));
        map.put(RouterConstants.ModuleUser.CHOOSE_GAME, RouteMeta.build(RouteType.ACTIVITY, ChooseGameActivity.class, RouterConstants.ModuleUser.CHOOSE_GAME, ServiceManagerNative.USER, null, -1, Integer.MIN_VALUE));
        map.put(RouterConstants.ModuleUser.CHOOSE_SCTIPT, RouteMeta.build(RouteType.ACTIVITY, ChooseScriptActivity.class, RouterConstants.ModuleUser.CHOOSE_SCTIPT, ServiceManagerNative.USER, null, -1, Integer.MIN_VALUE));
        map.put(RouterConstants.ModuleUser.KICK_OUT, RouteMeta.build(RouteType.ACTIVITY, KickOutActivity.class, RouterConstants.ModuleUser.KICK_OUT, ServiceManagerNative.USER, null, -1, Integer.MIN_VALUE));
        map.put(RouterConstants.ModuleUser.LOGIN, RouteMeta.build(RouteType.ACTIVITY, LoginActivity.class, RouterConstants.ModuleUser.LOGIN, ServiceManagerNative.USER, null, -1, Integer.MIN_VALUE));
        map.put(RouterConstants.ModuleUser.QUESTION, RouteMeta.build(RouteType.ACTIVITY, QuestionActivity.class, RouterConstants.ModuleUser.QUESTION, ServiceManagerNative.USER, null, -1, Integer.MIN_VALUE));
        map.put(RouterConstants.ModuleUser.REGISTER, RouteMeta.build(RouteType.ACTIVITY, RegisterActivity.class, RouterConstants.ModuleUser.REGISTER, ServiceManagerNative.USER, null, -1, Integer.MIN_VALUE));
        map.put(RouterConstants.Provider.PROVIDER_USER, RouteMeta.build(RouteType.PROVIDER, aqa.class, RouterConstants.Provider.PROVIDER_USER, ServiceManagerNative.USER, null, -1, Integer.MIN_VALUE));
    }
}
