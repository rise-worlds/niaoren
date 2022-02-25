package com.lody.virtual.client.hook.proxies.mount;

import android.os.Build;
import com.lody.virtual.client.hook.base.MethodProxy;
import com.lody.virtual.client.hook.utils.MethodParameterUtils;
import java.io.File;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
class MethodProxies {
    MethodProxies() {
    }

    /* loaded from: classes.dex */
    static class GetVolumeList extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object afterCall(Object obj, Method method, Object[] objArr, Object obj2) throws Throwable {
            return obj2;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getVolumeList";
        }

        GetVolumeList() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean beforeCall(Object obj, Method method, Object... objArr) {
            if (objArr == null || objArr.length == 0) {
                return super.beforeCall(obj, method, objArr);
            }
            if (objArr[0] instanceof Integer) {
                objArr[0] = Integer.valueOf(getRealUid());
            }
            MethodParameterUtils.replaceFirstAppPkg(objArr);
            return super.beforeCall(obj, method, objArr);
        }
    }

    /* loaded from: classes.dex */
    static class Mkdirs extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "mkdirs";
        }

        Mkdirs() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean beforeCall(Object obj, Method method, Object... objArr) {
            MethodParameterUtils.replaceFirstAppPkg(objArr);
            return super.beforeCall(obj, method, objArr);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            String str;
            if (Build.VERSION.SDK_INT < 19) {
                return super.call(obj, method, objArr);
            }
            if (objArr.length == 1) {
                str = (String) objArr[0];
            } else {
                str = (String) objArr[1];
            }
            File file = new File(str);
            if (file.exists() || file.mkdirs()) {
                return 0;
            }
            return -1;
        }
    }
}
