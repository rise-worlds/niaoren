package p110z1;

import android.content.Context;
import android.net.Uri;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.enums.TypeKind;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IInterceptorGroup;
import com.alibaba.android.arouter.facade.template.ILogger;
import com.alibaba.android.arouter.facade.template.IProvider;
import com.alibaba.android.arouter.facade.template.IProviderGroup;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.alibaba.android.arouter.facade.template.IRouteRoot;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/* renamed from: z1.j */
/* loaded from: classes3.dex */
public class LogisticsCenter {

    /* renamed from: a */
    static ThreadPoolExecutor f22070a;

    /* renamed from: b */
    private static Context f22071b;

    /* renamed from: c */
    private static boolean f22072c;

    /* renamed from: b */
    private static void m2351b() {
        f22072c = false;
    }

    /* renamed from: b */
    private static void m2350b(String str) {
        if (!C5599y.m75a((CharSequence) str)) {
            try {
                Object newInstance = Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
                if (newInstance instanceof IRouteRoot) {
                    m2353a((IRouteRoot) newInstance);
                } else if (newInstance instanceof IProviderGroup) {
                    m2354a((IProviderGroup) newInstance);
                } else if (newInstance instanceof IInterceptorGroup) {
                    m2355a((IInterceptorGroup) newInstance);
                } else {
                    ILogger iLogger = ARouter.f22668c;
                    iLogger.info("ARouter::", "register failed, class name: " + str + " should implements one of IRouteRoot/IProviderGroup/IInterceptorGroup.");
                }
            } catch (Exception unused) {
                ILogger iLogger2 = ARouter.f22668c;
                iLogger2.error("ARouter::", "register class error:" + str);
            }
        }
    }

    /* renamed from: a */
    private static void m2353a(IRouteRoot iRouteRoot) {
        m2349c();
        if (iRouteRoot != null) {
            iRouteRoot.loadInto(Warehouse.f22213a);
        }
    }

    /* renamed from: a */
    private static void m2355a(IInterceptorGroup iInterceptorGroup) {
        m2349c();
        if (iInterceptorGroup != null) {
            iInterceptorGroup.loadInto(Warehouse.f22217e);
        }
    }

    /* renamed from: a */
    private static void m2354a(IProviderGroup iProviderGroup) {
        m2349c();
        if (iProviderGroup != null) {
            iProviderGroup.loadInto(Warehouse.f22216d);
        }
    }

