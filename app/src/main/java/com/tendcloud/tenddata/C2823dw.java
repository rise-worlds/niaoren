package com.tendcloud.tenddata;

import java.io.File;
import java.io.FileFilter;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.dw */
/* loaded from: classes2.dex */
final class C2823dw implements FileFilter {
    @Override // java.io.FileFilter
    public boolean accept(File file) {
        if (file != null) {
            try {
                String name = file.getName();
                if (name != null && name.startsWith("cpu")) {
                    for (int i = 3; i < name.length(); i++) {
                        if (name.charAt(i) < '0' || name.charAt(i) > '9') {
                            return false;
                        }
                    }
                    return true;
                }
            } catch (Throwable unused) {
            }
        }
        return false;
    }
}
