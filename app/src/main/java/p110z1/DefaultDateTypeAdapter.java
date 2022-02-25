package p110z1;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.os */
/* loaded from: classes3.dex */
public final class DefaultDateTypeAdapter extends TypeAdapter<Date> {

    /* renamed from: a */
    private static final String f22728a = "DefaultDateTypeAdapter";

    /* renamed from: b */
    private final Class<? extends Date> f22729b;

    /* renamed from: c */
    private final List<DateFormat> f22730c;

    DefaultDateTypeAdapter(Class<? extends Date> cls) {
        this.f22730c = new ArrayList();
        this.f22729b = m1617a(cls);
        this.f22730c.add(DateFormat.getDateTimeInstance(2, 2, Locale.US));
        if (!Locale.getDefault().equals(Locale.US)) {
            this.f22730c.add(DateFormat.getDateTimeInstance(2, 2));
        }
        if (JavaVersion.m1376b()) {
            this.f22730c.add(PreJava9DateFormatProvider.m1355a(2, 2));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultDateTypeAdapter(Class<? extends Date> cls, String str) {
        this.f22730c = new ArrayList();
        this.f22729b = m1617a(cls);
        this.f22730c.add(new SimpleDateFormat(str, Locale.US));
        if (!Locale.getDefault().equals(Locale.US)) {
            this.f22730c.add(new SimpleDateFormat(str));
        }
    }

    DefaultDateTypeAdapter(Class<? extends Date> cls, int i) {
        this.f22730c = new ArrayList();
        this.f22729b = m1617a(cls);
        this.f22730c.add(DateFormat.getDateInstance(i, Locale.US));
        if (!Locale.getDefault().equals(Locale.US)) {
            this.f22730c.add(DateFormat.getDateInstance(i));
        }
        if (JavaVersion.m1376b()) {
            this.f22730c.add(PreJava9DateFormatProvider.m1356a(i));
        }
    }

    public DefaultDateTypeAdapter(int i, int i2) {
        this(Date.class, i, i2);
    }

    public DefaultDateTypeAdapter(Class<? extends Date> cls, int i, int i2) {
        this.f22730c = new ArrayList();
        this.f22729b = m1617a(cls);
        this.f22730c.add(DateFormat.getDateTimeInstance(i, i2, Locale.US));
        if (!Locale.getDefault().equals(Locale.US)) {
            this.f22730c.add(DateFormat.getDateTimeInstance(i, i2));
        }
        if (JavaVersion.m1376b()) {
            this.f22730c.add(PreJava9DateFormatProvider.m1355a(i, i2));
        }
    }

    /* renamed from: a */
    private static Class<? extends Date> m1617a(Class<? extends Date> cls) {
        if (cls == Date.class || cls == java.sql.Date.class || cls == Timestamp.class) {
            return cls;
        }
        throw new IllegalArgumentException("Date type must be one of " + Date.class + ", " + Timestamp.class + ", or " + java.sql.Date.class + " but was " + cls);
    }

    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void mo1235a(JsonWriter rhVar, Date date) throws IOException {
        if (date == null) {
            rhVar.mo1161f();
            return;
        }
        synchronized (this.f22730c) {
            rhVar.mo1171b(this.f22730c.get(0).format(date));
        }
    }

    /* renamed from: a */
    public Date mo1234b(JsonReader reVar) throws IOException {
        if (reVar.mo1205f() == JsonToken.NULL) {
            reVar.mo1201j();
            return null;
        }
        Date b = m1614b(reVar.mo1203h());
        Class<? extends Date> cls = this.f22729b;
        if (cls == Date.class) {
            return b;
        }
        if (cls == Timestamp.class) {
            return new Timestamp(b.getTime());
        }
        if (cls == java.sql.Date.class) {
            return new java.sql.Date(b.getTime());
        }
        throw new AssertionError();
    }

    /* renamed from: b */
    private Date m1614b(String str) {
        synchronized (this.f22730c) {
            for (DateFormat dateFormat : this.f22730c) {
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
    }

    public String toString() {
        DateFormat dateFormat = this.f22730c.get(0);
        if (dateFormat instanceof SimpleDateFormat) {
            return "DefaultDateTypeAdapter(" + ((SimpleDateFormat) dateFormat).toPattern() + ')';
        }
        return "DefaultDateTypeAdapter(" + dateFormat.getClass().getSimpleName() + ')';
    }
}
