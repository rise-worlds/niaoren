package com.lody.virtual.server.p063pm;

import android.app.IStopUserCallback;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.util.SparseArray;
import android.util.Xml;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.lody.virtual.C1713R;
import com.lody.virtual.client.env.Constants;
import com.lody.virtual.helper.utils.ArrayUtils;
import com.lody.virtual.helper.utils.AtomicFile;
import com.lody.virtual.helper.utils.FastXmlSerializer;
import com.lody.virtual.helper.utils.VLog;
import com.lody.virtual.p061os.VEnvironment;
import com.lody.virtual.p061os.VUserHandle;
import com.lody.virtual.p061os.VUserInfo;
import com.lody.virtual.p061os.VUserManager;
import com.lody.virtual.server.interfaces.IUserManager;
import com.lody.virtual.server.p062am.VActivityManagerService;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.apache.commons.mail.EmailConstants;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p110z1.MemoryConstants;

/* renamed from: com.lody.virtual.server.pm.VUserManagerService */
/* loaded from: classes.dex */
public class VUserManagerService extends IUserManager.Stub {
    private static final String ATTR_CREATION_TIME = "created";
    private static final String ATTR_FLAGS = "flags";
    private static final String ATTR_ICON_PATH = "icon";
    private static final String ATTR_ID = "id";
    private static final String ATTR_LAST_LOGGED_IN_TIME = "lastLoggedIn";
    private static final String ATTR_NEXT_SERIAL_NO = "nextSerialNumber";
    private static final String ATTR_PARTIAL = "partial";
    private static final String ATTR_SERIAL_NO = "serialNumber";
    private static final String ATTR_USER_VERSION = "version";
    private static final boolean DBG = false;
    private static final long EPOCH_PLUS_30_YEARS = 946080000000L;
    private static final String LOG_TAG = "VUserManagerService";
    private static final int MIN_USER_ID = 1;
    private static final String TAG_NAME = "name";
    private static final String TAG_USER = "user";
    private static final String TAG_USERS = "users";
    private static final String USER_INFO_DIR = "system" + File.separator + TAG_USERS;
    private static final String USER_LIST_FILENAME = "userlist.xml";
    private static final String USER_PHOTO_FILENAME = "photo.png";
    private static final int USER_VERSION = 1;
    private static VUserManagerService sInstance;
    private final File mBaseUserPath;
    private final Context mContext;
    private boolean mGuestEnabled;
    private final Object mInstallLock;
    private int mNextSerialNumber;
    private int mNextUserId;
    private final Object mPackagesLock;
    private final VPackageManagerService mPm;
    private HashSet<Integer> mRemovingUserIds;
    private int[] mUserIds;
    private final File mUserListFile;
    private int mUserVersion;
    private SparseArray<VUserInfo> mUsers;
    private final File mUsersDir;

    @Override // com.lody.virtual.server.interfaces.IUserManager
    public void wipeUser(int i) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public VUserManagerService(Context context, VPackageManagerService vPackageManagerService, Object obj, Object obj2) {
        this(context, vPackageManagerService, obj, obj2, VEnvironment.getDataDirectory(), new File(VEnvironment.getDataDirectory(), "user"));
    }

