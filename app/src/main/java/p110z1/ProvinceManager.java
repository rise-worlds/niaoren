package p110z1;

import com.nrzs.http.Api;
import com.nrzs.http.BaseRepository;
import com.nrzs.http.UICallback;

/* renamed from: z1.ale */
/* loaded from: classes3.dex */
public class ProvinceManager {

    /* renamed from: a */
    public static String f16324a = "http://ip.taobao.com/service/getIpInfo.php?ip=myip";

    /* renamed from: b */
    private String f16325b;

    /* renamed from: c */
    private BaseRepository<C3647b> f16326c;

    /* renamed from: d */
    private UICallback<C3647b> f16327d;

    /* renamed from: b */
    public void m12602b() {
    }

    /* renamed from: a */
    public void m12603a() {
        if (this.f16326c == null) {
            this.f16326c = new BaseRepository<>();
        }
        if (this.f16327d == null) {
            this.f16327d = new UICallback<C3647b>() { // from class: z1.ale.1
                @Override // com.nrzs.http.UICallback
                /* renamed from: a */
                public void mo3021a(Throwable th) {
                }

                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo3022a(C3647b bVar) {
                }
            };
        }
        this.f16326c.m18568a(Api.m18586a(f16324a)).m18571a(this.f16327d).m18570a(C3647b.class).m18574a(11);
    }

    /* compiled from: ProvinceManager.java */
    /* renamed from: z1.ale$c */
    /* loaded from: classes3.dex */
    private static class C3648c {

        /* renamed from: a */
        private static final ProvinceManager f16346a = new ProvinceManager();

        private C3648c() {
        }
    }

    private ProvinceManager() {
    }

    /* renamed from: c */
    public static ProvinceManager m12601c() {
        return C3648c.f16346a;
    }

    /* compiled from: ProvinceManager.java */
    /* renamed from: z1.ale$b */
    /* loaded from: classes3.dex */
    public class C3647b {

        /* renamed from: a */
        public int f16343a;

        /* renamed from: b */
        public C3646a f16344b;

        public C3647b() {
        }

        public String toString() {
            return "IpInfoWrapper{code=" + this.f16343a + ", data=" + this.f16344b.toString() + '}';
        }
    }

    /* compiled from: ProvinceManager.java */
    /* renamed from: z1.ale$a */
    /* loaded from: classes3.dex */
    public class C3646a {

        /* renamed from: a */
        public String f16329a;

        /* renamed from: b */
        public String f16330b;

        /* renamed from: c */
        public String f16331c;

        /* renamed from: d */
        public String f16332d;

        /* renamed from: e */
        public String f16333e;

        /* renamed from: f */
        public String f16334f;

        /* renamed from: g */
        public String f16335g;

        /* renamed from: h */
        public String f16336h;

        /* renamed from: i */
        public String f16337i;

        /* renamed from: j */
        public String f16338j;

        /* renamed from: k */
        public String f16339k;

        /* renamed from: l */
        public String f16340l;

        /* renamed from: m */
        public String f16341m;

        public C3646a() {
        }

        public String toString() {
            return "IpInfo{ip='" + this.f16329a + "', country='" + this.f16330b + "', area='" + this.f16331c + "', region='" + this.f16332d + "', city='" + this.f16333e + "', county='" + this.f16334f + "', isp='" + this.f16335g + "', country_id='" + this.f16336h + "', area_id='" + this.f16337i + "', region_id='" + this.f16338j + "', city_id='" + this.f16339k + "', county_id='" + this.f16340l + "', isp_id='" + this.f16341m + "'}";
        }
    }
}
