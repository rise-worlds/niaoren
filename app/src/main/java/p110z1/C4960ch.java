package p110z1;

import com.alipay.sdk.app.AlipayResultActivity;
import com.alipay.sdk.app.C0663l;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.ch */
/* loaded from: classes3.dex */
public class C4960ch implements AlipayResultActivity.AbstractC0645a {

    /* renamed from: a */
    final /* synthetic */ CountDownLatch f20686a;

    /* renamed from: b */
    final /* synthetic */ C4943cf f20687b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C4960ch(C4943cf cfVar, CountDownLatch countDownLatch) {
        this.f20687b = cfVar;
        this.f20686a = countDownLatch;
    }

    @Override // com.alipay.sdk.app.AlipayResultActivity.AbstractC0645a
    /* renamed from: a */
    public void mo5265a(int i, String str, String str2) {
        this.f20687b.f20642j = C0663l.m25286a(i, str, str2);
        this.f20686a.countDown();
    }
}
