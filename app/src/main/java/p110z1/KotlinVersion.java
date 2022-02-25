package p110z1;

import org.apache.commons.p105io.FilenameUtils;
import org.apache.http.cookie.ClientCookie;

@bwy(m8750a = "1.1")
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00172\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0017B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\u0011\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0000H\u0096\u0002J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u000e\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0003H\u0016J\u0016\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003J\u001e\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0003J\b\u0010\u0014\u001a\u00020\u0015H\u0016J \u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0003H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u000e\u0010\f\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, m8860e = {"Lkotlin/KotlinVersion;", "", "major", "", "minor", "(II)V", "patch", "(III)V", "getMajor", "()I", "getMinor", "getPatch", ClientCookie.VERSION_ATTR, "compareTo", "other", "equals", "", "", "hashCode", "isAtLeast", "toString", "", "versionOf", "Companion", "kotlin-stdlib"})
/* renamed from: z1.bvx */
/* loaded from: classes3.dex */
public final class KotlinVersion implements Comparable<KotlinVersion> {

    /* renamed from: a */
    public static final int f20354a = 255;

    /* renamed from: d */
    private final int f20357d;

    /* renamed from: e */
    private final int f20358e;

    /* renamed from: f */
    private final int f20359f;

    /* renamed from: g */
    private final int f20360g;

    /* renamed from: c */
    public static final C4800a f20356c = new C4800a(null);
    @JvmPlatformAnnotations
    @NotNull

    /* renamed from: b */
    public static final KotlinVersion f20355b = new KotlinVersion(1, 3, 50);

    public KotlinVersion(int i, int i2, int i3) {
        this.f20358e = i;
        this.f20359f = i2;
        this.f20360g = i3;
        this.f20357d = m8886b(this.f20358e, this.f20359f, this.f20360g);
    }

    /* renamed from: a */
    public final int m8891a() {
        return this.f20358e;
    }

    /* renamed from: b */
    public final int m8887b() {
        return this.f20359f;
    }

    /* renamed from: c */
    public final int m8885c() {
        return this.f20360g;
    }

    public KotlinVersion(int i, int i2) {
        this(i, i2, 0);
    }

    /* renamed from: b */
    private final int m8886b(int i, int i2, int i3) {
        if (i >= 0 && 255 >= i && i2 >= 0 && 255 >= i2 && i3 >= 0 && 255 >= i3) {
            return (i << 16) + (i2 << 8) + i3;
        }
        throw new IllegalArgumentException(("Version components are out of range: " + i + FilenameUtils.EXTENSION_SEPARATOR + i2 + FilenameUtils.EXTENSION_SEPARATOR + i3).toString());
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f20358e);
        sb.append(FilenameUtils.EXTENSION_SEPARATOR);
        sb.append(this.f20359f);
        sb.append(FilenameUtils.EXTENSION_SEPARATOR);
        sb.append(this.f20360g);
        return sb.toString();
    }

    public boolean equals(@dbs Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof KotlinVersion)) {
            obj = null;
        }
        KotlinVersion bvxVar = (KotlinVersion) obj;
        return bvxVar != null && this.f20357d == bvxVar.f20357d;
    }

    public int hashCode() {
        return this.f20357d;
    }

    /* renamed from: a */
    public int compareTo(@NotNull KotlinVersion bvxVar) {
        cji.m5162f(bvxVar, "other");
        return this.f20357d - bvxVar.f20357d;
    }

    /* renamed from: a */
    public final boolean m8890a(int i, int i2) {
        int i3 = this.f20358e;
        return i3 > i || (i3 == i && this.f20359f >= i2);
    }

    /* renamed from: a */
    public final boolean m8889a(int i, int i2, int i3) {
        int i4;
        int i5 = this.f20358e;
        return i5 > i || (i5 == i && ((i4 = this.f20359f) > i2 || (i4 == i2 && this.f20360g >= i3)));
    }

    /* compiled from: KotlinVersion.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, m8860e = {"Lkotlin/KotlinVersion$Companion;", "", "()V", "CURRENT", "Lkotlin/KotlinVersion;", "MAX_COMPONENT_VALUE", "", "kotlin-stdlib"})
    /* renamed from: z1.bvx$a */
    /* loaded from: classes3.dex */
    public static final class C4800a {
        private C4800a() {
        }

        public /* synthetic */ C4800a(DefaultConstructorMarker civVar) {
            this();
        }
    }
}
