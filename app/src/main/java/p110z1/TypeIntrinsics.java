package p110z1;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import p110z1.ckw;

/* renamed from: z1.ckn */
/* loaded from: classes3.dex */
public class TypeIntrinsics {
    /* renamed from: a */
    private static <T extends Throwable> T m5072a(T t) {
        return (T) cji.m5177a((Throwable) t, TypeIntrinsics.class.getName());
    }

    /* renamed from: a */
    public static void m5074a(Object obj, String str) {
        String name = obj == null ? "null" : obj.getClass().getName();
        m5073a(name + " cannot be cast to " + str);
    }

    /* renamed from: a */
    public static void m5073a(String str) {
        throw m5078a(new ClassCastException(str));
    }

    /* renamed from: a */
    public static ClassCastException m5078a(ClassCastException classCastException) {
        throw ((ClassCastException) m5072a(classCastException));
    }

    /* renamed from: a */
    public static boolean m5077a(Object obj) {
        return (obj instanceof Iterator) && (!(obj instanceof KMarkers) || (obj instanceof ckt));
    }

    /* renamed from: b */
    public static Iterator m5071b(Object obj) {
        if ((obj instanceof KMarkers) && !(obj instanceof ckt)) {
            m5074a(obj, "kotlin.collections.MutableIterator");
        }
        return m5068c(obj);
    }

    /* renamed from: b */
    public static Iterator m5069b(Object obj, String str) {
        if ((obj instanceof KMarkers) && !(obj instanceof ckt)) {
            m5073a(str);
        }
        return m5068c(obj);
    }

    /* renamed from: c */
    public static Iterator m5068c(Object obj) {
        try {
            return (Iterator) obj;
        } catch (ClassCastException e) {
            throw m5078a(e);
        }
    }

    /* renamed from: d */
    public static boolean m5066d(Object obj) {
        return (obj instanceof ListIterator) && (!(obj instanceof KMarkers) || (obj instanceof ckv));
    }

    /* renamed from: e */
    public static ListIterator m5064e(Object obj) {
        if ((obj instanceof KMarkers) && !(obj instanceof ckv)) {
            m5074a(obj, "kotlin.collections.MutableListIterator");
        }
        return m5062f(obj);
    }

    /* renamed from: c */
    public static ListIterator m5067c(Object obj, String str) {
        if ((obj instanceof KMarkers) && !(obj instanceof ckv)) {
            m5073a(str);
        }
        return m5062f(obj);
    }

    /* renamed from: f */
    public static ListIterator m5062f(Object obj) {
        try {
            return (ListIterator) obj;
        } catch (ClassCastException e) {
            throw m5078a(e);
        }
    }

    /* renamed from: g */
    public static boolean m5060g(Object obj) {
        return (obj instanceof Iterable) && (!(obj instanceof KMarkers) || (obj instanceof cks));
    }

    /* renamed from: h */
    public static Iterable m5058h(Object obj) {
        if ((obj instanceof KMarkers) && !(obj instanceof cks)) {
            m5074a(obj, "kotlin.collections.MutableIterable");
        }
        return m5056i(obj);
    }

    /* renamed from: d */
    public static Iterable m5065d(Object obj, String str) {
        if ((obj instanceof KMarkers) && !(obj instanceof cks)) {
            m5073a(str);
        }
        return m5056i(obj);
    }

    /* renamed from: i */
    public static Iterable m5056i(Object obj) {
        try {
            return (Iterable) obj;
        } catch (ClassCastException e) {
            throw m5078a(e);
        }
    }

    /* renamed from: j */
    public static boolean m5054j(Object obj) {
        return (obj instanceof Collection) && (!(obj instanceof KMarkers) || (obj instanceof ckr));
    }

    /* renamed from: k */
    public static Collection m5053k(Object obj) {
        if ((obj instanceof KMarkers) && !(obj instanceof ckr)) {
            m5074a(obj, "kotlin.collections.MutableCollection");
        }
        return m5052l(obj);
    }

    /* renamed from: e */
    public static Collection m5063e(Object obj, String str) {
        if ((obj instanceof KMarkers) && !(obj instanceof ckr)) {
            m5073a(str);
        }
        return m5052l(obj);
    }

    /* renamed from: l */
    public static Collection m5052l(Object obj) {
        try {
            return (Collection) obj;
        } catch (ClassCastException e) {
            throw m5078a(e);
        }
    }

    /* renamed from: m */
    public static boolean m5051m(Object obj) {
        return (obj instanceof List) && (!(obj instanceof KMarkers) || (obj instanceof AbstractC5628cku));
    }

    /* renamed from: n */
    public static List m5050n(Object obj) {
        if ((obj instanceof KMarkers) && !(obj instanceof AbstractC5628cku)) {
            m5074a(obj, "kotlin.collections.MutableList");
        }
        return m5049o(obj);
    }

    /* renamed from: f */
    public static List m5061f(Object obj, String str) {
        if ((obj instanceof KMarkers) && !(obj instanceof AbstractC5628cku)) {
            m5073a(str);
        }
        return m5049o(obj);
    }

