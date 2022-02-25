package com.lody.virtual.client.hook.proxies.libcore;

import com.lody.virtual.client.NativeEngine;
import com.lody.virtual.client.VClient;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.hook.base.MethodProxy;
import com.lody.virtual.helper.utils.Reflect;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import p110z1.Os;

/* loaded from: classes.dex */
class MethodProxies {
    MethodProxies() {
    }

    /* loaded from: classes.dex */
    static class Lstat extends Stat {
        @Override // com.lody.virtual.client.hook.proxies.libcore.MethodProxies.Stat, com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "lstat";
        }

        Lstat() {
        }

        @Override // com.lody.virtual.client.hook.proxies.libcore.MethodProxies.Stat, com.lody.virtual.client.hook.base.MethodProxy
        public Object afterCall(Object obj, Method method, Object[] objArr, Object obj2) throws Throwable {
            if (obj2 != null) {
                Reflect on = Reflect.m18998on(obj2);
                if (((Integer) on.get("st_uid")).intValue() == VirtualCore.get().myUid()) {
                    on.set("st_uid", Integer.valueOf(VClient.get().getVUid()));
                }
            }
            return obj2;
        }
    }

    /* loaded from: classes.dex */
    static class Fstat extends Stat {
        @Override // com.lody.virtual.client.hook.proxies.libcore.MethodProxies.Stat, com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "fstat";
        }

        Fstat() {
        }

        @Override // com.lody.virtual.client.hook.proxies.libcore.MethodProxies.Stat, com.lody.virtual.client.hook.base.MethodProxy
        public Object afterCall(Object obj, Method method, Object[] objArr, Object obj2) throws Throwable {
            if (obj2 != null) {
                Reflect on = Reflect.m18998on(obj2);
                if (((Integer) on.get("st_uid")).intValue() == VirtualCore.get().myUid()) {
                    on.set("st_uid", Integer.valueOf(VClient.get().getVUid()));
                }
            }
            return obj2;
        }
    }

    /* loaded from: classes.dex */
    static class Getpwnam extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getpwnam";
        }

        Getpwnam() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object afterCall(Object obj, Method method, Object[] objArr, Object obj2) throws Throwable {
            if (obj2 != null) {
                Reflect on = Reflect.m18998on(obj2);
                if (((Integer) on.get("pw_uid")).intValue() == VirtualCore.get().myUid()) {
                    on.set("pw_uid", Integer.valueOf(VClient.get().getVUid()));
                }
            }
            return obj2;
        }
    }

    /* loaded from: classes.dex */
    static class GetUid extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getuid";
        }

        GetUid() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object afterCall(Object obj, Method method, Object[] objArr, Object obj2) throws Throwable {
            return Integer.valueOf(NativeEngine.onGetUid(((Integer) obj2).intValue()));
        }
    }

    /* loaded from: classes.dex */
    static class GetsockoptUcred extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getsockoptUcred";
        }

        GetsockoptUcred() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object afterCall(Object obj, Method method, Object[] objArr, Object obj2) throws Throwable {
            if (obj2 != null) {
                Reflect on = Reflect.m18998on(obj2);
                if (((Integer) on.get("uid")).intValue() == VirtualCore.get().myUid()) {
                    on.set("uid", Integer.valueOf(getBaseVUid()));
                }
            }
            return obj2;
        }
    }

    /* loaded from: classes.dex */
    static class Stat extends MethodProxy {
        private static Field st_uid;

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "stat";
        }

        Stat() {
        }

        static {
            try {
                st_uid = Os.TYPE.getMethod("stat", String.class).getReturnType().getDeclaredField("st_uid");
                st_uid.setAccessible(true);
            } catch (Throwable th) {
                throw new IllegalStateException(th);
            }
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object afterCall(Object obj, Method method, Object[] objArr, Object obj2) throws Throwable {
            if (((Integer) st_uid.get(obj2)).intValue() == VirtualCore.get().myUid()) {
                st_uid.set(obj2, Integer.valueOf(getBaseVUid()));
            }
            return obj2;
        }
    }
}
