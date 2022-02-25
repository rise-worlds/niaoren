package org.greenrobot.eventbus.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

/* loaded from: classes2.dex */
public class ErrorDialogFragments {

    /* renamed from: a */
    public static int f14789a;

    /* renamed from: b */
    public static Class<?> f14790b;

    /* renamed from: a */
    public static Dialog m14820a(Context context, Bundle bundle, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(bundle.getString(ErrorDialogManager.f14794d));
        builder.setMessage(bundle.getString(ErrorDialogManager.f14795e));
        int i = f14789a;
        if (i != 0) {
            builder.setIcon(i);
        }
        builder.setPositiveButton(17039370, onClickListener);
        return builder.create();
    }

    /* renamed from: a */
    public static void m14819a(DialogInterface dialogInterface, int i, Activity activity, Bundle bundle) {
        Class<?> cls = f14790b;
        if (cls != null) {
            try {
                ErrorDialogManager.f14791a.f14826a.m14787b().m3427d(cls.newInstance());
            } catch (Exception e) {
                throw new RuntimeException("Event cannot be constructed", e);
            }
        }
        if (bundle.getBoolean(ErrorDialogManager.f14796f, false) && activity != null) {
            activity.finish();
        }
    }

    @TargetApi(11)
    /* loaded from: classes2.dex */
    public static class Honeycomb extends DialogFragment implements DialogInterface.OnClickListener {
        @Override // android.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            return ErrorDialogFragments.m14820a(getActivity(), getArguments(), this);
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            ErrorDialogFragments.m14819a(dialogInterface, i, getActivity(), getArguments());
        }
    }

    /* loaded from: classes2.dex */
    public static class Support extends android.support.p003v4.app.DialogFragment implements DialogInterface.OnClickListener {
        @Override // android.support.p003v4.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            return ErrorDialogFragments.m14820a(getActivity(), getArguments(), this);
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            ErrorDialogFragments.m14819a(dialogInterface, i, getActivity(), getArguments());
        }
    }
}
