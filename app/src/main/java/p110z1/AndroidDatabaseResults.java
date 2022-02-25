package p110z1;

import android.database.Cursor;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* renamed from: z1.rm */
/* loaded from: classes3.dex */
public class AndroidDatabaseResults implements DatabaseResults {

    /* renamed from: a */
    private static final int f23144a = 8;

    /* renamed from: f */
    private static final DatabaseType f23145f = new SqliteAndroidDatabaseType();

    /* renamed from: b */
    private final Cursor f23146b;

    /* renamed from: c */
    private final String[] f23147c;

    /* renamed from: d */
    private final Map<String, Integer> f23148d;

    /* renamed from: e */
    private final ObjectCache f23149e;

    public AndroidDatabaseResults(Cursor cursor, ObjectCache scVar) {
        this.f23146b = cursor;
        this.f23147c = cursor.getColumnNames();
        if (this.f23147c.length >= 8) {
            this.f23148d = new HashMap();
            int i = 0;
            while (true) {
                String[] strArr = this.f23147c;
                if (i >= strArr.length) {
                    break;
                }
                this.f23148d.put(strArr[i], Integer.valueOf(i));
                i++;
            }
        } else {
            this.f23148d = null;
        }
        this.f23149e = scVar;
    }

    @Deprecated
    public AndroidDatabaseResults(Cursor cursor, boolean z, ObjectCache scVar) {
        this(cursor, scVar);
    }

    @Override // p110z1.DatabaseResults
    /* renamed from: a */
    public int mo227a() {
        return this.f23146b.getColumnCount();
    }

    @Override // p110z1.DatabaseResults
    /* renamed from: b */
    public String[] mo224b() {
        int a = mo227a();
        String[] strArr = new String[a];
        for (int i = 0; i < a; i++) {
            strArr[i] = this.f23146b.getColumnName(i);
        }
        return strArr;
    }

    @Override // p110z1.DatabaseResults
    /* renamed from: c */
    public boolean mo222c() {
        return this.f23146b.moveToFirst();
    }

    @Override // p110z1.DatabaseResults
    /* renamed from: d */
    public boolean mo220d() {
        return this.f23146b.moveToNext();
    }

    @Override // p110z1.DatabaseResults
    /* renamed from: e */
    public boolean mo218e() {
        return this.f23146b.moveToLast();
    }

    @Override // p110z1.DatabaseResults
    /* renamed from: f */
    public boolean mo216f() {
        return this.f23146b.moveToPrevious();
    }

    @Override // p110z1.DatabaseResults
    /* renamed from: a */
    public boolean mo226a(int i) {
        return this.f23146b.move(i);
    }

    @Override // p110z1.DatabaseResults
    /* renamed from: b */
    public boolean mo223b(int i) {
        return this.f23146b.moveToPosition(i);
    }

    /* renamed from: g */
    public int m1143g() {
        return this.f23146b.getCount();
    }

    /* renamed from: h */
    public int m1142h() {
        return this.f23146b.getPosition();
    }

    @Override // p110z1.DatabaseResults
    /* renamed from: a */
    public int mo225a(String str) throws SQLException {
        int b = m1144b(str);
        if (b >= 0) {
            return b;
        }
        StringBuilder sb = new StringBuilder(str.length() + 4);
        f23145f.mo899b(sb, str);
        int b2 = m1144b(sb.toString());
        if (b2 >= 0) {
            return b2;
        }
        String[] columnNames = this.f23146b.getColumnNames();
        throw new SQLException("Unknown field '" + str + "' from the Android sqlite cursor, not in:" + Arrays.toString(columnNames));
    }

    @Override // p110z1.DatabaseResults
    /* renamed from: c */
    public String mo221c(int i) {
        return this.f23146b.getString(i);
    }

    @Override // p110z1.DatabaseResults
    /* renamed from: d */
    public boolean mo219d(int i) {
        return !this.f23146b.isNull(i) && this.f23146b.getShort(i) != 0;
    }

    @Override // p110z1.DatabaseResults
    /* renamed from: e */
    public char mo217e(int i) throws SQLException {
        String string = this.f23146b.getString(i);
        if (string == null || string.length() == 0) {
            return (char) 0;
        }
        if (string.length() == 1) {
            return string.charAt(0);
        }
        throw new SQLException("More than 1 character stored in database column: " + i);
    }

    @Override // p110z1.DatabaseResults
    /* renamed from: f */
    public byte mo215f(int i) {
        return (byte) mo213h(i);
    }

    @Override // p110z1.DatabaseResults
    /* renamed from: g */
    public byte[] mo214g(int i) {
        return this.f23146b.getBlob(i);
    }

    @Override // p110z1.DatabaseResults
    /* renamed from: h */
    public short mo213h(int i) {
        return this.f23146b.getShort(i);
    }

    @Override // p110z1.DatabaseResults
    /* renamed from: i */
    public int mo211i(int i) {
        return this.f23146b.getInt(i);
    }

    @Override // p110z1.DatabaseResults
    /* renamed from: j */
    public long mo209j(int i) {
        return this.f23146b.getLong(i);
    }

    @Override // p110z1.DatabaseResults
    /* renamed from: k */
    public float mo207k(int i) {
        return this.f23146b.getFloat(i);
    }

    @Override // p110z1.DatabaseResults
    /* renamed from: l */
    public double mo206l(int i) {
        return this.f23146b.getDouble(i);
    }

    @Override // p110z1.DatabaseResults
    /* renamed from: m */
    public Timestamp mo205m(int i) throws SQLException {
        throw new SQLException("Android does not support timestamp.  Use JAVA_DATE_LONG or JAVA_DATE_STRING types");
    }

    @Override // p110z1.DatabaseResults
    /* renamed from: n */
    public InputStream mo204n(int i) {
        return new ByteArrayInputStream(this.f23146b.getBlob(i));
    }

    @Override // p110z1.DatabaseResults
    /* renamed from: o */
    public BigDecimal mo203o(int i) throws SQLException {
        throw new SQLException("Android does not support BigDecimal type.  Use BIG_DECIMAL or BIG_DECIMAL_STRING types");
    }

    @Override // p110z1.DatabaseResults
    /* renamed from: p */
    public boolean mo202p(int i) {
        return this.f23146b.isNull(i);
    }

    @Override // p110z1.DatabaseResults
    /* renamed from: i */
    public ObjectCache mo212i() {
        return this.f23149e;
    }

    @Override // p110z1.DatabaseResults
    /* renamed from: j */
    public void mo210j() {
        this.f23146b.close();
    }

    @Override // p110z1.DatabaseResults
    /* renamed from: k */
    public void mo208k() {
        mo210j();
    }

    /* renamed from: l */
    public Cursor m1141l() {
        return this.f23146b;
    }

    public String toString() {
        return getClass().getSimpleName() + "@" + Integer.toHexString(super.hashCode());
    }

    /* renamed from: b */
    private int m1144b(String str) {
        Map<String, Integer> map = this.f23148d;
        if (map == null) {
            int i = 0;
            while (true) {
                String[] strArr = this.f23147c;
                if (i >= strArr.length) {
                    return -1;
                }
                if (strArr[i].equals(str)) {
                    return i;
                }
                i++;
            }
        } else {
            Integer num = map.get(str);
            if (num == null) {
                return -1;
            }
            return num.intValue();
        }
    }
}
