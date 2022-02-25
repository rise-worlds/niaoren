package com.tencent.smtt.utils;

import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UnknownFormatConversionException;

/* renamed from: com.tencent.smtt.utils.e */
/* loaded from: classes2.dex */
public class Elf implements Closeable {

    /* renamed from: a */
    static final char[] f13301a = {127, 'E', 'L', 'F', 0};

    /* renamed from: b */
    final char[] f13302b = new char[16];

    /* renamed from: c */
    boolean f13303c;

    /* renamed from: d */
    AbstractC2651j[] f13304d;

    /* renamed from: e */
    AbstractC2653l[] f13305e;

    /* renamed from: f */
    byte[] f13306f;

    /* renamed from: g */
    private final DataReader f13307g;

    /* renamed from: h */
    private final AbstractC2642a f13308h;

    /* renamed from: i */
    private final AbstractC2652k[] f13309i;

    /* renamed from: j */
    private byte[] f13310j;

    /* compiled from: Elf.java */
    /* renamed from: com.tencent.smtt.utils.e$a */
    /* loaded from: classes2.dex */
    public static abstract class AbstractC2642a {

        /* renamed from: a */
        short f13311a;

        /* renamed from: b */
        short f13312b;

        /* renamed from: c */
        int f13313c;

        /* renamed from: d */
        int f13314d;

        /* renamed from: e */
        short f13315e;

        /* renamed from: f */
        short f13316f;

        /* renamed from: g */
        short f13317g;

        /* renamed from: h */
        short f13318h;

        /* renamed from: i */
        short f13319i;

        /* renamed from: j */
        short f13320j;

        /* renamed from: a */
        abstract long mo16470a();

        /* renamed from: b */
        abstract long mo16469b();
    }

    /* compiled from: Elf.java */
    /* renamed from: com.tencent.smtt.utils.e$k */
    /* loaded from: classes2.dex */
    public static abstract class AbstractC2652k {

        /* renamed from: g */
        int f13357g;

        /* renamed from: h */
        int f13358h;

        /* renamed from: i */
        int f13359i;

        /* renamed from: j */
        int f13360j;

        /* renamed from: a */
        public abstract int mo16468a();

        /* renamed from: b */
        public abstract long mo16467b();
    }

    /* renamed from: a */
    final boolean m16481a() {
        return this.f13302b[0] == f13301a[0];
    }

    /* renamed from: b */
    final char m16477b() {
        return this.f13302b[4];
    }

    /* renamed from: c */
    final char m16475c() {
        return this.f13302b[5];
    }

    /* renamed from: d */
    public final boolean m16474d() {
        return m16477b() == 2;
    }

    /* renamed from: e */
    public final boolean m16473e() {
        return m16475c() == 1;
    }

    /* compiled from: Elf.java */
    /* renamed from: com.tencent.smtt.utils.e$b */
    /* loaded from: classes2.dex */
    static class C2643b extends AbstractC2642a {

        /* renamed from: k */
        int f13321k;

        /* renamed from: l */
        int f13322l;

        /* renamed from: m */
        int f13323m;

        C2643b() {
        }

        @Override // com.tencent.smtt.utils.Elf.AbstractC2642a
        /* renamed from: a */
        long mo16470a() {
            return this.f13323m;
        }

        @Override // com.tencent.smtt.utils.Elf.AbstractC2642a
        /* renamed from: b */
        long mo16469b() {
            return this.f13322l;
        }
    }

    /* compiled from: Elf.java */
    /* renamed from: com.tencent.smtt.utils.e$f */
    /* loaded from: classes2.dex */
    static class C2647f extends AbstractC2642a {

        /* renamed from: k */
        long f13338k;

        /* renamed from: l */
        long f13339l;

        /* renamed from: m */
        long f13340m;

        C2647f() {
        }

        @Override // com.tencent.smtt.utils.Elf.AbstractC2642a
        /* renamed from: a */
        long mo16470a() {
            return this.f13340m;
        }

        @Override // com.tencent.smtt.utils.Elf.AbstractC2642a
        /* renamed from: b */
        long mo16469b() {
            return this.f13339l;
        }
    }

