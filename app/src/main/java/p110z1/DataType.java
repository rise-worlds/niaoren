package p110z1;

/* renamed from: z1.sn */
/* loaded from: classes3.dex */
public enum DataType {
    STRING(StringType.m632b()),
    LONG_STRING(LongStringType.m641a()),
    STRING_BYTES(StringBytesType.m634a()),
    BOOLEAN(BooleanType.m663b()),
    BOOLEAN_OBJ(BooleanObjectType.m664a()),
    DATE(DateType.m651a()),
    DATE_LONG(DateLongType.m657a()),
    DATE_STRING(DateStringType.m656a()),
    CHAR(CharType.m659a()),
    CHAR_OBJ(CharacterObjectType.m658b()),
    BYTE(ByteType.m660b()),
    BYTE_ARRAY(ByteArrayType.m662a()),
    BYTE_OBJ(ByteObjectType.m661a()),
    SHORT(ShortType.m637b()),
    SHORT_OBJ(ShortObjectType.m638a()),
    INTEGER(IntType.m644a()),
    INTEGER_OBJ(IntegerObjectType.m643b()),
    LONG(LongType.m640b()),
    LONG_OBJ(LongObjectType.m642a()),
    FLOAT(FloatType.m645b()),
    FLOAT_OBJ(FloatObjectType.m646a()),
    DOUBLE(DoubleType.m649b()),
    DOUBLE_OBJ(DoubleObjectType.m650a()),
    SERIALIZABLE(SerializableType.m639a()),
    ENUM_STRING(EnumStringType.m647a()),
    ENUM_INTEGER(EnumIntegerType.m648a()),
    UUID(UuidType.m630a()),
    BIG_INTEGER(BigIntegerType.m665a()),
    BIG_DECIMAL(BigDecimalStringType.m672a()),
    BIG_DECIMAL_NUMERIC(BigDecimalNumericType.m673a()),
    DATE_TIME(DateTimeType.m655a()),
    SQL_DATE(SqlDateType.m635c()),
    TIME_STAMP(TimeStampType.m631c()),
    UNKNOWN(null);
    
    private final DataPersister dataPersister;

    DataType(DataPersister slVar) {
        this.dataPersister = slVar;
    }

    public DataPersister getDataPersister() {
        return this.dataPersister;
    }
}
