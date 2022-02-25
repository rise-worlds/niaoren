package p110z1;

import android.annotation.TargetApi;
import mirror.RefClass;
import mirror.RefConstructor;
import mirror.RefInt;

@TargetApi(17)
/* renamed from: z1.cxb */
/* loaded from: classes3.dex */
public class CellIdentityGsm {
    public static Class<?> TYPE = RefClass.load(CellIdentityGsm.class, android.telephony.CellIdentityGsm.class);
    public static RefConstructor<android.telephony.CellIdentityGsm> ctor;
    public static RefInt mCid;
    public static RefInt mLac;
    public static RefInt mMcc;
    public static RefInt mMnc;
}
