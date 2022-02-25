package p110z1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.SparseArray;
import com.liulishuo.filedownloader.model.ConnectionModel;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p110z1.FileDownloadDatabase;
import p110z1.FileDownloadHelper;

/* renamed from: z1.ago */
/* loaded from: classes3.dex */
public class SqliteDatabaseImpl implements FileDownloadDatabase {

    /* renamed from: a */
    public static final String f15655a = "filedownloader";

    /* renamed from: b */
    public static final String f15656b = "filedownloaderConnection";

    /* renamed from: c */
    private final SQLiteDatabase f15657c = new SqliteDatabaseOpenHelper(FileDownloadHelper.m13229a()).getWritableDatabase();

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: a */
    public void mo13468a(int i) {
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: f */
    public void mo13444f(int i) {
    }

    /* renamed from: c */
    public static C3451c m13449c() {
        return new C3451c();
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: b */
    public FileDownloadModel mo13453b(int i) {
        Throwable th;
        Cursor cursor = null;
        try {
            Cursor rawQuery = this.f15657c.rawQuery(FileDownloadUtils.m13182a("SELECT * FROM %s WHERE %s = ?", f15655a, "_id"), new String[]{Integer.toString(i)});
            try {
                if (rawQuery.moveToNext()) {
                    FileDownloadModel b = m13451b(rawQuery);
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    return b;
                }
                if (rawQuery != null) {
                    rawQuery.close();
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                cursor = rawQuery;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: c */
    public List<ConnectionModel> mo13448c(int i) {
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            cursor = this.f15657c.rawQuery(FileDownloadUtils.m13182a("SELECT * FROM %s WHERE %s = ?", f15656b, ConnectionModel.f10389a), new String[]{Integer.toString(i)});
            while (cursor.moveToNext()) {
                ConnectionModel aVar = new ConnectionModel();
                aVar.m19092a(i);
                aVar.m19088b(cursor.getInt(cursor.getColumnIndex(ConnectionModel.f10390b)));
                aVar.m19091a(cursor.getLong(cursor.getColumnIndex(ConnectionModel.f10391c)));
                aVar.m19087b(cursor.getLong(cursor.getColumnIndex(ConnectionModel.f10392d)));
                aVar.m19085c(cursor.getLong(cursor.getColumnIndex(ConnectionModel.f10393e)));
                arrayList.add(aVar);
            }
            return arrayList;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: d */
    public void mo13446d(int i) {
        SQLiteDatabase sQLiteDatabase = this.f15657c;
        sQLiteDatabase.execSQL("DELETE FROM filedownloaderConnection WHERE id = " + i);
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: a */
    public void mo13456a(ConnectionModel aVar) {
        this.f15657c.insert(f15656b, null, aVar.m19082f());
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: a */
    public void mo13466a(int i, int i2, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConnectionModel.f10392d, Long.valueOf(j));
        this.f15657c.update(f15656b, contentValues, "id = ? AND connectionIndex = ?", new String[]{Integer.toString(i), Integer.toString(i2)});
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: a */
    public void mo13467a(int i, int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FileDownloadModel.f10372m, Integer.valueOf(i2));
        this.f15657c.update(f15655a, contentValues, "_id = ? ", new String[]{Integer.toString(i)});
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: a */
    public void mo13457a(FileDownloadModel fileDownloadModel) {
        this.f15657c.insert(f15655a, null, fileDownloadModel.m19109p());
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: b */
    public void mo13450b(FileDownloadModel fileDownloadModel) {
        if (fileDownloadModel == null) {
            FileDownloadLog.m13210d(this, "update but model == null!", new Object[0]);
        } else if (mo13453b(fileDownloadModel.m19135a()) != null) {
            this.f15657c.update(f15655a, fileDownloadModel.m19109p(), "_id = ? ", new String[]{String.valueOf(fileDownloadModel.m19135a())});
        } else {
            mo13457a(fileDownloadModel);
        }
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: e */
    public boolean mo13445e(int i) {
        return this.f15657c.delete(f15655a, "_id = ?", new String[]{String.valueOf(i)}) != 0;
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: a */
    public void mo13469a() {
        this.f15657c.delete(f15655a, null, null);
        this.f15657c.delete(f15656b, null, null);
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: a */
    public void mo13462a(int i, String str, long j, long j2, int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FileDownloadModel.f10368i, Long.valueOf(j));
        contentValues.put(FileDownloadModel.f10369j, Long.valueOf(j2));
        contentValues.put("etag", str);
        contentValues.put(FileDownloadModel.f10372m, Integer.valueOf(i2));
        m13463a(i, contentValues);
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: a */
    public void mo13464a(int i, long j, String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", (Byte) (byte) 2);
        contentValues.put(FileDownloadModel.f10369j, Long.valueOf(j));
        contentValues.put("etag", str);
        contentValues.put("filename", str2);
        m13463a(i, contentValues);
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: a */
    public void mo13465a(int i, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", (Byte) (byte) 3);
        contentValues.put(FileDownloadModel.f10368i, Long.valueOf(j));
        m13463a(i, contentValues);
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: a */
    public void mo13460a(int i, Throwable th, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FileDownloadModel.f10370k, th.toString());
        contentValues.put("status", (Byte) (byte) -1);
        contentValues.put(FileDownloadModel.f10368i, Long.valueOf(j));
        m13463a(i, contentValues);
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: a */
    public void mo13461a(int i, Throwable th) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FileDownloadModel.f10370k, th.toString());
        contentValues.put("status", (Byte) (byte) 5);
        m13463a(i, contentValues);
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: b */
    public void mo13452b(int i, long j) {
        mo13445e(i);
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: c */
    public void mo13447c(int i, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", (Byte) (byte) -2);
        contentValues.put(FileDownloadModel.f10368i, Long.valueOf(j));
        m13463a(i, contentValues);
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: b */
    public FileDownloadDatabase.AbstractC3443a mo13454b() {
        return new C3449a(this);
    }

    /* renamed from: a */
    public FileDownloadDatabase.AbstractC3443a m13458a(SparseArray<FileDownloadModel> sparseArray, SparseArray<List<ConnectionModel>> sparseArray2) {
        return new C3449a(sparseArray, sparseArray2);
    }

    /* renamed from: a */
    private void m13463a(int i, ContentValues contentValues) {
        this.f15657c.update(f15655a, contentValues, "_id = ? ", new String[]{String.valueOf(i)});
    }

    /* compiled from: SqliteDatabaseImpl.java */
    /* renamed from: z1.ago$a */
    /* loaded from: classes3.dex */
    public class C3449a implements FileDownloadDatabase.AbstractC3443a {

        /* renamed from: b */
        private final SparseArray<FileDownloadModel> f15659b;

        /* renamed from: c */
        private C3450b f15660c;

        /* renamed from: d */
        private final SparseArray<FileDownloadModel> f15661d;

        /* renamed from: e */
        private final SparseArray<List<ConnectionModel>> f15662e;

        @Override // p110z1.FileDownloadDatabase.AbstractC3443a
        /* renamed from: a */
        public void mo13441a(FileDownloadModel fileDownloadModel) {
        }

        C3449a(SqliteDatabaseImpl agoVar) {
            this(null, null);
        }

        C3449a(SparseArray<FileDownloadModel> sparseArray, SparseArray<List<ConnectionModel>> sparseArray2) {
            this.f15659b = new SparseArray<>();
            this.f15661d = sparseArray;
            this.f15662e = sparseArray2;
        }

        @Override // java.lang.Iterable
        public Iterator<FileDownloadModel> iterator() {
            C3450b bVar = new C3450b();
            this.f15660c = bVar;
            return bVar;
        }

        @Override // p110z1.FileDownloadDatabase.AbstractC3443a
        /* renamed from: a */
        public void mo13443a() {
            C3450b bVar = this.f15660c;
            if (bVar != null) {
                bVar.m13438b();
            }
            int size = this.f15659b.size();
            if (size >= 0) {
                SqliteDatabaseImpl.this.f15657c.beginTransaction();
                for (int i = 0; i < size; i++) {
                    try {
                        int keyAt = this.f15659b.keyAt(i);
                        FileDownloadModel fileDownloadModel = this.f15659b.get(keyAt);
                        SqliteDatabaseImpl.this.f15657c.delete(SqliteDatabaseImpl.f15655a, "_id = ?", new String[]{String.valueOf(keyAt)});
                        SqliteDatabaseImpl.this.f15657c.insert(SqliteDatabaseImpl.f15655a, null, fileDownloadModel.m19109p());
                        if (fileDownloadModel.m19111n() > 1) {
                            List<ConnectionModel> c = SqliteDatabaseImpl.this.mo13448c(keyAt);
                            if (c.size() > 0) {
                                SqliteDatabaseImpl.this.f15657c.delete(SqliteDatabaseImpl.f15656b, "id = ?", new String[]{String.valueOf(keyAt)});
                                for (ConnectionModel aVar : c) {
                                    aVar.m19092a(fileDownloadModel.m19135a());
                                    SqliteDatabaseImpl.this.f15657c.insert(SqliteDatabaseImpl.f15656b, null, aVar.m19082f());
                                }
                            }
                        }
                    } finally {
                        SqliteDatabaseImpl.this.f15657c.endTransaction();
                    }
                }
                if (!(this.f15661d == null || this.f15662e == null)) {
                    int size2 = this.f15661d.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        int a = this.f15661d.valueAt(i2).m19135a();
                        List<ConnectionModel> c2 = SqliteDatabaseImpl.this.mo13448c(a);
                        if (c2 != null && c2.size() > 0) {
                            this.f15662e.put(a, c2);
                        }
                    }
                }
                SqliteDatabaseImpl.this.f15657c.setTransactionSuccessful();
            }
        }

        @Override // p110z1.FileDownloadDatabase.AbstractC3443a
        /* renamed from: b */
        public void mo13440b(FileDownloadModel fileDownloadModel) {
            SparseArray<FileDownloadModel> sparseArray = this.f15661d;
            if (sparseArray != null) {
                sparseArray.put(fileDownloadModel.m19135a(), fileDownloadModel);
            }
        }

        @Override // p110z1.FileDownloadDatabase.AbstractC3443a
        /* renamed from: a */
        public void mo13442a(int i, FileDownloadModel fileDownloadModel) {
            this.f15659b.put(i, fileDownloadModel);
        }
    }

    /* compiled from: SqliteDatabaseImpl.java */
    /* renamed from: z1.ago$b */
    /* loaded from: classes3.dex */
    class C3450b implements Iterator<FileDownloadModel> {

        /* renamed from: b */
        private final Cursor f15664b;

        /* renamed from: c */
        private final List<Integer> f15665c = new ArrayList();

        /* renamed from: d */
        private int f15666d;

        C3450b() {
            this.f15664b = SqliteDatabaseImpl.this.f15657c.rawQuery("SELECT * FROM filedownloader", null);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f15664b.moveToNext();
        }

        /* renamed from: a */
        public FileDownloadModel next() {
            FileDownloadModel b = SqliteDatabaseImpl.m13451b(this.f15664b);
            this.f15666d = b.m19135a();
            return b;
        }

        @Override // java.util.Iterator
        public void remove() {
            this.f15665c.add(Integer.valueOf(this.f15666d));
        }

        /* renamed from: b */
        void m13438b() {
            this.f15664b.close();
            if (!this.f15665c.isEmpty()) {
                String join = TextUtils.join(", ", this.f15665c);
                if (FileDownloadLog.f15845a) {
                    FileDownloadLog.m13211c(this, "delete %s", join);
                }
                SqliteDatabaseImpl.this.f15657c.execSQL(FileDownloadUtils.m13182a("DELETE FROM %s WHERE %s IN (%s);", SqliteDatabaseImpl.f15655a, "_id", join));
                SqliteDatabaseImpl.this.f15657c.execSQL(FileDownloadUtils.m13182a("DELETE FROM %s WHERE %s IN (%s);", SqliteDatabaseImpl.f15656b, ConnectionModel.f10389a, join));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static FileDownloadModel m13451b(Cursor cursor) {
        FileDownloadModel fileDownloadModel = new FileDownloadModel();
        fileDownloadModel.m19133a(cursor.getInt(cursor.getColumnIndex("_id")));
        fileDownloadModel.m19131a(cursor.getString(cursor.getColumnIndex("url")));
        String string = cursor.getString(cursor.getColumnIndex("path"));
        boolean z = true;
        if (cursor.getShort(cursor.getColumnIndex(FileDownloadModel.f10365f)) != 1) {
            z = false;
        }
        fileDownloadModel.m19130a(string, z);
        fileDownloadModel.m19134a((byte) cursor.getShort(cursor.getColumnIndex("status")));
        fileDownloadModel.m19132a(cursor.getLong(cursor.getColumnIndex(FileDownloadModel.f10368i)));
        fileDownloadModel.m19124c(cursor.getLong(cursor.getColumnIndex(FileDownloadModel.f10369j)));
        fileDownloadModel.m19123c(cursor.getString(cursor.getColumnIndex(FileDownloadModel.f10370k)));
        fileDownloadModel.m19126b(cursor.getString(cursor.getColumnIndex("etag")));
        fileDownloadModel.m19121d(cursor.getString(cursor.getColumnIndex("filename")));
        fileDownloadModel.m19128b(cursor.getInt(cursor.getColumnIndex(FileDownloadModel.f10372m)));
        return fileDownloadModel;
    }

    /* compiled from: SqliteDatabaseImpl.java */
    /* renamed from: z1.ago$c */
    /* loaded from: classes3.dex */
    public static class C3451c implements FileDownloadHelper.AbstractC3479c {
        @Override // p110z1.FileDownloadHelper.AbstractC3479c
        /* renamed from: a */
        public FileDownloadDatabase mo13222a() {
            return new SqliteDatabaseImpl();
        }
    }
}
