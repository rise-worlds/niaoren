package p110z1;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

/* renamed from: z1.bc */
/* loaded from: classes3.dex */
public class C4130bc {

    /* renamed from: a */
    private static final String f18206a = "RSA";

    /* renamed from: b */
    private static PublicKey m9758b(String str, String str2) throws NoSuchAlgorithmException, Exception {
        return KeyFactory.getInstance(str).generatePublic(new X509EncodedKeySpec(C3961ay.m9836a(str2)));
    }

    /* JADX WARN: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: TypeSearchVarInfo not found in map for var: r0v3 java.lang.Object
    	at jadx.core.dex.visitors.typeinference.TypeSearchState.getVarInfo(TypeSearchState.java:34)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.lambda$resolveIndependentVariables$1(TypeSearch.java:174)
    	at java.base/java.util.stream.MatchOps$1MatchSink.accept(Unknown Source)
    	at java.base/java.util.ArrayList$ArrayListSpliterator.tryAdvance(Unknown Source)
    	at java.base/java.util.stream.ReferencePipeline$7$1.accept(Unknown Source)
    	at java.base/java.util.ArrayList$ArrayListSpliterator.tryAdvance(Unknown Source)
    	at java.base/java.util.stream.ReferencePipeline.forEachWithCancel(Unknown Source)
    	at java.base/java.util.stream.AbstractPipeline.copyIntoWithCancel(Unknown Source)
    	at java.base/java.util.stream.AbstractPipeline.copyInto(Unknown Source)
    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(Unknown Source)
    	at java.base/java.util.stream.MatchOps$MatchOp.evaluateSequential(Unknown Source)
     */
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0052: MOVE  (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:25:0x0052 */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0055 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Unknown variable types count: 1 */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] m9759a(java.lang.String r5, java.lang.String r6) {
        /*
            r0 = 0
            java.lang.String r1 = "RSA"
            java.security.PublicKey r6 = m9758b(r1, r6)     // Catch: all -> 0x0044, Exception -> 0x0046
            java.lang.String r1 = "RSA/ECB/PKCS1Padding"
            javax.crypto.Cipher r1 = javax.crypto.Cipher.getInstance(r1)     // Catch: all -> 0x0044, Exception -> 0x0046
            r2 = 1
            r1.init(r2, r6)     // Catch: all -> 0x0044, Exception -> 0x0046
            java.lang.String r6 = "UTF-8"
            byte[] r5 = r5.getBytes(r6)     // Catch: all -> 0x0044, Exception -> 0x0046
            int r6 = r1.getBlockSize()     // Catch: all -> 0x0044, Exception -> 0x0046
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch: all -> 0x0044, Exception -> 0x0046
            r2.<init>()     // Catch: all -> 0x0044, Exception -> 0x0046
            r3 = 0
        L_0x0021:
            int r4 = r5.length     // Catch: Exception -> 0x0042, all -> 0x0051
            if (r3 >= r4) goto L_0x0035
            int r4 = r5.length     // Catch: Exception -> 0x0042, all -> 0x0051
            int r4 = r4 - r3
            if (r4 >= r6) goto L_0x002b
            int r4 = r5.length     // Catch: Exception -> 0x0042, all -> 0x0051
            int r4 = r4 - r3
            goto L_0x002c
        L_0x002b:
            r4 = r6
        L_0x002c:
            byte[] r4 = r1.doFinal(r5, r3, r4)     // Catch: Exception -> 0x0042, all -> 0x0051
            r2.write(r4)     // Catch: Exception -> 0x0042, all -> 0x0051
            int r3 = r3 + r6
            goto L_0x0021
        L_0x0035:
            byte[] r0 = r2.toByteArray()     // Catch: Exception -> 0x0042, all -> 0x0051
            r2.close()     // Catch: IOException -> 0x003d
            goto L_0x0050
        L_0x003d:
            r5 = move-exception
            p110z1.C4921cd.m5618a(r5)
            goto L_0x0050
        L_0x0042:
            r5 = move-exception
            goto L_0x0048
        L_0x0044:
            r5 = move-exception
            goto L_0x0053
        L_0x0046:
            r5 = move-exception
            r2 = r0
        L_0x0048:
            p110z1.C4921cd.m5618a(r5)     // Catch: all -> 0x0051
            if (r2 == 0) goto L_0x0050
            r2.close()     // Catch: IOException -> 0x003d
        L_0x0050:
            return r0
        L_0x0051:
            r5 = move-exception
            r0 = r2
        L_0x0053:
            if (r0 == 0) goto L_0x005d
            r0.close()     // Catch: IOException -> 0x0059
            goto L_0x005d
        L_0x0059:
            r6 = move-exception
            p110z1.C4921cd.m5618a(r6)
        L_0x005d:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.C4130bc.m9759a(java.lang.String, java.lang.String):byte[]");
    }
}
