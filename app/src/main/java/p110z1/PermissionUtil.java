package p110z1;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import java.util.ArrayList;

/* renamed from: z1.ape */
/* loaded from: classes3.dex */
public class PermissionUtil {

    /* renamed from: a */
    public static final int f17114a = 2000;

    /* compiled from: PermissionUtil.java */
    /* renamed from: z1.ape$a */
    /* loaded from: classes3.dex */
    public enum EnumC3830a {
        GRANTED("已授权"),
        DENIED("未授权"),
        WAIT("等待授权");
        
        String stringValue;

        EnumC3830a(String str) {
            this.stringValue = str;
        }

        public String stringValue() {
            return this.stringValue;
        }
    }

    /* renamed from: a */
    public static EnumC3830a m11847a(Activity activity, String... strArr) {
        int length = strArr.length;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            if (!(ContextCompat.checkSelfPermission(activity, strArr[i]) == 0)) {
                arrayList.add(strArr[i]);
            }
        }
        if (!arrayList.isEmpty()) {
            m11845b(activity, (String[]) arrayList.toArray(new String[arrayList.size()]));
        }
        return arrayList.size() <= 0 ? EnumC3830a.GRANTED : EnumC3830a.WAIT;
    }

    /* renamed from: b */
    public static void m11845b(@NonNull Activity activity, @NonNull String[] strArr) {
        ActivityCompat.requestPermissions(activity, strArr, 2000);
    }

    /* renamed from: a */
    public static void m11846a(Fragment fragment, String[] strArr) {
        fragment.requestPermissions(strArr, 2000);
    }

    /* renamed from: a */
    public static EnumC3830a m11848a(int i, String[] strArr, int[] iArr, String... strArr2) {
        if (i == 2000) {
            int length = strArr.length;
            boolean z = true;
            for (int i2 = 0; i2 < length; i2++) {
                if (iArr[i2] != 0) {
                    boolean z2 = z;
                    for (String str : strArr2) {
                        if (TextUtils.equals(str, strArr[i2])) {
                            z2 = false;
                        }
                    }
                    z = z2;
                }
            }
            if (z) {
                return EnumC3830a.GRANTED;
            }
            if (!z) {
                return EnumC3830a.DENIED;
            }
        }
        return EnumC3830a.WAIT;
    }
}
