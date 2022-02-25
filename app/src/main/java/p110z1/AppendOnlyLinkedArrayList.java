package p110z1;

/* renamed from: z1.bsl */
/* loaded from: classes3.dex */
public class AppendOnlyLinkedArrayList<T> {

    /* renamed from: a */
    final int f20058a;

    /* renamed from: b */
    final Object[] f20059b;

    /* renamed from: c */
    Object[] f20060c;

    /* renamed from: d */
    int f20061d;

    /* compiled from: AppendOnlyLinkedArrayList.java */
    /* renamed from: z1.bsl$a */
    /* loaded from: classes3.dex */
    public interface AbstractC4743a<T> extends Predicate<T> {
        @Override // p110z1.Predicate
        boolean test(T t);
    }

    public AppendOnlyLinkedArrayList(int i) {
        this.f20058a = i;
        this.f20059b = new Object[i + 1];
        this.f20060c = this.f20059b;
    }

    /* renamed from: a */
    public void m9456a(T t) {
        int i = this.f20058a;
        int i2 = this.f20061d;
        if (i2 == i) {
            Object[] objArr = new Object[i + 1];
            this.f20060c[i] = objArr;
            this.f20060c = objArr;
            i2 = 0;
        }
        this.f20060c[i2] = t;
        this.f20061d = i2 + 1;
    }

    /* renamed from: b */
    public void m9451b(T t) {
        this.f20059b[0] = t;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0018, code lost:
        continue;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m9453a(p110z1.AppendOnlyLinkedArrayList.AbstractC4743a<? super T> r5) {
        /*
            r4 = this;
            java.lang.Object[] r0 = r4.f20059b
            int r1 = r4.f20058a
        L_0x0004:
            if (r0 == 0) goto L_0x001d
            r2 = 0
        L_0x0007:
            if (r2 >= r1) goto L_0x0018
            r3 = r0[r2]
            if (r3 != 0) goto L_0x000e
            goto L_0x0018
        L_0x000e:
            boolean r3 = r5.test(r3)
            if (r3 == 0) goto L_0x0015
            return
        L_0x0015:
            int r2 = r2 + 1
            goto L_0x0007
        L_0x0018:
            r0 = r0[r1]
            java.lang.Object[] r0 = (java.lang.Object[]) r0
            goto L_0x0004
        L_0x001d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.AppendOnlyLinkedArrayList.m9453a(z1.bsl$a):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0019, code lost:
        continue;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <U> boolean m9452a(p110z1.Subscriber<? super U> r5) {
        /*
            r4 = this;
            java.lang.Object[] r0 = r4.f20059b
            int r1 = r4.f20058a
        L_0x0004:
            r2 = 0
            if (r0 == 0) goto L_0x001e
        L_0x0007:
            if (r2 >= r1) goto L_0x0019
            r3 = r0[r2]
            if (r3 != 0) goto L_0x000e
            goto L_0x0019
        L_0x000e:
            boolean r3 = p110z1.NotificationLite.acceptFull(r3, r5)
            if (r3 == 0) goto L_0x0016
            r5 = 1
            return r5
        L_0x0016:
            int r2 = r2 + 1
            goto L_0x0007
        L_0x0019:
            r0 = r0[r1]
            java.lang.Object[] r0 = (java.lang.Object[]) r0
            goto L_0x0004
        L_0x001e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.AppendOnlyLinkedArrayList.m9452a(z1.dbx):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0019, code lost:
        continue;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <U> boolean m9454a(p110z1.Observer<? super U> r5) {
        /*
            r4 = this;
            java.lang.Object[] r0 = r4.f20059b
            int r1 = r4.f20058a
        L_0x0004:
            r2 = 0
            if (r0 == 0) goto L_0x001e
        L_0x0007:
            if (r2 >= r1) goto L_0x0019
            r3 = r0[r2]
            if (r3 != 0) goto L_0x000e
            goto L_0x0019
        L_0x000e:
            boolean r3 = p110z1.NotificationLite.acceptFull(r3, r5)
            if (r3 == 0) goto L_0x0016
            r5 = 1
            return r5
        L_0x0016:
            int r2 = r2 + 1
            goto L_0x0007
        L_0x0019:
            r0 = r0[r1]
            java.lang.Object[] r0 = (java.lang.Object[]) r0
            goto L_0x0004
        L_0x001e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.AppendOnlyLinkedArrayList.m9454a(z1.ass):boolean");
    }

    /* renamed from: a */
    public <S> void m9455a(S s, BiPredicate<? super S, ? super T> aujVar) throws Exception {
        Object[] objArr = this.f20059b;
        int i = this.f20058a;
        while (true) {
            for (int i2 = 0; i2 < i; i2++) {
                Object obj = objArr[i2];
                if (obj == null || aujVar.mo9871a(s, obj)) {
                    return;
                }
            }
            objArr = objArr[i];
        }
    }
}
