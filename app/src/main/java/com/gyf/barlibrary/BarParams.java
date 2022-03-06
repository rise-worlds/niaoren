package com.gyf.barlibrary;

import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.view.View;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.gyf.barlibrary.c */
/* loaded from: classes.dex */
public class BarParams implements Cloneable {

    /* renamed from: H */
    OnKeyboardListener f9266H;

    /* renamed from: x */
    View f9290x;

    /* renamed from: y */
    View f9291y;
    @ColorInt

    /* renamed from: z */
    int f9292z;
    @ColorInt

    /* renamed from: a */
    int f9267a = 0;
    @ColorInt

    /* renamed from: b */
    int f9268b = -16777216;

    /* renamed from: c */
    int f9269c = -16777216;
    @FloatRange(from = 0.0d, m25696to = 1.0d)

    /* renamed from: d */
    float f9270d = 0.0f;
    @FloatRange(from = 0.0d, m25696to = 1.0d)

    /* renamed from: e */
    float f9271e = 0.0f;

    /* renamed from: f */
    public boolean f9272f = false;

    /* renamed from: g */
    boolean f9273g = false;

    /* renamed from: h */
    BarHide f9274h = BarHide.FLAG_SHOW_BAR;

    /* renamed from: i */
    boolean f9275i = false;

    /* renamed from: j */
    boolean f9276j = false;

    /* renamed from: k */
    boolean f9277k = false;

    /* renamed from: l */
    boolean f9278l = false;
    @FloatRange(from = 0.0d, m25696to = 1.0d)

    /* renamed from: m */
    float f9279m = 0.0f;
    @FloatRange(from = 0.0d, m25696to = 1.0d)

    /* renamed from: n */
    float f9280n = 0.0f;

    /* renamed from: o */
    boolean f9281o = true;
    @ColorInt

    /* renamed from: p */
    int f9282p = -16777216;
    @ColorInt

    /* renamed from: q */
    int f9283q = -16777216;

    /* renamed from: r */
    Map<View, Map<Integer, Integer>> f9284r = new HashMap();
    @FloatRange(from = 0.0d, m25696to = 1.0d)

    /* renamed from: s */
    float f9285s = 0.0f;
    @ColorInt

    /* renamed from: t */
    int f9286t = 0;
    @ColorInt

    /* renamed from: u */
    int f9287u = -16777216;
    @FloatRange(from = 0.0d, m25696to = 1.0d)

    /* renamed from: v */
    float f9288v = 0.0f;

    /* renamed from: w */
    public boolean f9289w = false;

    /* renamed from: A */
    boolean f9259A = false;

    /* renamed from: B */
    public boolean f9260B = false;

    /* renamed from: C */
    int f9261C = 18;

    /* renamed from: D */
    boolean f9262D = true;

    /* renamed from: E */
    boolean f9263E = true;

    /* renamed from: F */
    boolean f9264F = true;
    @Deprecated

    /* renamed from: G */
    boolean f9265G = false;

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public BarParams clone() {
        try {
            return (BarParams) super.clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }
}
