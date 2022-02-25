package com.noober.background.drawable;

import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.StyleableRes;
import com.noober.background.C1944R;

/* loaded from: classes.dex */
public class AnimationDrawableCreator implements ICreateDrawable {
    private TypedArray animationTa;
    private int duration = 0;
    private AnimationDrawable drawable = new AnimationDrawable();

    public AnimationDrawableCreator(TypedArray typedArray) {
        this.animationTa = typedArray;
    }

    @Override // com.noober.background.drawable.ICreateDrawable
    public Drawable create() throws Exception {
        Drawable drawable;
        for (int i = 0; i < this.animationTa.getIndexCount(); i++) {
            int index = this.animationTa.getIndex(i);
            if (index == C1944R.styleable.bl_anim_bl_duration) {
                this.duration = this.animationTa.getInt(index, 0);
            } else if (index == C1944R.styleable.bl_anim_bl_oneshot) {
                this.drawable.setOneShot(this.animationTa.getBoolean(index, false));
            }
        }
        if (this.animationTa.hasValue(C1944R.styleable.bl_anim_bl_frame_drawable_item0) && (drawable = this.animationTa.getDrawable(C1944R.styleable.bl_anim_bl_frame_drawable_item0)) != null) {
            if (this.animationTa.hasValue(C1944R.styleable.bl_anim_bl_duration_item0)) {
                this.drawable.addFrame(drawable, this.animationTa.getInt(C1944R.styleable.bl_anim_bl_duration_item0, 0));
            } else {
                this.drawable.addFrame(drawable, this.duration);
            }
        }
        addFrame(C1944R.styleable.bl_anim_bl_frame_drawable_item0, C1944R.styleable.bl_anim_bl_duration_item0);
        addFrame(C1944R.styleable.bl_anim_bl_frame_drawable_item1, C1944R.styleable.bl_anim_bl_duration_item1);
        addFrame(C1944R.styleable.bl_anim_bl_frame_drawable_item2, C1944R.styleable.bl_anim_bl_duration_item2);
        addFrame(C1944R.styleable.bl_anim_bl_frame_drawable_item3, C1944R.styleable.bl_anim_bl_duration_item3);
        addFrame(C1944R.styleable.bl_anim_bl_frame_drawable_item4, C1944R.styleable.bl_anim_bl_duration_item4);
        addFrame(C1944R.styleable.bl_anim_bl_frame_drawable_item5, C1944R.styleable.bl_anim_bl_duration_item5);
        addFrame(C1944R.styleable.bl_anim_bl_frame_drawable_item6, C1944R.styleable.bl_anim_bl_duration_item6);
        addFrame(C1944R.styleable.bl_anim_bl_frame_drawable_item7, C1944R.styleable.bl_anim_bl_duration_item7);
        addFrame(C1944R.styleable.bl_anim_bl_frame_drawable_item8, C1944R.styleable.bl_anim_bl_duration_item8);
        addFrame(C1944R.styleable.bl_anim_bl_frame_drawable_item9, C1944R.styleable.bl_anim_bl_duration_item9);
        addFrame(C1944R.styleable.bl_anim_bl_frame_drawable_item10, C1944R.styleable.bl_anim_bl_duration_item10);
        addFrame(C1944R.styleable.bl_anim_bl_frame_drawable_item11, C1944R.styleable.bl_anim_bl_duration_item11);
        addFrame(C1944R.styleable.bl_anim_bl_frame_drawable_item12, C1944R.styleable.bl_anim_bl_duration_item12);
        addFrame(C1944R.styleable.bl_anim_bl_frame_drawable_item13, C1944R.styleable.bl_anim_bl_duration_item13);
        addFrame(C1944R.styleable.bl_anim_bl_frame_drawable_item14, C1944R.styleable.bl_anim_bl_duration_item14);
        return this.drawable;
    }

    private void addFrame(@StyleableRes int i, @StyleableRes int i2) {
        Drawable drawable;
        if (this.animationTa.hasValue(i) && (drawable = this.animationTa.getDrawable(i)) != null) {
            if (this.animationTa.hasValue(i2)) {
                this.drawable.addFrame(drawable, this.animationTa.getInt(i2, 0));
            } else {
                this.drawable.addFrame(drawable, this.duration);
            }
        }
    }
}
