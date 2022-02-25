package com.tendcloud.tenddata;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.dk */
/* loaded from: classes2.dex */
final class C2805dk extends ObjectInputStream {
    final /* synthetic */ ClassLoader val$loader;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2805dk(InputStream inputStream, ClassLoader classLoader) {
        super(inputStream);
        this.val$loader = classLoader;
    }

    @Override // java.io.ObjectInputStream
    public Class resolveClass(ObjectStreamClass objectStreamClass) {
        Class<?> cls = Class.forName(objectStreamClass.getName(), false, this.val$loader);
        return cls == null ? super.resolveClass(objectStreamClass) : cls;
    }
}
