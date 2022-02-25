package p110z1;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.nrzs.base.router.RouterConstants;
import com.nrzs.base.router.provider.CarkeyProvider;

@Route(path = RouterConstants.Provider.PROVIDER_APP)
/* renamed from: z1.eo */
/* loaded from: classes3.dex */
public class CarkeyProviderImpl implements CarkeyProvider {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.nrzs.base.router.provider.CarkeyProvider
    public void showdialog(Context context) {
        new DialogC5331eu(context).show();
    }

    @Override // com.nrzs.base.router.provider.CarkeyProvider
    public boolean isopen() {
        return PreSetListManager.m12116a().m12110f();
    }

    @Override // com.nrzs.base.router.provider.CarkeyProvider
    public String getVaCourseUrl() {
        return PreSetListManager.m12116a().m12112d();
    }
}
