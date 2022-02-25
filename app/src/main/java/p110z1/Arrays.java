package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000H\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a1\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\u0001¢\u0006\u0004\b\u0005\u0010\u0006\u001a!\u0010\u0007\u001a\u00020\b\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\u0001¢\u0006\u0004\b\t\u0010\n\u001a?\u0010\u000b\u001a\u00020\f\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\n\u0010\r\u001a\u00060\u000ej\u0002`\u000f2\u0010\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u0011H\u0002¢\u0006\u0004\b\u0012\u0010\u0013\u001a+\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0015\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00030\u0003¢\u0006\u0002\u0010\u0016\u001a8\u0010\u0017\u001a\u0002H\u0018\"\u0010\b\u0000\u0010\u0019*\u0006\u0012\u0002\b\u00030\u0003*\u0002H\u0018\"\u0004\b\u0001\u0010\u0018*\u0002H\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001bH\u0087\b¢\u0006\u0002\u0010\u001c\u001a)\u0010\u001d\u001a\u00020\u0001*\b\u0012\u0002\b\u0003\u0018\u00010\u0003H\u0087\b\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000¢\u0006\u0002\u0010\u001e\u001aG\u0010\u001f\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00180\u00150 \"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0018*\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00180 0\u0003¢\u0006\u0002\u0010!¨\u0006\""}, m8860e = {"contentDeepEqualsImpl", "", TessBaseAPI.f9204e, "", "other", "contentDeepEquals", "([Ljava/lang/Object;[Ljava/lang/Object;)Z", "contentDeepToStringImpl", "", "contentDeepToString", "([Ljava/lang/Object;)Ljava/lang/String;", "contentDeepToStringInternal", "", C4985cm.f20833c, "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "processed", "", "contentDeepToStringInternal$ArraysKt__ArraysKt", "([Ljava/lang/Object;Ljava/lang/StringBuilder;Ljava/util/List;)V", "flatten", "", "([[Ljava/lang/Object;)Ljava/util/List;", "ifEmpty", "R", "C", "defaultValue", "Lkotlin/Function0;", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "isNullOrEmpty", "([Ljava/lang/Object;)Z", "unzip", "Lkotlin/Pair;", "([Lkotlin/Pair;)Lkotlin/Pair;", "kotlin-stdlib"}, m8859f = "kotlin/collections/ArraysKt", m8857h = 1)
/* renamed from: z1.bzd */
/* loaded from: classes3.dex */
public class Arrays extends ArraysJVM {
    @NotNull
    /* renamed from: a */
    public static final <T> List<T> m8277a(@NotNull T[][] tArr) {
        cji.m5162f(tArr, "$this$flatten");
        int i = 0;
        for (T[] tArr2 : tArr) {
            i += tArr2.length;
        }
        ArrayList arrayList = new ArrayList(i);
        for (T[] tArr3 : tArr) {
            bzk.m6756a((Collection) arrayList, (Object[]) tArr3);
        }
        return arrayList;
    }

    @NotNull
    /* renamed from: a */
    public static final <T, R> Tuples<List<T>, List<R>> m8278a(@NotNull Tuples<? extends T, ? extends R>[] bwoVarArr) {
        cji.m5162f(bwoVarArr, "$this$unzip");
        ArrayList arrayList = new ArrayList(bwoVarArr.length);
        ArrayList arrayList2 = new ArrayList(bwoVarArr.length);
        for (Tuples<? extends T, ? extends R> bwoVar : bwoVarArr) {
            arrayList.add(bwoVar.getFirst());
            arrayList2.add(bwoVar.getSecond());
        }
        return bxh.m8730a(arrayList, arrayList2);
    }

    @bwy(m8750a = "1.3")
    @cey
    /* renamed from: d */
    private static final boolean m8275d(@dbs Object[] objArr) {
        if (objArr != null) {
            if (!(objArr.length == 0)) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Incorrect types in method signature: <C:[Ljava/lang/Object;:TR;R:Ljava/lang/Object;>(TC;Lz1/chc<+TR;>;)TR; */
    @bwy(m8750a = "1.3")
    @cey
    /* renamed from: a */
    private static final Object m8280a(Object[] objArr, chc chcVar) {
        return objArr.length == 0 ? chcVar.invoke() : objArr;
    }

    @bwy(m8750a = "1.3")
    @bwt
    @cgo(m5270a = "contentDeepEquals")
    /* renamed from: a */
    public static final <T> boolean m8279a(@NotNull T[] tArr, @NotNull T[] tArr2) {
        cji.m5162f(tArr, "$this$contentDeepEqualsImpl");
        cji.m5162f(tArr2, "other");
        if (tArr == tArr2) {
            return true;
        }
        if (tArr.length != tArr2.length) {
            return false;
        }
        int length = tArr.length;
        for (int i = 0; i < length; i++) {
            T t = tArr[i];
            T t2 = tArr2[i];
            if (t != t2) {
                if (t == null || t2 == null) {
                    return false;
                }
                if (!(t instanceof Object[]) || !(t2 instanceof Object[])) {
                    if (!(t instanceof byte[]) || !(t2 instanceof byte[])) {
                        if (!(t instanceof short[]) || !(t2 instanceof short[])) {
                            if (!(t instanceof int[]) || !(t2 instanceof int[])) {
                                if (!(t instanceof long[]) || !(t2 instanceof long[])) {
                                    if (!(t instanceof float[]) || !(t2 instanceof float[])) {
                                        if (!(t instanceof double[]) || !(t2 instanceof double[])) {
                                            if (!(t instanceof char[]) || !(t2 instanceof char[])) {
                                                if (!(t instanceof boolean[]) || !(t2 instanceof boolean[])) {
                                                    if (!(t instanceof UByteArray) || !(t2 instanceof UByteArray)) {
                                                        if (!(t instanceof UShortArray) || !(t2 instanceof UShortArray)) {
                                                            if (!(t instanceof UIntArray) || !(t2 instanceof UIntArray)) {
                                                                if (!(t instanceof ULongArray) || !(t2 instanceof ULongArray)) {
                                                                    if (!cji.m5184a(t, t2)) {
                                                                        return false;
                                                                    }
                                                                } else if (!cbo.m6111a(((ULongArray) t).m8489d(), ((ULongArray) t2).m8489d())) {
                                                                    return false;
                                                                }
                                                            } else if (!cbo.m6137a(((UIntArray) t).m8569d(), ((UIntArray) t2).m8569d())) {
                                                                return false;
                                                            }
                                                        } else if (!cbo.m6079a(((UShortArray) t).m8383d(), ((UShortArray) t2).m8383d())) {
                                                            return false;
                                                        }
                                                    } else if (!cbo.m6162a(((UByteArray) t).m8647d(), ((UByteArray) t2).m8647d())) {
                                                        return false;
                                                    }
                                                } else if (!java.util.Arrays.equals((boolean[]) t, (boolean[]) t2)) {
                                                    return false;
                                                }
                                            } else if (!java.util.Arrays.equals((char[]) t, (char[]) t2)) {
                                                return false;
                                            }
                                        } else if (!java.util.Arrays.equals((double[]) t, (double[]) t2)) {
                                            return false;
                                        }
                                    } else if (!java.util.Arrays.equals((float[]) t, (float[]) t2)) {
                                        return false;
                                    }
                                } else if (!java.util.Arrays.equals((long[]) t, (long[]) t2)) {
                                    return false;
                                }
                            } else if (!java.util.Arrays.equals((int[]) t, (int[]) t2)) {
                                return false;
                            }
                        } else if (!java.util.Arrays.equals((short[]) t, (short[]) t2)) {
                            return false;
                        }
                    } else if (!java.util.Arrays.equals((byte[]) t, (byte[]) t2)) {
                        return false;
                    }
                } else if (!bzb.m8279a((Object[]) t, (Object[]) t2)) {
                    return false;
                }
            }
        }
        return true;
    }

    @bwy(m8750a = "1.3")
    @bwt
    @cgo(m5270a = "contentDeepToString")
    @NotNull
    /* renamed from: c */
    public static final <T> String m8276c(@NotNull T[] tArr) {
        cji.m5162f(tArr, "$this$contentDeepToStringImpl");
        StringBuilder sb = new StringBuilder((cmi.m4701d(tArr.length, 429496729) * 5) + 2);
        m8281a(tArr, sb, new ArrayList());
        String sb2 = sb.toString();
        cji.m5175b(sb2, "StringBuilder(capacity).…builderAction).toString()");
        return sb2;
    }

    /* renamed from: a */
    private static final <T> void m8281a(@NotNull T[] tArr, StringBuilder sb, List<Object[]> list) {
        if (list.contains(tArr)) {
            sb.append("[...]");
            return;
        }
        list.add(tArr);
        sb.append('[');
        int length = tArr.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            T t = tArr[i];
            if (t == null) {
                sb.append("null");
            } else if (t instanceof Object[]) {
                m8281a((Object[]) t, sb, list);
            } else if (t instanceof byte[]) {
                String arrays = java.util.Arrays.toString((byte[]) t);
                cji.m5175b(arrays, "java.util.Arrays.toString(this)");
                sb.append(arrays);
            } else if (t instanceof short[]) {
                String arrays2 = java.util.Arrays.toString((short[]) t);
                cji.m5175b(arrays2, "java.util.Arrays.toString(this)");
                sb.append(arrays2);
            } else if (t instanceof int[]) {
                String arrays3 = java.util.Arrays.toString((int[]) t);
                cji.m5175b(arrays3, "java.util.Arrays.toString(this)");
                sb.append(arrays3);
            } else if (t instanceof long[]) {
                String arrays4 = java.util.Arrays.toString((long[]) t);
                cji.m5175b(arrays4, "java.util.Arrays.toString(this)");
                sb.append(arrays4);
            } else if (t instanceof float[]) {
                String arrays5 = java.util.Arrays.toString((float[]) t);
                cji.m5175b(arrays5, "java.util.Arrays.toString(this)");
                sb.append(arrays5);
            } else if (t instanceof double[]) {
                String arrays6 = java.util.Arrays.toString((double[]) t);
                cji.m5175b(arrays6, "java.util.Arrays.toString(this)");
                sb.append(arrays6);
            } else if (t instanceof char[]) {
                String arrays7 = java.util.Arrays.toString((char[]) t);
                cji.m5175b(arrays7, "java.util.Arrays.toString(this)");
                sb.append(arrays7);
            } else if (t instanceof boolean[]) {
                String arrays8 = java.util.Arrays.toString((boolean[]) t);
                cji.m5175b(arrays8, "java.util.Arrays.toString(this)");
                sb.append(arrays8);
            } else if (t instanceof UByteArray) {
                sb.append(cbo.m5878l(((UByteArray) t).m8647d()));
            } else if (t instanceof UShortArray) {
                sb.append(cbo.m5872l(((UShortArray) t).m8383d()));
            } else if (t instanceof UIntArray) {
                sb.append(cbo.m5876l(((UIntArray) t).m8569d()));
            } else if (t instanceof ULongArray) {
                sb.append(cbo.m5874l(((ULongArray) t).m8489d()));
            } else {
                sb.append(t.toString());
            }
        }
        sb.append(']');
        list.remove(bzk.m6810a((List) list));
    }
}
