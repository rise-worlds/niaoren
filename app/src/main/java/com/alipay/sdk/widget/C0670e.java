package com.alipay.sdk.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import p110z1.C3876ar;
import p110z1.C4921cd;

/* renamed from: com.alipay.sdk.widget.e */
/* loaded from: classes.dex */
public class C0670e {
    /* renamed from: a */
    public static Dialog m25258a(Context context, String str, String str2, String str3, DialogInterface.OnClickListener onClickListener, String str4, DialogInterface.OnClickListener onClickListener2) {
        AlertDialog.Builder a = m25259a(context, str, str3, onClickListener, str4, onClickListener2);
        a.setTitle(str);
        a.setMessage(str2);
        AlertDialog create = a.create();
        create.setCanceledOnTouchOutside(false);
        create.setOnKeyListener(new DialogInterface$OnKeyListenerC0671f());
        try {
            create.show();
        } catch (Throwable th) {
            C4921cd.m5619a(C3876ar.f17447x, "showDialog ", th);
        }
        return create;
    }

    /* renamed from: a */
    private static AlertDialog.Builder m25259a(Context context, String str, String str2, DialogInterface.OnClickListener onClickListener, String str3, DialogInterface.OnClickListener onClickListener2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (!TextUtils.isEmpty(str3) && onClickListener2 != null) {
            builder.setPositiveButton(str3, onClickListener2);
        }
        if (!TextUtils.isEmpty(str2) && onClickListener != null) {
            builder.setNegativeButton(str2, onClickListener);
        }
        return builder;
    }
}
