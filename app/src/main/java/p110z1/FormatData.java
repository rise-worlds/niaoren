package p110z1;

import java.util.List;

/* renamed from: z1.fs */
/* loaded from: classes3.dex */
public class FormatData {

    /* renamed from: a */
    private String f21693a;

    /* renamed from: b */
    private List<C5358a> f21694b;

    /* renamed from: a */
    public String m2830a() {
        return this.f21693a;
    }

    /* renamed from: a */
    public void m2829a(String str) {
        this.f21693a = str;
    }

    /* renamed from: b */
    public List<C5358a> m2827b() {
        return this.f21694b;
    }

    /* renamed from: a */
    public void m2828a(List<C5358a> list) {
        this.f21694b = list;
    }

    /* compiled from: FormatData.java */
    /* renamed from: z1.fs$a */
    /* loaded from: classes3.dex */
    public static class C5358a {

        /* renamed from: a */
        private int f21695a;

        /* renamed from: b */
        private int f21696b;

        /* renamed from: c */
        private String f21697c;

        /* renamed from: d */
        private LinkType f21698d;

        /* renamed from: e */
        private String f21699e;

        /* renamed from: f */
        private String f21700f;

        /* renamed from: a */
        public String m2826a() {
            return this.f21699e;
        }

        /* renamed from: a */
        public void m2824a(String str) {
            this.f21699e = str;
        }

        /* renamed from: b */
        public String m2822b() {
            return this.f21700f;
        }

        /* renamed from: b */
        public void m2820b(String str) {
            this.f21700f = str;
        }

        /* renamed from: c */
        public LinkType m2819c() {
            return this.f21698d;
        }

        /* renamed from: a */
        public void m2823a(LinkType fpVar) {
            this.f21698d = fpVar;
        }

        /* renamed from: d */
        public String m2817d() {
            return this.f21697c;
        }

        /* renamed from: c */
        public void m2818c(String str) {
            this.f21697c = str;
        }

        public C5358a(int i, int i2, String str, LinkType fpVar) {
            this.f21695a = i;
            this.f21696b = i2;
            this.f21697c = str;
            this.f21698d = fpVar;
        }

        public C5358a(int i, int i2, String str, String str2, LinkType fpVar) {
            this.f21695a = i;
            this.f21696b = i2;
            this.f21699e = str;
            this.f21700f = str2;
            this.f21698d = fpVar;
        }

        /* renamed from: e */
        public int m2816e() {
            return this.f21695a;
        }

        /* renamed from: a */
        public void m2825a(int i) {
            this.f21695a = i;
        }

        /* renamed from: f */
        public int m2815f() {
            return this.f21696b;
        }

        /* renamed from: b */
        public void m2821b(int i) {
            this.f21696b = i;
        }
    }
}
