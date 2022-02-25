package retrofit2;

import com.googlecode.tesseract.android.TessBaseAPI;
import p110z1.Lambda;
import p110z1.Metadata;
import p110z1.Unit;
import p110z1.chd;
import p110z1.dbs;

/* compiled from: KotlinExtensions.kt */
@Metadata(m8864a = 3, m8863b = {1, 1, 13}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\n¢\u0006\u0002\b\u0006¨\u0006\u0007"}, m8860e = {"<anonymous>", "", TessBaseAPI.f9204e, "", "it", "", "invoke", "retrofit2/KotlinExtensions$awaitResponse$2$1"})
/* renamed from: retrofit2.KotlinExtensions$awaitResponse$$inlined$suspendCancellableCoroutine$lambda$1 */
/* loaded from: classes2.dex */
final class C3293xc95e9eb1 extends Lambda implements chd<Throwable, Unit> {
    final /* synthetic */ Call $this_awaitResponse$inlined;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C3293xc95e9eb1(Call call) {
        super(1);
        this.$this_awaitResponse$inlined = call;
    }

    @Override // p110z1.chd
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        invoke2(th);
        return Unit.f20418a;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@dbs Throwable th) {
        this.$this_awaitResponse$inlined.cancel();
    }
}
