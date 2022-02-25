package p110z1;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/* renamed from: z1.aeq */
/* loaded from: classes3.dex */
public class ToastUtil {

    /* renamed from: a */
    private static Toast f15463a;

    private ToastUtil() {
    }

    /* renamed from: a */
    public static void m13876a(Context context, CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            Toast toast = f15463a;
            if (toast == null) {
                f15463a = Toast.makeText(context, charSequence, 0);
            } else {
                toast.setText(charSequence);
            }
            f15463a.show();
        }
    }
}
