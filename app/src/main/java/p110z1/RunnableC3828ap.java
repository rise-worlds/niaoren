package p110z1;

import android.content.Context;
import android.text.TextUtils;
import p110z1.C3754ao;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.ap */
/* loaded from: classes3.dex */
public final class RunnableC3828ap implements Runnable {

    /* renamed from: a */
    final /* synthetic */ String f17109a;

    /* renamed from: b */
    final /* synthetic */ Context f17110b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC3828ap(String str, Context context) {
        this.f17109a = str;
        this.f17110b = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean b;
        boolean b2;
        if (!TextUtils.isEmpty(this.f17109a)) {
            b2 = C3754ao.C3757b.m12140b(this.f17110b, this.f17109a);
            if (!b2) {
                return;
            }
        }
        for (int i = 0; i < 4; i++) {
            String a = C3754ao.C3755a.m12150a(this.f17110b);
            if (!TextUtils.isEmpty(a)) {
                b = C3754ao.C3757b.m12140b(this.f17110b, a);
                if (!b) {
                    return;
                }
            } else {
                return;
            }
        }
    }
}
