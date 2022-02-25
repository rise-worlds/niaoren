package com.tendcloud.tenddata;

import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.hh */
/* loaded from: classes2.dex */
final class C2939hh extends PhoneStateListener {

    /* renamed from: a */
    static final long f14147a = 180000;

    /* renamed from: d */
    int f14150d;

    /* renamed from: b */
    long f14148b = 0;

    /* renamed from: c */
    long f14149c = 0;

    /* renamed from: e */
    int f14151e = 0;

    @Override // android.telephony.PhoneStateListener
    public void onCellLocationChanged(CellLocation cellLocation) {
        try {
            if (cellLocation.getClass().equals(GsmCellLocation.class)) {
                this.f14150d = ((GsmCellLocation) cellLocation).getLac();
                m15512a();
            } else if (cellLocation.getClass().equals(CdmaCellLocation.class)) {
                this.f14150d = ((CdmaCellLocation) cellLocation).getNetworkId();
                m15512a();
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: a */
    private void m15512a() {
        try {
            C2924gv.f14122a.post(new RunnableC2940hi(this));
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }
}