    private VUserManagerService(Context context, VPackageManagerService vPackageManagerService, Object obj, Object obj2, File file, File file2) {
        this.mUsers = new SparseArray<>();
        this.mRemovingUserIds = new HashSet<>();
        this.mNextUserId = 1;
        this.mUserVersion = 0;
        this.mContext = context;
        this.mPm = vPackageManagerService;
        this.mInstallLock = obj;
        this.mPackagesLock = obj2;
        synchronized (this.mInstallLock) {
            synchronized (this.mPackagesLock) {
                this.mUsersDir = new File(file, USER_INFO_DIR);
                this.mUsersDir.mkdirs();
                new File(this.mUsersDir, ResultTypeConstant.f7213z).mkdirs();
                this.mBaseUserPath = file2;
                this.mUserListFile = new File(this.mUsersDir, USER_LIST_FILENAME);
                readUserListLocked();
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < this.mUsers.size(); i++) {
                    VUserInfo valueAt = this.mUsers.valueAt(i);
                    if (valueAt.partial && i != 0) {
                        arrayList.add(valueAt);
                    }
                }
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    VUserInfo vUserInfo = (VUserInfo) arrayList.get(i2);
                    VLog.m18986w(LOG_TAG, "Removing partially created user #" + i2 + " (name=" + vUserInfo.name + ")", new Object[0]);
                    removeUserStateLocked(vUserInfo.f10500id);
                }
                sInstance = this;
            }
        }
    }

    public static VUserManagerService get() {
        VUserManagerService vUserManagerService;
        synchronized (VUserManagerService.class) {
            vUserManagerService = sInstance;
        }
        return vUserManagerService;
    }

    @Override // com.lody.virtual.server.interfaces.IUserManager
    public List<VUserInfo> getUsers(boolean z) {
        ArrayList arrayList;
        synchronized (this.mPackagesLock) {
            arrayList = new ArrayList(this.mUsers.size());
            for (int i = 0; i < this.mUsers.size(); i++) {
                VUserInfo valueAt = this.mUsers.valueAt(i);
                if (!valueAt.partial && (!z || !this.mRemovingUserIds.contains(Integer.valueOf(valueAt.f10500id)))) {
                    arrayList.add(valueAt);
                }
            }
        }
        return arrayList;
    }

    @Override // com.lody.virtual.server.interfaces.IUserManager
    public VUserInfo getUserInfo(int i) {
        VUserInfo userInfoLocked;
        synchronized (this.mPackagesLock) {
            userInfoLocked = getUserInfoLocked(i);
        }
        return userInfoLocked;
    }

    private VUserInfo getUserInfoLocked(int i) {
        VUserInfo vUserInfo = this.mUsers.get(i);
        if (vUserInfo == null || !vUserInfo.partial || this.mRemovingUserIds.contains(Integer.valueOf(i))) {
            return vUserInfo;
        }
        VLog.m18986w(LOG_TAG, "getUserInfo: unknown user #" + i, new Object[0]);
        return null;
    }

    public boolean exists(int i) {
        boolean contains;
        synchronized (this.mPackagesLock) {
            contains = ArrayUtils.contains(this.mUserIds, i);
        }
        return contains;
    }

    @Override // com.lody.virtual.server.interfaces.IUserManager
    public void setUserName(int i, String str) {
        synchronized (this.mPackagesLock) {
            VUserInfo vUserInfo = this.mUsers.get(i);
            boolean z = false;
            if (vUserInfo != null && !vUserInfo.partial) {
                if (str != null && !str.equals(vUserInfo.name)) {
                    vUserInfo.name = str;
                    writeUserLocked(vUserInfo);
                    z = true;
                }
                if (z) {
                    sendUserInfoChangedBroadcast(i);
                    return;
                }
                return;
            }
            VLog.m18986w(LOG_TAG, "setUserName: unknown user #" + i, new Object[0]);
        }
    }

    @Override // com.lody.virtual.server.interfaces.IUserManager
    public void setUserIcon(int i, Bitmap bitmap) {
        synchronized (this.mPackagesLock) {
            VUserInfo vUserInfo = this.mUsers.get(i);
            if (vUserInfo != null && !vUserInfo.partial) {
                writeBitmapLocked(vUserInfo, bitmap);
                writeUserLocked(vUserInfo);
                sendUserInfoChangedBroadcast(i);
                return;
            }
            VLog.m18986w(LOG_TAG, "setUserIcon: unknown user #" + i, new Object[0]);
        }
    }

    private void sendUserInfoChangedBroadcast(int i) {
        Intent intent = new Intent(Constants.ACTION_USER_INFO_CHANGED);
        intent.putExtra(Constants.EXTRA_USER_HANDLE, i);
        intent.addFlags(MemoryConstants.f21646d);
        VActivityManagerService.get().sendBroadcastAsUser(intent, new VUserHandle(i));
    }

    @Override // com.lody.virtual.server.interfaces.IUserManager
    public Bitmap getUserIcon(int i) {
        synchronized (this.mPackagesLock) {
            VUserInfo vUserInfo = this.mUsers.get(i);
            if (vUserInfo != null && !vUserInfo.partial) {
                if (vUserInfo.iconPath == null) {
                    return null;
                }
                return BitmapFactory.decodeFile(vUserInfo.iconPath);
            }
            VLog.m18986w(LOG_TAG, "getUserIcon: unknown user #" + i, new Object[0]);
            return null;
        }
    }

    @Override // com.lody.virtual.server.interfaces.IUserManager
    public boolean isGuestEnabled() {
        boolean z;
        synchronized (this.mPackagesLock) {
            z = this.mGuestEnabled;
        }
        return z;
    }

    @Override // com.lody.virtual.server.interfaces.IUserManager
    public void setGuestEnabled(boolean z) {
        synchronized (this.mPackagesLock) {
            if (this.mGuestEnabled != z) {
                this.mGuestEnabled = z;
                for (int i = 0; i < this.mUsers.size(); i++) {
                    VUserInfo valueAt = this.mUsers.valueAt(i);
                    if (!valueAt.partial && valueAt.isGuest()) {
                        if (!z) {
                            removeUser(valueAt.f10500id);
                        }
                        return;
                    }
                }
                if (z) {
                    createUser("Guest", 4);
                }
            }
        }
    }

    public void makeInitialized(int i) {
        synchronized (this.mPackagesLock) {
            VUserInfo vUserInfo = this.mUsers.get(i);
            if (vUserInfo == null || vUserInfo.partial) {
                VLog.m18986w(LOG_TAG, "makeInitialized: unknown user #" + i, new Object[0]);
            }
            if ((vUserInfo.flags & 16) == 0) {
                vUserInfo.flags |= 16;
                writeUserLocked(vUserInfo);
            }
        }
    }

    private boolean isUserLimitReachedLocked() {
        return this.mUsers.size() >= VUserManager.getMaxSupportedUsers();
    }

    private void writeBitmapLocked(VUserInfo vUserInfo, Bitmap bitmap) {
        try {
            File file = new File(this.mUsersDir, Integer.toString(vUserInfo.f10500id));
            File file2 = new File(file, USER_PHOTO_FILENAME);
            if (!file.exists()) {
                file.mkdir();
            }
            Bitmap.CompressFormat compressFormat = Bitmap.CompressFormat.PNG;
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            if (bitmap.compress(compressFormat, 100, fileOutputStream)) {
                vUserInfo.iconPath = file2.getAbsolutePath();
            }
            try {
                fileOutputStream.close();
            } catch (IOException unused) {
            }
        } catch (FileNotFoundException e) {
            VLog.m18986w(LOG_TAG, "Error setting photo for user ", e);
        }
    }

    public int[] getUserIds() {
        int[] iArr;
        synchronized (this.mPackagesLock) {
            iArr = this.mUserIds;
        }
        return iArr;
    }

    int[] getUserIdsLPr() {
        return this.mUserIds;
    }

    private void readUserList() {
        synchronized (this.mPackagesLock) {
            readUserListLocked();
        }
    }

    private void readUserListLocked() {
        Throwable th;
        XmlPullParser newPullParser;
        int next;
        VUserInfo readUser;
        this.mGuestEnabled = false;
        if (!this.mUserListFile.exists()) {
            fallbackToSingleUserLocked();
            return;
        }
        FileInputStream fileInputStream = null;
        try {
            try {
                try {
                    fileInputStream = new AtomicFile(this.mUserListFile).openRead();
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (IOException unused) {
            } catch (XmlPullParserException unused2) {
            }
            try {
                newPullParser = Xml.newPullParser();
                newPullParser.setInput(fileInputStream, null);
                while (true) {
                    next = newPullParser.next();
                    if (next == 2 || next == 1) {
                        break;
                    }
                }
            } catch (IOException unused3) {
                fileInputStream = fileInputStream;
                fallbackToSingleUserLocked();
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return;
            } catch (XmlPullParserException unused4) {
                fileInputStream = fileInputStream;
                fallbackToSingleUserLocked();
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return;
            } catch (Throwable th3) {
                th = th3;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                throw th;
            }
            if (next != 2) {
                VLog.m18992e(LOG_TAG, "Unable to read user list");
                fallbackToSingleUserLocked();
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } else {
                this.mNextSerialNumber = -1;
                if (newPullParser.getName().equals(TAG_USERS)) {
                    String attributeValue = newPullParser.getAttributeValue(null, ATTR_NEXT_SERIAL_NO);
                    if (attributeValue != null) {
                        this.mNextSerialNumber = Integer.parseInt(attributeValue);
                    }
                    String attributeValue2 = newPullParser.getAttributeValue(null, "version");
                    if (attributeValue2 != null) {
                        this.mUserVersion = Integer.parseInt(attributeValue2);
                    }
                }
                while (true) {
                    int next2 = newPullParser.next();
                    if (next2 == 1) {
                        break;
                    } else if (next2 == 2 && newPullParser.getName().equals("user") && (readUser = readUser(Integer.parseInt(newPullParser.getAttributeValue(null, "id")))) != null) {
                        this.mUsers.put(readUser.f10500id, readUser);
                        if (readUser.isGuest()) {
                            this.mGuestEnabled = true;
                        }
                        if (this.mNextSerialNumber < 0 || this.mNextSerialNumber <= readUser.f10500id) {
                            this.mNextSerialNumber = readUser.f10500id + 1;
                        }
                    }
                }
                updateUserIdsLocked();
                upgradeIfNecessary();
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            }
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    private void upgradeIfNecessary() {
        int i = this.mUserVersion;
        if (i < 1) {
            VUserInfo vUserInfo = this.mUsers.get(0);
            if ("Primary".equals(vUserInfo.name)) {
                vUserInfo.name = "Admin";
                writeUserLocked(vUserInfo);
            }
            i = 1;
        }
        if (i < 1) {
            VLog.m18986w(LOG_TAG, "User version " + this.mUserVersion + " didn't upgrade as expected to 1", new Object[0]);
            return;
        }
        this.mUserVersion = i;
        writeUserListLocked();
    }

    private void fallbackToSingleUserLocked() {
        VUserInfo vUserInfo = new VUserInfo(0, this.mContext.getResources().getString(C1713R.string.owner_name), null, 19);
        this.mUsers.put(0, vUserInfo);
        this.mNextSerialNumber = 1;
        updateUserIdsLocked();
        writeUserListLocked();
        writeUserLocked(vUserInfo);
    }

    private void writeUserLocked(VUserInfo vUserInfo) {
        FileOutputStream fileOutputStream;
        Exception e;
        AtomicFile atomicFile = new AtomicFile(new File(this.mUsersDir, vUserInfo.f10500id + ".xml"));
        try {
            fileOutputStream = atomicFile.startWrite();
        } catch (Exception e2) {
            e = e2;
            fileOutputStream = null;
        }
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            FastXmlSerializer fastXmlSerializer = new FastXmlSerializer();
            fastXmlSerializer.setOutput(bufferedOutputStream, EmailConstants.UTF_8);
            fastXmlSerializer.startDocument(null, true);
            fastXmlSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
            fastXmlSerializer.startTag(null, "user");
            fastXmlSerializer.attribute(null, "id", Integer.toString(vUserInfo.f10500id));
            fastXmlSerializer.attribute(null, ATTR_SERIAL_NO, Integer.toString(vUserInfo.serialNumber));
            fastXmlSerializer.attribute(null, ATTR_FLAGS, Integer.toString(vUserInfo.flags));
            fastXmlSerializer.attribute(null, ATTR_CREATION_TIME, Long.toString(vUserInfo.creationTime));
            fastXmlSerializer.attribute(null, ATTR_LAST_LOGGED_IN_TIME, Long.toString(vUserInfo.lastLoggedInTime));
            if (vUserInfo.iconPath != null) {
                fastXmlSerializer.attribute(null, ATTR_ICON_PATH, vUserInfo.iconPath);
            }
            if (vUserInfo.partial) {
                fastXmlSerializer.attribute(null, ATTR_PARTIAL, "true");
            }
            fastXmlSerializer.startTag(null, "name");
            fastXmlSerializer.text(vUserInfo.name);
            fastXmlSerializer.endTag(null, "name");
            fastXmlSerializer.endTag(null, "user");
            fastXmlSerializer.endDocument();
            atomicFile.finishWrite(fileOutputStream);
        } catch (Exception e3) {
            e = e3;
            VLog.m18992e(LOG_TAG, "Error writing user info " + vUserInfo.f10500id + "\n" + e);
            atomicFile.failWrite(fileOutputStream);
        }
    }

    private void writeUserListLocked() {
        FileOutputStream fileOutputStream;
        AtomicFile atomicFile = new AtomicFile(this.mUserListFile);
        try {
            fileOutputStream = atomicFile.startWrite();
            try {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                FastXmlSerializer fastXmlSerializer = new FastXmlSerializer();
                fastXmlSerializer.setOutput(bufferedOutputStream, EmailConstants.UTF_8);
                fastXmlSerializer.startDocument(null, true);
                fastXmlSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
                fastXmlSerializer.startTag(null, TAG_USERS);
                fastXmlSerializer.attribute(null, ATTR_NEXT_SERIAL_NO, Integer.toString(this.mNextSerialNumber));
                fastXmlSerializer.attribute(null, "version", Integer.toString(this.mUserVersion));
                for (int i = 0; i < this.mUsers.size(); i++) {
                    fastXmlSerializer.startTag(null, "user");
                    fastXmlSerializer.attribute(null, "id", Integer.toString(this.mUsers.valueAt(i).f10500id));
                    fastXmlSerializer.endTag(null, "user");
                }
                fastXmlSerializer.endTag(null, TAG_USERS);
                fastXmlSerializer.endDocument();
                atomicFile.finishWrite(fileOutputStream);
            } catch (Exception unused) {
                atomicFile.failWrite(fileOutputStream);
                VLog.m18992e(LOG_TAG, "Error writing user list");
            }
        } catch (Exception unused2) {
            fileOutputStream = null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x0101, code lost:
        if (r3 == null) goto L_0x010b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0103, code lost:
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0108, code lost:
        if (r3 == null) goto L_0x010b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x010b, code lost:
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.lody.virtual.p061os.VUserInfo readUser(int r18) {
        /*
            Method dump skipped, instructions count: 268
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lody.virtual.server.p063pm.VUserManagerService.readUser(int):com.lody.virtual.os.VUserInfo");
    }

    private int readIntAttribute(XmlPullParser xmlPullParser, String str, int i) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        if (attributeValue == null) {
            return i;
        }
        try {
            return Integer.parseInt(attributeValue);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    private long readLongAttribute(XmlPullParser xmlPullParser, String str, long j) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        if (attributeValue == null) {
            return j;
        }
        try {
            return Long.parseLong(attributeValue);
        } catch (NumberFormatException unused) {
            return j;
        }
    }

    @Override // com.lody.virtual.server.interfaces.IUserManager
    public VUserInfo createUser(String str, int i) {
        long clearCallingIdentity = Binder.clearCallingIdentity();
        try {
            synchronized (this.mInstallLock) {
                synchronized (this.mPackagesLock) {
                    if (isUserLimitReachedLocked()) {
                        return null;
                    }
                    int nextAvailableIdLocked = getNextAvailableIdLocked();
                    VUserInfo vUserInfo = new VUserInfo(nextAvailableIdLocked, str, null, i);
                    File file = new File(this.mBaseUserPath, Integer.toString(nextAvailableIdLocked));
                    int i2 = this.mNextSerialNumber;
                    this.mNextSerialNumber = i2 + 1;
                    vUserInfo.serialNumber = i2;
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis <= EPOCH_PLUS_30_YEARS) {
                        currentTimeMillis = 0;
                    }
                    vUserInfo.creationTime = currentTimeMillis;
                    vUserInfo.partial = true;
                    VAppManagerService.get().onUserCreated(vUserInfo);
                    this.mUsers.put(nextAvailableIdLocked, vUserInfo);
                    writeUserListLocked();
                    writeUserLocked(vUserInfo);
                    this.mPm.createNewUser(nextAvailableIdLocked, file);
                    vUserInfo.partial = false;
                    writeUserLocked(vUserInfo);
                    updateUserIdsLocked();
                    Intent intent = new Intent(Constants.ACTION_USER_ADDED);
                    intent.putExtra(Constants.EXTRA_USER_HANDLE, vUserInfo.f10500id);
                    VActivityManagerService.get().sendBroadcastAsUser(intent, VUserHandle.ALL, null);
                    return vUserInfo;
                }
            }
        } finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
    }

    @Override // com.lody.virtual.server.interfaces.IUserManager
    public boolean removeUser(int i) {
        synchronized (this.mPackagesLock) {
            VUserInfo vUserInfo = this.mUsers.get(i);
            if (!(i == 0 || vUserInfo == null)) {
                this.mRemovingUserIds.add(Integer.valueOf(i));
                vUserInfo.partial = true;
                writeUserLocked(vUserInfo);
                return VActivityManagerService.get().stopUser(i, new IStopUserCallback.Stub() { // from class: com.lody.virtual.server.pm.VUserManagerService.1
                    @Override // android.app.IStopUserCallback
                    public void userStopAborted(int i2) {
                    }

                    @Override // android.app.IStopUserCallback
                    public void userStopped(int i2) {
                        VUserManagerService.this.finishRemoveUser(i2);
                    }
                }) == 0;
            }
            return false;
        }
    }

    void finishRemoveUser(final int i) {
        long clearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Intent intent = new Intent(Constants.ACTION_USER_REMOVED);
            intent.putExtra(Constants.EXTRA_USER_HANDLE, i);
            VActivityManagerService.get().sendOrderedBroadcastAsUser(intent, VUserHandle.ALL, null, new BroadcastReceiver() { // from class: com.lody.virtual.server.pm.VUserManagerService.2
                /* JADX WARN: Type inference failed for: r1v1, types: [com.lody.virtual.server.pm.VUserManagerService$2$1] */
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent2) {
                    new Thread() { // from class: com.lody.virtual.server.pm.VUserManagerService.2.1
                        @Override // java.lang.Thread, java.lang.Runnable
                        public void run() {
                            synchronized (VUserManagerService.this.mInstallLock) {
                                synchronized (VUserManagerService.this.mPackagesLock) {
                                    VUserManagerService.this.removeUserStateLocked(i);
                                }
                            }
                        }
                    }.start();
                }
            }, null, -1, null, null);
        } finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeUserStateLocked(int i) {
        this.mPm.cleanUpUser(i);
        this.mUsers.remove(i);
        this.mRemovingUserIds.remove(Integer.valueOf(i));
        File file = this.mUsersDir;
        new AtomicFile(new File(file, i + ".xml")).delete();
        writeUserListLocked();
        updateUserIdsLocked();
        removeDirectoryRecursive(VEnvironment.getUserSystemDirectory(i));
    }

    private void removeDirectoryRecursive(File file) {
        if (file.isDirectory()) {
            for (String str : file.list()) {
                removeDirectoryRecursive(new File(file, str));
            }
        }
        file.delete();
    }

    @Override // com.lody.virtual.server.interfaces.IUserManager
    public int getUserSerialNumber(int i) {
        synchronized (this.mPackagesLock) {
            if (!exists(i)) {
                return -1;
            }
            return getUserInfoLocked(i).serialNumber;
        }
    }

    @Override // com.lody.virtual.server.interfaces.IUserManager
    public int getUserHandle(int i) {
        int[] iArr;
        synchronized (this.mPackagesLock) {
            for (int i2 : this.mUserIds) {
                if (getUserInfoLocked(i2).serialNumber == i) {
                    return i2;
                }
            }
            return -1;
        }
    }

    private void updateUserIdsLocked() {
        int i = 0;
        for (int i2 = 0; i2 < this.mUsers.size(); i2++) {
            if (!this.mUsers.valueAt(i2).partial) {
                i++;
            }
        }
        int[] iArr = new int[i];
        int i3 = 0;
        for (int i4 = 0; i4 < this.mUsers.size(); i4++) {
            if (!this.mUsers.valueAt(i4).partial) {
                i3++;
                iArr[i3] = this.mUsers.keyAt(i4);
            }
        }
        this.mUserIds = iArr;
    }

    public void userForeground(int i) {
        synchronized (this.mPackagesLock) {
            VUserInfo vUserInfo = this.mUsers.get(i);
            long currentTimeMillis = System.currentTimeMillis();
            if (vUserInfo != null && !vUserInfo.partial) {
                if (currentTimeMillis > EPOCH_PLUS_30_YEARS) {
                    vUserInfo.lastLoggedInTime = currentTimeMillis;
                    writeUserLocked(vUserInfo);
                }
                return;
            }
            VLog.m18986w(LOG_TAG, "userForeground: unknown user #" + i, new Object[0]);
        }
    }

    private int getNextAvailableIdLocked() {
        int i;
        synchronized (this.mPackagesLock) {
            i = this.mNextUserId;
            while (i < Integer.MAX_VALUE && (this.mUsers.indexOfKey(i) >= 0 || this.mRemovingUserIds.contains(Integer.valueOf(i)))) {
                i++;
            }
            this.mNextUserId = i + 1;
        }
        return i;
    }
}
