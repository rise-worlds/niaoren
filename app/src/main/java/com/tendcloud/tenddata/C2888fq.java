package com.tendcloud.tenddata;

import java.text.DecimalFormat;
import org.json.JSONObject;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.fq */
/* loaded from: classes2.dex */
class C2888fq extends AbstractC2894fv {

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.fq$a */
    /* loaded from: classes2.dex */
    enum EnumC2889a {
        WALKING(5),
        RUNNING(8),
        BICYCLE(10),
        BUS(12),
        SUBWAY(13),
        CAR(14),
        STILL(15);
        
        private final int label;

        EnumC2889a(int i) {
            this.label = i;
        }

        public String getLabel() {
            return String.valueOf(this.label);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2888fq(AbstractC2885fn fnVar) {
        super(fnVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.tendcloud.tenddata.AbstractC2894fv
    /* renamed from: a */
    public JSONObject mo15678a(double[] dArr) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        for (int i = 0; i < dArr.length; i++) {
            if (!Double.isNaN(dArr[i])) {
                jSONObject2.put(EnumC2889a.values()[i].getLabel(), decimalFormat.format(dArr[i]));
            }
        }
        jSONObject.put("probility", jSONObject2);
        jSONObject.put("ts", System.currentTimeMillis());
        return jSONObject;
    }
}
