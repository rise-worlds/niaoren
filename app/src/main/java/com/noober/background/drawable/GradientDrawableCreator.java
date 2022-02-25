package com.noober.background.drawable;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import com.noober.background.C1944R;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class GradientDrawableCreator implements ICreateDrawable {
    private TypedArray typedArray;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GradientDrawableCreator(TypedArray typedArray) {
        this.typedArray = typedArray;
    }

    @Override // com.noober.background.drawable.ICreateDrawable
    public Drawable create() throws XmlPullParserException {
        GradientDrawable gradientDrawable = new GradientDrawable();
        float[] fArr = new float[8];
        Rect rect = new Rect();
        int i = 0;
        int i2 = 0;
        float f = 0.0f;
        float f2 = 0.0f;
        int i3 = 0;
        int i4 = 0;
        float f3 = 0.0f;
        float f4 = -1.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (i2 < this.typedArray.getIndexCount()) {
            int index = this.typedArray.getIndex(i2);
            if (index == C1944R.styleable.background_bl_shape) {
                gradientDrawable.setShape(this.typedArray.getInt(index, i));
                f6 = f6;
            } else {
                if (index == C1944R.styleable.background_bl_solid_color) {
                    i3 = this.typedArray.getColor(index, i);
                } else if (index == C1944R.styleable.background_bl_corners_radius) {
                    gradientDrawable.setCornerRadius(this.typedArray.getDimension(index, 0.0f));
                    f6 = f6;
                } else if (index == C1944R.styleable.background_bl_corners_bottomLeftRadius) {
                    f6 = f6;
                    fArr[6] = this.typedArray.getDimension(index, 0.0f);
                    fArr[7] = this.typedArray.getDimension(index, 0.0f);
                } else {
                    f6 = f6;
                    if (index == C1944R.styleable.background_bl_corners_bottomRightRadius) {
                        fArr[4] = this.typedArray.getDimension(index, 0.0f);
                        fArr[5] = this.typedArray.getDimension(index, 0.0f);
                    } else if (index == C1944R.styleable.background_bl_corners_topLeftRadius) {
                        fArr[0] = this.typedArray.getDimension(index, 0.0f);
                        fArr[1] = this.typedArray.getDimension(index, 0.0f);
                    } else if (index == C1944R.styleable.background_bl_corners_topRightRadius) {
                        fArr[2] = this.typedArray.getDimension(index, 0.0f);
                        fArr[3] = this.typedArray.getDimension(index, 0.0f);
                    } else if (index == C1944R.styleable.background_bl_gradient_angle) {
                        i9 = this.typedArray.getInteger(index, 0);
                        f6 = f6;
                    } else if (index == C1944R.styleable.background_bl_gradient_centerX) {
                        f6 = this.typedArray.getFloat(index, -1.0f);
                    } else if (index == C1944R.styleable.background_bl_gradient_centerY) {
                        f7 = this.typedArray.getFloat(index, -1.0f);
                        f6 = f6;
                    } else if (index == C1944R.styleable.background_bl_gradient_centerColor) {
                        i5 = this.typedArray.getColor(index, 0);
                        f6 = f6;
                    } else if (index == C1944R.styleable.background_bl_gradient_endColor) {
                        i7 = this.typedArray.getColor(index, 0);
                        f6 = f6;
                    } else if (index == C1944R.styleable.background_bl_gradient_startColor) {
                        i6 = this.typedArray.getColor(index, 0);
                        f6 = f6;
                    } else if (index == C1944R.styleable.background_bl_gradient_gradientRadius) {
                        gradientDrawable.setGradientRadius(this.typedArray.getDimension(index, 0.0f));
                    } else if (index == C1944R.styleable.background_bl_gradient_type) {
                        int i10 = this.typedArray.getInt(index, 0);
                        gradientDrawable.setGradientType(i10);
                        i8 = i10;
                        f6 = f6;
                    } else if (index == C1944R.styleable.background_bl_gradient_useLevel) {
                        gradientDrawable.setUseLevel(this.typedArray.getBoolean(index, false));
                    } else if (index == C1944R.styleable.background_bl_padding_left) {
                        rect.left = (int) this.typedArray.getDimension(index, 0.0f);
                    } else if (index == C1944R.styleable.background_bl_padding_top) {
                        rect.top = (int) this.typedArray.getDimension(index, 0.0f);
                    } else if (index == C1944R.styleable.background_bl_padding_right) {
                        rect.right = (int) this.typedArray.getDimension(index, 0.0f);
                    } else if (index == C1944R.styleable.background_bl_padding_bottom) {
                        rect.bottom = (int) this.typedArray.getDimension(index, 0.0f);
                    } else if (index == C1944R.styleable.background_bl_size_width) {
                        f = this.typedArray.getDimension(index, 0.0f);
                        f6 = f6;
                    } else if (index == C1944R.styleable.background_bl_size_height) {
                        f2 = this.typedArray.getDimension(index, 0.0f);
                        f6 = f6;
                    } else if (index == C1944R.styleable.background_bl_stroke_width) {
                        f4 = this.typedArray.getDimension(index, 0.0f);
                        f6 = f6;
                    } else if (index == C1944R.styleable.background_bl_stroke_color) {
                        i4 = this.typedArray.getColor(index, 0);
                        f6 = f6;
                    } else if (index == C1944R.styleable.background_bl_stroke_dashWidth) {
                        f5 = this.typedArray.getDimension(index, 0.0f);
                        f6 = f6;
                    } else if (index == C1944R.styleable.background_bl_stroke_dashGap) {
                        f3 = this.typedArray.getDimension(index, 0.0f);
                        f6 = f6;
                    }
                }
                i2++;
                i = 0;
            }
            i2++;
            i = 0;
        }
        if (hasSetRadius(fArr)) {
            gradientDrawable.setCornerRadii(fArr);
        }
        if (this.typedArray.hasValue(C1944R.styleable.background_bl_size_width) && this.typedArray.hasValue(C1944R.styleable.background_bl_size_height)) {
            gradientDrawable.setSize((int) f, (int) f2);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            if (this.typedArray.hasValue(C1944R.styleable.background_bl_pressed_solid_color) && this.typedArray.hasValue(C1944R.styleable.background_bl_unPressed_solid_color)) {
                arrayList.add(16842919);
                arrayList.add(-16842919);
                arrayList2.add(Integer.valueOf(this.typedArray.getColor(C1944R.styleable.background_bl_pressed_solid_color, 0)));
                arrayList2.add(Integer.valueOf(this.typedArray.getColor(C1944R.styleable.background_bl_unPressed_solid_color, 0)));
            }
            if (this.typedArray.hasValue(C1944R.styleable.background_bl_checkable_solid_color) && this.typedArray.hasValue(C1944R.styleable.background_bl_unCheckable_solid_color)) {
                arrayList.add(16842911);
                arrayList.add(-16842911);
                arrayList2.add(Integer.valueOf(this.typedArray.getColor(C1944R.styleable.background_bl_checkable_solid_color, 0)));
                arrayList2.add(Integer.valueOf(this.typedArray.getColor(C1944R.styleable.background_bl_unCheckable_solid_color, 0)));
            }
            if (this.typedArray.hasValue(C1944R.styleable.background_bl_checked_solid_color) && this.typedArray.hasValue(C1944R.styleable.background_bl_unChecked_solid_color)) {
                arrayList.add(16842912);
                arrayList.add(-16842912);
                arrayList2.add(Integer.valueOf(this.typedArray.getColor(C1944R.styleable.background_bl_checked_solid_color, 0)));
                arrayList2.add(Integer.valueOf(this.typedArray.getColor(C1944R.styleable.background_bl_unChecked_solid_color, 0)));
            }
            if (this.typedArray.hasValue(C1944R.styleable.background_bl_enabled_solid_color) && this.typedArray.hasValue(C1944R.styleable.background_bl_unEnabled_solid_color)) {
                arrayList.add(16842910);
                arrayList.add(-16842910);
                arrayList2.add(Integer.valueOf(this.typedArray.getColor(C1944R.styleable.background_bl_enabled_solid_color, 0)));
                arrayList2.add(Integer.valueOf(this.typedArray.getColor(C1944R.styleable.background_bl_unEnabled_solid_color, 0)));
            }
            if (this.typedArray.hasValue(C1944R.styleable.background_bl_selected_solid_color) && this.typedArray.hasValue(C1944R.styleable.background_bl_unSelected_solid_color)) {
                arrayList.add(16842913);
                arrayList.add(-16842913);
                arrayList2.add(Integer.valueOf(this.typedArray.getColor(C1944R.styleable.background_bl_selected_solid_color, 0)));
                arrayList2.add(Integer.valueOf(this.typedArray.getColor(C1944R.styleable.background_bl_unSelected_solid_color, 0)));
            }
            if (this.typedArray.hasValue(C1944R.styleable.background_bl_focused_solid_color) && this.typedArray.hasValue(C1944R.styleable.background_bl_unFocused_solid_color)) {
                arrayList.add(16842908);
                arrayList.add(-16842908);
                arrayList2.add(Integer.valueOf(this.typedArray.getColor(C1944R.styleable.background_bl_focused_solid_color, 0)));
                arrayList2.add(Integer.valueOf(this.typedArray.getColor(C1944R.styleable.background_bl_unFocused_solid_color, 0)));
            }
            if (arrayList.size() > 0) {
                int[][] iArr = new int[arrayList.size()];
                int[] iArr2 = new int[arrayList.size()];
                Iterator it = arrayList.iterator();
                int i11 = 0;
                while (it.hasNext()) {
                    int[] iArr3 = new int[1];
                    iArr3[0] = ((Integer) it.next()).intValue();
                    iArr[i11] = iArr3;
                    iArr2[i11] = ((Integer) arrayList2.get(i11)).intValue();
                    i11++;
                }
                gradientDrawable.setColor(new ColorStateList(iArr, iArr2));
            } else if (this.typedArray.hasValue(C1944R.styleable.background_bl_solid_color)) {
                gradientDrawable.setColor(i3);
            }
        } else if (this.typedArray.hasValue(C1944R.styleable.background_bl_solid_color)) {
            gradientDrawable.setColor(i3);
        }
        if (this.typedArray.hasValue(C1944R.styleable.background_bl_stroke_width)) {
            if (Build.VERSION.SDK_INT >= 21) {
                ArrayList arrayList3 = new ArrayList();
                ArrayList arrayList4 = new ArrayList();
                if (this.typedArray.hasValue(C1944R.styleable.background_bl_pressed_stroke_color) && this.typedArray.hasValue(C1944R.styleable.background_bl_unPressed_stroke_color)) {
                    arrayList3.add(16842919);
                    arrayList3.add(-16842919);
                    arrayList4.add(Integer.valueOf(this.typedArray.getColor(C1944R.styleable.background_bl_pressed_stroke_color, 0)));
                    arrayList4.add(Integer.valueOf(this.typedArray.getColor(C1944R.styleable.background_bl_unPressed_stroke_color, 0)));
                }
                if (this.typedArray.hasValue(C1944R.styleable.background_bl_checkable_stroke_color) && this.typedArray.hasValue(C1944R.styleable.background_bl_unCheckable_stroke_color)) {
                    arrayList3.add(16842911);
                    arrayList3.add(-16842911);
                    arrayList4.add(Integer.valueOf(this.typedArray.getColor(C1944R.styleable.background_bl_checkable_stroke_color, 0)));
                    arrayList4.add(Integer.valueOf(this.typedArray.getColor(C1944R.styleable.background_bl_unCheckable_stroke_color, 0)));
                }
                if (this.typedArray.hasValue(C1944R.styleable.background_bl_checked_stroke_color) && this.typedArray.hasValue(C1944R.styleable.background_bl_unChecked_stroke_color)) {
                    arrayList3.add(16842912);
                    arrayList3.add(-16842912);
                    arrayList4.add(Integer.valueOf(this.typedArray.getColor(C1944R.styleable.background_bl_checked_stroke_color, 0)));
                    arrayList4.add(Integer.valueOf(this.typedArray.getColor(C1944R.styleable.background_bl_unChecked_stroke_color, 0)));
                }
                if (this.typedArray.hasValue(C1944R.styleable.background_bl_enabled_stroke_color) && this.typedArray.hasValue(C1944R.styleable.background_bl_unEnabled_stroke_color)) {
                    arrayList3.add(16842910);
                    arrayList3.add(-16842910);
                    arrayList4.add(Integer.valueOf(this.typedArray.getColor(C1944R.styleable.background_bl_enabled_stroke_color, 0)));
                    arrayList4.add(Integer.valueOf(this.typedArray.getColor(C1944R.styleable.background_bl_unEnabled_stroke_color, 0)));
                }
                if (this.typedArray.hasValue(C1944R.styleable.background_bl_selected_stroke_color) && this.typedArray.hasValue(C1944R.styleable.background_bl_unSelected_stroke_color)) {
                    arrayList3.add(16842913);
                    arrayList3.add(-16842913);
                    arrayList4.add(Integer.valueOf(this.typedArray.getColor(C1944R.styleable.background_bl_selected_stroke_color, 0)));
                    arrayList4.add(Integer.valueOf(this.typedArray.getColor(C1944R.styleable.background_bl_unSelected_stroke_color, 0)));
                }
                if (this.typedArray.hasValue(C1944R.styleable.background_bl_focused_stroke_color) && this.typedArray.hasValue(C1944R.styleable.background_bl_unFocused_stroke_color)) {
                    arrayList3.add(16842908);
                    arrayList3.add(-16842908);
                    arrayList4.add(Integer.valueOf(this.typedArray.getColor(C1944R.styleable.background_bl_focused_stroke_color, 0)));
                    arrayList4.add(Integer.valueOf(this.typedArray.getColor(C1944R.styleable.background_bl_unFocused_stroke_color, 0)));
                }
                if (arrayList3.size() > 0) {
                    int[][] iArr4 = new int[arrayList3.size()];
                    int[] iArr5 = new int[arrayList3.size()];
                    Iterator it2 = arrayList3.iterator();
                    int i12 = 0;
                    while (it2.hasNext()) {
                        int[] iArr6 = new int[1];
                        iArr6[0] = ((Integer) it2.next()).intValue();
                        iArr4[i12] = iArr6;
                        iArr5[i12] = ((Integer) arrayList4.get(i12)).intValue();
                        i12++;
                    }
                    gradientDrawable.setStroke((int) f4, new ColorStateList(iArr4, iArr5), f5, f3);
                } else if (this.typedArray.hasValue(C1944R.styleable.background_bl_stroke_color)) {
                    gradientDrawable.setStroke((int) f4, i4, f5, f3);
                }
            } else if (this.typedArray.hasValue(C1944R.styleable.background_bl_stroke_color)) {
                gradientDrawable.setStroke((int) f4, i4, f5, f3);
            }
        }
        if (this.typedArray.hasValue(C1944R.styleable.background_bl_gradient_centerX) && this.typedArray.hasValue(C1944R.styleable.background_bl_gradient_centerY)) {
            gradientDrawable.setGradientCenter(f6, f7);
        }
        if (this.typedArray.hasValue(C1944R.styleable.background_bl_gradient_startColor) && this.typedArray.hasValue(C1944R.styleable.background_bl_gradient_endColor) && Build.VERSION.SDK_INT >= 16) {
            gradientDrawable.setColors(this.typedArray.hasValue(C1944R.styleable.background_bl_gradient_centerColor) ? new int[]{i6, i5, i7} : new int[]{i6, i7});
        }
        if (i8 == 0 && this.typedArray.hasValue(C1944R.styleable.background_bl_gradient_angle) && Build.VERSION.SDK_INT >= 16) {
            int i13 = i9 % 360;
            if (i13 % 45 == 0) {
                GradientDrawable.Orientation orientation = GradientDrawable.Orientation.LEFT_RIGHT;
                if (i13 == 0) {
                    orientation = GradientDrawable.Orientation.LEFT_RIGHT;
                } else if (i13 == 45) {
                    orientation = GradientDrawable.Orientation.BL_TR;
                } else if (i13 == 90) {
                    orientation = GradientDrawable.Orientation.BOTTOM_TOP;
                } else if (i13 == 135) {
                    orientation = GradientDrawable.Orientation.BR_TL;
                } else if (i13 == 180) {
                    orientation = GradientDrawable.Orientation.RIGHT_LEFT;
                } else if (i13 == 225) {
                    orientation = GradientDrawable.Orientation.TR_BL;
                } else if (i13 == 270) {
                    orientation = GradientDrawable.Orientation.TOP_BOTTOM;
                } else if (i13 == 315) {
                    orientation = GradientDrawable.Orientation.TL_BR;
                }
                gradientDrawable.setOrientation(orientation);
            } else {
                throw new XmlPullParserException(this.typedArray.getPositionDescription() + "<gradient> tag requires 'angle' attribute to be a multiple of 45");
            }
        }
        if (this.typedArray.hasValue(C1944R.styleable.background_bl_padding_left) && this.typedArray.hasValue(C1944R.styleable.background_bl_padding_top) && this.typedArray.hasValue(C1944R.styleable.background_bl_padding_right) && this.typedArray.hasValue(C1944R.styleable.background_bl_padding_bottom)) {
            if (Build.VERSION.SDK_INT >= 29) {
                gradientDrawable.setPadding(rect.left, rect.top, rect.right, rect.bottom);
            } else {
                try {
                    Field declaredField = gradientDrawable.getClass().getDeclaredField("mPadding");
                    declaredField.setAccessible(true);
                    declaredField.set(gradientDrawable, rect);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return gradientDrawable;
    }

    private boolean hasSetRadius(float[] fArr) {
        for (float f : fArr) {
            if (f != 0.0f) {
                return true;
            }
        }
        return false;
    }
}
