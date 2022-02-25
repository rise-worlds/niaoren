package p110z1;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

/* renamed from: z1.aqe */
/* loaded from: classes3.dex */
public final class RegexUtil {

    /* renamed from: A */
    public static final String f17293A = "^[A-Za-z0-9]+$";

    /* renamed from: B */
    public static final String f17294B = "^[a-zA-Z0-9_]+$";

    /* renamed from: C */
    public static final String f17295C = "[\\\\%,*$<>]";

    /* renamed from: D */
    public static final String f17296D = "[u4e00-u9fa5]";

    /* renamed from: E */
    public static final String f17297E = "^[一-龥_a-zA-Z0-9]+$";

    /* renamed from: F */
    public static final String f17298F = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /* renamed from: G */
    public static final String f17299G = "^[\\@A-Za-z0-9\\!\\#\\$\\%\\^\\&\\*\\.\\~]{6,12}$";

    /* renamed from: a */
    public static final String f17300a = "^[a-zA-Z0-9\\!\\@\\#\\￥\\%\\^\\&\\*\\(\\)\\_\\=\\+\\-\\?\\~\\.]+$";

    /* renamed from: b */
    static final Set<String> f17301b = new TreeSet();

    /* renamed from: c */
    public static HashMap<String, String> f17302c = new HashMap<>();

    /* renamed from: d */
    public static final String f17303d = "^(/{0,1}\\w){1,}\\.(gif|dmp|png|jpg)$|^\\w{1,}\\.(gif|dmp|png|jpg)$";

    /* renamed from: e */
    public static final String f17304e = "(?:\\w[-._\\w]*\\w@\\w[-._\\w]*\\w\\.\\w{2,3}$)";

    /* renamed from: f */
    public static final String f17305f = "(\\w+)://([^/:]+)(:\\d*)?([^#\\s]*)";

    /* renamed from: g */
    public static final String f17306g = "(http|https|ftp)://([^/:]+)(:\\d*)?([^#\\s]*)";

    /* renamed from: h */
    public static final String f17307h = "^((((19){1}|(20){1})d{2})|d{2})[-\\s]{1}[01]{1}d{1}[-\\s]{1}[0-3]{1}d{1}$";

    /* renamed from: i */
    public static final String f17308i = "^(?:0[0-9]{2,3}[-\\s]{1}|\\(0[0-9]{2,4}\\))[0-9]{6,8}$|^[1-9]{1}[0-9]{5,7}$|^[1-9]{1}[0-9]{10}$";

    /* renamed from: j */
    public static final String f17309j = "^\\d{10}|\\d{13}|\\d{15}|\\d{18}$";

    /* renamed from: k */
    public static final String f17310k = "^[0-9]{6}$";

    /* renamed from: l */
    public static final String f17311l = "^[^'\"\\;,:-<>\\s].+$";

    /* renamed from: m */
    public static final String f17312m = "^\\d+$";

    /* renamed from: n */
    public static final String f17313n = "^[1-9]+\\d*$";

    /* renamed from: o */
    public static final String f17314o = "^[0-9]*[1-9][0-9]*$";

    /* renamed from: p */
    public static final String f17315p = "^((-\\d+)|(0+))$";

    /* renamed from: q */
    public static final String f17316q = "^-[0-9]*[1-9][0-9]*$";

    /* renamed from: r */
    public static final String f17317r = "^-?\\d+$";

    /* renamed from: s */
    public static final String f17318s = "^\\d+(\\.\\d+)?$";

    /* renamed from: t */
    public static final String f17319t = "^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$";

    /* renamed from: u */
    public static final String f17320u = "^((-\\d+(\\.\\d+)?)|(0+(\\.0+)?))$";

    /* renamed from: v */
    public static final String f17321v = "^(-(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*)))$";

    /* renamed from: w */
    public static final String f17322w = "^(-?\\d+)(\\.\\d+)?$";

    /* renamed from: x */
    public static final String f17323x = "^[A-Za-z]+$";

    /* renamed from: y */
    public static final String f17324y = "^[A-Z]+$";

    /* renamed from: z */
    public static final String f17325z = "^[a-z]+$";

    private RegexUtil() {
        f17301b.add("(");
        f17301b.add(")");
        f17301b.add("[");
        f17301b.add("]");
        f17301b.add("{");
        f17301b.add(C4963cj.f20747d);
        f17301b.add(SimpleComparison.f23612f);
        f17301b.add(SimpleComparison.f23610d);
    }

    /* renamed from: a */
    public static RegexUtil m11597a() {
        return new RegexUtil();
    }

    /* renamed from: a */
    public void m11594a(String str, String str2) {
        f17302c.put(str, str2);
    }

    /* renamed from: a */
    public String m11596a(String str) {
        return f17302c.get(str) != null ? f17302c.get(str) : "";
    }

    /* renamed from: b */
    public void m11593b() {
        f17302c.clear();
    }

    /* renamed from: b */
    public static boolean m11592b(String str, String str2) {
        return Pattern.compile(str).matcher(str2).matches();
    }

    /* renamed from: a */
    public static Set<String> m11595a(String str, char c, char c2) {
        TreeSet treeSet = new TreeSet();
        if (str == null || str.equals("")) {
            return treeSet;
        }
        String[] split = str.split("(\\" + c + ")");
        for (int i = 1; i < split.length; i++) {
            treeSet.add(split[i].substring(0, split[i].indexOf(c2)));
        }
        return treeSet;
    }
}
