package p110z1;

import p110z1.FileDownloadHelper;

/* renamed from: z1.agh */
/* loaded from: classes3.dex */
public class DefaultConnectionCountAdapter implements FileDownloadHelper.AbstractC3477a {

    /* renamed from: a */
    private static final long f15628a = 1048576;

    /* renamed from: b */
    private static final long f15629b = 5242880;

    /* renamed from: c */
    private static final long f15630c = 52428800;

    /* renamed from: d */
    private static final long f15631d = 104857600;

    @Override // p110z1.FileDownloadHelper.AbstractC3477a
    /* renamed from: a */
    public int mo13224a(int i, String str, String str2, long j) {
        if (j < 1048576) {
            return 1;
        }
        if (j < f15629b) {
            return 2;
        }
        if (j < f15630c) {
            return 3;
        }
        return j < f15631d ? 4 : 5;
    }
}
