package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.alibaba.android.arouter.facade.template.IRouteRoot;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import java.util.Map;

/* loaded from: classes.dex */
public class ARouter$$Root$$moduleuser implements IRouteRoot {
    @Override // com.alibaba.android.arouter.facade.template.IRouteRoot
    public void loadInto(Map<String, Class<? extends IRouteGroup>> map) {
        map.put(ServiceManagerNative.USER, ARouter$$Group$$user.class);
    }
}
