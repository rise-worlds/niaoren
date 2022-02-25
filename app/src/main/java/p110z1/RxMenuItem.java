package p110z1;

import android.graphics.drawable.Drawable;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.MenuItem;

/* renamed from: z1.xx */
/* loaded from: classes3.dex */
public final class RxMenuItem {
    @CheckResult
    @NonNull
    /* renamed from: a */
    public static Observable<Object> m118a(@NonNull MenuItem menuItem) {
        C5596xi.m126a(menuItem, "menuItem == null");
        return new MenuItemClickOnSubscribe(menuItem, C5593xf.f23667b);
    }

    @CheckResult
    @NonNull
    /* renamed from: a */
    public static Observable<Object> m117a(@NonNull MenuItem menuItem, @NonNull Predicate<? super MenuItem> auxVar) {
        C5596xi.m126a(menuItem, "menuItem == null");
        C5596xi.m126a(auxVar, "handled == null");
        return new MenuItemClickOnSubscribe(menuItem, auxVar);
    }

    @CheckResult
    @NonNull
    /* renamed from: b */
    public static Observable<MenuItemActionViewEvent> m116b(@NonNull MenuItem menuItem) {
        C5596xi.m126a(menuItem, "menuItem == null");
        return new MenuItemActionViewEventObservable(menuItem, C5593xf.f23667b);
    }

    @CheckResult
    @NonNull
    /* renamed from: b */
    public static Observable<MenuItemActionViewEvent> m115b(@NonNull MenuItem menuItem, @NonNull Predicate<? super MenuItemActionViewEvent> auxVar) {
        C5596xi.m126a(menuItem, "menuItem == null");
        C5596xi.m126a(auxVar, "handled == null");
        return new MenuItemActionViewEventObservable(menuItem, auxVar);
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: c */
    public static Consumer<? super Boolean> m114c(@NonNull final MenuItem menuItem) {
        C5596xi.m126a(menuItem, "menuItem == null");
        menuItem.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$b-T9Fy2pHQbaghv83HRjjvob53o
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                menuItem.setChecked(((Boolean) obj).booleanValue());
            }
        };
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: d */
    public static Consumer<? super Boolean> m113d(@NonNull final MenuItem menuItem) {
        C5596xi.m126a(menuItem, "menuItem == null");
        menuItem.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$x6QYrRtnLpDkGY8aTz_lHsDbUWo
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                menuItem.setEnabled(((Boolean) obj).booleanValue());
            }
        };
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: e */
    public static Consumer<? super Drawable> m112e(@NonNull final MenuItem menuItem) {
        C5596xi.m126a(menuItem, "menuItem == null");
        menuItem.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$FJicwosndJz-V0faP8UtNCXKnwE
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                menuItem.setIcon((Drawable) obj);
            }
        };
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: f */
    public static Consumer<? super Integer> m111f(@NonNull final MenuItem menuItem) {
        C5596xi.m126a(menuItem, "menuItem == null");
        menuItem.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$PKN1GOD5K58JdTZjjyDFaLn_oV8
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                menuItem.setIcon(((Integer) obj).intValue());
            }
        };
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: g */
    public static Consumer<? super CharSequence> m110g(@NonNull final MenuItem menuItem) {
        C5596xi.m126a(menuItem, "menuItem == null");
        menuItem.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$wLWujyn-XKkUVy8HBSPkFOftmTY
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                menuItem.setTitle((CharSequence) obj);
            }
        };
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: h */
    public static Consumer<? super Integer> m109h(@NonNull final MenuItem menuItem) {
        C5596xi.m126a(menuItem, "menuItem == null");
        menuItem.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$01MVCgBXwIZNtgued3XV_jZPJwE
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                menuItem.setTitle(((Integer) obj).intValue());
            }
        };
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: i */
    public static Consumer<? super Boolean> m108i(@NonNull final MenuItem menuItem) {
        C5596xi.m126a(menuItem, "menuItem == null");
        menuItem.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$CW3JJaTKFcwnLdUOB89NeHm61fs
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                menuItem.setVisible(((Boolean) obj).booleanValue());
            }
        };
    }

    private RxMenuItem() {
        throw new AssertionError("No instances.");
    }
}
