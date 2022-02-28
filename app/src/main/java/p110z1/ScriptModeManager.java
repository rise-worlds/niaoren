package p110z1;

import android.os.Build;
import com.angel.nrzs.App;
import com.blankj.utilcode.util.SPUtils;
import com.cyjh.mobileanjian.ipc.utils.RootUtil;

/* renamed from: z1.em */
/* loaded from: classes3.dex */
public class ScriptModeManager {

    /* renamed from: c */
    private static ScriptModeManager f21476c;

    /* renamed from: a */
    private int f21477a = 0;

    /* renamed from: b */
    private int f21478b = 0;

    /* renamed from: a */
    public int m3103a() {
        if (!m3100c()) {
            this.f21477a = 1;
        }
        if (this.f21477a == 0) {
            this.f21477a = aly.m12532a();
            if (this.f21477a == 0) {
                if (RootUtil.isRoot()) {
                    this.f21477a = 1;
                } else {
                    this.f21477a = 2;
                }
            }
        }
        return this.f21477a;
    }

    /* renamed from: b */
    public int m3101b() {
        if (!m3100c()) {
            this.f21477a = 1;
        }
        if (this.f21477a == 0) {
            this.f21477a = aly.m12532a();
            if (this.f21477a == 0) {
                if (SPUtils.m23341a().m23318b(ShareVal.f16596f, false)) {
                    this.f21477a = 1;
                    aly.m12530b(this.f21477a);
                } else if (RootUtil.isRoot()) {
                    this.f21477a = 1;
                } else {
                    this.f21477a = 2;
                }
            }
        }
        return this.f21477a;
    }

    /* renamed from: a */
    public void m3102a(int i) {
        if (!m3100c()) {
            this.f21477a = 1;
            return;
        }
        aly.m12530b(i);
        this.f21477a = i;
    }

    /* renamed from: c */
    public boolean m3100c() {
        if (this.f21478b == 0) {
            if (Build.VERSION.SDK_INT < 21 || CommonUtil.m11899a(App.getInstance())) {
                this.f21478b = 2;
            } else {
                this.f21478b = 1;
            }
        }
        int i = this.f21478b;
        return i == 1 || i != 2;
    }

    /* renamed from: d */
    public void m3099d() {
        this.f21477a = aly.m12532a();
        if (this.f21477a == 0) {
            if (RootUtil.isRoot()) {
                this.f21477a = 1;
            } else {
                this.f21477a = 2;
            }
            aly.m12530b(this.f21477a);
        }
    }

    /* renamed from: e */
    public void m3098e() {
        this.f21478b = 0;
    }

    private ScriptModeManager() {
    }

    /* renamed from: f */
    public static ScriptModeManager m3097f() {
        ScriptModeManager emVar = f21476c;
        if (emVar == null) {
            synchronized (ScriptModeManager.class) {
                emVar = f21476c;
                if (emVar == null) {
                    emVar = new ScriptModeManager();
                    f21476c = emVar;
                }
            }
        }
        return emVar;
    }
}
