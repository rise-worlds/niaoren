package com.blankj.utilcode.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.p003v4.content.ContextCompat;
import android.util.Log;
import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import p110z1.PermissionConstants;

/* loaded from: classes.dex */
public final class PermissionUtils {

    /* renamed from: a */
    private static final List<String> f6493a = m24203a();

    /* renamed from: b */
    private static PermissionUtils f6494b;

    /* renamed from: l */
    private static AbstractC0938c f6495l;

    /* renamed from: m */
    private static AbstractC0938c f6496m;

    /* renamed from: c */
    private AbstractC0936b f6497c;

    /* renamed from: d */
    private AbstractC0938c f6498d;

    /* renamed from: e */
    private AbstractC0935a f6499e;

    /* renamed from: f */
    private AbstractC0939d f6500f;

    /* renamed from: g */
    private Set<String> f6501g = new LinkedHashSet();

    /* renamed from: h */
    private List<String> f6502h;

    /* renamed from: i */
    private List<String> f6503i;

    /* renamed from: j */
    private List<String> f6504j;

    /* renamed from: k */
    private List<String> f6505k;

    /* renamed from: com.blankj.utilcode.util.PermissionUtils$a */
    /* loaded from: classes.dex */
    public interface AbstractC0935a {
        /* renamed from: a */
        void m24166a(List<String> list);

        /* renamed from: a */
        void m24165a(List<String> list, List<String> list2);
    }

    /* renamed from: com.blankj.utilcode.util.PermissionUtils$b */
    /* loaded from: classes.dex */
    public interface AbstractC0936b {

        /* renamed from: com.blankj.utilcode.util.PermissionUtils$b$a */
        /* loaded from: classes.dex */
        public interface AbstractC0937a {
            /* renamed from: a */
            void mo24163a(boolean z);
        }

        /* renamed from: a */
        void m24164a(AbstractC0937a aVar);
    }

    /* renamed from: com.blankj.utilcode.util.PermissionUtils$c */
    /* loaded from: classes.dex */
    public interface AbstractC0938c {
        /* renamed from: a */
        void m24162a();

        /* renamed from: b */
        void m24161b();
    }

    /* renamed from: com.blankj.utilcode.util.PermissionUtils$d */
    /* loaded from: classes.dex */
    public interface AbstractC0939d {
        /* renamed from: a */
        void m24160a(Activity activity);
    }

    /* renamed from: a */
    public static List<String> m24203a() {
        return m24193a(Utils.m24103a().getPackageName());
    }

