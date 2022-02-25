package p110z1;

import java.sql.SQLException;

/* renamed from: z1.sr */
/* loaded from: classes3.dex */
public interface FieldConverter {
    SqlType getSqlType();

    boolean isStreamType();

    Object javaToSqlArg(FieldType ssVar, Object obj) throws SQLException;

    Object parseDefaultString(FieldType ssVar, String str) throws SQLException;

    Object resultStringToJava(FieldType ssVar, String str, int i) throws SQLException;

    Object resultToJava(FieldType ssVar, DatabaseResults woVar, int i) throws SQLException;

    Object resultToSqlArg(FieldType ssVar, DatabaseResults woVar, int i) throws SQLException;

    Object sqlArgToJava(FieldType ssVar, Object obj, int i) throws SQLException;
}
