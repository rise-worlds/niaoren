package p110z1;

import java.io.BufferedReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007H\u0096\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, m8860e = {"Lkotlin/io/LinesSequence;", "Lkotlin/sequences/Sequence;", "", "reader", "Ljava/io/BufferedReader;", "(Ljava/io/BufferedReader;)V", "iterator", "", "kotlin-stdlib"})
/* renamed from: z1.cgc */
/* loaded from: classes3.dex */
public final class ReadWrite implements Sequence<String> {

    /* renamed from: a */
    private final BufferedReader f20682a;

    public ReadWrite(@NotNull BufferedReader bufferedReader) {
        cji.m5162f(bufferedReader, "reader");
        this.f20682a = bufferedReader;
    }

    /* compiled from: ReadWrite.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0017\n\u0000\n\u0002\u0010(\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\t\u0010\u0006\u001a\u00020\u0004H\u0096\u0002J\t\u0010\u0007\u001a\u00020\u0002H\u0096\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0002X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, m8860e = {"kotlin/io/LinesSequence$iterator$1", "", "", "done", "", "nextValue", "hasNext", "next", "kotlin-stdlib"})
    /* renamed from: z1.cgc$a */
    /* loaded from: classes3.dex */
    public static final class C4958a implements Iterator<String>, KMarkers {

        /* renamed from: b */
        private String f20684b;

        /* renamed from: c */
        private boolean f20685c;

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        C4958a() {
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f20684b == null && !this.f20685c) {
                this.f20684b = ReadWrite.this.f20682a.readLine();
                if (this.f20684b == null) {
                    this.f20685c = true;
                }
            }
            return this.f20684b != null;
        }

        @NotNull
        /* renamed from: a */
        public String next() {
            if (hasNext()) {
                String str = this.f20684b;
                this.f20684b = null;
                if (str == null) {
                    cji.m5196a();
                }
                return str;
            }
            throw new NoSuchElementException();
        }
    }

    @Override // p110z1.Sequence
    @NotNull
    /* renamed from: a */
    public Iterator<String> mo3707a() {
        return new C4958a();
    }
}