    /* compiled from: Elf.java */
    /* renamed from: com.tencent.smtt.utils.e$d */
    /* loaded from: classes2.dex */
    static class C2645d extends AbstractC2652k {

        /* renamed from: a */
        int f13330a;

        /* renamed from: b */
        int f13331b;

        /* renamed from: c */
        int f13332c;

        /* renamed from: d */
        int f13333d;

        /* renamed from: e */
        int f13334e;

        /* renamed from: f */
        int f13335f;

        C2645d() {
        }

        @Override // com.tencent.smtt.utils.Elf.AbstractC2652k
        /* renamed from: a */
        public int mo16468a() {
            return this.f13333d;
        }

        @Override // com.tencent.smtt.utils.Elf.AbstractC2652k
        /* renamed from: b */
        public long mo16467b() {
            return this.f13332c;
        }
    }

    /* compiled from: Elf.java */
    /* renamed from: com.tencent.smtt.utils.e$h */
    /* loaded from: classes2.dex */
    static class C2649h extends AbstractC2652k {

        /* renamed from: a */
        long f13347a;

        /* renamed from: b */
        long f13348b;

        /* renamed from: c */
        long f13349c;

        /* renamed from: d */
        long f13350d;

        /* renamed from: e */
        long f13351e;

        /* renamed from: f */
        long f13352f;

        C2649h() {
        }

        @Override // com.tencent.smtt.utils.Elf.AbstractC2652k
        /* renamed from: a */
        public int mo16468a() {
            return (int) this.f13350d;
        }