    /* renamed from: c */
    private static void m2349c() {
        if (!f22072c) {
            f22072c = true;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00b4 A[Catch: Exception -> 0x0195, all -> 0x01b6, TryCatch #1 {Exception -> 0x0195, blocks: (B:5:0x0007, B:7:0x0013, B:8:0x001e, B:10:0x0024, B:13:0x002b, B:14:0x004b, B:16:0x0062, B:17:0x0075, B:18:0x0078, B:19:0x00ae, B:21:0x00b4, B:23:0x00c2, B:24:0x00dc, B:26:0x00e4, B:27:0x00fe, B:29:0x0106, B:30:0x0120, B:32:0x014a, B:33:0x0153, B:35:0x0159), top: B:44:0x0007, outer: #0 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized void m2358a(android.content.Context r8, java.util.concurrent.ThreadPoolExecutor r9) throws p110z1.HandlerException {
        /*
            Method dump skipped, instructions count: 441
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.LogisticsCenter.m2358a(android.content.Context, java.util.concurrent.ThreadPoolExecutor):void");
    }

    /* renamed from: a */
    public static Postcard m2352a(String str) {
        RouteMeta routeMeta = Warehouse.f22216d.get(str);
        if (routeMeta == null) {
            return null;
        }
        return new Postcard(routeMeta.getPath(), routeMeta.getGroup());
    }

    /* renamed from: a */
    public static synchronized void m2357a(Postcard postcard) {
        synchronized (LogisticsCenter.class) {
            if (postcard != null) {
                RouteMeta routeMeta = Warehouse.f22214b.get(postcard.getPath());
                if (routeMeta != null) {
                    postcard.setDestination(routeMeta.getDestination());
                    postcard.setType(routeMeta.getType());
                    postcard.setPriority(routeMeta.getPriority());
                    postcard.setExtra(routeMeta.getExtra());
                    Uri uri = postcard.getUri();
                    if (uri != null) {
                        Map<String, String> a = C5599y.m76a(uri);
                        Map<String, Integer> paramsType = routeMeta.getParamsType();
                        if (MapUtils.m282a(paramsType)) {
                            for (Map.Entry<String, Integer> entry : paramsType.entrySet()) {
                                m2356a(postcard, entry.getValue(), entry.getKey(), a.get(entry.getKey()));
                            }
                            postcard.getExtras().putStringArray(ARouter.f22667b, (String[]) paramsType.keySet().toArray(new String[0]));
                        }
                        postcard.withString(ARouter.f22666a, uri.toString());
                    }
                    switch (routeMeta.getType()) {
                        case PROVIDER:
                            Class<?> destination = routeMeta.getDestination();
                            IProvider iProvider = Warehouse.f22215c.get(destination);
                            if (iProvider == null) {
                                try {
                                    iProvider = (IProvider) destination.getConstructor(new Class[0]).newInstance(new Object[0]);
                                    iProvider.init(f22071b);
                                    Warehouse.f22215c.put(destination, iProvider);
                                } catch (Exception e) {
                                    throw new HandlerException("Init provider failed! " + e.getMessage());
                                }
                            }
                            postcard.setProvider(iProvider);
                            postcard.greenChannel();
                            break;
                        case FRAGMENT:
                            postcard.greenChannel();
                            break;
                    }
                } else {
                    Class<? extends IRouteGroup> cls = Warehouse.f22213a.get(postcard.getGroup());
                    if (cls != null) {
                        try {
                            if (ARouter.m1703c()) {
                                ARouter.f22668c.debug("ARouter::", String.format(Locale.getDefault(), "The group [%s] starts loading, trigger by [%s]", postcard.getGroup(), postcard.getPath()));
                            }
                            ((IRouteGroup) cls.getConstructor(new Class[0]).newInstance(new Object[0])).loadInto(Warehouse.f22214b);
                            Warehouse.f22213a.remove(postcard.getGroup());
                            if (ARouter.m1703c()) {
                                ARouter.f22668c.debug("ARouter::", String.format(Locale.getDefault(), "The group [%s] has already been loaded, trigger by [%s]", postcard.getGroup(), postcard.getPath()));
                            }
                            m2357a(postcard);
                        } catch (Exception e2) {
                            throw new HandlerException("ARouter::Fatal exception when loading group meta. [" + e2.getMessage() + "]");
                        }
                    } else {
                        throw new NoRouteFoundException("ARouter::There is no route match the path [" + postcard.getPath() + "], in group [" + postcard.getGroup() + "]");
                    }
                }
            } else {
                throw new NoRouteFoundException("ARouter::No postcard!");
            }
        }
    }

    /* renamed from: a */
    private static void m2356a(Postcard postcard, Integer num, String str, String str2) {
        if (!C5599y.m75a((CharSequence) str) && !C5599y.m75a((CharSequence) str2)) {
            try {
                if (num == null) {
                    postcard.withString(str, str2);
                } else if (num.intValue() == TypeKind.BOOLEAN.ordinal()) {
                    postcard.withBoolean(str, Boolean.parseBoolean(str2));
                } else if (num.intValue() == TypeKind.BYTE.ordinal()) {
                    postcard.withByte(str, Byte.valueOf(str2).byteValue());
                } else if (num.intValue() == TypeKind.SHORT.ordinal()) {
                    postcard.withShort(str, Short.valueOf(str2).shortValue());
                } else if (num.intValue() == TypeKind.INT.ordinal()) {
                    postcard.withInt(str, Integer.valueOf(str2).intValue());
                } else if (num.intValue() == TypeKind.LONG.ordinal()) {
                    postcard.withLong(str, Long.valueOf(str2).longValue());
                } else if (num.intValue() == TypeKind.FLOAT.ordinal()) {
                    postcard.withFloat(str, Float.valueOf(str2).floatValue());
                } else if (num.intValue() == TypeKind.DOUBLE.ordinal()) {
                    postcard.withDouble(str, Double.valueOf(str2).doubleValue());
                } else if (num.intValue() == TypeKind.STRING.ordinal()) {
                    postcard.withString(str, str2);
                } else if (num.intValue() != TypeKind.PARCELABLE.ordinal()) {
                    if (num.intValue() == TypeKind.OBJECT.ordinal()) {
                        postcard.withString(str, str2);
                    } else {
                        postcard.withString(str, str2);
                    }
                }
            } catch (Throwable th) {
                ILogger iLogger = ARouter.f22668c;
                iLogger.warning("ARouter::", "LogisticsCenter setValue failed! " + th.getMessage());
            }
        }
    }

    /* renamed from: a */
    public static void m2359a() {
        Warehouse.m2228a();
    }
}
