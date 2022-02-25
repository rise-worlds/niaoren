package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.alibaba.android.arouter.facade.template.IRouteRoot;
import java.util.Map;

/* loaded from: classes.dex */
public class ARouter$$Root$$app implements IRouteRoot {
    @Override // com.alibaba.android.arouter.facade.template.IRouteRoot
    public void loadInto(Map<String, Class<? extends IRouteGroup>> map) {
        map.put("app", ARouter$$Group$$app.class);
        map.put("data", ARouter$$Group$$data.class);
        map.put("pay", ARouter$$Group$$pay.class);
        map.put("ui", ARouter$$Group$$ui.class);
    }
}
