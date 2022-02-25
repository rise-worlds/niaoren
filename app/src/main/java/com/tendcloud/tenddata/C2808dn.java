package com.tendcloud.tenddata;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.tendcloud.tenddata.C2825dy;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.dn */
/* loaded from: classes2.dex */
public class C2808dn {

    /* renamed from: e */
    public static final Parcelable.Creator f13761e = new C2809do();

    /* renamed from: a */
    public boolean f13762a;

    /* renamed from: b */
    public int f13763b;

    /* renamed from: c */
    public final String f13764c;

    /* renamed from: d */
    public final int f13765d;

    /* renamed from: f */
    private final C2825dy.C2826a f13766f;

    public C2808dn(int i) {
        this.f13765d = i;
        this.f13764c = m16053a(i);
        this.f13766f = C2825dy.C2826a.get(i);
        C2825dy.C2829d d = m16050d();
        try {
            if (this.f13766f != null) {
                C2825dy.C2827b group = this.f13766f.getGroup("cpuacct");
                C2825dy.C2827b group2 = this.f13766f.getGroup("cpu");
                if (group2.group != null) {
                    this.f13762a = !group2.group.contains("bg_non_interactive");
                    if (group.group.split("/").length > 1) {
                        this.f13763b = Integer.parseInt(group.group.split("/")[1].replace("uid_", ""));
                    } else if (d != null) {
                        this.f13763b = d.getUid();
                    }
                } else if (d != null) {
                    this.f13763b = d.getUid();
                }
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            if (d != null) {
                this.f13763b = d.getUid();
            }
        }
    }

    /* renamed from: a */
    public String m16054a() {
        try {
            return this.f13764c.split(":")[0];
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: b */
    public String m16052b() {
        try {
            if (this.f13764c.split(":").length <= 1) {
                return "";
            }
            return ":" + this.f13764c.split(":")[1];
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: c */
    public C2825dy.C2826a m16051c() {
        return this.f13766f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public C2808dn(Parcel parcel) {
        this.f13764c = parcel.readString();
        this.f13765d = parcel.readInt();
        this.f13766f = (C2825dy.C2826a) parcel.readParcelable(C2825dy.C2826a.class.getClassLoader());
        this.f13762a = parcel.readByte() != 0;
    }

    /* renamed from: a */
    static String m16053a(int i) {
        String str = null;
        try {
            str = C2825dy.readFile(String.format("/proc/%d/cmdline", Integer.valueOf(i))).trim();
            if (TextUtils.isEmpty(str)) {
                return C2825dy.C2828c.get(i).getComm();
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
        return str;
    }

    /* renamed from: d */
    public final C2825dy.C2829d m16050d() {
        try {
            return C2825dy.C2829d.get(this.f13765d);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return null;
        }
    }

    /* renamed from: e */
    public C2825dy.C2828c m16049e() {
        try {
            return C2825dy.C2828c.get(this.f13765d);
        } catch (Throwable unused) {
            return null;
        }
    }
}
