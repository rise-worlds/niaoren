package com.tendcloud.tenddata;

import java.io.File;
import java.util.Comparator;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ij */
/* loaded from: classes2.dex */
class C2977ij implements Comparator {
    final /* synthetic */ C2973ii this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2977ij(C2973ii iiVar) {
        this.this$0 = iiVar;
    }

    public int compare(File file, File file2) {
        return file.getName().compareTo(file2.getName());
    }
}
