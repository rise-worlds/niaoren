package com.xuhao.didi.p082a.p088e;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;

/* renamed from: com.xuhao.didi.a.e.a */
/* loaded from: classes2.dex */
public class BytesUtils {
    /* renamed from: a */
    public static String m15178a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr != null) {
            for (int i = 0; i < bArr.length; i++) {
                String str = Integer.toHexString(bArr[i] & 255) + ExpandableTextView.f6958c;
                if (str.length() == 2) {
                    str = ResultTypeConstant.f7213z + str;
                }
                sb.append(str);
            }
        }
        return sb.toString();
    }
}
