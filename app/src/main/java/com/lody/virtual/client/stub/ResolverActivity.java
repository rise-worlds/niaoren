package com.lody.virtual.client.stub;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PatternMatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.lody.virtual.C1713R;
import com.lody.virtual.client.VClient;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.env.Constants;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.lody.virtual.client.ipc.VActivityManager;
import com.lody.virtual.helper.utils.Reflect;
import com.lody.virtual.helper.utils.VLog;
import com.lody.virtual.os.VUserHandle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public class ResolverActivity extends Activity implements AdapterView.OnItemClickListener {
    private static final boolean DEBUG = false;
    private static final String TAG = "ResolverActivity";
    private AlertDialog dialog;
    private ResolveListAdapter mAdapter;
    private Button mAlwaysButton;
    private boolean mAlwaysUseOption;
    private int mIconDpi;
    private int mIconSize;
    private int mLastSelected = -1;
    private int mLaunchedFromUid;
    private ListView mListView;
    private int mMaxColumns;
    private Button mOnceButton;
    protected Bundle mOptions;
    private PackageManager mPm;
    private boolean mRegistered;
    protected int mRequestCode;
    protected IBinder mResultTo;
    protected String mResultWho;
    private boolean mShowExtended;

    private Intent makeMyIntent() {
        Intent intent = new Intent(getIntent());
        intent.setComponent(null);
        intent.setFlags(intent.getFlags() & (-8388609));
        return intent;
    }

    @Override // android.app.Activity
    @SuppressLint({"MissingSuperCall"})
    protected void onCreate(Bundle bundle) {
        int i;
        Intent makeMyIntent = makeMyIntent();
        Set<String> categories = makeMyIntent.getCategories();
        if (!"android.intent.action.MAIN".equals(makeMyIntent.getAction()) || categories == null || categories.size() != 1 || !categories.contains("android.intent.category.HOME")) {
            i = C1713R.string.choose;
        } else {
            i = C1713R.string.choose;
        }
        onCreate(bundle, makeMyIntent, getResources().getText(i), null, null, true, makeMyIntent.getIntExtra(Constants.EXTRA_USER_HANDLE, VUserHandle.getCallingUserId()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onCreate(Bundle bundle, Intent intent, CharSequence charSequence, Intent[] intentArr, List<ResolveInfo> list, boolean z, int i) {
        super.onCreate(bundle);
        this.mLaunchedFromUid = i;
        this.mPm = getPackageManager();
        this.mAlwaysUseOption = z;
        this.mMaxColumns = getResources().getInteger(C1713R.integer.config_maxResolverActivityColumns);
        this.mRegistered = true;
        ActivityManager activityManager = (ActivityManager) getSystemService(ServiceManagerNative.ACTIVITY);
        this.mIconDpi = activityManager.getLauncherLargeIconDensity();
        this.mIconSize = activityManager.getLauncherLargeIconSize();
        this.mAdapter = new ResolveListAdapter(this, intent, intentArr, list, this.mLaunchedFromUid);
        int count = this.mAdapter.getCount();
        if (Build.VERSION.SDK_INT >= 17 && this.mLaunchedFromUid < 0) {
            finish();
        } else if (count == 1) {
            startSelected(0, false);
            this.mRegistered = false;
            finish();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(charSequence);
            if (count > 1) {
                this.mListView = new ListView(this);
                this.mListView.setAdapter((ListAdapter) this.mAdapter);
                this.mListView.setOnItemClickListener(this);
                this.mListView.setOnItemLongClickListener(new ItemLongClickListener());
                builder.setView(this.mListView);
                if (z) {
                    this.mListView.setChoiceMode(1);
                }
            } else {
                builder.setMessage(C1713R.string.noApplications);
            }
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.lody.virtual.client.stub.ResolverActivity.1
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    ResolverActivity.this.finish();
                }
            });
            this.dialog = builder.show();
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        AlertDialog alertDialog = this.dialog;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.dialog.dismiss();
        }
        super.onDestroy();
    }

    @TargetApi(15)
    Drawable getIcon(Resources resources, int i) {
        try {
            return resources.getDrawableForDensity(i, this.mIconDpi);
        } catch (Resources.NotFoundException unused) {
            return null;
        }
    }

    Drawable loadIconForResolveInfo(ResolveInfo resolveInfo) {
        Drawable icon;
        try {
        } catch (PackageManager.NameNotFoundException e) {
            VLog.m18992e(TAG, "Couldn't find resources for package\n" + VLog.getStackTraceString(e));
        }
        if (resolveInfo.resolvePackageName != null && resolveInfo.icon != 0 && (icon = getIcon(this.mPm.getResourcesForApplication(resolveInfo.resolvePackageName), resolveInfo.icon)) != null) {
            return icon;
        }
        int iconResource = resolveInfo.getIconResource();
        if (iconResource != 0) {
            Drawable icon2 = getIcon(this.mPm.getResourcesForApplication(resolveInfo.activityInfo.packageName), iconResource);
            if (icon2 != null) {
                return icon2;
            }
        }
        return resolveInfo.loadIcon(this.mPm);
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        if (!this.mRegistered) {
            this.mRegistered = true;
        }
        this.mAdapter.handlePackagesChanged();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        if (this.mRegistered) {
            this.mRegistered = false;
        }
        if ((getIntent().getFlags() & 268435456) != 0 && !isChangingConfigurations()) {
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        if (this.mAlwaysUseOption) {
            int checkedItemPosition = this.mListView.getCheckedItemPosition();
            boolean z = checkedItemPosition != -1;
            this.mLastSelected = checkedItemPosition;
            this.mAlwaysButton.setEnabled(z);
            this.mOnceButton.setEnabled(z);
            if (z) {
                this.mListView.setSelection(checkedItemPosition);
            }
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int checkedItemPosition = this.mListView.getCheckedItemPosition();
        boolean z = checkedItemPosition != -1;
        if (!this.mAlwaysUseOption || (z && this.mLastSelected == checkedItemPosition)) {
            startSelected(i, false);
            return;
        }
        this.mAlwaysButton.setEnabled(z);
        this.mOnceButton.setEnabled(z);
        if (z) {
            this.mListView.smoothScrollToPosition(checkedItemPosition);
        }
        this.mLastSelected = checkedItemPosition;
    }

    void startSelected(int i, boolean z) {
        if (!isFinishing()) {
            onIntentSelected(this.mAdapter.resolveInfoForPosition(i), this.mAdapter.intentForPosition(i), z);
            finish();
        }
    }

    protected void onIntentSelected(ResolveInfo resolveInfo, Intent intent, boolean z) {
        String resolveType;
        if (this.mAlwaysUseOption && this.mAdapter.mOrigResolveList != null) {
            IntentFilter intentFilter = new IntentFilter();
            if (intent.getAction() != null) {
                intentFilter.addAction(intent.getAction());
            }
            Set<String> categories = intent.getCategories();
            if (categories != null) {
                for (String str : categories) {
                    intentFilter.addCategory(str);
                }
            }
            intentFilter.addCategory("android.intent.category.DEFAULT");
            int i = resolveInfo.match & 268369920;
            Uri data = intent.getData();
            String str2 = null;
            if (i == 6291456 && (resolveType = intent.resolveType(this)) != null) {
                try {
                    intentFilter.addDataType(resolveType);
                } catch (IntentFilter.MalformedMimeTypeException e) {
                    VLog.m18986w(TAG, "mimeType\n" + VLog.getStackTraceString(e), new Object[0]);
                    intentFilter = null;
                }
            }
            if (data != null && data.getScheme() != null && (i != 6291456 || (!"file".equals(data.getScheme()) && !ServiceManagerNative.CONTENT.equals(data.getScheme())))) {
                intentFilter.addDataScheme(data.getScheme());
                if (Build.VERSION.SDK_INT >= 19) {
                    Iterator<PatternMatcher> schemeSpecificPartsIterator = resolveInfo.filter.schemeSpecificPartsIterator();
                    if (schemeSpecificPartsIterator != null) {
                        String schemeSpecificPart = data.getSchemeSpecificPart();
                        while (true) {
                            if (schemeSpecificPart == null || !schemeSpecificPartsIterator.hasNext()) {
                                break;
                            }
                            PatternMatcher next = schemeSpecificPartsIterator.next();
                            if (next.match(schemeSpecificPart)) {
                                intentFilter.addDataSchemeSpecificPart(next.getPath(), next.getType());
                                break;
                            }
                        }
                    }
                    Iterator<IntentFilter.AuthorityEntry> authoritiesIterator = resolveInfo.filter.authoritiesIterator();
                    if (authoritiesIterator != null) {
                        while (true) {
                            if (!authoritiesIterator.hasNext()) {
                                break;
                            }
                            IntentFilter.AuthorityEntry next2 = authoritiesIterator.next();
                            if (next2.match(data) >= 0) {
                                int port = next2.getPort();
                                String host = next2.getHost();
                                if (port >= 0) {
                                    str2 = Integer.toString(port);
                                }
                                intentFilter.addDataAuthority(host, str2);
                            }
                        }
                    }
                    Iterator<PatternMatcher> pathsIterator = resolveInfo.filter.pathsIterator();
                    if (pathsIterator != null) {
                        String path = data.getPath();
                        while (true) {
                            if (path == null || !pathsIterator.hasNext()) {
                                break;
                            }
                            PatternMatcher next3 = pathsIterator.next();
                            if (next3.match(path)) {
                                intentFilter.addDataPath(next3.getPath(), next3.getType());
                                break;
                            }
                        }
                    }
                }
            }
            if (intentFilter != null) {
                int size = this.mAdapter.mOrigResolveList.size();
                ComponentName[] componentNameArr = new ComponentName[size];
                int i2 = 0;
                for (int i3 = 0; i3 < size; i3++) {
                    ResolveInfo resolveInfo2 = this.mAdapter.mOrigResolveList.get(i3);
                    componentNameArr[i3] = new ComponentName(resolveInfo2.activityInfo.packageName, resolveInfo2.activityInfo.name);
                    if (resolveInfo2.match > i2) {
                        i2 = resolveInfo2.match;
                    }
                }
                if (z) {
                    getPackageManager().addPreferredActivity(intentFilter, i2, componentNameArr, intent.getComponent());
                } else {
                    try {
                        Reflect.m18998on(VClient.get().getCurrentApplication().getPackageManager()).call("setLastChosenActivity", intent, intent.resolveTypeIfNeeded(getContentResolver()), 65536, intentFilter, Integer.valueOf(i2), intent.getComponent());
                    } catch (Exception e2) {
                        VLog.m18993d(TAG, "Error calling setLastChosenActivity\n" + VLog.getStackTraceString(e2), new Object[0]);
                    }
                }
            }
        }
        if (intent != null) {
            ActivityInfo resolveActivityInfo = VirtualCore.get().resolveActivityInfo(intent, this.mLaunchedFromUid);
            if (resolveActivityInfo == null) {
                startActivity(intent);
                return;
            }
            intent.addFlags(33554432);
            if (!(VActivityManager.get().startActivity(intent, resolveActivityInfo, this.mResultTo, this.mOptions, this.mResultWho, this.mRequestCode, this.mLaunchedFromUid) == 0 || this.mResultTo == null || this.mRequestCode <= 0)) {
                VActivityManager.get().sendCancelActivityResult(this.mResultTo, this.mResultWho, this.mRequestCode);
            }
        }
    }

    void showAppDetails(ResolveInfo resolveInfo) {
        startActivity(new Intent().setAction("android.settings.APPLICATION_DETAILS_SETTINGS").setData(Uri.fromParts(ServiceManagerNative.PACKAGE, resolveInfo.activityInfo.packageName, null)).addFlags(524288));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class ViewHolder {
        public ImageView icon;
        public TextView text;
        public TextView text2;

        public ViewHolder(View view) {
            this.text = (TextView) view.findViewById(C1713R.C1715id.text1);
            this.text2 = (TextView) view.findViewById(C1713R.C1715id.text2);
            this.icon = (ImageView) view.findViewById(C1713R.C1715id.icon);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class DisplayResolveInfo {
        Drawable displayIcon;
        CharSequence displayLabel;
        CharSequence extendedInfo;
        Intent origIntent;

        /* renamed from: ri */
        ResolveInfo f10499ri;

        DisplayResolveInfo(ResolveInfo resolveInfo, CharSequence charSequence, CharSequence charSequence2, Intent intent) {
            this.f10499ri = resolveInfo;
            this.displayLabel = charSequence;
            this.extendedInfo = charSequence2;
            this.origIntent = intent;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class ResolveListAdapter extends BaseAdapter {
        private final List<ResolveInfo> mBaseResolveList;
        private final LayoutInflater mInflater;
        private final Intent[] mInitialIntents;
        private final Intent mIntent;
        private ResolveInfo mLastChosen;
        private final int mLaunchedFromUid;
        List<ResolveInfo> mOrigResolveList;
        private int mInitialHighlight = -1;
        List<DisplayResolveInfo> mList = new ArrayList();

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public ResolveListAdapter(Context context, Intent intent, Intent[] intentArr, List<ResolveInfo> list, int i) {
            this.mIntent = new Intent(intent);
            this.mInitialIntents = intentArr;
            this.mBaseResolveList = list;
            this.mLaunchedFromUid = i;
            this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
            rebuildList();
        }

        public void handlePackagesChanged() {
            getCount();
            rebuildList();
            notifyDataSetChanged();
            if (getCount() == 0) {
                ResolverActivity.this.finish();
            }
        }

        public int getInitialHighlight() {
            return this.mInitialHighlight;
        }

        private void rebuildList() {
            List<ResolveInfo> list;
            int size;
            this.mList.clear();
            List<ResolveInfo> list2 = this.mBaseResolveList;
            if (list2 != null) {
                this.mOrigResolveList = null;
                list = list2;
            } else {
                List<ResolveInfo> queryIntentActivities = ResolverActivity.this.mPm.queryIntentActivities(this.mIntent, 65536 | (ResolverActivity.this.mAlwaysUseOption ? 64 : 0));
                this.mOrigResolveList = queryIntentActivities;
                list = queryIntentActivities;
            }
            if (list != null && (size = list.size()) > 0) {
                ResolveInfo resolveInfo = list.get(0);
                int i = size;
                for (int i2 = 1; i2 < i; i2++) {
                    ResolveInfo resolveInfo2 = list.get(i2);
                    if (resolveInfo.priority != resolveInfo2.priority || resolveInfo.isDefault != resolveInfo2.isDefault) {
                        while (i2 < i) {
                            List<ResolveInfo> list3 = this.mOrigResolveList;
                            if (list3 == list) {
                                this.mOrigResolveList = new ArrayList(list3);
                            }
                            list.remove(i2);
                            i--;
                        }
                    }
                }
                if (i > 1) {
                    Collections.sort(list, new ResolveInfo.DisplayNameComparator(ResolverActivity.this.mPm));
                }
                if (this.mInitialIntents != null) {
                    int i3 = 0;
                    while (true) {
                        Intent[] intentArr = this.mInitialIntents;
                        if (i3 >= intentArr.length) {
                            break;
                        }
                        Intent intent = intentArr[i3];
                        if (intent != null) {
                            ActivityInfo resolveActivityInfo = intent.resolveActivityInfo(ResolverActivity.this.getPackageManager(), 0);
                            if (resolveActivityInfo == null) {
                                VLog.m18986w(ResolverActivity.TAG, "No activity found for " + intent, new Object[0]);
                            } else {
                                ResolveInfo resolveInfo3 = new ResolveInfo();
                                resolveInfo3.activityInfo = resolveActivityInfo;
                                if (intent instanceof LabeledIntent) {
                                    LabeledIntent labeledIntent = (LabeledIntent) intent;
                                    resolveInfo3.resolvePackageName = labeledIntent.getSourcePackage();
                                    resolveInfo3.labelRes = labeledIntent.getLabelResource();
                                    resolveInfo3.nonLocalizedLabel = labeledIntent.getNonLocalizedLabel();
                                    resolveInfo3.icon = labeledIntent.getIconResource();
                                }
                                List<DisplayResolveInfo> list4 = this.mList;
                                ResolverActivity resolverActivity = ResolverActivity.this;
                                list4.add(new DisplayResolveInfo(resolveInfo3, resolveInfo3.loadLabel(resolverActivity.getPackageManager()), null, intent));
                            }
                        }
                        i3++;
                    }
                }
                ResolveInfo resolveInfo4 = list.get(0);
                CharSequence loadLabel = resolveInfo4.loadLabel(ResolverActivity.this.mPm);
                ResolverActivity.this.mShowExtended = false;
                ResolveInfo resolveInfo5 = resolveInfo4;
                int i4 = 0;
                for (int i5 = 1; i5 < i; i5++) {
                    if (loadLabel == null) {
                        loadLabel = resolveInfo5.activityInfo.packageName;
                    }
                    ResolveInfo resolveInfo6 = list.get(i5);
                    CharSequence loadLabel2 = resolveInfo6.loadLabel(ResolverActivity.this.mPm);
                    CharSequence charSequence = loadLabel2 == null ? resolveInfo6.activityInfo.packageName : loadLabel2;
                    if (!charSequence.equals(loadLabel)) {
                        processGroup(list, i4, i5 - 1, resolveInfo5, loadLabel);
                        i4 = i5;
                        resolveInfo5 = resolveInfo6;
                        loadLabel = charSequence;
                    }
                }
                processGroup(list, i4, i - 1, resolveInfo5, loadLabel);
            }
        }

        private void processGroup(List<ResolveInfo> list, int i, int i2, ResolveInfo resolveInfo, CharSequence charSequence) {
            if ((i2 - i) + 1 == 1) {
                ResolveInfo resolveInfo2 = this.mLastChosen;
                if (resolveInfo2 != null && resolveInfo2.activityInfo.packageName.equals(resolveInfo.activityInfo.packageName) && this.mLastChosen.activityInfo.name.equals(resolveInfo.activityInfo.name)) {
                    this.mInitialHighlight = this.mList.size();
                }
                this.mList.add(new DisplayResolveInfo(resolveInfo, charSequence, null, null));
                return;
            }
            ResolverActivity.this.mShowExtended = true;
            boolean z = false;
            CharSequence loadLabel = resolveInfo.activityInfo.applicationInfo.loadLabel(ResolverActivity.this.mPm);
            if (loadLabel == null) {
                z = true;
            }
            if (!z) {
                HashSet hashSet = new HashSet();
                hashSet.add(loadLabel);
                for (int i3 = i + 1; i3 <= i2; i3++) {
                    CharSequence loadLabel2 = list.get(i3).activityInfo.applicationInfo.loadLabel(ResolverActivity.this.mPm);
                    if (loadLabel2 == null || hashSet.contains(loadLabel2)) {
                        z = true;
                        break;
                    } else {
                        hashSet.add(loadLabel2);
                    }
                }
                hashSet.clear();
            }
            while (i <= i2) {
                ResolveInfo resolveInfo3 = list.get(i);
                ResolveInfo resolveInfo4 = this.mLastChosen;
                if (resolveInfo4 != null && resolveInfo4.activityInfo.packageName.equals(resolveInfo3.activityInfo.packageName) && this.mLastChosen.activityInfo.name.equals(resolveInfo3.activityInfo.name)) {
                    this.mInitialHighlight = this.mList.size();
                }
                if (z) {
                    this.mList.add(new DisplayResolveInfo(resolveInfo3, charSequence, resolveInfo3.activityInfo.packageName, null));
                } else {
                    this.mList.add(new DisplayResolveInfo(resolveInfo3, charSequence, resolveInfo3.activityInfo.applicationInfo.loadLabel(ResolverActivity.this.mPm), null));
                }
                i++;
            }
        }

        public ResolveInfo resolveInfoForPosition(int i) {
            return this.mList.get(i).f10499ri;
        }

        public Intent intentForPosition(int i) {
            DisplayResolveInfo displayResolveInfo = this.mList.get(i);
            Intent intent = new Intent(displayResolveInfo.origIntent != null ? displayResolveInfo.origIntent : this.mIntent);
            intent.addFlags(50331648);
            ActivityInfo activityInfo = displayResolveInfo.f10499ri.activityInfo;
            intent.setComponent(new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name));
            return intent;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.mList.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return this.mList.get(i);
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = this.mInflater.inflate(C1713R.layout.resolve_list_item, viewGroup, false);
                ViewHolder viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
                ViewGroup.LayoutParams layoutParams = viewHolder.icon.getLayoutParams();
                int i2 = ResolverActivity.this.mIconSize;
                layoutParams.height = i2;
                layoutParams.width = i2;
            }
            bindView(view, this.mList.get(i));
            return view;
        }

        private final void bindView(View view, DisplayResolveInfo displayResolveInfo) {
            ViewHolder viewHolder = (ViewHolder) view.getTag();
            viewHolder.text.setText(displayResolveInfo.displayLabel);
            if (ResolverActivity.this.mShowExtended) {
                viewHolder.text2.setVisibility(0);
                viewHolder.text2.setText(displayResolveInfo.extendedInfo);
            } else {
                viewHolder.text2.setVisibility(8);
            }
            if (displayResolveInfo.displayIcon == null) {
                new LoadIconTask().execute(displayResolveInfo);
            }
            viewHolder.icon.setImageDrawable(displayResolveInfo.displayIcon);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class ItemLongClickListener implements AdapterView.OnItemLongClickListener {
        ItemLongClickListener() {
        }

        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
            ResolverActivity.this.showAppDetails(ResolverActivity.this.mAdapter.resolveInfoForPosition(i));
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class LoadIconTask extends AsyncTask<DisplayResolveInfo, Void, DisplayResolveInfo> {
        LoadIconTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public DisplayResolveInfo doInBackground(DisplayResolveInfo... displayResolveInfoArr) {
            DisplayResolveInfo displayResolveInfo = displayResolveInfoArr[0];
            if (displayResolveInfo.displayIcon == null) {
                displayResolveInfo.displayIcon = ResolverActivity.this.loadIconForResolveInfo(displayResolveInfo.f10499ri);
            }
            return displayResolveInfo;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void onPostExecute(DisplayResolveInfo displayResolveInfo) {
            ResolverActivity.this.mAdapter.notifyDataSetChanged();
        }
    }
}
