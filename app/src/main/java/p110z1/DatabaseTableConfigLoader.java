package p110z1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/* renamed from: z1.ws */
/* loaded from: classes3.dex */
public class DatabaseTableConfigLoader {

    /* renamed from: a */
    private static final String f23627a = "# --table-start--";

    /* renamed from: b */
    private static final String f23628b = "# --table-end--";

    /* renamed from: c */
    private static final String f23629c = "# --table-fields-start--";

    /* renamed from: d */
    private static final String f23630d = "# --table-fields-end--";

    /* renamed from: e */
    private static final String f23631e = "dataClass";

    /* renamed from: f */
    private static final String f23632f = "tableName";

    /* renamed from: a */
    public static List<DatabaseTableConfig<?>> m182a(BufferedReader bufferedReader) throws SQLException {
        ArrayList arrayList = new ArrayList();
        while (true) {
            DatabaseTableConfig b = m178b(bufferedReader);
            if (b == null) {
                return arrayList;
            }
            arrayList.add(b);
        }
    }

    /* renamed from: b */
    public static <T> DatabaseTableConfig<T> m178b(BufferedReader bufferedReader) throws SQLException {
        DatabaseTableConfig<T> wrVar = new DatabaseTableConfig<>();
        boolean z = false;
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null && !readLine.equals(f23628b)) {
                    if (readLine.equals(f23629c)) {
                        m181a(bufferedReader, wrVar);
                    } else if (readLine.length() != 0 && !readLine.startsWith("#") && !readLine.equals(f23627a)) {
                        String[] split = readLine.split(SimpleComparison.f23609c, -2);
                        if (split.length == 2) {
                            m179a(wrVar, split[0], split[1]);
                            z = true;
                        } else {
                            throw new SQLException("DatabaseTableConfig reading from stream cannot parse line: " + readLine);
                        }
                    }
                }
            } catch (IOException e) {
                throw SqlExceptionUtil.m529a("Could not read DatabaseTableConfig from stream", e);
            }
        }
        if (z) {
            return wrVar;
        }
        return null;
    }

    /* renamed from: a */
    public static <T> void m180a(BufferedWriter bufferedWriter, DatabaseTableConfig<T> wrVar) throws SQLException {
        try {
            m177b(bufferedWriter, wrVar);
        } catch (IOException e) {
            throw SqlExceptionUtil.m529a("Could not write config to writer", e);
        }
    }

    /* renamed from: b */
    private static <T> void m177b(BufferedWriter bufferedWriter, DatabaseTableConfig<T> wrVar) throws IOException, SQLException {
        bufferedWriter.append(f23627a);
        bufferedWriter.newLine();
        if (wrVar.m188b() != null) {
            bufferedWriter.append(f23631e).append('=').append((CharSequence) wrVar.m188b().getName());
            bufferedWriter.newLine();
        }
        if (wrVar.m186c() != null) {
            bufferedWriter.append(f23632f).append('=').append((CharSequence) wrVar.m186c());
            bufferedWriter.newLine();
        }
        bufferedWriter.append(f23629c);
        bufferedWriter.newLine();
        if (wrVar.m184d() != null) {
            for (DatabaseFieldConfig spVar : wrVar.m184d()) {
                DatabaseFieldConfigLoader.m745a(bufferedWriter, spVar, wrVar.m186c());
            }
        }
        bufferedWriter.append(f23630d);
        bufferedWriter.newLine();
        bufferedWriter.append(f23628b);
        bufferedWriter.newLine();
    }

    /* renamed from: a */
    private static <T> void m179a(DatabaseTableConfig<T> wrVar, String str, String str2) {
        if (str.equals(f23631e)) {
            try {
                wrVar.m197a(Class.forName(str2));
            } catch (ClassNotFoundException unused) {
                throw new IllegalArgumentException("Unknown class specified for dataClass: " + str2);
            }
        } else if (str.equals(f23632f)) {
            wrVar.m196a(str2);
        }
    }

    /* renamed from: a */
    private static <T> void m181a(BufferedReader bufferedReader, DatabaseTableConfig<T> wrVar) throws SQLException {
        DatabaseFieldConfig a;
        ArrayList arrayList = new ArrayList();
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null || readLine.equals(f23630d) || (a = DatabaseFieldConfigLoader.m746a(bufferedReader)) == null) {
                    break;
                }
                arrayList.add(a);
            } catch (IOException e) {
                throw SqlExceptionUtil.m529a("Could not read next field from config file", e);
            }
        }
        wrVar.m194a(arrayList);
    }
}
