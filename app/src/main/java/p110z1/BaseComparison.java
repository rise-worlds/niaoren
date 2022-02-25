package p110z1;

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.p105io.FilenameUtils;

/* renamed from: z1.vt */
/* loaded from: classes3.dex */
abstract class BaseComparison implements Comparison {

    /* renamed from: c */
    private static final String f23584c = "0123456789.-+";

    /* renamed from: a */
    protected final String f23585a;

    /* renamed from: b */
    protected final FieldType f23586b;

    /* renamed from: d */
    private final Object f23587d;

    @Override // p110z1.Comparison
    /* renamed from: a */
    public abstract void mo275a(StringBuilder sb);

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseComparison(String str, FieldType ssVar, Object obj, boolean z) throws SQLException {
        if (!z || ssVar == null || ssVar.m689z()) {
            this.f23585a = str;
            this.f23586b = ssVar;
            this.f23587d = obj;
            return;
        }
        throw new SQLException("Field '" + str + "' is of data type " + ssVar.m713f() + " which can not be compared");
    }

    @Override // p110z1.Clause
    /* renamed from: a */
    public void mo274a(DatabaseType siVar, String str, StringBuilder sb, List<ArgumentHolder> list) throws SQLException {
        if (str != null) {
            siVar.mo899b(sb, str);
            sb.append(FilenameUtils.EXTENSION_SEPARATOR);
        }
        siVar.mo899b(sb, this.f23585a);
        sb.append(' ');
        mo275a(sb);
        mo273a(siVar, sb, list);
    }

    @Override // p110z1.Comparison
    /* renamed from: a */
    public String mo276a() {
        return this.f23585a;
    }

    @Override // p110z1.Comparison
    /* renamed from: a */
    public void mo273a(DatabaseType siVar, StringBuilder sb, List<ArgumentHolder> list) throws SQLException {
        mo277a(siVar, this.f23586b, sb, list, this.f23587d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:39:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void mo277a(p110z1.DatabaseType r9, p110z1.FieldType r10, java.lang.StringBuilder r11, java.util.List<p110z1.ArgumentHolder> r12, java.lang.Object r13) throws java.sql.SQLException {
        /*
            r8 = this;
            if (r13 == 0) goto L_0x00e2
            boolean r0 = r13 instanceof p110z1.ArgumentHolder
            r1 = 0
            r2 = 63
            if (r0 == 0) goto L_0x0018
            r11.append(r2)
            z1.up r13 = (p110z1.ArgumentHolder) r13
            java.lang.String r9 = r8.f23585a
            r13.mo507a(r9, r10)
            r12.add(r13)
            goto L_0x00d9
        L_0x0018:
            boolean r0 = r13 instanceof p110z1.ColumnArg
            if (r0 == 0) goto L_0x0035
            z1.ur r13 = (p110z1.ColumnArg) r13
            java.lang.String r10 = r13.m513a()
            if (r10 == 0) goto L_0x002c
            r9.mo899b(r11, r10)
            r10 = 46
            r11.append(r10)
        L_0x002c:
            java.lang.String r10 = r13.m512b()
            r9.mo899b(r11, r10)
            goto L_0x00d9
        L_0x0035:
            boolean r0 = r10.m742A()
            if (r0 == 0) goto L_0x0050
            r11.append(r2)
            z1.vc r9 = new z1.vc
            r9.<init>()
            java.lang.String r0 = r8.f23585a
            r9.mo507a(r0, r10)
            r9.mo387a(r13)
            r12.add(r9)
            goto L_0x00d9
        L_0x0050:
            boolean r0 = r10.m699p()
            if (r0 == 0) goto L_0x0074
            java.lang.Class r0 = r10.m717d()
            java.lang.Class r2 = r13.getClass()
            boolean r0 = r0.isAssignableFrom(r2)
            if (r0 == 0) goto L_0x0074
            z1.ss r4 = r10.m698q()
            java.lang.Object r7 = r4.m720b(r13)
            r2 = r8
            r3 = r9
            r5 = r11
            r6 = r12
            r2.mo277a(r3, r4, r5, r6, r7)
            goto L_0x00da
        L_0x0074:
            boolean r12 = r10.m697r()
            if (r12 == 0) goto L_0x0086
            java.lang.Object r10 = r10.m716d(r13)
            java.lang.String r10 = r10.toString()
            r9.mo903a(r11, r10)
            goto L_0x00d9
        L_0x0086:
            boolean r9 = r10.m699p()
            if (r9 == 0) goto L_0x00d2
            java.lang.Object r9 = r10.m716d(r13)
            java.lang.String r9 = r9.toString()
            int r12 = r9.length()
            if (r12 <= 0) goto L_0x00ce
            java.lang.String r12 = "0123456789.-+"
            char r13 = r9.charAt(r1)
            int r12 = r12.indexOf(r13)
            if (r12 < 0) goto L_0x00a7
            goto L_0x00ce
        L_0x00a7:
            java.sql.SQLException r11 = new java.sql.SQLException
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = "Foreign field "
            r12.append(r13)
            r12.append(r10)
            java.lang.String r10 = " does not seem to be producing a numerical value '"
            r12.append(r10)
            r12.append(r9)
            java.lang.String r9 = "'. Maybe you are passing the wrong object to comparison: "
            r12.append(r9)
            r12.append(r8)
            java.lang.String r9 = r12.toString()
            r11.<init>(r9)
            throw r11
        L_0x00ce:
            r11.append(r9)
            goto L_0x00d9
        L_0x00d2:
            java.lang.Object r9 = r10.m716d(r13)
            r11.append(r9)
        L_0x00d9:
            r1 = 1
        L_0x00da:
            if (r1 == 0) goto L_0x00e1
            r9 = 32
            r11.append(r9)
        L_0x00e1:
            return
        L_0x00e2:
            java.sql.SQLException r9 = new java.sql.SQLException
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "argument for '"
            r11.append(r12)
            java.lang.String r10 = r10.m719c()
            r11.append(r10)
            java.lang.String r10 = "' is null"
            r11.append(r10)
            java.lang.String r10 = r11.toString()
            r9.<init>(r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.BaseComparison.mo277a(z1.si, z1.ss, java.lang.StringBuilder, java.util.List, java.lang.Object):void");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f23585a);
        sb.append(' ');
        mo275a(sb);
        sb.append(' ');
        sb.append(this.f23587d);
        return sb.toString();
    }
}
