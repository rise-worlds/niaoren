package p110z1;

import android.text.TextUtils;

/* renamed from: z1.br */
/* loaded from: classes3.dex */
public enum EnumC4725br {
    None("none"),
    WapPay("js://wappay"),
    Update("js://update"),
    OpenWeb("loc:openweb"),
    SetResult("loc:setResult"),
    Exit("loc:exit");
    

    /* renamed from: g */
    private String f19941g;

    EnumC4725br(String str) {
        this.f19941g = str;
    }

    /* renamed from: a */
    public static EnumC4725br m9495a(String str) {
        EnumC4725br[] values;
        if (TextUtils.isEmpty(str)) {
            return None;
        }
        EnumC4725br brVar = None;
        for (EnumC4725br brVar2 : values()) {
            if (str.startsWith(brVar2.f19941g)) {
                return brVar2;
            }
        }
        return brVar;
    }
}
