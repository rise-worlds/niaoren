package p110z1;

import android.app.Application;
import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.angel.nrzs.App;
import com.nrzs.base.router.RouterConstants;
import com.nrzs.data.router.ChannelProvider;

@Route(path = RouterConstants.Provider.PROVIDER_GETCHANNEL)
/* renamed from: z1.ep */
/* loaded from: classes3.dex */
public class Getchannelmpl implements ChannelProvider {
    @Override // com.nrzs.data.router.ChannelProvider
    /* renamed from: a */
    public String mo3077a() {
        return WalleChannelReader.m13041a(App.m25213a(), EventConstants.f16434b);
    }

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
        ARouter.m1713a((Application) App.m25213a());
    }
}
