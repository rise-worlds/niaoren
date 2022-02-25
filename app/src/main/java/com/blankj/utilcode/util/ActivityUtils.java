package com.blankj.utilcode.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p003v4.app.ActivityOptionsCompat;
import android.support.p003v4.util.Pair;
import android.util.Log;
import android.view.View;
import java.util.LinkedList;
import java.util.List;

/* renamed from: com.blankj.utilcode.util.a */
/* loaded from: classes.dex */
public final class ActivityUtils {
    private ActivityUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static Activity m24005a(@NonNull View view) {
        if (view != null) {
            return m24035a(view.getContext());
        }
        throw new NullPointerException("Argument 'view' of type View (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static Activity m24035a(@NonNull Context context) {
        if (context == null) {
            throw new NullPointerException("Argument 'context' of type Context (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (context instanceof Activity) {
            return (Activity) context;
        } else {
            while (context instanceof ContextWrapper) {
                if (context instanceof Activity) {
                    return (Activity) context;
                }
                context = ((ContextWrapper) context).getBaseContext();
            }
            return null;
        }
    }

    /* renamed from: a */
    public static boolean m23997a(@NonNull String str, @NonNull String str2) {
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            Intent intent = new Intent();
            intent.setClassName(str, str2);
            return (Utils.m24103a().getPackageManager().resolveActivity(intent, 0) == null || intent.resolveActivity(Utils.m24103a().getPackageManager()) == null || Utils.m24103a().getPackageManager().queryIntentActivities(intent, 0).size() == 0) ? false : true;
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24004a(@NonNull Class<? extends Activity> cls) {
        if (cls != null) {
            Context d = Utils.m24095d();
            m24033a(d, (Bundle) null, d.getPackageName(), cls.getName(), (Bundle) null);
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m24002a(@NonNull Class<? extends Activity> cls, @Nullable Bundle bundle) {
        if (cls != null) {
            Context d = Utils.m24095d();
            m24033a(d, (Bundle) null, d.getPackageName(), cls.getName(), bundle);
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m24003a(@NonNull Class<? extends Activity> cls, @AnimRes int i, @AnimRes int i2) {
        if (cls != null) {
            Context d = Utils.m24095d();
            m24033a(d, (Bundle) null, d.getPackageName(), cls.getName(), m24034a(d, i, i2));
            if (Build.VERSION.SDK_INT < 16 && (d instanceof Activity)) {
                ((Activity) d).overridePendingTransition(i, i2);
                return;
            }
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m24055a(@NonNull Activity activity, @NonNull Class<? extends Activity> cls) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls != null) {
            m24033a(activity, (Bundle) null, activity.getPackageName(), cls.getName(), (Bundle) null);
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24049a(@NonNull Activity activity, @NonNull Class<? extends Activity> cls, @Nullable Bundle bundle) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls != null) {
            m24033a(activity, (Bundle) null, activity.getPackageName(), cls.getName(), bundle);
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24048a(@NonNull Activity activity, @NonNull Class<? extends Activity> cls, View... viewArr) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls != null) {
            m24033a(activity, (Bundle) null, activity.getPackageName(), cls.getName(), m24037a(activity, viewArr));
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24053a(@NonNull Activity activity, @NonNull Class<? extends Activity> cls, @AnimRes int i, @AnimRes int i2) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls != null) {
            m24033a(activity, (Bundle) null, activity.getPackageName(), cls.getName(), m24034a((Context) activity, i, i2));
            if (Build.VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i, i2);
            }
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24011a(@NonNull Bundle bundle, @NonNull Class<? extends Activity> cls) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls != null) {
            Context d = Utils.m24095d();
            m24033a(d, bundle, d.getPackageName(), cls.getName(), (Bundle) null);
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24009a(@NonNull Bundle bundle, @NonNull Class<? extends Activity> cls, @Nullable Bundle bundle2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls != null) {
            Context d = Utils.m24095d();
            m24033a(d, bundle, d.getPackageName(), cls.getName(), bundle2);
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24010a(@NonNull Bundle bundle, @NonNull Class<? extends Activity> cls, @AnimRes int i, @AnimRes int i2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls != null) {
            Context d = Utils.m24095d();
            m24033a(d, bundle, d.getPackageName(), cls.getName(), m24034a(d, i, i2));
            if (Build.VERSION.SDK_INT < 16 && (d instanceof Activity)) {
                ((Activity) d).overridePendingTransition(i, i2);
            }
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24027a(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull Class<? extends Activity> cls) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls != null) {
            m24033a(activity, bundle, activity.getPackageName(), cls.getName(), (Bundle) null);
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24021a(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull Class<? extends Activity> cls, @Nullable Bundle bundle2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls != null) {
            m24033a(activity, bundle, activity.getPackageName(), cls.getName(), bundle2);
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24020a(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull Class<? extends Activity> cls, View... viewArr) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls != null) {
            m24033a(activity, bundle, activity.getPackageName(), cls.getName(), m24037a(activity, viewArr));
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24025a(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull Class<? extends Activity> cls, @AnimRes int i, @AnimRes int i2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls != null) {
            m24033a(activity, bundle, activity.getPackageName(), cls.getName(), m24034a((Context) activity, i, i2));
            if (Build.VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i, i2);
            }
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static void m23979b(@NonNull String str, @NonNull String str2) {
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            m24033a(Utils.m24095d(), (Bundle) null, str, str2, (Bundle) null);
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m23995a(@NonNull String str, @NonNull String str2, @Nullable Bundle bundle) {
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            m24033a(Utils.m24095d(), (Bundle) null, str, str2, bundle);
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m23996a(@NonNull String str, @NonNull String str2, @AnimRes int i, @AnimRes int i2) {
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            Context d = Utils.m24095d();
            m24033a(d, (Bundle) null, str, str2, m24034a(d, i, i2));
            if (Build.VERSION.SDK_INT < 16 && (d instanceof Activity)) {
                ((Activity) d).overridePendingTransition(i, i2);
            }
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24047a(@NonNull Activity activity, @NonNull String str, @NonNull String str2) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            m24033a(activity, (Bundle) null, str, str2, (Bundle) null);
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24045a(@NonNull Activity activity, @NonNull String str, @NonNull String str2, @Nullable Bundle bundle) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            m24033a(activity, (Bundle) null, str, str2, bundle);
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#2 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24044a(@NonNull Activity activity, @NonNull String str, @NonNull String str2, View... viewArr) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            m24033a(activity, (Bundle) null, str, str2, m24037a(activity, viewArr));
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#2 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24046a(@NonNull Activity activity, @NonNull String str, @NonNull String str2, @AnimRes int i, @AnimRes int i2) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            m24033a(activity, (Bundle) null, str, str2, m24034a((Context) activity, i, i2));
            if (Build.VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i, i2);
            }
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#2 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24008a(@NonNull Bundle bundle, @NonNull String str, @NonNull String str2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            m24033a(Utils.m24095d(), bundle, str, str2, (Bundle) null);
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24006a(@NonNull Bundle bundle, @NonNull String str, @NonNull String str2, @Nullable Bundle bundle2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            m24033a(Utils.m24095d(), bundle, str, str2, bundle2);
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#2 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24007a(@NonNull Bundle bundle, @NonNull String str, @NonNull String str2, @AnimRes int i, @AnimRes int i2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            Context d = Utils.m24095d();
            m24033a(d, bundle, str, str2, m24034a(d, i, i2));
            if (Build.VERSION.SDK_INT < 16 && (d instanceof Activity)) {
                ((Activity) d).overridePendingTransition(i, i2);
            }
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#2 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24019a(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull String str, @NonNull String str2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            m24033a(activity, bundle, str, str2, (Bundle) null);
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24013a(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull String str, @NonNull String str2, @Nullable Bundle bundle2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            m24033a(activity, bundle, str, str2, bundle2);
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#3 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24012a(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull String str, @NonNull String str2, View... viewArr) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            m24033a(activity, bundle, str, str2, m24037a(activity, viewArr));
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#3 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24017a(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull String str, @NonNull String str2, @AnimRes int i, @AnimRes int i2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            m24033a(activity, bundle, str, str2, m24034a((Context) activity, i, i2));
            if (Build.VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i, i2);
            }
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#3 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static boolean m24032a(@NonNull Intent intent) {
        if (intent != null) {
            return m24029a(intent, Utils.m24095d(), (Bundle) null);
        }
        throw new NullPointerException("Argument 'intent' of type Intent (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static boolean m24028a(@NonNull Intent intent, @Nullable Bundle bundle) {
        if (intent != null) {
            return m24029a(intent, Utils.m24095d(), bundle);
        }
        throw new NullPointerException("Argument 'intent' of type Intent (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static boolean m24031a(@NonNull Intent intent, @AnimRes int i, @AnimRes int i2) {
        if (intent != null) {
            Context d = Utils.m24095d();
            boolean a = m24029a(intent, d, m24034a(d, i, i2));
            if (a && Build.VERSION.SDK_INT < 16 && (d instanceof Activity)) {
                ((Activity) d).overridePendingTransition(i, i2);
            }
            return a;
        }
        throw new NullPointerException("Argument 'intent' of type Intent (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m24064a(@NonNull Activity activity, @NonNull Intent intent) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (intent != null) {
            m24029a(intent, activity, (Bundle) null);
        } else {
            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24058a(@NonNull Activity activity, @NonNull Intent intent, @Nullable Bundle bundle) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (intent != null) {
            m24029a(intent, activity, bundle);
        } else {
            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24057a(@NonNull Activity activity, @NonNull Intent intent, View... viewArr) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (intent != null) {
            m24029a(intent, activity, m24037a(activity, viewArr));
        } else {
            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24062a(@NonNull Activity activity, @NonNull Intent intent, @AnimRes int i, @AnimRes int i2) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (intent != null) {
            m24029a(intent, activity, m24034a((Context) activity, i, i2));
            if (Build.VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i, i2);
            }
        } else {
            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24054a(@NonNull Activity activity, @NonNull Class<? extends Activity> cls, int i) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls != null) {
            m24056a(activity, (Bundle) null, activity.getPackageName(), cls.getName(), i, (Bundle) null);
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24051a(@NonNull Activity activity, @NonNull Class<? extends Activity> cls, int i, @Nullable Bundle bundle) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls != null) {
            m24056a(activity, (Bundle) null, activity.getPackageName(), cls.getName(), i, bundle);
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24050a(@NonNull Activity activity, @NonNull Class<? extends Activity> cls, int i, View... viewArr) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls != null) {
            m24056a(activity, (Bundle) null, activity.getPackageName(), cls.getName(), i, m24037a(activity, viewArr));
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24052a(@NonNull Activity activity, @NonNull Class<? extends Activity> cls, int i, @AnimRes int i2, @AnimRes int i3) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls != null) {
            m24056a(activity, (Bundle) null, activity.getPackageName(), cls.getName(), i, m24034a((Context) activity, i2, i3));
            if (Build.VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i2, i3);
            }
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24026a(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull Class<? extends Activity> cls, int i) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls != null) {
            m24056a(activity, bundle, activity.getPackageName(), cls.getName(), i, (Bundle) null);
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24023a(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull Class<? extends Activity> cls, int i, @Nullable Bundle bundle2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls != null) {
            m24056a(activity, bundle, activity.getPackageName(), cls.getName(), i, bundle2);
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24022a(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull Class<? extends Activity> cls, int i, View... viewArr) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls != null) {
            m24056a(activity, bundle, activity.getPackageName(), cls.getName(), i, m24037a(activity, viewArr));
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24024a(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull Class<? extends Activity> cls, int i, @AnimRes int i2, @AnimRes int i3) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls != null) {
            m24056a(activity, bundle, activity.getPackageName(), cls.getName(), i, m24034a((Context) activity, i2, i3));
            if (Build.VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i2, i3);
            }
        } else {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24018a(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull String str, @NonNull String str2, int i) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            m24056a(activity, bundle, str, str2, i, (Bundle) null);
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#3 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24015a(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull String str, @NonNull String str2, int i, @Nullable Bundle bundle2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            m24056a(activity, bundle, str, str2, i, bundle2);
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#3 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24014a(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull String str, @NonNull String str2, int i, View... viewArr) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            m24056a(activity, bundle, str, str2, i, m24037a(activity, viewArr));
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#3 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24016a(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull String str, @NonNull String str2, int i, @AnimRes int i2, @AnimRes int i3) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 != null) {
            m24056a(activity, bundle, str, str2, i, m24034a((Context) activity, i2, i3));
            if (Build.VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i2, i3);
            }
        } else {
            throw new NullPointerException("Argument 'cls' of type String (#3 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24063a(@NonNull Activity activity, @NonNull Intent intent, int i) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (intent != null) {
            m24030a(intent, activity, i, (Bundle) null);
        } else {
            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24060a(@NonNull Activity activity, @NonNull Intent intent, int i, @Nullable Bundle bundle) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (intent != null) {
            m24030a(intent, activity, i, bundle);
        } else {
            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24059a(@NonNull Activity activity, @NonNull Intent intent, int i, View... viewArr) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (intent != null) {
            m24030a(intent, activity, i, m24037a(activity, viewArr));
        } else {
            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24061a(@NonNull Activity activity, @NonNull Intent intent, int i, @AnimRes int i2, @AnimRes int i3) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (intent != null) {
            m24030a(intent, activity, i, m24034a((Context) activity, i2, i3));
            if (Build.VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i2, i3);
            }
        } else {
            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m23993a(@NonNull Intent[] intentArr) {
        if (intentArr != null) {
            m23991a(intentArr, Utils.m24095d(), (Bundle) null);
            return;
        }
        throw new NullPointerException("Argument 'intents' of type Intent[] (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m23990a(@NonNull Intent[] intentArr, @Nullable Bundle bundle) {
        if (intentArr != null) {
            m23991a(intentArr, Utils.m24095d(), bundle);
            return;
        }
        throw new NullPointerException("Argument 'intents' of type Intent[] (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m23992a(@NonNull Intent[] intentArr, @AnimRes int i, @AnimRes int i2) {
        if (intentArr != null) {
            Context d = Utils.m24095d();
            m23991a(intentArr, d, m24034a(d, i, i2));
            if (Build.VERSION.SDK_INT < 16 && (d instanceof Activity)) {
                ((Activity) d).overridePendingTransition(i, i2);
                return;
            }
            return;
        }
        throw new NullPointerException("Argument 'intents' of type Intent[] (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m24040a(@NonNull Activity activity, @NonNull Intent[] intentArr) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (intentArr != null) {
            m23991a(intentArr, activity, (Bundle) null);
        } else {
            throw new NullPointerException("Argument 'intents' of type Intent[] (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24038a(@NonNull Activity activity, @NonNull Intent[] intentArr, @Nullable Bundle bundle) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (intentArr != null) {
            m23991a(intentArr, activity, bundle);
        } else {
            throw new NullPointerException("Argument 'intents' of type Intent[] (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24039a(@NonNull Activity activity, @NonNull Intent[] intentArr, @AnimRes int i, @AnimRes int i2) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (intentArr != null) {
            m23991a(intentArr, activity, m24034a((Context) activity, i, i2));
            if (Build.VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i, i2);
            }
        } else {
            throw new NullPointerException("Argument 'intents' of type Intent[] (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m24068a() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        m24032a(intent);
    }

    /* renamed from: b */
    public static List<Activity> m23989b() {
        return Utils.m24096c();
    }

    /* renamed from: c */
    public static String m23977c() {
        return m23998a(Utils.m24103a().getPackageName());
    }

    /* renamed from: a */
    public static String m23998a(@NonNull String str) {
        if (str != null) {
            Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.addFlags(268435456);
            for (ResolveInfo resolveInfo : Utils.m24103a().getPackageManager().queryIntentActivities(intent, 0)) {
                if (resolveInfo.activityInfo.packageName.equals(str)) {
                    return resolveInfo.activityInfo.name;
                }
            }
            return "no " + str;
        }
        throw new NullPointerException("Argument 'pkg' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: d */
    public static Activity m23972d() {
        return Utils.m24097b().m24086a();
    }

    /* renamed from: b */
    public static boolean m23984b(Context context) {
        return m24066a(m24035a(context));
    }

    /* renamed from: a */
    public static boolean m24066a(Activity activity) {
        return activity != null && !activity.isFinishing() && (Build.VERSION.SDK_INT < 17 || !activity.isDestroyed());
    }

    /* renamed from: b */
    public static boolean m23987b(@NonNull Activity activity) {
        if (activity != null) {
            for (Activity activity2 : Utils.m24096c()) {
                if (activity2.equals(activity)) {
                    return true;
                }
            }
            return false;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static boolean m23982b(@NonNull Class<? extends Activity> cls) {
        if (cls != null) {
            for (Activity activity : Utils.m24096c()) {
                if (activity.getClass().equals(cls)) {
                    return true;
                }
            }
            return false;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: c */
    public static void m23976c(@NonNull Activity activity) {
        if (activity != null) {
            m24043a(activity, false);
            return;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m24043a(@NonNull Activity activity, boolean z) {
        if (activity != null) {
            activity.finish();
            if (!z) {
                activity.overridePendingTransition(0, 0);
                return;
            }
            return;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m24065a(@NonNull Activity activity, @AnimRes int i, @AnimRes int i2) {
        if (activity != null) {
            activity.finish();
            activity.overridePendingTransition(i, i2);
            return;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: c */
    public static void m23975c(@NonNull Class<? extends Activity> cls) {
        if (cls != null) {
            m24001a(cls, false);
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m24001a(@NonNull Class<? extends Activity> cls, boolean z) {
        if (cls != null) {
            for (Activity activity : Utils.m24096c()) {
                if (activity.getClass().equals(cls)) {
                    activity.finish();
                    if (!z) {
                        activity.overridePendingTransition(0, 0);
                    }
                }
            }
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static void m23981b(@NonNull Class<? extends Activity> cls, @AnimRes int i, @AnimRes int i2) {
        if (cls != null) {
            for (Activity activity : Utils.m24096c()) {
                if (activity.getClass().equals(cls)) {
                    activity.finish();
                    activity.overridePendingTransition(i, i2);
                }
            }
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static boolean m23986b(@NonNull Activity activity, boolean z) {
        if (activity != null) {
            return m24041a(activity, z, false);
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static boolean m24041a(@NonNull Activity activity, boolean z, boolean z2) {
        if (activity != null) {
            LinkedList<Activity> c = Utils.m24096c();
            for (int size = c.size() - 1; size >= 0; size--) {
                Activity activity2 = c.get(size);
                if (activity2.equals(activity)) {
                    if (z) {
                        m24043a(activity2, z2);
                    }
                    return true;
                }
                m24043a(activity2, z2);
            }
            return false;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static boolean m24042a(@NonNull Activity activity, boolean z, @AnimRes int i, @AnimRes int i2) {
        if (activity != null) {
            LinkedList<Activity> c = Utils.m24096c();
            for (int size = c.size() - 1; size >= 0; size--) {
                Activity activity2 = c.get(size);
                if (activity2.equals(activity)) {
                    if (z) {
                        m24065a(activity2, i, i2);
                    }
                    return true;
                }
                m24065a(activity2, i, i2);
            }
            return false;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static boolean m23980b(@NonNull Class<? extends Activity> cls, boolean z) {
        if (cls != null) {
            return m23999a(cls, z, false);
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static boolean m23999a(@NonNull Class<? extends Activity> cls, boolean z, boolean z2) {
        if (cls != null) {
            LinkedList<Activity> c = Utils.m24096c();
            for (int size = c.size() - 1; size >= 0; size--) {
                Activity activity = c.get(size);
                if (activity.getClass().equals(cls)) {
                    if (z) {
                        m24043a(activity, z2);
                    }
                    return true;
                }
                m24043a(activity, z2);
            }
            return false;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static boolean m24000a(@NonNull Class<? extends Activity> cls, boolean z, @AnimRes int i, @AnimRes int i2) {
        if (cls != null) {
            LinkedList<Activity> c = Utils.m24096c();
            for (int size = c.size() - 1; size >= 0; size--) {
                Activity activity = c.get(size);
                if (activity.getClass().equals(cls)) {
                    if (z) {
                        m24065a(activity, i, i2);
                    }
                    return true;
                }
                m24065a(activity, i, i2);
            }
            return false;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: d */
    public static void m23970d(@NonNull Class<? extends Activity> cls) {
        if (cls != null) {
            m23973c(cls, false);
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: c */
    public static void m23973c(@NonNull Class<? extends Activity> cls, boolean z) {
        if (cls != null) {
            LinkedList<Activity> c = Utils.m24096c();
            for (int size = c.size() - 1; size >= 0; size--) {
                Activity activity = c.get(size);
                if (!activity.getClass().equals(cls)) {
                    m24043a(activity, z);
                }
            }
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: c */
    public static void m23974c(@NonNull Class<? extends Activity> cls, @AnimRes int i, @AnimRes int i2) {
        if (cls != null) {
            LinkedList<Activity> c = Utils.m24096c();
            for (int size = c.size() - 1; size >= 0; size--) {
                Activity activity = c.get(size);
                if (!activity.getClass().equals(cls)) {
                    m24065a(activity, i, i2);
                }
            }
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: e */
    public static void m23969e() {
        m23994a(false);
    }

    /* renamed from: a */
    public static void m23994a(boolean z) {
        LinkedList<Activity> c = Utils.m24096c();
        for (int size = c.size() - 1; size >= 0; size--) {
            Activity activity = c.get(size);
            activity.finish();
            if (!z) {
                activity.overridePendingTransition(0, 0);
            }
        }
    }

    /* renamed from: a */
    public static void m24067a(@AnimRes int i, @AnimRes int i2) {
        LinkedList<Activity> c = Utils.m24096c();
        for (int size = c.size() - 1; size >= 0; size--) {
            Activity activity = c.get(size);
            activity.finish();
            activity.overridePendingTransition(i, i2);
        }
    }

    /* renamed from: f */
    public static void m23966f() {
        m23978b(false);
    }

    /* renamed from: b */
    public static void m23978b(boolean z) {
        LinkedList<Activity> c = Utils.m24096c();
        for (int size = c.size() - 2; size >= 0; size--) {
            m24043a(c.get(size), z);
        }
    }

    /* renamed from: b */
    public static void m23988b(@AnimRes int i, @AnimRes int i2) {
        LinkedList<Activity> c = Utils.m24096c();
        for (int size = c.size() - 2; size >= 0; size--) {
            m24065a(c.get(size), i, i2);
        }
    }

    /* renamed from: d */
    public static Drawable m23971d(@NonNull Activity activity) {
        if (activity != null) {
            return m24036a(activity.getComponentName());
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: e */
    public static Drawable m23967e(@NonNull Class<? extends Activity> cls) {
        if (cls != null) {
            return m24036a(new ComponentName(Utils.m24103a(), cls));
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static Drawable m24036a(@NonNull ComponentName componentName) {
        if (componentName != null) {
            try {
                return Utils.m24103a().getPackageManager().getActivityIcon(componentName);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            throw new NullPointerException("Argument 'activityName' of type ComponentName (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: e */
    public static Drawable m23968e(@NonNull Activity activity) {
        if (activity != null) {
            return m23985b(activity.getComponentName());
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: f */
    public static Drawable m23965f(@NonNull Class<? extends Activity> cls) {
        if (cls != null) {
            return m23985b(new ComponentName(Utils.m24103a(), cls));
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static Drawable m23985b(@NonNull ComponentName componentName) {
        if (componentName != null) {
            try {
                return Utils.m24103a().getPackageManager().getActivityLogo(componentName);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            throw new NullPointerException("Argument 'activityName' of type ComponentName (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    private static void m24033a(Context context, Bundle bundle, String str, String str2, @Nullable Bundle bundle2) {
        Intent intent = new Intent("android.intent.action.VIEW");
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setComponent(new ComponentName(str, str2));
        m24029a(intent, context, bundle2);
    }

    /* renamed from: a */
    private static boolean m24029a(Intent intent, Context context, Bundle bundle) {
        if (!m23983b(intent)) {
            Log.e("ActivityUtils", "intent is unavailable");
            return false;
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        if (bundle == null || Build.VERSION.SDK_INT < 16) {
            context.startActivity(intent);
            return true;
        }
        context.startActivity(intent, bundle);
        return true;
    }

    /* renamed from: b */
    private static boolean m23983b(Intent intent) {
        return Utils.m24103a().getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }

    /* renamed from: a */
    private static boolean m24056a(Activity activity, Bundle bundle, String str, String str2, int i, @Nullable Bundle bundle2) {
        Intent intent = new Intent("android.intent.action.VIEW");
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setComponent(new ComponentName(str, str2));
        return m24030a(intent, activity, i, bundle2);
    }

    /* renamed from: a */
    private static boolean m24030a(Intent intent, Activity activity, int i, @Nullable Bundle bundle) {
        if (!m23983b(intent)) {
            Log.e("ActivityUtils", "intent is unavailable");
            return false;
        } else if (bundle == null || Build.VERSION.SDK_INT < 16) {
            activity.startActivityForResult(intent, i);
            return true;
        } else {
            activity.startActivityForResult(intent, i, bundle);
            return true;
        }
    }

    /* renamed from: a */
    private static void m23991a(Intent[] intentArr, Context context, @Nullable Bundle bundle) {
        if (!(context instanceof Activity)) {
            for (Intent intent : intentArr) {
                intent.addFlags(268435456);
            }
        }
        if (bundle == null || Build.VERSION.SDK_INT < 16) {
            context.startActivities(intentArr);
        } else {
            context.startActivities(intentArr, bundle);
        }
    }

    /* renamed from: a */
    private static Bundle m24034a(Context context, int i, int i2) {
        return ActivityOptionsCompat.makeCustomAnimation(context, i, i2).toBundle();
    }

    /* renamed from: a */
    private static Bundle m24037a(Activity activity, View[] viewArr) {
        int length;
        if (Build.VERSION.SDK_INT < 21 || viewArr == null || (length = viewArr.length) <= 0) {
            return null;
        }
        Pair[] pairArr = new Pair[length];
        for (int i = 0; i < length; i++) {
            pairArr[i] = Pair.create(viewArr[i], viewArr[i].getTransitionName());
        }
        return ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairArr).toBundle();
    }
}
