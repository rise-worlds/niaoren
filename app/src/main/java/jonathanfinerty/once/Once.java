package jonathanfinerty.once;

import android.content.Context;
import android.content.pm.PackageManager;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class Once {
    public static final int THIS_APP_INSTALL = 0;
    public static final int THIS_APP_VERSION = 1;

    /* renamed from: a */
    private static long f14675a = -1;

    /* renamed from: b */
    private static PersistedMap f14676b;

    /* renamed from: c */
    private static PersistedSet f14677c;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Scope {
    }

    private Once() {
    }

    public static void initialise(Context context) {
        if (f14676b == null) {
            f14676b = new PersistedMap(context, "TagLastSeenMap");
        }
        if (f14677c == null) {
            f14677c = new PersistedSet(context, "ToDoSet");
        }
        try {
            f14675a = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).lastUpdateTime;
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    public static void toDo(int i, String str) {
        List<Long> list = f14676b.get(str);
        if (list.isEmpty()) {
            f14677c.put(str);
            return;
        }
        Long l = list.get(list.size() - 1);
        if (i == 1 && l.longValue() <= f14675a) {
            f14677c.put(str);
        }
    }

    public static void toDo(String str) {
        f14677c.put(str);
    }

    public static boolean needToDo(String str) {
        return f14677c.contains(str);
    }

    public static boolean beenDone(String str) {
        return beenDone(0, str, Amount.moreThan(0));
    }

    public static boolean beenDone(String str, CountChecker countChecker) {
        return beenDone(0, str, countChecker);
    }

    public static boolean beenDone(int i, String str) {
        return beenDone(i, str, Amount.moreThan(0));
    }

    public static boolean beenDone(int i, String str, CountChecker countChecker) {
        List<Long> list = f14676b.get(str);
        int i2 = 0;
        if (list.isEmpty()) {
            return false;
        }
        if (i == 0) {
            return countChecker.check(list.size());
        }
        for (Long l : list) {
            if (l.longValue() > f14675a) {
                i2++;
            }
        }
        return countChecker.check(i2);
    }

    public static boolean beenDone(TimeUnit timeUnit, long j, String str) {
        return beenDone(timeUnit, j, str, Amount.moreThan(0));
    }

    public static boolean beenDone(TimeUnit timeUnit, long j, String str, CountChecker countChecker) {
        return beenDone(timeUnit.toMillis(j), str, countChecker);
    }

    public static boolean beenDone(long j, String str) {
        return beenDone(j, str, Amount.moreThan(0));
    }

    public static boolean beenDone(long j, String str, CountChecker countChecker) {
        List<Long> list = f14676b.get(str);
        int i = 0;
        if (list.isEmpty()) {
            return false;
        }
        for (Long l : list) {
            if (l.longValue() > new Date().getTime() - j) {
                i++;
            }
        }
        return countChecker.check(i);
    }

    public static void markDone(String str) {
        f14676b.put(str, new Date().getTime());
        f14677c.remove(str);
    }

    public static void clearDone(String str) {
        f14676b.remove(str);
    }

    public static void clearToDo(String str) {
        f14677c.remove(str);
    }

    public static void clearAll() {
        f14676b.clear();
    }

    public static void clearAllToDos() {
        f14677c.clear();
    }
}
