package p110z1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Timer;
import java.util.TimerTask;

/* compiled from: ToastUtil.java */
/* renamed from: z1.aqg */
/* loaded from: classes3.dex */
public class aqg {

    /* renamed from: a */
    private static ImageView f17326a;

    /* renamed from: b */
    private static Toast f17327b;

    /* renamed from: c */
    private static Toast f17328c;

    /* renamed from: d */
    private static Toast f17329d;

    /* renamed from: a */
    public static void m11586a(Context context, String str) {
        Toast toast = f17327b;
        if (toast == null) {
            f17327b = Toast.makeText(context, str, 0);
        } else {
            toast.setText(str);
            f17327b.setDuration(0);
        }
        f17327b.show();
    }

    /* renamed from: b */
    public static void m11583b(Context context, String str) {
        Toast toast = f17328c;
        if (toast == null) {
            f17328c = Toast.makeText(context, str, 0);
            f17328c.setGravity(17, 0, 0);
        } else {
            toast.setText(str);
            f17328c.setDuration(0);
        }
        f17328c.show();
    }

    /* renamed from: a */
    public static void m11587a(Context context, View view, String str) {
        Toast toast = f17329d;
        if (toast == null) {
            f17329d = Toast.makeText(context, str, 0);
            f17329d.setGravity(17, 0, 0);
            ((ViewGroup) f17329d.getView()).addView(view, 0);
        } else {
            toast.setText(str);
            f17329d.setDuration(0);
        }
        f17329d.show();
    }

    /* renamed from: a */
    public static void m11585a(Context context, String str, int i) {
        final Timer timer = new Timer();
        final Toast makeText = Toast.makeText(context, str, 1);
        timer.schedule(new TimerTask() { // from class: z1.aqg.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                makeText.show();
            }
        }, 0L, 3000L);
        new Timer().schedule(new TimerTask() { // from class: z1.aqg.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                makeText.cancel();
                timer.cancel();
            }
        }, i);
    }

    /* renamed from: a */
    public static void m11584a(Context context, String str, int i, int i2) {
        View inflate = LayoutInflater.from(context).inflate(i, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(i2);
        if (textView != null) {
            textView.setText(str);
        }
        Toast toast = new Toast(context);
        toast.setView(inflate);
        toast.show();
    }
}
