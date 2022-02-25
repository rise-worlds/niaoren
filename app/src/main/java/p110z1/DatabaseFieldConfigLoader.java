package p110z1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.SQLException;

/* renamed from: z1.sq */
/* loaded from: classes3.dex */
public class DatabaseFieldConfigLoader {

    /* renamed from: A */
    private static final String f23295A = "persisterClass";

    /* renamed from: B */
    private static final String f23296B = "allowGeneratedIdInsert";

    /* renamed from: C */
    private static final String f23297C = "columnDefinition";

    /* renamed from: D */
    private static final String f23298D = "foreignAutoCreate";

    /* renamed from: E */
    private static final String f23299E = "version";

    /* renamed from: F */
    private static final String f23300F = "foreignColumnName";

    /* renamed from: G */
    private static final String f23301G = "readOnly";

    /* renamed from: H */
    private static final String f23302H = "foreignCollection";

    /* renamed from: I */
    private static final String f23303I = "foreignCollectionEager";

    /* renamed from: J */
    private static final String f23304J = "maxEagerForeignCollectionLevel";

    /* renamed from: K */
    private static final String f23305K = "foreignCollectionMaxEagerLevel";

    /* renamed from: L */
    private static final String f23306L = "foreignCollectionColumnName";

    /* renamed from: M */
    private static final String f23307M = "foreignCollectionOrderColumn";

    /* renamed from: N */
    private static final String f23308N = "foreignCollectionOrderColumnName";

    /* renamed from: O */
    private static final String f23309O = "foreignCollectionOrderAscending";

    /* renamed from: P */
    private static final String f23310P = "foreignCollectionForeignColumnName";

    /* renamed from: Q */
    private static final String f23311Q = "foreignCollectionForeignFieldName";

    /* renamed from: a */
    private static final String f23312a = "# --field-start--";

    /* renamed from: b */
    private static final String f23313b = "# --field-end--";

    /* renamed from: c */
    private static final int f23314c = 1;

    /* renamed from: d */
    private static final DataPersister f23315d = DatabaseFieldConfig.f23252b.getDataPersister();

    /* renamed from: e */
    private static final String f23316e = "fieldName";

    /* renamed from: f */
    private static final String f23317f = "columnName";

    /* renamed from: g */
    private static final String f23318g = "dataPersister";

    /* renamed from: h */
    private static final String f23319h = "defaultValue";

    /* renamed from: i */
    private static final String f23320i = "width";

    /* renamed from: j */
    private static final String f23321j = "canBeNull";

    /* renamed from: k */
    private static final String f23322k = "id";

    /* renamed from: l */
    private static final String f23323l = "generatedId";

    /* renamed from: m */
    private static final String f23324m = "generatedIdSequence";

    /* renamed from: n */
    private static final String f23325n = "foreign";

    /* renamed from: o */
    private static final String f23326o = "useGetSet";

    /* renamed from: p */
    private static final String f23327p = "unknownEnumValue";

    /* renamed from: q */
    private static final String f23328q = "throwIfNull";

    /* renamed from: r */
    private static final String f23329r = "format";

    /* renamed from: s */
    private static final String f23330s = "unique";

    /* renamed from: t */
    private static final String f23331t = "uniqueCombo";

    /* renamed from: u */
    private static final String f23332u = "index";

    /* renamed from: v */
    private static final String f23333v = "indexName";

    /* renamed from: w */
    private static final String f23334w = "uniqueIndex";

    /* renamed from: x */
    private static final String f23335x = "uniqueIndexName";

    /* renamed from: y */
    private static final String f23336y = "foreignAutoRefresh";

