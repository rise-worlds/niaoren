package com.common.router;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.lbd.p054xj.p056ui.dialog.XnkjRunDialog;
import com.lbd.p054xj.socket.C1545f;
import com.nrzs.base.router.RouterConstants;
import com.nrzs.base.router.provider.XNKJRunProvider;
import com.nrzs.data.game.bean.TopicInfo;
import com.nrzs.data.p066ft.bean.EnginInteraRequestInfo;
import p110z1.EventBus;
import p110z1.FileWriteUtils;
import p110z1.FloatAssistManager;
import p110z1.FloatDataManager;
import p110z1.FloatXnkjManager;
import p110z1.FwManager;
import p110z1.StartXNKJEvent;
import p110z1.aee;
import p110z1.apa;

@Route(path = RouterConstants.Provider.PROVIDER_XNKJ_RUN)
/* loaded from: classes.dex */
public class XNKJRunProviderImpl implements XNKJRunProvider {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.nrzs.base.router.provider.XNKJRunProvider
    public void showdialog(Context context, DialogInterface.OnClickListener onClickListener) {
        new XnkjRunDialog(context, onClickListener).show();
    }

    @Override // com.nrzs.base.router.provider.XNKJRunProvider
    public void startRom(Context context, Object obj) {
        FloatAssistManager.m12397i().m12413b(0);
        FloatDataManager.m12352j().m12366a((TopicInfo) obj);
        FwManager.getInstance().showXJAssistListView();
    }

    @Override // com.nrzs.base.router.provider.XNKJRunProvider
    public void startGame(String str) {
        EventBus.m3448a().m3427d(new StartXNKJEvent(aee.m14183a(str)));
    }

    @Override // com.nrzs.base.router.provider.XNKJRunProvider
    public void writeHeartSID(String str) {
        FileWriteUtils.m14067f(str);
        FloatXnkjManager.INSTANCE.handerHeart();
    }

    private void writeEnginInteraToRom(final EnginInteraRequestInfo enginInteraRequestInfo) {
        C1545f.m19586c(new C1545f.AbstractC1551c<Object>() { // from class: com.common.router.XNKJRunProviderImpl.1
            @Override // com.lbd.p054xj.socket.C1545f.AbstractRunnableC1552d
            public void onSuccess(@Nullable Object obj) {
            }

            @Override // com.lbd.p054xj.socket.C1545f.AbstractRunnableC1552d
            @Nullable
            public Object doInBackground() throws Throwable {
                FileWriteUtils.m14066g(apa.m11879a(enginInteraRequestInfo));
                return null;
            }
        });
    }
}
