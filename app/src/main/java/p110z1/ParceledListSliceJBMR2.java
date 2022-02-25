package p110z1;

import android.os.Parcelable;
import java.util.List;
import mirror.MethodParams;
import mirror.RefClass;
import mirror.RefConstructor;
import mirror.RefMethod;
import mirror.RefStaticObject;

/* renamed from: z1.cuv */
/* loaded from: classes3.dex */
public class ParceledListSliceJBMR2 {
    public static RefStaticObject<Parcelable.Creator> CREATOR;
    public static Class<?> TYPE = RefClass.load(ParceledListSliceJBMR2.class, "android.content.pm.ParceledListSlice");
    @MethodParams({List.class})
    public static RefConstructor<Parcelable> ctor;
    public static RefMethod<List> getList;
}
