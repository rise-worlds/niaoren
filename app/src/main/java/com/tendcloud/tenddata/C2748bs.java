package com.tendcloud.tenddata;

import android.os.Vibrator;
import com.tendcloud.tenddata.C2744bq;
import com.tendcloud.tenddata.C2848em;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.bs */
/* loaded from: classes2.dex */
class C2748bs implements C2848em.AbstractC2849a {
    final /* synthetic */ C2744bq.RunnableC2746b this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2748bs(C2744bq.RunnableC2746b bVar) {
        this.this$0 = bVar;
    }

    @Override // com.tendcloud.tenddata.C2848em.AbstractC2849a
    public void onAddTestDeviceEvent() {
        if (C2744bq.m16223a().f13623i != null) {
            C2744bq.m16223a().f13623i.sendMessage(C2744bq.m16223a().f13623i.obtainMessage(1));
            if (C2855es.m15792b(C2664ab.f13513g, "android.permission.VIBRATE")) {
                ((Vibrator) C2664ab.f13513g.getSystemService("vibrator")).vibrate(100L);
            }
        }
    }
}
