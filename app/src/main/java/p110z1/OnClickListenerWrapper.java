package p110z1;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Toast;
import com.nrzs.moudleui.C2202R;

/* renamed from: z1.apm */
/* loaded from: classes3.dex */
public class OnClickListenerWrapper {
    /* renamed from: a */
    public static View.OnClickListener m11696a(final Context context, final View.OnClickListener onClickListener) {
        if (onClickListener == null) {
            return null;
        }
        return new View.OnClickListener() { // from class: z1.apm.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (OnClickListenerWrapper.m11697a(context)) {
                    View.OnClickListener onClickListener2 = onClickListener;
                    if (onClickListener2 != null) {
                        onClickListener2.onClick(view);
                        return;
                    }
                    return;
                }
                Context context2 = context;
                Toast.makeText(context2, context2.getString(C2202R.string.bird_game_hot_search_no_web), 0).show();
            }
        };
    }

    /* renamed from: a */
    public static boolean m11697a(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
            return false;
        }
        return activeNetworkInfo.isAvailable();
    }
}
