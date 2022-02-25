package p110z1;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

/* renamed from: z1.cx */
/* loaded from: classes3.dex */
final class C5184cx implements FileFilter {

    /* renamed from: a */
    final /* synthetic */ C5170cw f21104a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C5184cx(C5170cw cwVar) {
        this.f21104a = cwVar;
    }

    @Override // java.io.FileFilter
    public final boolean accept(File file) {
        return Pattern.matches("cpu[0-9]+", file.getName());
    }
}
