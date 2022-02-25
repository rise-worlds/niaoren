package p110z1;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;

/* renamed from: z1.xc */
/* loaded from: classes3.dex */
public final class RxJava2CallAdapterFactory extends CallAdapter.Factory {

    /* renamed from: a */
    private final Scheduler f23664a;

    /* renamed from: a */
    public static RxJava2CallAdapterFactory m130a() {
        return new RxJava2CallAdapterFactory(null);
    }

    /* renamed from: a */
    public static RxJava2CallAdapterFactory m129a(Scheduler astVar) {
        if (astVar != null) {
            return new RxJava2CallAdapterFactory(astVar);
        }
        throw new NullPointerException("scheduler == null");
    }

    private RxJava2CallAdapterFactory(Scheduler astVar) {
        this.f23664a = astVar;
    }

    @Override // retrofit2.CallAdapter.Factory
    public CallAdapter<?> get(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        boolean z;
        boolean z2;
        Type type2;
        Class<?> rawType = getRawType(type);
        if (rawType == Completable.class) {
            return new RxJava2CallAdapter(Void.class, this.f23664a, false, true, false, false, false, true);
        }
        boolean z3 = rawType == Flowable.class;
        boolean z4 = rawType == Single.class;
        boolean z5 = rawType == Maybe.class;
        if (rawType != Observable.class && !z3 && !z4 && !z5) {
            return null;
        }
        if (!(type instanceof ParameterizedType)) {
            String str = !z3 ? z4 ? "Single" : "Observable" : "Flowable";
            throw new IllegalStateException(str + " return type must be parameterized as " + str + "<Foo> or " + str + "<? extends Foo>");
        }
        Type parameterUpperBound = getParameterUpperBound(0, (ParameterizedType) type);
        Class<?> rawType2 = getRawType(parameterUpperBound);
        if (rawType2 == Response.class) {
            if (parameterUpperBound instanceof ParameterizedType) {
                type2 = getParameterUpperBound(0, (ParameterizedType) parameterUpperBound);
                z2 = false;
                z = false;
            } else {
                throw new IllegalStateException("Response must be parameterized as Response<Foo> or Response<? extends Foo>");
            }
        } else if (rawType2 != C5588wz.class) {
            type2 = parameterUpperBound;
            z2 = false;
            z = true;
        } else if (parameterUpperBound instanceof ParameterizedType) {
            type2 = getParameterUpperBound(0, (ParameterizedType) parameterUpperBound);
            z2 = true;
            z = false;
        } else {
            throw new IllegalStateException("Result must be parameterized as Result<Foo> or Result<? extends Foo>");
        }
        return new RxJava2CallAdapter(type2, this.f23664a, z2, z, z3, z4, z5, false);
    }
}
