package p110z1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Arrays;

/* renamed from: z1.tx */
/* loaded from: classes3.dex */
public class SerializableType extends BaseDataType {

    /* renamed from: a */
    private static final SerializableType f23420a = new SerializableType();

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isAppropriateId() {
        return false;
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isArgumentHolderRequired() {
        return true;
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isComparable() {
        return false;
    }

    @Override // p110z1.BaseFieldConverter, p110z1.FieldConverter
    public boolean isStreamType() {
        return true;
    }

    /* renamed from: a */
    public static SerializableType m639a() {
        return f23420a;
    }

    private SerializableType() {
        super(SqlType.SERIALIZABLE, new Class[0]);
    }

    protected SerializableType(SqlType suVar, Class<?>[] clsArr) {
        super(suVar, clsArr);
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object parseDefaultString(FieldType ssVar, String str) throws SQLException {
        throw new SQLException("Default values for serializable types are not supported");
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object resultToSqlArg(FieldType ssVar, DatabaseResults woVar, int i) throws SQLException {
        return woVar.mo214g(i);
    }

    @Override // p110z1.BaseFieldConverter, p110z1.FieldConverter
    public Object sqlArgToJava(FieldType ssVar, Object obj, int i) throws SQLException {
        Throwable th;
        Exception e;
        ObjectInputStream objectInputStream;
        byte[] bArr = (byte[]) obj;
        ObjectInputStream objectInputStream2 = null;
        try {
            try {
                objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bArr));
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            Object readObject = objectInputStream.readObject();
            try {
                objectInputStream.close();
            } catch (IOException unused) {
            }
            return readObject;
        } catch (Exception e3) {
            e = e3;
            objectInputStream2 = objectInputStream;
            throw SqlExceptionUtil.m529a("Could not read serialized object from byte array: " + Arrays.toString(bArr) + "(len " + bArr.length + ")", e);
        } catch (Throwable th3) {
            th = th3;
            objectInputStream2 = objectInputStream;
            if (objectInputStream2 != null) {
                try {
                    objectInputStream2.close();
                } catch (IOException unused2) {
                }
            }
            throw th;
        }
    }

    @Override // p110z1.BaseFieldConverter, p110z1.FieldConverter
    public Object javaToSqlArg(FieldType ssVar, Object obj) throws SQLException {
        Throwable th;
        Exception e;
        ByteArrayOutputStream byteArrayOutputStream;
        ObjectOutputStream objectOutputStream;
        ObjectOutputStream objectOutputStream2 = null;
        try {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            objectOutputStream.writeObject(obj);
            objectOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e3) {
            e = e3;
            objectOutputStream2 = objectOutputStream;
            throw SqlExceptionUtil.m529a("Could not write serialized object to byte array: " + obj, e);
        } catch (Throwable th3) {
            th = th3;
            objectOutputStream2 = objectOutputStream;
            if (objectOutputStream2 != null) {
                try {
                    objectOutputStream2.close();
                } catch (IOException unused) {
                }
            }
            throw th;
        }
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isValidForField(Field field) {
        return Serializable.class.isAssignableFrom(field.getType());
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object resultStringToJava(FieldType ssVar, String str, int i) throws SQLException {
        throw new SQLException("Serializable type cannot be converted from string to Java");
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public Class<?> getPrimaryClass() {
        return Serializable.class;
    }
}
