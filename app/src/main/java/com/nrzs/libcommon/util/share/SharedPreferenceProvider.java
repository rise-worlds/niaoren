package com.nrzs.libcommon.util.share;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p003v4.util.ArrayMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
public class SharedPreferenceProvider extends ContentProvider {

    /* renamed from: a */
    private Map<String, AbstractC2191a> f11181a = new ArrayMap();

    /* renamed from: b */
    private AbstractC2191a f11182b = new AbstractC2191a() { // from class: com.nrzs.libcommon.util.share.SharedPreferenceProvider.1
        @Override // com.nrzs.libcommon.util.share.SharedPreferenceProvider.AbstractC2191a
        /* renamed from: a */
        public Bundle mo18541a(@Nullable String str, @Nullable Bundle bundle) {
            Bundle bundle2 = new Bundle();
            bundle2.putInt(PreferenceUtil.f11213g, Process.myPid());
            return bundle2;
        }
    };

    /* renamed from: c */
    private AbstractC2191a f11183c = new AbstractC2191a() { // from class: com.nrzs.libcommon.util.share.SharedPreferenceProvider.2
        @Override // com.nrzs.libcommon.util.share.SharedPreferenceProvider.AbstractC2191a
        /* renamed from: a */
        public Bundle mo18541a(@Nullable String str, @Nullable Bundle bundle) {
            if (bundle != null) {
                Context context = SharedPreferenceProvider.this.getContext();
                if (context != null) {
                    String string = bundle.getString("key_key");
                    SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
                    int i = bundle.getInt("key_value_type");
                    switch (i) {
                        case 1:
                            bundle.putString("key_value", sharedPreferences.getString(string, bundle.getString("key_value")));
                            return bundle;
                        case 2:
                            bundle.putInt("key_value", sharedPreferences.getInt(string, bundle.getInt("key_value")));
                            return bundle;
                        case 3:
                            bundle.putLong("key_value", sharedPreferences.getLong(string, bundle.getLong("key_value")));
                            return bundle;
                        case 4:
                            bundle.putFloat("key_value", sharedPreferences.getFloat(string, bundle.getFloat("key_value")));
                            return bundle;
                        case 5:
                            bundle.putBoolean("key_value", sharedPreferences.getBoolean(string, bundle.getBoolean("key_value")));
                            return bundle;
                        case 6:
                            ArrayList<String> arrayList = null;
                            Set<String> stringSet = sharedPreferences.getStringSet(string, null);
                            if (stringSet != null) {
                                arrayList = new ArrayList<>(stringSet);
                            }
                            bundle.putStringArrayList("key_value", arrayList);
                            return bundle;
                        default:
                            throw new IllegalArgumentException("unknown valueType:" + i);
                    }
                } else {
                    throw new IllegalArgumentException("methodQueryValues, ctx is null!");
                }
            } else {
                throw new IllegalArgumentException("methodQueryValues, extras is null!");
            }
        }
    };

    /* renamed from: d */
    private AbstractC2191a f11184d = new AbstractC2191a() { // from class: com.nrzs.libcommon.util.share.SharedPreferenceProvider.3
        @Override // com.nrzs.libcommon.util.share.SharedPreferenceProvider.AbstractC2191a
        /* renamed from: a */
        public Bundle mo18541a(@Nullable String str, @Nullable Bundle bundle) {
            if (bundle != null) {
                Context context = SharedPreferenceProvider.this.getContext();
                if (context != null) {
                    bundle.putBoolean(PreferenceUtil.f11213g, context.getSharedPreferences(str, 0).contains(bundle.getString("key_key")));
                    return bundle;
                }
                throw new IllegalArgumentException("methodQueryValues, ctx is null!");
            }
            throw new IllegalArgumentException("methodQueryValues, extras is null!");
        }
    };

