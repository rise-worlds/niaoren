package p110z1;

import android.annotation.TargetApi;
import android.telephony.CellIdentityGsm;
import android.telephony.CellSignalStrengthGsm;
import mirror.RefClass;
import mirror.RefConstructor;
import mirror.RefObject;

@TargetApi(17)
/* renamed from: z1.cxd */
/* loaded from: classes3.dex */
public class CellInfoGsm {
    public static Class<?> TYPE = RefClass.load(CellInfoGsm.class, android.telephony.CellInfoGsm.class);
    public static RefConstructor<android.telephony.CellInfoGsm> ctor;
    public static RefObject<CellIdentityGsm> mCellIdentityGsm;
    public static RefObject<CellSignalStrengthGsm> mCellSignalStrengthGsm;
}
