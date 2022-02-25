package p110z1;

import android.content.Context;
import java.util.HashMap;
import java.util.concurrent.Callable;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.ax */
/* loaded from: classes3.dex */
public final class CallableC3942ax implements Callable<String> {

    /* renamed from: a */
    final /* synthetic */ C4745bt f17682a;

    /* renamed from: b */
    final /* synthetic */ Context f17683b;

    /* renamed from: c */
    final /* synthetic */ HashMap f17684c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CallableC3942ax(C4745bt btVar, Context context, HashMap hashMap) {
        this.f17682a = btVar;
        this.f17683b = context;
        this.f17684c = hashMap;
    }

    /* renamed from: a */
    public String call() throws Exception {
        String b;
        b = C3937aw.m9859b(this.f17682a, this.f17683b, this.f17684c);
        return b;
    }
}
