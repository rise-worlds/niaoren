package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import java.util.concurrent.Callable;

/* renamed from: z1.xy */
/* loaded from: classes3.dex */
public final class RxView {
    @CheckResult
    @NonNull
    /* renamed from: a */
    public static Observable<Object> m107a(@NonNull View view) {
        C5596xi.m126a(view, "view == null");
        return new ViewAttachesObservable(view, true);
    }

    @CheckResult
    @NonNull
    /* renamed from: b */
    public static Observable<ViewAttachEvent> m102b(@NonNull View view) {
        C5596xi.m126a(view, "view == null");
        return new ViewAttachEventObservable(view);
    }

    @CheckResult
    @NonNull
    /* renamed from: c */
    public static Observable<Object> m99c(@NonNull View view) {
        C5596xi.m126a(view, "view == null");
        return new ViewAttachesObservable(view, false);
    }

    @CheckResult
    @NonNull
    /* renamed from: d */
    public static Observable<Object> m97d(@NonNull View view) {
        C5596xi.m126a(view, "view == null");
        return new ViewClickObservable(view);
    }

    @CheckResult
    @NonNull
    /* renamed from: e */
    public static Observable<DragEvent> m95e(@NonNull View view) {
        C5596xi.m126a(view, "view == null");
        return new ViewDragObservable(view, C5593xf.f23667b);
    }

    @CheckResult
    @NonNull
    /* renamed from: a */
    public static Observable<DragEvent> m103a(@NonNull View view, @NonNull Predicate<? super DragEvent> auxVar) {
        C5596xi.m126a(view, "view == null");
        C5596xi.m126a(auxVar, "handled == null");
        return new ViewDragObservable(view, auxVar);
    }

    @CheckResult
    @NonNull
    @RequiresApi(16)
    /* renamed from: f */
    public static Observable<Object> m94f(@NonNull View view) {
        C5596xi.m126a(view, "view == null");
        return new ViewTreeObserverDrawObservable(view);
    }

    @CheckResult
    @NonNull
    /* renamed from: g */
    public static InitialValueObservable<Boolean> m93g(@NonNull View view) {
        C5596xi.m126a(view, "view == null");
        return new ViewFocusChangeObservable(view);
    }

    @CheckResult
    @NonNull
    /* renamed from: h */
    public static Observable<Object> m92h(@NonNull View view) {
        C5596xi.m126a(view, "view == null");
        return new ViewTreeObserverGlobalLayoutObservable(view);
    }

    @CheckResult
    @NonNull
    /* renamed from: i */
    public static Observable<MotionEvent> m91i(@NonNull View view) {
        C5596xi.m126a(view, "view == null");
        return new ViewHoverObservable(view, C5593xf.f23667b);
    }

    @CheckResult
    @NonNull
    /* renamed from: b */
    public static Observable<MotionEvent> m100b(@NonNull View view, @NonNull Predicate<? super MotionEvent> auxVar) {
        C5596xi.m126a(view, "view == null");
        C5596xi.m126a(auxVar, "handled == null");
        return new ViewHoverObservable(view, auxVar);
    }

    @CheckResult
    @NonNull
    /* renamed from: j */
    public static Observable<Object> m90j(@NonNull View view) {
        C5596xi.m126a(view, "view == null");
        return new ViewLayoutChangeObservable(view);
    }

    @CheckResult
    @NonNull
    /* renamed from: k */
    public static Observable<ViewLayoutChangeEvent> m89k(@NonNull View view) {
        C5596xi.m126a(view, "view == null");
        return new ViewLayoutChangeEventObservable(view);
    }

    @CheckResult
    @NonNull
    /* renamed from: l */
    public static Observable<Object> m88l(@NonNull View view) {
        C5596xi.m126a(view, "view == null");
        return new ViewLongClickObservable(view, C5593xf.f23666a);
    }

    @CheckResult
    @NonNull
    /* renamed from: a */
    public static Observable<Object> m104a(@NonNull View view, @NonNull Callable<Boolean> callable) {
        C5596xi.m126a(view, "view == null");
        C5596xi.m126a(callable, "handled == null");
        return new ViewLongClickObservable(view, callable);
    }

    @CheckResult
    @NonNull
    /* renamed from: b */
    public static Observable<Object> m101b(@NonNull View view, @NonNull Callable<Boolean> callable) {
        C5596xi.m126a(view, "view == null");
        C5596xi.m126a(callable, "proceedDrawingPass == null");
        return new ViewTreeObserverPreDrawObservable(view, callable);
    }

