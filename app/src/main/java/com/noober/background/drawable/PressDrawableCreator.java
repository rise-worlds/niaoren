package com.noober.background.drawable;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import com.noober.background.C1944R;

/* loaded from: classes.dex */
public class PressDrawableCreator implements ICreateDrawable {
    private GradientDrawable drawable;
    private TypedArray pressTa;
    private TypedArray typedArray;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PressDrawableCreator(GradientDrawable gradientDrawable, TypedArray typedArray, TypedArray typedArray2) {
        this.drawable = gradientDrawable;
        this.pressTa = typedArray2;
        this.typedArray = typedArray;
    }

    @Override // com.noober.background.drawable.ICreateDrawable
    public Drawable create() throws Exception {
        StateListDrawable stateListDrawable = new StateListDrawable();
        for (int i = 0; i < this.pressTa.getIndexCount(); i++) {
            int index = this.pressTa.getIndex(i);
            if (index == C1944R.styleable.background_press_bl_pressed_color) {
                int color = this.pressTa.getColor(index, 0);
                GradientDrawable drawable = DrawableFactory.getDrawable(this.typedArray);
                drawable.setColor(color);
                stateListDrawable.addState(new int[]{16842919}, drawable);
            } else if (index == C1944R.styleable.background_press_bl_unpressed_color) {
                this.drawable.setColor(this.pressTa.getColor(index, 0));
                stateListDrawable.addState(new int[]{-16842919}, this.drawable);
            }
        }
        return stateListDrawable;
    }
}