        @Override // com.tencent.smtt.utils.Elf.AbstractC2652k
        /* renamed from: b */
        public long mo16467b() {
            return this.f13349c;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Elf.java */
    /* renamed from: com.tencent.smtt.utils.e$l */
    /* loaded from: classes2.dex */
    public static abstract class AbstractC2653l {

        /* renamed from: c */
        int f13361c;

        /* renamed from: d */
        char f13362d;

        /* renamed from: e */
        char f13363e;

        /* renamed from: f */
        short f13364f;

        AbstractC2653l() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Elf.java */
    /* renamed from: com.tencent.smtt.utils.e$e */
    /* loaded from: classes2.dex */
    public static class C2646e extends AbstractC2653l {

        /* renamed from: a */
        int f13336a;

        /* renamed from: b */
        int f13337b;

        C2646e() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Elf.java */
    /* renamed from: com.tencent.smtt.utils.e$i */
    /* loaded from: classes2.dex */
    public static class C2650i extends AbstractC2653l {

        /* renamed from: a */
        long f13353a;

        /* renamed from: b */
        long f13354b;

        C2650i() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Elf.java */
    /* renamed from: com.tencent.smtt.utils.e$j */
    /* loaded from: classes2.dex */
    public static abstract class AbstractC2651j {

        /* renamed from: g */
        int f13355g;

        /* renamed from: h */
        int f13356h;

        AbstractC2651j() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Elf.java */
    /* renamed from: com.tencent.smtt.utils.e$c */
    /* loaded from: classes2.dex */
    public static class C2644c extends AbstractC2651j {

        /* renamed from: a */
        int f13324a;

        /* renamed from: b */
        int f13325b;

        /* renamed from: c */
        int f13326c;

        /* renamed from: d */
        int f13327d;

        /* renamed from: e */
        int f13328e;

        /* renamed from: f */
        int f13329f;

        C2644c() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Elf.java */
    /* renamed from: com.tencent.smtt.utils.e$g */
    /* loaded from: classes2.dex */
    public static class C2648g extends AbstractC2651j {

        /* renamed from: a */
        long f13341a;

        /* renamed from: b */
        long f13342b;

        /* renamed from: c */
        long f13343c;

        /* renamed from: d */
        long f13344d;

        /* renamed from: e */
        long f13345e;

        /* renamed from: f */
        long f13346f;

        C2648g() {
        }
    }

    public Elf(File file) throws IOException, UnknownFormatConversionException {
        DataReader cVar = new DataReader(file);
        this.f13307g = cVar;
        cVar.m16492a(this.f13302b);
        if (m16481a()) {
            cVar.m16494a(m16473e());
            boolean d = m16474d();
            if (d) {
                C2647f fVar = new C2647f();
                fVar.f13311a = cVar.m16496a();
                fVar.f13312b = cVar.m16496a();
                fVar.f13313c = cVar.m16491b();
                fVar.f13338k = cVar.m16490c();
                fVar.f13339l = cVar.m16490c();
                fVar.f13340m = cVar.m16490c();
                this.f13308h = fVar;
            } else {
                C2643b bVar = new C2643b();
                bVar.f13311a = cVar.m16496a();
                bVar.f13312b = cVar.m16496a();
                bVar.f13313c = cVar.m16491b();
                bVar.f13321k = cVar.m16491b();
                bVar.f13322l = cVar.m16491b();
                bVar.f13323m = cVar.m16491b();
                this.f13308h = bVar;
            }
            AbstractC2642a aVar = this.f13308h;
            aVar.f13314d = cVar.m16491b();
            aVar.f13315e = cVar.m16496a();
            aVar.f13316f = cVar.m16496a();
            aVar.f13317g = cVar.m16496a();
            aVar.f13318h = cVar.m16496a();
            aVar.f13319i = cVar.m16496a();
            aVar.f13320j = cVar.m16496a();
            this.f13309i = new AbstractC2652k[aVar.f13319i];
            for (int i = 0; i < aVar.f13319i; i++) {
                cVar.m16495a(aVar.mo16470a() + (aVar.f13318h * i));
                if (d) {
                    C2649h hVar = new C2649h();
                    hVar.f13357g = cVar.m16491b();
                    hVar.f13358h = cVar.m16491b();
                    hVar.f13347a = cVar.m16490c();
                    hVar.f13348b = cVar.m16490c();
                    hVar.f13349c = cVar.m16490c();
                    hVar.f13350d = cVar.m16490c();
                    hVar.f13359i = cVar.m16491b();
                    hVar.f13360j = cVar.m16491b();
                    hVar.f13351e = cVar.m16490c();
                    hVar.f13352f = cVar.m16490c();
                    this.f13309i[i] = hVar;
                } else {
                    C2645d dVar = new C2645d();
                    dVar.f13357g = cVar.m16491b();
                    dVar.f13358h = cVar.m16491b();
                    dVar.f13330a = cVar.m16491b();
                    dVar.f13331b = cVar.m16491b();
                    dVar.f13332c = cVar.m16491b();
                    dVar.f13333d = cVar.m16491b();
                    dVar.f13359i = cVar.m16491b();
                    dVar.f13360j = cVar.m16491b();
                    dVar.f13334e = cVar.m16491b();
                    dVar.f13335f = cVar.m16491b();
                    this.f13309i[i] = dVar;
                }
            }
            if (aVar.f13320j > -1) {
                short s = aVar.f13320j;
                AbstractC2652k[] kVarArr = this.f13309i;
                if (s < kVarArr.length) {
                    AbstractC2652k kVar = kVarArr[aVar.f13320j];
                    if (kVar.f13358h == 3) {
                        this.f13310j = new byte[kVar.mo16468a()];
                        cVar.m16495a(kVar.mo16467b());
                        cVar.m16493a(this.f13310j);
                        if (this.f13303c) {
                            m16472f();
                            return;
                        }
                        return;
                    }
                    throw new UnknownFormatConversionException("Wrong string section e_shstrndx=" + ((int) aVar.f13320j));
                }
            }
            throw new UnknownFormatConversionException("Invalid e_shstrndx=" + ((int) aVar.f13320j));
        }
        throw new UnknownFormatConversionException("Invalid elf magic: " + file);
    }

    /* renamed from: f */
    private void m16472f() throws IOException {
        AbstractC2642a aVar = this.f13308h;
        DataReader cVar = this.f13307g;
        boolean d = m16474d();
        AbstractC2652k a = m16478a(com.lody.virtual.helper.dedex.Elf.SHN_DYNSYM);
        if (a != null) {
            cVar.m16495a(a.mo16467b());
            int a2 = a.mo16468a() / (d ? 24 : 16);
            this.f13305e = new AbstractC2653l[a2];
            char[] cArr = new char[1];
            for (int i = 0; i < a2; i++) {
                if (d) {
                    C2650i iVar = new C2650i();
                    iVar.f13361c = cVar.m16491b();
                    cVar.m16492a(cArr);
                    iVar.f13362d = cArr[0];
                    cVar.m16492a(cArr);
                    iVar.f13363e = cArr[0];
                    iVar.f13353a = cVar.m16490c();
                    iVar.f13354b = cVar.m16490c();
                    iVar.f13364f = cVar.m16496a();
                    this.f13305e[i] = iVar;
                } else {
                    C2646e eVar = new C2646e();
                    eVar.f13361c = cVar.m16491b();
                    eVar.f13336a = cVar.m16491b();
                    eVar.f13337b = cVar.m16491b();
                    cVar.m16492a(cArr);
                    eVar.f13362d = cArr[0];
                    cVar.m16492a(cArr);
                    eVar.f13363e = cArr[0];
                    eVar.f13364f = cVar.m16496a();
                    this.f13305e[i] = eVar;
                }
            }
            AbstractC2652k kVar = this.f13309i[a.f13359i];
            cVar.m16495a(kVar.mo16467b());
            this.f13306f = new byte[kVar.mo16468a()];
            cVar.m16493a(this.f13306f);
        }
        this.f13304d = new AbstractC2651j[aVar.f13317g];
        for (int i2 = 0; i2 < aVar.f13317g; i2++) {
            cVar.m16495a(aVar.mo16469b() + (aVar.f13316f * i2));
            if (d) {
                C2648g gVar = new C2648g();
                gVar.f13355g = cVar.m16491b();
                gVar.f13356h = cVar.m16491b();
                gVar.f13341a = cVar.m16490c();
                gVar.f13342b = cVar.m16490c();
                gVar.f13343c = cVar.m16490c();
                gVar.f13344d = cVar.m16490c();
                gVar.f13345e = cVar.m16490c();
                gVar.f13346f = cVar.m16490c();
                this.f13304d[i2] = gVar;
            } else {
                C2644c cVar2 = new C2644c();
                cVar2.f13355g = cVar.m16491b();
                cVar2.f13356h = cVar.m16491b();
                cVar2.f13324a = cVar.m16491b();
                cVar2.f13325b = cVar.m16491b();
                cVar2.f13326c = cVar.m16491b();
                cVar2.f13327d = cVar.m16491b();
                cVar2.f13328e = cVar.m16491b();
                cVar2.f13329f = cVar.m16491b();
                this.f13304d[i2] = cVar2;
            }
        }
    }

    /* renamed from: a */
    public final AbstractC2652k m16478a(String str) {
        AbstractC2652k[] kVarArr;
        for (AbstractC2652k kVar : this.f13309i) {
            if (str.equals(m16480a(kVar.f13357g))) {
                return kVar;
            }
        }
        return null;
    }

    /* renamed from: a */
    public final String m16480a(int i) {
        if (i == 0) {
            return "SHN_UNDEF";
        }
        int i2 = i;
        while (true) {
            byte[] bArr = this.f13310j;
            if (bArr[i2] == 0) {
                return new String(bArr, i, i2 - i);
            }
            i2++;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f13307g.close();
    }

    /* renamed from: a */
    public static boolean m16479a(File file) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            long readInt = randomAccessFile.readInt();
            randomAccessFile.close();
            return readInt == 2135247942;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m16476b(File file) {
        if (!m16471g() || !m16479a(file)) {
            return true;
        }
        try {
            new Elf(file);
            return true;
        } catch (IOException e) {
            Log.e("ELF", "checkElfFile IOException: " + e);
            return false;
        } catch (UnknownFormatConversionException e2) {
            Log.e("ELF", "checkElfFile UnknownFormatConversionException: " + e2);
            return true;
        } catch (Throwable th) {
            Log.e("ELF", "checkElfFile Throwable: " + th);
            return true;
        }
    }

    /* renamed from: g */
    private static boolean m16471g() {
        String property = System.getProperty("java.vm.version");
        return property != null && property.startsWith("2");
    }
}
