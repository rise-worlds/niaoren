package p110z1;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* renamed from: z1.qu */
/* loaded from: classes3.dex */
public final class SqlDateTypeAdapter extends TypeAdapter<Date> {

    /* renamed from: a */
    public static final TypeAdapterFactory f22952a = new TypeAdapterFactory() { // from class: z1.qu.1
        @Override // p110z1.TypeAdapterFactory
        /* renamed from: a */
        public <T> TypeAdapter<T> mo1264a(Gson oxVar, TypeToken<T> rdVar) {
            if (rdVar.getRawType() == Date.class) {
                return new SqlDateTypeAdapter();
            }
            return null;
        }
    };

    /* renamed from: b */
    private final DateFormat f22953b = new SimpleDateFormat("MMM d, yyyy");

    /* renamed from: a */
    public synchronized Date mo1234b(JsonReader reVar) throws IOException {
        if (reVar.mo1205f() == JsonToken.NULL) {
            reVar.mo1201j();
            return null;
        }
        try {
            return new Date(this.f22953b.parse(reVar.mo1203h()).getTime());
        } catch (ParseException e) {
            throw new JsonSyntaxException(e);
        }
    }

    /* renamed from: a  reason: avoid collision after fix types in other method */
    public synchronized void mo1235a(JsonWriter rhVar, Date date) throws IOException {
        rhVar.mo1171b(date == null ? null : this.f22953b.format((java.util.Date) date));
    }
}
