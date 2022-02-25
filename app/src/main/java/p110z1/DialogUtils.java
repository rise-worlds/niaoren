package p110z1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/* renamed from: z1.aed */
/* loaded from: classes3.dex */
public class DialogUtils {
    /* renamed from: a */
    public static AlertDialog m14192a(Context context, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder cancelable = new AlertDialog.Builder(context).setCancelable(false);
        cancelable.setMessage("新增悬浮窗功能。需要您在系统设置中手动开通系统权限。");
        cancelable.setPositiveButton("暂不开启", onClickListener);
        cancelable.setNeutralButton("立即开启", onClickListener);
        return cancelable.create();
    }
}
