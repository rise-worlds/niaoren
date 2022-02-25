package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import com.stripe.android.model.C2395g;
import org.apache.tools.ant.types.selectors.SizeSelector;
import p110z1.KProperty;

/* compiled from: KProperty.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\bf\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00020\u0004:\u0001\u000eJ\u001d\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u00002\u0006\u0010\f\u001a\u00028\u0001H&¢\u0006\u0002\u0010\rR\u001e\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u000f"}, m8860e = {"Lkotlin/reflect/KMutableProperty1;", TessBaseAPI.f9204e, "R", "Lkotlin/reflect/KProperty1;", "Lkotlin/reflect/KMutableProperty;", "setter", "Lkotlin/reflect/KMutableProperty1$Setter;", "getSetter", "()Lkotlin/reflect/KMutableProperty1$Setter;", "set", "", C2395g.f12127u, SizeSelector.SIZE_KEY, "(Ljava/lang/Object;Ljava/lang/Object;)V", "Setter", "kotlin-stdlib"})
/* renamed from: z1.cnc */
/* loaded from: classes3.dex */
public interface cnc<T, R> extends KProperty<R>, cnh<T, R> {

    /* compiled from: KProperty.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\bf\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00050\u0004¨\u0006\u0006"}, m8860e = {"Lkotlin/reflect/KMutableProperty1$Setter;", TessBaseAPI.f9204e, "R", "Lkotlin/reflect/KMutableProperty$Setter;", "Lkotlin/Function2;", "", "kotlin-stdlib"})
    /* renamed from: z1.cnc$a */
    /* loaded from: classes3.dex */
    public interface AbstractC5001a<T, R> extends cho<T, R, Unit>, KProperty.AbstractC4999a<R> {
    }

    @Override // p110z1.KProperty, p110z1.cnb
    @NotNull
    AbstractC5001a<T, R> getSetter();

    void set(T t, R r);
}
