package p110z1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: z1.hg */
/* loaded from: classes3.dex */
public final class GeoResultParser extends ResultParser {

    /* renamed from: a */
    private static final Pattern f21875a = Pattern.compile("geo:([\\-0-9.]+),([\\-0-9.]+)(?:,([\\-0-9.]+))?(?:\\?(.*))?", 2);

    @Override // p110z1.ResultParser
    /* renamed from: a */
    public final /* synthetic */ ParsedResult mo2567a(C5422ol olVar) {
        return m2600c(olVar);
    }

    /* renamed from: c */
    private static GeoParsedResult m2600c(C5422ol olVar) {
        Matcher matcher = f21875a.matcher(m2579b(olVar));
        if (!matcher.matches()) {
            return null;
        }
        String group = matcher.group(4);
        try {
            double parseDouble = Double.parseDouble(matcher.group(1));
            if (parseDouble <= 90.0d && parseDouble >= -90.0d) {
                double parseDouble2 = Double.parseDouble(matcher.group(2));
                if (parseDouble2 <= 180.0d && parseDouble2 >= -180.0d) {
                    double d = 0.0d;
                    if (matcher.group(3) != null) {
                        double parseDouble3 = Double.parseDouble(matcher.group(3));
                        if (parseDouble3 < 0.0d) {
                            return null;
                        }
                        d = parseDouble3;
                    }
                    return new GeoParsedResult(parseDouble, parseDouble2, d, group);
                }
                return null;
            }
            return null;
        } catch (NumberFormatException unused) {
            return null;
        }
    }
}
