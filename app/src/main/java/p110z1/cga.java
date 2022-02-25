package p110z1;

import java.io.File;

/* compiled from: FileTreeWalk.kt */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u001a\n\u0010\u0005\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0006\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0007"}, m8860e = {"walk", "Lkotlin/io/FileTreeWalk;", "Ljava/io/File;", "direction", "Lkotlin/io/FileWalkDirection;", "walkBottomUp", "walkTopDown", "kotlin-stdlib"}, m8859f = "kotlin/io/FilesKt", m8857h = 1)
/* renamed from: z1.cga */
/* loaded from: classes3.dex */
class cga extends FileReadWrite {
    /* renamed from: a */
    public static /* synthetic */ FileTreeWalk m5332a(File file, cfw cfwVar, int i, Object obj) {
        if ((i & 1) != 0) {
            cfwVar = cfw.TOP_DOWN;
        }
        return cfx.m5333a(file, cfwVar);
    }

    @NotNull
    /* renamed from: a */
    public static final FileTreeWalk m5333a(@NotNull File file, @NotNull cfw cfwVar) {
        cji.m5162f(file, "$this$walk");
        cji.m5162f(cfwVar, "direction");
        return new FileTreeWalk(file, cfwVar);
    }

    @NotNull
    /* renamed from: f */
    public static final FileTreeWalk m5331f(@NotNull File file) {
        cji.m5162f(file, "$this$walkTopDown");
        return cfx.m5333a(file, cfw.TOP_DOWN);
    }

    @NotNull
    /* renamed from: g */
    public static final FileTreeWalk m5330g(@NotNull File file) {
        cji.m5162f(file, "$this$walkBottomUp");
        return cfx.m5333a(file, cfw.BOTTOM_UP);
    }
}
