package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.RadioGroup;

/* renamed from: z1.aar */
/* loaded from: classes3.dex */
public final class RxRadioGroup {
    @CheckResult
    @NonNull
    /* renamed from: a */
    public static InitialValueObservable<Integer> m14499a(@NonNull RadioGroup radioGroup) {
        C5596xi.m126a(radioGroup, "view == null");
        return new RadioGroupCheckedChangeObservable(radioGroup);
    }

    @CheckResult
    @NonNull
    /* renamed from: b */
    public static Consumer<? super Integer> m14497b(@NonNull final RadioGroup radioGroup) {
        C5596xi.m126a(radioGroup, "view == null");
        return new Consumer() { // from class: z1.-$$Lambda$aar$5Sf6QFp8YovVTDGaCd09je5GlX4
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                RxRadioGroup.m14498a(radioGroup, (Integer) obj);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public static /* synthetic */ void m14498a(@NonNull RadioGroup radioGroup, Integer num) throws Exception {
        if (num.intValue() == -1) {
            radioGroup.clearCheck();
        } else {
            radioGroup.check(num.intValue());
        }
    }

    private RxRadioGroup() {
        throw new AssertionError("No instances.");
    }
}
