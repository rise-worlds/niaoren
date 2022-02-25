package p110z1;

import com.stripe.android.view.ShippingInfoWidget;
import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Iterator;
import org.apache.commons.p105io.FilenameUtils;

@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003\u001a\u001b\u001cB\u0019\b\u0010\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0089\u0001\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b\u00128\u0010\f\u001a4\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u000b\u0018\u00010\r\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014¢\u0006\u0002\u0010\u0015J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00020\u0017H\u0096\u0002J\u000e\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0014J\u001a\u0010\u0007\u001a\u00020\u00002\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t0\bJ \u0010\f\u001a\u00020\u00002\u0018\u0010\u0019\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000b0\rJ\u001a\u0010\n\u001a\u00020\u00002\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b0\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R@\u0010\f\u001a4\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u000b\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, m8860e = {"Lkotlin/io/FileTreeWalk;", "Lkotlin/sequences/Sequence;", "Ljava/io/File;", "start", "direction", "Lkotlin/io/FileWalkDirection;", "(Ljava/io/File;Lkotlin/io/FileWalkDirection;)V", "onEnter", "Lkotlin/Function1;", "", "onLeave", "", "onFail", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "f", "Ljava/io/IOException;", "e", "maxDepth", "", "(Ljava/io/File;Lkotlin/io/FileWalkDirection;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;I)V", "iterator", "", "depth", "function", "DirectoryState", "FileTreeWalkIterator", "WalkState", "kotlin-stdlib"})
/* renamed from: z1.cfu */
/* loaded from: classes3.dex */
public final class FileTreeWalk implements Sequence<File> {

    /* renamed from: a */
    private final File f20660a;

    /* renamed from: b */
    private final cfw f20661b;

    /* renamed from: c */
    private final chd<File, Boolean> f20662c;

    /* renamed from: d */
    private final chd<File, Unit> f20663d;

    /* renamed from: e */
    private final cho<File, IOException, Unit> f20664e;

    /* renamed from: f */
    private final int f20665f;

    /* JADX WARN: Multi-variable type inference failed */
    private FileTreeWalk(File file, cfw cfwVar, chd<? super File, Boolean> chdVar, chd<? super File, Unit> chdVar2, cho<? super File, ? super IOException, Unit> choVar, int i) {
        this.f20660a = file;
        this.f20661b = cfwVar;
        this.f20662c = chdVar;
        this.f20663d = chdVar2;
        this.f20664e = choVar;
        this.f20665f = i;
    }

