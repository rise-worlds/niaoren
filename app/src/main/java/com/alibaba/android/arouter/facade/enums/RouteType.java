package com.alibaba.android.arouter.facade.enums;

/* loaded from: classes.dex */
public enum RouteType {
    ACTIVITY(0, "android.app.Activity"),
    SERVICE(1, "android.app.Service"),
    PROVIDER(2, "com.alibaba.android.arouter.facade.template.IProvider"),
    CONTENT_PROVIDER(-1, "android.app.ContentProvider"),
    BOARDCAST(-1, ""),
    METHOD(-1, ""),
    FRAGMENT(-1, "android.app.Fragment"),
    UNKNOWN(-1, "Unknown route type");
    
    String className;

    /* renamed from: id */
    int f118id;

    public int getId() {
        return this.f118id;
    }

    public RouteType setId(int i) {
        this.f118id = i;
        return this;
    }

    public String getClassName() {
        return this.className;
    }

    public RouteType setClassName(String str) {
        this.className = str;
        return this;
    }

    RouteType(int i, String str) {
        this.f118id = i;
        this.className = str;
    }

    public static RouteType parse(String str) {
        RouteType[] values;
        for (RouteType routeType : values()) {
            if (routeType.getClassName().equals(str)) {
                return routeType;
            }
        }
        return UNKNOWN;
    }
}
