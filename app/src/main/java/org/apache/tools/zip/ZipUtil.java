package org.apache.tools.zip;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.CRC32;
import org.apache.tools.zip.UnsupportedZipFeatureException;

/* loaded from: classes2.dex */
public abstract class ZipUtil {
    private static final byte[] DOS_TIME_MIN = ZipLong.getBytes(8448);

    public static long adjustToLong(int i) {
        return i < 0 ? i + 4294967296L : i;
    }

    public static ZipLong toDosTime(Date date) {
        return new ZipLong(toDosTime(date.getTime()));
    }

    public static byte[] toDosTime(long j) {
        byte[] bArr = new byte[4];
        toDosTime(j, bArr, 0);
        return bArr;
    }

    public static void toDosTime(long j, byte[] bArr, int i) {
        toDosTime(Calendar.getInstance(), j, bArr, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void toDosTime(Calendar calendar, long j, byte[] bArr, int i) {
        calendar.setTimeInMillis(j);
        int i2 = calendar.get(1);
        if (i2 < 1980) {
            byte[] bArr2 = DOS_TIME_MIN;
            System.arraycopy(bArr2, 0, bArr, i, bArr2.length);
            return;
        }
        ZipLong.putLong((calendar.get(13) >> 1) | ((i2 - 1980) << 25) | ((calendar.get(2) + 1) << 21) | (calendar.get(5) << 16) | (calendar.get(11) << 11) | (calendar.get(12) << 5), bArr, i);
    }

    public static Date fromDosTime(ZipLong zipLong) {
        return new Date(dosToJavaTime(zipLong.getValue()));
    }

    public static long dosToJavaTime(long j) {
        Calendar instance = Calendar.getInstance();
        instance.set(1, ((int) ((j >> 25) & 127)) + 1980);
        instance.set(2, ((int) ((j >> 21) & 15)) - 1);
        instance.set(5, ((int) (j >> 16)) & 31);
        instance.set(11, ((int) (j >> 11)) & 31);
        instance.set(12, ((int) (j >> 5)) & 63);
        instance.set(13, ((int) (j << 1)) & 62);
        instance.set(14, 0);
        return instance.getTime().getTime();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setNameAndCommentFromExtraFields(ZipEntry zipEntry, byte[] bArr, byte[] bArr2) {
        String unicodeStringIfOriginalMatches;
        String name = zipEntry.getName();
        String unicodeStringIfOriginalMatches2 = getUnicodeStringIfOriginalMatches((UnicodePathExtraField) zipEntry.getExtraField(UnicodePathExtraField.UPATH_ID), bArr);
        if (unicodeStringIfOriginalMatches2 != null && !name.equals(unicodeStringIfOriginalMatches2)) {
            zipEntry.setName(unicodeStringIfOriginalMatches2);
        }
        if (bArr2 != null && bArr2.length > 0 && (unicodeStringIfOriginalMatches = getUnicodeStringIfOriginalMatches((UnicodeCommentExtraField) zipEntry.getExtraField(UnicodeCommentExtraField.UCOM_ID), bArr2)) != null) {
            zipEntry.setComment(unicodeStringIfOriginalMatches);
        }
    }

    private static String getUnicodeStringIfOriginalMatches(AbstractUnicodeExtraField abstractUnicodeExtraField, byte[] bArr) {
        if (abstractUnicodeExtraField != null) {
            CRC32 crc32 = new CRC32();
            crc32.update(bArr);
            if (crc32.getValue() == abstractUnicodeExtraField.getNameCRC32()) {
                try {
                    return ZipEncodingHelper.UTF8_ZIP_ENCODING.decode(abstractUnicodeExtraField.getUnicodeName());
                } catch (IOException unused) {
                    return null;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] copy(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        return bArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean canHandleEntryData(ZipEntry zipEntry) {
        return supportsEncryptionOf(zipEntry) && supportsMethodOf(zipEntry);
    }

    private static boolean supportsEncryptionOf(ZipEntry zipEntry) {
        return !zipEntry.getGeneralPurposeBit().usesEncryption();
    }

    private static boolean supportsMethodOf(ZipEntry zipEntry) {
        return zipEntry.getMethod() == 0 || zipEntry.getMethod() == 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void checkRequestedFeatures(ZipEntry zipEntry) throws UnsupportedZipFeatureException {
        if (!supportsEncryptionOf(zipEntry)) {
            throw new UnsupportedZipFeatureException(UnsupportedZipFeatureException.Feature.ENCRYPTION, zipEntry);
        } else if (!supportsMethodOf(zipEntry)) {
            throw new UnsupportedZipFeatureException(UnsupportedZipFeatureException.Feature.METHOD, zipEntry);
        }
    }
}
