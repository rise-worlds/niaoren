package p110z1;

/* renamed from: z1.mk */
/* loaded from: classes3.dex */
final class BoundingBox {

    /* renamed from: a */
    final BitMatrix f22429a;

    /* renamed from: b */
    final ResultPoint f22430b;

    /* renamed from: c */
    final ResultPoint f22431c;

    /* renamed from: d */
    final ResultPoint f22432d;

    /* renamed from: e */
    final ResultPoint f22433e;

    /* renamed from: f */
    final int f22434f;

    /* renamed from: g */
    final int f22435g;

    /* renamed from: h */
    final int f22436h;

    /* renamed from: i */
    final int f22437i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BoundingBox(BitMatrix hyVar, ResultPoint onVar, ResultPoint onVar2, ResultPoint onVar3, ResultPoint onVar4) throws NotFoundException {
        boolean z = false;
        boolean z2 = onVar == null || onVar2 == null;
        z = (onVar3 == null || onVar4 == null) ? true : z;
        if (!z2 || !z) {
            if (z2) {
                onVar = new ResultPoint(0.0f, onVar3.f22727d);
                onVar2 = new ResultPoint(0.0f, onVar4.f22727d);
            } else if (z) {
                onVar3 = new ResultPoint(hyVar.f21920a - 1, onVar.f22727d);
                onVar4 = new ResultPoint(hyVar.f21920a - 1, onVar2.f22727d);
            }
            this.f22429a = hyVar;
            this.f22430b = onVar;
            this.f22431c = onVar2;
            this.f22432d = onVar3;
            this.f22433e = onVar4;
            this.f22434f = (int) Math.min(onVar.f22726c, onVar2.f22726c);
            this.f22435g = (int) Math.max(onVar3.f22726c, onVar4.f22726c);
            this.f22436h = (int) Math.min(onVar.f22727d, onVar3.f22727d);
            this.f22437i = (int) Math.max(onVar2.f22727d, onVar4.f22727d);
            return;
        }
        throw NotFoundException.m1647a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BoundingBox(BoundingBox mkVar) {
        this.f22429a = mkVar.f22429a;
        this.f22430b = mkVar.f22430b;
        this.f22431c = mkVar.f22431c;
        this.f22432d = mkVar.f22432d;
        this.f22433e = mkVar.f22433e;
        this.f22434f = mkVar.f22434f;
        this.f22435g = mkVar.f22435g;
        this.f22436h = mkVar.f22436h;
        this.f22437i = mkVar.f22437i;
    }

    /* renamed from: a */
    private static BoundingBox m2019a(BoundingBox mkVar, BoundingBox mkVar2) throws NotFoundException {
        return mkVar == null ? mkVar2 : mkVar2 == null ? mkVar : new BoundingBox(mkVar.f22429a, mkVar.f22430b, mkVar.f22431c, mkVar2.f22432d, mkVar2.f22433e);
    }

    /* renamed from: a */
    private BoundingBox m2020a(int i, int i2, boolean z) throws NotFoundException {
        ResultPoint onVar;
        ResultPoint onVar2;
        ResultPoint onVar3;
        ResultPoint onVar4;
        ResultPoint onVar5 = this.f22430b;
        ResultPoint onVar6 = this.f22431c;
        ResultPoint onVar7 = this.f22432d;
        ResultPoint onVar8 = this.f22433e;
        if (i > 0) {
            ResultPoint onVar9 = z ? onVar5 : onVar7;
            int i3 = ((int) onVar9.f22727d) - i;
            if (i3 < 0) {
                i3 = 0;
            }
            ResultPoint onVar10 = new ResultPoint(onVar9.f22726c, i3);
            if (z) {
                onVar2 = onVar10;
                onVar = onVar7;
            } else {
                onVar = onVar10;
                onVar2 = onVar5;
            }
        } else {
            onVar2 = onVar5;
            onVar = onVar7;
        }
        if (i2 > 0) {
            ResultPoint onVar11 = z ? this.f22431c : this.f22433e;
            int i4 = ((int) onVar11.f22727d) + i2;
            if (i4 >= this.f22429a.f21921b) {
                i4 = this.f22429a.f21921b - 1;
            }
            ResultPoint onVar12 = new ResultPoint(onVar11.f22726c, i4);
            if (z) {
                onVar4 = onVar12;
                onVar3 = onVar8;
            } else {
                onVar3 = onVar12;
                onVar4 = onVar6;
            }
        } else {
            onVar4 = onVar6;
            onVar3 = onVar8;
        }
        return new BoundingBox(this.f22429a, onVar2, onVar4, onVar, onVar3);
    }

    /* renamed from: a */
    private int m2021a() {
        return this.f22434f;
    }

    /* renamed from: b */
    private int m2018b() {
        return this.f22435g;
    }

    /* renamed from: c */
    private int m2017c() {
        return this.f22436h;
    }

    /* renamed from: d */
    private int m2016d() {
        return this.f22437i;
    }

    /* renamed from: e */
    private ResultPoint m2015e() {
        return this.f22430b;
    }

    /* renamed from: f */
    private ResultPoint m2014f() {
        return this.f22432d;
    }

    /* renamed from: g */
    private ResultPoint m2013g() {
        return this.f22431c;
    }

    /* renamed from: h */
    private ResultPoint m2012h() {
        return this.f22433e;
    }
}
