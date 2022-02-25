package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import org.apache.tools.ant.taskdefs.condition.ParserSupports;
import org.apache.tools.ant.types.selectors.SizeSelector;

@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0004\u001a\u00028\u0000¢\u0006\u0002\u0010\u0005J)\u0010\b\u001a\u00020\t2\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\f\u001a\u00028\u00002\u0006\u0010\r\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\u000eJ)\u0010\u000f\u001a\u00020\u00102\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\f\u001a\u00028\u00002\u0006\u0010\r\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\u0011J$\u0010\u0012\u001a\u00028\u00002\b\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0096\u0002¢\u0006\u0002\u0010\u0014J,\u0010\u0015\u001a\u00020\t2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\u0006\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0016R\u0010\u0010\u0006\u001a\u00028\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u0017"}, m8860e = {"Lkotlin/properties/ObservableProperty;", TessBaseAPI.f9204e, "Lkotlin/properties/ReadWriteProperty;", "", "initialValue", "(Ljava/lang/Object;)V", SizeSelector.SIZE_KEY, "Ljava/lang/Object;", "afterChange", "", ParserSupports.PROPERTY, "Lkotlin/reflect/KProperty;", "oldValue", "newValue", "(Lkotlin/reflect/KProperty;Ljava/lang/Object;Ljava/lang/Object;)V", "beforeChange", "", "(Lkotlin/reflect/KProperty;Ljava/lang/Object;Ljava/lang/Object;)Z", "getValue", "thisRef", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "setValue", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "kotlin-stdlib"})
/* renamed from: z1.cli */
/* loaded from: classes3.dex */
public abstract class ObservableProperty<T> implements clk<Object, T> {

    /* renamed from: a */
    private T f20804a;

    /* renamed from: a */
    protected void mo4914a(@NotNull cnf<?> cnfVar, T t, T t2) {
        cji.m5162f(cnfVar, ParserSupports.PROPERTY);
    }

    /* renamed from: b */
    protected boolean mo4913b(@NotNull cnf<?> cnfVar, T t, T t2) {
        cji.m5162f(cnfVar, ParserSupports.PROPERTY);
        return true;
    }

    public ObservableProperty(T t) {
        this.f20804a = t;
    }

    @Override // p110z1.clk
    /* renamed from: a */
    public T mo4911a(@dbs Object obj, @NotNull cnf<?> cnfVar) {
        cji.m5162f(cnfVar, ParserSupports.PROPERTY);
        return this.f20804a;
    }

    @Override // p110z1.clk
    /* renamed from: a */
    public void mo4910a(@dbs Object obj, @NotNull cnf<?> cnfVar, T t) {
        cji.m5162f(cnfVar, ParserSupports.PROPERTY);
        T t2 = this.f20804a;
        if (mo4913b(cnfVar, t2, t)) {
            this.f20804a = t;
            mo4914a(cnfVar, t2, t);
        }
    }
}
