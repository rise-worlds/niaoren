package p110z1;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.Context;
import android.os.Binder;
import android.os.Build;
import android.util.Log;
import com.common.utils.log.LogUtils;

/* renamed from: z1.acy */
/* loaded from: classes3.dex */
public class FloatingPermissionCompat {

    /* renamed from: a */
    private static final String f15276a = "FloatPermissionCompat";

    /* renamed from: b */
    private static FloatingPermissionCompat f15277b;

    /* renamed from: c */
    private AbstractC3335a f15278c;

    /* compiled from: FloatingPermissionCompat.java */
    /* renamed from: z1.acy$a */
    /* loaded from: classes3.dex */
    public interface AbstractC3335a {
        /* renamed from: a */
        boolean mo14318a();

        /* renamed from: a */
        boolean mo14317a(Context context);

        /* renamed from: b */
        boolean mo14323b(Context context);
    }

    private FloatingPermissionCompat() {
        if (Build.VERSION.SDK_INT < 23) {
            if (acz.m14328d()) {
                this.f15278c = new MiuiCompatImpl();
            } else if (acz.m14327e()) {
                this.f15278c = new MeizuCompatImpl();
            } else if (acz.m14329c()) {
                this.f15278c = new HuaweiCompatImpl();
            } else if (acz.m14326f()) {
                this.f15278c = new QihooCompatImpl();
            } else {
                this.f15278c = new BelowApi23CompatImpl() { // from class: z1.acy.1
                    @Override // p110z1.FloatingPermissionCompat.AbstractC3335a
                    /* renamed from: a */
                    public boolean mo14318a() {
                        return false;
                    }

                    @Override // p110z1.FloatingPermissionCompat.AbstractC3335a
                    /* renamed from: a */
                    public boolean mo14317a(Context context) {
                        return false;
                    }
                };
            }
        } else if (acz.m14327e()) {
            this.f15278c = new MeizuCompatImpl();
        } else {
            this.f15278c = new Api23CompatImpl();
        }
    }

    /* renamed from: a */
    public static FloatingPermissionCompat m14338a() {
        if (f15277b == null) {
            f15277b = new FloatingPermissionCompat();
        }
        return f15277b;
    }

    /* renamed from: a */
    public boolean m14337a(Context context) {
        return this.f15278c.mo14323b(context);
    }

    /* renamed from: b */
    public boolean m14335b() {
        return this.f15278c.mo14318a();
    }

    /* renamed from: b */
    public boolean m14334b(Context context) {
        if (!m14335b()) {
            return false;
        }
        return this.f15278c.mo14317a(context);
    }

    @TargetApi(19)
    /* renamed from: a */
    public static boolean m14336a(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 19) {
            try {
                return ((Integer) AppOpsManager.class.getDeclaredMethod("checkOp", Integer.TYPE, Integer.TYPE, String.class).invoke((AppOpsManager) context.getSystemService("appops"), Integer.valueOf(i), Integer.valueOf(Binder.getCallingUid()), context.getPackageName())).intValue() == 0;
            } catch (Exception e) {
                LogUtils.m22036e(f15276a, Log.getStackTraceString(e));
            }
        } else {
            LogUtils.m22036e(f15276a, "Below API 19 cannot invoke!");
        }
        return false;
    }
}
