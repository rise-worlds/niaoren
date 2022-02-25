package patch;

import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/* renamed from: patch.d */
/* loaded from: classes2.dex */
public class PatchedResources extends Resources {

    /* renamed from: a */
    AssetManager f15016a;

    /* renamed from: b */
    DisplayMetrics f15017b;

    /* renamed from: c */
    Configuration f15018c;

    /* renamed from: d */
    private int f15019d;

    public PatchedResources(AssetManager assetManager, DisplayMetrics displayMetrics, Configuration configuration, int i) {
        super(assetManager, displayMetrics, configuration);
        this.f15016a = assetManager;
        this.f15017b = displayMetrics;
        this.f15018c = configuration;
        this.f15019d = i;
    }

    @Override // android.content.res.Resources
    public DisplayMetrics getDisplayMetrics() {
        DisplayMetrics displayMetrics = super.getDisplayMetrics();
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        displayMetrics.widthPixels = i > i2 ? i : i2;
        if (i > i2) {
            i = i2;
        }
        displayMetrics.heightPixels = i;
        return displayMetrics;
    }
}
