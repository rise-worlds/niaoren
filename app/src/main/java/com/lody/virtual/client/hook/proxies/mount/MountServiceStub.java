package com.lody.virtual.client.hook.proxies.mount;

import android.app.usage.StorageStats;
import android.content.pm.PackageManager;
import android.os.IInterface;
import android.p002os.ParcelableException;
import com.lody.virtual.client.hook.annotations.Inject;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.ReplaceLastPkgMethodProxy;
import com.lody.virtual.client.hook.base.StaticMethodProxy;
import com.lody.virtual.client.ipc.VPackageManager;
import com.lody.virtual.helper.compat.BuildCompat;
import com.lody.virtual.helper.utils.ArrayUtils;
import java.lang.reflect.Method;
import mirror.RefStaticMethod;
import p110z1.IMountService;
import p110z1.IStorageManager;

@Inject(MethodProxies.class)
/* loaded from: classes.dex */
public class MountServiceStub extends BinderInvocationProxy {
    public MountServiceStub() {
        super(getInterfaceMethod(), "mount");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new ReplaceLastPkgMethodProxy("getTotalBytes"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("getCacheBytes"));
        addMethodProxy(new StaticMethodProxy("getCacheQuotaBytes") { // from class: com.lody.virtual.client.hook.proxies.mount.MountServiceStub.1
            @Override // com.lody.virtual.client.hook.base.MethodProxy
            public Object call(Object obj, Method method, Object... objArr) throws Throwable {
                if (objArr[objArr.length - 1] instanceof Integer) {
                    objArr[objArr.length - 1] = Integer.valueOf(getRealUid());
                }
                return method.invoke(obj, objArr);
            }
        });
        addMethodProxy(new ReplaceLastPkgMethodProxy("queryStatsForUser") { // from class: com.lody.virtual.client.hook.proxies.mount.MountServiceStub.2
            @Override // com.lody.virtual.client.hook.base.MethodProxy
            public Object call(Object obj, Method method, Object... objArr) throws Throwable {
                replaceLastUserId(objArr);
                return super.call(obj, method, objArr);
            }
        });
        addMethodProxy(new ReplaceLastPkgMethodProxy("queryExternalStatsForUser") { // from class: com.lody.virtual.client.hook.proxies.mount.MountServiceStub.3
            @Override // com.lody.virtual.client.hook.base.MethodProxy
            public Object call(Object obj, Method method, Object... objArr) throws Throwable {
                replaceLastUserId(objArr);
                return super.call(obj, method, objArr);
            }
        });
        addMethodProxy(new ReplaceLastPkgMethodProxy("queryStatsForUid"));
        addMethodProxy(new StaticMethodProxy("queryStatsForPackage") { // from class: com.lody.virtual.client.hook.proxies.mount.MountServiceStub.4
            @Override // com.lody.virtual.client.hook.base.MethodProxy
            public Object call(Object obj, Method method, Object... objArr) throws Throwable {
                int indexOfFirst = ArrayUtils.indexOfFirst(objArr, String.class);
                int indexOfLast = ArrayUtils.indexOfLast(objArr, Integer.class);
                if (indexOfFirst == -1 || indexOfLast == -1) {
                    replaceLastUserId(objArr);
                    return super.call(obj, method, objArr);
                }
                int intValue = ((Integer) objArr[indexOfLast]).intValue();
                return MountServiceStub.this.queryStatsForPackage((String) objArr[indexOfFirst], intValue);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public StorageStats queryStatsForPackage(String str, int i) {
        if (VPackageManager.get().getApplicationInfo(str, 0, i) != null) {
            StorageStats newInstance = p110z1.StorageStats.ctor.newInstance();
            p110z1.StorageStats.cacheBytes.set(newInstance, 0L);
            p110z1.StorageStats.codeBytes.set(newInstance, 0L);
            p110z1.StorageStats.dataBytes.set(newInstance, 0L);
            return newInstance;
        }
        throw new ParcelableException(new PackageManager.NameNotFoundException(str));
    }

    private static RefStaticMethod<IInterface> getInterfaceMethod() {
        if (BuildCompat.isOreo()) {
            return IStorageManager.C5176a.asInterface;
        }
        return IMountService.C5175a.asInterface;
    }
}