    /* renamed from: e */
    private AbstractC2191a f11185e = new AbstractC2191a() { // from class: com.nrzs.libcommon.util.share.SharedPreferenceProvider.4
        @Override // com.nrzs.libcommon.util.share.SharedPreferenceProvider.AbstractC2191a
        /* renamed from: a */
        public Bundle mo18541a(@Nullable String str, @Nullable Bundle bundle) {
            if (bundle != null) {
                Context context = SharedPreferenceProvider.this.getContext();
                if (context != null) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
                    ArrayList parcelableArrayList = bundle.getParcelableArrayList(PreferenceUtil.f11213g);
                    if (parcelableArrayList == null) {
                        parcelableArrayList = new ArrayList();
                    }
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    Iterator it = parcelableArrayList.iterator();
                    while (it.hasNext()) {
                        Bundle bundle2 = (Bundle) it.next();
                        int i = bundle2.getInt("key_op_type");
                        switch (i) {
                            case 2:
                                edit = m18542a(edit, bundle2);
                                break;
                            case 3:
                                edit = edit.clear();
                                break;
                            case 4:
                                edit = edit.remove(bundle2.getString("key_key"));
                                break;
                            default:
                                throw new IllegalArgumentException("unkonw op type:" + i);
                        }
                    }
                    int i2 = bundle.getInt("key_op_type");
                    if (i2 == 6) {
                        edit.apply();
                        return null;
                    } else if (i2 == 5) {
                        boolean commit = edit.commit();
                        Bundle bundle3 = new Bundle();
                        bundle3.putBoolean(PreferenceUtil.f11213g, commit);
                        return bundle3;
                    } else {
                        throw new IllegalArgumentException("unknown applyOrCommit:" + i2);
                    }
                } else {
                    throw new IllegalArgumentException("methodQueryValues, ctx is null!");
                }
            } else {
                throw new IllegalArgumentException("methodQueryValues, extras is null!");
            }
        }

        /* renamed from: a */
        private SharedPreferences.Editor m18542a(SharedPreferences.Editor editor, Bundle bundle) {
            String string = bundle.getString("key_key");
            int i = bundle.getInt("key_value_type");
            switch (i) {
                case 1:
                    return editor.putString(string, bundle.getString("key_value"));
                case 2:
                    return editor.putInt(string, bundle.getInt("key_value"));
                case 3:
                    return editor.putLong(string, bundle.getLong("key_value"));
                case 4:
                    return editor.putFloat(string, bundle.getFloat("key_value"));
                case 5:
                    return editor.putBoolean(string, bundle.getBoolean("key_value"));
                case 6:
                    ArrayList<String> stringArrayList = bundle.getStringArrayList("key_value");
                    if (stringArrayList == null) {
                        return editor.putStringSet(string, null);
                    }
                    return editor.putStringSet(string, new HashSet(stringArrayList));
                default:
                    throw new IllegalArgumentException("unknown valueType:" + i);
            }
        }
    };

    /* renamed from: com.nrzs.libcommon.util.share.SharedPreferenceProvider$a */
    /* loaded from: classes2.dex */
    public interface AbstractC2191a {
        /* renamed from: a */
        Bundle mo18541a(@Nullable String str, @Nullable Bundle bundle);
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        this.f11181a.put(PreferenceUtil.f11210d, this.f11183c);
        this.f11181a.put(PreferenceUtil.f11207a, this.f11184d);
        this.f11181a.put(PreferenceUtil.f11211e, this.f11185e);
        this.f11181a.put(PreferenceUtil.f11212f, this.f11182b);
        return true;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public Cursor query(@NonNull Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContentProvider
    @Nullable
    public String getType(@NonNull Uri uri) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContentProvider
    @Nullable
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContentProvider
    public int delete(@NonNull Uri uri, @Nullable String str, @Nullable String[] strArr) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContentProvider
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String str, @Nullable String[] strArr) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContentProvider
    @Nullable
    public Bundle call(@NonNull String str, @Nullable String str2, @Nullable Bundle bundle) {
        AbstractC2191a aVar = this.f11181a.get(str);
        if (aVar == null) {
            return null;
        }
        return aVar.mo18541a(str2, bundle);
    }
}
