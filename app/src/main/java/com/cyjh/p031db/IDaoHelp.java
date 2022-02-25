package com.cyjh.p031db;

import java.util.List;

/* renamed from: com.cyjh.db.IDaoHelp */
/* loaded from: classes.dex */
public interface IDaoHelp<T, ID> {
    int batchDelete(List<ID> list);

    void batchInsert(List<T> list);

    void batchInsertOrUpdate(List<T> list);

    void batchUpdate(List<T> list);

    int delete(T t);

    int deleteAll();

    int deleteById(ID id);

    long getCount();

    int insert(T t);

    void insertOrUpdate(T t);

    boolean isOpen();

    T query(ID id);

    List<T> queryAll();

    int update(T t);
}
