package p110z1;

import android.content.Context;
import android.util.Log;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.nrzs.base.router.RouterConstants;
import com.nrzs.base.router.provider.PsySuccessProvider;
import p110z1.C5309ek;

@Route(path = RouterConstants.Provider.PROVIDER_PAY)
/* renamed from: z1.er */
/* loaded from: classes3.dex */
public class PaySuccessProviderImpl implements PsySuccessProvider {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.nrzs.base.router.provider.PsySuccessProvider
    public void success(String str) {
        Log.e("paysueesee", "收到刷新的回调");
        EventBus.m3448a().m3427d(new C5309ek.C5310a(str));
    }
}
