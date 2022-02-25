package com.nrzs.base.router.provider;

import android.content.Context;
import com.alibaba.android.arouter.facade.template.IProvider;

/* loaded from: classes.dex */
public interface CarkeyProvider extends IProvider {
    String getVaCourseUrl();

    boolean isopen();

    void showdialog(Context context);
}