    /* renamed from: z */
    private static final String f23337z = "maxForeignAutoRefreshLevel";

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0017, code lost:
        if (r3 == false) goto L_0x001a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0019, code lost:
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001a, code lost:
        return null;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static p110z1.DatabaseFieldConfig m746a(java.io.BufferedReader r7) throws java.sql.SQLException {
        /*
            z1.sp r0 = new z1.sp
            r0.<init>()
            r1 = 1
            r2 = 0
            r3 = 0
        L_0x0008:
            java.lang.String r4 = r7.readLine()     // Catch: IOException -> 0x005e
            if (r4 != 0) goto L_0x000f
            goto L_0x0017
        L_0x000f:
            java.lang.String r5 = "# --field-end--"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x001c
        L_0x0017:
            if (r3 == 0) goto L_0x001a
            return r0
        L_0x001a:
            r7 = 0
            return r7
        L_0x001c:
            int r5 = r4.length()
            if (r5 == 0) goto L_0x0008
            java.lang.String r5 = "#"
            boolean r5 = r4.startsWith(r5)
            if (r5 != 0) goto L_0x0008
            java.lang.String r5 = "# --field-start--"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0033
            goto L_0x0008
        L_0x0033:
            java.lang.String r3 = "="
            r5 = -2
            java.lang.String[] r3 = r4.split(r3, r5)
            int r5 = r3.length
            r6 = 2
            if (r5 != r6) goto L_0x0047
            r4 = r3[r2]
            r3 = r3[r1]
            m744a(r0, r4, r3)
            r3 = 1
            goto L_0x0008
        L_0x0047:
            java.sql.SQLException r7 = new java.sql.SQLException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "DatabaseFieldConfig reading from stream cannot parse line: "
            r0.append(r1)
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            r7.<init>(r0)
            throw r7
        L_0x005e:
            r7 = move-exception
            java.lang.String r0 = "Could not read DatabaseFieldConfig from stream"
            java.sql.SQLException r7 = p110z1.SqlExceptionUtil.m529a(r0, r7)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.DatabaseFieldConfigLoader.m746a(java.io.BufferedReader):z1.sp");
    }

    /* renamed from: a */
    public static void m745a(BufferedWriter bufferedWriter, DatabaseFieldConfig spVar, String str) throws SQLException {
        try {
            m743b(bufferedWriter, spVar, str);
        } catch (IOException e) {
            throw SqlExceptionUtil.m529a("Could not write config to writer", e);
        }
    }

    /* renamed from: b */
    public static void m743b(BufferedWriter bufferedWriter, DatabaseFieldConfig spVar, String str) throws IOException {
        bufferedWriter.append(f23312a);
        bufferedWriter.newLine();
        if (spVar.m826a() != null) {
            bufferedWriter.append(f23316e).append('=').append((CharSequence) spVar.m826a());
            bufferedWriter.newLine();
        }
        if (spVar.m812b() != null) {
            bufferedWriter.append(f23317f).append('=').append((CharSequence) spVar.m812b());
            bufferedWriter.newLine();
        }
        if (spVar.m802d() != f23315d) {
            DataType[] values = DataType.values();
            int length = values.length;
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                DataType snVar = values[i];
                if (snVar.getDataPersister() == spVar.m802d()) {
                    bufferedWriter.append(f23318g).append('=').append((CharSequence) snVar.name());
                    bufferedWriter.newLine();
                    z = true;
                    break;
                }
                i++;
            }
            if (!z) {
                throw new IllegalArgumentException("Unknown data persister field: " + spVar.m802d());
            }
        }
        if (spVar.m798e() != null) {
            bufferedWriter.append(f23319h).append('=').append((CharSequence) spVar.m798e());
            bufferedWriter.newLine();
        }
        if (spVar.m794f() != 0) {
            bufferedWriter.append(f23320i).append('=').append((CharSequence) Integer.toString(spVar.m794f()));
            bufferedWriter.newLine();
        }
        if (!spVar.m791g()) {
            bufferedWriter.append(f23321j).append('=').append((CharSequence) Boolean.toString(spVar.m791g()));
            bufferedWriter.newLine();
        }
        if (spVar.m788h()) {
            bufferedWriter.append("id").append('=').append("true");
            bufferedWriter.newLine();
        }
        if (spVar.m785i()) {
            bufferedWriter.append(f23323l).append('=').append("true");
            bufferedWriter.newLine();
        }
        if (spVar.m782j() != null) {
            bufferedWriter.append(f23324m).append('=').append((CharSequence) spVar.m782j());
            bufferedWriter.newLine();
        }
        if (spVar.m779k()) {
            bufferedWriter.append(f23325n).append('=').append("true");
            bufferedWriter.newLine();
        }
        if (spVar.m773m()) {
            bufferedWriter.append(f23326o).append('=').append("true");
            bufferedWriter.newLine();
        }
        if (spVar.m770n() != null) {
            bufferedWriter.append(f23327p).append('=').append((CharSequence) spVar.m770n().getClass().getName()).append("#").append((CharSequence) spVar.m770n().name());
            bufferedWriter.newLine();
        }
        if (spVar.m767o()) {
            bufferedWriter.append(f23328q).append('=').append("true");
            bufferedWriter.newLine();
        }
        if (spVar.m761q() != null) {
            bufferedWriter.append(f23329r).append('=').append((CharSequence) spVar.m761q());
            bufferedWriter.newLine();
        }
        if (spVar.m758r()) {
            bufferedWriter.append(f23330s).append('=').append("true");
            bufferedWriter.newLine();
        }
        if (spVar.m755s()) {
            bufferedWriter.append(f23331t).append('=').append("true");
            bufferedWriter.newLine();
        }
        String f = spVar.m793f(str);
        if (f != null) {
            bufferedWriter.append(f23333v).append('=').append((CharSequence) f);
            bufferedWriter.newLine();
        }
        String h = spVar.m787h(str);
        if (h != null) {
            bufferedWriter.append(f23335x).append('=').append((CharSequence) h);
            bufferedWriter.newLine();
        }
        if (spVar.m751v()) {
            bufferedWriter.append(f23336y).append('=').append("true");
            bufferedWriter.newLine();
        }
        if (spVar.m750w() != -1) {
            bufferedWriter.append(f23337z).append('=').append((CharSequence) Integer.toString(spVar.m750w()));
            bufferedWriter.newLine();
        }
        if (spVar.m834E() != DatabaseFieldConfig.f23251a) {
            bufferedWriter.append(f23295A).append('=').append((CharSequence) spVar.m834E().getName());
            bufferedWriter.newLine();
        }
        if (spVar.m833F()) {
            bufferedWriter.append(f23296B).append('=').append("true");
            bufferedWriter.newLine();
        }
        if (spVar.m832G() != null) {
            bufferedWriter.append(f23297C).append('=').append((CharSequence) spVar.m832G());
            bufferedWriter.newLine();
        }
        if (spVar.m831H()) {
            bufferedWriter.append(f23298D).append('=').append("true");
            bufferedWriter.newLine();
        }
        if (spVar.m830I()) {
            bufferedWriter.append("version").append('=').append("true");
            bufferedWriter.newLine();
        }
        String J = spVar.m829J();
        if (J != null) {
            bufferedWriter.append(f23300F).append('=').append((CharSequence) J);
            bufferedWriter.newLine();
        }
        if (spVar.m828K()) {
            bufferedWriter.append(f23301G).append('=').append("true");
            bufferedWriter.newLine();
        }
        if (spVar.m749x()) {
            bufferedWriter.append(f23302H).append('=').append("true");
            bufferedWriter.newLine();
        }
        if (spVar.m748y()) {
            bufferedWriter.append(f23303I).append('=').append("true");
            bufferedWriter.newLine();
        }
        if (spVar.m747z() != 1) {
            bufferedWriter.append(f23305K).append('=').append((CharSequence) Integer.toString(spVar.m747z()));
            bufferedWriter.newLine();
        }
        if (spVar.m838A() != null) {
            bufferedWriter.append(f23306L).append('=').append((CharSequence) spVar.m838A());
            bufferedWriter.newLine();
        }
        if (spVar.m837B() != null) {
            bufferedWriter.append(f23308N).append('=').append((CharSequence) spVar.m837B());
            bufferedWriter.newLine();
        }
        if (!spVar.m836C()) {
            bufferedWriter.append(f23309O).append('=').append((CharSequence) Boolean.toString(spVar.m836C()));
            bufferedWriter.newLine();
        }
        if (spVar.m835D() != null) {
            bufferedWriter.append(f23311Q).append('=').append((CharSequence) spVar.m835D());
            bufferedWriter.newLine();
        }
        bufferedWriter.append(f23313b);
        bufferedWriter.newLine();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    private static void m744a(DatabaseFieldConfig spVar, String str, String str2) {
        Enum[] enumArr;
        if (str.equals(f23316e)) {
            spVar.m822a(str2);
        } else if (str.equals(f23317f)) {
            spVar.m810b(str2);
        } else if (str.equals(f23318g)) {
            spVar.m816a(DataType.valueOf(str2).getDataPersister());
        } else if (str.equals(f23319h)) {
            spVar.m804c(str2);
        } else if (str.equals(f23320i)) {
            spVar.m825a(Integer.parseInt(str2));
        } else if (str.equals(f23321j)) {
            spVar.m813a(Boolean.parseBoolean(str2));
        } else if (str.equals("id")) {
            spVar.m807b(Boolean.parseBoolean(str2));
        } else if (str.equals(f23323l)) {
            spVar.m803c(Boolean.parseBoolean(str2));
        } else if (str.equals(f23324m)) {
            spVar.m800d(str2);
        } else if (str.equals(f23325n)) {
            spVar.m799d(Boolean.parseBoolean(str2));
        } else if (str.equals(f23326o)) {
            spVar.m795e(Boolean.parseBoolean(str2));
        } else if (str.equals(f23327p)) {
            String[] split = str2.split("#", -2);
            if (split.length == 2) {
                try {
                    Object[] enumConstants = Class.forName(split[0]).getEnumConstants();
                    if (enumConstants != null) {
                        boolean z = false;
                        for (Enum r5 : (Enum[]) enumConstants) {
                            if (r5.name().equals(split[1])) {
                                spVar.m823a(r5);
                                z = true;
                            }
                        }
                        if (!z) {
                            throw new IllegalArgumentException("Invalid enum value name for unknownEnumvalue: " + str2);
                        }
                        return;
                    }
                    throw new IllegalArgumentException("Invalid class is not an Enum for unknownEnumValue: " + str2);
                } catch (ClassNotFoundException unused) {
                    throw new IllegalArgumentException("Unknown class specified for unknownEnumValue: " + str2);
                }
            } else {
                throw new IllegalArgumentException("Invalid value for unknownEnumValue which should be in class#name format: " + str2);
            }
        } else if (str.equals(f23328q)) {
            spVar.m792f(Boolean.parseBoolean(str2));
        } else if (str.equals(f23329r)) {
            spVar.m796e(str2);
        } else if (str.equals(f23330s)) {
            spVar.m786h(Boolean.parseBoolean(str2));
        } else if (str.equals(f23331t)) {
            spVar.m783i(Boolean.parseBoolean(str2));
        } else if (str.equals(f23332u)) {
            spVar.m780j(Boolean.parseBoolean(str2));
        } else if (str.equals(f23333v)) {
            spVar.m780j(true);
            spVar.m790g(str2);
        } else if (str.equals(f23334w)) {
            spVar.m777k(Boolean.parseBoolean(str2));
        } else if (str.equals(f23335x)) {
            spVar.m777k(true);
            spVar.m784i(str2);
        } else if (str.equals(f23336y)) {
            spVar.m774l(Boolean.parseBoolean(str2));
        } else if (str.equals(f23337z)) {
            spVar.m811b(Integer.parseInt(str2));
        } else if (str.equals(f23295A)) {
            try {
                spVar.m824a((Class<? extends DataPersister>) Class.forName(str2));
            } catch (ClassNotFoundException unused2) {
                throw new IllegalArgumentException("Could not find persisterClass: " + str2);
            }
        } else if (str.equals(f23296B)) {
            spVar.m762p(Boolean.parseBoolean(str2));
        } else if (str.equals(f23297C)) {
            spVar.m766o(str2);
        } else if (str.equals(f23298D)) {
            spVar.m759q(Boolean.parseBoolean(str2));
        } else if (str.equals("version")) {
            spVar.m756r(Boolean.parseBoolean(str2));
        } else if (str.equals(f23300F)) {
            spVar.m763p(str2);
        } else if (str.equals(f23301G)) {
            spVar.m754s(Boolean.parseBoolean(str2));
        } else if (str.equals(f23302H)) {
            spVar.m771m(Boolean.parseBoolean(str2));
        } else if (str.equals(f23303I)) {
            spVar.m768n(Boolean.parseBoolean(str2));
        } else if (str.equals(f23304J)) {
            spVar.m805c(Integer.parseInt(str2));
        } else if (str.equals(f23305K)) {
            spVar.m805c(Integer.parseInt(str2));
        } else if (str.equals(f23306L)) {
            spVar.m781j(str2);
        } else if (str.equals(f23307M)) {
            spVar.m775l(str2);
        } else if (str.equals(f23308N)) {
            spVar.m775l(str2);
        } else if (str.equals(f23309O)) {
            spVar.m765o(Boolean.parseBoolean(str2));
        } else if (str.equals(f23310P)) {
            spVar.m769n(str2);
        } else if (str.equals(f23311Q)) {
            spVar.m769n(str2);
        }
    }
}
