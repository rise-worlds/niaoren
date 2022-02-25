package p110z1;

import android.os.Parcelable;
import java.util.List;
import mirror.MethodParams;
import mirror.RefClass;
import mirror.RefConstructor;
import mirror.RefMethod;
import mirror.RefStaticObject;

/* renamed from: z1.cuu */
/* loaded from: classes3.dex */
public class ParceledListSlice {
    public static RefStaticObject<Parcelable.Creator> CREATOR;
    public static Class<?> TYPE = RefClass.load(ParceledListSlice.class, "android.content.pm.ParceledListSlice");
    public static RefMethod<Boolean> append;
    public static RefConstructor<Parcelable> ctor;
    @MethodParams({List.class})
    public static RefConstructor<Parcelable> ctorQ;
    public static RefMethod<List<?>> getList;
    public static RefMethod<Boolean> isLastSlice;
    public static RefMethod<Parcelable> populateList;
    public static RefMethod<Void> setLastSlice;
}
