package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.TextView;

/* renamed from: z1.aaw */
/* loaded from: classes3.dex */
public final class RxTextView {
    @CheckResult
    @NonNull
    /* renamed from: a */
    public static Observable<Integer> m14482a(@NonNull TextView textView) {
        C5596xi.m126a(textView, "view == null");
        return m14480a(textView, C5593xf.f23667b);
    }

    @CheckResult
    @NonNull
    /* renamed from: a */
    public static Observable<Integer> m14480a(@NonNull TextView textView, @NonNull Predicate<? super Integer> auxVar) {
        C5596xi.m126a(textView, "view == null");
        C5596xi.m126a(auxVar, "handled == null");
        return new TextViewEditorActionObservable(textView, auxVar);
    }

    @CheckResult
    @NonNull
    /* renamed from: b */
    public static Observable<TextViewEditorActionEvent> m14479b(@NonNull TextView textView) {
        C5596xi.m126a(textView, "view == null");
        return m14478b(textView, C5593xf.f23667b);
    }

    @CheckResult
    @NonNull
    /* renamed from: b */
    public static Observable<TextViewEditorActionEvent> m14478b(@NonNull TextView textView, @NonNull Predicate<? super TextViewEditorActionEvent> auxVar) {
        C5596xi.m126a(textView, "view == null");
        C5596xi.m126a(auxVar, "handled == null");
        return new TextViewEditorActionEventObservable(textView, auxVar);
    }

    @CheckResult
    @NonNull
    /* renamed from: c */
    public static InitialValueObservable<CharSequence> m14477c(@NonNull TextView textView) {
        C5596xi.m126a(textView, "view == null");
        return new TextViewTextObservable(textView);
    }

    @CheckResult
    @NonNull
    /* renamed from: d */
    public static InitialValueObservable<TextViewTextChangeEvent> m14476d(@NonNull TextView textView) {
        C5596xi.m126a(textView, "view == null");
        return new TextViewTextChangeEventObservable(textView);
    }

    @CheckResult
    @NonNull
    /* renamed from: e */
    public static InitialValueObservable<TextViewBeforeTextChangeEvent> m14475e(@NonNull TextView textView) {
        C5596xi.m126a(textView, "view == null");
        return new TextViewBeforeTextChangeEventObservable(textView);
    }

    @CheckResult
    @NonNull
    /* renamed from: f */
    public static InitialValueObservable<TextViewAfterTextChangeEvent> m14474f(@NonNull TextView textView) {
        C5596xi.m126a(textView, "view == null");
        return new TextViewAfterTextChangeEventObservable(textView);
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: g */
    public static Consumer<? super CharSequence> m14473g(@NonNull final TextView textView) {
        C5596xi.m126a(textView, "view == null");
        textView.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$_TgR95GQgF8pWIMGadN6msjghk8
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                textView.setText((CharSequence) obj);
            }
        };
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: h */
    public static Consumer<? super Integer> m14472h(@NonNull final TextView textView) {
        C5596xi.m126a(textView, "view == null");
        textView.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$-sOKQlgEN0fox0vaBGw4VXe8yBI
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                textView.setText(((Integer) obj).intValue());
            }
        };
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: i */
    public static Consumer<? super CharSequence> m14471i(@NonNull final TextView textView) {
        C5596xi.m126a(textView, "view == null");
        textView.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$S6BMAc9xCbTfyuMD_aC8rZvuOUk
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                textView.setError((CharSequence) obj);
            }
        };
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: j */
    public static Consumer<? super Integer> m14470j(@NonNull final TextView textView) {
        C5596xi.m126a(textView, "view == null");
        return new Consumer() { // from class: z1.-$$Lambda$aaw$kwQs79SmTjRMZGhDFSfuG7NxKwQ
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                RxTextView.m14481a(textView, (Integer) obj);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public static /* synthetic */ void m14481a(@NonNull TextView textView, Integer num) throws Exception {
        textView.setError(textView.getContext().getResources().getText(num.intValue()));
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: k */
    public static Consumer<? super CharSequence> m14469k(@NonNull final TextView textView) {
        C5596xi.m126a(textView, "view == null");
        textView.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$08Ww22sHyGNFmcfoYZdeCy0Jg4g
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                textView.setHint((CharSequence) obj);
            }
        };
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: l */
    public static Consumer<? super Integer> m14468l(@NonNull final TextView textView) {
        C5596xi.m126a(textView, "view == null");
        textView.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$tkZQP_1Eq5O23h15CNIA8rs1o7E
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                textView.setHint(((Integer) obj).intValue());
            }
        };
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: m */
    public static Consumer<? super Integer> m14467m(@NonNull final TextView textView) {
        C5596xi.m126a(textView, "view == null");
        textView.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$ehrgFah1bBrzqbolCbx7SIe-NLo
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                textView.setTextColor(((Integer) obj).intValue());
            }
        };
    }

    private RxTextView() {
        throw new AssertionError("No instances.");
    }
}
