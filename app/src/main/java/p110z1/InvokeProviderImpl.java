package p110z1;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.nrzs.base.router.RouterConstants;
import com.nrzs.base.router.provider.InvokeProvider;

@Route(path = RouterConstants.Provider.PROVIDER_GAME)
/* renamed from: z1.aok */
/* loaded from: classes3.dex */
public class InvokeProviderImpl implements InvokeProvider {
    @Override // com.nrzs.base.router.provider.InvokeProvider
    public Object getObject() {
        return "Game InvokeProviderImpl";
    }

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.nrzs.base.router.provider.InvokeProvider
    public void invoke(Object... objArr) {
        PXKJCoreUtils.m12923a((String) objArr[0], ((Integer) objArr[1]).intValue());
    }
}
