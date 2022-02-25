package p110z1;

import android.app.Application;
import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.angel.nrzs.App;
import com.angel.nrzs.app.activity.NRZSWebviewActivity;
import com.nrzs.base.router.RouterConstants;
import com.nrzs.base.router.RouterUtils;
import com.nrzs.base.router.provider.PayProvider;
import com.nrzs.data.other.bean.AdResultInfoItem;

@Route(path = RouterConstants.Provider.PROVIDER_TOWEB_PAY)
/* renamed from: z1.eq */
/* loaded from: classes3.dex */
public class PayProviderlmpl implements PayProvider {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
        ARouter.m1713a((Application) App.m25213a());
    }

    @Override // com.nrzs.base.router.provider.PayProvider
    public void openPay(Context context, int i, int i2, int i3) {
        if (LoginManager.m12620d().m12606r()) {
            AdResultInfoItem adResultInfoItem = new AdResultInfoItem();
            if (i3 == 2) {
                adResultInfoItem.Title = "支持鸟人送金币";
            } else {
                adResultInfoItem.Title = "购买续费";
            }
            adResultInfoItem.ExecArgs = HttpVal.f16516c;
            NRZSWebviewActivity.m25006a(context, i, i2, i3, adResultInfoItem);
            return;
        }
        RouterUtils.toLogin(1, 0);
    }
}
