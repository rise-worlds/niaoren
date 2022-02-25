package com.lody.virtual.helper.utils;

import android.content.pm.Signature;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class SignaturesUtils {
    public static int compareSignatures(Signature[] signatureArr, Signature[] signatureArr2) {
        if (signatureArr == null) {
            return signatureArr2 == null ? 1 : -1;
        }
        if (signatureArr2 == null) {
            return -2;
        }
        if (signatureArr.length != signatureArr2.length) {
            return -3;
        }
        if (signatureArr.length == 1) {
            return signatureArr[0].equals(signatureArr2[0]) ? 0 : -3;
        }
        ArrayList arrayList = new ArrayList();
        for (Signature signature : signatureArr) {
            arrayList.add(signature);
        }
        ArrayList arrayList2 = new ArrayList();
        for (Signature signature2 : signatureArr2) {
            arrayList2.add(signature2);
        }
        return arrayList.equals(arrayList2) ? 0 : -3;
    }
}
