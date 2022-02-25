package p110z1;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* renamed from: z1.qv */
/* loaded from: classes3.dex */
public final class TimeTypeAdapter extends TypeAdapter<Time> {

    /* renamed from: a */
    public static final TypeAdapterFactory f22954a = new TypeAdapterFactory() { // from class: z1.qv.1
        @Override // p110z1.TypeAdapterFactory
        /* renamed from: a */
        public <T> TypeAdapter<T> mo1264a(Gson oxVar, TypeToken<T> rdVar) {
            if (rdVar.getRawType() == Time.class) {
                return new TimeTypeAdapter();
            }
            return null;
        }
    };

    /* renamed from: b */
    private final DateFormat f22955b = new SimpleDateFormat("hh:mm:ss a");

    /* renamed from: a */
    public synchronized Time mo1234b(JsonReader reVar) throws IOException {
        if (reVar.mo1205f() == JsonToken.NULL) {
            reVar.mo1201j();
            return null;
        }
        try {
            return new Time(this.f22955b.parse(reVar.mo1203h()).getTime());
        } catch (ParseException e) {
            throw new JsonSyntaxException(e);
        }
    }

    /* renamed from: a  reason: avoid collision after fix types in other method */
    public synchronized void mo1235a(JsonWriter rhVar, Time time) throws IOException {
        rhVar.mo1171b(time == null ? null : this.f22955b.format((Date) time));
    }
}