    /* renamed from: o */
    public static List m5049o(Object obj) {
        try {
            return (List) obj;
        } catch (ClassCastException e) {
            throw m5078a(e);
        }
    }

    /* renamed from: p */
    public static boolean m5048p(Object obj) {
        return (obj instanceof Set) && (!(obj instanceof KMarkers) || (obj instanceof ckx));
    }

    /* renamed from: q */
    public static Set m5047q(Object obj) {
        if ((obj instanceof KMarkers) && !(obj instanceof ckx)) {
            m5074a(obj, "kotlin.collections.MutableSet");
        }
        return m5046r(obj);
    }

    /* renamed from: g */
    public static Set m5059g(Object obj, String str) {
        if ((obj instanceof KMarkers) && !(obj instanceof ckx)) {
            m5073a(str);
        }
        return m5046r(obj);
    }

    /* renamed from: r */
    public static Set m5046r(Object obj) {
        try {
            return (Set) obj;
        } catch (ClassCastException e) {
            throw m5078a(e);
        }
    }

    /* renamed from: s */
    public static boolean m5045s(Object obj) {
        return (obj instanceof Map) && (!(obj instanceof KMarkers) || (obj instanceof ckw));
    }

    /* renamed from: t */
    public static Map m5044t(Object obj) {
        if ((obj instanceof KMarkers) && !(obj instanceof ckw)) {
            m5074a(obj, "kotlin.collections.MutableMap");
        }
        return m5043u(obj);
    }

    /* renamed from: h */
    public static Map m5057h(Object obj, String str) {
        if ((obj instanceof KMarkers) && !(obj instanceof ckw)) {
            m5073a(str);
        }
        return m5043u(obj);
    }

    /* renamed from: u */
    public static Map m5043u(Object obj) {
        try {
            return (Map) obj;
        } catch (ClassCastException e) {
            throw m5078a(e);
        }
    }

    /* renamed from: v */
    public static boolean m5042v(Object obj) {
        return (obj instanceof Map.Entry) && (!(obj instanceof KMarkers) || (obj instanceof ckw.AbstractC4975a));
    }

    /* renamed from: w */
    public static Map.Entry m5041w(Object obj) {
        if ((obj instanceof KMarkers) && !(obj instanceof ckw.AbstractC4975a)) {
            m5074a(obj, "kotlin.collections.MutableMap.MutableEntry");
        }
        return m5040x(obj);
    }

    /* renamed from: i */
    public static Map.Entry m5055i(Object obj, String str) {
        if ((obj instanceof KMarkers) && !(obj instanceof ckw.AbstractC4975a)) {
            m5073a(str);
        }
        return m5040x(obj);
    }

    /* renamed from: x */
    public static Map.Entry m5040x(Object obj) {
        try {
            return (Map.Entry) obj;
        } catch (ClassCastException e) {
            throw m5078a(e);
        }
    }

    /* renamed from: y */
    public static int m5039y(Object obj) {
        if (obj instanceof FunctionBase) {
            return ((FunctionBase) obj).getArity();
        }
        if (obj instanceof chc) {
            return 0;
        }
        if (obj instanceof chd) {
            return 1;
        }
        if (obj instanceof cho) {
            return 2;
        }
        if (obj instanceof chs) {
            return 3;
        }
        if (obj instanceof cht) {
            return 4;
        }
        if (obj instanceof chu) {
            return 5;
        }
        if (obj instanceof chv) {
            return 6;
        }
        if (obj instanceof chw) {
            return 7;
        }
        if (obj instanceof chx) {
            return 8;
        }
        if (obj instanceof chy) {
            return 9;
        }
        if (obj instanceof che) {
            return 10;
        }
        if (obj instanceof chf) {
            return 11;
        }
        if (obj instanceof chg) {
            return 12;
        }
        if (obj instanceof chh) {
            return 13;
        }
        if (obj instanceof chi) {
            return 14;
        }
        if (obj instanceof chj) {
            return 15;
        }
        if (obj instanceof chk) {
            return 16;
        }
        if (obj instanceof chl) {
            return 17;
        }
        if (obj instanceof chm) {
            return 18;
        }
        if (obj instanceof chn) {
            return 19;
        }
        if (obj instanceof chp) {
            return 20;
        }
        if (obj instanceof chq) {
            return 21;
        }
        return obj instanceof chr ? 22 : -1;
    }

    /* renamed from: a */
    public static boolean m5076a(Object obj, int i) {
        return (obj instanceof bvt) && m5039y(obj) == i;
    }

    /* renamed from: b */
    public static Object m5070b(Object obj, int i) {
        if (obj != null && !m5076a(obj, i)) {
            m5074a(obj, "kotlin.jvm.functions.Function" + i);
        }
        return obj;
    }

    /* renamed from: a */
    public static Object m5075a(Object obj, int i, String str) {
        if (obj != null && !m5076a(obj, i)) {
            m5073a(str);
        }
        return obj;
    }
}
