package p110z1;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.Context;
import android.os.Binder;
import android.os.Build;
import android.provider.Settings;
import com.blankj.utilcode.util.SPUtils;
import java.util.concurrent.Callable;

/* renamed from: z1.anl */
/* loaded from: classes3.dex */
public class FloatWindowManager {

    /* compiled from: FloatWindowManager.java */
    /* renamed from: z1.anl$a */
    /* loaded from: classes3.dex */
    public interface AbstractC3717a {
        /* renamed from: a */
        void mo12295a(int i);
    }

    /* renamed from: a */
    public static void m12304a(final Context context, Observer<Integer> assVar) {
        Observable.m10527a((ObservableOnSubscribe) new ObservableOnSubscribe<Integer>() { // from class: z1.anl.1
            @Override // p110z1.ObservableOnSubscribe
            /* renamed from: a */
            public void mo10164a(@AbstractC3889atm ObservableEmitter<Integer> asnVar) throws Exception {
                int i = 2;
                if (!ann.m12268g() && !ann.m12269f()) {
                    i = (!FloatWindowPermissionChecker.m12294a(context) || !FloatWindowManager.m12299d(context) || !FloatWindowManager.m12300c(context)) ? 4 : 1;
                }
                asnVar.onNext(Integer.valueOf(i));
            }
        }).m10350c(Schedulers.m9047b()).m10277f(Schedulers.m9047b()).m10491a(AndroidSchedulers.m10005a()).subscribe(assVar);
    }

    /* renamed from: b */
    public static void m12301b(final Context context, Observer<Integer> assVar) {
        Observable.m10527a((ObservableOnSubscribe) new ObservableOnSubscribe<Integer>() { // from class: z1.anl.2
            @Override // p110z1.ObservableOnSubscribe
            /* renamed from: a */
            public void mo10164a(@AbstractC3889atm ObservableEmitter<Integer> asnVar) throws Exception {
                int i = 1;
                if (!SPUtils.m23341a().m23318b(ShareVal.f16593c, false) && !ann.m12268g() && !ann.m12269f() && (!FloatWindowPermissionChecker.m12294a(context) || !FloatWindowManager.m12299d(context) || !FloatWindowManager.m12300c(context))) {
                    i = 4;
                }
                asnVar.onNext(Integer.valueOf(i));
            }
        }).m10350c(Schedulers.m9047b()).m10277f(Schedulers.m9047b()).m10491a(AndroidSchedulers.m10005a()).subscribe(assVar);
    }

    /* renamed from: a */
    public static Boolean m12303a(final Context context, boolean z, final AbstractC3717a aVar) {
        if (CommonUtil.m11899a(context.getApplicationContext())) {
            return true;
        }
        if (!z && SPUtils.m23341a().m23318b(ShareVal.f16593c, false)) {
            return true;
        }
        VUiKit.m11713a().mo3332a(new Callable<Integer>() { // from class: z1.anl.5
            /* renamed from: a */
            public Integer call() throws Exception {
                int i;
                if (ann.m12268g()) {
                    i = 1;
                } else if (ann.m12269f()) {
                    i = 2;
                } else if (!FloatWindowPermissionChecker.m12294a(context) || !FloatWindowManager.m12299d(context) || !FloatWindowManager.m12300c(context)) {
                    i = 4;
                } else {
                    i = (FloatWindowPermissionChecker.m12294a(context) || FloatWindowManager.m12299d(context) || FloatWindowManager.m12300c(context)) ? 5 : 3;
                }
                if (!SPUtils.m23341a().m23318b(ShareVal.f16594d, false) && i == 5) {
                    i = 3;
                }
                return Integer.valueOf(i);
            }
        }).mo3282b(new DoneCallback<Integer>() { // from class: z1.anl.4
            /* renamed from: a */
            public void onDone(Integer num) {
                AbstractC3717a.this.mo12295a(num.intValue());
            }
        }).mo3285a(new FailCallback<Throwable>() { // from class: z1.anl.3
            /* renamed from: a */
            public void onFail(Throwable th) {
                AbstractC3717a.this.mo12295a(-1);
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public static boolean m12300c(Context context) {
        Boolean bool;
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                bool = (Boolean) Settings.class.getDeclaredMethod("canDrawOverlays", Context.class).invoke(null, context);
            } catch (Exception unused) {
            }
            return bool.booleanValue();
        }
        bool = true;
        return bool.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public static boolean m12299d(Context context) {
        if (Build.VERSION.SDK_INT >= 19) {
            return m12305a(context, 24);
        }
        return true;
    }

    @TargetApi(19)
    /* renamed from: a */
    private static boolean m12305a(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 19) {
            try {
                return ((Integer) AppOpsManager.class.getDeclaredMethod("checkOp", Integer.TYPE, Integer.TYPE, String.class).invoke((AppOpsManager) context.getSystemService("appops"), Integer.valueOf(i), Integer.valueOf(Binder.getCallingUid()), context.getPackageName())).intValue() == 0;
            } catch (Exception unused) {
            }
        }
        return false;
    }
}
