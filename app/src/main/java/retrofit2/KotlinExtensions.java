package retrofit2;

import android.support.v4.app.NotificationCompat;
import com.googlecode.tesseract.android.TessBaseAPI;
import java.lang.reflect.Method;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import org.apache.commons.p105io.FilenameUtils;
import p110z1.AbstractC4442bk;
import p110z1.Continuation;
import p110z1.DebugProbes;
import p110z1.KotlinNullPointerException;
import p110z1.Metadata;
import p110z1.NotNull;
import p110z1.Result;
import p110z1.bww;
import p110z1.cdz;
import p110z1.cgo;
import p110z1.cji;
import p110z1.dbs;

/* compiled from: KotlinExtensions.kt */
@Metadata(m8864a = 2, m8863b = {1, 1, 13}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a%\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u0003H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0004\u001a+\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00010\u0003H\u0087@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0004\u001a+\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0007\"\b\b\u0000\u0010\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u0003H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0004\u001a\u001a\u0010\b\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\tH\u0086\b¢\u0006\u0002\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, m8860e = {"await", TessBaseAPI.f9204e, "", "Lretrofit2/Call;", "(Lretrofit2/Call;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitNullable", "awaitResponse", "Lretrofit2/Response;", "create", "Lretrofit2/Retrofit;", "(Lretrofit2/Retrofit;)Ljava/lang/Object;", "retrofit"})
@cgo(m5270a = "KotlinExtensions")
/* loaded from: classes2.dex */
public final class KotlinExtensions {
    private static final <T> T create(@NotNull Retrofit retrofit) {
        cji.m5192a(4, TessBaseAPI.f9204e);
        return (T) retrofit.create(Object.class);
    }

    @dbs
    public static final <T> Object await(@NotNull Call<T> call, @NotNull Continuation<? super T> ccpVar) {
        CancellableContinuation cancellableContinuationImpl = new CancellableContinuationImpl(cdz.m5535a(ccpVar), 1);
        final CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
        cancellableContinuation.invokeOnCancellation(new C3291x19835f10(call));
        call.enqueue(new Callback<T>() { // from class: retrofit2.KotlinExtensions$await$2$2
            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<T> call2, @NotNull Response<T> response) {
                cji.m5162f(call2, NotificationCompat.CATEGORY_CALL);
                cji.m5162f(response, "response");
                if (response.isSuccessful()) {
                    Object body = response.body();
                    if (body == null) {
                        Object tag = call2.request().tag(Invocation.class);
                        if (tag == null) {
                            cji.m5196a();
                        }
                        cji.m5175b(tag, "call.request().tag(Invocation::class.java)!!");
                        Method method = ((Invocation) tag).method();
                        StringBuilder sb = new StringBuilder();
                        sb.append("Response from ");
                        cji.m5175b(method, AbstractC4442bk.f19102q);
                        Class<?> declaringClass = method.getDeclaringClass();
                        cji.m5175b(declaringClass, "method.declaringClass");
                        sb.append(declaringClass.getName());
                        sb.append(FilenameUtils.EXTENSION_SEPARATOR);
                        sb.append(method.getName());
                        sb.append(" was null but response body type was declared as non-null");
                        KotlinNullPointerException bvwVar = new KotlinNullPointerException(sb.toString());
                        Result.C4802a aVar = Result.Companion;
                        cancellableContinuation.resumeWith(Result.m25723constructorimpl(bww.m8760a((Throwable) bvwVar)));
                        return;
                    }
                    Result.C4802a aVar2 = Result.Companion;
                    cancellableContinuation.resumeWith(Result.m25723constructorimpl(body));
                    return;
                }
                Result.C4802a aVar3 = Result.Companion;
                cancellableContinuation.resumeWith(Result.m25723constructorimpl(bww.m8760a((Throwable) new HttpException(response))));
            }

            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<T> call2, @NotNull Throwable th) {
                cji.m5162f(call2, NotificationCompat.CATEGORY_CALL);
                cji.m5162f(th, "t");
                Result.C4802a aVar = Result.Companion;
                cancellableContinuation.resumeWith(Result.m25723constructorimpl(bww.m8760a(th)));
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == cdz.m5528b()) {
            DebugProbes.m5503c(ccpVar);
        }
        return result;
    }

    @dbs
    @cgo(m5270a = "awaitNullable")
    public static final <T> Object awaitNullable(@NotNull Call<T> call, @NotNull Continuation<? super T> ccpVar) {
        CancellableContinuation cancellableContinuationImpl = new CancellableContinuationImpl(cdz.m5535a(ccpVar), 1);
        final CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
        cancellableContinuation.invokeOnCancellation(new C3292x19835f11(call));
        call.enqueue(new Callback<T>() { // from class: retrofit2.KotlinExtensions$await$4$2
            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<T> call2, @NotNull Response<T> response) {
                cji.m5162f(call2, NotificationCompat.CATEGORY_CALL);
                cji.m5162f(response, "response");
                if (response.isSuccessful()) {
                    Object body = response.body();
                    Result.C4802a aVar = Result.Companion;
                    cancellableContinuation.resumeWith(Result.m25723constructorimpl(body));
                    return;
                }
                Result.C4802a aVar2 = Result.Companion;
                cancellableContinuation.resumeWith(Result.m25723constructorimpl(bww.m8760a((Throwable) new HttpException(response))));
            }

            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<T> call2, @NotNull Throwable th) {
                cji.m5162f(call2, NotificationCompat.CATEGORY_CALL);
                cji.m5162f(th, "t");
                Result.C4802a aVar = Result.Companion;
                cancellableContinuation.resumeWith(Result.m25723constructorimpl(bww.m8760a(th)));
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == cdz.m5528b()) {
            DebugProbes.m5503c(ccpVar);
        }
        return result;
    }

    @dbs
    public static final <T> Object awaitResponse(@NotNull Call<T> call, @NotNull Continuation<? super Response<T>> ccpVar) {
        CancellableContinuation cancellableContinuationImpl = new CancellableContinuationImpl(cdz.m5535a(ccpVar), 1);
        final CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
        cancellableContinuation.invokeOnCancellation(new C3293xc95e9eb1(call));
        call.enqueue(new Callback<T>() { // from class: retrofit2.KotlinExtensions$awaitResponse$2$2
            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<T> call2, @NotNull Response<T> response) {
                cji.m5162f(call2, NotificationCompat.CATEGORY_CALL);
                cji.m5162f(response, "response");
                Result.C4802a aVar = Result.Companion;
                cancellableContinuation.resumeWith(Result.m25723constructorimpl(response));
            }

            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<T> call2, @NotNull Throwable th) {
                cji.m5162f(call2, NotificationCompat.CATEGORY_CALL);
                cji.m5162f(th, "t");
                Result.C4802a aVar = Result.Companion;
                cancellableContinuation.resumeWith(Result.m25723constructorimpl(bww.m8760a(th)));
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == cdz.m5528b()) {
            DebugProbes.m5503c(ccpVar);
        }
        return result;
    }
}
