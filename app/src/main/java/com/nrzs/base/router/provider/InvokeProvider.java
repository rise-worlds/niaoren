package com.nrzs.base.router.provider;

import com.alibaba.android.arouter.facade.template.IProvider;

/* loaded from: classes.dex */
public interface InvokeProvider extends IProvider {
    Object getObject();

    void invoke(Object... objArr);
}
