package com.lidroid.xutils.p058db.sqlite;

import android.text.TextUtils;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.lidroid.xutils.p058db.converter.ColumnConverterFactory;
import com.lidroid.xutils.p058db.table.ColumnUtils;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p110z1.ManyClause;
import p110z1.SimpleComparison;

/* renamed from: com.lidroid.xutils.db.sqlite.WhereBuilder */
/* loaded from: classes.dex */
public class WhereBuilder {
    private final List<String> whereItems = new ArrayList();

    private WhereBuilder() {
    }

    /* renamed from: b */
    public static WhereBuilder m19226b() {
        return new WhereBuilder();
    }

    /* renamed from: b */
    public static WhereBuilder m19225b(String str, String str2, Object obj) {
        WhereBuilder whereBuilder = new WhereBuilder();
        whereBuilder.appendCondition(null, str, str2, obj);
        return whereBuilder;
    }

    public WhereBuilder and(String str, String str2, Object obj) {
        appendCondition(this.whereItems.size() == 0 ? null : ManyClause.f23595a, str, str2, obj);
        return this;
    }

    /* renamed from: or */
    public WhereBuilder m19224or(String str, String str2, Object obj) {
        appendCondition(this.whereItems.size() == 0 ? null : ManyClause.f23596b, str, str2, obj);
        return this;
    }

    public WhereBuilder expr(String str) {
        List<String> list = this.whereItems;
        list.add(ExpandableTextView.f6958c + str);
        return this;
    }

    public WhereBuilder expr(String str, String str2, Object obj) {
        appendCondition(null, str, str2, obj);
        return this;
    }

    public int getWhereItemSize() {
        return this.whereItems.size();
    }

    public String toString() {
        if (this.whereItems.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String str : this.whereItems) {
            sb.append(str);
        }
        return sb.toString();
    }

    private void appendCondition(String str, String str2, String str3, Object obj) {
        StringBuilder sb = new StringBuilder();
        if (this.whereItems.size() > 0) {
            sb.append(ExpandableTextView.f6958c);
        }
        if (!TextUtils.isEmpty(str)) {
            sb.append(String.valueOf(str) + ExpandableTextView.f6958c);
        }
        sb.append(str2);
        if ("!=".equals(str3)) {
            str3 = SimpleComparison.f23615i;
        } else if ("==".equals(str3)) {
            str3 = SimpleComparison.f23609c;
        }
        if (obj != null) {
            sb.append(ExpandableTextView.f6958c + str3 + ExpandableTextView.f6958c);
            int i = 0;
            Iterable<Object> iterable = null;
            if ("IN".equalsIgnoreCase(str3)) {
                if (obj instanceof Iterable) {
                    iterable = (Iterable) obj;
                } else if (obj.getClass().isArray()) {
                    ArrayList arrayList = new ArrayList();
                    int length = Array.getLength(obj);
                    while (i < length) {
                        arrayList.add(Array.get(obj, i));
                        i++;
                    }
                    iterable = arrayList;
                }
                if (iterable != null) {
                    StringBuffer stringBuffer = new StringBuffer("(");
                    for (Object obj2 : iterable) {
                        Object convert2DbColumnValueIfNeeded = ColumnUtils.convert2DbColumnValueIfNeeded(obj2);
                        if (ColumnDbType.TEXT.equals(ColumnConverterFactory.getDbColumnType(convert2DbColumnValueIfNeeded.getClass()))) {
                            String obj3 = convert2DbColumnValueIfNeeded.toString();
                            if (obj3.indexOf(39) != -1) {
                                obj3 = obj3.replace("'", "''");
                            }
                            stringBuffer.append("'" + obj3 + "'");
                        } else {
                            stringBuffer.append(convert2DbColumnValueIfNeeded);
                        }
                        stringBuffer.append(",");
                    }
                    stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                    stringBuffer.append(")");
                    sb.append(stringBuffer.toString());
                } else {
                    throw new IllegalArgumentException("value must be an Array or an Iterable.");
                }
            } else if ("BETWEEN".equalsIgnoreCase(str3)) {
                if (obj instanceof Iterable) {
                    iterable = (Iterable) obj;
                } else if (obj.getClass().isArray()) {
                    ArrayList arrayList2 = new ArrayList();
                    int length2 = Array.getLength(obj);
                    while (i < length2) {
                        arrayList2.add(Array.get(obj, i));
                        i++;
                    }
                    iterable = arrayList2;
                }
                if (iterable != null) {
                    Iterator it = iterable.iterator();
                    if (it.hasNext()) {
                        Object next = it.next();
                        if (it.hasNext()) {
                            Object next2 = it.next();
                            Object convert2DbColumnValueIfNeeded2 = ColumnUtils.convert2DbColumnValueIfNeeded(next);
                            Object convert2DbColumnValueIfNeeded3 = ColumnUtils.convert2DbColumnValueIfNeeded(next2);
                            if (ColumnDbType.TEXT.equals(ColumnConverterFactory.getDbColumnType(convert2DbColumnValueIfNeeded2.getClass()))) {
                                String obj4 = convert2DbColumnValueIfNeeded2.toString();
                                if (obj4.indexOf(39) != -1) {
                                    obj4 = obj4.replace("'", "''");
                                }
                                String obj5 = convert2DbColumnValueIfNeeded3.toString();
                                if (obj5.indexOf(39) != -1) {
                                    obj5 = obj5.replace("'", "''");
                                }
                                sb.append("'" + obj4 + "'");
                                sb.append(" AND ");
                                sb.append("'" + obj5 + "'");
                            } else {
                                sb.append(convert2DbColumnValueIfNeeded2);
                                sb.append(" AND ");
                                sb.append(convert2DbColumnValueIfNeeded3);
                            }
                        } else {
                            throw new IllegalArgumentException("value must have tow items.");
                        }
                    } else {
                        throw new IllegalArgumentException("value must have tow items.");
                    }
                } else {
                    throw new IllegalArgumentException("value must be an Array or an Iterable.");
                }
            } else {
                Object convert2DbColumnValueIfNeeded4 = ColumnUtils.convert2DbColumnValueIfNeeded(obj);
                if (ColumnDbType.TEXT.equals(ColumnConverterFactory.getDbColumnType(convert2DbColumnValueIfNeeded4.getClass()))) {
                    String obj6 = convert2DbColumnValueIfNeeded4.toString();
                    if (obj6.indexOf(39) != -1) {
                        obj6 = obj6.replace("'", "''");
                    }
                    sb.append("'" + obj6 + "'");
                } else {
                    sb.append(convert2DbColumnValueIfNeeded4);
                }
            }
        } else if (SimpleComparison.f23609c.equals(str3)) {
            sb.append(" IS NULL");
        } else if (SimpleComparison.f23615i.equals(str3)) {
            sb.append(" IS NOT NULL");
        } else {
            sb.append(ExpandableTextView.f6958c + str3 + " NULL");
        }
        this.whereItems.add(sb.toString());
    }
}
