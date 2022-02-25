package com.lody.virtual.server.p063pm;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import com.lody.virtual.helper.utils.VLog;
import com.lody.virtual.server.p063pm.parser.VPackage;
import com.lody.virtual.server.p063pm.parser.VPackage.IntentInfo;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* renamed from: com.lody.virtual.server.pm.IntentResolver */
/* loaded from: classes.dex */
public abstract class IntentResolver<F extends VPackage.IntentInfo, R> {
    private static final String TAG = "IntentResolver";
    private static final Comparator sResolvePrioritySorter = new Comparator() { // from class: com.lody.virtual.server.pm.IntentResolver.1
        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            int i;
            int i2;
            if (obj instanceof IntentFilter) {
                i2 = ((IntentFilter) obj).getPriority();
                i = ((IntentFilter) obj2).getPriority();
            } else if (!(obj instanceof ResolveInfo)) {
                return 0;
            } else {
                ResolveInfo resolveInfo = (ResolveInfo) obj;
                ResolveInfo resolveInfo2 = (ResolveInfo) obj2;
                i2 = resolveInfo.filter == null ? 0 : resolveInfo.filter.getPriority();
                i = resolveInfo2.filter == null ? 0 : resolveInfo2.filter.getPriority();
            }
            if (i2 > i) {
                return -1;
            }
            return i2 < i ? 1 : 0;
        }
    };
    private HashSet<F> mFilters = new HashSet<>();
    private HashMap<String, F[]> mTypeToFilter = new HashMap<>();
    private HashMap<String, F[]> mBaseTypeToFilter = new HashMap<>();
    private HashMap<String, F[]> mWildTypeToFilter = new HashMap<>();
    private HashMap<String, F[]> mSchemeToFilter = new HashMap<>();
    private HashMap<String, F[]> mActionToFilter = new HashMap<>();
    private HashMap<String, F[]> mTypedActionToFilter = new HashMap<>();

    protected boolean allowFilterResult(F f, List<R> list) {
        return true;
    }

    protected Object filterToLabel(F f) {
        return "IntentFilter";
    }

    protected boolean isFilterStopped(F f) {
        return false;
    }

    protected abstract boolean isPackageForFilter(String str, F f);

    protected abstract F[] newArray(int i);

    /* JADX WARN: Multi-variable type inference failed */
    protected R newResult(F f, int i, int i2) {
        return f;
    }

    private static FastImmutableArraySet<String> getFastIntentCategories(Intent intent) {
        Set<String> categories = intent.getCategories();
        if (categories == null) {
            return null;
        }
        return new FastImmutableArraySet<>(categories.toArray(new String[categories.size()]));
    }

    public void addFilter(F f) {
        this.mFilters.add(f);
        int register_intent_filter = register_intent_filter(f, f.filter.schemesIterator(), this.mSchemeToFilter, "      Scheme: ");
        int register_mime_types = register_mime_types(f, "      Type: ");
        if (register_intent_filter == 0 && register_mime_types == 0) {
            register_intent_filter(f, f.filter.actionsIterator(), this.mActionToFilter, "      Action: ");
        }
        if (register_mime_types != 0) {
            register_intent_filter(f, f.filter.actionsIterator(), this.mTypedActionToFilter, "      TypedAction: ");
        }
    }

    private boolean filterEquals(IntentFilter intentFilter, IntentFilter intentFilter2) {
        int countDataSchemes;
        int countActions = intentFilter.countActions();
        if (countActions != intentFilter2.countActions()) {
            return false;
        }
        for (int i = 0; i < countActions; i++) {
            if (!intentFilter2.hasAction(intentFilter.getAction(i))) {
                return false;
            }
        }
        int countCategories = intentFilter.countCategories();
        if (countCategories != intentFilter2.countCategories()) {
            return false;
        }
        for (int i2 = 0; i2 < countCategories; i2++) {
            if (!intentFilter2.hasCategory(intentFilter.getCategory(i2))) {
                return false;
            }
        }
        if (!(intentFilter.countDataTypes() == intentFilter2.countDataTypes() && (countDataSchemes = intentFilter.countDataSchemes()) == intentFilter2.countDataSchemes())) {
            return false;
        }
        for (int i3 = 0; i3 < countDataSchemes; i3++) {
            if (!intentFilter2.hasDataScheme(intentFilter.getDataScheme(i3))) {
                return false;
            }
        }
        if (intentFilter.countDataAuthorities() == intentFilter2.countDataAuthorities() && intentFilter.countDataPaths() == intentFilter2.countDataPaths()) {
            return Build.VERSION.SDK_INT < 19 || intentFilter.countDataSchemeSpecificParts() == intentFilter2.countDataSchemeSpecificParts();
        }
        return false;
    }

    private ArrayList<F> collectFilters(F[] fArr, IntentFilter intentFilter) {
        F f;
        ArrayList<F> arrayList = null;
        if (fArr != null) {
            for (int i = 0; i < fArr.length && (f = fArr[i]) != null; i++) {
                if (filterEquals(f.filter, intentFilter)) {
                    if (arrayList == null) {
                        arrayList = new ArrayList<>();
                    }
                    arrayList.add(f);
                }
            }
        }
        return arrayList;
    }

    public ArrayList<F> findFilters(IntentFilter intentFilter) {
        if (intentFilter.countDataSchemes() == 1) {
            return collectFilters(this.mSchemeToFilter.get(intentFilter.getDataScheme(0)), intentFilter);
        }
        if (intentFilter.countDataTypes() != 0 && intentFilter.countActions() == 1) {
            return collectFilters(this.mTypedActionToFilter.get(intentFilter.getAction(0)), intentFilter);
        }
        if (intentFilter.countDataTypes() == 0 && intentFilter.countDataSchemes() == 0 && intentFilter.countActions() == 1) {
            return collectFilters(this.mActionToFilter.get(intentFilter.getAction(0)), intentFilter);
        }
        ArrayList<F> arrayList = null;
        Iterator<F> it = this.mFilters.iterator();
        while (it.hasNext()) {
            F next = it.next();
            if (filterEquals(next.filter, intentFilter)) {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public void removeFilter(F f) {
        removeFilterInternal(f);
        this.mFilters.remove(f);
    }

    void removeFilterInternal(F f) {
        int unregister_intent_filter = unregister_intent_filter(f, f.filter.schemesIterator(), this.mSchemeToFilter, "      Scheme: ");
        int unregister_mime_types = unregister_mime_types(f, "      Type: ");
        if (unregister_intent_filter == 0 && unregister_mime_types == 0) {
            unregister_intent_filter(f, f.filter.actionsIterator(), this.mActionToFilter, "      Action: ");
        }
        if (unregister_mime_types != 0) {
            unregister_intent_filter(f, f.filter.actionsIterator(), this.mTypedActionToFilter, "      TypedAction: ");
        }
    }

    public Iterator<F> filterIterator() {
        return new IteratorWrapper(this.mFilters.iterator());
    }

    public Set<F> filterSet() {
        return Collections.unmodifiableSet(this.mFilters);
    }

    public List<R> queryIntentFromList(Intent intent, String str, boolean z, ArrayList<F[]> arrayList, int i) {
        ArrayList arrayList2 = new ArrayList();
        FastImmutableArraySet<String> fastIntentCategories = getFastIntentCategories(intent);
        String scheme = intent.getScheme();
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            buildResolveList(intent, fastIntentCategories, z, str, scheme, arrayList.get(i2), arrayList2, i);
        }
        sortResults(arrayList2);
        return arrayList2;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00e5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<R> queryIntent(android.content.Intent r18, java.lang.String r19, boolean r20, int r21) {
        /*
            Method dump skipped, instructions count: 251
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lody.virtual.server.p063pm.IntentResolver.queryIntent(android.content.Intent, java.lang.String, boolean, int):java.util.List");
    }

    protected void sortResults(List<R> list) {
        Collections.sort(list, sResolvePrioritySorter);
    }

    protected void dumpFilter(PrintWriter printWriter, String str, F f) {
        printWriter.print(str);
        printWriter.println(f);
    }

    protected void dumpFilterLabel(PrintWriter printWriter, String str, Object obj, int i) {
        printWriter.print(str);
        printWriter.print(obj);
        printWriter.print(": ");
        printWriter.println(i);
    }

    private void addFilter(HashMap<String, F[]> hashMap, String str, F f) {
        F[] fArr = hashMap.get(str);
        if (fArr == null) {
            F[] newArray = newArray(2);
            hashMap.put(str, newArray);
            newArray[0] = f;
            return;
        }
        int length = fArr.length;
        int i = length;
        while (i > 0 && fArr[i - 1] == null) {
            i--;
        }
        if (i < length) {
            fArr[i] = f;
            return;
        }
        F[] newArray2 = newArray((length * 3) / 2);
        System.arraycopy(fArr, 0, newArray2, 0, length);
        newArray2[length] = f;
        hashMap.put(str, newArray2);
    }

    private int register_mime_types(F f, String str) {
        String str2;
        Iterator<String> typesIterator = f.filter.typesIterator();
        if (typesIterator == null) {
            return 0;
        }
        int i = 0;
        while (typesIterator.hasNext()) {
            String next = typesIterator.next();
            i++;
            int indexOf = next.indexOf(47);
            if (indexOf > 0) {
                str2 = next.substring(0, indexOf).intern();
            } else {
                next = next + "/*";
                str2 = next;
            }
            addFilter(this.mTypeToFilter, next, f);
            if (indexOf > 0) {
                addFilter(this.mBaseTypeToFilter, str2, f);
            } else {
                addFilter(this.mWildTypeToFilter, str2, f);
            }
        }
        return i;
    }

    private int unregister_mime_types(F f, String str) {
        String str2;
        Iterator<String> typesIterator = f.filter.typesIterator();
        if (typesIterator == null) {
            return 0;
        }
        int i = 0;
        while (typesIterator.hasNext()) {
            String next = typesIterator.next();
            i++;
            int indexOf = next.indexOf(47);
            if (indexOf > 0) {
                str2 = next.substring(0, indexOf).intern();
            } else {
                next = next + "/*";
                str2 = next;
            }
            remove_all_objects(this.mTypeToFilter, next, f);
            if (indexOf > 0) {
                remove_all_objects(this.mBaseTypeToFilter, str2, f);
            } else {
                remove_all_objects(this.mWildTypeToFilter, str2, f);
            }
        }
        return i;
    }

    private int register_intent_filter(F f, Iterator<String> it, HashMap<String, F[]> hashMap, String str) {
        int i = 0;
        if (it == null) {
            return 0;
        }
        while (it.hasNext()) {
            i++;
            addFilter(hashMap, it.next(), f);
        }
        return i;
    }

    private int unregister_intent_filter(F f, Iterator<String> it, HashMap<String, F[]> hashMap, String str) {
        int i = 0;
        if (it == null) {
            return 0;
        }
        while (it.hasNext()) {
            i++;
            remove_all_objects(hashMap, it.next(), f);
        }
        return i;
    }

    private void remove_all_objects(HashMap<String, F[]> hashMap, String str, Object obj) {
        F[] fArr = hashMap.get(str);
        if (fArr != null) {
            int length = fArr.length - 1;
            while (length >= 0 && fArr[length] == null) {
                length--;
            }
            int i = length;
            while (length >= 0) {
                if (fArr[length] == obj) {
                    int i2 = i - length;
                    if (i2 > 0) {
                        System.arraycopy(fArr, length + 1, fArr, length, i2);
                    }
                    fArr[i] = null;
                    i--;
                }
                length--;
            }
            if (i < 0) {
                hashMap.remove(str);
            } else if (i < fArr.length / 2) {
                F[] newArray = newArray(i + 2);
                System.arraycopy(fArr, 0, newArray, 0, i + 1);
                hashMap.put(str, newArray);
            }
        }
    }

    private void buildResolveList(Intent intent, FastImmutableArraySet<String> fastImmutableArraySet, boolean z, String str, String str2, F[] fArr, List<R> list, int i) {
        F f;
        int match;
        F[] fArr2 = fArr;
        String action = intent.getAction();
        Uri data = intent.getData();
        String str3 = intent.getPackage();
        int length = fArr2 != null ? fArr2.length : 0;
        int i2 = 0;
        boolean z2 = false;
        while (i2 < length && (f = fArr2[i2]) != null) {
            if ((str3 == null || isPackageForFilter(str3, f)) && allowFilterResult(f, list) && (match = f.filter.match(action, str, str2, data, fastImmutableArraySet, TAG)) >= 0) {
                if (!z || f.filter.hasCategory("android.intent.category.DEFAULT")) {
                    R newResult = newResult(f, match, i);
                    if (newResult != null) {
                        list.add(newResult);
                    }
                } else {
                    z2 = true;
                }
            }
            i2++;
            fArr2 = fArr;
        }
        if (!z2) {
            return;
        }
        if (list.size() == 0) {
            VLog.m18986w(TAG, "resolveIntent failed: found match, but none with CATEGORY_DEFAULT", new Object[0]);
        } else if (list.size() > 1) {
            VLog.m18986w(TAG, "resolveIntent: multiple matches, only some with CATEGORY_DEFAULT", new Object[0]);
        }
    }

    /* renamed from: com.lody.virtual.server.pm.IntentResolver$IteratorWrapper */
    /* loaded from: classes.dex */
    private class IteratorWrapper implements Iterator<F> {
        private F mCur;

        /* renamed from: mI */
        private Iterator<F> f10504mI;

        IteratorWrapper(Iterator<F> it) {
            this.f10504mI = it;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f10504mI.hasNext();
        }

        @Override // java.util.Iterator
        public F next() {
            F next = this.f10504mI.next();
            this.mCur = next;
            return next;
        }

        @Override // java.util.Iterator
        public void remove() {
            F f = this.mCur;
            if (f != null) {
                IntentResolver.this.removeFilterInternal(f);
            }
            this.f10504mI.remove();
        }
    }
}
