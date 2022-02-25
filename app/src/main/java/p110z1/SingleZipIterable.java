package p110z1;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import p110z1.SingleMap;
import p110z1.SingleZipArray;

/* renamed from: z1.bqi */
/* loaded from: classes3.dex */
public final class SingleZipIterable<T, R> extends Single<R> {

    /* renamed from: a */
    final Iterable<? extends SingleSource<? extends T>> f19848a;

    /* renamed from: b */
    final Function<? super Object[], ? extends R> f19849b;

    public SingleZipIterable(Iterable<? extends SingleSource<? extends T>> iterable, Function<? super Object[], ? extends R> aunVar) {
        this.f19848a = iterable;
        this.f19849b = aunVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super R> asxVar) {
        SingleSource[] ataVarArr = new SingleSource[8];
        try {
            Iterator<? extends SingleSource<? extends T>> it = this.f19848a.iterator();
            SingleSource[] ataVarArr2 = ataVarArr;
            int i = 0;
            while (it.hasNext()) {
                SingleSource ataVar = (SingleSource) it.next();
                if (ataVar == null) {
                    EmptyDisposable.error(new NullPointerException("One of the sources is null"), asxVar);
                    return;
                }
                if (i == ataVarArr2.length) {
                    ataVarArr2 = (SingleSource[]) Arrays.copyOf(ataVarArr2, (i >> 2) + i);
                }
                i++;
                ataVarArr2[i] = ataVar;
            }
            if (i == 0) {
                EmptyDisposable.error(new NoSuchElementException(), asxVar);
            } else if (i == 1) {
                ataVarArr2[0].mo10018a(new SingleMap.C4691a(asxVar, new C4709a()));
            } else {
                SingleZipArray.C4707b bVar = new SingleZipArray.C4707b(asxVar, i, this.f19849b);
                asxVar.onSubscribe(bVar);
                for (int i2 = 0; i2 < i && !bVar.isDisposed(); i2++) {
                    ataVarArr2[i2].mo10018a(bVar.observers[i2]);
                }
            }
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            EmptyDisposable.error(th, asxVar);
        }
    }

    /* compiled from: SingleZipIterable.java */
    /* renamed from: z1.bqi$a */
    /* loaded from: classes3.dex */
    final class C4709a implements Function<T, R> {
        C4709a() {
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object[], java.lang.Object] */
        @Override // p110z1.Function
        public R apply(T t) throws Exception {
            return (R) ObjectHelper.m9873a(SingleZipIterable.this.f19849b.apply(new Object[]{t}), "The zipper returned a null value");
        }
    }
}
