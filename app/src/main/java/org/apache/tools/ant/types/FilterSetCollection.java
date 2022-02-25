package org.apache.tools.ant.types;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class FilterSetCollection {
    private List<FilterSet> filterSets = new ArrayList();

    public FilterSetCollection() {
    }

    public FilterSetCollection(FilterSet filterSet) {
        addFilterSet(filterSet);
    }

    public void addFilterSet(FilterSet filterSet) {
        this.filterSets.add(filterSet);
    }

    public String replaceTokens(String str) {
        for (FilterSet filterSet : this.filterSets) {
            str = filterSet.replaceTokens(str);
        }
        return str;
    }

    public boolean hasFilters() {
        for (FilterSet filterSet : this.filterSets) {
            if (filterSet.hasFilters()) {
                return true;
            }
        }
        return false;
    }
}
