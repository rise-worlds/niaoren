package p110z1;

import java.util.concurrent.Callable;

/* renamed from: z1.bid */
/* loaded from: classes3.dex */
final class ScalarXMapZHelper {
    private ScalarXMapZHelper() {
        throw new IllegalStateException("No instances!");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static <T> boolean m9672a(Object obj, Function<? super T, ? extends CompletableSource> aunVar, CompletableObserver arpVar) {
        if (!(obj instanceof Callable)) {
            return false;
        }
        CompletableSource arsVar = null;
        try {
            Object obj2 = (Object) ((Callable) obj).call();
            if (obj2 != 0) {
                arsVar = (CompletableSource) ObjectHelper.m9873a(aunVar.apply(obj2), "The mapper returned a null CompletableSource");
            }
            if (arsVar == null) {
                EmptyDisposable.complete(arpVar);
            } else {
                arsVar.mo11309a(arpVar);
            }
            return true;
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            EmptyDisposable.error(th, arpVar);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static <T, R> boolean m9671a(Object obj, Function<? super T, ? extends MaybeSource<? extends R>> aunVar, Observer<? super R> assVar) {
        if (!(obj instanceof Callable)) {
            return false;
        }
        MaybeSource asiVar = null;
        try {
            Object obj2 = (Object) ((Callable) obj).call();
            if (obj2 != 0) {
                asiVar = (MaybeSource) ObjectHelper.m9873a(aunVar.apply(obj2), "The mapper returned a null MaybeSource");
            }
            if (asiVar == null) {
                EmptyDisposable.complete(assVar);
            } else {
                asiVar.mo10646a(MaybeToObservable.m9688b((Observer) assVar));
            }
            return true;
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            EmptyDisposable.error(th, assVar);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static <T, R> boolean m9670b(Object obj, Function<? super T, ? extends SingleSource<? extends R>> aunVar, Observer<? super R> assVar) {
        if (!(obj instanceof Callable)) {
            return false;
        }
        SingleSource ataVar = null;
        try {
            Object obj2 = (Object) ((Callable) obj).call();
            if (obj2 != 0) {
                ataVar = (SingleSource) ObjectHelper.m9873a(aunVar.apply(obj2), "The mapper returned a null SingleSource");
            }
            if (ataVar == null) {
                EmptyDisposable.complete(assVar);
            } else {
                ataVar.mo10018a(SingleToObservable.m9539b((Observer) assVar));
            }
            return true;
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            EmptyDisposable.error(th, assVar);
            return true;
        }
    }
}
