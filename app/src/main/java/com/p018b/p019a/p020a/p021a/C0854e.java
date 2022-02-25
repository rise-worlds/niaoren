package com.p018b.p019a.p020a.p021a;

import com.github.kevinsawicki.http.HttpRequest;
import com.p018b.p019a.C0896ab;
import com.p018b.p019a.C0905at;
import com.p018b.p019a.CacheControl;
import com.p018b.p019a.Headers;
import com.p018b.p019a.Request;
import com.p018b.p019a.Response;
import com.p018b.p019a.p020a.Internal;
import com.p018b.p019a.p020a.p023c.HttpDate;
import com.p018b.p019a.p020a.p023c.HttpHeaders;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.tools.ant.taskdefs.WaitFor;

/* compiled from: CacheStrategy.java */
/* renamed from: com.b.a.a.a.e */
/* loaded from: classes.dex */
public final class C0854e {

    /* renamed from: a */
    final long f5698a;

    /* renamed from: b */
    final Request f5699b;

    /* renamed from: c */
    final Response f5700c;

    /* renamed from: d */
    private Date f5701d;

    /* renamed from: e */
    private String f5702e;

    /* renamed from: f */
    private Date f5703f;

    /* renamed from: g */
    private String f5704g;

    /* renamed from: h */
    private Date f5705h;

    /* renamed from: i */
    private long f5706i;

    /* renamed from: j */
    private long f5707j;

    /* renamed from: k */
    private String f5708k;

    /* renamed from: l */
    private int f5709l;

    public C0854e(long j, Request aoVar, Response asVar) {
        this.f5709l = -1;
        this.f5698a = j;
        this.f5699b = aoVar;
        this.f5700c = asVar;
        if (asVar != null) {
            this.f5706i = asVar.m24446h();
            this.f5707j = asVar.m24445i();
            Headers d = asVar.m24450d();
            int a = d.m24559a();
            for (int i = 0; i < a; i++) {
                String a2 = d.m24558a(i);
                String b = d.m24555b(i);
                if ("Date".equalsIgnoreCase(a2)) {
                    this.f5701d = HttpDate.m24745a(b);
                    this.f5702e = b;
                } else if (HttpRequest.HEADER_EXPIRES.equalsIgnoreCase(a2)) {
                    this.f5705h = HttpDate.m24745a(b);
                } else if (HttpRequest.HEADER_LAST_MODIFIED.equalsIgnoreCase(a2)) {
                    this.f5703f = HttpDate.m24745a(b);
                    this.f5704g = b;
                } else if (HttpRequest.HEADER_ETAG.equalsIgnoreCase(a2)) {
                    this.f5708k = b;
                } else if ("Age".equalsIgnoreCase(a2)) {
                    this.f5709l = HttpHeaders.m24736b(b, -1);
                }
            }
        }
    }

    /* renamed from: a */
    public final CacheStrategy m24818a() {
        CacheStrategy dVar;
        CacheControl g;
        long j;
        String str;
        long j2;
        long j3;
        if (this.f5700c == null) {
            dVar = new CacheStrategy(this.f5699b, null);
        } else if (this.f5699b.m24464g() && this.f5700c.m24451c() == null) {
            dVar = new CacheStrategy(this.f5699b, null);
        } else if (!CacheStrategy.m24819a(this.f5700c, this.f5699b)) {
            dVar = new CacheStrategy(this.f5699b, null);
        } else {
            CacheControl f = this.f5699b.m24465f();
            if (!f.m24418a()) {
                Request aoVar = this.f5699b;
                if (!((aoVar.m24470a("If-Modified-Since") == null && aoVar.m24470a(HttpRequest.HEADER_IF_NONE_MATCH) == null) ? false : true)) {
                    Date date = this.f5701d;
                    long j4 = 0;
                    long max = date != null ? Math.max(0L, this.f5707j - date.getTime()) : 0L;
                    if (this.f5709l != -1) {
                        max = Math.max(max, TimeUnit.SECONDS.toMillis(this.f5709l));
                    }
                    long j5 = this.f5707j;
                    long j6 = max + (j5 - this.f5706i) + (this.f5698a - j5);
                    if (this.f5700c.m24447g().m24415c() != -1) {
                        j = TimeUnit.SECONDS.toMillis(g.m24415c());
                    } else if (this.f5705h != null) {
                        Date date2 = this.f5701d;
                        if (date2 != null) {
                            j3 = date2.getTime();
                        } else {
                            j3 = this.f5707j;
                        }
                        j = this.f5705h.getTime() - j3;
                        if (j <= 0) {
                            j = 0;
                        }
                    } else if (this.f5703f == null || this.f5700c.m24454a().m24471a().m24524k() != null) {
                        j = 0;
                    } else {
                        Date date3 = this.f5701d;
                        if (date3 != null) {
                            j2 = date3.getTime();
                        } else {
                            j2 = this.f5706i;
                        }
                        long time = j2 - this.f5703f.getTime();
                        j = time > 0 ? time / 10 : 0L;
                    }
                    if (f.m24415c() != -1) {
                        j = Math.min(j, TimeUnit.SECONDS.toMillis(f.m24415c()));
                    }
                    long millis = f.m24410h() != -1 ? TimeUnit.SECONDS.toMillis(f.m24410h()) : 0L;
                    CacheControl g2 = this.f5700c.m24447g();
                    if (!g2.m24412f() && f.m24411g() != -1) {
                        j4 = TimeUnit.SECONDS.toMillis(f.m24411g());
                    }
                    if (!g2.m24418a()) {
                        long j7 = millis + j6;
                        if (j7 < j4 + j) {
                            C0905at f2 = this.f5700c.m24448f();
                            if (j7 >= j) {
                                f2.m24433a("Warning", "110 HttpURLConnection \"Response is stale\"");
                            }
                            if (j6 > WaitFor.ONE_DAY) {
                                if (this.f5700c.m24447g().m24415c() == -1 && this.f5705h == null) {
                                    f2.m24433a("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
                                }
                            }
                            dVar = new CacheStrategy(null, f2.m24444a());
                        }
                    }
                    String str2 = this.f5708k;
                    if (str2 != null) {
                        str = HttpRequest.HEADER_IF_NONE_MATCH;
                    } else if (this.f5703f != null) {
                        str = "If-Modified-Since";
                        str2 = this.f5704g;
                    } else if (this.f5701d != null) {
                        str = "If-Modified-Since";
                        str2 = this.f5702e;
                    } else {
                        dVar = new CacheStrategy(this.f5699b, null);
                    }
                    C0896ab b = this.f5699b.m24468c().m24556b();
                    Internal.f5689a.mo24485a(b, str, str2);
                    dVar = new CacheStrategy(this.f5699b.m24466e().m24462a(b.m24554a()).m24463a(), this.f5700c);
                }
            }
            dVar = new CacheStrategy(this.f5699b, null);
        }
        return (dVar.f5696a == null || !this.f5699b.m24465f().m24409i()) ? dVar : new CacheStrategy(null, null);
    }
}
