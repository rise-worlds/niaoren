package p110z1;

import java.sql.SQLException;
import java.util.Iterator;

/* renamed from: z1.rs */
/* loaded from: classes3.dex */
public interface CloseableIterator<T> extends Iterator<T> {
    /* renamed from: a */
    T mo446a(int i) throws SQLException;

    /* renamed from: a */
    void mo447a() throws SQLException;

    /* renamed from: b */
    void mo445b();

    /* renamed from: c */
    DatabaseResults mo444c();

    /* renamed from: d */
    void mo443d();

    /* renamed from: e */
    T mo442e() throws SQLException;

    /* renamed from: f */
    T mo441f() throws SQLException;

    /* renamed from: g */
    T mo440g() throws SQLException;

    /* renamed from: h */
    T mo439h() throws SQLException;
}
