package p110z1;

import java.util.Arrays;
import java.util.Iterator;
import p110z1.MaybeMap;
import p110z1.MaybeZipArray;

/* renamed from: z1.bhl */
/* loaded from: classes3.dex */
public final class MaybeZipIterable<T, R> extends Maybe<R> {

    /* renamed from: a */
    final Iterable<? extends MaybeSource<? extends T>> f18732a;

    /* renamed from: b */
    final Function<? super Object[], ? extends R> f18733b;

    public MaybeZipIterable(Iterable<? extends MaybeSource<? extends T>> iterable, Function<? super Object[], ? extends R> aunVar) {
        this.f18732a = iterable;
        this.f18733b = aunVar;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super R> asfVar) {
        MaybeSource[] asiVarArr = new MaybeSource[8];
        try {
            Iterator<? extends MaybeSource<? extends T>> it = this.f18732a.iterator();
            MaybeSource[] asiVarArr2 = asiVarArr;
            int i = 0;
            while (it.hasNext()) {
                MaybeSource asiVar = (MaybeSource) it.next();
                if (asiVar == null) {
                    EmptyDisposable.error(new NullPointerException("One of the sources is null"), asfVar);
                    return;
                }
                if (i == asiVarArr2.length) {
                    asiVarArr2 = (MaybeSource[]) Arrays.copyOf(asiVarArr2, (i >> 2) + i);
                }
                i++;
                asiVarArr2[i] = asiVar;
            }
            if (i == 0) {
                EmptyDisposable.complete(asfVar);
            } else if (i == 1) {
                asiVarArr2[0].mo10646a(new MaybeMap.C4309a(asfVar, new C4346a()));
            } else {
                MaybeZipArray.C4344b bVar = new MaybeZipArray.C4344b(asfVar, i, this.f18733b);
                asfVar.onSubscribe(bVar);
                for (int i2 = 0; i2 < i && !bVar.isDisposed(); i2++) {
                    asiVarArr2[i2].mo10646a(bVar.observers[i2]);
                }
            }
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            EmptyDisposable.error(th, asfVar);
        }
    }

    /* compiled from: MaybeZipIterable.java */
    /* renamed from: z1.bhl$a */
    /* loaded from: classes3.dex */
    final class C4346a implements Function<T, R> {
        C4346a() {
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object[], java.lang.Object] */
        @Override // p110z1.Function
        public R apply(T t) throws Exception {
            return (R) ObjectHelper.m9873a(MaybeZipIterable.this.f18733b.apply(new Object[]{t}), "The zipper returned a null value");
        }
    }
}
