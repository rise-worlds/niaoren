package p110z1;

import java.sql.SQLException;

/* renamed from: z1.sk */
/* loaded from: classes3.dex */
public abstract class BaseFieldConverter implements FieldConverter {
    @Override // p110z1.FieldConverter
    public boolean isStreamType() {
        return false;
    }

    @Override // p110z1.FieldConverter
    public Object javaToSqlArg(FieldType ssVar, Object obj) throws SQLException {
        return obj;
    }

    @Override // p110z1.FieldConverter
    public Object sqlArgToJava(FieldType ssVar, Object obj, int i) throws SQLException {
        return obj;
    }

    @Override // p110z1.FieldConverter
    public Object resultToJava(FieldType ssVar, DatabaseResults woVar, int i) throws SQLException {
        Object resultToSqlArg = resultToSqlArg(ssVar, woVar, i);
        if (resultToSqlArg == null) {
            return null;
        }
        return sqlArgToJava(ssVar, resultToSqlArg, i);
    }
}
