package com.nrzs.base.router.provider;

import android.content.Context;
import android.content.DialogInterface;
import com.alibaba.android.arouter.facade.template.IProvider;

/* loaded from: classes.dex */
public interface XNKJRunProvider extends IProvider {
    void showdialog(Context context, DialogInterface.OnClickListener onClickListener);

    void startGame(String str);

    void startRom(Context context, Object obj);

    void writeHeartSID(String str);
}
