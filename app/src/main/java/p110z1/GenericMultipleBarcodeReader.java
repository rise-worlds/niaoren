package p110z1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* renamed from: z1.jv */
/* loaded from: classes3.dex */
public final class GenericMultipleBarcodeReader implements MultipleBarcodeReader {

    /* renamed from: a */
    private static final int f22177a = 100;

    /* renamed from: b */
    private static final int f22178b = 4;

    /* renamed from: c */
    private final Reader f22179c;

    private GenericMultipleBarcodeReader(Reader ojVar) {
        this.f22179c = ojVar;
    }

    @Override // p110z1.MultipleBarcodeReader
    /* renamed from: b */
    public final C5422ol[] mo1931b(BinaryBitmap htVar) throws NotFoundException {
        return mo1928c(htVar);
    }

    @Override // p110z1.MultipleBarcodeReader
    /* renamed from: c */
    public final C5422ol[] mo1928c(BinaryBitmap htVar) throws NotFoundException {
        ArrayList arrayList = new ArrayList();
        m2247a(htVar, null, arrayList, 0, 0, 0);
        if (!arrayList.isEmpty()) {
            return (C5422ol[]) arrayList.toArray(new C5422ol[arrayList.size()]);
        }
        throw NotFoundException.m1647a();
    }

    /* renamed from: a */
    private void m2247a(BinaryBitmap htVar, Map<DecodeHintType, ?> map, List<C5422ol> list, int i, int i2, int i3) {
        boolean z;
        float f;
        float f2;
        List<C5422ol> list2;
        C5422ol olVar;
        BinaryBitmap htVar2 = htVar;
        int i4 = i2;
        for (int i5 = i3; i5 <= 4; i5++) {
            try {
                C5422ol a = this.f22179c.mo1636a(htVar2, map);
                Iterator<C5422ol> it = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next().f22707a.equals(a.f22707a)) {
                            z = true;
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
                if (!z) {
                    ResultPoint[] onVarArr = a.f22710d;
                    if (onVarArr == null) {
                        list2 = list;
                        olVar = a;
                    } else {
                        ResultPoint[] onVarArr2 = new ResultPoint[onVarArr.length];
                        for (int i6 = 0; i6 < onVarArr.length; i6++) {
                            ResultPoint onVar = onVarArr[i6];
                            if (onVar != null) {
                                onVarArr2[i6] = new ResultPoint(onVar.f22726c + i, onVar.f22727d + i4);
                            }
                        }
                        olVar = new C5422ol(a.f22707a, a.f22708b, a.f22709c, onVarArr2, a.f22711e, a.f22713g);
                        olVar.m1634a(a.f22712f);
                        list2 = list;
                    }
                    list2.add(olVar);
                }
                ResultPoint[] onVarArr3 = a.f22710d;
                if (onVarArr3 != null && onVarArr3.length != 0) {
                    int a2 = htVar2.m2561a();
                    int b = htVar2.m2558b();
                    float f3 = a2;
                    float f4 = b;
                    float f5 = 0.0f;
                    float f6 = 0.0f;
                    for (ResultPoint onVar2 : onVarArr3) {
                        if (onVar2 != null) {
                            float f7 = onVar2.f22726c;
                            float f8 = onVar2.f22727d;
                            if (f7 < f3) {
                                f3 = f7;
                            }
                            if (f8 < f4) {
                                f4 = f8;
                            }
                            if (f7 <= f6) {
                                f7 = f6;
                            }
                            if (f8 > f5) {
                                f5 = f8;
                                f6 = f7;
                            } else {
                                f6 = f7;
                            }
                        }
                    }
                    if (f3 > 100.0f) {
                        f2 = f5;
                        f = f6;
                        m2247a(htVar2.m2560a(0, 0, (int) f3, b), map, list, i, i4, i5 + 1);
                    } else {
                        f2 = f5;
                        f = f6;
                    }
                    if (f4 > 100.0f) {
                        m2247a(htVar2.m2560a(0, 0, a2, (int) f4), map, list, i, i4, i5 + 1);
                    }
                    if (f < a2 - 100) {
                        int i7 = (int) f;
                        m2247a(htVar2.m2560a(i7, 0, a2 - i7, b), map, list, i + i7, i4, i5 + 1);
                    }
                    if (f2 < b - 100) {
                        int i8 = (int) f2;
                        htVar2 = htVar2.m2560a(0, i8, a2, b - i8);
                        i4 += i8;
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } catch (ReaderException unused) {
                return;
            }
        }
    }

    /* renamed from: a */
    private static C5422ol m2246a(C5422ol olVar, int i, int i2) {
        ResultPoint[] onVarArr = olVar.f22710d;
        if (onVarArr == null) {
            return olVar;
        }
        ResultPoint[] onVarArr2 = new ResultPoint[onVarArr.length];
        for (int i3 = 0; i3 < onVarArr.length; i3++) {
            ResultPoint onVar = onVarArr[i3];
            if (onVar != null) {
                onVarArr2[i3] = new ResultPoint(onVar.f22726c + i, onVar.f22727d + i2);
            }
        }
        C5422ol olVar2 = new C5422ol(olVar.f22707a, olVar.f22708b, olVar.f22709c, onVarArr2, olVar.f22711e, olVar.f22713g);
        olVar2.m1634a(olVar.f22712f);
        return olVar2;
    }
}
