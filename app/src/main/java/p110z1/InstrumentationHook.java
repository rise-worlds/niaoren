package p110z1;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import java.lang.reflect.Field;

@Deprecated
/* renamed from: z1.h */
/* loaded from: classes3.dex */
public class InstrumentationHook extends Instrumentation {
    @Override // android.app.Instrumentation
    public Activity newActivity(ClassLoader classLoader, String str, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        String[] stringArrayExtra;
        Class<?> loadClass = classLoader.loadClass(str);
        Object newInstance = loadClass.newInstance();
        if (ARouter.m1698h() && (stringArrayExtra = intent.getStringArrayExtra(ARouter.f22667b)) != null && stringArrayExtra.length > 0) {
            for (String str2 : stringArrayExtra) {
                Object obj = intent.getExtras().get(C5599y.m74a(str2));
                if (obj != null) {
                    try {
                        Field declaredField = loadClass.getDeclaredField(C5599y.m74a(str2));
                        declaredField.setAccessible(true);
                        declaredField.set(newInstance, obj);
                    } catch (Exception e) {
                        ARouter.f22668c.error("ARouter::", "Inject values for activity error! [" + e.getMessage() + "]");
                    }
                }
            }
        }
        return (Activity) newInstance;
    }
}
