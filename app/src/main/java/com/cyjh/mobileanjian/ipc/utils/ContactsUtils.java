package com.cyjh.mobileanjian.ipc.utils;

import android.content.ContentProviderOperation;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;

/* renamed from: com.cyjh.mobileanjian.ipc.utils.d */
/* loaded from: classes.dex */
public class ContactsUtils {

    /* renamed from: a */
    private static final String f8679a = "d";

    /* renamed from: a */
    public static boolean m20655a(Context context, String str, String str2, String str3) {
        ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
        arrayList.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI).withValue("account_type", null).withValue("account_name", null).build());
        arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/name").withValue("data2", str2).withValue("data3", str).build());
        arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/phone_v2").withValue("data1", str3).withValue("data2", 2).build());
        try {
            context.getContentResolver().applyBatch("com.android.contacts", arrayList);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, e.getMessage(), 1).show();
            return false;
        }
    }

    /* renamed from: a */
    public static void m20656a(Context context, String str) {
        Cursor query = context.getContentResolver().query(ContactsContract.Data.CONTENT_URI, new String[]{"raw_contact_id"}, "data1=?", new String[]{str}, null);
        try {
            if (query.moveToFirst()) {
                do {
                    context.getContentResolver().delete(ContentUris.withAppendedId(ContactsContract.RawContacts.CONTENT_URI, query.getInt(0)), null, null);
                } while (query.moveToNext());
            }
        } catch (Exception e) {
            Log.e(f8679a, e.getMessage());
        } finally {
            query.close();
        }
    }

    /* renamed from: a */
    public static void m20657a(Context context) {
        context.getContentResolver().delete(ContactsContract.RawContacts.CONTENT_URI, null, null);
    }
}
