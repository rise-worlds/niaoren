package p110z1;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* renamed from: z1.qn */
/* loaded from: classes3.dex */
public final class DateTypeAdapter extends TypeAdapter<Date> {

    /* renamed from: a */
    public static final TypeAdapterFactory f22912a = new TypeAdapterFactory() { // from class: z1.qn.1
        @Override // p110z1.TypeAdapterFactory
        /* renamed from: a */
        public <T> TypeAdapter<T> mo1264a(Gson oxVar, TypeToken<T> rdVar) {
            if (rdVar.getRawType() == Date.class) {
                return new DateTypeAdapter();
            }
            return null;
        }
    };

    /* renamed from: b */
    private final List<DateFormat> f22913b = new ArrayList();

    public DateTypeAdapter() {
        this.f22913b.add(DateFormat.getDateTimeInstance(2, 2, Locale.US));
        if (!Locale.getDefault().equals(Locale.US)) {
            this.f22913b.add(DateFormat.getDateTimeInstance(2, 2));
        }
        if (JavaVersion.m1376b()) {
            this.f22913b.add(PreJava9DateFormatProvider.m1355a(2, 2));
        }
    }

    /* renamed from: a */
    public Date mo1234b(JsonReader reVar) throws IOException {
        if (reVar.mo1205f() != JsonToken.NULL) {
            return m1336b(reVar.mo1203h());
        }
        reVar.mo1201j();
        return null;
    }

    /* renamed from: b */
    private synchronized Date m1336b(String str) {
        for (DateFormat dateFormat : this.f22913b) {
            try {
                return dateFormat.parse(str);
            } catch (ParseException unused) {
            }
        }
        try {
            return ISO8601Utils.m1230a(str, new ParsePosition(0));
        } catch (ParseException e) {
            throw new JsonSyntaxException(str, e);
        }
    }

    /* renamed from: a  reason: avoid collision after fix types in other method */
    public synchronized void mo1235a(JsonWriter rhVar, Date date) throws IOException {
        if (date == null) {
            rhVar.mo1161f();
        } else {
            rhVar.mo1171b(this.f22913b.get(0).format(date));
        }
    }
}
