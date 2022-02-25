package p110z1;

import android.util.SparseArray;
import com.liulishuo.filedownloader.model.ConnectionModel;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p110z1.FileDownloadDatabase;
import p110z1.FileDownloadHelper;

/* renamed from: z1.agm */
/* loaded from: classes3.dex */
public class NoDatabaseImpl implements FileDownloadDatabase {

    /* renamed from: a */
    final SparseArray<FileDownloadModel> f15642a = new SparseArray<>();

    /* renamed from: b */
    final SparseArray<List<ConnectionModel>> f15643b = new SparseArray<>();

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: a */
    public void mo13468a(int i) {
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: a */
    public void mo13467a(int i, int i2) {
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: a */
    public void mo13465a(int i, long j) {
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: a */
    public void mo13464a(int i, long j, String str, String str2) {
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: a */
    public void mo13462a(int i, String str, long j, long j2, int i2) {
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: a */
    public void mo13461a(int i, Throwable th) {
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: a */
    public void mo13460a(int i, Throwable th, long j) {
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: c */
    public void mo13447c(int i, long j) {
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: f */
    public void mo13444f(int i) {
    }

    /* renamed from: c */
    public static C3446c m13479c() {
        return new C3446c();
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: b */
    public FileDownloadModel mo13453b(int i) {
        return this.f15642a.get(i);
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: c */
    public List<ConnectionModel> mo13448c(int i) {
        ArrayList arrayList = new ArrayList();
        List<ConnectionModel> list = this.f15643b.get(i);
        if (list != null) {
            arrayList.addAll(list);
        }
        return arrayList;
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: d */
    public void mo13446d(int i) {
        this.f15643b.remove(i);
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: a */
    public void mo13456a(ConnectionModel aVar) {
        int a = aVar.m19093a();
        List<ConnectionModel> list = this.f15643b.get(a);
        if (list == null) {
            list = new ArrayList<>();
            this.f15643b.put(a, list);
        }
        list.add(aVar);
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: a */
    public void mo13466a(int i, int i2, long j) {
        List<ConnectionModel> list = this.f15643b.get(i);
        if (list != null) {
            for (ConnectionModel aVar : list) {
                if (aVar.m19089b() == i2) {
                    aVar.m19087b(j);
                    return;
                }
            }
        }
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: a */
    public void mo13457a(FileDownloadModel fileDownloadModel) {
        this.f15642a.put(fileDownloadModel.m19135a(), fileDownloadModel);
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: b */
    public void mo13450b(FileDownloadModel fileDownloadModel) {
        if (fileDownloadModel == null) {
            FileDownloadLog.m13210d(this, "update but model == null!", new Object[0]);
        } else if (mo13453b(fileDownloadModel.m19135a()) != null) {
            this.f15642a.remove(fileDownloadModel.m19135a());
            this.f15642a.put(fileDownloadModel.m19135a(), fileDownloadModel);
        } else {
            mo13457a(fileDownloadModel);
        }
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: e */
    public boolean mo13445e(int i) {
        this.f15642a.remove(i);
        return true;
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: a */
    public void mo13469a() {
        this.f15642a.clear();
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: b */
    public void mo13452b(int i, long j) {
        mo13445e(i);
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: b */
    public FileDownloadDatabase.AbstractC3443a mo13454b() {
        return new C3444a();
    }

    /* compiled from: NoDatabaseImpl.java */
    /* renamed from: z1.agm$a */
    /* loaded from: classes3.dex */
    class C3444a implements FileDownloadDatabase.AbstractC3443a {
        @Override // p110z1.FileDownloadDatabase.AbstractC3443a
        /* renamed from: a */
        public void mo13443a() {
        }

        @Override // p110z1.FileDownloadDatabase.AbstractC3443a
        /* renamed from: a */
        public void mo13442a(int i, FileDownloadModel fileDownloadModel) {
        }

        @Override // p110z1.FileDownloadDatabase.AbstractC3443a
        /* renamed from: a */
        public void mo13441a(FileDownloadModel fileDownloadModel) {
        }

        @Override // p110z1.FileDownloadDatabase.AbstractC3443a
        /* renamed from: b */
        public void mo13440b(FileDownloadModel fileDownloadModel) {
        }

        C3444a() {
        }

        @Override // java.lang.Iterable
        public Iterator<FileDownloadModel> iterator() {
            return new C3445b();
        }
    }

    /* compiled from: NoDatabaseImpl.java */
    /* renamed from: z1.agm$b */
    /* loaded from: classes3.dex */
    class C3445b implements Iterator<FileDownloadModel> {
        /* renamed from: a */
        public FileDownloadModel next() {
            return null;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return false;
        }

        @Override // java.util.Iterator
        public void remove() {
        }

        C3445b() {
        }
    }

    /* compiled from: NoDatabaseImpl.java */
    /* renamed from: z1.agm$c */
    /* loaded from: classes3.dex */
    public static class C3446c implements FileDownloadHelper.AbstractC3479c {
        @Override // p110z1.FileDownloadHelper.AbstractC3479c
        /* renamed from: a */
        public FileDownloadDatabase mo13222a() {
            return new NoDatabaseImpl();
        }
    }
}
