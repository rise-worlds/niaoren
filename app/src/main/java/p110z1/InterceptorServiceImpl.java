package p110z1;

import android.content.Context;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.service.InterceptorService;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Route(path = "/arouter/service/interceptor")
/* renamed from: z1.i */
/* loaded from: classes3.dex */
public class InterceptorServiceImpl implements InterceptorService {

    /* renamed from: a */
    private static boolean f21939a;

    /* renamed from: b */
    private static final Object f21940b = new Object();

    @Override // com.alibaba.android.arouter.facade.service.InterceptorService
    public void doInterceptions(final Postcard postcard, final InterceptorCallback interceptorCallback) {
        if (Warehouse.f22218f == null || Warehouse.f22218f.size() <= 0) {
            interceptorCallback.onContinue(postcard);
            return;
        }
        m2485b();
        if (!f21939a) {
            interceptorCallback.onInterrupt(new HandlerException("Interceptors initialization takes too much time."));
        } else {
            LogisticsCenter.f22070a.execute(new Runnable() { // from class: z1.i.1
                @Override // java.lang.Runnable
                public void run() {
                    CancelableCountDownLatch qVar = new CancelableCountDownLatch(Warehouse.f22218f.size());
                    try {
                        InterceptorServiceImpl.m2484b(0, qVar, postcard);
                        qVar.await(postcard.getTimeout(), TimeUnit.SECONDS);
                        if (qVar.getCount() > 0) {
                            interceptorCallback.onInterrupt(new HandlerException("The interceptor processing timed out."));
                        } else if (postcard.getTag() != null) {
                            interceptorCallback.onInterrupt(new HandlerException(postcard.getTag().toString()));
                        } else {
                            interceptorCallback.onContinue(postcard);
                        }
                    } catch (Exception e) {
                        interceptorCallback.onInterrupt(e);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static void m2484b(final int i, final CancelableCountDownLatch qVar, final Postcard postcard) {
        if (i < Warehouse.f22218f.size()) {
            Warehouse.f22218f.get(i).process(postcard, new InterceptorCallback() { // from class: z1.i.2
                @Override // com.alibaba.android.arouter.facade.callback.InterceptorCallback
                public void onContinue(Postcard postcard2) {
                    CancelableCountDownLatch.this.countDown();
                    InterceptorServiceImpl.m2484b(i + 1, CancelableCountDownLatch.this, postcard2);
                }

                @Override // com.alibaba.android.arouter.facade.callback.InterceptorCallback
                public void onInterrupt(Throwable th) {
                    postcard.setTag(th == null ? new HandlerException("No message.") : th.getMessage());
                    CancelableCountDownLatch.this.m1379a();
                }
            });
        }
    }

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(final Context context) {
        LogisticsCenter.f22070a.execute(new Runnable() { // from class: z1.i.3
            @Override // java.lang.Runnable
            public void run() {
                if (MapUtils.m282a(Warehouse.f22217e)) {
                    for (Map.Entry<Integer, Class<? extends IInterceptor>> entry : Warehouse.f22217e.entrySet()) {
                        Class<? extends IInterceptor> value = entry.getValue();
                        try {
                            IInterceptor iInterceptor = (IInterceptor) value.getConstructor(new Class[0]).newInstance(new Object[0]);
                            iInterceptor.init(context);
                            Warehouse.f22218f.add(iInterceptor);
                        } catch (Exception e) {
                            throw new HandlerException("ARouter::ARouter init interceptor error! name = [" + value.getName() + "], reason = [" + e.getMessage() + "]");
                        }
                    }
                    boolean unused = InterceptorServiceImpl.f21939a = true;
                    ARouter.f22668c.info("ARouter::", "ARouter interceptors init over.");
                    synchronized (InterceptorServiceImpl.f21940b) {
                        InterceptorServiceImpl.f21940b.notifyAll();
                    }
                }
            }
        });
    }

    /* renamed from: b */
    private static void m2485b() {
        synchronized (f21940b) {
            while (!f21939a) {
                try {
                    f21940b.wait(10000L);
                } catch (InterruptedException e) {
                    throw new HandlerException("ARouter::Interceptor init cost too much time error! reason = [" + e.getMessage() + "]");
                }
            }
        }
    }
}
