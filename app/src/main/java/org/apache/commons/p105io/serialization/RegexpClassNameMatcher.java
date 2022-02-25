package org.apache.commons.p105io.serialization;

import java.util.regex.Pattern;

/* renamed from: org.apache.commons.io.serialization.RegexpClassNameMatcher */
/* loaded from: classes2.dex */
final class RegexpClassNameMatcher implements ClassNameMatcher {
    private final Pattern pattern;

    public RegexpClassNameMatcher(String str) {
        this(Pattern.compile(str));
    }

    public RegexpClassNameMatcher(Pattern pattern) {
        if (pattern != null) {
            this.pattern = pattern;
            return;
        }
        throw new IllegalArgumentException("Null pattern");
    }

    @Override // org.apache.commons.p105io.serialization.ClassNameMatcher
    public final boolean matches(String str) {
        return this.pattern.matcher(str).matches();
    }
}
