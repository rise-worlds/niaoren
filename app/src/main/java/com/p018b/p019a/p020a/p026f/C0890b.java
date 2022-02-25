package com.p018b.p019a.p020a.p026f;

import java.io.File;
import java.io.IOException;

/* compiled from: FileSystem.java */
/* renamed from: com.b.a.a.f.b */
/* loaded from: classes.dex */
final class C0890b implements FileSystem {
    @Override // com.p018b.p019a.p020a.p026f.FileSystem
    /* renamed from: a */
    public final void mo24598a(File file) {
        if (!file.delete() && file.exists()) {
            throw new IOException("failed to delete " + file);
        }
    }
}
