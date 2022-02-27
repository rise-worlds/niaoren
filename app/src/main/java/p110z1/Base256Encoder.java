package p110z1;

import android.support.v4.view.InputDeviceCompat;
import android.support.v7.widget.helper.ItemTouchHelper;
import com.tencent.smtt.sdk.TbsListener;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.iy */
/* loaded from: classes3.dex */
public final class Base256Encoder implements AbstractC5379jd {
    @Override // p110z1.AbstractC5379jd
    /* renamed from: a */
    public final int mo2275a() {
        return 5;
    }

    @Override // p110z1.AbstractC5379jd
    /* renamed from: a */
    public final void mo2273a(EncoderContext jeVar) {
        StringBuilder sb = new StringBuilder();
        sb.append((char) 0);
        while (true) {
            if (!jeVar.m2326b()) {
                break;
            }
            sb.append(jeVar.m2332a());
            jeVar.f22083f++;
            if (C5380jg.m2305a(jeVar.f22078a, jeVar.f22083f, 5) != 5) {
                jeVar.f22084g = 0;
                break;
            }
        }
        int length = sb.length() - 1;
        int length2 = jeVar.f22082e.length() + length + 1;
        jeVar.m2330a(length2);
        boolean z = jeVar.f22085h.f22114b - length2 > 0;
        if (jeVar.m2326b() || z) {
            if (length <= 249) {
                sb.setCharAt(0, (char) length);
            } else if (length <= 1555) {
                sb.setCharAt(0, (char) ((length / ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION) + 249));
                sb.insert(1, (char) (length % ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION));
            } else {
                throw new IllegalStateException("Message length not in valid ranges: ".concat(String.valueOf(length)));
            }
        }
        int length3 = sb.length();
        for (int i = 0; i < length3; i++) {
            int charAt = sb.charAt(i) + (((jeVar.f22082e.length() + 1) * TbsListener.ErrorCode.NEEDDOWNLOAD_10) % 255) + 1;
            if (charAt > 255) {
                charAt += InputDeviceCompat.SOURCE_ANY;
            }
            jeVar.m2331a((char) charAt);
        }
    }

    /* renamed from: a */
    private static char m2363a(char c, int i) {
        int i2 = c + ((i * TbsListener.ErrorCode.NEEDDOWNLOAD_10) % 255) + 1;
        return i2 <= 255 ? (char) i2 : (char) (i2 + InputDeviceCompat.SOURCE_ANY);
    }
}
