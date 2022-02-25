package com.tendcloud.tenddata;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import com.tendcloud.tenddata.C2723bn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.WeakHashMap;

/* compiled from: td */
@TargetApi(16)
/* renamed from: com.tendcloud.tenddata.bp */
/* loaded from: classes2.dex */
abstract class AbstractC2732bp implements C2723bn.AbstractC2724a {
    private final List mPath;
    private final C2723bn mPathfinder = new C2723bn();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.bp$f */
    /* loaded from: classes2.dex */
    public interface AbstractC2740f {
        void OnEvent(View view, String str, boolean z);
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.bp$g */
    /* loaded from: classes2.dex */
    interface AbstractC2741g {
        void onLayoutError(C2739e eVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void cleanup();

    protected abstract String name();

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.bp$e */
    /* loaded from: classes2.dex */
    static class C2739e {
        private final String mErrorType;
        private final String mName;

        public C2739e(String str, String str2) {
            this.mErrorType = str;
            this.mName = str2;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public String getErrorType() {
            return this.mErrorType;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public String getName() {
            return this.mName;
        }
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.bp$h */
    /* loaded from: classes2.dex */
    static class C2742h extends AbstractC2732bp {
        private final C2712bi mAccessor;
        private final C2712bi mMutator;
        private final Object[] mOriginalValueHolder = new Object[1];
        private final WeakHashMap mOriginalValues = new WeakHashMap();

        @Override // com.tendcloud.tenddata.AbstractC2732bp
        protected String name() {
            return "Property Mutator";
        }

        C2742h(List list, C2712bi biVar, C2712bi biVar2) {
            super(list);
            this.mMutator = biVar;
            this.mAccessor = biVar2;
        }

        @Override // com.tendcloud.tenddata.AbstractC2732bp
        public void cleanup() {
            for (Map.Entry entry : this.mOriginalValues.entrySet()) {
                View view = (View) entry.getKey();
                Object value = entry.getValue();
                if (value != null) {
                    Object[] objArr = this.mOriginalValueHolder;
                    objArr[0] = value;
                    this.mMutator.m16256a(view, objArr);
                }
            }
        }

        @Override // com.tendcloud.tenddata.C2723bn.AbstractC2724a
        public void accumulate(View view) {
            try {
                if (this.mAccessor != null) {
                    Object[] a = this.mMutator.m16258a();
                    if (1 == a.length) {
                        Object obj = a[0];
                        Object a2 = this.mAccessor.m16257a(view);
                        if (obj != a2) {
                            if (obj != null) {
                                if (!(obj instanceof Bitmap) || !(a2 instanceof Bitmap)) {
                                    if ((obj instanceof BitmapDrawable) && (a2 instanceof BitmapDrawable)) {
                                        Bitmap bitmap = ((BitmapDrawable) obj).getBitmap();
                                        Bitmap bitmap2 = ((BitmapDrawable) a2).getBitmap();
                                        if (bitmap != null && bitmap.sameAs(bitmap2)) {
                                            return;
                                        }
                                    } else if (obj.equals(a2)) {
                                        return;
                                    }
                                } else if (((Bitmap) obj).sameAs((Bitmap) a2)) {
                                    return;
                                }
                            }
                            if (!(a2 instanceof Bitmap) && !(a2 instanceof BitmapDrawable) && !this.mOriginalValues.containsKey(view)) {
                                this.mOriginalValueHolder[0] = a2;
                                if (this.mMutator.m16254a(this.mOriginalValueHolder)) {
                                    this.mOriginalValues.put(view, a2);
                                } else {
                                    this.mOriginalValues.put(view, null);
                                }
                            }
                        } else {
                            return;
                        }
                    }
                }
                this.mMutator.m16257a(view);
            } catch (Throwable unused) {
            }
        }
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.bp$c */
    /* loaded from: classes2.dex */
    static class C2737c {
        private C2737c() {
        }

        boolean hasCycle(TreeMap treeMap) {
            ArrayList arrayList = new ArrayList();
            while (!treeMap.isEmpty()) {
                if (!detectSubgraphCycle(treeMap, (View) treeMap.firstKey(), arrayList)) {
                    return false;
                }
            }
            return true;
        }

        private boolean detectSubgraphCycle(TreeMap treeMap, View view, List list) {
            try {
                if (list.contains(view)) {
                    return false;
                }
                if (!treeMap.containsKey(view)) {
                    return true;
                }
                List list2 = (List) treeMap.remove(view);
                list.add(view);
                int size = list2.size();
                for (int i = 0; i < size; i++) {
                    if (!detectSubgraphCycle(treeMap, (View) list2.get(i), list)) {
                        return false;
                    }
                }
                list.remove(view);
                return true;
            } catch (Throwable unused) {
                return true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.bp$a */
    /* loaded from: classes2.dex */
    public static class C2733a extends AbstractC2738d {
        private final int mEventType;
        private final WeakHashMap mWatching = new WeakHashMap();

        /* JADX INFO: Access modifiers changed from: package-private */
        public C2733a(List list, int i, String str, AbstractC2740f fVar) {
            super(list, str, fVar, false);
            this.mEventType = i;
        }

        @Override // com.tendcloud.tenddata.AbstractC2732bp
        public void cleanup() {
            try {
                for (Map.Entry entry : this.mWatching.entrySet()) {
                    View view = (View) entry.getKey();
                    C2734a aVar = (C2734a) entry.getValue();
                    View.AccessibilityDelegate oldDelegate = getOldDelegate(view);
                    if (oldDelegate == aVar) {
                        view.setAccessibilityDelegate(aVar.getRealDelegate());
                    } else if (oldDelegate instanceof C2734a) {
                        ((C2734a) oldDelegate).removeFromDelegateChain(aVar);
                    }
                }
                this.mWatching.clear();
            } catch (Throwable unused) {
            }
        }

        @Override // com.tendcloud.tenddata.C2723bn.AbstractC2724a
        public void accumulate(View view) {
            try {
                View.AccessibilityDelegate oldDelegate = getOldDelegate(view);
                if (!(oldDelegate instanceof C2734a) || !((C2734a) oldDelegate).willFireEvent(getEventName())) {
                    C2734a aVar = new C2734a(oldDelegate);
                    view.setAccessibilityDelegate(aVar);
                    this.mWatching.put(view, aVar);
                }
            } catch (Throwable unused) {
            }
        }

        @Override // com.tendcloud.tenddata.AbstractC2732bp
        protected String name() {
            return getEventName() + " event when (" + this.mEventType + ")";
        }

        private View.AccessibilityDelegate getOldDelegate(View view) {
            try {
                return (View.AccessibilityDelegate) view.getClass().getMethod("getAccessibilityDelegate", new Class[0]).invoke(view, new Object[0]);
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
                return null;
            }
        }

        /* compiled from: td */
        /* renamed from: com.tendcloud.tenddata.bp$a$a */
        /* loaded from: classes2.dex */
        class C2734a extends View.AccessibilityDelegate {
            private View.AccessibilityDelegate mRealDelegate;

            C2734a(View.AccessibilityDelegate accessibilityDelegate) {
                this.mRealDelegate = accessibilityDelegate;
            }

            View.AccessibilityDelegate getRealDelegate() {
                return this.mRealDelegate;
            }

            boolean willFireEvent(String str) {
                try {
                    if (C2733a.this.getEventName() == null || !C2733a.this.getEventName().equals(str)) {
                        if (!(this.mRealDelegate instanceof C2734a)) {
                            return false;
                        }
                        if (!((C2734a) this.mRealDelegate).willFireEvent(str)) {
                            return false;
                        }
                    }
                    return true;
                } catch (Throwable th) {
                    C2933hb.postSDKError(th);
                    return false;
                }
            }

            void removeFromDelegateChain(C2734a aVar) {
                try {
                    if (this.mRealDelegate == aVar) {
                        this.mRealDelegate = aVar.getRealDelegate();
                    } else if (this.mRealDelegate instanceof C2734a) {
                        ((C2734a) this.mRealDelegate).removeFromDelegateChain(aVar);
                    }
                } catch (Throwable th) {
                    C2933hb.postSDKError(th);
                }
            }

            @Override // android.view.View.AccessibilityDelegate
            public void sendAccessibilityEvent(View view, int i) {
                try {
                    if (i == C2733a.this.mEventType) {
                        C2733a.this.fireEvent(view);
                    }
                    if (this.mRealDelegate != null) {
                        this.mRealDelegate.sendAccessibilityEvent(view, i);
                    }
                } catch (Throwable th) {
                    C2933hb.postSDKError(th);
                }
            }
        }
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.bp$b */
    /* loaded from: classes2.dex */
    static class C2735b extends AbstractC2738d {
        private final Map mWatching = new HashMap();

        /* JADX INFO: Access modifiers changed from: package-private */
        public C2735b(List list, String str, AbstractC2740f fVar) {
            super(list, str, fVar, true);
        }

        @Override // com.tendcloud.tenddata.AbstractC2732bp
        public void cleanup() {
            try {
                for (Map.Entry entry : this.mWatching.entrySet()) {
                    ((TextView) entry.getKey()).removeTextChangedListener((TextWatcher) entry.getValue());
                }
                this.mWatching.clear();
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }

        @Override // com.tendcloud.tenddata.C2723bn.AbstractC2724a
        public void accumulate(View view) {
            try {
                if (view instanceof TextView) {
                    TextView textView = (TextView) view;
                    C2736a aVar = new C2736a(textView);
                    TextWatcher textWatcher = (TextWatcher) this.mWatching.get(textView);
                    if (textWatcher != null) {
                        textView.removeTextChangedListener(textWatcher);
                    }
                    textView.addTextChangedListener(aVar);
                    this.mWatching.put(textView, aVar);
                }
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }

        @Override // com.tendcloud.tenddata.AbstractC2732bp
        protected String name() {
            return getEventName() + " on Text Change";
        }

        /* compiled from: td */
        /* renamed from: com.tendcloud.tenddata.bp$b$a */
        /* loaded from: classes2.dex */
        class C2736a implements TextWatcher {
            private final View mBoundTo;

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            C2736a(View view) {
                this.mBoundTo = view;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                C2735b.this.fireEvent(this.mBoundTo);
            }
        }
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.bp$i */
    /* loaded from: classes2.dex */
    static class C2743i extends AbstractC2738d {
        private boolean mSeen = false;

        @Override // com.tendcloud.tenddata.AbstractC2732bp
        public void cleanup() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public C2743i(List list, String str, AbstractC2740f fVar) {
            super(list, str, fVar, false);
        }

        @Override // com.tendcloud.tenddata.C2723bn.AbstractC2724a
        public void accumulate(View view) {
            if (view != null && !this.mSeen) {
                fireEvent(view);
            }
            this.mSeen = view != null;
        }

        @Override // com.tendcloud.tenddata.AbstractC2732bp
        protected String name() {
            return getEventName() + " when Detected";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.bp$d */
    /* loaded from: classes2.dex */
    public static abstract class AbstractC2738d extends AbstractC2732bp {
        private final boolean mDebounce;
        private final String mEventName;
        private final AbstractC2740f mListener;

        AbstractC2738d(List list, String str, AbstractC2740f fVar, boolean z) {
            super(list);
            this.mListener = fVar;
            this.mEventName = str;
            this.mDebounce = z;
        }

        void fireEvent(View view) {
            try {
                this.mListener.OnEvent(view, this.mEventName, this.mDebounce);
            } catch (Throwable unused) {
            }
        }

        String getEventName() {
            return this.mEventName;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void visit(View view) {
        this.mPathfinder.m16236a(view, this.mPath, this);
    }

    protected AbstractC2732bp(List list) {
        this.mPath = list;
    }

    protected List getPath() {
        return this.mPath;
    }

    protected C2723bn getPathfinder() {
        return this.mPathfinder;
    }
}
