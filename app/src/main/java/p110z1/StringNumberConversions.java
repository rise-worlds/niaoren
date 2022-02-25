package p110z1;

@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000.\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0006\u001a\u001b\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0007¢\u0006\u0002\u0010\t\u001a\u0013\u0010\n\u001a\u0004\u0018\u00010\b*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u000b\u001a\u001b\u0010\n\u001a\u0004\u0018\u00010\b*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0007¢\u0006\u0002\u0010\f\u001a\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u000f\u001a\u001b\u0010\r\u001a\u0004\u0018\u00010\u000e*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0007¢\u0006\u0002\u0010\u0010\u001a\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0013\u001a\u001b\u0010\u0011\u001a\u0004\u0018\u00010\u0012*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0007¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, m8860e = {"numberFormatError", "", "input", "", "toByteOrNull", "", "(Ljava/lang/String;)Ljava/lang/Byte;", "radix", "", "(Ljava/lang/String;I)Ljava/lang/Byte;", "toIntOrNull", "(Ljava/lang/String;)Ljava/lang/Integer;", "(Ljava/lang/String;I)Ljava/lang/Integer;", "toLongOrNull", "", "(Ljava/lang/String;)Ljava/lang/Long;", "(Ljava/lang/String;I)Ljava/lang/Long;", "toShortOrNull", "", "(Ljava/lang/String;)Ljava/lang/Short;", "(Ljava/lang/String;I)Ljava/lang/Short;", "kotlin-stdlib"}, m8859f = "kotlin/text/StringsKt", m8857h = 1)
/* renamed from: z1.cps */
/* loaded from: classes3.dex */
class StringNumberConversions extends cpr {
    @bwy(m8750a = "1.1")
    @dbs
    /* renamed from: f */
    public static final Byte m4079f(@NotNull String str) {
        cji.m5162f(str, "$this$toByteOrNull");
        return cpl.m4083b(str, 10);
    }

    @bwy(m8750a = "1.1")
    @dbs
    /* renamed from: b */
    public static final Byte m4083b(@NotNull String str, int i) {
        int intValue;
        cji.m5162f(str, "$this$toByteOrNull");
        Integer d = cpl.m4081d(str, i);
        if (d == null || (intValue = d.intValue()) < -128 || intValue > 127) {
            return null;
        }
        return Byte.valueOf((byte) intValue);
    }

    @bwy(m8750a = "1.1")
    @dbs
    /* renamed from: g */
    public static final Short m4078g(@NotNull String str) {
        cji.m5162f(str, "$this$toShortOrNull");
        return cpl.m4082c(str, 10);
    }

    @bwy(m8750a = "1.1")
    @dbs
    /* renamed from: c */
    public static final Short m4082c(@NotNull String str, int i) {
        int intValue;
        cji.m5162f(str, "$this$toShortOrNull");
        Integer d = cpl.m4081d(str, i);
        if (d == null || (intValue = d.intValue()) < -32768 || intValue > 32767) {
            return null;
        }
        return Short.valueOf((short) intValue);
    }

    @bwy(m8750a = "1.1")
    @dbs
    /* renamed from: h */
    public static final Integer m4077h(@NotNull String str) {
        cji.m5162f(str, "$this$toIntOrNull");
        return cpl.m4081d(str, 10);
    }

    @bwy(m8750a = "1.1")
    @dbs
    /* renamed from: d */
    public static final Integer m4081d(@NotNull String str, int i) {
        boolean z;
        int i2;
        int i3;
        cji.m5162f(str, "$this$toIntOrNull");
        cov.m4246a(i);
        int length = str.length();
        if (length == 0) {
            return null;
        }
        int i4 = 0;
        char charAt = str.charAt(0);
        int i5 = -2147483647;
        if (charAt >= '0') {
            i2 = 0;
            z = false;
        } else if (length == 1) {
            return null;
        } else {
            if (charAt == '-') {
                i5 = Integer.MIN_VALUE;
                i2 = 1;
                z = true;
            } else if (charAt != '+') {
                return null;
            } else {
                i2 = 1;
                z = false;
            }
        }
        int i6 = i5 / i;
        int i7 = length - 1;
        if (i2 <= i7) {
            while (true) {
                int a = cov.m4247a(str.charAt(i2), i);
                if (a >= 0 && i4 >= i6 && (i3 = i4 * i) >= i5 + a) {
                    i4 = i3 - a;
                    if (i2 == i7) {
                        break;
                    }
                    i2++;
                } else {
                    return null;
                }
            }
        }
        return z ? Integer.valueOf(i4) : Integer.valueOf(-i4);
    }

    @bwy(m8750a = "1.1")
    @dbs
    /* renamed from: i */
    public static final Long m4076i(@NotNull String str) {
        cji.m5162f(str, "$this$toLongOrNull");
        return cpl.m4080e(str, 10);
    }

    @bwy(m8750a = "1.1")
    @dbs
    /* renamed from: e */
    public static final Long m4080e(@NotNull String str, int i) {
        boolean z;
        cji.m5162f(str, "$this$toLongOrNull");
        cov.m4246a(i);
        int length = str.length();
        Long l = null;
        if (length == 0) {
            return null;
        }
        int i2 = 0;
        char charAt = str.charAt(0);
        long j = -9223372036854775807L;
        if (charAt >= '0') {
            z = false;
        } else if (length == 1) {
            return null;
        } else {
            if (charAt == '-') {
                j = Long.MIN_VALUE;
                i2 = 1;
                z = true;
            } else if (charAt != '+') {
                return null;
            } else {
                i2 = 1;
                z = false;
            }
        }
        long j2 = i;
        long j3 = j / j2;
        long j4 = 0;
        int i3 = length - 1;
        if (i2 <= i3) {
            while (true) {
                int a = cov.m4247a(str.charAt(i2), i);
                if (a >= 0 && j4 >= j3) {
                    long j5 = j4 * j2;
                    long j6 = a;
                    if (j5 >= j + j6) {
                        l = null;
                        j4 = j5 - j6;
                        if (i2 == i3) {
                            break;
                        }
                        i2++;
                    } else {
                        return null;
                    }
                } else {
                    return l;
                }
            }
        }
        return z ? Long.valueOf(j4) : Long.valueOf(-j4);
    }

    @NotNull
    /* renamed from: j */
    public static final Void m4075j(@NotNull String str) {
        cji.m5162f(str, "input");
        throw new NumberFormatException("Invalid number format: '" + str + '\'');
    }
}
