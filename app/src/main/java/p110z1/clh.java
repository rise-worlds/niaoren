package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import org.apache.tools.ant.taskdefs.condition.ParserSupports;
import org.apache.tools.ant.types.selectors.SizeSelector;

/* compiled from: Delegates.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u0002H\u00010\u0003B\u0005¢\u0006\u0002\u0010\u0004J$\u0010\u0007\u001a\u00028\u00002\b\u0010\b\u001a\u0004\u0018\u00010\u00022\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\nH\u0096\u0002¢\u0006\u0002\u0010\u000bJ,\u0010\f\u001a\u00020\r2\b\u0010\b\u001a\u0004\u0018\u00010\u00022\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n2\u0006\u0010\u0005\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u000eR\u0012\u0010\u0005\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\u000f"}, m8860e = {"Lkotlin/properties/NotNullVar;", TessBaseAPI.f9204e, "", "Lkotlin/properties/ReadWriteProperty;", "()V", SizeSelector.SIZE_KEY, "Ljava/lang/Object;", "getValue", "thisRef", ParserSupports.PROPERTY, "Lkotlin/reflect/KProperty;", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "setValue", "", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "kotlin-stdlib"})
/* renamed from: z1.clh */
/* loaded from: classes3.dex */
final class clh<T> implements clk<Object, T> {

    /* renamed from: a */
    private T f20803a;

    @Override // p110z1.clk
    @NotNull
    /* renamed from: a */
    public T mo4911a(@dbs Object obj, @NotNull cnf<?> cnfVar) {
        cji.m5162f(cnfVar, ParserSupports.PROPERTY);
        T t = this.f20803a;
        if (t != null) {
            return t;
        }
        throw new IllegalStateException("Property " + cnfVar.getName() + " should be initialized before get.");
    }

    @Override // p110z1.clk
    /* renamed from: a */
    public void mo4910a(@dbs Object obj, @NotNull cnf<?> cnfVar, @NotNull T t) {
        cji.m5162f(cnfVar, ParserSupports.PROPERTY);
        cji.m5162f(t, SizeSelector.SIZE_KEY);
        this.f20803a = t;
    }
}
