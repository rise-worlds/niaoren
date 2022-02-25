package p110z1;

import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Window;
import java.util.ArrayList;
import java.util.Random;

/* renamed from: z1.fc */
/* loaded from: classes3.dex */
public class DelUtils {
    /* renamed from: a */
    public static Integer[] m2934a(Window window) {
        if (window == null) {
            return null;
        }
        Integer[] numArr = new Integer[2];
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= 19) {
            window.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        } else {
            window.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        }
        numArr[0] = Integer.valueOf(displayMetrics.widthPixels);
        numArr[1] = Integer.valueOf(displayMetrics.heightPixels);
        return numArr;
    }

    /* renamed from: a */
    public static String m2936a() {
        Random random = new Random();
        return "http://106.14.135.179/ImmersionBar/" + random.nextInt(40) + ".jpg";
    }

    /* renamed from: b */
    public static ArrayList<String> m2933b() {
        return m2935a(4);
    }

    /* renamed from: a */
    public static ArrayList<String> m2935a(int i) {
        ArrayList<String> arrayList = new ArrayList<>();
        Random random = new Random();
        do {
            String str = "http://106.14.135.179/ImmersionBar/" + random.nextInt(40) + ".jpg";
            if (!arrayList.contains(str)) {
                arrayList.add(str);
            }
        } while (arrayList.size() < i);
        return arrayList;
    }

    /* renamed from: c */
    public static String m2932c() {
        Random random = new Random();
        return "http://106.14.135.179/ImmersionBar/phone/" + random.nextInt(40) + ".jpeg";
    }
}
