package org.apache.commons.p105io.serialization;

import org.apache.commons.p105io.FilenameUtils;

/* renamed from: org.apache.commons.io.serialization.WildcardClassNameMatcher */
/* loaded from: classes2.dex */
final class WildcardClassNameMatcher implements ClassNameMatcher {
    private final String pattern;

    public WildcardClassNameMatcher(String str) {
        this.pattern = str;
    }

    @Override // org.apache.commons.p105io.serialization.ClassNameMatcher
    public final boolean matches(String str) {
        return FilenameUtils.wildcardMatch(str, this.pattern);
    }
}