    @CheckResult
    @NonNull
    @RequiresApi(23)
    /* renamed from: m */
    public static Observable<ViewScrollChangeEvent> m87m(@NonNull View view) {
        C5596xi.m126a(view, "view == null");
        return new ViewScrollChangeEventObservable(view);
    }

    @CheckResult
    @NonNull
    /* renamed from: n */
    public static Observable<Integer> m86n(@NonNull View view) {
        C5596xi.m126a(view, "view == null");
        return new ViewSystemUiVisibilityChangeObservable(view);
    }

    @CheckResult
    @NonNull
    /* renamed from: o */
    public static Observable<MotionEvent> m85o(@NonNull View view) {
        C5596xi.m126a(view, "view == null");
        return new ViewTouchObservable(view, C5593xf.f23667b);
    }

    @CheckResult
    @NonNull
    /* renamed from: c */
    public static Observable<MotionEvent> m98c(@NonNull View view, @NonNull Predicate<? super MotionEvent> auxVar) {
        C5596xi.m126a(view, "view == null");
        C5596xi.m126a(auxVar, "handled == null");
        return new ViewTouchObservable(view, auxVar);
    }

    @CheckResult
    @NonNull
    /* renamed from: p */
    public static Observable<KeyEvent> m84p(@NonNull View view) {
        C5596xi.m126a(view, "view == null");
        return new ViewKeyObservable(view, C5593xf.f23667b);
    }

    @CheckResult
    @NonNull
    /* renamed from: d */
    public static Observable<KeyEvent> m96d(@NonNull View view, @NonNull Predicate<? super KeyEvent> auxVar) {
        C5596xi.m126a(view, "view == null");
        C5596xi.m126a(auxVar, "handled == null");
        return new ViewKeyObservable(view, auxVar);
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: q */
    public static Consumer<? super Boolean> m83q(@NonNull final View view) {
        C5596xi.m126a(view, "view == null");
        view.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$gK172Xgn5xEfPYz7r4UPn1qNik4
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                view.setActivated(((Boolean) obj).booleanValue());
            }
        };
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: r */
    public static Consumer<? super Boolean> m82r(@NonNull final View view) {
        C5596xi.m126a(view, "view == null");
        view.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$LM0tNECl2sa_Vsvb8SOxmMC8_Wg
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                view.setClickable(((Boolean) obj).booleanValue());
            }
        };
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: s */
    public static Consumer<? super Boolean> m81s(@NonNull final View view) {
        C5596xi.m126a(view, "view == null");
        view.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$KYLyHv_QtrhhGLpVbgx2u85Qbzc
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                view.setEnabled(((Boolean) obj).booleanValue());
            }
        };
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: t */
    public static Consumer<? super Boolean> m80t(@NonNull final View view) {
        C5596xi.m126a(view, "view == null");
        view.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$Tmm4CkdFX-rrOzY-IHAW2o_cMf4
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                view.setPressed(((Boolean) obj).booleanValue());
            }
        };
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: u */
    public static Consumer<? super Boolean> m79u(@NonNull final View view) {
        C5596xi.m126a(view, "view == null");
        view.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$76y1IytPcMRIfWgJatswPY7COrY
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                view.setSelected(((Boolean) obj).booleanValue());
            }
        };
    }

    @CheckResult
    @NonNull
    /* renamed from: v */
    public static Consumer<? super Boolean> m78v(@NonNull View view) {
        C5596xi.m126a(view, "view == null");
        return m106a(view, 8);
    }

    @CheckResult
    @NonNull
    /* renamed from: a */
    public static Consumer<? super Boolean> m106a(@NonNull final View view, final int i) {
        C5596xi.m126a(view, "view == null");
        if (i == 0) {
            throw new IllegalArgumentException("Setting visibility to VISIBLE when false would have no effect.");
        } else if (i == 4 || i == 8) {
            return new Consumer() { // from class: z1.-$$Lambda$xy$JW4Lm71TeH-fBBo3XQEOjGGj9bg
                @Override // p110z1.Consumer
                public final void accept(Object obj) {
                    RxView.m105a(view, i, (Boolean) obj);
                }
            };
        } else {
            throw new IllegalArgumentException("Must set visibility to INVISIBLE or GONE when false.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public static /* synthetic */ void m105a(@NonNull View view, int i, Boolean bool) throws Exception {
        if (bool.booleanValue()) {
            i = 0;
        }
        view.setVisibility(i);
    }

    private RxView() {
        throw new AssertionError("No instances.");
    }
}
