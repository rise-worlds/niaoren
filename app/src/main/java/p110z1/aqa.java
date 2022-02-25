package p110z1;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.LogUtils;
import com.nrzs.base.router.RouterConstants;
import com.nrzs.base.router.provider.InvokeProvider;

/* compiled from: InvokeProviderImpl.java */
@Route(path = RouterConstants.Provider.PROVIDER_USER)
/* renamed from: z1.aqa */
/* loaded from: classes3.dex */
public class aqa implements InvokeProvider {
    @Override // com.nrzs.base.router.provider.InvokeProvider
    public Object getObject() {
        return "User InvokeProviderImpl";
    }

    @Override // com.nrzs.base.router.provider.InvokeProvider
    public void invoke(Object... objArr) {
        LogUtils.m23742b("invoke");
        Autologinmanager.m11654a((Context) objArr[0]).m11655a();
    }

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
        LogUtils.m23742b("InvokeProviderImpl:" + context.getClass().getCanonicalName());
    }
}
