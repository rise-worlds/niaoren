package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import com.stripe.android.view.ShippingInfoWidget;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.apache.tools.ant.types.selectors.SizeSelector;

@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH$J\b\u0010\n\u001a\u00020\tH\u0004J\t\u0010\u000b\u001a\u00020\fH\u0096\u0002J\u000e\u0010\r\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u000eJ\u0015\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00028\u0000H\u0004¢\u0006\u0002\u0010\u0011J\b\u0010\u0012\u001a\u00020\fH\u0002R\u0012\u0010\u0004\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m8860e = {"Lkotlin/collections/AbstractIterator;", TessBaseAPI.f9204e, "", "()V", "nextValue", "Ljava/lang/Object;", ShippingInfoWidget.f12562e, "Lkotlin/collections/State;", "computeNext", "", "done", "hasNext", "", "next", "()Ljava/lang/Object;", "setNext", SizeSelector.SIZE_KEY, "(Ljava/lang/Object;)V", "tryToComputeNext", "kotlin-stdlib"})
/* renamed from: z1.byr */
/* loaded from: classes3.dex */
public abstract class AbstractIterator<T> implements Iterator<T>, KMarkers {

    /* renamed from: a */
    private cbe f20421a = cbe.NotReady;

    /* renamed from: b */
    private T f20422b;

    /* renamed from: a */
    protected abstract void mo4549a();

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.f20421a != cbe.Failed) {
            switch (this.f20421a) {
                case Done:
                    return false;
                case Ready:
                    return true;
                default:
                    return m8323c();
            }
        } else {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    @Override // java.util.Iterator
    public T next() {
        if (hasNext()) {
            this.f20421a = cbe.NotReady;
            return this.f20422b;
        }
        throw new NoSuchElementException();
    }

    /* renamed from: c */
    private final boolean m8323c() {
        this.f20421a = cbe.Failed;
        mo4549a();
        return this.f20421a == cbe.Ready;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final void m8325a(T t) {
        this.f20422b = t;
        this.f20421a = cbe.Ready;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public final void m8324b() {
        this.f20421a = cbe.Done;
    }
}
