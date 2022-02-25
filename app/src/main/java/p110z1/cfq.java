package p110z1;

import java.io.File;

/* compiled from: Exceptions.kt */
@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a$\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0001H\u0002¨\u0006\u0006"}, m8860e = {"constructMessage", "", "file", "Ljava/io/File;", "other", "reason", "kotlin-stdlib"})
/* renamed from: z1.cfq */
/* loaded from: classes3.dex */
public final class cfq {
    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static final String m5393b(File file, File file2, String str) {
        StringBuilder sb = new StringBuilder(file.toString());
        if (file2 != null) {
            sb.append(" -> " + file2);
        }
        if (str != null) {
            sb.append(": " + str);
        }
        String sb2 = sb.toString();
        cji.m5175b(sb2, "sb.toString()");
        return sb2;
    }
}
