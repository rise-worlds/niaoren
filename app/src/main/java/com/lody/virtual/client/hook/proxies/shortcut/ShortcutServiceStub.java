package com.lody.virtual.client.hook.proxies.shortcut;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.text.TextUtils;
import android.util.ArraySet;
import com.lody.virtual.client.VClient;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.ReplaceCallingPkgMethodProxy;
import com.lody.virtual.helper.compat.ParceledListSliceCompat;
import com.lody.virtual.helper.utils.BitmapUtils;
import com.lody.virtual.helper.utils.Reflect;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import p110z1.IShortcutService;
import p110z1.ParceledListSlice;

@TargetApi(25)
/* loaded from: classes.dex */
public class ShortcutServiceStub extends BinderInvocationProxy {
    public ShortcutServiceStub() {
        super(IShortcutService.C5139a.TYPE, "shortcut");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new ReplaceCallingPkgMethodProxy("disableShortcuts"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("enableShortcuts"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getRemainingCallCount"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getRateLimitResetTime"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getIconMaxDimensions"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getMaxShortcutCountPerActivity"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("reportShortcutUsed"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("onApplicationActive"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("hasShortcutHostPermission"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("removeAllDynamicShortcuts"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("removeDynamicShortcuts"));
        addMethodProxy(new WrapperShortcutInfo("requestPinShortcut", 1, false));
        addMethodProxy(new UnWrapperShortcutInfo("getPinnedShortcuts"));
        addMethodProxy(new WrapperShortcutInfo("addDynamicShortcuts", 1, false));
        addMethodProxy(new WrapperShortcutInfo("setDynamicShortcuts", 1, false));
        addMethodProxy(new UnWrapperShortcutInfo("getDynamicShortcuts"));
        addMethodProxy(new WrapperShortcutInfo("createShortcutResultIntent", 1, null));
        addMethodProxy(new WrapperShortcutInfo("updateShortcuts", 1, false));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getManifestShortcuts") { // from class: com.lody.virtual.client.hook.proxies.shortcut.ShortcutServiceStub.1
            @Override // com.lody.virtual.client.hook.base.MethodProxy
            public Object call(Object obj, Method method, Object... objArr) {
                return ParceledListSliceCompat.create(new ArrayList());
            }
        });
    }

    /* loaded from: classes.dex */
    static class WrapperShortcutInfo extends ReplaceCallingPkgMethodProxy {
        private Object defValue;
        private int infoIndex;

        public WrapperShortcutInfo(String str, int i, Object obj) {
            super(str);
            this.infoIndex = i;
            this.defValue = obj;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            if (!getConfig().isAllowCreateShortcut()) {
                return this.defValue;
            }
            int i = this.infoIndex;
            Object obj2 = objArr[i];
            if (obj2 == null) {
                return this.defValue;
            }
            if (obj2 instanceof ShortcutInfo) {
                objArr[i] = ShortcutServiceStub.wrapper(VClient.get().getCurrentApplication(), (ShortcutInfo) obj2, getAppPkg(), getAppUserId());
            } else {
                ArrayList arrayList = new ArrayList();
                try {
                    List<?> call = ParceledListSlice.getList.call(obj2, new Object[0]);
                    if (call != null) {
                        for (int size = call.size() - 1; size >= 0; size--) {
                            Object obj3 = call.get(size);
                            if (obj3 instanceof ShortcutInfo) {
                                ShortcutInfo unWrapper = ShortcutServiceStub.unWrapper(VClient.get().getCurrentApplication(), (ShortcutInfo) obj3, getAppPkg(), getAppUserId());
                                if (unWrapper != null) {
                                    arrayList.add(unWrapper);
                                }
                            }
                        }
                    }
                    objArr[this.infoIndex] = ParceledListSliceCompat.create(arrayList);
                } catch (Throwable unused) {
                    return this.defValue;
                }
            }
            return method.invoke(obj, objArr);
        }
    }

    /* loaded from: classes.dex */
    static class UnWrapperShortcutInfo extends ReplaceCallingPkgMethodProxy {
        public UnWrapperShortcutInfo(String str) {
            super(str);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            Object call = super.call(obj, method, objArr);
            if (call == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            if (!getConfig().isAllowCreateShortcut()) {
                return ParceledListSliceCompat.create(arrayList);
            }
            List<?> call2 = ParceledListSlice.getList.call(call, new Object[0]);
            if (call2 != null) {
                for (int size = call2.size() - 1; size >= 0; size--) {
                    Object obj2 = call2.get(size);
                    if (obj2 instanceof ShortcutInfo) {
                        ShortcutInfo unWrapper = ShortcutServiceStub.unWrapper(VClient.get().getCurrentApplication(), (ShortcutInfo) obj2, getAppPkg(), getAppUserId());
                        if (unWrapper != null) {
                            arrayList.add(unWrapper);
                        }
                    }
                }
            }
            return ParceledListSliceCompat.create(arrayList);
        }
    }

    static ShortcutInfo wrapper(Context context, ShortcutInfo shortcutInfo, String str, int i) {
        Bitmap bitmap;
        Icon icon = (Icon) Reflect.m18998on(shortcutInfo).opt("mIcon");
        if (icon != null) {
            bitmap = BitmapUtils.drawableToBitmap(icon.loadDrawable(context));
        } else {
            bitmap = BitmapUtils.drawableToBitmap(context.getApplicationInfo().loadIcon(VirtualCore.get().getPackageManager()));
        }
        Intent wrapperShortcutIntent = VirtualCore.get().wrapperShortcutIntent(shortcutInfo.getIntent(), null, str, i);
        wrapperShortcutIntent.putExtra("_VA_|categories", setToString(shortcutInfo.getCategories()));
        wrapperShortcutIntent.putExtra("_VA_|activity", shortcutInfo.getActivity());
        Context context2 = VirtualCore.get().getContext();
        ShortcutInfo.Builder builder = new ShortcutInfo.Builder(context2, str + "@" + i + "/" + shortcutInfo.getId());
        if (shortcutInfo.getLongLabel() != null) {
            builder.setLongLabel(shortcutInfo.getLongLabel());
        }
        if (shortcutInfo.getShortLabel() != null) {
            builder.setShortLabel(shortcutInfo.getShortLabel());
        }
        builder.setIcon(Icon.createWithBitmap(bitmap));
        builder.setIntent(wrapperShortcutIntent);
        return builder.build();
    }

    static ShortcutInfo unWrapper(Context context, ShortcutInfo shortcutInfo, String str, int i) throws URISyntaxException {
        Intent intent = shortcutInfo.getIntent();
        Intent intent2 = null;
        if (intent == null) {
            return null;
        }
        String stringExtra = intent.getStringExtra("_VA_|_pkg_");
        int intExtra = intent.getIntExtra("_VA_|_user_id_", 0);
        if (!TextUtils.equals(stringExtra, str) || intExtra != i) {
            return null;
        }
        String id = shortcutInfo.getId();
        String substring = id.substring(id.indexOf("/") + 1);
        Icon icon = (Icon) Reflect.m18998on(shortcutInfo).opt("mIcon");
        String stringExtra2 = intent.getStringExtra("_VA_|_uri_");
        if (!TextUtils.isEmpty(stringExtra2)) {
            intent2 = Intent.parseUri(stringExtra2, 0);
        }
        ComponentName componentName = (ComponentName) intent.getParcelableExtra("_VA_|activity");
        String stringExtra3 = intent.getStringExtra("_VA_|categories");
        ShortcutInfo.Builder builder = new ShortcutInfo.Builder(context, substring);
        if (icon != null) {
            builder.setIcon(icon);
        }
        if (shortcutInfo.getLongLabel() != null) {
            builder.setLongLabel(shortcutInfo.getLongLabel());
        }
        if (shortcutInfo.getShortLabel() != null) {
            builder.setShortLabel(shortcutInfo.getShortLabel());
        }
        if (componentName != null) {
            builder.setActivity(componentName);
        }
        if (intent2 != null) {
            builder.setIntent(intent2);
        }
        Set<String> set = toSet(stringExtra3);
        if (set != null) {
            builder.setCategories(set);
        }
        return builder.build();
    }

    private static <T> String setToString(Set<T> set) {
        if (set == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (T t : set) {
            if (z) {
                z = false;
            } else {
                sb.append(",");
            }
            sb.append(t);
        }
        return sb.toString();
    }

    @TargetApi(23)
    private static Set<String> toSet(String str) {
        if (str == null) {
            return null;
        }
        String[] split = str.split(",");
        ArraySet arraySet = new ArraySet();
        for (String str2 : split) {
            arraySet.add(str2);
        }
        return arraySet;
    }
}