    /* renamed from: a */
    public static List<String> m24193a(String str) {
        try {
            String[] strArr = Utils.m24103a().getPackageManager().getPackageInfo(str, 4096).requestedPermissions;
            if (strArr == null) {
                return Collections.emptyList();
            }
            return Arrays.asList(strArr);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /* renamed from: a */
    public static boolean m24192a(String... strArr) {
        for (String str : strArr) {
            if (!m24185b(str)) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: b */
    private static boolean m24185b(String str) {
        return Build.VERSION.SDK_INT < 23 || ContextCompat.checkSelfPermission(Utils.m24103a(), str) == 0;
    }

    @RequiresApi(api = 23)
    /* renamed from: b */
    public static boolean m24191b() {
        return Settings.System.canWrite(Utils.m24103a());
    }

    @RequiresApi(api = 23)
    /* renamed from: a */
    public static void m24197a(AbstractC0938c cVar) {
        if (!m24191b()) {
            f6495l = cVar;
            PermissionActivity.m24167a(Utils.m24103a(), 2);
        } else if (cVar != null) {
            cVar.m24162a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(23)
    /* renamed from: c */
    public static void m24181c(Activity activity, int i) {
        Intent intent = new Intent("android.settings.action.MANAGE_WRITE_SETTINGS");
        intent.setData(Uri.parse("package:" + Utils.m24103a().getPackageName()));
        if (!m24200a(intent)) {
            m24178d();
        } else {
            activity.startActivityForResult(intent, i);
        }
    }

    @RequiresApi(api = 23)
    /* renamed from: c */
    public static boolean m24183c() {
        return Settings.canDrawOverlays(Utils.m24103a());
    }

    @RequiresApi(api = 23)
    /* renamed from: b */
    public static void m24188b(AbstractC0938c cVar) {
        if (!m24183c()) {
            f6496m = cVar;
            PermissionActivity.m24167a(Utils.m24103a(), 3);
        } else if (cVar != null) {
            cVar.m24162a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(23)
    /* renamed from: d */
    public static void m24177d(Activity activity, int i) {
        Intent intent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION");
        intent.setData(Uri.parse("package:" + Utils.m24103a().getPackageName()));
        if (!m24200a(intent)) {
            m24178d();
        } else {
            activity.startActivityForResult(intent, i);
        }
    }

    /* renamed from: d */
    public static void m24178d() {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + Utils.m24103a().getPackageName()));
        if (m24200a(intent)) {
            Utils.m24103a().startActivity(intent.addFlags(268435456));
        }
    }

    /* renamed from: b */
    public static PermissionUtils m24184b(String... strArr) {
        return new PermissionUtils(strArr);
    }

    /* renamed from: a */
    private static boolean m24200a(Intent intent) {
        return Utils.m24103a().getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }

    private PermissionUtils(String... strArr) {
        String[] a;
        for (String str : strArr) {
            for (String str2 : PermissionConstants.m2833a(str)) {
                if (f6493a.contains(str2)) {
                    this.f6501g.add(str2);
                }
            }
        }
        f6494b = this;
    }

    /* renamed from: a */
    public PermissionUtils m24198a(AbstractC0936b bVar) {
        this.f6497c = bVar;
        return this;
    }

    /* renamed from: c */
    public PermissionUtils m24180c(AbstractC0938c cVar) {
        this.f6498d = cVar;
        return this;
    }

    /* renamed from: a */
    public PermissionUtils m24199a(AbstractC0935a aVar) {
        this.f6499e = aVar;
        return this;
    }

    /* renamed from: a */
    public PermissionUtils m24196a(AbstractC0939d dVar) {
        this.f6500f = dVar;
        return this;
    }

    /* renamed from: e */
    public void m24174e() {
        this.f6503i = new ArrayList();
        this.f6502h = new ArrayList();
        if (Build.VERSION.SDK_INT < 23) {
            this.f6503i.addAll(this.f6501g);
            m24168j();
            return;
        }
        for (String str : this.f6501g) {
            if (m24185b(str)) {
                this.f6503i.add(str);
            } else {
                this.f6502h.add(str);
            }
        }
        if (this.f6502h.isEmpty()) {
            m24168j();
        } else {
            m24169i();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(api = 23)
    /* renamed from: i */
    public void m24169i() {
        this.f6504j = new ArrayList();
        this.f6505k = new ArrayList();
        PermissionActivity.m24167a(Utils.m24103a(), 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(api = 23)
    /* renamed from: a */
    public boolean m24202a(final Activity activity) {
        boolean z = false;
        if (this.f6497c != null) {
            Iterator<String> it = this.f6502h.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (activity.shouldShowRequestPermissionRationale(it.next())) {
                        m24190b(activity);
                        this.f6497c.m24164a(new AbstractC0936b.AbstractC0937a() { // from class: com.blankj.utilcode.util.PermissionUtils.1
                            @Override // com.blankj.utilcode.util.PermissionUtils.AbstractC0936b.AbstractC0937a
                            /* renamed from: a */
                            public void mo24163a(boolean z2) {
                                activity.finish();
                                if (z2) {
                                    PermissionUtils.this.m24169i();
                                } else {
                                    PermissionUtils.this.m24168j();
                                }
                            }
                        });
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
            this.f6497c = null;
        }
        return z;
    }

    /* renamed from: b */
    private void m24190b(Activity activity) {
        for (String str : this.f6502h) {
            if (m24185b(str)) {
                this.f6503i.add(str);
            } else {
                this.f6504j.add(str);
                if (!activity.shouldShowRequestPermissionRationale(str)) {
                    this.f6505k.add(str);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j */
    public void m24168j() {
        if (this.f6498d != null) {
            if (this.f6502h.size() == 0 || this.f6501g.size() == this.f6503i.size()) {
                this.f6498d.m24162a();
            } else if (!this.f6504j.isEmpty()) {
                this.f6498d.m24161b();
            }
            this.f6498d = null;
        }
        if (this.f6499e != null) {
            if (this.f6502h.size() == 0 || this.f6501g.size() == this.f6503i.size()) {
                this.f6499e.m24166a(this.f6503i);
            } else if (!this.f6504j.isEmpty()) {
                this.f6499e.m24165a(this.f6505k, this.f6504j);
            }
            this.f6499e = null;
        }
        this.f6497c = null;
        this.f6500f = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m24182c(Activity activity) {
        m24190b(activity);
        m24168j();
    }

    @RequiresApi(api = 23)
    /* loaded from: classes.dex */
    public static class PermissionActivity extends Activity {

        /* renamed from: a */
        public static final int f6508a = 1;

        /* renamed from: b */
        public static final int f6509b = 2;

        /* renamed from: c */
        public static final int f6510c = 3;

        /* renamed from: d */
        private static final String f6511d = "TYPE";

        /* renamed from: a */
        public static void m24167a(Context context, int i) {
            Intent intent = new Intent(context, PermissionActivity.class);
            intent.addFlags(268435456);
            intent.putExtra(f6511d, i);
            context.startActivity(intent);
        }

        @Override // android.app.Activity
        protected void onCreate(@Nullable Bundle bundle) {
            getWindow().addFlags(262160);
            int intExtra = getIntent().getIntExtra(f6511d, 1);
            if (intExtra == 1) {
                if (PermissionUtils.f6494b == null) {
                    super.onCreate(bundle);
                    Log.e("PermissionUtils", "request permissions failed");
                    finish();
                    return;
                }
                if (PermissionUtils.f6494b.f6500f != null) {
                    PermissionUtils.f6494b.f6500f.m24160a(this);
                }
                super.onCreate(bundle);
                if (!PermissionUtils.f6494b.m24202a(this) && PermissionUtils.f6494b.f6502h != null) {
                    int size = PermissionUtils.f6494b.f6502h.size();
                    if (size <= 0) {
                        finish();
                    } else {
                        requestPermissions((String[]) PermissionUtils.f6494b.f6502h.toArray(new String[size]), 1);
                    }
                }
            } else if (intExtra == 2) {
                super.onCreate(bundle);
                PermissionUtils.m24181c(this, 2);
            } else if (intExtra == 3) {
                super.onCreate(bundle);
                PermissionUtils.m24177d(this, 3);
            }
        }

        @Override // android.app.Activity
        public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
            if (strArr == null) {
                throw new NullPointerException("Argument 'permissions' of type String[] (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            } else if (iArr != null) {
                PermissionUtils.f6494b.m24182c(this);
                finish();
            } else {
                throw new NullPointerException("Argument 'grantResults' of type int[] (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
        }

        @Override // android.app.Activity, android.view.Window.Callback
        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            finish();
            return true;
        }

        @Override // android.app.Activity
        protected void onActivityResult(int i, int i2, Intent intent) {
            if (i == 2) {
                if (PermissionUtils.f6495l != null) {
                    if (PermissionUtils.m24191b()) {
                        PermissionUtils.f6495l.m24162a();
                    } else {
                        PermissionUtils.f6495l.m24161b();
                    }
                    AbstractC0938c unused = PermissionUtils.f6495l = null;
                } else {
                    return;
                }
            } else if (i == 3) {
                if (PermissionUtils.f6496m != null) {
                    Utils.m24098a(new Runnable() { // from class: com.blankj.utilcode.util.PermissionUtils.PermissionActivity.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (PermissionUtils.m24183c()) {
                                PermissionUtils.f6496m.m24162a();
                            } else {
                                PermissionUtils.f6496m.m24161b();
                            }
                            AbstractC0938c unused2 = PermissionUtils.f6496m = null;
                        }
                    }, 100L);
                } else {
                    return;
                }
            }
            finish();
        }
    }
}
