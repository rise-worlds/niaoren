package p110z1;

import android.content.Context;

/* renamed from: z1.acc */
/* loaded from: classes3.dex */
public class ResourceUtil {
    /* renamed from: a */
    public static int m14395a(Context context, String str, String str2) {
        context.getPackageName();
        String packageName = context.getPackageName();
        try {
            Class<?>[] classes = Class.forName(packageName + ".R").getClasses();
            Class<?> cls = null;
            int i = 0;
            while (true) {
                if (i >= classes.length) {
                    break;
                } else if (classes[i].getName().split("\\$")[1].equals(str)) {
                    cls = classes[i];
                    break;
                } else {
                    i++;
                }
            }
            if (cls != null) {
                return cls.getField(str2).getInt(cls);
            }
            return 0;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return 0;
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
            return 0;
        } catch (NoSuchFieldException e4) {
            e4.printStackTrace();
            return 0;
        } catch (SecurityException e5) {
            e5.printStackTrace();
            return 0;
        }
    }
}
