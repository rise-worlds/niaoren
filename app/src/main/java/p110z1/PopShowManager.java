package p110z1;

import android.content.Context;
import android.text.TextUtils;
import com.blankj.utilcode.util.SPUtils;
import com.nrzs.data.ddy.bean.respond.OrderDaileInfo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.tools.ant.util.DateUtils;

/* renamed from: z1.el */
/* loaded from: classes3.dex */
public class PopShowManager {

    /* renamed from: h */
    private static PopShowManager f21461h;

    /* renamed from: i */
    private static ThreadLocal<SimpleDateFormat> f21462i = new ThreadLocal<>();

    /* renamed from: b */
    private String f21464b;

    /* renamed from: c */
    private String f21465c;

    /* renamed from: d */
    private boolean f21466d;

    /* renamed from: e */
    private Context f21467e;

    /* renamed from: f */
    private List<C5314b> f21468f = new ArrayList();

    /* renamed from: g */
    private int f21469g = 0;

    /* renamed from: a */
    public boolean f21463a = false;

    /* compiled from: PopShowManager.java */
    /* renamed from: z1.el$a */
    /* loaded from: classes3.dex */
    public interface AbstractC5313a {
        /* renamed from: a */
        void mo3109a(int i);
    }

    /* renamed from: a */
    public static PopShowManager m3117a(Context context) {
        if (f21461h == null) {
            synchronized (VersionManager.class) {
                if (f21461h == null) {
                    f21461h = new PopShowManager(context);
                }
            }
        }
        return f21461h;
    }

    public PopShowManager(Context context) {
        this.f21467e = context;
        m3119a();
    }

    /* renamed from: a */
    public void m3119a() {
        m3111d();
        m3110e();
    }

    /* renamed from: d */
    private void m3111d() {
        this.f21465c = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
    }

    /* renamed from: e */
    private void m3110e() {
        this.f21468f.clear();
        if (LoginManager.m12620d().m12606r()) {
            if (LoginManager.m12620d().m12610n()) {
                C5314b bVar = new C5314b();
                bVar.f21473b = 0;
                this.f21468f.add(bVar);
            }
            if (LoginManager.m12620d().m12608p() != null && LoginManager.m12620d().m12608p().size() > 0) {
                for (int i = 0; i < LoginManager.m12620d().m12608p().size(); i++) {
                    C5314b bVar2 = new C5314b();
                    bVar2.f21473b = 1;
                    bVar2.f21475d = LoginManager.m12620d().m12608p().get(i);
                    this.f21468f.add(bVar2);
                }
            }
        }
    }

    /* renamed from: b */
    public void m3114b() {
        this.f21466d = SPUtils.m23341a().m23318b(ShareVal.f16578I, false);
        this.f21464b = SPUtils.m23341a().m23320b(ShareVal.f16577H, "");
        m3111d();
        if (TextUtils.isEmpty(this.f21464b)) {
            this.f21464b = this.f21465c;
            SPUtils.m23341a().m23332a(ShareVal.f16577H, this.f21465c);
        }
        if (LoginManager.m12620d().m12606r() && this.f21468f.size() > 0) {
            try {
                if (!m3116a(this.f21464b)) {
                    SPUtils.m23341a().m23322b(ShareVal.f16579J, 0);
                    this.f21469g = 0;
                    m3118a(this.f21469g);
                } else if (!this.f21466d) {
                    m3118a(0);
                } else {
                    this.f21469g = SPUtils.m23341a().m23315c(ShareVal.f16579J, 0);
                    m3118a(this.f21469g + 1);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m3118a(int i) {
        if (!this.f21463a && i < this.f21468f.size()) {
            C5314b bVar = this.f21468f.get(i);
            if (bVar.f21473b == 0) {
                NrVipExpireDialog exVar = new NrVipExpireDialog(this.f21467e, new AbstractC5313a() { // from class: z1.el.1
                    @Override // p110z1.PopShowManager.AbstractC5313a
                    /* renamed from: a */
                    public void mo3109a(int i2) {
                        PopShowManager.this.m3118a(i2);
                    }
                });
                this.f21468f.get(i).f21474c = true;
                exVar.show();
            } else if (bVar.f21473b == 1) {
                DdyExpireDialog evVar = new DdyExpireDialog(this.f21467e, i, bVar.f21475d, new AbstractC5313a() { // from class: z1.el.2
                    @Override // p110z1.PopShowManager.AbstractC5313a
                    /* renamed from: a */
                    public void mo3109a(int i2) {
                        PopShowManager.this.m3118a(i2);
                    }
                });
                this.f21468f.get(i).f21474c = true;
                evVar.show();
            }
            SPUtils.m23341a().m23332a(ShareVal.f16577H, this.f21465c);
            SPUtils.m23341a().m23328a(ShareVal.f16578I, true);
            SPUtils.m23341a().m23322b(ShareVal.f16579J, i);
        }
    }

    /* renamed from: a */
    public static boolean m3116a(String str) throws ParseException {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date(System.currentTimeMillis()));
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(m3112c().parse(str));
        return instance2.get(1) == instance.get(1) && instance2.get(6) - instance.get(6) == 0;
    }

    /* renamed from: b */
    public static boolean m3113b(String str) throws ParseException {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date(System.currentTimeMillis()));
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(m3112c().parse(str));
        return instance2.get(1) == instance.get(1) && instance2.get(6) - instance.get(6) == -1;
    }

    /* renamed from: c */
    public static SimpleDateFormat m3112c() {
        if (f21462i.get() == null) {
            f21462i.set(new SimpleDateFormat(DateUtils.ISO8601_DATE_PATTERN, Locale.CHINA));
        }
        return f21462i.get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PopShowManager.java */
    /* renamed from: z1.el$b */
    /* loaded from: classes3.dex */
    public class C5314b {

        /* renamed from: b */
        private int f21473b;

        /* renamed from: c */
        private boolean f21474c = false;

        /* renamed from: d */
        private OrderDaileInfo f21475d;

        C5314b() {
        }
    }
}