    /* synthetic */ FileTreeWalk(File file, cfw cfwVar, chd chdVar, chd chdVar2, cho choVar, int i, int i2, DefaultConstructorMarker civVar) {
        this(file, (i2 & 2) != 0 ? cfw.TOP_DOWN : cfwVar, chdVar, chdVar2, choVar, (i2 & 32) != 0 ? Integer.MAX_VALUE : i);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FileTreeWalk(@NotNull File file, @NotNull cfw cfwVar) {
        this(file, cfwVar, null, null, null, 0, 32, null);
        cji.m5162f(file, "start");
        cji.m5162f(cfwVar, "direction");
    }

    public /* synthetic */ FileTreeWalk(File file, cfw cfwVar, int i, DefaultConstructorMarker civVar) {
        this(file, (i & 2) != 0 ? cfw.TOP_DOWN : cfwVar);
    }

    @Override // p110z1.Sequence
    @NotNull
    /* renamed from: a */
    public Iterator<File> mo3707a() {
        return new C4949b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FileTreeWalk.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\"\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\u0007\u001a\u0004\u0018\u00010\u0003H&R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, m8860e = {"Lkotlin/io/FileTreeWalk$WalkState;", "", "root", "Ljava/io/File;", "(Ljava/io/File;)V", "getRoot", "()Ljava/io/File;", "step", "kotlin-stdlib"})
    /* renamed from: z1.cfu$c */
    /* loaded from: classes3.dex */
    public static abstract class AbstractC4953c {
        @NotNull

        /* renamed from: a */
        private final File f20679a;

        @dbs
        /* renamed from: a */
        public abstract File mo5370a();

        public AbstractC4953c(@NotNull File file) {
            cji.m5162f(file, "root");
            this.f20679a = file;
        }

        @NotNull
        /* renamed from: b */
        public final File m5369b() {
            return this.f20679a;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FileTreeWalk.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\"\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, m8860e = {"Lkotlin/io/FileTreeWalk$DirectoryState;", "Lkotlin/io/FileTreeWalk$WalkState;", "rootDir", "Ljava/io/File;", "(Ljava/io/File;)V", "kotlin-stdlib"})
    /* renamed from: z1.cfu$a */
    /* loaded from: classes3.dex */
    public static abstract class AbstractC4948a extends AbstractC4953c {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AbstractC4948a(@NotNull File file) {
            super(file);
            cji.m5162f(file, "rootDir");
            if (byj.f20419a) {
                boolean isDirectory = file.isDirectory();
                if (byj.f20419a && !isDirectory) {
                    throw new AssertionError("rootDir must be verified to be directory beforehand.");
                }
            }
        }
    }

    /* compiled from: FileTreeWalk.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003\r\u000e\u000fB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0007\u001a\u00020\bH\u0014J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0002J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0002H\u0082\u0010R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m8860e = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;", "Lkotlin/collections/AbstractIterator;", "Ljava/io/File;", "(Lkotlin/io/FileTreeWalk;)V", ShippingInfoWidget.f12562e, "Ljava/util/ArrayDeque;", "Lkotlin/io/FileTreeWalk$WalkState;", "computeNext", "", "directoryState", "Lkotlin/io/FileTreeWalk$DirectoryState;", "root", "gotoNext", "BottomUpDirectoryState", "SingleFileState", "TopDownDirectoryState", "kotlin-stdlib"})
    /* renamed from: z1.cfu$b */
    /* loaded from: classes3.dex */
    private final class C4949b extends AbstractIterator<File> {

        /* renamed from: b */
        private final ArrayDeque<AbstractC4953c> f20667b = new ArrayDeque<>();

        public C4949b() {
            if (FileTreeWalk.this.f20660a.isDirectory()) {
                this.f20667b.push(m5372a(FileTreeWalk.this.f20660a));
            } else if (FileTreeWalk.this.f20660a.isFile()) {
                this.f20667b.push(new C4951b(this, FileTreeWalk.this.f20660a));
            } else {
                m8324b();
            }
        }

        @Override // p110z1.AbstractIterator
        /* renamed from: a */
        protected void mo4549a() {
            File c = m5371c();
            if (c != null) {
                m8325a((C4949b) c);
            } else {
                m8324b();
            }
        }

        /* renamed from: a */
        private final AbstractC4948a m5372a(File file) {
            switch (cfv.f20680a[FileTreeWalk.this.f20661b.ordinal()]) {
                case 1:
                    return new C4952c(this, file);
                case 2:
                    return new C4950a(this, file);
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }

        /* renamed from: c */
        private final File m5371c() {
            File a;
            while (true) {
                AbstractC4953c peek = this.f20667b.peek();
                if (peek == null) {
                    return null;
                }
                a = peek.mo5370a();
                if (a == null) {
                    this.f20667b.pop();
                } else if (cji.m5184a(a, peek.m5369b()) || !a.isDirectory() || this.f20667b.size() >= FileTreeWalk.this.f20665f) {
                    break;
                } else {
                    this.f20667b.push(m5372a(a));
                }
            }
            return a;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* compiled from: FileTreeWalk.kt */
        @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\r\u001a\u0004\u0018\u00010\u0003H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\f\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m8860e = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator$BottomUpDirectoryState;", "Lkotlin/io/FileTreeWalk$DirectoryState;", "rootDir", "Ljava/io/File;", "(Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;Ljava/io/File;)V", "failed", "", "fileIndex", "", "fileList", "", "[Ljava/io/File;", "rootVisited", "step", "kotlin-stdlib"})
        /* renamed from: z1.cfu$b$a */
        /* loaded from: classes3.dex */
        public final class C4950a extends AbstractC4948a {

            /* renamed from: a */
            final /* synthetic */ C4949b f20668a;

            /* renamed from: b */
            private boolean f20669b;

            /* renamed from: c */
            private File[] f20670c;

            /* renamed from: d */
            private int f20671d;

            /* renamed from: e */
            private boolean f20672e;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C4950a(C4949b bVar, @NotNull File file) {
                super(file);
                cji.m5162f(file, "rootDir");
                this.f20668a = bVar;
            }

            @Override // p110z1.FileTreeWalk.AbstractC4953c
            @dbs
            /* renamed from: a */
            public File mo5370a() {
                if (!this.f20672e && this.f20670c == null) {
                    chd chdVar = FileTreeWalk.this.f20662c;
                    if (chdVar != null && !((Boolean) chdVar.invoke(m5369b())).booleanValue()) {
                        return null;
                    }
                    this.f20670c = m5369b().listFiles();
                    if (this.f20670c == null) {
                        cho choVar = FileTreeWalk.this.f20664e;
                        if (choVar != null) {
                            Unit bydVar = (Unit) choVar.invoke(m5369b(), new cfl(m5369b(), null, "Cannot list files in a directory", 2, null));
                        }
                        this.f20672e = true;
                    }
                }
                File[] fileArr = this.f20670c;
                if (fileArr != null) {
                    int i = this.f20671d;
                    if (fileArr == null) {
                        cji.m5196a();
                    }
                    if (i < fileArr.length) {
                        File[] fileArr2 = this.f20670c;
                        if (fileArr2 == null) {
                            cji.m5196a();
                        }
                        int i2 = this.f20671d;
                        this.f20671d = i2 + 1;
                        return fileArr2[i2];
                    }
                }
                if (!this.f20669b) {
                    this.f20669b = true;
                    return m5369b();
                }
                chd chdVar2 = FileTreeWalk.this.f20663d;
                if (chdVar2 != null) {
                    Unit bydVar2 = (Unit) chdVar2.invoke(m5369b());
                }
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* compiled from: FileTreeWalk.kt */
        @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\f\u001a\u0004\u0018\u00010\u0003H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, m8860e = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator$TopDownDirectoryState;", "Lkotlin/io/FileTreeWalk$DirectoryState;", "rootDir", "Ljava/io/File;", "(Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;Ljava/io/File;)V", "fileIndex", "", "fileList", "", "[Ljava/io/File;", "rootVisited", "", "step", "kotlin-stdlib"})
        /* renamed from: z1.cfu$b$c */
        /* loaded from: classes3.dex */
        public final class C4952c extends AbstractC4948a {

            /* renamed from: a */
            final /* synthetic */ C4949b f20675a;

            /* renamed from: b */
            private boolean f20676b;

            /* renamed from: c */
            private File[] f20677c;

            /* renamed from: d */
            private int f20678d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C4952c(C4949b bVar, @NotNull File file) {
                super(file);
                cji.m5162f(file, "rootDir");
                this.f20675a = bVar;
            }

            /* JADX WARN: Code restructure failed: missing block: B:34:0x008b, code lost:
                if (r0.length == 0) goto L_0x008d;
             */
            @Override // p110z1.FileTreeWalk.AbstractC4953c
            @p110z1.dbs
            /* renamed from: a */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public java.io.File mo5370a() {
                /*
                    r10 = this;
                    boolean r0 = r10.f20676b
                    r1 = 0
                    if (r0 != 0) goto L_0x0028
                    z1.cfu$b r0 = r10.f20675a
                    z1.cfu r0 = p110z1.FileTreeWalk.this
                    z1.chd r0 = p110z1.FileTreeWalk.m5381a(r0)
                    if (r0 == 0) goto L_0x0020
                    java.io.File r2 = r10.m5369b()
                    java.lang.Object r0 = r0.invoke(r2)
                    java.lang.Boolean r0 = (java.lang.Boolean) r0
                    boolean r0 = r0.booleanValue()
                    if (r0 != 0) goto L_0x0020
                    return r1
                L_0x0020:
                    r0 = 1
                    r10.f20676b = r0
                    java.io.File r0 = r10.m5369b()
                    return r0
                L_0x0028:
                    java.io.File[] r0 = r10.f20677c
                    if (r0 == 0) goto L_0x004c
                    int r2 = r10.f20678d
                    if (r0 != 0) goto L_0x0033
                    p110z1.cji.m5196a()
                L_0x0033:
                    int r0 = r0.length
                    if (r2 >= r0) goto L_0x0037
                    goto L_0x004c
                L_0x0037:
                    z1.cfu$b r0 = r10.f20675a
                    z1.cfu r0 = p110z1.FileTreeWalk.this
                    z1.chd r0 = p110z1.FileTreeWalk.m5376c(r0)
                    if (r0 == 0) goto L_0x004b
                    java.io.File r2 = r10.m5369b()
                    java.lang.Object r0 = r0.invoke(r2)
                    z1.byd r0 = (p110z1.Unit) r0
                L_0x004b:
                    return r1
                L_0x004c:
                    java.io.File[] r0 = r10.f20677c
                    if (r0 != 0) goto L_0x00a2
                    java.io.File r0 = r10.m5369b()
                    java.io.File[] r0 = r0.listFiles()
                    r10.f20677c = r0
                    java.io.File[] r0 = r10.f20677c
                    if (r0 != 0) goto L_0x0081
                    z1.cfu$b r0 = r10.f20675a
                    z1.cfu r0 = p110z1.FileTreeWalk.this
                    z1.cho r0 = p110z1.FileTreeWalk.m5378b(r0)
                    if (r0 == 0) goto L_0x0081
                    java.io.File r2 = r10.m5369b()
                    z1.cfl r9 = new z1.cfl
                    java.io.File r4 = r10.m5369b()
                    r5 = 0
                    java.lang.String r6 = "Cannot list files in a directory"
                    r7 = 2
                    r8 = 0
                    r3 = r9
                    r3.<init>(r4, r5, r6, r7, r8)
                    java.lang.Object r0 = r0.invoke(r2, r9)
                    z1.byd r0 = (p110z1.Unit) r0
                L_0x0081:
                    java.io.File[] r0 = r10.f20677c
                    if (r0 == 0) goto L_0x008d
                    if (r0 != 0) goto L_0x008a
                    p110z1.cji.m5196a()
                L_0x008a:
                    int r0 = r0.length
                    if (r0 != 0) goto L_0x00a2
                L_0x008d:
                    z1.cfu$b r0 = r10.f20675a
                    z1.cfu r0 = p110z1.FileTreeWalk.this
                    z1.chd r0 = p110z1.FileTreeWalk.m5376c(r0)
                    if (r0 == 0) goto L_0x00a1
                    java.io.File r2 = r10.m5369b()
                    java.lang.Object r0 = r0.invoke(r2)
                    z1.byd r0 = (p110z1.Unit) r0
                L_0x00a1:
                    return r1
                L_0x00a2:
                    java.io.File[] r0 = r10.f20677c
                    if (r0 != 0) goto L_0x00a9
                    p110z1.cji.m5196a()
                L_0x00a9:
                    int r1 = r10.f20678d
                    int r2 = r1 + 1
                    r10.f20678d = r2
                    r0 = r0[r1]
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: p110z1.FileTreeWalk.C4949b.C4952c.mo5370a():java.io.File");
            }
        }

        /* compiled from: FileTreeWalk.kt */
        @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, m8860e = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator$SingleFileState;", "Lkotlin/io/FileTreeWalk$WalkState;", "rootFile", "Ljava/io/File;", "(Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;Ljava/io/File;)V", "visited", "", "step", "kotlin-stdlib"})
        /* renamed from: z1.cfu$b$b */
        /* loaded from: classes3.dex */
        private final class C4951b extends AbstractC4953c {

            /* renamed from: a */
            final /* synthetic */ C4949b f20673a;

            /* renamed from: b */
            private boolean f20674b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C4951b(C4949b bVar, @NotNull File file) {
                super(file);
                cji.m5162f(file, "rootFile");
                this.f20673a = bVar;
                if (byj.f20419a) {
                    boolean isFile = file.isFile();
                    if (byj.f20419a && !isFile) {
                        throw new AssertionError("rootFile must be verified to be file beforehand.");
                    }
                }
            }

            @Override // p110z1.FileTreeWalk.AbstractC4953c
            @dbs
            /* renamed from: a */
            public File mo5370a() {
                if (this.f20674b) {
                    return null;
                }
                this.f20674b = true;
                return m5369b();
            }
        }
    }

    @NotNull
    /* renamed from: a */
    public final FileTreeWalk m5380a(@NotNull chd<? super File, Boolean> chdVar) {
        cji.m5162f(chdVar, "function");
        return new FileTreeWalk(this.f20660a, this.f20661b, chdVar, this.f20663d, this.f20664e, this.f20665f);
    }

    @NotNull
    /* renamed from: b */
    public final FileTreeWalk m5377b(@NotNull chd<? super File, Unit> chdVar) {
        cji.m5162f(chdVar, "function");
        return new FileTreeWalk(this.f20660a, this.f20661b, this.f20662c, chdVar, this.f20664e, this.f20665f);
    }

    @NotNull
    /* renamed from: a */
    public final FileTreeWalk m5379a(@NotNull cho<? super File, ? super IOException, Unit> choVar) {
        cji.m5162f(choVar, "function");
        return new FileTreeWalk(this.f20660a, this.f20661b, this.f20662c, this.f20663d, choVar, this.f20665f);
    }

    @NotNull
    /* renamed from: a */
    public final FileTreeWalk m5382a(int i) {
        if (i > 0) {
            return new FileTreeWalk(this.f20660a, this.f20661b, this.f20662c, this.f20663d, this.f20664e, i);
        }
        throw new IllegalArgumentException("depth must be positive, but was " + i + FilenameUtils.EXTENSION_SEPARATOR);
    }
}
