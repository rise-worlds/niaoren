package com.cyjh.db;

import android.content.Context;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import p110z1.Dao;
import p110z1.PreparedDelete;

/* renamed from: com.cyjh.db.DaoHelpImp */
/* loaded from: classes.dex */
public class DaoHelpImp<T, ID> implements IDaoHelp<T, ID> {
    protected Dao<T, ID> dao;

    public DaoHelpImp(Context context, Class cls, Class<T> cls2) {
        try {
            this.dao = OpenHelperManager.m19844a(context, cls).getDao(cls2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override // com.cyjh.db.IDaoHelp
    public int insert(T t) {
        try {
            return this.dao.mo1062e((Dao<T, ID>) t);
        } catch (SQLException unused) {
            return 0;
        }
    }

    @Override // com.cyjh.db.IDaoHelp
    public int update(T t) {
        try {
            return this.dao.mo1055h(t);
        } catch (SQLException unused) {
            return 0;
        }
    }

    @Override // com.cyjh.db.IDaoHelp
    public int delete(T t) {
        try {
            return this.dao.mo1051j(t);
        } catch (SQLException unused) {
            return 0;
        }
    }

    @Override // com.cyjh.db.IDaoHelp
    public int deleteById(ID id) {
        try {
            return this.dao.mo1049k(id);
        } catch (SQLException unused) {
            return 0;
        }
    }

    @Override // com.cyjh.db.IDaoHelp
    public int deleteAll() {
        try {
            return this.dao.mo1092a((PreparedDelete) this.dao.mo1063e().m511a());
        } catch (SQLException unused) {
            return 0;
        }
    }

    @Override // com.cyjh.db.IDaoHelp
    public T query(ID id) {
        try {
            return this.dao.mo1104a((Dao<T, ID>) id);
        } catch (SQLException unused) {
            return null;
        }
    }

    @Override // com.cyjh.db.IDaoHelp
    public List<T> queryAll() {
        try {
            return this.dao.mo1083b();
        } catch (SQLException unused) {
            return null;
        }
    }

    @Override // com.cyjh.db.IDaoHelp
    public long getCount() {
        try {
            return this.dao.mo1048l();
        } catch (SQLException unused) {
            return 0L;
        }
    }

    @Override // com.cyjh.db.IDaoHelp
    public boolean isOpen() {
        return this.dao.mo1038w().mo248f();
    }

    @Override // com.cyjh.db.IDaoHelp
    public void insertOrUpdate(T t) {
        try {
            this.dao.mo1057g(t);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override // com.cyjh.db.IDaoHelp
    public void batchInsertOrUpdate(final List<T> list) {
        try {
            this.dao.mo1094a(new Callable<Void>() { // from class: com.cyjh.db.DaoHelpImp.1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.concurrent.Callable
                public Void call() throws Exception {
                    try {
                        for (Object obj : list) {
                            DaoHelpImp.this.dao.mo1057g(obj);
                        }
                        return null;
                    } catch (Exception unused) {
                        return null;
                    }
                }
            });
        } catch (Exception unused) {
        }
    }

    @Override // com.cyjh.db.IDaoHelp
    public void batchInsert(final List<T> list) {
        if (list != null && !list.isEmpty()) {
            try {
                this.dao.mo1094a(new Callable<Void>() { // from class: com.cyjh.db.DaoHelpImp.2
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.concurrent.Callable
                    public Void call() throws Exception {
                        for (Object obj : list) {
                            DaoHelpImp.this.dao.mo1062e((Dao<T, ID>) obj);
                        }
                        return null;
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.cyjh.db.IDaoHelp
    public void batchUpdate(final List<T> list) {
        if (list != null && !list.isEmpty()) {
            try {
                this.dao.mo1094a(new Callable<Void>() { // from class: com.cyjh.db.DaoHelpImp.3
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.concurrent.Callable
                    public Void call() throws Exception {
                        for (Object obj : list) {
                            DaoHelpImp.this.dao.mo1055h(obj);
                        }
                        return null;
                    }
                });
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.cyjh.db.IDaoHelp
    public int batchDelete(List<ID> list) {
        try {
            return this.dao.mo1078b((Collection) list);
        } catch (SQLException unused) {
            return 0;
        }
    }
}
